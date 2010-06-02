/*
 * Copyright 2008 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.gwt.jsio.rebind;

import java.lang.reflect.Field;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.JParameter;
import com.google.gwt.core.ext.typeinfo.JPrimitiveType;
import com.google.gwt.jsio.client.FieldName;
import com.google.gwt.jsio.client.Global;
import com.google.gwt.jsio.client.JSWrapper;

/**
 * Defines one or more methods to be implemented by JSWrapperGenerator.
 */
// XXX Refactor into type-specific subtypes
class Task {
  JMethod getter;
  JMethod setter;
  JMethod binding;
  JMethod imported;
  JMethod exported;
  JMethod constructor;

  /**
   * Determines the field name to be used by the methods associated with the
   * Task.
   * 
   * @param logger the TreeLogger in use by the enclosing content
   * @return the JSO property/Wrapper field name to use when generating the
   *         methods in the Task
   * @throws UnableToCompleteException
   */
  public String getFieldName(TreeLogger logger)
      throws UnableToCompleteException {
    logger = logger.branch(TreeLogger.DEBUG, "Determining field name for Task",
        null);
    if (getter != null) {
      return extractFieldName(logger, getter, false);
    } else if (setter != null) {
      return extractFieldName(logger, setter, false);
    } else if (binding != null) {
      return extractFieldName(logger, binding, true);
    } else if (exported != null) {
      return extractFieldName(logger, exported, true);
    } else if (imported != null) {
      return extractFieldName(logger, imported, true);
    } else if (constructor != null) {
      return extractFieldName(logger, constructor, true);
    } else {
      logger.log(TreeLogger.ERROR, "Unable to determine field name", null);
      throw new UnableToCompleteException();
    }
  }

  /**
   * @return the name of the method that was defined in Java source. Retuns null
   *         on failure.
   */
  public String getJavaMethodName() {
    if (getter != null) {
      return getter.getName();
    } else if (setter != null) {
      return setter.getName();
    } else if (binding != null) {
      return binding.getName();
    } else if (exported != null) {
      return exported.getName();
    } else if (imported != null) {
      return imported.getName();
    } else if (constructor != null) {
      return constructor.getName();
    }
    return null;
  }

  /**
   * Validation check to ensure that at least one method is defined within the
   * Task.
   * 
   * @return <code>true</code> if there is at least one method defined for the
   *         Task.
   */
  public boolean hasMethods() {
    return (getter != null) || (setter != null) || (imported != null)
        || (exported != null) || (constructor != null) || (binding != null);
  }

  /**
   * Validates the task within the specified context.
   * 
   * @return <code>true</code> if an error is detected in the task
   */
  public boolean validate(JSWrapperGenerator generator,
      FragmentGeneratorContext context) {
    TreeLogger logger = context.parentLogger.branch(TreeLogger.DEBUG,
        "Validating task " + getJavaMethodName() + "().", null);
    JClassType jsoType = context.typeOracle.findType(JavaScriptObject.class.getName());

    if ((imported != null) && ((getter != null) || (setter != null))) {
      logger.log(TreeLogger.ERROR, "Imported functions may not be combined "
          + "with bean-style accessors", null);
      return true;
    }

    // If there are no methods attached to a task, we've encountered an
    // internal error.
    if (!hasMethods()) {
      logger.log(TreeLogger.ERROR, "No methods for task.", null);
      return true;
    }

    if (constructor != null) {
      JClassType returnType = constructor.getReturnType().isClassOrInterface();
      JClassType wrapperType = context.typeOracle.findType(JSWrapper.class.getName());

      if (!(jsoType.isAssignableFrom(returnType) || constructor.getEnclosingType().isAssignableFrom(
          returnType))) {
        logger.log(TreeLogger.ERROR,
            "Methods annotated with @gwt.constructor or @gwt.global must "
                + "return a JavaScriptObject or their enclosing class.", null);
        return true;
      }

      try {
        if (wrapperType.isAssignableFrom(returnType)
            && JSWrapperGenerator.hasTag(logger, constructor, Global.class) != null) {
          logger.log(TreeLogger.ERROR,
              "Cannot place @gwt.global annotation on JSWrapper methods."
                  + " Apply to the class or interface instead.", null);
          return true;
        }
      } catch (UnableToCompleteException e) {
        // Already logged the error in hasTag, just return here.
        return true;
      }
    }

    // Sanity check that we picked up the right setter
    if ((getter != null)
        && (setter != null)
        && !getter.getReturnType().equals(
            generator.getSetterParameter(setter).getType())) {
      logger.log(TreeLogger.ERROR, "Setter " + setter.getName()
          + " has different parameter type " + "from getter "
          + getter.getName(), null);
      return true;
    }

    if (exported != null) {
      if (context.readOnly) {
        // If the interface is read-only, we couldn't add the function linkage
        logger.log(TreeLogger.ERROR, "Cannot export function "
            + exported.getName() + " in read-only wrapper.", null);
        return true;

      } else if (!context.maintainIdentity && !exported.isStatic()) {
        // If we have no __gwtPeer object, only allow static methods
        logger.log(TreeLogger.ERROR, "Cannot export instance function "
            + exported.getName() + " in noIdentity wrapper.", null);
        return true;
      }
    }

    if (binding != null) {
      if (!JPrimitiveType.VOID.equals(binding.getReturnType().isPrimitive())) {
        logger.log(TreeLogger.ERROR,
            "Binding functions must have a void type.", null);
        return true;
      }

      JParameter[] params = binding.getParameters();
      if (params.length == 0
          || !jsoType.isAssignableFrom(params[0].getType().isClassOrInterface())) {
        logger.log(TreeLogger.ERROR, "The first parameter of a binding method "
            + "must be a JavaScriptObject", null);
        return true;
      }
    }

    return false;
  }

  /**
   * Determine the correct field name to use for a method.
   */
  protected String extractFieldName(TreeLogger logger, JMethod m,
      boolean imported) throws UnableToCompleteException {
    logger = logger.branch(TreeLogger.DEBUG, "Extracting field name", null);

    FieldName fieldNameAnnotation = JSWrapperGenerator.hasTag(logger, m,
        FieldName.class);

    if (fieldNameAnnotation == null) {
      // If the method is imported and there's no overriding annotation,
      // just return the methods original name. This ensures that native
      // JS methods that look like bean getter/setters don't get munged.
      if (imported) {
        return m.getName();
      }

      // If no gwt.fieldName is specified, see if there's a naming policy
      // defined on the enclosing class.
      JClassType enclosing = m.getEnclosingType();
      com.google.gwt.jsio.client.NamePolicy namePolicyAnnotation = JSWrapperGenerator.hasTag(
          logger, enclosing, com.google.gwt.jsio.client.NamePolicy.class);
      NamePolicy policy;

      // If there is no namePolicy or it's not of the desired form, default
      // to the JavaBean-style naming policy.
      if (namePolicyAnnotation == null) {
        policy = NamePolicy.BEAN;
        logger.log(TreeLogger.DEBUG, "No useful policy metadata for class",
            null);

      } else {
        // Use the provided name to access fields within NamePolicy
        String policyName = namePolicyAnnotation.value();
        try {
          Field f = NamePolicy.class.getDeclaredField(policyName.toUpperCase());
          policy = (NamePolicy) f.get(null);

        } catch (IllegalAccessException e) {
          logger.log(TreeLogger.ERROR, "Bad gwt.namePolicy " + policyName, e);
          throw new UnableToCompleteException();

        } catch (NoSuchFieldException e) {
          // This means that the value specified is not a field, but likely
          // a class name. Try instantiating one and seeing if it's a
          // subclass of NamePolicy.
          try {
            Class<? extends NamePolicy> clazz = Class.forName(policyName).asSubclass(
                NamePolicy.class);
            policy = clazz.newInstance();
          } catch (ClassCastException ee) {
            logger.log(TreeLogger.ERROR,
                "@gwt.namePolicy is not an implementation of NamePolicy", null);
            throw new UnableToCompleteException();
          } catch (ClassNotFoundException ee) {
            logger.log(TreeLogger.ERROR, "Bad gwt.namePolicy " + policyName, ee);
            throw new UnableToCompleteException();
          } catch (IllegalAccessException ee) {
            logger.log(TreeLogger.ERROR, "Bad gwt.namePolicy " + policyName, ee);
            throw new UnableToCompleteException();
          } catch (InstantiationException ee) {
            logger.log(TreeLogger.ERROR, "Bad gwt.namePolicy " + policyName, ee);
            throw new UnableToCompleteException();
          }
        }
      }

      // Execute the conversion
      String propertyName = TaskFactory.getPropertyNameFromMethod(m);
      return policy.convert(propertyName);

    } else {
      // Use the field name specified on the method
      logger.log(TreeLogger.DEBUG, "Overriding field name based on annotation",
          null);
      return fieldNameAnnotation.value();
    }
  }
}

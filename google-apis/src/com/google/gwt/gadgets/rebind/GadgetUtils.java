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
package com.google.gwt.gadgets.rebind;

import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JGenericType;
import com.google.gwt.core.ext.typeinfo.JParameterizedType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.gadgets.client.Gadget;
import com.google.gwt.gadgets.client.impl.PreferenceGeneratorName;

import org.w3c.dom.Element;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Utility methods for Gadget Generators.
 */
class GadgetUtils {
  /**
   * Return an instance of a PreferenceGenerator that can be used for a subtype
   * of Preference.
   */
  static PreferenceGenerator getPreferenceGenerator(TreeLogger logger,
      JClassType extendsPreferenceType) throws UnableToCompleteException {

    PreferenceGeneratorName generator = extendsPreferenceType.getAnnotation(PreferenceGeneratorName.class);

    if (generator == null) {
      logger.log(TreeLogger.ERROR, "No PreferenceGenerator defined for type "
          + extendsPreferenceType.getQualifiedSourceName(), null);
      throw new UnableToCompleteException();
    }

    try {
      String typeName = generator.value();

      Class<? extends PreferenceGenerator> clazz = Class.forName(typeName).asSubclass(
          PreferenceGenerator.class);
      return clazz.newInstance();
    } catch (ClassCastException e) {
      logger.log(TreeLogger.ERROR, "Not a PreferenceGenerator", e);
      throw new UnableToCompleteException();
    } catch (ClassNotFoundException e) {
      logger.log(TreeLogger.ERROR, "Unable to create PreferenceGenerator", e);
      throw new UnableToCompleteException();
    } catch (InstantiationException e) {
      logger.log(TreeLogger.ERROR, "Unable to create PreferenceGenerator", e);
      throw new UnableToCompleteException();
    } catch (IllegalAccessException e) {
      logger.log(TreeLogger.ERROR, "Unable to create PreferenceGenerator", e);
      throw new UnableToCompleteException();
    }
  }

  /**
   * Returns the subtype of UserPreferencesaccepted by a Gadget.
   */
  static JClassType getUserPrefsType(TreeLogger logger, TypeOracle typeOracle,
      JClassType extendsGadget) throws UnableToCompleteException {
    JGenericType gadgetType = typeOracle.findType(Gadget.class.getName()).isGenericType();
    assert gadgetType != null;

    JParameterizedType paramType = null;

    while (true) {
      paramType = extendsGadget.isParameterized();

      if (paramType != null && gadgetType.equals(paramType.getBaseType())) {
        break;
      }

      extendsGadget = extendsGadget.getSuperclass();

      if (extendsGadget == null) {
        logger.log(TreeLogger.ERROR, "Unable to find Gadget in type hierarchy",
            null);
        throw new UnableToCompleteException();
      }
    }

    JClassType[] typeArgs = paramType.getTypeArgs();

    assert typeArgs.length == 1;
    JClassType toReturn = typeArgs[0].isClassOrInterface();
    if (toReturn == null) {
      logger.log(TreeLogger.ERROR,
          "A Gadget's UserPreferences type must be an interface", null);
      throw new UnableToCompleteException();
    }

    return toReturn;
  }

  /**
   * Add the key-value pairs of an Annotation to an Element. Enumerated
   * properties will be ignored.
   */
  static void writeAnnotationToElement(TreeLogger logger, Annotation a,
      Element elt) throws UnableToCompleteException {
    for (Method m : a.annotationType().getDeclaredMethods()) {
      try {
        String name = m.getName();
        Object value = m.invoke(a);
        if (value instanceof Enum) {
          continue;
        } else if (!m.getDefaultValue().equals(value)) {
          elt.setAttribute(name, value.toString());
        }
      } catch (IllegalAccessException e) {
        logger.log(TreeLogger.ERROR, "Could not decode annotation", e);
        throw new UnableToCompleteException();
      } catch (InvocationTargetException e) {
        logger.log(TreeLogger.ERROR, "Could not decode annotation", e);
        throw new UnableToCompleteException();
      }
    }
  }

  /**
   * Utility class.
   */
  private GadgetUtils() {
  }
}

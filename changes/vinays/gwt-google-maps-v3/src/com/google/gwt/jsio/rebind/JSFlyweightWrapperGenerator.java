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

import java.util.Map;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JField;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.JParameter;
import com.google.gwt.core.ext.typeinfo.JParameterizedType;
import com.google.gwt.core.ext.typeinfo.JPrimitiveType;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.jsio.client.Binding;
import com.google.gwt.jsio.client.Constructor;
import com.google.gwt.jsio.client.Global;
import com.google.gwt.user.rebind.SourceWriter;

/**
 * Generates a flyweight-style JSIO interface.
 */
public class JSFlyweightWrapperGenerator extends JSWrapperGenerator {
  /**
   * The name of a static method that can be implemented in a class so that it
   * can receive a peer object. It must accept a JSO.
   */
  public static final String CREATE_PEER = "createPeer";

  @Override
  protected int getImportOffset() {
    return 1;
  }

  @Override
  protected TaskFactory.Policy getPolicy() {
    return TaskFactory.FLYWEIGHT_POLICY;
  }

  @Override
  protected JParameter getSetterParameter(JMethod setter) {
    return setter.getParameters()[1];
  }

  /**
   * Sets the objRef field on a FragmentGeneratorContext to refer to the correct
   * JavaScriptObject.
   */
  protected void setObjRef(FragmentGeneratorContext context, JMethod method)
      throws UnableToCompleteException {
    JParameter[] paramList = method.getParameters();
    if (paramList.length == 0) {
      context.parentLogger.branch(TreeLogger.ERROR,
          "No parameters specified for method " + method.getName()
              + "().  (First parameter must be a JavaScriptObject.)", null);
      throw new UnableToCompleteException();
    }
    JParameter param = method.getParameters()[0];
    JClassType paramType = param.getType().isClassOrInterface();
    JField f;

    if (context.typeOracle.findType(JavaScriptObject.class.getName()).equals(
        paramType)) {
      context.objRef = param.getName();

    } else if ((f = PeeringFragmentGenerator.findPeer(context.typeOracle,
        paramType)) != null) {
      context.objRef = param.getName() + ".@"
          + f.getEnclosingType().getQualifiedSourceName() + "::" + f.getName();

    } else {
      context.parentLogger.branch(TreeLogger.ERROR,
          "Invalid first parameter type for flyweight imported function. "
              + "It is not a JavaScriptObject and it lacks a jsoPeer field.",
          null);
      throw new UnableToCompleteException();
    }
  }

  protected void writeBinding(FragmentGeneratorContext context, JMethod binding)
      throws UnableToCompleteException {
    TreeLogger logger = context.parentLogger.branch(TreeLogger.DEBUG,
        "Writing binding function", null);
    context = new FragmentGeneratorContext(context);
    context.parentLogger = logger;

    SourceWriter sw = context.sw;
    TypeOracle typeOracle = context.typeOracle;
    Binding bindingAnnotation = JSWrapperGenerator.hasTag(logger, binding,
        Binding.class);

    // Write the java half to add assertions to the code. These will be elided
    // in web mode, and the method become a pure delegation, allowing it to
    // be removed completely
    sw.print("public void ");
    sw.print(binding.getName());
    sw.print("(");
    JParameter[] params = binding.getParameters();

    context.parameterName = "jso";
    sw.print(params[0].getType().getQualifiedSourceName());
    sw.print(" ");
    sw.print(context.parameterName);

    JClassType bindingType = null;
    if (params.length == 2) {
      // Infer the binding type from the second parameter of the binding
      // method.
      bindingType = params[1].getType().isClassOrInterface();
      context.objRef = "obj";

      sw.print(", ");
      sw.print(bindingType.getQualifiedSourceName());
      sw.print(" ");
      sw.print(context.objRef);
    } else if (bindingAnnotation != null
        && bindingAnnotation.value().length() > 0) {
      // Use the binding type specified in the the gwt.binding annotation.
      bindingType = typeOracle.findType(bindingAnnotation.value());
      if (bindingType == null) {
        logger.log(TreeLogger.ERROR, "Could not resolve binding type "
            + bindingType, null);
        throw new UnableToCompleteException();
      }
    }

    sw.println(") {");
    sw.indent();

    for (Task t : context.tasks) {
      if (t.imported != null) {
        String fieldName = t.getFieldName(logger);
        sw.print("assert JSONWrapperUtil.hasField(");
        sw.print(context.parameterName);
        sw.print(", \"");
        sw.print(fieldName);
        sw.print("\") : \"Backing JSO missing imported function ");
        sw.print(fieldName);
        sw.println("\";");
      }
    }

    sw.print(binding.getName());
    sw.print("Native (");
    sw.print(context.parameterName);
    if (params.length == 2) {
      sw.print(",");
      sw.print(context.objRef);
    }
    sw.println(");");
    sw.outdent();
    sw.println("}");

    // Write the native half to perform the actual binding operations
    sw.print("public native void ");
    sw.print(binding.getName());
    sw.print("Native (");
    sw.print(params[0].getType().getQualifiedSourceName());
    sw.print(" ");
    sw.print(context.parameterName);
    if (params.length == 2) {
      sw.print(", ");
      sw.print(bindingType.getQualifiedSourceName());
      sw.print(" ");
      sw.print(context.objRef);
    }
    sw.println(") /*-{");
    sw.indent();

    // A binding should have been declared void
    context.returnType = JPrimitiveType.VOID;

    if (context.maintainIdentity && params.length == 2) {
      // XXX link the Java object to the JSO?

      // Verify that the incoming object doesn't already have a wrapper object.
      // If there is a backreference, throw an exception.
      sw.print("if (");
      sw.print(context.parameterName);
      sw.print(".");
      sw.print(BACKREF);
      sw.println(") {");
      sw.indent();
      sw.println("@com.google.gwt.jsio.client.impl.JSONWrapperUtil::throwMultipleWrapperException()();");
      sw.outdent();
      sw.println("}");

      // Assign the backreference from the JSO object to the delegate
      sw.print(context.parameterName);
      sw.print(".");
      sw.print(BACKREF);
      sw.print(" = ");
      sw.print(context.objRef);
      sw.println(";");
    }

    writeEmptyFieldInitializers(context);

    if (bindingType != null) {
      // Extract the exported methods
      context.tasks = TaskFactory.extractMethods(logger, typeOracle,
          bindingType, TaskFactory.EXPORTER_POLICY).values();
      writeMethodBindings(context);
    } else {
      logger.log(TreeLogger.DEBUG,
          "Not binding methods to any particular type.", null);
    }

    sw.outdent();
    sw.println("}-*/;");
  }

  /**
   * Writes common boilerplate code for all implementations.
   */
  @Override
  protected void writeBoilerplate(final TreeLogger logger,
      final FragmentGeneratorContext context) throws UnableToCompleteException {
  }

  @Override
  protected void writeConstructor(FragmentGeneratorContext context,
      JMethod constructor) throws UnableToCompleteException {

    TreeLogger logger = context.parentLogger.branch(TreeLogger.DEBUG,
        "Writing constructor " + constructor.getName(), null);
    SourceWriter sw = context.sw;

    JParameter[] parameters = constructor.getParameters();
    if (parameters == null) {
      parameters = new JParameter[0];
    }

    // Method declaration
    sw.print("public native ");
    sw.print(constructor.getReturnType().getQualifiedSourceName());
    sw.print(" ");
    sw.print(constructor.getName());
    sw.print("(");
    for (int i = 0; i < parameters.length; i++) {
      JType returnType = parameters[i].getType();
      JParameterizedType pType = returnType.isParameterized();

      if (pType != null) {
        sw.print(pType.getRawType().getQualifiedSourceName());
      } else {
        sw.print(returnType.getQualifiedSourceName());
      }

      sw.print(" ");
      sw.print(parameters[i].getName());

      if (i < parameters.length - 1) {
        sw.print(", ");
      }
    }
    sw.print(")");
    sw.println(" /*-{");
    sw.indent();

    JType returnType = constructor.getReturnType();

    FragmentGeneratorContext subContext = new FragmentGeneratorContext(context);
    subContext.returnType = returnType;
    subContext.parameterName = "jsReturn";
    subContext.objRef = "jsReturn";

    sw.print("var ");
    sw.print(subContext.objRef);
    sw.print(" = ");

    Constructor constructorAnnotation = hasTag(logger, constructor,
        Constructor.class);
    Global globalAnnotation = hasTag(logger, constructor, Global.class);
    if (constructorAnnotation != null) {
      // If the imported method is acting as an invocation of a JavaScript
      // constructor, use the new Foo() syntax, otherwise treat is an an
      // invocation on a field on the underlying JSO.
      sw.print("new ");
      sw.print(constructorAnnotation.value());

      // Write the invocation's parameter list
      sw.print("(");
      for (int i = 0; i < parameters.length; i++) {
        // Create a sub-context to generate the wrap/unwrap logic
        JType subType = parameters[i].getType();
        FragmentGeneratorContext subParams = new FragmentGeneratorContext(
            context);
        subParams.returnType = subType;
        subParams.parameterName = parameters[i].getName();

        FragmentGenerator fragmentGenerator = context.fragmentGeneratorOracle.findFragmentGenerator(
            logger, context.typeOracle, subType);
        if (fragmentGenerator == null) {
          logger.log(TreeLogger.ERROR, "No fragment generator for "
              + returnType.getQualifiedSourceName(), null);
          throw new UnableToCompleteException();
        }

        fragmentGenerator.toJS(subParams);

        if (i < parameters.length - 1) {
          sw.print(", ");
        }
      }
      sw.print(")");

    } else if (globalAnnotation != null) {
      sw.print(globalAnnotation.value());

    } else {
      logger.log(TreeLogger.ERROR,
          "Writing a constructor, but no constructor-appropriate annotations",
          null);
      throw new UnableToCompleteException();
    }
    sw.println(";");

    writeEmptyFieldInitializers(subContext);

    sw.print("return ");
    sw.print(subContext.objRef);
    sw.println(";");

    sw.outdent();
    sw.println("}-*/;");
  }

  /**
   * This is a no-op in the flyweight style.
   */
  @Override
  protected void writeEmptyFieldInitializerMethod(final TreeLogger logger,
      final Map<String, Task> propertyAccessors,
      final FragmentGeneratorContext context) throws UnableToCompleteException {
  }

  @Override
  protected void writeGetter(FragmentGeneratorContext context, JMethod getter)
      throws UnableToCompleteException {

    context = new FragmentGeneratorContext(context);
    setObjRef(context, getter);
    context.parameterName = context.objRef + "." + context.fieldName;

    super.writeGetter(context, getter);
  }

  @Override
  protected void writeImported(FragmentGeneratorContext context,
      JMethod imported) throws UnableToCompleteException {

    context = new FragmentGeneratorContext(context);

    // It's invalid to have an imported method without a leading JSO object
    if (imported.getParameters().length > 0) {
      setObjRef(context, imported);
    } else {
      context.parentLogger.branch(TreeLogger.ERROR,
          "Imported methods in a flyweight interface must have a leading "
              + "JavaScriptObject parameter", null);
      throw new UnableToCompleteException();
    }

    super.writeImported(context, imported);
  }

  @Override
  protected void writeSetter(FragmentGeneratorContext context, JMethod setter)
      throws UnableToCompleteException {

    context = new FragmentGeneratorContext(context);
    setObjRef(context, setter);

    super.writeSetter(context, setter);
  }

  @Override
  protected void writeSingleTask(FragmentGeneratorContext context, Task task)
      throws UnableToCompleteException {
    if (task.binding != null) {
      writeBinding(context, task.binding);
    } else {
      super.writeSingleTask(context, task);
    }
  }
}

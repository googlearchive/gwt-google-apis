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

import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.HasAnnotations;
import com.google.gwt.core.ext.typeinfo.HasMetaData;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.JParameter;
import com.google.gwt.core.ext.typeinfo.JParameterizedType;
import com.google.gwt.core.ext.typeinfo.JPrimitiveType;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.jsio.client.Constructor;
import com.google.gwt.jsio.client.Global;
import com.google.gwt.jsio.client.JSWrapper;
import com.google.gwt.jsio.client.NoIdentity;
import com.google.gwt.jsio.client.ReadOnly;
import com.google.gwt.jsio.client.impl.MetaDataName;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;

/**
 * The Generator that provides implementations of JSWrapper.
 */
public class JSWrapperGenerator extends Generator {

  /**
   * The name of the field within the backing object that refers back to the
   * JSWrapper object.
   */
  public static final String BACKREF = "__gwtPeer";

  /**
   * The name of the static field that contains the class's Extractor instance.
   */
  protected static final String EXTRACTOR = "__extractor";

  /**
   * Singleton instance of the FragmentGeneratorOracle for the system.
   */
  protected static final FragmentGeneratorOracle FRAGMENT_ORACLE = new FragmentGeneratorOracle();

  /**
   * The name of the backing object field.
   */
  protected static final String OBJ = "jsoPeer";

  /**
   * Allows the metadata warning to be turned off to prevent log spam.
   */
  private static final boolean SUPPRESS_WARNINGS = Boolean.getBoolean("JSWrapper.suppressMetaWarnings");

  /**
   * Extract an Annotation. If the requested Annotation does not exist on the
   * target node, the target's metadata will be examined for a tag based on the
   * requested Annotation's {@link MetaDataName} meta-annotation. If the target
   * has metadata which can be interpreted as the return type of the requested
   * Annotation's value method, a {@link Proxy} will be synthesized. The proxy
   * mode is only to support existing functionality, all new features should be
   * added via new annotations.
   * 
   * @param <A> the desired type of Annotation
   * @param <M> the type of object to search
   * @param logger a logger
   * @param target the object to search
   * @param annotation the desired type of annotation
   * @return an instance of the requested annotation, or <code>null</code> if
   *         the annotation is not present and an instance of the annotation
   *         cannot be synthesized due to lack of metadata
   * @throws UnableToCompleteException if metadata with the correct tag exists
   *           but cannot be interpreted as the return type of the annotation's
   *           value method
   */
  @SuppressWarnings("deprecation")
  static <A extends Annotation, M extends HasAnnotations & HasMetaData> A hasTag(
      TreeLogger logger, M target, final Class<A> annotation)
      throws UnableToCompleteException {
    logger = logger.branch(TreeLogger.TRACE, "Looking for annotation/meta "
        + annotation.getName(), null);

    // If the item has an annotation of the requested type, return it
    A toReturn = target.getAnnotation(annotation);
    if (toReturn != null) {
      logger.log(TreeLogger.TRACE, "Found Annotation instance", null);
      return toReturn;
    }

    // Otherwise, fall back to HasMetaData
    MetaDataName metaDataName = annotation.getAnnotation(MetaDataName.class);
    if (metaDataName == null) {
      // Indicates that the requested annotation doesn't have legacy support
      logger.log(TreeLogger.TRACE, "No legacy support for this annotation",
          null);
      return null;
    }

    String tagName = metaDataName.value();
    boolean hasTag = false;
    for (String tag : target.getMetaDataTags()) {
      if (tagName.equals(tag)) {
        hasTag = true;
        if (!SUPPRESS_WARNINGS) {
          logger.log(TreeLogger.WARN, target
              + " uses deprecated metadata.  Replace with annotation "
              + annotation.getName(), null);
        }
        break;
      }
    }
    if (!hasTag) {
      logger.log(TreeLogger.TRACE, "No metadata with tag " + tagName, null);
      return null;
    }

    Object value;
    try {
      Method valueMethod = annotation.getMethod("value");
      Class<?> returnType = valueMethod.getReturnType();
      String[][] metaData = target.getMetaData(metaDataName.value());

      // Use the default value if there's no value in the metadata
      Object annotationDefaultValue;
      try {
        annotationDefaultValue = annotation.getMethod("value").getDefaultValue();
      } catch (NoSuchMethodException e) {
        annotationDefaultValue = null;
      }

      if (annotationDefaultValue == null && metaData.length == 0) {
        logger.log(TreeLogger.ERROR, "Metadata " + tagName
            + " must appear exactly once", null);
        throw new UnableToCompleteException();

      } else if (returnType.equals(String.class)) {
        if (metaData[0].length == 1) {
          logger.log(TreeLogger.TRACE, "Using value from metadata", null);
          value = metaData[0][0];

        } else if (annotationDefaultValue != null) {
          value = annotationDefaultValue;
        } else {
          logger.log(TreeLogger.ERROR, "Metadata " + tagName
              + " must have exactly one value", null);
          throw new UnableToCompleteException();
        }

      } else if (annotationDefaultValue != null) {
        logger.log(TreeLogger.TRACE, "Using annotation's default value", null);
        value = annotationDefaultValue;

      } else {
        logger.log(TreeLogger.ERROR, "Can't deal with return type "
            + returnType.getName(), null);
        throw new UnableToCompleteException();
      }
    } catch (NoSuchMethodException e) {
      // Just a tag annotation
      value = null;
    }

    final Object finalValue = value;
    Object proxy = Proxy.newProxyInstance(annotation.getClassLoader(),
        new Class<?>[] {annotation}, new InvocationHandler() {
          public Object invoke(Object proxy, Method method, Object[] args)
              throws Throwable {
            String name = method.getName();
            if (name.equals("annotationType")) {
              return annotation;
            } else if (name.equals("hashCode")) {
              return 0;
            } else if (name.equals("toString")) {
              return "Proxy for type " + annotation.getName() + " : "
                  + finalValue;
            } else if (name.equals("value")) {
              return finalValue;
            } else if (method.getDefaultValue() != null) {
              return method.getDefaultValue();
            }
            throw new RuntimeException("Don't know how to service " + name
                + " in type " + method.getDeclaringClass().getName());
          }
        });

    return annotation.cast(proxy);
  }

  /**
   * Get the erased type of the parameterization of the JSWrapper. Returns
   * <code>null</code> if JSWrapper is not in the class's inhertence
   * hierarchy.
   */
  private static JClassType findJSWrapperParameterization(TypeOracle oracle,
      JClassType extendsJSWrapper) {

    // Break recursion
    if (extendsJSWrapper == null) {
      return null;
    }

    // Are we looking at JSWrapper<T>; if so, return it's parameterization
    JClassType rawJSWrapper = oracle.findType(JSWrapper.class.getName()).getErasedType();
    JParameterizedType asParam = extendsJSWrapper.isParameterized();
    if (asParam != null && asParam.getErasedType().equals(rawJSWrapper)) {
      return asParam.getTypeArgs()[0].getErasedType();
    }

    // Try the supertype
    JClassType toReturn = findJSWrapperParameterization(oracle,
        extendsJSWrapper.getSuperclass());
    if (toReturn != null) {
      return toReturn;
    }

    // Not in the supertype hierarchy, search the interfaces
    for (JClassType implemented : extendsJSWrapper.getImplementedInterfaces()) {
      toReturn = findJSWrapperParameterization(oracle, implemented);
      if (toReturn != null) {
        return toReturn;
      }
    }

    // No type for you
    return null;
  }

  /**
   * Entry point into the Generator.
   */
  public final String generate(TreeLogger logger, GeneratorContext context,
      java.lang.String typeName) throws UnableToCompleteException {

    // The TypeOracle knows about all types in the type system
    final TypeOracle typeOracle = context.getTypeOracle();

    // Get a reference to the type that the generator should implement
    final JClassType sourceType = typeOracle.findType(typeName);

    // Ensure that the requested type exists
    if (sourceType == null) {
      logger.log(TreeLogger.ERROR, "Could not find requested typeName", null);
      throw new UnableToCompleteException();
    }

    // Pick a name for the generated class to not conflict. Enclosing class
    // names must be preserved.
    final String generatedSimpleSourceName = "__"
        + sourceType.getName().replaceAll("\\.", "__") + "Impl";

    // Begin writing the generated source.
    final ClassSourceFileComposerFactory f = new ClassSourceFileComposerFactory(
        sourceType.getPackage().getName(), generatedSimpleSourceName);

    // Pull in source imports
    f.addImport(GWT.class.getName());
    f.addImport(JavaScriptObject.class.getName());
    // This is a cheat, but doesn't require excessive maintenance
    f.addImport("com.google.gwt.jsio.client.*");
    f.addImport("com.google.gwt.jsio.client.impl.*");

    // Either extend an abstract base class or implement the interface
    if (sourceType.isClass() != null) {
      f.setSuperclass(sourceType.getQualifiedSourceName());

    } else if (sourceType.isInterface() != null) {
      f.addImplementedInterface(sourceType.getQualifiedSourceName());

    } else {
      // Something is very wrong if this statement is reached
      logger.log(TreeLogger.ERROR,
          "Requested JClassType is neither a class nor an interface.", null);
      throw new UnableToCompleteException();
    }

    // All source gets written through this Writer
    final PrintWriter out = context.tryCreate(logger,
        sourceType.getPackage().getName(), generatedSimpleSourceName);

    // If an implementation already exists, we don't need to do any work
    if (out != null) {
      // We really use a SourceWriter since it's convenient
      final SourceWriter sw = f.createSourceWriter(context, out);

      final Map<String, Task> propertyAccessors = TaskFactory.extractMethods(
          logger, typeOracle, sourceType, getPolicy());

      // Create the base context to be used during generation
      FragmentGeneratorContext fragmentContext = new FragmentGeneratorContext();
      fragmentContext.parentLogger = logger;
      fragmentContext.fragmentGeneratorOracle = FRAGMENT_ORACLE;
      fragmentContext.typeOracle = typeOracle;
      fragmentContext.sw = sw;
      fragmentContext.objRef = "this.@" + f.getCreatedClassName() + "::" + OBJ;
      fragmentContext.simpleTypeName = generatedSimpleSourceName;
      fragmentContext.qualifiedTypeName = f.getCreatedClassName();
      fragmentContext.returnType = sourceType;
      fragmentContext.creatorFixups = new HashSet<JClassType>();
      fragmentContext.readOnly = hasTag(logger, sourceType, ReadOnly.class) != null;
      fragmentContext.maintainIdentity = !(fragmentContext.readOnly || hasTag(
          logger, sourceType, NoIdentity.class) != null);
      fragmentContext.tasks = propertyAccessors.values();

      // Perform sanity checks on the extracted information
      validateType(propertyAccessors, fragmentContext);

      // Write all code that's not implementing methods
      writeBoilerplate(logger, fragmentContext);

      // Write the JSO initializer if required
      if (!fragmentContext.readOnly) {
        writeEmptyFieldInitializerMethod(logger, propertyAccessors,
            fragmentContext);
      }

      writeMethods(fragmentContext, propertyAccessors);
      writeFixups(logger, typeOracle, sw, fragmentContext.creatorFixups);

      // Write the generated code to disk
      sw.commit(logger);
    }

    // Return the name of the concrete class
    return f.getCreatedClassName();
  }

  /**
   * Specifies the first parameter of imported methods to pass to the imported
   * JavaScript function.
   */
  protected int getImportOffset() {
    return 0;
  }

  protected TaskFactory.Policy getPolicy() {
    return TaskFactory.WRAPPER_POLICY;
  }

  /**
   * Extracts the parameter from a setter method that contains the value to
   * store into the backing object.
   */
  protected JParameter getSetterParameter(JMethod setter) {
    return setter.getParameters()[0];
  }

  /**
   * Aggregate pre-write validation checks.
   */
  protected void validateType(Map<String, Task> propertyAccessors,
      FragmentGeneratorContext context) throws UnableToCompleteException {

    // Try to print as many errors as possible in a run before throwing the
    // exception
    boolean error = false;

    for (Task pair : propertyAccessors.values()) {
      error |= pair.validate(this, context);
    }

    if (error) {
      throw new UnableToCompleteException();
    }
  }

  /**
   * Writes common boilerplate code for all implementations.
   */
  protected void writeBoilerplate(TreeLogger logger,
      FragmentGeneratorContext context) throws UnableToCompleteException {

    SourceWriter sw = context.sw;
    TypeOracle typeOracle = context.typeOracle;
    JType returnType = context.returnType;

    // The backing object
    sw.print("private JavaScriptObject ");
    sw.print(OBJ);
    sw.println(";");

    // Build a constructor to initialize state.
    sw.print("public ");
    sw.print(context.simpleTypeName);
    sw.println("() {");
    sw.indent();
    sw.println("setJavaScriptObject(__nativeInit());");
    sw.outdent();
    sw.println("}");

    // Determine the correct expression to use to initialize the object
    JClassType asClass = context.returnType.isClassOrInterface();
    Constructor constructorAnnotation = hasTag(logger, asClass,
        Constructor.class);
    Global globalAnnotation = hasTag(logger, asClass, Global.class);
    String constructor;
    if (globalAnnotation != null) {
      constructor = globalAnnotation.value();
    } else if (constructorAnnotation != null) {
      constructor = "new " + constructorAnnotation.value() + "()";
    } else {
      boolean hasImports = false;
      for (Task t : context.tasks) {
        hasImports |= t.imported != null;

        if (hasImports) {
          break;
        }
      }

      if (!hasImports) {
        // Probably a JSON or pojo-style object
        constructor = "{}";
      } else {
        constructor = "null";
      }
    }

    JClassType parameterization = findJSWrapperParameterization(
        context.typeOracle, asClass);
    if (parameterization == null) {
      parameterization = asClass;
    }

    // Initialize native state of the wrapper
    sw.println("private native JavaScriptObject __nativeInit() /*-{");
    sw.indent();
    sw.print("return ");
    sw.print(constructor);
    sw.println(";");
    sw.outdent();
    sw.println("}-*/;");

    // Allow the backing JSONObject to be accessed
    sw.println("public JavaScriptObject getJavaScriptObject() {");
    sw.indent();
    sw.print("return ");
    sw.print(OBJ);
    sw.println(";");
    sw.outdent();
    sw.println("}");

    // Defer actual parsing to JSONWrapperUtil to take advantage of using
    // a common function implementation between generated classes.
    sw.println("public void setJSONData(String data)");
    sw.println("throws JSONWrapperException {");
    sw.indent();
    sw.println("setJavaScriptObject(JSONWrapperUtil.evaluate(data));");
    sw.outdent();
    sw.println("}");

    // Satisfies JSWrapper and allows generated implementations to
    // efficiently initialize new objects.
    // Method declaration
    sw.print("public " + parameterization.getParameterizedQualifiedSourceName()
        + " setJavaScriptObject(");
    sw.println("JavaScriptObject obj) {");
    sw.indent();

    sw.println("if (obj != null) {");
    sw.indent();
    for (Task t : context.tasks) {
      if (t.imported != null) {
        String fieldName = t.getFieldName(logger);
        sw.print("assert JSONWrapperUtil.hasField(obj, \"");
        sw.print(fieldName);
        sw.print("\") : \"Backing JSO missing imported function ");
        sw.print(fieldName);
        sw.println("\";");
      }
    }
    sw.outdent();
    sw.println("}");
    sw.println("return setJavaScriptObjectNative(obj);");
    sw.outdent();
    sw.println("}");

    // Method declaration
    sw.println("public native " + context.simpleTypeName
        + " setJavaScriptObjectNative(JavaScriptObject obj) /*-{");
    sw.indent();

    if (context.maintainIdentity) {
      // Delete the backing object's reference to the current wrapper
      sw.print("if (");
      sw.print(context.objRef);
      sw.println(") {");
      sw.indent();
      sw.print("delete ");
      sw.print(context.objRef);
      sw.print(".");
      sw.print(BACKREF);
      sw.println(";");
      sw.outdent();
      sw.println("}");
    }

    // If the incoming JSO is null or undefined, reset the JSWrapper
    sw.println("if (!obj) {");
    sw.indent();
    sw.print(context.objRef);
    sw.println(" = null;");
    sw.println("return this;");
    sw.outdent();
    sw.println("}");

    if (context.maintainIdentity) {
      // Verify that the incoming object doesn't already have a wrapper object.
      // If there is a backreference, throw an exception.
      sw.print("if (obj.");
      sw.print(BACKREF);
      sw.println(") {");
      sw.indent();
      sw.println("@com.google.gwt.jsio.client.impl.JSONWrapperUtil::throwMultipleWrapperException()();");
      sw.outdent();
      sw.println("}");
    }

    // Capture the object in the wrapper
    sw.print(context.objRef);
    sw.println(" = obj;");

    if (context.maintainIdentity) {
      // Assign the backreference from the wrapped object to the wrapper
      sw.print(context.objRef);
      sw.print(".");
      sw.print(BACKREF);
      sw.println(" = this;");
    }

    if (!context.readOnly) {
      // Initialize any other fields if the JSWrapper is read-write
      sw.print("this.@");
      sw.print(context.qualifiedTypeName);
      sw.print("::__initializeEmptyFields(Lcom/google/gwt/core/client/JavaScriptObject;)(");
      sw.print(context.objRef);
      sw.println(");");
    }

    sw.println("return this;");
    sw.outdent();
    sw.println("}-*/;");

    // If the generated class will be used with a JSList, we need an Extractor
    // implementation. We'll create an implementation per generated
    // class to ensure that if the class is used with a JSList, only one
    // instance of the Extractor will ever exist.
    sw.println("public final Extractor<"
        + parameterization.getParameterizedQualifiedSourceName()
        + "> getExtractor() {");
    sw.indent();
    sw.print("return ");
    sw.print(EXTRACTOR);
    sw.println(";");
    sw.outdent();
    sw.println("}");

    // The one instance of the Extractor
    sw.print("private final static Extractor ");
    sw.print(EXTRACTOR);
    sw.print(" = new Extractor() {");
    sw.indent();
    FragmentGeneratorContext subParams = new FragmentGeneratorContext(context);
    subParams.parameterName = "obj";
    FragmentGenerator fragmentGenerator = context.fragmentGeneratorOracle.findFragmentGenerator(
        logger, typeOracle, returnType);

    sw.println("public native Object fromJS(JavaScriptObject obj) /*-{");
    sw.indent();
    sw.print("return ");
    fragmentGenerator.fromJS(subParams);
    sw.println(";");
    sw.outdent();
    sw.println("}-*/;");

    // Write the Extracor's toJS function and close the Extractor
    // implementation.
    sw.println("public native JavaScriptObject toJS(Object obj) /*-{");
    sw.indent();
    sw.print("return ");
    fragmentGenerator.toJS(subParams);
    sw.println(";");
    sw.outdent();
    sw.println("}-*/;");

    // Finish the class
    sw.outdent();
    sw.println("};");
  }

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

    // The return type of the function we're importing.
    JType returnType = constructor.getReturnType();

    sw.print("var jsReturn = ");

    // If the imported method is acting as an invocation of a JavaScript
    // constructor, use the new Foo() syntax, otherwise treat is an an
    // invocation on a field on the underlying JSO.
    sw.print("new ");
    Constructor constructorAnnotation = hasTag(logger, constructor,
        Constructor.class);
    sw.print(constructorAnnotation.value());

    // Write the invocation's parameter list
    sw.print("(");
    for (int i = getImportOffset(); i < parameters.length; i++) {
      // Create a sub-context to generate the wrap/unwrap logic
      JType subType = parameters[i].getType();
      FragmentGeneratorContext subParams = new FragmentGeneratorContext(context);
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
    sw.println(");");

    FragmentGeneratorContext subContext = new FragmentGeneratorContext(context);
    subContext.returnType = returnType;
    subContext.parameterName = "jsReturn";

    sw.println("return this.@com.google.gwt.jsio.client.JSWrapper::setJavaScriptObject(Lcom/google/gwt/core/client/JavaScriptObject;)(jsReturn);");
    sw.outdent();
    sw.println("}-*/;");
  }

  /**
   * Provides a method to encapsulate empty field initialization.
   */
  protected void writeEmptyFieldInitializerMethod(final TreeLogger logger,
      final Map<String, Task> propertyAccessors,
      final FragmentGeneratorContext context) throws UnableToCompleteException {
    SourceWriter sw = context.sw;
    JClassType returnType = context.returnType.isClassOrInterface();

    sw.println("private native void __initializeEmptyFields(JavaScriptObject jso) /*-{");
    sw.indent();

    FragmentGeneratorContext subContext = new FragmentGeneratorContext(context);
    subContext.parameterName = "jso";
    writeEmptyFieldInitializers(subContext);

    subContext.tasks = TaskFactory.extractMethods(logger,
        subContext.typeOracle, returnType, TaskFactory.EXPORTER_POLICY).values();
    writeMethodBindings(subContext);

    sw.outdent();
    sw.println("}-*/;");
  }

  /**
   * Ensures that no field referenced by generated logic will ever return an
   * undefined value. This allows every subsequent getFoo() call to simply
   * return the field value, without having to check it for an undefined value.
   */
  protected void writeEmptyFieldInitializers(FragmentGeneratorContext context)
      throws UnableToCompleteException {
    SourceWriter sw = context.sw;
    TreeLogger logger = context.parentLogger.branch(TreeLogger.DEBUG,
        "Writing field initializers", null);

    for (Task task : context.tasks) {
      final String fieldName = task.getFieldName(logger);

      // If there is no getter, we don't need to worry about an empty
      // field initializer.
      if (task.getter == null) {
        continue;
      }

      final JType returnType = task.getter.getReturnType();

      FragmentGenerator fragmentGenerator = FRAGMENT_ORACLE.findFragmentGenerator(
          logger, context.typeOracle, returnType);

      sw.print("if (!");
      sw.print(context.parameterName);
      sw.print(".hasOwnProperty('");
      sw.print(fieldName);
      sw.println("')) {");
      sw.indent();

      sw.print(context.parameterName);
      sw.print(".");
      sw.print(fieldName);
      sw.print(" = ");
      sw.print(fragmentGenerator.defaultValue(context.typeOracle, returnType));
      sw.println(";");

      sw.outdent();
      sw.println("}");
    }
  }

  protected void writeFixups(TreeLogger logger, TypeOracle typeOracle,
      SourceWriter sw, Set<JClassType> creatorFixups)
      throws UnableToCompleteException {
    for (JClassType asClass : creatorFixups) {
      // If the type is parameterized, we want to replace it with the raw type
      // so that no angle-brackets are used.
      JParameterizedType pType = asClass.isParameterized();
      if (pType != null) {
        asClass = pType.getRawType();
      }

      sw.print("private static ");
      sw.print(asClass.getQualifiedSourceName());
      sw.print(" __create__");
      sw.print(asClass.getQualifiedSourceName().replaceAll("\\.", "_"));
      sw.println("() {");
      sw.indent();
      sw.print("return (");
      sw.print(asClass.getQualifiedSourceName());
      sw.print(")GWT.create(");
      sw.print(asClass.getQualifiedSourceName());
      sw.println(".class);");
      sw.outdent();
      sw.println("}");
    }
  }

  protected void writeGetter(FragmentGeneratorContext context, JMethod getter)
      throws UnableToCompleteException {

    TreeLogger logger = context.parentLogger.branch(TreeLogger.DEBUG,
        "Writing getter " + getter.getName(), null);
    TypeOracle typeOracle = context.typeOracle;
    SourceWriter sw = context.sw;

    final JType returnType = getter.getReturnType();

    FragmentGenerator fragmentGenerator = FRAGMENT_ORACLE.findFragmentGenerator(
        logger, typeOracle, context.returnType);

    sw.print("public native ");
    sw.print(returnType.getQualifiedSourceName());
    sw.print(" ");
    sw.print(getter.getName());
    sw.print("(");

    // This is only important when working with the flyweight subclass
    JParameter[] params = getter.getParameters();
    for (int i = 0; i < params.length; i++) {
      sw.print(params[i].getType().getQualifiedSourceName());
      sw.print(" ");
      sw.print(params[i].getName());

      if (i < params.length - 1) {
        sw.print(", ");
      }
    }
    sw.print(")");
    sw.println(" /*-{");
    sw.indent();

    sw.print("return ");
    fragmentGenerator.fromJS(context);
    sw.println(";");

    sw.outdent();
    sw.println("}-*/;");
  }

  protected void writeImported(FragmentGeneratorContext context,
      JMethod imported) throws UnableToCompleteException {

    TreeLogger logger = context.parentLogger.branch(TreeLogger.DEBUG,
        "Writing import " + imported.getName(), null);
    SourceWriter sw = context.sw;

    // Simplifies the rest of writeImported
    JParameter[] parameters = imported.getParameters();
    if (parameters == null) {
      parameters = new JParameter[0];
    }

    // Method declaration
    sw.print("public native ");
    sw.print(imported.getReturnType().getQualifiedSourceName());
    sw.print(" ");
    sw.print(imported.getName());
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

    // The return type of the function we're importing.
    final JType returnType = imported.getReturnType();

    // Don't bother recording a return value for void invocations.
    if (!JPrimitiveType.VOID.equals(returnType.isPrimitive())) {
      sw.print("var jsReturn = ");
    }

    sw.print(context.objRef);
    sw.print(".");
    sw.print(context.fieldName);

    // Write the invocation's parameter list
    sw.print("(");
    for (int i = getImportOffset(); i < parameters.length; i++) {
      // Create a sub-context to generate the wrap/unwrap logic
      JType subType = parameters[i].getType();
      FragmentGeneratorContext subParams = new FragmentGeneratorContext(context);
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
    sw.println(");");

    // Wrap the return type in the correct Java type. Void returns are ignored
    if (!JPrimitiveType.VOID.equals(returnType.isPrimitive())) {
      FragmentGeneratorContext subContext = new FragmentGeneratorContext(
          context);
      subContext.returnType = returnType;
      subContext.parameterName = "jsReturn";

      FragmentGenerator fragmentGenerator = FRAGMENT_ORACLE.findFragmentGenerator(
          logger, context.typeOracle, returnType);

      sw.print("return ");

      fragmentGenerator.fromJS(subContext);
      sw.println(";");
    }

    sw.outdent();
    sw.println("}-*/;");
  }

  protected void writeMethodBindings(FragmentGeneratorContext context)
      throws UnableToCompleteException {
    SourceWriter sw = context.sw;
    TreeLogger logger = context.parentLogger.branch(TreeLogger.DEBUG,
        "Writing method bindings initializers", null);

    for (Task task : context.tasks) {
      final String fieldName = task.getFieldName(logger);

      if (task.exported != null) {
        sw.print(context.parameterName);
        sw.print(".");
        sw.print(fieldName);
        sw.print(" = ");

        FragmentGeneratorContext subContext = new FragmentGeneratorContext(
            context);
        subContext.parameterName = "this." + BACKREF;

        JSFunctionFragmentGenerator.writeFunctionForMethod(subContext,
            task.exported);
        sw.println(";");
      }
    }
  }

  /**
   * Write the field, getter, and setter for the properties we know about. Also
   * write BusObjectImpl methods for Map-style access.
   */
  protected void writeMethods(FragmentGeneratorContext context,
      Map<String, Task> propertyAccessors) throws UnableToCompleteException {
    TreeLogger logger = context.parentLogger.branch(TreeLogger.DEBUG,
        "Writing methods", null);

    for (Task task : propertyAccessors.values()) {
      context.fieldName = task.getFieldName(logger);
      writeSingleTask(context, task);
    }
  }

  protected void writeSetter(FragmentGeneratorContext context, JMethod setter)
      throws UnableToCompleteException {

    TreeLogger logger = context.parentLogger.branch(TreeLogger.DEBUG,
        "Writing setter " + setter.getName(), null);
    TypeOracle typeOracle = context.typeOracle;
    SourceWriter sw = context.sw;

    JType parameterType = context.returnType;

    FragmentGenerator fragmentGenerator = FRAGMENT_ORACLE.findFragmentGenerator(
        logger, typeOracle, context.returnType);
    if (fragmentGenerator == null) {
      throw new UnableToCompleteException();
    }

    // Ensure that there will be no angle-bracket in the output
    JParameterizedType pType = parameterType.isParameterized();
    if (pType != null) {
      parameterType = pType.getRawType();
    }

    sw.print("public native void ");
    sw.print(setter.getName());
    sw.print("(");
    // This is only important when working with the flyweight subclass
    JParameter[] params = setter.getParameters();
    for (int i = 0; i < params.length; i++) {
      sw.print(params[i].getType().getQualifiedSourceName());
      sw.print(" ");
      sw.print(params[i].getName());

      if (i < params.length - 1) {
        sw.print(", ");
      }
    }

    sw.println(") /*-{");
    sw.indent();
    sw.print(context.objRef);
    sw.print(".");
    sw.print(context.fieldName);
    sw.print(" = ");
    fragmentGenerator.toJS(context);
    sw.println(";");
    sw.outdent();
    sw.println("}-*/;");
  }

  protected void writeSingleTask(FragmentGeneratorContext context, Task task)
      throws UnableToCompleteException {
    TreeLogger logger = context.parentLogger.branch(TreeLogger.DEBUG,
        "Writing Task " + task.getFieldName(context.parentLogger), null);

    context = new FragmentGeneratorContext(context);
    context.parentLogger = logger;

    logger.log(TreeLogger.DEBUG, "Implementing task " + context.fieldName, null);

    if (task.getter != null) {
      context.returnType = task.getter.getReturnType();
      context.parameterName = context.objRef + "." + context.fieldName;
      writeGetter(context, task.getter);
    }

    if (task.imported != null) {
      context.returnType = task.imported.getReturnType();
      writeImported(context, task.imported);
    }

    if (task.setter != null) {
      if (context.readOnly) {
        logger.log(TreeLogger.ERROR,
            "Unable to write property setter on read-only wrapper.", null);
        throw new UnableToCompleteException();
      }

      JParameter parameter = getSetterParameter(task.setter);
      context.returnType = parameter.getType();
      // What the user called the parameter
      context.parameterName = parameter.getName();
      writeSetter(context, task.setter);
    }

    if (task.constructor != null) {
      context.returnType = task.constructor.getReturnType();
      context.parameterName = "this.";
      context.objRef = "this";
      writeConstructor(context, task.constructor);
    }
  }
}
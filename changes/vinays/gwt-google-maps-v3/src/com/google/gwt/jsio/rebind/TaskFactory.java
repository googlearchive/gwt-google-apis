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

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.JParameter;
import com.google.gwt.core.ext.typeinfo.JPrimitiveType;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.jsio.client.BeanProperties;
import com.google.gwt.jsio.client.Binding;
import com.google.gwt.jsio.client.Constructor;
import com.google.gwt.jsio.client.Exported;
import com.google.gwt.jsio.client.Global;
import com.google.gwt.jsio.client.Imported;
import com.google.gwt.jsio.client.JSWrapper;

/**
 * Examines types to produce Tasks.
 */
public class TaskFactory {
  /**
   * Defines an extraction policy when creating Tasks.
   */
  public static interface Policy {
    /**
     * Specifies the base interface type so that it will be ignored by
     * {@link TaskFactory#extractMethods(TreeLogger, TypeOracle, JClassType, com.google.gwt.jsio.rebind.TaskFactory.Policy)}.
     */
    Collection<JMethod> getOperableMethods(TypeOracle oracle, JClassType clazz);

    /**
     * Exporting methods via a flyweight interface is done by binding an
     * instance of a type (or just the static methods of a type) to a JSO.
     */
    boolean shouldBind(TreeLogger logger, TypeOracle oracle, JMethod m)
        throws UnableToCompleteException;

    /**
     * Determines if a method should be treated as an invocation of an
     * underlying JavaScript constructor function.
     */
    boolean shouldConstruct(TreeLogger logger, TypeOracle oracle, JMethod m)
        throws UnableToCompleteException;

    /**
     * Determines if the generator should generate an export binding for the
     * method.
     */
    boolean shouldExport(TreeLogger logger, TypeOracle oracle, JMethod m)
        throws UnableToCompleteException;

    /**
     * Determines if the generator should implement a particular method. A
     * method will be implemented only if it is abstract and defined in a class
     * derived from JSWrapper
     */
    boolean shouldImplement(TreeLogger logger, TypeOracle oracle, JMethod m)
        throws UnableToCompleteException;

    /**
     * Determines if the generator should generate an import binding for the
     * method.
     */
    boolean shouldImport(TreeLogger logger, TypeOracle oracle, JMethod m)
        throws UnableToCompleteException;
  }

  /**
   * This policy only checks to see if methods are tagged with gwt.exported. All
   * other methods will be ignored under this policy.
   */
  private static class ExporterPolicy implements Policy {
    public Collection<JMethod> getOperableMethods(TypeOracle oracle,
        JClassType clazz) {
      Map<String, JMethod> toReturn = new HashMap<String, JMethod>();
      Stack<JClassType> stack = new Stack<JClassType>();

      // Start by creating a stack that will look at all supertypes of the
      // class under inspection
      while (clazz != null) {
        stack.push(clazz);
        clazz = clazz.getSuperclass();
      }

      for (JClassType searchIn : stack) {
        for (JMethod m : searchIn.getMethods()) {
          // We add a stripped declaration so that changes which don't affect
          // the overall signature will be overwritten by the methods in the
          // leaf type.
          toReturn.put(m.getReadableDeclaration(true, true, true, true, true),
              m);
        }
      }

      return toReturn.values();
    }

    public boolean shouldBind(TreeLogger logger, TypeOracle oracle, JMethod m) {
      return false;
    }

    public boolean shouldConstruct(TreeLogger logger, TypeOracle oracle,
        JMethod m) {
      return false;
    }

    public boolean shouldExport(TreeLogger logger, TypeOracle typeOracle,
        JMethod method) throws UnableToCompleteException {
      return JSWrapperGenerator.hasTag(logger, method, Exported.class) != null;
    }

    public boolean shouldImplement(TreeLogger logger, TypeOracle oracle,
        JMethod m) {
      return false;
    }

    public boolean shouldImport(TreeLogger logger, TypeOracle oracle, JMethod m) {
      return false;
    }
  }

  /**
   * A variation on WrapperPolicy that handles the different signatures of
   * flyweight-style methods. Adds binding tasks.
   */
  private static class FlyweightPolicy extends WrapperPolicy {
    @Override
    public boolean shouldBind(TreeLogger logger, TypeOracle typeOracle,
        JMethod method) throws UnableToCompleteException {

      boolean hasBindingTag = JSWrapperGenerator.hasTag(logger, method,
          Binding.class) != null;
      JParameter[] params = method.getParameters();

      return method.isAbstract()
          && hasBindingTag
          && ((params.length == 1) || (params.length == 2))
          && isJsoOrPeer(typeOracle, params[0].getType())
          && ((params.length == 1) || (params[1].getType().isClassOrInterface() != null));
    }

    @Override
    public boolean shouldImport(TreeLogger logger, TypeOracle typeOracle,
        JMethod method) throws UnableToCompleteException {
      JClassType enclosing = method.getEnclosingType();
      String methodName = method.getName();
      int arguments = method.getParameters().length;

      boolean hasBindingTag = JSWrapperGenerator.hasTag(logger, method,
          Binding.class) != null;
      boolean hasImportTag = JSWrapperGenerator.hasTag(logger, method,
          Imported.class) != null;
      boolean methodHasBeanTag = JSWrapperGenerator.hasTag(logger, method,
          BeanProperties.class) != null;
      boolean classHasBeanTag = JSWrapperGenerator.hasTag(logger, enclosing,
          BeanProperties.class) != null;

      boolean isIs = (arguments == 1)
          && (methodName.startsWith("is"))
          && (JPrimitiveType.BOOLEAN.equals(method.getReturnType().isPrimitive()))
          && isJsoOrPeer(typeOracle, method.getParameters()[0].getType());
      boolean isGetter = (arguments == 1)
          && (methodName.startsWith("get") && isJsoOrPeer(typeOracle,
              method.getParameters()[0].getType()));
      boolean isSetter = (arguments == 2)
          && (methodName.startsWith("set") && isJsoOrPeer(typeOracle,
              method.getParameters()[0].getType()));
      boolean propertyAccessor = isIs || isGetter || isSetter;

      return !(hasBindingTag || methodHasBeanTag || (propertyAccessor
          && !hasImportTag && classHasBeanTag));
    }

    protected boolean isJsoOrPeer(TypeOracle oracle, JType type) {
      JClassType jsoType = oracle.findType(JavaScriptObject.class.getName()).isClass();
      return jsoType.isAssignableFrom(type.isClass())
          || (PeeringFragmentGenerator.findPeer(oracle, type) != null);
    }
  }

  /**
   * Creates constructor, import, and property Tasks.
   */
  private static class WrapperPolicy implements Policy {
    public Collection<JMethod> getOperableMethods(TypeOracle typeOracle,
        JClassType clazz) {
      return Arrays.asList(clazz.getOverridableMethods());
    }

    public boolean shouldBind(TreeLogger logger, TypeOracle typeOracle,
        JMethod method) throws UnableToCompleteException {
      return false;
    }

    public boolean shouldConstruct(TreeLogger logger, TypeOracle typeOracle,
        JMethod method) throws UnableToCompleteException {
      boolean methodConstructorTag = JSWrapperGenerator.hasTag(logger, method,
          Constructor.class) != null;
      boolean methodGlobalTag = JSWrapperGenerator.hasTag(logger, method,
          Global.class) != null;

      return methodConstructorTag || methodGlobalTag;
    }

    public boolean shouldExport(TreeLogger logger, TypeOracle typeOracle,
        JMethod method) throws UnableToCompleteException {
      return false;
    }

    public boolean shouldImplement(TreeLogger logger, TypeOracle typeOracle,
        JMethod method) throws UnableToCompleteException {
      JClassType enclosing = method.getEnclosingType().getErasedType();
      JClassType operableType = typeOracle.findType(getOperableClassName()).getErasedType();
      // JParameterizedType asParam = enclosing.isParameterized();
      // if (asParam != null) {
      // enclosing = asParam.get
      // }

      return method.isAbstract() && !enclosing.equals(operableType);
    }

    public boolean shouldImport(TreeLogger logger, TypeOracle typeOracle,
        JMethod method) throws UnableToCompleteException {
      JClassType enclosing = method.getEnclosingType();
      String methodName = method.getName();
      int arguments = method.getParameters().length;

      boolean hasImportTag = JSWrapperGenerator.hasTag(logger, method,
          Imported.class) != null;
      boolean methodHasBeanTag = JSWrapperGenerator.hasTag(logger, method,
          BeanProperties.class) != null;
      boolean classHasBeanTag = JSWrapperGenerator.hasTag(logger, enclosing,
          BeanProperties.class) != null;
      boolean isIs = (arguments == 0)
          && (methodName.startsWith("is"))
          && (JPrimitiveType.BOOLEAN.equals(method.getReturnType().isPrimitive()));
      boolean isGetter = (arguments == 0) && (methodName.startsWith("get"));
      boolean isSetter = (arguments == 1) && (methodName.startsWith("set"));
      boolean propertyAccessor = isIs || isGetter || isSetter;

      return !(methodHasBeanTag || (propertyAccessor && !hasImportTag && classHasBeanTag));
    }

    protected String getOperableClassName() {
      return JSWrapper.class.getName();
    }
  }

  public static final Policy FLYWEIGHT_POLICY = new FlyweightPolicy();

  public static final Policy WRAPPER_POLICY = new WrapperPolicy();

  public static final Policy EXPORTER_POLICY = new ExporterPolicy();

  /**
   * Populate propertyAccessors from an array of JMethods.
   * 
   * @return A Map of Strings to Tasks.
   */
  public static Map<String, Task> extractMethods(TreeLogger logger,
      TypeOracle typeOracle, JClassType clazz, Policy policy)
      throws UnableToCompleteException {
    TreeLogger parentLogger = logger.branch(TreeLogger.DEBUG,
        "Extracting methods from " + clazz.getName(), null);

    // Value to return
    final Map<String, Task> propertyAccessors = new HashMap<String, Task>();

    // Iterate over all methods that the generated subclass could override
    for (JMethod m : policy.getOperableMethods(typeOracle, clazz)) {
      final String methodName = m.getName();
      logger = parentLogger.branch(TreeLogger.DEBUG, "Examining "
          + m.toString(), null);

      // Look for methods that are to be exported by the presence of
      // the gwt.exported annotation.
      if (policy.shouldExport(logger, typeOracle, m)) {
        Task task = getPropertyPair(propertyAccessors,
            m.getReadableDeclaration());
        task.exported = m;
        logger.log(TreeLogger.DEBUG, "Added as export", null);
        continue;
      }

      if (policy.shouldBind(logger, typeOracle, m)) {
        Task task = getPropertyPair(propertyAccessors,
            m.getReadableDeclaration());
        task.binding = m;
        logger.log(TreeLogger.DEBUG, "Added as binding", null);
        continue;
      }

      // Ignore concrete methods and those methods that are not declared in
      // a subtype of JSWrapper.
      if (!policy.shouldImplement(logger, typeOracle, m)) {
        logger.log(TreeLogger.DEBUG, "Ignoring method " + m.toString(), null);
        continue;
      }

      if (policy.shouldConstruct(logger, typeOracle, m)) {
        // getReadableDeclaration is used so that overloaded methods will
        // be stored with distinct keys.
        Task task = getPropertyPair(propertyAccessors,
            m.getReadableDeclaration());
        task.constructor = m;
        logger.log(TreeLogger.DEBUG, "Using constructor/global override", null);

        // Enable bypassing of name-determination logic with the presence of the
        // @gwt.imported annotation
      } else if (policy.shouldImport(logger, typeOracle, m)) {
        // getReadableDeclaration is used so that overloaded methods will
        // be stored with distinct keys.
        Task task = getPropertyPair(propertyAccessors,
            m.getReadableDeclaration());
        task.imported = m;
        logger.log(TreeLogger.DEBUG, "Using import override", null);

        // Look for setFoo()
      } else if (methodName.startsWith("set")) {
        String propertyName = getPropertyNameFromMethod(m);
        Task task = getPropertyPair(propertyAccessors, propertyName);
        task.setter = m;
        logger.log(TreeLogger.DEBUG, "Determined this is a setter", null);

        // Look for getFoo() or isFoo()
      } else if ((methodName.startsWith("get") || methodName.startsWith("is"))) {
        String propertyName = getPropertyNameFromMethod(m);
        Task task = getPropertyPair(propertyAccessors, propertyName);
        task.getter = m;
        logger.log(TreeLogger.DEBUG, "Determined this is a getter", null);

        // We could not make a decision on what should be done with the method.
      } else {
        logger.log(TreeLogger.ERROR, "Could not decide on implementation of "
            + m.getName(), null);
        throw new UnableToCompleteException();
      }
    }

    return propertyAccessors;
  }

  /**
   * Utility method to extract the bean-style property name from a method.
   * 
   * @return The property name if the method's name looks like a bean property,
   *         otherwise the method's name.
   */
  protected static String getPropertyNameFromMethod(JMethod method) {
    String methodName = method.getName();

    if (methodName.startsWith("get")) {
      return methodName.substring(3);

    } else if (methodName.startsWith("set")) {
      return methodName.substring(3);

    } else if (methodName.startsWith("is")) {
      return methodName.substring(2);

    } else {
      return methodName;
    }
  }

  /**
   * Utility method to access a Map of String, Tasks.
   * 
   * @param propertyAccessors The Map to operate on
   * @param property The name of the property
   * @return A Task in the given map; created if it does not exist
   */
  protected static Task getPropertyPair(Map<String, Task> propertyAccessors,
      String property) {
    if (propertyAccessors.containsKey(property)) {
      return propertyAccessors.get(property);
    } else {
      final Task pair = new Task();
      propertyAccessors.put(property, pair);
      return pair;
    }
  }

  /**
   * Utility class.
   */
  private TaskFactory() {
  }
}

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

import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.user.rebind.SourceWriter;

/**
 * Encapsulates accessors for boxed primitive values.
 */
class BoxedTypeFragmentGenerator extends FragmentGenerator {
  @Override
  boolean accepts(TypeOracle typeOracle, JType type) {
    JClassType asClass = type.isClass();

    if (asClass == null) {
      return false;
    }

    return isAssignable(typeOracle, asClass, Boolean.class)
        || isAssignable(typeOracle, asClass, Character.class)
        || isAssignable(typeOracle, asClass, Number.class);
  }

  @Override
  String defaultValue(TypeOracle typeOracle, JType type)
      throws UnableToCompleteException {

    JClassType returnType = type.isClassOrInterface();

    if (isAssignable(typeOracle, returnType, Boolean.class)) {
      return "false";

    } else if (isAssignable(typeOracle, returnType, Character.class)) {
      return "32";

    } else if (isAssignable(typeOracle, returnType, Number.class)) {
      return "0";
    }

    throw new UnableToCompleteException();
  }

  @Override
  void fromJS(FragmentGeneratorContext context)
      throws UnableToCompleteException {
    TreeLogger logger = context.parentLogger.branch(TreeLogger.DEBUG,
        "Building value getter statement", null);

    SourceWriter sw = context.sw;
    TypeOracle typeOracle = context.typeOracle;
    JClassType returnType = context.returnType.isClassOrInterface();

    // Don't use "x || null" expression because the value may be boolean false
    sw.print("(");
    sw.print(context.parameterName);
    sw.print(" == null || ");
    sw.print(context.parameterName);
    sw.print(" == undefined) ? null : ");
    sw.print("@com.google.gwt.jsio.client.impl.JSONWrapperUtil::createWrapper");

    // Just plow through the Boxed types
    if (isAssignable(typeOracle, returnType, Boolean.class)) {
      sw.print("(Z)(");

    } else if (isAssignable(typeOracle, returnType, Byte.class)) {
      sw.print("(B)(");

    } else if (isAssignable(typeOracle, returnType, Character.class)) {
      sw.print("(C)(");

    } else if (isAssignable(typeOracle, returnType, Double.class)) {
      sw.print("(D)(");

    } else if (isAssignable(typeOracle, returnType, Float.class)) {
      sw.print("(F)(");

    } else if (isAssignable(typeOracle, returnType, Integer.class)) {
      sw.print("(I)(");

    } else if (isAssignable(typeOracle, returnType, Long.class)) {
      sw.print("(J)(");

    } else if (isAssignable(typeOracle, returnType, Short.class)) {
      sw.print("(S)(");

    } else {
      logger.log(TreeLogger.ERROR, "Unknown boxed type "
          + returnType.getQualifiedSourceName(), null);
      throw new UnableToCompleteException();
    }

    sw.print(context.parameterName);
    sw.print(")");
  }

  @Override
  void toJS(FragmentGeneratorContext context) throws UnableToCompleteException {
    context.parentLogger.branch(TreeLogger.DEBUG,
        "Building boxed value setter statement", null);
    SourceWriter sw = context.sw;
    JClassType returnType = context.returnType.isClassOrInterface();

    sw.print(context.parameterName);
    if (isAssignable(context.typeOracle, returnType, Boolean.class)) {
      sw.print(".@java.lang.Boolean::booleanValue()()");
    } else if (isAssignable(context.typeOracle, returnType, Character.class)) {
      sw.print(".@java.lang.Character::charValue()()");
    } else {
      sw.print(".@java.lang.Number::doubleValue()()");
    }
  }

  @Override
  void writeExtractorJSNIReference(FragmentGeneratorContext context) {
    SourceWriter sw = context.sw;
    sw.print("@com.google.gwt.jsio.client.impl.JSONWrapperUtil::");
    sw.print(context.returnType.getSimpleSourceName().toUpperCase());
    sw.print("_EXTRACTOR");
  }
}

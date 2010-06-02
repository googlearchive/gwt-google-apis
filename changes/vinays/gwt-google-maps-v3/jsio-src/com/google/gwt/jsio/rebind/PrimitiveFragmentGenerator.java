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
import com.google.gwt.core.ext.typeinfo.JPrimitiveType;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.user.rebind.SourceWriter;

/**
 * Encapsulates accessors for primitive properties.
 */
class PrimitiveFragmentGenerator extends FragmentGenerator {
  @Override
  boolean accepts(TypeOracle oracle, JType type) {
    return type.isPrimitive() != null;
  }

  @Override
  String defaultValue(TypeOracle oracle, JType type) {
    JPrimitiveType returnType = type.isPrimitive();

    if (returnType.equals(JPrimitiveType.BOOLEAN)) {
      return "false";

    } else if (returnType.equals(JPrimitiveType.CHAR)) {
      return "32";

    } else {
      return "0";
    }
  }

  @Override
  void fromJS(FragmentGeneratorContext context)
      throws UnableToCompleteException {
    context.parentLogger.branch(TreeLogger.DEBUG,
        "Building primitive value getter statement", null);
    SourceWriter sw = context.sw;
    String argName = context.parameterName;
    JPrimitiveType primitiveType = context.returnType.isPrimitive();
    // Map boolean values that are undefined or null to false
    if (primitiveType != null && primitiveType.equals(JPrimitiveType.BOOLEAN)) {
      sw.print("!!" + argName);
    } else {
      sw.print(argName);
    }
  }

  @Override
  boolean isIdentity() {
    return true;
  }

  @Override
  void toJS(FragmentGeneratorContext context) throws UnableToCompleteException {
    context.parentLogger.branch(TreeLogger.DEBUG,
        "Building primitive value setter statement", null);
    SourceWriter sw = context.sw;

    sw.print(context.parameterName);
  }

  @Override
  void writeExtractorJSNIReference(FragmentGeneratorContext context)
      throws UnableToCompleteException {
    context.parentLogger.branch(TreeLogger.ERROR,
        "Primitives should never need extraction", null);
    throw new UnableToCompleteException();
  }
}

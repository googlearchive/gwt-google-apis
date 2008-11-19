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
package com.google.gwt.search.jsio.rebind;

import com.google.gwt.search.jsio.client.impl.JSONWrapperUtil;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.user.rebind.SourceWriter;

/**
 * Encapsulates accessors for String properties.
 */
class StringFragmentGenerator extends FragmentGenerator {
  @Override
  boolean accepts(TypeOracle oracle, JType type) {
    JClassType asClass = type.isClassOrInterface();

    if (asClass == null) {
      return false;
    } else {
      return isAssignable(oracle, asClass, String.class);
    }
  }
  
  @Override
  void fromJS(FragmentGeneratorContext context)
      throws UnableToCompleteException {
    context.parentLogger.branch(TreeLogger.DEBUG,
        "Building string value getter statement", null);
    SourceWriter sw = context.sw;

    sw.print(context.parameterName);
  }

  @Override
  boolean isIdentity() {
    return true;
  }

  @Override
  void toJS(FragmentGeneratorContext context) throws UnableToCompleteException {
    context.parentLogger.branch(TreeLogger.DEBUG,
        "Building string value setter statement", null);
    SourceWriter sw = context.sw;

    sw.print(context.parameterName);
  }

  @Override
  void writeExtractorJSNIReference(FragmentGeneratorContext context) {
    SourceWriter sw = context.sw;
//    sw.print("@com.google.gwt.jsio.client.impl.JSONWrapperUtil::STRING_EXTRACTOR");
    sw.print("@" + JSONWrapperUtil.class.getName() + "::STRING_EXTRACTOR");
  }
}

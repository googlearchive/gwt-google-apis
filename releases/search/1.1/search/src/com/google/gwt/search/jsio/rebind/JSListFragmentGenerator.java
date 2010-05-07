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

import com.google.gwt.search.jsio.client.JSList;
import com.google.gwt.search.jsio.client.JSWrapper;
import com.google.gwt.search.jsio.client.impl.Extractor;
import com.google.gwt.search.jsio.client.impl.JSListWrapper;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JParameterizedType;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.user.rebind.SourceWriter;

/**
 * Encapsulates accessors for List properties.
 */
class JSListFragmentGenerator extends JSWrapperFragmentGenerator {

  @Override
  protected void writeJSNIObjectCreator(FragmentGeneratorContext context)
      throws UnableToCompleteException {

    TypeOracle typeOracle = context.typeOracle;
    SourceWriter sw = context.sw;
    JParameterizedType listType = context.returnType.isParameterized();
    JType argumentType = listType.getTypeArgs()[0];
    TreeLogger logger = context.parentLogger.branch(TreeLogger.DEBUG,
        "Writing JSNI object creator for "
            + argumentType.getQualifiedSourceName(), null);

//    sw.print("@com.google.gwt.jsio.client.impl.JSListWrapper::create(Lcom/google/gwt/jsio/client/impl/Extractor;)(");
    sw.print("@" + JSListWrapper.class.getName() + "::create(L" + Extractor.class.getName().replace('.', '/') + ";)(");

    FragmentGenerator fragmentGenerator = context.fragmentGeneratorOracle.findFragmentGenerator(
        logger, typeOracle, argumentType.isClassOrInterface());

    FragmentGeneratorContext subParams = new FragmentGeneratorContext(context);
    subParams.returnType = argumentType;

    fragmentGenerator.writeExtractorJSNIReference(subParams);
    sw.print(")");
//    sw.print(".@com.google.gwt.jsio.client.JSWrapper::setJavaScriptObject(Lcom/google/gwt/core/client/JavaScriptObject;)(");
    sw.print(".@" + JSWrapper.class.getName() + "::setJavaScriptObject(Lcom/google/gwt/core/client/JavaScriptObject;)(");
    sw.print(context.parameterName);
    sw.print(")");
  }

  @Override
  boolean accepts(TypeOracle oracle, JType type) {
    JParameterizedType asInterface = type.isParameterized();

    if (asInterface == null) {
      return false;
    } else {
      return isAssignable(oracle, asInterface.getRawType(), JSList.class);
      // return oracle.findType(JSList.class.getName()).equals(asInterface);
    }
  }

  @Override
  String defaultValue(TypeOracle typeOracle, JType type)
      throws UnableToCompleteException {
    return "[]";
  }

  @Override
  void writeExtractorJSNIReference(FragmentGeneratorContext context)
      throws UnableToCompleteException {
    TreeLogger logger = context.parentLogger.branch(TreeLogger.DEBUG,
        "Writing JSNI reference to Extractor", null);
    TypeOracle typeOracle = context.typeOracle;
    SourceWriter sw = context.sw;
    JParameterizedType listType = context.returnType.isParameterized();
    JType argumentType = listType.getTypeArgs()[0];

//    sw.print("@com.google.gwt.jsio.client.impl.JSListWrapper::createExtractor(Lcom/google/gwt/jsio/client/impl/Extractor;)(");
    sw.print("@" + JSListWrapper.class.getName() + "::createExtractor(L" + Extractor.class.getName().replace('.', '/') + ";)(");

    FragmentGenerator fragmentGenerator = context.fragmentGeneratorOracle.findFragmentGenerator(
        logger, typeOracle, argumentType.isClassOrInterface());

    FragmentGeneratorContext subParams = new FragmentGeneratorContext(context);
    subParams.returnType = argumentType;

    fragmentGenerator.writeExtractorJSNIReference(subParams);
    sw.print(")");
  }
}

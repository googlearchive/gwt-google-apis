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
import com.google.gwt.jsio.client.JSWrapper;
import com.google.gwt.user.rebind.SourceWriter;

/**
 * Encapsulates accessors for JSWrapper properties.
 */
class JSWrapperFragmentGenerator extends FragmentGenerator {
  protected void writeJSNIObjectCreator(FragmentGeneratorContext context)
      throws UnableToCompleteException {
    SourceWriter sw = context.sw;
    JClassType returnType = context.returnType.isClassOrInterface().getErasedType();

    sw.print("@");
    sw.print(context.qualifiedTypeName);
    sw.print("::");
    sw.print("__create__");
    sw.print(returnType.getQualifiedSourceName().replaceAll("\\.", "_"));
    sw.print("()()");
    sw.print(".@com.google.gwt.jsio.client.JSWrapper::setJavaScriptObject(Lcom/google/gwt/core/client/JavaScriptObject;)(");
    sw.print(context.parameterName);
    sw.print(")");

    context.creatorFixups.add(returnType);
  }

  protected void writeJSNIValue(FragmentGeneratorContext context)
      throws UnableToCompleteException {
    SourceWriter sw = context.sw;
    sw.print(".@com.google.gwt.jsio.client.JSWrapper::getJavaScriptObject()()");
  }

  @Override
  boolean accepts(TypeOracle oracle, JType type) {
    JClassType asClass = type.isClassOrInterface();

    if (asClass == null) {
      return false;
    } else {
      return isAssignable(oracle, asClass, JSWrapper.class);
    }
  }

  @Override
  final void fromJS(FragmentGeneratorContext context)
      throws UnableToCompleteException {
    context.parentLogger.branch(TreeLogger.DEBUG,
        "Building string value getter statement", null);
    SourceWriter sw = context.sw;

    // arg == null ? null : (arg.__gwtPeer || <new object>.setJSO(arg));

    sw.print(context.parameterName);
    sw.print(" == null ? null : ");
    sw.print("(");
    sw.print(context.parameterName);
    sw.print(".");
    sw.print(JSWrapperGenerator.BACKREF);
    sw.print(" || ");
    writeJSNIObjectCreator(context);
    sw.print(")");
  }

  @Override
  final void toJS(FragmentGeneratorContext context)
      throws UnableToCompleteException {
    context.parentLogger.branch(TreeLogger.DEBUG,
        "Building string value setter statement", null);
    SourceWriter sw = context.sw;
    sw.print(context.parameterName);
    sw.print(" == null ? null : ");
    sw.print(context.parameterName);
    writeJSNIValue(context);
  }

  @Override
  void writeExtractorJSNIReference(FragmentGeneratorContext context)
      throws UnableToCompleteException {
    SourceWriter sw = context.sw;
    JClassType elementType = context.returnType.isClassOrInterface().getErasedType();

    sw.print("@");
    sw.print(context.qualifiedTypeName);
    sw.print("::");
    sw.print("__create__");
    sw.print(elementType.getQualifiedSourceName().replaceAll("\\.", "_"));
    sw.print("()().@com.google.gwt.jsio.client.JSWrapper::getExtractor()()");

    context.creatorFixups.add(elementType);
  }
}

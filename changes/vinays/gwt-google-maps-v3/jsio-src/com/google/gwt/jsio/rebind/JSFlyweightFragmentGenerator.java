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
import com.google.gwt.jsio.client.JSFlyweightWrapper;

/**
 * This doesn't include support for arrays, but merely provides an error
 * message.
 */
class JSFlyweightFragmentGenerator extends FragmentGenerator {

  @Override
  boolean accepts(TypeOracle oracle, JType type) {
    JClassType asClass = type.isClassOrInterface();
    if (asClass == null) {
      return false;
    }

    return isAssignable(oracle, asClass, JSFlyweightWrapper.class);
  }

  @Override
  void fromJS(FragmentGeneratorContext context)
      throws UnableToCompleteException {
    logError(context.parentLogger);
  }

  @Override
  void toJS(FragmentGeneratorContext context) throws UnableToCompleteException {
    logError(context.parentLogger);
  }

  @Override
  void writeExtractorJSNIReference(FragmentGeneratorContext context)
      throws UnableToCompleteException {
    logError(context.parentLogger);
  }

  private void logError(TreeLogger logger) throws UnableToCompleteException {
    logger.branch(TreeLogger.ERROR, "JSFlyweightWrappers cannot be imported "
        + " or exported. Use JSWrapper instead", null);
    throw new UnableToCompleteException();
  }
}

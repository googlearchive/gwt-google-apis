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

/**
 * This doesn't add support for Longs and longs, but provides an explanation as
 * to why they aren't supported.
 */
public class LongFragmentGenerator extends FragmentGenerator {

  @Override
  boolean accepts(TypeOracle oracle, JType type) {
    return type.isPrimitive() == JPrimitiveType.LONG
        || (type.isClass() != null && isAssignable(oracle, type.isClass(),
            Long.class));
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
    logger.branch(TreeLogger.ERROR,
        "Primitive longs and boxed Long types are not supported because they "
            + "cannot be represented in JavaScript without loss of precision. "
            + "Use a double or int instead.", null);
    throw new UnableToCompleteException();
  }
}

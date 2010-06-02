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

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;

/**
 * Encapsulates type-specific code generation logic.
 */
abstract class FragmentGenerator {

  /**
   * Utility to check if a JClassType is assignable in the type system to a
   * specified Class. The use of Class literals eliminates the possibility of
   * misspelling a class name.
   * 
   * @return <code>true</code> iff <code>type</code> can be assigned to a
   *         type of <code>clazz</code> within the type system
   */
  protected static boolean isAssignable(TypeOracle typeOracle, JClassType type,
      Class<?> clazz) {

    return (type.isAssignableTo(typeOracle.findType(clazz.getName())));
  }

  /**
   * Allows FragmentGenerators to specify the types on which they can operate.
   * 
   * @param oracle the type system
   * @param type the type under examination
   * @return <code>true</code> iff the FragementGenerator can create code for
   *         the specified type
   */
  abstract boolean accepts(TypeOracle oracle, JType type);

  /**
   * Specify the JavaScript value to be used for unititialized values. This
   * should be a right-hand assignment value.
   */
  String defaultValue(TypeOracle oracle, JType type)
      throws UnableToCompleteException {
    return "null";
  }

  /**
   * Create a right-hand assignment value that represents the value of
   * <code>context.parameterName</code>.
   */
  abstract void fromJS(FragmentGeneratorContext context)
      throws UnableToCompleteException;

  /**
   * Subclasses should return <code>true</code> iff the fromJS and toJS
   * functions don't apply a transformation to the input variable.
   */
  boolean isIdentity() {
    return false;
  }

  /**
   * Create a right-hand assignment value that represents the value of
   * <code>context.parametername</code>.
   */
  abstract void toJS(FragmentGeneratorContext context)
      throws UnableToCompleteException;

  /**
   * Write a JSNI reference to an Extractor instance for the generating type.
   */
  abstract void writeExtractorJSNIReference(FragmentGeneratorContext context)
      throws UnableToCompleteException;
}

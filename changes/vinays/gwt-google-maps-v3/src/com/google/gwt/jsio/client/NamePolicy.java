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
package com.google.gwt.jsio.client;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import com.google.gwt.jsio.client.impl.MetaDataName;

/**
 * The policy to use when mangling bean property names to JSON object property
 * names. The value of this annotation can be one of the predefined policy
 * names, or the qualified source name of a type that implement
 * {@link com.google.gwt.jsio.rebind.NamePolicy}.
 */
@Documented
@MetaDataName("gwt.namePolicy")
@Target(ElementType.TYPE)
public @interface NamePolicy {
  /**
   * Converts SomeFunctionName to someFunctionName.
   */
  String BEAN = "bean";

  /**
   * Converts SomeFunctionName to some_function_name.
   */
  String C_STYLE = "c_style";

  /**
   * Makes no changes.
   */
  String EXACT = "exact";

  /**
   * Converts to lower-case.
   */
  String LOWER = "lower";

  /**
   * Converts to upper-case.
   */
  String UPPER = "upper";

  String value();
}

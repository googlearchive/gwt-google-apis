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

/**
 * Defines a property-name to field-name conversion policy.
 */
public abstract class NamePolicy {

  /**
   * Converts SomeFunctionName to someFunctionName.
   */
  static final NamePolicy BEAN = new NamePolicy() {
    @Override
    public String convert(String propertyName) {
      StringBuffer sb = new StringBuffer(propertyName);
      sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
      return sb.toString();
    }
  };

  /**
   * Converts SomeFunctionName to some_function_name.
   */
  static final NamePolicy C_STYLE = new NamePolicy() {
    @Override
    public String convert(String propertyName) {
      StringBuffer sb = new StringBuffer(propertyName);
      sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));

      // Go right-to-left to ignore the changing length of the String
      for (int i = sb.length() - 1; i > 0; i--) {
        if (Character.isUpperCase(sb.charAt(i))) {
          sb = sb.replace(i, i + 1, "_" + Character.toLowerCase(sb.charAt(i)));
        }
      }

      return sb.toString();
    }
  };

  /**
   * Makes no changes.
   */
  static final NamePolicy EXACT = new NamePolicy() {
    @Override
    public String convert(String propertyName) {
      return propertyName;
    }
  };

  /**
   * Converts to lower-case.
   */
  static final NamePolicy LOWER = new NamePolicy() {
    @Override
    public String convert(String propertyName) {
      return propertyName.toLowerCase();
    }
  };

  /**
   * Converts to upper-case.
   */
  static final NamePolicy UPPER = new NamePolicy() {
    @Override
    public String convert(String propertyName) {
      return propertyName.toUpperCase();
    }
  };

  /**
   * Apply the conversion.
   * 
   * @param propertyName The extracted property name
   * @return The JSON field name for the property
   */
  public abstract String convert(String propertyName);
}

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
package com.google.gwt.gadgets.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.gadgets.client.UserPreferences.DataType;
import com.google.gwt.gadgets.client.UserPreferences.Preference;

/**
 * A list of String preferences.
 */
@DataType("list")
public abstract class ListPreference extends Preference<String[]> {
  
  /**
   * Returns the value of a list preference as a list of strings.
   * 
   * @return the value of a list preference as a list of strings.
   */
  @Override
  public String[] getValue() {
    return prefs.getString(getName()).split("\\|");
  }

  /**
   * Set a preference with a list of strings.
   * 
   * @param value the list of strings to set.
   */
  @Override
  void set(String[] value) {
    JavaScriptObject array = JavaScriptObject.createArray();
    for (String s : value) {
      prefs.push(array, s);
    }
    prefs.setArray(getName(), array);
  }
}
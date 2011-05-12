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

import com.google.gwt.gadgets.client.UserPreferences.DataType;
import com.google.gwt.gadgets.client.UserPreferences.Preference;

/**
 * A String preference.
 */
@DataType("string")
public abstract class StringPreference extends Preference<String> {
  /**
   * Returns the value of a preference as a string.
   * 
   * @return the value of a preference as a string. 
   */
  @Override
  public String getValue() {
    return PreferencesProvider.get().getString(getName());
  }

  /**
   * Set the value of a preference as a string.
   * 
   *  @param value the value to set.
   */
  @Override
  void set(String value) {
    PreferencesProvider.get().set(getName(), value);
  }
}
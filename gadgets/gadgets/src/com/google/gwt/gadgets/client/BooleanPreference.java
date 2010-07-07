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
 * A Boolean preference.
 */
@DataType("bool")
public abstract class BooleanPreference extends Preference<Boolean> {
  /**
   * Returns a boolean value for a preference (shows as a checkbox).
   * 
   * @return the value of the preference.
   */
  @Override
  public Boolean getValue() {
    return PreferencesProvider.get().getBool(getName());
  }

  /**
   * Provide a boolean value for a preference (shows as a checkbox).
   * 
   * @param value the value to set for the preference.
   */
  @Override
  void set(Boolean value) {
    PreferencesProvider.get().set(getName(), value);
  }
}
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

import com.google.gwt.gadgets.client.impl.PreferenceGeneratorName;
import com.google.gwt.gadgets.client.impl.PreferencesUtil;

/**
 * Represents the types of user preferences available in the Gadget container.
 * 
 * @param <T> the type of data encapsulated
 */
@PreferenceGeneratorName("com.google.gwt.gadgets.rebind.DefaultPreferenceGenerator")
public abstract class Preference<T> {
  Preference() {
    PreferencesUtil.maybeInit();
  }

  public abstract String getName();

  public abstract T getValue();

  /**
   * Used by {@link SetPrefsFeature#set(Preference, Object)}.
   * It's not exposed directly because the support for setting preference values
   * requires the <code>setprefs</code> feature.
   */
  abstract void set(T value);
}

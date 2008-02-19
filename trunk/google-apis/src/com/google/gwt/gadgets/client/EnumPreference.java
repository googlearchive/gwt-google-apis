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

import com.google.gwt.gadgets.annotations.DataType;
import com.google.gwt.gadgets.client.impl.PreferenceGeneratorName;
import com.google.gwt.gadgets.client.impl.PreferencesUtil;

/**
 * A preference containing only enumerated values.
 * 
 * @param <E> the type of enumeration
 */
@DataType("enum")
@PreferenceGeneratorName("com.google.gwt.gadgets.rebind.EnumPreferenceGenerator")
public abstract class EnumPreference<E extends Enum<E>> extends Preference<E> {
  void set(E value) {
    PreferencesUtil.set(getName(), value.name());
  }
}
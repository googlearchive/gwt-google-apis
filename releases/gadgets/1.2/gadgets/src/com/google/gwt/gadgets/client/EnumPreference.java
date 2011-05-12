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
import com.google.gwt.gadgets.client.impl.PreferenceGeneratorName;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * A preference containing only enumerated values. The enumerated values will be
 * presented to the user in the order in which the values are defined within the
 * declaration of the enumeration.
 * 
 * @param <E> the type of enumeration
 */
@DataType("enum")
@PreferenceGeneratorName("com.google.gwt.gadgets.rebind.EnumPreferenceGenerator")
public abstract class EnumPreference<E extends Enum<E>> extends Preference<E> {
  /**
   * Declares the display name for Enum preference values.
   * <p>
   * Example:
   * 
   * <pre>
   * public enum MyEnum {
   *   {@literal @}EnumDisplayValue(&quot;Pretty&quot;)
   *   FOO,
   *   {@literal @}EnumDisplayValue(&quot;Names&quot;)
   *   BAR,
   *   {@literal @}EnumDisplayValue(&quot;For humans&quot;)
   *   BAZ
   * }
   * </pre>
   */
  @Target(ElementType.FIELD)
  public @interface EnumDisplayValue {
    String value();
  }

  /**
   * Set an enum type value. The enum data type is presented in the user
   * interface as a menu of choices.
   * 
   * @param value the enum value to set.
   */
  @Override
  void set(E value) {
    PreferencesProvider.get().set(getName(), value.name());
  }
}
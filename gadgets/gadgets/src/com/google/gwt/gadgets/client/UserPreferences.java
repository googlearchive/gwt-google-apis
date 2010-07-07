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

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Encapsulates user preferences for a Gadget. Developers should declare the
 * preferences required by the Gadget in a subtype of UserPreferences that is
 * used in the method declaration of {@link Gadget#init(UserPreferences)}.
 */
public interface UserPreferences {
  /**
   * Used to declare the container data type for a Preference.
   */
  @Target(ElementType.TYPE)
  public @interface DataType {
    String value();
  }

  /**
   * Represents the types of user preferences available in the Gadget container.
   * 
   * @param <T> the type of data encapsulated
   */
  @PreferenceGeneratorName("com.google.gwt.gadgets.rebind.DefaultPreferenceGenerator")
  public abstract class Preference<T> {
  
    /**
     * The unique name of the preference. This value is typically the name of
     * the accessor method.
     */
    public abstract String getName();

    /**
     * Returns the current value of the preference.
     */
    public abstract T getValue();

    /**
     * Used by {@link SetPrefsFeatureImpl#set(Preference, Object)}. It's not
     * exposed directly because the support for setting preference values
     * requires the <code>setprefs</code> feature.
     */
    abstract void set(T value);
  }

  /**
   * Annotates instances of Preference getters in UserPreferences objects. The
   * name of the getter method will be used as the name of the preference.
   */
  @Documented
  @Target(ElementType.METHOD)
  public @interface PreferenceAttributes {
    /**
     * Additional, mutually exclusive options to apply to a preference.
     */
    public static enum Options {
      NORMAL, HIDDEN, REQUIRED
    }

    /**
     * The default value of the preference.
     */
    String default_value() default "";

    /**
     * The user-visible name of the preference. If no name is specified, the
     * name of the accessor method will be used.
     */
    String display_name() default "";

    /**
     * Additional options that may be applied to the preference.
     */
    Options options() default Options.NORMAL;
  }
}

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
package com.google.gwt.gadgets.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Annotates instances of PreferenceType getters in UserPreferences objects.
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

  public String default_value() default "";

  public String display_name() default "";

  public Options options() default Options.NORMAL;
}

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
package com.google.gwt.gadgets.sample.hellogadgets.client;

import com.google.gwt.gadgets.client.BooleanPreference;
import com.google.gwt.gadgets.client.EnumPreference;
import com.google.gwt.gadgets.client.StringPreference;
import com.google.gwt.gadgets.client.UserPreferences;
import com.google.gwt.gadgets.client.EnumPreference.EnumDisplayValue;
import com.google.gwt.gadgets.client.UserPreferences.PreferenceAttributes.Options;

/**
 * The preferences for the HelloWorld Gadget.
 */
public interface HelloPreferences extends UserPreferences {
  /**
   * The horizontal alignment of the button.
   */
  public static enum ButtonPosition {
    @EnumDisplayValue("Left")
    LEFT("left"),

    @EnumDisplayValue("Center")
    CENTER("center"),

    @EnumDisplayValue("Right")
    RIGHT("right");

    private final String align;

    private ButtonPosition(String align) {
      this.align = align;
    }

    public String getAlign() {
      return align;
    }
  }

  @PreferenceAttributes(display_name = "Button text position", default_value = "CENTER")
  EnumPreference<ButtonPosition> buttonPosition();

  @PreferenceAttributes(display_name = "Alert prompt", default_value = "Hello, Gadgets!", options = Options.REQUIRED)
  StringPreference promptSomethingElse();

  @PreferenceAttributes(display_name = "Show settings message", default_value = "true")
  BooleanPreference showMessage();

  @PreferenceAttributes(options = Options.HIDDEN, default_value = "Hello World (GWT)")
  StringPreference title();
}

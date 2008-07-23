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
package com.google.gwt.search.client;

import com.google.gwt.search.jsio.client.JSOpaque;

/**
 * Pre-localized labels to use for the "keep" label within a SearchControl.
 * 
 * @see SearchControlOptions#setKeepLabel(KeepLabel)
 */
public enum KeepLabel {

  /**
   * A localized message for "save".
   */
  SAVE("SAVE"),

  /**
   * A localized message for "keep".
   */
  KEEP("KEEP"),

  /**
   * A localized message for "include".
   */
  INCLUDE("INCLUDE"),

  /**
   * A localized message for "copy".
   */
  COPY("COPY"),

  /**
   * No label should be displayed, just the icon.
   */
  BLANK("BLANK");

  private final JSOpaque value;

  private KeepLabel(String keep) {
    value = new JSOpaque("$wnd.GSearchControl.KEEP_LABEL_" + keep);
  }

  JSOpaque getValue() {
    return value;
  }
}

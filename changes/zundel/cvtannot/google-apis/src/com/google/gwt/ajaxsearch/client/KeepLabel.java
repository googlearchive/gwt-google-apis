/*
 * Copyright 2007 Google Inc.
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
package com.google.gwt.ajaxsearch.client;

import com.google.gwt.jsio.client.JSOpaque;

/**
 * Pre-localized labels to use for the "keep" label within a SearchControl.
 * 
 * @see SearchControlOptions#setKeepLabel(KeepLabel)
 */
public final class KeepLabel extends JSOpaque {
  
  /**
   * A localized message for "save".
   */
  public static final KeepLabel SAVE = new KeepLabel("SAVE");
  
  /**
   * A localized message for "keep".
   */
  public static final KeepLabel KEEP = new KeepLabel("KEEP");
  
  /**
   * A localized message for "include".
   */
  public static final KeepLabel INCLUDE = new KeepLabel("INCLUDE");
  
  /**
   * A localized message for "copy".
   */
  public static final KeepLabel COPY = new KeepLabel("COPY");

  /**
   * No label should be displayed, just the icon.
   */
  public static final KeepLabel BLANK = new KeepLabel("BLANK");

  private KeepLabel(String keep) {
    super("$wnd.GSearchControl.KEEP_LABEL_" + keep);
  }
}

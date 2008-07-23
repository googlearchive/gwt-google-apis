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
 * Sets the target window to display full results in when the user selects a
 * result in a SearchControl.
 * 
 * @see SearchControlOptions#setLinkTarget(LinkTarget)
 */
public enum LinkTarget {
  /**
   * The result should be displayed in a new window.
   */
  BLANK("BLANK"),

  /**
   * The result should be displayed in the current window or frame.
   */
  SELF("SELF"),

  /**
   * The result should replace any frameset.
   */
  TOP("TOP"),

  /**
   * The result should be displayed in the frame's parent window.
   */
  PARENT("PARENT");

  private final JSOpaque value;

  private LinkTarget(String reference) {
    value = new JSOpaque("$wnd.GSearch.LINK_TARGET_" + reference);
  }

  JSOpaque getValue() {
    return value;
  }
}

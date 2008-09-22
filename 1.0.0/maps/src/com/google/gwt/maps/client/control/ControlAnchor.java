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
package com.google.gwt.maps.client.control;

import com.google.gwt.maps.jsio.client.JSOpaque;

/**
 * Wraps the class GControlAnchor which is used by {@link ControlPosition} to determine
 * which corner of the screen to use as an anchor for positioning a control.
 */
public enum ControlAnchor {

  BOTTOM_LEFT("$wnd.G_ANCHOR_BOTTOM_LEFT"), 
  BOTTOM_RIGHT("$wnd.G_ANCHOR_BOTTOM_RIGHT"), 
  TOP_LEFT("$wnd.G_ANCHOR_TOP_LEFT"), 
  TOP_RIGHT("$wnd.G_ANCHOR_TOP_RIGHT");

  private final JSOpaque value;

  private ControlAnchor(String constantName) {
    value = new JSOpaque(constantName);
  }

  JSOpaque getValue() {
    return value;
  }

}

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
package com.google.gwt.maps.client;

import com.google.gwt.jsio.client.JSOpaque;

/**
 * Identifies a layer of the map used as a parameter to
 * {@link MapWidget#getPane(MapPaneType)}.
 */
public enum MapPaneType {

  FLOAT_PANE("$wnd.G_MAP_FLOAT_PANE"), 
  FLOAT_SHADOW_PANE("$wnd.G_MAP_FLOAT_SHADOW_PANE"),
  MAP_PANE("$wnd.G_MAP_MAP_PANE"), 
  MARKER_MOUSE_TARGET_PANE("$wnd.G_MAP_MARKER_MOUSE_TARGET_PANE"), 
  MARKER_PANE("$wnd.G_MAP_MARKER_PANE"), 
  MARKER_SHADOW_PANE("$wnd.G_MAP_MARKER_SHADOW_PANE");

  private final JSOpaque value;

  private MapPaneType(String value) {
    this.value = new JSOpaque(value);
  }

  JSOpaque getValue() {
    return value;
  }
}

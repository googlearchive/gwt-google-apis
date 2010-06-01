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

import com.google.gwt.maps.jsio.client.JSOpaque;

/**
 * Identifies a layer of the map used as a parameter to
 * {@link MapWidget#getPane(MapPaneType)}.
 */
public enum MapPaneType {

  /**
   * This pane contains the info window. It is above everything else on the map.
   */
  FLOAT_PANE("$wnd.G_MAP_FLOAT_PANE"),
  /**
   * This pane contains the shadow of the info window. It is above the
   * {@link #MARKER_PANE}, so that markers can be in the shadow of the info
   * window.
   */
  FLOAT_SHADOW_PANE("$wnd.G_MAP_FLOAT_SHADOW_PANE"),
  /**
   * This pane is still below the shadows of the markers, directly on top of the
   * map. It contains for instance the polylines.
   */
  MAP_PANE("$wnd.G_MAP_MAP_PANE"),
  /**
   * This pane contains transparent elements that receive DOM mouse events for
   * the markers. Is is above the {@link #FLOAT_SHADOW_PANE}, so that markers in
   * the shadow of the info window can be clickable.
   */
  MARKER_MOUSE_TARGET_PANE("$wnd.G_MAP_MARKER_MOUSE_TARGET_PANE"),
  /**
   * This pane contains the markers.
   */
  MARKER_PANE("$wnd.G_MAP_MARKER_PANE"),
  /**
   * This pane contains the shadows of the markers. It is below the markers.
   */
  MARKER_SHADOW_PANE("$wnd.G_MAP_MARKER_SHADOW_PANE"),
  /**
   * This pane contains polylines, polygons, ground overlays and tile layer
   * overlays.
   */
  OVERLAY_LAYER_PANE("$wnd.G_MAP_OVERLAY_LAYER_PANE");

  private final JSOpaque value;

  private MapPaneType(String value) {
    this.value = new JSOpaque(value);
  }

  JSOpaque getValue() {
    return value;
  }
}

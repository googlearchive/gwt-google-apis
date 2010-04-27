/*
 * Copyright 2010 Google Inc.
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

/**
 * Tests the MapPaneType class.
 */
public class MapPaneTypeTest extends MapsTestCase {

  @Override
  public String getModuleName() {
    return "com.google.gwt.maps.GoogleMapsTest";
  }

  public void testMapPaneType() {
    loadApi(new Runnable() {
      public void run() {
        assertNotNull("FLOAT_PANE", MapPaneType.FLOAT_PANE.getValue());
        assertNotNull("FLOAT_SHADOW_PANE",
            MapPaneType.FLOAT_SHADOW_PANE.getValue());
        assertNotNull("MAP_PANE", MapPaneType.MAP_PANE.getValue());
        assertNotNull("MARKER_MOUSE_TARGET_PANE",
            MapPaneType.MARKER_MOUSE_TARGET_PANE.getValue());
        assertNotNull("MARKER_PANE", MapPaneType.MARKER_PANE.getValue());
        assertNotNull("MARKER_SHADOW_PANE",
            MapPaneType.MARKER_SHADOW_PANE.getValue());
        assertNotNull("OVERLAY_LAYER_PANE", MapPaneType.OVERLAY_LAYER_PANE);
      }
    });
  }
}

/*
 * Copyright 2009 Google Inc.
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

import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.Size;
import com.google.gwt.user.client.ui.RootPanel;

import java.util.ArrayList;
import java.util.List;

/**
 * Tests for the MapOptions object.
 * 
 */
public class MapOptionsTest extends MapsTestCase {
  private static native boolean nativeTestBackgroundColor(MapOptions options,
      String value) /*-{
    return options.backgroundColor == value;
  }-*/;

  private static native boolean nativeTestDraggableCursor(MapOptions options,
      String value) /*-{
    return options.draggableCursor == value;
  }-*/;

  private static native boolean nativeTestDraggingCursor(MapOptions options,
      String value) /*-{
    return options.draggingCursor == value;
  }-*/;

  private static native boolean nativeTestGoogleBarOptions(MapOptions options,
      GoogleBarOptions googleBarOptions) /*-{
    return options.googleBarOptions === googleBarOptions;
  }-*/;

  private static native boolean nativeTestMapTypes(MapOptions options) /*-{
    return options.mapTypes[0] == $wnd.G_HYBRID_MAP;
  }-*/;

  private static native boolean nativeTestSize(MapOptions options, int width,
      int height) /*-{
    return options.size.width == width && options.size.height == height;
  }-*/;

  @Override
  public String getModuleName() {
    return "com.google.gwt.maps.GoogleMapsTest";
  }

  public void testMapOptions() {
    loadApi(new Runnable() {
      public void run() {

        LatLng atlanta = LatLng.newInstance(33.7814790, -84.3880580);
        MapOptions options = MapOptions.newInstance();

        // Test the setters
        options.setBackgroundColor("#f00");
        options.setDraggableCursor("text");
        options.setDraggingCursor("crosshair");
        GoogleBarOptions googleBarOptions = GoogleBarOptions.newInstance();
        options.setGoogleBarOptions(googleBarOptions);
        List<MapType> mapTypes = new ArrayList<MapType>();
        mapTypes.add(MapType.getHybridMap());
        options.setMapTypes(mapTypes);
        options.setSize(Size.newInstance(100, 200));

        assertTrue("backgroundColor",
            nativeTestBackgroundColor(options, "#f00"));
        assertTrue("draggagleCursor",
            nativeTestDraggableCursor(options, "text"));
        assertTrue("draggingCursor", nativeTestDraggingCursor(options,
            "crosshair"));
        assertTrue("googleBarOptions", nativeTestGoogleBarOptions(options,
            googleBarOptions));
        assertTrue("mapTypes", nativeTestMapTypes(options));
        assertTrue("size", nativeTestSize(options, 100, 200));

        MapWidget map = new MapWidget(atlanta, 8, options);
        map.setSize("300px", "300px");
        RootPanel.get().add(map);
      }
    });
  }
}

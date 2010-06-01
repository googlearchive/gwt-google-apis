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

/**
 * Tests for the MapUIOptions class.
 * 
 */
public class MapUIOptionsTest extends MapsTestCase {

  @Override
  public String getModuleName() {
    return "com.google.gwt.maps.GoogleMapsTest";
  }

  public void testMapUIOptions() {
    loadApi(new Runnable() {
      public void run() {
        LatLng atlanta = LatLng.newInstance(33.7814790, -84.3880580);
        MapWidget map = new MapWidget(atlanta, 8);
        map.setSize("300px", "300px");
        RootPanel.get().add(map);

        // A set of options for a small map.
        MapUIOptions options = MapUIOptions.newInstance(Size.newInstance(100,
            100));
        options.setDoubleClick(true);
        options.setHybridMapType(true);
        options.setKeyboard(true);
        options.setLargeMapControl3d(true);
        options.setMapTypeControl(true);
        options.setMenuMapTypeControl(true);
        options.setNormalMapType(true);
        options.setPhysicalMapType(true);
        options.setSatelliteMapType(true);
        options.setScaleControl(true);
        options.setScrollwheel(true);
        options.setSmallZoomControl3d(true);

        assertTrue("double click", options.getDoubleClick());
        assertTrue("hybrid", options.getHybridMapType());
        assertTrue("keyboard", options.getKeyboard());
        assertTrue("largemapcontrol3d", options.getLargeMapControl3d());
        assertTrue("maptype control", options.getMapTypeControl());
        assertTrue("menu maptype control", options.getMenuMapTypeControl());
        assertTrue("normal", options.getNormalMapType());
        assertTrue("physical", options.getPhysicalMapType());
        assertTrue("satellite", options.getSatelliteMapType());
        assertTrue("scale control", options.getScaleControl());
        assertTrue("scrollwheel", options.getScrollwheel());
        assertTrue("smallzoomcontrol3d", options.getSmallZoomControl3d());

        options.setDoubleClick(false);
        options.setHybridMapType(false);
        options.setKeyboard(false);
        options.setLargeMapControl3d(false);
        options.setMapTypeControl(false);
        options.setMenuMapTypeControl(false);
        options.setNormalMapType(false);
        options.setPhysicalMapType(false);
        options.setSatelliteMapType(false);
        options.setScaleControl(false);
        options.setScrollwheel(false);
        options.setSmallZoomControl3d(false);

        assertFalse("double click", options.getDoubleClick());
        assertFalse("hybrid", options.getHybridMapType());
        assertFalse("keyboard", options.getKeyboard());
        assertFalse("largemapcontrol3d", options.getLargeMapControl3d());
        assertFalse("maptype control", options.getMapTypeControl());
        assertFalse("menu maptype control", options.getMenuMapTypeControl());
        assertFalse("normal", options.getNormalMapType());
        assertFalse("physical", options.getPhysicalMapType());
        assertFalse("satellite", options.getSatelliteMapType());
        assertFalse("scale control", options.getScaleControl());
        assertFalse("scrollwheel", options.getScrollwheel());
        assertFalse("smallzoomcontrol3d", options.getSmallZoomControl3d());
      }
    });
  }
}

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
package com.google.gwt.maps.client.geom;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.maps.client.CopyrightCollection;
import com.google.gwt.maps.client.MapType;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.TestUtilities;
import com.google.gwt.maps.client.TileLayer;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Tests the Overlay class.
 */
public class ProjectionTest extends GWTTestCase {
  // length of time to wait for asynchronous test to complete.
  static final int ASYNC_DELAY_MSEC = 5000;
  static TileLayer tileLayer = null;

  private static void initTileLayer() {
    if (tileLayer != null) {
      return;
    }

    tileLayer = new TileLayer(new CopyrightCollection("gwt-maps Unit Test"), 1,
        20) {
      @Override
      public double getOpacity() {
        return 1.0;
      }

      @Override
      public String getTileURL(Point tile, int zoomLevel) {
        return "spots.png";
      }

      @Override
      public boolean isPng() {
        return true;
      }
    };
  }

  @Override
  public String getModuleName() {
    return "com.google.gwt.maps.GoogleMapsTest";
  }

  /**
   * Runs before every test method.
   */
  public void gwtSetUp() {
    TestUtilities.cleanDom();
  }

  /**
   * Create a simple projection and add it to a map.
   */
  public void testProjectionDefault() {
    initTileLayer();
    MapWidget map = new MapWidget();
    map.setSize("300px", "300px");
    Projection projection = new MercatorProjection(20);
    assertNotNull("new MercatorProjection(20)", projection);
    MapType mapType = new MapType(new TileLayer[] {tileLayer}, projection,
        "MyMap");
    map.addMapType(mapType);
    RootPanel.get().add(map);
    map.setZoomLevel(10);
    map.setCurrentMapType(mapType);
  }

  /**
   * Subclass the Projection abstract class and add it to a map.
   */
  public void testSubclassProjection() {
    initTileLayer();
    MapWidget map = new MapWidget();
    map.setSize("300px", "300px");
    Projection projection = new Projection() {

      @Override
      public Point fromLatLngToPixel(LatLng latlng, int zoomLevel) {
        assertNotNull(latlng);
        assertTrue("zoomLevel > 0", zoomLevel > 0);
        return new Point(1, 1);
      }

      @Override
      public LatLng fromPixelToLatLng(Point point, int zoomLevel,
          boolean unbounded) {
        assertNotNull(point);
        assertTrue("zoomLevel > 0", zoomLevel > 0);
        return new LatLng(45, 45);
      }

      @Override
      public double getWrapWidth(int zoomLevel) {
        assertTrue("zoomLevel > 0", zoomLevel > 0);
        return 100;
      }

      @Override
      public boolean tileCheckRange(TileIndex index, int zoomLevel, int tileSize) {
        assertNotNull("index", index);
        assertTrue("zoomLevel > 0", zoomLevel > 0);
        assertTrue("tileSize > 0", tileSize > 0);
        return false;
      }
    };

    MapType mapType = new MapType(new TileLayer[] {tileLayer}, projection,
        "MyMap");
    map.addMapType(mapType);
    RootPanel.get().add(map);
    map.setCurrentMapType(mapType);
  }

}

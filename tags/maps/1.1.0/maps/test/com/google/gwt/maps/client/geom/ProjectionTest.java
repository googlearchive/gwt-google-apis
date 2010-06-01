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

import com.google.gwt.maps.client.CopyrightCollection;
import com.google.gwt.maps.client.MapType;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.MapsTestCase;
import com.google.gwt.maps.client.TestUtilities;
import com.google.gwt.maps.client.TileLayer;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Tests the Projection classes.
 */
public class ProjectionTest extends MapsTestCase {

  private static TileLayer initTileLayer() {
    TileLayer tileLayer;
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
    return tileLayer;
  }

  @Override
  public String getModuleName() {
    return "com.google.gwt.maps.GoogleMapsTest";
  }

  /**
   * Runs before every test method.
   */
  @Override
  public void gwtSetUp() {
    TestUtilities.cleanDom();
  }

  /**
   * Test calling the MercatorProjection methods.
   */
  public void testMercatorProjection() {
    loadApi(new Runnable() {
      public void run() {
        TileLayer tileLayer = initTileLayer();
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

        // Now try to call some of the MercatorProjection methods directly
        LatLng lResult = projection.fromPixelToLatLng(
            Point.newInstance(10, 10), map.getZoomLevel(), false);
        assertNotNull("translation from Pixel to LatLng", lResult);
        Point pResult = projection.fromLatLngToPixel(map.getCenter(),
            map.getZoomLevel());
        assertNotNull("translation from LatLng to Pixel", pResult);
        double dResult = projection.getWrapWidth(map.getZoomLevel());
        assertTrue("getWrapWidth()", dResult > 0);
        // Not sure how to test tileCheckRange()

        // The map gets recentered when its created and these callbacks
        // may fire multiple times. Give the test some time to quiesce.
        new Timer() {
          public void run() {
            finishTest();
          }
        }.schedule(2000);
      }
    }, false);
  }

  /**
   * Create a simple projection and add it to a map.
   */
  public void testProjectionDefault() {
    loadApi(new Runnable() {
      public void run() {
        TileLayer tileLayer = initTileLayer();
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

        // The map gets recentered when its created and these callbacks
        // may fire multiple times. Give the test some time to quiesce.
        new Timer() {
          public void run() {
            finishTest();
          }
        }.schedule(2000);

      }
    }, false);
  }

  /**
   * Subclass the Projection abstract class and add it to a map.
   */
  public void testSubclassProjection() {
    loadApi(new Runnable() {
      public void run() {
        TileLayer tileLayer = initTileLayer();
        MapWidget map = new MapWidget();
        map.setSize("300px", "300px");
        Projection projection = new Projection() {

          @Override
          public Point fromLatLngToPixel(LatLng latlng, int zoomLevel) {
            assertNotNull(latlng);
            assertTrue("zoomLevel > 0", zoomLevel > 0);
            return Point.newInstance(1, 1);
          }

          @Override
          public LatLng fromPixelToLatLng(Point point, int zoomLevel,
              boolean unbounded) {
            assertNotNull(point);
            assertTrue("zoomLevel > 0", zoomLevel > 0);
            return LatLng.newInstance(45, 45);
          }

          @Override
          public double getWrapWidth(int zoomLevel) {
            assertTrue("zoomLevel > 0", zoomLevel > 0);
            return 100;
          }

          @Override
          public boolean tileCheckRange(TileIndex index, int zoomLevel,
              int tileSize) {
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

        // The map gets recentered when its created and these callbacks
        // may fire multiple times. Give the test some time to quiesce.
        new Timer() {
          public void run() {
            finishTest();
          }
        }.schedule(2000);
      }
    }, false);
  }
}

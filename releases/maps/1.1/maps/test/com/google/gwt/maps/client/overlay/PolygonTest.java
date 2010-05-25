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
package com.google.gwt.maps.client.overlay;

import com.google.gwt.maps.client.MapType;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.MapsTestCase;
import com.google.gwt.maps.client.TestUtilities;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Tests the Polygon class.
 */
public class PolygonTest extends MapsTestCase {
  @Override
  public String getModuleName() {
    return "com.google.gwt.maps.GoogleMapsTest";
  }

  /**
   * Runs before each test method.
   */
  @Override
  public void gwtSetUp() {
    TestUtilities.cleanDom();
  }

  /**
   * Test the getArea() method.
   */
  public void testGetArea() {
    loadApi(new Runnable() {
      public void run() {
        MapWidget map = new MapWidget(LatLng.newInstance(0, 0), 3);
        map.setSize("500px", "400px");
        LatLng[] points = { // 
        LatLng.newInstance(45, 45), //
            LatLng.newInstance(45, -45), //
            LatLng.newInstance(0, 0)};
        Polygon p = new Polygon(points);
        map.addOverlay(p);
        RootPanel.get().add(map);
        double result = p.getArea();
        assertTrue("non-negative area", result > 0);
      }
    });
  }

  /**
   * Test the simple Polygon constructor.
   */
  public void testPolygon() {
    loadApi(new Runnable() {
      public void run() {
        MapWidget map = new MapWidget(LatLng.newInstance(0, 0), 3);
        map.setSize("500px", "400px");
        LatLng[] points = { // 
        LatLng.newInstance(45, 45), //
            LatLng.newInstance(45, -45), //
            LatLng.newInstance(0, 0)};
        Polygon p = new Polygon(points);
        map.addOverlay(p);
        RootPanel.get().add(map);
      }
    });
  }

  /**
   * Tests the fromEncoded() methods.
   */
  public void testPolygonFromEncoded() {
    loadApi(new Runnable() {
      public void run() {
        MapWidget map = new MapWidget(LatLng.newInstance(33.75951619957536,
            -84.39289301633835), 20);
        map.setSize("500px", "400px");
        map.addMapType(MapType.getHybridMap());
        map.setCurrentMapType(MapType.getHybridMap());

        EncodedPolyline[] polylines = new EncodedPolyline[2];
        polylines[0] = EncodedPolyline.newInstance("{t`mEt_bbO?eAx@??dAy@?", 2,
            "BBBBB", 1);
        polylines[0].setColor("#ff00ff");
        polylines[0].setWeight(2);
        polylines[0].setOpacity(0.7);

        polylines[1] = EncodedPolyline.newInstance("au`mEz_bbO?sAbA@?pAcA?", 2,
            "BBBBB", 1, "#0000ff", 2, 0.9);

        Polygon theFountain = Polygon.fromEncoded(polylines, true, "#ff0000",
            0.3, true);

        int vertexCount = theFountain.getVertexCount();
        assertEquals("vertex count", vertexCount, 5);

        RootPanel.get().add(map);
      }
    });
  }

  /**
   * Test insertVertex() and deleteVerex().
   */
  public void testPolygonInsertDelete() {
    loadApi(new Runnable() {
      public void run() {
        MapWidget map = new MapWidget(LatLng.newInstance(0, 0), 3);
        map.setSize("500px", "400px");
        LatLng[] points = { // 
        LatLng.newInstance(45, 45), //
            LatLng.newInstance(45, -45), //
            LatLng.newInstance(0, 0)};
        Polygon p = new Polygon(points);
        map.addOverlay(p);
        RootPanel.get().add(map);
        p.insertVertex(1, LatLng.newInstance(45, 0));
        p.deleteVertex(3);
      }
    });
  }

  /**
   * Test the Polygon constructor with extra args, but not PolygonOptions.
   */
  public void testPolygonNoOpts() {
    loadApi(new Runnable() {
      public void run() {
        MapWidget map = new MapWidget(LatLng.newInstance(0, 0), 3);
        map.setSize("500px", "400px");
        LatLng[] points = { // 
        LatLng.newInstance(45, 45), //
            LatLng.newInstance(45, -45), //
            LatLng.newInstance(0, 0)};
        Polygon p = new Polygon(points, "#ff0000", 3, 1.0, "#0000ff", 0.3);
        map.addOverlay(p);
        RootPanel.get().add(map);
      }
    });
  }

  /**
   * Test the getVertexCount() method.
   */
  public void testPolygonVertexCount() {
    loadApi(new Runnable() {
      public void run() {
        MapWidget map = new MapWidget(LatLng.newInstance(0, 0), 3);
        map.setSize("500px", "400px");
        LatLng[] points = { // 
        LatLng.newInstance(45, 45), //
            LatLng.newInstance(45, -45), //
            LatLng.newInstance(0, 0)};
        Polygon p = new Polygon(points);
        map.addOverlay(p);
        RootPanel.get().add(map);
        assertEquals("vertex count", 3, p.getVertexCount());
      }
    });
  }

  /**
   * Test the Polygon constructor with the PolygonOptions in the constructor.
   */
  public void testPolygonWithOptions() {
    loadApi(new Runnable() {
      public void run() {
        MapWidget map = new MapWidget(LatLng.newInstance(0, 0), 3);
        map.setSize("500px", "400px");
        LatLng[] points = { // 
        LatLng.newInstance(45, 45), //
            LatLng.newInstance(45, -45), //
            LatLng.newInstance(0, 0)};
        PolygonOptions opts = PolygonOptions.newInstance();
        opts.setClickable(false);
        Polygon p = new Polygon(points, "#ff0000", 3, 1.0, "#0000ff", 0.3, opts);
        map.addOverlay(p);
        RootPanel.get().add(map);
      }
    });
  }

  /**
   * Test the Polygon constructor with the PolygonOptions in the constructor.
   */
  public void testPolygonWithOptionsClickable() {
    loadApi(new Runnable() {
      public void run() {
        MapWidget map = new MapWidget(LatLng.newInstance(0, 0), 3);
        map.setSize("500px", "400px");
        LatLng[] points = { // 
        LatLng.newInstance(45, 45), //
            LatLng.newInstance(45, -45), //
            LatLng.newInstance(0, 0)};
        PolygonOptions opts = PolygonOptions.newInstance(false);
        Polygon p = new Polygon(points, "#ff0000", 3, 1.0, "#0000ff", 0.3, opts);
        map.addOverlay(p);
        RootPanel.get().add(map);
      }
    });
  }
}

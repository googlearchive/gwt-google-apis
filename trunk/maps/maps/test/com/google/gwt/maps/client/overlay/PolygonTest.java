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

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.maps.client.MapType;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.TestUtilities;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Tests the Polygon class.
 */
public class PolygonTest extends GWTTestCase {
  // length of time to wait for asynchronous test to complete.
  static final int ASYNC_DELAY_MSEC = 5000;

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
   * Tests the fromEncoded() methods.
   */
  public void testPolygonFromEncoded() {
    MapWidget map = new MapWidget(new LatLng(33.75951619957536,
        -84.39289301633835), 20);
    map.setSize("500px", "400px");
    map.addMapType(MapType.getHybridMap());
    map.setCurrentMapType(MapType.getHybridMap());

    EncodedPolyline[] polylines = new EncodedPolyline[2];
    polylines[0] = EncodedPolyline.createEncodedPolyline(
        "{t`mEt_bbO?eAx@??dAy@?", 2, "BBBBB", 1);
    polylines[0].setColor("#ff00ff");
    polylines[0].setWeight(2);
    polylines[0].setOpacity(0.7);

    polylines[1] = EncodedPolyline.createEncodedPolyline(
        "au`mEz_bbO?sAbA@?pAcA?", 2, "BBBBB", 1, "#0000ff", 2, 0.9);

    Polygon theFountain = Polygon.fromEncoded(polylines, true, "#ff0000", 0.3,
        true);

    int vertexCount = theFountain.getVertexCount();
    assertEquals("vertex count", vertexCount, 5);

    RootPanel.get().add(map);
  }
}

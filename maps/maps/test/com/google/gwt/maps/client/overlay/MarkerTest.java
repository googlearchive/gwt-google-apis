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
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.TestUtilities;
import com.google.gwt.maps.client.event.MarkerClickHandler;
import com.google.gwt.maps.client.event.MarkerClickHandler.MarkerClickEvent;
import com.google.gwt.maps.client.geom.LatLng;

/**
 * Tests the Marker class.
 */
public class MarkerTest extends GWTTestCase {
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

  public void testMarkerGetTitle() {
    Marker marker = new Marker(new LatLng(0,0));
    String result = marker.getTitle();
    assertNull("expected null title", result);
    
    MarkerOptions mo = MarkerOptions.newInstance();
    mo.setTitle("Non-null title");
    marker = new Marker(new LatLng(0,1), mo);
    result = marker.getTitle();
    assertNotNull("expected non-null title", result);
  }
  
  /**
   * Test of a marker subclass.
   */
  public void testSubclassMarker() {
    class MyMarker extends Marker {
      final String infoText;

      MyMarker(LatLng latlng, String text) {
        super(latlng);
        infoText = text;
      }
    }

    final String marker1Text = "Marker One";

    MyMarker marker1 = new MyMarker(new LatLng(1, 1), marker1Text);
    marker1.addMarkerClickHandler(new MarkerClickHandler() {

      public void onClick(MarkerClickEvent event) {
        Marker m = event.getSender();
        assertTrue("instanceof MyMarker", m instanceof MyMarker);
        MyMarker myMarker = (MyMarker) m;
        assertEquals("marker 1 click event", marker1Text, myMarker.infoText);
        finishTest();
      }

    });

    final MapWidget map = new MapWidget();
    map.setSize("300px", "300px");

    map.addOverlay(marker1);
    delayTestFinish(ASYNC_DELAY_MSEC);
    
    marker1.trigger(new MarkerClickEvent(marker1));
  }
}

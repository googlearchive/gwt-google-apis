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
import com.google.gwt.maps.client.event.PolylineCancelLineHandler;
import com.google.gwt.maps.client.event.PolylineEndLineHandler;
import com.google.gwt.maps.client.event.PolylineLineUpdatedHandler;
import com.google.gwt.maps.client.event.PolylineCancelLineHandler.PolylineCancelLineEvent;
import com.google.gwt.maps.client.event.PolylineEndLineHandler.PolylineEndLineEvent;
import com.google.gwt.maps.client.event.PolylineLineUpdatedHandler.PolylineLineUpdatedEvent;
import com.google.gwt.maps.client.geom.LatLng;

/**
 * Tests the events attached to a Polyline and Polygon.
 */
public class PolyEventsTest extends GWTTestCase {
  // Length of time to wait for asynchronous test to complete.
  static final int ASYNC_DELAY_MSEC = 5000;

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
   * Test the "cancelline" event using a trigger.
   */
  public void testPolylineCancelLineTrigger() {
    final MapWidget m = new MapWidget();
    final Polyline polyline = setupPolyline(m);

    m.addOverlay(polyline);
    polyline.setDrawingEnabled();
    polyline.addPolylineCancelLineHandler(new PolylineCancelLineHandler() {

      public void onCancel(PolylineCancelLineEvent event) {
        finishTest();
      }

    });
    delayTestFinish(ASYNC_DELAY_MSEC);
    polyline.trigger(new PolylineCancelLineEvent(polyline));
  }

  /**
   * Test the "endline" event using a trigger.
   */
  public void testPolylineEndLineTrigger() {
    final MapWidget m = new MapWidget();
    final Polyline polyline = setupPolyline(m);
    final LatLng testLatLng = LatLng.newInstance(31,32);
    
    m.addOverlay(polyline);
    polyline.setDrawingEnabled();
    polyline.addPolylineEndLineHandler(new PolylineEndLineHandler() {

      public void onEnd(PolylineEndLineEvent event) {
        assertEquals("event.getLatLng()", testLatLng, event.getLatLng());
        finishTest();
      }

    });
    delayTestFinish(ASYNC_DELAY_MSEC);
    polyline.trigger(new PolylineEndLineEvent(polyline, testLatLng));
  }

  /**
   * Test the "lineupdated" event using a trigger.
   */
  public void testPolylineLineUpdatedTrigger() {
    final MapWidget m = new MapWidget();
    final Polyline polyline = setupPolyline(m);

    m.addOverlay(polyline);
    polyline.setEditingEnabled(true);
    polyline.addPolylineLineUpdatedHandler(new PolylineLineUpdatedHandler() {

      public void onUpdate(PolylineLineUpdatedEvent event) {
        finishTest();
      }

    });
    delayTestFinish(ASYNC_DELAY_MSEC);
    polyline.trigger(new PolylineLineUpdatedEvent(polyline));
  }

  private Polyline setupPolyline(MapWidget m) {
    m.setCenter(LatLng.newInstance(37.4569, -122.1569));
    m.setZoomLevel(8);

    LatLng[] polylinePoints = new LatLng[2];
    polylinePoints[1] = m.getBounds().getNorthEast();
    polylinePoints[0] = m.getCenter();
    final Polyline polyline = new Polyline(polylinePoints);
    return polyline;
  }
}

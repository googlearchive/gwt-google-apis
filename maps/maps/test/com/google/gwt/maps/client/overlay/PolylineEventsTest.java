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

import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.MapsTestCase;
import com.google.gwt.maps.client.TestUtilities;
import com.google.gwt.maps.client.event.PolylineCancelLineHandler;
import com.google.gwt.maps.client.event.PolylineEndLineHandler;
import com.google.gwt.maps.client.event.PolylineLineUpdatedHandler;
import com.google.gwt.maps.client.event.PolylineMouseOutHandler;
import com.google.gwt.maps.client.event.PolylineMouseOverHandler;
import com.google.gwt.maps.client.event.PolylineCancelLineHandler.PolylineCancelLineEvent;
import com.google.gwt.maps.client.event.PolylineEndLineHandler.PolylineEndLineEvent;
import com.google.gwt.maps.client.event.PolylineLineUpdatedHandler.PolylineLineUpdatedEvent;
import com.google.gwt.maps.client.event.PolylineMouseOutHandler.PolylineMouseOutEvent;
import com.google.gwt.maps.client.event.PolylineMouseOverHandler.PolylineMouseOverEvent;
import com.google.gwt.maps.client.geom.LatLng;

/**
 * Tests the events attached to a Polyline and Polygon.
 */
public class PolylineEventsTest extends MapsTestCase {

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
   * Test the "cancelline" event using a trigger.
   */
  public void testPolylineCancelLineTrigger() {
    loadApi(new Runnable() {
      public void run() {
        final MapWidget m = new MapWidget();
        final Polyline polyline = setupPolyline(m);

        m.addOverlay(polyline);
        polyline.setDrawingEnabled();
        polyline.addPolylineCancelLineHandler(new PolylineCancelLineHandler() {

          public void onCancel(PolylineCancelLineEvent event) {
            finishTest();
          }

        });
        polyline.trigger(new PolylineCancelLineEvent(polyline));
      }
    }, false);
  }

  /**
   * Test the "endline" event using a trigger.
   */
  public void testPolylineEndLineTrigger() {
    loadApi(new Runnable() {
      public void run() {
        final MapWidget m = new MapWidget();
        final Polyline polyline = setupPolyline(m);
        final LatLng testLatLng = LatLng.newInstance(31, 32);

        m.addOverlay(polyline);
        polyline.setDrawingEnabled();
        polyline.addPolylineEndLineHandler(new PolylineEndLineHandler() {

          public void onEnd(PolylineEndLineEvent event) {
            assertEquals("event.getLatLng()", testLatLng, event.getLatLng());
            finishTest();
          }

        });
        polyline.trigger(new PolylineEndLineEvent(polyline, testLatLng));
      }
    }, false);
  }

  /**
   * Test the "lineupdated" event using a trigger.
   */
  public void testPolylineLineUpdatedTrigger() {
    loadApi(new Runnable() {
      public void run() {
        final MapWidget m = new MapWidget();
        final Polyline polyline = setupPolyline(m);

        m.addOverlay(polyline);
        polyline.setEditingEnabled(true);
        polyline.addPolylineLineUpdatedHandler(new PolylineLineUpdatedHandler() {

          public void onUpdate(PolylineLineUpdatedEvent event) {
            finishTest();
          }

        });
        polyline.trigger(new PolylineLineUpdatedEvent(polyline));
      }
    }, false);
  }

  /**
   * Test the "mouseout" event using a trigger.
   */
  public void testPolylineMouseOut() {
    loadApi(new Runnable() {
      public void run() {
        final MapWidget m = new MapWidget();
        final Polyline polyline = setupPolyline(m);
        m.addOverlay(polyline);
        polyline.addPolylineMouseOutHandler(new PolylineMouseOutHandler() {

          public void onMouseOut(PolylineMouseOutEvent event) {
            finishTest();
          }

        });
        polyline.trigger(new PolylineMouseOutEvent(polyline));
      }
    }, false);
  }

  /**
   * Test the "mouseover" event using a trigger.
   */
  public void testPolylineMouseOver() {
    loadApi(new Runnable() {
      public void run() {
        final MapWidget m = new MapWidget();
        final Polyline polyline = setupPolyline(m);
        m.addOverlay(polyline);
        polyline.addPolylineMouseOverHandler(new PolylineMouseOverHandler() {

          public void onMouseOver(PolylineMouseOverEvent event) {
            finishTest();
          }

        });
        polyline.trigger(new PolylineMouseOverEvent(polyline));
      }
    }, false);
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

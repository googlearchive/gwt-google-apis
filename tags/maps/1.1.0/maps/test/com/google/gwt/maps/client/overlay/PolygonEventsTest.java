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
import com.google.gwt.maps.client.event.PolygonCancelLineHandler;
import com.google.gwt.maps.client.event.PolygonEndLineHandler;
import com.google.gwt.maps.client.event.PolygonLineUpdatedHandler;
import com.google.gwt.maps.client.event.PolygonMouseOutHandler;
import com.google.gwt.maps.client.event.PolygonMouseOverHandler;
import com.google.gwt.maps.client.event.PolygonCancelLineHandler.PolygonCancelLineEvent;
import com.google.gwt.maps.client.event.PolygonEndLineHandler.PolygonEndLineEvent;
import com.google.gwt.maps.client.event.PolygonLineUpdatedHandler.PolygonLineUpdatedEvent;
import com.google.gwt.maps.client.event.PolygonMouseOutHandler.PolygonMouseOutEvent;
import com.google.gwt.maps.client.event.PolygonMouseOverHandler.PolygonMouseOverEvent;
import com.google.gwt.maps.client.geom.LatLng;

/**
 * Tests the events attached to a Polygon and Polygon.
 */
public class PolygonEventsTest extends MapsTestCase {

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
  public void testPolygonCancelLineTrigger() {
    loadApi(new Runnable() {
      public void run() {
        final MapWidget m = new MapWidget();
        final Polygon polyline = setupPolygon(m);

        m.addOverlay(polyline);
        polyline.setDrawingEnabled();
        polyline.addPolygonCancelLineHandler(new PolygonCancelLineHandler() {

          public void onCancel(PolygonCancelLineEvent event) {
            finishTest();
          }

        });
        polyline.trigger(new PolygonCancelLineEvent(polyline));
      }
    }, false);
  }

  /**
   * Test the "endline" event using a trigger.
   */
  public void testPolygonEndLineTrigger() {
    loadApi(new Runnable() {
      public void run() {
        final MapWidget m = new MapWidget();
        final Polygon polyline = setupPolygon(m);
        final LatLng testLatLng = LatLng.newInstance(31, 32);

        m.addOverlay(polyline);
        polyline.setDrawingEnabled();
        polyline.addPolygonEndLineHandler(new PolygonEndLineHandler() {

          public void onEnd(PolygonEndLineEvent event) {
            assertEquals("event.getLatLng()", testLatLng, event.getLatLng());
            finishTest();
          }

        });
        polyline.trigger(new PolygonEndLineEvent(polyline, testLatLng));
      }
    }, false);
  }

  /**
   * Test the "lineupdated" event using a trigger.
   */
  public void testPolygonLineUpdatedTrigger() {
    loadApi(new Runnable() {
      public void run() {
        final MapWidget m = new MapWidget();
        final Polygon polyline = setupPolygon(m);

        m.addOverlay(polyline);
        polyline.setEditingEnabled(true);
        polyline.addPolygonLineUpdatedHandler(new PolygonLineUpdatedHandler() {

          public void onUpdate(PolygonLineUpdatedEvent event) {
            finishTest();
          }

        });
        polyline.trigger(new PolygonLineUpdatedEvent(polyline));
      }
    }, false);
  }

  /**
   * Test the "mouseout" event using a trigger.
   */
  public void testPolygonMouseOut() {
    loadApi(new Runnable() {
      public void run() {
        final MapWidget m = new MapWidget();
        final Polygon polyline = setupPolygon(m);
        m.addOverlay(polyline);
        polyline.addPolygonMouseOutHandler(new PolygonMouseOutHandler() {

          public void onMouseOut(PolygonMouseOutEvent event) {
            finishTest();
          }

        });
        polyline.trigger(new PolygonMouseOutEvent(polyline));
      }
    }, false);
  }

  /**
   * Test the "mouseover" event using a trigger.
   */
  public void testPolygonMouseOver() {
    loadApi(new Runnable() {
      public void run() {
        final MapWidget m = new MapWidget();
        final Polygon polyline = setupPolygon(m);
        m.addOverlay(polyline);
        polyline.addPolygonMouseOverHandler(new PolygonMouseOverHandler() {

          public void onMouseOver(PolygonMouseOverEvent event) {
            finishTest();
          }

        });
        polyline.trigger(new PolygonMouseOverEvent(polyline));
      }
    }, false);
  }

  private Polygon setupPolygon(MapWidget m) {
    m.setCenter(LatLng.newInstance(37.4569, -122.1569));
    m.setZoomLevel(8);

    LatLng[] polylinePoints = new LatLng[2];
    polylinePoints[1] = m.getBounds().getNorthEast();
    polylinePoints[0] = m.getCenter();
    final Polygon polyline = new Polygon(polylinePoints);
    return polyline;
  }
}

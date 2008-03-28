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
package com.google.gwt.maps.client;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.maps.client.event.MapAddMapTypeHandler;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.event.MapDoubleClickHandler;
import com.google.gwt.maps.client.event.MapMoveEndHandler;
import com.google.gwt.maps.client.event.MapMoveHandler;
import com.google.gwt.maps.client.event.MapMoveStartHandler;
import com.google.gwt.maps.client.event.MapRemoveMapTypeHandler;
import com.google.gwt.maps.client.event.MapRightClickHandler;
import com.google.gwt.maps.client.event.MapAddMapTypeHandler.MapAddMapTypeEvent;
import com.google.gwt.maps.client.event.MapClickHandler.MapClickEvent;
import com.google.gwt.maps.client.event.MapDoubleClickHandler.MapDoubleClickEvent;
import com.google.gwt.maps.client.event.MapMoveEndHandler.MapMoveEndEvent;
import com.google.gwt.maps.client.event.MapMoveHandler.MapMoveEvent;
import com.google.gwt.maps.client.event.MapMoveStartHandler.MapMoveStartEvent;
import com.google.gwt.maps.client.event.MapRemoveMapTypeHandler.MapRemoveMapTypeEvent;
import com.google.gwt.maps.client.event.MapRightClickHandler.MapRightClickEvent;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.Overlay;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Tests for the Maps events.
 * 
 * Design Note(zundel): These events are, in theory, asynchronous, but as I
 * wrote these test cases, it seems that they are called synchronously in many
 * cases. Nevertheless, I'm leaving the asynchronous test infrastructure in case
 * the underlying implementation changes.
 */
public class MapWidgetEventsTest extends GWTTestCase {

  // Used as a flag to test some trigger callback methods.
  static boolean passed;

  @Override
  public String getModuleName() {
    return "com.google.gwt.maps.GoogleMapsTest";
  }

  /**
   * This test can use the built-in maps triggering mechanism w/o resorting to
   * using one of the trigger() methods.
   */
  public void testMapAddMapTypeEvent() {

    MapWidget m = new MapWidget();
    m.addMapAddMapTypeHandler(new MapAddMapTypeHandler() {

      public void onAddMapType(MapAddMapTypeEvent event) {
        assertTrue("maptype doesn't match", event.getType().equals(
            MapType.getMarsElevationMap()));
        finishTest();
      }

    });
    RootPanel.get().add(m);
    delayTestFinish(5000);
    m.addMapType(MapType.getMarsElevationMap());
  }

  public void testMapAddMapTypeTrigger() {

    MapWidget m = new MapWidget();
    m.addMapAddMapTypeHandler(new MapAddMapTypeHandler() {

      public void onAddMapType(MapAddMapTypeEvent event) {
        assertTrue("maptype doesn't match", event.getType().equals(
            MapType.getMarsElevationMap()));
        finishTest();
      }

    });
    RootPanel.get().add(m);
    MapAddMapTypeEvent e = new MapAddMapTypeEvent(m,
        MapType.getMarsElevationMap());
    delayTestFinish(5000);
    m.trigger(e);
  }

  public void testMapClickTrigger() {
    MapWidget m = new MapWidget();
    m.addMapClickHandler(new MapClickHandler() {

      public void onClick(MapClickEvent e) {
        Overlay o = e.getOverlay();
        LatLng p = e.getLatLng();
        assertNotNull("maker is null", o);
        Marker marker = (Marker) o;
        assertTrue(marker.getPoint().getLatitude() == 12.34);
        assertTrue(marker.getPoint().getLongitude() == -22.2);
        assertNotNull("point is null", p);
        assertTrue(p.getLatitude() == 10.1);
        assertTrue(p.getLongitude() == 12.2);
        finishTest();
      }

    });
    RootPanel.get().add(m);
    Marker marker = new Marker(new LatLng(12.34, -22.2));
    MapClickEvent e = new MapClickEvent(m, marker, new LatLng(10.1, 12.2));
    delayTestFinish(5000);
    m.trigger(e);
  }

  public void testMapDoubleClickTrigger() {
    MapWidget m = new MapWidget();
    m.addMapDoubleClickHandler(new MapDoubleClickHandler() {

      public void onDoubleClick(MapDoubleClickEvent e) {
        LatLng p = e.getLatLng();
        assertNotNull("point is null", p);
        assertTrue(p.getLatitude() == 10.1);
        assertTrue(p.getLongitude() == 12.2);
        finishTest();
      }

    });
    RootPanel.get().add(m);
    MapDoubleClickEvent e = new MapDoubleClickEvent(m, new LatLng(10.1, 12.2));
    delayTestFinish(5000);
    m.trigger(e);
  }

  public void testMapMoveEndEvent() {
    delayTestFinish(15000);

    final LatLng start = new LatLng(37.4419, -122.1419);
    final LatLng end = new LatLng(37.4569, -122.1569);

    final MapWidget m = new MapWidget();

    m.addMapMoveEndHandler(new MapMoveEndHandler() {

      public void onMoveEnd(MapMoveEndEvent event) {
        finishTest();
      }

    });
    RootPanel.get().add(m);
  }

  public void testMapMoveEndTrigger() {
    delayTestFinish(5000);

    MapWidget m = new MapWidget(new LatLng(37.4419, -122.1419), 13);
    
    MapMoveEndHandler handler = new MapMoveEndHandler() {

      public void onMoveEnd(MapMoveEndEvent event) {
        System.out.println("onMoveEnd()");
        finishTest();
      }

    };
      
    m.addMapMoveEndHandler(handler);
    
    RootPanel.get().add(m);

    MapMoveEndEvent e = new MapMoveEndEvent(m);
    m.trigger(e);
  }

  // public void testMapMoveEvent() {
  // assertTrue("Test not implemented.", false);
  // }

//  /**
//   * This test can use the built-in maps triggering mechanism w/o resorting to
//   * using one of the trigger() methods.
//   * 
//   * TODO(zundel): this test is broken
//   */
//  public void testMapMoveStartEvent() {
//    delayTestFinish(15000);
//
//    final LatLng start = new LatLng(37.4419, -122.1419);
//    final LatLng end = new LatLng(37.4569, -122.1569);
//
//    final MapWidget m = new MapWidget();
//
//    m.addMapMoveStartHandler(new MapMoveStartHandler() {
//
//      public void onMoveStart(MapMoveStartEvent event) {
//        finishTest();
//      }
//
//    });
//    RootPanel.get().add(m);
//
//    m.addMapLoadHandler(new MapLoadHandler() {
//
//      public void onLoad(MapLoadEvent event) {
//        System.out.println("load event popped.");
//        m.setCenter(start);
//        Timer cmd = new Timer() {
//
//          @Override
//          public void run() {
//            /* Move the map */
//            System.out.println("Timer popped");
//            m.panTo(end);
//          }
//        };
//        cmd.schedule(100);
//      }
//
//    });
//  }

  public void testMapMoveStartTrigger() {
    delayTestFinish(5000);
    MapWidget m = new MapWidget();
    m.addMapMoveStartHandler(new MapMoveStartHandler() {

      public void onMoveStart(MapMoveStartEvent event) {
        finishTest();
      }

    });
    RootPanel.get().add(m);
    MapMoveStartEvent e = new MapMoveStartEvent(m);
    m.trigger(e);
  }

  public void testMapMoveTrigger() {
    delayTestFinish(5000);
    MapWidget m = new MapWidget();
    m.addMapMoveHandler(new MapMoveHandler() {

      public void onMove(MapMoveEvent event) {
        finishTest();
      }

    });
    RootPanel.get().add(m);
    MapMoveEvent e = new MapMoveEvent(m);
    m.trigger(e);
  }
  
  /**
   * This test can use the built-in maps triggering mechanism w/o resorting to
   * using one of the trigger() methods.
   */
  public void testMapRemoveMapTypeEvent() {

    MapWidget m = new MapWidget();
    m.addMapRemoveMapTypeHandler(new MapRemoveMapTypeHandler() {

      public void onRemoveMapType(MapRemoveMapTypeEvent event) {
        assertTrue("maptype doesn't match", event.getType().equals(
            MapType.getNormalMap()));
        finishTest();
      }

    });
    RootPanel.get().add(m);
    delayTestFinish(5000);
    m.removeMapType(MapType.getNormalMap());
  }

  public void testMapRemoveMapTypeTrigger() {

    MapWidget m = new MapWidget();
    m.addMapRemoveMapTypeHandler(new MapRemoveMapTypeHandler() {

      public void onRemoveMapType(MapRemoveMapTypeEvent event) {
        assertTrue("maptype doesn't match", event.getType().equals(
            MapType.getNormalMap()));
        finishTest();
      }

    });
    MapRemoveMapTypeEvent e = new MapRemoveMapTypeEvent(m,
        MapType.getNormalMap());
    delayTestFinish(5000);
    m.trigger(e);
  }

  public void testMapRightClickTrigger() {
    MapWidget m = new MapWidget();
    m.addMapRightClickHandler(new MapRightClickHandler() {

      public void onRightClick(MapRightClickEvent e) {
        Point p = e.getPoint();
        Marker marker = (Marker) e.getOverlay();
        Element elem = e.getElement();
        assertNotNull("point is null", p);
        assertTrue(p.getX() == 101);
        assertTrue(p.getY() == 222);
        assertNotNull("element is null", elem);
        assertNotNull("maker is null", marker);
        assertTrue(marker.getPoint().getLatitude() == 12.34);
        assertTrue(marker.getPoint().getLongitude() == -22.2);
        finishTest();
      }

    });
    RootPanel.get().add(m);
    Marker marker = new Marker(new LatLng(12.34, -22.2));
    MapRightClickEvent e = new MapRightClickEvent(m, new Point(101, 222),
        m.getElement(), marker);
    delayTestFinish(5000);
    m.trigger(e);
  }

}

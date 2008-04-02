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
import com.google.gwt.maps.client.event.MapInfoWindowBeforeCloseHandler;
import com.google.gwt.maps.client.event.MapInfoWindowCloseHandler;
import com.google.gwt.maps.client.event.MapInfoWindowOpenHandler;
import com.google.gwt.maps.client.event.MapAddMapTypeHandler;
import com.google.gwt.maps.client.event.MapAddOverlayHandler;
import com.google.gwt.maps.client.event.MapClearOverlaysHandler;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.event.MapDoubleClickHandler;
import com.google.gwt.maps.client.event.MapDragEndHandler;
import com.google.gwt.maps.client.event.MapDragHandler;
import com.google.gwt.maps.client.event.MapDragStartHandler;
import com.google.gwt.maps.client.event.MapMouseMoveHandler;
import com.google.gwt.maps.client.event.MapMouseOutHandler;
import com.google.gwt.maps.client.event.MapMouseOverHandler;
import com.google.gwt.maps.client.event.MapMoveEndHandler;
import com.google.gwt.maps.client.event.MapMoveHandler;
import com.google.gwt.maps.client.event.MapMoveStartHandler;
import com.google.gwt.maps.client.event.MapRemoveMapTypeHandler;
import com.google.gwt.maps.client.event.MapRemoveOverlayHandler;
import com.google.gwt.maps.client.event.MapRightClickHandler;
import com.google.gwt.maps.client.event.MapZoomEndHandler;
import com.google.gwt.maps.client.event.MapInfoWindowBeforeCloseHandler.MapInfoWindowBeforeCloseEvent;
import com.google.gwt.maps.client.event.MapInfoWindowCloseHandler.MapInfoWindowCloseEvent;
import com.google.gwt.maps.client.event.MapInfoWindowOpenHandler.MapInfoWindowOpenEvent;
import com.google.gwt.maps.client.event.MapAddMapTypeHandler.MapAddMapTypeEvent;
import com.google.gwt.maps.client.event.MapAddOverlayHandler.MapAddOverlayEvent;
import com.google.gwt.maps.client.event.MapClearOverlaysHandler.MapClearOverlaysEvent;
import com.google.gwt.maps.client.event.MapClickHandler.MapClickEvent;
import com.google.gwt.maps.client.event.MapDoubleClickHandler.MapDoubleClickEvent;
import com.google.gwt.maps.client.event.MapDragEndHandler.MapDragEndEvent;
import com.google.gwt.maps.client.event.MapDragHandler.MapDragEvent;
import com.google.gwt.maps.client.event.MapDragStartHandler.MapDragStartEvent;
import com.google.gwt.maps.client.event.MapMouseMoveHandler.MapMouseMoveEvent;
import com.google.gwt.maps.client.event.MapMouseOutHandler.MapMouseOutEvent;
import com.google.gwt.maps.client.event.MapMouseOverHandler.MapMouseOverEvent;
import com.google.gwt.maps.client.event.MapMoveEndHandler.MapMoveEndEvent;
import com.google.gwt.maps.client.event.MapMoveHandler.MapMoveEvent;
import com.google.gwt.maps.client.event.MapMoveStartHandler.MapMoveStartEvent;
import com.google.gwt.maps.client.event.MapRemoveMapTypeHandler.MapRemoveMapTypeEvent;
import com.google.gwt.maps.client.event.MapRemoveOverlayHandler.MapRemoveOverlayEvent;
import com.google.gwt.maps.client.event.MapRightClickHandler.MapRightClickEvent;
import com.google.gwt.maps.client.event.MapZoomEndHandler.MapZoomEndEvent;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.Overlay;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Tests for the MapWidget events.
 * 
 * Design Note(zundel): These events are, in theory, asynchronous, but as I
 * wrote these test cases, it seems that they are called synchronously in many
 * cases. Nevertheless, I'm leaving the asynchronous test infrastructure in case
 * the underlying implementation changes.
 * 
 * Most events have a test that is triggered by the GEvent.trigger() mechanism
 * (testXXXTrigger()) as well as a test that is triggered by API calls
 * (testXXXEvent()). Some of the events depend on user interaction and cannot be
 * triggered by the Maps API calls.
 */
public class MapWidgetEventsTest extends GWTTestCase {
  // length of time to wait for asyncronous test to complete.
  static final int ASYNC_DELAY_MSEC = 5000;

  // Used as a flag to test some trigger callback methods.
  static boolean passed;

  @Override
  public String getModuleName() {
    return "com.google.gwt.maps.GoogleMapsTest";
  }

  public void testInfoWindowBeforeCloseEvent() {
    final MapWidget m = new MapWidget();
    m.addInfoWindowBeforeCloseHandler(new MapInfoWindowBeforeCloseHandler() {

      public void onInfoWindowBeforeClose(MapInfoWindowBeforeCloseEvent event) {
        MapWidget sender = event.getSender();
        assertEquals(sender, m);
        finishTest();
      }

    });

    RootPanel.get().add(m);

    final InfoWindow info = m.getInfoWindow();

    // If we do not wait for the infowindowopen event before calling close,
    // the close event will never fire.
    m.addInfoWindowOpenHandler(new MapInfoWindowOpenHandler() {
      public void onInfoWindowOpen(MapInfoWindowOpenEvent event) {
        info.close();
      }
    });
    info.open(m.getCenter(), new InfoWindowContent("Hello Maps!"));

    delayTestFinish(ASYNC_DELAY_MSEC);
  }

  public void testInfoWindowBeforeCloseTrigger() {
    final MapWidget m = new MapWidget();
    m.addInfoWindowBeforeCloseHandler(new MapInfoWindowBeforeCloseHandler() {

      public void onInfoWindowBeforeClose(MapInfoWindowBeforeCloseEvent event) {
        MapWidget sender = event.getSender();
        assertEquals(sender, m);
        finishTest();
      }

    });
    RootPanel.get().add(m);
    MapInfoWindowBeforeCloseEvent e = new MapInfoWindowBeforeCloseEvent(m);
    delayTestFinish(ASYNC_DELAY_MSEC);
    m.trigger(e);
  }

  public void testInfoWindowCloseEvent() {
    final MapWidget m = new MapWidget();
    m.setSize("300px", "300px");
    RootPanel.get().add(m);

    m.addInfoWindowCloseHandler(new MapInfoWindowCloseHandler() {

      public void onInfoWindowClose(MapInfoWindowCloseEvent event) {
        MapWidget sender = event.getSender();
        assertEquals(sender, m);
        finishTest();
      }

    });

    final InfoWindow info = m.getInfoWindow();

    // If we do not wait for the infowindowopen event before calling close,
    // the close event will never fire.
    m.addInfoWindowOpenHandler(new MapInfoWindowOpenHandler() {
      public void onInfoWindowOpen(MapInfoWindowOpenEvent event) {
        info.close();
      }
    });

    info.open(m.getCenter(), new InfoWindowContent("Hello Maps!"));
    delayTestFinish(ASYNC_DELAY_MSEC);
  }

  public void testInfoWindowCloseTrigger() {
    final MapWidget m = new MapWidget();
    m.addInfoWindowCloseHandler(new MapInfoWindowCloseHandler() {

      public void onInfoWindowClose(MapInfoWindowCloseEvent event) {
        MapWidget sender = event.getSender();
        assertEquals(sender, m);
        finishTest();
      }

    });
    RootPanel.get().add(m);
    MapInfoWindowCloseEvent e = new MapInfoWindowCloseEvent(m);
    delayTestFinish(ASYNC_DELAY_MSEC);
    m.trigger(e);
  }

  public void testInfoWindowOpenEvent() {
    final MapWidget m = new MapWidget();
    m.addInfoWindowOpenHandler(new MapInfoWindowOpenHandler() {

      public void onInfoWindowOpen(MapInfoWindowOpenEvent event) {
        MapWidget sender = event.getSender();
        assertEquals(sender, m);
        finishTest();
      }

    });

    RootPanel.get().add(m);

    delayTestFinish(ASYNC_DELAY_MSEC);
    InfoWindow info = m.getInfoWindow();
    info.open(m.getCenter(), new InfoWindowContent("Hello Maps!"));
  }

  public void testInfoWindowOpenTrigger() {
    final MapWidget m = new MapWidget();
    RootPanel.get().add(m);
    m.addInfoWindowOpenHandler(new MapInfoWindowOpenHandler() {

      public void onInfoWindowOpen(MapInfoWindowOpenEvent event) {
        MapWidget sender = event.getSender();
        assertEquals(sender, m);
        finishTest();
      }

    });

    MapInfoWindowOpenEvent e = new MapInfoWindowOpenEvent(m);
    delayTestFinish(ASYNC_DELAY_MSEC);
    m.trigger(e);
  }

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
    delayTestFinish(ASYNC_DELAY_MSEC);
    m.addMapType(MapType.getMarsElevationMap());
  }

  public void testMapAddMapTypeTrigger() {
    final MapWidget m = new MapWidget();
    m.addMapAddMapTypeHandler(new MapAddMapTypeHandler() {

      public void onAddMapType(MapAddMapTypeEvent event) {
        MapWidget sender = event.getSender();
        assertEquals(sender, m);
        assertTrue("maptype doesn't match", event.getType().equals(
            MapType.getMarsElevationMap()));
        finishTest();
      }

    });
    RootPanel.get().add(m);
    MapAddMapTypeEvent e = new MapAddMapTypeEvent(m,
        MapType.getMarsElevationMap());
    delayTestFinish(ASYNC_DELAY_MSEC);
    m.trigger(e);
  }

  public void testMapAddOverlayEvent() {
    final MapWidget m = new MapWidget();
    final Marker marker = new Marker(new LatLng(0.0, 0.0));
    m.addMapAddOverlayHandler(new MapAddOverlayHandler() {

      public void onAddOverlay(MapAddOverlayEvent event) {
        assertEquals(event.getSender(), m);
        assertEquals(event.getOverlay(), marker);
        finishTest();
      }

    });
    RootPanel.get().add(m);
    delayTestFinish(ASYNC_DELAY_MSEC);
    m.addOverlay(marker);
  }

  public void testMapAddOverlayTrigger() {
    final MapWidget m = new MapWidget();
    final Marker marker = new Marker(new LatLng(0.0, 0.0));
    m.addMapAddOverlayHandler(new MapAddOverlayHandler() {

      public void onAddOverlay(MapAddOverlayEvent event) {
        assertEquals(event.getSender(), m);
        assertEquals(event.getOverlay(), marker);
        finishTest();
      }

    });
    RootPanel.get().add(m);
    MapAddOverlayEvent e = new MapAddOverlayEvent(m, marker);
    delayTestFinish(ASYNC_DELAY_MSEC);
    m.trigger(e);
  }

  /**
   * This test can use the built-in maps triggering mechanism w/o resorting to
   * using one of the trigger() methods.
   */
  public void testMapClearOverlayEvent() {
    final MapWidget m = new MapWidget();
    final Marker marker = new Marker(new LatLng(0.0, 0.0));
    m.addMapClearOverlaysHandler(new MapClearOverlaysHandler() {

      public void onClearOverlays(MapClearOverlaysEvent event) {
        assertEquals(event.getSender(), m);
        finishTest();
      }

    });
    RootPanel.get().add(m);
    delayTestFinish(ASYNC_DELAY_MSEC);
    m.addOverlay(marker);
    m.clearOverlays();
  }

  public void testMapClearOverlayTrigger() {

    final MapWidget m = new MapWidget();
    final Marker marker = new Marker(new LatLng(0.0, 0.0));
    m.addMapClearOverlaysHandler(new MapClearOverlaysHandler() {

      public void onClearOverlays(MapClearOverlaysEvent event) {
        assertEquals(event.getSender(), m);

        finishTest();
      }

    });
    RootPanel.get().add(m);
    MapClearOverlaysEvent e = new MapClearOverlaysEvent(m);
    delayTestFinish(ASYNC_DELAY_MSEC);
    m.trigger(e);
  }

  /**
   * Note: testMapClickEvent() can't be implemented as there is no way to create
   * a "click" event by API calls apart from GEvent.trigger().
   */
  public void testMapClickTrigger() {
    final MapWidget m = new MapWidget();
    m.addMapClickHandler(new MapClickHandler() {

      public void onClick(MapClickEvent event) {
        Overlay o = event.getOverlay();
        LatLng p = event.getLatLng();
        assertEquals(event.getSender(), m);
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
    delayTestFinish(ASYNC_DELAY_MSEC);
    m.trigger(e);
  }

  /**
   * Note: testMapDoubleClickEvent() can't be implemented as there is no way to
   * create a "dblclick" event by API calls apart from GEvent.trigger().
   */
  public void testMapDoubleClickTrigger() {
    final MapWidget m = new MapWidget();
    m.addMapDoubleClickHandler(new MapDoubleClickHandler() {

      public void onDoubleClick(MapDoubleClickEvent event) {
        assertEquals(event.getSender(), m);
        LatLng p = event.getLatLng();
        assertNotNull("point is null", p);
        assertTrue(p.getLatitude() == 10.1);
        assertTrue(p.getLongitude() == 12.2);
        finishTest();
      }

    });
    RootPanel.get().add(m);
    MapDoubleClickEvent e = new MapDoubleClickEvent(m, new LatLng(10.1, 12.2));
    delayTestFinish(ASYNC_DELAY_MSEC);
    m.trigger(e);
  }

  /**
   * Note: testDragEndEvent() can't be implemented as there is no way to create
   * a "dragend" event by API calls apart from GEvent.trigger().
   */
  public void testMapDragEndTrigger() {
    final MapWidget m = new MapWidget();
    RootPanel.get().add(m);

    m.addMapDragEndHandler(new MapDragEndHandler() {

      public void onDragEnd(MapDragEndEvent event) {
        assertEquals(event.getSender(), m);
        finishTest();
      }
    });

    delayTestFinish(ASYNC_DELAY_MSEC);
    MapDragEndEvent e = new MapDragEndEvent(m);
    m.trigger(e);
  }

  /**
   * Note: testMapDrag() can't be implemented as there is no way to create a
   * "drag" event by API calls apart from GEvent.trigger().
   */
  public void testMapDragTrigger() {
    final MapWidget m = new MapWidget();
    RootPanel.get().add(m);

    m.addMapDragHandler(new MapDragHandler() {

      public void onDrag(MapDragEvent event) {
        assertEquals(event.getSender(), m);
        finishTest();
      }
    });

    delayTestFinish(ASYNC_DELAY_MSEC);
    MapDragEvent e = new MapDragEvent(m);
    m.trigger(e);
  }

  /**
   * Note: testMapDragStartEvent() can't be implemented as there is no way to
   * create a "dragstart" event by API calls apart from GEvent.trigger().
   */
  public void testMapDragStartTrigger() {
    final MapWidget m = new MapWidget();
    RootPanel.get().add(m);

    m.addMapDragStartHandler(new MapDragStartHandler() {

      public void onDragStart(MapDragStartEvent event) {
        assertEquals(event.getSender(), m);
        finishTest();
      }
    });

    delayTestFinish(ASYNC_DELAY_MSEC);
    MapDragStartEvent e = new MapDragStartEvent(m);
    m.trigger(e);
  }

  /**
   * Note: testMapMouseMoveEvent() can't be implemented as there is no way to
   * create a "mousemove" event by API calls apart from GEvent.trigger().
   */
  public void testMapMouseMoveTrigger() {
    final MapWidget m = new MapWidget();
    final LatLng latlng = new LatLng(1.0, 2.0);

    m.addMapMouseMoveHandler(new MapMouseMoveHandler() {

      public void onMouseMove(MapMouseMoveEvent event) {
        assertEquals(event.getSender(), m);
        assertEquals(latlng, event.getLatLng());
        finishTest();
      }

    });
    RootPanel.get().add(m);
    MapMouseMoveEvent e = new MapMouseMoveEvent(m, latlng);
    delayTestFinish(ASYNC_DELAY_MSEC);
    m.trigger(e);
  }

  /**
   * Note: testMapMouseOutEvent() can't be implemented as there is no way to
   * create a "mouseout" event by API calls apart from GEvent.trigger().
   */
  public void testMapMouseOutTrigger() {
    final MapWidget m = new MapWidget();
    final LatLng latlng = new LatLng(1.0, 2.0);

    m.addMapMouseOutHandler(new MapMouseOutHandler() {

      public void onMouseOut(MapMouseOutEvent event) {

        assertEquals(event.getSender(), m);
        assertEquals(latlng, event.getLatLng());

        finishTest();
      }

    });
    RootPanel.get().add(m);
    MapMouseOutEvent e = new MapMouseOutEvent(m, latlng);
    delayTestFinish(ASYNC_DELAY_MSEC);
    m.trigger(e);
  }

  /**
   * Note: testMapMouseOverEvent() can't be implemented as there is no way to
   * create a "mouseover" event by API calls apart from GEvent.trigger().
   */
  public void testMapMouseOverTrigger() {
    final MapWidget m = new MapWidget();
    final LatLng latlng = new LatLng(1.0, 2.0);

    m.addMapMouseOverHandler(new MapMouseOverHandler() {

      public void onMouseOver(MapMouseOverEvent event) {

        assertEquals(event.getSender(), m);
        assertEquals(latlng, event.getLatLng());

        finishTest();
      }

    });
    RootPanel.get().add(m);
    MapMouseOverEvent e = new MapMouseOverEvent(m, latlng);
    delayTestFinish(ASYNC_DELAY_MSEC);
    m.trigger(e);
  }

  public void testMapMoveEndEvent() {
    final LatLng end = new LatLng(37.4569, -122.1569);
    final MapWidget m = new MapWidget();

    /*
     * The MoveEnd event gets called on setCenter(). There is a setCenter() call
     * implicit in. Call now so that first move event won't get in the way of
     * the test.
     */
    RootPanel.get().add(m);

    m.addMapMoveEndHandler(new MapMoveEndHandler() {

      public void onMoveEnd(MapMoveEndEvent event) {
        assertEquals(event.getSender(), m);
        finishTest();
      }

    });
    delayTestFinish(ASYNC_DELAY_MSEC);
    m.setCenter(end);
  }

  // TODO(zundel): come up with a reason why testMapMoveStartEvent() doesn't
  // work

  // public void testMapMoveStartEvent() {
  //
  // final LatLng start = new LatLng(37.4419, -122.1419);
  // final LatLng end = new LatLng(37.4569, -122.1569);
  //
  // final MapWidget m = new MapWidget();
  //
  // /*
  // * The MoveEnd event gets called on setCenter(). There is a setCenter() call
  // * implicit in. Call now so that first move event won't get in the way of
  // * the test.
  // */
  // RootPanel.get().add(m);
  //
  // m.addMapMoveStartHandler(new MapMoveStartHandler() {
  //
  // public void onMoveStart(MapMoveStartEvent event) {
  // finishTest();
  // }
  //
  // });
  // delayTestFinish(ASYNC_DELAY_MSEC);
  // m.setCenter(start);
  // new Timer() {
  // @Override
  // public void run() {
  // m.panTo(end);
  // }
  // }.schedule(250);
  // }

  public void testMapMoveEndTrigger() {
    final MapWidget m = new MapWidget();

    /*
     * The MoveEnd event gets called on setCenter(). There is a setCenter() call
     * implicit in. Call now so that first move event won't get in the way of
     * the test.
     */
    RootPanel.get().add(m);

    m.addMapMoveEndHandler(new MapMoveEndHandler() {

      public void onMoveEnd(MapMoveEndEvent event) {
        assertEquals(event.getSender(), m);
        finishTest();
      }

    });

    delayTestFinish(ASYNC_DELAY_MSEC);

    MapMoveEndEvent e = new MapMoveEndEvent(m);
    m.trigger(e);
  }

  public void testMapMoveEvent() {
    final LatLng start = new LatLng(37.4419, -122.1419);
    final LatLng end = new LatLng(37.4569, -122.1569);

    final MapWidget m = new MapWidget(start, 13);

    /*
     * The MoveEnd event gets called on setCenter(). There is a setCenter() call
     * implicit in. Call now so that first move event won't get in the way of
     * the test.
     */
    RootPanel.get().add(m);

    m.addMapMoveHandler(new MapMoveHandler() {

      public void onMove(MapMoveEvent event) {
        assertEquals(event.getSender(), m);
        finishTest();
      }

    });

    delayTestFinish(ASYNC_DELAY_MSEC);
    MapMoveEvent e = new MapMoveEvent(m);
    m.setCenter(end);
  }

  public void testMapMoveStartTrigger() {
    delayTestFinish(ASYNC_DELAY_MSEC);
    final MapWidget m = new MapWidget();
    m.addMapMoveStartHandler(new MapMoveStartHandler() {

      public void onMoveStart(MapMoveStartEvent event) {
        MapWidget sender = event.getSender();
        assertEquals(sender, m);
        finishTest();
      }

    });
    RootPanel.get().add(m);
    MapMoveStartEvent e = new MapMoveStartEvent(m);
    m.trigger(e);
  }

  public void testMapMoveTrigger() {
    final MapWidget m = new MapWidget();

    RootPanel.get().add(m);

    m.addMapMoveHandler(new MapMoveHandler() {

      public void onMove(MapMoveEvent event) {
        assertEquals(event.getSender(), m);
        finishTest();
      }

    });
    delayTestFinish(ASYNC_DELAY_MSEC);

    MapMoveEvent e = new MapMoveEvent(m);
    m.trigger(e);
  }

  /**
   * This test can use the built-in maps triggering mechanism w/o resorting to
   * using one of the trigger() methods.
   */
  public void testMapRemoveMapTypeEvent() {

    final MapWidget m = new MapWidget();
    m.addMapRemoveMapTypeHandler(new MapRemoveMapTypeHandler() {

      public void onRemoveMapType(MapRemoveMapTypeEvent event) {
        assertEquals(event.getSender(), m);
        assertTrue("maptype doesn't match", event.getType().equals(
            MapType.getNormalMap()));
        finishTest();
      }

    });
    RootPanel.get().add(m);
    delayTestFinish(ASYNC_DELAY_MSEC);
    m.removeMapType(MapType.getNormalMap());
  }

  public void testMapRemoveMapTypeTrigger() {
    final MapWidget m = new MapWidget();
    m.addMapRemoveMapTypeHandler(new MapRemoveMapTypeHandler() {

      public void onRemoveMapType(MapRemoveMapTypeEvent event) {
        assertEquals(event.getSender(), m);
        assertTrue("maptype doesn't match", event.getType().equals(
            MapType.getNormalMap()));
        finishTest();
      }

    });
    MapRemoveMapTypeEvent e = new MapRemoveMapTypeEvent(m,
        MapType.getNormalMap());
    delayTestFinish(ASYNC_DELAY_MSEC);
    m.trigger(e);
  }

  /**
   * This test can use the built-in maps triggering mechanism w/o resorting to
   * using one of the trigger() methods.
   */
  public void testMapRemoveOverlayEvent() {
    final MapWidget m = new MapWidget();
    final Marker marker = new Marker(new LatLng(0.0, 0.0));
    m.addMapRemoveOverlayHandler(new MapRemoveOverlayHandler() {

      public void onRemoveOverlay(MapRemoveOverlayEvent event) {
        assertEquals(event.getSender(), m);
        assertEquals(event.getOverlay(), marker);
        finishTest();
      }

    });
    RootPanel.get().add(m);
    delayTestFinish(ASYNC_DELAY_MSEC);
    m.addOverlay(marker);
    m.removeOverlay(marker);
  }

  public void testMapRemoveOverlayTrigger() {

    final MapWidget m = new MapWidget();
    final Marker marker = new Marker(new LatLng(0.0, 0.0));
    m.addMapRemoveOverlayHandler(new MapRemoveOverlayHandler() {

      public void onRemoveOverlay(MapRemoveOverlayEvent event) {
        assertEquals(event.getSender(), m);
        assertEquals(event.getOverlay(), marker);
        finishTest();
      }

    });
    RootPanel.get().add(m);
    MapRemoveOverlayEvent e = new MapRemoveOverlayEvent(m, marker);
    delayTestFinish(ASYNC_DELAY_MSEC);
    m.trigger(e);
  }

  /**
   * Note: testMapRightClickEvent() can't be implemented as there is no way to
   * create a "singlerightclick" event by API calls apart from GEvent.trigger().
   */
  public void testMapRightClickTrigger() {
    final MapWidget m = new MapWidget();
    m.addMapRightClickHandler(new MapRightClickHandler() {

      public void onRightClick(MapRightClickEvent event) {
        assertEquals(event.getSender(), m);
        Point p = event.getPoint();
        Marker marker = (Marker) event.getOverlay();
        Element elem = event.getElement();
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
    delayTestFinish(ASYNC_DELAY_MSEC);
    m.trigger(e);
  }

  public void testMapZoomEndEvent() {

    final MapWidget m = new MapWidget(new LatLng(37.4419, -122.1419), 13);

    m.addMapZoomEndHandler(new MapZoomEndHandler() {

      public void onZoomEnd(MapZoomEndEvent event) {
        int oldZoom = event.getOldZoomLevel();
        int newZoom = event.getNewZoomLevel();
        assertEquals(event.getSender(), m);
        assertEquals(oldZoom, 13);
        assertEquals(newZoom, 14);
        finishTest();
      }

    });

    RootPanel.get().add(m);

    delayTestFinish(ASYNC_DELAY_MSEC);
    m.setZoomLevel(14);
  }

  public void testMapZoomEndTrigger() {
    final MapWidget m = new MapWidget();
    m.addMapZoomEndHandler(new MapZoomEndHandler() {

      public void onZoomEnd(MapZoomEndEvent event) {
        int oldZoom = event.getOldZoomLevel();
        int newZoom = event.getNewZoomLevel();
        assertEquals(event.getSender(), m);
        assertEquals(oldZoom, 13);
        assertEquals(newZoom, 14);
        finishTest();
      }

    });
    RootPanel.get().add(m);
    MapZoomEndEvent e = new MapZoomEndEvent(m, 13, 14);
    delayTestFinish(ASYNC_DELAY_MSEC);
    m.trigger(e);
  }
}
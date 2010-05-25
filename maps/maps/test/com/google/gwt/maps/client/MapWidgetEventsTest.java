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

import com.google.gwt.dom.client.Element;
import com.google.gwt.maps.client.event.MapAddMapTypeHandler;
import com.google.gwt.maps.client.event.MapAddOverlayHandler;
import com.google.gwt.maps.client.event.MapClearOverlaysHandler;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.event.MapDoubleClickHandler;
import com.google.gwt.maps.client.event.MapDragEndHandler;
import com.google.gwt.maps.client.event.MapDragHandler;
import com.google.gwt.maps.client.event.MapDragStartHandler;
import com.google.gwt.maps.client.event.MapInfoWindowBeforeCloseHandler;
import com.google.gwt.maps.client.event.MapInfoWindowCloseHandler;
import com.google.gwt.maps.client.event.MapInfoWindowOpenHandler;
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
import com.google.gwt.maps.client.event.MapAddMapTypeHandler.MapAddMapTypeEvent;
import com.google.gwt.maps.client.event.MapAddOverlayHandler.MapAddOverlayEvent;
import com.google.gwt.maps.client.event.MapClearOverlaysHandler.MapClearOverlaysEvent;
import com.google.gwt.maps.client.event.MapClickHandler.MapClickEvent;
import com.google.gwt.maps.client.event.MapDoubleClickHandler.MapDoubleClickEvent;
import com.google.gwt.maps.client.event.MapDragEndHandler.MapDragEndEvent;
import com.google.gwt.maps.client.event.MapDragHandler.MapDragEvent;
import com.google.gwt.maps.client.event.MapDragStartHandler.MapDragStartEvent;
import com.google.gwt.maps.client.event.MapInfoWindowBeforeCloseHandler.MapInfoWindowBeforeCloseEvent;
import com.google.gwt.maps.client.event.MapInfoWindowCloseHandler.MapInfoWindowCloseEvent;
import com.google.gwt.maps.client.event.MapInfoWindowOpenHandler.MapInfoWindowOpenEvent;
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
import com.google.gwt.user.client.Timer;
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
public class MapWidgetEventsTest extends MapsTestCase {

  @Override
  public String getModuleName() {
    return "com.google.gwt.maps.GoogleMapsTest";
  }

  public void testInfoWindowBeforeCloseEvent() {
    loadApi(new Runnable() {
      public void run() {

        final MapWidget m = new MapWidget();
        m.addInfoWindowBeforeCloseHandler(new MapInfoWindowBeforeCloseHandler() {
          public void onInfoWindowBeforeClose(
              MapInfoWindowBeforeCloseEvent event) {
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
      }
    }, false);
  }

  public void testInfoWindowBeforeCloseTrigger() {
    loadApi(new Runnable() {
      public void run() {
        final MapWidget m = new MapWidget();
        m.addInfoWindowBeforeCloseHandler(new MapInfoWindowBeforeCloseHandler() {
          public void onInfoWindowBeforeClose(
              MapInfoWindowBeforeCloseEvent event) {
            MapWidget sender = event.getSender();
            assertEquals(sender, m);
            finishTest();
          }
        });
        RootPanel.get().add(m);
        MapInfoWindowBeforeCloseEvent e = new MapInfoWindowBeforeCloseEvent(m);
        m.trigger(e);
      }
    }, false);
  }

  public void testInfoWindowCloseEvent() {
    loadApi(new Runnable() {
      public void run() {
        final MapWidget m = new MapWidget();
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
      }
    }, false);
  }

  public void testInfoWindowCloseTrigger() {
    loadApi(new Runnable() {
      public void run() {
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
        m.trigger(e);
      }
    }, false);
  }

  public void testInfoWindowOpenEvent() {
    loadApi(new Runnable() {
      public void run() {
        final MapWidget m = new MapWidget();
        m.addInfoWindowOpenHandler(new MapInfoWindowOpenHandler() {
          public void onInfoWindowOpen(MapInfoWindowOpenEvent event) {
            MapWidget sender = event.getSender();
            assertEquals(sender, m);
            finishTest();
          }
        });
        RootPanel.get().add(m);
        InfoWindow info = m.getInfoWindow();
        info.open(m.getCenter(), new InfoWindowContent("Hello Maps!"));
      }
    }, false);
  }

  public void testInfoWindowOpenTrigger() {
    loadApi(new Runnable() {
      public void run() {
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
        m.trigger(e);
      }
    }, false);
  }

  public void testMapAddMapTypeEvent() {
    loadApi(new Runnable() {
      public void run() {
        MapWidget m = new MapWidget();
        m.addMapAddMapTypeHandler(new MapAddMapTypeHandler() {
          public void onAddMapType(MapAddMapTypeEvent event) {
            assertTrue("maptype doesn't match", event.getType().equals(
                MapType.getMarsElevationMap()));
            finishTest();
          }
        });
        RootPanel.get().add(m);
        m.addMapType(MapType.getMarsElevationMap());
      }
    }, false);
  }

  public void testMapAddMapTypeTrigger() {
    loadApi(new Runnable() {
      public void run() {
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
        m.trigger(e);
      }
    }, false);
  }

  public void testMapAddOverlayEvent() {
    loadApi(new Runnable() {
      public void run() {
        final MapWidget m = new MapWidget();
        final Marker marker = new Marker(LatLng.newInstance(0.0, 0.0));
        m.addMapAddOverlayHandler(new MapAddOverlayHandler() {
          public void onAddOverlay(MapAddOverlayEvent event) {
            assertEquals(event.getSender(), m);
            assertEquals(event.getOverlay(), marker);
            finishTest();
          }
        });
        RootPanel.get().add(m);
        m.addOverlay(marker);
      }
    }, false);
  }

  public void testMapAddOverlayTrigger() {
    loadApi(new Runnable() {
      public void run() {
        final MapWidget m = new MapWidget();
        final Marker marker = new Marker(LatLng.newInstance(0.0, 0.0));
        m.addMapAddOverlayHandler(new MapAddOverlayHandler() {
          public void onAddOverlay(MapAddOverlayEvent event) {
            assertEquals(event.getSender(), m);
            assertEquals(event.getOverlay(), marker);
            finishTest();
          }
        });
        RootPanel.get().add(m);
        MapAddOverlayEvent e = new MapAddOverlayEvent(m, marker);
        m.trigger(e);
      }
    }, false);
  }

  /**
   * This test can use the built-in maps triggering mechanism w/o resorting to
   * using one of the trigger() methods.
   */
  public void testMapClearOverlayEvent() {
    loadApi(new Runnable() {
      public void run() {
        final MapWidget m = new MapWidget();
        final Marker marker = new Marker(LatLng.newInstance(0.0, 0.0));
        m.addMapClearOverlaysHandler(new MapClearOverlaysHandler() {
          public void onClearOverlays(MapClearOverlaysEvent event) {
            assertEquals(event.getSender(), m);
            finishTest();
          }
        });
        RootPanel.get().add(m);
        m.addOverlay(marker);
        m.clearOverlays();
      }
    }, false);
  }

  public void testMapClearOverlayTrigger() {
    loadApi(new Runnable() {
      public void run() {
        final MapWidget m = new MapWidget();
        final Marker marker = new Marker(LatLng.newInstance(0.0, 0.0));
        m.addOverlay(marker);
        m.addMapClearOverlaysHandler(new MapClearOverlaysHandler() {
          public void onClearOverlays(MapClearOverlaysEvent event) {
            assertEquals(event.getSender(), m);
            finishTest();
          }
        });
        RootPanel.get().add(m);
        MapClearOverlaysEvent e = new MapClearOverlaysEvent(m);
        m.trigger(e);
      }
    }, false);
  }

  /**
   * Note: testMapClickEvent() can't be implemented as there is no way to create
   * a "click" event by API calls apart from GEvent.trigger().
   */
  public void testMapClickTrigger() {
    loadApi(new Runnable() {
      public void run() {
        final MapWidget m = new MapWidget();
        m.addMapClickHandler(new MapClickHandler() {
          public void onClick(MapClickEvent event) {
            Overlay o = event.getOverlay();
            LatLng p = event.getLatLng();
            assertEquals(event.getSender(), m);
            assertNotNull("maker is null", o);
            Marker marker = (Marker) o;
            assertTrue(marker.getLatLng().getLatitude() == 12.34);
            assertTrue(marker.getLatLng().getLongitude() == -22.2);
            assertNotNull("point is null", p);
            assertTrue(p.getLatitude() == 10.1);
            assertTrue(p.getLongitude() == 12.2);
            LatLng overlaylatlng = event.getOverlayLatLng();
            assertNotNull("overlaylatlng is null", overlaylatlng);
            assertEquals("Latitude didn't match for overlaylatlng", 1.0,
                overlaylatlng.getLatitude(), .001);
            assertEquals("Longitude didn't match for overlaylatlng", 2.0,
                overlaylatlng.getLongitude(), .001);
            finishTest();
          }
        });
        RootPanel.get().add(m);
        Marker marker = new Marker(LatLng.newInstance(12.34, -22.2));
        MapClickEvent e = new MapClickEvent(m, marker, LatLng.newInstance(10.1,
            12.2), LatLng.newInstance(1.0, 2.0));
        m.trigger(e);
      }
    }, false);
  }

  /**
   * Note: testMapDoubleClickEvent() can't be implemented as there is no way to
   * create a "dblclick" event by API calls apart from GEvent.trigger().
   */
  public void testMapDoubleClickTrigger() {
    loadApi(new Runnable() {
      public void run() {
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
        MapDoubleClickEvent e = new MapDoubleClickEvent(m, LatLng.newInstance(
            10.1, 12.2));
        m.trigger(e);
      }
    }, false);
  }

  /**
   * Note: testDragEndEvent() can't be implemented as there is no way to create
   * a "dragend" event by API calls apart from GEvent.trigger().
   */
  public void testMapDragEndTrigger() {
    loadApi(new Runnable() {
      public void run() {
        final MapWidget m = new MapWidget();
        RootPanel.get().add(m);

        m.addMapDragEndHandler(new MapDragEndHandler() {

          public void onDragEnd(MapDragEndEvent event) {
            assertEquals(event.getSender(), m);
            finishTest();
          }
        });

        MapDragEndEvent e = new MapDragEndEvent(m);
        m.trigger(e);
      }
    }, false);
  }

  /**
   * Note: testMapDrag() can't be implemented as there is no way to create a
   * "drag" event by API calls apart from GEvent.trigger().
   */
  public void testMapDragTrigger() {
    loadApi(new Runnable() {
      public void run() {
        final MapWidget m = new MapWidget();
        RootPanel.get().add(m);

        m.addMapDragHandler(new MapDragHandler() {

          public void onDrag(MapDragEvent event) {
            assertEquals(event.getSender(), m);
            finishTest();
          }
        });
        MapDragEvent e = new MapDragEvent(m);
        m.trigger(e);
      }
    }, false);
  }

  /**
   * Note: testMapDragStartEvent() can't be implemented as there is no way to
   * create a "dragstart" event by API calls apart from GEvent.trigger().
   */
  public void testMapDragStartTrigger() {
    loadApi(new Runnable() {
      public void run() {

        final MapWidget m = new MapWidget();

        RootPanel.get().add(m);

        m.addMapDragStartHandler(new MapDragStartHandler() {

          public void onDragStart(MapDragStartEvent event) {
            assertEquals(event.getSender(), m);
            finishTest();
          }
        });

        MapDragStartEvent e = new MapDragStartEvent(m);
        m.trigger(e);
      }
    }, false);
  }

  /**
   * Note: testMapMouseMoveEvent() can't be implemented as there is no way to
   * create a "mousemove" event by API calls apart from GEvent.trigger().
   */
  public void testMapMouseMoveTrigger() {
    loadApi(new Runnable() {
      public void run() {
        final MapWidget m = new MapWidget();

        final LatLng latlng = LatLng.newInstance(1.0, 2.0);

        m.addMapMouseMoveHandler(new MapMouseMoveHandler() {

          public void onMouseMove(MapMouseMoveEvent event) {
            assertEquals(event.getSender(), m);
            assertEquals(latlng, event.getLatLng());
            finishTest();
          }

        });
        RootPanel.get().add(m);
        MapMouseMoveEvent e = new MapMouseMoveEvent(m, latlng);
        m.trigger(e);
      }
    }, false);
  }

  /**
   * Note: testMapMouseOutEvent() can't be implemented as there is no way to
   * create a "mouseout" event by API calls apart from GEvent.trigger().
   */
  public void testMapMouseOutTrigger() {
    loadApi(new Runnable() {
      public void run() {
        final MapWidget m = new MapWidget();
        final LatLng latlng = LatLng.newInstance(1.0, 2.0);

        m.addMapMouseOutHandler(new MapMouseOutHandler() {

          public void onMouseOut(MapMouseOutEvent event) {

            assertEquals(event.getSender(), m);
            assertEquals(latlng, event.getLatLng());

            finishTest();
          }

        });
        RootPanel.get().add(m);
        MapMouseOutEvent e = new MapMouseOutEvent(m, latlng);
        m.trigger(e);
      }
    }, false);
  }

  /**
   * Note: testMapMouseOverEvent() can't be implemented as there is no way to
   * create a "mouseover" event by API calls apart from GEvent.trigger().
   */
  public void testMapMouseOverTrigger() {
    loadApi(new Runnable() {
      public void run() {
        final MapWidget m = new MapWidget();
        final LatLng latlng = LatLng.newInstance(1.0, 2.0);
        m.addMapMouseOverHandler(new MapMouseOverHandler() {
          public void onMouseOver(MapMouseOverEvent event) {
            assertEquals(event.getSender(), m);
            assertEquals(latlng, event.getLatLng());
            finishTest();
          }
        });
        RootPanel.get().add(m);
        MapMouseOverEvent e = new MapMouseOverEvent(m, latlng);
        m.trigger(e);
      }
    }, false);
  }

  public void testMapMoveEndEvent() {
    loadApi(new Runnable() {
      public void run() {
        final LatLng end = LatLng.newInstance(37.4569, -122.1569);
        final MapWidget m = new MapWidget();

        /*
         * The Move event gets called on setCenter(). There is a setCenter()
         * call implicit in the MapWidget() constructor. Add to the map now so
         * that first move event won't get in the way of the test.
         */
        RootPanel.get().add(m);

        m.addMapMoveEndHandler(new MapMoveEndHandler() {

          public void onMoveEnd(MapMoveEndEvent event) {
            assertEquals(event.getSender(), m);
            finishTest();
          }

        });
        m.setCenter(end);
      }
    }, false);
  }

  /**
   * Live test of the MapMoveStart event. On Safari2/Mac this test times out.
   */
  public void testMapMoveStartEvent() {
    loadApi(new Runnable() {
      public void run() {

        final LatLng start = LatLng.newInstance(37.4419, -122.1419);
        final LatLng end = LatLng.newInstance(37.45, -122.15);

        final MapWidget m = new MapWidget(start, 13);

        // Apparently, setting the size is important for the move event.
        m.setSize("300px", "300px");

        /*
         * The MoveStart event gets called on setCenter(). There is a
         * setCenter() call implicit in the MapWidget() constructor. Add to the
         * map now so that first move event won't get in the way of the test.
         */
        RootPanel.get().add(m);
        m.addMapMoveStartHandler(new MapMoveStartHandler() {
          public void onMoveStart(MapMoveStartEvent event) {
            finishTest();
          }
        });

        // The code that handles resizing after the map is attached to the DOM
        // interferes with this test if the pan is specified before the map is
        // attached to the DOM.
        new Timer() {
          public void run() {
            m.panTo(end);
          }
        }.schedule(1000);

      }
    }, false);
  }

  public void testMapMoveEndTrigger() {
    loadApi(new Runnable() {
      public void run() {
        final MapWidget m = new MapWidget();

        /*
         * The MoveEnd event gets called on setCenter(). There is a setCenter()
         * call implicit in the MapWidget constructor. Add to the map now so
         * that first move event won't get in the way of the test.
         */
        RootPanel.get().add(m);

        m.addMapMoveEndHandler(new MapMoveEndHandler() {

          public void onMoveEnd(MapMoveEndEvent event) {
            assertEquals(event.getSender(), m);
            finishTest();
          }

        });
        MapMoveEndEvent e = new MapMoveEndEvent(m);
        m.trigger(e);
      }
    }, false);
  }

  public void testMapMoveEvent() {
    loadApi(new Runnable() {
      public void run() {
        final LatLng start = LatLng.newInstance(37.4419, -122.1419);
        final LatLng end = LatLng.newInstance(37.4569, -122.1569);

        final MapWidget m = new MapWidget(start, 13);

        /*
         * The Move event gets called on setCenter(). There is a setCenter()
         * call implicit in the MapWidget() constructor. Add to the map now so
         * that first move event won't get in the way of the test.
         */
        RootPanel.get().add(m);

        m.addMapMoveHandler(new MapMoveHandler() {

          public void onMove(MapMoveEvent event) {
            assertEquals(event.getSender(), m);
            finishTest();
          }

        });

        m.setCenter(end);
      }
    }, false);
  }

  public void testMapMoveStartTrigger() {
    loadApi(new Runnable() {
      public void run() {

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
    }, false);
  }

  public void testMapMoveTrigger() {
    loadApi(new Runnable() {
      public void run() {
        final MapWidget m = new MapWidget();
        RootPanel.get().add(m);
        m.addMapMoveHandler(new MapMoveHandler() {
          public void onMove(MapMoveEvent event) {
            assertEquals(event.getSender(), m);
            finishTest();
          }
        });
        MapMoveEvent e = new MapMoveEvent(m);
        m.trigger(e);
      }
    }, false);
  }

  /**
   * This test can use the built-in maps triggering mechanism w/o resorting to
   * using one of the trigger() methods.
   */
  public void testMapRemoveMapTypeEvent() {
    loadApi(new Runnable() {
      public void run() {
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
        m.removeMapType(MapType.getNormalMap());
      }
    }, false);
  }

  public void testMapRemoveMapTypeTrigger() {
    loadApi(new Runnable() {
      public void run() {
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
        m.trigger(e);
      }
    }, false);
  }

  /**
   * This test can use the built-in maps triggering mechanism w/o resorting to
   * using one of the trigger() methods.
   */
  public void testMapRemoveOverlayEvent() {
    loadApi(new Runnable() {
      public void run() {
        final MapWidget m = new MapWidget();
        final Marker marker = new Marker(LatLng.newInstance(0.0, 0.0));
        m.addMapRemoveOverlayHandler(new MapRemoveOverlayHandler() {

          public void onRemoveOverlay(MapRemoveOverlayEvent event) {
            assertEquals(event.getSender(), m);
            assertEquals(event.getOverlay(), marker);
            finishTest();
          }

        });
        RootPanel.get().add(m);
        m.addOverlay(marker);
        m.removeOverlay(marker);
      }
    }, false);
  }

  public void testMapRemoveOverlayTrigger() {
    loadApi(new Runnable() {
      public void run() {
        final MapWidget m = new MapWidget();
        final Marker marker = new Marker(LatLng.newInstance(0.0, 0.0));
        m.addMapRemoveOverlayHandler(new MapRemoveOverlayHandler() {

          public void onRemoveOverlay(MapRemoveOverlayEvent event) {
            assertEquals(event.getSender(), m);
            assertEquals(event.getOverlay(), marker);
            finishTest();
          }

        });
        RootPanel.get().add(m);
        MapRemoveOverlayEvent e = new MapRemoveOverlayEvent(m, marker);
        m.trigger(e);
      }
    }, false);
  }

  /**
   * Note: testMapRightClickEvent() can't be implemented as there is no way to
   * create a "singlerightclick" event by API calls apart from GEvent.trigger().
   */
  public void testMapRightClickTrigger() {
    loadApi(new Runnable() {
      public void run() {
        final MapWidget m = new MapWidget();
        m.addMapRightClickHandler(new MapRightClickHandler() {

          @SuppressWarnings("deprecation")
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
        Marker marker = new Marker(LatLng.newInstance(12.34, -22.2));
        MapRightClickEvent e = new MapRightClickEvent(m, Point.newInstance(101,
            222), m.getElement(), marker);
        m.trigger(e);
      }
    }, false);
  }

  public void testMapZoomEndEvent() {
    loadApi(new Runnable() {
      public void run() {
        final MapWidget m = new MapWidget(
            LatLng.newInstance(37.4419, -122.1419), 13);
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
        m.setZoomLevel(14);
      }
    }, false);
  }

  public void testMapZoomEndTrigger() {
    loadApi(new Runnable() {
      public void run() {
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
        m.trigger(e);
      }
    }, false);
  }
}
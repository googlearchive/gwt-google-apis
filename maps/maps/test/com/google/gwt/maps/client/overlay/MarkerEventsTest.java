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
import com.google.gwt.maps.client.InfoWindow;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.event.MapInfoWindowOpenHandler;
import com.google.gwt.maps.client.event.MarkerClickHandler;
import com.google.gwt.maps.client.event.MarkerDoubleClickHandler;
import com.google.gwt.maps.client.event.MarkerDragEndHandler;
import com.google.gwt.maps.client.event.MarkerDragHandler;
import com.google.gwt.maps.client.event.MarkerDragStartHandler;
import com.google.gwt.maps.client.event.MarkerInfoWindowBeforeCloseHandler;
import com.google.gwt.maps.client.event.MarkerInfoWindowCloseHandler;
import com.google.gwt.maps.client.event.MarkerInfoWindowOpenHandler;
import com.google.gwt.maps.client.event.MarkerMouseDownHandler;
import com.google.gwt.maps.client.event.MarkerMouseOutHandler;
import com.google.gwt.maps.client.event.MarkerMouseOverHandler;
import com.google.gwt.maps.client.event.MarkerMouseUpHandler;
import com.google.gwt.maps.client.event.MarkerRemoveHandler;
import com.google.gwt.maps.client.event.MarkerVisibilityChangedHandler;
import com.google.gwt.maps.client.event.MarkerClickHandler.MarkerClickEvent;
import com.google.gwt.maps.client.event.MarkerDoubleClickHandler.MarkerDoubleClickEvent;
import com.google.gwt.maps.client.event.MarkerDragEndHandler.MarkerDragEndEvent;
import com.google.gwt.maps.client.event.MarkerDragHandler.MarkerDragEvent;
import com.google.gwt.maps.client.event.MarkerDragStartHandler.MarkerDragStartEvent;
import com.google.gwt.maps.client.event.MarkerInfoWindowBeforeCloseHandler.MarkerInfoWindowBeforeCloseEvent;
import com.google.gwt.maps.client.event.MarkerInfoWindowCloseHandler.MarkerInfoWindowCloseEvent;
import com.google.gwt.maps.client.event.MarkerInfoWindowOpenHandler.MarkerInfoWindowOpenEvent;
import com.google.gwt.maps.client.event.MarkerMouseDownHandler.MarkerMouseDownEvent;
import com.google.gwt.maps.client.event.MarkerMouseOutHandler.MarkerMouseOutEvent;
import com.google.gwt.maps.client.event.MarkerMouseOverHandler.MarkerMouseOverEvent;
import com.google.gwt.maps.client.event.MarkerMouseUpHandler.MarkerMouseUpEvent;
import com.google.gwt.maps.client.event.MarkerRemoveHandler.MarkerRemoveEvent;
import com.google.gwt.maps.client.event.MarkerVisibilityChangedHandler.MarkerVisibilityChangedEvent;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
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
public class MarkerEventsTest extends GWTTestCase {
  // length of time to wait for asynchronous test to complete.
  static final int ASYNC_DELAY_MSEC = 5000;

  @Override
  public String getModuleName() {
    return "com.google.gwt.maps.GoogleMapsTest";
  }

  public void testMarkerClickTrigger() {
    LatLng atlanta = new LatLng(33.7814790, -84.3880580);
    final MapWidget map = new MapWidget(atlanta, 13);
    map.setSize("300px", "300px");
    RootPanel.get().add(map);

    final Marker marker = new Marker(atlanta);
    map.addOverlay(marker);

    marker.addMarkerClickHandler(new MarkerClickHandler() {

      public void onClick(MarkerClickEvent event) {
        assertEquals(event.getSender(), marker);
        finishTest();
      }

    });
    MarkerClickEvent e = new MarkerClickEvent(marker);
    this.delayTestFinish(ASYNC_DELAY_MSEC);
    marker.trigger(e);
  }

  public void testMarkerDoubleClickTrigger() {
    LatLng atlanta = new LatLng(33.7814790, -84.3880580);
    final MapWidget map = new MapWidget(atlanta, 13);
    map.setSize("300px", "300px");
    RootPanel.get().add(map);

    final Marker marker = new Marker(atlanta);
    map.addOverlay(marker);
    marker.addMarkerDoubleClickHandler(new MarkerDoubleClickHandler() {
      public void onDoubleClick(MarkerDoubleClickEvent event) {
        assertEquals(event.getSender(), marker);
        finishTest();
      }
    });
    MarkerDoubleClickEvent e = new MarkerDoubleClickEvent(marker);
    delayTestFinish(ASYNC_DELAY_MSEC);
    marker.trigger(e);
  }

  public void testMarkerDragEndTrigger() {
    LatLng atlanta = new LatLng(33.7814790, -84.3880580);
    final MapWidget map = new MapWidget(atlanta, 13);
    map.setSize("300px", "300px");
    RootPanel.get().add(map);

    final Marker marker = new Marker(atlanta);
    map.addOverlay(marker);
    marker.addMarkerDragEndHandler(new MarkerDragEndHandler() {
      public void onDragEnd(MarkerDragEndEvent event) {
        assertEquals(event.getSender(), marker);
        finishTest();
      }
    });
    MarkerDragEndEvent e = new MarkerDragEndEvent(marker);
    delayTestFinish(ASYNC_DELAY_MSEC);
    marker.trigger(e);
  }

  public void testMarkerDragStartTrigger() {
    LatLng atlanta = new LatLng(33.7814790, -84.3880580);
    final MapWidget map = new MapWidget(atlanta, 13);
    map.setSize("300px", "300px");
    RootPanel.get().add(map);

    final Marker marker = new Marker(atlanta);
    map.addOverlay(marker);
    marker.addMarkerDragStartHandler(new MarkerDragStartHandler() {
      public void onDragStart(MarkerDragStartEvent event) {
        assertEquals(event.getSender(), marker);
        finishTest();
      }
    });
    MarkerDragStartEvent e = new MarkerDragStartEvent(marker);
    delayTestFinish(ASYNC_DELAY_MSEC);
    marker.trigger(e);
  }

  public void testMarkerDragTrigger() {
    LatLng atlanta = new LatLng(33.7814790, -84.3880580);
    final MapWidget map = new MapWidget(atlanta, 13);
    map.setSize("300px", "300px");
    RootPanel.get().add(map);

    final Marker marker = new Marker(atlanta);
    map.addOverlay(marker);
    marker.addMarkerDragHandler(new MarkerDragHandler() {
      public void onDrag(MarkerDragEvent event) {
        assertEquals(event.getSender(), marker);
        finishTest();
      }
    });
    MarkerDragEvent e = new MarkerDragEvent(marker);
    delayTestFinish(ASYNC_DELAY_MSEC);
    marker.trigger(e);
  }
  
  public void testMarkerInfoWindowBeforeCloseEvent() {
    LatLng atlanta = new LatLng(33.7814790, -84.3880580);
    final MapWidget map = new MapWidget(atlanta, 13);
    map.setSize("300px", "300px");
    RootPanel.get().add(map);

    final Marker marker = new Marker(atlanta);
    
    marker.addMarkerInfoWindowBeforeCloseHandler(new MarkerInfoWindowBeforeCloseHandler() {
      public void onInfoWindowBeforeClose(MarkerInfoWindowBeforeCloseEvent event) {
        assertEquals(event.getSender(), marker);
        finishTest();
      }
    });
    final InfoWindow info = map.getInfoWindow();
    map.addInfoWindowOpenHandler(new MapInfoWindowOpenHandler() {

      public void onInfoWindowOpen(MapInfoWindowOpenEvent event) {
        DeferredCommand.addCommand(new Command() {
          public void execute() {
            info.close();            
          }
        });
      }
    });
    
    delayTestFinish(ASYNC_DELAY_MSEC);
    map.addOverlay(marker);
    info.open(marker, new InfoWindowContent("Hello World!"));
  }
  
  public void testMarkerInfoWindowBeforeCloseTrigger() {
    LatLng atlanta = new LatLng(33.7814790, -84.3880580);
    final MapWidget map = new MapWidget(atlanta, 13);
    map.setSize("300px", "300px");
    RootPanel.get().add(map);

    final Marker marker = new Marker(atlanta);
    map.addOverlay(marker);
    marker.addMarkerInfoWindowBeforeCloseHandler(new MarkerInfoWindowBeforeCloseHandler() {
      public void onInfoWindowBeforeClose(MarkerInfoWindowBeforeCloseEvent event) {
        assertEquals(event.getSender(), marker);
        finishTest();
      }
    });
    MarkerInfoWindowBeforeCloseEvent e = new MarkerInfoWindowBeforeCloseEvent(
        marker);
    delayTestFinish(ASYNC_DELAY_MSEC);
    marker.trigger(e);
  }
  
  public void testMarkerInfoWindowCloseEvent() {
    LatLng atlanta = new LatLng(33.7814790, -84.3880580);
    final MapWidget map = new MapWidget(atlanta, 13);
    map.setSize("300px", "300px");
    RootPanel.get().add(map);

    final Marker marker = new Marker(atlanta);
    map.addOverlay(marker);
    marker.addMarkerInfoWindowCloseHandler(new MarkerInfoWindowCloseHandler() {
      public void onInfoWindowClose(MarkerInfoWindowCloseEvent event) {
        assertEquals(event.getSender(), marker);
        finishTest();
      }
    });
    final InfoWindow info = map.getInfoWindow();
    map.addInfoWindowOpenHandler(new MapInfoWindowOpenHandler() {

      public void onInfoWindowOpen(MapInfoWindowOpenEvent event) {                
        DeferredCommand.addCommand(new Command() {
          public void execute() {
            info.close();            
          }
        });
      }
    });
    delayTestFinish(ASYNC_DELAY_MSEC);
    info.open(marker, new InfoWindowContent("Hello World!"));
  }
  
  public void testMarkerInfoWindowCloseTrigger() {
    LatLng atlanta = new LatLng(33.7814790, -84.3880580);
    final MapWidget map = new MapWidget(atlanta, 13);
    map.setSize("300px", "300px");
    RootPanel.get().add(map);

    final Marker marker = new Marker(atlanta);
    map.addOverlay(marker);
    marker.addMarkerInfoWindowCloseHandler(new MarkerInfoWindowCloseHandler() {
      public void onInfoWindowClose(MarkerInfoWindowCloseEvent event) {
        assertEquals(event.getSender(), marker);
        finishTest();
      }
    });
    MarkerInfoWindowCloseEvent e = new MarkerInfoWindowCloseEvent(marker);
    delayTestFinish(ASYNC_DELAY_MSEC);
    marker.trigger(e);
  }
  
  public void testMarkerInfoWindowOpenEvent() {
    LatLng atlanta = new LatLng(33.7814790, -84.3880580);
    final MapWidget map = new MapWidget(atlanta, 13);
    map.setSize("300px", "300px");

    RootPanel.get().add(map);

    final Marker marker = new Marker(atlanta);
    map.addOverlay(marker);
    final InfoWindow info = map.getInfoWindow();
    marker.addMarkerInfoWindowOpenHandler(new MarkerInfoWindowOpenHandler() {

      public void onInfoWindowOpen(MarkerInfoWindowOpenEvent event) {
        assertEquals(event.getSender(), marker);
        finishTest();
      }
      
    });
    
    delayTestFinish(ASYNC_DELAY_MSEC);
    info.open(marker, new InfoWindowContent("Hello World!"));
  }

  public void testMarkerInfoWindowOpenTrigger() {
    LatLng atlanta = new LatLng(33.7814790, -84.3880580);
    final MapWidget map = new MapWidget(atlanta, 13);
    map.setSize("300px", "300px");
    RootPanel.get().add(map);

    final Marker marker = new Marker(atlanta);
    map.addOverlay(marker);
    marker.addMarkerInfoWindowOpenHandler(new MarkerInfoWindowOpenHandler() {
      public void onInfoWindowOpen(MarkerInfoWindowOpenEvent event) {
        assertEquals(event.getSender(), marker);
        finishTest();
      }
    });
    MarkerInfoWindowOpenEvent e = new MarkerInfoWindowOpenEvent(marker);
    delayTestFinish(ASYNC_DELAY_MSEC);
    marker.trigger(e);
  }

  public void testMarkerMouseDownTrigger() {
    LatLng atlanta = new LatLng(33.7814790, -84.3880580);
    final MapWidget map = new MapWidget(atlanta, 13);
    map.setSize("300px", "300px");
    RootPanel.get().add(map);

    final Marker marker = new Marker(atlanta);
    map.addOverlay(marker);
    marker.addMarkerMouseDownHandler(new MarkerMouseDownHandler() {
      public void onMouseDown(MarkerMouseDownEvent event) {
        assertEquals(event.getSender(), marker);
        finishTest();
      }
    });
    MarkerMouseDownEvent e = new MarkerMouseDownEvent(marker);
    delayTestFinish(ASYNC_DELAY_MSEC);
    marker.trigger(e);
  }

  public void testMarkerMouseOutTrigger() {
    LatLng atlanta = new LatLng(33.7814790, -84.3880580);
    final MapWidget map = new MapWidget(atlanta, 13);
    map.setSize("300px", "300px");
    RootPanel.get().add(map);

    final Marker marker = new Marker(atlanta);
    map.addOverlay(marker);
    marker.addMarkerMouseOutHandler(new MarkerMouseOutHandler() {
      public void onMouseOut(MarkerMouseOutEvent event) {
        assertEquals(event.getSender(), marker);
        finishTest();
      }
    });
    MarkerMouseOutEvent e = new MarkerMouseOutEvent(marker);
    delayTestFinish(ASYNC_DELAY_MSEC);
    marker.trigger(e);
  }

  public void testMarkerMouseOverTrigger() {
    LatLng atlanta = new LatLng(33.7814790, -84.3880580);
    final MapWidget map = new MapWidget(atlanta, 13);
    map.setSize("300px", "300px");
    RootPanel.get().add(map);

    final Marker marker = new Marker(atlanta);
    map.addOverlay(marker);
    marker.addMarkerMouseOverHandler(new MarkerMouseOverHandler() {
      public void onMouseOver(MarkerMouseOverEvent event) {
        assertEquals(event.getSender(), marker);
        finishTest();
      }
    });
    MarkerMouseOverEvent e = new MarkerMouseOverEvent(marker);
    delayTestFinish(ASYNC_DELAY_MSEC);
    marker.trigger(e);
  }

  public void testMarkerMouseUpTrigger() {
    LatLng atlanta = new LatLng(33.7814790, -84.3880580);
    final MapWidget map = new MapWidget(atlanta, 13);
    map.setSize("300px", "300px");
    RootPanel.get().add(map);

    final Marker marker = new Marker(atlanta);
    map.addOverlay(marker);
    marker.addMarkerMouseUpHandler(new MarkerMouseUpHandler() {
      public void onMouseUp(MarkerMouseUpEvent event) {
        assertEquals(event.getSender(), marker);
        finishTest();
      }
    });
    MarkerMouseUpEvent e = new MarkerMouseUpEvent(marker);
    delayTestFinish(ASYNC_DELAY_MSEC);
    marker.trigger(e);
  }

  public void testMarkerRemoveEvent() {
    LatLng atlanta = new LatLng(33.7814790, -84.3880580);
    final MapWidget map = new MapWidget(atlanta, 13);
    map.setSize("300px", "300px");
    RootPanel.get().add(map);

    final Marker marker = new Marker(atlanta);
    map.addOverlay(marker);
    marker.addMarkerRemoveHandler(new MarkerRemoveHandler() {
      public void onRemove(MarkerRemoveEvent event) {
        assertEquals(event.getSender(), marker);
        finishTest();
      }
    });
    delayTestFinish(ASYNC_DELAY_MSEC);
    map.clearOverlays();
  }
  
  public void testMarkerRemoveTrigger() {
    LatLng atlanta = new LatLng(33.7814790, -84.3880580);
    final MapWidget map = new MapWidget(atlanta, 13);
    map.setSize("300px", "300px");
    RootPanel.get().add(map);

    final Marker marker = new Marker(atlanta);
    map.addOverlay(marker);
    marker.addMarkerRemoveHandler(new MarkerRemoveHandler() {
      public void onRemove(MarkerRemoveEvent event) {
        assertEquals(event.getSender(), marker);
        finishTest();
      }
    });
    MarkerRemoveEvent e = new MarkerRemoveEvent(marker);
    delayTestFinish(ASYNC_DELAY_MSEC);
    marker.trigger(e);
  }

  public void testMarkerVisibilityChangedEvent() {
    LatLng atlanta = new LatLng(33.7814790, -84.3880580);
    final MapWidget map = new MapWidget(atlanta, 13);
    map.setSize("300px", "300px");
    RootPanel.get().add(map);

    final Marker marker = new Marker(atlanta);
    map.addOverlay(marker);
    marker.addMarkerVisibilityChangedHandler(new MarkerVisibilityChangedHandler() {
      public void onVisibilityChanged(MarkerVisibilityChangedEvent event) {
        assertEquals(event.getSender(), marker);
        assertFalse(event.isVisible());
        finishTest();
      }
    });
    
    delayTestFinish(ASYNC_DELAY_MSEC);
    marker.setVisible(false);
  }
  
  public void testMarkerVisibilityChangedTrigger() {
    LatLng atlanta = new LatLng(33.7814790, -84.3880580);
    final MapWidget map = new MapWidget(atlanta, 13);
    map.setSize("300px", "300px");
    RootPanel.get().add(map);

    final Marker marker = new Marker(atlanta);
    map.addOverlay(marker);
    marker.addMarkerVisibilityChangedHandler(new MarkerVisibilityChangedHandler() {
      public void onVisibilityChanged(MarkerVisibilityChangedEvent event) {
        assertEquals(event.getSender(), marker);
        assertTrue(event.isVisible());
        finishTest();
      }
    });
    MarkerVisibilityChangedEvent e = new MarkerVisibilityChangedEvent(marker,
        true);
    delayTestFinish(ASYNC_DELAY_MSEC);
    marker.trigger(e);
  }
}
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
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.event.MapDoubleClickHandler;
import com.google.gwt.maps.client.event.MapRightClickHandler;
import com.google.gwt.maps.client.event.MapClickHandler.MapClickEvent;
import com.google.gwt.maps.client.event.MapDoubleClickHandler.MapDoubleClickEvent;
import com.google.gwt.maps.client.event.MapRightClickHandler.MapRightClickEvent;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.Overlay;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Tests for the Maps events.
 */
public class MapWidgetEventsTest extends GWTTestCase {

  @Override
  public String getModuleName() {
    return "com.google.gwt.maps.GoogleMapsTest";
  }

  public void testMapClickEvent() {
    MapWidget m = new MapWidget();
    m.addMapClickHandler(new MapClickHandler() {

      public void onClick(MapClickEvent e) {
        Overlay o = e.getOverlay();
        LatLng p = e.getPoint();
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

  public void testMapDoubleClickEvent() {
    MapWidget m = new MapWidget();
    m.addMapDoubleClickHandler(new MapDoubleClickHandler() {

      public void onDoubleClick(MapDoubleClickEvent e) {
        LatLng p = e.getPoint();
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
  
  public void testMapRightClickEvent() {
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
    MapRightClickEvent e = new MapRightClickEvent(m, new Point(101, 222), m.getElement(), marker);
    delayTestFinish(5000);
    m.trigger(e);
  }
  
}

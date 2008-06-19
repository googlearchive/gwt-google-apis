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
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Tests for the MapWidget and related classes.
 */
public class MapWidgetTest extends GWTTestCase {

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

  public void testIsBrowserCompatible() {
    assertTrue("The MAPS api is not compatible with this browser.", Maps.isBrowserCompatible());
  }
  
  public void testIsLoaded() {
    assertTrue("The MAPS api is not properly loaded.", Maps.isLoaded());
  }

  public void testMapWidgetCursors() {
    MapWidget m = new MapWidget(new LatLng(0, 80), 4, "wait", "help");
    RootPanel.get().add(m);
    assertEquals("Center didn't match.", m.getCenter(), new LatLng(0,80));
    assertEquals("Zoom level didn't match.", m.getZoomLevel(), 4);
  }

  public void testMapWidgetDefault() {
    MapWidget m = new MapWidget();
    RootPanel.get().add(m);
    assertEquals("Center didn't match.", m.getCenter(), new LatLng(0,0));
    assertEquals("Zoom level didn't match.", m.getZoomLevel(), 1);    
  }

  public void testMapWidgetLatLngZoom() {
    MapWidget m = new MapWidget(new LatLng(45, 45), 8);
    RootPanel.get().add(m);
    assertEquals("Center didn't match.", m.getCenter(), new LatLng(45,45));
    assertEquals("Zoom level didn't match.", m.getZoomLevel(), 8);    
  }
}

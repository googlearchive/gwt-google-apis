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
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Tests for the MapWidget and related classes.
 */
public class MapWidgetTest extends GWTTestCase {
  // length of time to wait for asynchronous test to complete.
  static final int ASYNC_DELAY_MSEC = 5000;

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

  public void testGetVersion() {
    String version = Maps.getVersion();
    /*
     * This will print on the console in hosted mode tests only and be ignored
     * for web mode tests. It is used for investigating and generating bug
     * reports when regression tests fail.
     */
    System.out.println("Maps API version: " + version);
    assertNotNull("Maps version", version);
  }

  public void testIsBrowserCompatible() {
    assertTrue("The MAPS api is not compatible with this browser.",
        Maps.isBrowserCompatible());
  }

  public void testIsLoaded() {
    assertTrue("The MAPS api is not properly loaded.", Maps.isLoaded());
  }

  public void testIsRTL() {
    assertFalse("Is RTL", Maps.isRTL());
  }

  public void testKeyboardHandler() {
    LatLng center = LatLng.newInstance(0, 0);
    final MapWidget map = new MapWidget(center, 1);
    map.setSize("300px", "300px");
    map.installKeyboardHandler();
    RootPanel.get().add(map);
  }

  public void testlog() {
    Maps.logWrite("foo");
    Maps.logWrite("red foo", "#f00");
    Maps.logWriteUrl("http://www.google.com/");
    Maps.logWriteHtml("<b><i>HTML</i> Content</b>");
  }

  public void testMapWidgetCloseInfoWindow() {
    LatLng center = LatLng.newInstance(0, 0);
    final MapWidget map = new MapWidget(center, 1);
    map.setSize("300px", "300px");
    RootPanel.get().add(map);
    InfoWindowContent content = new InfoWindowContent("<i>Hello World!</i>");
    InfoWindow info = map.getInfoWindow();
    info.open(center, content);
    DeferredCommand.addCommand(new Command() {

      public void execute() {
        map.closeInfoWindow();
        finishTest();
      }

    });
    delayTestFinish(ASYNC_DELAY_MSEC);
  }

  public void testMapWidgetCursors() {
    MapWidget m = new MapWidget(LatLng.newInstance(0, 80), 4, "wait", "help");
    RootPanel.get().add(m);
    assertTrue("Center didn't match.", m.getCenter().isEquals(
        LatLng.newInstance(0, 80)));
    assertEquals("Zoom level didn't match.", m.getZoomLevel(), 4);
    RootPanel.get().add(m);
  }

  public void testMapWidgetDefault() {
    MapWidget m = new MapWidget();
    RootPanel.get().add(m);
    assertTrue("Center didn't match.", m.getCenter().isEquals(
        LatLng.newInstance(0, 0)));
    assertEquals("Zoom level didn't match.", m.getZoomLevel(), 1);
  }

  public void testMapWidgetLatLngConvertPixel() {
    MapWidget m = new MapWidget(LatLng.newInstance(0, 0), 1);
    m.setSize("500px", "500px");
    RootPanel.get().add(m);
    Point result;
    result = m.convertLatLngToContainerPixel(LatLng.newInstance(45, 45));
    assertNotNull("convertLatLngToContainerPixel()", result);
    result = m.convertLatLngToDivPixel(LatLng.newInstance(45, 45));
    assertNotNull("convertLatLngToDivPixel()", result);

    result = m.convertLatLngToContainerPixel(LatLng.newInstance(0, 0));
    assertNotNull("convertLatLngToContainerPixel()", result);
    assertTrue("convertLatLngToContainerPixel().getX == 250",
        result.getX() == 250);
    assertTrue("convertLatLngToContainerPixel().getY == 250",
        result.getY() == 250);
    result = m.convertLatLngToDivPixel(LatLng.newInstance(0, 0));
    assertNotNull("convertLatLngToDivPixel()", result);
    assertTrue("convertLatLngToContainerPixel().getX == 250",
        result.getX() == 250);
    assertTrue("convertLatLngToContainerPixel().getY == 250",
        result.getY() == 250);

    LatLng latLng;
    latLng = m.convertContainerPixelToLatLng(Point.newInstance(100, 100));
    assertNotNull("convertContainerPixelToLatLng()", result);
    latLng = m.convertDivPixelToLatLng(Point.newInstance(100, 100));
    assertNotNull("convertDivPixelToLatLng()", result);

    latLng = m.convertContainerPixelToLatLng(Point.newInstance(250, 250));
    assertNotNull("convertContainerPixelToLatLng()", result);
    assertTrue(latLng.isEquals(LatLng.newInstance(0, 0)));
    latLng = m.convertDivPixelToLatLng(Point.newInstance(250, 250));
    assertNotNull("convertDivPixelToLatLng()", result);
    assertTrue(latLng.isEquals(LatLng.newInstance(0, 0)));
  }

  public void testMapWidgetLatLngZoom() {
    MapWidget m = new MapWidget(LatLng.newInstance(45, 45), 8);
    RootPanel.get().add(m);
    assertTrue("Center didn't match.", m.getCenter().isEquals(
        LatLng.newInstance(45, 45)));
    assertEquals("Zoom level didn't match.", m.getZoomLevel(), 8);
  }
}

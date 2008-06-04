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
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.RootPanel;

import java.util.ArrayList;
import java.util.List;

/**
 * Tests for the MapWidget and related classes.
 */
public class MapWidgetTest extends GWTTestCase {

  private static native String getNodeName(Element elem) /*-{
     return (elem.nodeName || "").toLowerCase();
  }-*/;

  @Override
  public String getModuleName() {
    return "com.google.gwt.maps.GoogleMapsTest";
  }

  /**
   * Removes all elements in the body, except scripts and iframes.
   */
  public void gwtSetUp() {
    Element bodyElem = RootPanel.getBodyElement();

    List<Element> toRemove = new ArrayList<Element>();
    for (int i = 0, n = DOM.getChildCount(bodyElem); i < n; ++i) {
      Element elem = DOM.getChild(bodyElem, i);
      String nodeName = getNodeName(elem);
      if (!"script".equals(nodeName) && !"iframe".equals(nodeName)) {
        toRemove.add(elem);
      }
    }

    for (int i = 0, n = toRemove.size(); i < n; ++i) {
      DOM.removeChild(bodyElem, toRemove.get(i));
    }
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

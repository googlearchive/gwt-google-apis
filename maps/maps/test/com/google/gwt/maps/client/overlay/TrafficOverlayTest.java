/*
 * Copyright 2010 Google Inc.
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
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.TestUtilities;
import com.google.gwt.maps.client.event.TrafficOverlayChangedHandler;
import com.google.gwt.maps.client.event.TrafficOverlayChangedHandler.TrafficOverlayChangedEvent;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.ui.RootPanel;

public class TrafficOverlayTest extends GWTTestCase {
  // length of time to wait for asynchronous test to complete.
  static final int ASYNC_DELAY_MSEC = 5000;

  @Override
  public String getModuleName() {
    return "com.google.gwt.maps.GoogleMapsTest";
  }

  /**
   * Runs before each test method.
   */
  @Override
  public void gwtSetUp() {
    TestUtilities.cleanDom();
  }

  /**
   * Test the simple TrafficOverlay constructor.
   */
  public void testTrafficOverlay() {
    MapWidget map = new MapWidget(LatLng.newInstance(0, 0), 3);
    map.setSize("500px", "400px");
    TrafficOverlay traffic = new TrafficOverlay();
    map.addOverlay(traffic);
    RootPanel.get().add(map);
  }

  /**
   * Test the TrafficOverlay constructor with options.
   */
  public void testTrafficOverlayOptions() {
    MapWidget map = new MapWidget(LatLng.newInstance(0, 0), 3);
    map.setSize("500px", "400px");
    TrafficOverlay traffic = new TrafficOverlay(
        TrafficOverlayOptions.newInstance(true));
    map.addOverlay(traffic);
    RootPanel.get().add(map);
  }

  /**
   * Test the TrafficOverlay constructor with options.
   */
  public void testTrafficOverlayOptions2() {
    MapWidget map = new MapWidget(LatLng.newInstance(0, 0), 3);
    map.setSize("500px", "400px");
    TrafficOverlay traffic = new TrafficOverlay(
        TrafficOverlayOptions.newInstance(false));
    map.addOverlay(traffic);
    RootPanel.get().add(map);
  }

  /**
   * Test the "changed" event using a trigger.
   */
  public void testTrafficOverlayChangedTrigger() {
    final MapWidget m = new MapWidget();
    final TrafficOverlay traffic = new TrafficOverlay();

    m.addOverlay(traffic);
    traffic.addTrafficOverlayChangedHandler(new TrafficOverlayChangedHandler() {

      public void onChanged(TrafficOverlayChangedEvent event) {
        finishTest();
      }

    });
    delayTestFinish(ASYNC_DELAY_MSEC);
    traffic.trigger(new TrafficOverlayChangedEvent(traffic, true));
  }
}

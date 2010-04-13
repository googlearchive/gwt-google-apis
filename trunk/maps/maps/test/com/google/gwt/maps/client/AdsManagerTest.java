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
package com.google.gwt.maps.client;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.maps.client.AdsManager.AdsManagerOptions;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Tests for the AdsManager class.
 * 
 */
public class AdsManagerTest extends GWTTestCase {
  LatLng atlanta;
  MapWidget map;

  @Override
  public String getModuleName() {
    return "com.google.gwt.maps.GoogleMapsTest";
  }

  @Override
  public void gwtSetUp() {
    atlanta = LatLng.newInstance(33.7814790, -84.3880580);
    map = new MapWidget(atlanta, 8);
    map.setSize("300px", "300px");
    RootPanel.get().add(map);
  }

  public void testAdsManager() {
    AdsManager adsManager = AdsManager.newInstance(map, "pub-1234567890123456");
    assertNotNull("Got back NULL object from creating new AdsManager instance",
        adsManager);
  }

  public void testAdsManagerEnable() {
    AdsManager adsManager = AdsManager.newInstance(map, "pub-1234567890123456");
    assertNotNull("Got back NULL object from creating new AdsManager instance",
        adsManager);
    adsManager.setEnabled(true);
    adsManager.setEnabled(false);
  }

  public void testAdsManagerOpts() {
    AdsManagerOptions options = AdsManagerOptions.newInstance().setMaxAdsOnMap(
        2);
    assertEquals("MaxAdsOnMap 2", 2, options.getMaxAdsOnMap());
    options.setMaxAdsOnMap(10).setMinZoomLevel(7).setChannel(3);
    assertEquals("MinZoomLevel 2", 7, options.getMinZoomLevel());
    assertEquals("MaxAdsOnMap 2", 10, options.getMaxAdsOnMap());
    assertEquals("Channel 2", 3, options.getChannel());
    options.setChannel(2);
    assertEquals("Channel 3", 2, options.getChannel());
    assertEquals("MinZoomLevel 3", 7, options.getMinZoomLevel());
    assertEquals("MaxAdsOnMap 3", 10, options.getMaxAdsOnMap());
    options.setStyle(AdsManagerOptions.STYLE_ADUNIT);
    assertEquals("Style adunit", AdsManagerOptions.STYLE_ADUNIT,
        options.getStyle());
  }

  public void testAdsManagerWithOpts() {
    AdsManagerOptions options = AdsManagerOptions.newInstance().setChannel(1).setMaxAdsOnMap(
        5).setMinZoomLevel(10);
    AdsManager adsManager = AdsManager.newInstance(map, "pub-1234567890123456",
        options);
    assertNotNull("Got back NULL object from creating new AdsManager instance",
        adsManager);
  }
}

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

import com.google.gwt.maps.client.AdsManager.AdsManagerOptions;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Tests for the AdsManager class.
 * 
 */
public class AdsManagerTest extends MapsTestCase {
  @Override
  public String getModuleName() {
    return "com.google.gwt.maps.GoogleMapsTest";
  }

  @Override
  public void gwtSetUp() {
    TestUtilities.cleanDom();
  }

  public MapWidget initMap() {
    LatLng atlanta = LatLng.newInstance(33.7814790, -84.3880580);
    MapWidget map = new MapWidget(atlanta, 8);
    map.setSize("300px", "300px");
    RootPanel.get().add(map);
    return map;
  }

  public void testAdsManager() {
    loadApi(new Runnable() {
      public void run() {
        MapWidget map = initMap();
        AdsManager adsManager = AdsManager.newInstance(map,
            "pub-1234567890123456");
        assertNotNull(
            "Got back NULL object from creating new AdsManager instance",
            adsManager);
      }
    });
  }

  public void testAdsManagerEnable() {
    loadApi(new Runnable() {
      public void run() {
        MapWidget map = initMap();
        AdsManager adsManager = AdsManager.newInstance(map,
            "pub-1234567890123456");
        assertNotNull(
            "Got back NULL object from creating new AdsManager instance",
            adsManager);
        adsManager.setEnabled(true);
        adsManager.setEnabled(false);
      }
    });
  }

  public void testAdsManagerOpts() {
    loadApi(new Runnable() {
      public void run() {
        AdsManagerOptions options = AdsManagerOptions.newInstance().setMaxAdsOnMap(
            2);
        assertEquals("MaxAdsOnMap 2", 2, options.getMaxAdsOnMap());
        options.setMaxAdsOnMap(10).setMinZoomLevel(7).setChannel(3.0d);
        assertEquals("MinZoomLevel 2", 7, options.getMinZoomLevel());
        assertEquals("MaxAdsOnMap 2", 10, options.getMaxAdsOnMap());
        assertEquals("Channel 2", 3.0d, options.getChannel(), 1e-8);
        options.setChannel(2.0d);
        assertEquals("Channel 3", 2.0d, options.getChannel(), 1e-8);
        assertEquals("MinZoomLevel 3", 7, options.getMinZoomLevel());
        assertEquals("MaxAdsOnMap 3", 10, options.getMaxAdsOnMap());
        options.setStyle(AdsManagerOptions.STYLE_ADUNIT);
        assertEquals("Style adunit", AdsManagerOptions.STYLE_ADUNIT,
            options.getStyle());
      }
    });
  }

  public void testAdsManagerWithOpts() {
    loadApi(new Runnable() {
      public void run() {
        MapWidget map = initMap();
        AdsManagerOptions options = AdsManagerOptions.newInstance().setChannel(
            1.0d).setMaxAdsOnMap(5).setMinZoomLevel(10);
        AdsManager adsManager = AdsManager.newInstance(map,
            "pub-1234567890123456", options);
        assertNotNull(
            "Got back NULL object from creating new AdsManager instance",
            adsManager);
      }
    });
  }
}
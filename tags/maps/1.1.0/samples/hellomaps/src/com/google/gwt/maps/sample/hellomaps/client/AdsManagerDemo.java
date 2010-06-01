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
package com.google.gwt.maps.sample.hellomaps.client;

import com.google.gwt.maps.client.AdsManager;
import com.google.gwt.maps.client.MapOptions;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.AdsManager.AdsManagerOptions;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.Size;
import com.google.gwt.user.client.ui.HTML;

/**
 * This demo shows an ad panel on the lower right side. Sometimes ads do not
 * work if a site has not been crawled by the Google web crawler.
 */
public class AdsManagerDemo extends MapsDemo {
  private static HTML descHTML = null;
  private static final String descString = "<p>Creates a 500 x 300 "
      + "pixel map viewport to Demonstrates the Maps Ads Manager.  "
      + "Expect to see an ad panel in the lower right of the map</p>";

  public static MapsDemoInfo init() {
    return new MapsDemoInfo() {
      @Override
      public MapsDemo createInstance() {
        return new AdsManagerDemo();
      }

      @Override
      public HTML getDescriptionHTML() {
        if (descHTML == null) {
          descHTML = new HTML(descString);
        }
        return descHTML;
      }

      @Override
      public String getName() {
        return "Ads Manager";
      }
    };
  }

  private final MapWidget map;

  public AdsManagerDemo() {
    MapOptions mapOptions = MapOptions.newInstance();
    mapOptions.setSize(Size.newInstance(500, 600));

    // Mountain View
    LatLng city = LatLng.newInstance(37.4419, -122.1419);
    map = new MapWidget(city, 13, mapOptions);
    map.setUIToDefault();

    AdsManagerOptions adsOptions = AdsManagerOptions.newInstance();
    adsOptions.setMaxAdsOnMap(3);
    adsOptions.setStyle(AdsManagerOptions.STYLE_ADUNIT);
    String publisherId = "pub-1234567890123456"; // bogus publisher id
    AdsManager adsManager = AdsManager.newInstance(map, publisherId, adsOptions);
    adsManager.setEnabled(true);

    initWidget(map);
  }
}

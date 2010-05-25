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
package com.google.gwt.maps.sample.hellomaps.client;

import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.LatLngBounds;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.client.geom.Size;
import com.google.gwt.maps.client.overlay.Icon;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.user.client.ui.HTML;

/**
 * This example creates a new type of icon, using the <a
 * href="http://labs.google.com/ridefinder">Google Ride Finder</a> "mini"
 * markers as an example. We have to specify the foreground image, the shadow
 * image, and the points at which we anchor the icon to the map and anchor the
 * info window to the icon.
 */
public class IconDemo extends MapsDemo {

  private static HTML descHTML = null;

  private static final String descString = "<p>Creates a 500 x 300 pixel map viewport centered on Palo Alto, CA USA.</p>"
      + "<p>Displays a new type of icon (mini marker) at random points"
      + "inside the maps viewport.</p>\n"
      + "<p>Equivalent to the Maps JavaScript API Example: "
      + "<a href=\"http://code.google.com/apis/maps/documentation/examples/icon-simple.html\">"
      + "http://code.google.com/apis/maps/documentation/examples/icon-simple.html</a></p>\n";

  public static MapsDemoInfo init() {
    return new MapsDemoInfo() {
      @Override
      public MapsDemo createInstance() {
        return new IconDemo();
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
        return "Creating Icons";
      }
    };
  }

  private MapWidget map;

  public IconDemo() {
    map = new MapWidget(LatLng.newInstance(37.4419, -122.1419), 13);
    map.setSize("500px", "300px");
    initWidget(map);
    map.setUIToDefault();
  }

  @Override
  public void onShow() {
    map.clearOverlays();

    // Create our "tiny" marker icon
    Icon icon = Icon.newInstance(
        "http://labs.google.com/ridefinder/images/mm_20_red.png");
    icon.setShadowURL("http://labs.google.com/ridefinder/images/mm_20_shadow.png");
    icon.setIconSize(Size.newInstance(12, 20));
    icon.setShadowSize(Size.newInstance(22, 20));
    icon.setIconAnchor(Point.newInstance(6, 20));
    icon.setInfoWindowAnchor(Point.newInstance(5, 1));

    // Add 10 markers to the map at random locations
    LatLngBounds bounds = map.getBounds();
    LatLng southWest = bounds.getSouthWest();
    LatLng northEast = bounds.getNorthEast();
    double lngSpan = northEast.getLongitude() - southWest.getLongitude();
    double latSpan = northEast.getLatitude() - southWest.getLatitude();
    MarkerOptions options = MarkerOptions.newInstance();
    options.setIcon(icon);
    for (int i = 0; i < 10; i++) {
      LatLng point = LatLng.newInstance(southWest.getLatitude() + latSpan
          * Math.random(), southWest.getLongitude() + lngSpan * Math.random());

      map.addOverlay(new Marker(point, options));
    }
  }
}

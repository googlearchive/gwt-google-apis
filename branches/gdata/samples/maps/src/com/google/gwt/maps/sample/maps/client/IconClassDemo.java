/*
 * Copyright 2007 Google Inc.
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
package com.google.gwt.maps.sample.maps.client;

import com.google.gwt.maps.client.InfoWindow;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.MapTypeControl;
import com.google.gwt.maps.client.control.SmallMapControl;
import com.google.gwt.maps.client.event.MarkerClickListener;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.LatLngBounds;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.client.geom.Size;
import com.google.gwt.maps.client.overlay.Icon;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;

/**
 * In many cases, your icons may have different foregrounds, but the same shape
 * and shadow. The easiest way to achieve this behavior is to use the copy
 * constructor for the =GIcon= class, which copies all the properties over to a
 * new icon which you can then customize.
 */
public class IconClassDemo extends MapsDemo {

  private MapWidget map;

  private Icon baseIcon;

  public static MapsDemoInfo init() {
    return new MapsDemoInfo() {
      public MapsDemo createInstance() {
        return new IconClassDemo();
      }

      public String getName() {
        return "Using Icon Classes";
      }
    };
  }

  public IconClassDemo() {
    map = new MapWidget(new LatLng(37.4419, -122.1419), 13);
    map.setSize("500px", "300px");
    initWidget(map);

    map.addControl(new SmallMapControl());
    map.addControl(new MapTypeControl());

    // Create a base icon for all of our markers that specifies the
    // shadow, icon dimensions, etc.
    baseIcon = new Icon();
    baseIcon.setShadowURL("http://www.google.com/mapfiles/shadow50.png");
    baseIcon.setIconSize(new Size(20, 34));
    baseIcon.setShadowSize(new Size(37, 34));
    baseIcon.setIconAnchor(new Point(9, 34));
    baseIcon.setInfoWindowAnchor(new Point(9, 2));
    // TOOD(sgross): undocumented?
    // baseIcon.setInfoShadowAnchor(new GPoint(18, 25));
  }

  /**
   * Creates a marker whose info window displays the letter corresponding to the
   * given index.
   */
  private Marker createMarker(LatLng point, int index) {
    // Create a lettered icon for this point using our icon class
    final char letter = (char) ('A' + index);
    Icon icon = new Icon(baseIcon);
    icon.setImageURL("http://www.google.com/mapfiles/marker" + letter + ".png");
    MarkerOptions options = new MarkerOptions();
    options.setIcon(icon);
    final Marker marker = new Marker(point, options);

    marker.addClickListener(new MarkerClickListener() {
      public void onClick(Marker sender) {
        InfoWindow info = map.getInfoWindow();
        info.open(sender, new InfoWindowContent("Marker <b>" + letter + "</b>"));
      }

      public void onDoubleClick(Marker sender) {
      }
    });
    return marker;
  }

  public void onShow() {
    map.clearOverlays();

    // Add 10 markers to the map at random locations
    LatLngBounds bounds = map.getBounds();
    LatLng southWest = bounds.getSouthWest();
    LatLng northEast = bounds.getNorthEast();
    double lngSpan = northEast.getLongitude() - southWest.getLongitude();
    double latSpan = northEast.getLatitude() - southWest.getLatitude();
    for (int i = 0; i < 10; i++) {
      LatLng point = new LatLng(southWest.getLatitude() + latSpan
          * Math.random(), southWest.getLongitude() + lngSpan * Math.random());
      map.addOverlay(createMarker(point, i));
    }
  }
}

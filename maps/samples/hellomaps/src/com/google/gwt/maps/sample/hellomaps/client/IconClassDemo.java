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

import com.google.gwt.maps.client.InfoWindow;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.event.MarkerClickHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.LatLngBounds;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.client.geom.Size;
import com.google.gwt.maps.client.overlay.Icon;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.user.client.ui.HTML;

/**
 * In many cases, your icons may have different foregrounds, but the same shape
 * and shadow. The easiest way to achieve this behavior is to use the copy
 * constructor for the =GIcon= class, which copies all the properties over to a
 * new icon which you can then customize.
 */
public class IconClassDemo extends MapsDemo {

  private static HTML descHTML = null;

  private static final String descString = "<p>Creates a map view of the "
      + "centered on Palo Alto, CA USA</p>"
      + "<p>A number of markers are created that share the same shadow, but have "
      + "different foreground images (letters)</p>\n"
      + "<p>Equivalent to the Maps JavaScript API Example: "
      + "<a http://code.google.com/apis/maps/documentation/examples/icon-custom.html\">"
      + "http://code.google.com/apis/maps/documentation/examples/icon-custom.html</a></p>\n";

  public static MapsDemoInfo init() {
    return new MapsDemoInfo() {
      @Override
      public MapsDemo createInstance() {
        return new IconClassDemo();
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
        return "Using Icon Classes";
      }
    };
  }

  private MapWidget map;

  private Icon baseIcon;

  public IconClassDemo() {
    map = new MapWidget(LatLng.newInstance(37.4419, -122.1419), 13);
    map.setSize("500px", "300px");
    initWidget(map);
    map.setUIToDefault();

    // Create a base icon for all of our markers that specifies the
    // shadow, icon dimensions, etc.
    baseIcon = Icon.newInstance();
    baseIcon.setShadowURL("http://www.google.com/mapfiles/shadow50.png");
    baseIcon.setIconSize(Size.newInstance(20, 34));
    baseIcon.setShadowSize(Size.newInstance(37, 34));
    baseIcon.setIconAnchor(Point.newInstance(9, 34));
    baseIcon.setInfoWindowAnchor(Point.newInstance(9, 2));
    // TOOD(sgross): undocumented?
    // baseIcon.setInfoShadowAnchor(new GPoint(18, 25));
  }

  @Override
  public void onShow() {
    map.clearOverlays();

    // Add 10 markers to the map at random locations
    LatLngBounds bounds = map.getBounds();
    LatLng southWest = bounds.getSouthWest();
    LatLng northEast = bounds.getNorthEast();
    double lngSpan = northEast.getLongitude() - southWest.getLongitude();
    double latSpan = northEast.getLatitude() - southWest.getLatitude();
    for (int i = 0; i < 10; i++) {
      LatLng point = LatLng.newInstance(southWest.getLatitude() + latSpan
          * Math.random(), southWest.getLongitude() + lngSpan * Math.random());
      map.addOverlay(createMarker(point, i));
    }
  }

  /**
   * Creates a marker whose info window displays the letter corresponding to the
   * given index.
   */
  private Marker createMarker(LatLng point, int index) {
    // Create a lettered icon for this point using our icon class
    final char letter = (char) ('A' + index);
    Icon icon = Icon.newInstance(baseIcon);
    icon.setImageURL("http://www.google.com/mapfiles/marker" + letter + ".png");
    MarkerOptions options = MarkerOptions.newInstance();
    options.setIcon(icon);
    final Marker marker = new Marker(point, options);

    marker.addMarkerClickHandler(new MarkerClickHandler() {

      public void onClick(MarkerClickEvent event) {
        InfoWindow info = map.getInfoWindow();
        info.open(event.getSender(), new InfoWindowContent("Marker <b>"
            + letter + "</b>"));
      }

    });

    return marker;
  }
}

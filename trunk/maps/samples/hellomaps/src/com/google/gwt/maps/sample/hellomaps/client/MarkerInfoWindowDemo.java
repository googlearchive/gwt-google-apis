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
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.user.client.ui.HTML;

/**
 * In this example, we show a custom info window above each marker by listening
 * to the click event for each marker.
 */
public class MarkerInfoWindowDemo extends MapsDemo {

  private static HTML descHTML = null;

  private static final String descString = "<p>Creates a 500 x 300 pixel map viewport centered on Palo Alto, CA USA.</p>"
      + "<p>Displays markers at random points.  Clicking on a marker displays an "
      + "InfoWindow with the number of the marker in the content.</p>\n";

  public static MapsDemoInfo init() {
    return new MapsDemoInfo() {
      @Override
      public MapsDemo createInstance() {
        return new MarkerInfoWindowDemo();
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
        return "Display Info Windows Above Markers";
      }
    };
  }

  private MapWidget map;

  public MarkerInfoWindowDemo() {
    map = new MapWidget(LatLng.newInstance(37.4419, -122.1419), 13);
    map.setSize("500px", "300px");
    initWidget(map);
    map.setUIToDefault();
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
      map.addOverlay(createMarker(point, i + 1));
    }
  }

  private Marker createMarker(LatLng point, final int number) {
    final Marker marker = new Marker(point);
    
    marker.addMarkerClickHandler(new MarkerClickHandler() {
      public void onClick(MarkerClickEvent event) {
        InfoWindow info = map.getInfoWindow();
        info.open(marker,
            new InfoWindowContent("Marker #<b>" + number + "</b>"));
      }
    });
    
    return marker;
  }
}
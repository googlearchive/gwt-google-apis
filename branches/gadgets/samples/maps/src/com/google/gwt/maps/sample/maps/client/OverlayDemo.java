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
package com.google.gwt.maps.sample.maps.client;

import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.MapTypeControl;
import com.google.gwt.maps.client.control.SmallMapControl;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.LatLngBounds;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.Polyline;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;

import java.util.Arrays;
import java.util.Comparator;

/**
 * This example displays 10 markers at random locations on the map and a
 * polyline with 5 random points. GMarker use the default Google Maps icon if no
 * other icon is given. See Creating Icons for an example using custom icons.
 * 
 * Remember that you must include the VML namespace and CSS in your HTML
 * document to view polylines in IE. See <a
 * href="http://www.google.com/apis/maps/documentation/index.html#XHTML_and_VML">XHTML
 * and VML</a> for more information.
 */
public class OverlayDemo extends MapsDemo {
  private static HTML descHTML = null;
  private static final String descString =
      "<p>Creates a 500 x 300 pixel map viewport centered on Palo Alto, CA USA. "
          + "You should be able to scroll the viewport by clicking and dragging "
          + "with the mouse.</p>\n"
          + "<p>You should see a purple polyline with multiple segments " 
          + "(The vertices are randomly generated).</p>"
          + "<p>Equivalent to the Maps JavaScript API Example: "
          + "<a href=\"http://code.google.com/apis/maps/documentation/examples/polyline-simple.html\">"
          + "http://code.google.com/apis/maps/documentation/examples/polyline-simple.html</a></p>\n";

  public static MapsDemoInfo init() {
    return new MapsDemoInfo() {
      @Override
      public MapsDemo createInstance() {
        return new OverlayDemo();
      }

      @Override
      public HTML getDescriptionHTML() {
        if (descHTML == null) descHTML = new HTML(descString);
        return descHTML;
      }

      @Override
      public String getName() {
        return "Map Overlays";
      }
    };
  }

  private MapWidget map;

  public OverlayDemo() {
    map = new MapWidget(new LatLng(37.4419, -122.1419), 13);
    map.setSize("500px", "300px");
    initWidget(map);
    map.addControl(new SmallMapControl());
    map.addControl(new MapTypeControl());
  }

  @Override
  public void onShow() {
    map.clearOverlays();

    // Add 10 markers in random locations on the map
    LatLngBounds bounds = map.getBounds();
    LatLng southWest = bounds.getSouthWest();
    LatLng northEast = bounds.getNorthEast();
    double lngSpan = northEast.getLongitude() - southWest.getLongitude();
    double latSpan = northEast.getLatitude() - southWest.getLatitude();
    for (int i = 0; i < 10; i++) {
      LatLng point =
          new LatLng(southWest.getLatitude() + latSpan * Math.random(),
              southWest.getLongitude() + lngSpan * Math.random());
      map.addOverlay(new Marker(point));
    }

    // Add a polyline with five random points. Sort the points by
    // longitude so that the line does not intersect itself.
    LatLng[] points = new LatLng[5];
    for (int i = 0; i < 5; i++) {
      points[i] =
          new LatLng(southWest.getLatitude() + latSpan * Math.random(),
              southWest.getLongitude() + lngSpan * Math.random());
    }

    Arrays.sort(points, new Comparator<LatLng>() {
      public int compare(LatLng p1, LatLng p2) {
        return new Double(p1.getLongitude()).compareTo(new Double(
            p2.getLongitude()));
      }
    });

    Polyline pline = new Polyline(points);
    map.addOverlay(pline);
    if (pline.getVertexCount() != 5) {
      Window.alert("Created polyline with 5 vertices, but now it has "
          + pline.getVertexCount());
    }
  }
}

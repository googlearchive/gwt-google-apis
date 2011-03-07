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

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.maps.client.MapType;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.LatLngBounds;
import com.google.gwt.maps.client.overlay.EncodedPolyline;
import com.google.gwt.maps.client.overlay.Layer;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.Polygon;
import com.google.gwt.maps.client.overlay.Polyline;
import com.google.gwt.maps.client.overlay.PolylineOptions;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

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
  private enum OverlayDemos {
    TEST_POLYGON_ENCODED("Display Polygon from an encoded string"), //
    TEST_POLYLINE_ENCODED("Display Polyline from an encoded string"), //
    TEST_POLYLINE_ENCODED_TRANSPARENT("Display Polyline from an encoded string with default transparency"), //
    TEST_POLYLINE_GEODESIC("Display a geodesic Polyline from Zurich to NYC"), //
    TEST_POLYLINE_ONE("Display a Polyline"), //
    TEST_TEN_MARKERS("Display ten Random Markers"), //
    TEST_GLAYER("Third Party Layer Support"); //

    final String value;

    OverlayDemos(String s) {
      value = s;
    }

    String valueOf() {
      return value;
    }
  }

  private static HTML descHTML = null;
  private static final String descString = "<p>Creates a 500 x 300 pixel map viewport. "
      + "You should be able to scroll the viewport by clicking and dragging "
      + "with the mouse.</p>\n"
      + "<ul>"
      + "<li><b>Display Polygon from an encoded string</b> Traces the outline of the Centennial Olympic Park fountain in Atlanta, GA USA.</li>\n"
      + "<li><b>Display Polyline from an encoded string</b> Traces a route across a bridge that is 100% opaque.</li>\n"
      + "<li><b>Display Polyline from an encoded string with default transparency</b> Similar to the previous demo, but the transparency of the line is set to the default.</li>"
      + "<li><b>Display a geodesic Polyline from Zurich to NYC</b> A line that follows the curve of the earth.</li>"
      + "<li><b>Display a Polyline</b> You should see a purple polyline with multiple segments "
      + "(The vertices are randomly generated).</li>\n"
      + "<li><b>Display ten random Markers</b> Ten Markers are placed at random points in the viewport.</li>"
      + "<li><b>Load a <a href=\"http://spreadsheets.google.com/pub?key=p9pdwsai2hDN-cAocTLhnag\">Third Party Layer</a></b>"
      + "</ul>"
      + "<p>Equivalent to the Maps JavaScript API Example: "
      + "<a href=\"http://code.google.com/apis/maps/documentation/examples/polyline-simple.html\">"
      + "http://code.google.com/apis/maps/documentation/examples/polyline-simple.html</a></p>\n";

  // A polyline stored in an encoded string.
  private static final String ENCODED_LEVELS = "B????????????????????????????????????B";
  private static final String ENCODED_POINTS = "iuowFf{kbMzH}N`IbJb@zBpYzO{dAvfF{LwDyN`_@`NzKqB|Ec@|L}BKmBbCoPjrBeEdy@uJ`Mn@zoAer@bjA~Xz{JczBa]pIps@de@tW}rCdxSwhPl`XgikCl{soA{dLdAaaF~cCyxCk_Aao@jp@kEvnCgoJ`]y[pVguKhCkUflAwrEzKk@yzCv^k@?mI";

  private static final int NUM_POINTS = 10;

  public static MapsDemoInfo init() {
    return new MapsDemoInfo() {
      @Override
      public MapsDemo createInstance() {
        return new OverlayDemo();
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
        return "Map Overlays";
      }
    };
  }

  private ListBox actionListBox;
  private MapWidget map;

  public OverlayDemo() {

    VerticalPanel vertPanel = new VerticalPanel();
    vertPanel.setStyleName("hm-panel");

    actionListBox = new ListBox();
    for (OverlayDemos od : OverlayDemos.values()) {
      actionListBox.addItem(od.valueOf());
    }
    actionListBox.addChangeHandler(new ChangeHandler() {
      public void onChange(ChangeEvent event) {
        displayOverlay();
      }
    });

    HorizontalPanel horizPanel = new HorizontalPanel();
    horizPanel.add(new Label("Choose Action:"));
    horizPanel.add(actionListBox);
    horizPanel.setSpacing(10);

    vertPanel.add(horizPanel);

    map = new MapWidget(LatLng.newInstance(37.4419, -122.1419), 13);
    map.setSize("500px", "300px");
    map.setUIToDefault();
    vertPanel.add(map);
    map.addMapType(MapType.getHybridMap());
    map.setCurrentMapType(MapType.getHybridMap());

    initWidget(vertPanel);
  }

  @Override
  public void onShow() {
    displayOverlay();
  }

  private void displayOverlay() {

    map.clearOverlays();

    LatLngBounds bounds = map.getBounds();
    LatLng southWest = bounds.getSouthWest();
    LatLng northEast = bounds.getNorthEast();
    double lngSpan = northEast.getLongitude() - southWest.getLongitude();
    double latSpan = northEast.getLatitude() - southWest.getLatitude();

    LatLng[] points = new LatLng[NUM_POINTS];

    for (int i = 0; i < NUM_POINTS; i++) {
      points[i] = LatLng.newInstance(southWest.getLatitude() + latSpan * Math.random(),
          southWest.getLongitude() + lngSpan * Math.random());
      GWT.log("points[" + i + "] = " + points[i] + " z-index = "
          + Marker.getZIndex(points[i].getLatitude()), null);
    }

    OverlayDemos selected = OverlayDemos.values()[actionListBox.getSelectedIndex()];

    switch (selected) {
      case TEST_TEN_MARKERS: {
        // Add markers in random locations on the map
        for (int i = 0; i < NUM_POINTS; i++) {
          map.addOverlay(new Marker(points[i]));
        }
      }
        break;

      case TEST_POLYLINE_ONE: {
        // Add a polyline with NUM_POINTS random points. Sort the points by
        // longitude so that the line does not intersect itself.
        Arrays.sort(points, new Comparator<LatLng>() {
          public int compare(LatLng p1, LatLng p2) {
            return new Double(p1.getLongitude()).compareTo(new Double(
                p2.getLongitude()));
          }
        });
        Polyline pline = new Polyline(points);
        map.addOverlay(pline);
        if (pline.getVertexCount() != NUM_POINTS) {
          Window.alert("Created polyline with " + NUM_POINTS
              + " vertices, but now it has " + pline.getVertexCount());
        }
      }
        break;

      case TEST_POLYLINE_ENCODED: {
        // Add a polyline encoded in a string
        map.setCenter(LatLng.newInstance(40.71213418976525, -73.96785736083984), 15);
        Polyline pline = Polyline.fromEncoded("#3333cc", 10, 1.0,
            ENCODED_POINTS, 32, ENCODED_LEVELS, 4);
        map.addOverlay(pline);
      }
        break;
      case TEST_POLYLINE_GEODESIC: {
        LatLng nycToZurich[] = {LatLng.newInstance(40.75, -73.90), // New York
            LatLng.newInstance(47.3, 8.5) // Zurich
        };
        map.setCenter(LatLng.newInstance(40, -25), 2);
        Polyline pline = new Polyline(nycToZurich, "#FF0000", 1, .75,
            PolylineOptions.newInstance(false, true));
        map.addOverlay(pline);
      }
        break;
      case TEST_POLYLINE_ENCODED_TRANSPARENT: {
        // Add a polyline with transparency
        map.setCenter(LatLng.newInstance(40.71213418976525, -73.96785736083984), 15);
        Polyline pline = Polyline.fromEncoded(ENCODED_POINTS, 32,
            ENCODED_LEVELS, 4);
        map.addOverlay(pline);
      }
        break;

      case TEST_POLYGON_ENCODED: {
        // Add a polygon encoded as a series of polylines.
        map.setCenter(LatLng.newInstance(33.75951619957536, -84.39289301633835), 20);
        EncodedPolyline[] polylines = new EncodedPolyline[2];
        polylines[0] = EncodedPolyline.newInstance("au`mEz_bbO?sAbA@?pAcA?", 2,
            "BBBBB", 1, "#ff0000", 2, 0.9);

        polylines[1] = EncodedPolyline.newInstance("{t`mEt_bbO?eAx@??dAy@?", 2,
            "BBBBB", 1);
        polylines[1].setColor("#ff0000");
        polylines[1].setWeight(2);
        polylines[1].setOpacity(0.7);

        Polygon theFountain = Polygon.fromEncoded(polylines, true, "#ff0000",
            0.2, true);
        map.addOverlay(theFountain);
      }
        break;

      case TEST_GLAYER: {
        map.setCenter(LatLng.newInstance(33.75951619957536, -84.39289301633835), 15);
        map.addOverlay(Layer.newInstance("com.panoramio.all"));
      }
      break;
      
      default:
        Window.alert("Cannot handle selection: " + selected.valueOf());
        break;
    }
  }
}

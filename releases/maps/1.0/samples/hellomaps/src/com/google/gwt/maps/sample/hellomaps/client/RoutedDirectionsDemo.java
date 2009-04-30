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
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.geocode.DirectionQueryOptions;
import com.google.gwt.maps.client.geocode.DirectionResults;
import com.google.gwt.maps.client.geocode.Directions;
import com.google.gwt.maps.client.geocode.DirectionsCallback;
import com.google.gwt.maps.client.geocode.DirectionsPanel;
import com.google.gwt.maps.client.geocode.Placemark;
import com.google.gwt.maps.client.geocode.Route;
import com.google.gwt.maps.client.geocode.StatusCodes;
import com.google.gwt.maps.client.geocode.Waypoint;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;

import java.util.List;

/**
 * This demo extends the simple demo, by adding waypoints to the requested
 * directions.
 * 
 * For full documentation of the various objects, methods and events in the
 * Directions API package, consult the API Reference at {@link
 * "http://code.google.com/apis/maps"}
 */
public class RoutedDirectionsDemo extends MapsDemo {
  private static final LatLng ATLANTA = LatLng.newInstance(33.7814790,
      -84.3880580);
  // Points of interest along the map.
  private static final LatLng STONE_MOUNTAIN_PARK = LatLng.newInstance(
      33.80653802509606, -84.15252685546875);
  // Cyclorama
  private static final LatLng CYCLORAMA = LatLng.newInstance(
      33.741185330333956, -84.35834884643555);
  // Georgia Aquarium
  private static final LatLng GEORGIA_AQUARIUM = LatLng.newInstance(
      33.761443931868925, -84.39432263374329);
  // Underground Atlanta
  private static final LatLng UNDERGROUND_ATLANTA = LatLng.newInstance(
      33.75134645137294, -84.39026713371277);
  // The Dwarf House Restaurant in Hapeville
  private static final String DWARF_HOUSE = "461 N Central Ave Hapeville, GA";
  private static HTML descHTML = null;
  private static final String descString = "<p>Displays a map centered on"
      + " Atlanta, GA USA</p>"
      + " <p>Queries the Google Directions service and displays a polyline,"
      + " markers on the map, and textual directions on the right.  The"
      + " directions are for a leisurely route from Midtown Atlanta to the"
      + " Atlanta Airport through various waypoints.</p>\n";

  private static Waypoint waypoints[] = {
      new Waypoint("10 10th St NE, Atlanta, GA 30309"), // Midtown Atlanta
      new Waypoint(GEORGIA_AQUARIUM), //
      new Waypoint(UNDERGROUND_ATLANTA), //
      new Waypoint(CYCLORAMA), //
      new Waypoint(STONE_MOUNTAIN_PARK), //
      new Waypoint(DWARF_HOUSE), //
      new Waypoint("N Terminal Pkwy, Atlanta, GA 30320") // The Airport
  };

  public static MapsDemoInfo init() {
    return new MapsDemoInfo() {
      @Override
      public MapsDemo createInstance() {
        return new RoutedDirectionsDemo();
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
        return "Routed Directions";
      }
    };
  }

  private MapWidget map;

  public RoutedDirectionsDemo() {
    HorizontalPanel hp = new HorizontalPanel();
    map = new MapWidget(ATLANTA, 15);
    map.setSize("400px", "480px");
    map.setUIToDefault();
    map.getElement().getStyle().setPropertyPx("margin", 15);
    hp.add(map);
    DirectionsPanel directionsPanel = new DirectionsPanel();
    hp.add(directionsPanel);
    directionsPanel.setSize("100%", "100%");

    initWidget(hp);

    DirectionQueryOptions opts = new DirectionQueryOptions(map,
        directionsPanel);

    // Create directions from Midtown Atlanta to the Airport with a *few*
    // stops along the way.
    Directions.loadFromWaypoints(waypoints, opts, new DirectionsCallback() {

      public void onFailure(int statusCode) {
        Window.alert("Failed to load directions: Status "
            + StatusCodes.getName(statusCode) + " " + statusCode);
      }

      public void onSuccess(DirectionResults result) {
        GWT.log("Successfully loaded directions.", null);

        // A little exercise of the route API
        List<Route> routes = result.getRoutes();
        for (Route r : routes) {
          Placemark start = r.getStartGeocode();
          GWT.log("start of route: " + start.getAddress(), null);
          Placemark end = r.getEndGeocode();
          GWT.log("end of route: " + end.getAddress(), null);
        }
      }
    });
  }
}

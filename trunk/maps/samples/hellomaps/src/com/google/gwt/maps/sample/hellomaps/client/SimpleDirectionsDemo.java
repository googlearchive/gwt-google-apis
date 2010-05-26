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
import com.google.gwt.maps.client.geocode.StatusCodes;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasVerticalAlignment;

/**
 * You can add driving directions using the Google Maps API by using the new
 * GDirections object. The GDirections objects requests and receives direction
 * results using either query strings (e.g. "New York, NY to Chicago, IL") or
 * provided textual lat/lons (e.g. "40.712882, -73.967257 to
 * 41.943181,-87.770677"). The GDirections object also supports multi-part
 * driving directions using a series of points. Directions may be displayed as
 * either a polyline drawing the route on a map, as a series of textual
 * description within a DIV element (e.g. "Turn right onto the Williamsburg
 * Bridge ramp") or both.
 * 
 * To use directions in the Google Maps API, create an object of type
 * GDirections and designate a Map object and/or DIV to receive and display
 * results. By default, the map is centered and bounded by the returned route(s)
 * (though you can change this with parameters within a GDirectionOptions
 * object).
 * 
 * Driving directions are requested using the GDirections.load() method. This
 * method takes a query string and a set of optional GDirectionsOptions
 * parameters. If the GDirections object was constructed with a supplied Map
 * object, then the returned directions will contain a polyline overlay. If the
 * GDirections object was constructed with a supplied DIV element, then the
 * returned directions will contain a GRoute object, containing a set of GStep
 * objects. (If the directions consist of multi-point directions, the returned
 * directions will contain multiple GRoute objects, each consisting of a series
 * of GStep objects.)
 * 
 * Note that the directions object is not populated with this return information
 * immediately. For this reason, the GDirections object defines a "load" event
 * which you can intercept to determine this state.
 * 
 * Once directions are returned, the GDirections object will internally store
 * results which you can retrieve using GDirections.getPolyline() and/or
 * GDirections.getRoute(i:Number) methods. Steps within a route can be retrieved
 * using the GRoute.getStep(i:Number) method and the HTML summary of that step
 * can be retrieved using GStep.getDescriptionHtml().
 * 
 * The GDirections object also supports multi-point directions, which can be
 * constructed using the GDirections.loadFromWaypoints() method. This method
 * takes an array of textual input addresses or textual lat/lon points. Each
 * separate waypoint is computed as a separate route and returned in a separate
 * GRoute object, each of which contains a series of GStep objects.
 * 
 * GRoute objects store the number of steps (of type GStep for that route, the
 * starting and ending geocode for that route, and other computed information
 * such as distance, duration, and exact lat/lon of the endpoint (which may be
 * different than the ending geocode if the geocode does not lie on a road
 * segment). Each GStep object as well contains the description for that text
 * (e.g. "Merge onto US-101 S via the ramp to San Jose") plus computed
 * information including the distance, duration, and exact lat/lon as well.
 * 
 * The GDirections object fires three events which you can intercept:
 * 
 * "load": This event is triggered when a directions result successfully
 * returns, but before any overlay elements are added to the map/panel.
 * "addoverlay": This event is triggered after the polyline and/or textual
 * directions components are added to the map and/or DIV elements. "error": This
 * event is triggered if a directions request results in an error. Callers can
 * use GDirections.getStatus() to get more information about the error.
 * 
 * For full documentation of the various objects, methods and events in the
 * Directions API package, consult the API Reference
 */
public class SimpleDirectionsDemo extends MapsDemo {
  private static HTML descHTML = null;

  private static final String descString = "<p>Displays a map centered on "
      + "Cambridge, MA USA</p>"
      + "<p>Queries the Google Directions service and displays a polyline, "
      + "markers on the map, and textual directions on the right.  The "
      + "directions are from the MIT dorms to Fenway Park.</p>\n"
      + "<p>Equivalent to the Maps JavaScript API Example: "
      + "<a href=\"http://code.google.com/apis/maps/documentation/examples/directions-simple.html\">"
      + "http://code.google.com/apis/maps/documentation/examples/directions-simple.html</a></p>\n";

  public static MapsDemoInfo init() {
    return new MapsDemoInfo() {
      @Override
      public MapsDemo createInstance() {
        return new SimpleDirectionsDemo();
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
        return "Simple Directions";
      }
    };
  }

  private MapWidget map;

  public SimpleDirectionsDemo() {
    final Grid grid = new Grid(1, 2);
    grid.setWidth("100%");
    grid.getCellFormatter().setWidth(0, 0, "500px");
    grid.getCellFormatter().setVerticalAlignment(0, 0,
        HasVerticalAlignment.ALIGN_TOP);
    grid.getCellFormatter().setWidth(0, 1, "300px");
    grid.getCellFormatter().setVerticalAlignment(0, 1,
        HasVerticalAlignment.ALIGN_TOP);
    map = new MapWidget(LatLng.newInstance(42.351505, -71.094455), 15);
    map.setHeight("480px");
    grid.setWidget(0, 0, map);
    DirectionsPanel directionsPanel = new DirectionsPanel();
    grid.setWidget(0, 1, directionsPanel);
    directionsPanel.setSize("100%", "100%");

    initWidget(grid);

    DirectionQueryOptions opts = new DirectionQueryOptions(map, directionsPanel);
    String query = "from: 500 Memorial Dr, Cambridge, MA to: 4 Yawkey Way, Boston, MA";
    Directions.load(query, opts, new DirectionsCallback() {

      public void onFailure(int statusCode) {
        Window.alert("Failed to load directions: Status "
            + StatusCodes.getName(statusCode) + " " + statusCode);
      }

      public void onSuccess(DirectionResults result) {
        GWT.log("Successfully loaded directions.", null);
      }
    });
  }
}

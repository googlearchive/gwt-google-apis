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
package com.google.gwt.maps.client.geocode;

import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.MapsTestCase;
import com.google.gwt.maps.client.geocode.DirectionQueryOptions.TravelMode;
import com.google.gwt.maps.client.overlay.Marker;

import java.util.List;

/**
 * Tests for the geocoding service and supporting classes.
 */
public class DirectionsTest extends MapsTestCase {

  @Override
  public String getModuleName() {
    return "com.google.gwt.maps.GoogleMapsTest";
  }

  public void testDirections() {
    loadApi(new Runnable() {
      public void run() {
        MapWidget map = new MapWidget();
        DirectionsPanel directionsPanel = new DirectionsPanel();
        DirectionQueryOptions opts = new DirectionQueryOptions(map,
            directionsPanel);
        String query = "from: 10 10th St NW, Atlanta, GA 30309 USA "
            + "to: 1600 Amphitheatre Parkway Mountain View, CA 94043 USA";
        Directions.load(query, opts, new DirectionsCallback() {

          public void onFailure(int statusCode) {
            fail("Query failed with status: " + statusCode + " "
                + StatusCodes.getName(statusCode));
          }

          public void onSuccess(DirectionResults result) {
            assertNotNull("CopyrightHtml", result.getCopyrightsHtml());
            assertNotNull("Distance", result.getDistance());
            assertTrue("Distance.inMeters() > 0",
                result.getDistance().inMeters() > 0);
            assertNotNull("Distance.inLocalizedUnits",
                result.getDistance().inLocalizedUnits());
            assertNotNull("Duration", result.getDuration());
            assertTrue("Duration.inSeconds() > 0",
                result.getDuration().inSeconds() > 0);
            assertNotNull("Duration.inLocalizedUnits",
                result.getDuration().inLocalizedUnits());
            assertNotNull("result.getRoutes()", result.getRoutes());
            assertNotNull("result.getSummaryHtml()", result.getSummaryHtml());
            assertNotNull("polyline", result.getPolyline());
            assertNotNull("result.getPlacemarks()", result.getPlacemarks());
            List<Marker> markers = result.getMarkers();
            assertNotNull("markers", markers);
            assertNotNull("markers.get(0)", markers.get(0));
            assertNotNull("markers.get(1)", markers.get(1));
            finishTest();
          }
        });
      }
    }, false);
  }

  public void testDirectionsNoMap() {
    loadApi(new Runnable() {
      public void run() {
        String query = "from: 10 10th St NW, Atlanta, GA 30309 USA "
            + "to: 1600 Amphitheatre Parkway Mountain View, CA 94043 USA";

        Directions.load(query, new DirectionsCallback() {

          public void onFailure(int statusCode) {
            fail("Query failed with status: " + statusCode + " "
                + StatusCodes.getName(statusCode));
          }

          public void onSuccess(DirectionResults result) {
            assertNotNull("CopyrightHtml", result.getCopyrightsHtml());
            assertNotNull("Distance", result.getDistance());
            assertTrue("Distance.inMeters() > 0",
                result.getDistance().inMeters() > 0);
            assertNotNull("Distance.inLocalizedUnits",
                result.getDistance().inLocalizedUnits());
            assertNotNull("Duration", result.getDuration());
            assertTrue("Duration.inSeconds() > 0",
                result.getDuration().inSeconds() > 0);
            assertNotNull("Duration.inLocalizedUnits",
                result.getDuration().inLocalizedUnits());
            List<Marker> markers = result.getMarkers();
            assertNotNull("markers", markers);
            assertNotNull("markers.get(0)", markers.get(0));
            assertNotNull("markers.get(1)", markers.get(1));
            assertNotNull("result.getRoutes()", result.getRoutes());
            assertNotNull("result.getPlacemarks()", result.getPlacemarks());
            assertNotNull("result.getSummaryHtml()", result.getSummaryHtml());

            // Some items are not retrieved if not specified in
            // DirectionsOptions()
            assertNull("polyline", result.getPolyline());

            finishTest();
          }
        });
      }
    }, false);
  }

  public void testDirectionsNoPanel() {
    loadApi(new Runnable() {
      public void run() {
        MapWidget map = new MapWidget();
        DirectionQueryOptions opts = new DirectionQueryOptions(map);
        String query = "from: 10 10th St NW, Atlanta, GA 30309 USA "
            + "to: 1600 Amphitheatre Parkway Mountain View, CA 94043 USA";
        Directions.load(query, opts, new DirectionsCallback() {

          public void onFailure(int statusCode) {
            fail("Query failed with status: " + statusCode + " "
                + StatusCodes.getName(statusCode));
          }

          public void onSuccess(DirectionResults result) {
            assertNotNull("CopyrightHtml", result.getCopyrightsHtml());
            assertNotNull("Distance", result.getDistance());
            assertTrue("Distance.inMeters() > 0",
                result.getDistance().inMeters() > 0);
            assertNotNull("Distance.inLocalizedUnits",
                result.getDistance().inLocalizedUnits());
            assertNotNull("Duration", result.getDuration());
            assertTrue("Duration.inSeconds() > 0",
                result.getDuration().inSeconds() > 0);
            assertNotNull("Duration.inLocalizedUnits",
                result.getDuration().inLocalizedUnits());
            assertNotNull("result.getRoutes()", result.getRoutes());
            assertNotNull("result.getSummaryHtml()", result.getSummaryHtml());
            assertNotNull("polyline", result.getPolyline());
            assertNotNull("result.getPlacemarks()", result.getPlacemarks());
            List<Marker> markers = result.getMarkers();
            assertNotNull("markers", markers);
            assertNotNull("markers.get(0)", markers.get(0));
            assertNotNull("markers.get(1)", markers.get(1));
            result.clear();
            finishTest();
          }
        });
      }
    }, false);
  }

  // A simple test to ensure none of the methods throws exceptions.
  public void testDirectionsQueryOptions() {
    loadApi(new Runnable() {
      public void run() {
        MapWidget map = new MapWidget();
        DirectionsPanel directionsPanel = new DirectionsPanel();
        DirectionQueryOptions opts = new DirectionQueryOptions(map,
            directionsPanel);
        opts.setAvoidHighways(true);
        opts.setLocale("de_DE");
        opts.setPreserveViewport(true);
        opts.setRetrievePolyline(false);
        opts.setRetrieveSteps(false);
        opts.setTravelMode(TravelMode.DRIVING);
      }
    });
  }

  public void testFailedDirections() {
    loadApi(new Runnable() {
      public void run() {
        MapWidget map = new MapWidget();
        DirectionsPanel directionsPanel = new DirectionsPanel();
        DirectionQueryOptions opts = new DirectionQueryOptions(map,
            directionsPanel);
        String query = "from: 1000 Baloney St NW, Atlantis, XY"
            + "to: 160000 amphibian street absolutelynowhere ZX";
        Directions.load(query, opts, new DirectionsCallback() {

          public void onFailure(int statusCode) {
            finishTest();
          }

          public void onSuccess(DirectionResults result) {
            fail("Query suceeded when it should have failed.");
          }
        });
      }
    }, false);
  }

  // I checked the actual values returned for the DRIVING and WALKING constants
  // by the JS API. This test ensures that the TravelMode enum is working,
  // but could fail in the future if the values returned by these contstants
  // is changed.
  public void testTravelModes() {
    loadApi(new Runnable() {
      public void run() {
        assertEquals("TRAVEL_MODE_DRIVING", 1, TravelMode.DRIVING.value());
        assertEquals("TRAVEL_MODE_WALKING", 2, TravelMode.WALKING.value());
      }
    });
  }
}

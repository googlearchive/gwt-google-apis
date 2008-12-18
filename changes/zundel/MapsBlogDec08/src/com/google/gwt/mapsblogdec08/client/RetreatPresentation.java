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
package com.google.gwt.mapsblogdec08.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapType;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.Control;
import com.google.gwt.maps.client.control.ControlAnchor;
import com.google.gwt.maps.client.control.ControlPosition;
import com.google.gwt.maps.client.control.MapTypeControl;
import com.google.gwt.maps.client.control.SmallMapControl;
import com.google.gwt.maps.client.event.MarkerClickHandler;
import com.google.gwt.maps.client.geocode.DirectionQueryOptions;
import com.google.gwt.maps.client.geocode.DirectionResults;
import com.google.gwt.maps.client.geocode.Directions;
import com.google.gwt.maps.client.geocode.DirectionsCallback;
import com.google.gwt.maps.client.geocode.StatusCodes;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.Polyline;
import com.google.gwt.maps.client.overlay.PolylineOptions;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ImageBundle;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * This is a demonstration of using directions, polylines and info windows.
 */
public class RetreatPresentation extends Composite {

  /**
   * GWT can automatically sprite images
   */
  public interface BlogImages extends ImageBundle {
    @Resource("BabsonGlobe_144x108.jpg")
    AbstractImagePrototype babsonGlobe();

    @Resource("CawkerCity_200x150.jpg")
    AbstractImagePrototype ballOfTwine();

    @Resource("TasmanianDevil_158x150.jpg")
    AbstractImagePrototype tasmanianDevil();
  }

  /**
   * A Map control can be created in GWT
   */
  private class BlogControl extends Control.CustomControl {
    ListBox listBox = new ListBox();

    protected BlogControl() {
      super(new ControlPosition(ControlAnchor.TOP_RIGHT, 20, 40));
    }

    @Override
    public boolean isSelectable() {
      return false;
    }

    @Override
    protected Widget initialize(final MapWidget map) {
      final String CAWKER_CITY = "Cawker City";
      final String TASMANIA = "Tasmania";
      final String BABSON_COLLEGE = "Babson College";

      VerticalPanel panel = new VerticalPanel();
      panel.add(new Label("Where should we hold the Geo-GWT Retreat?"));
      panel.add(listBox);
      panel.getElement().getStyle().setProperty("backgroundColor", "#FFFFFF");
      panel.getElement().getStyle().setProperty("border", "solid #666666 1px");
      panel.setSpacing(5);
      panel.setWidth("110px");
      listBox.addItem("Select Venue:");
      listBox.addItem(CAWKER_CITY);
      listBox.addItem(TASMANIA);
      listBox.addItem(BABSON_COLLEGE);

      listBox.addChangeListener(new ChangeListener() {

        public void onChange(Widget sender) {
          String choice = listBox.getItemText(listBox.getSelectedIndex());
          map.clearOverlays();
          if (choice.equals(CAWKER_CITY)) {
            showCawkerCity();
          } else if (choice.equals(TASMANIA)) {
            showTasmania();
          } else if (choice.equals(BABSON_COLLEGE)) {
            showBabsonCollege();
          }
        }
      });
      return panel;
    }
  }

  private final LatLng atlanta = LatLng.newInstance(33.7814790, -84.3880580);
  private final LatLng babsonCollege = LatLng.newInstance(42.295691, -71.265918);
  private final LatLng cawkerCity = LatLng.newInstance(39.509, -98.434);
  private final LatLng hobartAirport = LatLng.newInstance(-42.82084, 147.518578);
  private final BlogImages images = GWT.create(BlogImages.class);
  private final LatLng kansasCityAirport = LatLng.newInstance(39.302516,
      -94.709342);
  private final LatLng loganAirport = LatLng.newInstance(42.371473, -70.986249);
  private final MapWidget map;
  private final LatLng siliconValley = LatLng.newInstance(37.4419, -122.1419);
  private final LatLng sydney = LatLng.newInstance(-33.869275299570795,
      151.1986541748047);
  private final LatLng tasmanianDevilPark = LatLng.newInstance(-43.084937,
      147.896919);

  public RetreatPresentation() {
    // Open a map centered on Cawker City, KS USA
    map = new MapWidget(cawkerCity, 2);
    map.setSize("100%", "100%");
    map.removeMapType(MapType.getNormalMap());
    map.removeMapType(MapType.getSatelliteMap());
    map.addMapType(MapType.getPhysicalMap());
    map.addMapType(MapType.getHybridMap());
    map.setCurrentMapType(MapType.getHybridMap());

    // Add some controls for the zoom level
    map.addControl(new SmallMapControl());
    map.addControl(new MapTypeControl());
    map.addControl(new BlogControl());
    
    initWidget(map);
  };

  /**
   * Shall we meet at the Babson Globe?
   */
  public void showBabsonCollege() {
    PolylineOptions polylineOptions = PolylineOptions.newInstance(false, true);
    final InfoWindowContent infoContent = makeInfoWindowContent(
        "BabsonCollege",
        images.babsonGlobe(),
        new Anchor(
            "Karen's Picassa Album",
            "http://picasaweb.google.com/karen.schober/BabsonCollegeWellesleyMassachusetts#5262787466135968066"));

    Marker m = new Marker(babsonCollege);
    m.addMarkerClickHandler(new MarkerClickHandler() {

      public void onClick(MarkerClickEvent event) {
        map.getInfoWindow().open(babsonCollege, infoContent);
      }

    });

    map.addOverlay(m);

    // Info window for destination
    map.setCenter(babsonCollege);
    map.setZoomLevel(3);
    map.getInfoWindow().open(babsonCollege, infoContent);

    // Add some Polylines to represent air travel
    Polyline p;
    p = new Polyline(new LatLng[] {atlanta, loganAirport}, "#FF3333", 2, .8,
        polylineOptions);
    map.addOverlay(p);
    p = new Polyline(new LatLng[] {siliconValley, loganAirport}, "#FF3333", 2,
        .8, polylineOptions);
    map.addOverlay(p);
    p = new Polyline(new LatLng[] {sydney, loganAirport}, "#FF3333", 2, .8,
        polylineOptions);
    map.addOverlay(p);

    // Driving route
    DirectionQueryOptions dirOptions = new DirectionQueryOptions();
    dirOptions.setRetrievePolyline(true);
    dirOptions.setRetrieveSteps(true);
    Directions.load(
        "from:logan international airport to:Great Map Drive, Wellesley, MA 02481 usa",
        dirOptions, new DirectionsCallback() {

          public void onFailure(int statusCode) {
            Window.alert("Query failed: " + statusCode + " "
                + StatusCodes.getName(statusCode));
          }

          public void onSuccess(DirectionResults result) {
            map.addOverlay(result.getPolyline());
          }
        });

  }

  /**
   * How about the world's largest ball of Sisal twine?
   */
  public void showCawkerCity() {
    PolylineOptions polylineOptions = PolylineOptions.newInstance(false, true);
    final InfoWindowContent infoContent = makeInfoWindowContent(
        "Cawker City",
        images.ballOfTwine(),
        new Anchor(
            "Matthew's Picassa Album",
            "http://picasaweb.google.com/matthewdcarver/WorldSLargestBallOfTwine#5052522256945404914"));

    Marker m = new Marker(cawkerCity);
    m.addMarkerClickHandler(new MarkerClickHandler() {

      public void onClick(MarkerClickEvent event) {
        map.getInfoWindow().open(cawkerCity, infoContent);
      }

    });

    // Add a marker
    map.addOverlay(m);
    map.setCenter(cawkerCity);
    map.setZoomLevel(3);

    // Add an info window to highlight a point of interest
    map.getInfoWindow().open(cawkerCity, infoContent);

    // Add some Polylines to represent air travel
    Polyline p;
    p = new Polyline(new LatLng[] {atlanta, kansasCityAirport}, "#FF3333", 2,
        .8, polylineOptions);
    map.addOverlay(p);
    p = new Polyline(new LatLng[] {siliconValley, kansasCityAirport},
        "#FF3333", 2, .8, polylineOptions);
    map.addOverlay(p);
    p = new Polyline(new LatLng[] {sydney, kansasCityAirport}, "#FF3333", 2,
        .8, polylineOptions);
    map.addOverlay(p);

    // Add the driving route
    DirectionQueryOptions dirOptions = new DirectionQueryOptions();
    dirOptions.setRetrievePolyline(true);
    dirOptions.setRetrieveSteps(true);
    Directions.load("from:601 Brasilia Ave Kansas City, MO, usa to:cawker city, ks, usa",
        dirOptions, new DirectionsCallback() {

          public void onFailure(int statusCode) {
            Window.alert("Query failed: " + statusCode + " "
                + StatusCodes.getName(statusCode));
          }

          public void onSuccess(DirectionResults result) {
            map.addOverlay(result.getPolyline());
          }
        });
  }

  /**
   * How about we visit one of the far corners of the globe?
   */
  public void showTasmania() {
    PolylineOptions polylineOptions = PolylineOptions.newInstance(false, true);
    Marker m = new Marker(tasmanianDevilPark);
    final InfoWindowContent infoContent = makeInfoWindowContent(
        "Tasmanian Devil Preserve", images.tasmanianDevil(), new Anchor(
            "Photo by Wayne McLean",
            "http://en.wikipedia.org/wiki/File:Tasdevil_large.jpg"));
    m.addMarkerClickHandler(new MarkerClickHandler() {

      public void onClick(MarkerClickEvent event) {
        map.getInfoWindow().open(tasmanianDevilPark, infoContent);
      }

    });
    map.addOverlay(m);
    map.setCenter(tasmanianDevilPark);
    map.setZoomLevel(3);

    // Add an info window to highlight a point of interest
    map.getInfoWindow().open(tasmanianDevilPark, infoContent);

    // Add some Polylines
    Polyline p;
    p = new Polyline(new LatLng[] {atlanta, sydney}, "#FF3333", 2, .8,
        polylineOptions);
    map.addOverlay(p);
    p = new Polyline(new LatLng[] {siliconValley, sydney}, "#FF3333", 2, .8,
        polylineOptions);
    map.addOverlay(p);
    p = new Polyline(new LatLng[] {sydney, hobartAirport}, "#FF3333", 2, .8,
        polylineOptions);
    map.addOverlay(p);

    // Add some Polylines to represent air travel
    DirectionQueryOptions dirOptions = new DirectionQueryOptions();
    dirOptions.setRetrievePolyline(true);
    dirOptions.setRetrieveSteps(true);
    Directions.load(
        "from:hobart airport, Tasmania, Aus to:Port Arthur Highway, Taranna, Tasmania, Australia 7180",
        dirOptions, new DirectionsCallback() {

          public void onFailure(int statusCode) {
            Window.alert("Query failed: " + statusCode + " "
                + StatusCodes.getName(statusCode));
          }

          public void onSuccess(DirectionResults result) {
            map.addOverlay(result.getPolyline());
          }
        });
  }

  // Routine for building InfoWindow for all the venues.
  private InfoWindowContent makeInfoWindowContent(String title,
      AbstractImagePrototype image, Anchor link) {

    VerticalPanel panel = new VerticalPanel();
    panel.add(new Label(title));
    panel.add(image.createImage());
    panel.add(link);
    link.getElement().getStyle().setProperty("fontSize", ".5em");
    link.getElement().getStyle().setProperty("position", "absolute");
    link.getElement().getStyle().setPropertyPx("left", 70);
    return new InfoWindowContent(panel);
  }

}

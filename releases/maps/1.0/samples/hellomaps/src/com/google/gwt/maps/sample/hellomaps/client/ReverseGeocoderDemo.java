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

import com.google.gwt.core.client.JsArray;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapUIOptions;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.event.MarkerClickHandler;
import com.google.gwt.maps.client.geocode.Geocoder;
import com.google.gwt.maps.client.geocode.LocationCallback;
import com.google.gwt.maps.client.geocode.Placemark;
import com.google.gwt.maps.client.geocode.StatusCodes;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * An example of how to reverse geocode. Select a point on the map and a marker
 * will be added with the list of placemarks returned.
 */
public class ReverseGeocoderDemo extends MapsDemo {

  private static HTML descHTML = null;
  private static final String descString = "<p>Creates a 500 x 300 pixel map "
      + "viewport zoomed out to show the entire world.</p>"
      + "<p>Click on the map to show the closest municipalities.</p>\n";

  public static MapsDemoInfo init() {
    return new MapsDemoInfo() {
      @Override
      public MapsDemo createInstance() {
        return new ReverseGeocoderDemo();
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
        return "Geocoding: Reverse";
      }
    };
  }

  private Geocoder geocoder;

  private MapWidget map;

  public ReverseGeocoderDemo() {
    VerticalPanel outer = new VerticalPanel();
    map = new MapWidget(LatLng.newInstance(0, 0), 1);
    outer.add(map);
    map.setSize("500px", "300px");
    initWidget(outer);
    // Workaround for bug with click handler & setUItoDefaults() - see issue 260
    MapUIOptions opts = map.getDefaultUI();
    opts.setDoubleClick(false);
    map.setUI(opts);

    map.addMapClickHandler(new MapClickHandler() {

      public void onClick(final MapClickEvent event) {
        // Do not mis-interpret clicks on the info window and marker as
        // map click events to be reverse geocoded.
        if (event.getOverlay() != null) {
          return;
        }
        final Marker marker = new Marker(event.getLatLng());
        final VerticalPanel panel = new VerticalPanel();
        final InfoWindowContent content = new InfoWindowContent(panel);
        panel.add(new Label("LatLng: " + event.getLatLng().toString()));

        // Do a reverse geocode of this position
        geocoder.getLocations(event.getLatLng(), new LocationCallback() {

          public void onFailure(int statusCode) {
            Window.alert("Failed to geocode position " + event.getLatLng()
                + ". Status: " + statusCode + " "
                + StatusCodes.getName(statusCode));
          }

          public void onSuccess(JsArray<Placemark> locations) {
            for (int i = 0; i < locations.length(); ++i) {
              Placemark location = locations.get(i);
              StringBuilder value = new StringBuilder();
              if (location.getAddress() != null) {
                value.append(location.getAddress());
              } else {
                if (location.getCity() != null) {
                  value.append(location.getCity());
                }
                if (location.getAdministrativeArea() != null) {
                  value.append(location.getAdministrativeArea() + ", ");
                }
                if (location.getCountry() != null) {
                  value.append(location.getCountry());
                }
              }
              int ordinal = (i + 1);
              panel.add(new Label("  " + ordinal + ") " + value.toString()));
            }
            map.addOverlay(marker);
            map.getInfoWindow().open(marker, content);
          }
        });
        marker.addMarkerClickHandler(new MarkerClickHandler() {

          public void onClick(MarkerClickEvent markerClickEvent) {
            if (!map.getInfoWindow().isVisible()) {
              map.getInfoWindow().open(marker, content);
            }
          }
        });
      }

    });
    geocoder = new Geocoder();
  }

}

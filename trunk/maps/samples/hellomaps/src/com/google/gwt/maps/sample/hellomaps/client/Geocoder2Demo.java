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
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.maps.client.InfoWindow;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.geocode.Geocoder;
import com.google.gwt.maps.client.geocode.LocationCallback;
import com.google.gwt.maps.client.geocode.Placemark;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitHandler;

/**
 * You can access the Google Maps API geocoder via the GClientGeocoder object.
 * Use the GClientGeocoder.getLatLng() method to convert a string address into a
 * GLatLng. Because geocoding involves sending a request to Google's servers, it
 * can take some time. To avoid making your script wait, you should pass in a
 * callback function to execute after the response returns.
 *
 * In this example, we geocode an address, add a marker at that point, and open
 * an info window displaying the address.
 */
public class Geocoder2Demo extends MapsDemo {
  private static HTML descHTML = null;

  private static final String descString = "<p> Creates a zoomed out map.</p>"
      + "<p>Clicking on one of the buttons below the map submits the  address to "
      + "the Google Geocoding service.  When the query returns successfully"
      + "the map will display a marker on the coordinates and display the "
      + "country code returned from the query in an InfoWindow.</p>"
      + "<p>Equivalent to the Maps JavaScript API Example: "
      + "<a href=\"http://code.google.com/apis/maps/documentation/examples/geocoding-extraction.html\">"
      + "http://code.google.com/apis/maps/documentation/examples/geocoding-extraction.html</a></p>\n";

  private static final String[] sampleAddresses = {
      "1600 amphitheatre mtn view ca",
      "1 Telegraph Hill Blvd, San Francisco, CA, USA",
      "4141 Avenue Pierre-De-Coubertin, Montréal, QC, Canada",
      "Champ de Mars 75007 Paris, France", "Piazza del Colosseo, Roma, Italia",
      "Domkloster 3, 50667 Köln, Deutschland",
      "Plaza de la Virgen de los Reyes, 41920, Sevilla, España",
      "123 Main St, Googleville"};

  /**
   * Constant used to layout the preconfigured addresses into a grid using this
   * number of columns.
   */
  private static final int NUM_ADDRESS_COLUMNS = 2;

  public static MapsDemoInfo init() {
    return new MapsDemoInfo() {
      @Override
      public MapsDemo createInstance() {
        return new Geocoder2Demo();
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
        return "Geocoding: Extracting Address Information";
      }
    };
  }

  private final Geocoder geocoder;

  private final MapWidget map;

  public Geocoder2Demo() {
    Panel panel = new FlowPanel();
    final FormPanel form = new FormPanel();
    form.setAction("#");
    Panel formElements = new FlowPanel();
    Label label = new Label("Search for an address:");
    formElements.add(label);
    final TextBox addressBox = new TextBox();
    addressBox.setVisibleLength(40);
    formElements.add(addressBox);
    Button submit = new Button("Search");
    submit.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        form.submit();
      }
    });
    formElements.add(submit);
    form.add(formElements);
    form.addSubmitHandler(new SubmitHandler() {
      public void onSubmit(SubmitEvent event) {
        showAddress(addressBox.getText());
        event.cancel();
      }
    });
    panel.add(form);

    map = new MapWidget(LatLng.newInstance(34, 0), 1);
    map.setSize("100%", "480px");
    panel.add(map);

    Grid grid = new Grid((sampleAddresses.length / NUM_ADDRESS_COLUMNS) + 1,
        NUM_ADDRESS_COLUMNS);

    for (int i = 0; i < sampleAddresses.length; i++) {
      final String address = sampleAddresses[i];
      Button link = new Button(address);
      // Hyperlink link = new Hyperlink(address, true,
      // "Extracting Structured Address Information");
      link.addClickHandler(new ClickHandler() {
        public void onClick(ClickEvent event) {
          addressBox.setText(address);
          form.submit();
        }
      });
      grid.setWidget(i / NUM_ADDRESS_COLUMNS, i % NUM_ADDRESS_COLUMNS, link);
    }
    panel.add(grid);

    initWidget(panel);
    geocoder = new Geocoder();
  }

  private void showAddress(final String address) {
    final InfoWindow info = map.getInfoWindow();
    geocoder.getLocations(address, new LocationCallback() {
      public void onFailure(int statusCode) {
        Window.alert("Sorry, we were unable to geocode that address");
      }

      public void onSuccess(JsArray<Placemark> locations) {
        Placemark place = locations.get(0);
        Marker marker = new Marker(place.getPoint());
        map.addOverlay(marker);
        String message = place.getAddress() + "<br>" + "<b>Country code:</b> "
            + place.getCountry();
        info.open(marker, new InfoWindowContent(message));
      }
    });
  }
}

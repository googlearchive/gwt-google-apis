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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.maps.client.InfoWindow;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.geocode.Geocoder;
import com.google.gwt.maps.client.geocode.LatLngCallback;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
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
public class GeocoderDemo extends MapsDemo {

  private static final LatLng ATLANTA = LatLng.newInstance(33.7814790, -84.3880580);

  private static HTML descHTML = null;
  private static final String descString = "<p>Creates a 500 x 300 pixel map "
    + "viewport centered on Atlanta, GA USA.</p>"
      + "<p>Type an address in the textbox.  Clicking the 'Go' button will"
      + "contact the Google Geocoding service, display the resulting "
      + "Lat/Lng coordinates, and re-position the map with those coordinates"
      + "in the center </p>\n"
      + "<p>Equivalent to the Maps JavaScript API Example: "
      + "<a href=\"http://code.google.com/apis/maps/documentation/examples/geocoding-simple.html\">"
      + "http://code.google.com/apis/maps/documentation/examples/geocoding-simple.html</a></p>\n";

  public static MapsDemoInfo init() {
    return new MapsDemoInfo() {
      @Override
      public MapsDemo createInstance() {
        return new GeocoderDemo();
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
        return "Geocoding: Simple";
      }
    };
  }

  private Geocoder geocoder;
  private Label latLabel;
  private Label lngLabel;

  private MapWidget map;

  public GeocoderDemo() {
    Panel panel = new FlowPanel();
    final FormPanel form = new FormPanel();
    form.setAction("#");

    Panel formElements = new FlowPanel();
    final TextBox address = new TextBox();
    address.setVisibleLength(60);
    address.setText("10 10th Street, Atlanta, GA");
    formElements.add(address);
    formElements.add(buildLatLngPanel());
    this.displayLatLng(ATLANTA);

    Button submit = new Button("Go!");
    submit.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        form.submit();
      }
    });
    formElements.add(submit);
    form.add(formElements);
    form.addSubmitHandler(new SubmitHandler() {
      public void onSubmit(SubmitEvent event) {
        showAddress(address.getText());
        event.cancel();
      }
    });
    panel.add(form);

    map = new MapWidget(ATLANTA, 13);
    map.setSize("500px", "300px");
    panel.add(map);
    initWidget(panel);
    geocoder = new Geocoder();
  }

  /*
   * Build a horizontal panel to display latitude and longitude returned from
   * the geocoding service.
   *
   * Broke this function out to make the constructor more readable.
   */
  private Panel buildLatLngPanel() {
    HorizontalPanel horiz = new HorizontalPanel();
    horiz.add(new Label("Lat:"));
    latLabel = new Label();
    horiz.add(latLabel);
    horiz.add(new Label("Long:"));
    lngLabel = new Label();
    horiz.add(lngLabel);
    horiz.setSpacing(10);
    return horiz;
  }

  private void displayLatLng(LatLng point) {
    NumberFormat fmt = NumberFormat.getFormat("#.0000000#");
    latLabel.setText(fmt.format(point.getLatitude()));
    lngLabel.setText(fmt.format(point.getLongitude()));
  }

  private void showAddress(final String address) {
    final InfoWindow info = map.getInfoWindow();
    geocoder.getLatLng(address, new LatLngCallback() {
      public void onFailure() {
        Window.alert(address + " not found");
      }

      public void onSuccess(LatLng point) {
        map.setCenter(point, 13);
        Marker marker = new Marker(point);
        map.addOverlay(marker);
        info.open(marker, new InfoWindowContent(address));
        displayLatLng(point);
      }
    });
  }
}

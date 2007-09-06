/*
 * Copyright 2007 Google Inc.
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

import com.google.gwt.maps.client.InfoWindow;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.geocode.Geocoder;
import com.google.gwt.maps.client.geocode.LatLngCallback;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FormHandler;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormSubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormSubmitEvent;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * You can access the Google Maps API geocoder via the GClientGeocoder object.
 * Use the GClientGeocoder.getLatLng() method to convert a string address into a
 * GLatLng. Because geocoding involves sending a request to Google's servers, it
 * can take some time. To avoid making your script wait, you should pass in a
 * callback function to exdcute after the response returns.
 * 
 * In this example, we geocode an address, add a marker at that point, and open
 * an info window displaying the address.
 */
public class GeocoderDemo extends MapsDemo {

  private MapWidget map;

  private Geocoder geocoder;

  public static MapsDemoInfo init() {
    return new MapsDemoInfo() {
      public MapsDemo createInstance() {
        return new GeocoderDemo();
      }

      public String getName() {
        return "Geocoding";
      }
    };
  }

  public GeocoderDemo() {
    Panel panel = new FlowPanel();
    final FormPanel form = new FormPanel();
    form.setAction("#");
    Panel formElements = new FlowPanel();
    final TextBox address = new TextBox();
    address.setVisibleLength(60);
    address.setText("1600 Amphitheatre Pky, Mountain View, CA");
    formElements.add(address);
    Button submit = new Button("Go!");
    submit.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        form.submit();
      }
    });
    formElements.add(submit);
    form.add(formElements);
    form.addFormHandler(new FormHandler() {
      public void onSubmit(FormSubmitEvent event) {
        showAddress(address.getText());
        event.setCancelled(true);
      }

      public void onSubmitComplete(FormSubmitCompleteEvent event) {
      }
    });
    panel.add(form);

    map = new MapWidget(new LatLng(37.4419, -122.1419), 13);
    map.setSize("500px", "300px");
    panel.add(map);
    initWidget(panel);
    geocoder = new Geocoder();
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
      }
    });
  }
}

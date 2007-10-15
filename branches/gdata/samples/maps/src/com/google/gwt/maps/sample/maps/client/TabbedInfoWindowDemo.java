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
import com.google.gwt.maps.client.InfoWindowContent.InfoWindowTab;
import com.google.gwt.maps.client.control.MapTypeControl;
import com.google.gwt.maps.client.control.SmallMapControl;
import com.google.gwt.maps.client.event.MarkerClickListener;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;

/**
 * Version 2 of the API introduces openInfoWindowTabs() and the GInfoWindowTab
 * class to support info windows with multiple, named tabs. The example below
 * displays a simple tabbed info window above a single marker.
 */
public class TabbedInfoWindowDemo extends MapsDemo {

  private MapWidget map;

  private Marker marker;

  private InfoWindowContent content;

  public static MapsDemoInfo init() {
    return new MapsDemoInfo() {
      public MapsDemo createInstance() {
        return new TabbedInfoWindowDemo();
      }

      public String getName() {
        return "Tabbed Info Windows";
      }
    };
  }

  public TabbedInfoWindowDemo() {
    map = new MapWidget(new LatLng(37.4419, -122.1419), 13);
    map.setSize("500px", "300px");
    initWidget(map);
    map.addControl(new SmallMapControl());
    map.addControl(new MapTypeControl());

    // Our info window content
    InfoWindowTab[] infoTabs = new InfoWindowTab[] {
        new InfoWindowTab("Tab #1", "This is tab #1 content"),
        new InfoWindowTab("Tab #2", "This is tab #2 content")};
    content = new InfoWindowContent(infoTabs);

    // Place a marker in the center of the map and open the info window
    // automatically
    marker = new Marker(map.getCenter());
    marker.addClickListener(new MarkerClickListener() {
      public void onClick(Marker sender) {
        InfoWindow info = map.getInfoWindow();
        info.open(marker, content);
      }

      public void onDoubleClick(Marker sender) {
      }
    });
    map.addOverlay(marker);
  }

  public void onShow() {
    InfoWindow info = map.getInfoWindow();
    info.open(marker, content);
  }
}

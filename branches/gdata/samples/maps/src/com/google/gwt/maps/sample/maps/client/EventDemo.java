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

import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.event.MapMoveListener;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;

/**
 * To register an event listener, call the GEvent.addListener method. Pass it a
 * map, an event to listen for, and a function to call when the specified event
 * occurs. In the following example code, we display the latitude and longitude
 * of the center of the map after the user drags the map.
 */
public class EventDemo extends MapsDemo {

  private MapWidget map;

  public static MapsDemoInfo init() {
    return new MapsDemoInfo() {
      public MapsDemo createInstance() {
        return new EventDemo();
      }

      public String getName() {
        return "Event Listeners";
      }
    };
  }

  public EventDemo() {
    Panel panel = new FlowPanel();
    map = new MapWidget(new LatLng(37.4419, -122.1419), 13);
    map.setSize("500px", "300px");
    panel.add(map);
    final Label message = new Label();
    panel.add(message);
    initWidget(panel);
    map.addMapMoveListener(new MapMoveListener() {
      public void onMove(MapWidget sender) {
      }

      public void onMoveEnd(MapWidget sender) {
        message.setText(map.getCenter().toString());
      }

      public void onMoveStart(MapWidget sender) {
      }
    });
  }
}

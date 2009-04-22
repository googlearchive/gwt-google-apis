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

import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.event.MapMoveEndHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;

/**
 * To register an event listener, call the GEvent.addListener method. Pass it a
 * map, an event to listen for, and a function to call when the specified event
 * occurs. In the following example code, we display the latitude and longitude
 * of the center of the map after the user drags the map.
 */
public class EventDemo extends MapsDemo {

  private static HTML descHTML =
      null;
  private static final String descString =
      "<p>Creates a 500 x 300 pixel map viewport centered on Palo Alto, CA USA. "
          + "You should be able to scroll the viewport by clicking and dragging "
          + "with the mouse.</p>\n"
          + "<p>Dragging the map fires a MapMoveListener that reports the "
          + "Lat/Lng coordinates below the map.</p>"
          + "<p>Equivalent to the Maps JavaScript API Example: "
          + "<a href=\"http://code.google.com/apis/maps/documentation/examples/event-context.html\">"
          + "http://code.google.com/apis/maps/documentation/examples/event-context.html</a></p>\n";

  public static MapsDemoInfo init() {
    return new MapsDemoInfo() {
      @Override
      public MapsDemo createInstance() {
        return new EventDemo();
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
        return "Simple Event Handler Demo";
      }
    };
  }

  private MapWidget map;

  public EventDemo() {
    Panel panel =
        new FlowPanel();
    map =
        new MapWidget(LatLng.newInstance(37.4419, -122.1419), 13);
    map.setSize("500px", "300px");
    map.setUIToDefault();
    panel.add(map);
    final Label message =
        new Label();
    panel.add(message);
    initWidget(panel);
    
    map.addMapMoveEndHandler(new MapMoveEndHandler() {
      public void onMoveEnd(MapMoveEndEvent event) {
        message.setText(map.getCenter().toString());
      }
    });
  }
}

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

import com.google.gwt.maps.client.InfoWindow;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.event.MarkerDragEndHandler;
import com.google.gwt.maps.client.event.MarkerDragStartHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.user.client.ui.HTML;

/**
 * Markers are interactive objects that can be clicked on and dragged to a new
 * location. In this example, we place a draggable marker on the map, and listen
 * to a few of its simpler events. Draggable markers support four kinds of
 * events -- click, dragstart, drag and dragend. By default, markers are
 * clickable but not draggable, so they need to be initialized with the
 * additional marker option draggable set to true. Draggable markers are also
 * bouncy by default. If you don't like this behavior, just set the bouncy
 * option to false and it will drop down gracefully.
 */
public class DragMarkerDemo extends MapsDemo {
  private static HTML descHTML = null;

  private static final String descString = "<p>Creates a 500 x 300 pixel map viewport centered on Palo Alto, CA USA.</p>"
      + "<p>Draws a marker at the center of the maps window.  The marker will move if you click and drag it.</p>\n"
      + "<p>Equivalent to the Maps JavaScript API Example: "
      + "<a href=\"http://code.google.com/apis/maps/documentation/examples/marker-drag.html\">"
      + "http://code.google.com/apis/maps/documentation/examples/marker-drag.html</a></p>\n";

  public static MapsDemoInfo init() {
    return new MapsDemoInfo() {
      @Override
      public MapsDemo createInstance() {
        return new DragMarkerDemo();
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
        return "Draggable Markers";
      }
    };
  }

  private MapWidget map;

  public DragMarkerDemo() {
    map = new MapWidget(LatLng.newInstance(37.4419, -122.1419), 13);
    map.setSize("500px", "300px");
    initWidget(map);
  }

  @Override
  public void onShow() {
    map.clearOverlays();

    MarkerOptions options = MarkerOptions.newInstance();
    options.setDraggable(true);
    final Marker marker = new Marker(map.getCenter(), options);
    final InfoWindow info = map.getInfoWindow();
    
    marker.addMarkerDragEndHandler(new MarkerDragEndHandler() {
      public void onDragEnd(MarkerDragEndEvent event) {
          info.open(marker, new InfoWindowContent("Just bouncing along..."));
        }
      
    });
    
    marker.addMarkerDragStartHandler(new MarkerDragStartHandler() {
      public void onDragStart(MarkerDragStartEvent event) {
        info.setVisible(false);
      }
    });
    
    map.addOverlay(marker);
  }
}

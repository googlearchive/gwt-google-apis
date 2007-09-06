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
import com.google.gwt.maps.client.event.DragListener;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;

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

  private MapWidget map;

  public static MapsDemoInfo init() {
    return new MapsDemoInfo() {
      public MapsDemo createInstance() {
        return new DragMarkerDemo();
      }

      public String getName() {
        return "Draggable Markers";
      }
    };
  }

  public DragMarkerDemo() {
    map = new MapWidget(new LatLng(37.4419, -122.1419), 13);
    map.setSize("500px", "300px");
    initWidget(map);

  }

  public void onShow() {
    map.clearOverlays();

    MarkerOptions options = new MarkerOptions();
    options.setDraggable(true);
    final Marker marker = new Marker(map.getCenter(), options);
    final InfoWindow info = map.getInfoWindow();

    marker.addDragListener(new DragListener() {
      boolean created = false;

      public void onDrag() {
      }

      public void onDragEnd() {
        if (created) {
          info.setVisible(true);
        } else {
          info.open(marker, new InfoWindowContent("Just bouncing along..."));
        }
      }

      public void onDragStart() {
        info.setVisible(false);
      }
    });
    map.addOverlay(marker);
  }
}

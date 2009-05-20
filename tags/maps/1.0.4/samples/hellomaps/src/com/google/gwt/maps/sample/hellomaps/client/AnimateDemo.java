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
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HTML;

/**
 * The following example displays a map, waits two seconds, and then pans to a
 * new center point.
 * 
 * The panTo method centers the map at a given point. If the specified point is
 * in the visible part of the map, then the map pans smoothly to the point; if
 * not, the map jumps to the point.
 */
public class AnimateDemo extends MapsDemo {

  private static HTML descHTML = null;
  private static final String descString = "<p>Creates a 500 x 300 pixel map viewport centered on Palo Alto, CA USA. "
      + "You should be able to scroll the viewport by clicking and dragging "
      + "with the mouse.</p>\n"
      + "<p>After one second, the map will automatically scroll "
      + "diagonally.</p>\n"
      + "<p>Equivalent to the Maps JavaScript API Example: "
      + "<a href=\"http://code.google.com/apis/maps/documentation/examples/map-animate.html\">"
      + "http://code.google.com/apis/maps/documentation/examples/map-animate.html</a></p>\n";

  public static MapsDemoInfo init() {
    return new MapsDemoInfo() {
      @Override
      public MapsDemo createInstance() {
        return new AnimateDemo();
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
        return "Map Movement and Animation";
      }
    };
  }

  private MapWidget map;

  private final LatLng start = LatLng.newInstance(37.4419, -122.1419);

  private final LatLng end = LatLng.newInstance(37.4569, -122.1569);

  public AnimateDemo() {
    map = new MapWidget(start, 13);
    map.setSize("500px", "300px");
    map.setUIToDefault();
    initWidget(map);
  }

  @Override
  public void onShow() {
    map.setCenter(start);
    new Timer() {
      @Override
      public void run() {
        map.panTo(end);
      }
    }.schedule(1000);
  }
}

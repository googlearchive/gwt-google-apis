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

import com.google.gwt.core.client.GWT;
import com.google.gwt.maps.client.MapType;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.MapTypeControl;
import com.google.gwt.maps.client.control.SmallMapControl;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HTML;

/**
 * You can add controls to the map with the addControl method. In this case, we
 * add the built-in GSmallMapControl and GMapTypeControl controls, which let us
 * pan/zoom the map and switch between Map and Satellite modes, respectively.
 */
public class ControlsDemo extends MapsDemo {

  private static HTML descHTML = null;
  private static final String descString = "<p>Creates a 500 x 300 pixel map viewport centered on Palo Alto, CA USA. "
      + "You should be able to scroll the viewport by clicking and dragging "
      + "with the mouse.</p>\n"
      + "<p>The map window should be decorated with controls for zooming "
      + "in and out and for changing the map type.</p>"
      + "<p>Equivalent to the Maps JavaScript API Example: "
      + "<a href=\"http://code.google.com/apis/maps/documentation/examples/control-simple.html\">"
      + "http://code.google.com/apis/maps/documentation/examples/control-simple.html</a></p>\n";

  public static MapsDemoInfo init() {
    return new MapsDemoInfo() {
      @Override
      public MapsDemo createInstance() {
        return new ControlsDemo();
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
        return "Adding Controls to the Map";
      }
    };
  }

  private MapWidget map;

  public ControlsDemo() {
    map = new MapWidget(LatLng.newInstance(37.4419, -122.1419), 13);
    map.setSize("500px", "300px");
    initWidget(map);
    map.addControl(new SmallMapControl());
    map.addControl(new MapTypeControl());

    Timer timer = new Timer() {

      @Override
      public void run() {
        // Exercise the minimum & maximum resolution entry points.
        MapType types[] = map.getMapTypes();
        for (MapType t : types) {
          int minResolution = t.getMinimumResolution();
          int maxResolution = t.getMaximumResolution();
          GWT.log("Map Type: " + t.getName(true) + " Min resolution: "
              + minResolution + " Max Resolution: " + maxResolution, null);

          minResolution = t.getMinimumResolution();
          maxResolution = t.getMaximumResolution();
          GWT.log("@ point: " + map.getCenter().toString() + " Map Type: "
              + t.getName(true) + " Min resolution: " + minResolution
              + " Max Resolution: " + maxResolution, null);
        }
      }
    };
    timer.schedule(1000);
  }
}

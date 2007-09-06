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
import com.google.gwt.maps.client.control.MapTypeControl;
import com.google.gwt.maps.client.control.SmallMapControl;
import com.google.gwt.maps.client.geom.LatLng;

/**
 * You can add controls to the map with the addControl method. In this case, we
 * add the built-in GSmallMapControl and GMapTypeControl controls, which let us
 * pan/zoom the map and switch between Map and Satellite modes, respectively.
 */
public class ControlsDemo extends MapsDemo {

  private MapWidget map;

  public static MapsDemoInfo init() {
    return new MapsDemoInfo() {
      public MapsDemo createInstance() {
        return new ControlsDemo();
      }

      public String getName() {
        return "Adding Controls to the Map";
      }
    };
  }

  public ControlsDemo() {
    map = new MapWidget(new LatLng(37.4419, -122.1419), 13);
    map.setSize("500px", "300px");
    initWidget(map);
    map.addControl(new SmallMapControl());
    map.addControl(new MapTypeControl());
  }
}

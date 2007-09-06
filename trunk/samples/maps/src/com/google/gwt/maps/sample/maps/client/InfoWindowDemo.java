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
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;

/**
 * To create an info window, call the openInfoWindow method, passing it a
 * location and a DOM element to display. The following example code displays an
 * info window anchored to the center of the map with a simple "Hello, world"
 * message.
 */
public class InfoWindowDemo extends MapsDemo {

  private MapWidget map;

  public static MapsDemoInfo init() {
    return new MapsDemoInfo() {
      public MapsDemo createInstance() {
        return new InfoWindowDemo();
      }

      public String getName() {
        return "Opening an Info Window";
      }
    };
  }

  public InfoWindowDemo() {
    map = new MapWidget(new LatLng(37.4419, -122.1419), 13);
    map.setSize("500px", "300px");
    initWidget(map);
  }

  public void onShow() {
    InfoWindow info = map.getInfoWindow();
    Tree tree = new Tree();
    TreeItem foo = new TreeItem("Foo");
    tree.addItem(foo);
    TreeItem bar = new TreeItem("bar");
    foo.addItem(bar);
    bar.addItem("baz");
    bar.addItem("gwt");
    // max-height must be set in advance so info window is sized appropriately
    tree.setSize("217px", "104px");
    info.open(map.getCenter(), new InfoWindowContent(tree));
  }
}

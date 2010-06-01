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

import com.google.gwt.maps.client.MapOptions;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.ui.HTML;

/**
 * The following example creates a map and centers it on Palo Alto, California.
 */
public class DragCursorDemo extends MapsDemo {

  private static HTML descHTML = null;
  private static final String descString = "<p>Creates a 500 x 300 pixel map viewport centered on Palo Alto, CA USA. "
      + "You should be able to scroll the viewport by clicking and dragging "
      + "with the mouse.</p>\n"
      + "<p>The default mouse cursors have been overridden with crosshairs "
      + "when hovering over the map, and the text cursor when dragging the map.</p>"
      + "<p>Equivalent to the Maps JavaScript API Example: "
      + "<a href=\"http://code.google.com/apis/maps/documentation/examples/map-simple.html\">"
      + "http://code.google.com/apis/maps/documentation/examples/map-simple.html</a></p>\n";

  public static MapsDemoInfo init() {
    return new MapsDemoInfo() {

      @Override
      public MapsDemo createInstance() {
        return new DragCursorDemo();
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
        return "Override Map Drag Cursor";
      }
    };
  }

  private final MapWidget map;

  public DragCursorDemo() {
    MapOptions options = MapOptions.newInstance();
    options.setDraggableCursor("crosshair");
    options.setDraggingCursor("text");
    map = new MapWidget(LatLng.newInstance(37.4419, -122.1419), 13, options);
    map.setSize("500px", "300px");
    map.setUIToDefault();
    initWidget(map);
  }
}

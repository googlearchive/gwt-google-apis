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

import com.google.gwt.maps.client.MapUIOptions;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.Overlay;
import com.google.gwt.user.client.ui.HTML;

/**
 * To trigger an action when a user clicks the map, register a listener for the
 * "click" event on your Map instance. When the event is triggered, the event
 * handler will receive two arguments: the marker that was clicked (if any), and
 * the GLatLng of the clicked point. If no marker was clicked, the first
 * argument will be null. Note: Markers are the only built-in overlay that
 * supports the "click" event. Other types of overlays, like GPolyline, are not
 * clickable.
 * 
 * In the following example, when the visitor clicks a point on the map without
 * a marker, we create a new marker at that point. When the visitor clicks a
 * marker, we remove it from the map.
 */
public class ClickDemo extends MapsDemo {
  private static HTML descHTML = null;
  private static final String descString = "<p>Creates a 500 x 300 "
      + "pixel map viewport centered on Palo Alto, CA USA. " + "</p>\n"
      + "<p>Each time the mouse is clicked in this demo, a new marker is "
      + "placed on the map at the point there the click event occurred.</p>";

  public static MapsDemoInfo init() {
    return new MapsDemoInfo() {
      @Override
      public MapsDemo createInstance() {
        return new ClickDemo();
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
        return "Click Handling";
      }
    };
  }

  private final MapWidget map;

  public ClickDemo() {
    map = new MapWidget(LatLng.newInstance(37.4419, -122.1419), 13);
    map.setSize("500px", "300px");
    initWidget(map);
    // Workaround for bug with click handler & setUItoDefaults() - see issue 260
    MapUIOptions opts = map.getDefaultUI();
    opts.setDoubleClick(false);
    map.setUI(opts);

    map.addMapClickHandler(new MapClickHandler() {
      public void onClick(MapClickEvent e) {
        MapWidget sender = e.getSender();
        Overlay overlay = e.getOverlay();
        LatLng point = e.getLatLng();

        if (overlay != null && overlay instanceof Marker) {
          sender.removeOverlay(overlay);
        } else {
          sender.addOverlay(new Marker(point));
        }
      }
    });
  }
}

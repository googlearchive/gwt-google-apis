/*
 * Copyright 2009 Google Inc.
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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.event.StreetviewOverlayChangedHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.StreetviewOverlay;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;

import java.util.Date;

/**
 * The Google Maps API now allows you to add Street View data to your maps.
 * Locations where Street View data is available is displayed using the
 * GStreetviewOverlay, which implements the GOverlay interface. You add Street
 * View availability information to your map using the Map.addOverlay() method.
 * GStreetviewOverlay has two methods (hide() and show()) for toggling display
 * of the Street View overlay.
 */
public class StreetviewOverlayDemo extends MapsDemo {
  private static HTML descHTML = null;

  private static final String descString = "<p>Displays a map centered on Brooklyn, NY USA</p>"
      + "<p>The Google Street View service is called and retrieves an overlay "
      + "representing availabity of Street View data.  Pressing the 'Toggle Street View Overlay' "
      + "button will alternately hide and show the overlay.</p>"
      + "<p>Equivalent to the Maps JavaScript API Example: "
      + "<a href=\"http://code.google.com/apis/maps/documentation/examples/streetview-layer.html\">"
      + "http://code.google.com/apis/maps/documentation/examples/streetview-layer.html</a></p>\n";

  public static MapsDemoInfo init() {
    return new MapsDemoInfo() {
      @Override
      public MapsDemo createInstance() {
        return new StreetviewOverlayDemo();
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
        return "Street View Overlays";
      }
    };
  }

  private MapWidget map;

  private StreetviewOverlay svOverlay;

  private boolean svShown;

  public StreetviewOverlayDemo() {
    Panel panel = new FlowPanel();

    map = new MapWidget(LatLng.newInstance(40.652513, -73.936615), 12);
    map.setSize("640px", "480px");
    panel.add(map);
    Button toggleStreetview = new Button("Toggle Street View Overlay");
    toggleStreetview.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        if (svShown) {
          map.removeOverlay(svOverlay);
        } else {
          map.addOverlay(svOverlay);
        }
        svShown = !svShown;
      }
    });
    panel.add(toggleStreetview);
    final Label changeLabel = new Label();
    panel.add(changeLabel);
    initWidget(panel);
    svOverlay = new StreetviewOverlay();
    svOverlay.addStreetviewOverlayChangedHandler(new StreetviewOverlayChangedHandler() {

      public void onChanged(StreetviewOverlayChangedEvent event) {
        changeLabel.setText("Last Changed: " + new Date());
      }

    });
  }

  @Override
  public void onShow() {
    map.clearOverlays();
    map.addOverlay(svOverlay);
    svShown = true;
  }

}

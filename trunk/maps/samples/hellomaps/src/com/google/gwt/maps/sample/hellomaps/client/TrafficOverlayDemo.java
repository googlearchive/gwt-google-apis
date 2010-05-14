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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.event.TrafficOverlayChangedHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.TrafficOverlay;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;

import java.util.Date;

/**
 * The Google Maps API now allows you to add traffic information to your maps.
 * Traffic information is displayed using the GTrafficOverlay, which implements
 * the GOverlay interface. You add traffic information to your map using the
 * Map.addOverlay() method. GTrafficOverlay has two methods (hide() and show())
 * for toggling display of the traffic overlay. Traffic information is displayed
 * only for supported cities.
 */
public class TrafficOverlayDemo extends MapsDemo {
  private static HTML descHTML = null;

  private static final String descString = "<p>Displays a map centered on Brooklyn, NY USA</p>"
      + "<p>The Google traffic service is called and retrieves an overlay "
      + "representing current traffic conditions.  Pressing the 'Toggle Traffic' "
      + "button will alternately hide and show the overlay.</p>"
      + "<p>Equivalent to the Maps JavaScript API Example: "
      + "<a href=\"http://code.google.com/apis/maps/documentation/examples/trafficOverlay.html\">"
      + "http://code.google.com/apis/maps/documentation/examples/trafficOverlay.html</a></p>\n";

  public static MapsDemoInfo init() {
    return new MapsDemoInfo() {
      @Override
      public MapsDemo createInstance() {
        return new TrafficOverlayDemo();
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
        return "Traffic Overlays";
      }
    };
  }

  private MapWidget map;

  private TrafficOverlay trafficInfo;

  private boolean trafficShown;

  public TrafficOverlayDemo() {
    Panel panel = new FlowPanel();

    map = new MapWidget(LatLng.newInstance(40.652513, -73.936615), 12);
    map.setSize("640px", "480px");
    panel.add(map);
    Button toggleTraffic = new Button("Toggle Traffic");
    toggleTraffic.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        if (trafficShown) {
          map.removeOverlay(trafficInfo);
        } else {
          map.addOverlay(trafficInfo);
        }
        trafficShown = !trafficShown;
      }
    });
    panel.add(toggleTraffic);
    final Label changeLabel = new Label();
    panel.add(changeLabel);
    initWidget(panel);
    trafficInfo = new TrafficOverlay();
    trafficInfo.addTrafficOverlayChangedHandler(new TrafficOverlayChangedHandler() {

      public void onChanged(TrafficOverlayChangedEvent event) {
        changeLabel.setText("Last Changed: " + new Date());
      }

    });
  }

  @Override
  public void onShow() {
    map.clearOverlays();
    map.addOverlay(trafficInfo);
    trafficShown = true;
  }

  // TODO(zundel): Add handler for event "changed"
}

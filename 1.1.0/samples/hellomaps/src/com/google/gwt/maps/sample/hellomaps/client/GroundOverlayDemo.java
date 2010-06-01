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
import com.google.gwt.maps.client.event.GroundOverlayVisibilityChangedHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.LatLngBounds;
import com.google.gwt.maps.client.overlay.GroundOverlay;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * This demo shows how to create a custom overlay in the form of a Rectangle and
 * add it to the map.
 */
public class GroundOverlayDemo extends MapsDemo {

  private static HTML descHTML = null;

  private static final String descString = "<p>Creates a map viewport centered on Palo Alto, CA USA.</p>"
      + "<p>Draws a ground overlay at the center of the maps window with an image of a boot.</p>\n";

  public static MapsDemoInfo init() {
    return new MapsDemoInfo() {
      @Override
      public MapsDemo createInstance() {
        return new GroundOverlayDemo();
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
        return "Ground Overlays";
      }
    };
  }

  private MapWidget map;

  private boolean firstTime = true;
  private Button hideButton;
  private GroundOverlay groundOverlay;

  public GroundOverlayDemo() {
    VerticalPanel vp = new VerticalPanel();
    map = new MapWidget(LatLng.newInstance(37.4419, -122.1419), 13);
    vp.add(map);
    vp.setSpacing(10);
    map.setSize("500px", "500px");
    map.setUIToDefault();

    hideButton = new Button("Hide");
    vp.add(hideButton);
    hideButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        if (groundOverlay.isVisible()) {
          hideButton.setText("Show");
        } else {
          hideButton.setText("Hide");
        }
        groundOverlay.setVisible(!groundOverlay.isVisible());
      }
    });
    initWidget(vp);
  }

  @Override
  public void onShow() {
    // The map's bounds are meaningless until the map has been added to the DOM
    // and sized appropriately
    if (firstTime) {
      firstTime = false;
      LatLngBounds bounds = map.getBounds();
      LatLng southWest = bounds.getSouthWest();
      LatLng northEast = bounds.getNorthEast();
      double lngDelta = (northEast.getLongitude() - southWest.getLongitude()) / 4;
      double latDelta = (northEast.getLatitude() - southWest.getLatitude()) / 4;

      // generate bounds that covers center map with half the width and height
      LatLngBounds rectBounds = LatLngBounds.newInstance(LatLng.newInstance(
          southWest.getLatitude() + latDelta, southWest.getLongitude()
              + lngDelta), LatLng.newInstance(northEast.getLatitude() - latDelta,
          northEast.getLongitude() - lngDelta));
      groundOverlay = new GroundOverlay("boot.jpg", rectBounds);
      groundOverlay.addGroundOverlayVisibilityChangedHandler(new GroundOverlayVisibilityChangedHandler() {

        public void onVisibilityChanged(
            GroundOverlayVisibilityChangedEvent event) {
          if (event.isVisible()) {
            hideButton.setText("Hide Overlay");
          } else {
            hideButton.setText("Show Overlay");
          }
        }

      });
      map.addOverlay(groundOverlay);
    }
  }
}

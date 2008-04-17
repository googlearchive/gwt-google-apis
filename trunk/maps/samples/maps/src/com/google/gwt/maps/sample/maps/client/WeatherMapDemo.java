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
package com.google.gwt.maps.sample.maps.client;

import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.LargeMapControl;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.client.geom.Size;
import com.google.gwt.maps.client.overlay.Icon;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerManager;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.HTML;

/**
 * The Google Maps API now allows you to add traffic information to your maps.
 * Traffic information is displayed using the GTrafficOverlay, which implements
 * the GOverlay interface. You add traffic information to your map using the
 * Map.addOverlay() method. GTrafficOverlay has two methods (hide() and show())
 * for toggling display of the traffic overlay. Traffic information is displayed
 * only for supported cities.
 */
public class WeatherMapDemo extends MapsDemo {
  private static HTML descHTML = null;

  private static final String descString = "<p>Displays a map centered on Europe</p>"
      + "<p>Displays random icons of weather events on the map at zoom levels 3, 6, 8.</p>\n"
      + "<p>Equivalent to the Maps JavaScript API Example: "
      + "<a href=\"http://gmaps-utility-library.googlecode.com/svn/trunk/markermanager/release/examples/weather_map.html\">"
      + "http://gmaps-utility-library.googlecode.com/svn/trunk/markermanager/release/examples/weather_map.html</a></p>\n";

  private static final String[] IMAGES = {
      "sun", "rain", "snow", "storm"
  };

  public static MapsDemoInfo init() {
    return new MapsDemoInfo() {
      @Override
      public MapsDemo createInstance() {
        return new WeatherMapDemo();
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
        return "Weather Map";
      }
    };
  }

  private final Icon[] icons;

  private MapWidget map;

  public WeatherMapDemo() {
    icons = new Icon[IMAGES.length];
    for (int i = 0; i < IMAGES.length; i++) {
      icons[i] = new Icon(IMAGES[i] + ".png");
      icons[i].setIconAnchor(new Point(16, 16));
      icons[i].setInfoWindowAnchor(new Point(16, 0));
      icons[i].setIconSize(new Size(32, 32));
      icons[i].setShadowURL(IMAGES[i] + "-shadow.png");
      icons[i].setShadowSize(new Size(59, 32));
    }

    map = new MapWidget(new LatLng(48.25, 11.00), 4);
    map.setSize("650px", "400px");
    map.addControl(new LargeMapControl());
    map.setDoubleClickZoom(true);
    initWidget(map);

    // Delay this so that the rest of the UI can be rendered
    DeferredCommand.addCommand(new Command() {
      public void execute() {
        setupWeatherMarkers();
      }
    });
  }

  private LatLng getRandomPoint() {
    double lat = 48.25 + (Math.random() - 0.5) * 14.5;
    double lng = 11.00 + (Math.random() - 0.5) * 36.0;
    return new LatLng(Math.round(lat * 10) / 10, Math.round(lng * 10) / 10);
  }

  private Marker[] getWeatherMarkers(int numMarkers) {
    Marker[] markers = new Marker[numMarkers];
    for (int i = 0; i < numMarkers; i++) {
      int iconIndex = (int) (Math.random() * icons.length);
      MarkerOptions options = new MarkerOptions(icons[iconIndex]);
      markers[i] = new Marker(getRandomPoint(), options);
    }
    return markers;
  }

  private void setupWeatherMarkers() {
    MarkerManager manager = new MarkerManager(map);
    manager.addMarkers(getWeatherMarkers(20), 3);
    manager.addMarkers(getWeatherMarkers(200), 6);
    manager.addMarkers(getWeatherMarkers(1000), 8);
    manager.refresh();
  }
}

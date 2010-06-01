/*
 * Copyright 2010 Google Inc.
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
import com.google.gwt.maps.client.event.StreetviewInitializedHandler;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.event.StreetviewPitchChangedHandler;
import com.google.gwt.maps.client.event.StreetviewYawChangedHandler;
import com.google.gwt.maps.client.event.StreetviewZoomChangedHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Polygon;
import com.google.gwt.maps.client.streetview.LatLngStreetviewCallback;
import com.google.gwt.maps.client.streetview.StreetviewPanoramaOptions;
import com.google.gwt.maps.client.streetview.StreetviewPanoramaWidget;
import com.google.gwt.maps.client.streetview.Pov;
import com.google.gwt.maps.client.streetview.StreetviewClient;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * This demo shows how to construct a {@link StreetviewPanoramaWidget}, handle
 * Street View events and perform a panorama lookups.
 */
public class StreetviewDemo extends MapsDemo {
  private static HTML descHTML = null;
  private static final String descString = "<p>Creates a Street View Panorama "
      + "and a Map to demonstrate dispatching event between these two objects.</p>"
      + "<p>Expect to see a polygon object that represents you Street View point of"
      + " view on a map. Click on a map to change your location in Street View.</p>";

  public static MapsDemoInfo init() {
    return new MapsDemoInfo() {
      @Override
      public MapsDemo createInstance() {
        return new StreetviewDemo();
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
        return "Streetview Test";
      }
    };
  }

  private final VerticalPanel panel;
  private final MapWidget map;
  private final StreetviewPanoramaWidget panorama;
  private final StreetviewClient svClient;
  private final LatLng tenthStreet = LatLng.newInstance(33.78148, -84.38713);

  private LatLng currentLatLng = tenthStreet;
  private Pov currentPov = Pov.newInstance();
  private Polygon viewPolygon;

  public StreetviewDemo() {
    StreetviewPanoramaOptions options = StreetviewPanoramaOptions.newInstance();
    options.setLatLng(tenthStreet);
    svClient = new StreetviewClient();
    panorama = new StreetviewPanoramaWidget(options);
    panorama.setSize("500px", "300px");

    map = new MapWidget(tenthStreet, 16);
    map.setSize("500px", "300px");
    map.addMapClickHandler(new MapClickHandler() {
      public void onClick(MapClickEvent event) {
        LatLng point = event.getLatLng() == null ? event.getOverlayLatLng()
            : event.getLatLng();
        if (point != null) {
          svClient.getNearestPanoramaLatLng(point,
              new LatLngStreetviewCallback() {
                @Override
                public void onFailure() {
                }

                @Override
                public void onSuccess(LatLng point) {
                  panorama.setLocationAndPov(point, Pov.newInstance());
                }
              });
        }
      }
    });

    panorama.addInitializedHandler(new StreetviewInitializedHandler() {
      public void onInitialized(StreetviewInitializedEvent event) {
        currentLatLng = event.getLocation().getLatLng();
        currentPov = event.getLocation().getPov();
        map.setCenter(currentLatLng);
        updatePolyline();
      }
    });

    panorama.addPitchChangedHandler(new StreetviewPitchChangedHandler() {
      public void onPitchChanged(StreetviewPitchChangedEvent event) {
        currentPov.setPitch(event.getPitch());
        updatePolyline();
      }
    });

    panorama.addYawChangedHandler(new StreetviewYawChangedHandler() {
      public void onYawChanged(StreetviewYawChangedEvent event) {
        currentPov.setYaw(event.getYaw());
        updatePolyline();
      }
    });

    panorama.addZoomChangedHandler(new StreetviewZoomChangedHandler() {
      public void onZoomChanged(StreetviewZoomChangedEvent event) {
        currentPov.setZoom(event.getZoom());
        updatePolyline();
      }
    });

    panel = new VerticalPanel();
    panel.add(panorama);
    panel.add(map);
    initWidget(panel);
  }

  private void updatePolyline() {
    if (viewPolygon != null) {
      map.removeOverlay(viewPolygon);
    }

    // Some simple math to calculate viewPolygon

    double yaw = currentPov.getYaw();
    double distanceFactor = Math.cos(Math.toRadians(currentPov.getPitch())) * 0.0015 + 0.0005;
    double zoomFactor = currentPov.getZoom() * 0.7 + 1;

    LatLng[] points = new LatLng[11];
    points[0] = points[10] = currentLatLng;
    for (int i = 1; i < 10; i++) {
      double angle = Math.toRadians(yaw + (i - 5) * 7d / zoomFactor);
      points[i] = LatLng.newInstance(currentLatLng.getLatitude()
          + Math.cos(angle) * distanceFactor, currentLatLng.getLongitude()
          + Math.sin(angle) * distanceFactor);
    }

    viewPolygon = new Polygon(points, "blue", 1, 0.5, "blue", 0.15);
    map.addOverlay(viewPolygon);
  }
}

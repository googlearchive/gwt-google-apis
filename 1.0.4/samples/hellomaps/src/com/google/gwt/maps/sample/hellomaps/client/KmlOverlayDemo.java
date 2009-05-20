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
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.GeoXmlLoadCallback;
import com.google.gwt.maps.client.overlay.GeoXmlOverlay;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

/**
 * The Google Maps API supports the KML and GeoRSS data formats for displaying
 * geographic information. These data formats are added to a map using the
 * GGeoXml object, whose factory method takes the URL of a publicly accessible
 * XML file. GGeoXml placemarks are rendered as markers, while GGeoXml polylines
 * and polygons are rendered as Google Maps API polylines and polygons.
 * &ltGroundOverlay&gt; elements within KML files are rendered as GGroundOverlay
 * elements on the map.
 * 
 * GGeoXml objects are added to a map using the addOverlay() method. (You can
 * remove them from the map using removeOverlay().) Both KML and GeoRSS XML
 * files are supported.
 */
public class KmlOverlayDemo extends MapsDemo {

  private static HTML descHTML = null;

  private static final String descString = "<p>Creates a map view of the "
      + "contents of a KML file</p>"
      + "<p>Displays some paths in a KML file hosted at Google.</p>\n"
      + "<p>Equivalent to the Maps JavaScript API Example: "
      + "<a href=\"http://code.google.com/apis/maps/documentation/examples/geoxml-kml.html\">"
      + "http://code.google.com/apis/maps/documentation/examples/geoxml-kml.html</a></p>\n";

  public static MapsDemoInfo init() {
    return new MapsDemoInfo() {
      @Override
      public MapsDemo createInstance() {
        return new KmlOverlayDemo();
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
        return "KML Overlays";
      }
    };
  }

  private GeoXmlOverlay geoXml = null;
  private boolean geoXmlShown;
  private final MapWidget map;
  private final Button removeAddButton;

  public KmlOverlayDemo() {
    Panel panel = new FlowPanel();
    map = new MapWidget(LatLng.newInstance(41.875696, -87.624207), 11);
    map.setSize("640px", "480px");
    map.setUIToDefault();
    panel.add(map);

    // Toggle the visibility of the overlays by
    // adding and removing the overlay.
    removeAddButton = new Button("Remove/Add KML");
    // Toggle the visibility of the overlays
    // using the show() and hide() methods
    final Button showHideButton = new Button("Show/Hide KML");
    removeAddButton.setEnabled(false);
    removeAddButton.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        if (geoXml == null) {
          return;
        }
        if (geoXmlShown) {
          map.removeOverlay(geoXml);
          showHideButton.setEnabled(false);
        } else {
          map.addOverlay(geoXml);
          showHideButton.setEnabled(true);
        }
        geoXmlShown = !geoXmlShown;
      }
    });
    panel.add(removeAddButton);

    showHideButton.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        if (geoXml == null) {
          return;
        }
        if (geoXml.isHidden()) {
          geoXml.setVisible(true);
        } else {
          geoXml.setVisible(false);
        }
      }
    });

    panel.add(showHideButton);
    initWidget(panel);

    GeoXmlOverlay.load("http://mapgadgets.googlepages.com/cta.kml",
        new GeoXmlLoadCallback() {

          @Override
          public void onFailure(String url, Throwable e) {
            StringBuffer message = new StringBuffer("KML File " + url
                + " failed to load");
            if (e != null) {
              message.append(e.toString());
            }
            Window.alert(message.toString());
          }

          @Override
          public void onSuccess(String url, GeoXmlOverlay overlay) {
            geoXml = overlay;
            removeAddButton.setEnabled(true);
            showHideButton.setEnabled(true);
            map.addOverlay(geoXml);
            GWT.log("KML File " + url + "loaded successfully", null);
            GWT.log("Default Center=" + geoXml.getDefaultCenter(), null);
            GWT.log("Default Span=" + geoXml.getDefaultSpan(), null);
            GWT.log("Default Bounds=" + geoXml.getDefaultBounds(), null);
            GWT.log("Supports hide=" + geoXml.supportsHide(), null);
          }
        });
  }

  @Override
  public void onShow() {
    map.clearOverlays();
    if (geoXml != null) {
      map.addOverlay(geoXml);
    }
    geoXmlShown = true;
  }
}

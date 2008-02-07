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
import com.google.gwt.maps.client.overlay.GeoXmlOverlay;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

/**
 * The Google Maps API supports the KML and GeoRSS data formats for displaying
 * geographic information. These data formats are added to a map using the
 * GGeoXml object, whose constructor takes the URL of a publicly accessible
 * XML file. GGeoXml placemarks are rendered as markers, while GGeoXml polylines
 * and polygons are rendered as Google Maps API polylines and polygons.
 * &lt;GroundOverlay&gt; elements within KML files are rendered as
 * GGroundOverlay elements on the map.
 * 
 * GeoXmlOverlay objects are added to a map using the
 * {@link MapWidget#addOverlay(com.google.gwt.maps.client.overlay.Overlay)}
 * method. (You can remove them from the map using
 * {@link MapWidget#removeOverlay(com.google.gwt.maps.client.overlay.Overlay)}.)Both
 * KML and GeoRSS XML files are supported.
 */
public class GeoRssOverlayDemo extends MapsDemo {

  private static HTML descHTML = null;

  private static final String descString = "<p>Creates a map view of the "
      + "contents of a Geo RSS file</p>"
      + "<p>Each entry in the RSS file is displayed as a marker.</p>\n"
      + "<p>Equivalent to the Maps JavaScript API Example: "
      + "<a href=\"http://code.google.com/apis/maps/documentation/examples/geoxml-rss.html\">"
      + "http://code.google.com/apis/maps/documentation/examples/geoxml-rss.html</a></p>\n";

  public static MapsDemoInfo init() {
    return new MapsDemoInfo() {
      @Override
      public MapsDemo createInstance() {
        return new GeoRssOverlayDemo();
      }
      
      @Override
      public HTML getDescriptionHTML() {
        if (descHTML == null)
          descHTML = new HTML(descString);
        return descHTML;
      }
      
      @Override
      public String getName() {
        return "GeoRSS Overlays";
      }
    };
  }
  private MapWidget map;

  private GeoXmlOverlay geoXml;

  private boolean geoXmlShown;

  public GeoRssOverlayDemo() {
    Panel panel = new FlowPanel();

    map = new MapWidget(new LatLng(49.496675, -102.65625), 3);
    map.setSize("640px", "480px");
    panel.add(map);
    Button toggleButton = new Button("Toggle Markers");
    toggleButton.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        if (geoXmlShown) {
          map.removeOverlay(geoXml);
        } else {
          map.addOverlay(geoXml);
        }
        geoXmlShown = !geoXmlShown;
      }
    });
    panel.add(toggleButton);
    initWidget(panel);

    map.addControl(new LargeMapControl());
    geoXml = new GeoXmlOverlay(
        "http://api.flickr.com/services/feeds/groups_pool.gne?id=322338@N20&format=rss_200&georss=1");
  }

  @Override
  public void onShow() {
    map.clearOverlays();
    map.addOverlay(geoXml);
    geoXmlShown = true;
  }
}

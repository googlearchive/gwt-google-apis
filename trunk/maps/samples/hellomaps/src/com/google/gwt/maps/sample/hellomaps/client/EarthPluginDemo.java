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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.MapType;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.event.EarthInstanceHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Panel;

/**
 * Demo the Google Earth Plugin.
 */
public class EarthPluginDemo extends MapsDemo {

  private static HTML descHTML = null;

  private static final String descString = "<p>"
      + "Creates a 500 x 500 pixel map viewport centered on the Googleplex in Mountain View, CA USA. "
      + "Attempts to invoke the Google Earth plugin (not available on all browsers.)</p>";

  public static MapsDemoInfo init() {
    return new MapsDemoInfo() {
      @Override
      public MapsDemo createInstance() {
        return new EarthPluginDemo();
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
        return "Earth Plugin";
      }
    };
  }

  private MapWidget map;

  public EarthPluginDemo() {
    Panel panel = new FlowPanel();
    map = new MapWidget(LatLng.newInstance(37.42317, -122.08364), 16);
    map.setSize("500px", "500px");
    map.setUIToDefault();
    map.addMapType(MapType.getEarthMap());
    map.setCurrentMapType(MapType.getEarthMap());
    panel.add(map);
    initWidget(panel);
    map.getEarthInstance(new EarthInstanceHandler() {

      public void onEarthInstance(EarthInstanceEvent event) {
        final JavaScriptObject earth = event.getEarthInstance();
        if (earth == null) {
          Window.alert("Failed to init earth plugin");
        } else {
          /*
           * Create a marker. The timer is set to give the earth plugin a chance
           * to position to the proper point on the map.
           */
          new Timer() {

            @Override
            public void run() {
              createPlacemark(earth);
            }
          }.schedule(1000);
        }
      }
    });
  }

  /**
   * Part of a sample ripped out of the Google Earth documentation. See {@link
   * "http://code.google.com/apis/earth/documentation/introduction.html"}.
   * 
   * @param ge The Google Earth Instance
   */
  private native void createPlacemark(JavaScriptObject ge) /*-{
    var placemark = ge.createPlacemark('');
    placemark.setName("You are at Google");
    ge.getFeatures().appendChild(placemark);

    // Create style map for placemark
    var normal = ge.createIcon('');
    normal.setHref('http://maps.google.com/mapfiles/kml/paddle/red-circle.png');
    var iconNormal = ge.createStyle('');
    iconNormal.getIconStyle().setIcon(normal);
    var highlight = ge.createIcon('');
    highlight.setHref('http://maps.google.com/mapfiles/kml/paddle/red-circle.png');
    var iconHighlight = ge.createStyle('');
    iconHighlight.getIconStyle().setIcon(highlight);
    var styleMap = ge.createStyleMap('');
    styleMap.setNormalStyle(iconNormal);
    styleMap.setHighlightStyle(iconHighlight);
    placemark.setStyleSelector(styleMap);

    // Create point     
    var la = ge.getView().copyAsLookAt(ge.ALTITUDE_RELATIVE_TO_GROUND);
    var point = ge.createPoint('');
    point.setLatitude(la.getLatitude());
    point.setLongitude(la.getLongitude());
    placemark.setGeometry(point);
  }-*/;
}

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
package com.google.gwt.maps.client.impl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.geocode.DirectionQueryOptions;
import com.google.gwt.maps.client.geocode.Distance;
import com.google.gwt.maps.client.geocode.Duration;
import com.google.gwt.maps.client.geocode.Placemark;
import com.google.gwt.maps.client.geocode.Route;
import com.google.gwt.maps.client.geom.LatLngBounds;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.Polyline;
import com.google.gwt.maps.jsio.client.Constructor;
import com.google.gwt.maps.jsio.client.JSFlyweightWrapper;
import com.google.gwt.maps.jsio.client.JSList;
import com.google.gwt.user.client.Element;

/**
 * Wraps the GDirections object in the Maps API using JSIO.
 */
public interface DirectionsImpl extends JSFlyweightWrapper {

  /**
   * Return object from getStatus().
   */
  public static class Status extends JavaScriptObject {
    protected Status() {
    }

    public final native int getCode() /*-{
      return this.code;
    }-*/;
  }

  DirectionsImpl impl = GWT.create(DirectionsImpl.class);

  void clear(JavaScriptObject jsoPeer);
  
  @Constructor("$wnd.GDirections")
  JavaScriptObject construct(MapWidget map, Element panel);

  LatLngBounds getBounds(JavaScriptObject jsoPeer);

  String getCopyrightsHtml(JavaScriptObject jsoPeer);

  Distance getDistance(JavaScriptObject jsoPeer);

  Duration getDuration(JavaScriptObject jsoPeer);

  Placemark getGeocode(JavaScriptObject jsoPeer, int i);

  Marker getMarker(JavaScriptObject jsoPeer, int i);

  int getNumGeocodes(JavaScriptObject jsoPeer);

  int getNumRoutes(JavaScriptObject jsoPeer);

  Polyline getPolyline(JavaScriptObject jsoPeer);

  Route getRoute(JavaScriptObject jsoPeer, int i);

  Status getStatus(JavaScriptObject jsoPeer);

  String getSummaryHtml(JavaScriptObject jsoPeer);

  void load(JavaScriptObject jsoPeer, String query,
      DirectionQueryOptions options);

  void loadFromWaypoints(JavaScriptObject jsoPeer, JSList<String> waypoints,
      DirectionQueryOptions options);

}

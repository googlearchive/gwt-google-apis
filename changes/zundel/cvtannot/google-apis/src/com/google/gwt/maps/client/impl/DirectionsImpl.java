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
import com.google.gwt.jsio.client.JSFlyweightWrapper;
import com.google.gwt.jsio.client.JSList;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.geocode.DirectionQueryOptions;
import com.google.gwt.maps.client.geocode.Distance;
import com.google.gwt.maps.client.geocode.Duration;
import com.google.gwt.maps.client.geocode.Placemark;
import com.google.gwt.maps.client.geocode.Route;
import com.google.gwt.maps.client.geocode.Waypoint;
import com.google.gwt.maps.client.geom.LatLngBounds;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.Polyline;
import com.google.gwt.jsio.client.BeanProperties;
import com.google.gwt.jsio.client.Constructor;
import com.google.gwt.jsio.client.FieldName;
import com.google.gwt.user.client.Element;

/**
 * 
 */
public interface DirectionsImpl extends JSFlyweightWrapper {

  public static DirectionsImpl impl = (DirectionsImpl) GWT.create(DirectionsImpl.class);

  @Constructor("$wnd.GDirections")
  public JavaScriptObject construct(MapWidget map, Element panel);

  public LatLngBounds getBounds(JavaScriptObject jsoPeer);

  public String getCopyrightsHtml(JavaScriptObject jsoPeer);

  public Distance getDistance(JavaScriptObject jsoPeer);

  public Duration getDuration(JavaScriptObject jsoPeer);

  public Placemark getGeocode(JavaScriptObject jsoPeer, int i);

  public Marker getMarker(JavaScriptObject jsoPeer, int i);

  public int getNumGeocodes(JavaScriptObject jsoPeer);

  public int getNumRoutes(JavaScriptObject jsoPeer);

  public Polyline getPolyline(JavaScriptObject jsoPeer);

  public Route getRoute(JavaScriptObject jsoPeer, int i);

  @BeanProperties
  @FieldName("getStatus().code")
  public int getStatusCode(JavaScriptObject jsoPeer);

  public String getSummaryHtml(JavaScriptObject jsoPeer);

  public void load(JavaScriptObject jsoPeer, String query,
      DirectionQueryOptions options);

  public void loadFromWaypoints(JavaScriptObject jsoPeer,
      JSList<Waypoint> waypoints, DirectionQueryOptions options);

}

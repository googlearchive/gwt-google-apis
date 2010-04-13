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
import com.google.gwt.maps.client.MapType;
import com.google.gwt.maps.client.MapTypeOptions;
import com.google.gwt.maps.client.TileLayer;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.LatLngBounds;
import com.google.gwt.maps.client.geom.Projection;
import com.google.gwt.maps.client.geom.Size;
import com.google.gwt.maps.jsio.client.Binding;
import com.google.gwt.maps.jsio.client.Constructor;
import com.google.gwt.maps.jsio.client.Global;
import com.google.gwt.maps.jsio.client.JSFlyweightWrapper;
import com.google.gwt.maps.jsio.client.JSList;

/**
 * A Flyweight style wrapper for the Maps JavaScript MapType class.
 */
public interface MapTypeImpl extends JSFlyweightWrapper {
  MapTypeImpl impl = GWT.create(MapTypeImpl.class);

  @Binding
  void bind(JavaScriptObject jsoPeer, MapType mapType);

  @Constructor("$wnd.GMapType")
  JavaScriptObject construct(JSList<? extends TileLayer> layers,
      Projection projection, String name);

  @Constructor("$wnd.GMapType")
  JavaScriptObject construct(JSList<? extends TileLayer> layers,
      Projection projection, String name, MapTypeOptions opts);

  @Global("$wnd.G_AERIAL_HYBRID_MAP")
  JavaScriptObject getAerialHybridMap();

  @Global("$wnd.G_AERIAL_MAP")
  JavaScriptObject getAerialMap();

  String getAlt(JavaScriptObject jsoPeer);

  int getBoundsZoomLevel(JavaScriptObject jsoPeer, LatLngBounds bounds,
      Size viewSize);

  JSList<String> getCopyrights(JavaScriptObject jsoPeer, LatLngBounds bounds,
      int zoomLevel);

  @Global("$wnd.G_DEFAULT_MAP_TYPES")
  JavaScriptObject getDefaultMapTypes();

  @Global("$wnd.G_SATELLITE_3D_MAP")
  JavaScriptObject getEarthMapType();

  String getErrorMessage(JavaScriptObject jsoPeer);

  @Global("$wnd.G_HYBRID_MAP")
  JavaScriptObject getHybridMapType();

  String getLinkColor(JavaScriptObject jsoPeer);

  @Global("$wnd.G_MAPMAKER_HYBRID_MAP")
  JavaScriptObject getMapmakerHybridMap();

  @Global("$wnd.G_MAPMAKER_NORMAL_MAP")
  JavaScriptObject getMapmakerNormalMap();

  @Global("$wnd.G_MAPMAKER_MAP_TYPES")
  JavaScriptObject getMapmakerMapTypes();

  @Global("$wnd.G_MARS_ELEVATION_MAP")
  JavaScriptObject getMarsElevationMap();

  @Global("$wnd.G_MARS_INFRARED_MAP")
  JavaScriptObject getMarsInfraredMap();

  @Global("$wnd.G_MARS_VISIBLE_MAP")
  JavaScriptObject getMarsVisibleMap();

  int getMaximumResolution(JavaScriptObject jsoPeer);

  int getMinimumResolution(JavaScriptObject jsoPeer);

  @Global("$wnd.G_MOON_ELEVATION_MAP")
  JavaScriptObject getMoonElevationMapType();

  @Global("$wnd.G_MOON_MAP_TYPES")
  JavaScriptObject getMoonMapTypes();

  @Global("$wnd.G_MOON_VISIBLE_MAP")
  JavaScriptObject getMoonVisibleMap();

  String getName(JavaScriptObject jsoPeer, boolean shortName);

  @Global("$wnd.G_NORMAL_MAP")
  JavaScriptObject getNormalMapType();

  @Global("$wnd.G_PHYSICAL_MAP")
  JavaScriptObject getPhysicalMapType();

  Projection getProjection(JavaScriptObject jsoPeer);

  @Global("$wnd.G_SATELLITE_MAP")
  JavaScriptObject getSatelliteMapType();

  @Global("$wnd.G_SKY_MAP_TYPES")
  JavaScriptObject getSkyMapTypes();

  @Global("$wnd.G_SKY_VISIBLE_MAP")
  JavaScriptObject getSkyVisibleMap();

  int getSpanZoomLevel(JavaScriptObject jsoPeer, LatLng center, LatLng span,
      Size viewSize);

  String getTextColor(JavaScriptObject jsoPeer);

  JSList<TileLayer> getTileLayers(JavaScriptObject jsoPeer);

  int getTileSize(JavaScriptObject jsoPeer);

  String getUrlArg(JavaScriptObject jsoPeer);

}

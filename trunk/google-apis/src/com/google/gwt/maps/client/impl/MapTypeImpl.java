/*
 * Copyright 2007 Google Inc.
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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.jsio.client.JSFlyweightWrapper;
import com.google.gwt.jsio.client.JSList;
import com.google.gwt.maps.client.MapType;
import com.google.gwt.maps.client.MapTypeOptions;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.LatLngBounds;
import com.google.gwt.maps.client.geom.Projection;
import com.google.gwt.maps.client.geom.Size;

/**
 * @gwt.beanProperties
 */
public interface MapTypeImpl extends JSFlyweightWrapper {

  /**
   * @gwt.binding
   */
  public void bind(JavaScriptObject jsoPeer, MapType mapType);

  // /**
  // * @gwt.constructor $wnd.GMapType
  // */
  // public JavaScriptObject construct();

  /**
   * @gwt.constructor $wnd.GMapType
   */
  public JavaScriptObject construct(JSList/* TileLayer[] */layers,
      Projection projection, String name);

  /**
   * @gwt.constructor $wnd.GMapType
   */
  public JavaScriptObject construct(JSList/* TileLayer[] */layers,
      Projection projection, String name, MapTypeOptions opts);

  /**
   * @gwt.global $wnd.G_NORMAL_MAP
   */
  public JavaScriptObject getNormalMapType();

  /**
   * @gwt.global $wnd.G_SATELLITE_MAP
   */
  public JavaScriptObject getSatelliteMapType();

  /**
   * @gwt.global $wnd.G_HYBRID_MAP
   */
  public JavaScriptObject getHybridMapType();

  public int getSpanZoomLevel(JavaScriptObject jsoPeer, LatLng center,
      LatLng span, Size viewSize);

  public int getBoundsZoomLevel(JavaScriptObject jsoPeer, LatLngBounds bounds,
      Size viewSize);

  public String getName(JavaScriptObject jsoPeer, boolean shortName);

  public Projection getProjection(JavaScriptObject jsoPeer);

  public int getTileSize(JavaScriptObject jsoPeer);

  /**
   * @gwt.typeArgs <com.google.gwt.maps.client.TileLayer>
   */
  public JSList getTileLayers(JavaScriptObject jsoPeer);

  public int getMinimumResolution(JavaScriptObject jsoPeer, LatLng latlng);

  public int getMaximumResolution(JavaScriptObject jsoPeer, LatLng latlng);

  public String getTextColor(JavaScriptObject jsoPeer);

  public String getLinkColor(JavaScriptObject jsoPeer);

  public String getErrorMessage(JavaScriptObject jsoPeer);

  /**
   * @gwt.typeArgs <java.lang.String>
   */
  public JSList getCopyrights(JavaScriptObject jsoPeer, LatLngBounds bounds,
      int zoomLevel);

  public String getUrlArg(JavaScriptObject jsoPeer);
}

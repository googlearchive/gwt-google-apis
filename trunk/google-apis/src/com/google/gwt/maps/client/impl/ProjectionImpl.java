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
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.MercatorProjection;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.client.geom.Projection;

/**
 * 
 */
public interface ProjectionImpl extends JSFlyweightWrapper {

  ProjectionImpl impl = (ProjectionImpl) GWT.create(ProjectionImpl.class);

  /**
   * @gwt.binding
   */
  void bind(JavaScriptObject jsoPeer, MercatorProjection projection);

  /**
   * @gwt.binding
   */
  void bind(JavaScriptObject jsoPeer, Projection projection);

  /**
   * @gwt.constructor $wnd.GProjection
   */
  JavaScriptObject construct();

  /**
   * @gwt.constructor $wnd.GMercatorProjection
   */
  JavaScriptObject constructMercatorProjection(int zoomLevels);

  Point fromLatLngToPixel(JavaScriptObject jsoPeer, LatLng latlng,
      int zoomLevel);

  LatLng fromPixelToLatLng(JavaScriptObject jsoPeer, Point point,
      int zoomLevel, boolean unbounded);

  int getWrapWidth(JavaScriptObject jsoPeer, int zoomLevel);

  boolean tileCheckRange(JavaScriptObject jsoPeer, Point point,
      int zoomLevel, int tileSize);

}

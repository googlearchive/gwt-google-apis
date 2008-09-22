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
import com.google.gwt.core.client.JsArray;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.LatLngBounds;
import com.google.gwt.maps.client.overlay.PolyEditingOptions;
import com.google.gwt.maps.client.overlay.PolyStyleOptions;
import com.google.gwt.maps.client.overlay.PolygonOptions;
import com.google.gwt.maps.jsio.client.Constructor;
import com.google.gwt.maps.jsio.client.JSFlyweightWrapper;

/**
 * Wraps the GPolygon class from the Maps API using JSIO.
 */
public interface PolygonImpl extends JSFlyweightWrapper {

  PolygonImpl impl = GWT.create(PolygonImpl.class);

  @Constructor("$wnd.GPolygon")
  JavaScriptObject construct(JsArray<LatLng> points);

  @Constructor("$wnd.GPolygon")
  JavaScriptObject construct(JsArray<LatLng> points, String strokeColor,
      int strokeWeight, double strokeOpacity, String fillColor,
      double fillOpacity);

  @Constructor("$wnd.GPolygon")
  JavaScriptObject construct(JsArray<LatLng> points, String strokeColor,
      int strokeWeight, double strokeOpacity, String fillColor,
      double fillOpacity, PolygonOptions options);

  void deleteVertex(JavaScriptObject jsoPeer, int index);
  
  void disableEditing(JavaScriptObject jsoPeer);
  
  void enableDrawing(JavaScriptObject jsoPeer);
  
  void enableDrawing(JavaScriptObject jsoPeer, PolyEditingOptions options);
  
  void enableEditing(JavaScriptObject jsoPeer);
  
  void enableEditing(JavaScriptObject jsoPeer, PolyEditingOptions options);
  
  double getArea(JavaScriptObject jsoPeer);
  
  LatLngBounds getBounds(JavaScriptObject jsoPeer);
  
  LatLng getVertex(JavaScriptObject jsoPeer, int index);

  int getVertexCount(JavaScriptObject jsoPeer);

  void hide(JavaScriptObject jsoPeer);

  void insertVertex(JavaScriptObject jsoPeer, int index, LatLng latlng);
  
  boolean isHidden(JavaScriptObject jsoPeer);

  void setFillStyle(JavaScriptObject jsoPeer, PolyStyleOptions style);
  
  void setStrokeStyle(JavaScriptObject jsoPeer, PolyStyleOptions style);
  
  void show(JavaScriptObject jsoPeer);

  boolean supportsHide(JavaScriptObject jsoPeer);
}

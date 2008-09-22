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
import com.google.gwt.maps.client.overlay.PolylineOptions;
import com.google.gwt.maps.jsio.client.Constructor;
import com.google.gwt.maps.jsio.client.JSFlyweightWrapper;

/**
 * Implementation of the GPolyline class using JSIO.
 */
public interface PolylineImpl extends JSFlyweightWrapper {

  PolylineImpl impl = GWT.create(PolylineImpl.class);

  @Constructor("$wnd.GPolyline")
  JavaScriptObject construct(JsArray<LatLng> points);

  @Constructor("$wnd.GPolyline")
  JavaScriptObject construct(JsArray<LatLng> points, String color);

  @Constructor("$wnd.GPolyline")
  JavaScriptObject construct(JsArray<LatLng> points, String color, int weight);

  @Constructor("$wnd.GPolyline")
  JavaScriptObject construct(JsArray<LatLng> points, String color, int weight,
      double opacity);

  @Constructor("$wnd.GPolyline")
  JavaScriptObject construct(JsArray<LatLng> points, String color, int weight,
      double opacity, PolylineOptions options);
  
  void deleteVertex(JavaScriptObject jsoPeer, int index);

  void disableEditing(JavaScriptObject jsoPeer);

  void enableDrawing(JavaScriptObject jsoPeer);

  void enableDrawing(JavaScriptObject jsoPeer, PolyEditingOptions opts);
  
  void enableEditing(JavaScriptObject jsoPeer);
  
  void enableEditing(JavaScriptObject jsoPeer, PolyEditingOptions opts);

  LatLngBounds getBounds(JavaScriptObject jsoPeer);

  double getLength(JavaScriptObject jsoPeer);

  LatLng getVertex(JavaScriptObject jsoPeer, int index);

  int getVertexCount(JavaScriptObject jsoPeer);

  void hide(JavaScriptObject jsoPeer);

  void insertVertex(JavaScriptObject jsoPeer, int index, LatLng latlng);

  boolean isHidden(JavaScriptObject jsoPeer);

  void setStrokeStyle(JavaScriptObject jsoPeer, PolyStyleOptions style);

  void show(JavaScriptObject jsoPeer);

  boolean supportsHide(JavaScriptObject jsoPeer);

}
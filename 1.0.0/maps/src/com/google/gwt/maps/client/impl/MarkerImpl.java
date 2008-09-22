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
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Icon;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.maps.jsio.client.Constructor;
import com.google.gwt.maps.jsio.client.JSFlyweightWrapper;

/**
 * Wrapper for the GMarker object in the Maps API using JSIO.
 */
public interface MarkerImpl extends JSFlyweightWrapper {

  MarkerImpl impl = GWT.create(MarkerImpl.class);

  void closeInfoWindow(Marker marker);

  @Constructor("$wnd.GMarker")
  JavaScriptObject construct(LatLng point);

  @Constructor("$wnd.GMarker")
  JavaScriptObject construct(LatLng point, MarkerOptions options);

  void disableDragging(Marker marker);

  boolean draggable(Marker marker);

  boolean draggingEnabled(Marker marker);

  void enableDragging(Marker marker);

  Icon getIcon(Marker marker);

  LatLng getLatLng(Marker marker);

  @Deprecated
  LatLng getPoint(Marker marker);

  String getTitle(Marker marker);

  void hide(Marker marker);

  boolean isHidden(Marker marker);

  void openInfoWindow(Marker marker, JavaScriptObject content,
      JavaScriptObject options);

  void openInfoWindowTabs(Marker marker, JavaScriptObject content,
      JavaScriptObject options);

  void setImage(Marker marker, String url);

  void setLatLng(Marker marker, LatLng point);

  @Deprecated
  void setPoint(Marker marker, LatLng point);

  void show(Marker marker);

  void showMapBlowup(Marker marker);

  void showMapBlowup(Marker marker, JavaScriptObject options);
}

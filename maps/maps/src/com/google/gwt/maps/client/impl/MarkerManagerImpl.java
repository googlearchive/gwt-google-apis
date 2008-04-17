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
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerManagerOptions;
import com.google.gwt.maps.jsio.client.Constructor;
import com.google.gwt.maps.jsio.client.JSFlyweightWrapper;
import com.google.gwt.maps.jsio.client.JSList;

/**
 * Wrapper for the GMarkerManager object from the Maps API using JSIO.
 */
public interface MarkerManagerImpl extends JSFlyweightWrapper {

  MarkerManagerImpl impl = GWT.create(MarkerManagerImpl.class);

  void addMarker(JavaScriptObject jsoPeer, Marker marker, int minZoom);

  void addMarker(JavaScriptObject jsoPeer, Marker marker, int minZoom,
      int maxZoom);

  void addMarkers(JavaScriptObject jsoPeer,
      JSList<Marker> markers, int minZoom);

  void addMarkers(JavaScriptObject jsoPeer,
      JSList<Marker> markers, int minZoom, int maxZoom);

  @Constructor("$wnd.GMarkerManager")
  JavaScriptObject construct(MapWidget map);

  @Constructor("$wnd.GMarkerManager")
  JavaScriptObject construct(MapWidget map, MarkerManagerOptions options);

  int getMarkerCount(JavaScriptObject jsoPeer, int zoomLevel);

  void refresh(JavaScriptObject jsoPeer);
}

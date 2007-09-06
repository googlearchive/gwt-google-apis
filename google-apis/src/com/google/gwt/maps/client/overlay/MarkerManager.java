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
package com.google.gwt.maps.client.overlay;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.impl.MarkerManagerImpl;
import com.google.gwt.maps.client.util.JsUtil;

public final class MarkerManager {

  private final JavaScriptObject jsoPeer;

  public MarkerManager(MapWidget map) {
    jsoPeer = MarkerManagerImpl.impl.construct(map);
  }

  public MarkerManager(MapWidget map, MarkerManagerOptions options) {
    jsoPeer = MarkerManagerImpl.impl.construct(map, options);
  }

  public void addMarkers(Marker[] markers, int minZoom, int maxZoom) {
    MarkerManagerImpl.impl.addMarkers(jsoPeer, JsUtil.toJsList(markers),
        minZoom, maxZoom);
  }

  public void addMarkers(Marker[] markers, int minZoom) {
    MarkerManagerImpl.impl.addMarkers(jsoPeer, JsUtil.toJsList(markers),
        minZoom);
  }

  public void addMarker(Marker marker, int minZoom, int maxZoom) {
    MarkerManagerImpl.impl.addMarker(jsoPeer, marker, minZoom, maxZoom);
  }

  public void addMarker(Marker marker, int minZoom) {
    MarkerManagerImpl.impl.addMarker(jsoPeer, marker, minZoom);
  }

  public void refresh() {
    MarkerManagerImpl.impl.refresh(jsoPeer);
  }

  public int getMarkerCount(int zoomLevel) {
    return MarkerManagerImpl.impl.getMarkerCount(jsoPeer, zoomLevel);
  }
}

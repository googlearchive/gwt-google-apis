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
import com.google.gwt.maps.client.event.MarkerManagerChangedHandler;
import com.google.gwt.maps.client.event.MarkerManagerChangedHandler.MarkerManagerChangedEvent;
import com.google.gwt.maps.client.geom.LatLngBounds;
import com.google.gwt.maps.client.impl.HandlerCollection;
import com.google.gwt.maps.client.impl.JsUtil;
import com.google.gwt.maps.client.impl.MapEvent;
import com.google.gwt.maps.client.impl.MarkerManagerImpl;
import com.google.gwt.maps.client.impl.EventImpl.LatLngBoundsIntCallback;

/**
 * 
 */
public final class MarkerManager {

  private final JavaScriptObject jsoPeer;
  private HandlerCollection<MarkerManagerChangedHandler> markerManagerChangedHandlers;

  public MarkerManager(MapWidget map) {
    jsoPeer = MarkerManagerImpl.impl.construct(map);
  }

  public MarkerManager(MapWidget map, MarkerManagerOptions options) {
    jsoPeer = MarkerManagerImpl.impl.construct(map, options);
  }

  public void addMarker(Marker marker, int minZoom) {
    MarkerManagerImpl.impl.addMarker(jsoPeer, marker, minZoom);
  }

  public void addMarker(Marker marker, int minZoom, int maxZoom) {
    MarkerManagerImpl.impl.addMarker(jsoPeer, marker, minZoom, maxZoom);
  }

  /**
   * This event is fired when markers managed by a manager have been added to or
   * removed from the map. The event handler function should be prepared to
   * accept two arguments. The first one is the rectangle defining the bounds of
   * the visible grid. The second one carries the number of markers currently
   * shown on the map.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMarkerManagerChangedHandler(
      final MarkerManagerChangedHandler handler) {
    maybeInitMarkerManagerChangedHandlers();

    markerManagerChangedHandlers.addHandler(handler,
        new LatLngBoundsIntCallback() {
          @Override
          public void callback(LatLngBounds bounds, int markerCount) {
            MarkerManagerChangedEvent e = new MarkerManagerChangedEvent(
                MarkerManager.this, bounds, markerCount);
            handler.onChanged(e);
          }
        });
  }

  public void addMarkers(Marker[] markers, int minZoom) {
    MarkerManagerImpl.impl.addMarkers(jsoPeer, JsUtil.toJsList(markers),
        minZoom);
  }

  public void addMarkers(Marker[] markers, int minZoom, int maxZoom) {
    MarkerManagerImpl.impl.addMarkers(jsoPeer, JsUtil.toJsList(markers),
        minZoom, maxZoom);
  }

  public int getMarkerCount(int zoomLevel) {
    return MarkerManagerImpl.impl.getMarkerCount(jsoPeer, zoomLevel);
  }

  public void refresh() {
    MarkerManagerImpl.impl.refresh(jsoPeer);
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link MarkerManager#addMarkerManagerChangedHandler(MarkerManagerChangedHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeMarkerManagerHandler(MarkerManagerChangedHandler handler) {
    if (markerManagerChangedHandlers != null) {
      markerManagerChangedHandlers.removeHandler(handler);
    }
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(MarkerManagerChangedEvent event) {
    maybeInitMarkerManagerChangedHandlers();
    markerManagerChangedHandlers.trigger(event.getBounds(),
        event.getMarkerCount());
  }

  private void maybeInitMarkerManagerChangedHandlers() {
    if (markerManagerChangedHandlers == null) {
      markerManagerChangedHandlers = new HandlerCollection<MarkerManagerChangedHandler>(
          jsoPeer, MapEvent.CHANGED);
    }
  }

}

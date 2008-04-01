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
package com.google.gwt.maps.client.event;

import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.geom.LatLng;

import java.util.EventObject;

/**
 * Provides an interface to implement in order to receive
 * MapEvent.MOUSEOUTevents from the {@link MapWidget}.
 */
public interface MapMouseMoveHandler {

  /**
   * Encapsulates the arguments for the MapEvent.MOUSEMOVE event on a
   * {@link MapWidget}.
   */
  @SuppressWarnings("serial")
  class MapMouseMoveEvent extends EventObject {

    private final LatLng latlng;

    public MapMouseMoveEvent(MapWidget source, LatLng latlng) {
      super(source);
      this.latlng = latlng;
    }

    /**
     * Returns the map coordinates for this event.
     * 
     * @return the map coordinates for this event.
     */
    public LatLng getLatLng() {
      return latlng;
    }

    /**
     * Returns the instance of the map that generated this event.
     * 
     * @return the instance of the map that generated this event.
     */
    public MapWidget getSender() {
      return (MapWidget) getSource();
    }
  }

  /**
   * Method to be invoked when a MapEvent.MOUSEOUT event fires on a
   * {@link MapWidget}.
   * 
   * @param event contains the properties of the event.
   */
  void onMouseMove(MapMouseMoveEvent event);
}

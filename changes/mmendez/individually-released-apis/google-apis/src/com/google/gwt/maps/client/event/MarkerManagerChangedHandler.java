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

import com.google.gwt.maps.client.geom.LatLngBounds;
import com.google.gwt.maps.client.overlay.MarkerManager;

import java.util.EventObject;

/**
 * Provides an interface to implement in order to receive MapEvent.CHANGED events
 * from the {@link MarkerManager}.
 */
public interface MarkerManagerChangedHandler {

  /**
   * Encapsulates the arguments for the MapEvent.CHANGED event on a
   * {@link MarkerManager}.
   */
  @SuppressWarnings("serial")
  class MarkerManagerChangedEvent extends EventObject {

    final LatLngBounds bounds;
    final int markerCount;

    public MarkerManagerChangedEvent(MarkerManager source, LatLngBounds bounds,
        int markerCount) {
      super(source);
      this.bounds = bounds;
      this.markerCount = markerCount;
    }

    /**
     * Returns the bounding rectangle of coordinates associated with this event.
     * 
     * @return the bounding rectangle coordinates associated with this event.
     */
    public LatLngBounds getBounds() {
      return bounds;
    }

    /**
     * Returns the number of markers currently shown on the map.
     * 
     * @return the number of markers currently shown on the map.
     */
    public int getMarkerCount() {
      return markerCount;
    }

    /**
     * Returns the instance of the marker manager that generated this event.
     * 
     * @return the instance of the marker manager that generated this event.
     */
    public MarkerManager getSender() {
      return (MarkerManager) getSource();
    }
  }

  /**
   * Method to be invoked when a MapEvent.CHANGED event fires on a
   * {@link MarkerManager}.
   * 
   * @param event contains the properties of the event.
   */
  void onChanged(MarkerManagerChangedEvent event);
}

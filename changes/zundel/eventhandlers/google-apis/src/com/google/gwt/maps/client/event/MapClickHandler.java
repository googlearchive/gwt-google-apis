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
import com.google.gwt.maps.client.overlay.Overlay;

import java.util.EventObject;

/**
 * Provides an interface to implement in order to receive click events from the
 * {@link MapWidget}. Note that this event fired twice when the user intends to 
 * send a double click.
 */
public interface MapClickHandler {

  /**
   * Encapsulates the arguments for the "click" event on a {@link MapWidget}.
   */
  class MapClickEvent extends EventObject {
    private final Overlay overlay;
    private final LatLng point;

    public MapClickEvent(MapWidget source, Overlay overlay, LatLng point) {
      super(source);
      this.overlay = overlay;
      this.point = point;
    }

    /**
     * If the click was on a marker, then the marker is passed to the event
     * handler in the overlay argument, and a click event is also fired on the
     * marker.
     * 
     * @return an overlay instance if the click is on a marker, otherwise
     *         <code>null</code>.
     */
    public Overlay getOverlay() {
      return overlay;
    }

    /**
     * If the click is not on a marker, the geographical coordinates of the
     * point that was clicked are passed in the point argument.
     * 
     * @return a point coordinate if the click was not over a marker, otherwise
     *         <code>null</code>.
     */
    public LatLng getPoint() {
      return point;
    }
    
    /**
     * Returns the instance of the map that generated this event.
     * @return the instance of the map that generated this event.
     */
    public MapWidget getSender() {
      return (MapWidget) getSource();
    }
  }

  /**
   * Method to be invoked when a "click" event fires on a {@link MapWidget}.
   * 
   * @param event contains the properties of the event.
   */
  void onClick(MapClickEvent event);
}

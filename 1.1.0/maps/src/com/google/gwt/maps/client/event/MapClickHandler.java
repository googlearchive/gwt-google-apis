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
 * {@link MapWidget}.
 * 
 * This event is fired when the user clicks on the map with the mouse. A click
 * event passes different arguments based on the context of the click, and
 * whether or not the click occured on a clickable overlay. If the click does
 * not occur on a clickable overlay, the overlay argument is null and the latlng
 * argument contains the geographical coordinates of the point that was clicked.
 * If the user clicks on an overlay that is clickable (such as a GMarker,
 * GPolygon, GPolyline, or GInfoWindow), the overlay argument contains the
 * overlay object. In addition, a click event is then also fired on the overlay
 * itself.
 * 
 * Also note that this event fired twice when the user intends to send a double
 * click. The MapDoubleClickHandler will also be fired in this case.
 */
public interface MapClickHandler {

  /**
   * Encapsulates the arguments for the "click" event on a {@link MapWidget}.
   */
  @SuppressWarnings("serial")
  class MapClickEvent extends EventObject {
    private final LatLng latlng;
    private final Overlay overlay;
    private final LatLng overlaylatlng;

    public MapClickEvent(MapWidget source, Overlay overlay, LatLng latlng,
        LatLng overlaylatlng) {
      super(source);
      this.overlay = overlay;
      this.latlng = latlng;
      this.overlaylatlng = overlaylatlng;
    }

    /**
     * If the click is not on a marker, the geographical coordinates of the
     * point that was clicked are passed in the point argument.
     * 
     * @return a point coordinate if the click was not over a marker, otherwise
     *         <code>null</code>.
     */
    public LatLng getLatLng() {
      return latlng;
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
     * If the user clicks on an overlay that is clickable, returns the
     * coordinates of the clicked overlay.
     * 
     * @return the coordinates of the clicked overlay if the click was over an
     *         overlay, otherwise <code>null</code>.
     */
    public LatLng getOverlayLatLng() {
      return overlaylatlng;
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
   * Method to be invoked when a "click" event fires on a {@link MapWidget}.
   * 
   * @param event contains the properties of the event.
   */
  void onClick(MapClickEvent event);
}

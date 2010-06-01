/*
 * Copyright 2010 Google Inc.
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

import com.google.gwt.maps.client.impl.MapEvent;
import com.google.gwt.maps.client.streetview.StreetviewPanoramaWidget;

import java.util.EventObject;

/**
 * Provides an interface to implement in order to receive
 * {@link MapEvent#ZOOMCHANGED} events from the {@link StreetviewPanoramaWidget}
 * .
 */
public interface StreetviewZoomChangedHandler {

  /**
   * Encapsulates the arguments for the {@link MapEvent#ZOOMCHANGED} event on a
   * {@link StreetviewPanoramaWidget}.
   */
  @SuppressWarnings("serial")
  class StreetviewZoomChangedEvent extends EventObject {
    private final double zoom;

    public StreetviewZoomChangedEvent(StreetviewPanoramaWidget source,
        double zoom) {
      super(source);
      this.zoom = zoom;
    }

    /**
     * Returns the new zoom of the Street View point of view.
     *
     * @return the new zoom of the Street View point of view.
     */
    public double getZoom() {
      return zoom;
    }

    /**
     * Returns the instance of the {@link StreetviewPanoramaWidget} that
     * generated this event.
     *
     * @return the instance of the {@link StreetviewPanoramaWidget} that
     *         generated this event.
     */
    public StreetviewPanoramaWidget getSender() {
      return (StreetviewPanoramaWidget) getSource();
    }
  }

  /**
   * A method to implement in order to handle {@link StreetviewZoomChangedEvent}
   * .
   *
   * @param event event to handle.
   */
  void onZoomChanged(StreetviewZoomChangedEvent event);
}

/*
 * Copyright 2009 Google Inc.
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

import com.google.gwt.maps.client.overlay.StreetviewOverlay;

import java.util.EventObject;

/**
 * Provides an interface to implement in order to receive MapEvent.CHANGED
 * events from the {@link StreetviewOverlay}.
 */
public interface StreetviewOverlayChangedHandler {

  /**
   * Encapsulates the arguments for the MapEvent.CHANGED event on a
   * {@link StreetviewOverlay}.
   */
  @SuppressWarnings("serial")
  class StreetviewOverlayChangedEvent extends EventObject {

    final boolean hasStreetviewData;

    public StreetviewOverlayChangedEvent(StreetviewOverlay source,
        boolean hasStreetviewData) {
      super(source);
      this.hasStreetviewData = hasStreetviewData;
    }

    /**
     * Returns the instance of the overlay that generated this event.
     * 
     * @return the instance of the overlay that generated this event.
     */
    public StreetviewOverlay getSender() {
      return (StreetviewOverlay) getSource();
    }

    /**
     * Returns <code>true</code> if the viewport contains Street View data and
     * false otherwise. The event works regardless of whether the layer is
     * hidden or shown.
     * 
     * @return <code>true</code> if the viewport contains Street View data and
     *         false otherwise.
     */
    public boolean hasStreetviewData() {
      return hasStreetviewData;
    }
  }

  /**
   * Method to be invoked when a MapEvent.CHANGED event fires on a
   * {@link StreetviewOverlay}.
   * 
   * @param event contains the properties of the event.
   */
  void onChanged(StreetviewOverlayChangedEvent event);
}

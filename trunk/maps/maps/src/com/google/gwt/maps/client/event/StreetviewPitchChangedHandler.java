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
 * {@link MapEvent#PITCHCHANGED} events from the
 * {@link StreetviewPanoramaWidget}.
 */
public interface StreetviewPitchChangedHandler {

  /**
   * Encapsulates the arguments for the {@link MapEvent#PITCHCHANGED} event on a
   * {@link StreetviewPanoramaWidget}.
   */
  @SuppressWarnings("serial")
  class StreetviewPitchChangedEvent extends EventObject {
    private final double pitch;

    public StreetviewPitchChangedEvent(StreetviewPanoramaWidget source,
        double pitch) {
      super(source);
      this.pitch = pitch;
    }

    /**
     * Returns the new pitch of the Street View point of view.
     *
     * @return the new pitch of the Street View point of view.
     */
    public double getPitch() {
      return pitch;
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
   * A method to implement in order to handle
   * {@link StreetviewPitchChangedEvent}.
   *
   * @param event event to handle.
   */
  void onPitchChanged(StreetviewPitchChangedEvent event);
}

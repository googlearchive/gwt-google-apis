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

import com.google.gwt.maps.client.overlay.Polyline;

import java.util.EventObject;

/**
 * Provides an interface to implement in order to receive MapEvent.LINEUPDATED events
 * from the {@link Polyline}.
 */
public interface PolylineLineUpdatedHandler {

  /**
   * Encapsulates the arguments for the MapEvent.LINEUPDATED event on a
   * {@link Polyline}.
   */
  @SuppressWarnings("serial")
  class PolylineLineUpdatedEvent extends EventObject {

    public PolylineLineUpdatedEvent(Polyline source) {
      super(source);
    }

    /**
     * Returns the instance of the polyline that generated this event.
     * 
     * @return the instance of the polyline that generated this event.
     */
    public Polyline getSender() {
      return (Polyline) getSource();
    }
  }

  /**
   * Method to be invoked when a MapEvent.LINEUPDATED event fires on a
   * {@link Polyline}.
   * 
   * @param event contains the properties of the event.
   */
  void onUpdate(PolylineLineUpdatedEvent event);
}

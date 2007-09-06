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

import com.google.gwt.maps.client.impl.TrafficOverlayImpl;
import com.google.gwt.maps.client.overlay.Overlay.ConcreteOverlay;

/**
 * A traffic overlay object displays road traffic information. The traffic
 * overlay will only display traffic information for supported cities.
 * 
 * To add a traffic overlay to the map:
 * 
 * <pre>
 *  TrafficOverlay traffic = new TrafficOverlay();
 *  map.addOverlay(traffic);
 * </pre>
 */
public final class TrafficOverlay extends ConcreteOverlay {

  /**
   * Creates a new traffic overlay.
   * 
   * Use {@link com.google.gwt.maps.client.MapWidget#addOverlay(Overlay)} to add
   * this overlay to the map.
   */
  public TrafficOverlay() {
    super(TrafficOverlayImpl.impl.construct());
  }

  /**
   * Shows or hides this overlay.
   * 
   * @param visible true to show the overlay, false to hide.
   */
  public void setVisible(boolean visible) {
    TrafficOverlayImpl.impl.setVisible(this, visible);
  }
}

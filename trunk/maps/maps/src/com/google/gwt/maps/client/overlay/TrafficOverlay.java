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
package com.google.gwt.maps.client.overlay;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.event.TrafficOverlayChangedHandler;
import com.google.gwt.maps.client.event.TrafficOverlayChangedHandler.TrafficOverlayChangedEvent;
import com.google.gwt.maps.client.impl.HandlerCollection;
import com.google.gwt.maps.client.impl.MapEvent;
import com.google.gwt.maps.client.impl.TrafficOverlayImpl;
import com.google.gwt.maps.client.impl.EventImpl.BooleanCallback;
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

  private HandlerCollection<TrafficOverlayChangedHandler> trafficOverlayChangedHandlers;

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
   * Construct a new traffic overlay with options.
   * See {@link TrafficOverlayOptions}
   */
  public TrafficOverlay(TrafficOverlayOptions options) {
	  super(TrafficOverlayImpl.impl.construct(options));
  }
  
  /**
   * Create this overlay from an existing JavaScriptObject instance.
   * 
   * @param jsoPeer an existing JavaScriptObject instance.
   */
  protected TrafficOverlay(JavaScriptObject jsoPeer) {
    super(jsoPeer);
  }

  /**
   * 
   * This event is fired when the state of traffic data changes within the
   * current viewport. This event may be fired either when moving the map
   * between areas with and without traffic data or when the addition of a
   * {@link TrafficOverlay} to the map results in traffic data appearing within
   * the current viewport.
   * 
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addTrafficOverlayChangedHandler(
      final TrafficOverlayChangedHandler handler) {
    maybeInitTrafficOverlayChangedHandlers();

    trafficOverlayChangedHandlers.addHandler(handler, new BooleanCallback() {
      @Override
      public void callback(boolean trafficInView) {
        TrafficOverlayChangedEvent e = new TrafficOverlayChangedEvent(
            TrafficOverlay.this, trafficInView);
        handler.onChanged(e);
      }
    });
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link TrafficOverlay#addTrafficOverlayChangedHandler(TrafficOverlayChangedHandler)}
   * .
   * 
   * @param handler the handler to remove
   */
  public void removeTrafficOverlayHandler(TrafficOverlayChangedHandler handler) {
    if (trafficOverlayChangedHandlers != null) {
      trafficOverlayChangedHandlers.removeHandler(handler);
    }
  }

  /**
   * Shows or hides this overlay.
   * 
   * @param visible <code>true</code> to show the overlay, false to hide.
   */
  public void setVisible(boolean visible) {
    TrafficOverlayImpl.impl.setVisible(this, visible);
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(TrafficOverlayChangedEvent event) {
    maybeInitTrafficOverlayChangedHandlers();
    trafficOverlayChangedHandlers.trigger(event.isTrafficInView());
  }

  private void maybeInitTrafficOverlayChangedHandlers() {
    if (trafficOverlayChangedHandlers == null) {
      trafficOverlayChangedHandlers = new HandlerCollection<TrafficOverlayChangedHandler>(
          jsoPeer, MapEvent.CHANGED);
    }
  }
}

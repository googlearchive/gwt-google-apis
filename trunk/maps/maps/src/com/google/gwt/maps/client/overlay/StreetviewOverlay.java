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
package com.google.gwt.maps.client.overlay;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.event.StreetviewOverlayChangedHandler;
import com.google.gwt.maps.client.event.StreetviewOverlayChangedHandler.StreetviewOverlayChangedEvent;
import com.google.gwt.maps.client.impl.HandlerCollection;
import com.google.gwt.maps.client.impl.MapEvent;
import com.google.gwt.maps.client.impl.StreetviewOverlayImpl;
import com.google.gwt.maps.client.impl.EventImpl.BooleanCallback;
import com.google.gwt.maps.client.overlay.Overlay.ConcreteOverlay;

/**
 * A Street View overlay object displays where Street View is available by
 * highlighting roads in blue.
 * 
 * To add a Street View overlay to the map:
 * 
 * <pre>
 * StreetviewOverlay svOverlay = new StreetviewOverlay();
 * map.addOverlay(svOverlay);
 * </pre>
 */
public final class StreetviewOverlay extends ConcreteOverlay {

  public static StreetviewOverlay createPeer(JavaScriptObject jso) {
    StreetviewOverlay overlay = new StreetviewOverlay(jso);
    // perform bind to make sure the right JSO was passed
    StreetviewOverlayImpl.impl.bind(jso, overlay);
    return overlay;
  }

  private HandlerCollection<StreetviewOverlayChangedHandler> streetviewOverlayChangedHandlers;

  /**
   * Creates a new Street View overlay.
   * 
   * Use {@link com.google.gwt.maps.client.MapWidget#addOverlay(Overlay)} to add
   * this overlay to the map.
   */
  public StreetviewOverlay() {
    super(StreetviewOverlayImpl.impl.construct());
  }

  /**
   * Create this overlay from an existing JavaScriptObject instance.
   * 
   * @param jsoPeer an existing JavaScriptObject instance.
   */
  protected StreetviewOverlay(JavaScriptObject jsoPeer) {
    super(jsoPeer);
  }

  /**
   * This event is fired when the state of Street View data changes within the
   * current viewport. It is fired when moving the map from an area with Street
   * View data to one without, and vice-versa. It is also fired when the
   * addition of a {@link StreetviewOverlay} to the map results in Street View
   * data appearing within the current viewport.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addStreetviewOverlayChangedHandler(
      final StreetviewOverlayChangedHandler handler) {
    maybeInitStreetviewOverlayChangedHandlers();

    streetviewOverlayChangedHandlers.addHandler(handler, new BooleanCallback() {
      @Override
      public void callback(boolean hasStreetviewData) {
        StreetviewOverlayChangedEvent e = new StreetviewOverlayChangedEvent(
            StreetviewOverlay.this, hasStreetviewData);
        handler.onChanged(e);
      }
    });
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link StreetviewOverlay#addStreetviewOverlayChangedHandler(StreetviewOverlayChangedHandler)}
   * .
   * 
   * @param handler the handler to remove
   */
  public void removeStreetviewOverlayHandler(
      StreetviewOverlayChangedHandler handler) {
    if (streetviewOverlayChangedHandlers != null) {
      streetviewOverlayChangedHandlers.removeHandler(handler);
    }
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(StreetviewOverlayChangedEvent event) {
    maybeInitStreetviewOverlayChangedHandlers();
    streetviewOverlayChangedHandlers.trigger(event.hasStreetviewData());
  }

  private void maybeInitStreetviewOverlayChangedHandlers() {
    if (streetviewOverlayChangedHandlers == null) {
      streetviewOverlayChangedHandlers = new HandlerCollection<StreetviewOverlayChangedHandler>(
          jsoPeer, MapEvent.CHANGED);
    }
  }
}

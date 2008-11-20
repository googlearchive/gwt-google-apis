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
import com.google.gwt.maps.client.event.GroundOverlayVisibilityChangedHandler;
import com.google.gwt.maps.client.event.GroundOverlayVisibilityChangedHandler.GroundOverlayVisibilityChangedEvent;
import com.google.gwt.maps.client.geom.LatLngBounds;
import com.google.gwt.maps.client.impl.GroundOverlayImpl;
import com.google.gwt.maps.client.impl.HandlerCollection;
import com.google.gwt.maps.client.impl.MapEvent;
import com.google.gwt.maps.client.impl.EventImpl.BooleanCallback;
import com.google.gwt.maps.client.overlay.Overlay.ConcreteOverlay;

/**
 * A rectangular image overlay whose boundaries are defined by a
 * {@link LatLngBounds}.
 * 
 * Example:
 * 
 * <pre>
 *  MapWidget map; = ...;
 *  Overlay overlay = new GroundOverlay(<i>url</i>, <i>bounds</i>);
 *  map.addOverlay(overlay);
 * </pre>
 * 
 * @see com.google.gwt.maps.client.MapWidget#addOverlay(Overlay)
 */
public class GroundOverlay extends ConcreteOverlay {

  public static GroundOverlay createPeer(JavaScriptObject jsoPeer) {
    return new GroundOverlay(jsoPeer);
  }
  private HandlerCollection<GroundOverlayVisibilityChangedHandler> groundOverlayVisibilityChangedHandlers;

  /**
   * Creates a new ground overlay from the given image with the specified size.
   * 
   * @param imageUrl the URL of the overlay image
   * @param bounds the rectangle defining the bounds of the overlay
   */
  public GroundOverlay(String imageUrl, LatLngBounds bounds) {
    super(GroundOverlayImpl.impl.construct(imageUrl, bounds));
  }

  GroundOverlay(JavaScriptObject jsoPeer) {
    super(jsoPeer);
  }
  /**
   * This event is fired when the visibility of the ground overlay is changed (i.e. the
   * visibility is flipped from visible to hidden or vice-versa). The
   * <code>visible</code> parameter refers to the state of the ground overlay after
   * the visibility change has happened.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addGroundOverlayVisibilityChangedHandler(
      final GroundOverlayVisibilityChangedHandler handler) {
    maybeInitGroundOverlayVisibilityChangeHandlers();

    groundOverlayVisibilityChangedHandlers.addHandler(handler, new BooleanCallback() {
      @Override
      public void callback(boolean visible) {
        GroundOverlayVisibilityChangedEvent e = new GroundOverlayVisibilityChangedEvent(
            GroundOverlay.this, visible);
        handler.onVisibilityChanged(e);
      }
    });
  }
  
  /**
   * Returns <code>true</code> if the overlay is currently visible.
   * @return <code>true</code> if the overlay is currently visible.
   */
  public boolean isVisible() {
    return !GroundOverlayImpl.impl.isHidden(jsoPeer);
  }
  
  /**
   * Removes a single handler of this map previously added with
   * {@link GroundOverlay#addGroundOverlayVisibilityChangedHandler(GroundOverlayVisibilityChangedHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeGroundOverlayVisibilityChangedHandler(
      GroundOverlayVisibilityChangedHandler handler) {
    if (groundOverlayVisibilityChangedHandlers != null) {
      groundOverlayVisibilityChangedHandlers.removeHandler(handler);
    }
  }
 
  /**
   * Sets the overlay visible by passing <code>true</code> or hide it b passing <code>false</code>.
   * @param enabled sets the overlay visible by passing <code>true</code> or hide it b passing <code>false</code>.
   */
  public void setVisible(boolean enabled) {
    if (enabled) {
      GroundOverlayImpl.impl.show(jsoPeer);
    } else {
      GroundOverlayImpl.impl.hide(jsoPeer);
    }
  }
  
  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(GroundOverlayVisibilityChangedEvent event) {
    maybeInitGroundOverlayVisibilityChangeHandlers();
    groundOverlayVisibilityChangedHandlers.trigger(event.isVisible());
  }
  
  /**
   * Lazy init the HandlerCollection.
   */
  private void maybeInitGroundOverlayVisibilityChangeHandlers() {
    if (groundOverlayVisibilityChangedHandlers == null) {
      groundOverlayVisibilityChangedHandlers = new HandlerCollection<GroundOverlayVisibilityChangedHandler>(
          jsoPeer, MapEvent.VISIBILITYCHANGED);
    }
  }
  
}

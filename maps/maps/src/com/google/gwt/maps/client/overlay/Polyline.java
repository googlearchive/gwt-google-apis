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
import com.google.gwt.maps.client.event.PolylineClickHandler;
import com.google.gwt.maps.client.event.PolylineRemoveHandler;
import com.google.gwt.maps.client.event.RemoveListener;
import com.google.gwt.maps.client.event.PolylineClickHandler.PolylineClickEvent;
import com.google.gwt.maps.client.event.PolylineRemoveHandler.PolylineRemoveEvent;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.impl.EventImpl;
import com.google.gwt.maps.client.impl.HandlerCollection;
import com.google.gwt.maps.client.impl.JsUtil;
import com.google.gwt.maps.client.impl.ListenerCollection;
import com.google.gwt.maps.client.impl.MapEvent;
import com.google.gwt.maps.client.impl.PolylineFactoryImpl;
import com.google.gwt.maps.client.impl.PolylineImpl;
import com.google.gwt.maps.client.impl.PolylineOptionsImpl;
import com.google.gwt.maps.client.impl.EventImpl.LatLngCallback;
import com.google.gwt.maps.client.impl.EventImpl.VoidCallback;
import com.google.gwt.maps.client.overlay.Overlay.ConcreteOverlay;

/**
 * This is a map overlay that draws a polyline on the map, using the vector
 * drawing facilities of the browser if they are available, or an image overlay
 * from Google servers otherwise.
 * 
 */
public final class Polyline extends ConcreteOverlay {

  public static Polyline fromEncoded(String color, int weight, double opacity,
      String encodedPoints, int zoomFactor, String encodedLevels, int numLevels) {
    JavaScriptObject optionsJso = PolylineOptionsImpl.impl.construct();
    PolylineOptionsImpl.impl.setColor(optionsJso, color);
    PolylineOptionsImpl.impl.setWeight(optionsJso, weight);
    PolylineOptionsImpl.impl.setOpacity(optionsJso, opacity);
    PolylineOptionsImpl.impl.setPoints(optionsJso, encodedPoints);
    PolylineOptionsImpl.impl.setZoomFactor(optionsJso, zoomFactor);
    PolylineOptionsImpl.impl.setLevels(optionsJso, encodedLevels);
    PolylineOptionsImpl.impl.setNumLevels(optionsJso, numLevels);
    return new Polyline(PolylineFactoryImpl.impl.fromEncoded(optionsJso));
  }

  public static Polyline fromEncoded(String encodedPoints, int zoomFactor,
      String encodedLevels, int numLevels) {
    JavaScriptObject optionsJso = PolylineOptionsImpl.impl.construct();
    PolylineOptionsImpl.impl.setPoints(optionsJso, encodedPoints);
    PolylineOptionsImpl.impl.setZoomFactor(optionsJso, zoomFactor);
    PolylineOptionsImpl.impl.setLevels(optionsJso, encodedLevels);
    PolylineOptionsImpl.impl.setNumLevels(optionsJso, numLevels);
    return new Polyline(PolylineFactoryImpl.impl.fromEncoded(optionsJso));
  }

  /**
   * Used to create a new Polyline by wrapping an existing GPolyline object.
   * This method is invoked by the jsio library.
   * 
   * @param jsoPeer GPolyline object to wrap.
   * @return a new instance of Polyline.
   */
  @SuppressWarnings("unused")
  private static Polyline createPeer(JavaScriptObject jsoPeer) {
    return new Polyline(jsoPeer);
  }

  private HandlerCollection<PolylineClickHandler> polylineClickHandlers;
  private HandlerCollection<PolylineRemoveHandler> polylineRemoveHandlers;
  private ListenerCollection<RemoveListener> removeListeners;

  public Polyline(LatLng[] points) {
    super(PolylineImpl.impl.construct(JsUtil.toJsList(points)));
  }

  public Polyline(LatLng[] points, String color) {
    super(PolylineImpl.impl.construct(JsUtil.toJsList(points), color));
  }

  public Polyline(LatLng[] points, String color, int weight) {
    super(PolylineImpl.impl.construct(JsUtil.toJsList(points), color, weight));
  }

  public Polyline(LatLng[] points, String color, int weight, double opacity) {
    super(PolylineImpl.impl.construct(JsUtil.toJsList(points), color, weight,
        opacity));
  }

  private Polyline(JavaScriptObject jsoPeer) {
    super(jsoPeer);
  }

  /**
   * This event is fired when the polyline is clicked. Note that this event also
   * subsequently triggers a "click" event on the map, where the polyline is
   * passed as the overlay argument within that event.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addPolylineClickHandler(final PolylineClickHandler handler) {
    maybeInitPolylineClickHandlers();

    polylineClickHandlers.addHandler(handler, new LatLngCallback() {
      @Override
      public void callback(LatLng latlng) {
        PolylineClickEvent e = new PolylineClickEvent(Polyline.this, latlng);
        handler.onClick(e);
      }
    });
  }

  /**
   * This event is fired when the polyline is removed from the map, using
   * {@link com.google.gwt.maps.client.MapWidget#removeOverlay} or
   * {@link com.google.gwt.maps.client.MapWidget#clearOverlays}.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addPolylineRemoveHandler(final PolylineRemoveHandler handler) {
    maybeInitPolylineRemoveHandlers();

    polylineRemoveHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        PolylineRemoveEvent e = new PolylineRemoveEvent(Polyline.this);
        handler.onRemove(e);
      }
    });
  }

  public void addRemoveListener(final RemoveListener listener) {
    if (removeListeners == null) {
      removeListeners = new ListenerCollection<RemoveListener>();
    }

    JavaScriptObject removeEventHandles[] = {EventImpl.impl.addListenerVoid(
        super.jsoPeer, MapEvent.REMOVE, new VoidCallback() {
          @Override
          public void callback() {
            listener.onRemove(Polyline.this);
          }
        })};
    removeListeners.addListener(listener, removeEventHandles);
  }

  public void clearRemoveListeners() {
    if (removeListeners != null) {
      removeListeners.clearListeners();
    }
  }

  public LatLng getVertex(int index) {
    return PolylineImpl.impl.getVertex(super.jsoPeer, index);
  }

  public int getVertexCount() {
    return PolylineImpl.impl.getVertexCount(super.jsoPeer);
  }

  /**
   * Returns true if the polyline is visible on the map.
   * 
   * @return true if the polyline is visible on the map.
   */
  public boolean isVisible() {
    return !PolylineImpl.impl.isHidden(jsoPeer);
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link Polyline#addPolylineClickHandler(PolylineClickHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removePolylineClickHandler(PolylineClickHandler handler) {
    if (polylineClickHandlers != null) {
      polylineClickHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link Polyline#addPolylineRemoveHandler(PolylineRemoveHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removePolylineRemoveHandler(PolylineRemoveHandler handler) {
    if (polylineRemoveHandlers != null) {
      polylineRemoveHandlers.removeHandler(handler);
    }
  }

  public void removeRemoveListener(RemoveListener listener) {
    if (removeListeners != null) {
      removeListeners.removeListener(listener);
    }
  }
  
  // TODO(zundel): Implement getBounds() method. (issue 75)
  // TODO(zundel): Implement getLenght() method. (issue 75)

  /**
   * Show or hide the polyline.
   * 
   * @param visible true to show the polyline.
   */
  public void setVisible(boolean visible) {
    if (visible) {
      PolylineImpl.impl.show(jsoPeer);
    } else {
      PolylineImpl.impl.hide(jsoPeer);
    }
  }

  /**
   * Returns <code>true</code> if this environment supports the
   * {@link Polyline#setVisible} method.
   * 
   * @return <code>true</code> if setVisible(false) is supported in the
   *         current environment.
   */
  boolean supportsHide() {
    return PolylineImpl.impl.supportsHide(jsoPeer);
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(PolylineClickEvent event) {
    maybeInitPolylineClickHandlers();
    polylineRemoveHandlers.trigger(event.getLatLng());
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(PolylineRemoveEvent event) {
    maybeInitPolylineRemoveHandlers();
    polylineRemoveHandlers.trigger();
  }

  private void maybeInitPolylineClickHandlers() {
    if (polylineClickHandlers == null) {
      polylineClickHandlers = new HandlerCollection<PolylineClickHandler>(
          jsoPeer, MapEvent.CLICK);
    }
  }

  private void maybeInitPolylineRemoveHandlers() {
    if (polylineRemoveHandlers == null) {
      polylineRemoveHandlers = new HandlerCollection<PolylineRemoveHandler>(
          jsoPeer, MapEvent.REMOVE);
    }
  }
}

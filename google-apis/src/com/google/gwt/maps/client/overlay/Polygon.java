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
import com.google.gwt.maps.client.event.PolygonClickHandler;
import com.google.gwt.maps.client.event.PolygonRemoveHandler;
import com.google.gwt.maps.client.event.RemoveListener;
import com.google.gwt.maps.client.event.PolygonClickHandler.PolygonClickEvent;
import com.google.gwt.maps.client.event.PolygonRemoveHandler.PolygonRemoveEvent;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.impl.EventImpl;
import com.google.gwt.maps.client.impl.HandlerCollection;
import com.google.gwt.maps.client.impl.JsUtil;
import com.google.gwt.maps.client.impl.ListenerCollection;
import com.google.gwt.maps.client.impl.MapEvent;
import com.google.gwt.maps.client.impl.PolygonImpl;
import com.google.gwt.maps.client.impl.EventImpl.LatLngCallback;
import com.google.gwt.maps.client.impl.EventImpl.VoidCallback;
import com.google.gwt.maps.client.overlay.Overlay.ConcreteOverlay;

/**
 * This is a map overlay that draws a polygon on the map, using the vector
 * drawing facilities of the browser if they are available, or an image overlay
 * from Google servers otherwise. This is very similar to a
 * {@link com.google.gwt.maps.client.overlay.Polyline}, except that you can
 * additionally specify a fill color and opacity.
 */
public final class Polygon extends ConcreteOverlay {

  /**
   * Used to create a new Polygon by wrapping an existing GPolygon object. This
   * method is invoked by the jsio library.
   * 
   * @param jsoPeer GPolygon object to wrap.
   * @return a new instance of Polygon.
   */
  @SuppressWarnings("unused")
  private static Polygon createPeer(JavaScriptObject jsoPeer) {
    return new Polygon(jsoPeer);
  }

  private HandlerCollection<PolygonClickHandler> polygonClickHandlers;
  private HandlerCollection<PolygonRemoveHandler> polygonRemoveHandlers;
  private ListenerCollection<RemoveListener> removeListeners;

  public Polygon(LatLng[] points) {
    super(PolygonImpl.impl.construct(JsUtil.toJsList(points)));
  }

  public Polygon(LatLng[] points, String strokeColor, int strokeWeight,
      double strokeOpacity, String fillColor, double fillOpacity) {
    super(PolygonImpl.impl.construct(JsUtil.toJsList(points), strokeColor,
        strokeWeight, strokeOpacity, fillColor, fillOpacity));
  }

  private Polygon(JavaScriptObject jsoPeer) {
    super(jsoPeer);
  }

  /**
   * This event is fired when the polygon is clicked. Note that this event also
   * subsequently triggers a "click" event on the map, where the polygon is
   * passed as the overlay argument within that event.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addPolygonClickHandler(final PolygonClickHandler handler) {
    maybeInitPolygonClickHandlers();

    polygonClickHandlers.addHandler(handler, new LatLngCallback() {
      @Override
      public void callback(LatLng latlng) {
        PolygonClickEvent e = new PolygonClickEvent(Polygon.this, latlng);
        handler.onClick(e);
      }
    });
  }

  /**
   * This event is fired when the polygon is removed from the map, using
   * {@link com.google.gwt.maps.client.MapWidget#removeOverlay} or
   * {@link com.google.gwt.maps.client.MapWidget#clearOverlays}.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addPolygonRemoveHandler(final PolygonRemoveHandler handler) {
    maybeInitPolygonRemoveHandlers();

    polygonRemoveHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        PolygonRemoveEvent e = new PolygonRemoveEvent(Polygon.this);
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
            listener.onRemove(Polygon.this);
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
    return PolygonImpl.impl.getVertex(jsoPeer, index);
  }

  public int getVertextCount() {
    return PolygonImpl.impl.getVertextCount(jsoPeer);
  }

  /**
   * Returns true if the polygon is visible on the map.
   * 
   * @return true if the polygon is visible on the map.
   */
  public boolean isVisible() {
    return !PolygonImpl.impl.isHidden(jsoPeer);
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link Polygon#addPolygonClickHandler(PolygonClickHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removePolygonClickHandler(PolygonClickHandler handler) {
    if (polygonClickHandlers != null) {
      polygonClickHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link Polygon#addPolygonRemoveHandler(PolygonRemoveHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removePolygonRemoveHandler(PolygonRemoveHandler handler) {
    if (polygonRemoveHandlers != null) {
      polygonRemoveHandlers.removeHandler(handler);
    }
  }

  public void removeRemoveListener(RemoveListener listener) {
    if (removeListeners != null) {
      removeListeners.removeListener(listener);
    }
  }

  /**
   * Returns <code>true</code> if this environment supports the
   * {@link Polygon#setVisible(boolean)} method.
   * 
   * @return true if setVisible(<code>false</code>) is supported.
   */
  public boolean supportsHide() {
    // TODO(zundel): after the polygon hide/show fix is in place (issue 101), uncomment
    // return PolygonImpl.impl.supportsHide(jsoPeer);
    return false;
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(PolygonClickEvent event) {
    maybeInitPolygonClickHandlers();
    polygonRemoveHandlers.trigger(event.getLatLng());
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(PolygonRemoveEvent event) {
    maybeInitPolygonRemoveHandlers();
    polygonRemoveHandlers.trigger();
  }

  private void maybeInitPolygonClickHandlers() {
    if (polygonClickHandlers == null) {
      polygonClickHandlers = new HandlerCollection<PolygonClickHandler>(
          jsoPeer, MapEvent.CLICK);
    }
  }

  private void maybeInitPolygonRemoveHandlers() {
    if (polygonRemoveHandlers == null) {
      polygonRemoveHandlers = new HandlerCollection<PolygonRemoveHandler>(
          jsoPeer, MapEvent.REMOVE);
    }
  }

}

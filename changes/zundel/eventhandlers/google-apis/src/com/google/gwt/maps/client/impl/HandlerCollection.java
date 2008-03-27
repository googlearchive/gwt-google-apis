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
package com.google.gwt.maps.client.impl;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.client.impl.EventImpl.OverlayLatLngCallback;
import com.google.gwt.maps.client.impl.EventImpl.PointElementOverlayCallback;
import com.google.gwt.maps.client.impl.EventImpl.VoidCallback;
import com.google.gwt.maps.client.overlay.Overlay;
import com.google.gwt.user.client.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that keeps track of all JSO's associated with a particular listener
 * instance. Creates an easy way for the Public API to interact with the
 * GEvent.removeListener() and GEvent.clearListeners() methods when the time
 * comes to remove events.
 * 
 * @param <E> One of the XXXListener interfaces/abstract classes
 */
public class HandlerCollection<E> {
  private static class HandleContainer<T> {
    JavaScriptObject eventHandlerJso;
    T listener;

    private HandleContainer(T listener, JavaScriptObject eventHandlerJso) {
      this.listener = listener;
      this.eventHandlerJso = eventHandlerJso;
    }
  }

  private final List<HandleContainer<E>> handlers = new ArrayList<HandleContainer<E>>();
  private final JavaScriptObject jsoPeer;
  private final MapEvent mapEvent;

  /**
   * Create an empty collection.
   */
  public HandlerCollection(JavaScriptObject jsoPeer, MapEvent e) {
    this.jsoPeer = jsoPeer;
    mapEvent = e;
  }

  /**
   * Add a listener and the event handles associated with it.
   * 
   * @param listener The listener to add to the collection
   * @param callback The callback to call when the event fires.
   */
  public void addHandler(E listener, OverlayLatLngCallback callback) {
    JavaScriptObject jso = EventImpl.impl.addListener(jsoPeer,
        mapEvent.value(), callback);
    handlers.add(new HandleContainer<E>(listener, jso));
  }

  /**
   * Add a listener and the event handles associated with it.
   * 
   * @param listener The listener to add to the collection
   * @param callback The callback to call when the event fires.
   */
  public void addHandler(E listener, PointElementOverlayCallback callback) {
    JavaScriptObject jso = EventImpl.impl.addListener(jsoPeer,
        mapEvent.value(), callback);
    handlers.add(new HandleContainer<E>(listener, jso));
  }
  
  /**
   * Add a listener and the event handles associated with it.
   * 
   * @param listener The listener to add to the collection
   * @param callback The callback to call when the event fires.
   */
  public void addHandler(E listener, VoidCallback callback) {
    JavaScriptObject jso = EventImpl.impl.addListenerVoid(jsoPeer,
        mapEvent.value(), callback);
    handlers.add(new HandleContainer<E>(listener, jso));
  }
  /**
   * Remove all events for this type of listener.
   */
  public void clearHandlers() {
    // Iterate through our container, looking for this instance of the listener
    for (HandleContainer<E> hc : handlers) {
      // We've found the listener, so iterate and remove the handle that
      // was originally returned from GEvent.addListener()
      EventImpl.impl.removeListener(hc.eventHandlerJso);
    }
    handlers.clear();
  }
  
  /**
   * The type of event this collection represents.
   * 
   * @return the event type this collection was created with.
   */
  public MapEvent getMapEvent() {
    return mapEvent;
  }

  /**
   * Remove all JavaScript GEvent instances for a single listener.
   */
  public void removeHandler(E handler) {
    // Iterate through our container, looking for this instance of the listener
    for (HandleContainer<E> hc : handlers) {
      if (hc.listener == handler) {
        // We've found the listener, so remove the handle that
        // was originally returned from GEvent.addListener()
        EventImpl.impl.removeListener(hc.eventHandlerJso);

        handlers.remove(hc);
        break;
      }
    }
  }

  /**
   * Manually trigger an event that takes an overlay and point argument.
   * @param overlay An overlay to send as a parameter of the event.
   * @param point A coordinate to send as a parameter of the event.
   */
  public void trigger(Overlay overlay, LatLng point) {
    EventImpl.impl.trigger(jsoPeer, mapEvent.value, overlay, point);
  }
  
  /**
   * Manually trigger an event that takes an overlay and point argument.
   * @param elem A DOM element
   * @param overlay An overlay to send as a parameter of the event.
   * @param point A coordinate to send as a parameter of the event.
   */
  public void trigger(Point point, Element elem, Overlay overlay) {
    EventImpl.impl.trigger(jsoPeer, mapEvent.value, point, elem, overlay);
  }
  
  /**
   * Manually trigger an event that takes an overlay and point argument.
   */
  public void trigger() {
    EventImpl.impl.trigger(jsoPeer, mapEvent.value);
  }
}

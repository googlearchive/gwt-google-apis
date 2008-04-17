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

import java.util.ArrayList;
import java.util.List;

/**
 * A class that keeps track of all JSO's associated with a particular listener
 * instance.  Creates an easy way for the Public API to interact with 
 * the GEvent.removeListener() and GEvent.clearListeners() methods when the time
 * comes to remove events.
 * 
 * @param <E> One of the XXXListener interfaces/abstract classes
 */
public class ListenerCollection<E> {
  private static class HandleContainer<T> {
    JavaScriptObject eventHandlerJso[];
    T listener;

    private HandleContainer(T listener, JavaScriptObject eventHandlerJso[]) {
      this.listener = listener;
      this.eventHandlerJso = eventHandlerJso;
    }
  }

  private final List<HandleContainer<E>> handlers = new ArrayList<HandleContainer<E>>();

  /**
   * Create an empty collection.
   */
  public ListenerCollection() {
  }

  /**
   * Add a listener and the event handles associated with it.
   * 
   * @param listener The listener to add to the collection
   * @param eventHandlerJso An array of JSO event handles returned from the 
   *   Maps API.  Do not modify the contents of this array after it is passed
   *   to this method.
   */
  public void addListener(E listener, JavaScriptObject eventHandlerJso[]) {
    handlers.add(new HandleContainer<E>(listener, eventHandlerJso));
  }

  /**
   * Remove all events for this type of listener.
   */
  public void clearListeners() {
    // Iterate through our container, looking for this instance of the listener
    for (HandleContainer<E> hc : handlers) {
      // We've found the listener, so iterate and remove each handle that
      // was originally returned from GEvent.addListener()
      for (JavaScriptObject jso : hc.eventHandlerJso) {
        EventImpl.impl.removeListener(jso);
      }
    }
    handlers.clear();
  }

  /**
   * Remove all JavaScript GEvent instances for a single listener.
   */
  public void removeListener(E listener) {
    // Iterate through our container, looking for this instance of the listener
    for (HandleContainer<E> hc : handlers) {
      if (hc.listener == listener) {
        // We've found the listener, so iterate and remove each handle that
        // was originally returned from GEvent.addListener()
        for (JavaScriptObject jso : hc.eventHandlerJso) {
          EventImpl.impl.removeListener(jso);
        }
        handlers.remove(hc);
        break;
      }
    }
  }
}

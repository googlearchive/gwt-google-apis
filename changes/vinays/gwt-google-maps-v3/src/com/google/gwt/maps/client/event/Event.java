/* Copyright (c) 2010 Vinay Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.gwt.maps.client.event;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.HasJso;
import com.google.gwt.maps.client.event.impl.EventImpl;

/**
 * Utility class to add/remove maps event listeners.
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public class Event {

  /**
   * Adds the given listener function to the the given event name for the given
   * object instance. Returns an identifier for this listener that can be used
   * with eventRemoveListener().
   */
  public static HasMapsEventListener addListener(JavaScriptObject instance, String eventName,
      EventCallback callback) {
    final HasMapsEventListener listener = new MapsEventListener(EventImpl.impl
        .addListener(instance, eventName, callback));
    return listener;
  }

  
  /**
   * Adds the given listener function to the the given event name for the given
   * object instance. Returns an identifier for this listener that can be used
   * with eventRemoveListener().
   */
  public static HasMapsEventListener addListener(HasJso instance, String eventName,
      MouseEventCallback callback) {
    final HasMapsEventListener listener = new MapsEventListener(EventImpl.impl
        .addMouseListener(instance.getJso(), eventName, callback));
    return listener;
  }


  /**
   * Like eventAddListener, but the handler removes itself after handling the
   * first event.
   */
  public static HasMapsEventListener addListenerOnce(HasJso instance, String eventName,
      EventCallback callback) {
    final HasMapsEventListener listener = new MapsEventListener(EventImpl.impl
        .addListenerOnce(instance.getJso(), eventName, callback));
    return listener;
  }

  
  /**
   * Like eventAddListener, but the handler removes itself after handling the
   * first event.
   */
  public static HasMapsEventListener addListenerOnce(HasJso instance, String eventName,
      MouseEventCallback callback) {
    final HasMapsEventListener listener = new MapsEventListener(EventImpl.impl
        .addMouseListenerOnce(instance.getJso(), eventName, callback));
    return listener;
  }
  
  /**
   * Removes all listeners for all events for the given instance.
   */
  public static void clearInstanceListeners(JavaScriptObject instance) {
    EventImpl.impl.clearInstanceListeners(instance);
  }

  
  /**
   * Removes all listeners for the given event for the given instance.
   */
  public static void clearListeners(HasJso instance, String eventName) {
    EventImpl.impl.clearListeners(instance.getJso(), eventName);
  }

  
  /**
   * Removes the given listener, which should have been returned by
   * eventAddListener above.
   */
  public static void removeListener(HasMapsEventListener listener) {
    EventImpl.impl.removeListener(listener.getJso());
  }
  
  /**
   * Triggers the given event. All arguments after eventName are passed as
   * arguments to the listeners.
   */
  public static void trigger(JavaScriptObject instance, String eventName) {
    EventImpl.impl.trigger(instance, eventName);
  }

}

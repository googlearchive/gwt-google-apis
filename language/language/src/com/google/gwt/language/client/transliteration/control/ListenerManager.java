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
package com.google.gwt.language.client.transliteration.control;

import com.google.gwt.core.client.JavaScriptObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Keeps record of event listeners: both Java objects and their JSO
 * counterparts. Also facilitates creation of new event listeners.
 *
 * This class is non-public as its used only as an internal utility.
 */
class ListenerManager {

  /**
   * Stores the tuple containing event type, TranslitEventListener and its
   * JS counterpart.
   */
  private static class ListenerInfo {
    private final EventType eventType;
    private final JavaScriptObject jso;
    private final TransliterationEventListener listener;

    ListenerInfo(EventType eventType, TransliterationEventListener listener,
        JavaScriptObject jso) {
      this.eventType = eventType;
      this.listener = listener;
      this.jso = jso;
    }

    EventType getEventType() {
      return eventType;
    }

    JavaScriptObject getJSO() {
      return jso;
    }

    TransliterationEventListener getListener() {
      return listener;
    }
  }

  private static final List<ListenerInfo> listenerCache =
      new ArrayList<ListenerInfo>();

  /**
   * Creates a JavaScriptObject counterpart of input TranslitEventListener
   * object.
   *
   * @param listener the TranslitEventListener whose JS equivalent is desired
   * @return JavaScriptObject counterpart of listener
   */
  static final native JavaScriptObject createJSOEventListener(
      TransliterationEventListener listener) /*-{
    return function(e) {
      listener.@com.google.gwt.language.client.transliteration.control.TransliterationEventListener::onEventWrapper(Lcom/google/gwt/language/client/transliteration/control/TransliterationEventDetail;)(e);
    }
  }-*/;

  /**
   * Returns the JSO object associated with given listener. Returns null if
   * no such object exists. Also removes the object from the internal store.
   *
   * @param listener the Java event listener object
   * @return the JSO object
   */
  static JavaScriptObject findAndRemove(EventType eventType,
      TransliterationEventListener listener) {
    for (ListenerInfo listenerInfo : listenerCache) {
      if (listenerInfo.getEventType() == eventType
          && listenerInfo.getListener() == listener) {
        listenerCache.remove(listenerInfo);
        return listenerInfo.getJSO();
      }
    }
    return null;
  }

  /**
   * Stores the listener and its jso counterpart.
   *
   * @param listener the Java event listener object.
   * @param jso jso counterpart of listener.
   */
  static void store(EventType eventType, TransliterationEventListener listener,
      JavaScriptObject jso) {
    listenerCache.add(new ListenerInfo(eventType, listener, jso));
  }

  /**
   * Makes this class non-instantiable.
   */
  private ListenerManager() { }
}

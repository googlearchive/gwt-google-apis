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
package com.google.gwt.visualization.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;

/**
 * A listener to visualization events.  All listeners to visualization events
 * should extend this class.
 */
abstract public class Listener {
  public static final native void addListener(Visualization<?> visualization, 
                                              String eventName, 
                                              Listener listener) /*-{
    var callback = function(event) {
      @com.google.gwt.visualization.client.Listener::onCallback(Lcom/google/gwt/visualization/client/Visualization;Lcom/google/gwt/visualization/client/Listener;Lcom/google/gwt/visualization/client/Properties;)
          (visualization, listener, event);
    };
    $wnd.google.visualization.events.addListener(visualization, 
                                                 eventName, 
                                                 callback);
  }-*/;
  
  abstract protected void onEvent(Visualization<?> visualization, 
                                  Properties page);
  
  private void fireAndCatch(UncaughtExceptionHandler handler,
      Visualization<?> visualization, Properties event) {
    try {
      fireImpl(visualization, event);
    } catch (Throwable e) {
      handler.onUncaughtException(e);
    }
  }

  private void fireImpl(Visualization<?> visualization, Properties event) {
    onEvent(visualization, event);
  }

  @SuppressWarnings("unused")
  private static void onCallback(Visualization<?> visualization, 
                                 Listener listener, 
                                 Properties event) {
    UncaughtExceptionHandler handler = GWT.getUncaughtExceptionHandler();
    if (handler != null) {
      listener.fireAndCatch(handler, visualization, event);
    } else {
      listener.fireImpl(visualization, event);
    }
  }
}

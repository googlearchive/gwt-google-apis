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
package com.google.gwt.visualization.client.events;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.visualization.client.AbstractVisualization;
import com.google.gwt.visualization.client.ExceptionHelper;
import com.google.gwt.visualization.client.Properties;
import com.google.gwt.visualization.client.visualizations.Visualization;

/**
 * The base class for visualization event handlers.
 */
public abstract class Handler {
  private static native void addHandler(JavaScriptObject jso, String eventName, Handler handler) /*-{
    var callback = function(event) {
      @com.google.gwt.visualization.client.events.Handler::onCallback(Lcom/google/gwt/visualization/client/events/Handler;Lcom/google/gwt/visualization/client/Properties;)
          (handler, event);
    };
    $wnd.google.visualization.events.addListener(jso, eventName, callback);
  }-*/;
  
  public static void addHandler(Visualization<?> viz,
      String eventName, 
      Handler handler) {
    addHandler(viz.getJso(), eventName, handler);
  }
  
  public static void addHandler(AbstractVisualization<?> viz,
      String eventName, 
      Handler handler) {
    addHandler(viz.getJso(), eventName, handler);
  }

  @SuppressWarnings("unused")
  private static void onCallback(final Handler handler, 
                                 final Properties properties) {
    ExceptionHelper.runProtected(new Runnable() {
      public void run() {
        handler.onEvent(properties);
      }
    });
  }
  
  protected abstract void onEvent(Properties properties);
}

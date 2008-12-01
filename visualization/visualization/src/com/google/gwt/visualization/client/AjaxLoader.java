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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;

/**
 * A wrapper for the google Ajax loader.
 */
public class AjaxLoader {
  public static void loadVisualizationApi(Runnable onLoad, String... packages) {
    loadVisualizationApi("1", onLoad, AbstractDrawOptions.createJsArray(packages));
  }
  
  public static void loadVisualizationApi(String version, Runnable onLoad,
      JsArrayString packages) {
    loadApi("visualization", version, createSettings(onLoad, packages));
  }

  public static void loadVisualizationApi(String version, Runnable onLoad,
      String... packages) {
    loadVisualizationApi(version, onLoad, AbstractDrawOptions.createJsArray(packages));
  }

  public static native void loadApi(String api, String version, 
      JavaScriptObject settings) /*-{
    $wnd.google.load(api, version, settings);
  }-*/;

  private static native JavaScriptObject createSettings(Runnable onLoad, 
      JsArrayString packages) /*-{
    var callback = function() {
      @com.google.gwt.visualization.client.ExceptionHelper::runProtected(Ljava/lang/Runnable;)(onLoad);
    }
  	return {'callback' : callback, 'packages' : packages};
  }-*/;
}

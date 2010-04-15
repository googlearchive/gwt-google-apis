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

import com.google.gwt.ajaxloader.client.AjaxLoader;
import com.google.gwt.ajaxloader.client.ArrayHelper;
import com.google.gwt.ajaxloader.client.AjaxLoader.AjaxLoaderOptions;
import com.google.gwt.core.client.JsArrayString;

/**
 * This class contains utility methods that are of use to the API in general.  
 * Incldes a wrapper for loading the Visualization API using the google Ajax loader.
 */
public class VisualizationUtils {
   
  public static void loadVisualizationApi(Runnable onLoad, String... packages) {
    loadVisualizationApi("1", onLoad, ArrayHelper.toJsArrayString(packages));
  }

  public static void loadVisualizationApi(String version, Runnable onLoad,
      JsArrayString packages) {
    // TODO: map which packages have already been loaded, so that we can
    // call the loader only when necessary
    AjaxLoaderOptions options = AjaxLoaderOptions.newInstance();
    options.setPackages(packages);
    AjaxLoader.loadApi("visualization", version, onLoad, options); 
  }

  public static void loadVisualizationApi(String version, Runnable onLoad,
      String... packages) {
    loadVisualizationApi(version, onLoad, ArrayHelper.toJsArrayString(packages));
  }
  
  private VisualizationUtils() {
    // Do not allow this class to be instantiated.
  }
}

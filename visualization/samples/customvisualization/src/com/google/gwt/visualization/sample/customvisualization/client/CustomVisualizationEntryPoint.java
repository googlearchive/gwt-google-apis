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
package com.google.gwt.visualization.sample.customvisualization.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.visualization.client.AbstractVisualization;
import com.google.gwt.visualization.client.AbstractVisualization.VisualizationFactory;

/**
 * The EntryPoint implementation for the GWT application.
 * 
 */
class CustomVisualizationEntryPoint implements EntryPoint {

  private static native void callOnLoadCallback(String name) /*-{
    if ($wnd.onLoadCallback != undefined) {
      $wnd.onLoadCallback(name);
    }
  }-*/;

  public CustomVisualizationEntryPoint() {
  }

  public void onModuleLoad() {

    // Register the visualization
    AbstractVisualization.registerVisualization("CustomVisualization",
        new VisualizationFactory() {

          public AbstractVisualization<?> create() {
            return new CustomVisualization();
          }
        });

    callOnLoadCallback(GWT.getModuleName());
  }
}

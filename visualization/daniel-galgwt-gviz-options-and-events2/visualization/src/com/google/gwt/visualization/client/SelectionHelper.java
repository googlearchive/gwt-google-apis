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
import com.google.gwt.visualization.client.Selectable.SelectCallback;

/**
 * SelectionHelper implements selection related functions.
 *
 */
public class SelectionHelper {

  public static final native void addListener(Visualization<?> visualization, final SelectCallback callback) /*-{
    $wnd.google.visualization.events.addListener(visualization, 'select', function() {
      @com.google.gwt.visualization.client.SelectionHelper::onSelectCallback(Lcom/google/gwt/visualization/client/Visualization;Lcom/google/gwt/visualization/client/Selectable$SelectCallback;)(visualization, callback);
    });
  }-*/;
  
  public static final native Selection getSelection(Visualization<?> visualization) /*-{
    return visualization.getSelection();
  }-*/;

  public static final native void setSelection(Visualization<?> visualization, Selection sel) /*-{
    visualization.setSelection(sel);
  }-*/;
  
  private static void fireAndCatch(UncaughtExceptionHandler handler,
      Visualization<? extends AbstractDrawOptions> visualization, SelectCallback callback) {
    try {
      fireImpl(visualization, callback);
    } catch (Throwable e) {
      handler.onUncaughtException(e);
    }
  }

  private static void fireImpl(Visualization<?> visualization, SelectCallback callback) {
    callback.onSelect(visualization);
  }
  
  @SuppressWarnings("unused")
  private static void onSelectCallback(Visualization<?> visualization, SelectCallback callback) {
    UncaughtExceptionHandler handler = GWT.getUncaughtExceptionHandler();
    if (handler != null) {
      fireAndCatch(handler, visualization, callback);
    } else {
      fireImpl(visualization, callback);
    }
  }

  protected SelectionHelper() {
  }
}

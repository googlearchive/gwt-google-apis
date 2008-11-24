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
package com.google.gwt.gears.client.httprequest;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;

/**
 * Used for monitoring the progress of an upload. 
 */
public final class HttpRequestUpload extends JavaScriptObject {

  // Called from JSNI
  @SuppressWarnings("unused")
  private static void fireOnProgress(ProgressHandler handler,
      ProgressEvent event) {
    if (handler == null) {
      return;
    }

    UncaughtExceptionHandler ueh = GWT.getUncaughtExceptionHandler();
    if (ueh != null) {
      try {
        handler.onProgress(event);
      } catch (Throwable e) {
        ueh.onUncaughtException(e);
      }
    } else {
      handler.onProgress(event);
    }
  }

  protected HttpRequestUpload() {
    // required for overlay types
  }

  /**
   * Sets an event handler that fires as PUT or POST data is uploaded.
   * 
   * @param handler
   *          an event handler that fires as PUT or POST data is uploaded.
   */
  public native void setProgressHandler(ProgressHandler handler) /*-{
     this.onprogress = function(progressObject) {
       @com.google.gwt.gears.client.httprequest.HttpRequestUpload::fireOnProgress(Lcom/google/gwt/gears/client/httprequest/ProgressHandler;Lcom/google/gwt/gears/client/httprequest/ProgressEvent;)(handler, progressObject);
     };
   }-*/;
}

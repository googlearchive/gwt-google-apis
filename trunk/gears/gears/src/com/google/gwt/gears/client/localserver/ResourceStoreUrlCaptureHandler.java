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
package com.google.gwt.gears.client.localserver;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Provides an interface to implement in order to receive capture events.
 */
public interface ResourceStoreUrlCaptureHandler {
  /**
   * Encapsulates the arguments to the capture event.
   */
  final class ResourceStoreUrlCaptureEvent extends JavaScriptObject {
    protected ResourceStoreUrlCaptureEvent() {
      // Required for overlay types
    }

    /**
     * Returns the capture id associated with this event.
     * 
     * @return capture id associated with this event
     */
    public native int getCaptureId() /*-{
      return this.captureId;
    }-*/;

    /**
     * Returns the URL associated with this capture event.
     * 
     * @return URL associated with this capture event
     */
    public native String getUrl() /*-{
      return this.url;
    }-*/;

    /**
     * Returns <code>true</code> if the capture succeeded.
     * 
     * @return <code>true</code> if the capture succeeded
     */
    public native boolean isSuccess() /*-{
      return this.success;
    }-*/;
  }

  /**
   * Method to be invoked when a {@link ResourceStore} fires it capture event.
   * 
   * @param event contains the properties of the event
   */
  void onCapture(ResourceStoreUrlCaptureEvent event);
}

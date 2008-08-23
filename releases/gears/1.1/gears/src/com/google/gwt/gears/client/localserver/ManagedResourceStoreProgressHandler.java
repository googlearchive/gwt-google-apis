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
 * Provides an interface to implement in order to receive progress events.
 */
public interface ManagedResourceStoreProgressHandler {
  /**
   * Encapsulates the arguments to the progress event.
   */
  final class ManagedResourceStoreProgressEvent extends JavaScriptObject {
    protected ManagedResourceStoreProgressEvent() {
      // Required for overlay types
    }

    /**
     * Returns the number of files completed thus far.
     * 
     * @return the number of files completed thus far
     */
    public native double getFilesComplete() /*-{
      return this.filesComplete;
    }-*/;

    /**
     * Returns the total number of files that need to be captured.
     * 
     * @return the total number of files that need to be captured
     */
    public native double getFilesTotal() /*-{
      return this.filesTotal;
    }-*/;
  }

  /**
   * Method to be invoked when a {@link ManagedResourceStore} fires it progress
   * event.
   * 
   * @param event contains the properties of the event
   */
  void onProgress(ManagedResourceStoreProgressEvent event);
}
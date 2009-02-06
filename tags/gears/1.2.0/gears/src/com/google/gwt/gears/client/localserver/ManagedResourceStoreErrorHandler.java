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
 * Provides an interface to implement in order to receive update error events.
 */
public interface ManagedResourceStoreErrorHandler {
  /**
   * Encapsulates the arguments to the update error event.
   */
  final class ManagedResourceStoreErrorEvent extends JavaScriptObject {
    protected ManagedResourceStoreErrorEvent() {
      // Required for overlay types
    }

    /**
     * Returns the error message associated with the update.
     * 
     * @return error message associated with the update
     */
    public native String getMessage() /*-{
      return this.message;
    }-*/;
  }

  /**
   * Method to be invoked when a {@link ManagedResourceStore} fires it update
   * error event.
   * 
   * @param error contains the properties of the event
   */
  void onError(ManagedResourceStoreErrorEvent error);
}
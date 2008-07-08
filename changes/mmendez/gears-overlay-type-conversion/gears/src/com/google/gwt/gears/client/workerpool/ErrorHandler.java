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

package com.google.gwt.gears.client.workerpool;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * 
 */
public interface ErrorHandler {
  /**
   * 
   */
  final class ErrorEvent extends JavaScriptObject {
    protected ErrorEvent() {
      // Required for overlay types
    }

    public native int getLineNumber() /*-{
      return this.lineNumber;
    }-*/;

    public native String getMessage() /*-{
      return this.message;
    }-*/;
  }

  /**
   * This callback provides functionality in workers similar to the
   * window.onerror property. If set, it will be called for any unhandled errors
   * that occur inside a worker.
   * 
   * You can use this callback to implement "last-chance" error handling for
   * your workers. For example, you could log all unhandled errors into the
   * Database module.
   * 
   * NOTE: This callback can only be set from child workers.
   */
  boolean onError(ErrorEvent errorObject);
}

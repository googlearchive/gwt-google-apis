/*
 * Copyright 2010 Google Inc.
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
package com.google.gwt.gadgets.client.osapi;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Utility class for work with callbacks. For internal use only.
 */
class CallbackUtil {

  /**
   * Turns a {@link Callback} into a JavaScript function usable with underlying
   * JavaScript API.
   *
   * @param callback The {@link Callback} instance to wrap into JavaScript
   *          function.
   * @return JavaScript function usable with underlying JavaScript API.
   */
  static final native <T extends JavaScriptObject> JavaScriptObject getJsFunction(
      Callback<T> callback) /*-{
    return function(object) {
      @com.google.gwt.gadgets.client.osapi.CallbackUtil::onDone(Lcom/google/gwt/gadgets/client/osapi/Callback;Lcom/google/gwt/core/client/JavaScriptObject;)(callback, object);
    };
  }-*/;

  private static native OsapiError getError(JavaScriptObject result) /*-{
    return result.error;
  }-*/;

  private static native boolean hasError(JavaScriptObject result) /*-{
    return (result.error !== undefined);
  }-*/;

  /**
   * Invokes {@link Callback#onFail(OsapiError)} or
   * {@link Callback#onSuccess(JavaScriptObject)} method of the callback,
   * depending on the passed result.
   */
  @SuppressWarnings("unused")
  private static <T extends JavaScriptObject> void onDone(Callback<T> callback,
      T result) {
    if (hasError(result)) {
      callback.onFail(getError(result));
    } else {
      callback.onSuccess(result);
    }
  }

  /**
   * Prevents instantiation of this class.
   */
  private CallbackUtil() {
  }
}

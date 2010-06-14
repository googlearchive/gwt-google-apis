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
 * A class implementing a request batching other requests.
 */
public class BatchRequest extends JavaScriptObject {

  /**
   * Returns the new instance of {@link BatchRequest} class.
   *
   * @return The new instance of {@link BatchRequest} class.
   */
  public static final native BatchRequest newInstance() /*-{
    return $wnd.osapi.newBatch();
  }-*/;

  /**
   * Required by {@link JavaScriptObject} policy.
   */
  protected BatchRequest() {
  }

  /**
   * Adds an opensocial request to this batch request.
   *
   * @param request OpenSocial request to batch.
   * @param callback Callback to be called when request finishes.
   */
  public final <T extends JavaScriptObject> void add(
      final OsapiRequest<T> request, final Callback<T> callback) {
    addNative(request, CallbackUtil.getJsFunction(callback));
  }

  /**
   * Executes this batch request. After invoking this method
   * {@link BatchRequest#add} methods should not be called on this object.
   */
  public final native void execute() /*-{
    var self = this;
    this.execute(function(data) {
      for (var key in self.__gwt_wrapper_callback_store) {
        if (data[key] == null) {
          // expected object might not be returned in case of error,
          // in that case top level object is passed for error information
          self.__gwt_wrapper_callback_store[key](data);
        } else {
          self.__gwt_wrapper_callback_store[key](data[key]);
        }
      }
    });
  }-*/;

  private native <T extends JavaScriptObject> void addNative(
      OsapiRequest<T> request, JavaScriptObject callback) /*-{
    // __gwt_wrapper_request_count and __gwt_wrapper_callback_store
    // are either both initialised or both undefined
    if (this.__gwt_wrapper_request_count === undefined) {
      this.__gwt_wrapper_request_count = 0;
      this.__gwt_wrapper_callback_store = {};
    }
    var key = this.__gwt_wrapper_request_count++;
    this.__gwt_wrapper_callback_store[key] = callback;
    this.add(key.toString(), request);
  }-*/;
}
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
 * A class representing OpenSocial request.
 *
 * @param <T> The type of object returned by the request.
 */
public abstract class OsapiRequest<T extends JavaScriptObject> extends
    JavaScriptObject {

  /**
   * Required by {@link JavaScriptObject} policy.
   */
  protected OsapiRequest() {
  }

  /**
   * Executes this requests.
   *
   * @param callback Callback to be invoked when request finishes.
   */
  public final void execute(Callback<T> callback) {
    nativeExecute(CallbackUtil.getJsFunction(callback));
  }

  /**
   * Invokes underlying JavaScript API to execute this request with provided
   * {@link Callback} instance.
   *
   * @param callback The {@link Callback} instance to use for this request.
   */
  protected final native void nativeExecute(JavaScriptObject callback) /*-{
    this.execute(callback);
  }-*/;
}

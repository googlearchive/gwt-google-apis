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
 * An interface for callbacks for OpenSocial requests.
 *
 * @param <T> Type of object returned by the request.
 */
public interface Callback<T extends JavaScriptObject> {

  /**
   * Called when request fails.
   *
   * @param error The object encapsulating information about the error.
   */
  void onFail(OsapiError error);

  /**
   * Called when request succeeds.
   *
   * @param object The object being the result of the request.
   */
  void onSuccess(T object);
}

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
package com.google.gwt.opensocial.client;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Contains the requested server data mapped to the requested keys.
 *
 * @see "http://code.google.com/apis/opensocial/docs/0.8/reference/#opensocial.DataResponse"
 */
public class DataResponse extends JavaScriptObject {
  protected DataResponse() {
  }

  /**
   * Gets the ResponseItem for the requested field.
   */
  public final native ResponseItem get(String key) /*-{
    return this.get(key);
  }-*/;

  /**
   * If the entire request had a batch level error, returns the error message.
   */
  public final native String getErrorMessage() /*-{
    return this.getErrorMessage();
  }-*/;

  /**
   * Returns true if there was an error in fetching this data from the server.
   */
  public final native boolean hadError()/*-{
    return this.hadError();
  }-*/;
}

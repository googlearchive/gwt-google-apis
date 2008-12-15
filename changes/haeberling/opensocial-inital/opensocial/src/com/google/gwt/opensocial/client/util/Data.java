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
package com.google.gwt.opensocial.client.util;

import com.google.gwt.core.client.JavaScriptObject;

public class Data extends JavaScriptObject {
  protected Data() {
  }

  /**
   * Get String associated with the key
   */
  public final native boolean getBoolean(String key) /*-{
    return this[key];
  }-*/;

  /**
   * Get data associated with the key
   */
  public final native Data getData(String key) /*-{
    return this[key];
  }-*/;

  /**
   * Get String associated with the key
   */
  public final native double getDouble(String key) /*-{
    return this[key];
  }-*/;

  /**
   * Get String associated with the key
   */
  public final native int getInt(String key) /*-{
    return this[key];
  }-*/;

  /**
   * Get String associated with the key
   */
  public final native String getString(String key) /*-{
    return this[key];
  }-*/;
}

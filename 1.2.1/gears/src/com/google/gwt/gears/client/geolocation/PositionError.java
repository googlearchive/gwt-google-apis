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
package com.google.gwt.gears.client.geolocation;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Arguments returned when a Geolocation fails. 
 */
public final class PositionError extends JavaScriptObject {

  protected PositionError() {
    // required for overlay types
  }

  /**
   * Gets a unique identifier for this type of message.
   * 
   * @return A unique identifier for this type of message.
   */
  public native int getCode()/*-{
    return this.code;
  }-*/;

  /**
   * Gets a human readable message, suitable for logs.
   * 
   * @return a human readable message, suitable for logs.
   */
  public native String getMessage()/*-{
    return this.message;
  }-*/;
}

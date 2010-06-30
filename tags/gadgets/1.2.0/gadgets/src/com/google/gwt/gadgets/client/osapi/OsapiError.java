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
 * A class encapsulating the information about the error.
 */
public class OsapiError extends JavaScriptObject {

  /**
   * Required by {@link JavaScriptObject} policy.
   */
  protected OsapiError() {
  }

  /**
   * Returns a code that indicates the actual error that occurred.
   *
   * @return A code that indicates the actual error that occurred.
   */
  public final native int getCode() /*-{
    return this.code;
  }-*/;

  /**
   * Returns a {@link String} providing a short description of the error.
   *
   * @return A {@link String} providing a short description of the error.
   */
  public final native String getMessage() /*-{
    return this.message;
  }-*/;
}

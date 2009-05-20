/*
 * Copyright 2009 Google Inc.
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
package com.google.gwt.language.client;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Wrapper for javascript error object that contains details of errors.
 */
public class Error extends JavaScriptObject {

  protected Error() { }

  /**
   * A HTTP-style error code
   *
   * @return error code as integer
   */
  public final native int getCode() /*-{
    return this.code;
  }-*/;

  /**
   * A human readable string description of the error.
   *
   * @return error message as string
   */
  public final native String getMessage() /*-{
    return this.message;
  }-*/;
}

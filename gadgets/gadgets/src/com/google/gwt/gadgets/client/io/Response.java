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
package com.google.gwt.gadgets.client.io;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;

/**
 * A class wrapping the response returned by gadget container.
 *
 * @param <T> Type of parsed data of the response.
 */
public class Response<T> extends JavaScriptObject {

  protected Response() {
  }

  /**
   * Returns parsed data of the response, if applicable. This will contain a
   * different type of data depending on the type of request that was made. The
   * raw response text is returned if the response could not be parsed.
   *
   * See <a href="http://wiki.opensocial.org/index.php?title=Gadgets.io_(v0.9)#gadgets.io.ContentType"
   * >gadgets.io.ContentType</a> for information about what to expect in this
   * field.
   *
   * @return parsed data of the response, if applicable.
   */
  public final native T getData() /*-{
    return (this.data == null) ? null : this.data;
  }-*/;

  /**
   * Returns an array of any errors that occurred when making the request.
   *
   * @return an array of any errors that occurred when making the request or
   *         <code>null</code> if absent.
   */
  public final native JsArrayString getErrors() /*-{
    return (this.errors == null) ? null : this.errors;
  }-*/;

  /**
   * Returns the response header.
   *
   * @param header Header name.
   * @return Response header or <code>null</code> if absent.
   */
  public final native String getHeader(String header) /*-{
    var h = this.headers && this.headers[header];
    return (h == null) ? null : h;
  }-*/;

  /**
   * If this value is specified, the user needs to visit an external page to
   * approve the gadget's request to access data. Use of a pop-up window to
   * direct the user to the external page is recommended. Once the user has
   * approved access, the gadget can repeat the makeRequest call to retrieve the
   * data.
   *
   * @return The URL of external page to visit or <code>null</code> if absent.
   */
  public final native String getOauthApprovalUrl() /*-{
    return (this.oauthApprovalUrl == null) ? null : this.oauthApprovalUrl;
  }-*/;

  /**
   * If this value is specified, it indicates an OAuth-related error occurred.
   * The value will be one of a set of string constants that can be used for
   * programmatically detecting errors. The constants are undefined for
   * opensocial-0.8, but implementers should attempt to agree on a set of useful
   * constant values for standardization in opensocial-0.9.
   *
   * @return OAuth-related error constant or <code>null</code> if absent.
   */
  public final native String getOauthError() /*-{
    return (this.oauthError == null) ? null : this.oauthError;
  }-*/;

  /**
   * If this value is specified, it indicates an OAuth-related error occurred.
   * The value is free-form text that can be used to provide debugging
   * information for gadget developers.
   *
   * @return Free form text describing OAuth-related error or <code>null</code>
   *         if absent.
   */
  public final native String getOauthErrorText() /*-{
    return (this.oauthErrorText == null) ? null : this.oauthErrorText;
  }-*/;

  /**
   * Returns the status code of the response.
   *
   * @return the status code of the response.
   */
  public final native int getStatusCode() /*-{
    return (this.rc == null) ? -1 : this.rc;
  }-*/;

  /**
   * Returns unparsed data of the response.
   *
   * @return unparsed data of the response.
   */
  public final native String getText() /*-{
    return (this.text == null) ? null : this.text;
  }-*/;
}

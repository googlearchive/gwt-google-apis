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
package com.google.gwt.gears.client.httprequest;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.gears.client.blob.Blob;

/**
 * The HttpRequest API implements a subset of the W3C XmlHttpRequest
 * specification, and makes it available in both workers and the main HTML page.
 */
public final class HttpRequest extends JavaScriptObject {

  // Called from JSNI
  @SuppressWarnings("unused")
  private static void fireOnProgress(ProgressHandler handler,
      ProgressEvent event) {
    if (handler == null) {
      return;
    }

    UncaughtExceptionHandler ueh = GWT.getUncaughtExceptionHandler();
    if (ueh != null) {
      try {
        handler.onProgress(event);
      } catch (Throwable e) {
        ueh.onUncaughtException(e);
      }
    } else {
      handler.onProgress(event);
    }
  }

  /*
   * Method called when the JavaScript XmlHttpRequest object's readyState
   * reaches 4 (LOADED).
   * 
   * NOTE: this method is called from JSNI
   */
  @SuppressWarnings("unused")
  private static void fireRequestComplete(RequestCallback handler,
      HttpRequest request) {
    if (handler == null) {
      return;
    }

    UncaughtExceptionHandler ueh = GWT.getUncaughtExceptionHandler();
    if (ueh != null) {
      try {
        handler.onResponseReceived(request);
      } catch (Throwable e) {
        ueh.onUncaughtException(e);
      }
    } else {
      handler.onResponseReceived(request);
    }
  }

  protected HttpRequest() {
    // Required for overlay types
  }

  /**
   * Cancels the request.
   */
  public native void abort()/*-{
    this.abort();
  }-*/;

  /**
   * Returns a string containing the entire set of HTTP headers in the server
   * response.
   * 
   * @return a string containing the entire set of HTTP headers in the server
   *         response.
   */
  public native String getAllResponseHeaders()/*-{
    return this.getAllResponseHeaders();
  }-*/;;

  /**
   * Returns the state of the request.
   * <table>
   * <tr>
   * <td>0</td>
   * <td>Uninitialized</td>
   * </tr>
   * <tr>
   * <td>1</td>
   * <td>Open</td>
   * </tr>
   * <tr>
   * <td>2</td>
   * <td>Sent</td>
   * </tr>
   * <tr>
   * <td>3</td>
   * <td>Interactive</td>
   * </tr>
   * <tr>
   * <td>4</td>
   * <td>Complete</td>
   * </tr>
   * </table>
   * 
   * @return the state of the request
   */
  public native int getReadyState()/*-{
    return this.readyState;
  }-*/;

  /**
   * Returns the response body as a {@link Blob}. This property can be read when
   * the request is in the Complete state.
   * 
   * @return the response body as a {@link Blob}
   */
  public native Blob getResponseBlob()/*-{
    return this.responseBlob;
  }-*/;;

  /**
   * Returns the value of a specific HTTP header in the server response.
   * 
   * @param name the name of the header value to return
   * @return the value of the header
   */
  public native String getResponseHeader(String name)/*-{
    return this.getResponseHeader(name);
  }-*/;;

  /**
   * Returns the response body as a string. This property can be read when the
   * request is in the Interactive or Complete state.
   * 
   * @return the response body as a string
   */
  public native String getResponseText()/*-{
    return this.responseText;
  }-*/;

  /**
   * Returns the status as a number (e.g. 404 for "Not Found" or 200 for "OK").
   * This property can be read when the request is in the Interactive or
   * Complete state.
   * 
   * @return the status as a number
   */
  public native int getStatus()/*-{
    return this.status;
  }-*/;

  /**
   * Returns the status as a string (e.g. "Not Found" or "OK"). This property
   * can be read when the request is in the Interactive or Complete state.
   * 
   * @return the status as a string
   */
  public native String getStatusText()/*-{
    return this.statusText;
  }-*/;

  /**
   * Returns an {@link HttpRequestUpload} object for accessing properties
   * associated with POST or PUT data uploads.
   * 
   * @return an {@link HttpRequestUpload} object for accessing properties
   *         associated with POST or PUT data uploads.
   */
  public native HttpRequestUpload getUpload()/*-{
    return this.upload;
  }-*/;

  /**
   * Specifies the method and URL of a request.
   * 
   * @param method a value of "GET", "POST", "HEAD" or another HTTP method
   *          listed in the W3C specification
   * @param url either a relative or complete URL which must be from the same
   *          origin as the current context
   */
  public native void open(String method, String url)/*-{
    this.open(method, url);
  }-*/;

  /**
   * Sends the request.
   */
  public native void send()/*-{
    this.send();
  }-*/;

  /**
   * Sends the request.
   * 
   * @param postData {@link Blob} to be sent as the body of a POST or PUT
   *          request
   */
  public native void send(Blob postData)/*-{
    this.send(postData);
  }-*/;

  /**
   * Sends the request.
   * 
   * @param postData {@link Blob} to be sent as the body of a POST or PUT
   *          request
   * @param callback an event handler that fires as response data is downloaded.
   */
  public void send(Blob postData, RequestCallback callback) {
    setCallback(callback);
    send(postData);
  }

  /**
   * Sends the request.
   * 
   * @param callback an event handler that fires as response data is downloaded.
   */
  public void send(RequestCallback callback) {
    setCallback(callback);
    send();
  }

  /**
   * Sends the request.
   * 
   * @param postData String to be sent as the body of a POST or PUT request
   */
  public native void send(String postData)/*-{
    this.send(postData);
  }-*/;

  /**
   * Sends the request.
   * 
   * @param postData String to be sent as the body of a POST or PUT request
   * @param callback an event handler that fires as response data is downloaded.
   */
  public void send(String postData, RequestCallback callback) {
    setCallback(callback);
    send(postData);
  }

  /**
   * Sets an event handler that fires as response data is downloaded.
   * 
   * @param handler an event handler that fires as response data is downloaded.
   */
  public native void setCallback(RequestCallback handler) /*-{
    var request = this;
    this.onreadystatechange = function() {
      if (request.readyState == 4) {
        @com.google.gwt.gears.client.httprequest.HttpRequest::fireRequestComplete(Lcom/google/gwt/gears/client/httprequest/RequestCallback;Lcom/google/gwt/gears/client/httprequest/HttpRequest;)(handler, request);
        request.onreadystatechange = null;
        request.onprogress = null;
        request.upload.onprogress = null;
      }
    };
  }-*/;

  /**
   * Sets an event handler that fires as response data is downloaded.
   * 
   * @param handler an event handler that fires as response data is downloaded.
   */
  public native void setProgressHandler(ProgressHandler handler) /*-{
    this.onprogress = function(progressEvent) {
      @com.google.gwt.gears.client.httprequest.HttpRequest::fireOnProgress(Lcom/google/gwt/gears/client/httprequest/ProgressHandler;Lcom/google/gwt/gears/client/httprequest/ProgressEvent;)(handler, progressEvent);
    };
  }-*/;

  /**
   * Adds the header to the set of HTTP headers to be sent.
   * 
   * @param name the HTTP header to set
   * @param value the value of the header
   */
  public native void setRequestHeader(String name, String value)/*-{
    this.setRequestHeader(name, value);
  }-*/;

}

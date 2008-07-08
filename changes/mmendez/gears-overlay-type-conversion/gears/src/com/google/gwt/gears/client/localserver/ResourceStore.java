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
package com.google.gwt.gears.client.localserver;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.dom.client.Element;
import com.google.gwt.gears.client.impl.Utils;

/**
 * Manages an ad-hoc collection of captured URLs, which can be served locally.
 */
public final class ResourceStore extends JavaScriptObject {
  /**
   * This method is called from JSNI code.
   */
  @SuppressWarnings("unused")
  private static void fireUrlCaptureCallback(URLCaptureCallback callback,
      String url, boolean success, int captureId) {
    UncaughtExceptionHandler handler = GWT.getUncaughtExceptionHandler();
    if (handler != null) {
      fireUrlCaptureCallbackAndCatch(handler, callback, url, success, captureId);
    } else {
      fireUrlCaptureCallbackImpl(callback, url, success, captureId);
    }
  }

  private static void fireUrlCaptureCallbackAndCatch(
      UncaughtExceptionHandler handler, URLCaptureCallback callback,
      String url, boolean success, int captureId) {
    try {
      fireUrlCaptureCallbackImpl(callback, url, success, captureId);
    } catch (Throwable e) {
      handler.onUncaughtException(e);
    }
  }

  private static void fireUrlCaptureCallbackImpl(URLCaptureCallback callback,
      String url, boolean success, int captureId) {
    if (success) {
      callback.onCaptureSuccess(url, captureId);
    } else {
      callback.onCaptureFailure(url, captureId);
    }
  }

  protected ResourceStore() {
    // Required for overlay types
  }

  /**
   * Aborts the specified capture.
   */
  public native void abortCapture(int captureId) /*-{
    this.abortCapture(captureId);
  }-*/;

  /**
   * Initiates an update that runs asynchronously in the background, and returns
   * immediately. The return value is a captureId which can be passed to
   * abortCapture to cancel the update.
   * 
   * Relative URLs are interpreted according to the current page's location.
   * Upon completion of each URL the callback function is invoked.
   * 
   * An additional HTTP header is added when Gears is capturing URLs
   * X-Gears-Google: 1
   */
  public int capture(URLCaptureCallback callback, String... urls) {
    return capture(callback, Utils.toJavaScriptArray(urls));
  }

  /**
   * Captures the contents of the file indicated by the fileInputElement into
   * the store and associates that content with the given URL. The
   * fileInputElement argument must be a reference to an <input type=file> HTML
   * element.
   */
  public native void captureFile(Element fileInputElement, String url) /*-{
    this.captureFile(fileInputElement, url);
  }-*/;

  /**
   * Copies a cached URL.
   */
  public native void copy(String srcUrl, String destUrl) /*-{
    this.copy(srcUrl, destUrl);
  }-*/;

  /**
   * Returns a {@link FileSubmitter}, which is used to submit URLs that are
   * contained in this store in HTML form submissions.
   */
  public native FileSubmitter createFileSubmitter() /*-{
    return this.createFileSubmitter();
  }-*/;

  /**
   * Returns all HTTP headers associated with the captured URL.
   */
  public native String getAllHeaders(String url) /*-{
    return this.getAllHeaders(url);
  }-*/;

  /**
   * Returns the leaf file name associated with url that was previously captured
   * by calling captureFile. Note that if url was captured by a method other
   * than calling captureFile then an empty string will be returned and no
   * exception will be thrown.
   */
  public native String getCapturedFileName(String url) /*-{
    return this.getCapturedFileName(url);
  }-*/;

  /**
   * Returns a specific HTTP header associated with the captured URL.
   */
  public native String getHeader(String url, String name) /*-{
    return this.getHeader(url, name);
  }-*/;

  /**
   * Returns the name of this store.
   */
  public native String getName() /*-{
    return this.name;
  }-*/;

  /**
   * Returns the cookie requirements of the store. For further details see <a
   * href="http://code.google.com/apis/gears/api_localserver.html#required_cookie">Cookies
   * and the LocalServer</a>. If the requiredCookie is empty, then resources
   * within this store are always served locally, provided the store is enabled.
   */
  public native String getRequiredCookie() /*-{
    return this.requiredCookie;
  }-*/;

  /**
   * Returns <code>true</code> if the URL is cached in this store.
   */
  public native boolean isCaptured(String url) /*-{
    return this.isCaptured(url);
  }-*/;

  /**
   * Returns <code>true</code> if local serving is enabled for this store,
   * <code>false</code> otherwise.
   */
  public native boolean isEnabled() /*-{
    return this.enabled;
  }-*/;

  /**
   * Removes the cached URL from this store.
   */
  public native void remove(String url) /*-{
    this.remove(url);
  }-*/;

  /**
   * Renames a URL that is cached in this store.
   */
  public native void rename(String srcUrl, String destUrl) /*-{
    this.rename(srcUrl, destUrl);
  }-*/;

  /**
   * Sets the local serving state of this store.
   */
  public native void setEnabled(boolean enabled) /*-{
    this.enabled = enabled;
  }-*/;

  private native int capture(URLCaptureCallback callback, JavaScriptObject urls) /*-{
    var jsCallback = function(url, success, captureId) {
      @com.google.gwt.gears.client.localserver.ResourceStore::fireUrlCaptureCallback(Lcom/google/gwt/gears/client/localserver/URLCaptureCallback;Ljava/lang/String;ZI)(callback,url,success,captureId);
    };
    return this.capture(urls, jsCallback);
  }-*/;
}
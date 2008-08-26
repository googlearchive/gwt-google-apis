/*
 * // * Copyright 2008 Google Inc.
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
  // Called from JSNI code.
  @SuppressWarnings("unused")
  private static void fireCapture(ResourceStoreUrlCaptureHandler handler,
      ResourceStoreUrlCaptureHandler.ResourceStoreUrlCaptureEvent event) {
    UncaughtExceptionHandler ueh = GWT.getUncaughtExceptionHandler();
    if (ueh != null) {
      try {
        handler.onCapture(event);
      } catch (Throwable e) {
        ueh.onUncaughtException(e);
      }
    } else {
      handler.onCapture(event);
    }
  }

  protected ResourceStore() {
    // Required for overlay types
  }

  /**
   * Aborts the specified capture.
   * 
   * @param captureId id of the capture to abort
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
   * Upon completion of each URL the handler instance is notified.
   * 
   * An additional HTTP header is added when Gears is capturing URLs
   * X-Gears-Google: 1
   * 
   * @param callback invoked per capture
   * @param urls set of URLs to capture
   * @return capture id for capture request
   * 
   * @throws com.google.gwt.core.client.JavaScriptException if any of the URLs
   *           are not from the same origin as the current page
   */
  public int capture(ResourceStoreUrlCaptureHandler callback, String... urls) {
    return capture(callback, Utils.toJavaScriptArray(urls));
  }

  /**
   * Captures the contents of the file indicated by the
   * <code>fileInputElement</code> into the store and associates that content
   * with the given URL. The <code>fileInputElement</code> argument must be a
   * reference to an &lt;input type=file&gt; HTML element.
   * 
   * @param fileInputElement element containing the file to capture
   * @param url URL to associate with the file capture
   * 
   * @throws com.google.gwt.core.client.JavaScriptException if the URL is not
   *           from the same origin as the current page or if the file cannot be
   *           read
   */
  public native void captureFile(Element fileInputElement, String url) /*-{
    this.captureFile(fileInputElement, url);
  }-*/;

  /**
   * Copies a cached URL.
   * 
   * @param srcUrl source URL
   * @param destUrl destination URL
   * 
   * @throws com.google.gwt.core.client.JavaScriptException if the source or
   *           destination URLs are not from the same origin as the current page
   */
  public native void copy(String srcUrl, String destUrl) /*-{
    this.copy(srcUrl, destUrl);
  }-*/;

  /**
   * Returns a {@link FileSubmitter}, which is used to submit URLs that are
   * contained in this store in HTML form submissions.
   * 
   * @return {@link FileSubmitter} instance
   */
  public native FileSubmitter createFileSubmitter() /*-{
    return this.createFileSubmitter();
  }-*/;

  /**
   * Returns all HTTP headers associated with the captured URL.
   * 
   * @param url captured URL
   * @return all HTTP headers associated with the URL
   * 
   * @throws com.google.gwt.core.client.JavaScriptException if the URL is not
   *           from the same origin as the current page
   */
  public native String getAllHeaders(String url) /*-{
    return this.getAllHeaders(url);
  }-*/;

  /**
   * Returns the leaf file name associated with <code>url</code> that was
   * previously captured by calling captureFile. Note that if <code>url</code>
   * was captured by a method other than calling captureFile then an empty
   * string will be returned and no exception will be thrown.
   * 
   * @param url URL whose file name we want
   * @return leaf file name associated with the URL or an empty if there is none
   * 
   * @throws com.google.gwt.core.client.JavaScriptException if the URL is not
   *           from the same origin as the current page
   */
  public native String getCapturedFileName(String url) /*-{
    return this.getCapturedFileName(url);
  }-*/;

  /**
   * Returns a specific HTTP header associated with the captured URL.
   * 
   * @param url captured URL
   * @param name HTTP header name
   * @return HTTP header value
   * 
   * @throws com.google.gwt.core.client.JavaScriptException if the URL is not
   *           from the same origin as the current page
   */
  public native String getHeader(String url, String name) /*-{
    return this.getHeader(url, name);
  }-*/;

  /**
   * Returns the name of this store.
   * 
   * @return name of the this store
   */
  public native String getName() /*-{
    return this.name;
  }-*/;

  /**
   * Returns the cookie requirements of the store. For further details see <a
   * href="http://code.google.com/apis/gears/api_localserver.html#required_cookie">Cookies
   * and the LocalServer</a>. If the requiredCookie is empty, then resources
   * within this store are always served locally, provided the store is enabled.
   * 
   * @return required cookie for this store
   */
  public native String getRequiredCookie() /*-{
    return this.requiredCookie;
  }-*/;

  /**
   * Returns <code>true</code> if the URL is cached in this store.
   * 
   * @param url URL to test
   * @return <code>true</code> if the URL is cached in this store
   * 
   * @throws com.google.gwt.core.client.JavaScriptException if the URL is not
   *           from the same origin as the current page
   */
  public native boolean isCaptured(String url) /*-{
    return this.isCaptured(url);
  }-*/;

  /**
   * Returns <code>true</code> if local serving is enabled for this store,
   * <code>false</code> otherwise.
   * 
   * @return <code>true</code> if local serving is enabled for this store,
   *         <code>false</code> otherwise
   */
  public native boolean isEnabled() /*-{
    return this.enabled;
  }-*/;

  /**
   * Removes the cached URL from this store.
   * 
   * @param url URL to remove
   * 
   * @throws com.google.gwt.core.client.JavaScriptException if the URL is not
   *           from the same origin as the current page
   */
  public native void remove(String url) /*-{
    this.remove(url);
  }-*/;

  /**
   * Renames a URL that is cached in this store.
   * 
   * @param srcUrl source URL
   * @param destUrl destination URL
   * 
   * @throws com.google.gwt.core.client.JavaScriptException if the source or
   *           destination URLs are not from the same origin as the current page
   */
  public native void rename(String srcUrl, String destUrl) /*-{
    this.rename(srcUrl, destUrl);
  }-*/;

  /**
   * Sets the local serving state of this store.
   * 
   * @param enabled <code>true</code> to enable local serving,
   *          <code>false</code> otherwise
   */
  public native void setEnabled(boolean enabled) /*-{
    this.enabled = enabled;
  }-*/;

  private native int capture(ResourceStoreUrlCaptureHandler callback,
      JavaScriptObject urls) /*-{
    var jsCallback = function(url, success, captureId) {
      @com.google.gwt.gears.client.localserver.ResourceStore::fireCapture(Lcom/google/gwt/gears/client/localserver/ResourceStoreUrlCaptureHandler;Lcom/google/gwt/gears/client/localserver/ResourceStoreUrlCaptureHandler$ResourceStoreUrlCaptureEvent;)(callback,{ url: url, success: success, captureId : captureId });
    };
    return this.capture(urls, jsCallback);
  }-*/;
}
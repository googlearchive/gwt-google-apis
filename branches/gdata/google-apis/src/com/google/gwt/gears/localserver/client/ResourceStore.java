/*
 * Copyright 2007 Google Inc.
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
package com.google.gwt.gears.localserver.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.gears.core.client.GearsException;
import com.google.gwt.user.client.Element;

/**
 * Manages an ad-hoc collection of captured URLs, which can be served locally.
 */
public class ResourceStore {
  /*
   * Called from JSNI code.
   */
  private static void fireURLCaptureCallback(URLCaptureCallback callback,
      String url, boolean success, int captureId) {
    try {
      if (success) {
        callback.onCaptureSuccess(url, captureId);
      } else {
        callback.onCaptureFailure(url, captureId);
      }
    } catch (Throwable e) {
      UncaughtExceptionHandler ueh = GWT.getUncaughtExceptionHandler();
      if (ueh != null) {
        ueh.onUncaughtException(e);
      }
    }
  }

  /*
   * Native proxy methods follow
   */
  private static native void nativeAbortCapture(JavaScriptObject rStoreObj,
      int captureId) /*-{
   rStoreObj.abortCapture(captureId);    
   }-*/;

  private static native void nativeCaptureFile(JavaScriptObject rStoreObj,
      Element element, String url) throws GearsException /*-{
   try {
   return rStoreObj.captureFile(element, url);
   } catch (e) {
   @com.google.gwt.gears.core.client.impl.GearsImpl::throwGearsException(Ljava/lang/String;)(e.toString());
   }
   }-*/;

  /**
   * Native proxy call to the capture method on the JavaScript object.
   * 
   * @param urls a list of URLs to capture
   * @param callback a Java callback object to receive notifications about URLs
   * @return an ID for the capture operation
   */
  private static native int nativeCaptureURL(JavaScriptObject rStoreObj,
      String[] urls, URLCaptureCallback callback) throws GearsException /*-{
   try {
   var newUrls = @com.google.gwt.gears.core.client.impl.GearsImpl::convertToJavaScript([Ljava/lang/String;)(urls);
   var callbackFunc = function(url, success, captureId) {
   @com.google.gwt.gears.localserver.client.ResourceStore::fireURLCaptureCallback(Lcom/google/gwt/gears/localserver/client/URLCaptureCallback;Ljava/lang/String;ZI)(callback,url,success,captureId);
   };
   return rStoreObj.capture(newUrls, callbackFunc);
   } catch (e) {
   @com.google.gwt.gears.core.client.impl.GearsImpl::throwGearsException(Ljava/lang/String;)(e.toString());
   }
   }-*/;

  private static native void nativeCopyURL(JavaScriptObject rStoreObj,
      String src, String dest) throws GearsException /*-{
   try {
   rStoreObj.copy(src, dest);
   } catch (e) {
   @com.google.gwt.gears.core.client.impl.GearsImpl::throwGearsException(Ljava/lang/String;)(e.toString());
   }
   }-*/;

  private static native JavaScriptObject nativeCreateFileSubmitter(
      JavaScriptObject rStoreObj) throws GearsException /*-{
   try {
   return rStoreObj.createFileSubmitter()();
   } catch (e) {
   @com.google.gwt.gears.core.client.impl.GearsImpl::throwGearsException(Ljava/lang/String;)(e.toString());
   }
   }-*/;

  private static native String nativeGetAllURLHeaders(
      JavaScriptObject rStoreObj, String url) throws GearsException /*-{
   try {
   var headers = rStoreObj.getAllHeaders(url);
   return headers == null ? null : headers;
   } catch (e) {
   @com.google.gwt.gears.core.client.impl.GearsImpl::throwGearsException(Ljava/lang/String;)(e.toString());
   } 
   }-*/;

  private static native String nativeGetCapturedFileName(
      JavaScriptObject rStoreObj, String url) throws GearsException /*-{
   try {
   var fileName = rStoreObj.getCapturedFileName(url);
   return fileName == null ? null : fileName;
   } catch (e) {
   @com.google.gwt.gears.core.client.impl.GearsImpl::throwGearsException(Ljava/lang/String;)(e.toString());
   }
   }-*/;

  private static native String nativeGetCapturedURLHeader(
      JavaScriptObject rStoreObj, String url, String name)
      throws GearsException /*-{
   try {
   return rStoreObj.getHeader(url, name);
   } catch (e) {
   @com.google.gwt.gears.core.client.impl.GearsImpl::throwGearsException(Ljava/lang/String;)(e.toString());
   }
   }-*/;

  private static native String nativeGetName(JavaScriptObject rStoreObj) /*-{
   return rStoreObj.name == null ? null : rStoreObj.name;
   }-*/;

  private static native String nativeGetRequiredCookie(
      JavaScriptObject rStoreObj) /*-{
   return rStoreObj.requiredCookie;
   }-*/;

  private static native boolean nativeIsEnabled(JavaScriptObject rStoreObj) /*-{
   return rStoreObj.enabled;
   }-*/;

  private static native boolean nativeIsURLCaptured(JavaScriptObject rStoreObj,
      String url) throws GearsException /*-{
   try {
   return rStoreObj.isCaptured(url);
   } catch (e) {
   @com.google.gwt.gears.core.client.impl.GearsImpl::throwGearsException(Ljava/lang/String;)(e.toString());
   }        
   }-*/;

  private static native void nativeRemoveURL(JavaScriptObject rStoreObj,
      String url) throws GearsException /*-{
   try {
   rStoreObj.remove(url);
   } catch (e) {
   @com.google.gwt.gears.core.client.impl.GearsImpl::throwGearsException(Ljava/lang/String;)(e.toString());
   }        
   }-*/;

  private static native void nativeRenameURL(JavaScriptObject rStoreObj,
      String src, String dest) throws GearsException /*-{
   try {
   rStoreObj.rename(src, dest);
   } catch (e) {
   @com.google.gwt.gears.core.client.impl.GearsImpl::throwGearsException(Ljava/lang/String;)(e.toString());
   }        
   }-*/;

  private static native void nativeSetEnabled(JavaScriptObject rStoreObj,
      boolean enabled) /*-{
   rStoreObj.enabled = enabled;
   }-*/;

  private static native void nativeSetFileInputElement(
      JavaScriptObject fileSub, Element element, String url) /*-{
   try {
   fileSub.setFileInputElement(element, url);
   } catch (e) {
   @com.google.gwt.gears.core.client.impl.GearsImpl::throwGearsException(Ljava/lang/String;)(e.toString());
   }
   }-*/;

  /**
   * Reference to the FileSubmitter JavaScript object provided by Gears.
   */
  private JavaScriptObject fileSub = null;

  /**
   * Reference to the ResourceStore JavaScript object provided by Gears.
   */
  private final JavaScriptObject rStoreObj;

  /**
   * Constructs an instance which wraps a given JavaScript
   * <code>ResourceStore</code> instance.
   * 
   * @param jsDb the object returned from the Gears factory's create method
   */
  ResourceStore(JavaScriptObject jso) {
    rStoreObj = jso;
  }

  /**
   * Aborts the capture operation associated with the given
   * <code>captureId</code>.
   * 
   * @param captureId the ID of the capture operation to abort; must have been
   *          returned by a call to
   *          {@link #captureURL(String, URLCaptureCallback)} or
   *          {@link #captureURLs(String[], URLCaptureCallback)}.
   */
  public void abortCapture(int captureId) {
    nativeAbortCapture(rStoreObj, captureId);
  }

  /**
   * Initiates an asynchronous background task to capture the indicated URL. The
   * return value is a captureId which can be passed to abortCapture to cancel
   * the task. Relative URLs are interpreted according to the current page's
   * location. Upon completion of the URL the appropriate method on the provided
   * {@link URLCaptureCallback} is invoked. An additional HTTP header is added
   * when Gears is capturing URLs: <code>X-Gears-Google: 1</code>
   * 
   * @param url the URL to capture
   * @param callback a callback to be invoked when the URL is captured
   * @return an opaque ID to for the operation
   * @throws GearsException if any of the URLs is not from the same origin as
   *           the current page
   */
  public int captureURL(String url, URLCaptureCallback callback)
      throws GearsException {
    return nativeCaptureURL(rStoreObj, new String[] {url}, callback);
  }

  /**
   * Initiates an asynchronous background task to capture the indicated URLs.
   * The return value is a captureId which can be passed to abortCapture to
   * cancel the task. Relative URLs are interpreted according to the current
   * page's location. Upon completion of each URL the appropriate method on the
   * provided {@link URLCaptureCallback} is invoked. An additional HTTP header
   * is added when Gears is capturing URLs: <code>X-Gears-Google: 1</code>
   * 
   * @param urls a list of URLs to capture
   * @param callback a callback to be invoked when the URL is captured
   * @return an opaque ID to for the operation
   * @throws GearsException if any of the URLs is not from the same origin as
   *           the current page
   */
  public int captureURLs(String[] urls, URLCaptureCallback callback)
      throws GearsException {
    return nativeCaptureURL(rStoreObj, urls, callback);
  }

  /**
   * Copies the data for a given URL to a new name.
   * 
   * @param srcUrl the URL whose data is to be replicated
   * @param destUrl the new URL to contain the replicated data
   * @throws GearsException if the source or destination URL is not from the
   *           same origin as the current page
   */
  public void copyCapturedURL(String srcUrl, String destUrl) throws GearsException {
    nativeCopyURL(rStoreObj, srcUrl, destUrl);
  }

  /**
   * Returns all HTTP headers associated with the captured url as a single
   * string.
   * 
   * @param capturedURL the URL whose headers are to be fetched
   * @return the header string in the response that was captured and bound the
   *         URL
   * @throws GearsException if the URL is not from the same origin as the
   *           current page
   */
  public String getAllCapturedURLHeaders(String capturedURL)
      throws GearsException {
    return nativeGetAllURLHeaders(rStoreObj, capturedURL);
  }

  /**
   * Returns the value of an HTTP response header associated with a captured
   * URL.
   * 
   * @param capturedURL the URL whose header is to be retrieved
   * @param headerName the name of the header to be retrieved
   * @return the value of the header in the request that captured the URL
   * @throws GearsException if the URL is not from the same origin as the
   *           current page
   */
  public String getCapturedURLHeaderValue(String capturedURL, String headerName)
      throws GearsException {
    return nativeGetCapturedURLHeader(rStoreObj, capturedURL, headerName);
  }

  /**
   * Retrieves the user-defined name of this store.
   * 
   * @return the name of the store
   */
  public String getName() {
    return nativeGetName(rStoreObj);
  }

  /**
   * Retrieves the name of the current cookie required by this store to be
   * present in the browser.
   * 
   * @return the current name of the required cookie, or null
   */
  public String getRequiredCookie() {
    return nativeGetRequiredCookie(rStoreObj);
  }

  /**
   * Returns true if the url has been captured.
   * 
   * @param url the URL to be tested
   * @return <code>true</code> if the URL has been captured;
   *         <code>false</code> otherwise
   * @throws GearsException if the URL is not from the same origin as the
   *           current page
   */
  public boolean isCapturedURL(String url) throws GearsException {
    return nativeIsURLCaptured(rStoreObj, url);
  }

  /**
   * Checks whether the store is enabled for serving its URLs locally.
   * 
   * @return true if the store is enabled, false otherwise
   */
  public boolean isEnabled() {
    return nativeIsEnabled(rStoreObj);
  }

  /**
   * Removes the indicated URL from the store.
   * 
   * @param url the URL to remove
   * @throws GearsException if the URL is not from the same origin as the
   *           current page
   */
  public void removeCapturedURL(String url) throws GearsException {
    nativeRemoveURL(rStoreObj, url);
  }

  /**
   * Renames a URL that is cached in the resource store. The data captured for a
   * given URL will be bound to a new URL, replacing the old one.
   * 
   * @param srcURL the URL to be renamed
   * @param destURL the new URL for which the data will be served
   * @throws GearsException if the URL is not from the same origin as the
   *           current page
   */
  public void renameCapturedURL(String srcURL, String destURL)
      throws GearsException {
    nativeRenameURL(rStoreObj, srcURL, destURL);
  }

  /**
   * Enables or disables local serving for the URLs captured in this store.
   * 
   * @param enabled the new state of the store
   */
  public void setEnabled(boolean enabled) {
    nativeSetEnabled(rStoreObj, enabled);
  }
}

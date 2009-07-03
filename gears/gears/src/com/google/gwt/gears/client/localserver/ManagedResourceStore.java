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
import com.google.gwt.gears.client.localserver.ManagedResourceStoreCompleteHandler.ManagedResourceStoreCompleteEvent;
import com.google.gwt.gears.client.localserver.ManagedResourceStoreErrorHandler.ManagedResourceStoreErrorEvent;
import com.google.gwt.gears.client.localserver.ManagedResourceStoreProgressHandler.ManagedResourceStoreProgressEvent;

/**
 * Manages a collection of URLs that are updated atomically, as a group. The
 * Gears local server always serves captured URLs locally. However, a given URL
 * can be captured in one of two ways: as part of an atomic collection of URLs,
 * or individually in an ad-hoc manner. This class manages atomic URL
 * collections; {@link ResourceStore} manages ad-hoc URLs.
 * 
 * A collection of URLs is defined by a manifest file. The manifest file is
 * based on JSON syntax: <code>
 * {
 * // version of the manifest file format
 * "betaManifestVersion": 1,
 *
 *   // version of the set of resources described in this manifest file
 *   "version": "my_version_string",
 * 
 *   // optional
 *   "redirectUrl":  "login.html",
 * 
 *   // URLs to be cached (urls relative to the manifest url)
 *   "entries": [
 *       { "url": "main.html", "src": "main_offline.html" },
 *       { "url": "main.js" }
 *     ]
 * }
 * </code>
 */
public final class ManagedResourceStore extends JavaScriptObject {
  /**
   * Gears is checking for a new manifest file.
   */
  public static final int UPDATE_CHECKING = 1;

  /**
   * Gears found a new manifest file and is downloading the new resources.
   */
  public static final int UPDATE_DOWNLOADING = 2;

  /**
   * The last update to run failed, and the error message is available from
   * {@link #getLastErrorMessage()}.
   */
  public static final int UPDATE_FAILED = 3;

  /**
   * An update is not running. The last update to run succeeded.
   */
  public static final int UPDATE_OK = 0;

  // Called from JSNI
  @SuppressWarnings("unused")
  private static void fireOnComplete(
      ManagedResourceStoreCompleteHandler handler,
      ManagedResourceStoreCompleteEvent event) {
    UncaughtExceptionHandler ueh = GWT.getUncaughtExceptionHandler();
    if (ueh != null) {
      try {
        handler.onComplete(event);
      } catch (Throwable e) {
        ueh.onUncaughtException(e);
      }
    } else {
      handler.onComplete(event);
    }
  }

  // Called from JSNI
  @SuppressWarnings("unused")
  private static void fireOnError(ManagedResourceStoreErrorHandler handler,
      ManagedResourceStoreErrorEvent event) {
    UncaughtExceptionHandler ueh = GWT.getUncaughtExceptionHandler();
    if (ueh != null) {
      try {
        handler.onError(event);
      } catch (Throwable e) {
        ueh.onUncaughtException(e);
      }
    } else {
      handler.onError(event);
    }
  }

  // Called from JSNI
  @SuppressWarnings("unused")
  private static void fireOnProgress(
      ManagedResourceStoreProgressHandler handler,
      ManagedResourceStoreProgressEvent event) {
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

  protected ManagedResourceStore() {
    // Required for overlay types
  }

  /**
   * Instructs Gears to check for an updated manifest file (as determined by
   * whether the value of the 'version' attribute in the manifest has changed),
   * and retrieve and atomically update the contained files, as necessary.
   * 
   * @see #getUpdateStatus()
   */
  public native void checkForUpdate() /*-{
    this.checkForUpdate();
  }-*/;

  /**
   * Returns the version of the set of resources that is currently being served
   * locally, or empty string if no version is currently in use. This value
   * reflects the <code>version</code> attribute from the manifest file only
   * after all resources listed in the manifest file have been cached locally.
   * 
   * @return the current version of empty string if there isn't one
   */
  public native String getCurrentVersion() /*-{
    return this.currentVersion;
  }-*/;

  /**
   * Returns the last error message reported by this instance. The return value
   * is undefined if the value {@link #getUpdateStatus()} is not
   * {@link #UPDATE_FAILED}.
   * 
   * @return the last error message reported by this instance
   */
  public native String getLastErrorMessage() /*-{
    return this.lastErrorMessage;
  }-*/;

  /**
   * Returns the time, in seconds, of the last update.
   * 
   * @return the time, in seconds, of the last update
   */
  public native double getLastUpdateCheckTime() /*-{
    return this.lastUpdateCheckTime;
  }-*/;

  /**
   * Returns the URL of the manifest file currently used by this store.
   * 
   * @return the URL of the manifest file currently used by this store
   */
  public native String getManifestUrl() /*-{
    return this.manifestUrl;
  }-*/;

  /**
   * Returns the name of this store.
   * 
   * @return the name of this store
   */
  public native String getName() /*-{
    return this.name;
  }-*/;

  /**
   * Returns the cookie requirements of this store.
   * 
   * @return the name of the cookie required by the store, or null if no
   *         required cookie was set
   */
  public native String getRequiredCookie() /*-{
    return this.requiredCookie;
  }-*/;

  /**
   * Returns the update status value of this store.
   * 
   * @return the status of the most recent update operation; one of the UPDATE_*
   *         fields
   */
  public native int getUpdateStatus() /*-{
    return this.updateStatus;
  }-*/;

  /**
   * Returns <code>true</code> if the store is currently enabled, or
   * <code>false</code> otherwise.
   * 
   * @return <code>true</code> if the store is currently enabled, or
   *         <code>false</code> otherwise
   */
  public native boolean isEnabled() /*-{
    return this.enabled;
  }-*/;

  /**
   * Enables or disables the store. If the store is disabled, it does not serve
   * requests locally.
   * 
   * @param enabled the new state of the store
   */
  public native void setEnabled(boolean enabled) /*-{
    this.enabled = enabled;
  }-*/;

  /**
   * Sets the URL of the manifest file defining the contents of this store.
   * 
   * @param manifestUrl the URL of the manifest file
   */
  public native void setManifestUrl(String manifestUrl) /*-{
    this.manifestUrl = manifestUrl;
  }-*/;

  /**
   * Sets the handler to call when the {@link ManagedResourceStore} completes an
   * update. Note that updates can be either started explicitly, by calling
   * {@link #checkForUpdate()}, or implicitly, when resources are served from
   * the store.
   * 
   * An update may or may not result in a new version of the store. If a new
   * version results, the newVersion property of the details object will contain
   * the store's new version string.
   * 
   * @param handler handler to call when the {@link ManagedResourceStore}
   *          completes an update
   */
  public native void setOnCompleteHandler(
      ManagedResourceStoreCompleteHandler handler) /*-{
    this.oncomplete = function(details) {
      @com.google.gwt.gears.client.localserver.ManagedResourceStore::fireOnComplete(Lcom/google/gwt/gears/client/localserver/ManagedResourceStoreCompleteHandler;Lcom/google/gwt/gears/client/localserver/ManagedResourceStoreCompleteHandler$ManagedResourceStoreCompleteEvent;)(handler, details);
    };
  }-*/;

  /**
   * Sets the handler to call when a {@link ManagedResourceStore} update fails.
   * 
   * Note that update failure may occur normally when an application is running
   * offline, or if the server is down.
   * 
   * @param handler the handler to call when a {@link ManagedResourceStore}
   *          update fails
   */
  public native void setOnErrorHandler(ManagedResourceStoreErrorHandler handler) /*-{
    this.onerror = function(error) {
      @com.google.gwt.gears.client.localserver.ManagedResourceStore::fireOnError(Lcom/google/gwt/gears/client/localserver/ManagedResourceStoreErrorHandler;Lcom/google/gwt/gears/client/localserver/ManagedResourceStoreErrorHandler$ManagedResourceStoreErrorEvent;)(handler, error);
    };
  }-*/;

  /**
   * Sets the handler to receive update notifications. This event is fired
   * periodically during a {@link ManagedResourceStore} update.
   * 
   * @param handler to receive update notifications
   */
  public native void setOnProgressHandler(
      ManagedResourceStoreProgressHandler handler) /*-{
    this.onprogress = function(details) {
      @com.google.gwt.gears.client.localserver.ManagedResourceStore::fireOnProgress(Lcom/google/gwt/gears/client/localserver/ManagedResourceStoreProgressHandler;Lcom/google/gwt/gears/client/localserver/ManagedResourceStoreProgressHandler$ManagedResourceStoreProgressEvent;)(handler, details);
    };
  }-*/;
}

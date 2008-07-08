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

import com.google.gwt.core.client.JavaScriptObject;

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
  public static final int UPDATE_CHECKING = 1;

  public static final int UPDATE_DOWNLOADING = 2;

  public static final int UPDATE_FAILED = 3;

  public static final int UPDATE_OK = 0;

  /**
   * Constructs a ManagedResourceStore object backed by the provided Gears
   * managed store object.
   * 
   * @param jso the object returned from the Gears factory's create method
   */
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
   * Fetches the current version of the manifest file associated with this
   * store.
   * 
   * @return the currentVersion
   */
  public native String getCurrentVersion() /*-{
    return this.currentVersion;
  }-*/;

  /**
   * Fetches the error most recently reported by this store. This will only be
   * useful if {@link #getUpdateStatus()} returns {@link #UPDATE_FAILED}.
   * 
   * @return the error most recently reported by this store
   */
  public native String getLastErrorMessage() /*-{
    return this.lastErrorMessage;
  }-*/;

  /**
   * @return the lastUpdateCheckTime
   */
  public native double getLastUpdateCheckTime() /*-{
    return this.lastUpdateCheckTime;
  }-*/;

  /**
   * @return the URL of the manifest file currently used by this store
   */
  public native String getManifestUrl() /*-{
    return this.manifestUrl;
  }-*/;

  /**
   * @return the user-defined name of this store
   */
  public native String getName() /*-{
    return this.name;
  }-*/;

  /**
   * @return the name of the cookie required by the store, or null if no
   *         required cookie was set
   */
  public native String getRequiredCookie() /*-{
    return this.requiredCookie;
  }-*/;

  /**
   * @return the status of the most recent update operation; one of the UPDATE_*
   *         fields
   */
  public native int getUpdateStatus() /*-{
    return this.updateStatus;
  }-*/;

  /**
   * @return true if the store is currently enabled, or false if not
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

  public native void setOnCompleteHandler(
      ManagedResourceStoreCompleteHandler handler) /*-{
    this.oncomplete = function(details) {
      handler.@com.google.gwt.gears.client.localserver.ManagedResourceStoreCompleteHandler::onComplete(Lcom/google/gwt/gears/client/localserver/ManagedResourceStoreCompleteHandler$ManagedResourceStoreCompleteEvent;)(details);
    };
  }-*/;

  public native void setOnErrorHandler(ManagedResourceStoreErrorHandler handler) /*-{
    this.onerror = function(error) {
      handler.@com.google.gwt.gears.client.localserver.ManagedResourceStoreErrorHandler::onError(Lcom/google/gwt/gears/client/localserver/ManagedResourceStoreErrorHandler$ManagedResourceStoreErrorEvent;)(details);
    };
  }-*/;

  public native void setOnProgressHandler(
      ManagedResourceStoreProgressHandler handler) /*-{
    this.oncomplete = function(details) {
      handler.@com.google.gwt.gears.client.localserver.ManagedResourceStoreProgressHandler::onProgress(Lcom/google/gwt/gears/client/localserver/ManagedResourceStoreProgressHandler$ManagedResourceStoreProgressEvent;)(details);
    };
  }-*/;
}

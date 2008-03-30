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
package com.google.gwt.gears.localserver.client;

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
public class ManagedResourceStore {
  public static final int UPDATE_CHECKING = 1;

  public static final int UPDATE_DOWNLOADING = 2;

  public static final int UPDATE_FAILED = 3;

  // Status codes reported by getUpdateStatus
  public static final int UPDATE_OK = 0;

  private static native void nativeCheckForUpdate(JavaScriptObject jsStore) /*-{
    jsStore.checkForUpdate();
  }-*/;

  private static native String nativeGetCurrentVersion(JavaScriptObject jsStore) /*-{
    return jsStore.currentVersion == null ? "" : jsStore.currentVersion;
  }-*/;

  private static native String nativeGetLastErrorMessage(
      JavaScriptObject jsStore)/*-{
    var err = jsStore.lastErrorMessage;
    return err == null ? null : err;
  }-*/;

  private static native long nativeGetLastUpdateCheckTime(
      JavaScriptObject jsStore) /*-{
    return jsStore.lastUpdateCheckTime;
  }-*/;

  private static native String nativeGetManifestURL(JavaScriptObject jsStore) /*-{
    return jsStore.manifestUrl == null ? null : jsStore.manifestUrl;
  }-*/;

  private static native String nativeGetName(JavaScriptObject jsStore) /*-{
    return jsStore.name == null ? null : jsStore.name;
  }-*/;

  private static native String nativeGetRequiredCookie(JavaScriptObject jsStore) /*-{
    return jsStore.requiredCookie == null ? null : jsStore.requiredCookie;
  }-*/;

  private static native int nativeGetUpdateStatus(JavaScriptObject jsStore) /*-{
    return jsStore.updateStatus;
  }-*/;

  private static native boolean nativeIsEnabled(JavaScriptObject jsStore) /*-{
    return jsStore.enabled;
  }-*/;

  private static native void nativeSetEnabled(JavaScriptObject jsStore,
      boolean enabled) /*-{
    jsStore.enabled = enabled;
  }-*/;

  private static native void nativeSetManifestURL(JavaScriptObject jsStore,
      String manifestURL) /*-{
    jsStore.manifestUrl = manifestURL;
  }-*/;

  /**
   * Reference to the ManagedResourceStore JavaScript object provided by Gears.
   */
  private JavaScriptObject mgdStore = null;

  /**
   * Constructs a ManagedResourceStore object backed by the provided Gears
   * managed store object.
   * 
   * @param jsDb the object returned from the Gears factory's create method
   */
  ManagedResourceStore(JavaScriptObject jso) {
    this.mgdStore = jso;
  }

  /**
   * This class requires a Gears managed store to operate.
   * 
   * @see #ManagedResourceStore(JavaScriptObject)
   */
  @SuppressWarnings("unused")
  private ManagedResourceStore() {
    // not default instantiable
  }

  /**
   * Instructs Gears to check for an updated manifest file (as determined by
   * whether the value of the 'version' attribute in the manifest has changed),
   * and retrieve and atomically update the contained files, as necessary.
   * 
   * @see #getUpdateStatus()
   */
  public void checkForUpdate() {
    nativeCheckForUpdate(mgdStore);
  }

  /**
   * Fetches the current version of the manifest file associated with this
   * store.
   * 
   * @return the currentVersion
   */
  public String getCurrentVersion() {
    return nativeGetCurrentVersion(mgdStore);
  }

  /**
   * Fetches the error most recently reported by this store. This will only be
   * useful if {@link #getUpdateStatus()} returns {@link #UPDATE_FAILED}.
   * 
   * @return the error most recently reported by this store
   */
  public String getLastErrorMessage() {
    return nativeGetLastErrorMessage(mgdStore);
  }

  /**
   * @return the lastUpdateCheckTime
   */
  public long getLastUpdateCheckTime() {
    return nativeGetLastUpdateCheckTime(mgdStore);
  }

  /**
   * @return the URL of the manifest file currently used by this store
   */
  public String getManifestURL() {
    return nativeGetManifestURL(mgdStore);
  }

  /**
   * @return the user-defined name of this store
   */
  public String getName() {
    return nativeGetName(mgdStore);
  }

  /**
   * @return the name of the cookie required by the store, or null if no
   *         required cookie was set
   */
  public String getRequiredCookie() {
    return nativeGetRequiredCookie(mgdStore);
  }

  /**
   * @return the status of the most recent update operation; one of the UPDATE_*
   *         fields
   */
  public int getUpdateStatus() {
    return nativeGetUpdateStatus(mgdStore);
  }

  /**
   * @return true if the store is currently enabled, or false if not
   */
  public boolean isEnabled() {
    return nativeIsEnabled(mgdStore);
  }

  /**
   * Enables or disables the store. If the store is disabled, it does not serve
   * requests locally.
   * 
   * @param enabled the new state of the store
   */
  public void setEnabled(boolean enabled) {
    nativeSetEnabled(mgdStore, enabled);
  }

  /**
   * Sets the URL of the manifest file defining the contents of this store.
   * 
   * @param manifestURL the URL of the manifest file
   * @throws NullPointerException if <code>manifestURL</code> is
   *           <code>null</code>
   */
  public void setManifestURL(String manifestURL) {
    if (manifestURL == null) {
      throw new NullPointerException();
    }

    nativeSetManifestURL(mgdStore, manifestURL);
  }
}
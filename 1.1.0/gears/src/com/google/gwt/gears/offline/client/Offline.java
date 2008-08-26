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
package com.google.gwt.gears.offline.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.gears.client.Factory;
import com.google.gwt.gears.client.GearsException;
import com.google.gwt.gears.client.localserver.LocalServer;
import com.google.gwt.gears.client.localserver.ManagedResourceStore;

/**
 * Provides access to a {@link ManagedResourceStore} to allow the application to
 * work offline. In the simplest case, an application developer only needs to
 * invoke {@link #getManagedResourceStore()} in order to allow the application
 * to run offline. In more complicated scenarios, the developer can choose to
 * use {@link #getManifestUrl()} to manually create a ManagedResourceStore.
 * <p>
 * A file named <code>GearsManifest.json</code> located in the root of the
 * public path may be used as an optional template for generating the final
 * manifest. The following substitutions will be made into the template:
 * </p>
 * <ul>
 * <li><code>__VERSION__</code>: An automatically-generated version string.
 * This value will be a hex-encoded MD5 checksum of all of the content in the
 * output directory.</li>
 * <li><code>__ENTRIES__</code>: A comma-separated list of manifest entries.</li>
 * <li><code>{@literal @filter} regex</code>: A pragma that will prevent
 * entries whose partial paths match the given regular expression from being
 * added to the manifest. The <code>{@literal @filter}</code> expressions may
 * appear at any position in the manifest template.
 * </ul>
 * 
 * Example manifest:
 * 
 * <pre>
 * // This is an example of a custom manifest
 * 
 * // Filter any filenames that contain &quot;.online.&quot;
 * // {@literal @filter} .*\.online\..*
 * {
 *  &quot;betaManifestVersion&quot; : 1,
 *  &quot;version&quot; : &quot;__VERSION__&quot;,
 *  &quot;entries&quot; : [__ENTRIES__]
 * }
 * </pre>
 * 
 * <p>
 * The <code>com.google.gwt.gears.Offline</code> module must be inherited in
 * order for the automatically-generated manifest to be created during
 * compilation. If you need to debug Offline-related behavior in hosted mode, it
 * will be necessary to run a compile/browse cycle to generate the manifest or
 * to use the <code>-noserver</code> mode of the hosted-mode shell.
 */
public class Offline {
  /**
   * There's an apparent 64-character limit on the length of the manifest store
   * name.
   */
  private static final int MAX_STORE_NAME_LENGTH = 64;

  private static ManagedResourceStore store;

  /**
   * A convenience method to create a ManagedResourceStore that will allow the
   * GWT application to run offline. A call to
   * {@link ManagedResourceStore#checkForUpdate()} will have been made to the
   * returned object. Subsequent calls to this function will return the same
   * instance of the ManagedResourceStore.
   * 
   * @return a preconfigured ManagedResourceStore
   * @throws GearsException if the ManagedResourceStore cannot be configured
   */
  public static ManagedResourceStore getManagedResourceStore()
      throws GearsException {
    if (store != null) {
      return store;
    }

    String storeName = GWT.getModuleName() + "_offline";
    if (storeName.length() > MAX_STORE_NAME_LENGTH) {
      storeName = storeName.substring(storeName.length()
          - MAX_STORE_NAME_LENGTH);
    }
    assert storeName.length() <= MAX_STORE_NAME_LENGTH;

    LocalServer server = Factory.getInstance().createLocalServer();
    store = server.createManagedStore(storeName);
    store.setManifestUrl(getManifestUrl());
    store.checkForUpdate();
    return store;
  }

  /**
   * Returns the URL at which the ManagedResourceStore's manifest file can be
   * found. This method can be called by users that wish to manually manage the
   * ManagedResourceStore used by the application.
   * 
   * @return URL of the manifest file
   */
  public static String getManifestUrl() {
    return GWT.getModuleBaseURL() + GWT.getModuleName() + ".nocache.manifest";
  }

  /**
   * Utility class.
   */
  private Offline() {
  }
}

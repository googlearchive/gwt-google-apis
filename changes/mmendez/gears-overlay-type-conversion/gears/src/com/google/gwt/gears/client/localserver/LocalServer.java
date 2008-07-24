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

import com.google.gwt.core.client.JavaScriptException;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.gears.client.GearsException;

/**
 * A factory and container for the set of {@link ResourceStore}s and
 * {@link ManagedResourceStore}s your application creates and uses. Use
 * resource stores to enable your JavaScript application to execute without a
 * network connection.
 */
public final class LocalServer extends JavaScriptObject {
  protected LocalServer() {
    // Required for overlay types
  }

  /**
   * Returns <code>true</code> if a request for the URL can be satisfied from
   * any of the resource stores available, taking into account whether the
   * containing store is enabled and if its requiredCookie matches. Relative
   * URLs are interpreted according to the current page location. Returns
   * <code>true</code> if the URL can be served locally.
   * 
   * @param url the URL to test
   * @return <code>true</code> if a request for the URL can be satisfied from
   *         any of the resource stores available
   * @throws GearsException if the URL is not from the same origin as the
   *           current page
   */
  public boolean canServeLocally(String url) throws GearsException {
    try {
      return uncheckedCanServeLocally(url);
    } catch (JavaScriptException ex) {
      throw new GearsException(ex.getMessage(), ex);
    }
  }

  /**
   * Opens an existing {@link ManagedResourceStore} or creates a new one if no
   * such store exists.
   * 
   * @param name of the {@link ManagedResourceStore}
   * @return {@link ManagedResourceStore} instance
   */
  public native ManagedResourceStore createManagedStore(String name) /*-{
    return this.createManagedStore(name);
  }-*/;

  /**
   * Opens an existing {@link ManagedResourceStore} or creates a new one if no
   * such store exists.
   * 
   * The combination of <code>name</code> and <code>requiredCookie</code>
   * (along with the domain) identify a unique store. Expected cookie format is
   * "name=value".
   * 
   * @param name of the {@link ManagedResourceStore}
   * @param requiredCookie cookie formatted as "name=value"
   * @return {@link ManagedResourceStore} instance
   */
  public native ManagedResourceStore createManagedStore(String name,
      String requiredCookie) /*-{
    return this.createManagedStore(name, requiredCookie);
  }-*/;

  /**
   * Opens an existing {@link ResourceStore} or creates a new one if no such
   * store exists.
   * 
   * @param name name of the {@link ResourceStore}
   * @return {@link ResourceStore} instance
   */
  public native ResourceStore createStore(String name) /*-{
    return this.createStore(name);
  }-*/;

  /**
   * Opens an existing {@link ResourceStore} or creates a new one if no such
   * store exists.
   * 
   * If a <code>requiredCookie</code> is given, creates a ResourceStore that
   * requires the cookie to be present in the client in order to serve the
   * contents from the store. The combination of <code>name</code> and
   * <code>requiredCookie</code> (along with the domain) identify a unique
   * store. Expected cookie format is "name=value".
   * 
   * @param name name of the {@link ResourceStore}
   * @param requiredCookie cookie formatted as "name=value"
   * @return {@link ResourceStore} instance
   */
  public native ResourceStore createStore(String name, String requiredCookie) /*-{
    return this.createStore(name, requiredCookie);
  }-*/;

  /**
   * Opens an existing {@link ManagedResourceStore} or returns <code>null</code>
   * if no such store exists.
   * 
   * @param name name of the {@link ManagedResourceStore}
   * @return existing {@link ManagedResourceStore} or returns <code>null</code>
   *         if no such store exists.
   */
  public native ManagedResourceStore openManagedStore(String name) /*-{
    return this.openManagedStore(name);
  }-*/;

  /**
   * Opens an existing {@link ManagedResourceStore} or returns <code>null</code>
   * if no such store exists.
   * 
   * The combination of name and requiredCookie (along with the domain) identify
   * a unique store. Expected cookie format is "name=value".
   * 
   * @param name name of the {@link ManagedResourceStore}
   * @param requiredCookie cookie formatted as "name=value"
   * @return existing {@link ManagedResourceStore} or returns <code>null</code>
   *         if no such store exists.
   */
  public native ManagedResourceStore openManagedStore(String name,
      String requiredCookie) /*-{
    return this.openManagedStore(name, requiredCookie);
  }-*/;

  /**
   * Opens an existing {@link ResourceStore} or returns <code>null</code> if
   * no such store exists. If the store was originally created with a
   * requiredCookie, the same value must be provided in order to open this
   * {@link ResourceStore}. *
   * 
   * @param name name of the {@link ResourceStore}
   * @return existing {@link ResourceStore} or returns <code>null</code> if no
   *         such store exists.
   */
  public native ResourceStore openStore(String name) /*-{
    return this.openStore(name);
  }-*/;

  /**
   * Opens an existing {@link ResourceStore} or returns <code>null</code> if
   * no such store exists. If the store was originally created with a
   * requiredCookie, the same value must be provided in order to open this
   * {@link ResourceStore}.
   * 
   * The combination of name and requiredCookie (along with the domain) identify
   * a unique store. Expected cookie format is "name=value".
   * 
   * @param name name of the {@link ResourceStore}
   * @param requiredCookie cookie formatted as "name=value"
   * @return existing {@link ResourceStore} or returns <code>null</code> if no
   *         such store exists.
   * 
   */
  public native ResourceStore openStore(String name, String requiredCookie) /*-{
    return this.openStore(name, requiredCookie);
  }-*/;

  /**
   * Removes a {@link ManagedResourceStore} and all of its contained URLs from
   * the local cache.
   * 
   * @param name name of the {@link ManagedResourceStore} to remove
   */
  public native void removeManagedStore(String name) /*-{
    this.removeManagedStore(name);
  }-*/;

  /**
   * Removes a {@link ManagedResourceStore} and all of its contained URLs from
   * the local cache.
   * 
   * @param name name of the {@link ManagedResourceStore}
   * @param requiredCookie cookie formatted as "name=value"
   * 
   */
  public native void removeManagedStore(String name, String requiredCookie) /*-{
    this.removeManagedStore(name, requiredCookie);
  }-*/;

  /**
   * Removes a {@link ResourceStore} and deletes all of its contents.
   * 
   * @param name name of the {@link ResourceStore}
   */
  public native void removeStore(String name) /*-{
    this.removeStore(name);
  }-*/;

  /**
   * Removes a {@link ResourceStore} and deletes all of its contents.
   * 
   * @param name name of the {@link ResourceStore}
   * @param requiredCookie cookie formatted as "name=value"
   */
  public native void removeStore(String name, String requiredCookie) /*-{
    this.removeStore(name, requiredCookie);
  }-*/;

  /**
   * Native proxy call to the canServeLocally method on the JavaScript object.
   */
  private native boolean uncheckedCanServeLocally(String url) /*-{
    return this.canServeLocally(url);
  }-*/;
}
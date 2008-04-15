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

import com.google.gwt.core.client.JavaScriptException;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.gears.core.client.Gears;
import com.google.gwt.gears.core.client.GearsException;
import com.google.gwt.gears.core.client.impl.GearsImpl;

/**
 * Used to manage the URLs that are served locally. A
 * {@link ManagedResourceStore} is used to capture and manage groups of URLs
 * that are atomically bundled together. A {@link ResourceStore} is used to
 * capture and manage URLs individually, on an ad-hoc basis.
 * 
 * Note that a captured URL is <b>always</b> served locally. That is, Gears's
 * local server does not attempt to detect offline/online state.
 */
public class LocalServer {
  /**
   * Native proxy call to the canServeLocally method on the JavaScript object.
   * 
   * @param url
   * @return
   */
  private static native boolean nativeCanServeLocally(JavaScriptObject jso,
      String url) /*-{
    return jso.canServeLocally(url);
  }-*/;

  private static native void nativeRemoveStore(JavaScriptObject server,
      String name, String requiredCookie) /*-{
    server.removeStore(name, requiredCookie);
  }-*/;

  /**
   * Reference to the local server JavaScript object provided by Gears.
   */
  private final JavaScriptObject server;

  /**
   * Constructs a <code>LocalServer</code> instance.
   * 
   * @throws GearsException if the LocalServer object could not be created
   */
  public LocalServer() throws GearsException {
    this(Gears.LOCALSERVER, Gears.GEARS_VERSION);
  }

  /**
   * Constructs a <code>LocalServer</code> instance.
   * 
   * @throws GearsException if the LocalServer object could not be created
   */
  protected LocalServer(String className, String classVersion)
      throws GearsException {
    this.server = GearsImpl.create(className, classVersion);
  }

  /**
   * Returns <code>true</code> if the URL can be served locally.
   * 
   * @param url the URL to test
   * @return true if the URL has been captured, false if not
   * @throws GearsException if the URL is not from the same origin as the
   *           current page
   * @throws NullPointerException if the <code>url</code> is <code>null</code>
   */
  public boolean canServeLocally(String url) throws GearsException {
    if (url == null) {
      throw new NullPointerException();
    }

    if (url.trim().length() == 0) {
      throw new IllegalArgumentException();
    }

    try {
      return nativeCanServeLocally(server, url);
    } catch (JavaScriptException ex) {
      throw new GearsException(ex.getMessage(), ex);
    }
  }

  /**
   * Opens an existing {@link ManagedResourceStore} or creates a new one if no
   * such store exists.
   * 
   * @param name the user-defined name of the store
   * @return a <code>ManagedResourceStore</code>
   * @throws GearsException on any error
   */
  public ManagedResourceStore createManagedResourceStore(String name)
      throws GearsException {
    return createManagedResourceStore(name, null);
  }

  /**
   * Opens an existing {@link ManagedResourceStore} or creates a new one if no
   * such store exists. Once the store is created, it will only serve pages if a
   * cookie exists under the value stored in <code>requiredCookie</code>.
   * 
   * @param name the user-defined name of the store
   * @param requiredCookie the name of a cookie which must be present for the
   *          store to serve pages
   * @return a ManagedResourceStore
   * @throws GearsException on any error
   */
  public ManagedResourceStore createManagedResourceStore(String name,
      String requiredCookie) throws GearsException {
    try {
      JavaScriptObject jso = nativeCallMethod("createManagedStore", name,
          requiredCookie);
      if (jso == null) {
        return null;
      }
      return new ManagedResourceStore(jso);
    } catch (JavaScriptException e) {
      throw new GearsException(e.getMessage(), e);
    }
  }

  /**
   * Opens an existing (non-managed/ad-hoc) {@link ResourceStore} or creates a
   * new one if the store does not exist.
   * 
   * @param name the user-defined name of the store
   * @return a ResourceStore
   * @throws GearsException on any error
   */
  public ResourceStore createResourceStore(String name) throws GearsException {
    return createResourceStore(name, null);
  }

  /**
   * Opens an existing (non-managed/ad-hoc) {@link ResourceStore} or creates a
   * new one if the store does not exist. Once the store is created, it will
   * only serve pages if a cookie exists under the value stored in
   * <code>requiredCookie</code>.
   * 
   * @param name the user-defined name of the store
   * @return a ResourceStore
   * @throws GearsException on any error
   */
  public ResourceStore createResourceStore(String name, String requiredCookie)
      throws GearsException {
    try {
      JavaScriptObject jso = nativeCallMethod("createStore", name,
          requiredCookie);
      if (jso == null) {
        return null;
      }
      return new ResourceStore(jso);
    } catch (JavaScriptException e) {
      throw new GearsException(e.getMessage(), e);
    }
  }

  /**
   * Opens an existing {@link ManagedResourceStore}.
   * 
   * @param name the user-defined name of the store
   * @return a ManagedResourceStore, or null if the store does not exist
   * @throws GearsException on any error
   */
  public ManagedResourceStore openManagedResourceStore(String name)
      throws GearsException {
    return openManagedResourceStore(name, null);
  }

  /**
   * Opens an existing {@link ManagedResourceStore}. Once the store is opened,
   * it will only serve pages if a cookie exists under the value stored in
   * <code>requiredCookie</code>.
   * 
   * @param name the user-defined name of the store
   * @return a ManagedResourceStore, or null if the store does not exist
   * @throws GearsException on any error
   */
  public ManagedResourceStore openManagedResourceStore(String name,
      String requiredCookie) throws GearsException {
    try {
      JavaScriptObject jso = nativeCallMethod("openManagedStore", name,
          requiredCookie);
      if (jso == null) {
        return null;
      }
      return new ManagedResourceStore(jso);
    } catch (JavaScriptException e) {
      throw new GearsException(e.getMessage(), e);
    }
  }

  /**
   * Opens an existing (non-managed/ad-hoc) {@link ResourceStore}.
   * 
   * @param name the user-defined name of the store
   * @return a ResourceStore, or null if the store does not exist
   * @throws GearsException on any error
   */
  public ResourceStore openResourceStore(String name) throws GearsException {
    return openResourceStore(name, null);
  }

  /**
   * Opens an existing (non-managed/ad-hoc) {@link ResourceStore}. Once the
   * store is opened, it will only serve pages if a cookie exists under the
   * value stored in <code>requiredCookie</code>.
   * 
   * @param name the user-defined name of the store
   * @return a ResourceStore, or null if the store does not exist
   * @throws GearsException on any error
   */
  public ResourceStore openResourceStore(String name, String requiredCookie)
      throws GearsException {
    try {
      JavaScriptObject jso = nativeCallMethod("openStore", name, requiredCookie);
      if (jso == null) {
        return null;
      }
      return new ResourceStore(jso);
    } catch (JavaScriptException e) {
      throw new GearsException(e.getMessage(), e);
    }
  }

  /**
   * Destroys a {@link ManagedResourceStore}.
   * 
   * @param name the user-defined name of the store to be removed
   * @throws GearsException on any error
   */
  public void removeManagedResourceStore(String name) throws GearsException {
    removeManagedResourceStore(name, null);
  }

  /**
   * Destroys a {@link ManagedResourceStore}.
   * 
   * @param name the user-defined name of the store to be removed
   * @param requiredCookie the name of a cookie which must be present
   * @throws GearsException on any error
   */
  public void removeManagedResourceStore(String name, String requiredCookie)
      throws GearsException {
    try {
      nativeCallMethod("removeManagedStore", name, requiredCookie);
    } catch (JavaScriptException e) {
      throw new GearsException(e.getMessage(), e);
    }
  }

  /**
   * Destroys a (non-managed/ad-hoc) {@link ResourceStore}.
   * 
   * @param name the user-defined name of the store to be removed
   * @throws GearsException on any error
   */
  public void removeResourceStore(String name) throws GearsException {
    removeResourceStore(name, null);
  }

  /**
   * Removes a {@link ResourceStore} from this <code>LocalServer</code>.
   * 
   * @param name the user-defined name of the store to be removed
   * @param requiredCookie the name of a cookie which must be present
   * @throws GearsException on any error
   */
  public void removeResourceStore(String name, String requiredCookie)
      throws GearsException {
    try {
      nativeRemoveStore(server, name, requiredCookie);
    } catch (JavaScriptException ex) {
      throw new GearsException(ex.getMessage(), ex);
    }
  }

  /**
   * Returns the JavaScript <code>LocalServer</code> object wrapped by this
   * instance.
   * 
   * @return the JavaScript <code>LocalServer</code> object wrapped by this
   *         instance
   */
  protected final JavaScriptObject getJavaScriptObject() {
    return server;
  }

  /**
   * Common native method used to call other methods. This avoids having a
   * separate native method for each of the other methods on this class.
   * 
   * @param func the name of the function on the JavaScript object to call
   * @param name the user-defined name of a store
   * @param requiredCookie the name of a required cookie, or null
   * @return whatever is returned by the proxied method call
   * 
   * TODO(mmendez): explore undefined vs null handling here
   */
  private native JavaScriptObject nativeCallMethod(String func, String name,
      String requiredCookie) /*-{
    if (requiredCookie == null) {
      return (this.@com.google.gwt.gears.localserver.client.LocalServer::server)[func](name) || null;
    } else {
      return (this.@com.google.gwt.gears.localserver.client.LocalServer::server)[func](name, requiredCookie) || null;
    }
  }-*/;
}

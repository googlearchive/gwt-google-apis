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
package com.google.gwt.gears.client.localserver;

import com.google.gwt.core.client.GWT;
import com.google.gwt.gears.core.client.Gears;
import com.google.gwt.gears.core.client.GearsException;
import com.google.gwt.junit.client.GWTTestCase;

/**
 * 
 */
public class LocalServerTest extends GWTTestCase {
  private static final String RESOURCE_STORE_NAME = "TestResourceStore";
  private static final String MANAGED_RESOURCE_STORE_NAME = "TestManagedResourceStore";

  private static String getMyURL() {
    return GWT.getModuleBaseURL() + "HelloWorld.js";
  }

  public String getModuleName() {
    return "com.google.gwt.gears.Gears";
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.localserver.client.LocalServer#canServeLocally(java.lang.String) canServeLocally("")}.
   * 
   * @throws GearsException
   */
  public void testCanServeLocallyEmptyURL() throws GearsException {
    LocalServer ls = new LocalServer();
    try {
      ls.canServeLocally("");
    } catch (IllegalArgumentException e) {
      // Expected to get here
    }
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.localserver.client.LocalServer#canServeLocally(java.lang.String) canServeLocally(null)}.
   * 
   * @throws GearsException
   */
  public void testCanServeLocallyNullURL() throws GearsException {
    LocalServer ls = new LocalServer();
    try {
      ls.canServeLocally(null);
      fail("Expected NullPointerException");
    } catch (NullPointerException e) {
      // Expected to get here
    }
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.localserver.client.LocalServer#canServeLocally(java.lang.String)}.
   * 
   * @throws GearsException
   */
  public void testCanServeLocallySameOriginURLCaptured() throws GearsException {
    final LocalServer ls = new LocalServer();
    delayTestFinish(5000);
    String requestedURL = getMyURL();
    final ResourceStore rs = ls.createResourceStore(RESOURCE_STORE_NAME);
    rs.removeCapturedURL(requestedURL);
    assertFalse(ls.canServeLocally(requestedURL));
    rs.captureURL(requestedURL, new URLCaptureCallback() {
      public void onCaptureFailure(String url, int captureId) {
        fail("Could not capture: " + url);
      }

      public void onCaptureSuccess(String url, int captureId) {
        boolean failed = true;
        try {
          failed = !ls.canServeLocally(url);
        } catch (GearsException e) {
          // we failed, ignore the exception
        }
        assertFalse(failed);
        finishTest();
      }
    });
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.localserver.client.LocalServer#canServeLocally(java.lang.String))}.
   * 
   * @throws GearsException
   */
  public void testCanServeLocallySameOriginURLNotCaptured()
      throws GearsException {
    LocalServer ls = new LocalServer();
    assertFalse(ls.canServeLocally(GWT.getModuleBaseURL() + "DoesNotExist.html"));
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.localserver.client.LocalServer#createManagedResourceStore(java.lang.String)}.
   * 
   * @throws GearsException
   */
  public void testCreateManagedStoreString() throws GearsException {
    LocalServer ls = new LocalServer();
    ManagedResourceStore mrs = ls.createManagedResourceStore(MANAGED_RESOURCE_STORE_NAME);
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.localserver.client.LocalServer#createManagedResourceStore(java.lang.String, java.lang.String)}.
   * 
   * @throws GearsException
   */
  public void testCreateManagedStoreStringString() throws GearsException {
    LocalServer ls = new LocalServer();
    ManagedResourceStore mrs = ls.createManagedResourceStore(
        MANAGED_RESOURCE_STORE_NAME, "foo");
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.localserver.client.LocalServer#createResourceStore(java.lang.String)}.
   * 
   * @throws GearsException
   */
  public void testCreateResourceStoreString() throws GearsException {
    LocalServer ls = new LocalServer();
    ResourceStore rs = ls.createResourceStore(RESOURCE_STORE_NAME);
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.localserver.client.LocalServer#createResourceStore(java.lang.String, java.lang.String)}.
   * 
   * @throws GearsException
   */
  public void testCreateResourceStoreStringString() throws GearsException {
    LocalServer ls = new LocalServer();
    ResourceStore rs = ls.createResourceStore(RESOURCE_STORE_NAME, "bar");
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.localserver.client.LocalServer#LocalServer(java.lang.String, java.lang.String)}.
   * 
   * @throws GearsException
   */
  public void testLocalServerStringString() throws GearsException {
    LocalServer ls = new LocalServer(Gears.LOCALSERVER, Gears.GEARS_VERSION);
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.localserver.client.LocalServer#openManagedResourceStore(java.lang.String)}.
   * 
   * @throws GearsException
   */
  public void testOpenManagedResourceStoreString() throws GearsException {
    LocalServer ls = new LocalServer();
    try {
      assertNull(ls.openManagedResourceStore("DOES_NOT_EXIST"));

      ls.createManagedResourceStore(MANAGED_RESOURCE_STORE_NAME);
      assertNotNull(ls.openManagedResourceStore(MANAGED_RESOURCE_STORE_NAME));
    } finally {
      ls.removeManagedResourceStore(MANAGED_RESOURCE_STORE_NAME);
    }
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.localserver.client.LocalServer#openManagedResourceStore(java.lang.String, java.lang.String)}.
   * 
   * @throws GearsException
   */
  public void testOpenManagedResourceStoreStringString() throws GearsException {
    LocalServer ls = new LocalServer();
    try {
      assertNull(ls.openManagedResourceStore("DOES_NOT_EXIST", "foo"));
      ls.createManagedResourceStore(MANAGED_RESOURCE_STORE_NAME, "foo");
      assertNotNull(ls.openManagedResourceStore(MANAGED_RESOURCE_STORE_NAME,
          "foo"));
    } finally {
      ls.removeManagedResourceStore(MANAGED_RESOURCE_STORE_NAME, "foo");
    }
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.localserver.client.LocalServer#openResourceStore(java.lang.String)}.
   * 
   * @throws GearsException
   */
  public void testOpenResourceStoreString() throws GearsException {
    LocalServer ls = new LocalServer();

    try {
      assertNull(ls.openResourceStore("DOES_NOT_EXIST"));
      ls.createResourceStore(RESOURCE_STORE_NAME);
      assertNotNull(ls.openResourceStore(RESOURCE_STORE_NAME));
    } finally {
      ls.removeResourceStore(RESOURCE_STORE_NAME);
    }
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.localserver.client.LocalServer#openStore(java.lang.String, java.lang.String)}.
   * 
   * @throws GearsException
   */
  public void testOpenResourceStoreStringString() throws GearsException {
    LocalServer ls = new LocalServer();
    try {
      assertNull(ls.openResourceStore("DOES_NOT_EXIST", "foo"));
      ls.createResourceStore(RESOURCE_STORE_NAME, "foo");
      ls.openResourceStore(RESOURCE_STORE_NAME, "foo");
    } finally {
      ls.removeResourceStore(RESOURCE_STORE_NAME, "foo");
    }
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.localserver.client.LocalServer#removeManagedResourceStore(java.lang.String)}.
   * 
   * @throws GearsException
   */
  public void testRemoveManagedResourceStoreString() throws GearsException {
    LocalServer ls = new LocalServer();
    ls.createManagedResourceStore(MANAGED_RESOURCE_STORE_NAME);
    ls.removeManagedResourceStore(MANAGED_RESOURCE_STORE_NAME);
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.localserver.client.LocalServer#removeManagedResourceStore(java.lang.String, java.lang.String)}.
   * 
   * @throws GearsException
   */
  public void testRemoveManagedResourceStoreStringString()
      throws GearsException {
    LocalServer ls = new LocalServer();
    ls.createManagedResourceStore(MANAGED_RESOURCE_STORE_NAME, "foo");
    ls.removeManagedResourceStore(MANAGED_RESOURCE_STORE_NAME, "foo");
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.localserver.client.LocalServer#removeResourceStore(java.lang.String)}.
   * 
   * @throws GearsException
   */
  public void testRemoveStoreString() throws GearsException {
    LocalServer ls = new LocalServer();
    ls.createResourceStore(RESOURCE_STORE_NAME);
    ls.removeResourceStore(RESOURCE_STORE_NAME);
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.localserver.client.LocalServer#removeResourceStore(java.lang.String, java.lang.String)}.
   * 
   * @throws GearsException
   */
  public void testRemoveStoreStringString() throws GearsException {
    LocalServer ls = new LocalServer();
    ls.createResourceStore(RESOURCE_STORE_NAME, "foo");
    ls.removeResourceStore(RESOURCE_STORE_NAME, "foo");
  }
}

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
import com.google.gwt.gears.client.Factory;
import com.google.gwt.gears.client.GearsException;
import com.google.gwt.junit.client.GWTTestCase;

/**
 * 
 */
public class LocalServerTest extends GWTTestCase {
  private static final String MANAGED_RESOURCE_STORE_NAME = "TestManagedResourceStore";
  private static final String RESOURCE_STORE_NAME = "TestResourceStore";

  private static String getMyURL() {
    return GWT.getModuleBaseURL() + "HelloWorld.js";
  }

  /**
   * Test method for
   * {@link LocalServer#canServeLocally(String) canServeLocally("")}.
   * 
   * @throws GearsException
   */
  public void disabledTestCanServeLocallyEmptyURL() throws GearsException {
    LocalServer ls = Factory.getInstance().createLocalServer();
    try {
      ls.canServeLocally("");
    } catch (IllegalArgumentException e) {
      // Expected to get here
    }
  }

  /**
   * Test method for
   * {@link LocalServer#canServeLocally(String) canServeLocally(null)}.
   * 
   * @throws GearsException
   */
  public void disabledTestCanServeLocallyNullURL() throws GearsException {
    LocalServer ls = Factory.getInstance().createLocalServer();
    try {
      ls.canServeLocally(null);
      fail("Expected NullPointerException");
    } catch (NullPointerException e) {
      // Expected to get here
    }
  }

  @Override
  public String getModuleName() {
    return "com.google.gwt.gears.Gears";
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.client.localserver.LocalServer#canServeLocally(java.lang.String)}.
   * 
   * @throws GearsException
   */
  public void testCanServeLocallySameOriginURLCaptured() throws GearsException {
    final LocalServer ls = Factory.getInstance().createLocalServer();
    delayTestFinish(5000);
    final String requestedURL = getMyURL();
    final ResourceStore rs = ls.createStore(RESOURCE_STORE_NAME);
    rs.remove(requestedURL);
    assertFalse(ls.canServeLocally(requestedURL));
    rs.capture(new ResourceStoreUrlCaptureHandler() {
      public void onCapture(ResourceStoreUrlCaptureEvent event) {
        assertTrue(event.isSuccess());
        assertEquals(requestedURL, event.getUrl());
        try {
          assertTrue(ls.canServeLocally(requestedURL));
        } catch (GearsException e) {
          fail(e.getMessage());
        }
        finishTest();
      }
    }, requestedURL);
  }

  /**
   * Test method for {@link LocalServer#canServeLocally(String)}.
   * 
   * @throws GearsException
   */
  public void testCanServeLocallySameOriginURLNotCaptured()
      throws GearsException {
    LocalServer ls = Factory.getInstance().createLocalServer();
    assertFalse(ls.canServeLocally(GWT.getModuleBaseURL() + "DoesNotExist.html"));
  }

  /**
   * Test method for {@link LocalServer#createManagedStore(java.lang.String)}.
   */
  public void testCreateManagedStoreString() {
    LocalServer ls = Factory.getInstance().createLocalServer();
    ls.createManagedStore(MANAGED_RESOURCE_STORE_NAME);
  }

  /**
   * Test method for
   * {@link LocalServer#createManagedStore(java.lang.String, java.lang.String)}.
   */
  public void testCreateManagedStoreStringString() {
    LocalServer ls = Factory.getInstance().createLocalServer();
    ls.createManagedStore(MANAGED_RESOURCE_STORE_NAME, "foo");
  }

  /**
   * Test method for {@link LocalServer#createStore(String)}.
   */
  public void testCreateResourceStoreString() {
    LocalServer ls = Factory.getInstance().createLocalServer();
    ls.createStore(RESOURCE_STORE_NAME);
  }

  /**
   * Test method for {@link LocalServer#createStore(String, String)}.
   */
  public void testCreateResourceStoreStringString() {
    LocalServer ls = Factory.getInstance().createLocalServer();
    ls.createStore(RESOURCE_STORE_NAME, "bar");
  }

  /**
   * Test method for {@link LocalServer#openManagedStore(String)}.
   */
  public void testOpenManagedResourceStoreString() {
    LocalServer ls = Factory.getInstance().createLocalServer();
    try {
      assertNull(ls.openManagedStore("DOES_NOT_EXIST"));

      ls.createManagedStore(MANAGED_RESOURCE_STORE_NAME);
      assertNotNull(ls.openManagedStore(MANAGED_RESOURCE_STORE_NAME));
    } finally {
      ls.removeManagedStore(MANAGED_RESOURCE_STORE_NAME);
    }
  }

  /**
   * Test method for {@link LocalServer#openManagedStore(String, String)}.
   */
  public void testOpenManagedResourceStoreStringString() {
    LocalServer ls = Factory.getInstance().createLocalServer();
    try {
      assertNull(ls.openManagedStore("DOES_NOT_EXIST", "foo"));
      ls.createManagedStore(MANAGED_RESOURCE_STORE_NAME, "foo");
      assertNotNull(ls.openManagedStore(MANAGED_RESOURCE_STORE_NAME, "foo"));
    } finally {
      ls.removeManagedStore(MANAGED_RESOURCE_STORE_NAME, "foo");
    }
  }

  /**
   * Test method for {@link LocalServer#openStore(String)}.
   */
  public void testOpenResourceStoreString() {
    LocalServer ls = Factory.getInstance().createLocalServer();

    try {
      assertNull(ls.openStore("DOES_NOT_EXIST"));
      ls.createStore(RESOURCE_STORE_NAME);
      assertNotNull(ls.openStore(RESOURCE_STORE_NAME));
    } finally {
      ls.removeStore(RESOURCE_STORE_NAME);
    }
  }

  /**
   * Test method for {@link LocalServer#openStore(String, String)}.
   */
  public void testOpenResourceStoreStringString() {
    LocalServer ls = Factory.getInstance().createLocalServer();
    try {
      assertNull(ls.openStore("DOES_NOT_EXIST", "foo"));
      ls.createStore(RESOURCE_STORE_NAME, "foo");
      ls.openStore(RESOURCE_STORE_NAME, "foo");
    } finally {
      ls.removeStore(RESOURCE_STORE_NAME, "foo");
    }
  }

  /**
   * Test method for {@link LocalServer#removeManagedStore(String)}.
   */
  public void testRemoveManagedResourceStoreString() {
    LocalServer ls = Factory.getInstance().createLocalServer();
    ls.createManagedStore(MANAGED_RESOURCE_STORE_NAME);
    ls.removeManagedStore(MANAGED_RESOURCE_STORE_NAME);
  }

  /**
   * Test method for {@link LocalServer#removeManagedStore(String, String)}.
   */
  public void testRemoveManagedResourceStoreStringString() {
    LocalServer ls = Factory.getInstance().createLocalServer();
    ls.createManagedStore(MANAGED_RESOURCE_STORE_NAME, "foo");
    ls.removeManagedStore(MANAGED_RESOURCE_STORE_NAME, "foo");
  }

  /**
   * Test method for {@link LocalServer#removeStore(String)}.
   */
  public void testRemoveStoreString() {
    LocalServer ls = Factory.getInstance().createLocalServer();
    ls.createStore(RESOURCE_STORE_NAME);
    ls.removeStore(RESOURCE_STORE_NAME);
  }

  /**
   * Test method for {@link LocalServer#removeStore(String, String)}.
   */
  public void testRemoveStoreStringString() {
    LocalServer ls = Factory.getInstance().createLocalServer();
    ls.createStore(RESOURCE_STORE_NAME, "foo");
    ls.removeStore(RESOURCE_STORE_NAME, "foo");
  }
}

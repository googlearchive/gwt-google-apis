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
package com.google.gwt.gears.client.localserver.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.gears.core.client.GearsException;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.Timer;

/**
 * Tests for {@link ManagedResourceStore}.
 */
public class ManagedResourceStoreTest extends GWTTestCase {
  private static final String MANAGED_RESOURCE_STORE_NAME = "ManagedResourceStore";

  private static String getTestManifestURL() {
    return GWT.getModuleBaseURL() + "manifest";
  }

  public String getModuleName() {
    return "com.google.gwt.gears.client.localserver.ManagedResourceStoreTest";
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.localserver.client.ManagedResourceStore#checkForUpdate()}
   * and
   * {@link com.google.gwt.gears.localserver.client.ManagedResourceStore#getUpdateStatus()}.
   * 
   * @throws GearsException
   */
  public void testCheckForUpdate() throws GearsException {
    final LocalServer ls = new LocalServer();
    ls.removeManagedResourceStore(MANAGED_RESOURCE_STORE_NAME);
    final ManagedResourceStore mrs = ls.createManagedResourceStore(MANAGED_RESOURCE_STORE_NAME);
    mrs.setManifestURL(getTestManifestURL());
    mrs.checkForUpdate();

    delayTestFinish(5000);
    new Timer() {
      public void run() {
        switch (mrs.getUpdateStatus()) {
          case ManagedResourceStore.UPDATE_CHECKING:
          case ManagedResourceStore.UPDATE_DOWNLOADING:
            schedule(500);
            break;
          case ManagedResourceStore.UPDATE_FAILED:
            fail("Failed to update the resource");
            break;
          case ManagedResourceStore.UPDATE_OK:
            assertEquals("gwt-google-apis 0.1", mrs.getCurrentVersion());
            assertTrue(mrs.getLastUpdateCheckTime() > 0);
            finishTest();
            break;
        }
      }
    }.schedule(500);
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.localserver.client.ManagedResourceStore#getCurrentVersion()}.
   * 
   * @throws GearsException
   */
  public void testGetCurrentVersion() throws GearsException {
    final LocalServer ls = new LocalServer();
    ls.removeManagedResourceStore(MANAGED_RESOURCE_STORE_NAME);
    final ManagedResourceStore mrs = ls.createManagedResourceStore(MANAGED_RESOURCE_STORE_NAME);
    assertEquals("", mrs.getCurrentVersion());
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.localserver.client.ManagedResourceStore#getLastErrorMessage()}.
   */
  public void testGetLastErrorMessage() {
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.localserver.client.ManagedResourceStore#getName()}.
   * 
   * @throws GearsException
   */
  public void testGetName() throws GearsException {
    LocalServer ls = new LocalServer();
    ls.removeManagedResourceStore(MANAGED_RESOURCE_STORE_NAME);
    ManagedResourceStore mrs = ls.createManagedResourceStore(MANAGED_RESOURCE_STORE_NAME);
    assertEquals(MANAGED_RESOURCE_STORE_NAME, mrs.getName());
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.localserver.client.ManagedResourceStore#getRequiredCookie()}.
   * 
   * @throws GearsException
   */
  public void testGetRequiredCookie() throws GearsException {
    LocalServer ls = new LocalServer();
    ls.removeManagedResourceStore(MANAGED_RESOURCE_STORE_NAME);
    ManagedResourceStore mrs = ls.createManagedResourceStore(
        MANAGED_RESOURCE_STORE_NAME, "foo");
    assertEquals("foo", mrs.getRequiredCookie());
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.localserver.client.ManagedResourceStore#setManifestURL(java.lang.String)}
   * and
   * {@link com.google.gwt.gears.localserver.client.ManagedResourceStore#getManifestURL()}.
   * 
   * @throws GearsException
   */
  public void testGetSetManifestUrl() throws GearsException {
    LocalServer ls = new LocalServer();
    ls.removeManagedResourceStore(MANAGED_RESOURCE_STORE_NAME);
    ManagedResourceStore mrs = ls.createManagedResourceStore(MANAGED_RESOURCE_STORE_NAME);
    mrs.setManifestURL(getTestManifestURL());
    assertEquals(getTestManifestURL(), mrs.getManifestURL());
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.localserver.client.ManagedResourceStore#isEnabled()}
   * and {@link ManagedResourceStore#setEnabled(boolean)}.
   * 
   * @throws GearsException
   */
  public void testIsEnabled() throws GearsException {
    LocalServer ls = new LocalServer();
    ls.removeManagedResourceStore(MANAGED_RESOURCE_STORE_NAME);
    ManagedResourceStore mrs = ls.createManagedResourceStore(MANAGED_RESOURCE_STORE_NAME);
    mrs.setEnabled(false);
    assertFalse(mrs.isEnabled());
    mrs.setEnabled(true);
    assertTrue(mrs.isEnabled());
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.localserver.client.ManagedResourceStore#setManifestUrl(java.lang.String)}.
   * 
   * @throws GearsException
   */
  public void testSetManifestURLEmpty() throws GearsException {
    LocalServer ls = new LocalServer();
    ls.removeManagedResourceStore(MANAGED_RESOURCE_STORE_NAME);
    ManagedResourceStore mrs = ls.createManagedResourceStore(MANAGED_RESOURCE_STORE_NAME);
    mrs.setManifestURL("");
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.localserver.client.ManagedResourceStore#setManifestUrl(java.lang.String)}.
   * 
   * @throws GearsException
   */
  public void testSetManifestURLNull() throws GearsException {
    LocalServer ls = new LocalServer();
    ls.removeManagedResourceStore(MANAGED_RESOURCE_STORE_NAME);
    ManagedResourceStore mrs = ls.createManagedResourceStore(MANAGED_RESOURCE_STORE_NAME);
    try {
      mrs.setManifestURL(null);
    } catch (NullPointerException e) {
      // Expected to get here
    }
  }
}

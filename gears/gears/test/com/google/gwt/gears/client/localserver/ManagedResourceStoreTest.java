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

  /**
   * Test method for {@link ManagedResourceStore#setManifestUrl(String)}.
   */
  public void disabledTestSetManifestURLNull() {
    LocalServer ls = Factory.getInstance().createLocalServer();
    ls.removeManagedStore(MANAGED_RESOURCE_STORE_NAME);
    ManagedResourceStore mrs = ls.createManagedStore(MANAGED_RESOURCE_STORE_NAME);
    try {
      mrs.setManifestUrl(null);
      fail("Expected a NullPointerException");
    } catch (NullPointerException e) {
      // Expected to get here
    }
  }

  @Override
  public String getModuleName() {
    return "com.google.gwt.gears.Gears";
  }

  /**
   * Test method for {@link ManagedResourceStore#checkForUpdate()} and
   * {@link ManagedResourceStore#getUpdateStatus()}.
   */
  public void testCheckForUpdate() {
    final LocalServer ls = Factory.getInstance().createLocalServer();
    ls.removeManagedStore(MANAGED_RESOURCE_STORE_NAME);
    final ManagedResourceStore mrs = ls.createManagedStore(MANAGED_RESOURCE_STORE_NAME);
    mrs.setManifestUrl(getTestManifestURL());
    mrs.checkForUpdate();

    delayTestFinish(5000);
    new Timer() {
      @Override
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
            // For some reason on FF getLastUpdateCheckTime() returns negative
            // values.
            // assertTrue(mrs.getLastUpdateCheckTime() > 0);
            finishTest();
            break;
        }
      }
    }.schedule(500);
  }

  /**
   * Test method for {@link ManagedResourceStore#getCurrentVersion()}.
   */
  public void testGetCurrentVersion() {
    final LocalServer ls = Factory.getInstance().createLocalServer();
    ls.removeManagedStore(MANAGED_RESOURCE_STORE_NAME);
    final ManagedResourceStore mrs = ls.createManagedStore(MANAGED_RESOURCE_STORE_NAME);
    assertEquals("", mrs.getCurrentVersion());
  }

  /**
   * Test method for {@link ManagedResourceStore#getLastErrorMessage()}.
   */
  public void testGetLastErrorMessage() {
  }

  /**
   * Test method for {@link ManagedResourceStore#getName()}.
   */
  public void testGetName() {
    LocalServer ls = Factory.getInstance().createLocalServer();
    ls.removeManagedStore(MANAGED_RESOURCE_STORE_NAME);
    ManagedResourceStore mrs = ls.createManagedStore(MANAGED_RESOURCE_STORE_NAME);
    assertEquals(MANAGED_RESOURCE_STORE_NAME, mrs.getName());
  }

  /**
   * Test method for {@link ManagedResourceStore#getRequiredCookie()}.
   */
  public void testGetRequiredCookie() {
    LocalServer ls = Factory.getInstance().createLocalServer();
    ls.removeManagedStore(MANAGED_RESOURCE_STORE_NAME);
    ManagedResourceStore mrs = ls.createManagedStore(
        MANAGED_RESOURCE_STORE_NAME, "foo");
    assertEquals("foo", mrs.getRequiredCookie());
  }

  /**
   * Test method for {@link ManagedResourceStore#setManifestUrl(String)} and
   * {@link ManagedResourceStore#getManifestUrl()}.
   */
  public void testGetSetManifestUrl() {
    LocalServer ls = Factory.getInstance().createLocalServer();
    ls.removeManagedStore(MANAGED_RESOURCE_STORE_NAME);
    ManagedResourceStore mrs = ls.createManagedStore(MANAGED_RESOURCE_STORE_NAME);
    mrs.setManifestUrl(getTestManifestURL());
    assertEquals(getTestManifestURL(), mrs.getManifestUrl());
  }

  /**
   * Test method for {@link ManagedResourceStore#isEnabled()} and
   * {@link ManagedResourceStore#setEnabled(boolean)}.
   */
  public void testIsEnabled() {
    LocalServer ls = Factory.getInstance().createLocalServer();
    ls.removeManagedStore(MANAGED_RESOURCE_STORE_NAME);
    ManagedResourceStore mrs = ls.createManagedStore(MANAGED_RESOURCE_STORE_NAME);
    mrs.setEnabled(false);
    assertFalse(mrs.isEnabled());
    mrs.setEnabled(true);
    assertTrue(mrs.isEnabled());
  }

  /**
   * Test method for {@link ManagedResourceStore#setManifestUrl(String)}.
   */
  public void testSetManifestURLEmpty() {
    LocalServer ls = Factory.getInstance().createLocalServer();
    ls.removeManagedStore(MANAGED_RESOURCE_STORE_NAME);
    ManagedResourceStore mrs = ls.createManagedStore(MANAGED_RESOURCE_STORE_NAME);
    mrs.setManifestUrl("");
  }
}

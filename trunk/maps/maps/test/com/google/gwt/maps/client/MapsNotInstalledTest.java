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
package com.google.gwt.maps.client;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.maps.client.geom.LatLng;

/**
 * Tests for the MapWidget.
 */
public class MapsNotInstalledTest extends GWTTestCase {

  @Override
  public String getModuleName() {
    return "com.google.gwt.maps.GoogleMapsNotInstalledTest";
  }

  /**
   * Runs before every test method.
   */
   @Override
  public void gwtSetUp() {
     TestUtilities.cleanDom();
   }

  public void testAssertLoaded() {
    try {
      Maps.assertLoaded();
      assertFalse("Expected an assertion", true);
    } catch (RuntimeException ex) {
      // Test passed!
    }
  }

  public void testIsNotLoaded() {
    assertFalse("Expected the MAPS api to be missing.", Maps.isLoaded());
  }

  public void testLatLngNotLoaded() {
    try {
      LatLng l = LatLng.newInstance(45, 45);
      assertNull("did not expect initialization to succeed", l);
      assertTrue("Expected an exception", false);
    } catch (RuntimeException ex) {
      // Test passed!
    }
  }

  public void testMapWidgetNotLoaded() {
    try {
      MapWidget m = new MapWidget();
      assertNull("did not expect initialization to succeed", m);
      assertTrue("Expected an exception", false);
    } catch (RuntimeException ex) {
      // Test passed!
    }
  }
}

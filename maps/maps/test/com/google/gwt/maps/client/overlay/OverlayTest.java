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
package com.google.gwt.maps.client.overlay;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.maps.client.TestUtilities;
import com.google.gwt.maps.client.geom.LatLng;

/**
 * Tests the Overlay class.
 */
public class OverlayTest extends GWTTestCase {
  // length of time to wait for asynchronous test to complete.
  static final int ASYNC_DELAY_MSEC = 5000;

  @Override
  public String getModuleName() {
    return "com.google.gwt.maps.GoogleMapsTest";
  }

  /**
   * Runs before every test method.
   */
  public void gwtSetUp() {
    TestUtilities.cleanDom();
  }

  public void testOverlayZIndex() {
    LatLng atlanta = LatLng.newInstance(33.7814790, -84.3880580);
    double result1 = Overlay.getZIndex(atlanta.getLatitude());
    assertTrue("expected non-zero value", result1 != 0.0);
    double result2 = Overlay.getZIndex(atlanta.getLatitude() + 1);
    assertTrue("expected non-zero value", result2 != 0.0);
    assertTrue("expected result1 > result2 ", result1 > result2);
  }
  
}

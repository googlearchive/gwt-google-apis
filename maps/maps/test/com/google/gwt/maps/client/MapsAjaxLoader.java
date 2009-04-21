/*
 * Copyright 2009 Google Inc.
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

/**
 * Tests for the Ajax Loader.
 * 
 * Note, the Maps.loadMapsApi() method needs to be in the first test method.
 */
public class MapsAjaxLoader extends GWTTestCase {
  // Google APIs for GWT Continuous Builder key
  private static final String KEY = "ABQIAAAAG8LzhtshQCjpSshU_uJjmxRII77nF3p9tM7LktEY_493N8UDkRSxVj4RkjzrVFb94nbD4QDNPJIIEg";

  @Override
  public String getModuleName() {
    return "com.google.gwt.maps.MapsAjaxLoader";
  }

  /**
   * NOTE: This test will fail when running remotely unless you provide the
   * appropriate Maps API key as the 'key' parameter. Edit the "key" variable
   * above.
   */
  public void testMapsAjaxLoader() {
    delayTestFinish(10000);
    Maps.loadMapsApi(KEY, "2.x", false, new Runnable() {

      public void run() {
        assertTrue(Maps.isLoaded());
        finishTest();
      }

    });
  }
}

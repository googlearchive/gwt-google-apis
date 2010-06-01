/*
 * Copyright 2010 Google Inc.
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

import com.google.gwt.ajaxloader.client.AjaxLoader.AjaxLoaderOptions;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * A base class to support testing the Maps API that requires loading.
 */
public abstract class MapsTestCase extends GWTTestCase {
  public static final int ASYNC_DELAY_MS = 10 * 1000;
  private boolean loaded = false;

  public MapsTestCase() {
    super();
  }

  @Override
  public String getModuleName() {
    return "com.google.gwt.maps.GoogleMapsTest";
  }

  /**
   * Creates a 300x300 pixel map centered on Atlanta and adds it to the page.
   * 
   * @return the created MapWidget instance.
   */
  protected MapWidget addDefaultMap() {
    LatLng atlanta = LatLng.newInstance(33.7814790, -84.3880580);
    MapWidget map = new MapWidget(atlanta, 8);
    map.setSize("300px", "300px");
    RootPanel.get().add(map);
    return map;
  }

  /**
   * Loads the Maps API asynchronously and runs the specified test.
   * When the testRunnable method completes, the test is considered finished
   * successfully.
   * 
   * @param testRunnable code to invoke when the API loaded.
   * 
   */
  protected void loadApi(final Runnable testRunnable) {
    loadApi(testRunnable, true);
  }

  /**
   * Loads the Maps API asynchronously and runs the specified test.
   * 
   * @param testRunnable code to invoke when the API loaded.
   * @param callFinishTest if <code>true</code>, the finishTest() method is
   *          called when the test completes. If <code>false</code>, the caller
   *          is responsible for ending the test.
   */
  protected void loadApi(final Runnable testRunnable,
      final boolean callFinishTest) {
    loadApi(testRunnable, callFinishTest, "2.x");
  }

  /**
   * Loads the Maps API asynchronously and runs the specified test.
   * 
   * @param testRunnable code to invoke when the API loaded.
   * @param callFinishTest if <code>true</code>, the finishTest() method is
   *          called when the test completes. If <code>false</code>, the caller
   *          is responsible for ending the test.
   * @param version the maps api version to load
   */  
  protected void loadApi(final Runnable testRunnable,
      final boolean callFinishTest, String version) {
    if (loaded) {
      testRunnable.run();
    } else {
      Maps.loadMapsApi(null, version, false, AjaxLoaderOptions.newInstance(),
          new Runnable() {

            public void run() {
              loaded = true;
              testRunnable.run();
              if (callFinishTest) {
                finishTest();
              }
            }
          });
      delayTestFinish(ASYNC_DELAY_MS);
    }
  }
}
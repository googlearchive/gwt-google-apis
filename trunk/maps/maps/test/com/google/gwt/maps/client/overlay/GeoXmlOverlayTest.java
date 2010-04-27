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

import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.MapsTestCase;
import com.google.gwt.maps.client.TestUtilities;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Tests the GeoXmlOverlay class.
 */
public class GeoXmlOverlayTest extends MapsTestCase {
  private static String KML_DEMO_URI = "http://gmaps-samples.googlecode.com/svn/trunk/ggeoxml/cta.kml";

  @Override
  public String getModuleName() {
    return "com.google.gwt.maps.GoogleMapsTest";
  }

  /**
   * Runs before every test method.
   */
  @Override
  public void gwtSetUp() {
    TestUtilities.cleanDom();
  }

  public void testBadUrl() {
    loadApi(new Runnable() {
      public void run() {
        GeoXmlOverlay.load("http://badurl.example.com/cta.kml",
            new GeoXmlLoadCallback() {
              @Override
              public void onFailure(String url, Throwable e) {
                // Test passed
                finishTest();
              }

              @Override
              public void onSuccess(String url, GeoXmlOverlay overlay) {
                assertTrue("testBadUrl: Expected failure", false);
              }
            });
      }
    }, false);
  }

  public void testSimple() {
    loadApi(new Runnable() {
      public void run() {
        final MapWidget map = new MapWidget();
        map.setSize("300px", "300px");
        RootPanel.get().add(map);
        GeoXmlOverlay.load(KML_DEMO_URI, new GeoXmlLoadCallback() {
          @Override
          public void onFailure(String url, Throwable e) {
            assertTrue("testSimple: Expected success", false);
          }

          @Override
          public void onSuccess(String url, GeoXmlOverlay overlay) {
            assertNotNull("url", url);
            assertNotNull("overlay", overlay);
            map.addOverlay(overlay);
            assertNotNull("getDefaultBounds()", overlay.getDefaultBounds());
            assertNotNull("getDefaultCenter()", overlay.getDefaultCenter());
            assertNotNull("getDefaultSpan()", overlay.getDefaultSpan());
            overlay.gotoDefaultViewport(map);
            finishTest();
          }
        });
      }
    }, false);
  }
}

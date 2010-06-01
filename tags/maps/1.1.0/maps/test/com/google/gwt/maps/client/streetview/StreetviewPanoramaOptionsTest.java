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
package com.google.gwt.maps.client.streetview;

import com.google.gwt.maps.client.JsBeanTestCase;
import com.google.gwt.maps.client.geom.LatLng;

/**
 * Tests setters / getters for typos in JSNI methods.
 */
public class StreetviewPanoramaOptionsTest extends JsBeanTestCase {

  public void testFields() {
    loadApi(new Runnable() {
      public void run() {
        StreetviewPanoramaOptions opts = StreetviewPanoramaOptions.newInstance();

        opts.setEnableFullScreen(true);
        assertEquals(true, getBoolean(opts, "enableFullScreen"));
        opts.setEnableFullScreen(false);
        assertEquals(false, getBoolean(opts, "enableFullScreen"));

        StreetviewFeatures features = StreetviewFeatures.newInstance();
        LatLng ll = LatLng.newInstance(-1, 1);
        Pov pov = Pov.newInstance();
        StreetviewUserPhotosOptions photoOpts = StreetviewUserPhotosOptions.newInstance();

        // Intentional repetition to check builder pattern
        opts.setEnableFullScreen(false).setFeatures(features).setLatLng(ll).setPov(
            pov).setUserPhotosOptions(photoOpts).setEnableFullScreen(false);

        assertSame(features, getJso(opts, "features"));
        assertSame(ll, getJso(opts, "latlng"));
        assertSame(pov, getJso(opts, "pov"));
        assertSame(photoOpts, getJso(opts, "userPhotosOptions"));
      }
    });
  }
}
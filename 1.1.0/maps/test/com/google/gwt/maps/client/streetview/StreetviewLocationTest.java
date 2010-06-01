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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.JsBeanTestCase;
import com.google.gwt.maps.client.geom.LatLng;

/**
 * Tests setters / getters for typos in JSNI methods.
 */
public class StreetviewLocationTest extends JsBeanTestCase {

  public void testFields() {
    loadApi(new Runnable() {
      public void run() {
        StreetviewLocation location = JavaScriptObject.createObject().cast();

        String description = "Googleplex";
        setProperty(location, "description", description);
        assertEquals(description, location.getDescription());

        LatLng ll = LatLng.newInstance(1, -1);
        setProperty(location, "latlng", ll);
        assertSame(ll, location.getLatLng());

        String panoId = "7";
        setProperty(location, "panoId", panoId);
        assertEquals(panoId, location.getPanoId());

        Pov pov = Pov.newInstance();
        setProperty(location, "pov", pov);
        assertSame(pov, location.getPov());
      }
    });
  }
}

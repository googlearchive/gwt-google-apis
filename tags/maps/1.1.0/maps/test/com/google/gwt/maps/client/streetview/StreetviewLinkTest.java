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

/**
 * Tests setters / getters for typos in JSNI methods.
 */
public class StreetviewLinkTest extends JsBeanTestCase {

  public void testFields() {
    loadApi(new Runnable() {
      public void run() {
        StreetviewLink link = JavaScriptObject.createObject().cast();

        String description = "Surely beautiful place";
        setProperty(link, "description", description);
        assertEquals(description, link.getDescription());

        String panoId = "3.141592ImTrappedInUniverseFactory95765912";
        setProperty(link, "panoId", panoId);
        assertEquals(panoId, link.getPanoId());

        double yaw = 42.42;
        setProperty(link, "yaw", yaw);
        assertEquals(yaw, link.getYaw());
      }
    });
  }
}
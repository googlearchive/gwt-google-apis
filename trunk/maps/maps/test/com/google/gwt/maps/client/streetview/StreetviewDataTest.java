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

import com.google.gwt.ajaxloader.client.ArrayHelper;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.maps.client.JsBeanTestCase;

/**
 * Tests setters / getters for typos in JSNI methods.
 */
public class StreetviewDataTest extends JsBeanTestCase {

  public void testFields() {
    loadApi(new Runnable() {
      public void run() {
        StreetviewData data = JavaScriptObject.createObject().cast();

        StreetviewLocation location = JavaScriptObject.createObject().cast();
        setProperty(data, "location", location);
        assertSame(location, data.getLocation());

        int code = 29;
        setProperty(data, "code", code);
        assertEquals(code, data.getCode());

        String copyright = "Google";
        setProperty(data, "copyright", copyright);
        assertEquals(copyright, data.getCopyright());

        JsArray<StreetviewLink> linkArray = ArrayHelper.toJsArray((StreetviewLink) JavaScriptObject.createObject());
        setProperty(data, "links", linkArray);
        assertSame(linkArray, data.getLinks());
      }
    });
  }
}

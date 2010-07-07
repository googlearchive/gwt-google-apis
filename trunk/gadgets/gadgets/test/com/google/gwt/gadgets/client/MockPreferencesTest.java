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
package com.google.gwt.gadgets.client;

import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.gadgets.client.impl.MockPreferencesFeatureImpl;
import com.google.gwt.junit.client.GWTTestCase;

/**
 * Tests {@link MockPreferencesFeatureImpl}.
 */
public class MockPreferencesTest extends GWTTestCase {

  @Override
  public String getModuleName() {
    return "com.google.gwt.gadgets.GadgetsTest";
  }

  public void testMockPrefsBool() {
    PreferencesProvider.set(MockPreferencesFeatureImpl.get());
    PreferencesFeature prefs = PreferencesProvider.get();

    prefs.set("boolValTrue", true);
    assertTrue(prefs.getBool("boolValTrue"));
    prefs.set("boolValFalse", false);
    assertFalse(prefs.getBool("boolValFalse"));
    assertFalse(prefs.getBool("boolValUnset"));
  }

  public void testMockPrefsString() {
    PreferencesProvider.set(MockPreferencesFeatureImpl.get());
    PreferencesFeature prefs = PreferencesProvider.get();

    prefs.set("stringVal", "data");
    assertEquals("stringVal", "data", prefs.getString("stringVal"));
    assertNull("stringValUnset", prefs.getString("boolValUnset"));

    prefs.set("boolValTrue", true);
    assertEquals("boolValTrue", "true", prefs.getString("boolValTrue"));
    prefs.set("boolValFalse", false);
    assertEquals("boolValFalse", "false", prefs.getString("boolValFalse"));

    JsArrayString arr = JsArrayString.createArray().cast();
    arr.push("foo");
    arr.push("bar");
    arr.push("baz");
    prefs.setArray("arrayVal", arr);
    assertEquals("arrayVal", "foo|bar|baz", prefs.getString("arrayVal"));
  }

  public void testMockPrefsArray() {
    PreferencesProvider.set(MockPreferencesFeatureImpl.get());
    PreferencesFeature prefs = PreferencesProvider.get();

    JsArrayString arr = JsArrayString.createArray().cast();
    arr.push("foo");
    arr.push("bar");
    arr.push("baz");
    prefs.setArray("arrayVal", arr);
    JsArrayString found = prefs.getArray("arrayVal");
    assertEquals("arrayVal.length", 3, found.length());
    assertEquals("arrayVal[0]", "foo", found.get(0));
    assertEquals("arrayVal[1]", "bar", found.get(1));
    assertEquals("arrayVal[2]", "baz", found.get(2));
  }

  public void testMockPrefsMsg() {
    MockPreferencesFeatureImpl impl = 
      (MockPreferencesFeatureImpl) MockPreferencesFeatureImpl.get();
    impl.setMsg("stringVal", "data");
    PreferencesProvider.set(impl);
    PreferencesFeature prefs = PreferencesProvider.get();

    assertEquals("stringVal", "data", prefs.getMsg("stringVal"));
    assertNull("stringValUnset", prefs.getString("boolValUnset"));
  }

}

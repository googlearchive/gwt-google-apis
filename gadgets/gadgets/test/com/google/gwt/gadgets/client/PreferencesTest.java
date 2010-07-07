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

import com.google.gwt.gadgets.client.impl.MockPreferencesFeatureImpl;
import com.google.gwt.junit.client.GWTTestCase;

/**
 * Tests {@link UserPreferences.Preferences}.
 */
public class PreferencesTest extends GWTTestCase {

  @Override
  public String getModuleName() {
    return "com.google.gwt.gadgets.GadgetsTest";
  }

  public void testBooleanPreference() {
    PreferencesProvider.set(MockPreferencesFeatureImpl.get());
    BooleanPreference booleanPref = new BooleanPreference() {
      @Override
      public String getName() {
        return "testPreference";
      }
    };
    assertFalse("unset boolean pref", booleanPref.getValue());
    booleanPref.set(true);
    assertTrue("set true boolean pref", booleanPref.getValue());
    booleanPref.set(false);
    assertFalse("set false boolean pref", booleanPref.getValue());
  }

  public void testListPreference() {
    PreferencesProvider.set(MockPreferencesFeatureImpl.get());
    ListPreference listPref = new ListPreference() {
      @Override
      public String getName() {
        return "testPreference";
      }
    };
    assertNull("null list pref", listPref.getValue());
    String[] value = {"foo", "bar", "baz"};
    listPref.set(value);
    String[] result = listPref.getValue();
    assertEquals("arrayVal.length", 3, result.length);
    assertEquals("arrayVal[0]", "foo", result[0]);
    assertEquals("arrayVal[1]", "bar", result[1]);
    assertEquals("arrayVal[2]", "baz", result[2]);
  }

  public void testStringPreference() {
    PreferencesProvider.set(MockPreferencesFeatureImpl.get());
    StringPreference stringPref = new StringPreference() {
      @Override
      public String getName() {
        return "testPreference";
      }
    };
    assertNull("unset string pref", stringPref.getValue());
    stringPref.set("foo");
    assertEquals("set string pref", "foo", stringPref.getValue());
  }

  public void testEnumPreference() {
    // TODO(zundel): there is no 'get' for this preference type - I'm not sure
    // how to test it. Otherwise, its just a string preference.
  }
}

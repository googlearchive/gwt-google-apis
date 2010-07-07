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
package com.google.gwt.gadgets.client.impl;

import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.gadgets.client.PreferencesFeature;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides access to Gadget preferences support.
 */
public class MockPreferencesFeatureImpl implements PreferencesFeature {

  private class MockPref {
    Boolean boolValue = false;
    String stringValue;
    JsArrayString arrayValue;

    public MockPref(String value) {
      stringValue = value;
    }

    public MockPref(Boolean value) {
      boolValue = value;
    }

    public MockPref(JsArrayString value) {
      arrayValue = value;
    }
  }

  private Map<String, MockPref> preferenceLookup = 
    new HashMap<String, MockPref>();
  private Map<String, String> msgLookup = new HashMap<String, String>();

  public static PreferencesFeature get() {
    return new MockPreferencesFeatureImpl();
  }

  protected MockPreferencesFeatureImpl() {
  }

  public boolean getBool(String name) {
    MockPref result = preferenceLookup.get(name);
    if (result != null && result.boolValue != null) {
      return result.boolValue;
    }
    return false;
  }

  public String getMsg(String name) {
    return msgLookup.get(name);
  }

  public void setMsg(String name, String value) {
    msgLookup.put(name, value);
  }

  public String getString(String name) {
    MockPref result = preferenceLookup.get(name);
    if (result != null) {
      if (result.stringValue != null) {
        return result.stringValue;
      } else if (result.arrayValue != null) {
        return result.arrayValue.join("|");
      } else if (result.boolValue != null) {
        return result.boolValue.toString();
      }
    }
    return null;
  }

  public void set(String name, String value) {
    preferenceLookup.put(name, new MockPref(value));
  }

  public void set(String name, boolean value) {
    preferenceLookup.put(name, new MockPref(value));
  }

  public void setArray(String name, JsArrayString value) {
    preferenceLookup.put(name, new MockPref(value));
  }

  public JsArrayString getArray(String name) {
    MockPref result = preferenceLookup.get(name);
    if (result != null) {
      return result.arrayValue;
    }
    return null;
  }
}

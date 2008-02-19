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
package com.google.gwt.gadgets.client.impl;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Utility methods for the preferences support.
 */
public class PreferencesUtil {
  public static JavaScriptObject prefs;

  public static native boolean getBool(String name) /*-{
    return @com.google.gwt.gadgets.client.impl.PreferencesUtil::prefs.getBool(name) || false;
  }-*/;

  public static native String getString(String name) /*-{
    return (
      _ = @com.google.gwt.gadgets.client.impl.PreferencesUtil::prefs.getString(name),
      _ == undefined ? null : _);
  }-*/;
  
  public static void maybeInit() {
    if (prefs == null) {
      prefs = nativeInitPrefs();
    }
  }

  public static native JavaScriptObject nativeInitPrefs() /*-{
     return new $wnd._IG_Prefs();
   }-*/;

  public static native void push(JavaScriptObject array, String value) /*-{
    array.push(value);
  }-*/;

  public static native void set(String name, String value) /*-{
    @com.google.gwt.gadgets.client.impl.PreferencesUtil::prefs.set(name, value);
  }-*/;

  public static native void setArray(String name, JavaScriptObject value) /*-{
    @com.google.gwt.gadgets.client.impl.PreferencesUtil::prefs.setArray(name, value);
  }-*/;

  private PreferencesUtil() {
  }
}

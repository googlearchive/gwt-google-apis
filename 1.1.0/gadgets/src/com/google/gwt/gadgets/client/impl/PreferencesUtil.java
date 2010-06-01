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
 * Provides access to Gadget preferences support.
 */
public class PreferencesUtil extends JavaScriptObject {

  public static native PreferencesUtil nativeInitPrefs() /*-{
     return new $wnd._IG_Prefs();
   }-*/;

  protected PreferencesUtil() {
  }

  public final native boolean getBool(String name) /*-{
    return this.getBool(name) || false;
  }-*/;
  
  public final native String getMsg(String name) /*-{
    var maybeString = this.getMsg(name);
    return maybeString == undefined ? null : maybeString;
  }-*/;

  public final native String getString(String name) /*-{
    var maybeString = this.getString(name);
    return maybeString == undefined ? null : maybeString;
  }-*/;

  public final native void push(JavaScriptObject array, String value) /*-{
    array.push(value);
  }-*/;

  public final native void set(String name, String value) /*-{
    this.set(name, value);
  }-*/;

  public final native void setArray(String name, JavaScriptObject value) /*-{
    this.setArray(name, value);
  }-*/;
}

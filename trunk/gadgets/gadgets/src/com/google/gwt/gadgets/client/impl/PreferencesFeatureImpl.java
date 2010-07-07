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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.gadgets.client.PreferencesFeature;

/**
 * Provides access to native Gadget preferences support.
 */
public class PreferencesFeatureImpl extends JavaScriptObject implements
    PreferencesFeature {

  public static native PreferencesFeature get() /*-{
    return new $wnd.gadgets.Prefs();
  }-*/;

  public static native PreferencesFeature get(String moduleId) /*-{
    return new $wnd.gadgets.Prefs(moduleId);
  }-*/;

  protected PreferencesFeatureImpl() {
  }

  public final native JsArrayString getArray(String name) /*-{
    this.getArray(name);
  }-*/;

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

  public native final void set(String name, boolean value) /*-{
    this.set(name, value);
  }-*/;

  public final native void set(String name, String value) /*-{
    this.set(name, value);
  }-*/;

  public final native void setArray(String name, JavaScriptObject value) /*-{
    this.setArray(name, value);
  }-*/;

  public native final void setArray(String name, JsArrayString value) /*-{
    this.setArray(name, value);
  }-*/;
}

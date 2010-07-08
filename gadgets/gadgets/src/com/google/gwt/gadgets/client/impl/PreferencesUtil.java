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
import com.google.gwt.gadgets.client.PreferencesProvider;

/**
 * Provides access to Gadget preferences support.
 * 
 * @deprecated use {@link com.google.gwt.gadgets.client.PreferencesFeature}
 */
@Deprecated
public class PreferencesUtil {
  private static PreferencesUtil singleton;

  /**
   * @deprecated use 
   *             {@link com.google.gwt.gadgets.client.PreferencesProvider#get()}
   */
  @Deprecated
  public static PreferencesUtil nativeInitPrefs() {
    if (singleton == null) {
      singleton = new PreferencesUtil();
    }
    return singleton;
  }

  /**
   * @deprecated use
   *             {@link com.google.gwt.gadgets.client.PreferencesFeature#getBool(String)}
   */
  @Deprecated
  public final boolean getBool(String name) {
    return PreferencesProvider.get().getBool(name);
  }

  /**
   * @deprecated use
   *             {@link com.google.gwt.gadgets.client.PreferencesFeature#getMsg(String)}
   */
  @Deprecated
  public final String getMsg(String name) {
    return PreferencesProvider.get().getMsg(name);
  }

  /**
   * @deprecated use
   *             {@link com.google.gwt.gadgets.client.PreferencesFeature#getString(String)}
   */
  @Deprecated
  public final String getString(String name) {
    return PreferencesProvider.get().getString(name);
  }

  /**
   * @deprecated use something else like the JsArray classes.
   * 
   */
  @Deprecated
  public final native void push(JavaScriptObject array, String value) /*-{
    array.push(value);
  }-*/;

  /**
   * @deprecated use
   *             {@link com.google.gwt.gadgets.client.PreferencesFeature#set(String, 
   *             String)}
   */
  @Deprecated
  public final void set(String name, String value) {
    PreferencesProvider.get().set(name, value);
  }

  /**
   * @deprecated use 
   *             {@link com.google.gwt.gadgets.client.PreferencesFeature#setArray(String, 
   *             com.google.gwt.core.client.JsArrayString)}
   * 
   */
  @Deprecated
  public final void setArray(String name, JavaScriptObject value) {
    PreferencesProvider.get().setArray(name, (JsArrayString) value);
  }
}

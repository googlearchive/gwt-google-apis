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
    return new $wnd.gadgets.Prefs();
  }-*/;

  protected PreferencesUtil() {
  }

  /**
   * Retrieve an array. You can cast the return value to
   * JsArray<yourFavoriteType>
   * 
   * @param name the name of the Preference value to retrieve.
   * @return a JavaScript Array object.
   */
  public final native JavaScriptObject getArray(String name) /*-{
    var maybeArray = getArray(name);
    return maybeArray == null ? null : maybeArray;
  }-*/;

  /**
   * Returns a boolean preference value.
   * 
   * @param name the name of the Preference value to retrieve.
   * @return a boolean preference value.
   */
  public final native boolean getBool(String name) /*-{
    return this.getBool(name) || false;
  }-*/;

  /**
   * Gets the current country, returned as ISO 3166-1 alpha-2 code.
   * 
   * @return the current country, returned as ISO 3166-1 alpha-2 code.
   */
  public final native String getCountry() /*-{
    return this.getCountry();
  }-*/;

  /**
   * Retrieves a preference as a double.
   * 
   * @param name the name of the Preference value to retrieve.
   * @return a preference value as a double.
   */
  public final native String getDouble(String name) /*-{
    var maybeFloat = this.getFloat(name);
    return maybeFloat == null ? NaN : Number(maybeFloat);
  }-*/;

  /**
   * Retrieves a preference as a string.
   * 
   * @param name the name of the Preference value to retrieve.
   * @return a preference value as a string.
   */
  public final native String getInt(int name) /*-{
    var maybeInt = this.getInt(name);
    return maybeInt == null ? 0 : Number(maybeInt);
  }-*/;

  /**
   * Gets the current language the gadget should use when rendering, returned as
   * a ISO 639-1 language code.
   * 
   * @return the current language the gadget should use when rendering, returned
   *         as a ISO 639-1 language code.
   */
  public final native String getLang() /*-{
    return this.getLang();
  }-*/;

  /**
   * Gets the module ID for the current instance.
   * 
   * @return the module ID for this module instance
   */
  public final native String getModuleId() /*-{
    return this.getModuleId();
  }-*/;

  /**
   * Returns an unformatted messages.
   * 
   * @param name the name of the Preference value to retrieve.
   * @return an unformatted message.
   */
  public final native String getMsg(String name) /*-{
    var maybeString = this.getMsg(name);
    return maybeString == null ? null : maybeString;
  }-*/;

  /**
   * Retrieves a preference as a string.
   * 
   * @param name the name of the Preference value to retrieve.
   * @return a preference valueas a string.
   */
  public final native String getString(String name) /*-{
    var maybeString = this.getString(name);
    return maybeString == null ? null : maybeString;
  }-*/;

  /**
   * Pushes a string onto a JavaScript Array object.
   * 
   * @param array an existing instance of a JavaScript Array object
   * @param value A string to push onto the array.
   * @deprecated Use the JsArray classes instead.
   */
  @Deprecated
  public final native void push(JavaScriptObject array, String value) /*-{
    array.push(value);
  }-*/;

  /**
   * Stores a preference.
   * 
   * @param name the name of the Preference value to store.
   * @param value A string value to store in the preference.
   */
  public final native void set(String name, String value) /*-{
    this.set(name, value);
  }-*/;

  /**
   * Stores an array preference.
   * 
   * @param name the name of the Preference value to store.
   * @param value an array preference. Try using the JsArray class to represent
   *          the array.
   */
  public final native void setArray(String name, JavaScriptObject value) /*-{
    this.setArray(name, value);
  }-*/;

  /**
   * Stores a preference.
   * 
   * @param name the name of the Preference value to store.
   * @param value A boolean value to store in the preference.
   */
  public final native void setBool(String name, boolean value)/*-{
    this.set(name, value);
  }-*/;

  /**
   * Stores a preference.
   * 
   * @param name the name of the Preference value to store.
   * @param value an double value to store in the preference.
   */
  public final native void setDouble(String name, double value) /*-{
    this.set(name, value);
  }-*/;

  /**
   * Stores a preference.
   * 
   * @param name the name of the Preference value to store.
   * @param value an integer value to store in the preference.
   */
  public final native void setInt(String name, int value) /*-{
    this.set(name, value);
  }-*/;
}

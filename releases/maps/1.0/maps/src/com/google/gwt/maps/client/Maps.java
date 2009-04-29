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
package com.google.gwt.maps.client;

import com.google.gwt.ajaxloader.client.AjaxLoader;
import com.google.gwt.ajaxloader.client.AjaxLoader.AjaxLoaderOptions;

/**
 * A collection of static methods and API wide constants.
 */
public class Maps {

  /**
   * Check for the availability of the maps API. If it is not loaded, throws a
   * RuntimeException.
   */
  public static void assertLoaded() {
    if (!isLoaded()) {
      throw new RuntimeException("The Maps API has not been loaded.\n"
          + "Is a <script> tag missing from your host HTML or module file?"
          + "  Is the Maps key missing or invalid?");
    }
  }

  /**
   * Return the Maps API Version currently loaded.
   * 
   * @return the Maps API Version currently loaded.
   */
  public static native String getVersion() /*-{
    return $wnd.G_API_VERSION;
  }-*/;

  /**
   * Returns <code>true</code> if the current browser supports the maps API
   * library.
   * 
   * @return <code>true</code> if the current browser supports the maps API
   *         library.
   */
  public static native boolean isBrowserCompatible() /*-{
    return $wnd.GBrowserIsCompatible();
  }-*/;

  /**
   * Check for the availability of the Maps API. This means that the Maps API
   * script is loaded and has successfully initialized.
   * 
   * @return <code>true</code> if the Maps API is loaded.
   */
  public static native boolean isLoaded() /*-{
    return $wnd.GMap2 !== undefined;
  }-*/;

  /**
   * Returns <code>true</code> if the application's UI layout is right-to-left
   * in bidirectional languages such as Arabic or Hebrew.
   * 
   * Note: because bidirectional languages such as Arabic and Hebrew affect the
   * layout of the GInfoWindow object, these languages are only selected if
   * explicitly requested via an applicable <code>hl</code> parameter or through
   * the addition of the allow_bidi=true parameter to the API bootstrap request.
   * For more information about BCP 47, see the W3 documentation.
   * 
   * @return <code>true</code> if the application's UI layout is right-to-left
   *         in bidirectional languages such as Arabic or Hebrew.
   */
  public static native boolean isRTL() /*-{
    return $wnd.GLanguage.isRtl();
  }-*/;

  /**
   * Initializes the Maps API using the AjaxLoader. This is in lieu of
   * specifying the &lt;script&gt; tag for maps.google.com in your hosted HTML
   * page or project's GWT module specification.
   * 
   * @see "http://code.google.com/apis/maps/documentation/#AJAX_Loader"
   * 
   * @param key Maps API key. See http://code.google.com/apis/maps/signup.html
   * @param version The version of the maps API to load. For example, "2.x"
   * @param usingSensor Use of the Google Maps API now requires that you
   *          indicate whether your application is using a sensor (such as a GPS
   *          locator) to determine the user's location. This is especially
   *          important for mobile devices.
   * @param settings other AjaxLoader settings for the Maps API. This parameter
   *          must not be <code>null</code>.
   * @param onLoad callback to be invoked when the library is loaded.
   */
  public static void loadMapsApi(String key, String version,
      boolean usingSensor, AjaxLoaderOptions settings, Runnable onLoad) {
    assert settings != null;
    AjaxLoader.init(key);
    if (usingSensor) {
      settings.setOtherParms("sensor=true");
    }
    AjaxLoader.loadApi("maps", version, onLoad, settings);
  }

  /**
   * Initializes the Maps API using the AjaxLoader. This is in lieu of
   * specifying the &lt;script&gt; tag for maps.google.com in your hosted HTML
   * page or project's GWT module specification.
   * 
   * @see "http://code.google.com/apis/maps/documentation/#AJAX_Loader"
   * 
   * @param key Maps API key. See http://code.google.com/apis/maps/signup.html
   * @param version The version of the maps API to load. For example, "2.x"
   * @param usingSensor Use of the Google Maps API now requires that you
   *          indicate whether your application is using a sensor (such as a GPS
   *          locator) to determine the user's location. This is especially
   *          important for mobile devices.
   * @param onLoad callback to be invoked when the library is loaded.
   */
  public static void loadMapsApi(String key, String version,
      boolean usingSensor, Runnable onLoad) {
    loadMapsApi(key, version, usingSensor, AjaxLoaderOptions.newInstance(),
        onLoad);
  }

  /**
   * Writes the message as plain text into the log window. HTML markup
   * characters will be escaped so that they are visible as characters.
   * 
   * characters will be escaped so that they are visible as characters.
   * 
   * @param message the message to write to the log window.
   */
  public static native void logWrite(String message) /*-{
    $wnd.GLog.write(message);
  }-*/;

  /**
   * Writes the message as plain text into the log window. HTML markup
   * characters will be escaped so that they are visible as characters.
   * 
   * @param message the message to write to the log window.
   * @param color CSS color specification for the text.
   */
  public static native void logWrite(String message, String color) /*-{
    $wnd.GLog.write(message, color);
  }-*/;

  /**
   * Writes text as HTML in the log window.
   * 
   * @param html message to write into the log window.
   */
  public static native void logWriteHtml(String html) /*-{
    $wnd.GLog.write(html);
  }-*/;

  /**
   * Writes a link to the given URL into the log window.
   * 
   * @param url URL to write into the log window.
   */
  public static native void logWriteUrl(String url) /*-{
    $wnd.GLog.write(url);
  }-*/;

  /**
   * Use private constructor so this class can't be instantiated.
   */
  private Maps() {
  }
}

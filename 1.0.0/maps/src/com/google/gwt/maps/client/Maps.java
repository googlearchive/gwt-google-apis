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
   * Use private constructor so this class can't be instantiated.
   */
  private Maps() {
  }
}

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
package com.google.gwt.gears.core.client;

/**
 * Represents the Gears installation in the browser. The most interesting
 * methods to user code are {@link #getBuildInfo()} and
 * {@link GearsImpl#create(String, String)}.
 * 
 * Also contains utility methods used by other classes in this API. These
 * methods are intentionally package-scoped.
 */
public class Gears {
  /**
   * String used to request a Database instance from Gears.
   */
  public static final String DATABASE = "beta.database";

  public static final String GEARS_VERSION = "1.0";

  /**
   * String used to request a LocalServer instance from Gears.
   */
  public static final String LOCALSERVER = "beta.localserver";

  /**
   * String used to request a Database instance from Gears.
   */
  public static final String WORKERPOOL = "beta.workerpool";

  /**
   * Fetches an informational-only string describing the current Gears build.
   * 
   * This string has no defined format, and client code should not rely on its
   * contents or a particular parsing algorithm.
   * 
   * @return the build info string for the current version of Gears, or
   *         <code>null</code> if Gears is not installed.
   */
  public static String getBuildInfo() {
    if (isInstalled()) {
      return nativeGetBuildInfo();
    }

    return null;
  }

  /**
   * Returns <code>true</code> if Gears is installed.
   * 
   * @return <code>true</code> if Gears is installed
   */
  public static native boolean isInstalled() /*-{
    var available = $wnd.google && $wnd.google.gears;
    return available != null;
  }-*/;

  private static native String nativeGetBuildInfo() /*-{
    return $wnd.google.gears.factory.getBuildInfo();
  }-*/;

  /**
   * Default constructor. Intentionally private.
   */
  private Gears() {
    // Not instantiable
  }
}

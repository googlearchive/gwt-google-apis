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

/**
 * <p>
 * Provides general-purpose utility functions.
 * </p>
 *
 * <p>
 * See <a
 * href="http://code.google.com/apis/gadgets/docs/reference/#gadgets.util"
 * >specification</a> for details.
 */
public class GadgetsUtil {

  /**
   * Escapes the input using HTML entities to make it safer. The following
   * characters are affected:
   * <ul>
   * <li>newline (\n, Unicode code point 10)</li>
   * <li>carriage return (\r, Unicode 13)</li>
   * <li>double quote (", Unicode 34)</li>
   * <li>ampersand (&, Unicode 38)</li>
   * <li>single quote (', Unicode 39)</li>
   * <li>left angle bracket (<, Unicode 60)</li>
   * <li>right angle bracket (>, Unicode 62)</li>
   * <li>backslash (\, Unicode 92)</li>
   * <li>line separator (Unicode 8232)</li>
   * <li>paragraph separator (Unicode 8233)</li>
   * </ul>
   *
   * @param str The string to escape
   * @return The escaped string
   */
  public static native String escapeString(String str) /*-{
    return $wnd.gadgets.util.escapeString(str);
  }-*/;

  /**
   * Gets the URL parameter.
   *
   * @param param Name of the parameter
   * @return Value of the parameter
   */
  public static native String getUrlParameters(String param) /*-{
    return $wnd.gadgets.util.getUrlParameters()[param];
  }-*/;

  /**
   * Reverses {@link #escapeString(String)}
   *
   * @param str The string to unescape
   * @return The unescaped string
   */
  public static native String unescapeString(String str) /*-{
    return $wnd.gadgets.util.unescapeString(str);
  }-*/;

  private GadgetsUtil() {
  }
}

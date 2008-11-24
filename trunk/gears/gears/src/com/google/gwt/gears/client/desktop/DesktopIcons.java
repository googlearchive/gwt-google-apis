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
package com.google.gwt.gears.client.desktop;

import com.google.gwt.core.client.JavaScriptObject;

/**
 *  Represents a Desktop Icon that can be created to launch a browser App.
 *
 */
public final class DesktopIcons extends JavaScriptObject {
  public static DesktopIcons create() {
    DesktopIcons icons = JavaScriptObject.createObject().cast();
    return icons;
  }

  protected DesktopIcons() {
    // required for overlay types
  };

  /**
   * Sets a relative or absolute URL of a PNG-formatted image with dimensions of
   * 16x16 pixels, or a data:// URL containing base64-encoded PNG data.
   * <p>
   * Relative URLs are resolved using the caller's locationURL
   * 
   * @param url
   *          URL of a PNG-formatted image with dimensions of 16x16 pixels
   * @return this instance
   */
  public native DesktopIcons set128x128(String url)/*-{
    this['128x128'] = url;
    return this;
  }-*/;

  /**
   * Sets a relative or absolute URL of a PNG-formatted image with dimensions of
   * 16x16 pixels, or a data:// URL containing base64-encoded PNG data.
   * <p>
   * Relative URLs are resolved using the caller's locationURL
   * 
   * @param url
   *          URL of a PNG-formatted image with dimensions of 16x16 pixels
   * @return this instance
   */
  public native DesktopIcons set16x16(String url)/*-{
    this['16x16'] = url;
    return this;
  }-*/;

  /**
   * Sets a relative or absolute URL of a PNG-formatted image with dimensions of
   * 16x16 pixels, or a data:// URL containing base64-encoded PNG data.
   * <p>
   * Relative URLs are resolved using the caller's locationURL
   * 
   * @param url
   *          URL of a PNG-formatted image with dimensions of 16x16 pixels
   * @return this instance
   */
  public native DesktopIcons set32x32(String url)/*-{
    this['32x32'] = url;
    return this;
  }-*/;

  /**
   * Sets a relative or absolute URL of a PNG-formatted image with dimensions of
   * 16x16 pixels, or a data:// URL containing base64-encoded PNG data.
   * <p>
   * Relative URLs are resolved using the caller's locationURL
   * 
   * @param url
   *          URL of a PNG-formatted image with dimensions of 16x16 pixels
   * @return this instance
   */
  public native DesktopIcons set48x48(String url)/*-{
    this['48x48'] = url;
    return this;
  }-*/;
}

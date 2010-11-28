/* Copyright (c) 2010 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.gwt.maps.client.base;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Two-dimensional size, where width is the distance on the x-axis, and height is
 * the distance on the y-axis.
 */
public class Size extends JavaScriptObject {

  public static native final Size newInstance(int width, int height) /*-{
    return new $wnd.google.maps.Size(width, height);
  }-*/;

  protected Size() {}

  public native final boolean equalsTo(Size other) /*-{
    return this.equals(other);
  }-*/;

  public native final int getHeight() /*-{
    return this.height;
  }-*/;

  public native final int getWidth() /*-{
    return this.width;
  }-*/;
  
  public native final String getString() /*-{
    return this.toString();
  }-*/;

  public native final int setHeight(int height) /*-{
    this.height = height;
  }-*/;

  public native final int setWidth(int width) /*-{
    this.width = width;
  }-*/;

}

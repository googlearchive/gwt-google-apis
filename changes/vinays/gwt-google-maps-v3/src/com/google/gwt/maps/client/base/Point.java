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
 * A point on a two-dimensional plane.
 */
public class Point extends JavaScriptObject {

  public static native final Point newInstance(double x, double y) /*-{
    return $wnd.google.maps.Point(x, y);
  }-*/;
  
  protected Point() {}

  public native final boolean equalsTo(Point other) /*-{
    return this.equals(other);
  }-*/;

  public native final String getString() /*-{
    return this.toString();
  }-*/;

  public native final double getX() /*-{
    return this.x;
  }-*/;

  public native final double getY() /*-{
    return this.y;
  }-*/;

  public native final double setX(double x) /*-{
    this.x = x;
  }-*/;

  public native final double setY(double y) /*-{
    this.y = y;
  }-*/;

}

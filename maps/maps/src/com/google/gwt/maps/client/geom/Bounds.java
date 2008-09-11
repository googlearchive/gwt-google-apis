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
package com.google.gwt.maps.client.geom;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

/**
 * Represents a rectangular bound. A Bounds is defined by minimum and maximum X
 * and Y coordinates on a plane.
 * 
 */
public class Bounds extends JavaScriptObject {

  /**
   * A Bounds is defined by minimum and maximum X and Y coordinates on a plane.
   * 
   * @param points
   */
  public static final native Bounds newInstance(JsArray<Point> points) /*-{
    return new $wnd.GBounds(points);
  }-*/;

  public static final native Bounds newInstance(int minX, int minY, int maxX,
      int maxY) /*-{
    return new $wnd.GBounds([ new $wnd.GPoint(minX, minY), 
      new $wnd.GPoint(maxX, maxY)]);
  }-*/;

  protected Bounds() {
    // A protected constructor is required in a JS overlay.
  }

  /**
   * Returns <code>true</code> if the passed rectangular area is entirely
   * contained in this rectangular area.
   * 
   * @param other the bound to compare.
   * @return <code>true</code> if the passed rectangular area is entirely
   *         contained in this rectangular area.
   */
  public final native boolean containsBounds(Bounds other) /*-{
    return this.containsBounds(other);
  }-*/;

  /**
   * Returns <code>true</code> if the rectangular area (inclusively) contains
   * the pixel coordinates.
   * 
   * @param p the point to compare.
   * @return <code>true</code> if the rectangular area (inclusively) contains
   *         the pixel coordinates.
   */
  public final native boolean containsPoint(Point p) /*-{
    return this.containsPoint(p);
  }-*/;

  /**
   * Enlarges this box so that the point is also contained in this box.
   * 
   * @param point the point to add to the bound.
   */
  public final native void extend(Point point) /*-{
    return this.extend(point);
  }-*/;

  /**
   * Returns the pixel coordinates of the center of the rectangular area.
   * 
   * @return the pixel coordinates of the center of the rectangular area.
   */
  public final native Point getCenter() /*-{
    return this.mid();
  }-*/;

  /**
   * Returns the pixel coordinates of the lower right corner of the rectangular
   * area.
   * 
   * @return the pixel coordinates of the lower right corner of the rectangular
   *         area.
   */
  public final native Point getLowerRight() /*-{
    return this.max();
  }-*/;

  /**
   * Returns the x coordinate of the right edge of the rectangle.
   * 
   * @return the x coordinate of the right edge of the rectangle.
   */
  public final native int getMaxX() /*-{
    return this.maxX;
  }-*/;

  /**
   * Returns the y coordinate of the bottom edge of the rectangle.
   * 
   * @return the y coordinate of the bottom edge of the rectangle.
   */
  public final native int getMaxY() /*-{
    return this.maxY;
  }-*/;

  /**
   * Returns the x coordinate of the left edge of the rectangle.
   * 
   * @return the x coordinate of the left edge of the rectangle.
   */
  public final native int getMinX() /*-{
    return this.minX;
  }-*/;

  /**
   * Returns the y coordinate of the top edge of the rectangle.
   * 
   * @return the y coordinate of the top edge of the rectangle.
   */
  public final native int getMinY() /*-{
    return this.minY;
  }-*/;

  /**
   * Returns the pixel coordinates of the upper left corner of the rectangular
   * area.
   * 
   * @return the pixel coordinates of the upper left corner of the rectangular
   *         area.
   */
  public final native Point getUpperLeft() /*-{
    return this.min();
  }-*/;

}

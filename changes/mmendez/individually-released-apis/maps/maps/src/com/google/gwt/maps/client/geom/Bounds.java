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
import com.google.gwt.maps.client.impl.BoundsImpl;
import com.google.gwt.maps.client.impl.JsUtil;

/**
 * Represents a rectangular bound. A Bounds is defined by minimum and maximum X
 * and Y coordinates on a plane.
 * 
 */
public final class Bounds {

  static Bounds createPeer(JavaScriptObject jsoPeer) {
    return new Bounds(jsoPeer);
  }

  private final JavaScriptObject jsoPeer;

  /**
   * A Bounds is defined by minimum and maximum X and Y coordinates on a plane.
   * 
   * @param points
   */
  public Bounds(Point[] points) {
    jsoPeer = BoundsImpl.impl.construct(JsUtil.toJsList(points));
  }

  /**
   * Wrap an existing GBounds object.
   * 
   * @param jsoPeer object to wrap.
   */
  private Bounds(JavaScriptObject jsoPeer) {
    this.jsoPeer = jsoPeer;
  }

  /**
   * Returns <code>true</code> if the passed rectangular area is entirely contained in this
   * rectangular area.
   * 
   * @param other the bound to compare.
   * @return <code>true</code> if the passed rectangular area is entirely contained in this
   *         rectangular area.
   */
  public boolean containsBounds(Bounds other) {
    return BoundsImpl.impl.containsBounds(jsoPeer, other);
  }

  /**
   * Returns <code>true</code> if the rectangular area (inclusively) contains the pixel
   * coordinates.
   * 
   * @param p the point to compare.
   * @return <code>true</code> if the rectangular area (inclusively) contains the pixel
   *         coordinates.
   */
  public boolean containsPoint(Point p) {
    return BoundsImpl.impl.containsPoint(jsoPeer, p);
  }

  /**
   * Enlarges this box so that the point is also contained in this box.
   * 
   * @param point the point to add to the bound.
   * @return a new bounds object that encloses the previous bounds plus the
   *         supplied point.
   */
  public Bounds extend(Point point) {
    Point pointArgs[] = {this.getUpperLeft(), this.getLowerRight()};
    // The JavaScript API enlarges the existing Bounds object. This API creates
    // a new object, so we need to clone this Bounds instance first.
    Bounds b = new Bounds(pointArgs);
    BoundsImpl.impl.extend(b.jsoPeer, point);
    
    return b;
  }

  /**
   * Returns the pixel coordinates of the center of the rectangular area.
   * 
   * @return the pixel coordinates of the center of the rectangular area.
   */
  public Point getCenter() {
    return BoundsImpl.impl.mid(jsoPeer);
  }

  /**
   * Returns the pixel coordinates of the lower right corner of the rectangular
   * area.
   * 
   * @return the pixel coordinates of the lower right corner of the rectangular
   *         area.
   */
  public Point getLowerRight() {
    return BoundsImpl.impl.max(jsoPeer);
  }

  /**
   * Returns the x coordinate of the right edge of the rectangle.
   * 
   * @return the x coordinate of the right edge of the rectangle.
   */
  public int getMaxX() {
    return BoundsImpl.impl.getMaxX(jsoPeer);
  }

  /**
   * Returns the y coordinate of the bottom edge of the rectangle.
   * 
   * @return the y coordinate of the bottom edge of the rectangle.
   */
  public int getMaxY() {
    return BoundsImpl.impl.getMaxY(jsoPeer);
  }

  /**
   * Returns the x coordinate of the left edge of the rectangle.
   * 
   * @return the x coordinate of the left edge of the rectangle.
   */
  public int getMinX() {
    return BoundsImpl.impl.getMinX(jsoPeer);
  }

  /**
   * Returns the y coordinate of the top edge of the rectangle.
   * 
   * @return the y coordinate of the top edge of the rectangle.
   */
  public int getMinY() {
    return BoundsImpl.impl.getMinY(jsoPeer);
  }

  /**
   * Returns the pixel coordinates of the upper left corner of the rectangular
   * area.
   * 
   * @return the pixel coordinates of the upper left corner of the rectangular
   *         area.
   */
  public Point getUpperLeft() {
    return BoundsImpl.impl.min(jsoPeer);
  }

  @Override
  public String toString() {
    return BoundsImpl.impl.toString(jsoPeer);
  }
}

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

/**
 * Represents a rectangle in geographical coordinates, including one that
 * crosses the 180 degrees meridian.
 */
public class LatLngBounds extends JavaScriptObject {

  /**
   * Construct a new LatLngBounds object. The bounds will be the maximum for the
   * map.
   */
  public static native LatLngBounds newInstance() /*-{
    return new $wnd.GLatLngBounds();
  }-*/;

  /**
   * Construct a new LatLngBounds object by specifying two opposite corners.
   * 
   * @param southWest the south-west corner of the bounds to create.
   * @param northEast the north-east corner of the bounds to create.
   */
  public static native LatLngBounds newInstance(LatLng southWest,
      LatLng northEast) /*-{
    return new $wnd.GLatLngBounds(southWest, northEast);
  }-*/;

  protected LatLngBounds() {
    // Protected constructor required for JavaScript overlay
  }

  /**
   * Tests the specified point to see if it is contained within the range of
   * this bounds object.
   * 
   * @deprecated See {@link #containsLatLng(LatLng)}
   * @param coordinate point to compare.
   * @return <code>true</code> if the specified point is contained within the
   *         range of this bounds object.
   * 
   */
  @Deprecated
  public final native boolean contains(LatLng coordinate) /*-{
    return this.contains(coordinate);
  }-*/;

  /**
   * Returns <code>true</code> if the passed rectangle is contained within
   * this rectangle.
   * 
   * @param other bounds to compare.
   * @return <code>true</code> if the geographical coordinates of the bounds
   *         lie within this rectangle.
   */
  public final native boolean containsBounds(LatLngBounds other) /*-{
    return this.containsBounds(other);
  }-*/;

  /**
   * Returns true iff the geographical coordinates of the point lie within this
   * rectangle.
   * 
   * @param coordinate point to compare
   * @return <code>true</code> if the geographical coordinates of the point
   *         lie within this rectangle.
   */
  public final native boolean containsLatLng(LatLng coordinate) /*-{
    return this.containsLatLng(coordinate);
  }-*/;

  /**
   * Returns a new rectangle such that it contains the given point. In longitude
   * direction, it is created in the smaller of the two possible ways. If both
   * are equal, it is created at the eastern boundary.
   * 
   * @param coordinate coordinate to use in creating the new LatLngBounds
   *          object.
   */
  public final native void extend(LatLng coordinate) /*-{
    this.extend(coordinate);
  }-*/;

  /**
   * Returns the point at the center of the rectangle.
   * 
   * @return the point at the center of the rectangle.
   */
  public final native LatLng getCenter() /*-{
    return this.getCenter();
  }-*/;

  /**
   * Returns the point at the north-east corner of the rectangle.
   * 
   * @return the point at the north-east corner of the rectangle.
   */
  public final native LatLng getNorthEast() /*-{
    return this.getNorthEast();
  }-*/;

  /**
   * Returns the point at the south-west corner of the rectangle.
   * 
   * @return the point at the south-west corner of the rectangle.
   */
  public final native LatLng getSouthWest() /*-{
    return this.getSouthWest();
  }-*/;

  /**
   * Returns <code>true</code> if the specified rectangle intersects this
   * rectangle.
   * 
   * @param other the rectangle to compare.
   * @return <code>true</code> if the specified rectangle intersects this
   *         rectangle.
   */
  public final native boolean intersects(LatLngBounds other) /*-{
    return this.intersects(other);
  }-*/;

  /**
   * Returns <code>true</code> if this rectangle is empty.
   * 
   * @return <code>true</code> if this rectangle is empty.
   */
  public final native boolean isEmpty() /*-{
    return this.isEmpty();
  }-*/;

  /**
   * Returns
   * <code>true<code> if this rectangle extends from the south pole to the north
   * pole.
   * 
   * @return <code>true</code> if this rectangle extends from the south pole to the north
   *         pole.
   */
  public final native boolean isFullLatitude() /*-{
    return this.isFullLat();
  }-*/;

  /**
   * Returns <code>true</code> if this rectangle extends fully around the
   * earth in the longitude direction.
   * 
   * @return <code>true</code> if this rectangle extends fully around the
   *         earth in the longitude direction.
   */
  public final native boolean isFullLongitude() /*-{
    return this.isFullLng();
  }-*/;

  /**
   * Returns a LatLng whose coordinates represent the size of this rectangle.
   * 
   * @return a LatLng whose coordinates represent the size of this rectangle.
   */
  public final native LatLng toSpan() /*-{
    return this.toSpan();
  }-*/;
}

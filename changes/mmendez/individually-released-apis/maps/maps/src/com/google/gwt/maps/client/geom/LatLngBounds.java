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

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.impl.LatLngBoundsImpl;

/**
 * Represents a rectangle in geographical coordinates, including one that
 * crosses the 180 degrees meridian.
 */
public final class LatLngBounds {

  private static final LatLngBoundsImpl impl = GWT.create(LatLngBoundsImpl.class);

  /**
   * Wraps an existing GLatLngBounds JavaScriptObject.
   * 
   * @param jsoPeer the object to wrap.
   * @return a new instance of a LatLngBounds object
   */
  static LatLngBounds createPeer(JavaScriptObject jsoPeer) {
    return new LatLngBounds(jsoPeer);
  }

  private final JavaScriptObject jsoPeer;

  /**
   * Construct a new LatLngBounds object. The bounds will be the maximum for the
   * map.
   */
  public LatLngBounds() {
    jsoPeer = impl.construct();
  }

  /**
   * Construct a new LatLngBounds object by specifying two opposite corners.
   * 
   * @param southWest the south-west corner of the bounds to create.
   * @param northEast the north-east corner of the bounds to create.
   */
  public LatLngBounds(LatLng southWest, LatLng northEast) {
    jsoPeer = impl.construct(southWest, northEast);
  }

  /**
   * Create a new LatLngBounds object by wrapping an existing GLatLngBounds
   * JavaScript object.
   * 
   * @param jsoPeer object to wrap.
   */
  private LatLngBounds(JavaScriptObject jsoPeer) {
    this.jsoPeer = jsoPeer;
  }

  /**
   * Tests the specified point to see if it is contained within the range of
   * this bounds object.
   * 
   * @param coordinate point to compare.
   * @return <code>true</code> if the specified point is contained within the
   *         range of this bounds object.
   */
  public boolean contains(LatLng coordinate) {
    return impl.contains(jsoPeer, coordinate);
  }

  /**
   * Returns <code>true</code> if the passed rectangle is contained within
   * this rectangle.
   * 
   * @param other bounds to compare.
   * @return <code>true</code> if the geographical coordinates of the bounds
   *         lie within this rectangle.
   */
  public boolean containsBounds(LatLngBounds other) {
    return impl.containsBounds(jsoPeer, other);
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof LatLngBounds) {
      return impl.equals(jsoPeer, (LatLngBounds) other);
    }
    return false;
  }

  /**
   * Returns a new rectangle such that it contains the given point. In longitude
   * direction, it is created in the smaller of the two possible ways. If both
   * are equal, it is created at the eastern boundary.
   * 
   * @param coordinate coordinate to use in creating the new LatLngBounds
   *          object.
   * @return a new rectangle such that it contains the given point.
   */
  public LatLngBounds extend(LatLng coordinate) {
    LatLngBounds extended = new LatLngBounds(getSouthWest(), getNorthEast());
    impl.extend(extended.jsoPeer, coordinate);
    return extended;
  }

  /**
   * Returns the point at the center of the rectangle.
   * 
   * @return the point at the center of the rectangle.
   */
  public LatLng getCenter() {
    return impl.getCenter(jsoPeer);
  }

  /**
   * Returns the point at the north-east corner of the rectangle.
   * 
   * @return the point at the north-east corner of the rectangle.
   */
  public LatLng getNorthEast() {
    return impl.getNorthEast(jsoPeer);
  }

  /**
   * Returns the point at the south-west corner of the rectangle.
   * 
   * @return the point at the south-west corner of the rectangle.
   */
  public LatLng getSouthWest() {
    return impl.getSouthWest(jsoPeer);
  }

  @Override
  public int hashCode() {
    return getNorthEast().hashCode() ^ (37 * getSouthWest().hashCode());
  }

  /**
   * Returns <code>true</code> if the specified rectangle intersects this
   * rectangle.
   * 
   * @param other the rectangle to compare.
   * @return <code>true</code> if the specified rectangle intersects this
   *         rectangle.
   */
  public boolean intersects(LatLngBounds other) {
    return impl.intersects(jsoPeer, other);
  }

  /**
   * Returns <code>true</code> if this rectangle is empty.
   * 
   * @return <code>true</code> if this rectangle is empty.
   */
  public boolean isEmpty() {
    return impl.isEmpty(jsoPeer);
  }

  /**
   * Returns
   * <code>true<code> if this rectangle extends from the south pole to the north
   * pole.
   * 
   * @return <code>true</code> if this rectangle extends from the south pole to the north
   *         pole.
   */
  public boolean isFullLatitude() {
    return impl.isFullLat(jsoPeer);
  }

  /**
   * Returns <code>true</code> if this rectangle extends fully around the
   * earth in the longitude direction.
   * 
   * @return <code>true</code> if this rectangle extends fully around the
   *         earth in the longitude direction.
   */
  public boolean isFullLongitude() {
    return impl.isFullLng(jsoPeer);
  }

  /**
   * Returns a LatLng whose coordinates represent the size of this rectangle.
   * 
   * @return a LatLng whose coordinates represent the size of this rectangle.
   */
  public LatLng toSpan() {
    return impl.toSpan(jsoPeer);
  }
}

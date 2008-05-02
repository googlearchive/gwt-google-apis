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
import com.google.gwt.maps.client.impl.PointImpl;
import com.google.gwt.maps.jsio.client.impl.Extractor;

/**
 * 
 */
public final class Point {

  // TODO: DELETE ME! (needs to function w/o)
  @SuppressWarnings("unused")
  private static final Extractor<Point> __extractor = new Extractor<Point>() {
    public Point fromJS(JavaScriptObject jso) {
      return createPeer(jso);
    }

    public JavaScriptObject toJS(Point point) {
      return point.jsoPeer;
    }
  };

  private static final PointImpl impl = GWT.create(PointImpl.class);

  static Point createPeer(JavaScriptObject jso) {
    return new Point(jso);
  }

  private final JavaScriptObject jsoPeer;

  public Point(int x, int y) {
    jsoPeer = impl.construct(x, y);
  }

  private Point(JavaScriptObject jso) {
    this.jsoPeer = jso;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Point) {
      return impl.equals(jsoPeer, (Point) obj);
    }
    return false;
  }

  /**
   * Returns the X coordinate.
   * @return the X coordinate.
   */
  public int getX() {
    return impl.getX(jsoPeer);
  }

  /**
   * Returns the Y coordinate.
   * @return the Y coordinate.
   */
  public int getY() {
    return impl.getY(jsoPeer);
  }

  @Override
  public int hashCode() {
    // Bias the Y coordinate using a prime number (101) so that inverted coordinates
    // do not hash to the same value.
    return getX() + (101 * getY());
  }

  @Override
  public String toString() {
    return impl.toString(jsoPeer);
  }

}

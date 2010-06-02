/* Copyright (c) 2010 Vinay Inc.
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

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.base.impl.PointImpl;

/**
 * This class implements {@link HasPoint}.
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public class Point implements HasPoint {

  final private PointImpl impl;
  final private JavaScriptObject jso;
  
  public Point(final PointImpl impl, final JavaScriptObject jso) {
    this.impl = impl;
    this.jso = jso;
  }
  
  public Point(final JavaScriptObject jso) {
    this.impl = GWT.create(PointImpl.class);
    this.jso = jso;
  }
  
  public Point(double x, double y) {
    this.impl = GWT.create(PointImpl.class);
    this.jso = impl.construct(x, y);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.base.HasPoint#equalsTo(com.google.gwt.maps.client.base.HasPoint)
   */
  @Override
  public boolean equalsTo(HasPoint other) {
    return impl.equals(jso, other.getJso());
  }
  
  /* (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object other) {
    if (other == null || !(other instanceof Point)) {
      return false;
    }
    return equalsTo((HasPoint) other);
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return impl.toString(jso);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.base.HasPoint#getJso()
   */
  @Override
  public JavaScriptObject getJso() {
    return jso;
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.base.HasPoint#getX()
   */
  @Override
  public double getX() {
    return impl.getX(jso);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.base.HasPoint#getY()
   */
  @Override
  public double getY() {
    return impl.getY(jso);
  }

}

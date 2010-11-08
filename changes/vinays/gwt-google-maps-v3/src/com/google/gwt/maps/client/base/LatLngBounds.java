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

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.base.impl.LatLngBoundsImpl;


/**
 * This class implements {@link HasLatLngBounds}.
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public class LatLngBounds implements HasLatLngBounds {

  final private LatLngBoundsImpl impl;
  final private JavaScriptObject jso;
  
  public LatLngBounds(final LatLngBoundsImpl impl, final JavaScriptObject jso) {
    this.impl = impl;
    this.jso = jso;
  }
  
  public LatLngBounds(final JavaScriptObject jso) {
    this.impl = GWT.create(LatLngBoundsImpl.class);
    this.jso = jso;
  }
  
  public LatLngBounds() {
    impl = GWT.create(LatLngBoundsImpl.class);
    jso = impl.construct();
  }

  public LatLngBounds(final LatLng sw, final LatLng ne) {
    impl = GWT.create(LatLngBoundsImpl.class);
    jso = impl.construct(sw, ne);
  }
  
  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.base.HasLatLngBounds#contains(com.google.gwt.maps.client.base.HasLatLng)
   */
  @Override
  public boolean contains(LatLng point) {
    return impl.contains(jso, point);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.base.HasLatLngBounds#equalsTo(com.google.gwt.maps.client.base.HasLatLngBounds)
   */
  @Override
  public boolean equalsTo(HasLatLngBounds other) {
    return impl.equals(jso, other.getJso());
  }
  
  /* (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object other) {
    if (other == null || !(other instanceof LatLngBounds)) {
      return false;
    }
    return equalsTo((HasLatLngBounds) other);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.base.HasLatLngBounds#extend(com.google.gwt.maps.client.base.HasLatLng)
   */
  @Override
  public HasLatLngBounds extend(LatLng point) {
    return new LatLngBounds(impl, impl.extend(jso, point));
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.base.HasLatLngBounds#getCenter()
   */
  @Override
  public LatLng getCenter() {
    return (LatLng) impl.getCenter(jso);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.base.HasLatLngBounds#getNorthEast()
   */
  @Override
  public LatLng getNorthEast() {
    return (LatLng) impl.getNorthEast(jso);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.base.HasLatLngBounds#getSouthWest()
   */
  @Override
  public LatLng getSouthWest() {
    return (LatLng) impl.getSouthWest(jso);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.base.HasLatLngBounds#intersects(com.google.gwt.maps.client.base.HasLatLngBounds)
   */
  @Override
  public boolean intersects(HasLatLngBounds other) {
    return impl.intersects(jso, other.getJso());
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.base.HasLatLngBounds#isEmpty()
   */
  @Override
  public boolean isEmpty() {
    return impl.isEmpty(jso);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.base.HasLatLngBounds#toSpan()
   */
  @Override
  public LatLng toSpan() {
    return (LatLng) impl.toSpan(jso);
  }
  
  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return impl.toString(jso);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.base.HasLatLngBounds#toUrlValue()
   */
  @Override
  public String toUrlValue() {
    return impl.toUrlValue(jso);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.base.HasLatLngBounds#toUrlValue(int)
   */
  @Override
  public String toUrlValue(int precision) {
    return impl.toUrlValue(jso, precision);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.base.HasLatLngBounds#union(com.google.gwt.maps.client.base.HasLatLngBounds)
   */
  @Override
  public HasLatLngBounds union(HasLatLngBounds other) {
    return new LatLngBounds(impl, impl.union(jso, other.getJso()));
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.base.HasLatLngBounds#getJso()
   */
  @Override
  public JavaScriptObject getJso() {
    return jso;
  }

}

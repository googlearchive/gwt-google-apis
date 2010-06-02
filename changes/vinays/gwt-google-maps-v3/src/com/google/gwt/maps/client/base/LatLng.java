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
import com.google.gwt.maps.client.base.impl.LatLngImpl;

/**
 * This class implements {@link HasLatLng}.
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public class LatLng implements HasLatLng {

  final private LatLngImpl impl;
  final private JavaScriptObject jso;
  
  public LatLng(final LatLngImpl impl, final JavaScriptObject jso) {
    this.impl = impl;
    this.jso = jso;
  }
  
  public LatLng(final JavaScriptObject jso) {
    this.impl = GWT.create(LatLngImpl.class);
    this.jso = jso;
  }
  
  public LatLng(final double lat, final double lng) {
    impl = GWT.create(LatLngImpl.class);
    jso = impl.construct(lat, lng);
  }
  
  public LatLng(final double lat, final double lng, boolean noWrap) {
    impl = GWT.create(LatLngImpl.class);
    jso = impl.construct(lat, lng, noWrap);
  }
  
  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.base.HasLatLng#getLatitude()
   */
  @Override
  public double getLatitude() {
    return impl.lat(jso);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.base.HasLatLng#getLongitude()
   */
  @Override
  public double getLongitude() {
    return impl.lng(jso);
  }
  
  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return impl.toString(jso);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.base.HasLatLng#toUrlValue()
   */
  @Override
  public String toUrlValue() {
    return impl.toUrlValue(jso);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.base.HasLatLng#toUrlValue(int)
   */
  @Override
  public String toUrlValue(int precision) {
    return impl.toUrlValue(jso, precision);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.base.HasLatLng#equalsTo(com.google.gwt.maps.client.base.HasLatLng)
   */
  @Override
  public boolean equalsTo(HasLatLng other) {
    return impl.equals(jso, other.getJso());
  }
  
  /* (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object other) {
    if (other == null || !(other instanceof LatLng)) {
      return false;
    }
    return equalsTo((HasLatLng) other);
  }
  
  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.base.HasLatLng#getJso()
   */
  @Override
  public JavaScriptObject getJso() {
    return jso;
  }

}

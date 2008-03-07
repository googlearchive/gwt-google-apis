/*
 * Copyright 2007 Google Inc.
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
 * 
 */
public final class LatLngBounds {

  private static final LatLngBoundsImpl impl =
      (LatLngBoundsImpl) GWT.create(LatLngBoundsImpl.class);

  static LatLngBounds createPeer(JavaScriptObject jsoPeer) {
    return new LatLngBounds(jsoPeer);
  }

  private final JavaScriptObject jsoPeer;

  public LatLngBounds() {
    jsoPeer = impl.construct();
  }

  public LatLngBounds(LatLng southWest, LatLng northEast) {
    jsoPeer = impl.construct(southWest, northEast);
  }

  private LatLngBounds(JavaScriptObject jsoPeer) {
    this.jsoPeer = jsoPeer;
  }

  public boolean contains(LatLng coordinate) {
    return impl.contains(jsoPeer, coordinate);
  }

  public boolean containsBounds(LatLngBounds other) {
    return impl.containsBounds(jsoPeer, other);
  }

  public boolean equals(Object other) {
    if (other instanceof LatLngBounds) {
      return impl.equals(jsoPeer, (LatLngBounds) other);
    }
    return false;
  }

  public LatLngBounds extend(LatLng coordinate) {
    LatLngBounds extended = new LatLngBounds(getSouthWest(), getNorthEast());
    impl.extend(extended.jsoPeer, coordinate);
    return extended;
  }

  public LatLng getCenter() {
    return impl.getCenter(jsoPeer);
  }

  public LatLng getNorthEast() {
    return impl.getNorthEast(jsoPeer);
  }

  public LatLng getSouthWest() {
    return impl.getSouthWest(jsoPeer);
  }

  public boolean intersects(LatLngBounds other) {
    return impl.intersects(jsoPeer, other);
  }

  public boolean isEmpty() {
    return impl.isEmpty(jsoPeer);
  }

  public boolean isFullLatitude() {
    return impl.isFullLat(jsoPeer);
  }

  public boolean isFullLongitude() {
    return impl.isFullLng(jsoPeer);
  }

  public LatLng toSpan() {
    return impl.toSpan(jsoPeer);
  }
}

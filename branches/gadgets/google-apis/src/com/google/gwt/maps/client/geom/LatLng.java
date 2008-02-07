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
import com.google.gwt.jsio.client.impl.Extractor;
import com.google.gwt.maps.client.impl.LatLngImpl;

/**
 * A geographical point represented by a latitude and a longitude.
 * 
 * Instances of this class are immutable.
 */
public final class LatLng {

  private static final LatLngImpl impl = (LatLngImpl) GWT.create(LatLngImpl.class);

  // TODO: DELETE ME! (needs to function w/o)
  private static final Extractor __extractor = new Extractor() {
    public Object fromJS(JavaScriptObject jso) {
      return createPeer(jso);
    }

    public JavaScriptObject toJS(Object o) {
      return ((LatLng) o).jsoPeer;
    }
  };

  static LatLng createPeer(JavaScriptObject jso) {
    return new LatLng(jso);
  }

  private final JavaScriptObject jsoPeer;

  public LatLng(double latitude, double longitude) {
    jsoPeer = impl.construct(latitude, longitude);
  }
  
  public LatLng(double latitude, double longitude, boolean unbounded) {
    jsoPeer = impl.construct(latitude, longitude, unbounded);
  }

  private LatLng(JavaScriptObject jso) {
    this.jsoPeer = jso;
  }

  /**
   * Returns the distance from another LatLng in meters.
   * 
   * This measurement can be off by as much as 0.3% since the Earth is
   * approximated as a sphere.
   * 
   * @param other
   * @return
   */
  public double distanceFrom(LatLng other) {
    return impl.distanceFrom(jsoPeer, other);
  }

  public boolean equals(Object other) {
    if (other instanceof LatLng) {
      return impl.equals(jsoPeer, (LatLng) other);
    }
    return false;
  }

  /**
   * Returns the latitude coordinate of this point in degrees as a number
   * between -90 and 90.
   * 
   * @return the latitude coordinate of this point in degrees.
   */
  public double getLatitude() {
    return impl.lat(jsoPeer);
  }

  /**
   * Returns the latitude coordinate of this point in radians as a number
   * between -<i>pi</i>/2 and <i>pi</i>/2.
   * 
   * @return the latitude coordinate of this point in radians
   */
  public double getLatitudeRadians() {
    return impl.latRadians(jsoPeer);
  }

  /**
   * Returns the longitude coordinate of this point in degrees as a number
   * between -180 and 180.
   * 
   * @return the longitude coordinate of this point in degrees.
   */
  public double getLongitude() {
    return impl.lng(jsoPeer);
  }

  /**
   * Returns the longitude coordinate of this point in radians as a number
   * between -<i>pi</i> and <i>pi</i>.
   * 
   * @return the longitude coordinate of this point in degrees.
   */
  public double getLongitudeRadians() {
    return impl.lngRadians(jsoPeer);
  }

  public String toString() {
    return impl.toString(jsoPeer);
  }

  /**
   * Returns a string that represents this point in a format suitable for use as
   * a URL parameter value.
   * 
   * For example, a precision of six digits corresponds to a resolution of 4
   * inches or 11 centimeters.
   * 
   * @param precision the precision in number of digits
   * @return a URL-safe string that represents this point
   */
  public String toUrlValue(int precision) {
    return impl.toUrlValue(jsoPeer, precision);
  }
}

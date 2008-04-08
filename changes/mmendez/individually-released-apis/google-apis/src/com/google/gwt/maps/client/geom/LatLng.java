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
import com.google.gwt.jsio.client.impl.Extractor;
import com.google.gwt.maps.client.impl.LatLngImpl;

/**
 * A geographical point represented by a latitude and a longitude.
 * 
 * Instances of this class are immutable.
 */
public final class LatLng {

  // TODO: DELETE ME! (needs to function w/o)
  @SuppressWarnings("unused")
  private static final Extractor<LatLng> __extractor = new Extractor<LatLng>() {
    public LatLng fromJS(JavaScriptObject jso) {
      return createPeer(jso);
    }

    public JavaScriptObject toJS(LatLng o) {
      return o.jsoPeer;
    }
  };

  private static final LatLngImpl impl = GWT.create(LatLngImpl.class);

  /**
   * Wrap an existing GLatLng.
   * 
   * @param jso the object to wrap
   * @return a newly created LatLng that wraps the GLatLng JavaScript object.
   */
  static LatLng createPeer(JavaScriptObject jso) {
    return new LatLng(jso);
  }

  private final JavaScriptObject jsoPeer;

  /**
   * Create a new point. latitude will be clamped to lie between -90 degrees and
   * +90 degrees, and longitude will be wrapped to lie between -180 degrees and
   * +180 degrees.
   * 
   * @param latitude value between -90 and +90 degrees (clamped)
   * @param longitude value between -180 and +180 degrees (wrapped)
   */
  public LatLng(double latitude, double longitude) {
    jsoPeer = impl.construct(latitude, longitude);
  }

  /**
   * Create a new point. latitude will be clamped to lie between -90 degrees and
   * +90 degrees, and longitude will be wrapped to lie between -180 degrees and
   * +180 degrees.
   * 
   * @param latitude value between -90 and +90 degrees (clamped)
   * @param longitude value between -180 and +180 degrees (wrapped)
   * @param unbounded if <code>true</code>, then numbers will not be wrapped or clamped.
   */
  public LatLng(double latitude, double longitude, boolean unbounded) {
    jsoPeer = impl.construct(latitude, longitude, unbounded);
  }

  /**
   * Create a new object from an existing java script object.
   * @param jso object to wrap.
   */
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
   * @return the distance computed between the two points
   */
  public double distanceFrom(LatLng other) {
    return impl.distanceFrom(jsoPeer, other);
  }

  @Override
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

  @Override
  public int hashCode() {
    return ((int) getLatitude() * 1000000)
        ^ (19 * (int) getLongitude() * 1000000);
  }

  @Override
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

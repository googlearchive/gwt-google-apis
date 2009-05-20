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
import com.google.gwt.core.client.JsArray;

/**
 * A geographical point represented by a latitude and a longitude.
 * 
 * Instances of this class are immutable.
 */
public class LatLng extends JavaScriptObject {

  /**
   * Converts the supplied latitude/longitude String value into a LatLng object.
   * The passed string should be in the format "latitude,longitude." Any space
   * between the latitude and longitude values will be ignored. This method will
   * return a LatLng with precision to 6 digits
   * 
   * @return a LatLng with precision to 6 digits.
   */
  public static native LatLng fromUrlValue(String value) /*-{
    return $wnd.GLatLng.fromUrlValue(value);
  }-*/;

  /**
   * Create a new point. latitude will be clamped to lie between -90 degrees and
   * +90 degrees, and longitude will be wrapped to lie between -180 degrees and
   * +180 degrees.
   * 
   * @param latitude value between -90 and +90 degrees (clamped)
   * @param longitude value between -180 and +180 degrees (wrapped)
   */
  public static native LatLng newInstance(double latitude, double longitude) /*-{
    @com.google.gwt.maps.client.Maps::assertLoaded()();
    return new $wnd.GLatLng(latitude, longitude);
  }-*/;

  /**
   * Create a new point. latitude will be clamped to lie between -90 degrees and
   * +90 degrees, and longitude will be wrapped to lie between -180 degrees and
   * +180 degrees.
   * 
   * @param latitude value between -90 and +90 degrees (clamped)
   * @param longitude value between -180 and +180 degrees (wrapped)
   * @param unbounded if <code>true</code>, then numbers will not be wrapped or
   *          clamped.
   */
  public static native LatLng newInstance(double latitude, double longitude,
      boolean unbounded) /*-{
    @com.google.gwt.maps.client.Maps::assertLoaded()();
    return new $wnd.GLatLng(latitude, longitude, unbounded);
  }-*/;

  /**
   * Convenience routine for creating a JsArray from Java array of LatLng
   * values.
   * 
   * @param points A Java array of LatLng values
   * @return A JavaScript array of LatLng values
   */
  @SuppressWarnings("unchecked")
  public static JsArray<LatLng> toJsArray(LatLng[] points) {
    JsArray<LatLng> result = (JsArray<LatLng>) createArray();
    for (int i = 0; i < points.length; i++) {
      result.set(i, points[i]);
    }
    return result;
  }

  protected LatLng() {
    // Protected constructor required for JavaScript overlays.
  }

  /**
   * Returns the distance from another LatLng in meters.
   * 
   * By default, this distance is calculated given the default equatorial earth
   * radius of 6378137 meters. This measurement can be off by as much as 0.3%
   * since the Earth is approximated as a sphere. See
   * {@link #distanceFrom(LatLng, double)}.
   * 
   * @param other point to measure distance to
   * @return the distance computed between the two points
   */
  public final native double distanceFrom(LatLng other) /*-{
    return this.distanceFrom(other);
  }-*/;

  /**
   * Returns the distance from another LatLng in meters.
   * 
   * By default, this distance is calculated given the default equatorial earth
   * radius of 6378137 meters. The earth is approximated as a sphere, hence the
   * distance could be off as much as 0.3%, especially in the polar extremes.
   * 
   * @param other point to measure distance to
   * @param radius alternative radius value to use.
   * @return the distance computed between the two points
   */
  public final native double distanceFrom(LatLng other, double radius) /*-{
    return this.distanceFrom(other, radius);
  }-*/;

  /**
   * Returns the latitude coordinate of this point in degrees as a number
   * between -90 and 90.
   * 
   * @return the latitude coordinate of this point in degrees.
   */
  public final native double getLatitude() /*-{
    return this.lat();
  }-*/;

  /**
   * Returns the latitude coordinate of this point in radians as a number
   * between -<i>pi</i>/2 and <i>pi</i>/2.
   * 
   * @return the latitude coordinate of this point in radians
   */
  public final native double getLatitudeRadians() /*-{
    return this.latRadians();
  }-*/;

  /**
   * Returns the longitude coordinate of this point in degrees as a number
   * between -180 and 180.
   * 
   * @return the longitude coordinate of this point in degrees.
   */
  public final native double getLongitude() /*-{
    return this.lng();
  }-*/;

  /**
   * Returns the longitude coordinate of this point in radians as a number
   * between -<i>pi</i> and <i>pi</i>.
   * 
   * @return the longitude coordinate of this point in degrees.
   */
  public final native double getLongitudeRadians() /*-{
    return this.lngRadians();
  }-*/;

  /**
   * Does what equals() ought to do, but we are constrained by the JS overlay
   * rules.
   * 
   * @param other a point to compare
   * @return <code>true</code> if the latitude and longitude match.
   */
  public final native boolean isEquals(LatLng other) /*-{
    return this.equals(other);
  }-*/;

  /**
   * Returns a string that represents this point in a format suitable for use as
   * a URL parameter value.
   * 
   * @return a URL-safe string that represents this point
   */
  public final native String toUrlValue() /*-{
    return this.toUrlValue();
  }-*/;

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
  public final native String toUrlValue(int precision) /*-{
    return this.toUrlValue(precision);
  }-*/;
}
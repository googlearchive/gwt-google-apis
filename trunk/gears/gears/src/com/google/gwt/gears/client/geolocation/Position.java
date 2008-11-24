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
package com.google.gwt.gears.client.geolocation;

import com.google.gwt.core.client.JavaScriptObject;

import java.util.Date;

/**
 * An object representing a position returned from the Geolocation service.
 */
public final class Position extends JavaScriptObject {

  protected Position() {
    // required for overlay types
  }

  /**
   * Gets the horizontal accuracy of the position in meters, or null if not
   * available.
   * 
   * @return the horizontal accuracy of the position in meters, or null if not
   *         available
   */
  public native double getAccuracy() /*-{
    return this.accuracy;
  }-*/;

  /**
   * Gets the height in meters (WGS84 datum), or null if not available.
   * 
   * @return the height in meters (WGS84 datum), or null if not available.
   */
  public native double getAltitude() /*-{
    return this.altitude;
  }-*/;

  /**
   * Gets the vertical accuracy of the position in meters, or null if not
   * available.
   * 
   * @return the vertical accuracy of the position in meters, or null if not
   *         available.
   */
  public native double getAltitudeAccuracy() /*-{
    return this.altitudeAccuracy;
  }-*/;

  /**
   * Gets the reverse-geocoded {@link Address}, if requested and available.
   * 
   * @return the reverse-geocoded {@link Address}, if requested and available.
   */
  public native Address getGearsAddress() /*-{
    return this.gearsAddress;
  }-*/;

  /**
   * Gets the latitude in degrees using the World Geodetic System 1984 (WGS84)
   * datum.
   * 
   * @return the latitude in degrees using the World Geodetic System 1984
   *         (WGS84) datum
   */
  public native double getLatitude() /*-{
    return this.latitude;
  }-*/;

  /**
   * Gets the longitude in degrees (WGS84 datum).
   * 
   * @return the longitude in degrees (WGS84 datum).
   */
  public native double getLongitude() /*-{
    return this.longitude;
  }-*/;

  /**
   * Gets the time when the location was established.
   * 
   * @return the time when the location was established.
   */
  public Date getTimestamp() {
    return new Date((long) getTimestamp0());
  }

  private native double getTimestamp0() /*-{
    return this.timestamp.getTime();
  }-*/;
}

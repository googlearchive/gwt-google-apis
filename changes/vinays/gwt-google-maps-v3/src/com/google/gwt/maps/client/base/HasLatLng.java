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

import com.google.gwt.core.client.JavaScriptObject;


/**
 * LatLng is a point in geographical coordinates, latitude and longitude. Notice
 * that although usual map projections associate longitude with the x-coordinate
 * of the map, and latitude with the y-coordinate, the latitude coordinate is
 * always written first, followed by the longitude. Notice also that you cannot
 * modify the coordinates of a LatLng. If you want to compute another point, you
 * have to create a new one.
 * 
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public interface HasLatLng {

  /**
   * Returns the latitude in degrees.
   */
  public double getLatitude();

  /**
   * Returns the longitude in degrees.
   */
  public double getLongitude();
  
  /**
   * Converts to string representation.
   */
  public String toString();

  /**
   * Returns a string of the form "lat,lng" for this LatLng. We round the
   * lat/lng values to 6 decimal places by default.
   */
  public String toUrlValue();

  /**
   * Returns a string of the form "lat,lng" for this LatLng. We round the
   * lat/lng values to 6 decimal places by default.
   */
  public String toUrlValue(int precision);
  
  /**
   * Comparison function.
   */
  public boolean equalsTo(HasLatLng other);
  
  /**
   * @return the jso.
   */
  public JavaScriptObject getJso();
}

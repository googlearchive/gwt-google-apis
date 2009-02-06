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
package com.google.gwt.maps.client.geocode;

import com.google.gwt.maps.client.geom.LatLng;

/**
 * This class is used to create an array of arguments to the {@link Directions}
 * class for specifying multi-point directions.
 */
public class Waypoint {
  private final String data;

  /**
   * Construct a waypoint from a point.
   * 
   * @param point point that specifies this waypoint.
   */
  public Waypoint(LatLng point) {
    data = point.getLatitude() + "," + point.getLongitude();
  }

  /**
   * Construct a waypoint from a {@link Placemark}.
   * 
   * @param placemark an address that specifies this waypoint.
   */
  public Waypoint(Placemark placemark) {
    data = placemark.getAddress();
  }

  /**
   * Construct a waypoint from an address entered as a string.
   * 
   * @param address an address that specifies this waypoint.
   */
  public Waypoint(String address) {
    data = address;
  }
  
  @Override
  public String toString() {
    return data;
  }
}

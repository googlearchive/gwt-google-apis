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
package com.google.gwt.maps.client.geocode;

/**
 * These constant values are returned by {@link Placemark#getAccuracy()}.
 */
public final class GeoAddressAccuracy {

  /*
   * Design note: the JavaScript Maps API does not actually define these
   * constants, it simply specifies the meaning of the numeric values. Thus,
   * there is no way to seed these constants from JavaScript.
   */
  public static final int UNKNOWN_LOCATION = 0;

  public static final int COUNTRY = 1;

  public static final int REGION = 2;

  public static final int SUB_REGION = 3;

  public static final int TOWN = 4;

  public static final int POSTAL_CODE = 5;

  public static final int STREET = 6;

  public static final int INTERSECTION = 7;

  public static final int ADDRESS = 8;

  // This class is not meant to be instantiated by users
  private GeoAddressAccuracy() {
  }
}

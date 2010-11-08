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
package com.google.gwt.maps.client.geocoder;

import com.google.gwt.maps.client.HasJso;
import com.google.gwt.maps.client.base.HasLatLngBounds;
import com.google.gwt.maps.client.base.LatLng;

/**
 * GeocoderGeometry information about this GeocoderResult.
 *
 */
public interface HasGeocoderGeometry extends HasJso {
  
  /**
   * Describes the type of location returned from a geocode.
   */
  public static enum GeocoderLocationType {
    /**
     * The returned result is approximate.
     */
    APPROXIMATE ("APPROXIMATE"),
    /**
   * The returned result is the geometric center of a result such a line (e.g.
   * street) or polygon (region).
   */
    GEOMETRIC_CENTER ("GEOMETRIC_CENTER"),
    /**
   * The returned result reflects an approximation (usually on a road)
   * interpolated between two precise points (such as intersections).
   * Interpolated results are generally returned when rooftop geocodes are
   * unavilable for a street address.
   */
    RANGE_INTERPOLATED ("RANGE_INTERPOLATED"),
    /**
   * The returned result reflects a precise geocode.
   */
    ROOFTOP ("ROOFTOP");
    
    private String value;
    
    GeocoderLocationType(String value) {
      this.value = value;
    }
    
    @Override
    public String toString() {
      return value;
    }
  }

  /**
   * The latitude/longitude coordinates of this result.
   */
  public LatLng getLocation();
  
  /**
   * The type of location returned in location.
   */
  public GeocoderLocationType getLocationType();
  
  /**
   * The bounds of the recommended viewport for displaying this GeocodeResult.
   */
  public HasLatLngBounds getViewport();
  
  /**
   * The precise bounds of this GeocodeResult, if applicable.
   */
  public HasLatLngBounds getBounds(); // optional.
}

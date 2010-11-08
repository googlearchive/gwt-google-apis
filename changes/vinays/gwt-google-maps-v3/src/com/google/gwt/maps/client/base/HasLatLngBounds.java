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
package com.google.gwt.maps.client.base;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * A LatLngBounds instance represents a rectangle in geographical coordinates,
 * including one that crosses the 180 degrees longitudinal meridian.
 * 
 */
public interface HasLatLngBounds {
  
  /**
   * Returns true if the given lat/lng is in this bounds.
   */
  public boolean contains(LatLng point);
  
  /**
   * Returns true if this bounds approximately equals the given bounds.
   */
  public boolean equalsTo(HasLatLngBounds other);
  
  /**
   * Extends this bounds to contain the given point.
   */
  public HasLatLngBounds extend(LatLng point);
  
  /**
   * Computes the center of this LatLngBounds
   */
  public LatLng getCenter();
  
  /**
   * Returns the north-east corner of this bounds.
   */
  public LatLng getNorthEast();
  
  /**
   * Returns the south-west corner of this bounds.
   */
  public LatLng getSouthWest();
  
  /**
   * Returns true if this bounds shares any points with this bounds.
   */
  public boolean intersects(HasLatLngBounds other);
  
  /**
   * Returns if the bounds are empty.
   */
  public boolean isEmpty();
  
  /**
   * Converts the given map bounds to a lat/lng span.
   */
  public LatLng toSpan();
  
  /**
   * Converts to string.
   */
  public String toString();

  /**
   * Returns a string of the form "lat_lo,lng_lo,lat_hi,lng_hi" for this bounds,
   * where "lo" corresponds to the southwest corner of the bounding box, while
   * "hi" corresponds to the northeast corner of that box.
   */
  public String toUrlValue();
  
  /**
   * Returns a string of the form "lat_lo,lng_lo,lat_hi,lng_hi" for this bounds,
   * where "lo" corresponds to the southwest corner of the bounding box, while
   * "hi" corresponds to the northeast corner of that box.
   */
  public String toUrlValue(int precision);
  
  /**
   * Extends this bounds to contain the union of this and the given bounds.
   */
  public HasLatLngBounds union(HasLatLngBounds other);
  
  /**
   * Gets the jso.
   */
  public JavaScriptObject getJso();
}

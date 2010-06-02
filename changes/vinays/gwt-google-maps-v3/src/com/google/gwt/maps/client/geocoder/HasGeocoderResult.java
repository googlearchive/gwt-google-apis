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
package com.google.gwt.maps.client.geocoder;

import java.util.List;

import com.google.gwt.maps.client.HasJso;

/**
 * A single geocoder result retrieved from the geocode server. Note that a
 * geocode may return multiple result objects.
 * 
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public interface HasGeocoderResult extends HasJso {

  /**
   * An array of strings denoting the type of the returned geocoded element. A
   * type consists of a unique string identifying the geocode result. (For
   * example, "administrative_area_level_1", "country", etc.)
   */
  public List<String> getTypes();
  
  /**
   * An array of {@link GeocoderAddressComponent}s.
   */
  public List<HasAddressComponent> getAddressComponents();
  
  @Deprecated
  public String getFormattedAddress();
  
  /**
   * A {@link GeocoderGeometry} object
   */
  public HasGeocoderGeometry getGeometry();
  
}

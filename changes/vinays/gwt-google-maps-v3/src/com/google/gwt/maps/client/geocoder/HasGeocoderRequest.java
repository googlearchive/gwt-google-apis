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
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.base.LatLngBounds;

/**
 * The specification for a geocoding request to be sent to the {@link Geocoder}.
 *
 */
public interface HasGeocoderRequest extends HasJso {
  
  public String getAddress();
  
  /**
   * Address. Optional.
   */
  public void setAddress(String address);
  
  public LatLngBounds getBounds();
  
  /**
   * LatLngBounds within which to search. Optional.
   */
  public void setBounds(LatLngBounds bounds);
  
  public String getLanguage();
  
  /**
   * Preferred language for results. Optional.
   */
  public void setLanguage(String language);
  
  public LatLng getLatLng();
  
  /**
   * LatLng about which to search. Optional.
   */
  public void setLatLng(LatLng latLng);
  
  public String getRegion();
  
  /**
   * Country code top-level domain within which to search. Optional.
   */
  public void setRegion(String region);
  
}

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
 * A single address component within a GeocoderResult. A full address may
 * consist of multiple address components.
 * 
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public interface HasAddressComponent extends HasJso {

  /**
   * The abbreviated, short text of the given address component.
   */
  public String getShortName();
  
  /**
   * The full text of the address component.
   */
  public String getLongName();
  
  /**
   * An array of strings denoting the type of this address component.
   */
  public List<String> getTypes();
  
}

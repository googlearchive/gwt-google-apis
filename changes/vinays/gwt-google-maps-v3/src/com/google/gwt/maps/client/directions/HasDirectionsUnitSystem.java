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
package com.google.gwt.maps.client.directions;

/**
 * The valid unit systems that can be specified in a DirectionsRequest.
 * 
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public interface HasDirectionsUnitSystem {

  /**
   * Specifies that distances in the DirectionsResult should be expressed in
   * imperial units.
   */
  public String Imperial();

  /**
   * Specifies that distances in the DirectionsResult should be expressed in
   * metric units.
   */
  public String Metric();

}

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

import java.util.List;

import com.google.gwt.maps.client.HasJso;

/**
 * A single route containing a set of legs in JSON format in a DirectionsResult.
 * 
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public interface HasDirectionsRoute extends HasJso {

  /**
   * Copyrights text to be displayed for this trip.
   */
  public String getCopyrights();
  
  /**
   * An array of DirectionsLegs, each of which contains information about the
   * steps of which it is composed. There will be one leg for each waypoint or
   * destination specified. So a route with no waypoints will contain one
   * DirectionsLeg and a route with one waypoint will contain two.
   */
  public List<HasDirectionsLeg> getLegs();
  
  /**
   * Warnings to be displayed when showing these directions.
   */
  public List<String> getWarnings();
  
}

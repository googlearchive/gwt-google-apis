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
 * The status returned by the DirectionsService on the completion of a call to
 * route().
 * 
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public interface HasDirectionsStatus {

  /**
   * The DirectionsRequest provided was invalid.
   */
  public String InvalidRequest();

  /**
   * Too many DirectionsWaypoints were provided in the DirectionsRequest. The
   * total allowed waypoints is 8, plus the origin, and destination.
   */
  public String MaxWaypointsExceeded();

  /**
   * At least one of the origin, destination, or waypoints could not be
   * geocoded.
   */
  public String NotFound();

  /**
   * The response contains a valid DirectionsResult.
   */
  public String Ok();

  /**
   * The webpage has gone over the requests limit in too short a period of time.
   */
  public String OverQueryLimit();

  /**
   * The webpage is not allowed to use the directions service.
   */
  public String RequestDenied();

  /**
   * A directions request could not be processed due to a server error. The
   * request may succeed if you try again.
   */
  public String UnknownError();

  /**
   * No route could be found between the origin and destination.
   */
  public String ZeroResults();

}

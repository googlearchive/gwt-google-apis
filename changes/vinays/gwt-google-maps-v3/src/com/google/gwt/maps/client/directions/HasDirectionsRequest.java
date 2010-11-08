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
package com.google.gwt.maps.client.directions;

import com.google.gwt.maps.client.HasJso;
import com.google.gwt.maps.client.base.LatLng;

import java.util.List;

/**
 * A directions query to be sent to the {@link DirectionsService}.
 * 
 */
public interface HasDirectionsRequest extends HasJso {

  public LatLng getDestinationLatLng();

  public String getDestinationString();

  public LatLng getOriginLatLng();

  public String getOriginString();

  public boolean isProvideRouteAlternatives();

  public String getRegion();

  public String getTravelMode();

  public String getUnitSystem();

  public List<HasDirectionsWaypoint> getWaypoints();

  /**
   * Location of destination. This can be specified as either a LatLng to be
   * geocoded. Required.
   */
  public void setDestinationLatLng(LatLng destination);

  /**
   * Location of destination. This can be specified as either a LatLng to be
   * geocoded. Required.
   */
  public void setDestinationString(String destination);

  /**
   * Location of origin. This can be specified as either a LatLng to be
   * geocoded. Required.
   */
  public void setOriginLatLng(LatLng origin);

  /**
   * Location of origin. This can be specified as either a string to be geocoded
   * or a LatLng. Required.
   */
  public void setOriginString(String origin);

  /**
   * Whether or not route alternatives should be provided. Optional.
   */
  public void setProvideRouteAlternatives(boolean provideTripAlternatives);

  /**
   * Region code used as a bias for geocoding requests. Optional.
   */
  public void setRegion(String region);

  /**
   * Type of routing requested. Required.
   */
  public void setTravelMode(String travelMode);

  /**
   * Preferred unit system to use when displaying distance. Defaults to the unit
   * system used in the country of origin.
   */
  public void setUnitSystem(String unitSystem);

  /**
   * Array of intermediate waypoints. Directions will be calculated from the
   * origin to the destination by way of each waypoint in this array. Optional.
   */
  public void setWaypoints(List<HasDirectionsWaypoint> waypoints);
}

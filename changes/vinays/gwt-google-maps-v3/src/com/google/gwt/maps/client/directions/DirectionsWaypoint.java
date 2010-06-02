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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.base.HasLatLng;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.directions.impl.DirectionsWaypointImpl;

/**
 * This class implements {@link HasDirectionsWaypoint}.
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public class DirectionsWaypoint implements HasDirectionsWaypoint {

  final private JavaScriptObject jso;
  
  public DirectionsWaypoint(JavaScriptObject jso) {
    this.jso = jso;
  }
  
  public DirectionsWaypoint() {
    this(DirectionsWaypointImpl.impl.construct());
  }

  @Override
  public HasLatLng getLocationLatLng() {
    return new LatLng(DirectionsWaypointImpl.impl.getLocationLatLng(jso));
  }

  @Override
  public String getLocationString() {
    return DirectionsWaypointImpl.impl.getLocationString(jso);
  }

  @Override
  public boolean isStopover() {
    return DirectionsWaypointImpl.impl.isStopover(jso);
  }

  @Override
  public void setLocation(String location) {
    DirectionsWaypointImpl.impl.setLocationString(jso, location);
  }

  @Override
  public void setLocation(HasLatLng point) {
    DirectionsWaypointImpl.impl.setLocationLatLng(jso, point.getJso());
  }

  @Override
  public void setStopover(boolean isStopover) {
    DirectionsWaypointImpl.impl.setStopover(jso, isStopover);
  }

  @Override
  public JavaScriptObject getJso() {
    return jso;
  }

}

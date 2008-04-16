/*
 * Copyright 2008 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.gwt.maps.client.geocode;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.impl.RouteImpl;

/**
 * Created by the {@link Directions} class to store information about a single
 * route in a directions result. Clients should not directly create objects of
 * this class.
 */
// TODO(zundel): Should we provide a List to access the steps in the route? This
// would be consistent with our implementation in {@link DirectionsResult}.
public final class Route {

  static Route createPeer(JavaScriptObject jsoPeer) {
    return new Route(jsoPeer);
  }

  private final JavaScriptObject jsoPeer;

  /**
   * Construct a Route by wrapping an existing GRoute JavaScript object.
   * 
   * @param jsoPeer the JavaScriptObject to wrap.
   */
  private Route(JavaScriptObject jsoPeer) {
    this.jsoPeer = jsoPeer;
  }

  /**
   * Returns the total distance of this route.
   * 
   * @return the total distance of this route.
   */
  public Distance getDistance() {
    return RouteImpl.impl.getDistance(jsoPeer);
  }

  /**
   * Returns the total estimated time of this route.
   * 
   * @return the total estimated time of this route.
   */
  public Duration getDuration() {
    return RouteImpl.impl.getDuration(jsoPeer);
  }

  /**
   * Returns the ending point of this route.
   * 
   * @return the ending point of this route.
   * 
   * @see Route#getEndLatLng()
   */
  public Placemark getEndGeocode() {
    return RouteImpl.impl.getEndGeocode(jsoPeer);
  }

  /**
   * Returns a {@link LatLng} object for the last point along the polyline for
   * this route. Note that this point may be different from the lat,lng in
   * {@link Route#getEndGeocode()} because getEndLatLng() always returns a point
   * that is snapped to the road network. There is no corresponding
   * getStartLatLng() method because that is identical to calling
   * Route.getStep(0).getLatLng().
   * 
   * @return the last point along the polyline for this route.
   */
  public LatLng getEndLatLng() {
    return RouteImpl.impl.getEndLatLng(jsoPeer);
  }

  /**
   * Returns the number of steps in this route.
   * 
   * @return the number of steps in this route.
   */
  public int getNumSteps() {
    return RouteImpl.impl.getNumSteps(jsoPeer);
  }

  /**
   * Returns the starting point of this route.
   * 
   * @return the starting point of this route.
   * 
   * @see Route#getEndLatLng()
   */
  public Placemark getStartGeocode() {
    return RouteImpl.impl.getStartGeocode(jsoPeer);
  }

  /**
   * Returns the Step object for the ith step in this route.
   * 
   * 
   * @param index The index of the step to return in the route
   * @return the Step object for the ith step in this route
   */
  public Step getStep(int index) {
    return RouteImpl.impl.getStep(jsoPeer, index);
  }

  /**
   * Returns an HTML snippet containing a summary of the distance and time for
   * this route.
   * 
   * @return an HTML snippet containing a summary of the distance and time for
   *         this route.
   */
  public String getSummaryHtml() {
    return RouteImpl.impl.getSummaryHtml(jsoPeer);
  }
}

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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.directions.impl.DirectionsRequestImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements {@link HasDirectionsRequest}.
 *
 */
public class DirectionsRequest implements HasDirectionsRequest {

  final private JavaScriptObject jso;
  
  public DirectionsRequest() {
    this.jso = DirectionsRequestImpl.impl.construct();
  }

  @Override
  public LatLng getDestinationLatLng() {
    return DirectionsRequestImpl.impl.getDestinationLatLng(jso).cast();
  }

  @Override
  public String getDestinationString() {
    return DirectionsRequestImpl.impl.getDestinationString(jso);
  }

  @Override
  public LatLng getOriginLatLng() {
    return DirectionsRequestImpl.impl.getOriginLatLng(jso).cast();
  }

  @Override
  public String getOriginString() {
    return DirectionsRequestImpl.impl.getOriginString(jso);
  }

  @Override
  public String getRegion() {
    return DirectionsRequestImpl.impl.getRegion(jso);
  }

  @Override
  public String getTravelMode() {
    return DirectionsRequestImpl.impl.getTravelMode(jso);
  }

  @Override
  public String getUnitSystem() {
    return DirectionsRequestImpl.impl.getUnitSystem(jso);
  }

  @Override
  public List<HasDirectionsWaypoint> getWaypoints() {
    final List<HasDirectionsWaypoint> waypoints = new ArrayList<HasDirectionsWaypoint>();
    final JsArray<JavaScriptObject> wptArray = DirectionsRequestImpl.impl.getWaypoints(jso);
    for (int i = 0; i < wptArray.length(); ++i) {
      waypoints.add(new DirectionsWaypoint(wptArray.get(i)));
    }
    return waypoints;
  }

  @Override
  public boolean isProvideRouteAlternatives() {
    return DirectionsRequestImpl.impl.isProvideRouteAlternatives(jso);
  }

  @Override
  public void setDestinationLatLng(LatLng destination) {
    DirectionsRequestImpl.impl.setDestinationLatLng(jso, destination);
  }

  @Override
  public void setDestinationString(String destination) {
    DirectionsRequestImpl.impl.setDestinationString(jso, destination);
  }

  @Override
  public void setOriginLatLng(LatLng origin) {
    DirectionsRequestImpl.impl.setOriginLatLng(jso, origin);
  }

  @Override
  public void setOriginString(String origin) {
    DirectionsRequestImpl.impl.setOriginString(jso, origin);
  }

  @Override
  public void setProvideRouteAlternatives(boolean provideTripAlternatives) {
    DirectionsRequestImpl.impl.setProvideRouteAlternatives(jso, provideTripAlternatives);
  }

  @Override
  public void setRegion(String region) {
    DirectionsRequestImpl.impl.setRegion(jso, region);
  }

  @Override
  public void setTravelMode(String travelMode) {
    DirectionsRequestImpl.impl.setTravelMode(jso, travelMode);
  }

  @Override
  public void setUnitSystem(String unitSystem) {
    DirectionsRequestImpl.impl.setUnitSystem(jso, unitSystem);
  }

  @SuppressWarnings("unchecked")
  @Override
  public void setWaypoints(List<HasDirectionsWaypoint> waypoints) {
    JsArray<JavaScriptObject> wpts = (JsArray<JavaScriptObject>) JavaScriptObject.createArray();
    for (int i = 0; i < waypoints.size(); ++i) {
      final HasDirectionsWaypoint waypoint = waypoints.get(i);
      wpts.set(i, waypoint.getJso());
    }
    DirectionsRequestImpl.impl.setWaypoints(jso, wpts);
  }

  @Override
  public JavaScriptObject getJso() {
    return jso;
  }

}

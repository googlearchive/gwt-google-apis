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
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.directions.impl.DirectionsStepImpl;

import java.util.ArrayList;
import java.util.List;

public class DirectionsStep implements HasDirectionsStep {

  final private JavaScriptObject jso;
  
  public DirectionsStep(final JavaScriptObject jso) {
    this.jso = jso;
  }

  @Override
  public HasTextAndValue getDistance() {
    return new DirectionsDistance(DirectionsStepImpl.impl.getDistance(jso));
  }

  @Override
  public HasTextAndValue getDuration() {
    return new DirectionsDuration(DirectionsStepImpl.impl.getDuration(jso));
  }

  @Override
  public LatLng getEndPoint() {
    return DirectionsStepImpl.impl.getEndPoint(jso).cast();
  }

  @Override
  public String getInstructions() {
    return DirectionsStepImpl.impl.getInstructions(jso);
  }

  @Override
  public List<LatLng> getPath() {
    List<LatLng> latLngs = new ArrayList<LatLng>();
    for (JavaScriptObject js : DirectionsStepImpl.impl.getPath(jso)) {
      latLngs.add((LatLng) js);
    }
    return latLngs;
  }

  @Override
  public LatLng getStartPoint() {
    return DirectionsStepImpl.impl.getStartPoint(jso).cast();
  }

  @Override
  public JavaScriptObject getJso() {
    return jso;
  }

}

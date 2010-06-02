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

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.base.HasLatLng;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.directions.impl.DirectionsStepImpl;

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
  public HasLatLng getEndPoint() {
    return new LatLng(DirectionsStepImpl.impl.getEndPoint(jso));
  }

  @Override
  public String getInstructions() {
    return DirectionsStepImpl.impl.getInstructions(jso);
  }

  @Override
  public List<HasLatLng> getPath() {
    List<HasLatLng> latLngs = new ArrayList<HasLatLng>();
    for (JavaScriptObject js : DirectionsStepImpl.impl.getPath(jso)) {
      latLngs.add(new LatLng(js));
    }
    return latLngs;
  }

  @Override
  public HasLatLng getStartPoint() {
    return new LatLng(DirectionsStepImpl.impl.getStartPoint(jso));
  }

  @Override
  public JavaScriptObject getJso() {
    return jso;
  }

}

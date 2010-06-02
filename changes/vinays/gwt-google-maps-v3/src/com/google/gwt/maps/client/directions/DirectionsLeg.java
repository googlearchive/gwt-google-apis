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

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.directions.impl.DirectionsLegImpl;
import com.google.gwt.maps.client.geocoder.GeocoderResult;
import com.google.gwt.maps.client.geocoder.HasGeocoderResult;

public class DirectionsLeg implements HasDirectionsLeg {

  final private DirectionsLegImpl impl;
  final private JavaScriptObject jso;
  
  public DirectionsLeg(final DirectionsLegImpl impl, final JavaScriptObject jso) {
    this.impl = impl;
    this.jso = jso;
  }
  
  public DirectionsLeg(final JavaScriptObject jso) {
    this.impl = GWT.create(DirectionsLegImpl.class);
    this.jso = jso;
  }

  @Override
  public HasTextAndValue getDistance() {
    return new DirectionsDistance(impl.getDistance(jso));
  }

  @Override
  public HasTextAndValue getDuration() {
    return new DirectionsDuration(impl.getDuration(jso));
  }

  @Override
  public HasGeocoderResult getEndGeocode() {
    return new GeocoderResult(impl.getEndGeocode(jso));
  }

  @Override
  public HasGeocoderResult getStartGeocode() {
    return new GeocoderResult(impl.getStartGeocode(jso));
  }

  @Override
  public List<HasDirectionsStep> getSteps() {
    List<HasDirectionsStep> steps = new ArrayList<HasDirectionsStep>();
    for (JavaScriptObject js : impl.getSteps(jso)) {
      steps.add(new DirectionsStep(js));
    }
    return steps;
  }

  @Override
  public JavaScriptObject getJso() {
    return jso;
  }

}

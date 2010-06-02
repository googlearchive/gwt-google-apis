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
import com.google.gwt.maps.client.directions.impl.DirectionsRouteImpl;

public class DirectionsRoute implements HasDirectionsRoute {

  final private DirectionsRouteImpl impl;
  final private JavaScriptObject jso;
  
  public DirectionsRoute(final DirectionsRouteImpl impl, final JavaScriptObject jso) {
    this.impl = impl;
    this.jso = jso;
  }
  
  public DirectionsRoute(final JavaScriptObject jso) {
    this.impl = GWT.create(DirectionsRouteImpl.class);
    this.jso = jso;
  }

  @Override
  public String getCopyrights() {
    return impl.getCopyrights(jso);
  }

  @Override
  public List<HasDirectionsLeg> getLegs() {
    final List<HasDirectionsLeg> legs = new ArrayList<HasDirectionsLeg>();
    for (JavaScriptObject js : impl.getLegs(jso)) {
      legs.add(new DirectionsLeg(js));
    }
    return legs;
  }

  @Override
  public List<String> getWarnings() {
    final List<String> warnings = new ArrayList<String>();
    warnings.addAll(impl.getWarnings(jso));
    return warnings;
  }

  @Override
  public JavaScriptObject getJso() {
    return jso;
  }

}

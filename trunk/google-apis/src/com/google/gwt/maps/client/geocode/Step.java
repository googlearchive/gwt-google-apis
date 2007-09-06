/*
 * Copyright 2007 Google Inc.
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
import com.google.gwt.maps.client.impl.StepImpl;

public final class Step {

  private final JavaScriptObject jsoPeer;

  static Step createPeer(JavaScriptObject jsoPeer) {
    return new Step(jsoPeer);
  }

  private Step(JavaScriptObject jsoPeer) {
    this.jsoPeer = jsoPeer;
  }

  public LatLng getLatLng() {
    return StepImpl.impl.getLatLng(jsoPeer);
  }

  public int getPolylineIndex() {
    return StepImpl.impl.getPolylineIndex(jsoPeer);
  }

  public String getDescriptionHtml() {
    return StepImpl.impl.getDescriptionHtml(jsoPeer);
  }

  public Distance getDistance() {
    return StepImpl.impl.getDistance(jsoPeer);
  }

  public Duration getDuration() {
    return StepImpl.impl.getDuration(jsoPeer);
  }

}

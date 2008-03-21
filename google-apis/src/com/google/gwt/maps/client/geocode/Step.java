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
import com.google.gwt.maps.client.impl.StepImpl;

/**
 * Created by the Directions object to store information about a single step
 * within a route in a directions result. Clients can not directly create
 * objects of this class.
 */
public final class Step {

  /**
   * Construct a new instance of a Step object by wrapping an existing GStep
   * JavaScriptObject.
   * 
   * @param jsoPeer the object to wrap
   * @return The wrapped object.
   */
  static Step createPeer(JavaScriptObject jsoPeer) {
    return new Step(jsoPeer);
  }

  private final JavaScriptObject jsoPeer;

  private Step(JavaScriptObject jsoPeer) {
    this.jsoPeer = jsoPeer;
  }

  /**
   * Returns an HTML string containing the description of this step.
   * @return an HTML string containing the description of this step.
   */
  public String getDescriptionHtml() {
    return StepImpl.impl.getDescriptionHtml(jsoPeer);
  }

  /**
   * Returns the total distance of this step. 
   * @return the total distance of this step. 
   */
  public Distance getDistance() {
    return StepImpl.impl.getDistance(jsoPeer);
  }

  /**
   * Returns the total time of this step.
   * @return the total time of this step.
   */
  public Duration getDuration() {
    return StepImpl.impl.getDuration(jsoPeer);
  }
  
  /**
   * Returns the first point along the polyline for this step.
   * @return the first point along the polyline for this step.
   */
  public LatLng getLatLng() {
    return StepImpl.impl.getLatLng(jsoPeer);
  }

  /**
   * Returns the index of the first point along the polyline for this step.
   * @return the index of the first point along the polyline for this step.
   */
  public int getPolylineIndex() {
    return StepImpl.impl.getPolylineIndex(jsoPeer);
  }
}

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

/**
 * Created by the Directions object to store information about a single step
 * within a route in a directions result. Clients can not directly create
 * objects of this class.
 */
public class Step extends JavaScriptObject {

  protected Step() {
    // Required for JavaScript overlay types
  }

  /**
   * Returns an HTML string containing the description of this step.
   * 
   * @return an HTML string containing the description of this step.
   */
  public final native String getDescriptionHtml() /*-{
    return this.getDescriptionHtml();
  }-*/;

  /**
   * Returns the total distance of this step.
   * 
   * @return the total distance of this step.
   */
  public final native Distance getDistance() /*-{
    return this.getDistance();
  }-*/;

  /**
   * Returns the total time of this step.
   * 
   * @return the total time of this step.
   */
  public final native Duration getDuration() /*-{
    return this.getDuration();
  }-*/;

  /**
   * Returns the first point along the polyline for this step.
   * 
   * @return the first point along the polyline for this step.
   */
  public final native LatLng getLatLng() /*-{
    return this.getLatLng();
  }-*/;

  /**
   * Returns the index of the first point along the polyline for this step.
   * 
   * @return the index of the first point along the polyline for this step.
   */
  public final native int getPolylineIndex() /*-{
    return this.getPolylineIndex();
  }-*/;
}
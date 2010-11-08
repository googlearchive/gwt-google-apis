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

import com.google.gwt.maps.client.HasJso;
import com.google.gwt.maps.client.base.LatLng;

import java.util.List;

/**
 * A single DirectionsStep in a DirectionsResult. Some fields may be undefined.
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public interface HasDirectionsStep extends HasJso {

  /**
   * The distance covered by this step. This property may be undefined as the
   * distance may be unknown.
   */
  public HasTextAndValue getDistance();
  
  /**
   * The typical time required to perform this step in seconds and in text form.
   * This property may be undefined as the duration may be unknown.
   */
  public HasTextAndValue getDuration();
  
  /**
   * The ending point of this step.
   */
  public LatLng getEndPoint();
  
  /**
   * Instructions for this step.
   */
  public String getInstructions();
  
  /**
   * A sequence of LatLngs describing the course of this step.
   */
  public List<LatLng> getPath();
  
  /**
   * The starting point of this step.
   */
  public LatLng getStartPoint();
  
}

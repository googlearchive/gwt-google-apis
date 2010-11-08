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
package com.google.gwt.maps.client.overlay;

import com.google.gwt.maps.client.HasJso;
import com.google.gwt.maps.client.base.LatLng;

import java.util.List;

/**
 * 
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public interface HasPolylineOptions extends HasJso {

  /**
   * 
   */
  List<LatLng> getPath();

  String getStrokeColor();

  double getStrokeOpacity();

  int getStrokeWeight();

  /**
   * The ordered sequence of coordinates of the Polyline. This path may be
   * specified using either a simple array of LatLngs, or an MVCArray of
   * LatLngs. Note that if you pass a simple array, it will be converted to an
   * MVCArray Inserting or removing LatLngs in the MVCArray will automatically
   * update the polyline on the map.
   */
  void setPath(List<LatLng> path);

  /**
   * The stroke color in HTML hex style, ie. "#FFAA00".
   */
  void setStrokeColor(String strokeColor);

  /**
   * The stroke opacity between 0.0 and 1.0.
   */
  void setStrokeOpacity(double strokeOpacity);

  /**
   * The stroke width in pixels.
   */
  void setStrokeWeight(int strokeWeight);

}

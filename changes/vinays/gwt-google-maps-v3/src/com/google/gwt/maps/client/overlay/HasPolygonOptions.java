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
import com.google.gwt.maps.client.HasMap;
import com.google.gwt.maps.client.base.LatLng;

import java.util.List;

/**
 * Polygon options.
 * 
 */
public interface HasPolygonOptions extends HasJso {

  public String getFillColor();

  public double getFillOpacity();

  public HasMap getMap();

  public List<List<LatLng>> getPaths();

  public String getStrokeColor();

  public double getStrokeOpacity();

  public int getStrokeWeight();

  public int getZIndex();

  /**
   * The fill color in HTML hex style, ie. "#00AAFF".
   */
  public void setFillColor(String fillColor);

  /**
   * The fill opacity between 0.0 and 1.0.
   */
  public void setFillOpacity(double fillOpacity);

  /**
   * Map on which to display Polygon.
   */
  public void setMap(HasMap map);

  /**
   * The ordered sequence of coordinates that designates a closed loop. Unlike
   * polylines, a polygon may consist of one or more paths. As a result, the
   * paths property may specify one or more arrays of LatLng coordinates. Simple
   * polygons may be defined using a single array of LatLngs.
   */
  public void setPaths(List<List<LatLng>> paths);

  /**
   * The stroke color in HTML hex style, ie. "#FFAA00".
   */
  public void setStrokeColor(String strokeColor);

  /**
   * The stroke opacity between 0.0 and 1.0
   */
  public void setStrokeOpacity(double strokeOpacity);

  /**
   * The stroke width in pixels.
   */
  public void setStrokeWeight(int strokeWeight);

  /**
   * The zIndex compared to other polys.
   */
  public void setZIndex(int zIndex);

}

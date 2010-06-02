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
package com.google.gwt.maps.client.overlay;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.base.HasLatLng;

/**
 * 
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public interface HasCircleOptions {

  public JavaScriptObject getJso();

  public HasLatLng getCenter();

  /**
   * The center.
   */
  public void setCenter(HasLatLng center);

  public String getFillColor();

  /**
   * The fill color in HTML hex style, ie. "#00AAFF".
   */
  public void setFillColor(String fillColor);

  public double getFillOpacity();

  /**
   * The fill opacity between 0.0 and 1.0.
   */
  public void setFillOpacity(double fillOpacity);

  public double getRadius();

  /**
   * The radius in meters on the Earth's surface
   */
  public void setRadius(double radius);

  public String getStrokeColor();

  /**
   * The stroke color in HTML hex style, ie. "#FFAA00"
   */
  public void setStrokeColor(String strokeColor);

  public double getStrokeOpacity();

  /**
   * The stroke opacity between 0.0 and 1.0.
   */
  public void setStrokeOpacity(double strokeOpacity);

  public int getStrokeWeight();

  /**
   * The stroke width in pixels.
   */
  public void setStrokeWeight(int strokeWeight);
  
}

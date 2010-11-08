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
import com.google.gwt.maps.client.HasMap;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.mvc.HasMVCObject;

/**
 * A circle on the Earth's surface; also known as a "spherical cap". This class
 * extends MVCObject.
 * 
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public interface HasCircle extends HasMVCObject {
  
  public JavaScriptObject getJso();

  /**
   * Returns the center of this circle.
   */
  public LatLng getCenter();
  
  /**
   * Returns the map on which this circle is displayed.
   */
  public HasMap getMap();
  
  /**
   * Returns the radius of this circle (in meters).
   */
  public double getRadius();
  
  /**
   * Sets the center of this circle.
   */
  public void setCenter(LatLng center);
  
  /**
   * Renders the circle on the specified map. If map is set to null, the circle
   * will be removed.
   */
  public void setMap(HasMap map);
  
  /**
   * Sets the radius of this circle (in meters).
   */
  public void setRadius(double radius);
  
}

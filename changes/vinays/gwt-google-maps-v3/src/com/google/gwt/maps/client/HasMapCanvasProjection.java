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
package com.google.gwt.maps.client;

import com.google.gwt.maps.client.base.HasPoint;
import com.google.gwt.maps.client.base.LatLng;

/**
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public interface HasMapCanvasProjection extends HasJso {

  /**
   * Computes the geographical coordinates from pixel coordinates in the map's container.
   */
  public LatLng fromContainerPixelToLatLng(HasPoint pixel);

  /**
   * Computes the geographical coordinates from pixel coordinates in the div
   * that holds the draggable map.
   */
  public LatLng fromDivPixelToLatLng(HasPoint pixel);

  /**
   * Computes the pixel coordinates of the given geographical location in the
   * DOM element the map's outer container.
   */
  public HasPoint fromLatLngToContainerPixel(LatLng latLng);

  /**
   * Computes the pixel coordinates of the given geographical location in the
   * DOM element that holds the draggable map.
   */
  public HasPoint fromLatLngToDivPixel(LatLng latLng);
  
  /**
   * The width of the world in pixels in the current zoom level.
   */
  public int getWorldWidth();
  
}

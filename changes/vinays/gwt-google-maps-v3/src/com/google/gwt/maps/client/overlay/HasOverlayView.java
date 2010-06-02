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

import com.google.gwt.maps.client.HasJso;
import com.google.gwt.maps.client.HasMap;
import com.google.gwt.maps.client.HasMapCanvasProjection;
import com.google.gwt.maps.client.HasMapPanes;
import com.google.gwt.maps.client.mvc.HasMVCObject;

/**
 * Interface to display custom types of overlay objects on the map.
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public interface HasOverlayView extends HasMVCObject {

  /**
   * Implement this method to draw or update the overlay. This method is called
   * after onAdd() and when the position from projection.fromLatLngToPixel()
   * would return a new value for a given LatLng. This can happen on change of
   * zoom, center, or map type. It is not necessarily called on drag or resize.
   */
  void draw();
  
  /**
   * 
   */
  HasMap getMap();

  /**
   * Returns the panes in which this OverlayView can be rendered. Only available
   * after draw has been called.
   */
  HasMapPanes getPanes();

  /**
   * Returns the MapCanvasProjection object associated with this OverlayView.
   * Only available after draw has been called.
   */
  HasMapCanvasProjection getProjection();

  /**
   * Implement this method to initialize the overlay DOM elements. This method
   * is called once after setMap() is called with a valid map. At this point,
   * panes and projection will have been initialized.
   */
  void onAdd();

  /**
   * Implement this method to remove your elements from the DOM. This method is
   * called once following a call to setMap(null).
   */
  void onRemove();
  
  /**
   * Adds the overlay to the map.
   */
  void setMap(HasMap map);
  
}

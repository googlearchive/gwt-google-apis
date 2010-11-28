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
import com.google.gwt.maps.client.Map;
import com.google.gwt.maps.client.base.LatLng;

/**
 * 
 *
 */
public interface HasMarkerOptions extends HasJso {

  boolean isClickable();

  /**
   * If true, the marker can be clicked.
   */
  void setClickable(boolean clickable);

  String getCursor();

  /**
   * Mouse cursor to show on hover.
   */
  void setCursor(String cursor);

  boolean isDraggable();

  /**
   * If true, the marker can be dragged.
   */
  void setDraggable(boolean draggable);

  boolean isFlat();

  /**
   * If true, the marker shadow will not be displayed.
   */
  void setFlat(boolean flat);
  
  HasMarkerImage getIcon();
  
  /**
   * Icon for the foreground.
   */
  void setIcon(HasMarkerImage image);
  
  Map getMap();

  /**
   * Map on which to display Marker.
   */
  void setMap(Map map);

  LatLng getPosition();

  /**
   * Marker position. Required.
   */
  void setPosition(LatLng position);

  String getTitle();

  /**
   * Rollover text.
   */
  void setTitle(String title);

  boolean isVisible();

  /**
   * If true, the marker is visible.
   */
  void setVisible(boolean visible);

  int getZIndex();

  /**
   * All Markers are displayed on the map in order of their zIndex, with higher
   * values displaying in front of Markers with lower values. By default,
   * Markers are displayed according to their latitude, with Markers of lower
   * latitudes appearing in front of Markers at higher latitudes.
   */
  void setZIndex(int zIndex);

}

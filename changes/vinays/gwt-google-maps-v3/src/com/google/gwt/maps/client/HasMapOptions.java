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
package com.google.gwt.maps.client;

import com.google.gwt.maps.client.base.LatLng;

/**
 * Getters and setters for MapOptions.
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public interface HasMapOptions extends HasJso {

  String getBackgroundColor();

  /**
   * Color used for the background of the Map div. This color will be visible
   * when tiles have not yet loaded as the user pans. This option can only be
   * set when the map is initialized.
   */
  void setBackgroundColor(String backgroundColor);

  LatLng getCenter();

  /**
   * The initial Map center. Required.
   */
  void setCenter(LatLng center);

  boolean isDisableDefaultUI();

  /**
   * Enables/disables all default UI. May be overridden individually.
   */
  void setDisableDefaultUI(boolean disableDefaultUI);

  boolean isDisableDoubleClickZoom();

  /**
   * Enables/disables zoom and center on double click. Enabled by default.
   */
  void setDisableDoubleClickZoom(boolean disableDoubleClickZoom);

  boolean isDraggable();

  /**
   * If false, prevents the map from being dragged. Dragging is enabled by default.
   */
  void setDraggable(boolean draggable);

  String getDraggableCursor();

  /**
   * The name or url of the cursor to display on a draggable object.
   */
  void setDraggableCursor(String draggableCursor);

  String getDraggingCursor();

  /**
   * The name or url of the cursor to display when an object is dragging.
   */
  void setDraggingCursor(String draggingCursor);

  boolean isKeyboardShortcuts();

  /**
   * If false, prevents the map from being controlled by the keyboard. Keyboard
   * shortcuts are enabled by default.
   */
  void setKeyboardShortcuts(boolean keyboardShortcuts);

  boolean isMapTypeControl();

  /**
   * The initial enabled/disabled state of the Map type control.
   */
  void setMapTypeControl(boolean mapTypeControl);

  HasMapTypeControlOptions getMapTypeControlOptions();

  /**
   * The initial display options for the Map type control.
   */
  void setMapTypeControlOptions(HasMapTypeControlOptions mapTypeControlOptions);

  String getMapTypeId();

  /**
   * The initial Map mapTypeId. Required.
   */
  void setMapTypeId(String mapTypeId);

  boolean isNavigationControl();

  /**
   * The initial enabled/disabled state of the navigation control.
   */
  void setNavigationControl(boolean navigationControl);

  HasNavigationControlOptions getNavigationControlOptions();

  /**
   * The initial display options for the navigation control.
   */
  void setNavigationControlOptions(HasNavigationControlOptions navigationControlOptions);

  boolean isNoClear();

  /**
   * If true, do not clear the contents of the Map div.
   */
  void setNoClear(boolean noClear);

  boolean isScaleControl();

  /**
   * The initial enabled/disabled state of the scale control.
   */
  void setScaleControl(boolean scaleControl);

  HasScaleControlOptions getScaleControlOptions();

  /**
   * The initial display options for the scale control.
   */
  void setScaleControlOptions(HasScaleControlOptions scaleControlOptions);

  boolean isScrollwheel();

  /**
   * If false, disables scrollwheel zooming on the map. The scrollwheel is enabled by default.
   */
  void setScrollwheel(boolean scrollwheel);

  int getZoom();

  /**
   * The initial Map zoom level. Required.
   */
  void setZoom(int zoom);

}

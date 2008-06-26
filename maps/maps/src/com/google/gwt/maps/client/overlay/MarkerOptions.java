/*
 * Copyright 2007 Google Inc.
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
package com.google.gwt.maps.client.overlay;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.impl.MarkerOptionsImpl;

/**
 * Instances of this class are used in the {@link Marker} constructor
 * <code>options</code> argument.
 */
public final class MarkerOptions {

  private final JavaScriptObject jsoPeer;

  public MarkerOptions() {
    jsoPeer = MarkerOptionsImpl.impl.construct();
  }

  public MarkerOptions(Icon icon) {
    this();
    setIcon(icon);
  }

  /**
   * When finishing dragging, this number is used to define the acceleration
   * rate of the marker during the bounce down to earth. The default value for
   * this option is 1.
   * 
   * @param bounceGravity number used to define the acceleration rate of the
   *          marker during the bounce.
   */
  public void setBounceGravity(double bounceGravity) {
    MarkerOptionsImpl.impl.setBounceGravity(jsoPeer, bounceGravity);
  }

  /**
   * Toggles whether or not the marker should bounce up and down after it
   * finishes dragging. The default value for this option is <code>false</code>
   * 
   * @param bouncy <code>true</code> to set the marker to be bouncy.
   */
  public void setBouncy(boolean bouncy) {
    MarkerOptionsImpl.impl.setBouncy(jsoPeer, bouncy);
  }

  /**
   * Toggles whether or not the marker is clickable. Markers that are not
   * clickable or draggable are inert, consume less resources and do not respond
   * to any events. The default value for this option is <code>true</code>,
   * i.e. if the option is not specified, the marker will be clickable.
   * 
   * @param clickable whether or not the marker is clickable.
   */
  public void setClickable(boolean clickable) {
    MarkerOptionsImpl.impl.setClickable(jsoPeer, clickable);
  }

  /**
   * When dragging markers normally, the marker floats up and away from the
   * cursor. Setting this value to true keeps the marker underneath the cursor,
   * and moves the cross downwards instead. The default value for this option is
   * false.
   * 
   * @param dragCrossMove set to <code>true</code> to keep the marker
   *          underneath the cursor when dragged.
   */
  public void setDragCrossMove(boolean dragCrossMove) {
    MarkerOptionsImpl.impl.setDragCrossMove(jsoPeer, dragCrossMove);
  }

  /**
   * Toggles whether or not the marker will be draggable by users. Markers set
   * up to be dragged require more resources to set up than markers that are
   * clickable. Any marker that is draggable is also clickable, bouncy and
   * auto-pan enabled by default. The default value for this option is
   * <code>false</code.
   * 
   * @param draggable whether or not the marker will be draggable by users.
   */
  public void setDraggable(boolean draggable) {
    MarkerOptionsImpl.impl.setDraggable(jsoPeer, draggable);
  }

  /**
   * Chooses the Icon for this class. If not specified,
   * {@link Icon#DEFAULT_ICON} is used.
   * 
   * @param icon sets the icon for this class.
   */
  public void setIcon(Icon icon) {
    MarkerOptionsImpl.impl.setIcon(jsoPeer, icon);
  }

  /**
   * This string will appear as tooltip on the marker, i.e. it will work just as
   * the title attribute on HTML elements.
   * 
   * @param title a string to set as the tooltip on the marker.
   */
  public void setTitle(String title) {
    MarkerOptionsImpl.impl.setTitle(jsoPeer, title);
  }
}

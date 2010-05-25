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

/**
 * Instances of this class are used in the {@link Marker} constructor
 * <code>options</code> argument.
 */
public class MarkerOptions extends JavaScriptObject {

  /**
   * Interface defining function changing default z-Index order of the markers
   */
  public static interface ZIndexProcess {
    public double computeZIndex(Marker marker);
  }

  public static MarkerOptions newInstance() {
    return (MarkerOptions) createObject();
  }

  public static MarkerOptions newInstance(Icon icon) {
    MarkerOptions options = newInstance();
    options.setIcon(icon);
    return options;
  }

  /**
   * JavaScript overlays require a protected constructor.
   */
  protected MarkerOptions() {
  }

  /**
   * Auto-pan the map as you drag the marker near the edge. If the marker is
   * draggable the default value for this option is <true>true</true>.
   * 
   * @param autoPan <code>true</code> to turn on auto pan.
   */
  public final native void setAutoPan(boolean autoPan) /*-{
    this.autoPan = autoPan;
  }-*/;

  /**
   * When finishing dragging, this number is used to define the acceleration
   * rate of the marker during the bounce down to earth. The default value for
   * this option is 1.
   * 
   * @param bounceGravity number used to define the acceleration rate of the
   *          marker during the bounce.
   */
  public final native void setBounceGravity(double bounceGravity) /*-{
    this.bounceGravity = bounceGravity;
  }-*/;

  /**
   * Toggles whether or not the marker should bounce up and down after it
   * finishes dragging. The default value for this option is <code>false</code>
   * 
   * @param bouncy <code>true</code> to set the marker to be bouncy.
   */
  public final native void setBouncy(boolean bouncy) /*-{
    this.bouncy = bouncy;
  }-*/;

  /**
   * Toggles whether or not the marker is clickable. Markers that are not
   * clickable or draggable are inert, consume less resources and do not respond
   * to any events. The default value for this option is <code>true</code>, i.e.
   * if the option is not specified, the marker will be clickable.
   * 
   * @param clickable whether or not the marker is clickable.
   */
  public final native void setClickable(boolean clickable) /*-{
    this.clickable = clickable;
  }-*/;

  /**
   * When dragging markers normally, the marker floats up and away from the
   * cursor. Setting this value to true keeps the marker underneath the cursor,
   * and moves the cross downwards instead. The default value for this option is
   * false.
   * 
   * @param dragCrossMove set to <code>true</code> to keep the marker underneath
   *          the cursor when dragged.
   */
  public final native void setDragCrossMove(boolean dragCrossMove) /*-{
    this.dragCrossMove = dragCrossMove;
  }-*/;

  /**
   * Toggles whether or not the marker will be draggable by users. Markers set
   * up to be dragged require more resources to set up than markers that are
   * clickable. Any marker that is draggable is also clickable, bouncy and
   * auto-pan enabled by default. The default value for this option is
   * <code>false</code.
   * 
   * @param draggable whether or not the marker will be draggable by users.
   */
  public final native void setDraggable(boolean draggable) /*-{
    this.draggable = draggable;
  }-*/;

  /**
   * Chooses the Icon for this class. If not specified,
   * {@link Icon#DEFAULT_ICON} is used.
   * 
   * @param icon sets the icon for this class.
   */
  public final native void setIcon(Icon icon) /*-{
    this.icon = icon;
  }-*/;

  /**
   * This string will appear as tooltip on the marker, i.e. it will work just as
   * the title attribute on HTML elements.
   * 
   * @param title a string to set as the tooltip on the marker.
   */
  public final native void setTitle(String title) /*-{
    this.title = title;
  }-*/;

  /**
   * This function is used for changing the z-Index order of the markers when
   * they are overlaid on the map and is also called when their infowindow is
   * opened. The default order is that the more southerly markers are placed
   * higher than more northerly markers. This function is passed in the Marker
   * object and returns a number indicating the new z-index.
   * 
   * @param process function used for changing the z-Index order of the markers
   */
  public final native void setZIndexProcess(ZIndexProcess process) /*-{
    this.zIndexProcess = function (jso) {
      var marker;
      if (jso.__gwtPeer == null) {
        marker = @com.google.gwt.maps.client.overlay.Marker::createPeer(Lcom/google/gwt/core/client/JavaScriptObject;)(jso);
      } else {
        marker = jso.__gwtPeer;
      }
      return process.@com.google.gwt.maps.client.overlay.MarkerOptions.ZIndexProcess::computeZIndex(Lcom/google/gwt/maps/client/overlay/Marker;)(marker);
    }
  }-*/;
}

/*
 * Copyright 2010 Google Inc.
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
package com.google.gwt.maps.client.streetview;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * A class representing the structure of a camera point of view used by street
 * view.
 *
 * @see "http://code.google.com/apis/maps/documentation/reference.html#GPov"
 */
public class Pov extends JavaScriptObject {

  /**
   * Returns a new instance of {@link Pov} class.
   *
   * By default all values are equal to 0.
   */
  public static Pov newInstance() {
    return JavaScriptObject.createObject().cast();
  }

  /**
   * Protected constructor required for JS overlay.
   */
  protected Pov() {
  }

  /**
   * Sets the camera yaw in degrees relative to true north. True north is 0
   * degrees, east is 90 degrees, south is 180 degrees, west is 270 degrees.
   *
   * @param yaw the camera yaw in degrees relative to true north.
   * @return this {@link Pov} object, for convenience when using the Builder
   *         pattern.
   */
  public final native Pov setYaw(double yaw) /*-{
    this.yaw = yaw;
    return this;
  }-*/;

  /**
   * Sets the camera pitch in degrees, relative to the street view vehicle.
   * Ranges from 90 degrees (directly upwards) to -90 degrees (directly
   * downwards).
   *
   * @param pitch the camera pitch in degrees, relative to the street view
   *          vehicle.
   * @return this {@link Pov} object, for convenience when using the Builder
   *         pattern.
   */
  public final native Pov setPitch(double pitch) /*-{
    this.pitch = pitch;
    return this;
  }-*/;

  /**
   * Sets the zoom level. Fully zoomed-out is level 0, zooming in increases the
   * zoom level.
   *
   * @param zoom the zoom level.
   * @return this {@link Pov} object, for convenience when using the Builder
   *         pattern.
   */
  public final native Pov setZoom(double zoom) /*-{
    this.zoom = zoom;
    return this;
  }-*/;

  /**
   * Returns the camera yaw in degrees relative to true north. True north is 0
   * degrees, east is 90 degrees, south is 180 degrees, west is 270 degrees.
   *
   * @return the camera yaw in degrees relative to true north.
   */
  public final native double getYaw() /*-{
    return (this.yaw == null) ? 0 : this.yaw;
  }-*/;

  /**
   * Returns the camera pitch in degrees, relative to the street view vehicle.
   * Ranges from 90 degrees (directly upwards) to -90 degrees (directly
   * downwards).
   *
   * @return the camera pitch in degrees, relative to the street view vehicle.
   */
  public final native double getPitch() /*-{
    return (this.pitch == null) ? 0 : this.pitch;
  }-*/;

  /**
   * Returns the zoom level. Fully zoomed-out is level 0, zooming in increases
   * the zoom level.
   *
   * @return the zoom level.
   */
  public final native double getZoom() /*-{
    return (this.zoom == null) ? 0 : this.zoom;
  }-*/;

}

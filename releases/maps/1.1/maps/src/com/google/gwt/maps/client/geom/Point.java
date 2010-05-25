/*
 * Copyright 2008 Google Inc.
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
package com.google.gwt.maps.client.geom;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * A Point represents a point on the map by its pixel coordinates. It doesn't
 * represent a point on the earth by its geographical coordinates.
 * Geographical coordinates are now represented by {@link LatLng}.
 * 
 * In the Google Maps coordinate system, the x coordinate increases to the
 * right, and the y coordinate increases downwards, though you may use Point
 * coordinates however you wish.
 * 
 */
public class Point extends JavaScriptObject {

  public static native Point newInstance(int x, int y) /*-{
    return new $wnd.GPoint(x,y);
  }-*/;
  
  protected Point() {
  }

  /**
   * Returns the X coordinate.
   * 
   * @return the X coordinate.
   */
  public final native int getX() /*-{
    return this.x;
  }-*/;

  /**
   * Returns the Y coordinate.
   * 
   * @return the Y coordinate.
   */
  public final native int getY() /*-{
    return this.y;
  }-*/;
}

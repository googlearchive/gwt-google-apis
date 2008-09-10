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
 * Specifies the position of a Tile in the map. Used as an argument to
 * {@link Projection#tileCheckRange(TileIndex,int,int)}
 * 
 */
public class TileIndex extends JavaScriptObject {

  /**
   * Construct a new TileIndex instance.  This is a representation of the Maps API GPoint object used for {@link com.google.gwt.maps.client.TileLayer} operations.
   * @param x the x coordinate
   * @param y the y coordinate
   * @return a new instance of a TileIndex option 
   */
  public static final native Point newInstance(int x, int y) /*-{
    return new $wnd.Point(x,y);  
  }-*/;
  
  /*
   * Design Note (zundel): The Maps API re-uses the Point object. I've modeled
   * it as a different class in order to allow Point to be immutable and to
   * disallow creating a TileIndex by users.
   */
  protected TileIndex() {
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

  /**
   * Sets the X coordinate.
   * 
   * @param x the X coordinate.
   */
  public final native void setX(int x) /*-{
    this.x = x;
  }-*/;
  
  /**
   * Sets the Y coordinate.
   * 
   * @param y the Y coordinate.
   */
  public final native void setY(int y) /*-{
    this.y = y;
  }-*/;

}

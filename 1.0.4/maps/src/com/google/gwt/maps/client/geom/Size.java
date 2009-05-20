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
 * A Size represents the size in pixels of a rectangular area of the map. The
 * size object has two parameters, width and height. Width is a difference in
 * the x-coordinate; height is a difference in the y-coordinate, of points.
 */
public class Size extends JavaScriptObject {

  /**
   * Construct a new Size object.
   * 
   * @param width Width is a difference in the x-coordinate.
   * @param height height is a difference in the y-coordinate, of points.
   */
  public static native Size newInstance(int width, int height) /*-{
    return new $wnd.GSize(width, height);
  }-*/;
  
  protected Size() {
    // Protected constructor required for a JS overlay
  }

  /**
   * Returns the height parameter.
   * 
   * @return the height parameter.
   */
  public final native int getHeight() /*-{
    return this.height;
  }-*/;

  /**
   * Returns the width parameter.
   * 
   * @return the width parameter.
   */
  public final native int getWidth() /*-{
    return this.width;
  }-*/;

}

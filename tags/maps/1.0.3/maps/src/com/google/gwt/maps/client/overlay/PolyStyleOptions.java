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
package com.google.gwt.maps.client.overlay;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Options to pass to the {@link Polyline} drawing routines.
 */
public class PolyStyleOptions extends JavaScriptObject {

  /**
   * Creates a new PolyStyleOptions object. 
   */
  public static PolyStyleOptions getInstance() {
    return (PolyStyleOptions) PolyStyleOptions.createObject();
  }
  
  /**
   * Create a new PolyStyleOptions object. 
   * 
   * @param weightInPixels the width of the line to create.
   */
  public static PolyStyleOptions getInstance(int weightInPixels) {
    PolyStyleOptions instance = getInstance();
    instance.setWeight(weightInPixels);
    return instance;
  }

  /**
   * Create a new PolyStyleOptions object. 
   * 
   * @param colorSpec color of the line. See {@link #setColor(String)} for the
   *          format of the string.
   */
  public static PolyStyleOptions newInstance(String colorSpec) {
    PolyStyleOptions instance = getInstance();
    instance.setColor(colorSpec);
    return instance;
  }

  /**
   * Create a PolyStyleOptions object. Used as a parameter to the
   * {@link Polyline} constructor.
   *
   * @param weightInPixels the width of the line to create.
   * @param colorSpec color of the line. See {@link #setColor(String)} for the
   *          format of the string.
   */
  public static PolyStyleOptions newInstance(String colorSpec, int weightInPixels, double opacity) {
    PolyStyleOptions instance = getInstance();
    instance.setColor(colorSpec);
    instance.setWeight(weightInPixels);
    instance.setOpacity(opacity);
    return instance;
  }

  protected PolyStyleOptions() {
    // Required for a JS overlay.
  }

  /**
   * Specifies a string that contains a hexadecimal numeric HTML style, i.e.
   * #RRGGBB.
   * 
   * @param colorSpec specifies a color in hex format.
   */
  public final native void setColor(String colorSpec) /*-{
    this.color = colorSpec;
  }-*/;

  /**
   * Specifies the opacity of the polyline.
   * 
   * @param opacity specifies the opacity of the polyline as a fractional value
   *          between 0 (transparent) and 1 (opaque).
   */
  public final native void setOpacity(double opacity) /*-{
    this.opacity = opacity;
  }-*/;

  /**
   * Specifies the width of the line in pixels.
   * 
   * @param weightInPixels specifies the width of the line in pixels.
   */
  public final native void setWeight(int weightInPixels) /*-{
    this.weight = weightInPixels;
  }-*/;

}

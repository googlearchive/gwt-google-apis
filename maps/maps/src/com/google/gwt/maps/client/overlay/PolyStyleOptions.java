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
import com.google.gwt.maps.client.impl.PolyStyleOptionsImpl;

/**
 * Options to pass to the {@link Polyline} drawing routines.
 */
public class PolyStyleOptions {

  private final JavaScriptObject jsoPeer;

  /**
   * Create an empty PolyStyleOptions object. Used as a parameter to the
   * {@link Polyline} constructor.
   */
  public PolyStyleOptions() {
    jsoPeer = PolyStyleOptionsImpl.impl.construct();
  }

  /**
   * Create a PolyStyleOptions object. Used as a parameter to the
   * {@link Polyline} constructor.
   * 
   * @param weightInPixels the width of the line to create.
   */
  public PolyStyleOptions(int weightInPixels) {
    this();
    setWeight(weightInPixels);
  }

  /**
   * Create a PolyStyleOptions object. Used as a parameter to the
   * {@link Polyline} constructor.
   * 
   * @param colorSpec color of the line. See {@link #setColor(String)} for the
   *          format of the string.
   */
  public PolyStyleOptions(String colorSpec) {
    this();
    setColor(colorSpec);
  }

  /**
   * Create a PolyStyleOptions object. Used as a parameter to the
   * {@link Polyline} constructor.
   *
   * @param weightInPixels the width of the line to create.
   * @param colorSpec color of the line. See {@link #setColor(String)} for the
   *          format of the string.
   */
  public PolyStyleOptions(String colorSpec, int weightInPixels, double opacity) {
    this();
    setColor(colorSpec);
    setWeight(weightInPixels);
    setOpacity(opacity);
  }

  /**
   * Specifies a string that contains a hexadecimal numeric HTML style, i.e.
   * #RRGGBB.
   * 
   * @param colorSpec specifies a color in hex format.
   */
  void setColor(String colorSpec) {
    PolyStyleOptionsImpl.impl.setColor(jsoPeer, colorSpec);
  }

  /**
   * Specifies the opacity of the polyline.
   * 
   * @param opacity specifies the opacity of the polyline as a fractional value
   *          between 0 (transparent) and 1 (opaque).
   */
  void setOpacity(double opacity) {
    PolyStyleOptionsImpl.impl.setOpacity(jsoPeer, opacity);
  }

  /**
   * Specifies the width of the line in pixels.
   * 
   * @param weightInPixels specifies the width of the line in pixels.
   */
  void setWeight(int weightInPixels) {
    PolyStyleOptionsImpl.impl.setWeight(jsoPeer, weightInPixels);
  }

}

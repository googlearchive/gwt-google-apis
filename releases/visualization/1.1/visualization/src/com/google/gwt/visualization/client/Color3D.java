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
package com.google.gwt.visualization.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.ajaxloader.client.Properties;

/**
 * Allows users to setup the 3D color scheme on <a href=
 * "http://code.google.com/apis/visualization/documentation/gallery/barchart.html"
 * > any 3D visualization</a>
 * 
 */
public class Color3D extends Properties {

  /**
   * Create an object to hold a color/shadow mapping for a chart element.
   */
  public static Color3D create() {
    return JavaScriptObject.createObject().<Color3D> cast();
  }

  /**
   * Create a color/shadow mapping for a chart element.
   * 
   * @param color color to use as the graph top color.
   * @param shadeColor color to use as the shadow color.
   */
  public static Color3D create(String color, String shadeColor) {
    return create().setFaceColor(color).setShadeColor(shadeColor);
  }

  protected Color3D() {
  }

  /**
   * Color to use on the face of the visualization.
   * 
   * @param color Color to use on the face of the visualization.
   */
  public final Color3D setFaceColor(String color) {
    set("color", color);
    return this;
  }

  /**
   * Color to use to shade the visualization.
   * 
   * @param shadeColor Color to use to shade the visualization.
   */
  public final Color3D setShadeColor(String shadeColor) {
    set("darker", shadeColor);
    return this;
  }

}

/*
 * Copyright 2011 Google Inc.
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

package com.google.gwt.visualization.client.visualizations.corechart;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Series specifications for Chart Options. The ComboChart is the primary user 
 * of series. Color-related draw options accept either the name of the color as
 * a string, or the detailed color specification.
 *
 * @see <a href=
 *      "http://code.google.com/apis/visualization/documentation/gallery/combochart.html#Configuration_Options"
 *      > Combo Configuration Options Reference</a>
 */
public class Series extends JavaScriptObject {
  /**
   * Available series types for combo charts. Used by various options, like 
   * ComboOptions.setSeriesType(). Note that bars are actually vertical bars 
   * (columns).
   */
  public enum Type {
    AREA,
    BARS,
    LINE,
  }

  public static Series create() {
    return JavaScriptObject.createObject().cast();
  }

  protected Series() {
  }

  /**
   * Sets the color of this particular series.
   *
   * @param color A valid HTML color string.
   */
  public final native void setColor(String color) /*-{
    this.color = color;
  }-*/;

  /**
   * Applicable for line and area series only.
   * @param width The width of the line, in pixels.
   */
  public final native void setLineWidth(int width) /*-{
    this.lineWidth = width;
  }-*/;

  /**
   * Applicable for line and area series only.
   * @param size The size of the circle on each data point, in pixels.
   */
  public final native void setPointSize(int size) /*-{
    this.pointSize = size;
  }-*/;

  public final void setType(Series.Type type) {
    setType(type.name().toLowerCase());
  }

  public final native void setType(String type) /*-{
    this.type = type;
  }-*/;
}

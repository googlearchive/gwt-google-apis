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

package com.google.gwt.visualization.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;

/**
 * Common options used by most charts from Google.
 * 
 */
public class CommonOptions extends AbstractDrawOptions {
  public static CommonOptions create() {
    return JavaScriptObject.createObject().cast();
  }

  protected CommonOptions() {
  }

  public final native void setBackgroundColor(Color color) /*-{
    this.backgroundColor = color;
  }-*/;

  public final native void setBackgroundColor(String color) /*-{
    this.backgroundColor = color;
  }-*/;

  public final native void setBorderColor(Color color) /*-{
    this.borderColor = color;
  }-*/;

  public final native void setBorderColor(String color) /*-{
    this.borderColor = color;
  }-*/;

  public final native void setColors(JsArrayString colors) /*-{
    this.colors = colors;
  }-*/;

  public final void setColors(String... colors) {
    setColors(createJsArray(colors));
  }

  public final native void setFocusBorderColor(Color color) /*-{
    this.focusBorderColor = color;
  }-*/;

  public final native void setFocusBorderColor(String color) /*-{
    this.focusBorderColor = color;
  }-*/;

  public final native void setHeight(int height) /*-{
    this.height = height;
  }-*/;

  public final void setLegend(LegendPosition position) {
    setLegend(position.toString());
  }

  public final native void setLegendBackgroundColor(Color color) /*-{
    this.legendBackgroundColor = color;
  }-*/;

  public final native void setLegendBackgroundColor(String color) /*-{
    this.legendBackgroundColor = color;
  }-*/;

  public final native void setLegendTextColor(Color color) /*-{
    this.legendTextColor = color;
  }-*/;

  public final native void setLegendTextColor(String color) /*-{
    this.legendTextColor = color;
  }-*/;

  public final void setSize(int width, int height) {
    setWidth(width);
    setHeight(height);
  }

  public final native void setTitle(String title) /*-{
    this.title = title;
  }-*/;

  public final native void setTitleColor(Color color) /*-{
    this.titleColor = color;
  }-*/;

  public final native void setTitleColor(String color) /*-{
    this.titleColor = color;
  }-*/;

  public final native void setWidth(int width) /*-{
    this.width = width;
  }-*/;

  private native void setLegend(String legend) /*-{
    this.legend = legend;
  }-*/;
}
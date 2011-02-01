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

/**
 * Common options used by most charts from Google that feature an axis.
 *
 */
public class CommonChartOptions extends CommonOptions {
  public static CommonChartOptions create() {
    return JavaScriptObject.createObject().cast();
  }

  protected CommonChartOptions() {
  }

  public final native void setAxisBackgroundColor(Color color) /*-{
    this.axisBackgroundColor = color;
  }-*/;

  public final native void setAxisBackgroundColor(String color) /*-{
    this.axisBackgroundColor = color;
  }-*/;

  public final native void setAxisColor(Color color) /*-{
    this.axisColor = color;
  }-*/;

  public final native void setAxisColor(String color) /*-{
    this.axisColor = color;
  }-*/;

  public final native void setAxisFontSize(double size) /*-{
    this.axisFontSize = size;
  }-*/;

  public final native void setLogScale(boolean logScale) /*-{
    this.logScale = logScale;
  }-*/;

  public final native void setMax(double max) /*-{
    this.max = max;
  }-*/;

  public final native void setMin(double min) /*-{
    this.min = min;
  }-*/;

  public final native void setReverseAxis(boolean reverseAxis) /*-{
    this.reverseAxis = reverseAxis;
  }-*/;

  public final native void setShowCategories(boolean showCategories) /*-{
    this.showCategories = showCategories;
  }-*/;

  public final native void setTitleX(String title) /*-{
    this.titleX = title;
  }-*/;

  public final native void setTitleY(String title) /*-{
    this.titleY = title;
  }-*/;
}
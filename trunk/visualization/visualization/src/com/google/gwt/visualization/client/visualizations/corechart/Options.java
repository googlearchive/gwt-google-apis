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

package com.google.gwt.visualization.client.visualizations.corechart;

import com.google.gwt.ajaxloader.client.ArrayHelper;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.visualization.client.AbstractDrawOptions;
import com.google.gwt.visualization.client.ChartArea;
import com.google.gwt.visualization.client.Color;
import com.google.gwt.visualization.client.LegendPosition;
import com.google.gwt.visualization.client.visualizations.corechart.CoreChart.Type;

/**
 * Common options used by core charts.
 *
 * @see <a href=
 *      "http://code.google.com/apis/visualization/documentation/gallery/areachart.html#Configuration_Options"
 *      > Configuration Options Reference</a>
 */
public class Options extends AbstractDrawOptions {
  public static Options create() {
    return JavaScriptObject.createObject().cast();
  }

  protected Options() {
  }

  public final native void setAxisTitlesPosition(String position) /*-{
    this.axisTitlesPosition = position;
  }-*/;

  public final native void setBackgroundColor(String color) /*-{
    this.backgroundColor = color;
  }-*/;

  public final native void setBackgroundColor(Color color) /*-{
    this.backgroundColor = color;
  }-*/;

  public final native void setChartArea(ChartArea chartArea) /*-{
    this.chartArea = chartArea;
  }-*/;

  public final native void setColors(JsArrayString colors) /*-{
    this.colors = colors;
  }-*/;

  public final void setColors(String... colors) {
    setColors(ArrayHelper.toJsArrayString(colors));
  }

  public final native void setCurveType(String type) /*-{
    this.curveType = type;
  }-*/;

  public final native void setFontName(String name) /*-{
    this.fontName = name;
  }-*/;

  public final native void setFontSize(double fontSize) /*-{
    this.fontSize = fontSize;
  }-*/;

  public final native void setGridlineColor(String color) /*-{
    this.gridlineColor = color;
  }-*/;

  public final native void setHAxisOptions(AxisOptions options) /*-{
    this.hAxis = options;
  }-*/;

  public final native void setHeight(int height) /*-{
    this.height = height;
  }-*/;

  public final native void setInterpolateNulls(boolean interpolate) /*-{
    this.interpolateNulls = interpolate;
  }-*/;

  public final native void setIsStacked(boolean isStacked) /*-{
    this.isStacked = isStacked;
  }-*/;

  public final void setLegend(LegendPosition position) {
    setLegend(position.toString().toLowerCase());
  }

  public final native void setLegend(String legend) /*-{
    this.legend = legend;
  }-*/;

  public final native void setLegendTextStyle(TextStyle style) /*-{
    this.legendTextStyle = style;
  }-*/;

  public final native void setLineWidth(int width) /*-{
    this.lineWidth = width;
  }-*/;

  public final native void setPointSize(int size) /*-{
    this.pointSize = size;
  }-*/;

  public final native void setReverseCategories(boolean reverseCategories) /*-{
    this.reverseCategories = reverseCategories;
  }-*/;

  public final native void setTitle(String title) /*-{
    this.title = title;
  }-*/;

  public final native void setTitleTextStyle(TextStyle style) /*-{
    this.titleTextStyle = style;
  }-*/;

  public final native void setTooltipTextStyle(TextStyle style) /*-{
    this.tooltipTextStyle = style;
  }-*/;

  public final native void setType(String type) /*-{
    this.type = type;
  }-*/;

  public final void setType(Type type) {
    setType(type.name().toLowerCase());
  }

  public final native void setVAxisOptions(AxisOptions options) /*-{
    this.vAxis = options;
  }-*/;

  public final native void setWidth(int width) /*-{
    this.width = width;
  }-*/;
}

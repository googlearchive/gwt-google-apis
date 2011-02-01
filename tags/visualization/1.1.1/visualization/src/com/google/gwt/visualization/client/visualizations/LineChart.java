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
package com.google.gwt.visualization.client.visualizations;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.Element;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.CommonChartOptions;
import com.google.gwt.visualization.client.Selectable;
import com.google.gwt.visualization.client.Selection;
import com.google.gwt.visualization.client.events.Handler;
import com.google.gwt.visualization.client.events.OnMouseOutHandler;
import com.google.gwt.visualization.client.events.OnMouseOverHandler;
import com.google.gwt.visualization.client.events.ReadyHandler;
import com.google.gwt.visualization.client.events.SelectHandler;

/**
 * Visualization that plots the data points on a coordinate plane and draws a
 * line between the points.
 *
 *
 * @see <a href=
 *      "http://code.google.com/apis/visualization/documentation/gallery/linechart.html"
 *      > Line Chart Visualization Reference</a>
 *
 * @deprecated this class is outdated. Instead, please use the class
 *             com.google.gwt.visualization.client.visualizations.corechart.LineChart
 */
@Deprecated
public class LineChart extends Visualization<LineChart.Options> implements
    Selectable {
  /**
   * Options for drawing the chart.
   *
   */
  public static class Options extends CommonChartOptions {
    public static Options create() {
      return JavaScriptObject.createObject().cast();
    }

    protected Options() {
    }

    public final native void setLineSize(int size) /*-{
      this.lineSize = size;
    }-*/;

    public final native void setPointSize(int size) /*-{
      this.pointSize = size;
    }-*/;

    public final native void setSmoothLine(boolean smooth) /*-{
      this.smoothLine = smooth;
    }-*/;
  }

  public static final String PACKAGE = "linechart";

  public LineChart() {
    super();
  }

  public LineChart(AbstractDataTable data, Options options) {
    super(data, options);
  }

  public final void addOnMouseOutHandler(OnMouseOutHandler handler) {
    Handler.addHandler(this, "onmouseout", handler);
  }

  public final void addOnMouseOverHandler(OnMouseOverHandler handler) {
    Handler.addHandler(this, "onmouseover", handler);
  }

  public final void addReadyHandler(ReadyHandler handler) {
    Handler.addHandler(this, "ready", handler);
  }

  public final void addSelectHandler(SelectHandler handler) {
    Selection.addSelectHandler(this, handler);
  }

  public final JsArray<Selection> getSelections() {
    return Selection.getSelections(this);
  }

  public final void setSelections(JsArray<Selection> sel) {
    Selection.setSelections(this, sel);
  }

  @Override
  protected native JavaScriptObject createJso(Element parent) /*-{
    return new $wnd.google.visualization.LineChart(parent);
  }-*/;
}

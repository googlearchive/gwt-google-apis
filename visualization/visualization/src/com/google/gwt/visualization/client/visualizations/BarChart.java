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
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.CommonChartOptions;
import com.google.gwt.visualization.client.Selectable;
import com.google.gwt.visualization.client.Selection;
import com.google.gwt.visualization.client.SelectionHelper;
import com.google.gwt.visualization.client.VisualizationWidget;
import com.google.gwt.visualization.client.events.SelectHandler;

/**
 * 
 * Visualization with horizontal bars showing the values.
 * 
 * @see <a href=
 *      "http://code.google.com/apis/visualization/documentation/gallery/barchart.html"
 *      > Bar Chart Visualization Reference</a>
 */
public class BarChart extends Visualization<BarChart.Options> implements
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

    public final native void set3D(boolean enable3D) /*-{
      this.is3D = enable3D;
    }-*/;

    public final native void setStacked(boolean stacked) /*-{
      this.isStacked = stacked;
    }-*/;
  }

  public static final String PACKAGE = "barchart";

  public static native BarChart create(Element parent) /*-{
   return new $wnd.google.visualization.BarChart(parent);
  }-*/;

  public static VisualizationWidget<BarChart, Options> createWidget() {
    Element div = DOM.createDiv();
    BarChart viz = create(div);
    return new VisualizationWidget<BarChart, Options>(div, viz);
  }

  public static VisualizationWidget<BarChart, Options> createWidget(
      AbstractDataTable data, Options options) {
    Element div = DOM.createDiv();
    BarChart viz = create(div);
    return new VisualizationWidget<BarChart, Options>(div, viz, data, options);
  }

  protected BarChart() {
  }

  public final void addSelectHandler(SelectHandler handler) {
    SelectionHelper.addSelectHandler(this, handler);
  }

  public final Selection getSelection() {
    return SelectionHelper.getSelection(this);
  }

  public final void setSelection(Selection sel) {
    SelectionHelper.setSelection(this, sel);
  }
}

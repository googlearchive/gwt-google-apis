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
package com.google.gwt.visualization.client.visualizations;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.CommonChartOptions;

/**
 * A bar chart that is rendered as an image using the Google Charts API.
 *
 *
 * @see <a href=
 *      "http://code.google.com/apis/visualization/documentation/gallery/imagebarchart.html"
 *      > Image Bar Chart Visualization Reference</a>
 */
public class ImageBarChart extends Visualization<ImageBarChart.Options> {

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

    /**
     * Controls whether multiple data columns will be displayed as stacked (as
     * opposed to grouped) bars. Default value is <code>true</code>.
     */
    public final native void setIsStacked(boolean isStacked) /*-{
      this.isStacked = isStacked;
    }-*/;

    /**
     * Controls whether the bars will be vertical. Default value is
     * <code>false</code>.
     */
    public final native void setIsVertical(boolean isVertical) /*-{
      this.isVertical = isVertical;
    }-*/;

    /**
     * If set to <code>false</code>, removes the labels of the categories (the X
     * axis labels).
     */
    public final native void setShowCategoryLabels(boolean showCategoryLabels) /*-{
      this.showCategoryLabels = showCategoryLabels;
    }-*/;

    /**
     * If set to <code>false</code>, removes the labels of the values (the Y
     * axis labels).
     */
    public final native void setShowValueLabels(boolean showValueLabels) /*-{
      this.showValueLabels = showValueLabels;
    }-*/;

    /**
     * The interval at which to show value axis labels. For example, if min is
     * 0, max is 100, and valueLabelsInterval is 20, the chart will show axis
     * labels at (0, 20, 40, 60, 80 100).
     */
    public final native void setValueLabelsInterval(double valueLabelsInterval) /*-{
      this.valueLabelsInterval = valueLabelsInterval;
    }-*/;
  }

  public static final String PACKAGE = "imagebarchart";

  public ImageBarChart() {
    super();
  }

  public ImageBarChart(AbstractDataTable data, Options options) {
    super(data, options);
  }

  @Override
  protected native JavaScriptObject createJso(Element parent) /*-{
    return new $wnd.google.visualization.ImageBarChart(parent);
  }-*/;
}

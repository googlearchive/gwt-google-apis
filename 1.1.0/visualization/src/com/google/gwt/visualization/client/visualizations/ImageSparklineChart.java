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
 * A sparkline chart that is rendered as an image using the Google Charts API.
 *
 * @see <a
 *      href="http://code.google.com/apis/visualization/documentation/gallery/imagesparkline.html">
 *      Image Sparkline Reference. </a>
 */
public class ImageSparklineChart extends Visualization<ImageSparklineChart.Options> {

  /**
   * Options for drawing the chart.
   */
  public static class Options extends CommonChartOptions {
    public static Options create() {
      return JavaScriptObject.createObject().cast();
    }

    protected Options() {
    }

    /**
     * Specifies a color to use for all charts. A string in the format #rrggbb.
     * For example: '#00cc00'. Used only if the
     * {@link ImageSparklineChart.Options#setColors(String...)} option isn't
     * specified.
     */
    public final native void setColor(String color) /*-{
      this.color = color;
    }-*/;

    /**
     * <p>
     * If <code>true</code>, will fill the area below the line in color.
     * </p>
     * <p>
     * <i>Note: this option is currently broken, but should be fixed soon.</i>
     * </p>
     */
    public final native void setFill(boolean fill) /*-{
      this.fill = fill;
    }-*/;

    /**
     * The position of the labels. Supported values are 'none', 'left', 'right'.
     */
    public final native void setLabelPosition(String labelPosition) /*-{
      this.labelPosition = labelPosition;
    }-*/;

    /**
     * Vertical or horizontal layout: 'v' for vertical, 'h' for horizontal.
     */
    public final native void setLayout(String layout) /*-{
      this.layout = layout;
    }-*/;

    /**
     * If set to <code>false</code>, removes axis lines and labels. Default
     * value is <code>true</code>.
     */
    public final native void setShowAxisLines(boolean showAxisLines) /*-{
      this.showAxisLines = showAxisLines;
    }-*/;

    /**
     * If set to <code>false</code>, removes the labels of the values (the Y
     * axis labels).
     */
    public final native void setShowValueLabels(boolean showValueLabels) /*-{
      this.showValueLabels = showValueLabels;
    }-*/;
  }

  public static final String PACKAGE = "imagesparkline";

  public ImageSparklineChart() {
    super();
  }

  public ImageSparklineChart(AbstractDataTable data, Options options) {
    super(data, options);
  }

  @Override
  protected native JavaScriptObject createJso(Element parent) /*-{
    return new $wnd.google.visualization.ImageSparkLine(parent);
  }-*/;
}

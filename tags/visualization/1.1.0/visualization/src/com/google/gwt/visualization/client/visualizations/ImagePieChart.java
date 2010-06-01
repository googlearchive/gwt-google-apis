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
 * A pie chart that is rendered as an image using the Google Charts API.
 *
 *
 * @see <a href=
 *      "http://code.google.com/apis/visualization/documentation/gallery/imagepiechart.html"
 *      > Image Pie Chart Visualization Reference</a>
 */
public class ImagePieChart extends Visualization<ImagePieChart.Options> {

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
     * If set to true, displays a three-dimensional chart. Default value is
     * <code>false</code>.
     */
    public final native void setIs3D(boolean is3D) /*-{
      this.is3D = is3D;
    }-*/;

    /**
     * Specifies a color to use for all charts. Each series will be a gradation
     * of the color specified. A string in the format #rrggbb. For example:
     * '#00cc00'. Used only if the {@link Options#setColors} option isn't specified.
     */
    public final native void setColor(String color) /*-{
      this.color = color;
    }-*/;

    /**
     * What label, if any, to show for each slice. Choose from the following
     * values:
     * <ul>
     * <li>'none' - No labels.</li>
     * <li>'value' - Use the slice value as a label.</li>
     * <li>'name' - Use the slice name (the column name).</li>
     * </ul>
     */
    public final native void setLabels(String labels) /*-{
      this.labels = labels;
    }-*/;
  }

  public static final String PACKAGE = "imagepiechart";

  public ImagePieChart() {
    super();
  }

  public ImagePieChart(AbstractDataTable data, Options options) {
    super(data, options);
  }

  @Override
  protected native JavaScriptObject createJso(Element parent) /*-{
    return new $wnd.google.visualization.ImagePieChart(parent);
  }-*/;
}

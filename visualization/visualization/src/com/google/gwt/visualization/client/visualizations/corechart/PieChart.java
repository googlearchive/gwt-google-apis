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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.visualization.client.AbstractDataTable;

/**
 * Classic Pie Chart visualization.
 *
 * @see <a href=
 *      "http://code.google.com/apis/visualization/documentation/gallery/piechart.html"
 *      >Pie Chart Visualization Reference</a>
 */
public class PieChart extends CoreChart {

  /**
   * @param data
   * @param options
   */
  public PieChart(AbstractDataTable data, Options options) {
    super(data, options);
    options.setType(CoreChart.Type.PIE);
  }

  /**
   * Options for drawing pie chart.
   */
  public static PieOptions createPieOptions() {
    return JavaScriptObject.createObject().cast();
  }

  /**
   * Specific options used by pie charts.
   *
   * @see <a href=
   *      "http://code.google.com/apis/visualization/documentation/gallery/piechart.html#Configuration_Options"
   *      > Configuration Pie Options Reference</a>
   */
  public static class PieOptions extends Options {
    public static PieOptions create() {
      return JavaScriptObject.createObject().cast();
    }

    protected PieOptions() {
    }

    public final native void set3D(boolean is3D) /*-{
      this.is3D = is3D;
    }-*/;

    public final native void setPieResidueSliceLabel(String label) /*-{
      this.pieResidueSliceLabel = label;
    }-*/;

    public final native void setPieSliceText(String text) /*-{
      this.pieSliceText = text;
    }-*/;

    public final native void setPieSliceTextStyle(TextStyle textStyle) /*-{
      this.pieSliceTextStyle = textStyle;
    }-*/;

    public final native void setSliceVisibilityThreshold(double angle) /*-{
      this.sliceVisibilityThreshold = angle;
    }-*/;
  }
}

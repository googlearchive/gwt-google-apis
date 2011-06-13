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
import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.Element;
import com.google.gwt.visualization.client.AbstractDataTable;

/**
 * Combo Chart visualization. A chart that lets you render each series as a
 * different marker type from the following list: columns, lines, and area
 * lines.
 *
 * @see <a href=
 *      "http://code.google.com/apis/chart/interactive/docs/gallery/combochart.html"
 *      >Combo Chart Visualization Reference</a>
 */
public class ComboChart extends CoreChart {

  /**
   * Specific options used by combo charts.
   *
   * @see <a href=
   *      "http://code.google.com/apis/visualization/documentation/gallery/combochart.html#Configuration_Options"
   *      > Combo Configuration Options Reference</a>
   */
  public static class Options extends
      com.google.gwt.visualization.client.visualizations.corechart.Options {

    /**
     * Creates a new instance of the ComboChart.Options class. Use this instead
     * of the constructor to create instances of this native JavaScript object.
     */
    public static Options create() {
      return JavaScriptObject.createObject().cast();
    }

    protected Options() {
    }

    public final native void setSeries(int index, Series seriesAtIndex) /*-{
      if (!this.series) {
        this.series = {};
      }
      this.series[index] = seriesAtIndex;
    }-*/;
    
    public final native void setSeries(JsArray<Series> series) /*-{
      this.series = series;
    }-*/;

    public final void setSeriesType(Series.Type seriesType) {
      setSeriesType(seriesType.name().toLowerCase());
    }

    public final native void setSeriesType(String seriesType) /*-{
      this.seriesType = seriesType;
    }-*/;
  }

  /**
   * Creates options for drawing a combo chart.
   */
  public static Options createComboOptions() {
    return Options.create();
  }

  /**
   * @param data
   * @param options
   */
  public ComboChart(AbstractDataTable data, Options options) {
    super(data, options);
  }

  /**
   * @see CoreChart#createJso(Element)
   */
  @Override
  protected native JavaScriptObject createJso(Element parent) /*-{
    return new $wnd.google.visualization.ComboChart(parent);
  }-*/;
}

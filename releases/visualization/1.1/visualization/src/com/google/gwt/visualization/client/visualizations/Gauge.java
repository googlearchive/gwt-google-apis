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

import com.google.gwt.ajaxloader.client.ArrayHelper;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.dom.client.Element;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.AbstractDrawOptions;

/**
 * Visualization showing a gauge with a dial for each value.
 * 
 * @see <a href=
 *      "http://code.google.com/apis/visualization/documentation/gallery/gauge.html"
 *      > Gauge Visualization Reference</a>
 */
public class Gauge extends Visualization<Gauge.Options> {
  /**
   * Options for drawing the chart.
   */
  public static class Options extends AbstractDrawOptions {
    public static Options create() {
      return JavaScriptObject.createObject().cast();
    }

    protected Options() {
    }

    /**
     * Set the range of values for the Gauge.
     * 
     * @param min The minimum value shown on the Gauge.
     * @param max The maximum value shown on the Gauge.
     */
    public final native void setGaugeRange(int min, int max) /*-{
      this.min = min;
      this.max = max;
    }-*/;

    /**
     * Set the range of values to be highlighted in green.
     * 
     * @param from The value where the green highlighting should start.
     * @param to The value where the green highlighting should end.
     */
    public final native void setGreenRange(int from, int to) /*-{
      this.greenFrom = from;
      this.greenTo = to;
    }-*/;

    public final native void setHeight(int height) /*-{
      this.height = height;
    }-*/;

    public final void setMajorTicks(String... labels) {
      setMajorTicks(ArrayHelper.toJsArrayString(labels));
    }

    public final native void setMinorTicks(int numberOfTicks) /*-{
      this.minorTicks = numberOfTicks;
     }-*/;

    /**
     * Set the range of values to be highlighted in red.
     * 
     * @param from The value where the red highlighting should start.
     * @param to The value where the red highlighting should end.
     */
    public final native void setRedRange(int from, int to) /*-{
      this.redFrom = from;
      this.redTo = to;
    }-*/;

    public final void setSize(int width, int height) {
      setWidth(width);
      setHeight(height);
    }

    public final native void setWidth(int width) /*-{
      this.width = width;
    }-*/;

    /**
     * Set the range of values to be highlighted in yellow.
     * 
     * @param from The value where the yellow highlighting should start.
     * @param to The value where the yellow highlighting should end.
     */
    public final native void setYellowRange(int from, int to) /*-{
      this.yellowFrom = from;
      this.yellowTo = to;
    }-*/;

    private native void setMajorTicks(JsArrayString labels) /*-{
      this.majorTicks = labels;
    }-*/;
  }

  public static final String PACKAGE = "gauge";

  public Gauge() {
    super();
  }

  public Gauge(AbstractDataTable data, Options options) {
    super(data, options);
  }

  @Override
  protected native JavaScriptObject createJso(Element parent) /*-{
    return new $wnd.google.visualization.Gauge(parent);
  }-*/;
}

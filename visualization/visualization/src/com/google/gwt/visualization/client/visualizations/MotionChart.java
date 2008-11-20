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
import com.google.gwt.visualization.client.AbstractDrawOptions;
import com.google.gwt.visualization.client.VisualizationWidget;

/**
 * Motion Chart visualization. Note that this chart does not work when loading 
 * the HTML from a local file. It works only when loading the HTML from a web 
 * server.
 * 
 * The visualization package name is "motionchart", as in:
 * <code>
 * google.load("visualization", "1", {packages:["motionchart"]});
 * </code>
 * 
 * 
 * @see <a
 *      href="http://code.google.com/apis/visualization/documentation/gallery/motionchart.html">
 *      Motion Chart Visualization Reference</a>
 */
public class MotionChart extends Visualization<MotionChart.Options> {
  /**
   * Options for drawing the chart.
   * 
   */
  public static class Options extends AbstractDrawOptions {
    public static Options create() {
      return JavaScriptObject.createObject().cast();
    }

    protected Options() {
    }

    public final native void setHeight(int height) /*-{
      this.height = height;
    }-*/;

    public final void setSize(int width, int height) {
      setWidth(width);
      setHeight(height);
    }

    public final native void setWidth(int width) /*-{
      this.width = width;
    }-*/;
  }

  public static native MotionChart create(Element parent) /*-{
    return new $wnd.google.visualization.MotionChart(parent);
  }-*/;
  
  public static VisualizationWidget<MotionChart, Options> 
  createWidget(AbstractDataTable data, Options options) {
    Element div = DOM.createDiv();
    MotionChart viz = create(div);
    return new VisualizationWidget<MotionChart, Options>(div, viz, data, 
        options);
  }
  
  public static VisualizationWidget<MotionChart, Options> createWidget() {
    Element div = DOM.createDiv();
    MotionChart viz = create(div);
    return new VisualizationWidget<MotionChart, Options>(div, viz);
  }
  
  protected MotionChart() {
  }
}

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
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.AbstractDrawOptions;
import com.google.gwt.visualization.client.VisualizationWidget;

/**
 * Visualization showing a gauge with a dial for each value. May be loaded by 
 * calling: 
 * <code>
 * google.load("visualization", "1", {packages:["gauge"]});
 * </code>
 * 
 * 
 * @see <a
 *      href="http://code.google.com/apis/visualization/documentation/gallery/gauge.html">
 *      Gauge Visualization Reference</a>
 */
public class Gauge extends Visualization<Gauge.DrawOptions> {
  /**
   * Options for drawing the chart.
   * 
   */
  public static class DrawOptions extends AbstractDrawOptions {    
    public static DrawOptions create() {
      return JavaScriptObject.createObject().cast();
    }

    protected DrawOptions() {
    }
    
    public final native void setGaugeRange(int min, int max) /*-{
      this.min = min;
      this.max = max;
    }-*/;
    
    public final native void setGreenRange(int from, int to) /*-{
      this.greenFrom = from;
      this.greenTo = to;
    }-*/;
    
    public final native void setHeight(int height) /*-{
      this.height = height;
    }-*/;
    
    public final void setMajorTicks(String... labels) {
      setMajorTicks(createJsArray(labels));
    }
    
    public final native void setMinorTicks(int numberOfTicks) /*-{
      this.minorTicks = numberOfTicks;
    }-*/;
    
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
    
    public final native void setYellowRange(int from, int to) /*-{
      this.yellowFrom = from;
      this.yellowTo = to;
    }-*/;
    
    private native void setMajorTicks(JsArrayString labels) /*-{
      this.majorTicks = labels;
    }-*/; 
  }

  public static native Gauge create(Element parent) /*-{
    return new $wnd.google.visualization.Gauge(parent);
  }-*/;
  
  public static VisualizationWidget<Gauge, DrawOptions> 
  createWidget(AbstractDataTable data, DrawOptions options) {
    Element div = DOM.createDiv();
    Gauge viz = create(div);
    return new VisualizationWidget<Gauge, DrawOptions>(div, viz, data, 
        options);
  }
  
  public static VisualizationWidget<Gauge, DrawOptions> createWidget() {
    Element div = DOM.createDiv();
    Gauge viz = create(div);
    return new VisualizationWidget<Gauge, DrawOptions>(div, viz);
  }

  protected Gauge() {
  }
}

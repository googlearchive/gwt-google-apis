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
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.AbstractDrawOptions;
import com.google.gwt.visualization.client.events.Handler;
import com.google.gwt.visualization.client.events.ReadyHandler;
import com.google.gwt.visualization.client.events.StateChangeHandler;

/**
 * Motion Chart visualization. Note that this chart does not work when loading
 * the HTML from a local file. It works only when loading the HTML from a web
 * server.
 * 
 * @see <a
 *      href="http://code.google.com/apis/visualization/documentation/gallery/motionchart.html"
 *      > Motion Chart Visualization Reference</a>
 */
public class MotionChart extends Visualization<MotionChart.Options> {
  /**
   * Options for drawing the chart.
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

    public final native void setShowAdvancedPanel(boolean showAdvancedPanel) /*-{
      this.showAdvancedPanel = showAdvancedPanel;
    }-*/;

    public final native void setShowChartButtons(boolean showChartButtons) /*-{
      this.showChartButtons = showChartButtons;
    }-*/;

    public final native void setShowHeader(boolean showHeader) /*-{
      this.showHeader = showHeader;
    }-*/;

    public final native void setShowSelectListComponent(
        boolean showSelectListComponent) /*-{
      this.showSelectListComponent = showSelectListComponent;
    }-*/;

    public final native void setShowSidePanel(boolean showSidePanel) /*-{
      this.showSidePanel = showSidePanel;
    }-*/;

    public final native void setShowXMetricPicker(boolean showXMetricPicker) /*-{
      this.showXMetricPicker = showXMetricPicker;
    }-*/;

    public final native void setShowXScalePicker(boolean showXScalePicker) /*-{
      this.showXScalePicker = showXScalePicker;
    }-*/;

    public final native void setShowYMetricPicker(boolean showYMetricPicker) /*-{
      this.showYMetricPicker = showYMetricPicker;
    }-*/;

    public final native void setShowYScalePicker(boolean showYScalePicker) /*-{
      this.showYScalePicker = showYScalePicker;
    }-*/;

    public final void setSize(int width, int height) {
      setWidth(width);
      setHeight(height);
    }

    public final native void setState(String state) /*-{
      this.state = state;
    }-*/;

    public final native void setWidth(int width) /*-{
      this.width = width;
    }-*/;
  }

  public static final String PACKAGE = "motionchart";

  public MotionChart() {
    super();
  }

  public MotionChart(AbstractDataTable data, Options options) {
    super(data, options);
  }

  public final void addReadyHandler(ReadyHandler handler) {
    Handler.addHandler(this, "ready", handler);
  }

  public final void addStateChangeHandler(StateChangeHandler handler) {
    Handler.addHandler(this, "statechange", handler);
  }

  /**
   * Returns the current state of the {@link MotionChart}, serialized to a JSON
   * string. To assign this state to the chart, assign this string to the state
   * option in the draw() method. This is often used to specify a custom chart
   * state on startup, instead of using the default state.
   * 
   * @return a JSON encoded string indicating the state of the UI. This method
   *         may return <code>null</code> if the state was not supplied by
   *         {@link MotionChart.Options#setState(String)} or a statechange event
   *         has not yet fired.
   */
  public final native String getState() /*-{
    var jso = this.@com.google.gwt.visualization.client.visualizations.Visualization::getJso()();

    // The getState() method doesn't seem to always be present. I think this 
    // happens when you don't properly initialize it or when you try to query 
    // it before a statechanged event fires.  
    if (jso.getState) {
      return jso.getState();
    }
    return null;
  }-*/;

  @Override
  protected native JavaScriptObject createJso(Element parent) /*-{
    return new $wnd.google.visualization.MotionChart(parent);
  }-*/;
}

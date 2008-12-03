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
import com.google.gwt.visualization.client.Selectable;
import com.google.gwt.visualization.client.Selection;
import com.google.gwt.visualization.client.SelectionHelper;
import com.google.gwt.visualization.client.VisualizationWidget;
import com.google.gwt.visualization.client.events.SelectHandler;

/**
 * Organization Chart visualization. May be loaded by calling: <code>
 * google.load("visualization", "1", {packages:["orgchart"]});
 * </code>
 * 
 * 
 * @see <a href=
 *      "http://code.google.com/apis/visualization/documentation/gallery/orgchart.html"
 *      > Organization Chart Visualization Reference</a>
 */
public class OrgChart extends Visualization<OrgChart.Options> implements
    Selectable {
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

    public final native void setAllowHtml(boolean allowHtml) /*-{
      this.allowHtml = allowHtml;
    }-*/;

    public final void setSize(Size size) {
      setSize(size.name().toLowerCase());
    }

    private native void setSize(String size) /*-{
      this.size = size;
    }-*/;
  }

  /**
   * Argument to {@link OrgChart.Options#setSize(Size)}
   */
  public static enum Size {
    LARGE, MEDIUM, SMALL
  }

  public static final String PACKAGE = "orgchart";

  public static native OrgChart create(Element parent) /*-{
    return new $wnd.google.visualization.OrgChart(parent);
  }-*/;

  public static VisualizationWidget<OrgChart, Options> createWidget() {
    Element div = DOM.createDiv();
    OrgChart viz = create(div);
    return new VisualizationWidget<OrgChart, Options>(div, viz);
  }

  public static VisualizationWidget<OrgChart, Options> createWidget(
      AbstractDataTable data, Options options) {
    Element div = DOM.createDiv();
    OrgChart viz = create(div);
    return new VisualizationWidget<OrgChart, Options>(div, viz, data, options);
  }

  protected OrgChart() {
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

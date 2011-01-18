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
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayInteger;
import com.google.gwt.dom.client.Element;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.AbstractDrawOptions;
import com.google.gwt.visualization.client.Selectable;
import com.google.gwt.visualization.client.Selection;
import com.google.gwt.visualization.client.events.CollapseHandler;
import com.google.gwt.visualization.client.events.Handler;
import com.google.gwt.visualization.client.events.OnMouseOutHandler;
import com.google.gwt.visualization.client.events.OnMouseOverHandler;
import com.google.gwt.visualization.client.events.ReadyHandler;
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

    public final native void setAllowCollapse(boolean allow) /*-{
      this.allowCollapse = allow;
    }-*/;

    public final native void setAllowHtml(boolean allowHtml) /*-{
      this.allowHtml = allowHtml;
    }-*/;

    /**
     * Use {@link #setNodeClass(String)} instead.
     */
    @Deprecated
    public final native void setColor(String color) /*-{
      this.color = color;
    }-*/;

    public final native void setNodeClass(String nodeClass) /*-{
      this.nodeClass = nodeClass;
    }-*/;

    public final native void setSelectedNodeClass(String selectedNodeClass) /*-{
      this.selectedNodeClass = selectedNodeClass;
    }-*/;

    /**
     * Use {@link #setSelectedNodeClass(String)} instead.
     */
    @Deprecated
    public final native void setSelectionColor(String color) /*-{
      this.selectionColor = color;
    }-*/;

    public final void setSize(Size size) {
      setSize(size.name().toLowerCase());
    }

    private native void setSize(String size) /*-{
      this.size = size;
    }-*/;
  }

  /**
   * Argument to {@link Options#setSize(Size)}
   */
  public static enum Size {
    LARGE, MEDIUM, SMALL
  }

  public static final String PACKAGE = "orgchart";

  public OrgChart() {
    super();
  }

  public OrgChart(AbstractDataTable data, Options options) {
    super(data, options);
  }

  public final void addCollapseHandler(CollapseHandler handler) {
    Handler.addHandler(this, "collapse", handler);
  }

  public final void addOnMouseOutHandler(OnMouseOutHandler handler) {
    Handler.addHandler(this, "onmouseout", handler);
  }

  public final void addOnMouseOverHandler(OnMouseOverHandler handler) {
    Handler.addHandler(this, "onmouseover", handler);
  }

  public final void addReadyHandler(ReadyHandler handler) {
    Handler.addHandler(this, "ready", handler);
  }

  public final void addSelectHandler(SelectHandler handler) {
    Selection.addSelectHandler(this, handler);
  }

  public void collapse(int row, boolean collapsed) {
    this.collapse(getJso(), row, collapsed);
  }

  public JsArrayInteger getChildrenIndexes(int row) {
    return this.getChildrenIndexes(getJso(), row);
  }

  public JsArrayInteger getCollapsedNodes() {
    return this.getCollapsedNodes(getJso());
  }

  public final JsArray<Selection> getSelections() {
    return Selection.getSelections(this);
  }

  public final void setSelections(JsArray<Selection> sel) {
    Selection.setSelections(this, sel);
  }

  @Override
  protected native JavaScriptObject createJso(Element parent) /*-{
    return new $wnd.google.visualization.OrgChart(parent);
  }-*/;

  private native void collapse(JavaScriptObject jso, int row, boolean collapsed) /*-{
    jso.collapse(row, collapsed);
  }-*/;

  private native JsArrayInteger getChildrenIndexes(JavaScriptObject jso, int row) /*-{
    return jso.getChildrenIndexes(row);
  }-*/;

  private native JsArrayInteger getCollapsedNodes(JavaScriptObject jso) /*-{
    return jso.getCollapsedNodes();
  }-*/;
}

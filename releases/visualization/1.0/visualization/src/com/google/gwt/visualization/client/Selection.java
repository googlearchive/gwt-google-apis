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
package com.google.gwt.visualization.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.visualization.client.events.Handler;
import com.google.gwt.visualization.client.events.SelectHandler;
import com.google.gwt.visualization.client.visualizations.Visualization;

/**
 * Selection stores information about the current selection.
 * 
 */
public class Selection extends JavaScriptObject {
  public static <E extends AbstractVisualization<?>> void addSelectHandler(
      E viz, SelectHandler handler) {
    Handler.addHandler(viz, "select", handler);
  }
  
  public static <E extends Visualization<?>> void addSelectHandler(
      E viz, SelectHandler handler) {
    Handler.addHandler(viz, "select", handler);
  }
  
  public static native Selection createCellSelection(int row, int column) /*-{
    return {'row' : row, 'column' : column};
  }-*/;
  
  public static native Selection createColumnSelection(int i) /*-{
    return {'column' : i};
  }-*/;

  public static native Selection createRowSelection(int i) /*-{
    return {'row' : i};
  }-*/;

  public static final native JsArray<Selection> getSelections(Selectable viz) /*-{
    var jso = viz.@com.google.gwt.visualization.client.visualizations.Visualization::getJso()();
    return jso.getSelection();
  }-*/;

  public static final native void setSelections(Selectable viz, JsArray<Selection> sel) /*-{
    var jso = viz.@com.google.gwt.visualization.client.visualizations.Visualization::getJso()();
    jso.setSelection(sel);
  }-*/;

  public static native void triggerSelection(Selectable viz, JsArray<Selection> selection) /*-{
    var jso = viz.@com.google.gwt.visualization.client.visualizations.Visualization::getJso()();
    $wnd.google.visualization.events.trigger(jso, 'select', selection);
  }-*/;

  protected Selection() {
  }

  /**
   * @return the column of the selection. Only call this if the selection
   *         is either a column or a cell.
   */
  public final native int getColumn() /*-{
    return this.column;
  }-*/;

  /**
   * @return the row of the selection. Only call this if the selection is
   *         either a row or a cell.
   */
  public final native int getRow() /*-{
    return this.row;
  }-*/;

  /**
   * @return <code>true</code> if the selection is a cell, 
   * otherwise <code>false</code>.
   */
  public final native boolean isCell() /*-{
    return typeof this.row == 'number' && typeof this.column == 'number';
  }-*/;

  /**
   * @return <code>true</code> if the selection is a column, 
   * otherwise <code>false</code>.
   */
  public final native boolean isColumn() /*-{
    return typeof this.row != 'number' && typeof this.column == 'number';
  }-*/;

  /**
   * @return <code>true</code> if the selection is a row, 
   * otherwise <code>false</code>.
   */
  public final native boolean isRow() /*-{
    return typeof this.row == 'number' && typeof this.column != 'number';
  }-*/;
}
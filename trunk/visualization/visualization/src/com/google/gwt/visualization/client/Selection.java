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
 */
public class Selection extends JavaScriptObject {
  /**
   * Add a SelectHandler to an AbstractVisualization.
   * @param viz An AbstractVisualization that implements Selectable.
   * @param handler The SelectHandler to add.
   */
  public static <E extends Visualization<?>, Selectable> void addSelectHandler(E viz, SelectHandler handler) {
    Handler.addHandler(viz, "select", handler);
  }
  
  /**
   * Create a selection that specifies a row and a column.
   * 
   * @param row the row of the selection.
   * @param column The column of the selection.
   * @return A selection that specifies a row and a column.
   */
  public static native Selection createCellSelection(int row, int column) /*-{
    return {'row' : row, 'column' : column};
  }-*/;

  /**
   * Create a selection with a null row.
   * 
   * @param i The column of the selection.
   * @return A selection with a null row.
   */
  public static native Selection createColumnSelection(int i) /*-{
    return {'column' : i};
  }-*/;

  /**
   * Create a selection with a null column.
   * 
   * @param i The row of the selection.
   * @return A selection with a null column.
   */
  public static native Selection createRowSelection(int i) /*-{
    return {'row' : i};
  }-*/;

  /**
   * Get the Selections that are currently selected.
   * 
   * @param viz A Selectable visualization.
   * @return A JsArray of Selections.
   */
  public static final native <E extends Visualization<?>, Selectable> JsArray<Selection> getSelections(E viz) /*-{
    var jso = viz.@com.google.gwt.visualization.client.visualizations.Visualization::getJso()();
    return jso.getSelection();
  }-*/;

  /**
   * Set the selections that will be selected.
   * 
   * @param viz A Selectable visualization.
   * @param selections The Selections that will be selected.
   */
  public static final native <E extends Visualization<?>, Selectable> void setSelections(E viz, JsArray<Selection> selections) /*-{
    var jso = viz.@com.google.gwt.visualization.client.visualizations.Visualization::getJso()();
    if (selections == null) {
      jso.setSelection([{'row': null, 'column': null}]);
    } else {
      jso.setSelection(selections);
    }
  }-*/;

  /**
   * Trigger a select event.
   * 
   * @param viz A Selectable visualization.
   * @param selections The selections that will be selected.
   */
  public static native <E extends Visualization<?>, Selectable> void triggerSelection(E viz, JsArray<Selection> selections) /*-{
    var jso = viz.@com.google.gwt.visualization.client.visualizations.Visualization::getJso()();
    $wnd.google.visualization.events.trigger(jso, 'select', selections);
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
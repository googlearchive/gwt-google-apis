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

import com.google.gwt.visualization.client.events.Handler;
import com.google.gwt.visualization.client.events.SelectHandler;
import com.google.gwt.visualization.client.visualizations.Visualization;

/**
 * SelectionHelper implements selection related functions.
 * 
 */
public class SelectionHelper {
  public static void addSelectHandler(Visualization<?> viz, SelectHandler handler) {
    Handler.addHandler(viz, "select", handler);
  }

  /**
   * Create a selection with one entry.
   * 
   * @param row row index or null.
   * @param column column index or null.
   * @return a Selection object.
   */
  public static final Selection createSelection(Integer row, Integer column) {
    Selection sel = createSelection();
    if (row != null) {
      setSelectionRow(sel, 0, row);
    }
    if (column != null) {
      setSelectionColumn(sel, 0, column);
    }
    return sel;
  }

  public static final native Selection getSelection(Selectable visualization) /*-{
    return visualization.getSelection();
  }-*/;

  public static final native void setSelection(Selectable visualization, Selection sel) /*-{
    visualization.setSelection(sel);
  }-*/;

  private static native Selection createSelection() /*-{
    return [ {"row" : null, "column" : null} ];
  }-*/;

  private static native void setSelectionColumn(Selection sel, int i, int column) /*-{
    sel[i].column = column;
  }-*/;

  private static native void setSelectionRow(Selection sel, int i, int row) /*-{
    sel[i].row = row;
  }-*/;

  protected SelectionHelper() {
  }
}

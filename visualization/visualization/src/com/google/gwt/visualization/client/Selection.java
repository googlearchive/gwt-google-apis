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

/**
 * Selection stores information about the current selection.
 * 
 */
public class Selection extends JavaScriptObject {
  protected Selection() {
  }

  /**
   * @param i the index of the selection.
   * @return the column of the i'th selection. Only call this if the selection
   *         is either a column or a cell.
   */
  public final native int getColumn(int i) /*-{
    return this[i].column;
  }-*/;

  /**
   * @return number of items that are selected
   */
  public final native int getLength() /*-{
    return this.length;
  }-*/;

  /**
   * @param i the index of the selection.
   * @return the row of the i'th selection. Only call this if the selection is
   *         either a row or a cell.
   */
  public final native int getRow(int i) /*-{
    return this[i].row;
  }-*/;

  /**
   * 
   * @param i the index of the selection.
   * @return true if the i'th selection is a cell.
   */
  public final native boolean isCell(int i) /*-{
    return (this[i].row && this[i].column) ? true : false;
  }-*/;

  /**
   * 
   * @param i the index of the selection.
   * @return true if the i'th selection is a column.
   */
  public final native boolean isColumn(int i) /*-{
    return (this[i].column && this[i].row == undefined) ? true : false;
  }-*/;

  /**
   * @param i the index of the selection.
   * @return true if the i'th selection is a row.
   */
  public final native boolean isRow(int i) /*-{
    return (this[i].column == undefined && this[i].row) ? true : false;
  }-*/;
}
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

import com.google.gwt.ajaxloader.client.ArrayHelper;
import com.google.gwt.core.client.JsArrayInteger;

/**
 * This class represents the DataView.
 *
 * @see <a
 *      href="http://code.google.com/apis/visualization/documentation/reference.html#DataView">
 *      DataView API Reference</a>
 */
public class DataView extends AbstractDataTable {

  public static native DataView create(AbstractDataTable table) /*-{
    return new $wnd.google.visualization.DataView(table);
  }-*/;

  protected DataView() {
  }

  public final native int getTableColumnIndex(int viewColumnIndex) /*-{
    return this.getTableColumnIndex(viewColumnIndex);
  }-*/;

  public final native int getTableRowIndex(int viewRowIndex) /*-{
    return this.getTableRowIndex(viewRowIndex);
  }-*/;

  public final native int getViewColumnIndex(int tableColumnIndex) /*-{
    return this.getViewColumnIndex(tableColumnIndex);
  }-*/;

  public final native JsArrayInteger getViewColumns() /*-{
    return this.getViewColumns();
  }-*/;

  public final native int getViewRowIndex(int tableRowIndex) /*-{
    return this.getViewRowIndex(tableRowIndex);
  }-*/;

  public final native JsArrayInteger getViewRows() /*-{
    return this.getViewRows();
  }-*/;

  public final void hideColumns(int[] columnIndices) {
    hideColumns(ArrayHelper.toJsArrayInteger(columnIndices));
  }

  public final native void hideColumns(JsArrayInteger columnIndices) /*-{
    this.hideColumns(columnIndices);
  }-*/;

  public final native void hideRows(int from, int to) /*-{
    this.hideRows(from, to);
  }-*/;

  public final void hideRows(int[] rowsIndices) {
    hideRows(ArrayHelper.toJsArrayInteger(rowsIndices));
  }

  public final native void hideRows(JsArrayInteger rowsIndices) /*-{
    this.hideRows(rowsIndices);
  }-*/;

  public final void setColumns(int[] columnIndices) {
    setColumns(ArrayHelper.toJsArrayInteger(columnIndices));
  }

  public final native void setColumns(JsArrayInteger columnIndices) /*-{
    this.setColumns(columnIndices);
  }-*/;

  public final native void setRows(int from, int to) /*-{
    this.setRows(from, to);
  }-*/;

  public final void setRows(int[] rowsIndices) {
    setRows(ArrayHelper.toJsArrayInteger(rowsIndices));
  }

  public final native void setRows(JsArrayInteger rowsIndices) /*-{
    this.setRows(rowsIndices);
  }-*/;

}

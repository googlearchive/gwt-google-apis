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

import java.util.Date;

/**
 * This class implements the common methods of DataTable and DataView.
 * 
 * @see <a
 *      href="http://code.google.com/apis/visualization/documentation/reference.html#DataTable">
 *      DataTable API Reference</a>
 */
public class AbstractDataTable extends JavaScriptObject {

  /**
   * The type of a column.
   */
  public enum ColumnType {
    BOOLEAN, DATE, DATETIME, NUMBER, STRING, TIMEOFDAY;

    static ColumnType getColumnTypeFromString(String parameter) {
      return ColumnType.valueOf(parameter.toUpperCase());
    }

    String getParameter() {
      return name().toLowerCase();
    }
  }

  protected AbstractDataTable() {
  }

  public final native String getColumnId(int columnIndex) /*-{
    return this.getColumnId(columnIndex);
  }-*/;

  public final native String getColumnLabel(int columnIndex) /*-{
    return this.getColumnLabel(columnIndex);
  }-*/;

  public final native String getColumnPattern(int columnIndex) /*-{
    return this.getColumnPattern(columnIndex);
  }-*/;

  public final native Range getColumnRange(int columnIndex) /*-{
    return this.getColumnRange(columnIndex);
  }-*/;

  public final ColumnType getColumnType(int columnIndex) {
    return ColumnType.getColumnTypeFromString(getColumnTypeAsString(columnIndex));
  }

 public final native String getFormattedValue(int rowIndex, int columnIndex)/*-{
    return this.getFormattedValue(rowIndex, columnIndex);
  }-*/;

  public final native int getNumberOfColumns() /*-{
    return this.getNumberOfColumns();
  }-*/;

  public final native int getNumberOfRows() /*-{
    return this.getNumberOfRows();
  }-*/;

  public final native String getProperty(int rowIndex, int columnIndex,
      String name) /*-{
    return this.getProperty(rowIndex, columnIndex, name);
  }-*/;

  public final Date getValueDate(int rowIndex, int columnIndex) {
    return new Date(getValueTimevalue(rowIndex, columnIndex));
  }

  public final native double getValueDouble(int rowIndex, int columnIndex) /*-{
    return this.getValue(rowIndex, columnIndex);
  }-*/;

  public final native int getValueInt(int rowIndex, int columnIndex) /*-{
    return this.getValue(rowIndex, columnIndex);
  }-*/;

  public final native String getValueString(int rowIndex, int columnIndex) /*-{
    return this.getValue(rowIndex, columnIndex);
  }-*/;
  
  private native String getColumnTypeAsString(int columnIndex)/*-{
    return this.getColumnType(columnIndex);
  }-*/;

  private native int getValueTimevalue(int rowIndex, int columnIndex) /*-{
    return this.getValue(rowIndex, columnIndex).getTime();
  }-*/;
}

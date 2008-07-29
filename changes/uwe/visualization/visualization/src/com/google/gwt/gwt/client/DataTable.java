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
package com.google.visualization.gwt.client;

import com.google.gwt.core.client.JavaScriptObject;

import java.util.Date;

/**
 * This class represents the DataTable.
 * 
 * @see <a
 *      href="http://code.google.com/apis/visualization/documentation/reference.html#DataTable">
 *      DataTable API Reference</a>
 */
public class DataTable extends JavaScriptObject {

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

  public static native DataTable create() /*-{
    return new $wnd.google.visualization.DataTable();
  }-*/;

  protected DataTable() {
  }

  public final int addColumn(ColumnType type) {
    return addColumn(type.getParameter());
  }

  public final int addColumn(ColumnType type, String label) {
    return addColumn(type.getParameter(), label);
  }

  public final int addColumn(ColumnType type, String label, String id) {
    return addColumn(type.getParameter(), label, id);
  }

  public final native int addRow() /*-{
    return this.addRow();
  }-*/;

  public final native int addRows(int number) /*-{
    return this.addRows(number);
  }-*/;

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
  
  public final void insertColumn(int columnIndex, ColumnType type) {
    insertColumn(columnIndex, type.getParameter());
  }
  
  public final void insertColumn(int columnIndex, ColumnType type,
      String label) {
    insertColumn(columnIndex, type.getParameter(), label);
  }
  
  public final void insertColumn(int columnIndex, ColumnType type,
      String label, String id) {
    insertColumn(columnIndex, type.getParameter(), label, id);
  }

  public final native void insertRows(int rowIndex, int numberOfRows) /*-{
    this.insertRows(rowIndex, numberOfRows);
  }-*/;

  public final native void removeColumn(int columnIndex) /*-{
    this.removeColumn(columnIndex);
  }-*/;

  public final native void removeColumns(int columnIndex, int numberOfColumns) /*-{
    this.removeColumns(columnIndex, numberOfColumns);
  }-*/;

  public final native void removeRow(int rowIndex) /*-{
    this.removeRow(rowIndex);
  }-*/;

  public final native void removeRows(int rowIndex, int numberOfRows) /*-{
    this.removeRows(rowIndex, numberOfRows);
  }-*/;

  public final native void setCell(int rowIndex, int columnIndex, boolean value) /*-{
    this.setCell(rowIndex, columnIndex, value);
  }-*/;

  public final native void setCell(int rowIndex, int columnIndex,
      boolean value, String formattedValue) /*-{
    this.setCell(rowIndex, columnIndex, value, formattedValue);
  }-*/;

  public final native void setCell(int rowIndex, int columnIndex,
      boolean value, String formattedValue, Properties properties) /*-{
    this.setCell(rowIndex, columnIndex, value, formattedValue, properties);
  }-*/;

  public final native void setCell(int rowIndex, int columnIndex, double value) /*-{
    this.setCell(rowIndex, columnIndex, value);
  }-*/;

  public final native void setCell(int rowIndex, int columnIndex, double value,
      String formattedValue) /*-{
    this.setCell(rowIndex, columnIndex, value, formattedValue);
  }-*/;

  public final native void setCell(int rowIndex, int columnIndex, double value,
      String formattedValue, Properties properties) /*-{
    this.setCell(rowIndex, columnIndex, value, formattedValue, properties);
  }-*/;

  public final native void setCell(int rowIndex, int columnIndex, int value) /*-{
    this.setCell(rowIndex, columnIndex, value);
  }-*/;

  public final native void setCell(int rowIndex, int columnIndex, int value,
      String formattedValue) /*-{
    this.setCell(rowIndex, columnIndex, value, formattedValue);
  }-*/;

  public final native void setCell(int rowIndex, int columnIndex, int value,
      String formattedValue, Properties properties) /*-{
    this.setCell(rowIndex, columnIndex, value, formattedValue, properties);
  }-*/;

  public final native void setCell(int rowIndex, int columnIndex, String value) /*-{
    this.setCell(rowIndex, columnIndex, value);
  }-*/;

  public final native void setCell(int rowIndex, int columnIndex, String value,
      String formattedValue) /*-{
    this.setCell(rowIndex, columnIndex, value, formattedValue);
  }-*/;

  public final native void setCell(int rowIndex, int columnIndex, String value,
      String formattedValue, Properties properties) /*-{
    this.setCell(rowIndex, columnIndex, value, formattedValue, properties);
  }-*/;

  public final native void setColumnLabel(int columnIndex, String label) /*-{
    this.setColumnLabel(columnIndex, label);
  }-*/;

  public final native void setFormattedValue(int rowIndex, int columnIndex,
      String value) /*-{
    this.setFormattedValue(rowIndex, columnIndex, value);
  }-*/;

  public final native void setProperties(int rowIndex, int columnIndex,
      Properties properties) /*-{
    this.setProperties(rowIndex, columnIndex, properties);
  }-*/;

  public final native void setProperty(int rowIndex, int columnIndex,
      String name, String value) /*-{
    this.setProperty(rowIndex, columnIndex, name, value);
  }-*/;

  public final native void setValue(int rowIndex, int columnIndex, boolean value) /*-{
    this.setValue(rowIndex, columnIndex, value);
  }-*/;

  public final void setValue(int rowIndex, int columnIndex, Date date) {
    setValueDate(rowIndex, columnIndex, (int) date.getTime());
  }

  public final native void setValue(int rowIndex, int columnIndex, double value) /*-{
    this.setValue(rowIndex, columnIndex, value);
  }-*/;

  public final native void setValue(int rowIndex, int columnIndex, int value) /*-{
    this.setValue(rowIndex, columnIndex, value);
  }-*/;

  public final native void setValue(int rowIndex, int columnIndex, int hour,
      int minute, int second, int millis) /*-{
    this.setValue(rowIndex, columnIndex, [ hour, minute, second, millis ]);
  }-*/;

  public final native void setValue(int rowIndex, int columnIndex, String value) /*-{
    this.setValue(rowIndex, columnIndex, value);
  }-*/;
  
  // TODO: add sort, getSortedRows and getValue for timeofday 

  private native int addColumn(String type) /*-{
    return this.addColumn(type);
  }-*/;

  private native int addColumn(String type, String label) /*-{
    return this.addColumn(type, label);
  }-*/;

  private native int addColumn(String type, String label, String id) /*-{
    return this.addColumn(type, label, id);
  }-*/;
  
  private native String getColumnTypeAsString(int columnIndex)/*-{
    return this.getColumnType(columnIndex);
  }-*/;

  private native int getValueTimevalue(int rowIndex, int columnIndex) /*-{
    return this.getValue(rowIndex, columnIndex).getTime();
  }-*/;
  
  private native void insertColumn(int columnIndex, String type) /*-{
    return this.insertColumn(columnIndex, type);
  }-*/;
  
  private native void insertColumn(int columnIndex, String type, String label) /*-{
    return this.insertColumn(columnIndex, type, label);
  }-*/;
  
  private native void insertColumn(int columnIndex, String type, String label,
      String id) /*-{
    return this.insertColumn(columnIndex, type, label, id);
  }-*/;

  private native void setValueDate(int rowIndex, int columnIndex, int value) /*-{
    this.setValue(rowIndex, columnIndex, new Date(value));
  }-*/;
}

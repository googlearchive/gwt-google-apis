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
import com.google.gwt.core.client.JsArrayInteger;
import com.google.gwt.core.client.JsArrayNumber;
import com.google.gwt.visualization.client.TimeOfDay.BadTimeException;

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
    BOOLEAN("boolean"),
    DATE("date"),
    DATETIME("datetime"),
    NUMBER("number"),
    STRING("string"),
    TIMEOFDAY("timeofday");

    private final String parameter;

    ColumnType(String parameter) {
      this.parameter = parameter;
    }
    /**
     * Get a ColumnType enum value from the String representation.
     * 
     * @param parameter A String corresponding to a ColumnType enum value.
     * @return a ColumnType enum value corresponding to the given String.
     */
    static ColumnType getColumnTypeFromString(String parameter) {
      return ColumnType.valueOf(parameter.toUpperCase());
    }

    /**
     * Get the String representation of the ColumnType.
     * 
     * @return the String representation of the ColumnType.
     */
    String getParameter() {
      return parameter;
    }
  }

  protected AbstractDataTable() {
  }

  public final native String getColumnId(int columnIndex) /*-{
    return this.getColumnId(columnIndex);
  }-*/;
  
  public final native int getColumnIndex(String columnId) /*-{
    return this.getColumnIndex(columnId);
  }-*/;

  public final native String getColumnLabel(int columnIndex) /*-{
    return this.getColumnLabel(columnIndex);
  }-*/;

  public final native String getColumnPattern(int columnIndex) /*-{
    return this.getColumnPattern(columnIndex);
  }-*/;

  public final native String getColumnProperty(int columnIndex,
      String propertyName) /*-{
    return this.getColumnProperty(columnIndex, propertyName);
  }-*/;

  public final native Range getColumnRange(int columnIndex) /*-{
    return this.getColumnRange(columnIndex);
  }-*/;

  public final ColumnType getColumnType(int columnIndex) {
    return ColumnType.getColumnTypeFromString(getColumnTypeAsString(columnIndex));
  }

  public final native String getFormattedValue(int rowIndex, int columnIndex) /*-{
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

  public final native boolean getValueBoolean(int rowIndex, int columnIndex) /*-{
    return this.getValue(rowIndex, columnIndex);
  }-*/;

  public final Date getValueDate(int rowIndex, int columnIndex) {
    JsArrayNumber timevalue = getValueTimevalue(rowIndex, columnIndex);
    if (timevalue.length() == 0) {
      return null;
    } else {
      return new Date((long) timevalue.get(0));
    }
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

  public final TimeOfDay getValueTimeOfDay(int rowIndex, int columnIndex) {
    JsArrayInteger jsArray = getValueArrayInteger(rowIndex, columnIndex);
    if (jsArray == null) {
      return null;
    }
    TimeOfDay result = new TimeOfDay();
    try {
      result.setHour(jsArray.get(0));
      result.setMinute(jsArray.get(1));
      result.setSecond(jsArray.get(2));
      result.setMillisecond(jsArray.get(3));
    } catch (BadTimeException e) {
      // this should never happen, because setValue should catch an invalid
      // time of day
      throw new RuntimeException("Invalid time of day.");
    }
    return result;
  }

  /**
   * Check if the value in a cell is null.  Helpful for columns with primitive
   * types such as number and boolean.
   * 
   * @param rowIndex The index of the row.
   * @param columnIndex The index of the column.
   * @return <code>true</code> if the value in the cell at rowIndex, 
   * columnIndex is null, otherwise <code>false</code>.
   */
  public final native boolean isValueNull(int rowIndex, int columnIndex) /*-{
    return this.getValue(rowIndex, columnIndex) == null;
  }-*/;

  private native String getColumnTypeAsString(int columnIndex)/*-{
    return this.getColumnType(columnIndex);
  }-*/;

  private native JsArrayInteger getValueArrayInteger(int rowIndex,
      int columnIndex) /*-{
    return this.getValue(rowIndex, columnIndex);
  }-*/;

  /**
   * Private jsni to access date value which may be null.
   * 
   * @param rowIndex The row.
   * @param columnIndex The column.
   * @return An array with 1 or 0 elements. 1 element indicates a non-null
   *         value, and 0 elements indicates a null value. This is a jsni hack.
   *         Can't return double because the return type may be null. Can't
   *         return Double because the behavior of trying to autobox a jsni
   *         "number" is undefined.
   */
  private native JsArrayNumber getValueTimevalue(int rowIndex, int columnIndex) /*-{
    var value = this.getValue(rowIndex, columnIndex);
    if (value == null) {
      return [];
    } else {
      return [value.getTime()];
    }
  }-*/;
}

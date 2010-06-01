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
package com.google.gwt.gears.client.database;

import com.google.gwt.core.client.JavaScriptException;
import com.google.gwt.core.client.JavaScriptObject;

import java.util.Date;

/**
 * Represents the results of executing an SQL statement. A
 * <code>ResultSet</code> must have its {@link #close()} called once it is no
 * longer needed.
 * 
 * The {@link ResultSet} class provides methods for getting fields as a
 * particular type, <code>getFieldAdByte</code>, <code>getFieldAdChar</code>,
 * etc. These getter methods will attempt to convert the field value into the
 * requested type, but the overflow behavior is undefined.
 */
public final class ResultSet extends JavaScriptObject {
  protected ResultSet() {
    // Required for overlay types
  }

  /**
   * Releases the state associated with this {@link ResultSet}.
   * 
   * You are required to call close() when you are finished with any
   * {@link ResultSet}.
   * 
   * Note: there is currently a feature request against the JavaScript API to
   * have close called automatically when the result set goes out of scope.
   * 
   * @throws DatabaseException on any error
   */
  public void close() throws DatabaseException {
    try {
      uncheckedClose();
    } catch (JavaScriptException ex) {
      throw new DatabaseException(ex.getMessage(), ex);
    }
  }

  /**
   * Retrieves the value of the field at <code>fieldIndex</code> as a
   * <code>byte</code>.
   * 
   * @param fieldIndex zero-based index of the field
   * @return the field value as a byte
   * @throws DatabaseException if the <code>fieldIndex</code> is out of range
   */
  public byte getFieldAsByte(int fieldIndex) throws DatabaseException {
    return (byte) getFieldAsDouble(fieldIndex);
  }

  /**
   * Retrieves the value of the field at <code>fieldIndex</code> as a
   * <code>char</code>.
   * 
   * @param fieldIndex zero-based index of the field
   * @return the field value as a char
   * @throws DatabaseException if the <code>fieldIndex</code> is out of range
   */
  public char getFieldAsChar(int fieldIndex) throws DatabaseException {
    try {
      return uncheckedGetFieldAsChar(fieldIndex);
    } catch (JavaScriptException ex) {
      throw new DatabaseException(ex.getMessage(), ex);
    }
  }

  /**
   * Retrieves the value of the field at <code>fieldIndex</code> as a
   * <code>Date</code>.
   * 
   * @param fieldIndex zero-based index of the field
   * @return the field value as a Date
   * @throws DatabaseException if the <code>fieldIndex</code> is out of range
   */
  public Date getFieldAsDate(int fieldIndex) throws DatabaseException {
    try {
      long value = (long) uncheckedGetFieldAsDate(fieldIndex);
      return new Date(value);
    } catch (JavaScriptException ex) {
      throw new DatabaseException(ex.getMessage(), ex);
    }
  }

  /**
   * Retrieves the value of the field at <code>fieldIndex</code> as a
   * <code>double</code>.
   * 
   * @param fieldIndex zero-based index of the field
   * @return the field value as a double
   * @throws DatabaseException if the <code>fieldIndex</code> is out of range
   */
  public double getFieldAsDouble(int fieldIndex) throws DatabaseException {
    try {
      return uncheckedGetFieldAsDouble(fieldIndex);
    } catch (JavaScriptException ex) {
      throw new DatabaseException(ex.getMessage(), ex);
    }
  }

  /**
   * Retrieves the value of the field at <code>fieldIndex</code> as a
   * <code>float</code>.
   * 
   * @param fieldIndex zero-based index of the field
   * @return the field value as a float
   * @throws DatabaseException if the <code>fieldIndex</code> is out of range
   */
  public float getFieldAsFloat(int fieldIndex) throws DatabaseException {
    return (float) getFieldAsDouble(fieldIndex);
  }

  /**
   * Retrieves the value of the field at <code>fieldIndex</code> as an
   * <code>int</code>.
   * 
   * @param fieldIndex zero-based index of the field
   * @return the field value as an int
   * @throws DatabaseException if the <code>fieldIndex</code> is out of range
   */
  public int getFieldAsInt(int fieldIndex) throws DatabaseException {
    return (int) getFieldAsDouble(fieldIndex);
  }

  /**
   * Retrieves the value of the field at <code>fieldIndex</code> as a
   * <code>long</code>.
   * 
   * @param fieldIndex zero-based index of the field
   * @return the field value as a long
   * @throws DatabaseException if the <code>fieldIndex</code> is out of range
   */
  public long getFieldAsLong(int fieldIndex) throws DatabaseException {
    return (long) getFieldAsDouble(fieldIndex);
  }

  /**
   * Retrieves the value of the field at <code>fieldIndex</code> as a
   * <code>short</code>.
   * 
   * @param fieldIndex zero-based index of the field
   * @return the field value as a short
   * @throws DatabaseException if the <code>fieldIndex</code> is out of range
   */
  public short getFieldAsShort(int fieldIndex) throws DatabaseException {
    return (short) getFieldAsDouble(fieldIndex);
  }

  /**
   * Retrieves the value of the field at <code>fieldIndex</code> as a
   * <code>String</code>.
   * 
   * @param fieldIndex zero-based index of the field
   * @return the field value as a String
   * @throws DatabaseException if the <code>fieldIndex</code> is out of range
   */
  public String getFieldAsString(int fieldIndex) throws DatabaseException {
    try {
      return uncheckedGetFieldAsString(fieldIndex);
    } catch (JavaScriptException ex) {
      throw new DatabaseException(ex.getMessage(), ex);
    }
  }

  /**
   * Returns the number of fields in this result set.
   * 
   * @return the number of fields (columns) in this result set
   */
  public native int getFieldCount() /*-{
    return this.fieldCount();
  }-*/;

  /**
   * Returns the name of the specified field (column) in this result set.
   * 
   * @param fieldIndex the zero-based index of the desired field
   * @return the name of the field
   */
  public String getFieldName(int fieldIndex) throws DatabaseException {
    try {
      return uncheckedGetFieldName(fieldIndex);
    } catch (JavaScriptException ex) {
      throw new DatabaseException(ex.getMessage(), ex);
    }
  }

  /**
   * Returns <code>true</code> if this <code>ResultSet</code> cursor is
   * currently pointing at a valid row. Typically used to iterate over rows in
   * the ResultSet.
   * 
   * @return true if there is a valid row containing data, false otherwise
   */
  public native boolean isValidRow() /*-{
    return this.isValidRow();
  }-*/;

  /**
   * Advances to the next row of the results.
   */
  public native void next() /*-{
    this.next();
  }-*/;

  /**
   * Native proxy call to the close method on the JavaScript object.
   */
  private native void uncheckedClose() /*-{
    this.close();
  }-*/;

  private native char uncheckedGetFieldAsChar(int fieldIndex) /*-{
    var val = this.field(fieldIndex);
    if (val == null) {
      return 0;
    }

    if (typeof val == 'string') {
      return val.charCodeAt(0);
    } else {
      return val;
    }
  }-*/;

  private native double uncheckedGetFieldAsDate(int fieldIndex) /*-{
    var val = this.field(fieldIndex);
    if (val == null) {
      return -1;
    } else {
      var d = new Date(val);
      return d.getTime();
    }
  }-*/;

  private native double uncheckedGetFieldAsDouble(int fieldIndex) /*-{
    var val = this.field(fieldIndex);
    return val == null ? 0 : Number(val);
  }-*/;

  private native String uncheckedGetFieldAsString(int fieldIndex) /*-{
    var val = this.field(fieldIndex);
    return val == null ? null : String(val);
  }-*/;

  private native String uncheckedGetFieldName(int fieldIndex) /*-{
    return this.fieldName(fieldIndex);
  }-*/;
}
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
package com.google.gwt.gears.database.client;

import com.google.gwt.core.client.JavaScriptException;
import com.google.gwt.core.client.JavaScriptObject;

import java.util.Date;

/**
 * Represents the results of executing an SQL statement. A
 * <code>ResultSet</code> must have its {@link #close()} called once it is no
 * longer needed.
 */
public class ResultSet {
  private static double getFieldAsDouble(JavaScriptObject jso, int fieldIndex)
      throws DatabaseException {
    try {
      return nativeGetFieldAsDouble(jso, fieldIndex);
    } catch (JavaScriptException ex) {
      throw new DatabaseException(ex.getMessage(), ex);
    }
  }

  private static String getFieldAsString(JavaScriptObject jso, int fieldIndex)
      throws DatabaseException {
    try {
      return nativeGetFieldAsString(jso, fieldIndex);
    } catch (JavaScriptException ex) {
      throw new DatabaseException(ex.getMessage(), ex);
    }
  }

  /**
   * Native proxy call to the close method on the JavaScript object.
   */
  private static native void nativeClose(JavaScriptObject rsetObj) /*-{
    rsetObj.close();
  }-*/;

  private static native char nativeGetFieldAsChar(JavaScriptObject rsetObj,
      int fieldIndex) /*-{
    var val = rsetObj.field(fieldIndex);
    if (val == null) {
      return 0;
    }
   
    if (typeof val == 'string') {
      return val.charCodeAt(0);
    } else {
      return val;
    }
  }-*/;

  private static native double nativeGetFieldAsDate(JavaScriptObject rsetObj,
      int fieldIndex) /*-{
    var val = rsetObj.field(fieldIndex);
    if (val == null) {
      return -1;
    } else {
      var d = new Date(val);
      return d.getTime();
    }
  }-*/;

  private static native double nativeGetFieldAsDouble(JavaScriptObject rsetObj,
      int fieldIndex) /*-{
    var val = rsetObj.field(fieldIndex);
    return val == null ? 0 : Number(val); 
  }-*/;

  private static native String nativeGetFieldAsString(JavaScriptObject rsetObj,
      int fieldIndex) /*-{
    var val = rsetObj.field(fieldIndex);
    return val == null ? null : String(val);
  }-*/;

  private static native int nativeGetFieldCount(JavaScriptObject rsetObj) /*-{
    return rsetObj.fieldCount();
  }-*/;

  private static native String nativeGetFieldName(JavaScriptObject rsetObj,
      int fieldIndex) /*-{
    return rsetObj.fieldName(fieldIndex);
  }-*/;

  private static native boolean nativeIsValidRow(JavaScriptObject rsetObj) /*-{
    return rsetObj.isValidRow();
  }-*/;

  private static native void nativeNext(JavaScriptObject rsetObj) /*-{
    rsetObj.next();
  }-*/;

  /**
   * Reference to the result set JavaScript object provided by Gears.
   */
  private final JavaScriptObject rsetObj;

  /**
   * Constructs a Database object backed by the provided Gears database object.
   * 
   * @param jsoRSet the object returned from the Gears factory's create method
   */
  ResultSet(JavaScriptObject jsoRSet) {
    rsetObj = jsoRSet;
  }

  /**
   * Closes the result set. Destroys the SQL statement that gave rise to this
   * result set. Calling close() is required. User code must always call close()
   * when it is finished with any ResultSet.
   * 
   * @throws DatabaseException on any error
   */
  public void close() throws DatabaseException {
    try {
      nativeClose(rsetObj);
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
    return (byte) getFieldAsDouble(rsetObj, fieldIndex);
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
      return nativeGetFieldAsChar(rsetObj, fieldIndex);
    } catch (JavaScriptException ex) {
      throw new DatabaseException(ex.getMessage(), ex);
    }
  }

  public Date getFieldAsDate(int fieldIndex) throws DatabaseException {
    try {
      long value = (long) nativeGetFieldAsDate(rsetObj, fieldIndex);
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
    return getFieldAsDouble(rsetObj, fieldIndex);
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
    return (float) getFieldAsDouble(rsetObj, fieldIndex);
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
    return (int) getFieldAsDouble(rsetObj, fieldIndex);
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
    return (long) getFieldAsDouble(rsetObj, fieldIndex);
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
    return (short) getFieldAsDouble(rsetObj, fieldIndex);
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
    return getFieldAsString(rsetObj, fieldIndex);
  }

  /**
   * Returns the number of fields in this result set.
   * 
   * @return the number of fields (columns) in this result set
   */
  public int getFieldCount() {
    return nativeGetFieldCount(rsetObj);
  }

  /**
   * Returns the name of the specified field (column) in this result set.
   * 
   * @param fieldIndex the zero-based index of the desired field
   * @return the name of the field
   */
  public String getFieldName(int fieldIndex) throws DatabaseException {
    try {
      return nativeGetFieldName(rsetObj, fieldIndex);
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
  public boolean isValidRow() {
    return nativeIsValidRow(rsetObj);
  }

  /**
   * Advances to the next row of the results.
   */
  public void next() {
    nativeNext(rsetObj);
  }
}

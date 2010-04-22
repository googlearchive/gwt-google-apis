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

import com.google.gwt.gears.client.Factory;
import com.google.gwt.gears.client.GearsException;
import com.google.gwt.junit.client.GWTTestCase;

import java.util.Date;

/**
 * Test for the {@link ResultSet} class.
 */
public class ResultSetTest extends GWTTestCase {

  private static final String DB_NAME = "ResultSetTest";

  private static ResultSet createDatabase(Database db, String typeName,
      String value) throws DatabaseException {
    ResultSet rs = db.execute("drop table if exists test");
    rs = db.execute("create table test (v " + typeName + ");");
    rs.close();

    rs = db.execute("insert into test values (?)", new String[] {value});
    rs.close();

    return db.execute("select * from test");
  }

  private static void storeAndVerifyDate(Database db, Date value,
      boolean asString) throws DatabaseException {

    String stringValue;
    if (asString) {
      stringValue = value.toString();
    } else {
      stringValue = Long.toString(value.getTime());
    }

    ResultSet rs = createDatabase(db, asString ? "string" : "number",
        stringValue);
    rs.close();
    rs = db.execute("select * from test");
    try {
      Date retrievedDate = rs.getFieldAsDate(0);
      assertEquals(value.toString(), retrievedDate.toString());
      if (!asString) {
        // The dates will not be equal if we stored them as a string
        // because we loose precision in the milliseconds component.
        assertEquals(value, retrievedDate);
      }
    } finally {
      rs.close();
    }
  }

  @Override
  public String getModuleName() {
    return "com.google.gwt.gears.Gears";
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.client.database.ResultSet#close()}.
   *
   * @throws GearsException
   */
  public void testClose() throws GearsException {
    Database db = Factory.getInstance().createDatabase();
    db.open(DB_NAME);
    ResultSet rs = db.execute("create table if not exists Demo (Phrase varchar(255), Timestamp int)");
    rs.close();
    try {
      rs.getFieldAsByte(0);
      fail("Expected DatabaseException");
    } catch (DatabaseException e) {
      // Expected to get here
    } finally {
      db.close();
    }
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.client.database.ResultSet#getFieldAsByte(int)}.
   *
   * @throws GearsException
   */
  public void testGetFieldAsByte() throws GearsException {
    Database db = Factory.getInstance().createDatabase();
    db.open(DB_NAME);
    try {
      byte value = (byte) 220;
      ResultSet rs = createDatabase(db, "number", Byte.toString(value));
      rs.close();
      rs = db.execute("select * from test");
      assertEquals(value, rs.getFieldAsByte(0));
      rs.close();
    } finally {
      db.close();
    }
  }

  /**
   * Test method for {@link
   * com.google.gwt.gears.client.database.ResultSet#getFieldAsChar(int)}.
   *
   * @throws GearsException
   */
  public void testGetFieldAsChar() throws GearsException {
    Database db = Factory.getInstance().createDatabase();
    db.open(DB_NAME);
    try {
      char value = 'A';
      ResultSet rs = createDatabase(db, "number", Character.toString(value));
      assertEquals(value, rs.getFieldAsChar(0));
      rs.close();
    } finally {
      db.close();
    }
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.client.database.ResultSet#getFieldAsDate(int)}.
   *
   * @throws GearsException
   */
  public void testGetFieldAsDate() throws GearsException {
    Database db = Factory.getInstance().createDatabase();
    db.open(DB_NAME);

    try {
      Date value = new Date();
      storeAndVerifyDate(db, value, true);
      storeAndVerifyDate(db, value, false);
    } finally {
      db.close();
    }
  }

  /**
   * Test method for {@link
   * com.google.gwt.gears.client.database.ResultSet#getFieldAsDouble(int)}.
   *
   * @throws GearsException
   */
  public void testGetFieldAsDouble() throws GearsException {
    Database db = Factory.getInstance().createDatabase();
    db.open(DB_NAME);
    try {
      double value = 3.14;
      ResultSet rs = createDatabase(db, "number", Double.toString(value));
      assertEquals(value, rs.getFieldAsDouble(0), 0.0);
      rs.close();
    } finally {
      db.close();
    }
  }

  /**
   * Test method for {@link
   * com.google.gwt.gears.client.database.ResultSet#getFieldAsFloat(int)}.
   *
   * @throws GearsException
   */
  public void testGetFieldAsFloat() throws GearsException {
    Database db = Factory.getInstance().createDatabase();
    db.open(DB_NAME);
    try {
      float value = 3.14F;
      ResultSet rs = createDatabase(db, "number", Double.toString(value));
      assertEquals(value, rs.getFieldAsFloat(0), 0.0);
      rs.close();
    } finally {
      db.close();
    }
  }

  /**
   * Test method for {@link
   * com.google.gwt.gears.client.database.ResultSet#getFieldAsInt(int)}.
   *
   * @throws GearsException
   */
  public void testGetFieldAsInt() throws GearsException {
    Database db = Factory.getInstance().createDatabase();
    db.open(DB_NAME);
    try {
      int value = 42;
      ResultSet rs = createDatabase(db, "number", Integer.toString(value));
      assertEquals(value, rs.getFieldAsInt(0));
      rs.close();
    } finally {
      db.close();
    }
  }

  /**
   * Test method for {@link
   * com.google.gwt.gears.client.database.ResultSet#getFieldAsLong(int)}.
   *
   * @throws GearsException
   */
  public void testGetFieldAsLong() throws GearsException {
    Database db = Factory.getInstance().createDatabase();
    db.open(DB_NAME);
    try {
      long value = 4200;
      ResultSet rs = createDatabase(db, "number", Long.toString(value));
      assertEquals(value, rs.getFieldAsLong(0));
      rs.close();
    } finally {
      db.close();
    }
  }

  /**
   * Test method for {@link
   * com.google.gwt.gears.client.database.ResultSet#getFieldAsShort(int)}.
   *
   * @throws GearsException
   */
  public void testGetFieldAsShort() throws GearsException {
    Database db = Factory.getInstance().createDatabase();
    db.open(DB_NAME);
    try {
      short value = 420;
      ResultSet rs = createDatabase(db, "number", Short.toString(value));
      assertEquals(value, rs.getFieldAsShort(0));
      rs.close();
    } finally {
      db.close();
    }
  }

  /**
   * Test method for {@link
   * com.google.gwt.gears.client.database.ResultSet#getFieldAsString(int)}.
   *
   * @throws GearsException
   */
  public void testGetFieldAsString() throws GearsException {
    Database db = Factory.getInstance().createDatabase();
    db.open(DB_NAME);
    try {
      String value = "foo";
      ResultSet rs = createDatabase(db, "text", value);
      assertEquals(value, rs.getFieldAsString(0));
      rs.close();
    } finally {
      db.close();
    }
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.client.database.ResultSet#getFieldCount()}.
   *
   * @throws GearsException
   */
  public void testGetFieldCount() throws GearsException {
    Database db = Factory.getInstance().createDatabase();
    db.open(DB_NAME);
    try {
      String value = "foo";
      ResultSet rs = createDatabase(db, "text", value);
      assertEquals(1, rs.getFieldCount());
      rs.close();
    } finally {
      db.close();
    }
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.client.database.ResultSet#getFieldName(int)}.
   *
   * @throws GearsException
   */
  public void testGetFieldName() throws GearsException {
    Database db = Factory.getInstance().createDatabase();
    db.open(DB_NAME);
    try {
      int value = 22000;
      ResultSet rs = createDatabase(db, "number", Integer.toString(value));
      rs.close();
      rs = db.execute("select * from test");
      assertTrue(rs.getFieldCount() == 1);
      assertTrue(rs.getFieldName(0).equals("v"));
      rs.close();
    } finally {
      db.close();
    }
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.client.database.ResultSet#isValidRow()} and
   * {@link com.google.gwt.gears.client.database.ResultSet#next()}.
   *
   * @throws GearsException
   */
  public void testNextAndIsValidRow() throws GearsException {
    Database db = Factory.getInstance().createDatabase();
    db.open(DB_NAME);
    try {
      String value = "foo";
      ResultSet rs = createDatabase(db, "text", value);
      assertTrue(rs.isValidRow());
      rs.next();
      assertFalse(rs.isValidRow());
      rs.close();
    } finally {
      db.close();
    }
  }
}

/*
 * Copyright 2007 Google Inc.
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
import com.google.gwt.junit.client.GWTTestCase;

/**
 * Tests for the {@link Database} class.
 */
public class DatabaseTest extends GWTTestCase {

  /**
   * Test method for
   * {@link com.google.gwt.gears.database.client.Database#execute(java.lang.String) execute("")}.
   * 
   * This causes an "uncatchable" exception in firefox using the Gears plugin
   * version 0.3.24
   * 
   * See http://code.google.com/p/gwt-google-apis/issues/detail?id=120
   */
  public void disabledTestExecuteStringEmptyString() {
    try {
      Database db = Factory.getInstance().createDatabase();
      db.execute("");
      fail("Expected a DatabaseException");
    } catch (DatabaseException e) {
      // Expected to get here
    }
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.client.database.Database#execute(java.lang.String) execute(null)}.
   * 
   * @throws DatabaseException
   */
  public void disabledTestExecuteStringNull() throws DatabaseException {
    try {
      Database db = Factory.getInstance().createDatabase();
      db.open();
      db.execute(null);
      fail("Expected GearsException");
    } catch (NullPointerException e) {
      // Expected to get here
    }
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.database.client.Database#execute(java.lang.String, java.lang.String[]) execute("SQL Statement", new String[]{})}.
   * 
   * This causes an "uncatchable" exception in firefox using the Gears plugin
   * version 0.3.24
   * 
   * See http://code.google.com/p/gwt-google-apis/issues/detail?id=120
   */
  public void disabledTestExecuteStringStringArrayEmptyArray() {
    try {
      Database db = Factory.getInstance().createDatabase();
      ResultSet rs = db.execute("create table if not exists Demo (Phrase varchar(255), Timestamp int)");
      rs.close();
      db.execute("insert into Demo values (?, ?)", new String[] {});
      fail("Expected a DatabaseException");
    } catch (DatabaseException e) {
      // Expected to get here
    }
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.database.client.Database#execute(java.lang.String, java.lang.String[]) execute("SQL Statement ?, ?", new String[]{})}.
   * 
   * This causes an "uncatchable" exception in firefox using the Gears plugin
   * version 0.3.24
   * 
   * See http://code.google.com/p/gwt-google-apis/issues/detail?id=120
   * 
   * @throws DatabaseException
   */
  public void disableTestExecuteWithInsuficientArgs() {
    try {
      Database db = Factory.getInstance().createDatabase();
      db.open();
      ResultSet rs = db.execute("create table if not exists Demo (Phrase varchar(255), Timestamp int)");
      rs.close();
      db.execute("insert into Demo values (?, ?)");
      fail("Expected a DatabaseException");
    } catch (DatabaseException ex) {
      // Expected to get here
    }
  }

  @Override
  public String getModuleName() {
    return "com.google.gwt.gears.Gears";
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.database.client.Database#close()}.
   * 
   * @throws DatabaseException
   */
  public void testClose() throws DatabaseException {
    Database db = Factory.getInstance().createDatabase();
    db.close();
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.database.client.Database#execute(java.lang.String) execute("create table if not exists Demo (Phrase varchar(255), Timestamp int)")}.
   * 
   * @throws DatabaseException
   */
  public void testExecuteString() throws DatabaseException {
    Database db = Factory.getInstance().createDatabase();
    db.open();
    ResultSet rs = db.execute("create table if not exists Demo (Phrase varchar(255), Timestamp int)");
    rs.close();
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.database.client.Database#getLastInsertRowId()}.
   * 
   * @throws DatabaseException
   */
  public void testGetLastInsertRowId() throws DatabaseException {
    Database db = Factory.getInstance().createDatabase();
    db.open();
    db.execute("create table if not exists Demo (Phrase varchar(255), Timestamp int)");
    db.execute("insert into Demo values (?, ?)", new String[] {
        "Hello", Integer.toString((int) System.currentTimeMillis())});
    int lastInsertRowId = db.getLastInsertRowId();
    db.execute("insert into Demo values (?, ?)", new String[] {
        "Hello", Integer.toString((int) System.currentTimeMillis() + 1)});
    assertTrue(lastInsertRowId < db.getLastInsertRowId());
  }

  public void testGetRowsAffected() throws DatabaseException {
    Database db = Factory.getInstance().createDatabase();
    db.open();
    db.execute("create table if not exists Demo (Phrase varchar(255), Timestamp int)");
    db.execute("insert into Demo values (?, ?)", new String[] {
        "Hello", Integer.toString((int) System.currentTimeMillis())});
    assertEquals(1, db.getRowsAffected());
  }
}

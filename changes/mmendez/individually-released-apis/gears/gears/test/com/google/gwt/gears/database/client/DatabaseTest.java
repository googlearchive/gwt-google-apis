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

package com.google.gwt.gears.database.client;

import com.google.gwt.gears.core.client.GearsException;
import com.google.gwt.junit.client.GWTTestCase;

/**
 * Tests for the {@link Database} class.
 */
public class DatabaseTest extends GWTTestCase {

  public String getModuleName() {
    return "com.google.gwt.gears.Gears";
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.database.client.Database#close()}.
   */
  public void testClose() {
    try {
      Database db = new Database();
      db.close();
    } catch (GearsException e) {
      fail(e.getMessage());
    }
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.database.client.Database#Database()}.
   */
  public void testDatabase() {
    try {
      new Database();
    } catch (GearsException e) {
      fail(e.getMessage());
    }
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.database.client.Database#execute(java.lang.String) execute("create table if not exists Demo (Phrase varchar(255), Timestamp int)")}.
   */
  public void testExecuteString() {
    try {
      Database db = new Database();
      ResultSet rs = db.execute("create table if not exists Demo (Phrase varchar(255), Timestamp int)");
      rs.close();
    } catch (GearsException e) {
      fail(e.getMessage());
    }
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.database.client.Database#execute(java.lang.String) execute("")}.
   */
  public void testExecuteStringEmptyString() {
    try {
      Database db = new Database();
      db.execute("");
      fail("Expected a GearsException");
    } catch (GearsException e) {
      // Expected to get here
    }
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.database.client.Database#execute(java.lang.String) execute(null)}.
   */
  public void testExecuteStringNull() {
    try {
      Database db = new Database();
      db.execute(null);
      fail("Expected GearsException");
    } catch (NullPointerException e) {
      // Expected to get here
    } catch (GearsException e) {
      fail(e.getMessage());
    }
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.database.client.Database#execute(java.lang.String, java.lang.String[]) execute("SQL Statement", new String[]{})}.
   */
  public void testExecuteStringStringArrayEmptyArray() {
    try {
      Database db = new Database();
      ResultSet rs = db.execute("create table if not exists Demo (Phrase varchar(255), Timestamp int)");
      rs.close();
      db.execute("insert into Demo values (?, ?)", new String[] {});
      fail("Expected GearsException");
    } catch (GearsException e) {
      // Expected to get here
    }
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.database.client.Database#execute(java.lang.String, java.lang.String[]) execute("SQL Statement", null)}.
   */
  public void testExecuteStringStringArrayNullArray() {
    try {
      Database db = new Database();
      ResultSet rs = db.execute("create table if not exists Demo (Phrase varchar(255), Timestamp int)");
      rs.close();
      db.execute("insert into Demo values (?, ?)", null);
    } catch (NullPointerException e) {
      // Expected to get here
    } catch (GearsException e) {
      fail(e.getMessage());
    }
  }

  /**
   * Test method for
   * {@link com.google.gwt.gears.database.client.Database#getLastInsertRowId()}.
   */
  public void testGetLastInsertRowId() {
    try {
      Database db = new Database();
      db.execute("create table if not exists Demo (Phrase varchar(255), Timestamp int)");
      db.execute("insert into Demo values (?, ?)", new String[] {
          "Hello", Integer.toString((int) System.currentTimeMillis())});
      int lastInsertRowId = db.getLastInsertRowId();
      db.execute("insert into Demo values (?, ?)", new String[] {
          "Hello", Integer.toString((int) System.currentTimeMillis() + 1)});
      assertTrue(lastInsertRowId < db.getLastInsertRowId());
    } catch (GearsException e) {
      fail(e.getMessage());
    }
  }
}

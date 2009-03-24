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
import com.google.gwt.gears.client.impl.Utils;

/**
 * An in-browser SQL database. Gears provides a SQL database using SQLite
 * syntax. For details, see the <a href="http://www.sqlite.org/lang.html">SQLite
 * syntax.</a>
 * 
 * Note that this class (and its related classes) intentionally do NOT implement
 * the JDBC interface, since the database provided by Gears does necessarily
 * implement all those semantics. It may be possible to add a JDBC layer on top
 * of this, but it's unclear whether that would really be useful.
 */
public final class Database extends JavaScriptObject {
  protected Database() {
    // Required for overlay types
  }

  /**
   * Closes the database connection, if any, currently associated with this
   * Database instance. Calling Database.close() is not required.
   * 
   * @throws DatabaseException if an error occurs
   */
  public void close() throws DatabaseException {
    try {
      uncheckedClose();
    } catch (JavaScriptException ex) {
      throw new DatabaseException(ex.getDescription(), ex);
    }
  }

  /**
   * Executes the specified SQL statement and returns a {@link ResultSet}
   * containing the results.
   * 
   * Substitute zero or more bind parameters from <code>args</code> into
   * <code>sqlStatement</code> and execute the resulting SQL statement. There
   * must be exactly as many items in <code>args</code> as there are ? place
   * holders in <code>sqlStatement</code>. <code>args</code> can be omitted if
   * there are no place holders. The results of executing the statement are
   * returned in a ResultSet.
   * 
   * Note that if multiple processes (including Workers) attempt to write to the
   * database at the same time, one can fail. It is up to the application to
   * retry in these situations.
   * 
   * @param sqlStatement SQL statement to execute; may use '?' place holders
   * @param args values for the place holders in the <code>sqlStatement</code>
   * @return {@link ResultSet} associated with the SQL statement
   * @throws DatabaseException if the SQL statement fails or if multiple Workers
   *           attempt to write to the database at the same time
   */
  public ResultSet execute(String sqlStatement, String... args)
      throws DatabaseException {
    try {
      return execute(sqlStatement, Utils.toJavaScriptArray(args));
    } catch (JavaScriptException ex) {
      throw new DatabaseException(ex.getDescription(), ex);
    }
  }

  /**
   * Returns the ID of the last row inserted into a table. This is a global
   * counter, and is unique over all rows in all tables.
   * 
   * @return the ID of the last row inserted, or 0 if no rows have been inserted
   *         on this connection
   */
  public native int getLastInsertRowId() /*-{
    return this.lastInsertRowId;
  }-*/;

  /**
   * Returns the number of database rows that were changed, inserted, or deleted
   * by the most recently completed INSERT, UPDATE, or DELETE statement on this
   * Database instance.
   * 
   * Note that an unconstrained delete of all rows in a table (DELETE FROM
   * table) will return zero rather than the number of rows that were originally
   * present in the table; if you need the number of rows, use DELETE FROM table
   * WHERE 1 instead, though be aware that this is slower than an unconstrained
   * delete.
   * 
   * @return the number of database rows impacted by the last INSERT, UPDATE or
   *         * DELETE statement on this Database instance
   */
  public native int getRowsAffected() /*-{
    return this.rowsAffected;
  }-*/;

  /**
   * Opens an unnamed database.
   */
  public native void open() /*-{
    this.open();
  }-*/;

  /**
   * Opens a database with the specified <code>name</code>. Note that this name
   * is local to the application's origin.
   * 
   * @param name name of the database
   */
  public native void open(String name) /*-{
    this.open(name);
  }-*/;

  /**
   * Completely deletes the currently opened database. Closes the database first
   * if necessary.
   */
  public void remove() throws DatabaseException {
    try {
      uncheckedRemove();
    } catch (JavaScriptException ex) {
      throw new DatabaseException(ex.getDescription(), ex);
    }
  }

  private native ResultSet execute(String sqlStatement, JavaScriptObject args) /*-{
    return this.execute(sqlStatement, args);
  }-*/;

  private native void uncheckedClose() /*-{
    this.close();
  }-*/;

  private native void uncheckedRemove() /*-{
    this.remove();
  }-*/;
}
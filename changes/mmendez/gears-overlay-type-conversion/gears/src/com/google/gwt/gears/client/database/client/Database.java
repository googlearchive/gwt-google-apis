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
package com.google.gwt.gears.client.database.client;

import com.google.gwt.core.client.JavaScriptException;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.gears.core.client.Gears;
import com.google.gwt.gears.core.client.GearsException;
import com.google.gwt.gears.core.client.impl.GearsImpl;

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
public class Database {

  private static ResultSet execute(JavaScriptObject jso, String statement,
      String[] args) throws DatabaseException {
    try {
      return new ResultSet(nativeExecute(jso, statement, args));
    } catch (JavaScriptException ex) {
      throw new DatabaseException(ex.getMessage(), ex);
    }
  }

  /**
   * Native proxy call to the close method on the JavaScript object.
   */
  private static native void nativeClose(JavaScriptObject database) /*-{
    return database.close();
  }-*/;

  /**
   * Native proxy call to the execute method on the JavaScript object.
   * 
   * @param statement the SQL statement to execute
   * @param args arguments to expand, or null if there are none
   * @return the JavaScript object corresponding to a ResultSet
   */
  private static native JavaScriptObject nativeExecute(
      JavaScriptObject database, String statement, String[] args) /*-{
    if (args == null) {
      return database.execute(statement);
    } else {
      var newArgs = @com.google.gwt.gears.core.client.impl.GearsImpl::convertToJavaScript([Ljava/lang/String;)(args);
      return database.execute(statement, newArgs);
    }
  }-*/;

  private static native int nativeGetLastInsertRowId(JavaScriptObject database) /*-{
    return database.lastInsertRowId;
  }-*/;

  /**
   * Native proxy call to the open method on the JavaScript object.
   * 
   * @param name the name to open, or null
   */
  private static native void nativeOpen(JavaScriptObject database, String name) /*-{
    if (name == null) {
      return database.open();
    } else {
      return database.open(name);
    }
  }-*/;

  /**
   * Reference to the database JavaScript object provided by Gears.
   */
  private final JavaScriptObject dbObj;

  /**
   * Constructs an opened <i>Database</i> object.
   * 
   * @throws GearsException if the Database could not be created
   */
  public Database() throws GearsException {
    this(null, Gears.DATABASE, Gears.GEARS_VERSION);
  }

  /**
   * Constructs an opened <i>Database</i> object with the specified name.
   * Passing <code>null</code> for the <code>databaseName</code> is
   * equivalent to {@link #Database()}.
   * 
   * @param databaseName the name of the database or <code>null</code> for an
   *          unnamed database
   * 
   * @throws GearsException if the Database could not be created
   */
  public Database(String databaseName) throws GearsException {
    this(databaseName, Gears.DATABASE, Gears.GEARS_VERSION);
  }

  /**
   * Constructs an opened <code>Database</code> instance using the specified
   * database name, class name and class version.
   * 
   * @param databaseName the name of the database if not <code>null</code>
   * @param className requested class name
   * @param classVersion requested API version
   * @throws GearsException if the requested version of the class is not
   *           available
   * @throws NullPointerException if the <code>className</code> or the
   *           <code>classVersion</code> are <code>null</code>.
   */
  protected Database(String databaseName, String className, String classVersion)
      throws GearsException {
    if (className == null || classVersion == null) {
      throw new NullPointerException();
    }
    dbObj = GearsImpl.create(className, classVersion);

    try {
      nativeOpen(dbObj, databaseName);
    } catch (JavaScriptException ex) {
      throw new DatabaseException(ex.getMessage(), ex);
    }
  }

  /**
   * Closes the currently-opened database table space.
   * 
   * @throws DatabaseException on any error
   */
  public void close() throws DatabaseException {
    try {
      nativeClose(dbObj);
    } catch (JavaScriptException ex) {
      throw new DatabaseException(ex.getMessage(), ex);
    }
  }

  /**
   * Executes the indicated SQL statement, and returns the results. Note that
   * the {@link ResultSet} returned must be closed when it is no longer needed.
   * 
   * @param statement the SQL statement to run
   * @return a new ResultSet containing the results; never null
   * @throws DatabaseException if the statement failed to execute
   * @throws NullPointerException if <code>statement</code> is
   *           <code>null</code>
   */
  public ResultSet execute(String statement) throws DatabaseException {
    if (statement == null) {
      throw new NullPointerException();
    }

    return execute(dbObj, statement, null);
  }

  /**
   * Executes the indicated SQL statement, and returns the results. The
   * <code>args</code> list may contain values to insert into the SQL
   * statement. Each "?" character in <code>statement</code> will be replaced
   * with the corresponding entry in <code>args</code>. Note that the
   * {@link ResultSet} returned must be closed when it is no longer needed.
   * 
   * @param statement the SQL statement to run
   * @param args a list of Strings to be inserted into the query template
   * @return a new ResultSet containing the results; never null
   * @throws DatabaseException if the statement failed to execute
   * @throws NullPointerException if either <code>statement</code> or
   *           <code>args</code> are <code>null</code>
   */
  public ResultSet execute(String statement, String[] args)
      throws DatabaseException {
    if (statement == null || args == null) {
      throw new NullPointerException();
    }

    return execute(dbObj, statement, args);
  }

  /**
   * Returns the ID of the last row inserted into a table. This is a global
   * counter, and is unique over all rows in all tables.
   * 
   * @return the ID of the last row inserted, or 0 if no rows have been inserted
   *         on this connection
   */
  public int getLastInsertRowId() {
    return nativeGetLastInsertRowId(dbObj);
  }

  /**
   * Returns the JavaScript <code>Database</code> object wrapped by this
   * instance.
   * 
   * @return the JavaScript <code>Database</code> object wrapped by this
   *         instance
   */
  protected final JavaScriptObject getJavaScriptObject() {
    return dbObj;
  }
}

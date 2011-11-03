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
package com.google.gwt.gears.sample.gwtnote.client.local;

import com.google.gwt.core.client.GWT;
import com.google.gwt.gears.client.Factory;
import com.google.gwt.gears.client.GearsException;
import com.google.gwt.gears.client.database.Database;
import com.google.gwt.gears.client.database.DatabaseException;
import com.google.gwt.gears.client.database.ResultSet;
import com.google.gwt.gears.client.localserver.LocalServer;
import com.google.gwt.gears.client.localserver.ManagedResourceStore;
import com.google.gwt.gears.sample.gwtnote.client.rpc.Note;
import com.google.gwt.user.client.Timer;

import java.util.ArrayList;

/**
 * Provides helper functions for accessing the Gears database. Used to
 * encapsulate all DB-related operations.
 */
public class GearsHelper {
  private static final String DB_UPDATE = "update notes set version=?, content=? where noteid=?";

  private static final String DB_INSERT = "insert into notes values (?, ?, ?)";

  private static final String DB_FETCH_TEXT = "select * from notes";

  private static final String DB_FETCH_NAMES = "select noteid from notes";

  private static final String DB_EXISTS = "select noteid from notes where noteid = ?";

  private static final String DB_CREATE = "create table notes ("
      + "noteid text not null primary key, version text not null, "
      + "content text not null)";

  protected boolean gearsReady;

  private Database db = null;

  private LocalServer localServer;

  private ManagedResourceStore store;

  /**
   * Creates a new GearsHelper.
   */
  public GearsHelper() {
    try {
      db = Factory.getInstance().createDatabase();
      try {
        db.execute(DB_FETCH_NAMES);
      } catch (GearsException ex) {
        db.execute(DB_CREATE);
      }

      // initialize the localstore and have it update the manifest
      localServer = Factory.getInstance().createLocalServer();
      store = localServer.createManagedStore("GWTGearsNote");
      store.setManifestUrl(GWT.getModuleBaseURL() + "manifest");
      store.checkForUpdate();
      Timer t = new Timer() {
        @Override
        public void run() {
          int status = store.getUpdateStatus();
          if (status == ManagedResourceStore.UPDATE_OK) {
            this.cancel();
          } else if (status == ManagedResourceStore.UPDATE_FAILED) {
            this.cancel();
          }
        }
      };
      t.scheduleRepeating(100);
    } catch (Throwable t) { // not just GearsException b/c we can also have NPEs
      localServer = null;
      store = null;
      db = null;
    }
  }

  /**
   * Checks whether Gears is present and enabled on the current browser.
   * 
   * @return true if Gears is present and enabled, false otherwise
   */
  public boolean gearsEnabled() {
    return (db != null);
  }

  /**
   * Unmarshals all rows in the database into an array of {@link Note}
   * instances.
   * 
   * @return a list of all notes in the DB
   */
  public Note[] getNotes() {
    if (db == null) {
      return null;
    }

    ResultSet rs = null;
    ArrayList<Note> al = new ArrayList<Note>();
    try {
      rs = db.execute(DB_FETCH_TEXT);
      while (rs.isValidRow()) {
        Note nd = new Note(rs.getFieldAsString(0), rs.getFieldAsString(1),
            rs.getFieldAsString(2));
        al.add(nd);
        rs.next();
      }
    } catch (DatabaseException e) {
      return null;
    }

    Note[] notes = new Note[al.size()];
    for (int i = 0; i < al.size(); ++i) {
      notes[i] = al.get(i);
    }
    return notes;
  }

  /**
   * Marshals the indicated note to the database. This amounts to updating the
   * row with the data as provided.
   * 
   * If <code>dirty</code> is true, the row will be marked as dirty. Note that
   * even if the argument is NOT true, the row may still be dirty -- that is,
   * <code>dirty</code> specifies whether the current operation makes the row
   * dirty, not the absolute state of the row.
   * 
   * @param n the note to update
   */
  public void updateNote(Note n) {
    if (db == null) {
      return;
    }

    String name = n.getName();
    String data = n.getText();
    String vers = n.getVersion();
    String[] args = null;

    ResultSet rs = null;
    try {
      // test whether the row exists or not -- controls behavior below
      args = new String[] {name};
      rs = db.execute(DB_EXISTS, args);
    } catch (DatabaseException e1) {
      return;
    }

    // if row exists update it; if not, create a new one
    if (rs.isValidRow()) {
      args = new String[] {vers, data, name};
      try {
        db.execute(DB_UPDATE, args);
      } catch (DatabaseException e) {
        return;
      }
    } else {
      args = new String[] {name, vers, data};
      try {
        db.execute(DB_INSERT, args);
      } catch (DatabaseException e) {
        return;
      }
    }
  }
}

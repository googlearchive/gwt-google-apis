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
package com.google.gwt.gears.sample.gwtnote.server;

import com.google.gwt.gears.sample.gwtnote.client.rpc.Note;
import com.google.gwt.gears.sample.gwtnote.client.rpc.NoteService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Server-side implementation of the {@link NoteService} interface.
 * 
 * This class is stateless. However, it also has no backing store, meaning that
 * if the server is restarted, the data and version all reset to defaults. Since
 * the server side is implicitly authoritative, a server restart will also
 * effectively wipe out any data on clients (unless the user overrides.) In a
 * real-world implementation, this servlet would have a real backing store such
 * as a database.
 * 
 * Similarly, this class has no user authentication, where a real-world
 * implementation probably would.
 * 
 * This class implements optimistic concurrency, in that it only accepts a data
 * update if the new data is reported along with the server's current version.
 * That is, if a client is not up to date and reports an old/non-current
 * version, the server rejects the update and requires the client to fetch the
 * new version before overwriting.
 */
public class NoteServiceImpl extends RemoteServiceServlet implements
    NoteService {

  /**
   * Fetches a list of all notes known to the server.
   * 
   * @return all notes on the server; may be null on error, but will never be
   *         empty
   */
  public Note[] getNotes() {
    Map<String,Note> data = getDataMap();
    Note[] notes = new Note[data.size()];
    Iterator<Note> it = data.values().iterator();
    for (int i = 0; it.hasNext(); ++i) {
      notes[i] = it.next();
    }
    return notes;
  }

  /**
   * Instructs the server to update its state with all the indicated notes. Many
   * clients may call this method; the server takes the union of all unique
   * names that it sees, from all clients. If the notes' versions are up to
   * date, they will replace any copies on the server.
   * 
   * See class description for summary of optimistic concurrency.
   * 
   * @param notes
   *          the list of notes to create or (attempt to) overwrite
   */
  public void setNotes(Note[] notes) {
    if (notes == null) {
      return;
    }
    Map<String,Note> data = getDataMap();
    for (int i = 0; i < notes.length; ++i) {
      if (data.containsKey(notes[i].getName())) {
        Note nd = data.get(notes[i].getName());
        int newVer = Integer.parseInt(notes[i].getVersion());
        int oldVer = Integer.parseInt(nd.getVersion());
        if (newVer >= oldVer) {
          if (!nd.getText().equals(notes[i].getText())) {
            nd.setText(notes[i].getText());
            nd.setVersion("" + ((newVer > oldVer ? newVer : oldVer) + 1));
          }
        }
      } else {
        data.put(notes[i].getName(), notes[i]);
      }
    }
  }

  /**
   * Fetches all the note records known to the server.
   * 
   * @return a Map containing all data known to the server
   */
  protected Map<String,Note> getDataMap() {
    Map<String,Note> m = (Map<String,Note>) getServletContext().getAttribute("com.google.gearsdemo.data");
    if (m == null) {
      m = new HashMap<String,Note>();
      m.put("default", new Note("default", "1", ""));
      getServletContext().setAttribute("com.google.gearsdemo.data", m);
    }
    return m;
  }
}

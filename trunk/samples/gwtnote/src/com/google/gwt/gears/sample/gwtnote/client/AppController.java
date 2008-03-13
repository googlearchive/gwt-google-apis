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
package com.google.gwt.gears.sample.gwtnote.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.gears.sample.gwtnote.client.local.GearsHelper;
import com.google.gwt.gears.sample.gwtnote.client.rpc.Note;
import com.google.gwt.gears.sample.gwtnote.client.rpc.NoteService;
import com.google.gwt.gears.sample.gwtnote.client.rpc.NoteServiceAsync;
import com.google.gwt.gears.sample.gwtnote.client.ui.RichTextWidget;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.Widget;

import java.util.HashMap;
import java.util.Iterator;

/**
 * A controller class that manages UI and data-synchronization events. This is
 * the core component of the GWT GearsNote application.
 */
public class AppController {

  protected static final String REPLACE_CONF_TEXT = "Someone has changed the note you are viewing. Keep your copy?";

  private static final int TICKS_PER_RPC = 100;

  protected boolean ready = false;

  protected boolean localDirty = false;

  private RichTextWidget rtw = null;

  private GearsHelper gears = null;

  private NoteServiceAsync rpc = null;

  private HashMap<String,Note> noteData = new HashMap<String,Note>();

  private int rpcCntdown = TICKS_PER_RPC;

  private Object lastData = "";

  /**
   * Default constructor.
   */
  public AppController() {
    // set up RPC handles
    rpc = GWT.create(NoteService.class);
    String rpcUrl = GWT.getModuleBaseURL() + "rpc";
    ((ServiceDefTarget) rpc).setServiceEntryPoint(rpcUrl);

    // create a database helper
    gears = new GearsHelper();
  }

  /**
   * Tells the controller which widget instance to use to monitor and update for
   * UI changes.
   * 
   * @param mainPanel
   *          the widget to use
   */
  public void setWidget(RichTextWidget mainPanel) {
    this.rtw = mainPanel;
    rtw.addNameChangeListener(new ChangeListener() {
      public void onChange(Widget sender) {
        String newName = rtw.getName();
        if (noteData.containsKey(newName)) {
          Note n = noteData.get(newName);
          if (!rtw.getHTML().equals(n.getText())) {
            rtw.setHTML(n.getText());
          }
        }
      }
    });
  }

  /**
   * Kicks off the main processing loop. That loop is a timer that fires every
   * 10 milliseconds, forever. It calls {@link #update()} which implements the
   * sync logic, and may or may not actually do anything for a give timer tick.
   * (For instance, it may be stalled waiting on an asynchronous RPC call to
   * return.)
   */
  public void startMainLoop() {
    init();

    Timer t = new Timer() {
      @Override
      public void run() {
        if (!ready) {
          return;
        }
        update();
      }
    };
    t.scheduleRepeating(10);
  }

  /**
   * General initialization.
   */
  protected void init() {
    ready = false;
    noteData.clear(); // ditto
    syncFromGears(true);
    syncToServer(true);
    
    // init the dirty-testing code
    localDirty = false;
    Note def = noteData.get("default");
    if (def != null) {
      lastData = def.getText();
    }
  }

  /**
   * Fallback-mode initialization. This method is called when the attempt to
   * fetch data from the RPC server on startup fails; this fallback method
   * fetches similar data from the local database.
   * 
   * @param isInit
   *          instructs this method that the attempt to init over RPC previously
   *          failed, which causes this method to take additional actions to
   *          prevent data loss when the server comes back online
   */
  protected void syncFromGears(boolean isInit) {
    if (!gears.gearsEnabled()) {
      return;
    }

    Note[] notes = gears.getNotes();
    Note n = null;
    if (notes != null && notes.length > 0) {
      for (int i = 0; i < notes.length; ++i) {
        n = notes[i];
        if (!noteData.containsKey(n.getName())) {
          n = notes[i];
          noteData.put(n.getName(), n);
        }
      }
    }
    if (isInit) {
      n = noteData.get("default");
      if (n != null) {
        rtw.setHTML(n.getText());
      }
    }
  }

  /**
   * Synchronizes the local application's state with the server's data. The
   * <code>gearsFallback</code> parameter should generally only be set to true
   * during first-time initialization.
   * 
   * @param isInit
   *          if true, indicates that a failure to contact server should result
   *          in an attempt to sync from the Gears database
   */
  protected void syncFromServer(final boolean isInit) {
    rpc.getNotes(new AsyncCallback<Note[]>() {
      public void onFailure(Throwable caught) {
        ready = true;
      }

      public void onSuccess(Note[] notes) {
        if (notes == null) {
          ready = true;
          return;
        }

        // process the results and figure out if we need to update our state
        Note n = null;
        for (int i = 0; i < notes.length; ++i) {
          n = notes[i];

          // server sent us a totally new record -- just store it
          if (!noteData.containsKey(n.getName())) {
            noteData.put(n.getName(), n);
            gears.updateNote(n);
            continue;
          }

          // record exists -- check if server version is more recent & handle it
          Note current = noteData.get(n.getName());
          if (!current.getVersion().equals(n.getVersion())) {
            current.setVersion(n.getVersion());
            if (current.getText().equals(n.getText())) {
              // versions don't match but text is same anyway
              gears.updateNote(current); // to update version...
              localDirty = false;
              lastData = current.getText();
            } else if (current.getName().equals(rtw.getName()) && localDirty
                && Window.confirm(REPLACE_CONF_TEXT)) {
              // if versions don't match, ask user for permission to override
              gears.updateNote(current); // to update version...
              // we are proceeding w/ local data, so don't touch UI
            } else {
              // user rejected override, or else was not current note
              current.setText(n.getText());
              gears.updateNote(current);
              
              localDirty = false;
              lastData = current.getText();

              // don't forget to update UI state...
              if (rtw.getName().equals(current.getName())) {
                rtw.setHTML(current.getText());
              }
            }
            continue;
          }
        }

        // in the special case of startup, check for default value
        if (isInit) {
          Note def = noteData.get("default");
          if (def != null) {
            rtw.setHTML(def.getText());
          }
        }
        ready = true;
      }
    });
  }

  /**
   * Uploads the current set of dirty (modified) notes to the server, and upon
   * acknowledgment of that, fetches the server's set of data.
   */
  protected void syncToServer(final boolean isInit) {
    // temporarily stall the main timer loop until we're done
    ready = false;

    // convert our Map of notes into an array
    Note[] notes = new Note[noteData.size()];
    Iterator<Note> it = noteData.values().iterator();
    for (int i = 0; it.hasNext(); ++i) {
      notes[i] = it.next();
    }

    // upload our current data
    rpc.setNotes(notes, new AsyncCallback<Void>() {
      public void onFailure(Throwable caught) {
        // next call is also likely to fail, so don't bother to try
        if (isInit) {
          Note def = noteData.get("default");
          if (def != null) {
            rtw.setHeight(def.getText());
          }
        }
        ready = true;
      }

      public void onSuccess(Void result) {
        // it worked: now request server's data
        syncFromServer(isInit); // releases 'ready' later
      }
    });
  }

  /**
   * Core execution routine. Called once per timer tick.
   */
  protected void update() {
    syncFromGears(false);
    if (rpcCntdown == 0) {
      rpcCntdown = TICKS_PER_RPC;
      syncToServer(false);
    }
    rpcCntdown -= 1;
    updateUIState();
  }

  /**
   * Synchronizes the user interface state with the in-memory data model. Note
   * that this goes both ways: it updates the UI if new data is present, and
   * also updates the data if the user has entered or changed data.
   */
  protected void updateUIState() {
    // extract data from the UI
    String curName = rtw.getName();
    String curData = rtw.getHTML();
    curData = (curData == null) ? "" : curData;
    curName = (curName == null) ? "" : curName;
    
    if (!curData.equals(lastData)) {
      localDirty = true;
      lastData = curData;
    }

    if (noteData.containsKey(curName)) {
      // fetch the latest data for the note the user is trying to look at
      Note n = noteData.get(curName);
      if (!n.getText().equals(curData)) {
        // if the UI doesn't currently show latest data, update it
        n.setText(curData);
        gears.updateNote(n);
      }
    } else {
      // if the user has created a new record (unknown name) just store it
      Note n = new Note(curName, "1", curData);
      noteData.put(curName, n);
      gears.updateNote(n);
    }

    // add all the notes to the options list
    Iterator<String> it = noteData.keySet().iterator();
    String[] names = new String[noteData.size()];
    for (int i = 0; it.hasNext(); ++i) {
      names[i] = it.next();
    }
    rtw.setNameOptions(names);
  }
}

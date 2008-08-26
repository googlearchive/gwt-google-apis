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
package com.google.gwt.gears.sample.gwtnote.client.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Functional specification for an RPC service providing simple data fetch and
 * store operations.
 * 
 * This is an asynchronous GWT RPC interface corresponding to
 * {@link NoteService}.
 */
public interface NoteServiceAsync {
  /**
   * Fetches a list of all notes available on the server.
   * 
   * @param callback the callback to notify when the request is complete
   */
  void getNotes(AsyncCallback<Note[]> callback);

  /**
   * Updates the server with some potentially changed note data.
   *
   * @param notes an array of Note that need to be updated
   * @param callback the callback to notify when the request is complete
   */
  void setNotes(Note[] notes, AsyncCallback<Void> callback);
}

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

import com.google.gwt.user.client.rpc.RemoteService;

/**
 * Functional specification for an RPC service providing simple data fetch and
 * store operations.
 */
public interface NoteService extends RemoteService {
  /**
   * Fetches a list of all notes available on the server.
   * 
   * @return a List of Note objects
   */
  Note[] getNotes();

  /**
   * Updates the server with some potentially changed note data.
   *
   * @param notes an array of Note that need to be updated
   */
  void setNotes(Note[] notes);
}

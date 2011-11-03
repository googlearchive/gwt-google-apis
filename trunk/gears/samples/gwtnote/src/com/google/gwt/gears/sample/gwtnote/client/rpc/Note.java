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

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Courier object for note data sent to and from the server.
 */
public class Note implements IsSerializable {
  private String text = null;

  private String version = null;

  private String name = null;

  /**
   * Constructs an empty Note. Be aware that most classes will consider an empty
   * note to be in an erroneous state. Prefer the
   * {@link #Note(String, String, String)} constructor.
   */
  public Note() {
  }

  /**
   * Constructs a note initialized with the indicated version and text.
   * 
   * @param name
   *          the unique ID of the note
   * @param version
   *          the version
   * @param text
   *          the data
   */
  public Note(String name, String version, String text) {
    this.version = version;
    this.text = text;
    this.name = name;
  }

  /**
   * Fetches the name of the note.
   * 
   * @return the name (unique ID) of the note
   */
  public String getName() {
    return name;
  }

  /**
   * Fetches the text of the note.
   * 
   * @return the text data
   */
  public String getText() {
    return text;
  }

  /**
   * Fetches the version of the note. Used for versioning notes as part of
   * optimistic concurrency. See
   * {@link com.google.gwt.gears.sample.gwtnote.server.NoteServiceImpl} for more details.
   * 
   * @return the version of the note
   */
  public String getVersion() {
    return version;
  }

  /**
   * Sets the name to the specified value.
   * 
   * @param name
   *          the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Sets the text to the specified data.
   * 
   * @param text
   *          the data to store
   */
  public void setText(String text) {
    this.text = text;
  }

  /**
   * Sets the version to the specified value.
   * 
   * @param version
   *          the version to store
   */
  public void setVersion(String version) {
    this.version = version;
  }

  /**
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "" + this.name + ", version " + this.version + "; " + this.text;
  }
}

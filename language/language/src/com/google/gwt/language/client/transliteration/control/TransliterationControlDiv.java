/*
 * Copyright 2009 Google Inc.
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
package com.google.gwt.language.client.transliteration.control;

import com.google.gwt.user.client.ui.Label;

/**
 * Transliteration control DIV element to which transliteration control should
 * be attached in normal mode of operation.
 */
public class TransliterationControlDiv extends Label {

  /**
   * We need an id because underlying JS API relies  on id of the div element
   * to attach control.
   *
   * @param id id of div element
   */
  public TransliterationControlDiv(String id) {
    if (id == null || id.trim().length() == 0) {
      throw new RuntimeException("Id cannot be null or blank");
    }
    this.getElement().setId(id);
  }

  /**
   * Return id of the div.
   *
   * @return id of div
   */
  public String getId() {
    return this.getElement().getId();
  }
}

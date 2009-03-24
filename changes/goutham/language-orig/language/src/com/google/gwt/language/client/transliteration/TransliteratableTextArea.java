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
package com.google.gwt.language.client.transliteration;

import com.google.gwt.user.client.ui.TextArea;

/**
 * Textarea that is transliteratable.
 */
public class TransliteratableTextArea extends TextArea {

  /**
   * @param id id for the textarea. This is required because transliteration JS
   *          API refer the ids to create transliteratable textarea controls.
   */
  public TransliteratableTextArea(String id) {
    if (id == null || id.trim().length() == 0) {
      throw new RuntimeException("Id should not be null or blank");
    }
    this.getElement().setId(id);
  }

  /**
   * Get id associated with this textarea.
   *
   * @return id of textarea
   */
  public String getId() {
    return this.getElement().getId();
  }
}

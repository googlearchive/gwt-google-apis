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
package com.google.gwt.language.client.translation;

/**
 * Content type of the text to be translated.
 */
public enum ContentType {
  HTML("html"), // Comments keep Eclipse formatter from putting on one line
  TEXT("text");

  private String value;

  private ContentType(String value) {
    this.value = value;
  }

  /**
   * Get the text value associated with enum member.
   * 
   * @return string containing enum value.
   */
  public String getValue() {
    return this.value;
  }
}

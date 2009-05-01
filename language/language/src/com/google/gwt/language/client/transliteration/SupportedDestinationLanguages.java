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

import com.google.gwt.core.client.JsArrayString;

/**
 * An enum that provides groups of supported destination languages.
 */
public enum SupportedDestinationLanguages {
  /**
   * All supported destination languages.
   */
  ALL(getNativeLanguages("ALL")),

  /**
   * All Indic supported destination languages
   */
  INDIC(getNativeLanguages("INDIC"));

  private static native JsArrayString getNativeLanguages(String array) /*-{
    return $wnd.google.elements.transliteration.SupportedDestinationLanguages[array];
  }-*/;

  private JsArrayString languages;

  private SupportedDestinationLanguages(JsArrayString languages) {
    this.languages = languages;
  }

  /**
   * Gets supported languages associated with this enum member.
   *
   * @return array of languages
   */
  public LanguageCode[] getLanguageCodes() {
    LanguageCode[] langs = new LanguageCode[languages.length()];
    for (int i = 0; i < languages.length(); ++i) {
      langs[i] = LanguageCode.getLanguage(languages.get(i));
    }
    return langs;
  }

  /**
   * Gets supported languages associated with this enum member.
   *
   * @return array of languages
   */
  public String[] getLanguages() {
    String[] langs = new String[languages.length()];
    for (int i = 0; i < languages.length(); ++i) {
      langs[i] = languages.get(i);
    }
    return langs;
  }
}

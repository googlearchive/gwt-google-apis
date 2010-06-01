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

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArrayString;

import java.util.ArrayList;
import java.util.List;

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

  private static native JsArrayString getNativeLanguages(String groupName) /*-{
    return $wnd.google.elements.transliteration.SupportedDestinationLanguages[groupName];
  }-*/;

  private JsArrayString languages;

  private SupportedDestinationLanguages(JsArrayString languages) {
    this.languages = languages;
  }

  /**
   * Gets supported languages associated with this enum member as {@code
   * LanguageCode}s.
   * 
   * @return array of languages
   */
  public LanguageCode[] getLanguageCodes() {
    
    // When the Language API developers push a new language, we don't want
    // to break anyone depending on this list, so filter out the languages
    // that GWT doesn't yet support.
    List<LanguageCode> langList = new ArrayList<LanguageCode>();
    for (int i = 0, j = languages.length(); i < j; ++i) {
      LanguageCode lang = LanguageCode.getLanguage(languages.get(i));
      if (lang == null) {
        GWT.log("SupportedDestinationLanguages: "
            + "GWT API doesn't handle language: " + languages.get(i)
            + "\nUpdate LanguageCode.java", null);
      } else {
        langList.add(lang);
      }
    }
    
    // Return the filtered list
    LanguageCode[] langs = new LanguageCode[langList.size()];
    for (int i = 0, j = langList.size(); i < j; i++) {
      langs[i] = langList.get(i);
    }

    return langs;
  }

  /**
   * Gets supported languages associated with this enum member as strings.
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

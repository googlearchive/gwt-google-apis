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

/**
 * Supported transliteration languages.
 */
public enum LanguageCode {
  AMHARIC("am"), // Comments keep Eclipse formatter from putting on one line
  ARABIC("ar"), //
  BENGALI("bn"), //
  CHINESE("zh"), //
  ENGLISH("en"), //
  GREEK("el"), //
  GUJARATI("gu"), //
  HINDI("hi"), //
  KANNADA("kn"), //
  MALAYALAM("ml"), //
  MARATHI("mr"), //
  NEPALI("ne"), //
  ORIYA("or"), //
  PUNJABI("pa"), //
  PERSIAN("fa"), //
  RUSSIAN("ru"), //
  SANSKRIT("sa"), //
  SERBIAN("sr"), //
  SINHALESE("si"), //
  TAMIL("ta"), //
  TELUGU("te"), //
  TIGRINYA("ti"), //
  URDU("ur");

  /**
   * Returns the LanguageCode corresponding to given 2-letter language code.
   *
   * @param lang language code
   * @return LanguageCode enum object
   */
  public static LanguageCode getLanguage(String lang) {
    for (LanguageCode l : LanguageCode.values()) {
      if (l.getLangCode().equals(lang)) {
        return l;
      }
    }
    return null;
  }

  private String langCode;

  private LanguageCode(String langCode) {
    this.langCode = langCode;
  }

  /**
   * Get 2-letter language code.
   *
   * @return 2-letter language code.
   */
  public String getLangCode() {
    return langCode;
  }
}

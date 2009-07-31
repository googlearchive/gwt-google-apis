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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.language.client.transliteration.LanguageCode;

/**
 * Wrapper for Options object.
 */
public class TransliterationControlOptions extends JavaScriptObject {

  /**
   * Creates instance of {@code Options} class with given parameters. Supports
   * multiple destination languages. The first language in the destination
   * language array is the default language for transliteration while others may
   * be enabled by using transliteration control widget.
   * 
   * @param srcLanguage source language of transliteration
   * @param destLanguages destination languages
   * @param transliterationEnabled
   * @param shortcutKey
   * @return instance of Options class
   */
  public static TransliterationControlOptions newInstance(
      LanguageCode srcLanguage, LanguageCode[] destLanguages,
      boolean transliterationEnabled, String shortcutKey) {
    JsArrayString destLanguageJsArray = (JsArrayString) JavaScriptObject.createArray();

    for (int i = 0; i < destLanguages.length; ++i) {
      destLanguageJsArray.set(i, destLanguages[i].getLangCode());
    }
    return newInstance(srcLanguage.getLangCode(), destLanguageJsArray,
        transliterationEnabled, shortcutKey);
  }

  /**
   * Creates instance of {@code Options} class with given parameters. Supports
   * multiple destination languages. The first language in the destination
   * language array is the default language for transliteration while others may
   * be enabled by using transliteration control widget. This is to support any
   * transliteration languages that might not be part of {@code LanguageCode}
   * enum yet.
   * 
   * @param srcLangCode source language of transliteration
   * @param destLangCodes destination languages
   * @param transliterationEnabled whether transliteration should be enabled by
   *          default
   * @param shortcutKey shortcut key to toggle transliteration
   * @return instance of {@code Options} class
   */
  public static TransliterationControlOptions newInstance(String srcLangCode,
      String[] destLangCodes, boolean transliterationEnabled, String shortcutKey) {
    JsArrayString destLanguageJsArray = (JsArrayString) JavaScriptObject.createArray();

    for (int i = 0; i < destLangCodes.length; ++i) {
      destLanguageJsArray.set(i, destLangCodes[i]);
    }
    return newInstance(srcLangCode, destLanguageJsArray,
        transliterationEnabled, shortcutKey);
  }

  /**
   * Private method that creates an instance of {@code Options} class with given
   * source language and array of destination languages.
   * 
   * @param srcLangCode source language of transliteration
   * @param destLangCodes destination languages
   * @param transliterationEnabled whether transliteration should be enabled by
   *          default
   * @param shortcutKey shortcut key to toggle transliteration
   * @return instance of {@code Options} class
   */
  private static native TransliterationControlOptions newInstance(
      String srcLangCode, JsArrayString destLangCodes,
      boolean transliterationEnabled, String shortcutKey) /*-{
    var options = new Object();
    options.sourceLanguage = srcLangCode;
    options.destinationLanguage = destLangCodes;
    options.transliterationEnabled = transliterationEnabled;
    options.shortcutKey = shortcutKey;
    return options;
  }-*/;

  protected TransliterationControlOptions() {
  }
}

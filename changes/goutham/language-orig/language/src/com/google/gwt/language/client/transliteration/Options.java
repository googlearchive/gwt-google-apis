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

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Wrapper for Options object.
 */
public class Options extends JavaScriptObject {
  protected Options() { }

  /**
   * Creates instance of this class with given parameters.
   *
   * @param srcLanguage source language of transliteration
   * @param destLanguage destination language of transliteration
   * @param transliterationEnabled whether transliteration should be enabled by
   *          default
   * @param shortcutKey shortcut key to toggle transliteration
   * @return instance of this class
   */
  public static Options newInstance(LanguageCode srcLanguage,
      LanguageCode destLanguage, boolean transliterationEnabled,
      String shortcutKey) {
    return newInstance(srcLanguage.getLangCode(), destLanguage.getLangCode(),
        transliterationEnabled, shortcutKey);
  }

  /**
   * This is public to support transliteration languages that might not be
   * part of {@code LanguageCode} enum yet.
   *
   * @param srcLangCode source language of transliteration
   * @param destLangCode destination language of transliteration
   * @param transliterationEnabled whether transliteration should be enabled by
   *          default
   * @param shortcutKey shortcut key to toggle transliteration
   * @return instance of this class
   */
  public static native Options newInstance(String srcLangCode,
      String destLangCode, boolean transliterationEnabled, String shortcutKey) /*-{
    var options = new Object();
    options.sourceLanguage = srcLangCode;
    options.destinationLanguage = destLangCode;
    options.transliterationEnabled = transliterationEnabled;
    options.shortcutKey = shortcutKey;
    return options;
  }-*/;
}

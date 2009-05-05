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
 * Contains miscellaneous transliteration API bindings.
 */
public class TransliterationUtils {
  /**
   * For the given sourceLanguage, this returns a map of destination languages
   * for which transliteration is supported.
   *
   * @param sourceLanguage the language to use for typing
   * @return an array of languages suitable as targets for transliteration.
   */
  public static LanguageCode[] getDestinationLanguages(
      LanguageCode sourceLanguage) {
    JsArrayString jsResult = nativeGetDestinationLanguages(sourceLanguage.getLangCode());
    LanguageCode[] result = new LanguageCode[jsResult.length()];
    for (int i = 0, n = jsResult.length(); i < n; i++) {
      result[i] = LanguageCode.valueOf(jsResult.get(i));
    }
    return result;
  }

  /**
   * Returns a boolean indicating whether transliteration in controls is
   * supported in the client's browser.
   *
   * @return boolean true if compatible, false otherwise
   */
  public static final native boolean isBrowserCompatible() /*-{
    return $wnd.google.elements.transliteration.isBrowserCompatible();
  }-*/;

  /**
   * Returns an array of language code constants suitable as targets for
   * transliteration with the specified source language.
   *
   * @param langCode name of a source language (not the 2 letter code).
   * @return a list of the language codes by name (not the 2 letter code).
   */
  private static native JsArrayString nativeGetDestinationLanguages(
      String langCode) /*-{
    var result = new Array();
    var map = $wnd.google.elements.transliteration.getDestinationLanguages(langCode);
    for (prop in map) {
      result.push(prop);
    }
    return result;
  }-*/;
}

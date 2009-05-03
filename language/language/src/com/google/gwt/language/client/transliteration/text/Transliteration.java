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
package com.google.gwt.language.client.transliteration.text;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.language.client.transliteration.LanguageCode;

import java.util.ArrayList;

/**
 * Global transliteration methods.
 */
public class Transliteration {

  /**
   * A method that will obtain transliterations for the given words in the
   * destination language. The result is supplied asynchronously to the given
   * callback function as the result object.
   *
   * @param wordsArray the words that are to be transliterated specified in
   *          array format
   * @param srcLang source language
   * @param destLang destination language
   * @param callback the callback that receives the result
   */
  public static final void transliterate(ArrayList<String> wordsArray,
      LanguageCode srcLang, LanguageCode destLang,
      TransliterationCallback callback) {
    JsArrayString jsArrayStr = (JsArrayString) JavaScriptObject.createArray();
    for (int i = 0; i < wordsArray.size(); ++i) {
      jsArrayStr.set(i, wordsArray.get(i));
    }
    transliterate(jsArrayStr, srcLang.getLangCode(), destLang.getLangCode(),
        callback);
  }

  /**
   * A method that will obtain transliterations for the given words in the
   * destination language. The result is supplied asynchronously to the given
   * callback function as the result object.
   *
   * @param wordsArray the words that are to be transliterated specified in
   *          array format
   * @param srcLang source language as language code
   * @param destLang destination language as language code
   * @param callback the callback that receives the result
   */
  private static native void transliterate(JsArrayString wordsArray,
      String srcLang, String destLang, TransliterationCallback callback) /*-{
    $wnd.google.language.transliterate(wordsArray, srcLang, destLang, function(result) {
      callback.@com.google.gwt.language.client.transliteration.text.TransliterationCallback::onCallbackWrapper(Lcom/google/gwt/language/client/transliteration/text/TransliterationResult;)(result);
    });
  }-*/;
}
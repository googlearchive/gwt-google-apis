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
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.language.client.Error;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains transliteration results.
 */
public class TransliterationResult extends JavaScriptObject {

  protected TransliterationResult() {
  }

  /**
   * Present if there was an error loading the feed.
   *
   * @return the {@code Error} object.
   */
  public final native Error getError() /*-{
    return this.error;
  }-*/;

  /**
   * Gets multiple transliterations of requested words. Each array of string
   * contains transliterations for corresponding word in the request.
   *
   * @return transliterations for words in request
   */
  public final List<List<String>> getTransliterations() {
    JsArray<JavaScriptObject> transliterations = getTransliterationsAsJsArray();
    List<List<String>> translitArray = new ArrayList<List<String>>();

    for (int i = 0; i < transliterations.length(); ++i) {
      JsArrayString translWordsJsArray = getTransliteratedWords(transliterations.get(i));
      ArrayList<String> transliteratedWords = new ArrayList<String>();

      for (int j = 0; j < translWordsJsArray.length(); ++j) {
        transliteratedWords.add(translWordsJsArray.get(j));
      }
      translitArray.add(transliteratedWords);
    }
    return translitArray;
  }

  /**
   * Gets transliterations obtained from server.
   *
   * @return an array of transliterated-word-arrays.
   */
  public final native JsArray<JavaScriptObject> getTransliterationsAsJsArray() /*-{
    return this.transliterations;
  }-*/;

  /**
   * An array of words that are transliterations for a given word in request.
   *
   * @param jso the JavaScriptObject which contains transliterations
   * @return array of transliterated words
   */
  private native JsArrayString getTransliteratedWords(JavaScriptObject jso) /*-{
    return jso.transliteratedWords;
  }-*/;
}

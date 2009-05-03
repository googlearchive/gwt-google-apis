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

import com.google.gwt.ajaxloader.client.ArrayHelper;
import com.google.gwt.core.client.JavaScriptException;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;

/**
 * Wrapper for google.elements.transliteration.TransliterationControl JS class.
 * Instances of this class are designed to enable transliteration on a set of
 * text fields in your page. Only one instance of this class can be created.
 * This is enforced by the underlying JS API.
 */
public class TransliterationControl extends JavaScriptObject {

  /**
   * Create an instance of TransliterationControl class. Only one instance of
   * this can be created per page.
   *
   * @param options the {@code Options} object containing required data
   * @return instance of this class
   */
  public static native TransliterationControl newInstance(
      TransliterationControlOptions options) /*-{
     return new $wnd.google.elements.transliteration.TransliterationControl(options);
   }-*/;

  protected TransliterationControl() {
  }

  /**
   * Adds a listener for the given event type. When the particular event type is
   * triggered, the listener is called with the event object. The contents of
   * the event object depend on the type of the event.
   *
   * @param eventType event type. See {@code EventType}
   * @param listener the listener to the event that is called when event is
   *          triggered
   */
  public final void addEventListener(EventType eventType,
      TransliterationEventListener listener) {
    JavaScriptObject jso = ListenerManager.createJSOEventListener(listener);
    addEventListener(eventType.getEventType(), jso);
    ListenerManager.store(eventType, listener, jso);
  }

  /**
   * Turns off transliteration.
   */
  public final native void disableTransliteration() /*-{
    this.disableTransliteration();
  }-*/;

  /**
   * Turns on transliteration.
   */
  public final native void enableTransliteration() /*-{
    this.enableTransliteration();
  }-*/;

  /**
   * Gets current language pair for transliteration. The array returned is
   * always of length 2; containing source and destination languages in order.
   *
   * @return a 2 element array containing source and destination languages
   */
  public final LanguageCode[] getLanguageCodePair() {
    JsArrayString jsArrayResults = getLanguagePairInternal();
    LanguageCode[] results = new LanguageCode[2];
    results[0] = LanguageCode.getLanguage(jsArrayResults.get(0));
    results[1] = LanguageCode.getLanguage(jsArrayResults.get(1));
    return results;
  }

  /**
   * Gets current language code pair for transliteration. The array returned is
   * always of length 2; containing source and destination language codes in
   * order.
   *
   * @return a 2 element array containing source and destination language codes.
   */
  public final String[] getLanguagePair() {
    JsArrayString jsArrayResults = getLanguagePairInternal();
    String[] results = new String[2];
    results[0] = jsArrayResults.get(0);
    results[1] = jsArrayResults.get(1);
    return results;
  }

  /**
   * Returns a boolean indicating whether transliteration is enabled or not.
   *
   * @return true if transliteration is enabled, false otherwise
   */
  public final native boolean isTransliterationEnabled() /*-{
    return this.isTransliterationEnabled();
  }-*/;

  /**
   * Makes textarea with given textarea id transliteratable.
   *
   * @param textAreaId textarea id
   * @throws JavaScriptException on invalid textarea id
   */
  public final void makeTransliteratable(String textAreaId) {
    makeTransliteratable(new String[] { textAreaId });
  }

  /**
   * Makes textarea given by id transliteratable.
   *
   * @param textArea textarea id
   * @param options options for textarea
   * @throws JavaScriptException on invalid textarea id
   */
  public final void makeTransliteratable(String textArea, TextElementOptions options) {
    makeTransliteratable(new String[] { textArea }, options);
  }

  /**
   * Makes array of textareas transliteratable.
   *
   * @param textAreaIds array of textarea ids.
   * @throws JavaScriptException on invalid textarea ids
   */
  public final void makeTransliteratable(String[] textAreaIds) {
    JsArrayString textAreaJsArray = ArrayHelper.createJsArray(textAreaIds);
    makeTransliteratable(textAreaJsArray);
  }

  /**
   * Makes array of textareas transliteratable with given options.
   *
   * @param textAreaIds array of textarea ids
   * @param options options for textarea adjustment
   * @throws JavaScriptException on invalid textarea ids
   */
  public final void makeTransliteratable(String[] textAreaIds,
      TextElementOptions options) {
    JsArrayString textAreaJsArray = ArrayHelper.createJsArray(textAreaIds);
    makeTransliteratable(textAreaJsArray, options);
  }

  /**
   * Makes a textarea transliteratable.
   *
   * @param textArea transliteratable textarea
   * @throws JavaScriptException on invalid textarea
   */
  public final void makeTransliteratable(TransliteratableTextArea textArea) {
    makeTransliteratable(new TransliteratableTextArea[] { textArea });
  }

  /**
   * Makes textarea transliteratable.
   *
   * @param textArea transliteratable textarea
   * @param options options for textarea
   * @throws JavaScriptException on invalid textarea
   */
  public final void makeTransliteratable(TransliteratableTextArea textArea,
      TextElementOptions options) {
    makeTransliteratable(new TransliteratableTextArea[] { textArea }, options);
  }

  /**
   * Makes array of textareas transliteratable.
   *
   * @param textAreas transliteratable textareas
   * @throws JavaScriptException on invalid textareas
   */
  public final void makeTransliteratable(TransliteratableTextArea[] textAreas) {
    JsArrayString textAreaJsArray = createJsArrayString(textAreas);
    makeTransliteratable(textAreaJsArray);
  }

  /**
   * Makes array of textareas transliteratable.
   *
   * @param textAreas transliteratable textareas
   * @param options options for textarea adjustment
   * @throws JavaScriptException on invalid textareas
   */
  public final void makeTransliteratable(TransliteratableTextArea[] textAreas,
      TextElementOptions options) {
    JsArrayString textAreaJsArray = JsArrayString.createArray().cast();
    int index = 0;
    for (TransliteratableTextArea textArea : textAreas) {
      textAreaJsArray.set(index, textArea.getId());
      ++index;
    }
    makeTransliteratable(textAreaJsArray, options);
  }

  /**
   * Removes a listener.
   *
   * @param eventType event type. See {@code EventType}
   * @param listener the listener to the event that is called when event is
   *          triggered
   */
  public final void removeEventListener(EventType eventType,
      TransliterationEventListener listener) {
    removeEventListener(eventType.getEventType(),
        ListenerManager.findAndRemove(eventType, listener));
  }

  /**
   * Changes the language pair for transliteration.
   *
   * @param sourceLanguage source language
   * @param destLanguage destination language
   * @return a boolean indicating whether the setLanguage action was successful.
   * @throws JavaScriptException Throws an exception under the following cases:
   *           sourceLanguage or destinationLanguage is invalid, the
   *           sourceLanguage-destinationLanguage language pair is not
   *           supported.
   */
  public final boolean setLanguagePair(LanguageCode sourceLanguage,
      LanguageCode destLanguage) throws JavaScriptException {
    return setLanguagePair(sourceLanguage.getLangCode(),
        destLanguage.getLangCode());
  }

  /**
   * Changes the language pair for transliteration.
   *
   * @param sourceLangCode source language code
   * @param destLangCode destination language code
   * @return a boolean indicating whether the setLanguage action was successful.
   * @throws JavaScriptException Throws an exception under the following cases:
   *           sourceLanguage or destinationLanguage is invalid, the
   *           sourceLanguage-destinationLanguage language pair is not
   *           supported.
   */
  public final native boolean setLanguagePair(String sourceLangCode,
      String destLangCode) /*-{
    return this.setLanguagePair(sourceLangCode, destLangCode);
  }-*/;

  /**
   * Shows transliteration control under div with given id.
   *
   * @param divId id of the div
   */
  public final native void showControl(String divId) /*-{
    this.showControl(divId);
  }-*/;

  /**
   * Shows the transliteration control under given div element.
   *
   * @param div the {@code TransliterationControlDiv} where control should be
   *          shown
   */
  public final void showControl(TransliterationControlDiv div) {
    showControl(div.getId());
  }

  /**
   * Toggles transliteration on or off status.
   */
  public final native void toggleTransliteration() /*-{
    this.toggleTransliteration();
  }-*/;

  /**
   * Adds a listener for the given event type. When the particular event type is
   * triggered, the listener is called with the event object. The contents of
   * the event object depend on the type of the event.
   *
   * @param eventType event type as string
   * @param jsoListener the listener to the event that is called when event is
   *          triggered
   */
  private native void addEventListener(String eventType,
      JavaScriptObject jsoListener) /*-{
    this.addEventListener(eventType, jsoListener);
  }-*/;

  /**
   * Creates JS array of strings using textarea ids.
   *
   * @param textAreas textarea array
   * @return js string array of textarea ids
   */
  private final JsArrayString createJsArrayString(
      TransliteratableTextArea[] textAreas) {
    JsArrayString textAreaJsArray = JsArrayString.createArray().cast();
    int index = 0;
    for (TransliteratableTextArea textArea : textAreas) {
      textAreaJsArray.set(index, textArea.getId());
      ++index;
    }
    return textAreaJsArray;
  }

  /**
   * Returns a JS array of string values containing source and destination
   * languages of transliteration.
   *
   * @return A JsArrayString with source and destination languages.
   */
  private native JsArrayString getLanguagePairInternal() /*-{
    var results = this.getLanguagePair();
    var array = new Array();
    array.push(results.sourceLanguage);
    array.push(results.destinationLanguage);
    return array;
  }-*/;

  /**
   * Makes given array of textareas transliteratable.
   *
   * @param textAreaJsArray ids of textareas
   */
  private final native void makeTransliteratable(
      JsArrayString textAreaJsArray) /*-{
    this.makeTransliteratable(textAreaJsArray);
  }-*/;

  /**
   * Makes given array of textareas transliteratable.
   *
   * @param textAreaJsArray ids of textareas
   * @param options options for textarea
   */
  private final native void makeTransliteratable(JsArrayString textAreaJsArray,
      TextElementOptions options) /*-{
    this.makeTransliteratable(textAreaJsArray, options);
  }-*/;

  /**
   * Removes a listener.
   *
   * @param eventType event type as string
   * @param listener the listener to the event that is called when event is
   *          triggered
   */
  private native void removeEventListener(String eventType,
      JavaScriptObject listener) /*-{
    this.removeEventListener(eventType, listener);
  }-*/;
}

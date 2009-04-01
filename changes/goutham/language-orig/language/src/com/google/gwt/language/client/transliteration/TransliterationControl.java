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

import com.google.gwt.core.client.JavaScriptException;
import com.google.gwt.core.client.JavaScriptObject;

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
  public static native TransliterationControl newInstance(Options options) /*-{
    return new $wnd.google.elements.transliteration.TransliterationControl(options);
  }-*/;

  protected TransliterationControl() { }

  /**
   * Adds a listener for the given event type. When the particular event type is
   * triggered, the listener is called with the event object. The contents of
   * the event object depend on the type of the event.
   *
   * @param eventType event type. See {@code EventType}
   * @param listener the listener to the event that is called when event is
   *          triggered
   */
  public final void addEventListener(EventType eventType, TranslitEventListener listener) {
    addEventListener(eventType.getEventType(), listener);
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
   * Returns a boolean indicating whether transliteration is enabled or not.
   *
   * @return true if transliteration is enabled, false otherwise
   */
  public final native boolean isTransliterationEnabled() /*-{
    return this.isTransliterationEnabled();
  }-*/;

  /**
   * Makes textarea with given id transliteratable.
   *
   * @param textAreaId id of textarea
   */
  public final native void makeTransliteratable(String textAreaId) /*-{
    this.makeTransliteratable([textAreaId]);
  }-*/;

  /**
   * Makes the given {@code TransliteratableTextArea} transliteratable.
   *
   * @param textArea an instance of {@code TransliteratableTextArea}
   */
  public final void makeTransliteratable(TransliteratableTextArea textArea) {
    makeTransliteratable(textArea.getId());
  }

  /**
   * Removes a listener.
   *
   * @param eventType event type. See {@code EventType}
   * @param listener the listener to the event that is called when event is
   *          triggered
   */
  public final void removeEventListener(EventType eventType, TranslitEventListener listener) {
    removeEventListener(eventType.getEventType(), listener);
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
    return setLanguagePair(sourceLanguage.getLangCode(), destLanguage.getLangCode());
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
  public final native boolean setLanguagePair(String sourceLangCode, String destLangCode) /*-{
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
   * @param listener the listener to the event that is called when event is
   *          triggered
   */
  private final native void addEventListener(String eventType, TranslitEventListener listener) /*-{
    this.addEventListener(eventType, function(e) {
      listener.@com.google.gwt.language.client.transliteration.TranslitEventListener::onEventWrapper(Lcom/google/gwt/language/client/transliteration/TransliterationEvent;)(e);
    });
  }-*/;

  /**
   * Removes a listener.
   *
   * @param eventType event type as string
   * @param listener the listener to the event that is called when event is
   *          triggered
   */
  private final native void removeEventListener(String eventType, TranslitEventListener listener) /*-{
    this.removeEventListener(eventType, function(e) {
      listener.@com.google.gwt.language.client.transliteration.TranslitEventListener::onEventWrapper(Lcom/google/gwt/language/client/transliteration/TransliterationEvent;)(e);
    });
  }-*/;
}

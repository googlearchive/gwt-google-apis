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

import com.google.gwt.ajaxloader.client.ArrayHelper;
import com.google.gwt.core.client.JavaScriptException;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.language.client.transliteration.LanguageCode;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.TextBoxBase;

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
   * Makes text field with given id transliteratable. Note that the text field
   * should already be attached to the page before this is called.
   *
   * @param textFieldId text field id
   * @throws JavaScriptException on invalid text field id
   */
  public final void makeTransliteratable(String textFieldId) {
    makeTransliteratable(new String[] {textFieldId});
  }

  /**
   * Makes text field with given id transliteratable with given options. Note
   * that the text field should already be attached to the page before this is
   * called.
   *
   * @param textField text field id
   * @param options options for text field
   * @throws JavaScriptException on invalid text field id
   */
  public final void makeTransliteratable(String textField,
      TextElementOptions options) {
    makeTransliteratable(new String[] {textField}, options);
  }

  /**
   * Makes array of text fields transliteratable. Note that the text field
   * should already be attached to the page before this is called.
   *
   * @param textFieldIds array of text field ids.
   * @throws JavaScriptException on invalid text field ids
   */
  public final void makeTransliteratable(String[] textFieldIds) {
    JsArrayString textFieldJsArray = ArrayHelper.createJsArray(textFieldIds);
    makeTransliteratable(textFieldJsArray);
  }

  /**
   * Makes array of text fields transliteratable with given options. Note that
   * the text field should already be attached to the page before this is
   * called.
   *
   * @param textFieldIds array of text field ids
   * @param options options for text field adjustment
   * @throws JavaScriptException on invalid text field ids
   */
  public final void makeTransliteratable(String[] textFieldIds,
      TextElementOptions options) {
    JsArrayString textFieldJsArray = ArrayHelper.createJsArray(textFieldIds);
    makeTransliteratable(textFieldJsArray, options);
  }

  /**
   * Make the given text area transliteratable.
   *
   * @param textArea the text area to be made transliteratable.
   */
  public final void makeTransliteratable(TextArea textArea) {
    makeTransliteratable(new TextArea[] { textArea });
  }

  /**
   * Make the given text area transliteratable with given options.
   *
   * @param textArea the text area to be made transliteratable.
   * @param options options for text area adjustment
   */
  public final void makeTransliteratable(TextArea textArea,
      TextElementOptions options) {
    makeTransliteratable(new TextArea[] { textArea }, options);
  }

  /**
   * Make the given text areas transliteratable.
   *
   * @param textAreas the array of text areas to be made transliteratable.
   */
  public final void makeTransliteratable(TextArea[] textAreas) {
    JsArray<JavaScriptObject> textFieldJsArray = createJsArray(textAreas);
    makeTransliteratable(textFieldJsArray);
  }

  /**
   * Make the given text areas transliteratable with given options.
   *
   * @param textAreas the text areas to be made transliteratable
   * @param options options for text area adjustment
   */
  public final void makeTransliteratable(TextArea[] textAreas,
      TextElementOptions options) {
    JsArray<JavaScriptObject> textFieldJsArray = createJsArray(textAreas);
    makeTransliteratable(textFieldJsArray, options);
  }

  /**
   * Make the given text box transliteratable.
   *
   * @param textBox the text box to be made transliteratable.
   */
  public final void makeTransliteratable(TextBox textBox) {
    makeTransliteratable(new TextBox[] { textBox });
  }

  /**
   * Make the given text box transliteratable with given options.
   *
   * @param textBox the text area to be made transliteratable.
   * @param options options for text box adjustment
   */
  public final void makeTransliteratable(TextBox textBox,
      TextElementOptions options) {
    makeTransliteratable(new TextBox[] { textBox }, options);
  }

  /**
   * Make the given text boxes transliteratable.
   *
   * @param textBoxes the array of text boxes to be made transliteratable.
   */
  public final void makeTransliteratable(TextBox[] textBoxes) {
    JsArray<JavaScriptObject> textFieldJsArray = createJsArray(textBoxes);
    makeTransliteratable(textFieldJsArray);
  }

  /**
   * Make the given text boxes transliteratable with given options.
   *
   * @param textBoxes the text boxes to be made transliteratable
   * @param options options for text box adjustment
   */
  public final void makeTransliteratable(TextBox[] textBoxes,
      TextElementOptions options) {
    JsArray<JavaScriptObject> textFieldJsArray = createJsArray(textBoxes);
    makeTransliteratable(textFieldJsArray, options);
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
   * Shows transliteration control under given DIV element.
   *
   * @param div the HTML DIV element
   */
  public final void showControl(HTML div) {
    showControl(div.getElement());
  }

  /**
   * Shows transliteration control under div with given id.
   *
   * @param divId id of the div
   */
  public final native void showControl(String divId) /*-{
    this.showControl(divId);
  }-*/;

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
   * Creates a JS array of text fields from given java array.
   *
   * @param textFields array of text fields
   * @return Javascript array of text fields
   */
  private final JsArray<JavaScriptObject> createJsArray(TextBoxBase[] textFields) {
    JsArray<JavaScriptObject> textFieldJsArray = JavaScriptObject.createArray().cast();
    int index = 0;
    for (TextBoxBase textField : textFields) {
      textFieldJsArray.set(index, textField.getElement());
      ++index;
    }
    return textFieldJsArray;
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
   * Makes the given text fields transliteratable.
   *
   * @param textFields the array of text field references
   */
  private final native void makeTransliteratable(
      JsArray<JavaScriptObject> textFields) /*-{
    this.makeTransliteratable(textFields);
  }-*/;

  /**
   * Make the given text fields transliteratable with given options.
   *
   * @param textFields javascript array of text fields.
   * @param options options for text field adjustment
   */
  private native void makeTransliteratable(
      JsArray<JavaScriptObject> textFields, TextElementOptions options) /*-{
    this.makeTransliteratable(textFields, options);
  }-*/;

  /**
   * Makes given array of text fields transliteratable.
   *
   * @param textFields ids of text fields
   */
  private native void makeTransliteratable(JsArrayString textFields) /*-{
    this.makeTransliteratable(textFields);
  }-*/;

  /**
   * Makes given array of text fields transliteratable.
   *
   * @param textFields ids of text fields
   * @param options options for text field adjustment
   */
  private native void makeTransliteratable(JsArrayString textFields,
      TextElementOptions options) /*-{
    this.makeTransliteratable(textFields, options);
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

  /**
   * Show transliteration control under given div element.
   *
   * @param div the div element to which control is attached.
   */
  private final native void showControl(Element div) /*-{
    this.showControl(div);
  }-*/;
}

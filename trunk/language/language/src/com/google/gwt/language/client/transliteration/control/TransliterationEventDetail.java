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
import com.google.gwt.language.client.transliteration.LanguageCode;

/**
 * The transliteration event object that is given to the event listener when a
 * registered event is triggered. See {@code TranslitEventListener}.
 */
public class TransliterationEventDetail extends JavaScriptObject {
  protected TransliterationEventDetail() {
  }

  /**
   * This is valid only if event type is LANGUAGE_CHANGED. See {@code EventType}
   * for types of events.
   * 
   * @return destination language
   */
  public final LanguageCode getDestinationLanguage() {
    return LanguageCode.getLanguage(getDestLangCode());
  }

  /**
   * This is valid only if event type is LANGUAGE_CHANGED. See {@code EventType}
   * for types of events.
   * 
   * @return source language
   */
  public final LanguageCode getSourceLanguage() {
    return LanguageCode.getLanguage(getSourceLangCode());
  }

  /**
   * This is valid call only when the event type is STATE_CHANGED. See {@code
   * EventType} for types of events.
   * 
   * @return boolean true if transliteration is enabled, false if its turned off
   */
  public final native boolean isTransliterationEnabled() /*-{
    return this.transliterationEnabled;
  }-*/;

  /**
   * @return language code of destination language
   */
  private native String getDestLangCode() /*-{
    return this.destinationLanguage;
  }-*/;

  /**
   * @return language code of source language
   */
  private native String getSourceLangCode() /*-{
    return this.sourceLanguage;
  }-*/;

}

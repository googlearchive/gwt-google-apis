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

/**
 * Wrapper for underlying JS EventType enum. The
 * google.elements.transliteration.TransliterationControl.EventType enumeration
 * provides possible events which can be raised during transliteration.
 * Developers can provide custom handlers for these events in their code.
 */
public enum EventType {
  /**
   * Event fired when the transliteration language pair is changed in the
   * TransliterationControl in any of the following ways:
   * <ul>
   * <li> When the transliteration language pair is changed using setLanguagePair.
   * <li> When the transliteration language is changed from the transliteration
   * control drawn by showControl.
   * </ul>
   * <br>
   * The event object passed to the listener contains the fields sourceLanguage
   * & destinationLanguage.
   */
  LANGUAGE_CHANGED(getNativeEventType("LANGUAGE_CHANGED")),

  /**
   * Event fired when the server could be successfully contacted for doing a
   * transliteration.
   */
  SERVER_REACHABLE(getNativeEventType("SERVER_REACHABLE")),

  /**
   * Event fired when the server could not be successfully contacted for doing a
   * transliteration.
   */
  SERVER_UNREACHABLE(getNativeEventType("SERVER_UNREACHABLE")),

  /**
   * Event fired when transliteration is enabled or disabled in the
   * TransliterationControl in any of the following ways:
   * <ul>
   * <li> When the transliteration is enabled or disabled using the shortcut key.
   * <li> When the transliteration is enabled or disabled using
   * enableTransliteration, disableTransliteration & toggleTransliteration.
   * <li> When the transliteration is enabled or disabled by clicking the
   * transliteration control drawn by showControl.
   * </ul>
   * <br>
   * The event object passed to the listener contains the field
   * transliterationEnabled which is true if the transliteration is 'on', else
   * false.
   */
  STATE_CHANGED(getNativeEventType("STATE_CHANGED"));

  private static native String getNativeEventType(String eventType) /*-{
    return $wnd.google.elements.transliteration.TransliterationControl.EventType[eventType];
  }-*/;

  private String eventType;

  private EventType(String eventType) {
    this.eventType = eventType;
  }

  public String getEventType() {
    return this.eventType;
  }
}

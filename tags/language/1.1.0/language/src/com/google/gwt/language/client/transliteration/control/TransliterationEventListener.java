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

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;

/**
 * Listener of transliteration events. Override this class' abstract method
 * onEvent() to put your own logic.
 */
public abstract class TransliterationEventListener {

  /**
   * Called when an event is triggered.
   *
   * @param result the event object
   */
  protected abstract void onEvent(TransliterationEventDetail result);

  /**
   * This wraps onEvent method and provides a framework for catching
   * exceptions in callbacks.
   *
   * @param event the event object.
   */
  public void onEventWrapper(TransliterationEventDetail event) {
    UncaughtExceptionHandler exceptionHandler = GWT.getUncaughtExceptionHandler();
    if (exceptionHandler != null) {
      try {
        onEvent(event);
      } catch (Exception e) {
        exceptionHandler.onUncaughtException(e);
      }
    } else {
      onEvent(event);
    }
  }
}

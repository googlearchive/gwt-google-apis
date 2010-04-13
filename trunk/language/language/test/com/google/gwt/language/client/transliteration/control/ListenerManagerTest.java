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
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.language.client.LanguageUtils;

/**
 * Tests for ListenerManager class.
 */
public class ListenerManagerTest extends GWTTestCase {
  private static final int MAX_TEST_FINISH_DELAY = 10000;
  
  @Override
  public String getModuleName() {
    return "com.google.gwt.language.Language";
  }

  public void testStoreAndRetrieveListeners() {
    LanguageUtils.loadTransliteration(new Runnable() {
      public void run() {
        TransliterationEventListener listener1 = buildTranslitEventListener();
        JavaScriptObject jso1 = ListenerManager.createJSOEventListener(listener1);
        ListenerManager.store(EventType.STATE_CHANGED, listener1, jso1);
        ListenerManager.store(EventType.LANGUAGE_CHANGED, listener1, jso1);

        assertEquals("Expected correct JSO in event type STATE_CHANGED", jso1,
            ListenerManager.findAndRemove(EventType.STATE_CHANGED, listener1));
        assertNull("Expected null when object is already retrieved earlier",
            ListenerManager.findAndRemove(EventType.STATE_CHANGED, listener1));
        assertEquals("Expected correct JSO in event type LANGUAGE_CHANGED",
            jso1, ListenerManager.findAndRemove(EventType.LANGUAGE_CHANGED,
                listener1));

        TransliterationEventListener listener2 = buildTranslitEventListener();
        JavaScriptObject jso2 = ListenerManager.createJSOEventListener(listener2);
        ListenerManager.store(EventType.LANGUAGE_CHANGED, listener2, jso2);

        assertNull("Expected null when incorrect event type is specified",
            ListenerManager.findAndRemove(EventType.STATE_CHANGED, listener2));
        assertEquals("Expected correct JSO when correct event type is given",
            jso2, ListenerManager.findAndRemove(EventType.LANGUAGE_CHANGED,
                listener2));
        assertNull("Expected null when object is already retrieved earlier",
            ListenerManager.findAndRemove(EventType.LANGUAGE_CHANGED, listener2));
        finishTest();
      }
    });
    delayTestFinish(MAX_TEST_FINISH_DELAY);
  }

/**
 * Builds a do-nothing TranslitEventListener Object for testing.
 *
 * @return TranslitEventListener object
 */
  private TransliterationEventListener buildTranslitEventListener() {
    TransliterationEventListener listener = new TransliterationEventListener() {

      @Override
      protected void onEvent(TransliterationEventDetail result) {
        // Do nothing.
      }

    };
    return listener;
  }

}

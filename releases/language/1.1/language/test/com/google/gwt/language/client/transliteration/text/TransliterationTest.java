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

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.language.client.LanguageUtils;
import com.google.gwt.language.client.transliteration.LanguageCode;

import java.util.ArrayList;

/**
 * Test for Transliteration class.
 */
public class TransliterationTest extends GWTTestCase {
  private static final int MAX_TEST_FINISH_DELAY = 10000;

  @Override
  public String getModuleName() {
    return "com.google.gwt.language.Language";
  }

  /**
   * Verify that transliterations are obtained and no exceptions are
   * thrown.
   */
  public void testTransliteration() {
    LanguageUtils.loadTransliteration(new Runnable() {
      public void run() {
        ArrayList<String> wordsArray = new ArrayList<String>();
        wordsArray.add("mera");
        wordsArray.add("naam");
        Transliteration.transliterate(wordsArray, LanguageCode.ENGLISH,
            LanguageCode.HINDI, new TransliterationCallback() {

              @Override
              protected void onCallback(TransliterationResult result) {
                assertTrue(result.getTransliterations().size() == 2);
                finishTest();
              }

        });
      }
    });
    delayTestFinish(MAX_TEST_FINISH_DELAY);
  }
}

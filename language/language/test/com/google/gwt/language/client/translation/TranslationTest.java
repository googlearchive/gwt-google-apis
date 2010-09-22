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
package com.google.gwt.language.client.translation;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.language.client.LanguageUtils;

/**
 * Tests for translation API.
 */
public class TranslationTest extends GWTTestCase {
  private static final int MAX_TEST_FINISH_DELAY = 10000;

  @Override
  public String getModuleName() {
    return "com.google.gwt.language.Language";
  }

  /**
   * Verifies that language detection API works correctly.
   */
  public void testLanguageDetection() {
    LanguageUtils.loadTranslation(new Runnable() {
      public void run() {
        Translation.detect("hello world", new LangDetCallback() {

          @Override
          protected void onCallback(LangDetResult result) {
            assertNull(result.getError());
            assertEquals("en", result.getLanguage());
            finishTest();
          }

        });
      }
    });
    delayTestFinish(MAX_TEST_FINISH_DELAY);
  }

  /**
   * Verifies that translation API works correctly.
   */
  public void testTranslation() {
    LanguageUtils.loadTranslation(new Runnable() {
      public void run() {
        Translation.translate("hello world", Language.ENGLISH,
            Language.SPANISH, new TranslationCallback() {

              @Override
              protected void onCallback(TranslationResult result) {
                assertNull(result.getError());
                assertEquals("hola mundo", result.getTranslatedText());
                finishTest();
              }

            });
      }
    });
    delayTestFinish(MAX_TEST_FINISH_DELAY);
  }

  /**
   * Verifies that invalid destination language of translation results in an
   * error response from server.
   */
  public void testTranslationError() {
    LanguageUtils.loadTranslation(new Runnable() {
      public void run() {
        Translation.translate("hello world", "en", "zz",
            new TranslationCallback() {

              @Override
              protected void onCallback(TranslationResult result) {
                assertEquals("invalid translation language pair",
                    result.getError().getMessage());
                assertEquals(400, result.getError().getCode());
                finishTest();
              }

            });
      }
    });
    delayTestFinish(MAX_TEST_FINISH_DELAY);
  }

  /**
   * Tests that Option object is instantiated properly.
   */
  public void testTranslationOptionObject() {
    final String textToTranslate = "hello world";
    Option option = Option.newInstance(textToTranslate, ContentType.HTML);
    assertNotNull(option);
    assertEquals(ContentType.HTML, option.getType());
    assertEquals(textToTranslate, option.getText());

    Option option2 = Option.newInstance(textToTranslate, ContentType.TEXT);
    assertEquals(ContentType.TEXT, option2.getType());
  }

  /**
   * Verifies that translation API works correctly with content type = html. The
   * API should leave alone any HTML tags.
   */
  public void testTranslationWithHTMLOption() {
    GWT.log("Started test of HTML option");
    LanguageUtils.loadTranslation(new Runnable() {
      public void run() {
        GWT.log("API Loaded, start test of HTML option");
        Option option = Option.newInstance("<bold>beans and rice</bold>",
            ContentType.HTML);
        assertNotNull(option);
        Translation.translate(option, Language.ENGLISH, Language.SPANISH,
            new TranslationCallback() {

              @Override
              protected void onCallback(TranslationResult result) {
                GWT.log("Got Callback HTML option");
                assertNull(result.getError());
                String resultText = result.getTranslatedText();

                // Since translation server can put spaces in response text, we
                // have to compare this way.
                assertTrue(resultText.startsWith("<bold>"));
                assertTrue(resultText.endsWith("</bold>"));
                assertTrue(resultText.contains("frijoles "));
                assertTrue(resultText.contains(" y "));
                assertTrue(resultText.contains(" arroz"));
                finishTest();
              }
            });
      }
    });
    delayTestFinish(MAX_TEST_FINISH_DELAY);
  }

  /**
   * Verifies that translation API works correctly with content type = text.
   * html tag contents are interpreted as words in the source language and may
   * be translated.
   */
  public void testTranslationWithTextOption() {
    LanguageUtils.loadTranslation(new Runnable() {
      public void run() {
        Option option = Option.newInstance(
            "Please bring me < bold > beans and rice. </ bold>", ContentType.TEXT);
        assertNotNull(option);
        Translation.translate(option, Language.ENGLISH, Language.SPANISH,
            new TranslationCallback() {

              @Override
              protected void onCallback(TranslationResult result) {
                assertNull(result.getError());
                String resultText = result.getTranslatedText();

                // Since translation server can put spaces in response text, we
                // have to compare this way.
                assertTrue(resultText.toLowerCase().contains("negrita"));
                assertTrue(resultText.toLowerCase().contains("frijol"));
                assertTrue(resultText.toLowerCase().contains(" y "));
                assertTrue(resultText.toLowerCase().contains(" arroz"));
                finishTest();
              }

            });
      }
    });
    delayTestFinish(MAX_TEST_FINISH_DELAY);
  }
}

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

import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.language.client.LanguageUtils;
import com.google.gwt.language.client.translation.Language.SupportedLanguages;

import java.util.HashMap;
import java.util.Map;

/**
 * Tests for Language enum API.
 */
public class LanguageEnumTest extends GWTTestCase {
  private static final int MAX_TEST_FINISH_DELAY = 10000;

  @Override
  public String getModuleName() {
    return "com.google.gwt.language.Language";
  }

  public void testLangCode() {
    assertEquals("Language.ENGLISH", "en", Language.ENGLISH.getLangCode());
    assertEquals("Language.SPANISH", "es", Language.SPANISH.getLangCode());
  }

  public void testValueOf() {
    Language result = Language.ENGLISH;
    Language result2 = Language.valueOf(Language.class, "ENGLISH");
    assertEquals("Language.ENGLISH", result, result2);
  }

  public void testSupportedLanguages() {
    LanguageUtils.loadTranslation(new Runnable() {
      public void run() {
        SupportedLanguages supportedLanguages = Language.nativeSupportedLangauges();
        JsArrayString languages = supportedLanguages.getLanguages();
        assertTrue("didn't find any languages", languages.length() > 0);
        for (int i = 0, length = languages.length(); i < length; ++i) {
          String languageName = languages.get(i);
          String languageCode = supportedLanguages.getLanguageCode(languageName);
          String foundLanguageName = supportedLanguages.getLanguageName(languageCode);
          // Some language codes are listed twice - there are 2 names for the
          // same language like "Filipino" and "Tagalong".
          assertNotNull(foundLanguageName);
        }
        finishTest();
      }
    });
    delayTestFinish(MAX_TEST_FINISH_DELAY);

  }

  /**
   * This is a test that finds definitions that have been added to JavaScript
   * that might not yet be reflected in the API.
   */
  public void testFindMissingEnums() {
    LanguageUtils.loadTranslation(new Runnable() {
      public void run() {
        SupportedLanguages supportedLanguages = Language.nativeSupportedLangauges();

        // Make a hash map of the enum values in Language.java
        Map<String, String> enumMap = new HashMap<String, String>();
        final Language languageEnums[] = Language.values();
        for (Language languageEnum : languageEnums) {
          enumMap.put(languageEnum.getLangCode(), languageEnum.name());
        }

        // Compare the enums declared in Language.java to the ones returned
        // from JavaScript.
        JsArrayString languages = supportedLanguages.getLanguages();
        for (int i = 0, length = languages.length(); i < length; ++i) {
          String languageName = languages.get(i);
          String languageCode = supportedLanguages.getLanguageCode(languageName);
          assertNotNull("* Missing Language: " + languageName + "(\""
              + languageCode + "\")", enumMap.get(languageCode));

        }
        finishTest();
      }
    });
    delayTestFinish(MAX_TEST_FINISH_DELAY);
  }
}

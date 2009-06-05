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

import com.google.gwt.junit.client.GWTTestCase;

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
}

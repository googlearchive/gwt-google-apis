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
package com.google.gwt.language;

import com.google.gwt.junit.tools.GWTTestSuite;
import com.google.gwt.language.client.translation.LanguageEnumTest;
import com.google.gwt.language.client.translation.TranslationTest;
import com.google.gwt.language.client.transliteration.control.ListenerManagerTest;
import com.google.gwt.language.client.transliteration.control.TransliterationControlTest;
import com.google.gwt.language.client.transliteration.text.TransliterationTest;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Language API test suite.
 */
public class LanguageTestSuite extends GWTTestSuite {

  public static Test suite() {
    TestSuite suite = new TestSuite("Tests for Language API");
    suite.addTestSuite(TranslationTest.class);
    suite.addTestSuite(LanguageEnumTest.class);
    suite.addTestSuite(TransliterationControlTest.class);
    suite.addTestSuite(ListenerManagerTest.class);
    suite.addTestSuite(TransliterationTest.class);
    return suite;
  }
}

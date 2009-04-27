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
package com.google.gwt.language.client.transliteration;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.language.client.LanguageUtils;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Tests for TransliterationControl class.
 */
public class TransliterationControlTest extends GWTTestCase {
  private static TransliterationControl control;
  private TransliterationControlDiv div;
  private TransliteratableTextArea textArea1;
  private TransliteratableTextArea textArea2;

  @Override
  public String getModuleName() {
    return "com.google.gwt.language.Language";
  }

  @Override
  public void gwtSetUp() {
    div = new TransliterationControlDiv("div1");
    RootPanel.get().add(div);

    textArea1 = new TransliteratableTextArea("id1");
    RootPanel.get().add(textArea1);

    textArea2 = new TransliteratableTextArea("id2");
    RootPanel.get().add(textArea2);
  }

  @Override
  public void gwtTearDown() {
    RootPanel.get().clear();
  }

  /**
   * Verifies that transliteration can be enabled or disabled using
   * Transliteration control.
   */
  public void testEnableTransliteration() {
    LanguageUtils.loadTransliteration(new Runnable() {
      public void run() {
        initialize();
        assertTrue("Expected Transliteration to be enabled",
            control.isTransliterationEnabled());

        control.disableTransliteration();
        assertFalse("Expected Transliteration to be disabled",
            control.isTransliterationEnabled());

        control.enableTransliteration();
        assertTrue("Expected Transliteration to be re-enabled",
            control.isTransliterationEnabled());

        control.toggleTransliteration();
        assertFalse("Expected transliteration to be disabled",
            control.isTransliterationEnabled());

        control.toggleTransliteration();
        assertTrue("Expected transliteration to be enabled",
            control.isTransliterationEnabled());
      }
    });
  }

  /**
   * Tests that language pair may be correctly set and unset.
   */
  public void testLanguagePairSetting() {
    LanguageUtils.loadTransliteration(new Runnable() {
      public void run() {
        initialize();
        control.setLanguagePair(LanguageCode.ENGLISH, LanguageCode.KANNADA);
        LanguageCode[] langPair = control.getLanguageCodePair();
        assertTrue(langPair[0] == LanguageCode.ENGLISH &&
            langPair[1] == LanguageCode.KANNADA);

        control.setLanguagePair(LanguageCode.ENGLISH, LanguageCode.HINDI);
        LanguageCode[] langPair2 = control.getLanguageCodePair();
        assertTrue(langPair2[0] == LanguageCode.ENGLISH &&
            langPair2[1] == LanguageCode.HINDI);
      }
    });
  }

  /**
   * Initializes Transliteration Control. Must be called after API loads.
   */
  private void initialize() {
    // TransliterationControl may not be instantiated more than once.
    if (control == null) {
      Options options = Options.newInstance(LanguageCode.ENGLISH,
          Transliteration.getDestinationLanguages(LanguageCode.ENGLISH), true,
          "ctrl+g");
      control = TransliterationControl.newInstance(options);
    }
    control.enableTransliteration();
    control.showControl(div);
  }
}

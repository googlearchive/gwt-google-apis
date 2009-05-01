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
package com.google.gwt.language.sample.hellolanguage.client;

import com.google.gwt.language.client.transliteration.LanguageCode;
import com.google.gwt.language.client.transliteration.Options;
import com.google.gwt.language.client.transliteration.SupportedDestinationLanguages;
import com.google.gwt.language.client.transliteration.TransliteratableTextArea;
import com.google.gwt.language.client.transliteration.TransliterationControl;
import com.google.gwt.language.client.transliteration.TransliterationControlDiv;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Demo for transliteration API.
 */
public class TransliterationDemo extends Composite {
  private final LanguageCode srcLanguage = LanguageCode.ENGLISH;
  private final LanguageCode[] destLanguages =
      SupportedDestinationLanguages.ALL.getLanguageCodes();
  private final Options options = Options.newInstance(srcLanguage, destLanguages,
      true, "ctrl+g");
  private final TransliterationControl control = TransliterationControl.newInstance(options);
  private final TransliterationControlDiv div = new TransliterationControlDiv(
      "translitdiv");
  private final TransliteratableTextArea transltextarea = new TransliteratableTextArea(
      "translarea");

  public TransliterationDemo() {
    VerticalPanel demoPanel = new VerticalPanel();
    demoPanel.add(div);
    demoPanel.add(transltextarea);

    VerticalPanel wrapperPanel = new VerticalPanel();
    wrapperPanel.setWidth("100%");
    wrapperPanel.add(demoPanel);
    wrapperPanel.setCellHorizontalAlignment(demoPanel,
        HasHorizontalAlignment.ALIGN_CENTER);
    initWidget(wrapperPanel);
  }

  /**
   * Initializes the transliteration controls and the makes the textarea
   * transliteratable. This must be called only after the panel containing this
   * demo widget has been attached to the root panel because the below
   * initializations require elements to be present on the UI to act on them.
   */
  public void initialize() {
    control.showControl(div);
    control.makeTransliteratable(transltextarea);

    // TODO: making textarea transliteratable is resizing the textarea. Fix the
    // issue. Currently we have to explicitly resize or specify
    // adjustTextareaStyle = false
    transltextarea.setWidth("500px");
    transltextarea.setHeight("120px");
  }
}

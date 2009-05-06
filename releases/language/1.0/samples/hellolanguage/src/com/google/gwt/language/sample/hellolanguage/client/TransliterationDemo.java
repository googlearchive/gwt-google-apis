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
import com.google.gwt.language.client.transliteration.SupportedDestinationLanguages;
import com.google.gwt.language.client.transliteration.control.TransliterationControl;
import com.google.gwt.language.client.transliteration.control.TransliterationControlOptions;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Demo for transliteration API.
 */
public class TransliterationDemo extends Composite {
  public TransliterationDemo() {
    VerticalPanel demoPanel = new VerticalPanel();

    demoPanel.add(new Label("Enter text to transliterate:"));

    HTML div = new HTML();
    // Style name used for IE7 workaround related to layout 
    // of the control. See Issue 261
    div.setStyleName("demo-transliterate-control");
    demoPanel.add(div);

    TextArea transltextarea = new TextArea();
    demoPanel.add(transltextarea);

    VerticalPanel wrapperPanel = new VerticalPanel();
    wrapperPanel.setWidth("100%");
    wrapperPanel.add(demoPanel);
    wrapperPanel.setCellHorizontalAlignment(demoPanel,
        HasHorizontalAlignment.ALIGN_CENTER);
    initWidget(wrapperPanel);

    initTransliterationControls(div, transltextarea);
  }

  /**
   * Initializes the transliteration controls.
   *
   * @param div the div to which language options menu is attached.
   * @param transltextarea the textarea for transliteration
   */
  private void initTransliterationControls(HTML div, TextArea transltextarea) {
    LanguageCode srcLanguage = LanguageCode.ENGLISH;
    LanguageCode[] destLanguages = SupportedDestinationLanguages.ALL.getLanguageCodes();

    TransliterationControlOptions options = TransliterationControlOptions.newInstance(
        srcLanguage, destLanguages, true, "ctrl+g");
    TransliterationControl control = TransliterationControl.newInstance(options);

    control.showControl(div);
    control.makeTransliteratable(transltextarea);

    // TODO: making textarea transliteratable is resizing the textarea. Fix the
    // issue. Currently we have to explicitly resize or specify
    // adjustTextareaStyle = false
    transltextarea.setWidth("500px");
    transltextarea.setHeight("120px");
  }
}

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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.language.client.translation.BrandingOptions;
import com.google.gwt.language.client.translation.Language;
import com.google.gwt.language.client.translation.Translation;
import com.google.gwt.language.client.translation.TranslationCallback;
import com.google.gwt.language.client.translation.TranslationResult;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Demo for translation API.
 */
public class TranslationDemo extends Composite {

  private final TextArea inputTextArea = new TextArea();
  private final Label outputDiv = new Label();

  private final ListBox sourceLanguages = new ListBox();
  private final ListBox destinationLanguages = new ListBox();

  public TranslationDemo() {
    inputTextArea.setStyleName(Styles.DEMO_TEXTAREA_STYLE);
    outputDiv.setStyleName(Styles.TRANS_OUTPUT_DIV_STYLE);

    VerticalPanel wrapperPanel = new VerticalPanel();
    wrapperPanel.setWidth("100%");

    VerticalPanel demoPanel = getDemoPanel();
    wrapperPanel.add(demoPanel);
    wrapperPanel.setCellHorizontalAlignment(demoPanel,
        HasHorizontalAlignment.ALIGN_CENTER);

    Widget branding = Translation.createBrandingWidget(BrandingOptions.newInstance(BrandingOptions.Type.VERTICAL));
    wrapperPanel.add(branding);
    wrapperPanel.setCellHorizontalAlignment(branding,
        HasHorizontalAlignment.ALIGN_RIGHT);

    initWidget(wrapperPanel);
  }

  /**
   * Creates a "Translate" button.
   *
   * @return button
   */
  private Button createTranslateButton() {
    Button translateButton = new Button("Translate");
    translateButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        Language src = Language.valueOf(sourceLanguages.getItemText(sourceLanguages.getSelectedIndex()));
        Language dest = Language.valueOf(destinationLanguages.getItemText(destinationLanguages.getSelectedIndex()));

        // Translation API call to translate text
        Translation.translate(inputTextArea.getText(), src, dest,
            new TranslationCallback() {
              @Override
              protected void onCallback(TranslationResult result) {
                if (result.getError() == null) {
                  outputDiv.setText(result.getTranslatedText());
                } else {
                  outputDiv.setText(result.getError().getMessage());
                }
              }
            });
      }
    });
    return translateButton;
  }

  /**
   * Creates translation control panel containing list boxes for source and
   * destination languages, and a button to translate.
   *
   * @return panel containing controls
   */
  private HorizontalPanel createTranslationControlPanel() {
    populateListBoxes();

    HorizontalPanel listBoxesPanel = new HorizontalPanel();
    listBoxesPanel.add(sourceLanguages);
    listBoxesPanel.add(new Label(">>"));
    listBoxesPanel.add(destinationLanguages);

    Button translateButton = createTranslateButton();

    HorizontalPanel controlPanel = new HorizontalPanel();
    controlPanel.setSpacing(10);
    controlPanel.setWidth("100%");
    controlPanel.add(listBoxesPanel);
    controlPanel.add(translateButton);
    controlPanel.setCellHorizontalAlignment(translateButton,
        HasHorizontalAlignment.ALIGN_RIGHT);

    return controlPanel;
  }

  /**
   * Contains the entire demo panel.
   *
   * @return demo panel
   */
  private VerticalPanel getDemoPanel() {
    VerticalPanel demoPanel = new VerticalPanel();
    demoPanel.add(new Label("Enter source language text: "));
    demoPanel.add(inputTextArea);
    demoPanel.add(createTranslationControlPanel());
    demoPanel.add(new Label("Translated text:"));
    demoPanel.add(outputDiv);
    return demoPanel;
  }

  /**
   * Populates list boxes with source and destination languages.
   */
  private void populateListBoxes() {
    int englishIndex = 0;
    int spanishIndex = 0;
    int iter = 0;

    // Populate source & destination language list boxes.
    for (Language lang : Language.values()) {
      if (!Translation.isTranslatable(lang)) {
        continue;
      }

      sourceLanguages.addItem(lang.toString());
      destinationLanguages.addItem(lang.toString());
      if (lang == Language.ENGLISH) {
        englishIndex = iter;
      } else if (lang == Language.SPANISH) {
        spanishIndex = iter;
      }
      ++iter;
    }

    // Select English as default source and Spanish as default destination
    // languages.
    sourceLanguages.setSelectedIndex(englishIndex);
    destinationLanguages.setSelectedIndex(spanishIndex);
  }
}

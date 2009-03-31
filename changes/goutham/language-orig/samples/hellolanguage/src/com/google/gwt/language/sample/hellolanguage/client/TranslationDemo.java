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

import com.google.gwt.language.client.translation.LangDetCallback;
import com.google.gwt.language.client.translation.LangDetResult;
import com.google.gwt.language.client.translation.Language;
import com.google.gwt.language.client.translation.Translation;
import com.google.gwt.language.client.translation.TranslationCallback;
import com.google.gwt.language.client.translation.TranslationResult;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Translation demo.
 *
 * TODO: Consider styling this demo before release.
 */
public class TranslationDemo {

  private final VerticalPanel mainPanel = new VerticalPanel();
  private final TextArea translationArea = new TextArea();

  public TranslationDemo() {

    // Construct panel
    mainPanel.add(new Label("Enter text:"));
    mainPanel.add(translationArea);

    VerticalPanel vPanel = new VerticalPanel();
    vPanel.setBorderWidth(1);
    vPanel.add(createTranslationPanel());
    vPanel.add(createLangDetectionPanel());
    mainPanel.add(vPanel);
  }

  /**
   * Returns the panel associated with this demo.
   *
   * @return the main panel
   */
  public Panel getDemoPanel() {
    return mainPanel;
  }

  /**
   * Returns language detection demo panel.
   *
   * @return language detection demo panel
   */
  private Panel createLangDetectionPanel() {
    VerticalPanel vPanel = new VerticalPanel();
    Button langDetectionButton = new Button("Detect language");
    final Label detectedLanguage = new Label();
    final Label waiting = new Label();

    vPanel.add(new Label("Language detection:"));
    vPanel.add(langDetectionButton);
    vPanel.add(new Label("Detected language:"));
    vPanel.add(detectedLanguage);
    vPanel.add(waiting);

    langDetectionButton.addClickListener(new ClickListener() {

      public void onClick(Widget sender) {
        detectedLanguage.setText("");
        waiting.setText("Waiting for detection results...");

        // Translation API call to detect language of text
        Translation.detect(translationArea.getText(), new LangDetCallback() {
          @Override
          protected void onCallback(LangDetResult result) {
            detectedLanguage.setText(result.getLanguage());
            waiting.setText("");
          }
        });
      }
    });
    return vPanel;
  }

  /**
   * Translation demo panel.
   *
   * TODO: Consider some RTL languages also.
   *
   * @return translation demo panel
   */
  private Panel createTranslationPanel() {
    Button translate = new Button("Translate");
    final Label translatedText = new Label();
    final Label waiting = new Label();

    final ListBox sourceLanguages = new ListBox();
    sourceLanguages.addItem(Language.ENGLISH.toString());
    sourceLanguages.addItem(Language.FRENCH.toString());
    sourceLanguages.addItem(Language.SPANISH.toString());

    final ListBox destLanguages = new ListBox();
    destLanguages.addItem(Language.SPANISH.toString());
    destLanguages.addItem(Language.ENGLISH.toString());
    destLanguages.addItem(Language.FRENCH.toString());

    HorizontalPanel hPanel = new HorizontalPanel();
    hPanel.add(new Label("Source:"));
    hPanel.add(sourceLanguages);
    hPanel.add(new Label("Destination:"));
    hPanel.add(destLanguages);
    hPanel.add(translate);

    VerticalPanel vPanel = new VerticalPanel();
    vPanel.add(new Label("Language translation:"));
    vPanel.add(hPanel);
    vPanel.add(new Label("Translated text:"));
    vPanel.add(translatedText);
    vPanel.add(waiting);

    // TODO: for GWT 1.6, should use EventHandler event callbacks.
    translate.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
        Language src = Language.valueOf(sourceLanguages.getItemText(
            sourceLanguages.getSelectedIndex()));
        Language dest = Language.valueOf(destLanguages.getItemText(
            destLanguages.getSelectedIndex()));

        translatedText.setText("");
        waiting.setText("Waiting for translation results...");

        // Translation API call to translate text
        Translation.translate(translationArea.getText(), src, dest,
            new TranslationCallback() {

          @Override
          protected void onCallback(TranslationResult result) {
            translatedText.setText(result.getTranslatedText());
            waiting.setText("");
          }
        });
      }
    });

    return vPanel;
  }
}

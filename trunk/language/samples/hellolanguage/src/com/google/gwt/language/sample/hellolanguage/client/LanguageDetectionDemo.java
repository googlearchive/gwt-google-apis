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
import com.google.gwt.language.client.translation.LangDetCallback;
import com.google.gwt.language.client.translation.LangDetResult;
import com.google.gwt.language.client.translation.Translation;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Demo for language detection API.
 */
public class LanguageDetectionDemo extends Composite {

  private final TextArea inputTextArea = new TextArea();
  private final Label outputDiv = new Label();

  public LanguageDetectionDemo() {
    inputTextArea.setStyleName(Styles.DEMO_TEXTAREA_STYLE);
    outputDiv.setStyleName(Styles.LANG_DET_OUTPUT_DIV_STYLE);

    VerticalPanel demoPanel = createDemoPanel();

    VerticalPanel wrapperPanel = new VerticalPanel();
    wrapperPanel.setWidth("100%");
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
   * Creates a demo panel.
   *
   * @return demo panel
   */
  private VerticalPanel createDemoPanel() {
    VerticalPanel demoPanel = new VerticalPanel();
    demoPanel.add(new Label("Enter text:"));
    demoPanel.add(inputTextArea);

    HorizontalPanel hPanel = new HorizontalPanel();
    hPanel.setSpacing(10);
    hPanel.add(createLanguageDetectionButton());
    hPanel.add(new Label(">>"));
    hPanel.add(outputDiv);
    demoPanel.add(hPanel);

    return demoPanel;
  }

  /**
   * Creates a "Detect" button to do language detection.
   *
   * @return button
   */
  private Button createLanguageDetectionButton() {
    Button langDetButton = new Button("Detect");
    langDetButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        Translation.detect(inputTextArea.getText(), new LangDetCallback() {
          @Override
          protected void onCallback(LangDetResult result) {
            outputDiv.setText("Detected language: " + result.getLanguage()
                + ", Confidence: " + result.confidence());
          }
        });
      }
    });
    return langDetButton;
  }
}

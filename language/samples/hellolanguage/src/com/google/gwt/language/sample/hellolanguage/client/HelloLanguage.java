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

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.language.client.LanguageUtils;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Main class for GWT language API demo.
 */
public class HelloLanguage implements EntryPoint {

  private static final int DEMO_PANEL_HEIGHT = 400;
  /**
   * The onModuleLoad() method is called when the body of the document is
   * finished loading. The JavaScript APIs are not loaded unless they were
   * included in the body of the .html file. Use the LanguageUtils.loadXXX()
   * methods to load them after the app starts, but before any API calls are
   * made.
   */
  public void onModuleLoad() {

    final TabPanel demoTabPanel = new TabPanel();
    demoTabPanel.getDeckPanel().setPixelSize(Window.getClientWidth() - 30,
        DEMO_PANEL_HEIGHT);
    RootPanel.get().add(demoTabPanel);

    Window.addResizeHandler(new ResizeHandler() {
      public void onResize(ResizeEvent event) {
        demoTabPanel.getDeckPanel().setPixelSize(Window.getClientWidth() - 30,
            DEMO_PANEL_HEIGHT);
      }
    });

    final VerticalPanel transDemoPanel = new VerticalPanel();
    transDemoPanel.add(loadingLabel());

    final VerticalPanel langDetectDemoPanel = new VerticalPanel();
    langDetectDemoPanel.add(loadingLabel());

    final VerticalPanel translitDemoPanel = new VerticalPanel();
    translitDemoPanel.add(loadingLabel());

    demoTabPanel.add(transDemoPanel, "Translation demo");
    demoTabPanel.add(langDetectDemoPanel, "Language detection demo");
    demoTabPanel.add(translitDemoPanel, "Transliteration demo");
    demoTabPanel.selectTab(0);

    LanguageUtils.loadTranslation(new Runnable() {
      public void run() {
        transDemoPanel.clear();
        transDemoPanel.add(new TranslationDemo());

        langDetectDemoPanel.clear();
        langDetectDemoPanel.add(new LanguageDetectionDemo());
      }
    });

    LanguageUtils.loadTransliteration(new Runnable() {
      public void run() {
        translitDemoPanel.clear();
        TransliterationDemo translitDemo = new TransliterationDemo();
        translitDemoPanel.add(translitDemo);
      }
    });
  }

  private Label loadingLabel() {
    return new Label("Loading...");
  }
}

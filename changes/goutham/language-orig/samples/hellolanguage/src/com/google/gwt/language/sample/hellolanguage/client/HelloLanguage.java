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
import com.google.gwt.language.client.LanguageUtils;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class HelloLanguage implements EntryPoint {

  /**
   * The onModuleLoad() method is called when the body of the document is
   * finished loading. The JavaScript APIs are not loaded unless they were
   * included in the body of the .html file. Use the LanguageUtils.loadXXX()
   * methods to load them after the app starts, but before any API calls are
   * made.
   */
  public void onModuleLoad() {
    // TODO: Use a tabbed interface for the demo? 
    LanguageUtils.loadTranslation(new Runnable() {

      public void run() {
        TranslationDemo translationDemo = new TranslationDemo();
        RootPanel.get("translateDemo").add(translationDemo.getDemoPanel());
      }
    });
    LanguageUtils.loadTransliteration(new Runnable() {

      public void run() {
        TransliterationDemo transliterateDemo = new TransliterationDemo();
        RootPanel.get("transliterateDemo").add(transliterateDemo.getDemoPanel());
      }
    });
  }
}

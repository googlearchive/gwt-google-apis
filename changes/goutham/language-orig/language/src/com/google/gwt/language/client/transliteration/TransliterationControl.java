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

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Wrapper for google.elements.transliteration.TransliterationControl JS class.
 * Instances of this class are designed to enable transliteration on a set of
 * text fields in your page. Only one instance of this class can be created.
 * This is enforced by the underlying JS API.
 */
public class TransliterationControl extends JavaScriptObject {

  protected TransliterationControl() { }

  /**
   * Create an instance of TransliterationControl class. Only one instance of
   * this can be created per page.
   *
   * @param options the {@code Options} object containing required data
   * @return instance of this class
   */
  public static native TransliterationControl newInstance(Options options) /*-{
    return new $wnd.google.elements.transliteration.TransliterationControl(options);
  }-*/;

  /**
   * Makes the given {@code TransliteratableTextArea} transliteratable.
   *
   * @param textArea an instance of {@code TransliteratableTextArea}
   */
  public final void makeTransliteratable(TransliteratableTextArea textArea) {
    makeTransliteratable(textArea.getId());
  }

  /**
   * Makes textarea with given id transliteratable.
   *
   * @param textAreaId id of textarea
   */
  public final native void makeTransliteratable(String textAreaId) /*-{
    this.makeTransliteratable([textAreaId]);
  }-*/;

  /**
   * Shows the transliteration control under given div element.
   *
   * @param div the {@code TransliterationControlDiv} where control should be
   *          shown
   */
  public final void showControl(TransliterationControlDiv div) {
    showControl(div.getId());
  }

  /**
   * Shows transliteratoin control under div with given id.
   *
   * @param divId id of the div
   */
  public final native void showControl(String divId) /*-{
    this.showControl(divId);
  }-*/;
}

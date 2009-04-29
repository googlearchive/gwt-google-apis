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
package com.google.gwt.language.client.translation;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.language.client.Error;

/**
 * Wrapper for translation API Language Detection results object.
 */
public class LangDetResult extends JavaScriptObject {
  protected LangDetResult() {
  }

  /**
   * Returns a numeric value between 0-1.0 that represents the confidence level
   * in the language code for the given text.
   * 
   * @return confidence value as double.
   */
  public final native double confidence() /*-{
    return this.confidence;
  }-*/;

  /**
   * Present if there was an error loading the feed.
   * 
   * @return the {@code Error} object.
   */
  public final native Error getError() /*-{
    return this.error;
  }-*/;

  /**
   * The language code associated with the given text.
   * 
   * @return language code of text as string.
   */
  public final native String getLanguage() /*-{
    return this.language;
  }-*/;

  /**
   * A boolean representing whether or not the detection interval believes the
   * language code is reliable for the given text.
   * 
   * @return true if result is reliable, false otherwise.
   */
  public final native boolean isReliable() /*-{
    return this.isReliable;
  }-*/;
}

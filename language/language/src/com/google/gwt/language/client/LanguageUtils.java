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
package com.google.gwt.language.client;

import com.google.gwt.ajaxloader.client.AjaxLoader;
import com.google.gwt.ajaxloader.client.AjaxLoader.AjaxLoaderOptions;

/**
 * Utility class for the Google Language API.
 */
public class LanguageUtils {

  public static final String TRANSLATION_API_NAME = "language";
  public static final String TRANSLITERATION_API_NAME = "elements";

  /**
   * Call this method to load the Translation API before using any API methods.
   * This will load the jsapi script from www.google.com and invoke the
   * google.load() method.
   */
  public static void loadTranslation(Runnable onLoad) {
    assert (onLoad != null);
    AjaxLoader.loadApi(TRANSLATION_API_NAME, "1", onLoad, null);
  }

  /**
   * Call this method to load Transliteration API before using the API methods.
   * To specify extra options for loading transliteration API, you can
   * use {@code AjaxLoader.loadApi()} directly.
   */
  public static void loadTransliteration(Runnable onLoad) {
    assert (onLoad != null);
    AjaxLoaderOptions options = AjaxLoaderOptions.newInstance();
    options.setPackages("transliteration");
    AjaxLoader.loadApi(TRANSLITERATION_API_NAME, "1", onLoad, options);
  }

  private LanguageUtils() {
    // Do not instantiate this class.
  }
}

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
package com.google.gwt.search.client;

import com.google.gwt.ajaxloader.client.AjaxLoader;
import com.google.gwt.ajaxloader.client.AjaxLoader.AjaxLoaderOptions;

/**
 * Utility methods useful for using the Search API.
 */
public abstract class SearchUtils {
  public static final String SEARCH_API_NAME = "search";
public static final String SEARCH_API_VERSION = "1";
/**
 * Call this method to load the Language API before using any API methods.
 * This will load the jsapi script from www.google.com and invoke the
 * google.load() method.
 * 
 * @param onLoad callback to be invoked when API is finished loading.
 */
  public static void loadSearchApi(Runnable onLoad) {
    assert (onLoad != null);
    AjaxLoader.loadApi(SEARCH_API_NAME, SEARCH_API_VERSION, onLoad, null);
  }

  /**
   * Call this method to load the Language API before using any API methods.
   * This will load the jsapi script from www.google.com and invoke the
   * google.load() method.
   * 
   * @param onLoad callback to be invoked when API is finished loading.
   * @param options loader API options.  Note that not all options are valid for this API.
   */
  public static void loadSearchApi(Runnable onLoad, AjaxLoaderOptions options) {
    assert (onLoad != null);
    AjaxLoader.loadApi(SEARCH_API_NAME, SEARCH_API_VERSION, onLoad, options); 
  }
  
  /**
   * Call this method to load the Language API before using any API methods.
   * This will load the jsapi script from www.google.com and invoke the
   * google.load() method.
   * 
   * @param onLoad callback to be invoked when API is finished loading.
   * @param language language to use in for the UI components.
   * @param noCss turns off loading of the css stylesheets.  
   */
  public static void loadSearchApi(Runnable onLoad, String language, boolean noCss) {
    assert (onLoad != null);
    AjaxLoaderOptions options = AjaxLoaderOptions.newInstance();
    options.setLanguage(language);
    options.setNoCss(noCss);
    AjaxLoader.loadApi(SEARCH_API_NAME, SEARCH_API_VERSION, onLoad, options); 
  }
  
  private SearchUtils() {
    // Do not instantiate this class.
  }
}

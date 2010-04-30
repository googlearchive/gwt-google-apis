/*
 * Copyright 2008 Google Inc.
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

import com.google.gwt.core.client.JsArray;
import com.google.gwt.junit.client.GWTTestCase;

/**
 * Some basic tests for the AJAX Search API.
 */
public class NewsSearchTest extends GWTTestCase {
  // length of time to wait for asynchronous test to complete.
  static final int ASYNC_DELAY_MSEC = 15000;

  @Override
  public String getModuleName() {
    return "com.google.gwt.search.SearchTest";
  }

  /**
   * Exercise some setters.
   */
  public void testnewsSearchOptions() {
    NewsSearch newsSearch = new NewsSearch();
    newsSearch.setResultSetSize(ResultSetSize.LARGE);
    newsSearch.setUserDefinedClassSuffix("foo");
    newsSearch.setUserDefinedLabel("myLabel");
  }

  /**
   * Run a basic real query and check to see that something came back.
   */
  public void testnewsSearch1() {
    SearchControlOptions options = new SearchControlOptions();
    NewsSearch newsSearch = new NewsSearch();
    newsSearch.setResultSetSize(ResultSetSize.SMALL);
    options.add(newsSearch);
    SearchControl searchControl = new SearchControl(options);
    searchControl.addSearchResultsHandler(new SearchResultsHandler() {
      public void onSearchResults(SearchResultsEvent event) {
        Search search = event.getSearch();
        JsArray<Result> results = event.getResults().cast();
        assertNotNull(results);
        assertTrue("results length", results.length() > 0);
        Result result = results.get(0);

        assertNotNull("news Search: search", search);
        assertNotNull("news Search: result", result);
        assertEquals("class name", NewsSearch.class.getName(),
            search.getClass().getName());
        NewsResult newsResult = NewsResult.isNewsResult(result);
        assertNotNull("isNewsResult", newsResult);
        assertNotNull("getClusterUrl()", newsResult.getClusterUrl());
        assertNotNull("getLocation()", newsResult.getLocation());
        assertNotNull("getPublishedDate()", newsResult.getPublishedDate());
        assertNotNull("getPublisher()", newsResult.getPublisher());
        assertNotNull("getRelatedStoreies()", newsResult.getRelatedStories());
        assertNotNull("getUnescapedUrl()", newsResult.getUnescapedUrl());
        assertNotNull("getTitle()", newsResult.getTitle());
        assertNotNull("getTitleNoFormatting()",
            newsResult.getTitleNoFormatting());
        assertNotNull("getUrl", newsResult.getUrl());
        finishTest();
      }

    });
    delayTestFinish(ASYNC_DELAY_MSEC);
    searchControl.execute("election");
  }

}

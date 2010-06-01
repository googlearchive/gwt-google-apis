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
public class BlogSearchTest extends GWTTestCase {
  // length of time to wait for asynchronous test to complete.
  static final int ASYNC_DELAY_MSEC = 15000;

  @Override
  public String getModuleName() {
    return "com.google.gwt.search.SearchTest";
  }

  /**
   * Exercise some setters.
   */
  public void testBlogSearchOptions() {
    BlogSearch blogSearch = new BlogSearch();
    blogSearch.setResultSetSize(ResultSetSize.LARGE);
    blogSearch.setUserDefinedClassSuffix("foo");
    blogSearch.setUserDefinedLabel("myLabel");
  }

  /**
   * Run a basic real query and check to see that something came back.
   */
  public void testBlogSearch1() {
    SearchControlOptions options = new SearchControlOptions();
    BlogSearch blogSearch = new BlogSearch();
    blogSearch.setResultSetSize(ResultSetSize.SMALL);
    options.add(blogSearch);
    SearchControl searchControl = new SearchControl(options);
    searchControl.addSearchResultsHandler(new SearchResultsHandler() {

      public void onSearchResults(SearchResultsEvent event) {
        Search search = event.getSearch();
        JsArray<Result> results = event.getResults().cast();
        assertNotNull(results);
        assertTrue("results length", results.length() > 0);
        Result result = results.get(0);

        assertNotNull("Blog Search: search", search);
        assertNotNull("Blog Search: result", result);
        assertEquals("class name", BlogSearch.class.getName(),
            search.getClass().getName());
                BlogResult blogResult = BlogResult.isBlogResult(result);
        assertNotNull("isBlogResult", blogResult);
        assertNotNull("getContent()", blogResult.getContent());
        assertNotNull("getAuthor()", blogResult.getAuthor());
        assertNotNull("getBlogUrl()", blogResult.getBlogUrl());
        assertNotNull("getPostUrl()", blogResult.getPostUrl());
        assertNotNull("getPublishedDate()", blogResult.getPublishedDate());
        assertNotNull("getTitle()", blogResult.getTitle());
        assertNotNull("getTitleNoFormatting()",
            blogResult.getTitleNoFormatting());
        finishTest();
      }
    });
    delayTestFinish(ASYNC_DELAY_MSEC);
    searchControl.execute("healthy food");
  }
}

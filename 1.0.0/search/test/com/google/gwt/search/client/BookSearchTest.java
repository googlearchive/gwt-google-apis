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

import com.google.gwt.junit.client.GWTTestCase;

/**
 * Some basic tests for the AJAX Search API.
 */
public class BookSearchTest extends GWTTestCase {
  // length of time to wait for asynchronous test to complete.
  static final int ASYNC_DELAY_MSEC = 15000;

  @Override
  public String getModuleName() {
    return "com.google.gwt.search.SearchTest";
  }
 
  /**
   * Exercise some setters.
   */
  public void testBookSearchOptions() {
    BookSearch bookSearch = new BookSearch();
    bookSearch.setResultSetSize(ResultSetSize.LARGE);
    bookSearch.setUserDefinedClassSuffix("foo");
    bookSearch.setUserDefinedLabel("myLabel");
    bookSearch.setSearchType(BookSearchType.ALL);
    bookSearch.setSearchType(BookSearchType.FULL_VIEW);
  }

  /**
   * Run a basic real query and check to see that something came back.
   */
  public void testBookSearch1() {
    SearchControlOptions options = new SearchControlOptions();
    BookSearch bookSearch = new BookSearch();
    bookSearch.setResultSetSize(ResultSetSize.SMALL);
    options.add(bookSearch);
    SearchControl searchControl = new SearchControl(options);
    searchControl.addSearchCompleteHandler(new SearchCompleteHandler() {
      public void onSearchComplete(SearchCompleteEvent event) {
        Search search = event.getSearch();
        Result result = event.getResult();

        assertNotNull("Book Search: search", search);
        assertNotNull("Book Search: result", result);
        assertEquals("class name", BookSearch.class.getName(),
            search.getClass().getName());
        assertEquals("Result class name", BookResult.class.getName(),
            result.getClass().getName());
        BookResult bookResult = (BookResult) result;
        
        assertNotNull("getUnescapedUrl()", bookResult.getUnescapedUrl());
        // assertNotNull("getUrl", bookResult.getUrl()); // no such method in bindings
        assertNotNull("getAuthors()", bookResult.getAuthors());
        assertNotNull("getBookId()", bookResult.getBookId());
        assertNotNull("getPublishedYear()", bookResult.getPublishedYear());
        assertNotNull("getPageCount()", bookResult.getPageCount());
        assertNotNull("getThumbnailHtml()", bookResult.getThumbnailHtml());
        assertNotNull("getTitle()", bookResult.getTitle());
        assertNotNull("getTitleNoFormatting()", bookResult.getTitleNoFormatting());
        finishTest();
      }

    });
    delayTestFinish(ASYNC_DELAY_MSEC);
    searchControl.execute("microsoft");
  }

}

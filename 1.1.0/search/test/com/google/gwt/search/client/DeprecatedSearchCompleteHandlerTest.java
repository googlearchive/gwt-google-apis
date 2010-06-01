/*
 * Copyright 2010 Google Inc.
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
public class DeprecatedSearchCompleteHandlerTest extends GWTTestCase {
  // length of time to wait for asynchronous test to complete.
  static final int ASYNC_DELAY_MSEC = 15000;
  static final String QUERY_STRING = "AJAX";

  @Override
  public String getModuleName() {
    return "com.google.gwt.search.SearchTest";
  }

  /**
   * Test a LocalResult's fields.
   */
  public void testSearchWithSearchComplete() {
    LocalSearch local = new LocalSearch();
    local.setResultSetSize(ResultSetSize.SMALL);
    local.setCenterPoint("10 10th Street NW, Atlanta, GA USA");

    local.addSearchCompleteHandler(new SearchCompleteHandler() {
      int resultCount = 0;

      public void onSearchComplete(SearchCompleteEvent event) {
        Search search = event.getSearch();
        Result result = event.getResult();

        resultCount++;
        if (resultCount > 1) {
          return;
        }

        assertNotNull("Simple Local Search: search", search);
        assertNotNull("Simple Local Search: result", result);
        assertEquals("Search class name", LocalSearch.class.getName(),
            search.getClass().getName());
        assertEquals("Result class name", LocalResult.class.getName(),
            result.getClass().getName());

        LocalResult localResult = (LocalResult) result;
        String ddUrl = localResult.getDdUrl();
        String ddUrlFrom = localResult.getDdUrlFromHere();
        String ddUrlTo = localResult.getDdUrlToHere();

        finishTest();
      }

    });
    delayTestFinish(ASYNC_DELAY_MSEC);
    local.execute("pizza");
  }

  public void testSearchControlWithSearchComplete() {
    SearchControlOptions options = new SearchControlOptions();
    WebSearch ws = new WebSearch();
    ws.setResultSetSize(ResultSetSize.SMALL);
    options.add(ws);
    SearchControl searchControl = new SearchControl(options);
    searchControl.addSearchCompleteHandler(new SearchCompleteHandler() {
      int resultCount = 0;

      public void onSearchComplete(SearchCompleteEvent event) {
        Search search = event.getSearch();
        Result result = event.getResult();

        resultCount++;
        if (resultCount > 1) {
          return;
        }

        assertNotNull("Simple Image Search: search", search);
        assertNotNull("Simple Image Search: result", result);
        assertEquals("Search class name", WebSearch.class.getName(),
            search.getClass().getName());
        assertEquals("Result class name", WebResult.class.getName(),
            result.getClass().getName());
        finishTest();
      }

    });
    delayTestFinish(ASYNC_DELAY_MSEC);
    searchControl.execute(QUERY_STRING);
  }
}

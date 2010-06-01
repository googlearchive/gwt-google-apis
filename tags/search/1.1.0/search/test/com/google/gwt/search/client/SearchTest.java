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
public class SearchTest extends GWTTestCase {
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
  @SuppressWarnings("unused")
  public void testLocalSearch() {
    LocalSearch local = new LocalSearch();
    local.setResultSetSize(ResultSetSize.SMALL);
    local.setCenterPoint("10 10th Street NW, Atlanta, GA USA");

    local.addSearchResultsHandler(new SearchResultsHandler() {

      public void onSearchResults(SearchResultsEvent event) {
        Search search = event.getSearch();
        JsArray<Result> results = event.getResults().cast();
        assertNotNull(results);
        assertTrue("results length", results.length() > 0);
        Result result = results.get(0);

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

  /**
   * Test a Handler attached to a Search object.
   */
  public void testSearchSearchHandler() {
    WebSearch ws = new WebSearch();
    ws.setResultSetSize(ResultSetSize.SMALL);
    ws.addSearchResultsHandler(new SearchResultsHandler() {

      public void onSearchResults(SearchResultsEvent event) {
        Search search = event.getSearch();
        JsArray<Result> results = event.getResults().cast();
        assertNotNull(results);
        assertTrue("results length", results.length() > 0);
        Result result = results.get(0);

        assertNotNull("Simple Image Search: search", search);
        assertNotNull("Simple Image Search: result", result);
        assertEquals("Search class name", WebSearch.class.getName(),
            search.getClass().getName());
        WebResult webResult = WebResult.isWebResult(result);
        assertNotNull("isWebResult()", webResult);
        finishTest();
      }

    });
    delayTestFinish(ASYNC_DELAY_MSEC);
    ws.execute(QUERY_STRING);
  }

  public void testSearchStartingCallback() {
    SearchControlOptions options = new SearchControlOptions();
    final WebSearch ws = new WebSearch();
    ws.setResultSetSize(ResultSetSize.SMALL);
    options.add(ws);
    final SearchControl searchControl = new SearchControl(options);
    searchControl.addSearchStartingHandler(new SearchStartingHandler() {

      public void onSearchStarting(SearchStartingEvent event) {
        SearchControl control = event.getSearchControl();
        Search search = event.getSearch();
        String query = event.getQuery();

        assertEquals("getSearchControl()", searchControl, control);
        assertEquals("getSearch()", ws, search);
        assertEquals("getQuery()", QUERY_STRING, query);
        finishTest();
      }

    });
    delayTestFinish(ASYNC_DELAY_MSEC);
    searchControl.execute(QUERY_STRING);
  }

  public void testSimpleQuery() {
    SearchControlOptions options = new SearchControlOptions();
    WebSearch ws = new WebSearch();
    ws.setResultSetSize(ResultSetSize.SMALL);
    options.add(ws);
    SearchControl searchControl = new SearchControl(options);
    searchControl.addSearchResultsHandler(new SearchResultsHandler() {
      public void onSearchResults(SearchResultsEvent event) {
        Search search = event.getSearch();
        JsArray<Result> results = event.getResults().cast();
        assertNotNull(results);
        assertTrue("results length", results.length() > 0);
        Result result = results.get(0);

        assertNotNull("Simple Image Search: search", search);
        assertNotNull("Simple Image Search: result", result);
        assertEquals("Search class name", WebSearch.class.getName(),
            search.getClass().getName());
        WebResult webResult = WebResult.isWebResult(result);
        assertNotNull("isWebResult()", webResult);
        finishTest();
      }

    });
    delayTestFinish(ASYNC_DELAY_MSEC);
    searchControl.execute(QUERY_STRING);
  }

  /**
   * Test the getResults() method.
   */
  public void testGetResults() {
    WebSearch ws = new WebSearch();
    ws.setResultSetSize(ResultSetSize.SMALL);
    ws.addSearchResultsHandler(new SearchResultsHandler() {
      public void onSearchResults(SearchResultsEvent event) {
        Search search = event.getSearch();
        JsArray<? extends Result> results = search.getResults();
        assertNotNull(results);
        assertTrue(results.length() > 0);
        for (int i = 0; i < results.length(); ++i) {
          Result res = results.get(i);
          assertNotNull(res);
        }

        finishTest();
      }

    });
    delayTestFinish(ASYNC_DELAY_MSEC);
    ws.execute(QUERY_STRING);
  }
}

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
public class SearchTest extends GWTTestCase {
  // length of time to wait for asynchronous test to complete.
  static final int ASYNC_DELAY_MSEC = 15000;

  @Override
  public String getModuleName() {
    return "com.google.gwt.search.SearchTest";
  }

  public void testSimpleQuery() {
    SearchControlOptions options = new SearchControlOptions();
    WebSearch ws = new WebSearch();
    ws.setResultSetSize(ResultSetSize.SMALL);
    options.add(ws);
    SearchControl searchControl = new SearchControl(options);
    searchControl.addSearchListener(new SearchListener() {

      public void onSearchResult(Search search, Result result) {
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
    searchControl.execute("AJAX");
  }
  
  /**
   * Test a listener attached to a Search object.
   */
  public void testSearchSearchListener() {
    SearchControlOptions options = new SearchControlOptions();
    WebSearch ws = new WebSearch();
    ws.setResultSetSize(ResultSetSize.SMALL);
    ws.addSearchListener(new SearchListener() {

      public void onSearchResult(Search search, Result result) {
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
    ws.execute("AJAX");
  }

}

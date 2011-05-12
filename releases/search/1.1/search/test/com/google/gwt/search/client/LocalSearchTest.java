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
public class LocalSearchTest extends GWTTestCase {
  // length of time to wait for asynchronous test to complete.
  static final int ASYNC_DELAY_MSEC = 15000;

  @Override
  public String getModuleName() {
    return "com.google.gwt.search.SearchTest";
  }

  /**
   * Exercise some setters.
   */
  public void testLocalSearchOptions() {
    LocalSearch localSearch = new LocalSearch();
    localSearch.setResultSetSize(ResultSetSize.LARGE);
    localSearch.setUserDefinedClassSuffix("foo");
    localSearch.setUserDefinedLabel("myLabel");
    localSearch.setCenterPoint("(45,45)");
  }

  /**
   * Run a basic real query and check to see that something came back.
   */
  public void testLocalSearch1() {
    SearchControlOptions options = new SearchControlOptions();
    LocalSearch localSearch = new LocalSearch();
    localSearch.setResultSetSize(ResultSetSize.SMALL);
    options.add(localSearch);
    SearchControl searchControl = new SearchControl(options);
    searchControl.addSearchResultsHandler(new SearchResultsHandler() {
      public void onSearchResults(SearchResultsEvent event) {
        Search search = event.getSearch();
        JsArray<Result> results = event.getResults().cast();
        assertNotNull(results);
        assertTrue("results length", results.length() > 0);
        Result result = results.get(0);

        assertNotNull("Local Search: search", search);
        assertNotNull("Local Search: result", result);
        assertEquals("class name", LocalSearch.class.getName(),
            search.getClass().getName());
        LocalResult localResult = LocalResult.isLocalResult(result);
        assertNotNull("isLocalResult", localResult);
        assertNotNull("getLat()", localResult.getLat());
        assertNotNull("getLng()", localResult.getLng());
        assertNotNull("getStreetAddress()", localResult.getStreetAddress());
        assertNotNull("getAddressLines()", localResult.getAddressLines());
        assertNotNull("getCity()", localResult.getCity());
        assertNotNull("getRegion()", localResult.getRegion());
        assertNotNull("getCountry()", localResult.getCountry());
        assertNotNull("getPhoneNumbers()", localResult.getPhoneNumbers());
        assertNotNull("getPhoneNumbers()", localResult.getDdUrl());
        assertNotNull("getDdUrlToHere()", localResult.getDdUrlToHere());
        assertNotNull("getDdUrlFromHere()", localResult.getDdUrlFromHere());
        assertNotNull("getTitle()", localResult.getTitle());
        assertNotNull("getTitleNoFormatting()",
            localResult.getTitleNoFormatting());
        assertNotNull("getUrl", localResult.getUrl());

        finishTest();
      }

    });
    delayTestFinish(ASYNC_DELAY_MSEC);
    searchControl.execute("post office");
  }

}

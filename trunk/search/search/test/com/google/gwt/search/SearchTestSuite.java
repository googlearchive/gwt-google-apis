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
package com.google.gwt.search;

import com.google.gwt.junit.tools.GWTTestSuite;
import com.google.gwt.search.client.BlogSearchTest;
import com.google.gwt.search.client.BookSearchTest;
import com.google.gwt.search.client.ImageSearchTest;
import com.google.gwt.search.client.LocalSearchTest;
import com.google.gwt.search.client.NewsSearchTest;
import com.google.gwt.search.client.DeprecatedSearchCompleteHandlerTest;
import com.google.gwt.search.client.SearchTest;
import com.google.gwt.search.client.VideoSearchTest;

import junit.framework.TestSuite;

/**
 * Test suite for the AJAX Search APIs.
 */
public class SearchTestSuite extends GWTTestSuite {
  public static TestSuite suite() {
    TestSuite suite = new TestSuite("Test for the AJAX Search API");
    suite.addTestSuite(SearchTest.class);
    suite.addTestSuite(ImageSearchTest.class);
    suite.addTestSuite(BlogSearchTest.class);
    suite.addTestSuite(BookSearchTest.class);
    suite.addTestSuite(LocalSearchTest.class);
    suite.addTestSuite(NewsSearchTest.class);
    suite.addTestSuite(VideoSearchTest.class);
    suite.addTestSuite(DeprecatedSearchCompleteHandlerTest.class);
    return suite;
  }
}

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
public class VideoSearchTest extends GWTTestCase {
  // length of time to wait for asynchronous test to complete.
  static final int ASYNC_DELAY_MSEC = 15000;

  @Override
  public String getModuleName() {
    return "com.google.gwt.search.SearchTest";
  }

  /**
   * Exercise some setters.
   */
  public void testVideoSearchOptions() {
    VideoSearch videoSearch = new VideoSearch();
    videoSearch.setResultSetSize(ResultSetSize.LARGE);
    videoSearch.setUserDefinedClassSuffix("foo");
    videoSearch.setUserDefinedLabel("myLabel");
  }

  /**
   * Run a basic real query and check to see that something came back.
   */
  public void testVideoSearch1() {
    SearchControlOptions options = new SearchControlOptions();
    VideoSearch videoSearch = new VideoSearch();
    videoSearch.setResultSetSize(ResultSetSize.SMALL);
    options.add(videoSearch);
    SearchControl searchControl = new SearchControl(options);
    searchControl.addSearchCompleteHandler(new SearchCompleteHandler() {
      public void onSearchComplete(SearchCompleteEvent event) {
        Search search = event.getSearch();
        Result result = event.getResult();

        assertNotNull("Video Search: search", search);
        assertNotNull("Video Search: result", result);
        assertEquals("class name", VideoSearch.class.getName(),
            search.getClass().getName());
        assertEquals("Result class name", VideoResult.class.getName(),
            result.getClass().getName());
        VideoResult videoResult = (VideoResult) result;

        String author = videoResult.getAuthor();
        assertTrue("getAuthor()", author == null || author.length() > 0);
        assertTrue("getDuration()", videoResult.getDuration() > 0);
        assertNotNull("getPlayUrl()", videoResult.getPlayUrl());
        assertNotNull("getPublished()", videoResult.getPublished());
        assertNotNull("getPublisedAsString()",
            videoResult.getPublishedAsString());
        assertNotNull("getPublisher()", videoResult.getPublisher());
        assertTrue("getRating()", videoResult.getRating() >= 0);
        assertTrue("getTbHeight()", videoResult.getTbHeight() > 0);
        assertTrue("getTbWidth()", videoResult.getTbWidth() > 0);
        assertNotNull("getTbUrl()", videoResult.getTbUrl());
        assertTrue("getViewCount()", videoResult.getViewCount() >= -1);

        assertNotNull("getTitle()", videoResult.getTitle());
        assertNotNull("getTitleNoFormatting()",
            videoResult.getTitleNoFormatting());
        assertNotNull("getUrl", videoResult.getUrl());

        finishTest();
      }

    });
    delayTestFinish(ASYNC_DELAY_MSEC);
    searchControl.execute("guitar");
  }
}

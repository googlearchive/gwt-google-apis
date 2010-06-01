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
public class ImageSearchTest extends GWTTestCase {
  // length of time to wait for asynchronous test to complete.
  static final int ASYNC_DELAY_MSEC = 15000;

  @Override
  public String getModuleName() {
    return "com.google.gwt.search.SearchTest";
  }

  /**
   * Exercise each of the search restriction methods and value combinations.
   */
  public void testImageSearchOptions() {
    ImageSearch imgSearch = new ImageSearch();
    imgSearch.clearColorization();
    imgSearch.clearFileType();
    imgSearch.clearImageSize();

    // Try setting the search restrictions. They are mutually exclusive, but at
    // least this will exercise the parameter passing logic.

    imgSearch.setColorization(ColorizationValue.BLACK_AND_WHITE);
    imgSearch.setColorization(ColorizationValue.GRAYSCALE);
    imgSearch.setColorization(ColorizationValue.COLOR);

    imgSearch.setFileType(FileTypeValue.BMP);
    imgSearch.setFileType(FileTypeValue.GIF);
    imgSearch.setFileType(FileTypeValue.JPG);
    imgSearch.setFileType(FileTypeValue.PNG);

    imgSearch.setImageSize(ImageSizeValue.EXTRA_LARGE);
    imgSearch.setImageSize(ImageSizeValue.LARGE);
    imgSearch.setImageSize(ImageSizeValue.MEDIUM);
    imgSearch.setImageSize(ImageSizeValue.SMALL);

    imgSearch.setImageType(ImageTypeValue.FACES);

    imgSearch.setSafeSearch(SafeSearchValue.MODERATE);
    imgSearch.setSafeSearch(SafeSearchValue.OFF);
    imgSearch.setSafeSearch(SafeSearchValue.STRICT);
  }

  /**
   * Run a basic real query and check to see that something came back.
   */
  public void testImageSearch1() {
    SearchControlOptions options = new SearchControlOptions();
    ImageSearch imgSearch = new ImageSearch();
    imgSearch.setResultSetSize(ResultSetSize.SMALL);
    options.add(imgSearch);
    SearchControl searchControl = new SearchControl(options);
    searchControl.addSearchResultsHandler(new SearchResultsHandler() {
      public void onSearchResults(SearchResultsEvent event) {
        Search search = event.getSearch();
        JsArray<Result> results = event.getResults().cast();
        assertNotNull(results);
        assertTrue("results length", results.length() > 0);
        Result result = results.get(0);

        assertNotNull("Image Search: search", search);
        assertNotNull("Image Search: result", result);
        assertEquals("class name", ImageSearch.class.getName(),
            search.getClass().getName());
        ImageResult iResult = ImageResult.isImageResult(result);
        assertNotNull("isImageResult", iResult);        
        assertNotNull("getContent()", iResult.getContent());
        assertNotNull("getContentNoFormatting",
            iResult.getContentNoFormatting());
        finishTest();
      }

    });
    delayTestFinish(ASYNC_DELAY_MSEC);
    searchControl.execute("ferrari");
  }

  /**
   * Test the search restriction options in a real query and each property in
   * the returned result more thoroughly.
   */
  public void testImageSearch2() {
    SearchControlOptions options = new SearchControlOptions();
    ImageSearch imgSearch = new ImageSearch();

    imgSearch.setResultSetSize(ResultSetSize.SMALL);
    imgSearch.setImageType(ImageTypeValue.FACES);
    imgSearch.setColorization(ColorizationValue.COLOR);
    imgSearch.setFileType(FileTypeValue.JPG);
    imgSearch.setImageSize(ImageSizeValue.MEDIUM);

    options.add(imgSearch);
    SearchControl searchControl = new SearchControl(options);
    searchControl.addSearchResultsHandler(new SearchResultsHandler() {
      int resultCount = 0;

      public void onSearchResults(SearchResultsEvent event) {
        resultCount++;
        if (resultCount > 1) {
          return;
        }
        Search search = event.getSearch();
        JsArray<Result> results = event.getResults().cast();
        assertNotNull(results);
        assertTrue("results length", results.length() > 0);
        Result result = results.get(0);

        assertNotNull("Image Search: search", search);
        assertNotNull("Image Search: result", result);
        assertEquals("Search class name", ImageSearch.class.getName(),
            search.getClass().getName());
        assertEquals("class name", ImageSearch.class.getName(),
            search.getClass().getName());
        ImageResult iResult = ImageResult.isImageResult(result);
        assertNotNull("isImageResult", iResult);
        assertNotNull("getContent()", iResult.getContent());
        assertNotNull("getContentNoFormatting",
            iResult.getContentNoFormatting());
        assertTrue("getHeight() > 0", iResult.getHeight() > 0);
        assertNotNull("getOriginalContextUrl()",
            iResult.getOriginalContextUrl());
        assertTrue("getThumbnailHeight > 0", iResult.getThumbnailHeight() > 0);
        assertNotNull("getThumbnailUrl()", iResult.getThumbnailUrl());
        assertTrue("getThumbnailWidth > 0", iResult.getThumbnailWidth() > 0);
        assertNotNull("getTitle()", iResult.getTitle());
        assertNotNull("getTitleNoFormatting", iResult.getTitleNoFormatting());
        assertNotNull("getUnescapedUrl", iResult.getUnescapedUrl());
        assertNotNull("getUrl", iResult.getUrl());
        assertNotNull("getUnescapedUrl", iResult.getUnescapedUrl());
        assertNotNull("getVisibleUrl", iResult.getVisibleUrl());
        assertTrue("getWidth()", iResult.getWidth() > 0);
        finishTest();
      }

    });
    delayTestFinish(ASYNC_DELAY_MSEC);
    searchControl.execute("madonna");
  }
}

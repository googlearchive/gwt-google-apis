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

import com.google.gwt.core.client.JsArray;

/**
 * Accepts search results.
 * 
 * @see Search#addSearchResultsHandler(SearchResultsHandler)
 * @see SearchControl#addSearchResultsHandler(SearchResultsHandler)
 */
public interface SearchResultsHandler {

  /**
   * A container for arguments of the Search callback.
   */
  static class SearchResultsEvent {
    private Search search;
    private JsArray<? extends Result> results;

    SearchResultsEvent(Search search, JsArray<? extends Result> results) {
      this.search = search;
      this.results = results;
    }

    /**
     * Returns the list of {@link Result} objects selected by the user.
     * 
     * @return the list of {@link Result} objects selected by the user.
     */
    public JsArray<? extends Result> getResults() {
      return results;
    }

    /**
     * Returns the {@link Search} that the user interacted with.
     * 
     * @return the {@link Search} that the user interacted with.
     */
    public Search getSearch() {
      return search;
    }
  }

  /**
   * Invoked when a Search has new results. The exact subtype of the Result will
   * vary based on the type of Search that created it. This event is invoked
   * once for each batch of results returned from the Google Search service.
   * 
   * @param event contains the arguments of the callback.
   */
  void onSearchResults(SearchResultsEvent event);
}

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

/**
 * Accepts search results.
 * 
 * @see SearchControl#addSearchStartingHandler(SearchStartingHandler)
 */
public interface SearchStartingHandler {

  /**
   * Container for the arguments to a SearchStarting callback.
   */
  public static class SearchStartingEvent {
    private String query;
    private Search search;
    private SearchControl searchControl;

    SearchStartingEvent(SearchControl searchControl, Search search, String query) {
      this.searchControl = searchControl;
      this.search = search;
      this.query = query;
    }

    /**
     * Returns the query being executed.
     * @return the query being executed.
     */
    public String getQuery() {
      return query;
    }

    /**
     * Returns the searcher that is about to start a search.
     * @return the searcher that is about to start a search.
     */
    public Search getSearch() {
      return search;
    }

    /**
     * Returns the search control. 
     * 
     * @return the search control. 
     */
    public SearchControl getSearchControl() {
      return searchControl;
    }
  }

  /**
   * Invoked when a Search has new results. The exact subtype of the Result will
   * vary based on the type of Search that created it.
   * 
   * @param event contains the parameters to the callback.
   * 
   */
  void onSearchStarting(SearchStartingEvent event);
}

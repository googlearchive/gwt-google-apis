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
 * @see Search#addSearchCompleteHandler(SearchCompleteHandler)
 * @see SearchControl#addSearchCompleteHandler(SearchCompleteHandler)
 */
public interface SearchCompleteHandler {

  /**
   * A container for arguments of the Search callback.
   */
  static class SearchCompleteEvent {
    private Search search;
    private Result result;

    SearchCompleteEvent(Search search, Result result) {
      this.search = search;
      this.result = result;
    }

    /**
     * Returns the {@link Result} selected by the user.
     * 
     * @return the {@link Result} selected by the user.
     */
    public Result getResult() {
      return result;
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
   * vary based on the type of Search that created it.
   * 
   * @param event contains the arguments of the callback.
   */
  void onSearchComplete(SearchCompleteEvent event);
}

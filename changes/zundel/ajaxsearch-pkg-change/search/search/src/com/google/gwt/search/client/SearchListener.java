/*
 * Copyright 2007 Google Inc.
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
 * @see Search#addSearchListener(SearchListener)
 * @see SearchControl#addSearchListener(SearchListener)
 */
public interface SearchListener {
  /**
   * Invoked when a Search has new results. The exact subtype of the Result will
   * vary based on the type of Search that created it.
   * 
   * @param search The Search that is providing results
   * @param result A Result obtained from the Search's execution.
   */
  void onSearchResult(Search search, Result result);
}

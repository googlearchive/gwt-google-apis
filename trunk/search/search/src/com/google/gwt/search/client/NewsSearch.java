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

import com.google.gwt.search.client.impl.GnewsSearch;

/**
 * A Google News search.
 */
public class NewsSearch extends Search {
  private static final GnewsSearch IMPL = GnewsSearch.IMPL;

  public NewsSearch() {
    super(IMPL);
  }

  /**
   * The default behavior of this searcher is to return results ordered by their
   * relevance. In some cases, it is useful to see results ordered by date. This
   * method may be used to change the result order.
   * 
   * @param order supplies the desired result order.
   */
  public void setResultOrder(ResultOrder order) {
    IMPL.setResultOrder(this, order.getValue());
  }

  /**
   * This method is used to restrict the set of news search results returned by
   * this searcher. To restrict results to all news results from the Seattle
   * Times, simply call this method with a value of "Seattle Times". To restrict
   * results to results from CNN, simply call with "CNN". The pattern is that
   * the title of the news source is seperated by either spaces or underscore
   * (e.g., "_"). To clear site restrictions, pass in a value of null.
   * 
   * @param site supplies a site restriction setting e.g., "Seattle Times",
   *          "CNN", etc.
   */
  public void setSiteRestriction(String site) {
    IMPL.setSiteRestriction(this, site);
  }
}

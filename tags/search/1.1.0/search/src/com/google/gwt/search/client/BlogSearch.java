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

import com.google.gwt.search.client.impl.GblogSearch;

/**
 * A Google blog search.
 */
public class BlogSearch extends Search {
  private static final GblogSearch IMPL = GblogSearch.IMPL;

  public BlogSearch() {
    super(IMPL);
  }

  /**
   * The default behavior of this searcher is to return results ordered by their
   * relevance. In some cases, it is useful to see results ordered by date. This
   * method may be used to change the result order.
   * 
   * @param order the desired result order.
   */
  public void setResultOrder(ResultOrder order) {
    IMPL.setResultOrder(this, order.getValue());
  }

  /**
   * This method is used to restrict the set of blog search results returned by
   * this searcher. To restrict results to all blog results on blogspot.com,
   * simply call this method with a value of "blogspot.com". To restrict results
   * to the Nintendo DS blog on Live Journal, simply call with
   * "http://community.livejournal.com/nintendo_ds/". To clear site
   * restrictions, pass in a value of null.
   * 
   * @param site supplies a site restriction setting e.g., "blogspot.com",
   *          "http://community.livejournal.com/nintendo_ds/", etc. 
   */
  public void setSiteRestriction(String site) {
    IMPL.setSiteRestriction(this, site);
  }
}

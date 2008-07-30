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

import com.google.gwt.search.client.impl.GwebSearch;

/**
 * A Google web search query.
 */
public class WebSearch extends Search {
  private static final GwebSearch IMPL = GwebSearch.IMPL;

  public WebSearch() {
    super(IMPL);
  }

  /**
   * This method is used to restrict the set of web search results returned by
   * this searcher. To restrict to www.amazon.com, simply call this method
   * passing in a value of "www.amazon.com". To clear site restrictions, pass in
   * a value of null.
   * 
   * @param site the value to restrict to.
   */
  public void setSiteRestriction(String site) {
    IMPL.setSiteRestriction(this, site);
  }

  /**
   * This method is used to restrict the set of web search results returned by
   * this searcher. See also <a
   * href="http://www.google.com/uds/samples/cse/csearch.html">Curriculum Search
   * Sample</a> and <a
   * href="http://www.google.com/uds/samples/cse/index.html">Custom Search
   * Engine Sample</a>.
   * 
   * @param site the value to restrict to.
   * @param refinement a <a
   *          href="http://www.google.com/coop/docs/cse/refinements.html">Custom
   *          Search Engine Refinement</a>
   */
  public void setSiteRestriction(String site, String refinement) {
    IMPL.setSiteRestriction(this, site, refinement);
  }

  /**
   * This method is used to restrict the set of web search results returned by
   * this searcher. See also: <a
   * href="http://www.google.com/uds/samples/cse/csearch.html">Curriculum Search
   * Sample</a> and <a
   * href="http://www.google.com/uds/samples/cse/index.html">Custom Search
   * Engine Sample</a>.
   * 
   * @param site the value to restrict to.
   * @param refinement an optional <a
   *          href="http://www.google.com/coop/docs/cse/refinements.html">Custom
   *          Search Engine Refinement</a>
   * @param moreResultsTemplate This specifies a URL template that will be used
   *          to generate the "More results" link at the bottom of a set of
   *          search results. The URL template must include a placeholder of
   *          "__QUERY__" and "__HL__". When generating the "More results" link,
   *          the system will replace these placeholders with the current query
   *          term and UI language.
   */
  public void setSiteRestriction(String site, String refinement,
      String moreResultsTemplate) {
    IMPL.setSiteRestriction(this, site, refinement, moreResultsTemplate);
  }
}

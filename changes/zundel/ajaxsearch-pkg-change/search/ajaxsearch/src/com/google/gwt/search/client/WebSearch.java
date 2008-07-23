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

  public void setSiteRestriction(String site) {
    IMPL.setSiteRestriction(this, site);
  }

  public void setSiteRestriction(String site, String refinement) {
    IMPL.setSiteRestriction(this, site, refinement);
  }

  public void setSiteRestriction(String site, String refinement,
      String moreResultsTemplate) {
    IMPL.setSiteRestriction(this, site, refinement, moreResultsTemplate);
  }
}

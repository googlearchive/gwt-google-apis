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
package com.google.gwt.ajaxsearch.client;

import com.google.gwt.ajaxsearch.client.impl.GbookSearch;

/**
 * A Google Book search.
 */
public class BookSearch extends Search {
  private static final GbookSearch IMPL = GbookSearch.IMPL;
  
  public BookSearch() {
    super(IMPL);
  }

  public void setRestriction(RestrictType type) {
    IMPL.setRestriction(this, type);
  }

  public void setRestriction(RestrictType type, RestrictValue value) {
    IMPL.setRestriction(this, type, value);
  }
}

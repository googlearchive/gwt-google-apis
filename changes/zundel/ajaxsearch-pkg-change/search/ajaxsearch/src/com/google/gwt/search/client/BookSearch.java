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

import com.google.gwt.search.client.impl.BookSearchRestrict;
import com.google.gwt.search.client.impl.GbookSearch;

/**
 * A Google Book search.
 */
public class BookSearch extends Search {
  private static final GbookSearch IMPL = GbookSearch.IMPL;

  public BookSearch() {
    super(IMPL);
  }

  /**
   * Remove the restriction of searching a particular library.
   */
  public void clearSearchLibrary() {
    IMPL.setRestriction(this, BookSearchRestrict.USER_LIST.getValue());
  }

  /**
   * Clear any previous setting of the search type restriction.
   */
  public void clearSearchType() {
    IMPL.setRestriction(this, BookSearchRestrict.TYPE.getValue());
  }

  /**
   * Clears a search restriction.
   * 
   * @deprecated use {@link #clearSearchType()} instead.
   * @param type Sets the kind of restriction.
   */
  public void setRestriction(RestrictType type) {
    IMPL.setRestriction(this, type.getValue());
  }

  /**
   * Sets or clears a search restriction.
   * 
   * @deprecated use {@link #setSearchType(BookSearchType)} or
   *             {@link #clearSearchType()} instead.
   * @param type Sets the kind of restriction.
   * @param value Sets the value of the restriction (or <code>null</code> to
   *          clear the restriction)
   */
  @Deprecated
  public void setRestriction(RestrictType type, RestrictValue value) {
    IMPL.setRestriction(this, type.getValue(), value.getValue());
  }

  /**
   * Restrict the search to a particular library.
   * 
   * @param libraryName
   */
  public void setSearchLibrary(String libraryName) {
    IMPL.setRestriction(this, BookSearchRestrict.TYPE.getValue(), libraryName);
  }

  /**
   * Restrict the search to a specific type.
   * 
   * @param value the type of books to restrict the books to.
   */
  public void setSearchType(BookSearchType value) {
    IMPL.setRestriction(this, BookSearchRestrict.TYPE.getValue(),
        value.getValue());
  }
}

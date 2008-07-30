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

import com.google.gwt.search.client.impl.GResult;
import com.google.gwt.search.client.impl.GbookResult;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.ui.Widget;

/**
 * Book search results.
 */
public class BookResult extends Result {

  BookResult(JavaScriptObject obj) {
    super(obj);
  }

  /**
   * Returns the list of authors of the book.
   * 
   * @return the list of authors of the book.
   */
  public String getAuthors() {
    return GbookResult.IMPL.getAuthors(this);
  }

  /**
   * Returns the identifier associated with the book. This is typically an ISBN.
   * 
   * @return the identifier associated with the book. This is typically an ISBN.
   */
  public String getBookId() {
    return GbookResult.IMPL.getBookId(this);
  }

  /**
   * Returns the number of pages contained within the book.
   * 
   * @return the number of pages contained within the book.
   */
  public int getPageCount() {
    return Integer.parseInt(GbookResult.IMPL.getPageCount(this));
  }

  /**
   * Returns the year that the book was published.
   * 
   * @return the year that the book was published.
   */
  public int getPublishedYear() {
    return Integer.parseInt(GbookResult.IMPL.getPublishedYear(this));
  }

  /**
   * Returns an html dom node that represents a thumbnail image of the books
   * cover. Note that if you have suppressed HTML generation (using
   * .setNoHtmlGeneration(), this property is not available.
   * 
   * @return Returns an html dom node that represents a thumbnail image of the
   *         books cover. Note that if you have suppressed HTML generation
   *         (using .setNoHtmlGeneration(), this property is not available.
   */
  public Widget getThumbnailHtml() {
    return makeWidget(GbookResult.IMPL.getThumbnailHtml(this));
  }

  /**
   * Returns the title of the book.
   * 
   * @return the title of the book.
   */
  public String getTitle() {
    return GbookResult.IMPL.getTitle(this);
  }

  /**
   * Returns the title, but unlike .title, this property is stripped of html
   * markup (e.g., <b>, <i>, etc.).
   * 
   * @return the title, but unlike .title, this property is stripped of html
   *         markup (e.g., &lt;b&gt;, &lt;i&gt;, etc.).
   */
  public String getTitleNoFormatting() {
    return GbookResult.IMPL.getTitleNoFormatting(this);
  }

  /**
   * Returns the raw URL of the result.
   * 
   * @return the raw URL of the result.
   */
  public String getUnescapedUrl() {
    return GbookResult.IMPL.getUnescapedUrl(this);
  }

  @Override
  GResult getImpl() {
    return GbookResult.IMPL;
  }
}

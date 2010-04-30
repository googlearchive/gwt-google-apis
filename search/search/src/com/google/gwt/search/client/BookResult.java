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

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;

/**
 * Book search results.
 */
public final class BookResult extends Result {
  public static final ResultClass RESULT_CLASS = ResultClass.BOOK_SEARCH_RESULT;

  public static BookResult isBookResult(Result result) {
    if (result.getResultClass().equals(RESULT_CLASS)) {
      return (BookResult) result;
    }
    return null;
  }

  protected BookResult() {
    // Required for overlay types
  }

  /**
   * Returns the list of authors of the book.
   * 
   * @return the list of authors of the book.
   */
  public native String getAuthors() /*-{
    return this.authors;
  }-*/;

  /**
   * Returns the identifier associated with the book. This is typically an ISBN.
   * 
   * @return the identifier associated with the book. This is typically an ISBN.
   */
  public native String getBookId() /*-{
    return this.bookId;
  }-*/;

  /**
   * Returns the number of pages contained within the book.
   * 
   * @return the number of pages contained within the book.
   */
  public native int getPageCount() /*-{
    return Number(this.pageCount);
  }-*/;

  /**
   * Returns the year that the book was published.
   * 
   * @return the year that the book was published.
   */
  public native int getPublishedYear() /*-{
    return Number(this.publishedYear);
  }-*/;

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
    return makeWidget(getThumbnailHtmlAsElement());
  }

  /**
   * Returns the title of the book.
   * 
   * @return the title of the book.
   */
  public native String getTitle() /*-{
    return this.title;
  }-*/;

  /**
   * Returns the title, but unlike .title, this property is stripped of html
   * markup (e.g., <b>, <i>, etc.).
   * 
   * @return the title, but unlike .title, this property is stripped of html
   *         markup (e.g., &lt;b&gt;, &lt;i&gt;, etc.).
   */
  public native String getTitleNoFormatting() /*-{
    return this.titleNoFormatting;
  }-*/;

  /**
   * Returns the raw URL of the result.
   * 
   * @return the raw URL of the result.
   */
  public native String getUnescapedUrl() /*-{
    return this.unescapedUrl;
  }-*/;

  /**
   * Returns an escaped version of the URL.
   * 
   * @return an escaped version of the URL.
   */
  public native String getUrl() /*-{
    return this.url;
  }-*/;

  private native Element getThumbnailHtmlAsElement() /*-{
    return this.thumbnailHtml;
  }-*/;
}

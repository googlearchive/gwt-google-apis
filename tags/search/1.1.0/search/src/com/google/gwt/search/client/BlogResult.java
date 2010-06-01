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

import java.util.Date;

/**
 * Blog search results.
 */
public final class BlogResult extends Result {
  public static final ResultClass RESULT_CLASS = ResultClass.BLOG_SEARCH_RESULT;

  public static BlogResult isBlogResult(Result result) {
    ResultClass resultClass = result.getResultClass();
    if (resultClass.equals(RESULT_CLASS)) {
      return (BlogResult) result;
    }
    return null;
  }

  protected BlogResult() {
    // Required for overlay types
  }

  /**
   * Returns the name of the author that wrote the blog post.
   * 
   * @return the name of the author that wrote the blog post
   */
  public native String getAuthor()/*-{
    return this.author;
  }-*/;

  /**
   * Returns the URL of the blog which contains the post. Typically, this URL is
   * displayed in green, beneath the blog search result and is linked to the
   * blog.
   * 
   * @return the URL of the blog which contains the post. Typically, this URL is
   *         displayed in green, beneath the blog search result and is linked to
   *         the blog.
   */
  public native String getBlogUrl() /*-{
    return this.blogUrl;
  }-*/;

  /**
   * Returns a snippet of content from the blog post associated with this search
   * result.
   * 
   * @return a snippet of content from the blog post associated with this search
   *         result.
   */
  public native String getContent() /*-{
    return this.content;
  }-*/;

  /**
   * Returns the URL to the blog post referenced in this search result.
   * 
   * @return the URL to the blog post referenced in this search result.
   */
  public native String getPostUrl() /*-{
    return this.postUrl;
  }-*/;

  /**
   * Returns the published date (rfc-822 format) of the blog post referenced by
   * this search result.
   * 
   * @return Returns the published date (rfc-822 format) of the blog post
   *         referenced by this search result.
   */
  public Date getPublishedDate() {
    return makeDate(getPublishedDateAsString());
  }

  /**
   * Returns the title of the blog post returned as a search result.
   * 
   * @return the title of the blog post returned as a search result.
   */
  public native String getTitle() /*-{
    return this.title;
  }-*/;

  /**
   * Returns the title, but unlike .title, this property is stripped of html
   * markup (e.g., &lt;b&gt;, &lt;i&gt;, etc.).
   * 
   * @return Returns the title, but unlike .title, this property is stripped of
   *         html markup (e.g., &lt;b&gt;, &lt;i&gt;, etc.).
   */
  public native String getTitleNoFormatting() /*-{
    return this.titleNoFormatting;
  }-*/;

  private native String getPublishedDateAsString()/*-{
    return this.publishedDate;
  }-*/;
}

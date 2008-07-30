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
import com.google.gwt.search.client.impl.GblogResult;
import com.google.gwt.core.client.JavaScriptObject;

import java.util.Date;

/**
 * Blog search results.
 */
public class BlogResult extends Result {

  BlogResult(JavaScriptObject obj) {
    super(obj);
  }

  /**
   * Returns the name of the author that wrote the blog post
   * 
   * @return the name of the author that wrote the blog post
   */
  public String getAuthor() {
    return GblogResult.IMPL.getAuthor(this);
  }

  /**
   * Returns the URL of the blog which contains the post. Typically, this URL is
   * displayed in green, beneath the blog search result and is linked to the
   * blog.
   * 
   * @return the URL of the blog which contains the post. Typically, this URL is
   *         displayed in green, beneath the blog search result and is linked to
   *         the blog.
   */
  public String getBlogUrl() {
    return GblogResult.IMPL.getBlogUrl(this);
  }

  /**
   * Returns a snippet of content from the blog post associated with this search
   * result.
   * 
   * @return a snippet of content from the blog post associated with this search
   *         result.
   */
  public String getContent() {
    return GblogResult.IMPL.getContent(this);
  }

  /**
   * Returns the URL to the blog post referenced in this search result.
   * 
   * @return the URL to the blog post referenced in this search result.
   */
  public String getPostUrl() {
    return GblogResult.IMPL.getPostUrl(this);
  }

  /**
   * Returns the published date (rfc-822 format) of the blog post referenced by
   * this search result.
   * 
   * @return Returns the published date (rfc-822 format) of the blog post
   *         referenced by this search result.
   */
  public Date getPublishedDate() {
    return makeDate(GblogResult.IMPL.getPublishedDate(this));
  }

  /**
   * Returns the title of the blog post returned as a search result.
   * 
   * @return the title of the blog post returned as a search result.
   */
  public String getTitle() {
    return GblogResult.IMPL.getTitle(this);
  }

  /**
   * Returns the title, but unlike .title, this property is stripped of html
   * markup (e.g., &lt;b&gt;, &lt;i&gt;, etc.).
   * 
   * @return Returns the title, but unlike .title, this property is stripped of html
   * markup (e.g., &lt;b&gt;, &lt;i&gt;, etc.).
   */
  public String getTitleNoFormatting() {
    return GblogResult.IMPL.getTitleNoFormatting(this);
  }

  @Override
  GResult getImpl() {
    return GblogResult.IMPL;
  }
}

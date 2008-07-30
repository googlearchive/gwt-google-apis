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
import com.google.gwt.search.client.impl.GwebResult;
import com.google.gwt.core.client.JavaScriptObject;

/**
 * Web search results.
 */
public class WebResult extends Result {

  WebResult(JavaScriptObject obj) {
    super(obj);
  }

  /**
   * Returns a url to google's cached version of the page responsible for
   * producing this result. This property may be null indicating that there is
   * no cache, and it might be out of date in cases where the search result has
   * been saved and in the mean time, the cache has gone stale. For best
   * results, this property should not be persisted.
   * 
   * @return a url to google's cached version of the page responsible for
   *         producing this result.
   */
  public String getCacheUrl() {
    return GwebResult.IMPL.getCacheUrl(this);
  }

  /**
   * Returns a brief snippet of information from the page associated with the
   * search result.
   * 
   * @return a brief snippet of information from the page associated with the
   *         search result.
   */
  public String getContent() {
    return GwebResult.IMPL.getContent(this);
  }

  /**
   * Returns the title value of the result.
   * 
   * @return the title value of the result.
   */
  public String getTitle() {
    return GwebResult.IMPL.getTitle(this);
  }

  /**
   * Returns the title, but unlike .title, this property is stripped of html
   * markup (e.g., <b>, <i>, etc.).
   * 
   * @return the title, but unlike .title, this property is stripped of html
   *         markup (e.g., &lt;b&gt;, &lt;i&gt;, etc.).
   */
  public String getTitleNoFormatting() {
    return GwebResult.IMPL.getTitleNoFormatting(this);
  }

  /**
   * Returns the raw URL of the result.
   * 
   * @return the raw URL of the result.
   */
  public String getUnescapedUrl() {
    return GwebResult.IMPL.getUnescapedUrl(this);
  }

  /**
   * Returns an escaped version of the above URL.
   * 
   * @return an escaped version of the above URL.
   */
  public String getUrl() {
    return GwebResult.IMPL.getUrl(this);
  }

  /**
   * Returns shortened version of the URL associated with the result. Typically
   * displayed in green, stripped of a protocol and path.
   * 
   * @return shortened version of the URL associated with the result. Typically
   *         displayed in green, stripped of a protocol and path.
   */
  public String getVisibleUrl() {
    return GwebResult.IMPL.getVisibleUrl(this);
  }

  @Override
  GResult getImpl() {
    return GwebResult.IMPL;
  }
}

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

/**
 * Web search results.
 */
public final class WebResult extends Result {
  public static final ResultClass RESULT_CLASS = ResultClass.WEB_SEARCH_RESULT;

  public static WebResult isWebResult(Result result) {
    if (result.getResultClass().equals(RESULT_CLASS)) {
      return (WebResult) result;
    }
    return null;
  }

  protected WebResult() {
    // Required for overlay types
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
  public native String getCacheUrl() /*-{
    return this.cacheUrl;
  }-*/;

  /**
   * Returns a brief snippet of information from the page associated with the
   * search result.
   * 
   * @return a brief snippet of information from the page associated with the
   *         search result.
   */
  public native String getContent() /*-{
    return this.content;
  }-*/;

  /**
   * Returns the title value of the result.
   * 
   * @return the title value of the result.
   */
  public native String getTitle() /*-{
    return this.title;
  }-*/;

  /**
   * Returns the title, but unlike .title, this property is stripped of html
   * markup (e.g., &lt;b&gt;, &lt;i&gt;, etc.).
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
   * Returns an escaped version of the above URL.
   * 
   * @return an escaped version of the above URL.
   */
  public native String getUrl() /*-{
    return this.url;
  }-*/;

  /**
   * Returns shortened version of the URL associated with the result. Typically
   * displayed in green, stripped of a protocol and path.
   * 
   * @return shortened version of the URL associated with the result. Typically
   *         displayed in green, stripped of a protocol and path.
   */
  public native String getVisibleUrl() /*-{
    return this.visibleUrl;
  }-*/;
}

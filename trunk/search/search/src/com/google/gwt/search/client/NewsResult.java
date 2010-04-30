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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

import java.util.Date;

/**
 * A News search result.
 */
public final class NewsResult extends Result {
  public static final ResultClass RESULT_CLASS = ResultClass.NEWS_SEARCH_RESULT;

  public static NewsResult isNewsResult(Result result) {
    if (result.getResultClass().equals(RESULT_CLASS)) {
      return (NewsResult) result;
    }
    return null;
  }

  /**
   * 
   */
  public static final class Image extends JavaScriptObject {
    protected Image() {
      // Required for overlay types
    }

    /**
     * Returns the publisher of the news article containing the image. The
     * suggested user interface is to display this under or in close proximity
     * to the image, hyper linked through the {@link #getUrl()} property from
     * above.
     * 
     * @return publisher of the news article containing the image
     */
    public native String getPublisher() /*-{
      return this.publisher;
    }-*/;

    /**
     * Returns the height of the image. The standard size of this image is 80
     * pixels wide and 50 pixels tall.
     * 
     * @return height of the image
     */
    public native int getTbHeight() /*-{
      return Number(this.tbHeight);
    }-*/;

    /**
     * Returns the URL of the image.
     * 
     * @return URL of the image
     */
    public native String getTbUrl() /*-{
      return this.tbUrl;
    }-*/;

    /**
     * Returns the width of the image. The standard size of this image is 80
     * pixels wide and 50 pixels tall
     * 
     * @return widht of the image
     */
    public native int getTbWidth() /*-{
      return Number(this.tbWidth);
    }-*/;

    /**
     * Returns the title of the article associated with the image.
     * 
     * @return title of the article associated with the image.
     */
    public native String getTitle() /*-{
      return this.title;
    }-*/;

    /**
     * Returns the title of the article associated with the image stripped of
     * HTML formatting.
     * 
     * @return title of the article associated with the image stripped of HTML
     *         formatting
     */
    public native String getTitleNoFormatting() /*-{
      return this.titleNoFormatting;
    }-*/;

    /**
     * Returns the URL of the article that contains this image. The image, when
     * displayed, would normally link through this URL.
     */
    public native String getUrl() /*-{
      return this.url;
    }-*/;
  }
  /**
   * A news story that is related to a primary NewsResult.
   */
  public static final class RelatedStory extends JavaScriptObject {
    protected RelatedStory() {
      // Required for overlay types
    }

    /**
     * Returns the location of the news story. This is a list of locations in
     * most specific to least specific order where the components are separated
     * by ",". Note, there may only be one element in the list... A typical
     * value for this property is "Edinburgh,Scotland,UK" or possibly "USA".
     * 
     * @return the location of the news story.
     */
    public native String getLocation() /*-{
      return this.location;
    }-*/;

    /**
     * Returns the published date (RFC-822 format) of the news story referenced
     * by this search result.
     * 
     * @return the published date (RFC-822 format) of the news story referenced
     *         by this search result.
     */
    public Date getPublishedDate() {
      return makeDate(getPublishedDateAsString());
    }

    public native String getPublishedDateAsString() /*-{
      return this.publishedDate;
    }-*/;

    /**
     * Returns the name of the publisher of the news story.
     * 
     * @return the name of the publisher of the news story.
     */
    public native String getPublisher() /*-{
      return this.publisher;
    }-*/;

    /**
     * Returns the title of the news story returned as a search result.
     * 
     * @return the title of the news story returned as a search result.
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
  }

  protected NewsResult() {
    // Required for overlay types
  }

  /**
   * When a news result has a set of related stories, this URL is available and
   * non-null. In this situation, the URL points to a landing page that points
   * to all of the related stories.
   * 
   * @return a landing page that points to all of the related stories (or null).
   */
  public native String getClusterUrl() /*-{
    return this.clusterUrl;
  }-*/;

  /**
   * Returns a snippet of content from the news story associated with this
   * search result.
   * 
   * @return a snippet of content from the news story associated with this
   *         search result.
   */
  public native String getContent() /*-{
    return this.content;
  }-*/;

  /**
   * This property is optional. It only appears in a result when the system has
   * determined that there is a good image that represents the cluster of news
   * articles related to this result. It's important to note the the image
   * relates to the cluster of news articles, not just the article that acts as
   * the primary article for this result. Given this, it's very important for
   * your related user interface to not mis-represent the image. You must always
   * display the news source of the article, and the news source of the image as
   * they are often different. You will notice that on Google News, the image is
   * displayed off to the side with full attribution to the source, and that
   * it's hyper-linked to the article associated with the image, not the article
   * associated with the current result.
   * 
   * This property is optional. It does not always exist. It's best to check for
   * <code>null</code> before using. When the property is present, it contains
   * the following sub-properties.
   * 
   * @return {@link Image} or <code>null</code> if none was provided
   */
  public native Image getImage() /*-{
    return this.image;
  }-*/;

  /**
   * Returns the location of the news story. This is a list of locations in most
   * specific to least specific order where the components are separated by ",".
   * Note, there may only be one element in the list... A typical value for this
   * property is "Edinburgh,Scotland,UK" or possibly "USA".
   * 
   * @return the location of the news story.
   */
  public native String getLocation() /*-{
    return this.location;
  }-*/;

  /**
   * Returns the published date (RFC-822 format) of the news story referenced by
   * this search result.
   * 
   * @return the published date (RFC-822 format) of the news story referenced by
   *         this search result.
   */
  public Date getPublishedDate() {
    return makeDate(getPublishedDateAsString());
  }

  /**
   * Returns the name of the publisher of the news story.
   * 
   * @return the name of the publisher of the news story.
   */
  public native String getPublisher() /*-{
    return this.publisher;
  }-*/;

  /**
   * A List of {@link NewsResult.RelatedStory}. It is only populated in a result
   * when the story also has a set of closely related stories.
   * 
   * @return A List of {@link NewsResult.RelatedStory}.
   */
  public native JsArray<RelatedStory> getRelatedStories() /*-{
    return this.relatedStories == null ? [] : this.relatedStories;
  }-*/;

  /**
   * Returns the title of the news story returned as a search result.
   * 
   * @return the title of the news story returned as a search result.
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

  private native String getPublishedDateAsString() /*-{
    return this.publishedDate;
  }-*/;
}

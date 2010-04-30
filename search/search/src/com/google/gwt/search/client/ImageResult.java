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
 * Image search results.
 */
public final class ImageResult extends Result {
  public static final ResultClass RESULT_CLASS = ResultClass.IMAGE_SEARCH_RESULT;

  public static ImageResult isImageResult(Result result) {
    if (result.getResultClass().equals(RESULT_CLASS)) {
      return (ImageResult) result;
    }
    return null;
  }

  protected ImageResult() {
    // Required for overlay types
  }

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
   * Returns the same information as {@link #getContent()} only stripped of HTML
   * formatting.
   * 
   * @return the same information as {@link #getContent()} only stripped of HTML
   *         formatting.
   */
  public native String getContentNoFormatting() /*-{
    return this.contentNoFormatting;
  }-*/;

  /**
   * Returns the height of the image in pixels.
   * 
   * @return the height of the image in pixels.
   */
  public native int getHeight() /*-{
    return Number(this.height);
  }-*/;

  /**
   * Returns the URL of the page containing the image.
   * 
   * @return the URL of the page containing the image.
   */
  public native String getOriginalContextUrl() /*-{
    return this.originalContextUrl;
  }-*/;

  /**
   * Returns the height in pixels of the image thumbnail.
   * 
   * @return the height in pixels of the image thumbnail.
   */
  public native int getThumbnailHeight() /*-{
    return Number(this.tbHeight);
  }-*/;

  /**
   * Returns the URL of a thumbnail image.
   * 
   * @return the URL of a thumbnail image.
   */
  public native String getThumbnailUrl() /*-{
    return this.tbUrl;
  }-*/;

  /**
   * Returns the width in pixels of the image thumbnail.
   * 
   * @return the width in pixels of the image thumbnail.
   */
  public native int getThumbnailWidth() /*-{
    return Number(this.tbWidth);
  }-*/;

  /**
   * Returns the title of the image, which is usually the base filename.
   * 
   * @return the title of the image, which is usually the base filename.
   */
  public native String getTitle() /*-{
    return this.title;
  }-*/;

  /**
   * Returns the title, but unlike .title, this property is stripped of html
   * markup. (e.g., &lt;b&gt;, &lt;i&gt;, etc.)
   * 
   * @return the title, but unlike .title, this property is stripped of html
   *         markup. (e.g., &lt;b&gt;, &lt;i&gt;, etc.)
   */
  public native String getTitleNoFormatting() /*-{
    return this.titleNoFormatting;
  }-*/;

  /**
   * Returns the raw URL of the image.
   * 
   * @return the raw URL of the image.
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

  /**
   * Returns a shortened version of the URL associated with the result.
   * Typically displayed in green, stripped of a protocol and path.
   * 
   * @return a shortened version of the URL associated with the result.
   */
  public native String getVisibleUrl() /*-{
    return this.visibleUrl;
  }-*/;

  /**
   * Returns the width of the image in pixels.
   * 
   * @return the width of the image in pixels.
   */
  public native int getWidth() /*-{
    return Number(this.width);
  }-*/;
}

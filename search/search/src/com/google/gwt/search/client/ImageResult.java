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
import com.google.gwt.search.client.impl.GimageResult;
import com.google.gwt.core.client.JavaScriptObject;

/**
 * Image search results.
 */
public class ImageResult extends Result {

  ImageResult(JavaScriptObject obj) {
    super(obj);
  }

  /**
   * Returns a brief snippet of information from the page associated with the
   * search result.
   * 
   * @return a brief snippet of information from the page associated with the
   *         search result.
   */
  public String getContent() {
    return GimageResult.IMPL.getContent(this);
  }

  /**
   * Returns the same information as {@link #getContent()} only stripped of HTML
   * formatting.
   * 
   * @return the same information as {@link #getContent()} only stripped of HTML
   *         formatting.
   */
  public String getContentNoFormatting() {
    return GimageResult.IMPL.getContentNoFormatting(this);
  }

  /**
   * Returns the height of the image in pixels.
   * 
   * @return the height of the image in pixels.
   */
  public int getHeight() {
    return Integer.parseInt(GimageResult.IMPL.getHeight(this));
  }

  /**
   * Returns the URL of the page containing the image.
   * 
   * @return the URL of the page containing the image.
   */
  public String getOriginalContextUrl() {
    return GimageResult.IMPL.getOriginalContextUrl(this);
  }

  /**
   * Returns the height in pixels of the image thumbnail.
   * 
   * @return the height in pixels of the image thumbnail.
   */
  public int getThumbnailHeight() {
    return Integer.parseInt(GimageResult.IMPL.getTbHeight(this));
  }

  /**
   * Returns the URL of a thumbnail image.
   * 
   * @return the URL of a thumbnail image.
   */
  public String getThumbnailUrl() {
    return GimageResult.IMPL.getTbUrl(this);
  }

  /**
   * Returns the width in pixels of the image thumbnail.
   * 
   * @return the width in pixels of the image thumbnail.
   */
  public int getThumbnailWidth() {
    return Integer.parseInt(GimageResult.IMPL.getTbWidth(this));
  }

  /**
   * Returns the title of the image, which is usually the base filename.
   * 
   * @return the title of the image, which is usually the base filename.
   */
  public String getTitle() {
    return GimageResult.IMPL.getTitle(this);
  }

  /**
   * Returns the title, but unlike .title, this property is stripped of html
   * markup. (e.g., &lt;b&gt;, &lt;i&gt;, etc.)
   * 
   * @return the title, but unlike .title, this property is stripped of html
   *         markup. (e.g., &lt;b&gt;, &lt;i&gt;, etc.)
   */
  public String getTitleNoFormatting() {
    return GimageResult.IMPL.getTitleNoFormatting(this);
  }

  /**
   * Returns the raw URL of the image.
   * 
   * @return the raw URL of the image.
   */
  public String getUnescapedUrl() {
    return GimageResult.IMPL.getUnescapedUrl(this);
  }

  /**
   * Returns an escaped version of the URL.
   * 
   * @return an escaped version of the URL.
   */
  public String getUrl() {
    return GimageResult.IMPL.getUrl(this);
  }

  /**
   * Returns a shortened version of the URL associated with the result.
   * Typically displayed in green, stripped of a protocol and path.
   * 
   * @return a shortened version of the URL associated with the result.
   */
  public String getVisibleUrl() {
    return GimageResult.IMPL.getVisibleUrl(this);
  }

  /**
   * Returns the width of the image in pixels.
   * 
   * @return the width of the image in pixels.
   */
  public int getWidth() {
    return Integer.parseInt(GimageResult.IMPL.getWidth(this));
  }

  @Override
  GResult getImpl() {
    return GimageResult.IMPL;
  }
}

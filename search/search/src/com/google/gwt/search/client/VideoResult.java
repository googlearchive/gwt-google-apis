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
import com.google.gwt.search.client.impl.GResult;
import com.google.gwt.search.client.impl.GvideoResult;

import java.util.Date;

/**
 * Video search result.
 */
public class VideoResult extends Result {

  VideoResult(JavaScriptObject obj) {
    super(obj);
  }

  /**
   * Returns a snippet style description of the video clip.
   * 
   * @return a snippet style description of the video clip.
   */
  public String getContent() {
    return GvideoResult.IMPL.getContent(this);
  }

  /**
   * Returns the approximate duration, in seconds, of the video.
   * 
   * @return the approximate duration, in seconds, of the video.
   */
  public long getDuration() {
    return Long.parseLong(GvideoResult.IMPL.getDuration(this));
  }

  /**
   * If present, returns the url of the flash version of the video that can be
   * played inline on your page. To play this video simply create and
   * &lt;embed&gt; element on your page using this value as the <code>src</code>
   * attribute and using <code>application/x-shockwave-flash</code> as the
   * type attribute. If you want the video to play right away, make sure to
   * append <code>&autoPlay=true</code> to the url.
   * 
   * @return If present, returns the url of the flash version of the video that
   *         can be played inline on your page. Otherwise, returns null.
   */
  public String getPlayUrl() {
    return GvideoResult.IMPL.getPlayUrl(this);
  }

  /**
   * Returns the published date of the video (rfc-822 format).
   * 
   * @return the published date of the video (rfc-822 format).
   */
  public Date getPublished() {
    return makeDate(GvideoResult.IMPL.getPublished(this));
  }

  /**
   * Returns the name of the video's publisher, typically displayed in green
   * below the video thumbnail, similar to the treatment used for the
   * <code>visibleUrl</code> property in the other search result objects.
   * 
   * @return the name of the video's publisher.
   */
  public String getPublisher() {
    return GvideoResult.IMPL.getPublisher(this);
  }

  /**
   * Returns the height in pixels of the video thumbnail.
   * 
   * @return the height in pixels of the video thumbnail.
   */
  public int getTbHeight() {
    return Integer.parseInt(GvideoResult.IMPL.getTbHeight(this));
  }

  /**
   * Returns the url of a thumbnail image which visually represents the video.
   * 
   * @return the url of a thumbnail image which visually represents the video.
   */
  public String getTbUrl() {
    return GvideoResult.IMPL.getTbUrl(this);
  }

  /**
   * Returns the width in pixels of the video thumbnail.
   * 
   * @return the width in pixels of the video thumbnail.
   */
  public int getTbWidth() {
    return Integer.parseInt(GvideoResult.IMPL.getTbWidth(this));
  }

  /**
   * Returns the title of the video result.
   * 
   * @return the title of the video result.
   */
  public String getTitle() {
    return GvideoResult.IMPL.getTitle(this);
  }

  /**
   * Returns the title, but unlike .title, this property is stripped of html
   * markup (e.g., &lt;b&gt;, &lt;i&gt;, etc.).
   * 
   * @return the title, but unlike .title, this property is stripped of html
   *         markup (e.g., &lt;b&gt;, &lt;i&gt;, etc.).
   */
  public String getTitleNoFormatting() {
    return GvideoResult.IMPL.getTitleNoFormatting(this);
  }

  /**
   * Returns the url of a playable version of the video result.
   * 
   * @return the url of a playable version of the video result.
   */
  public String getUrl() {
    return GvideoResult.IMPL.getUrl(this);
  }

  @Override
  GResult getImpl() {
    return GvideoResult.IMPL;
  }
}

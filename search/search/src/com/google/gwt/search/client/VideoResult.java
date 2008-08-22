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
 * Video search result.
 */
public final class VideoResult extends Result {

  protected VideoResult() {
    // Required for overlay types
  }

  /**
   * Returns the YouTube user name of the author of the video, if it is known.
   * 
   * @return YouTube user name of the author of the video, if it is known
   */
  public native String getAuthor() /*-{
    return this.author;
  }-*/;

  /**
   * Returns a snippet style description of the video clip.
   * 
   * @return a snippet style description of the video clip.
   */
  public native String getContent() /*-{
    return this.content;
  }-*/;

  /**
   * Returns the approximate duration, in seconds, of the video.
   * 
   * @return the approximate duration, in seconds, of the video.
   */
  public long getDuration() {
    return Long.parseLong(getDurationAsString());
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
  public native String getPlayUrl() /*-{
    return this.playUrl;
  }-*/;

  /**
   * Returns the published date of the video (rfc-822 format).
   * 
   * @return the published date of the video (rfc-822 format).
   */
  public Date getPublished() {
    return makeDate(getPublishedAsString());
  }

  public native String getPublishedAsString() /*-{
    return this.published;
  }-*/;

  /**
   * Returns the name of the video's publisher, typically displayed in green
   * below the video thumbnail, similar to the treatment used for the
   * <code>visibleUrl</code> property in the other search result objects.
   * 
   * @return the name of the video's publisher.
   */
  public native String getPublisher() /*-{
    return this.publisher;
  }-*/;

  /**
   * Returns the rating of the vidoe on scale of 1 to 5.
   * 
   * @return rating of the vidoe on scale of 1 to 5
   */
  public native int getRating() /*-{
    return Number(this.rating);
  }-*/;

  /**
   * Returns the height in pixels of the video thumbnail.
   * 
   * @return the height in pixels of the video thumbnail.
   */
  public native int getTbHeight()/*-{
    return Number(this.tbHeight);
  }-*/;

  /**
   * Returns the url of a thumbnail image which visually represents the video.
   * 
   * @return the url of a thumbnail image which visually represents the video.
   */
  public native String getTbUrl() /*-{
    return this.tbUrl;
  }-*/;

  /**
   * Returns the width in pixels of the video thumbnail.
   * 
   * @return the width in pixels of the video thumbnail.
   */
  public native int getTbWidth() /*-{
    return Number(this.tbWidth);
  }-*/;

  /**
   * Returns the title of the video result.
   * 
   * @return the title of the video result.
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
   * Returns the url of a playable version of the video result.
   * 
   * @return the url of a playable version of the video result.
   */
  public native String getUrl() /*-{
    return this.url;
  }-*/;

  /**
   * Returns the number of plays for this video.
   * 
   * @return number of plays for this video
   */
  public long getViewCount() {
    return Long.parseLong(getViewCountAsString());
  }

  private native String getDurationAsString() /*-{
    return this.duration;
  }-*/;

  private native String getViewCountAsString() /*-{
    return this.viewCount;
  }-*/;
}

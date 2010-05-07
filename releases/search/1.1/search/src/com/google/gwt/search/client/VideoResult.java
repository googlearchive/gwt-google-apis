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
  private final static ResultClass RESULT_CLASS = ResultClass.VIDEO_SEARCH_RESULT;

  public static VideoResult isVideoResult(Result result) {
    if (result.getResultClass().equals(RESULT_CLASS)) {
      return (VideoResult) result;
    }
    return null;
  }

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
   * attribute and using <code>application/x-shockwave-flash</code> as the type
   * attribute. If you want the video to play right away, make sure to append
   * <code>&autoPlay=true</code> to the url.
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
   * Returns the rating of the video on scale of 1 to 5. Before calling this
   * method, call {@link #hasRating()} to see if the <code>rating</code>
   * property exists in the Result object.
   * 
   * @return rating of the video on scale of 1 to 5. Return is undefined if no
   *         rating was found.
   */
  public int getRating() {
    assert hasRating() : "Check to see that the property exists with hasRating() before calling getRating()";
    return (int) nativeGetRating();
  }

  /**
   * Returns the rating of the video on scale of 1 to 5. Before calling this
   * method, call {@link #hasRating()} to see if the <code>rating</code>
   * property exists in the Result object.
   * 
   * @return rating of the video on scale of 1 to 5, including fractional
   *         values. Return is undefined if no rating was found.
   */
  public double getRatingDecimal() {
    assert hasRating() : "Check to see that the property exists with hasRating() before calling getRating()";
    return nativeGetRating();
  }

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
   * Returns the number of plays for this video. Before calling this method,
   * call {@link #hasViewCount()} to see if the <code>viewCount</code> property
   * exists in the Result object.
   * 
   * @return number of plays for this video. Return is undefined if the property
   *         does not exist in the Result object.
   */
  public long getViewCount() {
    assert hasViewCount() : "Check to see that the property exists with hasViewCount() before calling getViewCount()";
    return Long.parseLong(getViewCountAsString());
  }

  /**
   * Checks to see if the <code>rating</code> property is set in this object.
   * 
   * @return true if the <code>rating</code> property exists.
   */
  public native boolean hasRating() /*-{
    return this.rating == undefined ? false : true;
  }-*/;

  /**
   * Checks to see if the <code>viewCount</code> property is set in this object.
   * 
   * @return true if the <code>viewCount</code> property exists.
   */
  public native boolean hasViewCount() /*-{
    return (this.viewCount == null ? false : true);
  }-*/;

  private native String getDurationAsString() /*-{
    return this.duration;
  }-*/;

  private native String getViewCountAsString() /*-{
    return this.viewCount;
  }-*/;

  private native double nativeGetRating() /*-{
    return (this.rating == null ? 0 : Number(this.rating));
  }-*/;
}

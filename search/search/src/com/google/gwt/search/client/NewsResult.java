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
import com.google.gwt.search.client.impl.GnewsResult;
import com.google.gwt.search.jsio.client.impl.Extractor;
import com.google.gwt.core.client.JavaScriptObject;

import java.util.Date;
import java.util.List;

/**
 * A News search result.
 */
public class NewsResult extends Result {

  NewsResult(JavaScriptObject obj) {
    super(obj);
  }

  /**
   * A news story that is related to a primary NewsResult.
   */
  public static class RelatedStory {
    @SuppressWarnings("unused")
    private static final Extractor<RelatedStory> __extractor = new Extractor<RelatedStory>() {
      public RelatedStory fromJS(JavaScriptObject jso) {
        return new RelatedStory(jso);
      }

      public JavaScriptObject toJS(RelatedStory o) {
        return o.jsoPeer;
      }
    };

    private final JavaScriptObject jsoPeer;

    private RelatedStory(JavaScriptObject jsoPeer) {
      this.jsoPeer = jsoPeer;
    }

    /**
     * Returns the location of the news story. This is a list of locations in
     * most specific to least specific order where the components are separated
     * by ",". Note, there may only be one element in the list... A typical
     * value for this property is "Edinburgh,Scotland,UK" or possibly "USA".
     * 
     * @return the location of the news story.
     */
    public String getLocation() {
      return GnewsResult.RelatedStory.IMPL.getLocation(this);
    }

    /**
     * Returns the published date (RFC-822 format) of the news story referenced
     * by this search result.
     * 
     * @return the published date (RFC-822 format) of the news story referenced
     *         by this search result.
     */
    public Date getPublishedDate() {
      return makeDate(GnewsResult.RelatedStory.IMPL.getPublishedDate(this));
    }

    /**
     * Returns the name of the publisher of the news story.
     * 
     * @return the name of the publisher of the news story.
     */
    public String getPublisher() {
      return GnewsResult.RelatedStory.IMPL.getPublisher(this);
    }

    /**
     * Returns the title of the news story returned as a search result.
     * 
     * @return the title of the news story returned as a search result.
     */
    public String getTitle() {
      return GnewsResult.RelatedStory.IMPL.getTitle(this);
    }

    /**
     * Returns the title, but unlike .title, this property is stripped of html
     * markup (e.g., &lt;b&gt;, &lt;i&gt;, etc.).
     * 
     * @return the title, but unlike .title, this property is stripped of html
     *         markup (e.g., &lt;b&gt;, &lt;i&gt;, etc.).
     */
    public String getTitleNoFormatting() {
      return GnewsResult.RelatedStory.IMPL.getTitleNoFormatting(this);
    }

    /**
     * Returns the raw URL of the result.
     * 
     * @return the raw URL of the result.
     */
    public String getUnescapedUrl() {
      return GnewsResult.RelatedStory.IMPL.getUnescapedUrl(this);
    }

    /**
     * Returns an escaped version of the above URL.
     * 
     * @return an escaped version of the above URL.
     */
    public String getUrl() {
      return GnewsResult.RelatedStory.IMPL.getUrl(this);
    }
  }

  /**
   * When a news result has a set of related stories, this URL is available and
   * non-null. In this situation, the URL points to a landing page that points
   * to all of the related stories.
   * 
   * @return a landing page that points to all of the related stories (or null).
   */
  public String getClusterUrl() {
    return GnewsResult.IMPL.getClusterUrl(this);
  }

  /**
   * Returns a snippet of content from the news story associated with this
   * search result.
   * 
   * @return a snippet of content from the news story associated with this
   *         search result.
   */
  public String getContent() {
    return GnewsResult.IMPL.getContent(this);
  }

  /**
   * Returns the location of the news story. This is a list of locations in most
   * specific to least specific order where the components are separated by ",".
   * Note, there may only be one element in the list... A typical value for this
   * property is "Edinburgh,Scotland,UK" or possibly "USA".
   * 
   * @return the location of the news story.
   */
  public String getLocation() {
    return GnewsResult.IMPL.getLocation(this);
  }

  /**
   * Returns the published date (RFC-822 format) of the news story referenced by
   * this search result.
   * 
   * @return the published date (RFC-822 format) of the news story referenced by
   *         this search result.
   */
  public Date getPublishedDate() {
    return makeDate(GnewsResult.IMPL.getPublishedDate(this));
  }

  /**
   * Returns the name of the publisher of the news story.
   * 
   * @return the name of the publisher of the news story.
   */
  public String getPublisher() {
    return GnewsResult.IMPL.getPublisher(this);
  }

  /**
   * A List of {@link NewsResult.RelatedStory}. It is only populated in a
   * result when the story also has a set of closely related stories.
   * 
   * @return A List of {@link NewsResult.RelatedStory}.
   */
  public List<RelatedStory> getRelatedStories() {
    return GnewsResult.IMPL.getRelatedStories(this);
  }

  /**
   * Returns the title of the news story returned as a search result.
   * 
   * @return the title of the news story returned as a search result.
   */
  public String getTitle() {
    return GnewsResult.IMPL.getTitle(this);
  }

  /**
   * Returns the title, but unlike .title, this property is stripped of html
   * markup (e.g., &lt;b&gt;, &lt;i&gt;, etc.).
   * 
   * @return the title, but unlike .title, this property is stripped of html
   *         markup (e.g., &lt;b&gt;, &lt;i&gt;, etc.).
   */
  public String getTitleNoFormatting() {
    return GnewsResult.IMPL.getTitleNoFormatting(this);
  }

  /**
   * Returns the raw URL of the result.
   * 
   * @return the raw URL of the result.
   */
  public String getUnescapedUrl() {
    return GnewsResult.IMPL.getUnescapedUrl(this);
  }

  /**
   * Returns an escaped version of the above URL.
   * 
   * @return an escaped version of the above URL.
   */
  public String getUrl() {
    return GnewsResult.IMPL.getUrl(this);
  }

  @Override
  GResult getImpl() {
    return GnewsResult.IMPL;
  }
}

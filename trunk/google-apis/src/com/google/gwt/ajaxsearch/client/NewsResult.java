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
package com.google.gwt.ajaxsearch.client;

import com.google.gwt.ajaxsearch.client.impl.GResult;
import com.google.gwt.ajaxsearch.client.impl.GnewsResult;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.jsio.client.impl.Extractor;

import java.util.Date;
import java.util.List;

/**
 * A News search result.
 */
public class NewsResult extends Result {
  public NewsResult() {
  }

  NewsResult(JavaScriptObject obj) {
    super(obj);
  }

  /**
   * A news story that is related to a primary NewsResult.
   */
  public static class RelatedStory {
    private static final Extractor __extractor = new Extractor() {
      public Object fromJS(JavaScriptObject jso) {
        return new RelatedStory(jso);
      }

      public JavaScriptObject toJS(Object o) {
        return ((RelatedStory) o).jsoPeer;
      }
    };

    private final JavaScriptObject jsoPeer;

    // TODO: Do we even need this constructor?  If so, can it be private?
    public RelatedStory() {
      this(GnewsResult.RelatedStory.IMPL.construct());
    }

    private RelatedStory(JavaScriptObject jsoPeer) {
      this.jsoPeer = jsoPeer;
    }

    public String getLocation() {
      return GnewsResult.RelatedStory.IMPL.getLocation(this);
    }

    public Date getPublishedDate() {
      return makeDate(GnewsResult.RelatedStory.IMPL.getPublishedDate(this));
    }

    public String getPublisher() {
      return GnewsResult.RelatedStory.IMPL.getPublisher(this);
    }

    public String getTitle() {
      return GnewsResult.RelatedStory.IMPL.getTitle(this);
    }

    public String getTitleNoFormatting() {
      return GnewsResult.RelatedStory.IMPL.getTitleNoFormatting(this);
    }

    public String getUnescapedUrl() {
      return GnewsResult.RelatedStory.IMPL.getUnescapedUrl(this);
    }

    public String getUrl() {
      return GnewsResult.RelatedStory.IMPL.getUrl(this);
    }
  }

  public String getClusterUrl() {
    return GnewsResult.IMPL.getClusterUrl(this);
  }

  public String getContent() {
    return GnewsResult.IMPL.getContent(this);
  }

  public String getLocation() {
    return GnewsResult.IMPL.getLocation(this);
  }

  public Date getPublishedDate() {
    return makeDate(GnewsResult.IMPL.getPublishedDate(this));
  }

  public String getPublisher() {
    return GnewsResult.IMPL.getPublisher(this);
  }

  /**
   * A List of {@link NewsResult.RelatedStory}.
   */
  public List<RelatedStory> getRelatedStories() {
    return GnewsResult.IMPL.getRelatedStories(this);
  }

  public String getTitle() {
    return GnewsResult.IMPL.getTitle(this);
  }

  public String getTitleNoFormatting() {
    return GnewsResult.IMPL.getTitleNoFormatting(this);
  }

  public String getUnescapedUrl() {
    return GnewsResult.IMPL.getUnescapedUrl(this);
  }

  public String getUrl() {
    return GnewsResult.IMPL.getUrl(this);
  }

  GResult getImpl() {
    return GnewsResult.IMPL;
  }
}

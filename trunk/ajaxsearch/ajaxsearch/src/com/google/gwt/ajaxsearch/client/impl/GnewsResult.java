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
package com.google.gwt.ajaxsearch.client.impl;

import com.google.gwt.ajaxsearch.client.NewsResult;
import com.google.gwt.ajaxsearch.jsio.client.BeanProperties;
import com.google.gwt.ajaxsearch.jsio.client.Constructor;
import com.google.gwt.ajaxsearch.jsio.client.FieldName;
import com.google.gwt.ajaxsearch.jsio.client.JSFlyweightWrapper;
import com.google.gwt.ajaxsearch.jsio.client.JSList;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;

/**
 * @see http://code.google.com/apis/ajaxsearch/documentation/reference.html#_class_GnewsResult
 */
@BeanProperties
public interface GnewsResult extends GResult {
  GnewsResult IMPL = GWT.create(GnewsResult.class);

  /**
   * A news story that is related to a primary GnewsResult.
   */
  @BeanProperties
  public static interface RelatedStory extends JSFlyweightWrapper {
    RelatedStory IMPL = GWT.create(RelatedStory.class);

    @Constructor("$wnd.Object")
    JavaScriptObject construct();

    String getLocation(NewsResult.RelatedStory obj);

    String getPublishedDate(NewsResult.RelatedStory obj);

    String getPublisher(NewsResult.RelatedStory obj);

    String getTitle(NewsResult.RelatedStory obj);

    String getTitleNoFormatting(NewsResult.RelatedStory obj);

    String getUnescapedUrl(NewsResult.RelatedStory obj);

    String getUrl(NewsResult.RelatedStory obj);
  }

  String getClusterUrl(NewsResult obj);

  String getContent(NewsResult obj);

  String getLocation(NewsResult obj);

  String getPublishedDate(NewsResult obj);

  String getPublisher(NewsResult obj);

  @FieldName("relatedStories")
  JSList<NewsResult.RelatedStory> getRelatedStories(NewsResult obj);

  String getTitle(NewsResult obj);

  String getTitleNoFormatting(NewsResult obj);

  String getUnescapedUrl(NewsResult obj);

  String getUrl(NewsResult obj);
}

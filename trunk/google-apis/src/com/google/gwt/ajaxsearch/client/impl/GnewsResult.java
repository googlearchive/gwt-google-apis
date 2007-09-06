/*
 * Copyright 2007 Google Inc.
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
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.jsio.client.JSFlyweightWrapper;
import com.google.gwt.jsio.client.JSList;

/**
 * @gwt.beanProperties
 * @see http://code.google.com/apis/ajaxsearch/documentation/reference.html#_class_GnewsResult
 */
public interface GnewsResult extends GResult {
  public static final GnewsResult IMPL =
    (GnewsResult)GWT.create(GnewsResult.class);

  /**
   * A news story that is related to a primary GnewsResult.
   * @gwt.beanProperties
   */
  public static interface RelatedStory extends JSFlyweightWrapper {
    public static final RelatedStory IMPL =
      (RelatedStory)GWT.create(RelatedStory.class);

    /**
     * @gwt.constructor Object
     */
    public JavaScriptObject construct();
    public String getLocation(NewsResult.RelatedStory obj);
    public String getPublishedDate(NewsResult.RelatedStory obj);
    public String getPublisher(NewsResult.RelatedStory obj);
    public String getTitle(NewsResult.RelatedStory obj);
    public String getTitleNoFormatting(NewsResult.RelatedStory obj);
    public String getUnescapedUrl(NewsResult.RelatedStory obj);
    public String getUrl(NewsResult.RelatedStory obj);
  }
  
  public String getClusterUrl(NewsResult obj);
  public String getContent(NewsResult obj);
  public String getLocation(NewsResult obj);
  public String getPublishedDate(NewsResult obj);
  public String getPublisher(NewsResult obj);
  /**
   * @gwt.fieldName relatedStories
   * @gwt.typeArgs <com.google.gwt.ajaxsearch.client.NewsResult.RelatedStory>
   */
  public JSList getRelatedStories(NewsResult obj);
  public String getTitle(NewsResult obj);
  public String getTitleNoFormatting(NewsResult obj);
  public String getUnescapedUrl(NewsResult obj);
  public String getUrl(NewsResult obj);
}

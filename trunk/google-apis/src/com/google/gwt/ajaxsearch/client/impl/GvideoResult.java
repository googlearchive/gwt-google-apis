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

import com.google.gwt.ajaxsearch.client.VideoResult;
import com.google.gwt.jsio.client.BeanProperties;
import com.google.gwt.core.client.GWT;

/**
 * @see http://code.google.com/apis/ajaxsearch/documentation/reference.html#_class_GvideoResult
 */
@BeanProperties
public interface GvideoResult extends GResult {
  public static final GvideoResult IMPL =
    (GvideoResult)GWT.create(GvideoResult.class);

  public String getContent(VideoResult obj);
  public String getDuration(VideoResult obj);
  public String getPlayUrl(VideoResult obj);
  public String getPublished(VideoResult obj);
  public String getPublisher(VideoResult obj);
  public String getTbHeight(VideoResult obj);
  public String getTbUrl(VideoResult obj);
  public String getTbWidth(VideoResult obj);
  public String getTitle(VideoResult obj);
  public String getTitleNoFormatting(VideoResult obj);
  public String getUrl(VideoResult obj);
}

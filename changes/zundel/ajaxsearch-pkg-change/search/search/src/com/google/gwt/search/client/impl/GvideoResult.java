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
package com.google.gwt.search.client.impl;

import com.google.gwt.search.client.VideoResult;
import com.google.gwt.search.jsio.client.BeanProperties;
import com.google.gwt.core.client.GWT;

/**
 * @see http://code.google.com/apis/ajaxsearch/documentation/reference.html#_class_GvideoResult
 */
@BeanProperties
public interface GvideoResult extends GResult {
  GvideoResult IMPL = GWT.create(GvideoResult.class);

  String getContent(VideoResult obj);

  String getDuration(VideoResult obj);

  String getPlayUrl(VideoResult obj);

  String getPublished(VideoResult obj);

  String getPublisher(VideoResult obj);

  String getTbHeight(VideoResult obj);

  String getTbUrl(VideoResult obj);

  String getTbWidth(VideoResult obj);

  String getTitle(VideoResult obj);

  String getTitleNoFormatting(VideoResult obj);

  String getUrl(VideoResult obj);
}

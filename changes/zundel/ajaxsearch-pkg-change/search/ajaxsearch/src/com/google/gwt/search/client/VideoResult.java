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
import com.google.gwt.search.client.impl.GvideoResult;
import com.google.gwt.core.client.JavaScriptObject;

import java.util.Date;

/**
 * Video search result.
 */
public class VideoResult extends Result {

  VideoResult(JavaScriptObject obj) {
    super(obj);
  }

  public String getContent() {
    return GvideoResult.IMPL.getContent(this);
  }

  public long getDuration() {
    return Long.parseLong(GvideoResult.IMPL.getDuration(this));
  }

  public String getPlayUrl() {
    return GvideoResult.IMPL.getPlayUrl(this);
  }

  public Date getPublished() {
    return makeDate(GvideoResult.IMPL.getPublished(this));
  }

  public String getPublisher() {
    return GvideoResult.IMPL.getPublisher(this);
  }

  public int getTbHeight() {
    return Integer.parseInt(GvideoResult.IMPL.getTbHeight(this));
  }

  public String getTbUrl() {
    return GvideoResult.IMPL.getTbUrl(this);
  }

  public int getTbWidth() {
    return Integer.parseInt(GvideoResult.IMPL.getTbWidth(this));
  }

  public String getTitle() {
    return GvideoResult.IMPL.getTitle(this);
  }

  public String getTitleNoFormatting() {
    return GvideoResult.IMPL.getTitleNoFormatting(this);
  }

  public String getUrl() {
    return GvideoResult.IMPL.getUrl(this);
  }

  @Override
  GResult getImpl() {
    return GvideoResult.IMPL;
  }
}

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
import com.google.gwt.search.client.impl.GblogResult;
import com.google.gwt.core.client.JavaScriptObject;

import java.util.Date;

/**
 * Blog search results.
 */
public class BlogResult extends Result {

  BlogResult(JavaScriptObject obj) {
    super(obj);
  }

  public String getAuthor() {
    return GblogResult.IMPL.getAuthor(this);
  }

  public String getBlogUrl() {
    return GblogResult.IMPL.getBlogUrl(this);
  }

  public String getContent() {
    return GblogResult.IMPL.getContent(this);
  }

  public String getPostUrl() {
    return GblogResult.IMPL.getPostUrl(this);
  }

  public Date getPublishedDate() {
    return makeDate(GblogResult.IMPL.getPublishedDate(this));
  }

  public String getTitle() {
    return GblogResult.IMPL.getTitle(this);
  }

  public String getTitleNoFormatting() {
    return GblogResult.IMPL.getTitleNoFormatting(this);
  }

  @Override
  GResult getImpl() {
    return GblogResult.IMPL;
  }
}

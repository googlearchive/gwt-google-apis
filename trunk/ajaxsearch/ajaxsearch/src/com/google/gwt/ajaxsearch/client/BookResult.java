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
import com.google.gwt.ajaxsearch.client.impl.GbookResult;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.ui.Widget;

/**
 * Book search results.
 */
public class BookResult extends Result {

  BookResult(JavaScriptObject obj) {
    super(obj);
  }

  public String getAuthors() {
    return GbookResult.IMPL.getAuthors(this);
  }

  public String getBookId() {
    return GbookResult.IMPL.getBookId(this);
  }

  public int getPageCount() {
    return Integer.parseInt(GbookResult.IMPL.getPageCount(this));
  }

  public int getPublishedYear() {
    return Integer.parseInt(GbookResult.IMPL.getPublishedYear(this));
  }

  public Widget getThumbnailHtml() {
    return makeWidget(GbookResult.IMPL.getThumbnailHtml(this));
  }

  public String getTitle() {
    return GbookResult.IMPL.getTitle(this);
  }

  public String getTitleNoFormatting() {
    return GbookResult.IMPL.getTitleNoFormatting(this);
  }

  public String getUnescapedUrl() {
    return GbookResult.IMPL.getUnescapedUrl(this);
  }

  @Override
  GResult getImpl() {
    return GbookResult.IMPL;
  }
}

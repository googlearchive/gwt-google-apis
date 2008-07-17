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

import com.google.gwt.search.client.BookResult;
import com.google.gwt.search.jsio.client.BeanProperties;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;

/**
 * @see http://code.google.com/apis/ajaxsearch/documentation/reference.html#_class_GbookResult
 */
@BeanProperties
public interface GbookResult extends GResult {
  GbookResult IMPL = GWT.create(GbookResult.class);

  String getAuthors(BookResult obj);

  String getBookId(BookResult obj);

  String getPageCount(BookResult obj);

  String getPublishedYear(BookResult obj);

  Element getThumbnailHtml(BookResult obj);

  String getTitle(BookResult obj);

  String getTitleNoFormatting(BookResult obj);

  String getUnescapedUrl(BookResult obj);

  String getUrl(BookResult obj);
}

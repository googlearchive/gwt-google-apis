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

import com.google.gwt.ajaxsearch.client.BookResult;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;

/**
 * @gwt.beanProperties
 * @see http://code.google.com/apis/ajaxsearch/documentation/reference.html#_class_GbookResult
 */
public interface GbookResult extends GResult {
  public static final GbookResult IMPL =
    (GbookResult)GWT.create(GbookResult.class);

  public abstract String getAuthors(BookResult obj);
  public abstract String getBookId(BookResult obj);
  public abstract String getPageCount(BookResult obj);
  public abstract String getPublishedYear(BookResult obj);
  public abstract Element getThumbnailHtml(BookResult obj);
  public abstract String getTitle(BookResult obj);
  public abstract String getTitleNoFormatting(BookResult obj);
  public abstract String getUnescapedUrl(BookResult obj);
  public abstract String getUrl(BookResult obj);
}

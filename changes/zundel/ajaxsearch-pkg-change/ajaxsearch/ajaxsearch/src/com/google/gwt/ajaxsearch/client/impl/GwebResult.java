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

import com.google.gwt.ajaxsearch.client.WebResult;
import com.google.gwt.ajaxsearch.jsio.client.BeanProperties;
import com.google.gwt.core.client.GWT;

/**
 * @see http://code.google.com/apis/ajaxsearch/documentation/reference.html#_class_GwebResult
 */
@BeanProperties
public interface GwebResult extends GResult {
  GwebResult IMPL = GWT.create(GwebResult.class);

  String getCacheUrl(WebResult obj);

  String getContent(WebResult obj);

  String getTitle(WebResult obj);

  String getTitleNoFormatting(WebResult obj);

  String getUnescapedUrl(WebResult obj);

  String getUrl(WebResult obj);

  String getVisibleUrl(WebResult obj);
}

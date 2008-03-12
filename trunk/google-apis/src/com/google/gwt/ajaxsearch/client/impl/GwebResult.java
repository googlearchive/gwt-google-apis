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
import com.google.gwt.jsio.client.BeanProperties;
import com.google.gwt.core.client.GWT;

/**
 * @see http://code.google.com/apis/ajaxsearch/documentation/reference.html#_class_GwebResult
 */
@BeanProperties
public interface GwebResult extends GResult {
  public static final GwebResult IMPL =
    (GwebResult)GWT.create(GwebResult.class);

  public String getCacheUrl(WebResult obj);
  public String getContent(WebResult obj);
  public String getTitle(WebResult obj);
  public String getTitleNoFormatting(WebResult obj);
  public String getUnescapedUrl(WebResult obj);
  public String getUrl(WebResult obj);
  public String getVisibleUrl(WebResult obj);
}

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
import com.google.gwt.ajaxsearch.client.impl.GwebResult;
import com.google.gwt.core.client.JavaScriptObject;

/**
 * Web search results.
 */
public class WebResult extends Result {

  WebResult(JavaScriptObject obj) {
    super(obj);
  }

  public String getCacheUrl() {
    return GwebResult.IMPL.getCacheUrl(this);
  }

  public String getContent() {
    return GwebResult.IMPL.getContent(this);
  }

  public String getTitle() {
    return GwebResult.IMPL.getTitle(this);
  }

  public String getTitleNoFormatting() {
    return GwebResult.IMPL.getTitleNoFormatting(this);
  }

  public String getUnescapedUrl() {
    return GwebResult.IMPL.getUnescapedUrl(this);
  }

  public String getUrl() {
    return GwebResult.IMPL.getUrl(this);
  }

  public String getVisibleUrl() {
    return GwebResult.IMPL.getVisibleUrl(this);
  }

  @Override
  GResult getImpl() {
    return GwebResult.IMPL;
  }
}

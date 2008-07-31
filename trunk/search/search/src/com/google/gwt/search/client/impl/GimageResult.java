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

import com.google.gwt.search.client.ImageResult;
import com.google.gwt.search.jsio.client.BeanProperties;
import com.google.gwt.core.client.GWT;

/**
 * @see <a
 *      href="http://code.google.com/apis/ajaxsearch/documentation/reference.html#_class_GimageResult">AJAX
 *      Search API Reference</a>
 */
@BeanProperties
public interface GimageResult extends GResult {
  GimageResult IMPL = GWT.create(GimageResult.class);

  String getContent(ImageResult obj);

  String getContentNoFormatting(ImageResult obj);

  String getHeight(ImageResult obj);

  String getOriginalContextUrl(ImageResult obj);

  String getTbHeight(ImageResult obj);

  String getTbUrl(ImageResult obj);

  String getTbWidth(ImageResult obj);

  String getTitle(ImageResult obj);

  String getTitleNoFormatting(ImageResult obj);

  String getUnescapedUrl(ImageResult obj);

  String getUrl(ImageResult obj);

  String getVisibleUrl(ImageResult obj);

  String getWidth(ImageResult obj);
}

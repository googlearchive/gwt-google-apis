/*
 * Copyright 2010 Google Inc.
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

import com.google.gwt.search.jsio.client.JSOpaque;

import java.util.HashMap;
import java.util.Map;

/**
 * Helps determine proper sublcass from a {@link Result} object.
 */
public enum ResultClass {
  WEB_SEARCH_RESULT("WebSearch"), //
  LOCAL_SEARCH_RESULT("LocalSearch"), //
  VIDEO_SEARCH_RESULT("VideoSearch"), //
  BLOG_SEARCH_RESULT("BlogSearch"), //
  NEWS_SEARCH_RESULT("NewsSearch"), //
  BOOK_SEARCH_RESULT("BookSearch"), //
  IMAGE_SEARCH_RESULT("ImageSearch"), //
  PATENT_SEARCH_RESULT("PatentSearch"); //

  private final String value;
  private static final Map<String, ResultClass> classMap = new HashMap<String, ResultClass>();

  private ResultClass(String value) {
    this.value = new JSOpaque("$wnd.google.search." + value + ".RESULT_CLASS").toString();
  }

  public String getValue() {
    return value;
  }

  public static ResultClass fromString(String value) {
    if (classMap.size() == 0) {
      for (ResultClass result : values()) {
        classMap.put(result.value, result);
      }
    }
    return classMap.get(value);
  }
}

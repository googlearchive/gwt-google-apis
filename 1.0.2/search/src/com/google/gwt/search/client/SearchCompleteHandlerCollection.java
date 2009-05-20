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

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.search.client.SearchCompleteHandler.SearchCompleteEvent;

import java.util.ArrayList;

/**
 * A specialized List that is used to invoke methods on {@link SearchCompleteHandler}s.
 * 
 * On exception, no further listener callbacks will be invoked.
 */
public class SearchCompleteHandlerCollection extends ArrayList<SearchCompleteHandler> {
  void fireResult(Search search, Result result) {
    UncaughtExceptionHandler handler = GWT.getUncaughtExceptionHandler();
    SearchCompleteEvent event = new SearchCompleteEvent(search, result);
    for (SearchCompleteHandler l : this) {
      if (handler != null) {
        try {
          l.onSearchComplete(event);
        } catch (Throwable e) {
          handler.onUncaughtException(e);
          break;
        }
      } else {
        l.onSearchComplete(event);
      }
    }
  }
}

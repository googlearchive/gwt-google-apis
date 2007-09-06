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

import com.google.gwt.ajaxsearch.client.KeepLabel;
import com.google.gwt.ajaxsearch.client.LinkTarget;
import com.google.gwt.ajaxsearch.client.ResultSetSize;
import com.google.gwt.ajaxsearch.client.Search;
import com.google.gwt.ajaxsearch.client.SearchControl;
import com.google.gwt.ajaxsearch.client.TimeoutInterval;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.jsio.client.JSFlyweightWrapper;
import com.google.gwt.user.client.Element;

/**
 * Allows access to the Google AJAX Search API's major UI control.
 */
public interface GSearchControl extends JSFlyweightWrapper {
  
  void addSearcher(SearchControl jso, Search searcher);
  void addSearcher(SearchControl jso, Search searcher, GsearcherOptions options);
  /**
   * @gwt.binding
   */
  void bind(JavaScriptObject jso, SearchControl control);
  void cancelSearch(SearchControl jso);
  void clearAllResults(SearchControl jso);
  /**
   * @gwt.constructor $wnd.GSearchControl
   */
  JavaScriptObject construct();
  void draw(SearchControl jso, Element e);
  void draw(SearchControl jso, Element e, GdrawOptions opts);
  void execute(SearchControl jso);
  void execute(SearchControl jso, String query);
  void setLinkTarget(SearchControl jso, LinkTarget target);
  void setOnKeepCallback(SearchControl jso, JavaScriptObject context, KeepCallback method);
  void setOnKeepCallback(SearchControl jso, JavaScriptObject context, KeepCallback method, KeepLabel label);
  void setOnKeepCallback(SearchControl jso, JavaScriptObject context, KeepCallback method, String label);
  void setResultSetSize(SearchControl jso, ResultSetSize size);
  void setSearchCompleteCallback(SearchControl jso, JavaScriptObject context, SearchControlCompleteCallback method);
  void setSearchStartingCallback(SearchControl jso, JavaScriptObject context, SearchStartingCallback method);
  void setTimeoutInterval(SearchControl jso, TimeoutInterval interval);
}

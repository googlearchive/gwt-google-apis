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

import com.google.gwt.search.client.Search;
import com.google.gwt.search.client.SearchControl;
import com.google.gwt.search.jsio.client.Binding;
import com.google.gwt.search.jsio.client.Constructor;
import com.google.gwt.search.jsio.client.JSFlyweightWrapper;
import com.google.gwt.search.jsio.client.JSOpaque;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Element;

/**
 * Allows access to the Google AJAX Search API's major UI control.
 */
public interface GSearchControl extends JSFlyweightWrapper {

  void addSearcher(SearchControl jso, Search searcher);

  void addSearcher(SearchControl jso, Search searcher, GsearcherOptions options);

  @Binding
  void bind(JavaScriptObject jso, SearchControl control);

  void cancelSearch(SearchControl jso);

  void clearAllResults(SearchControl jso);

  @Constructor("$wnd.GSearchControl")
  JavaScriptObject construct();

  void draw(SearchControl jso, Element e);

  void draw(SearchControl jso, Element e, GdrawOptions opts);

  void execute(SearchControl jso);

  void execute(SearchControl jso, String query);

  void setLinkTarget(SearchControl jso, JSOpaque target);

  void setOnKeepCallback(SearchControl jso, JavaScriptObject context,
      KeepCallback method);

  void setOnKeepCallback(SearchControl jso, JavaScriptObject context,
      KeepCallback method, JSOpaque label);

  void setOnKeepCallback(SearchControl jso, JavaScriptObject context,
      KeepCallback method, String label);

  void setResultSetSize(SearchControl jso, JSOpaque size);

  void setSearchCompleteCallback(SearchControl jso, JavaScriptObject context,
      SearchControlCompleteCallback method);

  void setSearchStartingCallback(SearchControl jso, JavaScriptObject context,
      SearchStartingCallback method);

  void setTimeoutInterval(SearchControl jso, JSOpaque interval);
}

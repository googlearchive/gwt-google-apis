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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.search.client.Result;
import com.google.gwt.search.client.Search;
import com.google.gwt.search.jsio.client.BeanProperties;
import com.google.gwt.search.jsio.client.Binding;
import com.google.gwt.search.jsio.client.JSFlyweightWrapper;
import com.google.gwt.search.jsio.client.JSOpaque;
import com.google.gwt.user.client.Element;

/**
 * An interface to the base GSearch classes.
 */
public interface GSearch extends JSFlyweightWrapper {

  @Binding
  void bind(JavaScriptObject jso, Search search);

  void clearResults(Search jso);

  JavaScriptObject construct();

  void createResultHtml(Search jso, Result result);

  void execute(Search jso, String query);

  Element getAttribution(Search jso);

  @BeanProperties
  JavaScriptObject getCursor(Search search);

  @BeanProperties
  JsArray<Result> getResults(Search search);

  void gotoPage(Search search, int pageNumber);

  void setLinkTarget(Search jso, JSOpaque target);

  void setNoHtmlGeneration(Search jso);

  void setQueryAddition(Search jso, String addition);

  void setResultSetSize(Search jso, JSOpaque size);

  void setSearchCompleteCallback(Search jso, JavaScriptObject context,
      GSearchCompleteCallback method);

  void setUserDefinedClassSuffix(Search jso, String label);

  void setUserDefinedLabel(Search jso, String label);
}

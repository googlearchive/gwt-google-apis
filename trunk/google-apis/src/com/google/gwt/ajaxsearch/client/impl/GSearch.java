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

import com.google.gwt.ajaxsearch.client.LinkTarget;
import com.google.gwt.ajaxsearch.client.Result;
import com.google.gwt.ajaxsearch.client.ResultSetSize;
import com.google.gwt.ajaxsearch.client.Search;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.jsio.client.JSFlyweightWrapper;
import com.google.gwt.jsio.client.JSList;
import com.google.gwt.jsio.client.BeanProperties;
import com.google.gwt.jsio.client.Binding;
import com.google.gwt.user.client.Element;

/**
 * An interface to the base GSearch classes.
 */
public interface GSearch extends JSFlyweightWrapper {
  
  @Binding
  public void bind(JavaScriptObject jso, Search search);
  
  public void clearResults(Search jso);
  
  public JavaScriptObject construct();

  public void createResultHtml(Search jso, Result result);

  public void execute(Search jso, String query);

  public Element getAttribution(Search jso);

  @BeanProperties
  public JSList<Result> getResults(Search search);

  public void setLinkTarget(Search jso, LinkTarget target);

  public void setNoHtmlGeneration(Search jso);

  public void setQueryAddition(Search jso, String addition);

  public void setResultSetSize(Search jso, ResultSetSize size);

  public void setSearchCompleteCallback(Search jso,
      JavaScriptObject context, GSearchCompleteCallback method);

  public void setUserDefinedClassSuffix(Search jso,
      String label);

  public void setUserDefinedLabel(Search jso, String label);
}

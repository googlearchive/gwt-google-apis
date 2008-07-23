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

import com.google.gwt.search.client.Result;
import com.google.gwt.search.jsio.client.BeanProperties;
import com.google.gwt.search.jsio.client.Binding;
import com.google.gwt.search.jsio.client.Constructor;
import com.google.gwt.search.jsio.client.FieldName;
import com.google.gwt.search.jsio.client.JSFlyweightWrapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Element;

/**
 * Represents a search result.
 */
@BeanProperties
public interface GResult extends JSFlyweightWrapper {
  GResult IMPL = GWT.create(GResult.class);

  @Binding
  void bind(JavaScriptObject obj, Result result);

  @Constructor("$wnd.Object")
  JavaScriptObject construct();

  /**
   * This is accessed from {@link Result#createPeer}.
   */
  @FieldName("GsearchResultClass")
  String getGsearchResultClass(JavaScriptObject obj);

  /**
   * The docs for the JS library indicate that cloneNode() must be invoked on
   * the returned Element before it is used by client code.
   */
  Element getHtml(Result result);
}

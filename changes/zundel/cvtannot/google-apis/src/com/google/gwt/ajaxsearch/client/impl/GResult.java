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

import com.google.gwt.ajaxsearch.client.Result;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.jsio.client.JSFlyweightWrapper;
import com.google.gwt.jsio.client.BeanProperties;
import com.google.gwt.jsio.client.Binding;
import com.google.gwt.jsio.client.Constructor;
import com.google.gwt.jsio.client.FieldName;
import com.google.gwt.user.client.Element;

/**
 * Represents a search result. Because of the lack of inherent type information
 * in the underlying JavaScriptObjects, this wrapper class is equipped with the
 * utility method {@link #castToBestType()} to provide a more specific class of
 * GResult wrapper.
 * 
 */
@BeanProperties
public interface GResult extends JSFlyweightWrapper {
  public static final GResult IMPL = (GResult) GWT.create(GResult.class);

  @Binding
  public void bind(JavaScriptObject obj, Result result);

  @Constructor("Object")
  public JavaScriptObject construct();

  /**
   * This is accessed from {@link Result#createPeer}.
   * 
   */
  @FieldName("GsearchResultClass")
  public String getGsearchResultClass(JavaScriptObject obj);

  /**
   * The docs for the JS library indicate that cloneNode() must be invoked on
   * the returned Element before it is used by client code.
   */
  public Element getHtml(Result result);
}

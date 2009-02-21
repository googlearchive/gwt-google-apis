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

import com.google.gwt.search.client.ImageSearch;
import com.google.gwt.search.jsio.client.Constructor;
import com.google.gwt.search.jsio.client.JSOpaque;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;

/**
 * JSIO wrapper for GimageSearch.
 */
@Constructor("$wnd.GimageSearch")
public interface GimageSearch extends GSearch {
  GimageSearch IMPL = GWT.create(GimageSearch.class);

  JSOpaque RESULT_CLASS = new JSOpaque("$wnd.GimageSearch.RESULT_CLASS");

  @Constructor("$wnd.GimageSearch")
  JavaScriptObject construct();

  void setRestriction(ImageSearch obj, JSOpaque restrictionType);

  void setRestriction(ImageSearch obj, JSOpaque restrictionType,
      JSOpaque restrictionValue);

  void setSiteRestriction(ImageSearch obj, String site);
}

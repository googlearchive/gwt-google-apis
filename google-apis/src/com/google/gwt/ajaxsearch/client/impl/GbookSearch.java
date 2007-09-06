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

import com.google.gwt.ajaxsearch.client.RestrictType;
import com.google.gwt.ajaxsearch.client.RestrictValue;
import com.google.gwt.ajaxsearch.client.Search;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.jsio.client.JSOpaque;

/**
 * @gwt.constructor $wnd.GbookSearch
 */
public interface GbookSearch extends GSearch {
  public static final JSOpaque RESULT_CLASS =
      new JSOpaque("$wnd.GbookSearch.RESULT_CLASS");
  public static final GbookSearch IMPL =
      (GbookSearch)GWT.create(GbookSearch.class);

  /**
   * @gwt.constructor $wnd.GbookSearch
   */
  public abstract JavaScriptObject construct();

  public abstract void setRestriction(Search jso, RestrictType type);

  public abstract void setRestriction(Search jso, RestrictType type,
      RestrictValue value);
}

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
import com.google.gwt.search.jsio.client.Exported;
import com.google.gwt.search.jsio.client.JSFunction;

/**
 * Argument passed to {@link GSearchControl#setSearchStartingCallback(SearchControl, com.google.gwt.core.client.JavaScriptObject, SearchStartingCallback)}.
 */
public abstract class SearchStartingCallback extends JSFunction {
  @Exported
  public abstract void onSearchStart(SearchControl control, Search searcher,
      String query);
}

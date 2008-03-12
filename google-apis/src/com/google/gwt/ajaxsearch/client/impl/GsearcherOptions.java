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

import com.google.gwt.ajaxsearch.client.ExpandMode;
import com.google.gwt.jsio.client.JSWrapper;
import com.google.gwt.jsio.client.Constructor;
import com.google.gwt.user.client.Element;

/**
 * Used with {@link GSearchControl#addSearcher(GSearch, GsearcherOptions)}.
 */
@Constructor("$wnd.GsearcherOptions")
public interface GsearcherOptions extends JSWrapper<GsearcherOptions> {
  public void setExpandMode(ExpandMode mode);
  public void setRoot(Element e);
  public void setVideoResultsTbHeight(int height);
}

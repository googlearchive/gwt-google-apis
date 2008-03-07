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
package com.google.gwt.ajaxsearch.client;

import com.google.gwt.jsio.client.JSOpaque;

/**
 * Used with {@link BookSearch#setRestriction(RestrictType, RestrictValue)} to
 * control the types of results displayed.
 */
public class RestrictValue extends JSOpaque {
  public static final RestrictValue FULL_VIEW = new RestrictValue("FULL_VIEW");
  public static final RestrictValue ALL = new RestrictValue("ALL");

  private RestrictValue(String type) {
    super("$wnd.GbookSearch.TYPE_" + type + "_BOOKS");
  }
}

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
 * Specifies the desired number of results to retrieve when executing a Search.
 * The requested size is a guideline and does not reflect any particular number
 * of results.
 * 
 * @see Search#setResultSetSize(ResultSetSize)
 */
public final class ResultSetSize extends JSOpaque {
  /**
   * Requests a large number of results.
   */
  public static final ResultSetSize LARGE = new ResultSetSize("LARGE");
  
  /**
   * Requests a smaller number of results.
   */
  public static final ResultSetSize SMALL = new ResultSetSize("SMALL");

  private ResultSetSize(String size) {
    super("$wnd.GSearch." + size + "_RESULTSET");
  }
}

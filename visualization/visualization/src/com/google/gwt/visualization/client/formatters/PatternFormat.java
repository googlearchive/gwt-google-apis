/*
 * Copyright 2009 Google Inc.
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
package com.google.gwt.visualization.client.formatters;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayInteger;
import com.google.gwt.visualization.client.DataTable;

/**
 * Enables you to merge the values of designated columns into a single column, 
 * along with arbitrary text. So, for example, if you had a column for first 
 * name and a column for last name, you could populate a third column with 
 * {last name}, {first name}.
 * 
 * @see <a href="http://code.google.com/apis/visualization/documentation/reference.html#patternformatter"
 * > PatternFormat Reference. </a>
 */
public class PatternFormat extends JavaScriptObject {
  public static native PatternFormat create(String pattern) /*-{
    return new $wnd.google.visualization.PatternFormat(pattern);
  }-*/;

  protected PatternFormat() {
  }
  
  public final native void format(DataTable data, JsArrayInteger srcColumnIndices, 
      int dstColumnIndex) /*-{
    this.format(data, srcColumnIndices, dstColumnIndex);
  }-*/;
}

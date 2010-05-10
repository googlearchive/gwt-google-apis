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
import com.google.gwt.visualization.client.DataTable;

/**
 * Adds an up or down arrow to a numeric cell, depending on whether the value is 
 * above or below a specified base value.
 * 
 * @see <a href="http://code.google.com/apis/visualization/documentation/reference.html#arrowformatter"
 * > ArrowFormat Reference. </a>
 */
public class ArrowFormat extends JavaScriptObject {  
  /**
   * Options to configure the formatter.
   */
  public static class Options extends JavaScriptObject {
    public static Options create() {
      return JavaScriptObject.createObject().cast();
    }

    protected Options() {
    }
    
    public final native void setBase(double base) /*-{
      this.base = base;
    }-*/;
  }
  
  public static native ArrowFormat create(Options options) /*-{
    return new $wnd.google.visualization.ArrowFormat(options);
  }-*/;

  protected ArrowFormat() {
  }
  
  public final native void format(DataTable data, int columnIndex) /*-{
    this.format(data, columnIndex);
  }-*/;
}

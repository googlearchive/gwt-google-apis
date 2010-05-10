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
 * Formats a JavaScript Date value.
 * 
 * @see <a href="http://code.google.com/apis/visualization/documentation/reference.html#dateformatter"
 * > DateFormat Reference. </a>
 */
public class DateFormat extends JavaScriptObject {
  /**
   * A quick formatting option for the date.
   */
  public static enum FormatType {
    /**
     * Short format (month first): e.g., "2/28/08"
     */
    SHORT, 
    
    /**
     * Medium format: e.g., "Feb 28, 2008"
     */
    MEDIUM, 
    
    /**
     * Long format: e.g., "February 28, 2008"
     */
    LONG; 
    
    @Override
    public String toString() {
      return name().toLowerCase();
    }
  }

  /**
   * Options to configure the formatter.
   */
  public static class Options extends JavaScriptObject {
    public static Options create() {
      return JavaScriptObject.createObject().cast();
    }

    protected Options() {
    }
    
    public final void setPattern(FormatType pattern) {
      setFormatType(pattern.toString());
    }
    
    public final native void setPattern(String pattern) /*-{
      this.pattern = pattern;
    }-*/;
    
    public final native void setTimeZone(int zone) /*-{
      this.timeZone = zone;
    }-*/;
    
    private native void setFormatType(String type) /*-{
      this.formatType = type;
    }-*/;
  }
  
  public static native DateFormat create(Options options) /*-{
    return new $wnd.google.visualization.DateFormat(options);
  }-*/;

  protected DateFormat() {
  }
  
  public final native void format(DataTable data, int columnIndex) /*-{
    this.format(data, columnIndex);
  }-*/;
}

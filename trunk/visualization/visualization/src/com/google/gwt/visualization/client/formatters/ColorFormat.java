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
 * Assigns colors to the foreground or background of a numeric cell, depending
 * on the cell value.
 * 
 * @see <a href="http://code.google.com/apis/visualization/documentation/reference.html#colorformatter"> 
 *       ColorFormat Reference.</a>
 */
public class ColorFormat extends JavaScriptObject {
  public static native ColorFormat create() /*-{
    return new $wnd.google.visualization.ColorFormat();
  }-*/;

  protected ColorFormat() {
  }

  public final native void addGradientRange(double from, double to,
      String color, String fromBgColor, String toBgColor) /*-{
    this.addGradientRange(from, to, color, fromBgColor, toBgColor);
  }-*/;

  public final native void addRange(double from, double to, String color,
      String bgcolor) /*-{
    this.addRange(from, to, color, bgcolor);
  }-*/;

  public final native void format(DataTable data, int columnIndex) /*-{
    this.format(data, columnIndex);
  }-*/;
}

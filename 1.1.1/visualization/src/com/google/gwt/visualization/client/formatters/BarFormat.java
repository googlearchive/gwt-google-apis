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
 * Adds a colored bar to a numeric cell indicating whether the cell value is
 * above or below a specified base value.
 * 
 * @see <a href="http://code.google.com/apis/visualization/documentation/reference.html#barformatter"
 *      >BarFormat Reference.< /a>
 * 
 */
public class BarFormat extends JavaScriptObject {
  /**
   * BarFormat supports three colors for drawing the bar.
   */
  public static enum Color {
    RED, // Comment to keep enum values on separate lines (Eclipse formatter)
    GREEN, // 
    BLUE; //

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

    public final native void setBase(double base) /*-{
      this.base = base;
    }-*/;

    public final void setColorNegative(Color color) {
      setColorNegative(color.toString());
    }

    public final void setColorPositive(Color color) {
      setColorPositive(color.toString());
    }

    public final native void setMax(double max) /*-{
      this.max = max;
    }-*/;

    public final native void setMin(double min) /*-{
      this.min = min;
    }-*/;

    public final native void setShowValue(boolean show) /*-{
      this.showValue = show;
    }-*/;

    public final native void setWidth(int width) /*-{
      this.width = width;
    }-*/;

    private native void setColorNegative(String color) /*-{
      this.colorNegative = color;
    }-*/;

    private native void setColorPositive(String color) /*-{
      this.colorNegative = color;
    }-*/;
  }

  public static native BarFormat create(Options options) /*-{
    return new $wnd.google.visualization.BarFormat(options);
  }-*/;

  protected BarFormat() {
  }

  public final native void format(DataTable data, int columnIndex) /*-{
    this.format(data, columnIndex);
  }-*/;
}

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
package com.google.gwt.visualization.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.visualization.client.Selectable.SelectCallback;

/**
 * 
 * Pie chart visualization.
 * 
 * @see <a
 *      href="http://code.google.com/apis/visualization/documentation/gallery/piechart.html">
 *      Table Visualization Reference</a>
 */
public class PieChart extends Visualization<PieChart.DrawOptions> {

  /**
   * Options for drawing the pie chart.
   * 
   */
  public static class DrawOptions extends AbstractDrawOptions {

    public static DrawOptions create() {
      return JavaScriptObject.createObject().cast();
    }

    protected DrawOptions() {
    }

    // TODO(umaurer): add support for all options

    public final native void set3D(boolean enable3D) /*-{
      this.is3D = enable3D;
    }-*/;

    public final native void setBackgroundColor(String color) /*-{
      this.backgroundColor = color;
    }-*/; 

    public final native void setBorderColor(String color) /*-{
      this.borderColor = color;
    }-*/;

    public final native void setHeight(int height) /*-{
      this.height = height;
    }-*/;

    public final void setSize(int width, int height) {
      setWidth(width);
      setHeight(height);
    }

    public final native void setTitle(String title) /*-{
      this.title = title;
    }-*/;

    public final native void setWidth(int width) /*-{
      this.width = width;
    }-*/;
  }

  public static native PieChart create(Element parent) /*-{
    return new $wnd.google.visualization.PieChart(parent);
  }-*/;

  protected PieChart() {
  }

  public final void addListener(SelectCallback callback) {
    SelectionHelper.addListener(this, callback);
  }

  public final Selection getSelection() {
    return SelectionHelper.getSelection(this);
  }

  public final void setSelection(Selection sel) {
    SelectionHelper.setSelection(this, sel);
  }
}

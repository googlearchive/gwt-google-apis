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
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.dom.client.Element;

/**
 * 
 * Pie chart visualization.
 * 
 * @see <a
 *      href="http://code.google.com/apis/visualization/documentation/gallery/piechart.html">
 *      Table Visualization Reference</a>
 */
public class PieChart extends Visualization<PieChart.DrawOptions> 
    implements Selectable {

  /**
   * Options for drawing the pie chart.
   * 
   */
  public static class DrawOptions extends AbstractDrawOptions {

    /**
     * Specifies where to put the legend in the visualization.
     */
    public static enum LegendPosition {
      BOTTOM, LABEL, LEFT, NONE, RIGHT, TOP;
      
      @Override
      public String toString() {
        switch(this) {
          case RIGHT:
            return "right";
          case LEFT:
            return "left";
          case TOP:
            return "top";
          case BOTTOM:
            return "bottom";
          case NONE:
            return "none";
          case LABEL:
            return "label";
          default:
            // unreachable
            throw new RuntimeException();
        }
      }
    }
    
    public static DrawOptions create() {
      return JavaScriptObject.createObject().cast();
    }

    protected DrawOptions() {
    }
    
    public final native void set3D(boolean enable3D) /*-{
      this.enable3D = enable3D;
    }-*/;
    
    public final native void setBackgroundColor(Color color) /*-{
      this.backgroundColor = color;
    }-*/;
    
    public final native void setBackgroundColor(String color) /*-{
      this.backgroundColor = color;
    }-*/;
    
    public final native void setBorderColor(Color color) /*-{
      this.borderColor = color;
    }-*/;
    
    public final native void setBorderColor(String color) /*-{
      this.borderColor = color;
    }-*/;
    
    public final void setColors(String[] colors) {
      setColors(createJsArray(colors));
    }
    
    public final native void setFocusBorderColor(Color color) /*-{
      this.focusBorderColor = color;
    }-*/;
    
    public final native void setFocusBorderColor(String color) /*-{
      this.focusBorderColor = color;
    }-*/;
    
    public final native void setHeight(int height) /*-{
      this.height = height;
    }-*/;
    
    public final void setLegend(LegendPosition position) {
      setLegend(position.toString());
    }
    
    public final native void setLegendBackgroundColor(Color color) /*-{
      this.legendBackgroundColor = color;
    }-*/;
    
    public final native void setLegendBackgroundColor(String color) /*-{
      this.legendBackgroundColor = color;
    }-*/;
    
    public final native void setLegendTextColor(Color color) /*-{
      this.legendTextColor = color;
    }-*/;
    
    public final native void setLegendTextColor(String color) /*-{
      this.legendTextColor = color;
    }-*/;
    
    public final void setSize(int width, int height) {
      setWidth(width);
      setHeight(height);
    }
    
    public final native void setTitle(String title) /*-{
      this.title = title;
    }-*/;

    public final native void setTitleColor(Color color) /*-{
      this.titleColor = color;
    }-*/;
    
    public final native void setTitleColor(String color) /*-{
      this.titleColor = color;
    }-*/;

    public final native void setWidth(int width) /*-{
      this.width = width;
    }-*/;
    
    private native void setColors(JsArrayString colors) /*-{
      this.colors = colors;
    }-*/;
    
    private native void setLegend(String legend) /*-{
      this.legend = legend;
    }-*/;
  }

  public static native PieChart create(Element parent) /*-{
    return new $wnd.google.visualization.PieChart(parent);
  }-*/;

  protected PieChart() {
  }

  public final void addSelectListener(SelectListener listener) {
    SelectionHelper.addSelectListener(this, listener);
  }

  public final Selection getSelection() {
    return SelectionHelper.getSelection(this);
  }

  public final void setSelection(Selection sel) {
    SelectionHelper.setSelection(this, sel);
  }
}

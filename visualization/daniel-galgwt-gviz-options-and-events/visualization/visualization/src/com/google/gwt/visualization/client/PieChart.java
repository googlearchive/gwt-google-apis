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

    public static enum LegendPosition {
      RIGHT, LEFT, TOP, BOTTOM, NONE, LABEL;
      
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
            //unreachable
            throw new RuntimeException();
        }
      }
    }
    
    public static DrawOptions create() {
      return JavaScriptObject.createObject().cast();
    }

    protected DrawOptions() {
    }
    
    public final void setBackgroundColor(String color) {
      setOption("backgroundColor", color);
    }
    
    public final void setBackgroundColor(Color color) {
      setOption("backgroundColor", color);
    }
    
    public final void setLegendBackgroundColor(String color) {
      setOption("legendBackgroundColor", color);
    }
    
    public final void setLegendBackgroundColor(Color color) {
      setOption("legendBackgroundColor", color);
    }
    
    public final void setLegendTextColor(String color) {
      setOption("legendTextColor", color);
    }
    
    public final void setLegendTextColor(Color color) {
      setOption("legendTextColor", color);
    }
    
    public final void setBorderColor(String color) {
      setOption("borderColor", color);
    }
    
    public final void setFocusBorderColor(Color color) {
      setOption("focusBorderColor", color);
    }
    
    public final void setFocusBorderColor(String color) {
      setOption("focusBorderColor", color);
    }
    
    public final void setBorderColor(Color color) {
      setOption("borderColor", color);
    }
    
    public final void setColors(String[] colors) {
      setOption("colors", colors);
    }
    
    public final void setHeight(int height) {
      setOption("height", height);
    }
    
    public final void setWidth(int width) {
      setOption("width", width);
    }
    
    public final void setTitle(String title) {
      setOption("title", title);
    }
    
    public final void setTitleColor(String color) {
      setOption("titleColor", color);
    }
    
    public final void setTitleColor(Color color) {
      setOption("titleColor", color);
    }

    public final void set3D(boolean enable3D) {
      setOption("is3D", enable3D);
    }
    
    public final void setLegend(LegendPosition position) {
      setOption("legend", position.toString());
    }

    public final void setSize(int width, int height) {
      setWidth(width);
      setHeight(height);
    }
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

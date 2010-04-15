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
package com.google.gwt.visualization.client.visualizations;

import com.google.gwt.ajaxloader.client.ArrayHelper;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.dom.client.Element;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.AbstractDrawOptions;
import com.google.gwt.visualization.client.LegendPosition;

/**
 * A wrapper for the Google Chart API.
 * 
 * @see <a href="http://code.google.com/apis/visualization/documentation/gallery/genericimagechart.html"
 *      > Image Chart Visualization Reference</a>
 */
public class ImageChart extends Visualization<ImageChart.Options> {
  /**
   * Specification for how the visualization should display a column in the
   * data table as annotations on the chart.
   */
  public static class AnnotationColumn extends JavaScriptObject {
    /** 
     * Whether the annotation should be drawn in front of or behind other
     * chart elements.
     */
    public static enum Priority { LOW, MEDIUM, HIGH }
    
    public static native AnnotationColumn create(int index, int size) /*-{
      return {column: index, size: size};
    }-*/;
    
    protected AnnotationColumn() {
    }
    
    public final native void setColor(String color) /*-{
      this.color = color;
    }-*/;
    
    /**
     * If true, the annotation is drawn in a flag.  Otherwise, the annotation
     * is drawn as plain text.
     * 
     * @param draw Whether to draw a flag around the annotations.
     */
    public final void setDrawFlag(boolean draw) {
      if (draw) {
        setType("flag");
      } else {
        setType("text");
      }
    }
        
    public final native void setPositionColumn(int index) /*-{
      this.positionColumn = index;
    }-*/;
    
    public final void setPriority(Priority priority) {
      setPriority(priority.toString().toLowerCase());
    }
    
    private native void setPriority(String priority) /*-{
      this.priority = priority;
    }-*/;
    
    private native void setType(String type) /*-{
      this.type = type;
    }-*/;
  }
  
  /**
   * Options for drawing the chart.
   * 
   */
  public static class Options extends AbstractDrawOptions {
    public static Options create() {
      return JavaScriptObject.createObject().cast();
    }

    protected Options() {
    }
    
    public final native void setAnnotationColumns(
        JsArray<AnnotationColumn> columns) /*-{
      this.annotationColumns = columns;
    }-*/;
    
    public final native void setColor(String color) /*-{
      this.color = color;
    }-*/;
    
    public final native void setColors(JsArrayString colors) /*-{
      this.colors = colors;
    }-*/;

    public final void setColors(String... colors) {
      setColors(ArrayHelper.toJsArrayString(colors));
    }
    
    public final native void setFill(boolean fill) /*-{
      this.fill = fill;
    }-*/;

    public final native void setHeight(int height) /*-{
      this.height = height;
    }-*/;

    public final void setLegend(LegendPosition position) {
      setLegend(position.toString());
    }
    
    public final native void setMax(double max) /*-{
      this.max = max;
    }-*/;
    
    public final native void setMin(double min) /*-{
      this.min = min;
    }-*/;
    
    public final native void setShowCategoryLabels(boolean show) /*-{
      this.showCategoryLabels = show;
    }-*/;
    
    public final native void setShowValueLabels(boolean show) /*-{
      this.showValueLabels = show;
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

    private native void setLegend(String legend) /*-{
      this.legend = legend;
    }-*/;
  }

  public static final String PACKAGE = "imagechart";

  public ImageChart() {
    super();
  }

  public ImageChart(AbstractDataTable data, Options options) {
    super(data, options);
  }

  @Override
  protected native JavaScriptObject createJso(Element parent) /*-{
    return new $wnd.google.visualization.ImageChart(parent);
  }-*/;
}

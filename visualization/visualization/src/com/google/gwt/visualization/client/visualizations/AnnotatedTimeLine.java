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
package com.google.gwt.visualization.client.visualizations;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayInteger;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.dom.client.Element;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.AbstractDrawOptions;
import com.google.gwt.visualization.client.VisualizationWidget;
import com.google.gwt.visualization.client.events.Handler;
import com.google.gwt.visualization.client.events.RangeChangeHandler;

import java.sql.Date;

/**
 * Annotated timeline visualization. May be loaded by calling: <code>
 * google.load("visualization", "1", {packages:["annotatedtimeline"]});
 * </code>
 * 
 * 
 * @see <a href="http://code.google.com/apis/visualization/documentation/gallery/annotatedtimeline.html"
 *      > Annotated Time Line Visualization Reference</a>
 */
public class AnnotatedTimeLine 
extends Visualization<AnnotatedTimeLine.Options> {
  /**
   * Where to put the colored legend with respect to the date.
   */
  public static enum AnnotatedLegendPosition {
    /**
     * Put the colored legend on the same row as the date.
     */
    SAME_ROW, 
    
    /**
     * Put the colored legend on a new row.
     */
    NEW_ROW;

    @Override
    public String toString() {
      switch (this) {
        case SAME_ROW:
          return "sameRow";
        case NEW_ROW:
          return "newRow";
        default:
          // unreachable
          assert false;
      }
      // unreachable
      return null;
    }
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

    public final native void setAllowHtml(boolean allowHtml) /*-{
      this.allowHtml = allowHtml;
    }-*/;

    public final native void setAllValuesSuffix(String suffix) /*-{
      this.allValuesSuffix = suffix;
    }-*/;

    public final native void setAnnotationsWidth(int width) /*-{
      this.annotationsWidth = width;
    }-*/;

    public final native void setColors(JsArrayString colors) /*-{
      this.colors = colors;
    }-*/;

    public final void setColors(String... colors) {
      setColors(createJsArray(colors));
    }

    public final native void setDisplayAnnotations(boolean display) /*-{
      this.displayAnnotations = display;
    }-*/;

    public final native void setDisplayAnnotationsFilter(boolean display) /*-{
      this.displayAnnotationsFilter = display;
    }-*/;

    public final native void setDisplayExactValues(boolean display) /*-{
      this.displayExactValues = display;
    }-*/;

    public final native void setDisplayZoomButtons(boolean display) /*-{
      this.displayZoomButtons = display;
    }-*/;

    public final void setLegendPosition(AnnotatedLegendPosition position) {
      setLegendPosition(position.toString());
    }

    public final native void setMin(int min) /*-{
      this.min = min;
    }-*/;

    public final void setScaleColumns(int... scaleColumns) {
      setScaleColumns(createJsArray(scaleColumns));
    }

    public final native void setScaleColumns(JsArrayInteger scaleColumns) /*-{
      this.scaleColumns = scaleColumns;
    }-*/;

    public final void setScaleType(ScaleType type) {
      setScaleType(type.name().toLowerCase());
    }

    public final void setWindowMode(WindowMode wmode) {
      setWindowMode(wmode.name().toString().toLowerCase());
    }

    public final void setZoomEndTime(Date endTime) {
      setZoomEndTime(endTime.getTime());
    }

    public final void setZoomStartTime(Date startTime) {
      setZoomStartTime(startTime.getTime());
    }

    private native void setLegendPosition(String position) /*-{
      this.legendPosition = position;
    }-*/;

    private native void setScaleType(String type) /*-{
      this.scaleType = type;
    }-*/;

    private native void setWindowMode(String wmode) /*-{
      this.wmode = wmode;
    }-*/;

    private native void setZoomEndTime(double endTime) /*-{
      this.zoomEndTime = new $wnd.Date(endTime);
    }-*/;

    private native void setZoomStartTime(double startTime) /*-{
      this.zoomStartTime = new $wnd.Date(startTime);
    }-*/;
  }

  /**
   * Scale type for the timeline.
   * 
   * When ALLFIXED or ALLMAXIMIZE are used, it makes sense to set the 
   * scaleColumns option as well, otherwise some of the series will be 
   * displayed in a scale that is not the displayed one.
   */
  public static enum ScaleType {
    /**
     * Set the range of the values axis to be from 0 to the maximal value
     * in the input DataTable.
     */
    FIXED, 
    
    /**
     * Set the range of the values axis to be from the minimal value in the
     * input DataTable to the maximal value in the input DataTable.
     */
    MAXIMIZE, 
    
    /**
     * Set the range of the values axis to be from 0 to the maximal value in
     * each series.
     */
    ALLFIXED, 
    
    /**
     * Set the range of the values axis to be from the minimal value of each
     * series to the maximal value of each series.
     */
    ALLMAXIMIZE
  }

  /**
   * Flash/IE "wmode" parameter. The transparent Flash content, absolute
   * positioning, and layering capabilities available in Internet Explorer 4.0.
   */
  public static enum WindowMode {
    /**
     * Makes the application hide everything behind it on the page.
     */
    OPAQUE,
    
    /**
     * Plays the application in its own rectangular window on a web page.
     * WINDOW indicates that the Flash application has no interaction with HTML
     * layers and is always the topmost item.
     */
    WINDOW, 
    
    /**
     * Makes the background of the HTML page show through all the
     * transparent portions of the application and can slow animation 
     * performance.
     */
    TRANSPARENT
  }

  public static native AnnotatedTimeLine create(Element parent) /*-{
    return new $wnd.google.visualization.AnnotatedTimeLine(parent);
  }-*/;

  public static VisualizationWidget<AnnotatedTimeLine, Options> createWidget(
      AbstractDataTable data, Options options, int width, int height) {
    Element div = createDiv(width, height);
    AnnotatedTimeLine viz = create(div);
    return new VisualizationWidget<AnnotatedTimeLine, Options>(div, viz, data,
        options);
  }

  public static VisualizationWidget<AnnotatedTimeLine, Options> createWidget(
      int width, int height) {
    Element div = createDiv(width, height);
    AnnotatedTimeLine viz = create(div);
    return new VisualizationWidget<AnnotatedTimeLine, Options>(div, viz);
  }

  protected AnnotatedTimeLine() {
  }
  
  public final void addRangeChangeHandler(RangeChangeHandler handler) {
    Handler.addHandler(this, "rangeChange", handler);
  }
}

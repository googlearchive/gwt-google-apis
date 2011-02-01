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

import com.google.gwt.ajaxloader.client.ArrayHelper;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayInteger;
import com.google.gwt.dom.client.Element;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.AbstractDrawOptions;
import com.google.gwt.visualization.client.Selectable;
import com.google.gwt.visualization.client.Selection;
import com.google.gwt.visualization.client.events.Handler;
import com.google.gwt.visualization.client.events.ReadyHandler;
import com.google.gwt.visualization.client.events.RegionClickHandler;
import com.google.gwt.visualization.client.events.SelectHandler;
import com.google.gwt.visualization.client.events.ZoomOutHandler;

/**
 * Geomap visualization. Kind of like a cross between Map and IntensityMap.
 * 
 * @see <a href=
 *      "http://code.google.com/apis/visualization/documentation/gallery/geomap.html"
 *      >GeoMap Visualization Reference</a>
 */
public class GeoMap extends Visualization<GeoMap.Options> implements Selectable {
  /**
   * How to display data on the map.
   */
  public static enum DataMode {
    /**
     * Put markers on the map, changing size and color according to the number
     * given.
     */
    MARKERS,

    /**
     * Color regions inside the map according to the number given.
     */
    REGIONS
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
    
    public final void setColors(int... colors) {
      setColors(ArrayHelper.toJsArrayInteger(colors));
    }

    public final native void setColors(JsArrayInteger colors) /*-{
      this.colors = colors;
    }-*/;

    public final void setDataMode(DataMode mode) {
      setDataMode(mode.name().toLowerCase());
    }

    public final native void setHeight(int height) /*-{
      this.height = height + 'px';
    }-*/;

    public final native void setHeight(String height) /*-{
      this.height = height;
    }-*/;

    public final native void setRegion(String region) /*-{
      this.region = region;
    }-*/;

    public final native void setShowLegend(boolean show) /*-{
      this.showLegend = show;
    }-*/;

    public final native void setShowZoomOut(boolean show) /*-{
      this.showZoomOut = show;
    }-*/;

    public final void setSize(int width, int height) {
      setWidth(width);
      setHeight(height);
    }

    public final void setSize(String width, String height) {
      setWidth(width);
      setHeight(height);
    }

    public final native void setWidth(int width) /*-{
      this.width = width + 'px';
    }-*/;
    
    public final native void setWidth(String width) /*-{
      this.width = width;
    }-*/;
    
    public final native void setZoomOutLabel(String label) /*-{
      this.zoomOutLabel = label;
    }-*/;

    private native void setDataMode(String mode) /*-{
      this.dataMode = mode;
    }-*/;
  }

  public static final String PACKAGE = "geomap";

  public GeoMap() {
    super();
    // Setting size makes  a big difference for this visualization.
    setSize("100%", "100%");   
  }

  public GeoMap(AbstractDataTable data, Options options) {
    super(data, options);
    // Setting size makes  a big difference for this visualization.
    setSize("100%", "100%");
  }

  public void addReadyHandler(ReadyHandler handler) {
    Handler.addHandler(this, "drawingDone", handler);
  }

  public void addRegionClickHandler(RegionClickHandler handler) {
    Handler.addHandler(this, "regionClick", handler);
  }

  public final void addSelectHandler(SelectHandler handler) {
    Selection.addSelectHandler(this, handler);
  }

  public void addZoomOutHandler(ZoomOutHandler handler) {
    Handler.addHandler(this, "zoomOut", handler);
  }

  public final JsArray<Selection> getSelections() {
    return Selection.getSelections(this);
  }

  public final void setSelections(JsArray<Selection> sel) {
    Selection.setSelections(this, sel);
  }

  @Override
  protected native JavaScriptObject createJso(Element parent) /*-{
    return new $wnd.google.visualization.GeoMap(parent);
  }-*/; 
}

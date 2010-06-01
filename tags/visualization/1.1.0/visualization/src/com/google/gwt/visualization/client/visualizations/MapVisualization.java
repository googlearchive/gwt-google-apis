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
import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.Element;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.AbstractDrawOptions;
import com.google.gwt.visualization.client.Selectable;
import com.google.gwt.visualization.client.Selection;
import com.google.gwt.visualization.client.events.SelectHandler;

/**
 * Map visualization using the Google Maps API. Data values are displayed as
 * points on the map. Data values can be coordinates or addresses.
 * 
 * You need to load the Google Maps API with a valid Maps key in order to use
 * this visualization.
 * 
 * @see <a href=
 *      "http://code.google.com/apis/visualization/documentation/gallery/map.html"
 *      >Map Visualization Reference</a>
 */
public class MapVisualization extends Visualization<MapVisualization.Options>
    implements Selectable {
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

    public final native void setEnableScrollWheel(boolean enable) /*-{
      this.enableScrollWheel = enable;
    }-*/;

    public final native void setLineColor(String color) /*-{
      this.lineColor = color;
    }-*/;

    public final native void setLineWidth(int width) /*-{
      this.lineWidth = width;
    }-*/;

    public final void setMapType(Type type) {
      setMapType(type.name().toLowerCase());
    }

    public final native void setShowLine(boolean show) /*-{
      this.showLine = show;
    }-*/;

    public final native void setShowTip(boolean show) /*-{
      this.showTip = show;
    }-*/;

    public final native void setZoomLevel(double zoomLevel) /*-{
      this.zoomLevel = zoomLevel;
    }-*/;
    
    private native void setMapType(String type) /*-{
      this.mapType = type;
    }-*/;
  }

  /**
   * The type of map to use.
   */
  public static enum Type {
    HYBRID, NORMAL, SATELLITE
  }

  public static final String PACKAGE = "map";

  /**
   * 
   * @param data data to visualize
   * @param options Optional parameters for the visualization
   * @param width the CSS specifier for the width of the visualization
   * @param height the CSS specifier for the height of the visualization
   */
  public MapVisualization(AbstractDataTable data, Options options, String width, String height) {
    super(data, options);
    setSize(width, height);
  }

  /**
   * 
   * @param width the CSS specifier for the width of the visualization
   * @param height the CSS specifier for the height of the visualization
   */  
  public MapVisualization(String width, String height) {
    super();
    setSize(width, height);
  }

  public final void addSelectHandler(SelectHandler handler) {
    Selection.addSelectHandler(this, handler);
  }

  public final JsArray<Selection> getSelections() {
    return Selection.getSelections(this);
  }

  public final void setSelections(JsArray<Selection> sel) {
    Selection.setSelections(this, sel);
  }

  @Override
  protected native JavaScriptObject createJso(Element parent) /*-{
    return new $wnd.google.visualization.Map(parent);
  }-*/;
}

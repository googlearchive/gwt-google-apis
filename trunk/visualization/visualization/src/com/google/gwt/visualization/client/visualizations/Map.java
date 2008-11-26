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
import com.google.gwt.dom.client.Element;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.AbstractDrawOptions;
import com.google.gwt.visualization.client.Selectable;
import com.google.gwt.visualization.client.Selection;
import com.google.gwt.visualization.client.SelectionHelper;
import com.google.gwt.visualization.client.VisualizationWidget;
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
public class Map extends Visualization<Map.Options> implements Selectable {
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

    private native void setMapType(String type) /*-{
      this.mapType = type;
    }-*/;
  }

  /**
   * The type of map to use.
   */
  public static enum Type {
    NORMAL, SATELLITE, HYBRID
  }

  public static native Map create(Element parent) /*-{
    return new $wnd.google.visualization.Map(parent);
  }-*/;

  public static VisualizationWidget<Map, Options> createWidget(
      AbstractDataTable data, Options options, int width, int height) {
    Element div = createDiv(width, height);
    Map viz = create(div);
    return new VisualizationWidget<Map, Options>(div, viz, data, options);
  }

  public static VisualizationWidget<Map, Options> createWidget(int width,
      int height) {
    Element div = createDiv(width, height);
    Map viz = create(div);
    return new VisualizationWidget<Map, Options>(div, viz);
  }

  protected Map() {
  }

  public final void addSelectHandler(SelectHandler handler) {
    SelectionHelper.addSelectHandler(this, handler);
  }

  public final Selection getSelection() {
    return SelectionHelper.getSelection(this);
  }

  public final void setSelection(Selection sel) {
    SelectionHelper.setSelection(this, sel);
  }
}

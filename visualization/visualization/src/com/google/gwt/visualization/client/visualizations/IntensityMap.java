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
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.dom.client.Element;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.AbstractDrawOptions;
import com.google.gwt.visualization.client.Selectable;
import com.google.gwt.visualization.client.Selection;
import com.google.gwt.visualization.client.events.SelectHandler;

/**
 * A visualizations that highlights regions on a map, such as countries or
 * states, based on the values provided for each region.
 * 
 * 
 * @see <a href="http://code.google.com/apis/visualization/documentation/gallery/intensitymap.html"
 *      > Intensity Map Visualization Reference</a>
 */
public class IntensityMap extends Visualization<IntensityMap.Options> implements
    Selectable {
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

    public final native void setColors(JsArrayString colors) /*-{
      this.colors = colors;
    }-*/;

    public final void setColors(String... colors) {
      setColors(ArrayHelper.toJsArrayString(colors));
    }

    public final native void setHeight(int height) /*-{
      this.height = height;
    }-*/;

    public final void setRegion(Region region) {
      setRegion(region.name().toLowerCase());
    }

    public final native void setShowOneTab(boolean show) /*-{
      this.showOneTab = show;
    }-*/;

    public final void setSize(int width, int height) {
      setWidth(width);
      setHeight(height);
    }

    public final native void setWidth(int width) /*-{
      this.width = width;
    }-*/;

    private native void setRegion(String region) /*-{
      this.region = region;
    }-*/;
  }

  /**
   * Argument to {@link IntensityMap.Options#setRegion(String)}
   */
  public static enum Region {
    AFRICA, ASIA, EUROPE, MIDDLE_EAST, SOUTH_AMERICA, USA, WORLD
  }

  public static final String PACKAGE = "intensitymap";

  public IntensityMap() {
    super();
  }

  public IntensityMap(AbstractDataTable data, Options options) {
    super(data, options);
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
    return new $wnd.google.visualization.IntensityMap(parent);
  }-*/;
}

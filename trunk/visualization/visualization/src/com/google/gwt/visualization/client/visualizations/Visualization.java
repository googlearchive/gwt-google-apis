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
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.AbstractDrawOptions;
import com.google.gwt.visualization.client.events.ErrorHandler;
import com.google.gwt.visualization.client.events.Handler;

/**
 * A Visualization object can draw a DataTable.
 * 
 * @param <OptionsType> The options for drawing this visualization.
 */
public abstract class Visualization<OptionsType extends AbstractDrawOptions> extends
    Widget {
  /**
   * Create a div with the given width and height.
   * 
   * @param width The desired width.
   * @param height The desired height.
   * @return An HTML <div> Element.
   */
  public static Element createDiv(int width, int height) {
    Element result = DOM.createDiv();
    setSize(result, width, height);
    return result;
  }

  /**
   * Set the size of a div element by setting the style attribute.
   * 
   * @param div An HTML <div> Element.
   * @param width The desired width.
   * @param height The desierd height.
   */
  public static void setSize(Element div, int width, int height) {
    div.getStyle().setPropertyPx("width", width);
    div.getStyle().setPropertyPx("height", height);
  }

  private AbstractDataTable dataTable;
  private OptionsType options;
  private JavaScriptObject jso;

  public Visualization() {
    Element div = DOM.createDiv();
    jso = createJso(div);
    setElement(div);
    setStyleName("gwt-viz-container");
  }

  public Visualization(AbstractDataTable data, OptionsType options) {
    this();
    this.options = options;
    this.dataTable = data;
  }

  public final void addErrorHandler(ErrorHandler handler) {
    Handler.addHandler(this, "error", handler);
  }

  /**
   * Draws the visualization.
   * 
   * @param data The DataTable with the data.
   */
  public final native void draw(AbstractDataTable data) /*-{
    this.@com.google.gwt.visualization.client.visualizations.Visualization::jso.draw(data, {});
  }-*/;

  /**
   * Draws the visualization.
   * 
   * @param data The DataTable with the data.
   * @param options The options for drawing this visualization.
   */
  public final native void draw(AbstractDataTable data, OptionsType options) /*-{
    this.@com.google.gwt.visualization.client.visualizations.Visualization::jso.draw(data, options);
  }-*/;

  /**
   * Note: calling this method should not usually be necessary except by
   * subclasses. If you need to call it, make sure you know what you're doing.
   * 
   * @return The underlying JavaScriptObject representing the JavaScript
   *         implementation of the visualization.
   */
  public JavaScriptObject getJso() {
    return jso;
  }

  /**
   * Creates an instance of the underlying JavaScriptObject for this
   * visualization. Subclasses must override this with JSNI that calls the
   * JavaScript constructor for their visualization.
   * 
   * @param div The container for the visualization.
   * @return The underlying JavaScriptObject for the visualization.
   */
  protected abstract JavaScriptObject createJso(Element div);

  @Override
  protected void onLoad() {
    if (dataTable != null && options != null) {
      draw(dataTable, options);
      dataTable = null;
      options = null;
    }
  }
}

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
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.AbstractDrawOptions;

/**
 * A Visualization object can draw a DataTable.
 * 
 * @param <E> The options for drawing this visualization.
 */
public class Visualization<E extends AbstractDrawOptions> extends
    JavaScriptObject {
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

  protected Visualization() {
  }

  /**
   * Draws the visualization.
   * 
   * @param data The DataTable with the data.
   */
  public final native void draw(AbstractDataTable data) /*-{
    this.draw(data, {});
  }-*/;

  /**
   * Draws the visualization.
   * 
   * @param data The DataTable with the data.
   * @param options The options for drawing this visualization.
   */
  public final native void draw(AbstractDataTable data, E options) /*-{
    this.draw(data, options);
  }-*/;
}

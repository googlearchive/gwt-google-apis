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

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;

/**
 * 
 * Intended to hold a single visualization. This class is a base class for visualization widgets.
 * 
 * @param <T> visualization class
 * @param <E> draw options for this class
 * 
 */
public abstract class AbstractVisualizationContainer<T extends Visualization<E>, E extends AbstractDrawOptions>
    extends Widget {
  private DataTable dataTable = null;
  private Element elem;
  private E options = null;
  private T visualization = null;

  /**
   * This constructor will not automatically draw the visualization.
   */
  public AbstractVisualizationContainer() {
    elem = DOM.createDiv();
    setElement(elem);
    setStyleName("gwt-viz-container");
    visualization = create(elem);
  }

  /**
   * This constructor will automatically draw the visualization with the
   * provided dataTable and options.
   * 
   * @param dataTable the data table.
   * @param options the options for drawing the visualization.
   */
  public AbstractVisualizationContainer(DataTable dataTable, E options) {
    this();
    this.options = options;
    this.dataTable = dataTable;
  }

  /**
   * Draws the visualization.
   * 
   * @param dataTable the data table.
   * @param options the options for drawing the visualization.
   */
  public void draw(DataTable dataTable, E options) {
    visualization.draw(dataTable, options);
  }

  /**
   * This method creates the Visualization. 
   * @param elem parent element of the visualization.
   * @return the visualization.
   */
  protected abstract T create(Element elem);

  /**
   * @return the visualization object.
   */
  protected T getVisualization() {
    return visualization;
  }

  @Override
  protected void onLoad() {
    if (dataTable != null && options != null) {
      draw(dataTable, options);
      dataTable = null;
      options = null;
    }
  }
}

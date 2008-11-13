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
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.visualizations.Visualization;

/**
 * Intended to hold a single visualization. This class is a base class for
 * visualization widgets.
 * 
 * @param <VisualizationType> visualization class
 * @param <OptionsType> draw options for this class
 * 
 */
public class VisualizationWidget<VisualizationType extends Visualization<OptionsType>, OptionsType extends AbstractDrawOptions>
    extends Widget {
  private AbstractDataTable dataTable = null;
  private OptionsType options = null;
  private VisualizationType visualization = null;

  /**
   * This constructor will not automatically draw the visualization.
   * 
   * @param visualization The visualization to be displayed in the widget.
   */
  public VisualizationWidget(Element elem, VisualizationType visualization) {
    setElement(elem);
    setStyleName("gwt-viz-container");
    this.visualization = visualization;
  }

  /**
   * This constructor will automatically draw the visualization with the
   * provided dataTable and options.
   * 
   * @param visualization the visualization to be displayed in the widget.
   * @param dataTable the data table.
   * @param options the options for drawing the visualization.
   */
  public VisualizationWidget(Element elem, VisualizationType visualization,
      AbstractDataTable dataTable, OptionsType options) {
    this(elem, visualization);
    this.options = options;
    this.dataTable = dataTable;
  }

  /**
   * Draws the visualization.
   * 
   * @param dataTable the data table.
   */
  public void draw(AbstractDataTable dataTable) {
    visualization.draw(dataTable, null);
  }

  /**
   * Draws the visualization.
   * 
   * @param dataTable the data table.
   * @param options the options for drawing the visualization.
   */
  public void draw(AbstractDataTable dataTable, OptionsType options) {
    visualization.draw(dataTable, options);
  }

  /**
   * @return the visualization object.
   */
  public VisualizationType getVisualization() {
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

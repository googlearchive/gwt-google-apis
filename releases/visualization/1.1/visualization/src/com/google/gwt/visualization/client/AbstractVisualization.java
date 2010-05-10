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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * AbstractVisualization implementations can draw a visualization.
 * 
 * This class is implemented to write visualizations in GWT and export them to
 * JavaScript.
 * 
 * @param <E> The draw options class.
 * 
 */
public abstract class AbstractVisualization<E extends AbstractDrawOptions>
    extends Composite {
  /**
   * This interface is implemented by a factory class that can create
   * visualizations.
   */
  public interface VisualizationFactory {
    AbstractVisualization<?> create();
  }

  /**
   * This function makes the GWT class available in JavaScript.
   * 
   * @param name JavaScript name of the class.
   * @param factory Factory object that instantiates the visualization
   */
  public static final native void registerVisualization(String name,
      VisualizationFactory factory) /*-{
    // setup the constructor
    $wnd[name] = function(container) {
      this.gwt_vis = @com.google.gwt.visualization.client.AbstractVisualization::createVisualization(Lcom/google/gwt/visualization/client/AbstractVisualization$VisualizationFactory;Lcom/google/gwt/core/client/JavaScriptObject;Lcom/google/gwt/user/client/Element;)(factory, this, container);
    }
    // setup the required draw method
    $wnd[name].prototype.draw = function(data, options) {
      this.gwt_vis.@com.google.gwt.visualization.client.AbstractVisualization::draw(Lcom/google/gwt/visualization/client/AbstractDataTable;Lcom/google/gwt/visualization/client/AbstractDrawOptions;)(data, options);
    }
  }-*/;

  @SuppressWarnings("unused")
  private static AbstractVisualization<?> createVisualization(
      VisualizationFactory factory, JavaScriptObject jsVisualization,
      Element container) {
    AbstractVisualization<?> visualization = factory.create();
    visualization.jsVisualization = jsVisualization;

    // It is very important to call RootPanel, otherwise event handling does not
    // work.
    RootPanel.get(container.getId()).add(visualization);

    // Register optional methods.
    if (visualization instanceof Selectable) {
      registerSelectFunctions(jsVisualization);
    }
    return visualization;
  }

  private static native void fireSelectionEvent(JavaScriptObject jso) /*-{
    $wnd.google.visualization.events.trigger(jso, 'select', null);
  }-*/;

  private static native void registerSelectFunctions(JavaScriptObject jso) /*-{
    jso.getSelection = function() {
      return this.gwt_vis.@com.google.gwt.visualization.client.Selectable::getSelections()();
    }
    jso.setSelection = function(selection) {
      this.gwt_vis.@com.google.gwt.visualization.client.Selectable::setSelections(Lcom/google/gwt/core/client/JsArray;)(selection);
    }
  }-*/;

  /**
   * The javascript visualization is used to wrap the GWT visualization.
   */
  protected JavaScriptObject jsVisualization;

  /**
   * Draw the visualization.
   * 
   * @param data the DataTable to draw.
   * @param options the options for drawing the visualization.
   */
  public abstract void draw(AbstractDataTable data, E options);

  /**
   * Fires an event to notify the selection event listeners.
   */
  public void fireSelectionEvent() {
    fireSelectionEvent(this.jsVisualization);
  }

  /**
   * Note: calling this method should not usually be necessary except by
   * subclasses. If you need to call it, make sure you know what you're doing.
   * 
   * @return The underlying JavaScriptObject representing the JavaScript
   *         implementation of the visualization.
   */
  public JavaScriptObject getJso() {
    return jsVisualization;
  }
}

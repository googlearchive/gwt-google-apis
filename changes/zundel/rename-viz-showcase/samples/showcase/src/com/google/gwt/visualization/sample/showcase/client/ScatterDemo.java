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
package com.google.gwt.visualization.sample.showcase.client;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.VisualizationWidget;
import com.google.gwt.visualization.client.visualizations.ScatterChart;
import com.google.gwt.visualization.client.visualizations.ScatterChart.Options;

/**
 * Demo for ScatterChart visualization.
 */
public class ScatterDemo implements LeftTabPanel.WidgetProvider {
  public Widget getWidget() {
    Options options = Options.create();
    options.setHeight(240);
    options.setTitle("My Daily Activities");
    options.setWidth(400);

    DataTable data = Showcase.getDailyActivities();
    
    VisualizationWidget<ScatterChart, ScatterChart.Options> widget = 
      ScatterChart.createWidget(data, options);
    ScatterChart viz = widget.getVisualization();
    Label status = new Label();
    viz.addSelectHandler(new SelectionDemo(viz, status));
    
    VerticalPanel result = new VerticalPanel();
    result.add(status);
    result.add(widget);
    return result;
  }
}

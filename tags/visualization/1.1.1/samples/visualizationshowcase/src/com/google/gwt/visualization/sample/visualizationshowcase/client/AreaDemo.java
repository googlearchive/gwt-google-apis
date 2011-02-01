/*
 * Copyright 2010 Google Inc.
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
package com.google.gwt.visualization.sample.visualizationshowcase.client;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.visualizations.corechart.AreaChart;
import com.google.gwt.visualization.client.visualizations.corechart.AxisOptions;
import com.google.gwt.visualization.client.visualizations.corechart.Options;

/**
 * Demo for AreaChart visualization.
 */
public class AreaDemo implements LeftTabPanel.WidgetProvider {
  public Widget getWidget() {
    VerticalPanel result = new VerticalPanel();

    Options options = Options.create();
    options.setHeight(240);
    options.setTitle("Company Performance");
    options.setWidth(400);
    AxisOptions vAxisOptions = AxisOptions.create();
    vAxisOptions.setMinValue(0);
    vAxisOptions.setMaxValue(2000);
    options.setVAxisOptions(vAxisOptions);

    DataTable data = Showcase.getCompanyPerformance();
    AreaChart viz = new AreaChart(data, options);

    Label status = new Label();
    Label onMouseOverAndOutStatus = new Label();
    viz.addSelectHandler(new SelectionDemo(viz, status));
    viz.addReadyHandler(new ReadyDemo(status));
    viz.addOnMouseOverHandler(new OnMouseOverDemo(onMouseOverAndOutStatus));
    viz.addOnMouseOutHandler(new OnMouseOutDemo(onMouseOverAndOutStatus));
    result.add(status);
    result.add(viz);
    result.add(onMouseOverAndOutStatus);
    return result;
  }
}

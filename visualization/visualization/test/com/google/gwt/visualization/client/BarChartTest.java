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

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.visualizations.BarChart;
import com.google.gwt.visualization.client.visualizations.BarChart.Options;

/**
 * Tests for the BarChart class.
 */
public class BarChartTest extends VisualizationTest {
  @Override
  public String getModuleName() {
    return "com.google.gwt.visualization.VisualizationTest";
  }
  
  public void testBarChart() {
    loadApi(new Runnable() {
      public void run() {
        Widget widget;
        Options options = Options.create();
        options.setWidth(400);
        options.setHeight(400);
        options.set3D(true);
        options.setStacked(false);
        widget = BarChart.createWidget(createCompanyPerformance(), options);
        RootPanel.get().add(widget);
      }});
  }
  
  @Override
  protected String getVisualizationPackage() {
    return BarChart.PACKAGE;
  }
}

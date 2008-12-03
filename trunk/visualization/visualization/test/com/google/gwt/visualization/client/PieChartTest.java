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
import com.google.gwt.visualization.client.visualizations.PieChart;

/**
 * Tests for the PieChart class.
 */
public class PieChartTest extends VisualizationTest {
  /**
   * This test case will try creating a simple Pie chart. It first asserts that
   * the Visualization API has been correctly loaded (see ajax_loader.html).
   */
  public void testASimplePieChart() {
    loadApi(new Runnable() {
      public void run() {
        DataTable data = createDailyActivities();

        // Create a minimal pie chart.
        PieChart.Options options = PieChart.Options.create();
        options.setWidth(400);
        options.setHeight(240);
        RootPanel.get().add(PieChart.createWidget(data, options));
      }
    });
  }

  /**
   * Tests the options that are peculiar to the PieChart.Options class.
   */
  public void testPieChartOptions() {
    PieChart.Options options = PieChart.Options.create();
    options.set3D(true);
  }

  @Override
  protected String getVisualizationPackage() {
    return PieChart.PACKAGE;
  }
}

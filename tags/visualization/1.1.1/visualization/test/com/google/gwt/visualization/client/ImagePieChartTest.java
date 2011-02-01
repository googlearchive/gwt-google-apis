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
package com.google.gwt.visualization.client;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.visualizations.ImagePieChart;
import com.google.gwt.visualization.client.visualizations.ImagePieChart.Options;

/**
 * Tests for the {@link ImagePieChart} class.
 */
public class ImagePieChartTest extends VisualizationTest {

  @Override
  protected String getVisualizationPackage() {
    return ImagePieChart.PACKAGE;
  }

  public void testPieChart() {
    loadApi(new Runnable() {
      public void run() {
        Widget widget;
        Options options = Options.create();
        widget = new ImagePieChart(createCompanyPerformance(), options);
        RootPanel.get().add(widget);
      }
    });
  }

  public void testPieChartAllOptions() {
    loadApi(new Runnable() {
      public void run() {
        Widget widget;
        Options options = Options.create();
        options.setBackgroundColor("#FFFFFF");
        options.setColors("#FF0000", "#00FF00", "#0000FF");
        options.setHeight(400);
        options.setWidth(400);
        options.setLegend(LegendPosition.BOTTOM);
        options.setMin(0);
        options.setMax(100);
        options.setLabels("none");
        options.setIs3D(true);
        options.setColor("#ff0000");
        widget = new ImagePieChart(createDailyActivities(), options);
        RootPanel.get().add(widget);
      }
    });
  }
}

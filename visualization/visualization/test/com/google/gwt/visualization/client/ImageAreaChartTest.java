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
import com.google.gwt.visualization.client.visualizations.ImageAreaChart;
import com.google.gwt.visualization.client.visualizations.ImageAreaChart.Options;

/**
 * Tests for the {@link ImageAreaChart} class.
 */
public class ImageAreaChartTest extends VisualizationTest {

  @Override
  protected String getVisualizationPackage() {
    return ImageAreaChart.PACKAGE;
  }

  public void testAreaChart() {
    loadApi(new Runnable() {
      public void run() {
        Widget widget;
        Options options = Options.create();
        widget = new ImageAreaChart(createCompanyPerformance(), options);
        RootPanel.get().add(widget);
      }
    });
  }

  public void testAreaChartAllOptions() {
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
        options.setShowCategoryLabels(true);
        options.setShowValueLabels(true);
        options.setTitle("test");
        options.setValueLabelsInterval(20);
        widget = new ImageAreaChart(createCompanyPerformance(), options);
        RootPanel.get().add(widget);
      }
    });
  }
}

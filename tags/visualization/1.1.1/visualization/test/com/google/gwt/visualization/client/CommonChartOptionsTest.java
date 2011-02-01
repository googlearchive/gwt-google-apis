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
import com.google.gwt.dom.client.IFrameElement;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.visualizations.AreaChart;

/**
 * Tests for the CommonChartOptions class. Uses AreaChart, a typical client of
 * CommonChartOptions.
 */
public class CommonChartOptionsTest extends VisualizationTest {
  public void testOptions() {
    loadApi(new Runnable() {
      public void run() {
        DataTable data = createDailyActivities();

        // Create a minimal pie chart.
        AreaChart.Options options = AreaChart.Options.create();
        options.setHeight(400);
        options.setWidth(400);
        options.setAxisBackgroundColor("pink");
        options.setAxisColor("pink");
        options.setAxisFontSize(32.0);
        options.setLogScale(true);
        options.setReverseAxis(true);
        options.setTitleX("activity");
        options.setTitleY("hours");
        options.setShowCategories(true);

        Widget widget = new AreaChart(data, options);
        RootPanel.get().add(widget);
        Element div = widget.getElement();
        // assert that the div's first child is an iframe
        IFrameElement.as(div.getFirstChildElement());
      }
    });
  }

  @Override
  protected String getVisualizationPackage() {
    return AreaChart.PACKAGE;
  }
}

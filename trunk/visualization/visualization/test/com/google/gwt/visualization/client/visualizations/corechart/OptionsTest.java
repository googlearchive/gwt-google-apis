/*
 * Copyright 2011 Google Inc.
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
package com.google.gwt.visualization.client.visualizations.corechart;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.IFrameElement;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.ChartArea;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.LegendPosition;
import com.google.gwt.visualization.client.VisualizationTest;
import com.google.gwt.visualization.client.visualizations.corechart.PieChart.PieOptions;

import com.google.gwt.visualization.client.Color;

/**
 * Tests for the Options class. Uses PieChart for historical reasons.
 */
public class OptionsTest extends VisualizationTest {
  public void testOptions() {
    loadApi(new Runnable() {
      public void run() {
        DataTable data = createDailyActivities();

        // Create a minimal pie chart.
        PieOptions pieOptions = PieChart.createPieOptions();
        Options options = pieOptions;
        // Common options
        options.setAxisTitlesPosition("out");
        options.setBackgroundColor(Color.create("pink", "black", 2));
        ChartArea chartArea = ChartArea.create();
        chartArea.setTop(5);
        chartArea.setHeight(6);
        chartArea.setWidth(7);
        chartArea.setLeft(8);
        options.setChartArea(chartArea);
        options.setColors("pink", "black", "white");
        options.setFontName("Verbena");
        options.setFontSize(12);
        options.setGridlineColor("blue");
        options.setHeight(400);
        options.setInterpolateNulls(true);
        options.setLegend(LegendPosition.RIGHT);
        TextStyle textStyle = TextStyle.create();
        textStyle.setFontSize(10);
        textStyle.setFontName("Courier New");
        options.setLegendTextStyle(textStyle);
        options.setLineWidth(5);
        options.setTitle("My Daily Activities");
        options.setTitleTextStyle(textStyle);
        options.setTooltipTextStyle(textStyle);
        options.setWidth(400);

        // Pie specific
        pieOptions.set3D(true);
        pieOptions.setPieSliceText("data");
        pieOptions.setPieSliceTextStyle(textStyle);
        pieOptions.setPieResidueSliceLabel("Bling");

        Widget widget = new PieChart(data, options);
        RootPanel.get().add(widget);
        Element div = widget.getElement();
        // assert that the div's first child is an iframe
        IFrameElement.as(div.getFirstChildElement());
      }
    });
  }

  /**
   * Use a different test module.  Charts loaded with "corechart" cannot co-exist
   * with legacy charts.
   */
  @Override
  public String getModuleName() {
    return "com.google.gwt.visualization.CoreChartVisualizationTest";
  }

  @Override
  protected String getVisualizationPackage() {
    return CoreChart.PACKAGE;
  }
}

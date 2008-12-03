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
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.visualization.client.visualizations.PieChart;
import com.google.gwt.visualization.client.visualizations.PieChart.Options;
import com.google.gwt.visualization.client.visualizations.PieChart.PieLegendPosition;

/**
 * Tests for the CommonOptions class.  Uses PieChart because PieChart
 * is the only viz that uses CommonOptions rather than its subclass,
 * CommonChartOptions.
 */
public class CommonOptionsTest extends VisualizationTest {
  public void testWidth() {
    loadApi(new Runnable(){
      public void run() {
        DataTable data = createDailyActivities();

        // Create a minimal pie chart.
        PieChart.Options options = PieChart.Options.create();
        options.setWidth(400);
        VisualizationWidget<PieChart, Options> widget = 
          PieChart.createWidget(data, options);
        RootPanel.get().add(widget);
        Element div = widget.getElement();
        Element iframe = div.getFirstChildElement();
        assertEquals("400", iframe.getAttribute("width"));
      }});
  }
  
  public void testHeight() {
    loadApi(new Runnable(){
      public void run() {
        DataTable data = createDailyActivities();

        // Create a minimal pie chart.
        PieChart.Options options = PieChart.Options.create();
        options.setHeight(400);
        VisualizationWidget<PieChart, Options> widget = 
          PieChart.createWidget(data, options);
        RootPanel.get().add(widget);
        Element div = widget.getElement();
        Element iframe = div.getFirstChildElement();
        assertEquals("400", iframe.getAttribute("height"));
      }});
  }
  
  public void testLegend() {
    loadApi(new Runnable(){
      public void run() {
        Options options;
        DataTable data = createDailyActivities();

        options = Options.create();
        options.setLegend(LegendPosition.BOTTOM);
        RootPanel.get().add(PieChart.createWidget(data, options));

        options = Options.create();
        options.setLegend(LegendPosition.TOP);
        RootPanel.get().add(PieChart.createWidget(data, options));

        options = Options.create();
        options.setLegend(LegendPosition.LEFT);
        RootPanel.get().add(PieChart.createWidget(data, options));

        options = Options.create();
        options.setLegend(LegendPosition.RIGHT);
        RootPanel.get().add(PieChart.createWidget(data, options));

        options = Options.create();
        options.setLegend(LegendPosition.NONE);
        RootPanel.get().add(PieChart.createWidget(data, options));

        options = Options.create();
        options.setLegend(PieLegendPosition.LABEL);
        RootPanel.get().add(PieChart.createWidget(data, options));
      }});
  }
  
  @Override
  protected String getVisualizationPackage() {
    return PieChart.PACKAGE;
  }
  
  protected DataTable createDataTable() {
    return createDailyActivities();
  }
  
  protected Options createOptions() {
    return Options.create();
  }
  
  protected VisualizationWidget<PieChart, Options> 
  createWidget(DataTable data, Options options) {
    return PieChart.createWidget(data, options);
  }
}

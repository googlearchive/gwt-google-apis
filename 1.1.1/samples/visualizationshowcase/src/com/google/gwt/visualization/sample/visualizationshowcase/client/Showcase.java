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
package com.google.gwt.visualization.sample.visualizationshowcase.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.visualizations.AnnotatedTimeLine;
import com.google.gwt.visualization.client.visualizations.Gauge;
import com.google.gwt.visualization.client.visualizations.GeoMap;
import com.google.gwt.visualization.client.visualizations.ImageAreaChart;
import com.google.gwt.visualization.client.visualizations.ImageBarChart;
import com.google.gwt.visualization.client.visualizations.ImageChart;
import com.google.gwt.visualization.client.visualizations.ImageLineChart;
import com.google.gwt.visualization.client.visualizations.ImagePieChart;
import com.google.gwt.visualization.client.visualizations.ImageSparklineChart;
import com.google.gwt.visualization.client.visualizations.IntensityMap;
import com.google.gwt.visualization.client.visualizations.MapVisualization;
import com.google.gwt.visualization.client.visualizations.MotionChart;
import com.google.gwt.visualization.client.visualizations.OrgChart;
import com.google.gwt.visualization.client.visualizations.Table;
import com.google.gwt.visualization.client.visualizations.corechart.CoreChart;

/**
 * Google Visualization API in GWT demo.
 */
class Showcase implements EntryPoint {

  static DataTable getCompanyPerformance() {
    DataTable data = getCompanyPerformanceWithNulls();
    data.setValue(2, 1, 660);
    data.setValue(2, 2, 1120);
    return data;
  }

  static DataTable getCompanyPerformanceWithNulls() {
    DataTable data = DataTable.create();
    data.addColumn(ColumnType.STRING, "Year");
    data.addColumn(ColumnType.NUMBER, "Sales");
    data.addColumn(ColumnType.NUMBER, "Expenses");
    data.addRows(4);
    data.setValue(0, 0, "2004");
    data.setValue(0, 1, 1000);
    data.setValue(0, 2, 400);
    data.setValue(1, 0, "2005");
    data.setValue(1, 1, 1170);
    data.setValue(1, 2, 460);
    data.setValue(2, 0, "2006");
    data.setValueNull(2, 1);
    data.setValueNull(2, 2);
    data.setValue(3, 0, "2007");
    data.setValue(3, 1, 1030);
    data.setValue(3, 2, 540);
    return data;
  }

  static DataTable getDailyActivities() {
    DataTable data = DataTable.create();
    data.addColumn(ColumnType.STRING, "Task");
    data.addColumn(ColumnType.NUMBER, "Hours per Day");
    data.addRows(5);
    data.setValue(0, 0, "Work");
    data.setValue(0, 1, 11);
    data.setValue(1, 0, "Eat");
    data.setValue(1, 1, 2);
    data.setValue(2, 0, "Commute");
    data.setValue(2, 1, 2);
    data.setValue(3, 0, "Watch TV");
    data.setValue(3, 1, 2);
    data.setValue(4, 0, "Sleep");
    data.setValue(4, 1, 7);
    return data;
  }

  static DataTable getSugarSaltAndCaloriesComparison() {
    DataTable data = DataTable.create();
    data.addColumn(ColumnType.NUMBER, "Sugar");
    data.addColumn(ColumnType.NUMBER, "Calories");
    data.addColumn(ColumnType.NUMBER, "Salt");
    data.addRows(4);
    data.setValue(0, 0, 20);
    data.setValue(0, 1, 11);
    data.setValue(0, 2, 30);
    data.setValue(1, 0, 30);
    data.setValue(1, 1, 10);
    data.setValue(1, 2, 20);
    data.setValue(2, 0, 27);
    data.setValue(2, 1, 2);
    data.setValue(2, 2, 34);
    data.setValue(3, 0, 22);
    data.setValue(3, 1, 2);
    data.setValue(3, 1, 9);
    return data;
  }

  public void onModuleLoad() {
    VisualizationUtils.loadVisualizationApi(
        new Runnable() {
          public void run() {
            final VerticalPanel vp = new VerticalPanel();
            vp.setSpacing(15);
            RootPanel.get().add(vp);
            vp.add(new HTML(
                "The following visualizations are included in the GWT "
                    + "Visualization API in the package "
                    + "<tt>com.google.gwt.visualization.client.visualizations</tt>.  "
                    + "For a full listing of visualizations available in GWT, see <a href="
                    + '"'
                    + "http://code.google.com/apis/visualization/documentation/gallery.html"
                    + '"' + ">the Google Visualization API</a>."));
            LeftTabPanel tabby = new LeftTabPanel();
            vp.add(tabby);
            AnnotatedDemo annotatedWidget = new AnnotatedDemo();
            tabby.add(annotatedWidget, "AnnotatedTimeLine");
            tabby.add(new AreaDemo(), "AreaChart");
            tabby.add(new ImageAreaDemo(), "AreaChart (Image)");
            tabby.add(new BarDemo(), "BarChart");
            tabby.add(new ImageBarDemo(), "BarChart (Image)");
            tabby.add(new ColumnDemo(), "ColumnChart");
            tabby.add(new GaugeDemo(), "Gauge");
            tabby.add(new GeoDemo(), "GeoMap");
            tabby.add(new IntensityDemo(), "IntensityMap");
            tabby.add(new LineDemo(), "LineChart");
            tabby.add(new ImageLineDemo(), "LineChart (Image)");
            tabby.add(new MapDemo(), "Map");
            tabby.add(new MoneyDemo(), "MoneyChart");
            tabby.add(new MotionDemo(), "MotionChart");
            tabby.add(new OrgDemo(), "OrgChart");
            tabby.add(new PieDemo(), "PieChart");
            tabby.add(new ImagePieDemo(), "PieChart (Image)");
            tabby.add(new ImageDemo(), "RadarChart (Image)");
            tabby.add(new ScatterDemo(), "ScatterChart");
            tabby.add(new SparklineDemo(), "Sparkline (Image)");
            tabby.add(new TableDemo(), "Table");
            tabby.add(new ToolbarDemo(), "Toolbar");
            tabby.setWidget(annotatedWidget);
          }
        }, AnnotatedTimeLine.PACKAGE, CoreChart.PACKAGE,
        Gauge.PACKAGE, GeoMap.PACKAGE, ImageChart.PACKAGE,
        ImageLineChart.PACKAGE, ImageAreaChart.PACKAGE, ImageBarChart.PACKAGE,
        ImagePieChart.PACKAGE, IntensityMap.PACKAGE,
        MapVisualization.PACKAGE, MotionChart.PACKAGE, OrgChart.PACKAGE,
        Table.PACKAGE,
        ImageSparklineChart.PACKAGE);
  }
}

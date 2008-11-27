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

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;


/**
 * Google Visualization API in GWT demo.
 */
class Showcase implements EntryPoint {
  public void onModuleLoad() {
    final VerticalPanel vp  = new VerticalPanel();
    vp.getElement().getStyle().setPropertyPx("margin", 15); 
    RootPanel.get().add(vp);
    vp.add(new HTML("The following visualizations are included in the GWT " +
    		"Visualization API in the package " +
    		"<tt>com.google.gwt.visualization.client.visualizations</tt>.  " +
    		"For a full listing of visualizations available in GWT, see <a href=" + 
    		'"' + 
    		"http://code.google.com/apis/visualization/documentation/gallery.html" + 
    		'"' + 
    		">the Google Visualization API</a>."));
    LeftTabPanel tabby = new LeftTabPanel();
    vp.add(tabby);
    tabby.add(new AnnotatedDemo(), "AnnotatedTimeLine");
    tabby.add(new AreaDemo(), "AreaChart");
    tabby.add(new BarDemo(), "BarChart");
    tabby.add(new ColumnDemo(), "ColumnChart");
    tabby.add(new GaugeDemo(), "Gauge");
    tabby.add(new IntensityDemo(), "IntensityMap");
    tabby.add(new LineDemo(), "LineChart");
    tabby.add(new MotionDemo(), "MotionChart");
    tabby.add(new OrgDemo(), "OrgChart");
    tabby.add(new PieDemo(), "PieChart");
    tabby.add(new ScatterDemo(), "ScatterChart");
    tabby.add(new TableDemo(), "Table");
    // TODO: add map demos
    // tabby.add(new GeoDemo(), "Geo Map");
    // tabby.add(new MapDemo(), "Map");
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

  static DataTable getCompanyPerformance() {
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
    data.setValue(2, 1, 660);
    data.setValue(2, 2, 1120);
    data.setValue(3, 0, "2007");
    data.setValue(3, 1, 1030);
    data.setValue(3, 2, 540);
    return data;
  }
}

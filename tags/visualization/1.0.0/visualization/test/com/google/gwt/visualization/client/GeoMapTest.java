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
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.visualizations.GeoMap;
import com.google.gwt.visualization.client.visualizations.GeoMap.DataMode;

/**
 * Tests for the GeoMap visualization class.
 */
public class GeoMapTest extends VisualizationTest {

  /**
   * This test case will try creating a simple Pie chart. It first asserts that
   * the Visualization API has been correctly loaded (see ajax_loader.html).
   */
  public void testASimpleGeoGeoMap() {
    loadApi(new Runnable() {
      public void run() {
        GeoMap.Options options = GeoMap.Options.create();
        DataTable data = makeDataTable();
        options.setRegion("world");
        GeoMap geoMap = new GeoMap(data, options);
        geoMap.setSize("300px", "300px");
        RootPanel.get().add(geoMap);
      }
    });
  }

  /**
   * Tests the options that are peculiar to the GeoMap.Options class.
   */
  public void testGeoMapOptions() {
    loadApi(new Runnable() {
      public void run() {
        GeoMap.Options options = GeoMap.Options.create();
        options.setDataMode(DataMode.REGIONS);
        options.setHeight(600);
        options.setWidth(450);
        options.setShowLegend(true);
        options.setRegion("world");

        DataTable data = makeDataTable();
        GeoMap geoMap = new GeoMap(data, options);
        RootPanel.get().add(geoMap);
      }
    });
  }

  @Override
  protected String getVisualizationPackage() {
    return GeoMap.PACKAGE;
  }
  
  /**
   * A support method used by several test cases to create a simple DataTable.
   */
  private DataTable makeDataTable() {
    // Create a simple data table
    DataTable dataTable = DataTable.create();
    dataTable.addRows(7);
    dataTable.addColumn(ColumnType.STRING, "ADDRESS", "address");
    dataTable.addColumn(ColumnType.NUMBER, "SITES", "color");
    dataTable.setValue(0, 0, "Israel");
    dataTable.setValue(0, 1, 1);
    dataTable.setValue(1, 0, "United States");
    dataTable.setValue(0, 1, 2);
    dataTable.setValue(2, 0, "CH");
    dataTable.setValue(0, 1, 3);
    return dataTable;
  }
}

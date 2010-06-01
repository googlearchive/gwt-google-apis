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
import com.google.gwt.visualization.client.visualizations.MapVisualization;
import com.google.gwt.visualization.client.visualizations.MapVisualization.Type;

/**
 * Tests for the Map visualization class.
 */
public class MapVisualizationTest extends VisualizationTest {
  @Override
  public String getModuleName() {
    return "com.google.gwt.visualization.MapVisualizationTest";
  }

  /**
   * This test case will try creating a simple Pie chart. It first asserts that
   * the Visualization API has been correctly loaded (see ajax_loader.html).
   */
  public void testASimpleMapVisualization() {
    loadApi(new Runnable() {
      public void run() {
        MapVisualization.Options options = MapVisualization.Options.create();
        DataTable data = makeDataTable();
        MapVisualization mapViz = new MapVisualization(data, options, "400px",
            "400px");
        RootPanel.get().add(mapViz);
      }
    });
  }

  /**
   * Tests the options that are peculiar to the Map.Options class.
   */
  public void testMapOptions() {
    loadApi(new Runnable() {
      public void run() {
        MapVisualization.Options options = MapVisualization.Options.create();
        options.setEnableScrollWheel(true);
        options.setLineColor("red");
        options.setLineWidth(4);
        options.setMapType(Type.HYBRID);
        options.setShowLine(true);
        options.setShowTip(true);
        options.setZoomLevel(10.0);
        DataTable data = makeDataTable();
        MapVisualization mapViz = new MapVisualization(data, options, "400px",
            "400px");
        RootPanel.get().add(mapViz);
      }
    });
  }

  @Override
  protected String getVisualizationPackage() {
    return MapVisualization.PACKAGE;
  }

  /**
   * A support method used by several test cases to create a simple DataTable.
   */
  private DataTable makeDataTable() {
    // Create a simple data table
    DataTable data = DataTable.create();
    data.addColumn(ColumnType.NUMBER, "Latitude");
    data.addColumn(ColumnType.NUMBER, "Longitude");
    data.addColumn(ColumnType.STRING, "City");
    data.addRows(3);
    data.setValue(0, 0, 47.37);
    data.setValue(0, 1, 8.54);
    data.setValue(0, 2, "Zurich");
    data.setValue(1, 0, 32.05);
    data.setValue(1, 1, 34.75);
    data.setValue(1, 2, "Tel Aviv");
    data.setValue(2, 0, 33.76);
    data.setValue(2, 1, -84.39);
    data.setValue(2, 2, "Atlanta");
    return data;
  }
}

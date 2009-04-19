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
import com.google.gwt.visualization.client.visualizations.Gauge;

/**
 * Tests for the Gauge class.
 */
public class GaugeTest extends VisualizationTest {

  /**
   * This test case will try creating a simple Gauge. It first asserts that the
   * Visualization API has been correctly loaded (see ajax_loader.html).
   */
  public void testASimpleGauge() {
    loadApi(new Runnable() {

      public void run() {
        DataTable data = makeDataTable();
        Gauge.Options options = Gauge.Options.create();
        options.setSize(600, 200);
        RootPanel.get().add(new Gauge(data, options));
      }
    });
  }

  /**
   * Tests the options that are peculiar to the Gauge.Options class.
   */
  public void testGaugeOptions() {
    loadApi(new Runnable() {

      public void run() {
        Gauge.Options options = Gauge.Options.create();
        options.setGaugeRange(0, 100);
        options.setGreenRange(0, 25);
        options.setHeight(200);
        options.setMajorTicks("OK", "WARM", "HOT");
        options.setMinorTicks(2);
        options.setRedRange(50, 100);
        options.setSize(600, 200);
        options.setWidth(600);
        options.setYellowRange(25, 50);
        DataTable data = makeDataTable();
        RootPanel.get().add(new Gauge(data, options));
      }
    });
  }

  @Override
  protected String getVisualizationPackage() {
    return Gauge.PACKAGE;
  }

  /**
   * A support method used by several test cases to create a simple DataTable.
   */
  private DataTable makeDataTable() {
    // Create a simple data table
    DataTable data = DataTable.create();
    data.addColumn(ColumnType.STRING, "Fruit");
    data.addColumn(ColumnType.NUMBER, "Baskets");
    data.addRows(3);
    data.setValue(0, 0, "Banana");
    data.setValue(0, 1, 12);
    data.setValue(1, 0, "Jalape–o");
    data.setValue(1, 1, 33);
    data.setValue(2, 0, "Haba–ero");
    data.setValue(2, 1, 99);
    return data;
  }
}

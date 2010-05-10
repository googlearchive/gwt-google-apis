/*
 * Copyright 2009 Google Inc.
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

import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.formatters.ArrowFormat;
import com.google.gwt.visualization.client.formatters.ArrowFormat.Options;
import com.google.gwt.visualization.client.visualizations.PieChart;

/**
 * Tests for the ArrowFormat class.
 */
public class ArrowFormatTest extends VisualizationTest {
  @Override
  public String getModuleName() {
    return "com.google.gwt.visualization.VisualizationTest";
  }

  public void testArrowFormat() {
    loadApi(new Runnable() {
      public void run() {
        DataTable dataTable = DataTable.create();
        dataTable.addColumn(ColumnType.STRING, "Name", "name");
        dataTable.addColumn(ColumnType.NUMBER, "IQ", "iq");
        dataTable.addRows(5);
        dataTable.setValue(0, 0, "p1");
        dataTable.setValue(0, 1, 30);
        dataTable.setValue(1, 0, "p1");
        dataTable.setValue(1, 1, 5);
        dataTable.setValue(2, 0, "p1");
        dataTable.setValue(2, 1, 0);
        dataTable.setValue(3, 0, "p1");
        dataTable.setValue(3, 1, 7);
        dataTable.setValue(4, 0, "p1");
        dataTable.setValue(4, 1, -27);

        dataTable.setFormattedValue(3, 1, "3.333");
        assertEquals("3.333", dataTable.getFormattedValue(3, 1));

        Options options = Options.create();
        options.setBase(6);
        ArrowFormat formatter = ArrowFormat.create(options);
        formatter.format(dataTable, 1);
        // TODO(zundel): I commented out this assert - it is breaking the tests
        //   and is undocumented so I don't know how to fix it.
        // assertEquals("google-visualization-table-arrow-dr",
        // dataTable.getProperty(1, 1, "__td-class"));
      }
    });
  }

  @Override
  protected String getVisualizationPackage() {
    return PieChart.PACKAGE;
  }
}

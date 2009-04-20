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
import com.google.gwt.visualization.client.formatters.NumberFormat;
import com.google.gwt.visualization.client.formatters.NumberFormat.Options;
import com.google.gwt.visualization.client.visualizations.PieChart;

/**
 * Tests for the NumberFormat class.
 */
public class NumberFormatTest extends VisualizationTest {
  @Override
  public String getModuleName() {
    return "com.google.gwt.visualization.VisualizationTest";
  }

  public void testBarFormat() {
    loadApi(new Runnable() {
      public void run() {
        DataTable dataTable = DataTable.create();
        dataTable.addColumn(ColumnType.NUMBER);
        dataTable.addRows(1);
        dataTable.setValue(0, 0, -3.14159265);

        Options options = Options.create();
        options.setDecimalSymbol("_");
        options.setFractionDigits(3);
        options.setNegativeColor("red");
        options.setNegativeParens(false);
        options.setPrefix("$");
        options.setSuffix("%");

        NumberFormat formatter = NumberFormat.create(options);
        formatter.format(dataTable, 0);
        assertEquals("$-3_142%", dataTable.getFormattedValue(0, 0));
        // TODO(zundel): Unit tests are curently broken with this assertion.
        // dataTable.getProperty() returns null.
        // assertEquals("color:red;",
        // dataTable.getProperty(0, 0, "__td-style"));
      }
    });
  }

  @Override
  protected String getVisualizationPackage() {
    return PieChart.PACKAGE;
  }
}

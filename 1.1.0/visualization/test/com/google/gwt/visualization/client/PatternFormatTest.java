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

import com.google.gwt.ajaxloader.client.ArrayHelper;
import com.google.gwt.core.client.JsArrayInteger;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.formatters.PatternFormat;
import com.google.gwt.visualization.client.visualizations.PieChart;

/**
 * Tests for the PatternFormat class.
 */
public class PatternFormatTest extends VisualizationTest {
  @Override
  public String getModuleName() {
    return "com.google.gwt.visualization.VisualizationTest";
  }

  public void testPatternFormat() {
    loadApi(new Runnable() {
      public void run() {
        DataTable dataTable = DataTable.create();
        dataTable.addColumn(ColumnType.STRING, "Name");
        dataTable.addColumn(ColumnType.NUMBER, "age");
        dataTable.addRows(3);
        dataTable.setValue(0, 0, "hillel");
        dataTable.setValue(0, 1, 25);
        dataTable.setValue(1, 0, "yoav");
        dataTable.setValue(1, 1, 24);
        dataTable.setValue(2, 0, "niv");
        dataTable.setValue(2, 1, 27);

        PatternFormat formatter = PatternFormat.create("{0} is {1} years old. \\{2\\}");
        int[] java = new int[]{0, 1};
        JsArrayInteger js = ArrayHelper.toJsArrayInteger(java);
        formatter.format(dataTable, js, 1);
        assertEquals("hillel is 25 years old. {2}",
            dataTable.getFormattedValue(0, 1));
        assertEquals("yoav is 24 years old. {2}", dataTable.getFormattedValue(
            1, 1));
      }
    });
  }

  @Override
  protected String getVisualizationPackage() {
    return PieChart.PACKAGE;
  }
}

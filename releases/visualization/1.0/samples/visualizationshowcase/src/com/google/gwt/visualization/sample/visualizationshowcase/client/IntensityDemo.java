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

import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.visualizations.IntensityMap;
import com.google.gwt.visualization.client.visualizations.IntensityMap.Options;

/**
 * Demo for IntensityMap visualization.
 */
public class IntensityDemo implements LeftTabPanel.WidgetProvider {
  private Widget widget;
  
  public IntensityDemo() {
    Options options = Options.create();

    DataTable data = DataTable.create();
    data.addColumn(ColumnType.STRING, "", "Country");
    data.addColumn(ColumnType.NUMBER, "Population (mil)", "a");
    data.addColumn(ColumnType.NUMBER, "Area (km2)", "b");
    data.addRows(5);
    data.setValue(0, 0, "CN");
    data.setValue(0, 1, 1324);
    data.setValue(0, 2, 9640821);
    data.setValue(1, 0, "IN");
    data.setValue(1, 1, 1133);
    data.setValue(1, 2, 3287263);
    data.setValue(2, 0, "US");
    data.setValue(2, 1, 304);
    data.setValue(2, 2, 9629091);
    data.setValue(3, 0, "ID");
    data.setValue(3, 1, 232);
    data.setValue(3, 2, 1904569);
    data.setValue(4, 0, "BR");
    data.setValue(4, 1, 187);
    data.setValue(4, 2, 8514877);
    
    widget = new IntensityMap(data, options);
  }

  public Widget getWidget() {
    return widget;
  }
}

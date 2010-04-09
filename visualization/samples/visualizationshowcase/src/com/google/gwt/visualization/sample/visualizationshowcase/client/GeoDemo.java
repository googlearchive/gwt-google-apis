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
import com.google.gwt.visualization.client.visualizations.GeoMap;
import com.google.gwt.visualization.client.visualizations.GeoMap.Options;

/**
 * Demo for GeoMap visualization.
 */
public class GeoDemo implements LeftTabPanel.WidgetProvider {
  public Widget getWidget() {
    final Options options = Options.create();
    options.setDataMode(GeoMap.DataMode.REGIONS);
    options.setHeight(300);
    options.setWidth(450);
    options.setShowLegend(false);
    options.setColors(0xFF8747, 0xFFB581, 0xc06000);
    options.setRegion("world");

    final DataTable dataTable = DataTable.create();      
    dataTable.addRows(7);
    dataTable.addColumn(ColumnType.STRING, "ADDRESS", "address");
    dataTable.setValue(0, 0, "Israel");
    dataTable.setValue(1, 0, "United States");
    dataTable.setValue(2, 0, "Germany");
    dataTable.setValue(3, 0, "Brazil");
    dataTable.setValue(4, 0, "Canada");
    dataTable.setValue(5, 0, "France");
    dataTable.setValue(6, 0, "RU");
    
    final GeoMap geo = new GeoMap(dataTable, options);
    return geo;
  }
}

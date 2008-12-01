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

import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.visualizations.MapVisualization;
import com.google.gwt.visualization.client.visualizations.MapVisualization.Options;

/**
 * Demo for Map visualization.
 */
public class MapDemo implements LeftTabPanel.WidgetProvider {
  private Widget widget;

  public MapDemo() {
    Options options = Options.create();
    options.setEnableScrollWheel(true);
    options.setLineColor("pink");
    options.setLineWidth(5);
    options.setMapType(MapVisualization.Type.HYBRID);
    options.setShowLine(true);
    options.setShowTip(true);

    DataTable data = DataTable.create();
    data.addColumn(ColumnType.NUMBER, "Lat");
    data.addColumn(ColumnType.NUMBER, "Lon");
    data.addColumn(ColumnType.STRING, "Name");
    data.addRows(4);
    data.setCell(0, 0, 37.4232);
    data.setCell(0, 1, -122.0853);
    data.setCell(0, 2, "Work");
    data.setCell(1, 0, 37.4289);
    data.setCell(1, 1, -122.1697);
    data.setCell(1, 2, "University");
    data.setCell(2, 0, 37.6153);
    data.setCell(2, 1, -122.3900);
    data.setCell(2, 2, "Airport");
    data.setCell(3, 0, 37.4422);
    data.setCell(3, 1, -122.1731);
    data.setCell(3, 2, "Shopping");
    
    widget = MapVisualization.createWidget(data, options, 400, 300);
  }

  public Widget getWidget() {
    return widget;
    // return new ActivityMap();
  }
}

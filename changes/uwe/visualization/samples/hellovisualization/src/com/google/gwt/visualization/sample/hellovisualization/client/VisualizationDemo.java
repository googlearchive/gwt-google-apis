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
package com.google.gwt.visualization.sample.hellovisualization.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.visualization.client.AbstractDrawOptions;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.PieChart;
import com.google.gwt.visualization.client.PieChartWidget;
import com.google.gwt.visualization.client.Query;
import com.google.gwt.visualization.client.QueryResponse;
import com.google.gwt.visualization.client.Selection;
import com.google.gwt.visualization.client.Table;
import com.google.gwt.visualization.client.TableWidget;
import com.google.gwt.visualization.client.Visualization;
import com.google.gwt.visualization.client.DataTable.ColumnType;
import com.google.gwt.visualization.client.Query.Callback;
import com.google.gwt.visualization.client.Selectable.SelectCallback;

/**
 * Google Visualization API in GWT demo.
 * 
 */
class VisualizationDemo implements EntryPoint {

  public void onModuleLoad() {
    RootPanel.get().add(new Label("Google Visualization with GWT demo."));
    drawChart();
  }

  protected void drawChart() {
    /* create a datatable */
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

    /* create pie chart */
    
    
    PieChart.DrawOptions options = PieChart.DrawOptions.create();
    options.setWidth(400);
    options.setHeight(240);
    options.set3D(true);
    options.setTitle("My Daily Activities");
    RootPanel.get().add(new PieChartWidget(data, options));

    final Label label = new Label("no selection");
    RootPanel.get().add(label);

    // Read data from spreadsheet
    String dataUrl = "http://spreadsheets.google.com/tq?key=prll1aQH05yQqp_DKPP9TNg&pub=1";
    Query query = Query.create(dataUrl);
    query.send(new Callback() {

      public void onResponse(QueryResponse response) {
        if (response.isError()) {
          Window.alert("Error in query: " + response.getMessage() + ' '
              + response.getDetailedMessage());
          return;
        }
        
        final TableWidget chart = new TableWidget();
        RootPanel.get().add(chart);
        Table.DrawOptions options = Table.DrawOptions.create();
        options.setShowRowNumber(true);
        chart.draw(response.getDataTable(), options);
        chart.addListener(new SelectCallback() {

          public void onSelect(Visualization<? extends AbstractDrawOptions> visualization) {
            StringBuffer b = new StringBuffer();
            Selection s = chart.getSelection();
            for (int i = 0; i < s.getLength(); ++i) {
              if (s.isCell(i)) {
                b.append(" cell ");
                b.append(s.getRow(i));
                b.append(":");
                b.append(s.getColumn(i));
              } else if (s.isRow(i)) {
                b.append(" row ");
                b.append(s.getRow(i));
              } else {
                b.append(" column ");
                b.append(s.getColumn(i));
              }
            }
            label.setText("selection changed " + b.toString());
          }
        });
      }
    });
  }
}

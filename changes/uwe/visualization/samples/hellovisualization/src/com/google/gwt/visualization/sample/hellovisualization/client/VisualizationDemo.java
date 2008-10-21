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
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.AbstractDrawOptions;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.DataView;
import com.google.gwt.visualization.client.PieChart;
import com.google.gwt.visualization.client.PieChartWidget;
import com.google.gwt.visualization.client.Query;
import com.google.gwt.visualization.client.QueryResponse;
import com.google.gwt.visualization.client.Selection;
import com.google.gwt.visualization.client.Table;
import com.google.gwt.visualization.client.TableWidget;
import com.google.gwt.visualization.client.Visualization;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.Query.Callback;
import com.google.gwt.visualization.client.Selectable.SelectCallback;


/**
 * Google Visualization API in GWT demo.
 * 
 */
class VisualizationDemo implements EntryPoint {
  private final TabPanel tabPanel = new TabPanel();
  
  public void onModuleLoad() {
    final VerticalPanel vp  = new VerticalPanel();
    vp.getElement().getStyle().setPropertyPx("margin", 15); 
    vp.setWidth("100%");
    vp.setHeight(Window.getClientHeight() + "px");
    RootPanel.get().add(vp);
    vp.add(new Label("Google Visualization with GWT demo."));
    vp.add(tabPanel);
    tabPanel.setWidth("800");
    tabPanel.setHeight("600");
    tabPanel.add(createPieChart(), "Pie Chart");
    tabPanel.add(createTable(), "Table");
    tabPanel.add(createDataView(), "DataView");
    tabPanel.selectTab(0);
  }

  /**
   * Creates a table and a view and shows both next to each other.
   * @return a panel with two tables.
   */
  private Widget createDataView() {
    Panel panel = new HorizontalPanel();
    DataTable table = DataTable.create();
    
    /* create a table with 3 columns */
    table.addColumn(ColumnType.NUMBER, "x");
    table.addColumn(ColumnType.NUMBER, "x * x");
    table.addColumn(ColumnType.NUMBER, "sqrt(x)");
    table.addRows(10);
    for (int i = 0; i < table.getNumberOfRows(); i++) {
      table.setValue(i, 0, i);
      table.setValue(i, 1, i * i);
      table.setValue(i, 2, Math.sqrt(i));
    }
    /* Add original table */
    Panel flowPanel = new FlowPanel();
    panel.add(flowPanel);
    flowPanel.add(new Label("Original DataTable:"));
    TableWidget chart = new TableWidget();
    flowPanel.add(chart);
    chart.draw(table);
    
    flowPanel = new FlowPanel();
    flowPanel.add(new Label("DataView with columns 2 and 1:"));
    /* create a view on this table, with columns 2 and 1 */
    TableWidget viewChart = new TableWidget();
    DataView view = DataView.create(table);
    view.setColumns(new int[] {2, 1});
    flowPanel.add(viewChart);
    panel.add(flowPanel);
    viewChart.draw(view);
    
    return panel;
  }

  /**
   * Creates a pie chart visualization.
   * 
   * @return panel with pie chart.
   */
  private Widget createPieChart() {
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
    return new PieChartWidget(data, options);
  }

  /**
   * Creates a table visualization from a spreadsheet.
   * 
   * @return panel with a table.
   */
  private Widget createTable() {
    final Panel panel = new FlowPanel();
    final Label label = new Label("no selection");
    panel.add(new Label("Table visualization with selection."));
    panel.add(label);
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
        panel.add(chart);
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
    return panel;
  }
}

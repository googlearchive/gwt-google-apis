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
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.events.SelectHandler;
import com.google.gwt.visualization.client.visualizations.OrgChart;
import com.google.gwt.visualization.client.visualizations.OrgChart.Options;
import com.google.gwt.visualization.client.visualizations.OrgChart.Size;

/**
 * Tests for the OrgChart class.
 */
public class OrgChartTest extends VisualizationTest {
  @Override
  public String getModuleName() {
    return "com.google.gwt.visualization.VisualizationTest";
  }

  public void testOrgChart() {
    loadApi(new Runnable() {
      public void run() {
        Widget widget;
        Options options = Options.create();
        options.setAllowHtml(true);
        widget = new OrgChart(createDataTable(), options);
        RootPanel.get().add(widget);
        // System.out.println(widget.getElement().getString());
      }
    });
  }

  public void testSelection() {
    AjaxLoader.loadVisualizationApi(new Runnable() {
      public void run() {
        // Create a minimal pie chart.
        OrgChart.Options options = OrgChart.Options.create();
        final OrgChart viz = new OrgChart(createDataTable(), options);
  
        // Add a selection handler
        viz.addSelectHandler(new SelectHandler() {
  
          @Override
          public void onSelect(SelectEvent event) {
            assertNotNull(event);
            Selection s = viz.getSelection();
            assertEquals("Expected 1 element in the selection", 1,
                s.getLength());
            assertEquals("Expected row 1 to be selected", 1, s.getRow(0));
            assertEquals("Expected column 0 to be selected", 0, s.getColumn(0));
            finishTest();
          }
        });
        RootPanel.get().add(viz);
  
        Selection s = SelectionHelper.createSelection(1, 0);
        assertEquals("Expected 1 element in the selection", 1, s.getLength());
        assertEquals("Expected row 1 to be selected", 1, s.getRow(0));
        assertEquals("Expected column 0 to be selected", 0, s.getColumn(0));
        viz.setSelection(s);
        s = viz.getSelection();
        assertEquals("Expected 1 element in the selection", 1, s.getLength());
        assertEquals("Expected row 1 to be selected", 1, s.getRow(0));
        assertEquals("Expected column 0 to be selected", 0, s.getColumn(0));
        // Trigger a selection callback
        triggerSelection(viz, s);
      }
    }, OrgChart.PACKAGE);
    delayTestFinish(ASYNC_DELAY_MS);
  }

  public void testSize() {
    loadApi(new Runnable() {
      public void run() {
        Widget widget;
        Options options;
        
        options = Options.create();
        options.setSize(Size.SMALL);
        widget = new OrgChart(createDataTable(), options);
        RootPanel.get().add(widget);
        // System.out.println(widget.getElement().getString());
        
        options = Options.create();
        options.setSize(Size.MEDIUM);
        widget = new OrgChart(createDataTable(), options);
        RootPanel.get().add(widget);
        // System.out.println(widget.getElement().getString());
        
        options = Options.create();
        options.setSize(Size.LARGE);
        widget = new OrgChart(createDataTable(), options);
        RootPanel.get().add(widget);
        // System.out.println(widget.getElement().getString());
      }
    });
  }

  @Override
  protected String getVisualizationPackage() {
    return OrgChart.PACKAGE;
  }

  private AbstractDataTable createDataTable() {
    DataTable data = DataTable.create();
    data.addColumn(ColumnType.STRING, "Name");
    data.addColumn(ColumnType.STRING, "Manager");
    data.addRows(5);
    data.setValue(0, 0, "Mike");
    data.setValue(1, 0, "Jim");
    data.setValue(1, 1, "Mike");
    data.setValue(2, 0, "Alice");
    data.setValue(2, 1, "Mike");
    data.setValue(3, 0, "Bob");
    data.setValue(3, 1, "Jim");
    data.setValue(4, 0, "Carol");
    data.setValue(4, 1, "Bob");
    return data;
  }
}

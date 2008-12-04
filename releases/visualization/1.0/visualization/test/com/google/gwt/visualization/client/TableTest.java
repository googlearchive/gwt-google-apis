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
import com.google.gwt.visualization.client.events.SelectHandler;
import com.google.gwt.visualization.client.visualizations.Table;
import com.google.gwt.visualization.client.visualizations.Table.Options;
import com.google.gwt.visualization.client.visualizations.Table.Options.Policy;

/**
 * Tests for the Table class.
 */
public class TableTest extends VisualizationTest {
  @Override
  public String getModuleName() {
    return "com.google.gwt.visualization.VisualizationTest";
  }

  public void testPagePolicy() {
    loadApi(new Runnable() {
      public void run() {
        Widget widget;
        Options options;
        
        options = Options.create();
        options.setPage(Policy.DISABLE);
        widget = Table.createWidget(createCompanyPerformance(), options);
        RootPanel.get().add(widget);
        // System.out.println(widget.getElement().getString());
        
        options = Options.create();
        options.setPage(Policy.ENABLE);
        widget = Table.createWidget(createCompanyPerformance(), options);
        RootPanel.get().add(widget);
        // System.out.println(widget.getElement().getString());
        
        options = Options.create();
        options.setPage(Policy.EVENT);
        widget = Table.createWidget(createCompanyPerformance(), options);
        RootPanel.get().add(widget);
        // System.out.println(widget.getElement().getString());
      }
    });
  }

  public void testSelection() {
    AjaxLoader.loadVisualizationApi(new Runnable() {
      public void run() {
        DataTable data = createDailyActivities();
  
        // Create a minimal pie chart.
        Table.Options options = Table.Options.create();
        VisualizationWidget<Table, Table.Options> widget = Table.createWidget(
            data, options);
        final Table viz = widget.getVisualization();
  
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
        RootPanel.get().add(widget);
  
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
    }, Table.PACKAGE);
    delayTestFinish(ASYNC_DELAY_MS);
  }

  public void testSortPolicy() {
    loadApi(new Runnable() {
      public void run() {
        Widget widget;
        Options options;
        
        options = Options.create();
        options.setSort(Policy.DISABLE);
        widget = Table.createWidget(createCompanyPerformance(), options);
        RootPanel.get().add(widget);
        // System.out.println(widget.getElement().getString());
        
        options = Options.create();
        options.setSort(Policy.ENABLE);
        widget = Table.createWidget(createCompanyPerformance(), options);
        RootPanel.get().add(widget);
        // System.out.println(widget.getElement().getString());
        
        options = Options.create();
        options.setSort(Policy.EVENT);
        widget = Table.createWidget(createCompanyPerformance(), options);
        RootPanel.get().add(widget);
        // System.out.println(widget.getElement().getString());
      }
    });
  }

  public void testTable() {
    loadApi(new Runnable() {
      public void run() {
        Widget widget;
        Options options = Options.create();
        options.setAllowHtml(true);
        options.setPageSize(3);
        options.setShowRowNumber(true);
        widget = Table.createWidget(createCompanyPerformance(), options);
        RootPanel.get().add(widget);
        // System.out.println(widget.getElement().getString());
      }
    });
  }

  @Override
  protected String getVisualizationPackage() {
    return Table.PACKAGE;
  }
}

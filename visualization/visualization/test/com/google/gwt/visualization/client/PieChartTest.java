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
import com.google.gwt.visualization.client.events.SelectHandler;
import com.google.gwt.visualization.client.visualizations.PieChart;

/**
 * Tests for the PieChart class.
 */
public class PieChartTest extends VisualizationTest {
  // TODO: research the failure and reenable. this is apparently a gviz
  // bug.
  public void dontTestPieChartSelection() {
    AjaxLoader.loadVisualizationApi(new Runnable() {
      public void run() {
        DataTable data = createDailyActivities();

        // Create a minimal pie chart.
        PieChart.Options options = PieChart.Options.create();
        options.setWidth(400);
        options.setHeight(240);
        VisualizationWidget<PieChart, PieChart.Options> widget = PieChart.createWidget(
            data, options);
        final PieChart viz = widget.getVisualization();

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
    }, PieChart.PACKAGE);
    delayTestFinish(ASYNC_DELAY_MS);
  }

  /**
   * This test case will try creating a simple Pie chart. It first asserts that
   * the Visualization API has been correctly loaded (see ajax_loader.html).
   */
  public void testASimplePieChart() {
    loadApi(new Runnable() {
      public void run() {
        DataTable data = createDailyActivities();

        // Create a minimal pie chart.
        PieChart.Options options = PieChart.Options.create();
        options.setWidth(400);
        options.setHeight(240);
        RootPanel.get().add(PieChart.createWidget(data, options));
      }
    });
  }

  /**
   * Tests the options that are peculiar to the PieChart.Options class.
   */
  public void testPieChartOptions() {
    PieChart.Options options = PieChart.Options.create();
    options.set3D(true);
  }

  /**
   * Tests setting a selection object.
   */
  public void testPieChartSetSelection() {
    loadApi(new Runnable() {
      public void run() {
        DataTable data = createDailyActivities();

        // Create a minimal pie chart.
        PieChart.Options options = PieChart.Options.create();
        options.setWidth(400);
        options.setHeight(240);
        VisualizationWidget<PieChart, PieChart.Options> widget = PieChart.createWidget(
            data, options);

        PieChart viz = widget.getVisualization();
        Selection s = SelectionHelper.createSelection(1, 0);

        RootPanel.get().add(widget);

        // Note: viz.setSelection() will fail if the visualization has not been
        // added to the DOM.
        viz.setSelection(s);
      }
    });
  }

  @Override
  protected String getVisualizationPackage() {
    return PieChart.PACKAGE;
  }
}

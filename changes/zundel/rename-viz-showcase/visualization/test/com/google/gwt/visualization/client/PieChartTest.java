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

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.events.SelectHandler;
import com.google.gwt.visualization.client.visualizations.PieChart;

/**
 * Tests for the PieChart class.
 */
public class PieChartTest extends GWTTestCase {
  public static final int ASYNC_DELAY_MS = 5000;

  @Override
  public String getModuleName() {
    return "com.google.gwt.visualization.VisualizationTest";
  }

  /**
   * This test case will try creating a simple Pie chart. It first asserts that
   * the Visualization API has been correctly loaded (see ajax_loader.html).
   */
  public void testASimplePieChart() {
    assertTrue("ajax_loader.html wasn't called", nativeIsJunitHtmlCalled());
    assertTrue("AJAX API hasn't completed loading",
        nativeIsVisualizationAPILoaded());

    DataTable data = makeDataTable();

    // Create a minimal pie chart.
    PieChart.Options options = PieChart.Options.create();
    options.setWidth(400);
    options.setHeight(240);
    RootPanel.get().add(PieChart.createWidget(data, options));
  }

  /**
   * Tests the options that are peculiar to the PieChart.Options class.
   */
  public void testPieChartOptions() {
    PieChart.Options options = PieChart.Options.create();
    options.set3D(true);
  }

  public void testPieChartSelection() {
    DataTable data = makeDataTable();

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
        assertEquals("Expected row 1 to be selected", 1, s.getRow(0));
        assertEquals("Expected column 0 to be selected", 0, s.getColumn(0));
        assertEquals("Expected 1 element in the selection", 1, s.getLength());
        finishTest();
      }
    });
    RootPanel.get().add(widget);

    Selection s = SelectionHelper.createSelection(1, 0);
    viz.setSelection(s);
    // Trigger a selection callback
    triggerSelection(viz, s);
  }

  /**
   * Tests setting a selection object.
   */
  public void testPieChartSetSelection() {
    DataTable data = makeDataTable();

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

  /**
   * A support method used by several test cases to create a simple DataTable.
   */
  private DataTable makeDataTable() {
    // Create a simple data table
    DataTable data = DataTable.create();
    data.addColumn(ColumnType.STRING, "Fruit");
    data.addColumn(ColumnType.NUMBER, "Baskets");
    data.addRows(3);
    data.setValue(0, 0, "Apple");
    data.setValue(0, 1, 11);
    data.setValue(1, 0, "Banana");
    data.setValue(1, 1, 3);
    data.setValue(2, 0, "Cherry");
    data.setValue(2, 1, 7);

    return data;
  }

  private native boolean nativeIsJunitHtmlCalled() /*-{
    return $wnd.ajax_loader_called ? true: false;
  }-*/;

  private native boolean nativeIsVisualizationAPILoaded() /*-{
    return $wnd.visualization_api_loaded ? true: false;
  }-*/;

  /**
   * See <a href=
   * "http://code.google.com/apis/visualization/documentation/dev/events.html" >
   * Google Visualization API documentation</a> .
   * 
   * @param viz - the Visualization to trigger the event on
   * @param selection - a selection object.
   */
  private native void triggerSelection(PieChart viz,
      Selection s) /*-{
    $wnd.google.visualization.events.trigger(viz, 'select', s);
  }-*/;
}

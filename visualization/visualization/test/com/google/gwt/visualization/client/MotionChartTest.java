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

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptException;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.events.ReadyHandler;
import com.google.gwt.visualization.client.events.StateChangeHandler;
import com.google.gwt.visualization.client.visualizations.MotionChart;

import java.util.Date;

/**
 * Tests for the MotionChart class.
 *
 * Note that only one instance of MotionChart is allowed at a time, which kind
 * of restricts this unit test.
 */
public class MotionChartTest extends VisualizationTest {
  public static final int MOTION_CHART_ASYNC_DELAY_MS = 40 * 1000;

  /**
   * HACK: Part of workaround to avoid running
   * {@link MotionChartTest#testMotionChartReady} and
   * {@link MotionChartTest#testMotionChartStateChanged()} on FF, which fails if
   * screen of the tester machine is locked.
   */
  private static native boolean isFF() /*-{
    var ua = navigator.userAgent.toLowerCase();
    return ua.indexOf("gecko") != -1;
  }-*/;

  /**
   * This test case will try creating a simple MotionChart. It first asserts
   * that the Visualization API has been correctly loaded (see
   * ajax_loader.html).
   */
  public void testASimpleMotionChart() {
    loadApi(new Runnable() {
      public void run() {
        DataTable data = createDataTable();
        MotionChart.Options options = MotionChart.Options.create();
        options.setSize(600, 200);
        RootPanel.get().add(new MotionChart(data, options));
      }
    }, true, MOTION_CHART_ASYNC_DELAY_MS);
  }

  public void testMotionChartOptions() {
    loadApi(new Runnable() {
      public void run() {
        DataTable data = createDataTable();
        MotionChart.Options options = MotionChart.Options.create();
        options.setSize(600, 200);
        options.setShowAdvancedPanel(true);
        options.setShowChartButtons(true);
        options.setShowHeader(true);
        options.setShowSelectListComponent(true);
        options.setShowSidePanel(true);
        options.setShowXMetricPicker(true);
        options.setShowYMetricPicker(true);
        options.setShowXScalePicker(true);
        options.setShowYScalePicker(true);
        RootPanel.get().add(new MotionChart(data, options));
      }
    }, true, MOTION_CHART_ASYNC_DELAY_MS);
  }

  public void testMotionChartReady() {
    // see javadoc for isFF()
    if (!isFF()) {
      loadApi(new Runnable() {
        public void run() {
          DataTable data = createDataTable();
          MotionChart.Options options = MotionChart.Options.create();
          options.setSize(600, 200);
          MotionChart widget = new MotionChart(data, options);
          widget.addReadyHandler(new ReadyHandler() {
            @Override
            public void onReady(ReadyEvent event) {
              finishTest();
            }
          });
          RootPanel.get().add(widget);
        }
      }, false, MOTION_CHART_ASYNC_DELAY_MS);
    }
  }

  /**
   * This test is meant to test the statechanged event. Unfortunately, we don't
   * have a way to trigger user interaction, so this test just goes through the
   * motions of setting up the event.
   */
  public void testMotionChartStateChanged() {
    // see javadoc for isFF()
    if (!isFF()) {
      loadApi(new Runnable() {
        public void run() {
          DataTable data = createDataTable();
          MotionChart.Options options = MotionChart.Options.create();
          options.setSize(600, 200);
          final MotionChart widget = new MotionChart(data, options);
          widget.addStateChangeHandler(new StateChangeHandler() {
            @Override
            public void onStateChange(StateChangeEvent event) {
              // Unfortunately, when the state change is triggered manually in
              // this way, there is no getState() method present on the
              // MotionChart object, so we can't use an automatic test. See
              // MotionDemo.java
              finishTest();
            }
          });
          RootPanel.get().add(widget);
          triggerStateChanged(widget.getJso());
        }
      }, false, MOTION_CHART_ASYNC_DELAY_MS);
    }
  }

  @Override
  protected String getVisualizationPackage() {
    return MotionChart.PACKAGE;
  }

  @Override
  protected void gwtSetUp() throws Exception {
    cleanDom();
  }

  /**
   * A support method used by several test cases to create a simple DataTable.
   */
  private DataTable createDataTable() {
    // Create a simple data table
    DataTable data = DataTable.create();
    data.addColumn(ColumnType.STRING, "Fruit");
    data.addColumn(ColumnType.DATE, "Date");
    data.addColumn(ColumnType.NUMBER, "Baskets");
    data.addColumn(ColumnType.NUMBER, "Heat Index");
    data.addRows(3);
    data.setValue(0, 0, "Banana");
    data.setValue(0, 2, 12);
    data.setValue(0, 3, 3);
    data.setValue(1, 0, "Jalape�o");
    data.setValue(1, 2, 33);
    data.setValue(1, 3, 130);
    data.setValue(2, 0, "Haba�ero");
    data.setValue(2, 2, 99);
    data.setValue(2, 3, 1000);

    @SuppressWarnings("unused")
    int year, month, day;

    try {
      data.setValue(0, 1, new Date(year = 1988 - 1900, month = 0, day = 1));
      data.setValue(1, 1, new Date(year = 1988 - 1900, month = 0, day = 1));
      data.setValue(2, 1, new Date(year = 1988 - 1900, month = 0, day = 1));
    } catch (JavaScriptException ex) {
      GWT.log("Error creating data table - Date bug on mac?", ex);
    }
    return data;
  }

  private native void triggerStateChanged(JavaScriptObject jso) /*-{
    $wnd.google.visualization.events.trigger(jso, 'statechanged',
    {});
  }-*/;
}

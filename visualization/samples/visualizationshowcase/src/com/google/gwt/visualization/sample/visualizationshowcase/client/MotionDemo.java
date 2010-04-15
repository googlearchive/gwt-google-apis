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

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptException;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.events.StateChangeHandler;
import com.google.gwt.visualization.client.visualizations.MotionChart;
import com.google.gwt.visualization.client.visualizations.MotionChart.Options;

import java.util.Date;

/**
 * Demo for MotionChart visualization.
 */
public class MotionDemo implements LeftTabPanel.WidgetProvider {
  private static final String STATE_STRING = "{"
      + "\"duration\":{\"timeUnit\":\"D\",\"multiplier\":1},\"nonSelectedAlpha\":0.4,"
      + "\"yZoomedDataMin\":300,\"yZoomedDataMax\":1200,\"iconKeySettings\":[],\"yZoomedIn\":false,"
      + "\"xZoomedDataMin\":300,\"xLambda\":1,\"time\":\"1988-01-06\",\"orderedByX\":false,\"xZoomedIn\":false,"
      + "\"uniColorForNonSelected\":false,\"sizeOption\":\"_UNISIZE\",\"iconType\":\"BUBBLE\","
      + "\"playDuration\":15000,\"dimensions\":{\"iconDimensions\":[\"dim0\"]},\"xZoomedDataMax\":1200,"
      + "\"yLambda\":1,\"yAxisOption\":\"2\",\"colorOption\":\"4\",\"showTrails\":true,\"xAxisOption\":\"2\","
      + "\"orderedByY\":false}";
  private Widget widget;

  @SuppressWarnings("deprecation")
  public MotionDemo() {

    String protocol = Window.Location.getProtocol();
    if (protocol.startsWith("file")) {
      widget = new HTML(
          "<font color=\"blue\"><i>Note: Protocol is: "
              + protocol
              + ".  Note that this visualization does not work when loading the HTML from "
              + "a local file. It works only when loading the HTML from a "
              + "web server. </i></font>");
      return;
    }

    @SuppressWarnings("unused")
    int year, month, day;

    Options options = Options.create();
    options.setHeight(300);
    options.setWidth(600);
    options.setState(STATE_STRING);
    DataTable data = DataTable.create();
    data.addRows(6);
    data.addColumn(ColumnType.STRING, "Fruit");
    data.addColumn(ColumnType.DATE, "Date");
    data.addColumn(ColumnType.NUMBER, "Sales");
    data.addColumn(ColumnType.NUMBER, "Expenses");
    data.addColumn(ColumnType.STRING, "Location");
    data.setValue(0, 0, "Apples");
    data.setValue(0, 2, 1000);
    data.setValue(0, 3, 300);
    data.setValue(0, 4, "East");
    data.setValue(1, 0, "Oranges");
    data.setValue(1, 2, 950);
    data.setValue(1, 3, 200);
    data.setValue(1, 4, "West");
    data.setValue(2, 0, "Bananas");
    data.setValue(2, 2, 300);
    data.setValue(2, 3, 250);
    data.setValue(2, 4, "West");
    data.setValue(3, 0, "Apples");
    data.setValue(3, 2, 1200);
    data.setValue(3, 3, 400);
    data.setValue(3, 4, "East");
    data.setValue(4, 0, "Oranges");
    data.setValue(4, 2, 900);
    data.setValue(4, 3, 150);
    data.setValue(4, 4, "West");
    data.setValue(5, 0, "Bananas");
    data.setValue(5, 2, 788);
    data.setValue(5, 3, 617);
    data.setValue(5, 4, "West");

    try {
      data.setValue(0, 1, new Date(year = 1988 - 1900, month = 0, day = 1));
      data.setValue(1, 1, new Date(year = 1988 - 1900, month = 0, day = 1));
      data.setValue(2, 1, new Date(year = 1988 - 1900, month = 0, day = 1));
      data.setValue(3, 1, new Date(year = 1988 - 1900, month = 1, day = 1));
      data.setValue(4, 1, new Date(year = 1988 - 1900, month = 1, day = 1));
      data.setValue(5, 1, new Date(year = 1988 - 1900, month = 1, day = 1));
    } catch (JavaScriptException ex) {
      GWT.log("Error creating data table - Date bug on mac?", ex);
    }

    final MotionChart motionChart = new MotionChart(data, options);
    motionChart.addStateChangeHandler(new StateChangeHandler() {

      @Override
      public void onStateChange(StateChangeEvent event) {
        String result = motionChart.getState();
        GWT.log(result);
      }
    });

    widget = motionChart;

  }

  public Widget getWidget() {
    return widget;
  }
}

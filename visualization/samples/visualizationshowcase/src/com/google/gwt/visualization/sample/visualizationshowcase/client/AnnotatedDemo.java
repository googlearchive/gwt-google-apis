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
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.events.RangeChangeHandler;
import com.google.gwt.visualization.client.visualizations.AnnotatedTimeLine;
import com.google.gwt.visualization.client.visualizations.AnnotatedTimeLine.Options;

import java.util.Date;

/**
 * Demo for AnnotatedTimeLine visualization.
 */
public class AnnotatedDemo implements LeftTabPanel.WidgetProvider {
  private AnnotatedTimeLine chart;
  private final Label status = new Label();
  private final Label rangeStatus = new Label();
  private VerticalPanel widget = new VerticalPanel();

  @SuppressWarnings("deprecation")
  public AnnotatedDemo() {
    @SuppressWarnings("unused")
    int year, month, day;
    Options options = Options.create();
    options.setDisplayAnnotations(true);

    DataTable data = DataTable.create();
    data.addColumn(ColumnType.DATE, "Date");
    data.addColumn(ColumnType.NUMBER, "Sold Pencils");
    data.addColumn(ColumnType.STRING, "title1");
    data.addColumn(ColumnType.STRING, "text1");
    data.addColumn(ColumnType.NUMBER, "Sold Pens");
    data.addColumn(ColumnType.STRING, "title2");
    data.addColumn(ColumnType.STRING, "text2");
    data.addRows(6);
    try {
      data.setValue(0, 0, new Date(year = 2008 - 1900, month = 1, day = 1));
      data.setValue(1, 0, new Date(year = 2008 - 1900, month = 1, day = 2));
      data.setValue(2, 0, new Date(year = 2008 - 1900, month = 1, day = 3));
      data.setValue(3, 0, new Date(year = 2008 - 1900, month = 1, day = 4));
      data.setValue(4, 0, new Date(year = 2008 - 1900, month = 1, day = 5));
      data.setValue(5, 0, new Date(year = 2008 - 1900, month = 1, day = 6));
    } catch (JavaScriptException ex) {
      GWT.log("Error creating data table - Date bug on mac?", ex);
    }
    data.setValue(0, 1, 30000);
    data.setValue(0, 4, 40645);
    data.setValue(1, 1, 14045);
    data.setValue(1, 4, 20374);
    data.setValue(2, 1, 55022);
    data.setValue(2, 4, 50766);
    data.setValue(3, 1, 75284);
    data.setValue(3, 4, 14334);
    data.setValue(3, 5, "Out of Stock");
    data.setValue(3, 6, "Ran out of stock on pens at 4pm");
    data.setValue(4, 1, 41476);
    data.setValue(4, 2, "Bought 200k pens");
    data.setValue(4, 4, 66467);
    data.setValue(5, 1, 33322);
    data.setValue(5, 4, 39463);

    status.setText("'Ready' event was not yet fired");
    chart = new AnnotatedTimeLine(data, options, "700px", "240px");

    widget.add(chart);
    widget.add(status);
    widget.add(rangeStatus);

    addHandlers();
  }

  private void addHandlers() {
    chart.addRangeChangeHandler(new RangeChangeHandler() {
      @Override
      public void onRangeChange(RangeChangeEvent event) {
        rangeStatus.setText("The range has changed.\n" + event.getStart()
            + "\nFalls mainly on the plains.\n" + event.getEnd());
      }
    });
    chart.addReadyHandler(new ReadyDemo(status));
  }

  public Widget getWidget() {
    return widget;
  }
}

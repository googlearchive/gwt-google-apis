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
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.visualizations.OrgChart;
import com.google.gwt.visualization.client.visualizations.OrgChart.Options;
import com.google.gwt.visualization.client.visualizations.OrgChart.Size;

/**
 * Demo for OrgChart visualization.
 */
public class OrgDemo implements LeftTabPanel.WidgetProvider {
  private VerticalPanel panel = new VerticalPanel();

  public static interface Resources extends ClientBundle {
    @Source("OrgDemo.css")
    Css orgDemoCss();
  }

  public static interface Css extends CssResource {
    String nodeClass();
    String selectedNodeClass();
  }

  public OrgDemo() {
    Resources bundle = GWT.create(Resources.class);
    Css css = bundle.orgDemoCss();
    css.ensureInjected();

    Options options = Options.create();
    options.setSize(Size.LARGE);
    options.setAllowCollapse(true);
    options.setNodeClass(css.nodeClass());
    options.setSelectedNodeClass(css.selectedNodeClass());

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

    OrgChart viz = new OrgChart(data, options);
    Label status = new Label();
    viz.addSelectHandler(new SelectionDemo(viz, status));
    panel.add(viz);
    panel.add(status);
  }

  public Widget getWidget() {
    return panel;
  }
}

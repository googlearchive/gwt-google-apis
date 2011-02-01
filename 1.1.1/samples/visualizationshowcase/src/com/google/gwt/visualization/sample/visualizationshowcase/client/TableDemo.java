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

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.Query;
import com.google.gwt.visualization.client.QueryResponse;
import com.google.gwt.visualization.client.Query.Callback;
import com.google.gwt.visualization.client.visualizations.Table;

/**
 * Demo for Table visualization.
 */
public class TableDemo implements LeftTabPanel.WidgetProvider {
  private Panel panel = new VerticalPanel();

  public TableDemo() {
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

        Table viz = new Table();
        Table.Options options = Table.Options.create();
        options.setShowRowNumber(true);
        viz.draw(response.getDataTable(), options);
        Label status = new Label();
        viz.addSelectHandler(new SelectionDemo(viz, status));
        panel.add(viz);
        panel.add(status);
      }
    });
  }

  public Widget getWidget() {
    return panel;
  }
}

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
package com.google.gwt.visualization.sample.customvisualization.client;

import com.google.gwt.ajaxloader.client.ArrayHelper;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable.Cell;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.AbstractDrawOptions;
import com.google.gwt.visualization.client.AbstractVisualization;
import com.google.gwt.visualization.client.Selectable;
import com.google.gwt.visualization.client.Selection;
import com.google.gwt.visualization.client.events.SelectHandler;
import com.google.gwt.visualization.client.events.SelectHandler.SelectEvent;

/**
 * A Google Visualization written in GWT.
 *
 */
class CustomVisualization extends
    AbstractVisualization<CustomVisualization.CustomVisualizationDrawOptions>
    implements Selectable {

  /**
   * Drawing options supported by this visualization.
   */
  public static class CustomVisualizationDrawOptions extends
      AbstractDrawOptions {
    protected CustomVisualizationDrawOptions() {
    }

    public final native String getBackgroundColor() /*-{
      return this.backgroundColor || "white";
    }-*/;

    public final native void setBackgroundColor(String color) /*-{
      this.backgroundColor = color;
    }-*/;
  }

  private final FlexTable grid = new FlexTable();
  Cell cell;

  public CustomVisualization() {
    initWidget(grid);
  }

  public void addSelectHandler(final SelectHandler handler) {
    grid.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        cell = grid.getCellForEvent(event);
        handler.onSelect(new SelectEvent());
      }
    });
  }

  @Override
  public void draw(AbstractDataTable dataTable,
      CustomVisualizationDrawOptions options) {
    grid.clear();
    // Apply the drawing options
    if (options != null) {
      grid.getElement().getStyle().setProperty("backgroundColor",
          options.getBackgroundColor());
    }

    for (int c = 0; c < dataTable.getNumberOfColumns(); c++) {
      grid.setHTML(0, c, "<b>" + dataTable.getColumnLabel(c) + "</b>");
    }

    for (int r = 0; r < dataTable.getNumberOfRows(); r++) {
      for (int c = 0; c < dataTable.getNumberOfColumns(); c++) {
        grid.setText(r + 1, c, dataTable.getFormattedValue(r, c));
      }
    }
    // handle selection
    grid.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        cell = grid.getCellForEvent(event);
        CustomVisualization.this.fireSelectionEvent();
      }
    });
  }

  public JsArray<Selection> getSelections() {
    if (cell == null) {
      return ArrayHelper.toJsArray(Selection.createCellSelection(0, 0));
    } else {
      // -1 because of the title row
      return ArrayHelper.toJsArray(Selection.createCellSelection(
          cell.getRowIndex() - 1, cell.getCellIndex()));
    }
  }

  public void setSelections(JsArray<Selection> sel) {
    Window.alert("selection changed");
  }
}

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

import com.google.gwt.ajaxloader.client.ArrayHelper;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.events.SelectHandler;
import com.google.gwt.visualization.client.visualizations.Table;
import com.google.gwt.visualization.client.visualizations.Table.Options;
import com.google.gwt.visualization.client.visualizations.Table.Options.CssClassNames;
import com.google.gwt.visualization.client.visualizations.Table.Options.Policy;

/**
 * Tests for the Table class.
 */
public class TableTest extends VisualizationTest {
  @Override
  public String getModuleName() {
    return "com.google.gwt.visualization.VisualizationTest";
  }

  public void testASimpleTable() {
    loadApi(new Runnable() {
      public void run() {
        Widget widget;
        Options options = Options.create();
        widget = new Table(createCompanyPerformance(), options);
        RootPanel.get().add(widget);
        // System.out.println(widget.getElement().getString());
      }
    });
  }

  public void testPagePolicy() {
    loadApi(new Runnable() {
      public void run() {
        Widget widget;
        Options options;

        options = Options.create();
        options.setPage(Policy.DISABLE);
        widget = new Table(createCompanyPerformance(), options);
        RootPanel.get().add(widget);
        // System.out.println(widget.getElement().getString());

        options = Options.create();
        options.setPage(Policy.ENABLE);
        widget = new Table(createCompanyPerformance(), options);
        RootPanel.get().add(widget);
        // System.out.println(widget.getElement().getString());

        options = Options.create();
        options.setPage(Policy.EVENT);
        widget = new Table(createCompanyPerformance(), options);
        RootPanel.get().add(widget);
        // System.out.println(widget.getElement().getString());
      }
    });
  }

  public void testSelection() {
    loadApi(new Runnable() {
      public void run() {
        Table.Options options = Table.Options.create();
        final Table viz = new Table(createCompanyPerformance(), options);

        // Add a selection handler
        viz.addSelectHandler(new SelectHandler() {

          @Override
          public void onSelect(SelectEvent event) {
            assertNotNull(event);
            JsArray<Selection> s = viz.getSelections();
            assertEquals("Expected 1 element in the selection", 1, s.length());
            assertEquals("Expected row 1 to be selected", 1, s.get(0).getRow());
            assertEquals("Expected column 0 to be selected", 0,
                s.get(0).getColumn());
            finishTest();
          }
        });
        RootPanel.get().add(viz);

        JsArray<Selection> s = ArrayHelper.toJsArray(Selection.createCellSelection(
            1, 0));
        assertEquals("Expected 1 element in the selection", 1, s.length());
        assertEquals("Expected row 1 to be selected", 1, s.get(0).getRow());
        assertEquals("Expected column 0 to be selected", 0,
            s.get(0).getColumn());
        viz.setSelections(s);
        s = viz.getSelections();
        assertEquals("Expected 1 element in the selection", 1, s.length());
        assertEquals("Expected row 1 to be selected", 1, s.get(0).getRow());
        assertEquals("Expected column 0 to be selected", 0,
            s.get(0).getColumn());
        // Trigger a selection callback
        triggerSelection(viz, s);
      }
    }, false);
  }

  public void testSortPolicy() {
    loadApi(new Runnable() {
      public void run() {
        Widget widget;
        Options options;

        options = Options.create();
        options.setSort(Policy.DISABLE);
        widget = new Table(createCompanyPerformance(), options);
        RootPanel.get().add(widget);
        // System.out.println(widget.getElement().getString());

        options = Options.create();
        options.setSort(Policy.ENABLE);
        widget = new Table(createCompanyPerformance(), options);
        RootPanel.get().add(widget);
        // System.out.println(widget.getElement().getString());

        options = Options.create();
        options.setSort(Policy.EVENT);
        widget = new Table(createCompanyPerformance(), options);
        RootPanel.get().add(widget);
        // System.out.println(widget.getElement().getString());
      }
    });
  }

  public void testTableOptions() {
    loadApi(new Runnable() {
      public void run() {
        Widget widget;
        Options options = Options.create();
        options.setAllowHtml(true);
        options.setPageSize(3);
        options.setShowRowNumber(true);
        CssClassNames classNames = CssClassNames.createObject().cast();
        classNames.setHeaderCell("class1");
        classNames.setTableRow("class2");
        classNames.setOddTableRow("class3");
        classNames.setSelectedTableRow("class4");
        classNames.setHoverTableRow("class5");
        classNames.setHeaderCell("class5");
        classNames.setTableCell("class6");
        classNames.setRowNumberCell("class7");
        options.setCssClassNames(classNames);
        options.setScrollLeftStartPosition(10);
        options.setAlternatingRowStyle(true);
        options.setFirstRowNumber(11);
        options.setHeight("400px");
        options.setRtlTable(true);
        options.setSortAscending(false);
        options.setSortColumn(2);
        options.setStartPage(3);
        options.setWidth("400px");
        widget = new Table(createCompanyPerformance(), options);
        RootPanel.get().add(widget);
      }
    });
  }

  @Override
  protected String getVisualizationPackage() {
    return Table.PACKAGE;
  }
}

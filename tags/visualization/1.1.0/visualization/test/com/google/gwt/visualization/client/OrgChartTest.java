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
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.events.CollapseHandler;
import com.google.gwt.visualization.client.events.OnMouseOutHandler;
import com.google.gwt.visualization.client.events.OnMouseOverHandler;
import com.google.gwt.visualization.client.events.ReadyHandler;
import com.google.gwt.visualization.client.events.SelectHandler;
import com.google.gwt.visualization.client.visualizations.OrgChart;
import com.google.gwt.visualization.client.visualizations.OrgChart.Options;
import com.google.gwt.visualization.client.visualizations.OrgChart.Size;

/**
 * Tests for the OrgChart class.
 */
public class OrgChartTest extends VisualizationTest {
  @Override
  public String getModuleName() {
    return "com.google.gwt.visualization.VisualizationTest";
  }

  public void testCollapse() {
    loadApi(new Runnable() {
      public void run() {
        Options options = Options.create();
        options.setAllowHtml(true);
        options.setAllowCollapse(true);
        options.setColor("#00FF00");
        options.setSelectionColor("#FF0000");
        OrgChart widget = new OrgChart(createDataTable(), options);
        RootPanel.get().add(widget);
        assertEquals(0, widget.getCollapsedNodes().length());
        widget.collapse(0, true);
        assertEquals(0, widget.getCollapsedNodes().get(0));
      }
    });
  }

  public void testOnMouseOut() {
    loadApi(new Runnable() {
      public void run() {
        OrgChart chart;
        Options options = Options.create();
        chart = new OrgChart(createDataTable(), options);
        chart.addOnMouseOutHandler(new OnMouseOutHandler() {
          @Override
          public void onMouseOutEvent(OnMouseOutEvent event) {
            assertNotNull(event);
            assertEquals(1, event.getRow());
            assertEquals(1, event.getColumn());
            finishTest();
          }
        });
        triggerOnMouseOut(chart.getJso());
      }
    }, false);
  }

  public void testOnMouseOver() {
    loadApi(new Runnable() {
      public void run() {
        OrgChart chart;
        Options options = Options.create();
        chart = new OrgChart(createDataTable(), options);
        chart.addOnMouseOverHandler(new OnMouseOverHandler() {
          @Override
          public void onMouseOverEvent(OnMouseOverEvent event) {
            assertNotNull(event);
            assertEquals(1, event.getRow());
            assertEquals(1, event.getColumn());
            finishTest();
          }
        });
        triggerOnMouseOver(chart.getJso());
      }
    }, false);
  }

  public void testOrgChart() {
    loadApi(new Runnable() {
      public void run() {
        Widget widget;
        Options options = Options.create();
        options.setAllowHtml(true);
        options.setAllowCollapse(true);
        options.setColor("#00FF00");
        options.setSelectionColor("#FF0000");
        options.setNodeClass("foo");
        options.setSelectedNodeClass("foo-selected");
        widget = new OrgChart(createDataTable(), options);
        RootPanel.get().add(widget);
        // System.out.println(widget.getElement().getString());
      }
    });
  }

  public void testSelection() {
    loadApi(new Runnable() {
      public void run() {
        OrgChart.Options options = OrgChart.Options.create();
        final OrgChart viz = new OrgChart(createDataTable(), options);

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

  public void testSize() {
    loadApi(new Runnable() {
      public void run() {
        Widget widget;
        Options options;

        options = Options.create();
        options.setSize(Size.SMALL);
        widget = new OrgChart(createDataTable(), options);
        RootPanel.get().add(widget);
        // System.out.println(widget.getElement().getString());

        options = Options.create();
        options.setSize(Size.MEDIUM);
        widget = new OrgChart(createDataTable(), options);
        RootPanel.get().add(widget);
        // System.out.println(widget.getElement().getString());

        options = Options.create();
        options.setSize(Size.LARGE);
        widget = new OrgChart(createDataTable(), options);
        RootPanel.get().add(widget);
        // System.out.println(widget.getElement().getString());
      }
    });
  }

  public void testOrgChartCollapseHandler() {
    loadApi(new Runnable() {
      public void run() {
        AbstractDataTable data = createDataTable();
        OrgChart.Options options = OrgChart.Options.create();
        options.setSize(Size.LARGE);
        OrgChart widget = new OrgChart(data, options);
        widget.addCollapseHandler(new CollapseHandler() {
          @Override
          public void onCollapseEvent(CollapseEvent event) {
            assertEquals(event.getCollapsed(), true);
            assertEquals(event.getRow(), 1);
            finishTest();
          }
        });
        RootPanel.get().add(widget);
        triggerCollapse(widget.getJso());
      }
    }, false);

  };

  public void testOrgChartReady() {
    loadApi(new Runnable() {
      public void run() {
        AbstractDataTable data = createDataTable();
        OrgChart.Options options = OrgChart.Options.create();
        options.setSize(Size.LARGE);
        OrgChart widget = new OrgChart(data, options);
        widget.addReadyHandler(new ReadyHandler() {
          @Override
          public void onReady(ReadyEvent event) {
            finishTest();
          }
        });
        RootPanel.get().add(widget);
      }
    }, false);
  }

  @Override
  protected String getVisualizationPackage() {
    return OrgChart.PACKAGE;
  }

  private AbstractDataTable createDataTable() {
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
    return data;
  }

  private native void triggerOnMouseOut(JavaScriptObject jso) /*-{
    $wnd.google.visualization.events.trigger(jso, 'onmouseout', 
      {'row':1, 'column':1});
  }-*/;

  private native void triggerOnMouseOver(JavaScriptObject jso) /*-{
    $wnd.google.visualization.events.trigger(jso, 'onmouseover', 
      {'row':1, 'column':1});
  }-*/;

  private native void triggerCollapse(JavaScriptObject jso) /*-{
    $wnd.google.visualization.events.trigger(jso, 'collapse', 
      {'collapsed':true, 'row':1});
  }-*/;
}

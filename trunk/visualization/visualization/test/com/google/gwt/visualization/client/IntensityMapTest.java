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

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.events.SelectHandler;
import com.google.gwt.visualization.client.visualizations.IntensityMap;
import com.google.gwt.visualization.client.visualizations.IntensityMap.Options;
import com.google.gwt.visualization.client.visualizations.IntensityMap.Region;

/**
 * Tests for the IntensityMap class.
 */
public class IntensityMapTest extends VisualizationTest {
  public static DataTable createAsia() {
    DataTable data = DataTable.create();
    data.addColumn(ColumnType.STRING, "", "Country");
    data.addColumn(ColumnType.NUMBER, "Population (mil)", "a");
    data.addColumn(ColumnType.NUMBER, "Area (km2)", "b");
    data.addRows(2);
    data.setValue(0, 0, "CN");
    data.setValue(0, 1, 1324);
    data.setValue(0, 2, 9640821);
    data.setValue(1, 0, "IN");
    data.setValue(1, 1, 1133);
    data.setValue(1, 2, 3287263);
    return data;
  }

  public static DataTable createSingleTab() {
    DataTable data = DataTable.create();
    data.addColumn(ColumnType.STRING, "", "Country");
    data.addColumn(ColumnType.NUMBER, "Population (mil)", "a");
    data.addRows(5);
    data.setValue(0, 0, "CN");
    data.setValue(0, 1, 1324);
    data.setValue(1, 0, "IN");
    data.setValue(1, 1, 1133);
    data.setValue(2, 0, "US");
    data.setValue(2, 1, 304);
    data.setValue(3, 0, "ID");
    data.setValue(3, 1, 232);
    data.setValue(4, 0, "BR");
    data.setValue(4, 1, 187);
    return data;
  }

  public void dontTestSelection() {
    AjaxLoader.loadVisualizationApi(new Runnable() {
      public void run() {
        DataTable data = createDailyActivities();

        // Create a minimal pie chart.
        IntensityMap.Options options = IntensityMap.Options.create();
        options.setWidth(400);
        options.setHeight(240);
        VisualizationWidget<IntensityMap, IntensityMap.Options> widget = IntensityMap.createWidget(
            data, options);
        final IntensityMap viz = widget.getVisualization();

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
    }, IntensityMap.PACKAGE);
    delayTestFinish(ASYNC_DELAY_MS);
  }

  public void testColors() {
    loadApi(new Runnable() {

      public void run() {
        Widget widget;
        Options options;

        options = Options.create();
        options.setColors("green", "yellow");
        widget = IntensityMap.createWidget(createIntensityTable(), options);
        RootPanel.get().add(widget);
        assertEquals("f0f0f0%2Cffffff%2C008000", getParameter(widget, "chco"));

        options = Options.create();
        options.setColors("red", "blue");
        widget = IntensityMap.createWidget(createIntensityTable(), options);
        RootPanel.get().add(widget);
        assertEquals("f0f0f0%2Cffffff%2Cff0000", getParameter(widget, "chco"));
      }
    });
  }

  public void testRegion() {
    loadApi(new Runnable() {

      public void run() {
        Widget widget;
        Options options;

        options = Options.create();
        options.setRegion(Region.WORLD);
        widget = IntensityMap.createWidget(createAsia(), options);
        RootPanel.get().add(widget);
        assertEquals("world", getParameter(widget, "chl"));

        options = Options.create();
        options.setRegion(Region.ASIA);
        widget = IntensityMap.createWidget(createAsia(), options);
        RootPanel.get().add(widget);
        assertEquals("asia", getParameter(widget, "chl"));
      }
    });
  }

  public void testShowOneTab() {
    loadApi(new Runnable() {

      public void run() {
        Widget widget;
        Options options;
        Element grandson;

        options = Options.create();
        options.setShowOneTab(false);
        widget = IntensityMap.createWidget(createSingleTab(), options);
        RootPanel.get().add(widget);
        grandson = widget.getElement().getFirstChildElement().getFirstChildElement();
        assertEquals("div".toUpperCase(), grandson.getTagName().toUpperCase());
        assertEquals("google-visualization-intensitymap-map",
            grandson.getAttribute("class"));

        options = Options.create();
        options.setShowOneTab(true);
        widget = IntensityMap.createWidget(createSingleTab(), options);
        RootPanel.get().add(widget);
        System.out.println(widget.getElement().getString());
        // grandson =
        // widget.getElement().getFirstChildElement().getFirstChildElement();
        // assertEquals("table".toUpperCase(),
        // grandson.getTagName().toUpperCase());
        // assertEquals("google-visualization-intensitymap-tab-table",
        // grandson.getAttribute("class"));
      }
    });
  }

  @Override
  protected String getVisualizationPackage() {
    return IntensityMap.PACKAGE;
  }

  private AbstractDataTable createIntensityTable() {
    DataTable data = DataTable.create();
    data.addColumn(ColumnType.STRING, "", "Country");
    data.addColumn(ColumnType.NUMBER, "Population (mil)", "a");
    data.addColumn(ColumnType.NUMBER, "Area (km2)", "b");
    data.addRows(5);
    data.setValue(0, 0, "CN");
    data.setValue(0, 1, 1324);
    data.setValue(0, 2, 9640821);
    data.setValue(1, 0, "IN");
    data.setValue(1, 1, 1133);
    data.setValue(1, 2, 3287263);
    data.setValue(2, 0, "US");
    data.setValue(2, 1, 304);
    data.setValue(2, 2, 9629091);
    data.setValue(3, 0, "ID");
    data.setValue(3, 1, 232);
    data.setValue(3, 2, 1904569);
    data.setValue(4, 0, "BR");
    data.setValue(4, 1, 187);
    data.setValue(4, 2, 8514877);
    return data;
  }

  private String getParameter(Widget cog, String name) {
    Element div = cog.getElement();
    Element background = div.getElementsByTagName("div").getItem(2);
    String style = background.getAttribute("style");
    String url = style.split("\\(")[1].split("\\)")[0];
    return getParameter(url, name);
  }
}

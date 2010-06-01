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
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
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

  public void testSelection() {
    loadApi(new Runnable() {
      public void run() {
        DataTable data = createDailyActivities();
        IntensityMap.Options options = IntensityMap.Options.create();
        options.setWidth(400);
        options.setHeight(240);
        final IntensityMap viz = new IntensityMap(data, options);

        // Add a selection handler
        viz.addSelectHandler(new SelectHandler() {

          @Override
          public void onSelect(SelectEvent event) {
            assertNotNull(event);
            JsArray<Selection> s = viz.getSelections();
            assertEquals("Expected 1 element in the selection", 1,
                s.length());
            assertEquals("Expected column 0 to be selected", 0, s.get(0).getColumn());
            finishTest();
          }
        });
        RootPanel.get().add(viz);
        JsArray<Selection> s = 
            ArrayHelper.toJsArray(Selection.createColumnSelection(0));
        assertEquals("Expected 1 element in the selection", 1, s.length());
        assertEquals("Expected column 0 to be selected", 0, s.get(0).getColumn());
        viz.setSelections(s);
        s = viz.getSelections();
        assertEquals("Expected 1 element in the selection", 1, s.length());
        assertEquals("Expected column 0 to be selected", 0, s.get(0).getColumn());
        // Trigger a selection callback
        triggerSelection(viz, s);
      }
    }, false);
  }

  public void testColors() {
    loadApi(new Runnable() {

      public void run() {
        Widget widget;
        Options options;

        options = Options.create();
        options.setColors("green", "yellow");
        widget = new IntensityMap(createIntensityTable(), options);
        RootPanel.get().add(widget);
        assertEquals("failed to find expected parameter in url:"
            + getUrl(widget), "f0f0f0%2Cffffff%2C008000", getParameter(widget,
            "chco"));

        options = Options.create();
        options.setColors("red", "blue");
        widget = new IntensityMap(createIntensityTable(), options);
        RootPanel.get().add(widget);
        assertEquals("failed to find expected parameter in url:"
            + getUrl(widget), "f0f0f0%2Cffffff%2Cff0000", getParameter(widget,
            "chco"));
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
        widget = new IntensityMap(createAsia(), options);
        RootPanel.get().add(widget);
        assertEquals("world", getParameter(widget, "chl"));

        options = Options.create();
        options.setRegion(Region.ASIA);
        widget = new IntensityMap(createAsia(), options);
        RootPanel.get().add(widget);
        assertEquals("asia", getParameter(widget, "chl"));
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
    return getParameter(getUrl(cog), name);
  }

  private String getUrl(Widget cog) {
    Element div = cog.getElement();
    Element background = div.getElementsByTagName("div").getItem(2);
    Style style = background.getStyle();
    String styleUrl = style.getProperty("background");
    return styleUrl.split("\\(")[1].split("\\)")[0];
  }
}

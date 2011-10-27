/*
 * Copyright 2011 Google Inc.
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
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.events.OnMouseOutHandler;
import com.google.gwt.visualization.client.events.OnMouseOutHandler.OnMouseOutEvent;
import com.google.gwt.visualization.client.events.OnMouseOverHandler;
import com.google.gwt.visualization.client.events.OnMouseOverHandler.OnMouseOverEvent;
import com.google.gwt.visualization.client.visualizations.corechart.ComboChart;
import com.google.gwt.visualization.client.visualizations.corechart.CoreChart;
import com.google.gwt.visualization.client.visualizations.corechart.ComboChart.Options;
import com.google.gwt.visualization.client.visualizations.corechart.Series;
import com.google.gwt.visualization.client.visualizations.corechart.Series.Type;

import java.util.HashMap;
import java.util.Map;

/**
 * Tests for the ComboChart class.
 */
public class CoreComboChartTest extends VisualizationTest {

  /**
   * This test case tries creating a Combo chart with multiple series types.
   * It first asserts that the Visualization API has been correctly loaded 
   * (see ajax_loader.html).
   */
  public void testAComboChart() {
    loadApi(new Runnable() {
      public void run() {
        DataTable data = createDailyActivities();

        // Create a bar chart for each of the series.
        Options options = ComboChart.createComboOptions();
        options.setWidth(400);
        options.setHeight(240);
        options.setSeriesType(Type.BARS);
        RootPanel.get().add(new ComboChart(data, options));
        assertNoVisualizationErrors();
      }
    });
  }

  /**
   * This test case tries creating a Combo chart with customized series draw 
   * options for multiple series types. It first asserts that the Visualization 
   * API has been correctly loaded (see ajax_loader.html).
   */
  public void testCustomComboChart() {
    loadApi(new Runnable() {
      public void run() {
        // Customize both the series on the chart
        Series sales = Series.create();
        sales.setColor("black");
        sales.setType(Type.BARS);

        Series expenses = Series.create();
        expenses.setColor("red");
        expenses.setLineWidth(4);
        expenses.setPointSize(10);
        expenses.setType(Type.LINE);

        Options options = ComboChart.Options.create();
        options.setWidth(400);
        options.setHeight(240);
        options.setSeries(1, expenses);
        options.setSeries(0, sales); // Index order is irrelevant

        RootPanel.get().add(
            new ComboChart(createCompanyPerformance(), options));
        assertNoVisualizationErrors();
      }
    });
  }

  /**
   * This test case tries creating a Combo chart with a single series type. It 
   * also test the setSeries method. It first asserts that the Visualization 
   * API has been correctly loaded (see ajax_loader.html).
   */
  public void testASimpleComboChart() {
    loadApi(new Runnable() {
      public void run() {
        // Create a minimal chart.
        Options options = ComboChart.createComboOptions();
        options.setWidth(400);
        options.setHeight(240);

        // Customize one of the series on the chart
        Series sales = Series.create();
        sales.setColor("#009900");
        sales.setLineWidth(4);
        sales.setPointSize(10);
        sales.setType(Type.AREA);
        JsArray<Series> series = ArrayHelper.toJsArray(sales);
        options.setSeries(series);

        RootPanel.get().add(new ComboChart(createCompanyPerformance(),
            options));
        assertNoVisualizationErrors();
      }
    });
  }

  public void testOnMouseOver() {
    loadApi(new Runnable() {
      public void run() {
        Options options = Options.create();
        ComboChart chart = new ComboChart(createCompanyPerformance(), options);
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

  public void testOnMouseOut() {
    loadApi(new Runnable() {
      public void run() {
        Options options = ComboChart.createComboOptions();
        options.setSeriesType(Type.AREA);
        ComboChart chart = new ComboChart(createCompanyPerformance(), options);
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

  /**
   * Checks the HTML for visualization error elements. Fails the test if any 
   * such element exists.
   */
  private void assertNoVisualizationErrors() {
    Element error = Document.get().getElementById(
        "google-visualization-errors-0");

    if (error != null) {
      fail("At least one test in this suite caused the following error on the "
          + "page while rendering a chart: " + error.getInnerText());
    }
  }

  @Override
  protected String getVisualizationPackage() {
    return CoreChart.PACKAGE;
  }

  private native void triggerOnMouseOut(JavaScriptObject jso) /*-{
    $wnd.google.visualization.events.trigger(jso, 'onmouseout',
      {'row':1, 'column':1});
  }-*/;

  private native void triggerOnMouseOver(JavaScriptObject jso) /*-{
    $wnd.google.visualization.events.trigger(jso, 'onmouseover',
      {'row':1, 'column':1});
  }-*/;
}

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

import com.google.gwt.core.client.JsArray;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.visualizations.Visualization;

import java.util.ArrayList;
import java.util.List;

/**
 * A base class to support testing the visualization API that requires loading.
 */
public class VisualizationTest extends GWTTestCase {
  public static final int ASYNC_DELAY_MS = 10 * 1000;

  /**
   * Removes all elements in the body, except scripts and iframes.
   */
  public static void cleanDom() {
    Element bodyElem = RootPanel.getBodyElement();

    List<Element> toRemove = new ArrayList<Element>();
    for (int i = 0, n = DOM.getChildCount(bodyElem); i < n; ++i) {
      Element elem = DOM.getChild(bodyElem, i);
      String nodeName = getNodeName(elem);
      if (!"script".equals(nodeName) && !"iframe".equals(nodeName)) {
        toRemove.add(elem);
      }
    }

    for (int i = 0, n = toRemove.size(); i < n; ++i) {
      DOM.removeChild(bodyElem, toRemove.get(i));
    }
  }

  /**
   * Extracts the value of a named parameter from a URL query string.
   *
   * @param url the URL to extract the parameter from
   * @param name the name of the parameter
   * @return the value of the parameter
   */
  public static native String getParameter(String url, String name) /*-{
    var spec = "[\\?&]" + name + "=([^&#]*)";
    var regex = new RegExp(spec);
    var results = regex.exec(url);
    if(results == null) {
      return "";
    } else {
      return results[1];
    }
  }-*/;

  private static native String getNodeName(Element elem) /*-{
    return (elem.nodeName || "").toLowerCase();
  }-*/;

  private boolean loaded = false;

  public VisualizationTest() {
    super();
  }

  static DataTable getSugarSaltAndCaloriesComparison() {
    DataTable data = DataTable.create();
    data.addColumn(ColumnType.NUMBER, "Sugar");
    data.addColumn(ColumnType.NUMBER, "Calories");
    data.addColumn(ColumnType.NUMBER, "Salt");
    data.addRows(4);
    data.setValue(0, 0, 20);
    data.setValue(0, 1, 11);
    data.setValue(0, 2, 30);
    data.setValue(1, 0, 30);
    data.setValue(1, 1, 10);
    data.setValue(1, 2, 20);
    data.setValue(2, 0, 27);
    data.setValue(2, 1, 2);
    data.setValue(2, 2, 34);
    data.setValue(3, 0, 22);
    data.setValue(3, 1, 2);
    data.setValue(3, 1, 9);
    return data;
  }

  public AbstractDataTable createCompanyPerformance() {
    DataTable data = DataTable.create();
    data.addColumn(ColumnType.STRING, "Year");
    data.addColumn(ColumnType.NUMBER, "Sales");
    data.addColumn(ColumnType.NUMBER, "Expenses");
    data.addRows(4);
    data.setValue(0, 0, "2004");
    data.setValue(0, 1, 1000);
    data.setValue(0, 2, 400);
    data.setValue(1, 0, "2005");
    data.setValue(1, 1, 1170);
    data.setValue(1, 2, 460);
    data.setValue(2, 0, "2006");
    data.setValue(2, 1, 660);
    data.setValue(2, 2, 1120);
    data.setValue(3, 0, "2007");
    data.setValue(3, 1, 1030);
    data.setValue(3, 2, 540);
    return data;
  }

  @Override
  public String getModuleName() {
    return "com.google.gwt.visualization.VisualizationTest";
  }

  public void test() {
  }

  /**
   * A support method used by several test cases to create a simple DataTable.
   */
  protected DataTable createDailyActivities() {
    // Create a simple data table
    DataTable data = DataTable.create();
    data.addColumn(ColumnType.STRING, "Fruit");
    data.addColumn(ColumnType.NUMBER, "Baskets");
    data.addRows(3);
    data.setValue(0, 0, "Apple");
    data.setValue(0, 1, 11);
    data.setValue(1, 0, "Banana");
    data.setValue(1, 1, 3);
    data.setValue(2, 0, "Cherry");
    data.setValue(2, 1, 7);

    return data;
  }

  protected String getVisualizationPackage() {
    throw new UnsupportedOperationException();
  }

  /**
   * Loads the visualization API asynchronously and runs the specified test.
   * When the testRunnable method completes, the test is considered finished
   * successfully.
   *
   * @param testRunnable code to invoke when the API loadeded.
   *
   */
  protected void loadApi(final Runnable testRunnable) {
    loadApi(testRunnable, true);
  }

  /**
   * Loads the visualization API asynchronously and runs the specified test.
   *
   * @param testRunnable code to invoke when the API loadeded.
   * @param callFinishTest if <code>true</code>, the finishTest() method is
   *          called when the test completes. If <code>false</code>, the caller
   *          is responsible for ending the test.
   */
  protected void loadApi(final Runnable testRunnable,
      final boolean callFinishTest) {
    loadApi(testRunnable, callFinishTest, ASYNC_DELAY_MS);
  }

  /**
   * Loads the visualization API asynchronously and runs the specified test.
   *
   * @param testRunnable code to invoke when the API loadeded.
   * @param callFinishTest if <code>true</code>, the finishTest() method is
   *          called when the test completes. If <code>false</code>, the caller
   *          is responsible for ending the test.
   * @param asyncDelayMs number of milliseconds to wait for the test to finish.
   */
  protected void loadApi(final Runnable testRunnable,
      final boolean callFinishTest, final int asyncDelayMs) {
    if (loaded) {
      testRunnable.run();
    } else {
      VisualizationUtils.loadVisualizationApi(new Runnable() {
        public void run() {
          loaded = true;
          testRunnable.run();
          if (callFinishTest) {
            finishTest();
          }
        }
      }, getVisualizationPackage());
      delayTestFinish(asyncDelayMs);
    }
  }

  /**
   * See <a href=
   * "http://code.google.com/apis/visualization/documentation/dev/events.html" >
   * Google Visualization API documentation</a> .
   *
   * @param viz - the Visualization to trigger the event on
   * @param s - a selection object.
   */
  protected <E extends Visualization<?>, Selectable> void triggerSelection(
      E viz, JsArray<Selection> s) {
    Selection.triggerSelection(viz, s);
  }
}

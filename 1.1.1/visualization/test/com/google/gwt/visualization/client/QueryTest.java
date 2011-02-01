/*
 * Copyright 2009 Google Inc.
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

import com.google.gwt.visualization.client.Query.Callback;
import com.google.gwt.visualization.client.visualizations.BarChart;

/**
 * Tests for the Query and QueryResponse classes.
 */
public class QueryTest extends VisualizationTest {

  public void testQuerySpreadsheet() {
    loadApi(new Runnable() {
      public void run() {
        // This URL is used in the visualization API online samples.
        Query.Options options = Query.Options.create();
        options.setSendMethod(Query.SendMethod.SCRIPT_INJECTION);
        Query query = Query.create(
            "http://spreadsheets.google.com/tq?key=pCQbetd-CptHnwJEfo8tALA&pub=1",
            options);
        query.send(new Callback() {
          public void onResponse(QueryResponse response) {
            assertFalse("Response was an Error", response.isError());
            finishTest();
          }
        });
      }
    }, false);
  }

  public void testQuerySpreadsheetFail() {
    loadApi(new Runnable() {
      public void run() {
        // Set a longer timeout than the default in VisualizationTest. Sometimes
        // a remote URL takes a
        // while to respond, and I want to make sure it is longer than the
        // timeout
        // value set in query.setTimeout()
        delayTestFinish(10000);
        // Run with the URL for a bogus spreadsheet - should cause this call to
        // fail.
        Query query = Query.create("http://spreadsheets.google.com/tq?key=NONEXISTENT_SPREADSHEET&pub=1");
        query.setTimeout(8);
        query.send(new Callback() {
          public void onResponse(QueryResponse response) {
            assertTrue("Response expected an Error", response.isError());
            finishTest();
          }
        });
      }
    }, false);
  }

  @Override
  protected String getVisualizationPackage() {
    // Any package will do to test Query
    return BarChart.PACKAGE;
  }

}

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

import com.google.gwt.ajaxloader.client.Properties;
import com.google.gwt.ajaxloader.client.Properties.TypeException;
import com.google.gwt.visualization.client.visualizations.corechart.CoreChart;

/**
 * Tests for {@link ChartArea} classes.
 */
public class ChartAreaTest extends VisualizationTest {
  @Override
  public String getModuleName() {
    return "com.google.gwt.visualization.VisualizationTest";
  }

  public void testChartArea() {
    loadApi(new Runnable() {
      public void run() {
        ChartArea result = ChartArea.create();
        assertNotNull(result);

        result.setHeight(10.0);
        result.setTop(100.0);
        result.setWidth(1000.0);
        result.setLeft(10000.0);
        assertEquals(10.0, getResultNumber(result, "height"));
        assertEquals(100.0, getResultNumber(result, "top"));
        assertEquals(1000.0, getResultNumber(result, "width"));
        assertEquals(10000.0, getResultNumber(result, "left"));

        result.setHeight("10em");
        result.setTop("100em");
        result.setWidth("1000em");
        result.setLeft("10000em");
        assertEquals("10em", getResultString(result, "height"));
        assertEquals("100em", getResultString(result, "top"));
        assertEquals("1000em", getResultString(result, "width"));
        assertEquals("10000em", getResultString(result, "left"));
      }
    });
  }

  /**
   * Helper method to catch TypeException and shut-up eclipse warnings.
   */
  private static String getResultString(Properties obj, String field) {
    try {
      return obj.getString(field);
    } catch (TypeException ex) {
      fail("Can't fetch property " + field + " from object: " + ex);
    }
    return null;
  }

  /**
   * Helper method to catch TypeException and shut-up eclipse warnings.
   */
  private static double getResultNumber(Properties obj, String field) {
    try {
      return obj.getNumber(field);
    } catch (TypeException ex) {
      fail("Can't fetch property " + field + " from object: " + ex);
    }
    return Double.NaN;
  }

  @Override
  protected String getVisualizationPackage() {
    return CoreChart.PACKAGE;
  }
}

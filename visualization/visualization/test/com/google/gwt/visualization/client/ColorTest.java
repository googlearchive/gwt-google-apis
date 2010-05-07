/*
 * Copyright 2010 Google Inc.
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
import com.google.gwt.visualization.client.visualizations.PieChart;

/**
 * Tests for {@link Color} and {@link Color3D} classes.
 */
public class ColorTest extends VisualizationTest {
  @Override
  public String getModuleName() {
    return "com.google.gwt.visualization.VisualizationTest";
  }

  public void testColor() {
    loadApi(new Runnable() {
      public void run() {
        Color result = Color.create();
        assertNotNull(result);
        String fill = "test-fill";
        result.setFill(fill);
        String stroke = "test-stroke";
        result.setStroke(stroke);
        int strokeSize = 999;
        result.setStrokeSize(strokeSize);
        assertEquals(fill, getResultString(result, "fill"));
        assertEquals(stroke, getResultString(result, "stroke"));
        assertEquals(strokeSize, (int) getResultNumber(result, "strokeSize"));
      }
    });
  }

  public void testColor3D() {
    loadApi(new Runnable() {
      public void run() {
        String color1 = "red";
        String color2 = "#ff2020";
        Color3D result = Color3D.create(color1, color2);
        assertNotNull(result);
        assertEquals(color1, getResultString(result, "color"));
        assertEquals(color2, getResultString(result, "darker"));
        String color3 = "orange";
        String color4 = "blue";
        result.setFaceColor(color3);
        result.setShadeColor(color4);
        assertEquals(color3, getResultString(result, "color"));
        assertEquals(color4, getResultString(result, "darker"));
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
    return PieChart.PACKAGE;
  }
}

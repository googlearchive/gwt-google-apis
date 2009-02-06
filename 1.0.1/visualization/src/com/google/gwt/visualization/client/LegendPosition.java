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

/**
 * Specifies where to put the legend in the visualization. Not using enum
 * because I need to be able to create subclasses, for instance,
 * {@link com.google.gwt.visualization.client.visualizations.PieChart.PieLegendPosition}
 * .
 */
public class LegendPosition {
  public static final LegendPosition BOTTOM = new LegendPosition("bottom");
  public static final LegendPosition LEFT = new LegendPosition("left");
  public static final LegendPosition NONE = new LegendPosition("none");
  public static final LegendPosition RIGHT = new LegendPosition("right");
  public static final LegendPosition TOP = new LegendPosition("top");

  private final String name;

  protected LegendPosition(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
}
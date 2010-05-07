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
import com.google.gwt.dom.client.IFrameElement;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.visualizations.PieChart;
import com.google.gwt.visualization.client.visualizations.PieChart.Options;
import com.google.gwt.visualization.client.visualizations.PieChart.PieLegendPosition;

/**
 * Tests for the CommonOptions class. Uses PieChart because PieChart is the only
 * viz that uses CommonOptions rather than its subclass, CommonChartOptions.
 */
public class CommonOptionsTest extends VisualizationTest {
  public void testOptions() {
    loadApi(new Runnable() {
      public void run() {
        DataTable data = createDailyActivities();

        // Create a minimal pie chart.
        PieChart.Options options = PieChart.Options.create();
        options.setHeight(400);
        options.setWidth(400);
        options.setBackgroundColor("pink");
        options.setBorderColor("pink");
        options.setColors("pink", "black", "white");
        options.setColors(Color3D.create("#ff0000", "#ff2222"), Color3D.create(
            "#00ff00", "#22ff22"));
        options.setFocusBorderColor("pink");
        options.setLegendBackgroundColor("pink");
        options.setLegendFontSize(36.0);
        options.setLegendTextColor("pink");
        options.setTitle("My Daily Activities");
        options.setTitleColor("pink");
        options.setTitleFontSize(48.0);
        options.setTooltipHeight(12);
        options.setTooltipWidth(60);

        Widget widget = new PieChart(data, options);
        RootPanel.get().add(widget);
        Element div = widget.getElement();
        // assert that the div's first child is an iframe
        IFrameElement.as(div.getFirstChildElement());
      }
    });
  }

  public void testHeight() {
    loadApi(new Runnable() {
      public void run() {
        DataTable data = createDailyActivities();

        // Create a minimal pie chart.
        PieChart.Options options = PieChart.Options.create();
        options.setHeight(400);
        Widget widget = new PieChart(data, options);
        RootPanel.get().add(widget);
        Element div = widget.getElement();
        Element iframe = div.getFirstChildElement();
        assertEquals("400", iframe.getAttribute("height"));
      }
    });
  }

  public void testLegend() {
    loadApi(new Runnable() {
      public void run() {
        Options options;
        DataTable data = createDailyActivities();

        options = Options.create();
        options.setLegend(LegendPosition.BOTTOM);
        RootPanel.get().add(new PieChart(data, options));

        options = Options.create();
        options.setLegend(LegendPosition.TOP);
        RootPanel.get().add(new PieChart(data, options));

        options = Options.create();
        options.setLegend(LegendPosition.LEFT);
        RootPanel.get().add(new PieChart(data, options));

        options = Options.create();
        options.setLegend(LegendPosition.RIGHT);
        RootPanel.get().add(new PieChart(data, options));

        options = Options.create();
        options.setLegend(LegendPosition.NONE);
        RootPanel.get().add(new PieChart(data, options));

        options = Options.create();
        options.setLegend(PieLegendPosition.LABEL);
        RootPanel.get().add(new PieChart(data, options));
      }
    });
  }

  public void testWidth() {
    loadApi(new Runnable() {
      public void run() {
        DataTable data = createDailyActivities();

        // Create a minimal pie chart.
        PieChart.Options options = PieChart.Options.create();
        options.setWidth(400);
        Widget widget = new PieChart(data, options);
        RootPanel.get().add(widget);
        Element div = widget.getElement();
        Element iframe = div.getFirstChildElement();
        assertEquals("400", iframe.getAttribute("width"));
      }
    });
  }

  @Override
  protected String getVisualizationPackage() {
    return PieChart.PACKAGE;
  }
}

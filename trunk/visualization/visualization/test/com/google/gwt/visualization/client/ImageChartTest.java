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
import com.google.gwt.visualization.client.visualizations.ImageChart;
import com.google.gwt.visualization.client.visualizations.ImageChart.Options;

/**
 * Tests for the ImageChart class.
 */
public class ImageChartTest extends VisualizationTest {
  public void testOptions() {
    loadApi(new Runnable() {

      public void run() {
        Widget widget;
        Options options;

        options = Options.create();
        options.setColor("#FF00FF");
        options.setFill(true);
        options.setSize(500, 250);
        widget = new ImageChart(createCompanyPerformance(), options);
        RootPanel.get().add(widget);
        checkParameter(widget, "chm", 
            "b%2CFF00FF%2C0%2C1%2C0%7Cb%2CFF00FF%2C1%2C2%2C0");
        checkParameter(widget, "chs", "500x250");
      }
    });
  }

  @Override
  protected String getVisualizationPackage() {
    return ImageChart.PACKAGE;
  }
  
  private void checkParameter(Widget widget, String key, String expected) {
    assertEquals("failed to find expected parameter in url:"
        + getUrl(widget), expected, getParameter(widget, key));
  }

  private String getParameter(Widget cog, String name) {
    return getParameter(getUrl(cog), name);
  }

  private String getUrl(Widget cog) {
    Element div = cog.getElement();
    Element img = div.getElementsByTagName("img").getItem(0);
    return img.getAttribute("src");
  }
}

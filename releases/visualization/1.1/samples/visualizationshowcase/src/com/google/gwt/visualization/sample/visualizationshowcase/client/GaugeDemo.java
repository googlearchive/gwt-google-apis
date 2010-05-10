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
package com.google.gwt.visualization.sample.visualizationshowcase.client;

import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.visualizations.Gauge;

/**
 * Demo for Gauge visualization.
 */
public class GaugeDemo implements LeftTabPanel.WidgetProvider {
  private Widget widget;

  public GaugeDemo() {
    Gauge.Options options = Gauge.Options.create();
    options.setWidth(400);
    options.setHeight(240);
    options.setGaugeRange(0, 24);
    options.setGreenRange(0, 6);
    options.setYellowRange(6, 12);
    options.setRedRange(12, 24);

    DataTable data = Showcase.getDailyActivities();
    
    widget = new Gauge(data, options);
  }

  public Widget getWidget() {
    return widget;
  }
}

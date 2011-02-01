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

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.visualizations.Toolbar;
import com.google.gwt.visualization.client.visualizations.Toolbar.Component;
import com.google.gwt.visualization.client.visualizations.Toolbar.Type;

/**
 * Demo for the Visualization Toolbar.
 */
public class ToolbarDemo implements LeftTabPanel.WidgetProvider {
  private static final String GADGET = 
      "http://www.google.com/ig/modules/pie-chart.xml";
  // HACK: I didn't really draw the pie chart from this data because I didn't
  // want to bother with sending a query, but the data here more or less
  // matches the chart data.
  private static final String DATA_SOURCE = 
      "http://spreadsheets.google.com/tq?key=tRC7Qb0eZXwKXmnnPOmNj3g&pub=1";

  public Widget getWidget() {
    Panel result = new VerticalPanel();
    PieDemo pieDemo = new PieDemo();
    Widget pieWidget = pieDemo.getWidget();
    result.add(pieWidget);
    
    Toolbar toolbar = new Toolbar();
    
    Component component = Component.create();
    component.setType(Type.HTMLCODE);
    component.setDataSource(DATA_SOURCE);
    component.setGadget(GADGET);
    toolbar.addComponent(component);

    component = Component.create();
    component.setType(Type.CSV);
    component.setDataSource(DATA_SOURCE);
    component.setGadget(GADGET);
    toolbar.addComponent(component);
    
    component = Component.create();
    component.setType(Type.HTML);
    component.setDataSource(DATA_SOURCE);
    component.setGadget(GADGET);
    toolbar.addComponent(component);    

    component = Component.create();
    component.setType(Type.IGOOGLE);
    component.setDataSource(DATA_SOURCE);
    component.setGadget(GADGET);
    toolbar.addComponent(component);
    
    result.add(toolbar);

    return result;
  }
}

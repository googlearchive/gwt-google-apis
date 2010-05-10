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

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.visualization.client.visualizations.Toolbar;
import com.google.gwt.visualization.client.visualizations.Toolbar.Component;
import com.google.gwt.visualization.client.visualizations.Toolbar.Type;

/**
 * Tests for the Gauge class.
 */
public class ToolbarTest extends VisualizationTest {

  /**
   * This test case will try creating a simple toolbar.
   */
  public void testASimpleGauge() {
    loadApi(new Runnable() {

      public void run() {
        Toolbar toolbar = new Toolbar();
        
        Component component = Component.create();
        component.setType(Type.HTMLCODE);
        component.setDataSource("datasource123htmlcode");
        component.setGadget("gadget123htmlcode");
        toolbar.addComponent(component);
        
        component = Component.create();
        component.setType(Type.HTMLCODE);
        component.setDataSource("datasource123htmlcode2");
        component.setGadget("gadget123htmlcode2");
        toolbar.addComponent(component);

        component = Component.create();
        component.setType(Type.CSV);
        component.setDataSource("datasource123csv");
        component.setGadget("gadget123csv");
        toolbar.addComponent(component);
        
        component = Component.create();
        component.setType(Type.HTML);
        component.setDataSource("datasource123html2");
        component.setGadget("gadget123html2");
        toolbar.addComponent(component);    

        component = Component.create();
        component.setType(Type.IGOOGLE);
        component.setDataSource("datasource123igoogle");
        component.setGadget("gadget123igoogle2");
        toolbar.addComponent(component);
        
        RootPanel.get().add(toolbar);
      }
    });
  }

  @Override
  protected String getVisualizationPackage() {
    return Toolbar.PACKAGE;
  }
}

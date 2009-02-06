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
package com.google.gwt.maps.client.control;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.TestUtilities;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Tests for the control class and subclasses.
 */
public class ControlTest extends GWTTestCase {

  @Override
  public String getModuleName() {
    return "com.google.gwt.maps.GoogleMapsTest";
  }

  /**
   * Runs before every test method.
   */
   @Override
  public void gwtSetUp() {
     TestUtilities.cleanDom();
   }

   // Part of issue 83   
   public void testHierarchicalMapTypeControl() {
     MapWidget map = new MapWidget();
     map.setSize("300px", "300px");
     map.addControl(new HierarchicalMapTypeControl());
     RootPanel.get().add(map); 
   }
   
   public void testLargeMapControl() {
     MapWidget map = new MapWidget();
     map.setSize("300px", "300px");
     map.addControl(new LargeMapControl());
     RootPanel.get().add(map);
   }
   
   public void testMapTypeControl() {
     MapWidget map = new MapWidget();
     map.setSize("300px", "300px");
     map.addControl(new MapTypeControl());
     RootPanel.get().add(map);     
   }
   
   public void testMapTypeControlBoolean() {
     MapWidget map = new MapWidget();
     map.setSize("300px", "300px");
     map.addControl(new MapTypeControl(true));
     RootPanel.get().add(map);     
   }

   public void testMenuMapTypeControl() {
     MapWidget map = new MapWidget();
     map.setSize("300px", "300px");
     map.addControl(new MenuMapTypeControl());
     RootPanel.get().add(map);     
   }
   
   public void testMenuMapTypeControlBoolean() {
     MapWidget map = new MapWidget();
     map.setSize("300px", "300px");
     map.addControl(new MenuMapTypeControl(true));
     RootPanel.get().add(map);     
   }

   public void testOverviewMapControl() {
     MapWidget map = new MapWidget();
     map.setSize("300px", "300px");
     map.addControl(new OverviewMapControl());
     RootPanel.get().add(map);     
   }
   
   public void testScaleControl() {
     MapWidget map = new MapWidget();
     map.setSize("300px", "300px");
     map.addControl(new ScaleControl());
     RootPanel.get().add(map);     
   }
   
   public void testSmallMapControl() {
     MapWidget map = new MapWidget();
     map.setSize("300px", "300px");
     map.addControl(new SmallMapControl());
     RootPanel.get().add(map);
   }
   
   public void testSmallZoomControl() {
     MapWidget map = new MapWidget();
     map.setSize("300px", "300px");
     map.addControl(new SmallZoomControl());
     RootPanel.get().add(map);
   }   
}

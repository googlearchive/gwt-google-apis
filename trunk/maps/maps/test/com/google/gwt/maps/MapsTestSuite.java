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
package com.google.gwt.maps;

import com.google.gwt.junit.tools.GWTTestSuite;
import com.google.gwt.maps.client.CopyrightEventTest;
import com.google.gwt.maps.client.InfoWindowEventsTest;
import com.google.gwt.maps.client.MapWidgetEventsTest;
import com.google.gwt.maps.client.MapWidgetTest;
import com.google.gwt.maps.client.MapsNotInstalledTest;
import com.google.gwt.maps.client.StatusCodesTest;
import com.google.gwt.maps.client.geocode.GeocodeTest;
import com.google.gwt.maps.client.overlay.MarkerEventsTest;
import com.google.gwt.maps.client.overlay.MarkerTest;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * TestSuite for the Maps API.
 */
public class MapsTestSuite extends GWTTestSuite {
  public static Test suite() {
    TestSuite suite = new TestSuite("Test for the Maps API");
    suite.addTestSuite(MapWidgetEventsTest.class);
    suite.addTestSuite(MapsNotInstalledTest.class);
    suite.addTestSuite(MapWidgetTest.class);
    suite.addTestSuite(GeocodeTest.class); 
    suite.addTestSuite(InfoWindowEventsTest.class);
    suite.addTestSuite(MarkerEventsTest.class);
    suite.addTestSuite(MarkerTest.class);
    suite.addTestSuite(CopyrightEventTest.class);
    suite.addTestSuite(StatusCodesTest.class);
    return suite;
  }
}

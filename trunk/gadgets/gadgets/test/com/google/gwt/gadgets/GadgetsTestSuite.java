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
package com.google.gwt.gadgets;

import com.google.gwt.gadgets.client.MockPreferencesTest;
import com.google.gwt.gadgets.client.PreferencesTest;
import com.google.gwt.gadgets.client.PubSubTest;
import com.google.gwt.gadgets.client.osapi.BatchRequestTest;
import com.google.gwt.gadgets.client.osapi.OsapiCollectionTest;
import com.google.gwt.gadgets.client.osapi.OsapiRequestTest;
import com.google.gwt.gadgets.client.osapi.albums.GetAlbumsTest;
import com.google.gwt.gadgets.client.osapi.mediaitems.GetMediaItemsTest;
import com.google.gwt.gadgets.client.osapi.people.GetPeopleTest;
import com.google.gwt.gadgets.client.osapi.people.GetPersonTest;
import com.google.gwt.junit.tools.GWTTestSuite;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * TestSuite for the Gadgets API.
 */
public class GadgetsTestSuite extends GWTTestSuite {
  public static Test suite() {
    TestSuite suite = new TestSuite("Tests for Gadgets API");

    suite.addTestSuite(MockPreferencesTest.class);
    suite.addTestSuite(PreferencesTest.class);
    suite.addTestSuite(BatchRequestTest.class);
    suite.addTestSuite(GetAlbumsTest.class);
    suite.addTestSuite(GetMediaItemsTest.class);
    suite.addTestSuite(GetPeopleTest.class);
    suite.addTestSuite(GetPersonTest.class);
    suite.addTestSuite(OsapiCollectionTest.class);
    suite.addTestSuite(OsapiRequestTest.class);
    suite.addTestSuite(PubSubTest.class);
    
    return suite;
  }
}
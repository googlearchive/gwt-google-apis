/*
 * Copyright 2009 Google Inc.
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
package com.google.gwt.ajaxloader;

import com.google.gwt.junit.tools.GWTTestSuite;
import com.google.gwt.ajaxloader.client.AjaxLoaderTest;
import com.google.gwt.ajaxloader.client.JsDateTest;
import com.google.gwt.ajaxloader.client.PropertiesTest;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * TestSuite for the AjaxLoader API.
 */
public class AjaxLoaderTestSuite extends GWTTestSuite {
  public static Test suite() {
    TestSuite suite = new TestSuite("Test for the AjaxLoader API");
    suite.addTestSuite(AjaxLoaderTest.class);
    suite.addTestSuite(JsDateTest.class);
    suite.addTestSuite(PropertiesTest.class);
    return suite;
  }
}

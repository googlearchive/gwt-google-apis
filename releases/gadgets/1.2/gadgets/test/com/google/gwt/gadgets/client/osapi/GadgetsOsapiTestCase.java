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
package com.google.gwt.gadgets.client.osapi;

import com.google.gwt.junit.client.GWTTestCase;

/**
 * Superclass for all test of Osapi.
 */
public class GadgetsOsapiTestCase extends GWTTestCase {

  private static final int ASYNC_DELAY_MS = 2 * 1000;

  @Override
  public String getModuleName() {
    return "com.google.gwt.gadgets.GadgetsTest";
  }

  protected void asAsync() {
    delayTestFinish(ASYNC_DELAY_MS);
  }

  /**
   * Runs before every test method.
   */
  @Override
  protected void gwtSetUp() throws Exception {
    super.gwtSetUp();
    setMockBase();
  }

  /**
   * Cleaning after test.
   */
  @Override
  protected void gwtTearDown() throws Exception {
    clearMock();
    super.gwtTearDown();
  }

  private native void clearMock() /*-{
    $wnd.osapi = undefined;
  }-*/;

  private native void setMockBase() /*-{
    $wnd.osapi = {
      "people": {},
      "albums": {},
      "mediaitems": {}
    };
  }-*/;
}

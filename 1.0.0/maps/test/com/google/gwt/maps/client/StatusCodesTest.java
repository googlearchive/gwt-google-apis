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
package com.google.gwt.maps.client;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.maps.client.geocode.StatusCodes;

/**
 * Tests the StatusCode class.
 * 
 */
public class StatusCodesTest extends GWTTestCase {

  @Override
  public String getModuleName() {
    return "com.google.gwt.maps.GoogleMapsTest";
  }

  public void testStatusCodeValues() {
    assertEquals(StatusCodes.BAD_KEY,  610);
    assertEquals(StatusCodes.BAD_REQUEST, 400);
    assertEquals(StatusCodes.MISSING_ADDRESS, 601);
    assertEquals(StatusCodes.MISSING_QUERY, 601);
    assertEquals(StatusCodes.SERVER_ERROR, 500);
    assertEquals(StatusCodes.SUCCESS, 200);
    assertEquals(StatusCodes.UNAVAILABLE_ADDRESS, 603);
    assertEquals(StatusCodes.UNKNOWN_ADDRESS, 602);
  }
}

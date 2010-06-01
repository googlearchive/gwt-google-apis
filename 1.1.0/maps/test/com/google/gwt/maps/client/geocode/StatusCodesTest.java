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
package com.google.gwt.maps.client.geocode;

import com.google.gwt.maps.client.MapsTestCase;

/**
 * Tests for the StatusCode class.
 */
public class StatusCodesTest extends MapsTestCase {

  @Override
  public String getModuleName() {
    return "com.google.gwt.maps.GoogleMapsTest";
  }

  public void testGetNameByConstant() {
    loadApi(new Runnable() {
      public void run() {
        assertEquals(StatusCodes.getName(StatusCodes.BAD_KEY), "BAD_KEY");
        assertEquals(StatusCodes.getName(StatusCodes.BAD_REQUEST),
            "BAD_REQUEST");
        assertEquals(StatusCodes.getName(StatusCodes.MISSING_ADDRESS),
            "MISSING_ADDRESS");
        // Synonym for Missing Address
        assertEquals(StatusCodes.getName(StatusCodes.MISSING_QUERY),
            "MISSING_ADDRESS");
        assertEquals(StatusCodes.getName(StatusCodes.SERVER_ERROR),
            "SERVER_ERROR");
        assertEquals(StatusCodes.getName(StatusCodes.SUCCESS), "SUCCESS");
        assertEquals(StatusCodes.getName(StatusCodes.TOO_MANY_QUERIES),
            "TOO_MANY_QUERIES");
        assertEquals(StatusCodes.getName(StatusCodes.UNAVAILABLE_ADDRESS),
            "UNAVAILABLE_ADDRESS");
        assertEquals(StatusCodes.getName(StatusCodes.UNKNOWN_ADDRESS),
            "UNKNOWN_ADDRESS");
        assertEquals(StatusCodes.getName(StatusCodes.UNKNOWN_DIRECTIONS),
            "UNKNOWN_DIRECTIONS");
      }
    });
  }

  public void testGetNameByNumber() {
    loadApi(new Runnable() {
      public void run() {
        assertEquals(StatusCodes.getName(200), "SUCCESS");
        assertEquals(StatusCodes.getName(400), "BAD_REQUEST");
        assertEquals(StatusCodes.getName(500), "SERVER_ERROR");
        assertEquals(StatusCodes.getName(601), "MISSING_ADDRESS");
        assertEquals(StatusCodes.getName(602), "UNKNOWN_ADDRESS");
        assertEquals(StatusCodes.getName(603), "UNAVAILABLE_ADDRESS");
        assertEquals(StatusCodes.getName(604), "UNKNOWN_DIRECTIONS");
        assertEquals(StatusCodes.getName(610), "BAD_KEY");
        assertEquals(StatusCodes.getName(620), "TOO_MANY_QUERIES");
        assertEquals(StatusCodes.getName(123), "UNKNOWN_STATUS: 123");
      }
    });
  }

  public void testStatusCodeValues() {
    loadApi(new Runnable() {
      public void run() {
        assertEquals(StatusCodes.SUCCESS, 200);
        assertEquals(StatusCodes.BAD_REQUEST, 400);
        assertEquals(StatusCodes.SERVER_ERROR, 500);
        assertEquals(StatusCodes.MISSING_ADDRESS, 601);
        assertEquals(StatusCodes.MISSING_QUERY, 601);
        assertEquals(StatusCodes.UNKNOWN_ADDRESS, 602);
        assertEquals(StatusCodes.UNAVAILABLE_ADDRESS, 603);
        assertEquals(StatusCodes.UNKNOWN_DIRECTIONS, 604);
        assertEquals(StatusCodes.BAD_KEY, 610);
        assertEquals(StatusCodes.TOO_MANY_QUERIES, 620);
      }
    });
  }
}

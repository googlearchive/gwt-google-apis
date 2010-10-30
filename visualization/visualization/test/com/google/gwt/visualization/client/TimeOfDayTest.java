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
package com.google.gwt.visualization.client;

import com.google.gwt.visualization.client.TimeOfDay.BadTimeException;

import junit.framework.TestCase;

import java.util.Arrays;

/**
 * Unit test for TimeOfDay
 */
public class TimeOfDayTest extends TestCase {

  public void testCompareTo() throws BadTimeException {
    TimeOfDay[] array = {
      new TimeOfDay(1, 1, 1, 1),
      new TimeOfDay(10, 1, 1, 1),
      new TimeOfDay(1, 10, 1, 1),
      new TimeOfDay(1, 1, 10, 1),
      new TimeOfDay(1, 1, 1, 10),
      new TimeOfDay(0, 0, 0, 0),
      new TimeOfDay(23, 59, 59, 999),
    };
    Arrays.sort(array);
    assertEquals(Arrays.asList(
            new TimeOfDay(0, 0, 0, 0),
            new TimeOfDay(1, 1, 1, 1),
            new TimeOfDay(1, 1, 1, 10),
            new TimeOfDay(1, 1, 10, 1),
            new TimeOfDay(1, 10, 1, 1),
            new TimeOfDay(10, 1, 1, 1),
            new TimeOfDay(23, 59, 59, 999)),
        Arrays.asList(array));
    assertEquals(0, new TimeOfDay(1, 2, 3, 4).compareTo(new TimeOfDay(1, 2, 3, 4)));
  }
  
  public void testMillis() throws BadTimeException {
    assertEquals(86399999, new TimeOfDay(23,59, 59, 999).millis());
  }
}

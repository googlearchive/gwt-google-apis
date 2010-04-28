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
package com.google.gwt.maps.client.geom;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.maps.client.MapsTestCase;

/**
 * Tests the Bounds class.
 */
public class BoundsTest extends MapsTestCase {

  @Override
  public String getModuleName() {
    return "com.google.gwt.maps.GoogleMapsTest";
  }

  public void testContainsBounds() {
    loadApi(new Runnable() {
      public void run() {
        Bounds b1 = Bounds.newInstance(0, 0, 10, 10);
        Bounds b2 = Bounds.newInstance(2, 2, 8, 8);
        assertTrue("b1 contains b2", b1.containsBounds(b2));
        assertFalse("b2 contains b1", b2.containsBounds(b1));
      }
    });
  }

  public void testContainsPoint() {
    loadApi(new Runnable() {
      public void run() {
        Bounds b = Bounds.newInstance(0, 0, 10, 10);
        assertTrue("Contains 5,5", b.containsPoint(Point.newInstance(5, 5)));
        assertFalse("Contains 15,5", b.containsPoint(Point.newInstance(15, 5)));
      }
    });
  }

  @SuppressWarnings("unchecked")
  public void testCorners() {
    loadApi(new Runnable() {
      public void run() {
        JsArray<Point> points = (JsArray<Point>) Point.createArray();
        points.set(0, Point.newInstance(10, 20));
        points.set(1, Point.newInstance(30, 40));
        Bounds b = Bounds.newInstance(points);
        assertEquals("upper left X", 10, b.getUpperLeft().getX());
        assertEquals("upper left Y", 20, b.getUpperLeft().getY());
        assertEquals("lower right X", 30, b.getLowerRight().getX());
        assertEquals("lower right Y", 40, b.getLowerRight().getY());
      }
    });
  }

  @SuppressWarnings("unchecked")
  public void testExtend() {
    loadApi(new Runnable() {
      public void run() {
        JsArray<Point> points = (JsArray<Point>) Point.createArray();
        points.set(0, Point.newInstance(0, 0));
        points.set(1, Point.newInstance(45, 45));
        Bounds b = Bounds.newInstance(points);
        b.extend(Point.newInstance(45, 50));

        assertEquals("min x out of bounds", 0, b.getMinX());
        assertEquals("min y out of bounds", 0, b.getMinY());
        assertEquals("max x out of bounds", 45, b.getMaxX());
        assertEquals("max y out of bounds", 50, b.getMaxY());
      }
    });
  }

  @SuppressWarnings("unchecked")
  public void testMid() {
    loadApi(new Runnable() {
      public void run() {
        JsArray<Point> points = (JsArray<Point>) Point.createArray();
        points.set(0, Point.newInstance(0, 0));
        points.set(1, Point.newInstance(90, 100));
        Bounds b = Bounds.newInstance(points);
        assertEquals("center x coordinate", 45, b.getCenter().getX());
        assertEquals("center y coordinate", 50, b.getCenter().getY());
      }
    });
  }

  public void testNewInstance() {
    loadApi(new Runnable() {
      public void run() {
        @SuppressWarnings("unused")
        Bounds b = Bounds.newInstance(0, 0, 45, 45);
      }
    });
  }

  @SuppressWarnings("unchecked")
  public void testNewInstanceArray() {
    loadApi(new Runnable() {
      public void run() {
        JsArray<Point> points = (JsArray<Point>) Point.createArray();
        points.set(0, Point.newInstance(0, 0));
        points.set(1, Point.newInstance(45, 45));
        @SuppressWarnings("unused")
        Bounds b = Bounds.newInstance(points);
      }
    });
  }
}

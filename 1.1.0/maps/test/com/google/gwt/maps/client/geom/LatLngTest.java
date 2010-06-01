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
import com.google.gwt.maps.client.TestUtilities;

/**
 * Tests the LatLngBounds class.
 */
public class LatLngTest extends MapsTestCase {

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

  public void testDistanceFrom() {
    loadApi(new Runnable() {
      public void run() {
        LatLng p1 = LatLng.newInstance(1, 1);
        LatLng p2 = LatLng.newInstance(2, 2);
        double result = p1.distanceFrom(p2);
        double result2 = p1.distanceFrom(p2, 1000000); // a planet w/ radius
        // 1,000 km
        assertTrue(result > 0);
        assertTrue(result2 > 0);
        assertTrue(result > result2);
      }
    });
  }

  public void testFromUrlValue() {
    loadApi(new Runnable() {
      public void run() {
        LatLng result = LatLng.fromUrlValue("12.345, 54.321");
        assertEquals("fromUrl - latitute", result.getLatitude(), 12.345, .001);
        assertEquals("fromUrl - longitude", result.getLongitude(), 54.321, .001);
      }
    });
  }

  public void testIsEquals() {
    loadApi(new Runnable() {
      public void run() {
        LatLng p1 = LatLng.newInstance(1, 1);
        LatLng p2 = LatLng.newInstance(1, 1);
        LatLng p3 = LatLng.newInstance(3, 3);
        assertTrue(p1.isEquals(p2));
        assertTrue(p2.isEquals(p1));
        assertFalse(p1.isEquals(p3));
        assertFalse(p3.isEquals(p2));
      }
    });
  }

  public void testNewInstance() {
    loadApi(new Runnable() {
      public void run() {
        LatLng latlng = LatLng.newInstance(1.0, 2.0);
        assertEquals("latitude", 1.0, latlng.getLatitude());
        assertEquals("longitude", 2.0, latlng.getLongitude());
        latlng = LatLng.newInstance(1.0, 200.0, true);
        assertEquals("latitude", 1.0, latlng.getLatitude());
        assertEquals("longitude", 200.0, latlng.getLongitude());
      }
    });
  }

  public void testRadians() {
    loadApi(new Runnable() {
      public void run() {
        LatLng p1 = LatLng.newInstance(45, 0);
        double radians = p1.getLatitudeRadians();
        assertEquals(radians, (Math.PI / 4), .02);
        LatLng p2 = LatLng.newInstance(0, -90);
        radians = p2.getLongitudeRadians();
        assertEquals(radians, -(Math.PI / 2), .02);
      }
    });
  }

  public void testToJsArray() {
    loadApi(new Runnable() {
      public void run() {
        LatLng p1 = LatLng.newInstance(1, 1);
        LatLng p2 = LatLng.newInstance(2, 2);
        LatLng p3 = LatLng.newInstance(3, 3);
        LatLng[] points = {p1, p2, p3};
        JsArray<LatLng> arr = LatLng.toJsArray(points);
        assertEquals("p1", p1, arr.get(0));
        assertEquals("p2", p2, arr.get(1));
        assertEquals("p3", p3, arr.get(2));
        assertEquals("points.length()", points.length, arr.length());
      }
    });
  }

  public void testToUrlValue() {
    loadApi(new Runnable() {
      public void run() {
        LatLng p1 = LatLng.newInstance(Math.PI / 4, 0);
        String url1 = p1.toUrlValue();
        assertNotNull(url1);
        String url2 = p1.toUrlValue(2);
        assertNotNull(url2);
        // URLs should be different.
        assertTrue(!url1.equals(url2));
      }
    });
  }
}

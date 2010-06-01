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

import com.google.gwt.maps.client.MapsTestCase;
import com.google.gwt.maps.client.TestUtilities;

/**
 * Tests the LatLngBounds class.
 */
public class LatLngBoundsTest extends MapsTestCase {

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

  public void testContainsLatLng() {
    loadApi(new Runnable() {
      public void run() {
        LatLngBounds bounds = LatLngBounds.newInstance(
            LatLng.newInstance(0, 0), LatLng.newInstance(50, 50));
        assertTrue("contains 45,45", bounds.containsLatLng(LatLng.newInstance(
            45, 45)));
        assertFalse("contains -45,0", bounds.containsLatLng(LatLng.newInstance(
            -45, 0)));
      }
    });
  }

  public void testContainsLatLngBounds() {
    loadApi(new Runnable() {
      public void run() {
        LatLngBounds b1 = LatLngBounds.newInstance(LatLng.newInstance(0, 0),
            LatLng.newInstance(50, 50));
        LatLngBounds b2 = LatLngBounds.newInstance(LatLng.newInstance(10, 10),
            LatLng.newInstance(20, 20));
        LatLngBounds b3 = LatLngBounds.newInstance(LatLng.newInstance(60, 10),
            LatLng.newInstance(70, 20));
        assertTrue("b1 contains b2", b1.containsBounds(b2));
        assertFalse("b2 contains b1", b2.containsBounds(b1));
        assertFalse("b1 contains b3", b1.containsBounds(b3));
        assertFalse("b3 contains b1", b3.containsBounds(b1));
      }
    });
  }

  public void testExtend() {
    loadApi(new Runnable() {
      public void run() {
        LatLngBounds b1 = LatLngBounds.newInstance(LatLng.newInstance(0, 0),
            LatLng.newInstance(50.0, 50.0));
        b1.extend(LatLng.newInstance(60.0, 50.0));
        LatLng expected = LatLng.newInstance(60.0, 50.0);
        assertTrue("northeast expected: " + expected + " got: "
            + b1.getNorthEast(), expected.isEquals(b1.getNorthEast()));
      }
    });
  }

  public void testGetCenter() {
    loadApi(new Runnable() {
      public void run() {
        LatLng latlng1 = LatLng.newInstance(0, 0);
        LatLng latlng2 = LatLng.newInstance(10, 10);
        LatLng latlng3 = LatLng.newInstance(5, 5);
        LatLngBounds bounds = LatLngBounds.newInstance(latlng1, latlng2);
        assertTrue("getCenter", latlng3.isEquals(bounds.getCenter()));
      }
    });
  }

  public void testIntersects() {
    loadApi(new Runnable() {
      public void run() {
        LatLng latlng1 = LatLng.newInstance(2, 2);
        LatLng latlng2 = LatLng.newInstance(10, 10);
        LatLng latlng3 = LatLng.newInstance(0, 0);
        LatLng latlng4 = LatLng.newInstance(5, 5);
        LatLng latlng5 = LatLng.newInstance(1, 1);
        LatLngBounds b1 = LatLngBounds.newInstance(latlng1, latlng2);
        LatLngBounds b2 = LatLngBounds.newInstance(latlng3, latlng4);
        LatLngBounds b3 = LatLngBounds.newInstance(latlng3, latlng5);
        assertTrue("b1 intersects b2", b1.intersects(b2));
        assertTrue("b2 intersects b1", b2.intersects(b1));
        assertFalse("b3 intersects b1", b3.intersects(b1));
        assertFalse("b3 intersects b1", b1.intersects(b3));
      }
    });
  }

  public void testIsEmpty() {
    loadApi(new Runnable() {
      public void run() {
        LatLng latlng1 = LatLng.newInstance(47.5, 47.5);
        LatLng latlng2 = LatLng.newInstance(47.5, 47.5);
        LatLng latlng3 = LatLng.newInstance(40, 47.5);
        LatLng latlng4 = LatLng.newInstance(47.5, 50);
        LatLngBounds bounds = LatLngBounds.newInstance(latlng1, latlng2);
        assertFalse("nonempty bounds", bounds.isEmpty());
        bounds = LatLngBounds.newInstance(latlng3, latlng4);
        assertFalse("nonempty bounds", bounds.isEmpty());
        bounds = LatLngBounds.newInstance();
        assertTrue("empty bounds", bounds.isEmpty());
      }
    });
  }

  public void testIsFulLat() {
    loadApi(new Runnable() {
      public void run() {
        LatLng latlng1 = LatLng.newInstance(-90, 45);
        LatLng latlng2 = LatLng.newInstance(90, 50);
        LatLng latlng3 = LatLng.newInstance(50, 50);
        LatLngBounds bounds = LatLngBounds.newInstance(latlng1, latlng2);
        assertTrue("full Latitude", bounds.isFullLatitude());
        bounds = LatLngBounds.newInstance(latlng1, latlng3);
        assertFalse("not full Latitude", bounds.isFullLatitude());
      }
    });
  }

  public void testIsFulLng() {
    loadApi(new Runnable() {
      public void run() {
        LatLng latlng1 = LatLng.newInstance(-10, -180);
        LatLng latlng2 = LatLng.newInstance(50, 180);
        LatLng latlng3 = LatLng.newInstance(50, 50);
        LatLngBounds bounds = LatLngBounds.newInstance(latlng1, latlng2);
        assertTrue("full Longitude", bounds.isFullLongitude());
        bounds = LatLngBounds.newInstance(latlng1, latlng3);
        assertFalse("not full Longitude", bounds.isFullLongitude());
      }
    });
  }

  public void testNewInstance() {
    loadApi(new Runnable() {
      public void run() {
        LatLngBounds bounds = LatLngBounds.newInstance();
        assertNotNull(bounds);
        assertNotNull(bounds.getCenter());

        bounds = LatLngBounds.newInstance(LatLng.newInstance(0, 0),
            LatLng.newInstance(45, 45));
        assertNotNull(bounds);
      }
    });
  }

  public void testToSpan() {
    loadApi(new Runnable() {
      public void run() {
        LatLng latlng1 = LatLng.newInstance(-5, -4);
        LatLng latlng2 = LatLng.newInstance(5, 4);
        LatLngBounds bounds = LatLngBounds.newInstance(latlng1, latlng2);
        double width = bounds.toSpan().getLatitude();
        double height = bounds.toSpan().getLongitude();
        assertEquals("width", 10, (int) width);
        assertEquals("height", 8, (int) height);
      }
    });
  }
}

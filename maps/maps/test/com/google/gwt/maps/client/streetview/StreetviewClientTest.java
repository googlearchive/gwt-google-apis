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
package com.google.gwt.maps.client.streetview;

import com.google.gwt.maps.client.MapsTestCase;
import com.google.gwt.maps.client.TestUtilities;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.streetview.StreetviewClient.ReturnValue;

/**
 * Tests for {@link StreetviewClient} and supporting classes.
 */
public class StreetviewClientTest extends MapsTestCase {

  /**
   * Runs before every test method.
   */
  @Override
  public void gwtSetUp() {
    TestUtilities.cleanDom();
  }

  public void testGetNearestPanorama_noPanorama() {
    loadApi(new Runnable() {
      LatLng mountEverest = LatLng.newInstance(27.988056, 86.925278);

      public void run() {
        StreetviewClient client = new StreetviewClient();
        client.getNearestPanorama(mountEverest, new DataStreetviewCallback() {
          public void onDone(StreetviewData data) {
            assertEquals(ReturnValue.NO_NEARBY_PANO.getReturnCode(),
                data.getCode());
            assertNull(data.getLinks());
            assertNull(data.getCopyright());
            assertNull(data.getLocation());
            finishTest();
          }
        });
      }
    }, false);
  }

  public void testGetNearestPanorama_ok() {
    loadApi(new Runnable() {
      LatLng tenthStreet = LatLng.newInstance(33.7814839, -84.3879353);

      public void run() {
        StreetviewClient client = new StreetviewClient();
        client.getNearestPanorama(tenthStreet, new DataStreetviewCallback() {
          public void onDone(StreetviewData data) {
            assertEquals(ReturnValue.SUCCESS.getReturnCode(), data.getCode());
            LatLng point = data.getLocation().getLatLng();
            assertEquals(tenthStreet.getLatitude(), point.getLatitude(), 1e-2);
            assertEquals(tenthStreet.getLongitude(), point.getLongitude(), 1e-2);
            assertNotNull(data.getLinks());
            assertNotNull(data.getCopyright());
            assertNotNull(data.getLocation().getPanoId());
            assertNotNull(data.getLocation().getDescription());
            assertNotNull(data.getLocation().getPov());
            finishTest();
          }
        });
      }
    }, false);
  }

  public void testGetNearestPanoramaById_invalidId() {
    loadApi(new Runnable() {
      String panoId = "I just hope it's not a valid panoId";

      public void run() {
        StreetviewClient client = new StreetviewClient();
        client.getPanoramaById(panoId, new DataStreetviewCallback() {
          public void onDone(StreetviewData data) {
            assertEquals(ReturnValue.NO_NEARBY_PANO.getReturnCode(),
                data.getCode());
            assertNull(data.getLinks());
            assertNull(data.getCopyright());
            assertNull(data.getLocation());
            finishTest();
          }
        });
      }
    }, false);
  }

  public void testGetNearestPanoramaById_ok() {
    loadApi(new Runnable() {
      LatLng tenthStreet = LatLng.newInstance(33.7814839, -84.3879353);
      StreetviewClient client = new StreetviewClient();

      public void doTest(String panoId) {
        client.getPanoramaById(panoId, new DataStreetviewCallback() {
          public void onDone(StreetviewData data) {
            assertEquals(ReturnValue.SUCCESS.getReturnCode(), data.getCode());
            LatLng point = data.getLocation().getLatLng();
            assertEquals(tenthStreet.getLatitude(), point.getLatitude(), 1e-2);
            assertEquals(tenthStreet.getLongitude(), point.getLongitude(), 1e-2);
            assertNotNull(data.getLinks());
            assertNotNull(data.getCopyright());
            assertNotNull(data.getLocation().getPanoId());
            assertNotNull(data.getLocation().getDescription());
            assertNotNull(data.getLocation().getPov());
            finishTest();
          }
        });
      }

      // PanoIds are not stable, so we first need to obtain one to invoke
      // getPanoramaById() with it.
      public void run() {
        client.getNearestPanorama(tenthStreet, new DataStreetviewCallback() {
          public void onDone(StreetviewData data) {
            assertEquals(ReturnValue.SUCCESS.getReturnCode(), data.getCode());
            doTest(data.getLocation().getPanoId());
          }
        });
      }
    }, false);
  }

  public void testGetNearestPanoramaLatLng_noPanorama() {
    loadApi(new Runnable() {
      LatLng mountEverest = LatLng.newInstance(27.988056, 86.925278);

      public void run() {
        StreetviewClient client = new StreetviewClient();
        client.getNearestPanoramaLatLng(mountEverest,
            new LatLngStreetviewCallback() {
              @Override
              public void onFailure() {
                finishTest();
              }

              @Override
              public void onSuccess(LatLng point) {
                fail("Panorama not expected at Mount Everest");
              }
            });
      }
    }, false);
  }

  public void testGetNearestPanoramaLatLng_ok() {
    loadApi(new Runnable() {
      LatLng tenthStreet = LatLng.newInstance(33.7814839, -84.3879353);

      public void run() {
        StreetviewClient client = new StreetviewClient();
        client.getNearestPanoramaLatLng(tenthStreet,
            new LatLngStreetviewCallback() {
              @Override
              public void onFailure() {
                fail("No panorama at 10th street found");
              }

              @Override
              public void onSuccess(LatLng point) {
                assertEquals(tenthStreet.getLatitude(), point.getLatitude(),
                    1e-2);
                assertEquals(tenthStreet.getLongitude(), point.getLongitude(),
                    1e-2);
                finishTest();
              }
            });
      }
    }, false);
  }
}

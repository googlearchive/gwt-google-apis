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
import com.google.gwt.maps.client.event.StreetviewErrorHandler;
import com.google.gwt.maps.client.event.StreetviewInitializedHandler;
import com.google.gwt.maps.client.event.StreetviewPitchChangedHandler;
import com.google.gwt.maps.client.event.StreetviewYawChangedHandler;
import com.google.gwt.maps.client.event.StreetviewZoomChangedHandler;
import com.google.gwt.maps.client.event.StreetviewErrorHandler.StreetviewErrorEvent;
import com.google.gwt.maps.client.event.StreetviewPitchChangedHandler.StreetviewPitchChangedEvent;
import com.google.gwt.maps.client.event.StreetviewYawChangedHandler.StreetviewYawChangedEvent;
import com.google.gwt.maps.client.event.StreetviewZoomChangedHandler.StreetviewZoomChangedEvent;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Tests for {@link StreetviewPanoramaWidget} and supporting classes.
 */
public class StreetviewPanoramaWidgetTest2 extends MapsTestCase {

  /**
   * HACK: Workaround to avoid running these tests on FF. Tests including Flash
   * plugin fail on FF if screen of the tester machine is locked.
   */
  private static native boolean isFirefoxOnWindows() /*-{
    var ua = navigator.userAgent.toLowerCase();
    return (ua.indexOf("firefox") != -1) && (ua.indexOf("windows") != -1);
  }-*/;

  private StreetviewPanoramaWidget panorama;

  public void testFollowLink() {
    /**
     * HACK: Workaround to avoid running these tests on FF. Tests including
     * Flash plugin fail on FF if screen of the tester machine is locked.
     */
    if (isFirefoxOnWindows()) {
      return;
    }
    loadApi(new Runnable() {
      private int handleCount = 0;
      private double longitude = 0;

      public void run() {
        final LatLng tenthStreet = LatLng.newInstance(33.7814839, -84.3879353);
        StreetviewPanoramaOptions options = StreetviewPanoramaOptions.newInstance();
        options.setLatLng(tenthStreet);

        panorama = new StreetviewPanoramaWidget(options);
        panorama.setSize("500px", "300px");

        panorama.addInitializedHandler(new StreetviewInitializedHandler() {
          public void onInitialized(StreetviewInitializedEvent event) {
            double newLongitude = event.getLocation().getLatLng().getLongitude();

            if (handleCount > 0) {
              assertTrue(newLongitude > longitude);
            }

            longitude = newLongitude;
            handleCount++;

            if (handleCount == 1) {
              finishTest();
            }

            // It's hardcoded, as the only way to get exact links is by using
            // StreetviewClient, which is out of scope of this test.
            // Nevertheless going east on tenth street should work.
            panorama.followLink(90);
          }
        });

        RootPanel.get().add(panorama);
      }
    }, false);
  }

  /**
   * Using different module to force browser refresh for
   * {@link StreetviewPanoramaWidget} tests.
   */
  @Override
  public String getModuleName() {
    return "com.google.gwt.maps.GoogleStreetviewTest2";
  }

  public void testPanoramaWidget() {
    /**
     * HACK: Workaround to avoid running these tests on FF. Tests including
     * Flash plugin fail on FF if screen of the tester machine is locked.
     */
    if (isFirefoxOnWindows()) {
      return;
    }
    loadApi(new Runnable() {
      public void run() {
        RootPanel.get().add(newDefaultPanorama());
      }
    });
  }

  public void testPanoramaWidget_equallPropertiesOnInitialize() {
    /**
     * HACK: Workaround to avoid running these tests on FF. Tests including
     * Flash plugin fail on FF if screen of the tester machine is locked.
     */
    if (isFirefoxOnWindows()) {
      return;
    }
    loadApi(new Runnable() {
      public void run() {
        final LatLng tenthStreet = LatLng.newInstance(33.7814839, -84.3879353);
        final Pov pov = Pov.newInstance();
        pov.setPitch(-5).setYaw(180).setZoom(2);

        StreetviewPanoramaOptions options = StreetviewPanoramaOptions.newInstance();
        options.setLatLng(tenthStreet);
        options.setPov(pov);

        panorama = new StreetviewPanoramaWidget(options);
        panorama.setSize("500px", "300px");

        panorama.addInitializedHandler(new StreetviewInitializedHandler() {
          public void onInitialized(StreetviewInitializedEvent event) {
            LatLng point = panorama.getLatLng();
            assertEquals(tenthStreet.getLatitude(), point.getLatitude(), 1e-2);
            assertEquals(tenthStreet.getLongitude(), point.getLongitude(), 1e-2);

            Pov actualPov = panorama.getPov();
            assertEquals(pov.getPitch(), actualPov.getPitch(), 1e-2);
            assertEquals(pov.getYaw(), actualPov.getYaw(), 1e-2);
            // Intentionally not testing zoom, as it behaves unpredictably

            finishTest();
          }
        });

        RootPanel.get().add(panorama);
      }
    }, false);
  }

  public void testPanTo() {
    /**
     * HACK: Workaround to avoid running these tests on FF. Tests including
     * Flash plugin fail on FF if screen of the tester machine is locked.
     */
    if (isFirefoxOnWindows()) {
      return;
    }
    loadApi(new Runnable() {

      private boolean pitchChanged, yawChanged, zoomChanged;

      public void run() {
        panorama = newDefaultPanorama();

        final double pitch = 30;
        final double yaw = 180;
        final double zoom = 1;

        panorama.addPitchChangedHandler(new StreetviewPitchChangedHandler() {
          public void onPitchChanged(StreetviewPitchChangedEvent event) {
            assertEquals(panorama, event.getSender());
            // 'If' instead of 'assert', as this event might be triggered few
            // times before we invoke panTo() method
            if (Math.abs(pitch - event.getPitch()) < 1e-2) {
              pitchChanged = true;
              checkIfAllOccured();
            }
          }
        });

        panorama.addYawChangedHandler(new StreetviewYawChangedHandler() {
          public void onYawChanged(StreetviewYawChangedEvent event) {
            assertEquals(panorama, event.getSender());
            // 'If' instead of 'assert', as this event might be triggered few
            // times before we invoke panTo() method
            if (Math.abs(yaw - event.getYaw()) < 1e-2) {
              yawChanged = true;
              checkIfAllOccured();
            }
          }
        });

        panorama.addZoomChangedHandler(new StreetviewZoomChangedHandler() {
          public void onZoomChanged(StreetviewZoomChangedEvent event) {
            assertEquals(panorama, event.getSender());
            // 'If' instead of 'assert', as this event might be triggered few
            // times before we invoke panTo() method
            if (Math.abs(zoom - event.getZoom()) < 1e-2) {
              zoomChanged = true;
              checkIfAllOccured();
            }
          }
        });

        panorama.addInitializedHandler(new StreetviewInitializedHandler() {
          public void onInitialized(StreetviewInitializedEvent event) {
            Pov pov = Pov.newInstance();
            pov.setYaw(yaw).setPitch(pitch).setZoom(zoom);
            panorama.panTo(pov, false);
          }
        });

        RootPanel.get().add(panorama);
      }

      private void checkIfAllOccured() {
        if (pitchChanged && yawChanged && zoomChanged) {
          finishTest();
        }
      }
    }, false);
  }


  /**
   * Runs before every test method.
   */
  @Override
  protected void gwtSetUp() throws Exception {
    super.gwtSetUp();
    TestUtilities.cleanDom();
    panorama = null;
  }

  /**
   * Cleaning after test.
   */
  @Override
  protected void gwtTearDown() throws Exception {
    if (panorama != null) {
      panorama.remove();
    }
    super.gwtTearDown();
  }

  private StreetviewPanoramaWidget newDefaultPanorama() {
    LatLng tenthStreet = LatLng.newInstance(33.7814839, -84.3879353);
    StreetviewPanoramaOptions options = StreetviewPanoramaOptions.newInstance();
    options.setLatLng(tenthStreet);
    StreetviewPanoramaWidget panorama = new StreetviewPanoramaWidget(options);
    panorama.setSize("500px", "300px");
    return panorama;
  }
}

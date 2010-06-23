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
public class StreetviewEventRemoversTest extends MapsTestCase {

  /**
   * HACK: Workaround to avoid running these tests on FF. Tests including Flash
   * plugin fail on FF if screen of the tester machine is locked.
   */
  private static native boolean isFirefoxOnWindows() /*-{
    var ua = navigator.userAgent.toLowerCase();
    return (ua.indexOf("firefox") != -1) && (ua.indexOf("windows") != -1);
  }-*/;

  private static native boolean isIEOnWindows() /*-{
    var ua = navigator.userAgent.toLowerCase();
    return (ua.indexOf("msie") != -1) && (ua.indexOf("windows") != -1);
  }-*/;

  private StreetviewPanoramaWidget panorama;

  /**
   * Using different module to force browser refresh for
   * {@link StreetviewPanoramaWidget} tests.
   */
  @Override
  public String getModuleName() {
    return "com.google.gwt.maps.GoogleStreetviewEventRemoversTest";
  }

  public void testRemoveErrorHandler() {
    /**
     * HACK: Workaround to avoid running these tests on FF. Tests including
     * Flash plugin fail on FF if screen of the tester machine is locked.
     */
    if (isFirefoxOnWindows()) {
      return;
    }
    loadApi(new Runnable() {
      private int handleCount = 0;
      private boolean initialized = false;

      public void run() {
        panorama = newDefaultPanorama();

        panorama.addErrorHandler(new StreetviewErrorHandler() {
          public void onError(StreetviewErrorEvent event) {
            if (handleCount++ != 0) {
              fail("Handler used more then once");
            }

            panorama.removeErrorHandler(this);
          }
        });

        panorama.addInitializedHandler(new StreetviewInitializedHandler() {
          public void onInitialized(StreetviewInitializedEvent event) {
            initialized = true;
            panorama.trigger(new StreetviewErrorEvent(panorama, 0));
            panorama.trigger(new StreetviewErrorEvent(panorama, 0));
          }
        });

        RootPanel.get().add(panorama);

        new Timer() {
          @Override
          public void run() {
            assertEquals(
                "Expected one and only one error callback.  initialized="
                    + initialized, 1, handleCount);
            finishTest();
          }
        }.schedule(6 * 1000);
      }
    }, false);
  }

  public void testRemoveInitializedHandler() {
    /**
     * HACK: Workaround to avoid running these tests on FF. Tests including
     * Flash plugin fail on FF if screen of the tester machine is locked.
     * 
     * For some reason, the IE test is flaky anytime.
     */
    if (isFirefoxOnWindows() || isIEOnWindows()) {
      return;
    }
    loadApi(new Runnable() {
      private int handleCount = 0;
      private boolean initialized = false;

      public void run() {
        panorama = newDefaultPanorama();

        panorama.addInitializedHandler(new StreetviewInitializedHandler() {
          public void onInitialized(StreetviewInitializedEvent event) {
            initialized = true;
            if (handleCount++ != 0) {
              fail("Handler used more then once");
            }

            panorama.removeInitializedHandler(this);
            panorama.trigger(new StreetviewInitializedEvent(panorama, null));
          }
        });

        RootPanel.get().add(panorama);

        new Timer() {
          @Override
          public void run() {
            assertEquals(
                "Expected one and only one initialized callback.  initialized="
                    + initialized, 1, handleCount);
            finishTest();
          }
        }.schedule(5 * 1000);
      }
    }, false);
  }

  public void testRemovePitchChangedHandler() {
    /**
     * HACK: Workaround to avoid running these tests on FF. Tests including
     * Flash plugin fail on FF if screen of the tester machine is locked.
     */
    if (isFirefoxOnWindows() || isIEOnWindows()) {
      return;
    }
    loadApi(new Runnable() {
      private int handleCount = 0;
      private boolean initialized = false;

      public void run() {
        panorama = newDefaultPanorama();

        panorama.addPitchChangedHandler(new StreetviewPitchChangedHandler() {
          public void onPitchChanged(StreetviewPitchChangedEvent event) {
            if (handleCount++ != 0) {
              fail("Handler used more then once");
            }

            panorama.removePitchChangedHandler(this);
          }
        });

        panorama.addInitializedHandler(new StreetviewInitializedHandler() {
          public void onInitialized(StreetviewInitializedEvent event) {
            initialized = true;
            panorama.trigger(new StreetviewPitchChangedEvent(panorama, 0));
            panorama.trigger(new StreetviewPitchChangedEvent(panorama, 0));
          }
        });

        RootPanel.get().add(panorama);

        new Timer() {
          @Override
          public void run() {
            assertEquals(
                "Expected one and only one pitch changed callback.  initialized="
                    + initialized, 1, handleCount);
            finishTest();
          }
        }.schedule(6 * 1000);
      }
    }, false);
  }

  public void testRemoveYawChangedHandler() {
    /**
     * HACK: Workaround to avoid running these tests on FF. Tests including
     * Flash plugin fail on FF if screen of the tester machine is locked.
     * 
     * For some reason, the IE test is flaky anytime.
     */
    if (isFirefoxOnWindows() || isIEOnWindows()) {
      return;
    }
    loadApi(new Runnable() {
      private int handleCount = 0;
      private boolean initialized = false;

      public void run() {
        panorama = newDefaultPanorama();

        panorama.addYawChangedHandler(new StreetviewYawChangedHandler() {
          public void onYawChanged(StreetviewYawChangedEvent event) {
            if (handleCount++ != 0) {
              fail("Handler used more then once");
            }

            panorama.removeYawChangedHandler(this);
          }
        });

        panorama.addInitializedHandler(new StreetviewInitializedHandler() {
          public void onInitialized(StreetviewInitializedEvent event) {
            initialized = true;
            panorama.trigger(new StreetviewYawChangedEvent(panorama, 0));
            panorama.trigger(new StreetviewYawChangedEvent(panorama, 0));
          }
        });

        RootPanel.get().add(panorama);

        new Timer() {
          @Override
          public void run() {
            assertEquals(
                "Expected one and only one yaw changed callback.  initialized="
                    + initialized, 1, handleCount);
            finishTest();
          }
        }.schedule(6 * 1000);
      }
    }, false);
  }

  public void testRemoveZoomChangedHandler() {
    /**
     * HACK: Workaround to avoid running these tests on FF. Tests including
     * Flash plugin fail on FF if screen of the tester machine is locked.
     */
    if (isFirefoxOnWindows() || isIEOnWindows()) {
      return;
    }
    loadApi(new Runnable() {
      private int handleCount = 0;
      private boolean initialized = false;

      public void run() {
        panorama = newDefaultPanorama();

        panorama.addZoomChangedHandler(new StreetviewZoomChangedHandler() {
          public void onZoomChanged(StreetviewZoomChangedEvent event) {
            if (handleCount++ != 0) {
              fail("Handler used more then once");
            }

            panorama.removeZoomChangedHandler(this);
          }
        });

        panorama.addInitializedHandler(new StreetviewInitializedHandler() {
          public void onInitialized(StreetviewInitializedEvent event) {
            initialized = true;
            panorama.trigger(new StreetviewZoomChangedEvent(panorama, 0));
            panorama.trigger(new StreetviewZoomChangedEvent(panorama, 0));
          }
        });

        RootPanel.get().add(panorama);

        new Timer() {
          @Override
          public void run() {
            assertEquals(
                "Expected one and only one zoom changed callback.  initialized="
                    + initialized, 1, handleCount);
            finishTest();
          }
        }.schedule(6 * 1000);
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

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
package com.google.gwt.maps.client.impl;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.CopyrightCollection;
import com.google.gwt.maps.client.MapType;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.Maps;
import com.google.gwt.maps.client.MapsTestCase;
import com.google.gwt.maps.client.TileLayer;
import com.google.gwt.maps.client.control.ControlAnchor;
import com.google.gwt.maps.client.control.ControlPosition;
import com.google.gwt.maps.client.control.Control.CustomControl;
import com.google.gwt.maps.client.geocode.CustomGeocodeCache;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.MercatorProjection;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.client.geom.Projection;
import com.google.gwt.maps.client.geom.TileIndex;
import com.google.gwt.maps.client.overlay.Overlay;
import com.google.gwt.maps.client.overlay.TileLayerOverlay;
import com.google.gwt.maps.client.overlay.Overlay.ConcreteOverlay;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * This test case is used to manually test the minimum working version of the
 * Maps library. This is done by instantiating all classes backed by JSIO
 * classes that use '@Binding'
 * 
 * @Binding ./com/google/gwt/maps/client/impl/MapImpl.java:
 * @Binding ./com/google/gwt/maps/client/impl/ControlImpl.java:
 *          ./com/google/gwt/maps/client/impl/OverlayImpl.java:
 * @Binding ./com/google/gwt/maps/client/impl/TileLayerOverlayImpl.java:
 * @Binding ./com/google/gwt/maps/client/impl/ProjectionImpl.java:
 * @Binding ./com/google/gwt/maps/client/impl/ProjectionImpl.java:
 * @Binding ./com/google/gwt/maps/client/impl/MapTypeImpl.java:
 * @Binding ./com/google/gwt/maps/client/impl/IconImpl.java:
 * @Binding ./com/google/gwt/maps/client/impl/GeocodeCacheImpl.java:
 * @Binding ./com/google/gwt/maps/client/impl/TileLayerImpl.java:
 * @Binding
 */
public class MinimumMapVersionTest extends MapsTestCase {
  private static final String MAPS_MIN_VERSION = "2.147";

  private TileLayer initTileLayer() {
    TileLayer tileLayer = null;

    tileLayer = new TileLayer(new CopyrightCollection("gwt-maps Unit Test"), 1,
        20) {
      @Override
      public double getOpacity() {
        return 1.0;
      }

      @Override
      public String getTileURL(Point tile, int zoomLevel) {
        return "spots.png";
      }

      @Override
      public boolean isPng() {
        return true;
      }
    };
    return tileLayer;
  }

  private static native JavaScriptObject nativeMakeConcreteOverlay() /*-{
    return new $wnd.GMarker(new $wnd.GLatLng(45,45));
  }-*/;

  @Override
  public String getModuleName() {
    return "com.google.gwt.maps.GoogleMapsVersionTest";
  }

  public void test0isMapsLoaded() {
    loadApi(new Runnable() {
      public void run() {
        assertTrue(Maps.isLoaded());
      }
    }, true, MAPS_MIN_VERSION);
  }

  public void testConcreteOverlayImpl() {
    loadApi(new Runnable() {
      public void run() {
        @SuppressWarnings("unused")
        ConcreteOverlay concreteOverlay = new ConcreteOverlay(
            nativeMakeConcreteOverlay());
      }
    }, true, MAPS_MIN_VERSION);
  }

  public void testControl() {
    loadApi(new Runnable() {
      public void run() {
        ControlPosition pos = new ControlPosition(ControlAnchor.BOTTOM_LEFT, 0,
            0);
        @SuppressWarnings("unused")
        CustomControl c = new CustomControl(pos) {
          @Override
          public boolean isSelectable() {
            return false;
          }

          @Override
          protected Widget initialize(MapWidget map) {
            return new AbsolutePanel();
          }
        };
      }
    }, true, MAPS_MIN_VERSION);
  }

  public void testGeocodeCache() {
    loadApi(new Runnable() {
      public void run() {
        class MyGeocodeCache extends CustomGeocodeCache {
          @Override
          public JavaScriptObject get(String address) {
            JavaScriptObject result = super.get(address);
            return result;
          }

          @Override
          public void put(String address, JavaScriptObject reply) {
            super.put(address, reply);
          }

          @Override
          public String toCanonical(String address) {
            String result = super.toCanonical(address);
            return result;
          }
        }
        @SuppressWarnings("unused")
        MyGeocodeCache customGc = new MyGeocodeCache();
      }
    }, true, MAPS_MIN_VERSION);
  }

  public void testMapImpl() {
    loadApi(new Runnable() {
      public void run() {
        @SuppressWarnings("unused")
        MapWidget w = new MapWidget();
      }
    }, true, MAPS_MIN_VERSION);
  }

  public void testMapType() {
    loadApi(new Runnable() {
      public void run() {
        initTileLayer();
        TileLayer[] layers = new TileLayer[1];
        layers[0] = initTileLayer();
        @SuppressWarnings("unused")
        MapType t = new MapType(layers, new MercatorProjection(1),
            "versionTestLayer");
      }
    }, true, MAPS_MIN_VERSION);
  }

  public void testMercatorProjection() {
    loadApi(new Runnable() {
      public void run() {
        @SuppressWarnings("unused")
        MercatorProjection m = new MercatorProjection(2);
      }
    }, true, MAPS_MIN_VERSION);
  }

  public void testOverlayImpl() {
    loadApi(new Runnable() {
      public void run() {

        @SuppressWarnings("unused")
        Overlay o = new Overlay() {

          @Override
          protected Overlay copy() {
            return null;
          }

          @Override
          protected void initialize(MapWidget map) {
          }

          @Override
          protected void redraw(boolean force) {
          }

          @Override
          protected void remove() {
          }

        };
      }
    }, true, MAPS_MIN_VERSION);
  }

  public void testProjection() {
    loadApi(new Runnable() {
      public void run() {
        initTileLayer();
        @SuppressWarnings("unused")
        Projection projection = new Projection() {

          @Override
          public Point fromLatLngToPixel(LatLng latlng, int zoomLevel) {
            return Point.newInstance(0, 0);
          }

          @Override
          public LatLng fromPixelToLatLng(Point point, int zoomLevel,
              boolean unbounded) {
            return LatLng.newInstance(0, 0);
          }

          @Override
          public double getWrapWidth(int zoomLevel) {
            return 1.0;
          }

          @Override
          public boolean tileCheckRange(TileIndex index, int zoomLevel,
              int tileSize) {
            return true;
          }
        };
      }
    }, true, MAPS_MIN_VERSION);
  }

  public void testTileLayerOverlay() {
    loadApi(new Runnable() {
      public void run() {
        initTileLayer();
        @SuppressWarnings("unused")
        TileLayerOverlay overlay = new TileLayerOverlay(initTileLayer());
      }
    }, true, MAPS_MIN_VERSION);
  }
}

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
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.maps.client.CopyrightCollection;
import com.google.gwt.maps.client.MapType;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.Maps;
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
public class MinimumMapVersionTest extends GWTTestCase {
  static TileLayer tileLayer = null;

  private static void initTileLayer() {
    if (tileLayer != null) {
      return;
    }

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
  }

  private static native JavaScriptObject nativeMakeConcreteOverlay() /*-{
      return new $wnd.GMarker(new $wnd.GLatLng(45,45));
    }-*/;

  @Override
  public String getModuleName() {
    return "com.google.gwt.maps.GoogleMapsVersionTest";
  }

  public void test0isMapsLoaded() {
    assertTrue(Maps.isLoaded());
  }

  public void testConcreteOverlayImpl() {
    ConcreteOverlay concreteOverlay = new ConcreteOverlay(
        nativeMakeConcreteOverlay());
  }

  public void testControl() {
    ControlPosition pos = new ControlPosition(ControlAnchor.BOTTOM_LEFT, 0, 0);
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

  public void testGeocodeCache() {
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
    MyGeocodeCache customGc = new MyGeocodeCache();
  }

  public void testMapImpl() {
    MapWidget w = new MapWidget();
  }

  public void testMapType() {
    initTileLayer();
    TileLayer[] layers = new TileLayer[1];
    layers[0] = tileLayer;
    MapType t = new MapType(layers, new MercatorProjection(1),
        "versionTestLayer");
  }

  public void testMercatorProjection() {
    MercatorProjection m = new MercatorProjection(2);
  }

  public void testOverlayImpl() {
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

  public void testProjection() {
    initTileLayer();
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
      public boolean tileCheckRange(TileIndex index, int zoomLevel, int tileSize) {
        return true;
      }
    };
  }

  public void testTileLayerOverlay() {
    initTileLayer();
    TileLayerOverlay overlay = new TileLayerOverlay(tileLayer);
  }

}

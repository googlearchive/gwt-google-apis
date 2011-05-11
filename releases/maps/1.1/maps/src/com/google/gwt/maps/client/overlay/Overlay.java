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
package com.google.gwt.maps.client.overlay;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.impl.OverlayImpl;
import com.google.gwt.maps.jsio.client.Exported;

/**
 * The base class for adding objects at a specific position on top of the map.
 */
public abstract class Overlay {
  /**
   * This class is used to wrap Overlays written entirely in JavaScript.
   * 
   * Note that the "Overlay" class is intended to be a superclass, and thus,
   * implements its methods on the prototype of the GOverlay object.
   * ConcreteOverlay is intended to be used when you don't want to subclass
   * Overlay but use it directly.
   */
  public static class ConcreteOverlay extends Overlay {
    public ConcreteOverlay(JavaScriptObject jsoPeer) {
      super(jsoPeer);
      OverlayImpl.impl.bindConcreteOverlay(jsoPeer, this);
    }

    @Override
    protected final Overlay copy() {
      return OverlayImpl.impl.copy(super.jsoPeer);
    }

    @Override
    protected final void initialize(MapWidget map) {
      OverlayImpl.impl.initialize(super.jsoPeer, map);
    }

    @Override
    protected final void redraw(boolean force) {
      OverlayImpl.impl.redraw(super.jsoPeer, force);
    }

    @Override
    protected final void remove() {
      OverlayImpl.impl.remove(super.jsoPeer);
    }
  }
  
  // internal ref 1431785
  // Delayed loading of parts of the Maps API fools the instanceof test
  private static boolean useDuckTypes = nativeCmpGeoXmltoGroundOverlay();

  /**
   * Used to create a new Overlay by wrapping an existing GOverlay object. This
   * method is invoked by the jsio library.
   * 
   * @param jsoPeer GOverlay object to wrap.
   * @return a new instance of Overlay.
   */
  public static Overlay createPeer(JavaScriptObject jsoPeer) {
    
    if (nativeIsMarker(jsoPeer)) {
      return new Marker(jsoPeer);
    } else if (nativeIsStreetviewOverlay(jsoPeer)) {
      return new StreetviewOverlay(jsoPeer);
    } else if (nativeIsTileLayerOverlay(jsoPeer)) {
      return new TileLayerOverlay(jsoPeer);
    }
    
    if (useDuckTypes) {
        // Now, perform DuckType tests.  internal ref 1431785
      if (nativeIsPolylineDuck(jsoPeer)) {
        return new Polyline(jsoPeer);
      } else if (nativeIsTrafficOverlayDuck(jsoPeer)) {
        return new TrafficOverlay(jsoPeer);
      } else if (nativeIsPolygonDuck(jsoPeer)) {
        return new Polygon(jsoPeer);
      } else if (nativeIsGeoXmlDuck(jsoPeer)) {
        return new GeoXmlOverlay(jsoPeer);
      } else if (nativeIsGroundOverlayDuck(jsoPeer)) {
        return new GroundOverlay(jsoPeer);
      } else if (nativeIsStreetviewOverlayDuck(jsoPeer)) {
        return new StreetviewOverlay(jsoPeer);
      }
    } else {  
      if (nativeIsGeoXml(jsoPeer)) {
        return new GeoXmlOverlay(jsoPeer);        
      } else if (nativeIsTrafficOverlay(jsoPeer)) {
        return new TrafficOverlay(jsoPeer);
      } else if (nativeIsGroundOverlay(jsoPeer)) {
        return new GroundOverlay(jsoPeer);
      } else if (nativeIsPolyline(jsoPeer)) {
        return new Polyline(jsoPeer);
      } else if (nativeIsPolygon(jsoPeer)) {
        return new Polygon(jsoPeer);
      } else if (nativeIsLayer(jsoPeer)) {
        return new Layer(jsoPeer);
      }
    }
    
    if (nativeIsInfoWindow(jsoPeer)) {
      // We should never get to this code as MapWidget calls getInfoWindow()
      // in its constructor, so all InfoWindow instances should already be
      // bound.
      throw new UnsupportedOperationException(
          "Can't create InfoWindow object from JavaScriptObject.");
    }
    return new ConcreteOverlay(jsoPeer);
  }

  /**
   * Returns a CSS z-index value for a given latitude. It computes a z index
   * such that overlays further south are on top of overlays further north, thus
   * creating the 3D appearance of marker overlays.
   * 
   * @param latitude the latitude to retrieve the marker z-index for.
   * @return a CSS z-index value
   */
  public static native int getZIndex(double latitude) /*-{
    return $wnd.GOverlay.getZIndex(latitude);
  }-*/;

  /**
   * Workaround for instanceof test failing in JavaScript for some overlay
   * types. JS Maps API bug: internal ref 1431785
   * 
   * @param jsoPeer the object being tested.
   * @param prototype the prototype of the type to compare it to.
   * @return true if all properties in the prototype are found in the object's
   *         prototype.
   */
  @SuppressWarnings("unused")
  private static native boolean isSameDuckType(JavaScriptObject jsoPeer,
      JavaScriptObject prototype) /*-{
    for (var prop in prototype) {
        if (!(prop in jsoPeer)) {
          return false;
        }
       }
       return true;
  }-*/;

  /*
   * This odd test checks to see if the instanceof test is working properly. 
   * If this returns <code>true</code>, the test is faulty and a workaround must
   * be used.
   * 
   * internal ref 1431785
   */
  private static native boolean nativeCmpGeoXmltoGroundOverlay() /*-{
    var tmp = new $wnd.GGroundOverlay();
    return tmp instanceof $wnd.GGeoXml;
  }-*/;

  private static native boolean nativeIsGeoXml(JavaScriptObject jsoPeer) /*-{
    return (jsoPeer instanceof $wnd.GGeoXml);
  }-*/;

  private static native boolean nativeIsGeoXmlDuck(JavaScriptObject jsoPeer) /*-{
    // JS Maps API bug: internal ref 1431785
    // Use a duck type test
    return @com.google.gwt.maps.client.overlay.Overlay::isSameDuckType(Lcom/google/gwt/core/client/JavaScriptObject;Lcom/google/gwt/core/client/JavaScriptObject;)(jsoPeer, $wnd.GGeoXml.prototype);
  }-*/;

  private static native boolean nativeIsGroundOverlay(JavaScriptObject jsoPeer) /*-{
    return (jsoPeer instanceof $wnd.GGroundOverlay);
  }-*/;

  private static native boolean nativeIsGroundOverlayDuck(
      JavaScriptObject jsoPeer) /*-{
    // JS Maps API bug: internal ref 1431785
    // Use a duck type test
    return @com.google.gwt.maps.client.overlay.Overlay::isSameDuckType(Lcom/google/gwt/core/client/JavaScriptObject;Lcom/google/gwt/core/client/JavaScriptObject;)(jsoPeer, $wnd.GGroundOverlay.prototype);
  }-*/;

  private static native boolean nativeIsInfoWindow(JavaScriptObject jsoPeer) /*-{
    // The instanceof test won't work, because $wnd.GInfoWindow has no constructor.
    // Let's see if it quacks like a duck (has similar member functions)...
    if (!jsoPeer.selectTab || !jsoPeer.getTabs || !jsoPeer.getPixelOffset) {
      return false;
    }  
    return true;
  }-*/;

  private static native  boolean nativeIsLayer(JavaScriptObject jsoPeer) /*-{
    return (jsoPeer instanceof $wnd.GLayer);
  }-*/;

  private static native boolean nativeIsMarker(JavaScriptObject jsoPeer) /*-{
    return (jsoPeer instanceof $wnd.GMarker);
  }-*/;

  private static native boolean nativeIsPolygon(JavaScriptObject jsoPeer) /*-{
    return (jsoPeer instanceof $wnd.GPolygon);
  }-*/;
  
  private static native boolean nativeIsPolygonDuck(JavaScriptObject jsoPeer) /*-{
    // JS Maps API bug: internal ref 1431785
    // Use a duck type test
    return @com.google.gwt.maps.client.overlay.Overlay::isSameDuckType(Lcom/google/gwt/core/client/JavaScriptObject;Lcom/google/gwt/core/client/JavaScriptObject;)(jsoPeer, $wnd.GPolygon.prototype);
    
  }-*/;  

  private static native boolean nativeIsPolyline(JavaScriptObject jsoPeer) /*-{
    return (jsoPeer instanceof $wnd.GPolyline);
  }-*/;

  private static native boolean nativeIsPolylineDuck(JavaScriptObject jsoPeer) /*-{
    // JS Maps API bug: internal ref 1431785
    // Use a duck type test
    return @com.google.gwt.maps.client.overlay.Overlay::isSameDuckType(Lcom/google/gwt/core/client/JavaScriptObject;Lcom/google/gwt/core/client/JavaScriptObject;)(jsoPeer, $wnd.GPolyline.prototype);
  }-*/;

  private static native boolean nativeIsStreetviewOverlay(
      JavaScriptObject jsoPeer) /*-{
    return (jsoPeer instanceof $wnd.GStreetviewOverlay);
  }-*/;

  private static native boolean nativeIsStreetviewOverlayDuck(
      JavaScriptObject jsoPeer) /*-{
    // JS Maps API bug: internal ref 1431785
    // Use a duck type test
    return @com.google.gwt.maps.client.overlay.Overlay::isSameDuckType(Lcom/google/gwt/core/client/JavaScriptObject;Lcom/google/gwt/core/client/JavaScriptObject;)(jsoPeer, $wnd.GStreetviewOverlay.prototype);
  }-*/;

  private static native boolean nativeIsTileLayerOverlay(
      JavaScriptObject jsoPeer) /*-{
    return (jsoPeer instanceof $wnd.GTileLayerOverlay);
  }-*/;

  private static native boolean nativeIsTrafficOverlay(JavaScriptObject jsoPeer) /*-{
    return (jsoPeer instanceof $wnd.GTrafficOverlay);
  }-*/;

  private static native boolean nativeIsTrafficOverlayDuck(
      JavaScriptObject jsoPeer) /*-{
    // JS Maps API bug: internal ref 1431785
    // Use a duck type test
    return @com.google.gwt.maps.client.overlay.Overlay::isSameDuckType(Lcom/google/gwt/core/client/JavaScriptObject;Lcom/google/gwt/core/client/JavaScriptObject;)(jsoPeer, $wnd.GTrafficOverlay.prototype);
  }-*/;

  protected final JavaScriptObject jsoPeer;

  public Overlay() {
    jsoPeer = OverlayImpl.impl.constructOverlay();
    OverlayImpl.impl.bindOverlay(jsoPeer, this);
  }

  protected Overlay(JavaScriptObject jsoPeer) {
    this.jsoPeer = jsoPeer;
  }

  /**
   * Returns an uninitialized copy of itself that can be added to the map.
   * 
   * @return an uninitialized copy of itself that can be added to the map.
   */
  @Exported
  protected abstract Overlay copy();

  /**
   * Called by the map after the overlay is added to the map using
   * {@link MapWidget#addOverlay(Overlay)}. The overlay object can draw itself
   * into the different panes of the map that can be obtained using
   * {@link MapWidget#getPane(com.google.gwt.maps.client.MapPaneType)}.
   * 
   * @param map The map this overlay has been added to.
   */
  @Exported
  protected abstract void initialize(MapWidget map);

  /**
   * Called by the map when the map display has changed.
   * 
   * @param force The argument force will be true if the zoom level or the pixel
   *          offset of the map view has changed, so that the pixel coordinates
   *          need to be recomputed.
   */
  @Exported
  protected abstract void redraw(boolean force);

  /**
   * Called by the map after the overlay is removed from the map using
   * {@link MapWidget#removeOverlay(Overlay)} or
   * {@link MapWidget#clearOverlays()}. The overlay must remove itself from the
   * map panes here.
   */
  @Exported
  protected abstract void remove();
}

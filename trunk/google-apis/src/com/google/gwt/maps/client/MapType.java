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
package com.google.gwt.maps.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.jsio.client.JSList;
import com.google.gwt.jsio.client.impl.Extractor;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.LatLngBounds;
import com.google.gwt.maps.client.geom.Projection;
import com.google.gwt.maps.client.geom.Size;
import com.google.gwt.maps.client.impl.MapTypeImpl;
import com.google.gwt.maps.client.util.JsUtil;

/**
 * A MapType is a sequence of tile layers, a map projection, a tile size, and
 * assorted other settings, link colors, and copyrights.
 * 
 * There are three predefine map types: {@link MapType#normalMap},
 * {@link MapType#satelliteMap}, and {@link MapType#hybridMap}.
 * 
 * You can create instances of this class to define custom map types. In order
 * to show them on the map, use the {@link MapWidget#addMapType(MapType)}
 * method. You can use
 * {@link com.google.gwt.maps.client.overlay.TileLayerOverlay TileLayerOverlay}
 * to add to (rather than entirely replace) the map's tile layers.
 * 
 * @see MapWidget#addMapType(MapType)
 * @see com.google.gwt.maps.client.overlay.TileLayerOverlay
 */
public final class MapType {

  // TODO: DELETE ME! (needs to function w/o)
  private static final Extractor __extractor = new Extractor() {
    public Object fromJS(JavaScriptObject jso) {
      return new MapType(jso);
    }

    public JavaScriptObject toJS(Object o) {
      return ((MapType) o).jsoPeer;
    }
  };

  private static MapType[] defaultMapTypes;
  private static MapType hybridMap;
  private static boolean mapTypesInitialized = false;
  private static MapType marsElevationMap;
  private static MapType marsInfraredMap;
  private static MapType[] marsMapTypes;
  private static MapType marsVisibleMap;
  private static MapType moonElevationMap;
  private static MapType[] moonMapTypes;
  private static MapType moonVisibleMap;
  private static MapType normalMap;
  private static MapType physicalMap;
  private static MapType satelliteMap;
  private static MapType[] skyMapTypes;

  private static MapType skyVisibleMap;

  /**
   * Returns G_DEFAULT_MAP_TYPES as an Java Array of MapType objects.
   * 
   * @return an array of MapType objects
   */
  public static MapType[] getDefaultMapTypes() {
    if (defaultMapTypes != null) {
      return defaultMapTypes;
    }
    initMapTypes();
    int size = getDefaultMapTypesSize();
    defaultMapTypes = new MapType[size];
    for (int i = 0; i < defaultMapTypes.length; i++) {
      defaultMapTypes[i] = getDefaultMapType(i);
    }
    return defaultMapTypes;
  }

  /**
   * @return a map type that shows transparent street maps over Google Earth
   *         satellite images.
   */
  public static MapType getHybridMap() {
    initMapTypes();
    return hybridMap;
  }

  /**
   * @return a map type displays a shaded relief map of the surface of Mars,
   *         color-coded by altitude. This map type is not displayed within map
   *         type controls by default. (Since 2.95)
   */
  public static MapType getMarsElevationMap() {
    initMapTypes();
    return marsElevationMap;
  }

  /**
   * @return a map type displays a shaded infrared map of the surface of Mars,
   *         where warmer areas appear brighter and colder areas appear darker.
   *         (Since 2.95)
   */
  public static MapType getMarsInfraredMap() {
    initMapTypes();
    return marsInfraredMap;
  }

  /**
   * Turns G_MARS_MAP_TYPES into a Java Array of MapType objects.
   * 
   * @return an array of MapType objects
   */
  public static MapType[] getMarsMapTypes() {
    if (marsMapTypes != null) {
      return marsMapTypes;
    }
    initMapTypes();
    int size = getMarsMapTypesSize();
    marsMapTypes = new MapType[size];
    for (int i = 0; i < marsMapTypes.length; i++) {
      marsMapTypes[i] = getMarsMapType(i);
    }
    return marsMapTypes;
  }

  /**
   * @return a map type that displays photographs taken from orbit around Mars.
   *         This map type is not displayed within map type controls by default.
   *         (Since 2.95)
   */
  public static MapType getMarsVisibleMap() {
    initMapTypes();
    return marsVisibleMap;
  }

  /**
   * @return a map type that displays a shaded terrain map of the surface of the
   *         Moon, color-coded by altitude. This map type is not displayed
   *         within map type controls by default. (Since 2.95)
   */
  public static MapType getMoonElevationMap() {
    initMapTypes();
    return moonElevationMap;
  }

  /**
   * Turns G_MOON_MAP_TYPES into a Java Array of MapType objects.
   * 
   * @return an array of MapType objects
   */
  public static MapType[] getMoonMapTypes() {
    if (moonMapTypes != null) {
      return moonMapTypes;
    }
    initMapTypes();
    int size = getMoonMapTypesSize();
    moonMapTypes = new MapType[size];
    for (int i = 0; i < moonMapTypes.length; i++) {
      moonMapTypes[i] = getMoonMapType(i);
    }
    return moonMapTypes;
  }

  /**
   * @return a map type that displays photographs taken from orbit around the
   *         moon. This map type is not displayed within map type controls by
   *         default. (Since 2.95)
   */
  public static MapType getMoonVisibleMap() {
    initMapTypes();
    return moonVisibleMap;
  }

  /**
   * @return the normal street map type.
   */
  public static MapType getNormalMap() {
    initMapTypes();
    return normalMap;
  }

  /**
   * @return a map type that shows a terrain view.
   */
  public static MapType getPhysicalMap() {
    initMapTypes();
    return physicalMap;
  }

  /**
   * @return a map type that shows Google Earth satellite images.
   */
  public static MapType getSatelliteMap() {
    initMapTypes();
    return satelliteMap;
  }

  /**
   * Turns G_SKY_MAP_TYPES into a Java Array of MapType objects.
   * 
   * @return an array of MapType objects
   */
  public static MapType[] getSkyMapTypes() {
    if (skyMapTypes != null) {
      return skyMapTypes;
    }
    initMapTypes();
    int size = getSkyMapTypesSize();
    skyMapTypes = new MapType[size];
    for (int i = 0; i < skyMapTypes.length; i++) {
      skyMapTypes[i] = getSkyMapType(i);
    }
    return skyMapTypes;
  }

  /**
   * @return a map type shows a mosaic of the sky, covering the full celestial
   *         sphere. (Since 2.95)
   */
  public static MapType getSkyVisibleMap() {
    initMapTypes();
    return skyVisibleMap;
  }

  /**
   * @param jsoPeer a JavaScriptObject to wrap with a Java object.
   * @return A Java object wrapper for the jsoPeer passed.
   */
  private static MapType createPeer(JavaScriptObject jsoPeer) {
    MapType mapType = new MapType(jsoPeer);
    MapTypeImpl.impl.bind(jsoPeer, mapType);
    return mapType;
  }

  /**
   * Return one member of the G_DEFAULT_MAP_TYPES array. If it has already been
   * wrapped, return the wrapped object. Otherwise, wrap the object.
   * 
   * @param i index into the array
   * @return an element of the array as a Java object
   */
  private static native MapType getDefaultMapType(int i) /*-{
     var o = $wnd.G_DEFAULT_MAP_TYPES[i];
     if (o.__gwtPeer) {
        // Avoid double wrapping the object.
        return o.__gwtPeer;
     } else {
       return @com.google.gwt.maps.client.MapType::createPeer(Lcom/google/gwt/core/client/JavaScriptObject;)(o);
     }
  }-*/;

  /**
   * @return the length of the G_DEFAULT_MAP_TYPES array.
   */
  private static native int getDefaultMapTypesSize() /*-{
     return $wnd.G_DEFAULT_MAP_TYPES.length;
   }-*/;

  /**
   * Return one member of the G_MARS_MAP_TYPES array. If it has already been
   * wrapped, return the wrapped object. Otherwise, wrap the object.
   * 
   * @param i index into the array
   * @return an element of the array as a Java object
   */
  private static native MapType getMarsMapType(int i) /*-{
     var o = $wnd.G_MARS_MAP_TYPES[i];
     if (o.__gwtPeer) {
        // Avoid double wrapping the object.
        return o.__gwtPeer;
      } else {
        return @com.google.gwt.maps.client.MapType::createPeer(Lcom/google/gwt/core/client/JavaScriptObject;)(o);
      }
   }-*/;

  /**
   * @return the length of the MARS_MAP_TYPES array.
   */
  private static native int getMarsMapTypesSize() /*-{
     return $wnd.G_MARS_MAP_TYPES.length;
   }-*/;

  /**
   * Return one member of the G_MOON_MAP_TYPES array. If it has already been
   * wrapped, return the wrapped object. Otherwise, wrap the object.
   * 
   * @param i index into the array
   * @return an element of the array as a Java object
   */
  private static native MapType getMoonMapType(int i) /*-{
      var o = $wnd.G_MOON_MAP_TYPES[i];
      if (o.__gwtPeer) {
         // Avoid double wrapping the object.
         return o.__gwtPeer;
      } else {
        return @com.google.gwt.maps.client.MapType::createPeer(Lcom/google/gwt/core/client/JavaScriptObject;)(o);
      }
   }-*/;

  /**
   * @return the length of the MOON_MAP_TYPES array.
   */
  private static native int getMoonMapTypesSize() /*-{
     return $wnd.G_MOON_MAP_TYPES.length;
   }-*/;

  /**
   * Return one member of the G_SKY_MAP_TYPES array. If it has already been
   * wrapped, return the wrapped object. Otherwise, wrap the object.
   * 
   * @param i index into the array
   * @return an element of the array as a Java object
   */
  private static native MapType getSkyMapType(int i) /*-{
      var o = $wnd.G_SKY_MAP_TYPES[i];
      if (o.__gwtPeer) {
         // Avoid double wrapping the object.
         return o.__gwtPeer;
      } else {
        return @com.google.gwt.maps.client.MapType::createPeer(Lcom/google/gwt/core/client/JavaScriptObject;)(o);
      }
   }-*/;

  /**
   * @return the length of the SKY_MAP_TYPES array.
   */
  private static native int getSkyMapTypesSize() /*-{
     return $wnd.G_SKY_MAP_TYPES.length;
   }-*/;

  private static void initMapTypes() {
    if (mapTypesInitialized) {
      return;
    }

    normalMap = createPeer(MapTypeImpl.impl.getNormalMapType());
    satelliteMap = createPeer(MapTypeImpl.impl.getSatelliteMapType());
    hybridMap = createPeer(MapTypeImpl.impl.getHybridMapType());
    physicalMap = createPeer(MapTypeImpl.impl.getPhysicalMapType());
    moonElevationMap = createPeer(MapTypeImpl.impl.getMoonElevationMapType());
    moonVisibleMap = createPeer(MapTypeImpl.impl.getMoonVisibleMap());
    marsElevationMap = createPeer(MapTypeImpl.impl.getMarsElevationMap());
    marsVisibleMap = createPeer(MapTypeImpl.impl.getMarsVisibleMap());
    marsInfraredMap = createPeer(MapTypeImpl.impl.getMarsInfraredMap());
    skyVisibleMap = createPeer(MapTypeImpl.impl.getSkyVisibleMap());
    mapTypesInitialized = true;
  }

  private final JavaScriptObject jsoPeer;

  /**
   * Creates a new custom map type from the tile layers, projection, and name.
   * 
   * @param layers the map type's tile layers
   * @param projection the projection to be used for this map type
   * @param name the name of the map type
   */
  public MapType(TileLayer[] layers, Projection projection, String name) {
    jsoPeer = MapTypeImpl.impl.construct(JsUtil.toJsList(layers), projection, name);
    MapTypeImpl.impl.bind(jsoPeer, this);
  }

  /**
   * Creates a new custom map type from the tile layers, projection, name, and
   * options.
   * 
   * @param layers the map type's tile layers
   * @param projection the projection to be used for this map type
   * @param name the name of the map type
   * @param options options for this map type
   */
  public MapType(TileLayer[] layers, Projection projection, String name,
      MapTypeOptions options) {
    jsoPeer = MapTypeImpl.impl.construct(JsUtil.toJsList(layers), projection, name, options);
    MapTypeImpl.impl.bind(jsoPeer, this);
  }

  private MapType(JavaScriptObject jsoPeer) {
    this.jsoPeer = jsoPeer;
  }

  /**
   * Returns the highest resolution zoom level that shows show the given
   * geographical bounds in a map of the given pixel size.
   * 
   * @param bounds the bounds to show
   * @param viewSize the size of the viewport in pixels
   * @return the zoom level required to show the given bounds
   */
  public int getBoundsZoomLevel(LatLngBounds bounds, Size viewSize) {
    return MapTypeImpl.impl.getBoundsZoomLevel(jsoPeer, bounds, viewSize);
  }

  /**
   * Returns the copyrights for the given bounds and zoom level.
   * 
   * @param bounds the current viewport
   * @param zoomLevel the current zoom level
   * @return the copyrights corresponding to the given viewport
   */
  public String[] getCopyrights(LatLngBounds bounds, int zoomLevel) {
    JSList<String> copyrights = MapTypeImpl.impl.getCopyrights(jsoPeer, bounds, zoomLevel);
    String[] returnValue = new String[copyrights.size()];
    JsUtil.toArray(copyrights, returnValue);
    return returnValue;
  }

  /**
   * Returns the error message that is displayed in areas and zoom levels where
   * this map type does not have map tiles.
   * 
   * @return the error message for empty tiles
   */
  public String getErrorMessage() {
    return MapTypeImpl.impl.getErrorMessage(jsoPeer);
  }

  /**
   * Returns the color that used for a hyperlinks displayed on the map.
   * 
   * @return the color used for hyperlinks
   */
  public String getLinkColor() {
    return MapTypeImpl.impl.getLinkColor(jsoPeer);
  }

  /**
   * Returns the highest zoom level at which this map type is defined for a
   * given point.
   * 
   * @return the highest zoom level for the point
   */
  public int getMaximumResolution() {
    return MapTypeImpl.impl.getMaximumResolution(jsoPeer);
  }

  /**
   * Returns the highest zoom level at which this map type is defined for a
   * given point.
   * 
   * @param latlng the point at which to find the maximum resolution
   * @return the highest zoom level for the point
   */
  public int getMaximumResolution(LatLng latlng) {
    return MapTypeImpl.impl.getMaximumResolution(jsoPeer, latlng);
  }

  /**
   * Returns the lowest zoom level at which this map type is defined for a given
   * point.
   * 
   * @return the lowest zoom level for the point
   */
  public int getMinimumResolution() {
    return MapTypeImpl.impl.getMinimumResolution(jsoPeer);
  }

  /**
   * Returns the lowest zoom level at which this map type is defined for a given
   * point.
   * 
   * @param latlng the point at which to find the minimum resolution
   * @return the lowest zoom level for the point
   */
  public int getMinimumResolution(LatLng latlng) {
    return MapTypeImpl.impl.getMinimumResolution(jsoPeer, latlng);
  }

  /**
   * Returns the translated, human-readable string name of the map type.
   * 
   * The name is used as the button label in the
   * {@link com.google.gwt.maps.client.control.MapTypeControl MapTypeControl}.
   * 
   * @param shortName true to return the abbreviated name
   * @return the name of the map type
   */
  public String getName(boolean shortName) {
    return MapTypeImpl.impl.getName(jsoPeer, shortName);
  }

  /**
   * Returns the projection used by this map type.
   * 
   * @return the projection used by this map type
   */
  public Projection getProjection() {
    return MapTypeImpl.impl.getProjection(jsoPeer);
  }

  /**
   * Returns the highest resolution zoom level required to show the given span
   * with the given center point.
   * 
   * @TODO example, better descriptions
   * 
   * @param center the center of the viewport
   * @param span the span of the viewport
   * @param viewSize the size of the viewport in pixels
   * @return the highest possible zoom level
   */
  public int getSpanZoomLevel(LatLng center, LatLng span, Size viewSize) {
    return MapTypeImpl.impl.getSpanZoomLevel(jsoPeer, center, span, viewSize);
  }

  /**
   * Returns the color used for text displayed on the map.
   * 
   * @return the color used for text displayed on the map
   */
  public String getTextColor() {
    return MapTypeImpl.impl.getTextColor(jsoPeer);
  }

  /**
   * Returns the array of tile layers in the z-order they should be displayed.
   * 
   * @return the tile layers
   */
  public TileLayer[] getTileLayers() {
    JSList<TileLayer> layers = MapTypeImpl.impl.getTileLayers(jsoPeer);
    TileLayer[] returnValue = new TileLayer[layers.size()];
    JsUtil.toArray(layers, returnValue);
    return returnValue;
  }

  /**
   * Returns the tile size of this map's tile layers.
   * 
   * @return the size of the tiles
   */
  public int getTileSize() {
    return MapTypeImpl.impl.getTileSize(jsoPeer);
  }

  /**
   * Returns the URL argument used to refer to this map type.
   * 
   * The URL argument is used as a URL parameter value to identify this map type
   * in permalinks to the current map view.
   * 
   * @return the URL argument used to refer to this map type
   */
  public String getUrlArg() {
    return MapTypeImpl.impl.getUrlArg(jsoPeer);
  }

}

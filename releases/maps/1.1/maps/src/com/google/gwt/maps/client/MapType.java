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
import com.google.gwt.maps.client.event.MapTypeNewCopyrightHandler;
import com.google.gwt.maps.client.event.MapTypeNewCopyrightHandler.MapTypeNewCopyrightEvent;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.LatLngBounds;
import com.google.gwt.maps.client.geom.Projection;
import com.google.gwt.maps.client.geom.Size;
import com.google.gwt.maps.client.impl.HandlerCollection;
import com.google.gwt.maps.client.impl.JsUtil;
import com.google.gwt.maps.client.impl.MapEvent;
import com.google.gwt.maps.client.impl.MapTypeImpl;
import com.google.gwt.maps.client.impl.EventImpl.CopyrightCallback;
import com.google.gwt.maps.jsio.client.JSList;
import com.google.gwt.maps.jsio.client.impl.Extractor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
  @SuppressWarnings("unused")
  private static final Extractor<MapType> __extractor = new Extractor<MapType>() {
    public MapType fromJS(JavaScriptObject jso) {
      return new MapType(jso);
    }

    public JavaScriptObject toJS(MapType o) {
      return o.jsoPeer;
    }
  };

  private static MapType aerialHybridMap;
  private static MapType aerialMap;
  private static List<MapType> defaultMapTypes;
  private static MapType earthMap;
  private static MapType hybridMap;
  private static MapType mapmakerHybridMap;
  private static MapType mapmakerNormalMap;
  private static List<MapType> mapmakerMapTypes;
  private static boolean mapTypesInitialized = false;
  private static MapType marsElevationMap;
  private static MapType marsInfraredMap;
  private static List<MapType> marsMapTypes;
  private static MapType marsVisibleMap;
  private static MapType moonElevationMap;
  private static List<MapType> moonMapTypes;
  private static MapType moonVisibleMap;
  private static MapType normalMap;
  private static MapType physicalMap;
  private static MapType satelliteMap;
  private static List<MapType> skyMapTypes;
  private static MapType skyVisibleMap;

  /**
   * This map type displays a transparent layer of major streets on top of
   * aerial imagery.
   * 
   * @return a map type that displays a transparent layer of major streets on
   *         top of aerial imagery.
   */
  public static MapType getAerialHybridMap() {
    initMapTypes();
    return aerialHybridMap;
  }

  /**
   * This map type displays a transparent layer of major streets on satellite
   * images.
   * 
   * @return A map type that displays a transparent layer of major streets on
   *         satellite images.
   */
  public static MapType getAerialMap() {
    initMapTypes();
    return aerialMap;
  }

  /**
   * Returns G_DEFAULT_MAP_TYPES as an Java Array of MapType objects.
   * 
   * @return an immutable list of MapType objects.
   */
  public static List<MapType> getDefaultMapTypes() {
    if (defaultMapTypes != null) {
      return defaultMapTypes;
    }
    initMapTypes();
    int size = getDefaultMapTypesSize();
    List<MapType> l = new ArrayList<MapType>();
    for (int i = 0; i < size; i++) {
      l.add(getDefaultMapType(i));
    }
    defaultMapTypes = Collections.unmodifiableList(l);
    return defaultMapTypes;
  }

  /**
   * Returns a map type that shows Google Earth using the browser plugin.
   * 
   * @return a map type that shows Google Earth.
   */
  public static MapType getEarthMap() {
    initMapTypes();
    return earthMap;
  }

  /**
   * Returns a map type that shows transparent street maps over Google Earth
   * satellite images.
   * 
   * @return a map type that shows transparent street maps over Google Earth
   *         satellite images.
   */
  public static MapType getHybridMap() {
    initMapTypes();
    return hybridMap;
  }

  /**
   * This map type displays a transparent layer of major streets created using
   * Google Mapmaker on satellite images.
   * 
   * Note: When you use the Mapmaker maptype, users will only see maps in
   * countries where Google Map Maker is launched.
   * 
   * @return a map type that displays a transparent layer of major streets
   *         created using Google Mapmaker on satellite images.
   */
  public static MapType getMapmakerHybridMap() {
    initMapTypes();
    return mapmakerHybridMap;
  }

  /**
   * This map type displays a street map with tiles created using Google
   * Mapmaker.
   * 
   * Note: When you use a Mapmaker map type, users will only see maps in
   * countries where Google Map Maker is launched.
   * 
   * @return A map type that displays a street map with tiles created using
   *         Google Mapmaker.
   */
  public static MapType getMapmakerNormalMap() {
    initMapTypes();
    return mapmakerNormalMap;
  }

  /**
   * Turns G_MAPMAKER_MAP_TYPES into an immutable of MapType objects.
   * 
   * @return an immutable list of MapType objects.
   */
  public static List<MapType> getMapmakerMapTypes() {
    if (mapmakerMapTypes != null) {
      return mapmakerMapTypes;
    }
    initMapTypes();
    int size = getMapmakerMapTypesSize();
    List<MapType> l = new ArrayList<MapType>();
    for (int i = 0; i < size; i++) {
      l.add(getMapmakerMapType(i));
    }
    mapmakerMapTypes = Collections.unmodifiableList(l);
    return mapmakerMapTypes;
  }

  /**
   * Returns a map type displays a shaded relief map of the surface of Mars.
   * 
   * @return a map type displays a shaded relief map of the surface of Mars,
   *         color-coded by altitude. This map type is not displayed within map
   *         type controls by default. (Since 2.95)
   */
  public static MapType getMarsElevationMap() {
    initMapTypes();
    return marsElevationMap;
  }

  /**
   * Returns a map type displays a shaded infrared map of the surface of Mars.
   * 
   * @return a map type displays a shaded infrared map of the surface of Mars,
   *         where warmer areas appear brighter and colder areas appear darker.
   *         (Since 2.95)
   */
  public static MapType getMarsInfraredMap() {
    initMapTypes();
    return marsInfraredMap;
  }

  /**
   * Turns G_MARS_MAP_TYPES into an immutable of MapType objects.
   * 
   * @return an immutable list of MapType objects.
   */
  public static List<MapType> getMarsMapTypes() {
    if (marsMapTypes != null) {
      return marsMapTypes;
    }
    initMapTypes();
    int size = getMarsMapTypesSize();
    List<MapType> l = new ArrayList<MapType>();
    for (int i = 0; i < size; i++) {
      l.add(getMarsMapType(i));
    }
    marsMapTypes = Collections.unmodifiableList(l);
    return marsMapTypes;
  }

  /**
   * Returns a map type that displays photographs taken from orbit around Mars.
   * 
   * @return a map type that displays photographs taken from orbit around Mars.
   *         This map type is not displayed within map type controls by default.
   *         (Since 2.95)
   */
  public static MapType getMarsVisibleMap() {
    initMapTypes();
    return marsVisibleMap;
  }

  /**
   * Returns a map type that displays a shaded terrain map of the surface of the
   * Moon.
   * 
   * @return a map type that displays a shaded terrain map of the surface of the
   *         Moon, color-coded by altitude. This map type is not displayed
   *         within map type controls by default. (Since 2.95)
   */
  public static MapType getMoonElevationMap() {
    initMapTypes();
    return moonElevationMap;
  }

  /**
   * Turns G_MOON_MAP_TYPES into an immutable list of MapType objects.
   * 
   * @return an immutable list of MapType objects.
   */
  public static List<MapType> getMoonMapTypes() {
    if (moonMapTypes != null) {
      return moonMapTypes;
    }
    initMapTypes();
    int size = getMoonMapTypesSize();
    List<MapType> l = new ArrayList<MapType>();
    for (int i = 0; i < size; i++) {
      l.add(getMoonMapType(i));
    }
    moonMapTypes = Collections.unmodifiableList(l);
    return moonMapTypes;
  }

  /**
   * Returns a map type that displays photographs taken from orbit around the
   * moon.
   * 
   * @return a map type that displays photographs taken from orbit around the
   *         moon. This map type is not displayed within map type controls by
   *         default. (Since 2.95)
   */
  public static MapType getMoonVisibleMap() {
    initMapTypes();
    return moonVisibleMap;
  }

  /**
   * Returns the normal street map type.
   * 
   * @return the normal street map type.
   */
  public static MapType getNormalMap() {
    initMapTypes();
    return normalMap;
  }

  /**
   * Returns a map type that shows a terrain view.
   * 
   * @return a map type that shows a terrain view.
   */
  public static MapType getPhysicalMap() {
    initMapTypes();
    return physicalMap;
  }

  /**
   * Returns a map type that shows Google Earth satellite images.
   * 
   * @return a map type that shows Google Earth satellite images.
   */
  public static MapType getSatelliteMap() {
    initMapTypes();
    return satelliteMap;
  }

  /**
   * Turns G_SKY_MAP_TYPES into an immutable list of MapType objects.
   * 
   * @return an immutable list of MapType objects.
   */
  public static List<MapType> getSkyMapTypes() {
    if (skyMapTypes != null) {
      return skyMapTypes;
    }
    initMapTypes();
    int size = getSkyMapTypesSize();
    List<MapType> l = new ArrayList<MapType>();
    for (int i = 0; i < size; i++) {
      l.add(getSkyMapType(i));
    }
    skyMapTypes = Collections.unmodifiableList(l);
    return skyMapTypes;
  }

  /**
   * Returns a map type shows a mosaic of the sky.
   * 
   * @return a map type shows a mosaic of the sky, covering the full celestial
   *         sphere. (Since 2.95)
   */
  public static MapType getSkyVisibleMap() {
    initMapTypes();
    return skyVisibleMap;
  }

  /**
   * Wraps an existing GMapType object.
   * 
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
   * Return one member of the G_MAPMAKER_MAP_TYPES array. If it has already been
   * wrapped, return the wrapped object. Otherwise, wrap the object.
   * 
   * @param i index into the array
   * @return an element of the array as a Java object
   */
  private static native MapType getMapmakerMapType(int i) /*-{
    var o = $wnd.G_MAPMAKER_MAP_TYPES[i];
    if (o.__gwtPeer) {
       // Avoid double wrapping the object.
       return o.__gwtPeer;
     } else {
       return @com.google.gwt.maps.client.MapType::createPeer(Lcom/google/gwt/core/client/JavaScriptObject;)(o);
     }
  }-*/;

  /**
   * @return the length of the MAPMAKER_MAP_TYPES array.
   */
  private static native int getMapmakerMapTypesSize() /*-{
    return $wnd.G_MAPMAKER_MAP_TYPES.length;
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
    earthMap = createPeer(MapTypeImpl.impl.getEarthMapType());
    physicalMap = createPeer(MapTypeImpl.impl.getPhysicalMapType());
    moonElevationMap = createPeer(MapTypeImpl.impl.getMoonElevationMapType());
    moonVisibleMap = createPeer(MapTypeImpl.impl.getMoonVisibleMap());
    marsElevationMap = createPeer(MapTypeImpl.impl.getMarsElevationMap());
    marsVisibleMap = createPeer(MapTypeImpl.impl.getMarsVisibleMap());
    marsInfraredMap = createPeer(MapTypeImpl.impl.getMarsInfraredMap());
    skyVisibleMap = createPeer(MapTypeImpl.impl.getSkyVisibleMap());
    aerialMap = createPeer(MapTypeImpl.impl.getAerialMap());
    aerialHybridMap = createPeer(MapTypeImpl.impl.getAerialHybridMap());
    mapmakerNormalMap = createPeer(MapTypeImpl.impl.getMapmakerNormalMap());
    mapmakerHybridMap = createPeer(MapTypeImpl.impl.getMapmakerHybridMap());    
    mapTypesInitialized = true;
  }

  private final JavaScriptObject jsoPeer;

  private HandlerCollection<MapTypeNewCopyrightHandler> mapTypeNewCopyrightHandlers;

  /**
   * Creates a new custom map type from the tile layers, projection, and name.
   * 
   * @param layers the map type's tile layers
   * @param projection the projection to be used for this map type
   * @param name the name of the map type
   */
  public MapType(TileLayer[] layers, Projection projection, String name) {
    jsoPeer = MapTypeImpl.impl.construct(JsUtil.toJsList(layers), projection,
        name);
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
    jsoPeer = MapTypeImpl.impl.construct(JsUtil.toJsList(layers), projection,
        name, options);
    MapTypeImpl.impl.bind(jsoPeer, this);
  }

  private MapType(JavaScriptObject jsoPeer) {
    this.jsoPeer = jsoPeer;
  }

  /**
   * Add a handler for "newcopyright" events. This event is fired when a new
   * copyright was added to this copyright collection.
   * 
   * @param handler handler to invoke on mouse click events.
   */
  public void addMapTypeNewCopyrightHandler(
      final MapTypeNewCopyrightHandler handler) {
    maybeInitMapTypeNewCopyrightHandlers();

    mapTypeNewCopyrightHandlers.addHandler(handler, new CopyrightCallback() {
      @Override
      public void callback(Copyright copyright) {
        MapTypeNewCopyrightEvent e = new MapTypeNewCopyrightEvent(MapType.this,
            copyright);
        handler.onNewCopyright(e);
      }
    });
  }

  /**
   * Returns to the map the alternative text of this map type.
   * 
   * @return to the map the alternative text of this map type.
   */
  public String getAlt() {
    return MapTypeImpl.impl.getAlt(jsoPeer);
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
    JSList<String> copyrights = MapTypeImpl.impl.getCopyrights(jsoPeer, bounds,
        zoomLevel);
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
   * Returns the lowest zoom level at which this map type is defined for a given
   * point.
   * 
   * @return the lowest zoom level for the point
   */
  public int getMinimumResolution() {
    return MapTypeImpl.impl.getMinimumResolution(jsoPeer);
  }

  /**
   * Returns the translated, human-readable string name of the map type.
   * 
   * The name is used as the button label in the
   * {@link com.google.gwt.maps.client.control.MapTypeControl MapTypeControl}.
   * 
   * @param shortName <code>true</code> to return the abbreviated name
   * @return the name of the map type
   */
  public String getName(boolean shortName) {
    return MapTypeImpl.impl.getName(jsoPeer, shortName);
  }

  /**
   * Returns the native JavaScript object representing this MapType.
   * 
   * @return the native JavaScript object representing this MapType.
   */
  public JavaScriptObject getPeer() {
    return jsoPeer;
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
   * @return the tile layers.
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
   * @return the size of the tiles.
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

  /**
   * Removes a single handler of this map type previously added with
   * {@link MapType#addMapTypeNewCopyrightHandler(MapTypeNewCopyrightHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeMapTypeNewCopyrightHandler(
      MapTypeNewCopyrightHandler handler) {
    if (mapTypeNewCopyrightHandlers != null) {
      mapTypeNewCopyrightHandlers.removeHandler(handler);
    }
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(MapTypeNewCopyrightEvent event) {
    maybeInitMapTypeNewCopyrightHandlers();
    mapTypeNewCopyrightHandlers.trigger(event.getCopyright());
  }

  /**
   * Lazy init the HandlerCollection.
   */
  private void maybeInitMapTypeNewCopyrightHandlers() {
    if (mapTypeNewCopyrightHandlers == null) {
      mapTypeNewCopyrightHandlers = new HandlerCollection<MapTypeNewCopyrightHandler>(
          jsoPeer, MapEvent.NEWCOPYRIGHT);
    }
  }
}

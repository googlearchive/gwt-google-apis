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

import com.google.gwt.core.client.GWT;
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
 * There are three predefine map types: {@link MapType#NORMAL_MAP},
 * {@link MapType#SATELLITE_MAP}, and {@link MapType#HYBRID_MAP}.
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

  /**
   * An array of the three predefined map types.
   */
  public static final MapType[] DEFAULT_MAP_TYPES;

  /**
   * A map type that shows transparent street maps over Google Earth satellite
   * images.
   */
  public static final MapType HYBRID_MAP;

  /**
   * The normal street map type.
   */
  public static final MapType NORMAL_MAP;

  /**
   * A map type that shows Google Earth satellite images.
   */
  public static final MapType SATELLITE_MAP;

  private static final MapTypeImpl impl = (MapTypeImpl) GWT.create(MapTypeImpl.class);

  // TODO: DELETE ME! (needs to function w/o)
  private static final Extractor __extractor = new Extractor() {
    public Object fromJS(JavaScriptObject jso) {
      return new MapType(jso);
    }

    public JavaScriptObject toJS(Object o) {
      return ((MapType) o).jsoPeer;
    }
  };

  static {
    NORMAL_MAP = createPeer(impl.getNormalMapType());
    SATELLITE_MAP = createPeer(impl.getSatelliteMapType());
    HYBRID_MAP = createPeer(impl.getHybridMapType());
    DEFAULT_MAP_TYPES = new MapType[] {
        NORMAL_MAP, SATELLITE_MAP, HYBRID_MAP
    };
  }

  static MapType createPeer(JavaScriptObject jsoPeer) {
    MapType mapType = new MapType(jsoPeer);
    impl.bind(jsoPeer, mapType);
    return mapType;
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
    jsoPeer = impl.construct(JsUtil.toJsList(layers), projection, name);
    impl.bind(jsoPeer, this);
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
    jsoPeer = impl.construct(JsUtil.toJsList(layers), projection, name, options);
    impl.bind(jsoPeer, this);
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
    return impl.getBoundsZoomLevel(jsoPeer, bounds, viewSize);
  }

  /**
   * Returns the copyrights for the given bounds and zoom level.
   * 
   * @param bounds the current viewport
   * @param zoomLevel the current zoom level
   * @return the copyrights corresponding to the given viewport
   */
  public String[] getCopyrights(LatLngBounds bounds, int zoomLevel) {
    JSList copyrights = impl.getCopyrights(jsoPeer, bounds, zoomLevel);
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
    return impl.getErrorMessage(jsoPeer);
  }

  /**
   * Returns the color that used for a hyperlinks displayed on the map.
   * 
   * @return the color used for hyperlinks
   */
  public String getLinkColor() {
    return impl.getLinkColor(jsoPeer);
  }

  /**
   * Returns the highest zoom level at which this map type is defined for a
   * given point.
   * 
   * @return the highest zoom level for the point
   */
  public int getMaximumResolution() {
    return impl.getMaximumResolution(jsoPeer);
  }

  /**
   * Returns the highest zoom level at which this map type is defined for a
   * given point.
   * 
   * @param latlng the point at which to find the maximum resolution
   * @return the highest zoom level for the point
   */
  public int getMaximumResolution(LatLng latlng) {
    return impl.getMaximumResolution(jsoPeer, latlng);
  }

  /**
   * Returns the lowest zoom level at which this map type is defined for a given
   * point.
   * 
   * @param latlng the point at which to find the minimum resolution
   * @return the lowest zoom level for the point
   */
  public int getMinimumResolution() {
    return impl.getMinimumResolution(jsoPeer);
  }

  /**
   * Returns the lowest zoom level at which this map type is defined for a given
   * point.
   * 
   * @param latlng the point at which to find the minimum resolution
   * @return the lowest zoom level for the point
   */
  public int getMinimumResolution(LatLng latlng) {
    return impl.getMinimumResolution(jsoPeer, latlng);
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
    return impl.getName(jsoPeer, shortName);
  }

  /**
   * Returns the projection used by this map type.
   * 
   * @return the projection used by this map type
   */
  public Projection getProjection() {
    return impl.getProjection(jsoPeer);
  }

  /**
   * Returns the highest resolution zoom level required to show the given span
   * with the given center point.
   * 
   * @todo example, better descriptions
   * 
   * @param center the center of the viewport
   * @param span the span of the viewport
   * @param viewSize the size of the viewport in pixels
   * @return the highest possible zoom level
   */
  public int getSpanZoomLevel(LatLng center, LatLng span, Size viewSize) {
    return impl.getSpanZoomLevel(jsoPeer, center, span, viewSize);
  }

  /**
   * Returns the color used for text displayed on the map.
   * 
   * @return the color used for text displayed on the map
   */
  public String getTextColor() {
    return impl.getTextColor(jsoPeer);
  }

  /**
   * Returns the array of tile layers in the z-order they should be displayed.
   * 
   * @return the tile layers
   */
  public TileLayer[] getTileLayers() {
    JSList layers = impl.getTileLayers(jsoPeer);
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
    return impl.getTileSize(jsoPeer);
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
    return impl.getUrlArg(jsoPeer);
  }
}

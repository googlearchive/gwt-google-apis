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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.impl.ProjectionImpl;
import com.google.gwt.maps.jsio.client.Exported;

/**
 * This is the interface for map projections. A map projection instance is
 * passed to the constructor of {@link com.google.gwt.maps.client.MapType}.
 * This interface is implemented by the class {@link MercatorProjection}, which
 * is used by all predefined map types. You can implement this interface if you
 * want to define map types with different map projections.
 */
public abstract class Projection {

  static Projection createPeer(JavaScriptObject jsoPeer) {
    return new MercatorProjection(jsoPeer);
  }

  protected final JavaScriptObject jsoPeer;

  /**
   * Create a new Projection instance and bind it to this implementation.
   */
  public Projection() {
    jsoPeer = ProjectionImpl.impl.construct();
    ProjectionImpl.impl.bind(jsoPeer, this);
  }

  /**
   * Create a new Projection instance by wrapping an existing GProjection
   * object. Note that this version of the constructor does not call 'bind()' in
   * the Impl class.
   * 
   * @param jsoPeer object to wrap.
   */
  protected Projection(JavaScriptObject jsoPeer) {
    this.jsoPeer = jsoPeer;
  }

  /**
   * Returns the map coordinates in pixels for the point at the given
   * geographical coordinates, and the given zoom level.
   * 
   * @param latlng map coordinates to translate.
   * @param zoomLevel zoom level to translate.
   * @return the map coordinates in pixels for the point at the given
   *         geographical coordinates, and the given zoom level.
   */
  @Exported
  public abstract Point fromLatLngToPixel(LatLng latlng, int zoomLevel);

  /**
   * Returns the geographical coordinates for the point at the given map
   * coordinates in pixels, and the given zoom level. Flag unbounded causes the
   * geographical longitude coordinate not to wrap when beyond the -180 or 180
   * degrees meridian.
   * 
   * @param point the point at the given map coordinates in pixels to translate.
   * @param zoomLevel zoom level to translate.
   * @param unbounded if <code>true</code>, causes the geographical longitude coordinate
   *          not to wrap when beyond the -180 or 180 degrees meridian.
   * @return the geographical coordinates for the point at the given map
   *         coordinates.
   */
  @Exported
  public abstract LatLng fromPixelToLatLng(Point point, int zoomLevel,
      boolean unbounded);

  /**
   * Returns to the map the periodicity in x-direction, i.e. the number of
   * pixels after which the map repeats itself because it wrapped once round the
   * earth. By default, returns Double.POSITIVE_INFINITY, i.e. the map will not
   * repeat itself. This is used by the map to compute the placement of overlays
   * on map views that contain more than one copy of the earth (this usually
   * happens only at low zoom levels).
   * 
   * @param zoomLevel the zoom level to use.
   * @return the periodicity in x-direction, i.e. the number of pixels after
   *         which the map repeats itself). Specify Double.POSITIVE_INFINITY to
   *         indicate that the map does not repeat.
   */
  @Exported
  public abstract double getWrapWidth(int zoomLevel);

  /**
   * Indicates to the map if the tile index is in a valid range for the map
   * type. Otherwise the map will display an empty tile.
   * 
   * @param index the tile index to check. The coordinates may be modified to
   *          point to another instance of the same tile in the case that the
   *          map contains more than one copy of the earth, and hence the same
   *          tile at different tile coordinates.
   * @param zoomLevel the zoom level to check
   * @param tileSize
   * @return <code>true</code> if the tile index is in a valid range for the map type.
   */
  @Exported
  public abstract boolean tileCheckRange(TileIndex index, int zoomLevel,
      int tileSize);

}

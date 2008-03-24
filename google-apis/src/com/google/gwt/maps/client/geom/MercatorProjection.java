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

/**
 * An implementation of the Projection interface for the Mercator projection is
 * used by all predefined map types. A Mercator projection is one way to map a
 * sphere onto a two dimensional surface and is typically used for world maps. A
 * Mercator projection shows all lines of longitude as parallel vertical lines
 * rather than intersecting line, therefore, distances become distorted near the
 * poles. A Mercator map projection not only allows a rectangular map to be
 * drawn. but also easily allows tiles to be aligned for a continuous image.
 * 
 */
// TODO(zundel): This class defines override methods for the abstract Projection
// methods, but they never get called because a JSIO bind is never done. This
// works out O.K. because we are just wrapping a JSO, but it brings into
// question the design of the Projection hierarchy. Probably we need to follow
// the model used by {@link Overlay}.  See issue 90.
public final class MercatorProjection extends Projection {

  /**
   * Constructs a Mercator projection for the given number of zoom levels.
   * 
   * @param numZoomLevels the number of zoom levels to support.
   */
  public MercatorProjection(int numZoomLevels) {
    super(ProjectionImpl.impl.constructMercatorProjection(numZoomLevels));
    // This will cause an infinite loop.  See issue 29
    // ProjectionImpl.impl.bind(jsoPeer, this);
  }

  /**
   * Wrap an existing GMercatorProjection object.
   * 
   * @param jsoPeer the existing JavaScript object to wrap.
   */
  protected MercatorProjection(JavaScriptObject jsoPeer) {
    super(jsoPeer);
  }

  // TODO(zundel): this method is never used by custom maps because it is never
  // bound with JSIO.
  @Override
  public Point fromLatLngToPixel(LatLng latlng, int zoomLevel) {
    return ProjectionImpl.impl.fromLatLngToPixel(jsoPeer, latlng, zoomLevel);
  }

  // TODO(zundel): this method is never used by custom maps because it is never
  // bound with JSIO.
  @Override
  public LatLng fromPixelToLatLng(Point point, int zoomLevel, boolean unbound) {
    return ProjectionImpl.impl.fromPixelToLatLng(jsoPeer, point, zoomLevel,
        unbound);
  }

  /**
   * Mercator projection is periodic in longitude direction, therefore this
   * returns the width of the map of the entire Earth in pixels at the given
   * zoom level.
   * 
   * @see Projection#getWrapWidth(int)
   */
  // TODO(zundel): this method is never used by custom maps because it is never
  // bound with JSIO.
  @Override
  public double getWrapWidth(int zoomLevel) {
    return ProjectionImpl.impl.getWrapWidth(jsoPeer, zoomLevel);
  }
  
  // TODO(zundel): this method is never used by custom maps because it is never
  // bound with JSIO.
  @Override
  public boolean tileCheckRange(TileIndex index, int zoomLevel, int tileSize) {
    return ProjectionImpl.impl.tileCheckRange(jsoPeer, index, zoomLevel,
        tileSize);
  }
}

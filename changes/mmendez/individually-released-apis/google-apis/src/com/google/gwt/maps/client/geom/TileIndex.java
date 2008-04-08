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
import com.google.gwt.jsio.client.impl.Extractor;
import com.google.gwt.maps.client.impl.PointImpl;

/**
 * Specifies the position of a Tile in the map. Used as an argument to
 * {@link Projection#tileCheckRange(TileIndex,int,int)}
 * 
 */
public class TileIndex {

  /*
   * Design Note (zundel): The Maps API re-uses the Point object. I've modeled
   * it as a different class in order to allow Point to be immutable and to
   * disallow creating a TileIndex by users.
   */
  // TODO: DELETE ME! (needs to function w/o)
  @SuppressWarnings("unused")
  private static final Extractor<TileIndex> __extractor = new Extractor<TileIndex>() {
    public TileIndex fromJS(JavaScriptObject jso) {
      return createPeer(jso);
    }

    public JavaScriptObject toJS(TileIndex o) {
      return o.jsoPeer;
    }
  };

  /**
   * Create a new TileIndex by wrapping an existing JavaScript GPoint.
   * 
   * @param jso the object to wrap.
   * @return a new TileIndex.
   */
  static TileIndex createPeer(JavaScriptObject jso) {
    return new TileIndex(jso);
  }

  private final JavaScriptObject jsoPeer;

  private TileIndex(JavaScriptObject jso) {
    this.jsoPeer = jso;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof TileIndex) {
      return PointImpl.impl.equals(jsoPeer, (TileIndex) obj);
    }
    return false;
  }

  /**
   * Returns the X coordinate.
   * 
   * @return the X coordinate.
   */
  public int getX() {
    return PointImpl.impl.getX(jsoPeer);
  }

  /**
   * Returns the Y coordinate.
   * 
   * @return the Y coordinate.
   */
  public int getY() {
    return PointImpl.impl.getY(jsoPeer);
  }

  @Override
  public int hashCode() {
    // Bias the Y coordinate using a prime number (101) so that inverted
    // coordinates
    // do not hash to the same value.
    return getX() + (101 * getY());
  }

  /**
   * Sets the X coordinate.
   * 
   * @param x the X coordinate.
   */
  public void setX(int x) {
    PointImpl.impl.setX(jsoPeer, x);
  }
  
  /**
   * Sets the Y coordinate.
   * 
   * @param y the Y coordinate.
   */
  public void setY(int y) {
    PointImpl.impl.setY(jsoPeer, y);
  }

  @Override
  public String toString() {
    return PointImpl.impl.toString(jsoPeer);
  }
}

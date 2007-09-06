/*
 * Copyright 2007 Google Inc.
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
 * 
 */
public abstract class Projection {

  static Projection createPeer(JavaScriptObject jsoPeer) {
    return new MercatorProjection(jsoPeer);
  }

  protected final JavaScriptObject jsoPeer;

  public Projection() {
    jsoPeer = ProjectionImpl.impl.construct();
    ProjectionImpl.impl.bind(jsoPeer, this);
  }

  protected Projection(JavaScriptObject jsoPeer) {
    this.jsoPeer = jsoPeer;
  }

  /**
   * @gwt.exported getWrapWidth
   */
  public abstract int getWrapWidth(int zoomLevel);

  // TODO: better method name
  /**
   * @gwt.exported tileCheckRange
   */
  public abstract boolean tileCheckRange(Point point, int zoomLevel,
      Size tileSize);

  /**
   * @gwt.exported fromLatLngToPixel
   */
  protected abstract Point convertLatLngToPixel(LatLng latlng, int zoomLevel);

  // TODO: better argument name than "nofix"
  /**
   * @gwt.exported fromPixelToLatLng
   */
  protected abstract LatLng convertPixelToLatLng(Point point, int zoomLevel,
      boolean nofix);

}

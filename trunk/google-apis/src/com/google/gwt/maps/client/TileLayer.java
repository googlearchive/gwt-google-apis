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
package com.google.gwt.maps.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.jsio.client.impl.Extractor;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.client.impl.TileLayerImpl;

public abstract class TileLayer {

  private static final TileLayerImpl impl = (TileLayerImpl) GWT.create(TileLayerImpl.class);

  // TODO: abstract? concrete? both?
  
  // TODO: DELETE ME! (needs to function w/o)
  private static final Extractor __extractor = new Extractor() {
    public Object fromJS(JavaScriptObject jso) {
      return createPeer(jso);
    }

    public JavaScriptObject toJS(Object o) {
      return ((TileLayer) o).jsoPeer;
    }
  };

  static TileLayer createPeer(JavaScriptObject jsoPeer) {
    return new TileLayer(jsoPeer) {
      public double getOpacity() {
        return impl.getOpacity(this);
      }

      public String getTileURL(Point tile, int zoomLevel) {
        return impl.getTileUrl(this, tile, zoomLevel);
      }

      public boolean isPng() {
        return impl.isPng(this);
      }
    };
  }

  private final JavaScriptObject jsoPeer;

  public TileLayer(CopyrightCollection copyrights, int minResolution,
      int maxResolution) {
    jsoPeer = impl.construct(copyrights, minResolution, maxResolution);
    impl.bind(jsoPeer, this);
  }

  private TileLayer(JavaScriptObject jsoPeer) {
    this.jsoPeer = jsoPeer;
  }

  public int getMaxResolution() {
    return impl.getMaxResolution(this);
  }

  public int getMinResolution() {
    return impl.getMinResolution(this);
  }

  /**
   * @gwt.exported
   */
  public abstract double getOpacity();

  /**
   * @gwt.exported
   * @gwt.fieldName getTileUrl
   */
  public abstract String getTileURL(Point tile, int zoomLevel);

  /**
   * @gwt.exported
   */
  public abstract boolean isPng();

}

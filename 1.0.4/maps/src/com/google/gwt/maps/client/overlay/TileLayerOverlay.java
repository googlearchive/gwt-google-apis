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
import com.google.gwt.maps.client.TileLayer;
import com.google.gwt.maps.client.impl.TileLayerOverlayImpl;
import com.google.gwt.maps.client.overlay.Overlay.ConcreteOverlay;

/**
 * A TileLayerOverlay augments the map with a {@link TileLayer}. It implements
 * the {@link Overlay} interface and is added to the map using the
 * {@link com.google.gwt.maps.client.MapWidget#addOverlay(Overlay)} method. The
 * TileLayer is presented on top of the existing map imagery. To replace the
 * imagery instead, put the TileLayer inside a custom
 * {@link com.google.gwt.maps.client.MapType MapType}.
 */
public class TileLayerOverlay extends ConcreteOverlay {

  public static TileLayerOverlay createPeer(JavaScriptObject jso) {
    TileLayerOverlay overlay = new TileLayerOverlay(jso);
    // perform bind to make sure the right JSO was passed
    TileLayerOverlayImpl.impl.bind(jso, overlay);
    return overlay;
  }

  /**
   * Creates a tile layer overlay from the given tile layer.
   * 
   * @param tileLayer tile layer instance.
   */
  public TileLayerOverlay(TileLayer tileLayer) {
    super(TileLayerOverlayImpl.impl.construct(tileLayer));
  }

  /**
   * Creates a tile layer overlay from the given tile layer.
   * 
   * @param tileLayer tile layers instance.
   * @param options optional arguments
   */
  public TileLayerOverlay(TileLayer tileLayer, 
      TileLayerOverlayOptions options) {
    super(TileLayerOverlayImpl.impl.construct(tileLayer, options));
  }

  /**
   * Create this overlay from an existing JavaScriptObject instance.
   * 
   * @param jso an existing JavaScriptObject instance.
   */
  protected TileLayerOverlay(JavaScriptObject jso) {
    super(jso);
  }

  /**
   * Gets the tile layer used by this overlay.
   * 
   * @return the tile layer used by this overlay
   */
  public TileLayer getTileLayer() {
    return TileLayerOverlayImpl.impl.getTileLayer(this);
  }

  public void refresh() {
    TileLayerOverlayImpl.impl.refresh(this);
  }

  /**
   * Shows or hides this overlay.
   * 
   * @param visible <code>true</code> to show the overlay, <code>false</code> to
   *          hide.
   */
  public void setVisible(boolean visible) {
    if (visible) {
      TileLayerOverlayImpl.impl.show(this);
    } else {
      TileLayerOverlayImpl.impl.hide(this);
    }
  }
}
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
import com.google.gwt.maps.client.event.TileLayerNewCopyrightHandler;
import com.google.gwt.maps.client.event.TileLayerNewCopyrightHandler.TileLayerNewCopyrightEvent;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.client.impl.HandlerCollection;
import com.google.gwt.maps.client.impl.MapEvent;
import com.google.gwt.maps.client.impl.TileLayerImpl;
import com.google.gwt.maps.client.impl.EventImpl.CopyrightCallback;
import com.google.gwt.maps.jsio.client.Exported;
import com.google.gwt.maps.jsio.client.FieldName;
import com.google.gwt.maps.jsio.client.impl.Extractor;

/**
 * Implement this class in order to provide custom map tile layers, either
 * through MapType or TileLayerOverlay.  An implementation should use an
 * instance of TileLayer as a prototype, because this implements the copyright
 * handling.
 */
public abstract class TileLayer {

  // TODO: DELETE ME! (needs to function w/o)
  @SuppressWarnings("unused")
  private static final Extractor<TileLayer> __extractor = new Extractor<TileLayer>() {
    public TileLayer fromJS(JavaScriptObject jso) {
      return createPeer(jso);
    }

    public JavaScriptObject toJS(TileLayer o) {
      return o.jsoPeer;
    }
  };

  public static TileLayer createPeer(JavaScriptObject jsoPeer) {
    return new TileLayer(jsoPeer) {
      @Override
      public double getOpacity() {
        return TileLayerImpl.impl.getOpacity(this);
      }

      @Override
      public String getTileURL(Point tile, int zoomLevel) {
        return TileLayerImpl.impl.getTileUrl(this, tile, zoomLevel);
      }

      @Override
      public boolean isPng() {
        return TileLayerImpl.impl.isPng(this);
      }
    };
  }

  protected final JavaScriptObject jsoPeer;
  private HandlerCollection<TileLayerNewCopyrightHandler> tileLayerNewCopyrightHandlers;

  /**
   * @param copyrights copyrights to use for copyright handling
   * @param minResolution minimum zoom level to use for this tile layer
   * @param maxResolution maximum zoom level to use for this tile layer
   */
  public TileLayer(CopyrightCollection copyrights, int minResolution,
      int maxResolution) {
    jsoPeer = TileLayerImpl.impl.construct(copyrights, minResolution,
        maxResolution);
    TileLayerImpl.impl.bind(jsoPeer, this);
  }

  /**
   * Constructs a new TileLayer instance by wrapping an existing JavaScript instance
   * of GTileLayer.
   * 
   * @param jsoPeer object to wrap.
   */
  protected TileLayer(JavaScriptObject jsoPeer) {
    this.jsoPeer = jsoPeer;
  }

  /**
   * Add a handler for "newcopyright" events. This event is fired when a new
   * copyright was added to this copyright collection.
   * 
   * @param handler handler to invoke on mouse click events.
   */
  public void addTileLayerNewCopyrightHandler(
      final TileLayerNewCopyrightHandler handler) {
    maybeInitTileLayerNewCopyrightHandlers();

    tileLayerNewCopyrightHandlers.addHandler(handler,
        new CopyrightCallback() {
          @Override
          public void callback(Copyright copyright) {
            TileLayerNewCopyrightEvent e = new TileLayerNewCopyrightEvent(
                TileLayer.this, copyright);
            handler.onNewCopyright(e);
          }
        });
  }
  /**
   * Returns to the map type the highest zoom level of this tile layer.
   * 
   * @return the highest zoom level of this tile layer.
   */
  public int getMaxResolution() {
    return TileLayerImpl.impl.maxResolution(this);
  }

  /**
   * Returns to the map type the lowest zoom level of this tile layer.
   * 
   * @return the the lowest zoom level of this tile layer.
   */
  public int getMinResolution() {
    return TileLayerImpl.impl.minResolution(this);
  }

  /**
   * Returns to the map the opacity with which to display this tile
   * layer. 1.0 is opaque, 0.0 is transparent.
   * 
   * @return the opacity value to use for this tile layer.
   */
  @Exported
  public abstract double getOpacity();

  /**
   * Returns to the map the URL of the map tile with the tile indices
   * given by the x and y properties of the Point, at the given zoom level.
   * 
   * @param tile index of tile to compute URL for
   * @param zoomLevel zoom level to compute URL for
   * @return a URL to use to fetch the tile at the specified point and zoom
   *         level.
   */
  @Exported
  @FieldName("getTileUrl")
  public abstract String getTileURL(Point tile, int zoomLevel);

  /**
   * Returns to the map the copyright messages for this tile layer
   * that are pertinent for the given map region at the given zoom level. This
   * is used to generate the copyright message of the GMapType to which this
   * tile layer belongs.
   * 
   * @return A string representing the copyright messages.
   */
  @Exported
  public abstract boolean isPng();

  /**
   * Removes a single handler of this tile layer previously added with
   * {@link TileLayer#addTileLayerNewCopyrightHandler(TileLayerNewCopyrightHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeTileLayerNewCopyrightHandler(
      TileLayerNewCopyrightHandler handler) {
    if (tileLayerNewCopyrightHandlers != null) {
      tileLayerNewCopyrightHandlers.removeHandler(handler);
    }
  }
  
  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(TileLayerNewCopyrightEvent event) {
    maybeInitTileLayerNewCopyrightHandlers();
    tileLayerNewCopyrightHandlers.trigger(event.getCopyright());
  }
  
  /**
   * Lazy init the HandlerCollection.
   */
  private void maybeInitTileLayerNewCopyrightHandlers() {
    if (tileLayerNewCopyrightHandlers == null) {
      tileLayerNewCopyrightHandlers = new HandlerCollection<TileLayerNewCopyrightHandler>(
          jsoPeer, MapEvent.NEWCOPYRIGHT);
    }
  }

}

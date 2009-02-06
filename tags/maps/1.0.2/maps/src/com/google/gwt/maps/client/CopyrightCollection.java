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
import com.google.gwt.maps.client.event.NewCopyrightHandler;
import com.google.gwt.maps.client.event.NewCopyrightHandler.NewCopyrightEvent;
import com.google.gwt.maps.client.geom.LatLngBounds;
import com.google.gwt.maps.client.impl.CopyrightCollectionImpl;
import com.google.gwt.maps.client.impl.HandlerCollection;
import com.google.gwt.maps.client.impl.JsUtil;
import com.google.gwt.maps.client.impl.MapEvent;
import com.google.gwt.maps.client.impl.EventImpl.CopyrightCallback;
import com.google.gwt.maps.jsio.client.JSList;

/**
 * Manages copyright messages displayed on maps of custom map type. If you don't
 * implement custom map types, then you don't need to use this class. A
 * copyright collection contains information about which copyright to display
 * for which region on the map at which zoom level. This is important for map
 * types that display heterogeneous data such as the satellite map type.
 * 
 * @see TileLayer#TileLayer(CopyrightCollection, int, int)
 */
public final class CopyrightCollection {

  static CopyrightCollection createPeer(JavaScriptObject jsoPeer) {
    return new CopyrightCollection(jsoPeer);
  }

  private HandlerCollection<NewCopyrightHandler> newCopyrightHandlers;
  private final JavaScriptObject jsoPeer;

  /**
   * Creates an empty copyright collection.
   */
  public CopyrightCollection() {
    jsoPeer = CopyrightCollectionImpl.impl.construct();
  }

  /**
   * Creates an empty copyright collection with the given prefix. Each copyright
   * produced from this collection will have the given prefix.
   * 
   * @param prefix the prefix for every copyright
   */
  public CopyrightCollection(String prefix) {
    jsoPeer = CopyrightCollectionImpl.impl.construct(prefix);
  }
  
  private CopyrightCollection(JavaScriptObject jsoPeer) {
    this.jsoPeer = jsoPeer;
  }

  /**
   * Adds a copyright to this collection.
   * 
   * @param copyright the copyright to be added
   */
  public void addCopyright(Copyright copyright) {
    CopyrightCollectionImpl.impl.addCopyright(jsoPeer, copyright);
  }  

  /**
   * Add a handler for "newcopyright" events. This event is fired when a new
   * copyright was added to this copyright collection.
   * 
   * @param handler handler to invoke on mouse click events.
   */
  public void addNewCopyrightHandler(
      final NewCopyrightHandler handler) {
    maybeInitNewCopyrightHandlers();

    newCopyrightHandlers.addHandler(handler,
        new CopyrightCallback() {
          @Override
          public void callback(Copyright copyright) {
            NewCopyrightEvent e = new NewCopyrightEvent(
                CopyrightCollection.this, copyright);
            handler.onNewCopyright(e);
          }
        });
  }

  /**
   * Returns the copyright notice for the given viewport.
   * 
   * @param bounds the viewport's geographical bounds
   * @param zoomLevel the viewport's zoom level
   * @return the copyright notice for the given viewport
   */
  public String getCopyrightNotice(LatLngBounds bounds, int zoomLevel) {
    return CopyrightCollectionImpl.impl.getCopyrightNotice(jsoPeer, bounds,
        zoomLevel).toString();
  }

  /**
   * Returns the copyrights that should be displayed for the given viewport.
   * 
   * @param bounds the viewport's geographical bounds
   * @param zoomLevel the viewport's zoom level
   * @return the copyrights for the given viewport
   */
  public String[] getCopyrights(LatLngBounds bounds, int zoomLevel) {
    JSList<String> list = CopyrightCollectionImpl.impl.getCopyrights(jsoPeer,
        bounds, zoomLevel);
    String[] copyrights = new String[list.size()];
    JsUtil.toArray(list, copyrights);
    return copyrights;
  }

  /**
   * Removes a single handler of this copyright collection previously added with
   * {@link CopyrightCollection#addNewCopyrightHandler(NewCopyrightHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeNewCopyrightHandler(
      NewCopyrightHandler handler) {
    if (newCopyrightHandlers != null) {
      newCopyrightHandlers.removeHandler(handler);
    }
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(NewCopyrightEvent event) {
    maybeInitNewCopyrightHandlers();
    newCopyrightHandlers.trigger(event.getCopyright());
  }

  /**
   * Lazy init the HandlerCollection.
   */
  private void maybeInitNewCopyrightHandlers() {
    if (newCopyrightHandlers == null) {
      newCopyrightHandlers = new HandlerCollection<NewCopyrightHandler>(
          jsoPeer, MapEvent.NEWCOPYRIGHT);
    }
  }
}

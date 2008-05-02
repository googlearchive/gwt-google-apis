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
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.impl.OverlayImpl;
import com.google.gwt.maps.jsio.client.Exported;

/**
 * TODO(samgross): How are events supposed to work with overlays?
 */
public abstract class Overlay {

  /**
   * This class is used to wrap Overlays written entirely in JavaScript.
   * 
   * Note that the "Overlay" class is intended to be a superclass, and thus,
   * implements its methods on the prototype of the GOverlay object.
   * ConcreteOverlay is intended to be used when you don't want to subclass
   * Overlay but use it directly.
   */
  // TODO(zundel): Does it really work? The Maps code looks like it throws an
  // exception if you use Overlay directly, and if it was really bound to
  // Polyline, Marker, ..., it would obliterate their initialize, remove, copy,
  // and redraw() methods.
  public static class ConcreteOverlay extends Overlay {
    public ConcreteOverlay(JavaScriptObject jsoPeer) {
      super(jsoPeer);
      OverlayImpl.impl.bindConcreteOverlay(jsoPeer, this);
    }

    @Override
    protected final Overlay copy() {
      return OverlayImpl.impl.copy(super.jsoPeer);
    }

    @Override
    protected final void initialize(MapWidget map) {
      OverlayImpl.impl.initialize(super.jsoPeer, map);
    }

    @Override
    protected final void redraw(boolean force) {
      OverlayImpl.impl.redraw(super.jsoPeer, force);
    }

    @Override
    protected final void remove() {
      OverlayImpl.impl.remove(super.jsoPeer);
    }
  }

  /**
   * Returns a CSS z-index value for a given latitude. It computes a z index
   * such that overlays further south are on top of overlays further north, thus
   * creating the 3D appearance of marker overlays.
   * 
   * @param latitutde
   * @return a CSS z-index value
   */
  public static native int getZIndex(double latitutde) /*-{
    return $wnd.GOverlay.getZIndex(latitude);
  }-*/;

  /**
   * Used to create a new Overlay by wrapping an existing GOverlay object. This
   * method is invoked by the jsio library.
   * 
   * @param jsoPeer GOverlay object to wrap.
   * @return a new instance of Overlay.
   */
  @SuppressWarnings("unused")
  private static Overlay createPeer(JavaScriptObject jsoPeer) {
    return new ConcreteOverlay(jsoPeer);
  }

  protected final JavaScriptObject jsoPeer;

  public Overlay() {
    jsoPeer = OverlayImpl.impl.constructOverlay();
    OverlayImpl.impl.bindOverlay(jsoPeer, this);
  }

  protected Overlay(JavaScriptObject jsoPeer) {
    this.jsoPeer = jsoPeer;
  }

  /**
   * Returns an uninitialized copy of itself that can be added to the map.
   * 
   * @return an uninitialized copy of itself that can be added to the map.
   */
  @Exported
  protected abstract Overlay copy();

  /**
   * Called by the map after the overlay is added to the map using
   * {@link MapWidget#addOverlay(Overlay)}. The overlay object can draw itself
   * into the different panes of the map that can be obtained using
   * {@link MapWidget#getPane(com.google.gwt.maps.client.MapPaneType)}.
   * 
   * @param map The map this overlay has been added to.
   */
  @Exported
  protected abstract void initialize(MapWidget map);

  /**
   * Called by the map when the map display has changed.
   * 
   * @param force The argument force will be true if the zoom level or the pixel
   *          offset of the map view has changed, so that the pixel coordinates
   *          need to be recomputed.
   */
  @Exported
  protected abstract void redraw(boolean force);

  /**
   * Called by the map after the overlay is removed from the map using
   * {@link MapWidget#removeOverlay(Overlay)} or
   * {@link MapWidget#clearOverlays()}. The overlay must remove itself from the
   * map panes here.
   */
  @Exported
  protected abstract void remove();

}

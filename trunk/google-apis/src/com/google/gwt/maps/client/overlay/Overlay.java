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
import com.google.gwt.jsio.client.Exported;
import com.google.gwt.maps.client.impl.OverlayImpl;

/**
 * TODO: How are events supposed to work with overlays?
 */
public abstract class Overlay {

  /**
   * This class is used to wrap Overlays written entirely in JavaScript.
   */
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

  @Exported
  protected abstract Overlay copy();

  @Exported
  protected abstract void initialize(MapWidget map);

  @Exported
  protected abstract void redraw(boolean force);

  @Exported
  protected abstract void remove();

}

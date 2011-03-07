/*
 * Copyright 2011 Google Inc.
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
import com.google.gwt.maps.client.overlay.Overlay.ConcreteOverlay;

/**
 * 
 * This class instantiates a predefined "layer" overlay consisting of a
 * collection of related items. It implements the {@link Overlay} interface and
 * thus is added to the map using the
 * {@link com.google.gwt.maps.client.MapWidget#addOverlay(Overlay)} method.
 * 
 * For example:
 * <code>map.addOverlay(Layer.newInstance("com.panoramio.all"));</code>
 * 
 */
public class Layer extends ConcreteOverlay {

  /**
   * Native methods to access the Glayer object.
   */
  public static class LayerJso extends JavaScriptObject {
    protected LayerJso() {
    }

    public final native void hide() /*-{
      this.hide();
    }-*/;

    public final native void show() /*-{
      this.show;
    }-*/;
  }

  /**
   * returns <code>true</code> if the named layer is currently hidden.
   */
  public static native boolean isHidden(String layerId) /*-{
    return $wnd.GLayer.isHidden(layerId);
  }-*/;

  /**
   * Create a new layer instance
   * 
   * @param layerId unique Layer ID.
   *          http://spreadsheets.google.com/pub?key=p9pdwsai2hDN-cAocTLhnag
   *          contains a list of the currently supported layers.
   */
  public static Layer newInstance(String layerId) {
    return new Layer(newLayerJso(layerId));
  }

  private static native LayerJso newLayerJso(String layerId) /*-{
		return new $wnd.GLayer(layerId);
  }-*/;

  public Layer(JavaScriptObject jsoPeer) {
    super(jsoPeer);
  }

  /**
   * Hides this overlay so it is not visible, but maintains its position in the
   * stack of overlays
   */
  public void hide() {
    ((LayerJso) jsoPeer).hide();
  }

  /**
   * Shows a previously hidden Layer.
   */
  public void show() {
    ((LayerJso) jsoPeer).show();
  }
}

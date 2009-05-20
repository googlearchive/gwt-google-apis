/*
 * Copyright 2009 Google Inc.
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

/**
 * Used as an argument to the constructor for {@link TileLayerOverlay}.
 * 
 */
public class TileLayerOverlayOptions extends JavaScriptObject {

  public static native TileLayerOverlayOptions newInstance() /*-{
    // A more complex constructor works around inlining bug. See GWT issue 3568
    // http://code.google.com/p/google-web-toolkit/issues/detail?id=3568
    var obj;
    obj = new $wnd.Object();
    return obj;
  }-*/;

  protected TileLayerOverlayOptions() {
    // protected constructor required for JavaScriptObject overlays.
  }

  /**
   * Contains a value which determines the relative z-order for this
   * TileLayerOverlay. Higher priority tile layers will be rendered on top of
   * tile layers with a lower priority.
   * 
   * @param value priority value for this layer.
   * @return a reference to this object.
   */
  public final native TileLayerOverlayOptions setZPriority(double value) /*-{
    this.zPriority = value;
    return this;
  }-*/;
}

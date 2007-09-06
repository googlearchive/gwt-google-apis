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
package com.google.gwt.maps.client.impl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.jsio.client.JSFlyweightWrapper;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.geom.LatLngBounds;
import com.google.gwt.maps.client.overlay.Overlay;
import com.google.gwt.maps.client.overlay.Overlay.ConcreteOverlay;

public abstract class OverlayImpl implements JSFlyweightWrapper {

  public static final OverlayImpl impl = (OverlayImpl) GWT.create(OverlayImpl.class);

  /**
   * @gwt.binding
   */
  public abstract void bindOverlay(JavaScriptObject jsoPeer, Overlay overlay);

  public native void bindConcreteOverlay(JavaScriptObject jsoPeer,
      ConcreteOverlay overlay) /*-{
    jsoPeer.__gwtPeer = overlay;
  }-*/;

  /**
   * @gwt.constructor $wnd.GGeoXml
   */
  public abstract JavaScriptObject constructGeoXmlOverlay(String url);

  /**
   * @gwt.constructor $wnd.GGroundOverlay
   */
  public abstract  JavaScriptObject constructGroundOverlay(String imageUrl,
      LatLngBounds bounds);

  /**
   * @gwt.constructor $wnd.GOverlay
   */
  public abstract JavaScriptObject constructOverlay();

  public abstract Overlay copy(JavaScriptObject jsoPeer);

  public abstract void initialize(JavaScriptObject jsoPeer, MapWidget map);

  public abstract void redraw(JavaScriptObject jsoPeer, boolean force);

  public abstract void remove(JavaScriptObject jsoPeer);
}

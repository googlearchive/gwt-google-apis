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
package com.google.gwt.maps.client.impl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.LatLngBounds;
import com.google.gwt.maps.client.overlay.TileLayerOverlay;
import com.google.gwt.maps.jsio.client.Constructor;

/**
 * Creates a binding to the GGeoXml class in the Maps API.
 */
public abstract class GeoXmlOverlayImpl extends OverlayImpl {

  @SuppressWarnings("hiding")
  public static final GeoXmlOverlayImpl impl = //
  GWT.create(GeoXmlOverlayImpl.class);

  @Constructor("$wnd.GGeoXml")
  public abstract JavaScriptObject constructGeoXmlOverlay(String url);

  public abstract LatLngBounds getDefaultBounds(JavaScriptObject jsoPeer);

  public abstract LatLng getDefaultCenter(JavaScriptObject jsoPeer);

  public abstract LatLng getDefaultSpan(JavaScriptObject jsoPeer);

  public abstract TileLayerOverlay getTileLayerOverlay(JavaScriptObject jsoPeer);

  public abstract void gotoDefaultViewport(JavaScriptObject jsoPeer,
      JavaScriptObject mapWidget);

  public abstract boolean hasLoaded(JavaScriptObject jsoPeer);

  public abstract void hide(JavaScriptObject jsoPeer);

  public abstract boolean isHidden(JavaScriptObject jsoPeer);

  public abstract Boolean loadedCorrectly(JavaScriptObject jsoPeer);

  public abstract void show(JavaScriptObject jsoPeer);

  public abstract boolean supportsHide(JavaScriptObject jsoPeer);
}

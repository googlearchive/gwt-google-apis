/* Copyright (c) 2010 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.gwt.maps.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.base.LatLngBounds;
import com.google.gwt.maps.client.overlay.HasProjection;
import com.google.gwt.maps.client.overlay.Projection;
import com.google.gwt.user.client.Element;

/**
 * Map java overlay.
 */
public class Map extends JavaScriptObject {
  
  /**
   * Creates a new map inside of the given HTML container, which is typically a DIV element.
   */
  public static native final Map newInstance(Element div) /*-{
    return new $wnd.google.maps.Map(div);
  }-*/;
  
  /**
   * Creates a new map inside of the given HTML container, which is typically a DIV element.
   * With map options.
   */
  public static native final Map newInstance(Element div, JavaScriptObject opts) /*-{
    return new $wnd.google.maps.Map(div, opts);
  }-*/;
  
  protected Map() {}

  public native final void fitBounds(LatLngBounds bounds) /*-{
    this.fitBounds(bounds);
  }-*/;

  public native final LatLngBounds getBounds() /*-{
    return this.getBounds();
  }-*/;

  public native final LatLng getCenter() /*-{
    return this.getCenter();
  }-*/;

  public native final Element getDiv() /*-{
    return this.getDiv();
  }-*/;

  public native final String getMapTypeId() /*-{
    return this.getMapTypeId();
  }-*/;

  // TODO: return concrete type {@link Projection}.
  public native final JavaScriptObject getProjectionJso() /*-{
    return this.getProjection();
  }-*/;

  public final HasProjection getProjection() {
    return new Projection(getProjectionJso());
  };

  public native final int getZoom() /*-{
    return this.getZoom();
  }-*/;

  public native final void panBy(int x, int y) /*-{
    this.panBy(x, y);
  }-*/;

  public native final void panTo(LatLng latLng) /*-{
    this.panTo(latLng);
  }-*/;

  public native final void panToBounds(LatLngBounds bounds) /*-{
    this.panToBounds(bounds);
  }-*/;

  public native final void setCenter(LatLng latLng) /*-{
    this.setCenter(latLng);
  }-*/;

  public native final void setMapTypeId(String mapTypeId) /*-{
    this.setMapTypeId(mapTypeId);
  }-*/;

  // TODO: replace jso with concrete type {@link MapOptions}.
  public native final void setOptions(JavaScriptObject options) /*-{
    this.setOptions(options);
  }-*/;

  public native final void setZoom(int zoom) /*-{
    this.setZoom(zoom);
  }-*/;

}

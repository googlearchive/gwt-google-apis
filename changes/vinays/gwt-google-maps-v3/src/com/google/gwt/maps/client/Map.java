/* Copyright (c) 2010 Vinay Inc.
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
import com.google.gwt.maps.client.base.HasLatLngBounds;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.base.LatLngBounds;
import com.google.gwt.maps.client.impl.MapImpl;
import com.google.gwt.maps.client.mvc.MVCObject;
import com.google.gwt.maps.client.overlay.HasProjection;
import com.google.gwt.maps.client.overlay.Projection;
import com.google.gwt.user.client.Element;

/**
 * Map java overlay.
 * 
 * This class extends {@link MVCObject}.
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public class Map extends MVCObject implements HasMap {
  
  final private JavaScriptObject jso;
  
  public Map(JavaScriptObject jso) {
    this.jso = jso;
  }
  
  /**
   * Creates a new map inside of the given HTML container, which is typically a DIV element.
   */
  public Map(Element mapDiv) {
    this(MapImpl.impl.construct(mapDiv));
  }
  
  /**
   * Creates a new map inside of the given HTML container, which is typically a DIV element.
   * 
   * With map options.
   */
  public Map(Element mapDiv, HasMapOptions mapOptions) {
    this(MapImpl.impl.construct(mapDiv, mapOptions.getJso()));
  }

  @Override
  public void fitBounds(HasLatLngBounds bounds) {
    MapImpl.impl.fitBounds(jso, bounds.getJso());
  }

  @Override
  public HasLatLngBounds getBounds() {
    return new LatLngBounds(MapImpl.impl.getBounds(jso));
  }

  @Override
  public LatLng getCenter() {
    return (LatLng) MapImpl.impl.getCenter(jso);
  }

  @Override
  public Element getDiv() {
    return MapImpl.impl.getDiv(jso);
  }

  @Override
  public String getMapTypeId() {
    return MapImpl.impl.getMapTypeId(jso);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.HasMap#getProjection()
   */
  @Override
  public HasProjection getProjection() {
    return new Projection(MapImpl.impl.getProjection(jso));
  }

  @Override
  public int getZoom() {
    return MapImpl.impl.getZoom(jso);
  }

  @Override
  public void panBy(int x, int y) {
    MapImpl.impl.panBy(jso, x, y);
  }

  @Override
  public void panTo(LatLng latLng) {
    MapImpl.impl.panTo(jso, latLng);
  }

  @Override
  public void panToBounds(HasLatLngBounds bounds) {
    MapImpl.impl.panToBounds(jso, bounds.getJso());
  }

  @Override
  public void setCenter(LatLng latLng) {
    MapImpl.impl.setCenter(jso, latLng);
  }

  @Override
  public void setMapTypeId(String mapTypeId) {
    MapImpl.impl.setMapTypeId(jso, mapTypeId);
  }

  @Override
  public void setOptions(HasMapOptions options) {
    MapImpl.impl.setOptions(jso, options.getJso());
  }

  @Override
  public void setZoom(int zoom) {
    MapImpl.impl.setZoom(jso, zoom);
  }

  @Override
  public JavaScriptObject getJso() {
    return jso;
  }

}

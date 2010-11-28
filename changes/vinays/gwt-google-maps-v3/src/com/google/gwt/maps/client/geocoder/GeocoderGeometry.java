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
package com.google.gwt.maps.client.geocoder;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.base.LatLngBounds;
import com.google.gwt.maps.client.geocoder.impl.GeocoderGeometryImpl;

/**
 * This class implements {@link HasGeocoderGeometry}.
 *
 */
public class GeocoderGeometry implements HasGeocoderGeometry {

  final private JavaScriptObject jso;
  
  public GeocoderGeometry(final JavaScriptObject jso) {
    this.jso = jso;
  }

  @Override
  public LatLngBounds getBounds() {
    JavaScriptObject bounds = GeocoderGeometryImpl.impl.getBounds(jso);
    if (bounds == null) {
      return null;
    }
    return bounds.cast();
  }

  @Override
  public LatLng getLocation() {
    return GeocoderGeometryImpl.impl.getLocation(jso).cast();
  }

  @Override
  public GeocoderLocationType getLocationType() {
    return GeocoderLocationType.valueOf(GeocoderGeometryImpl.impl.getLocationType(jso));
  }

  @Override
  public LatLngBounds getViewport() {
    return GeocoderGeometryImpl.impl.getViewport(jso).cast();
  }

  @Override
  public JavaScriptObject getJso() {
    return jso;
  }

}

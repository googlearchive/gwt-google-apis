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
import com.google.gwt.maps.client.base.HasLatLngBounds;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.base.LatLngBounds;
import com.google.gwt.maps.client.geocoder.impl.GeocoderRequestImpl;

/**
 * This class implements {@link HasGeocoderRequest}.
 *
 */
public class GeocoderRequest implements HasGeocoderRequest {

  final private JavaScriptObject jso;
  
  public GeocoderRequest(final JavaScriptObject jso) {
    this.jso = jso;
  }
  
  public GeocoderRequest() {
    this(GeocoderRequestImpl.impl.construct());
  }

  @Override
  public String getAddress() {
    return GeocoderRequestImpl.impl.getAddress(jso);
  }

  @Override
  public HasLatLngBounds getBounds() {
    return new LatLngBounds(GeocoderRequestImpl.impl.getBounds(jso));
  }

  @Override
  public String getLanguage() {
    return GeocoderRequestImpl.impl.getLanguage(jso);
  }

  @Override
  public LatLng getLatLng() {
    return GeocoderRequestImpl.impl.getLatLng(jso).cast();
  }

  @Override
  public String getRegion() {
    return GeocoderRequestImpl.impl.getRegion(jso);
  }

  @Override
  public void setAddress(String address) {
    GeocoderRequestImpl.impl.setAddress(jso, address);
  }

  @Override
  public void setBounds(HasLatLngBounds bounds) {
    GeocoderRequestImpl.impl.setBounds(jso, bounds.getJso());
  }

  @Override
  public void setLanguage(String language) {
    GeocoderRequestImpl.impl.setLanguage(jso, language);
  }

  @Override
  public void setLatLng(LatLng latLng) {
    GeocoderRequestImpl.impl.setLatLng(jso, latLng);
  }

  @Override
  public void setRegion(String region) {
    GeocoderRequestImpl.impl.setRegion(jso, region);
  }

  @Override
  public JavaScriptObject getJso() {
    return jso;
  }

}

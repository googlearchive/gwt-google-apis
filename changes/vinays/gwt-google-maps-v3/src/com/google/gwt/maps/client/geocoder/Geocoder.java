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
package com.google.gwt.maps.client.geocoder;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.geocoder.impl.GeocoderImpl;

/**
 * This class implements {@link HasGeocoder}.
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public class Geocoder implements HasGeocoder {

  final private JavaScriptObject jso;
  
  public Geocoder(final JavaScriptObject jso) {
    this.jso = jso;
  }
  
  public Geocoder() {
    this(GeocoderImpl.impl.construct());
  }

  @Override
  public void geocode(HasGeocoderRequest request, GeocoderCallback callback) {
    GeocoderImpl.impl.geocode(jso, request.getJso(), callback);
  }

  @Override
  public JavaScriptObject getJso() {
    return jso;
  }

}

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

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.geocoder.impl.GeocoderResultImpl;

/**
 * This class implements {@link HasGeocoderResult}
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public class GeocoderResult implements HasGeocoderResult {

  final private JavaScriptObject jso;
  
  public GeocoderResult(final JavaScriptObject jso) {
    this.jso = jso;
  }

  @Override
  public List<HasAddressComponent> getAddressComponents() {
    List<HasAddressComponent> acl = new ArrayList<HasAddressComponent>();
    for (JavaScriptObject ac : GeocoderResultImpl.impl.getAddressComponents(jso)) {
      acl.add(new AddressComponent(ac));
    }
    return acl;
  }
  
  @Override
  @Deprecated
  public String getFormattedAddress() {
    return GeocoderResultImpl.impl.getFormattedAddress(jso);
  }

  @Override
  public HasGeocoderGeometry getGeometry() {
    return new GeocoderGeometry(GeocoderResultImpl.impl.getGeometry(jso));
  }

  @Override
  public List<String> getTypes() {
    List<String> types = new ArrayList<String>();
    types.addAll(GeocoderResultImpl.impl.getTypes(jso));
    return types;
  }

  @Override
  public JavaScriptObject getJso() {
    return jso;
  }

}

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
package com.google.gwt.maps.client.geocode;

import com.google.gwt.core.client.JsArray;

/**
 * Used as an argument for the getLocations() method in
 * {@link com.google.gwt.maps.client.geocode.Geocoder#getLocations(String, LocationCallback)}.
 */
// TODO(samgross): better names for interface and methods.
public interface LocationCallback {

  /**
   * Called when the geocoding service fails.
   * 
   * @param statusCode a value defined in {@link StatusCodes}
   */
  void onFailure(int statusCode);

  /**
   * Called when the geocoding service returns a result successfully.
   * 
   * @param locations an array of Placemarks that match the query.
   */
  void onSuccess(JsArray<Placemark> locations);
}

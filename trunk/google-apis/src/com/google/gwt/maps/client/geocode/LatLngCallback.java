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

import com.google.gwt.maps.client.geom.LatLng;

/**
 * Used as an argument to
 * {@link com.google.gwt.maps.client.geocode.Geocoder#getLatLng(String,LatLngCallback)}.
 */
// TODO(samgross): better names for interface and methods.
// TODO(zundel): This callback interface has the same name as
// EventImpl.LatLngCallback. yuk.
public interface LatLngCallback {

  /**
   * Invoked when the geocoding call fails.
   */
  void onFailure();

  /**
   * Invoked when the geocoding call succeeds.
   * 
   * @param point the point returned from the Google geocoder server.
   */
  void onSuccess(LatLng point);
}

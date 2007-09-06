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
package com.google.gwt.maps.client.geocode;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.jsio.client.JSList;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.LatLngBounds;
import com.google.gwt.maps.client.impl.GeocoderImpl;
import com.google.gwt.maps.client.impl.GeocoderImpl.LocationsCallback;
import com.google.gwt.maps.client.impl.GeocoderImpl.Response;
import com.google.gwt.maps.client.util.JsUtil;

public final class Geocoder {

  // TODO: name: ClientGeocoder or Geocoder?

  private final JavaScriptObject jsoPeer;

  public Geocoder() {
    jsoPeer = GeocoderImpl.impl.construct();
  }

  public Geocoder(GeocodeCache cache) {
    jsoPeer = GeocoderImpl.impl.construct(cache);
  }

  // TODO: callback interfaces

  // TODO: better naming: not get* since async? find*?

  // TODO: rename one of the callback types?
  public void getLatLng(String address, final LatLngCallback callback) {
    GeocoderImpl.impl.getLatLng(jsoPeer, address,
        new com.google.gwt.maps.client.impl.EventImpl.LatLngCallback() {
          public void callback(LatLng latlng) {
            if (latlng != null) {
              callback.onSuccess(latlng);
            } else {
              callback.onFailure();
            }
          }
        });
  }

  public void getLocations(String address, final LocationCallback callback) {
    GeocoderImpl.impl.getLocations(jsoPeer, address, new LocationsCallback() {
      public void callback(Response response) {
        int statusCode = response.getStatus().getCode();
        if (statusCode == StatusCodes.SUCCESS) {
          JSList placemarkList = response.getPlacemarks();
          Placemark[] placemarks = new Placemark[placemarkList.size()];
          JsUtil.toArray(placemarkList, placemarks);
          callback.onSuccess(placemarks);
        } else {
          callback.onFailure(statusCode);
        }
      }
    });
  }
  
  static void print(JavaScriptObject o) {
    System.out.print(o);
  }

  
  static void println(JavaScriptObject o) {
    System.out.println(o);
  }

  public GeocodeCache getCache() {
    return GeocoderImpl.impl.getCache(jsoPeer);
  }

  public void setViewport(LatLngBounds bounds) {
    GeocoderImpl.impl.setViewport(jsoPeer, bounds);
  }

  public LatLngBounds getViewport() {
    return GeocoderImpl.impl.getViewport(jsoPeer);
  }

  // TODO: perhaps a resource file with country codes?

  public void setBaseCountryCode(String countryCode) {
    GeocoderImpl.impl.setBaseCountryCode(jsoPeer, countryCode);
  }

  public String getBaseCountryCode() {
    return GeocoderImpl.impl.getBaseCountryCode(jsoPeer);
  }

  // TODO: reset???

  public void reset() {
    GeocoderImpl.impl.reset(jsoPeer);
  }

}

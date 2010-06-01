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

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.LatLngBounds;
import com.google.gwt.maps.client.impl.GeocoderImpl;
import com.google.gwt.maps.client.impl.GeocoderImpl.LocationsCallback;
import com.google.gwt.maps.client.impl.GeocoderImpl.Response;

/**
 * A class for Geocoding Addresses through Google's Geocoding service over the
 * Internet.
 */
public final class Geocoder {

  private final JavaScriptObject jsoPeer;

  /**
   * Creates a new instance of a geocoder that talks directly to Google servers.
   * A {@link FactualGeocodeCache} is used for results caching.
   */
  public Geocoder() {
    jsoPeer = GeocoderImpl.impl.construct();
  }

  /**
   * Create a new geocoder object allowing a custom cache to be used. When
   * possible, aggressive caching is greatly encouraged as the geocoding service
   * is computationally expensive and subject to daily limits by Google servers.
   * 
   * @param cache An instance of a cache to associate with the geocoder.
   * 
   * @see FactualGeocodeCache
   */
  public Geocoder(GeocodeCache cache) {
    jsoPeer = GeocoderImpl.impl.construct(cache);
  }

  /**
   * Returns the current country code in use by the given geocoder. (If no
   * country code is in effect, this method returns <code>null</code>.)
   * 
   * @return the current country code in use by the given geocoder.
   */
  public String getBaseCountryCode() {
    return GeocoderImpl.impl.getBaseCountryCode(jsoPeer);
  }

  /**
   * Returns the currently used geocode cache.
   * 
   * @return currently used geocode cache, or <code>null</code>, if no
   *         client-side caching is performed.
   */
  public GeocodeCache getCache() {
    return GeocoderImpl.impl.getCache(jsoPeer);
  }

  /**
   * Sends a request to Google servers to geocode the specified address. If the
   * address was successfully located, the user-specified callback function is
   * invoked with a LatLng point. Otherwise, the callback function is given a
   * <code>null</code> point. In case of ambiguous addresses, only the point for
   * the best match is passed to the callback function.
   * 
   * @param address the address to search for.
   * @param callback methods to call when the query returns.
   */
  // TODO(samgross): better naming: not get* since async? find*?
  // TODO(samgross): rename one of the callback types?
  public void getLatLng(String address, final LatLngCallback callback) {
    GeocoderImpl.impl.getLatLng(jsoPeer, address,
        new com.google.gwt.maps.client.impl.EventImpl.LatLngCallback() {
          @Override
          public void callback(LatLng latlng) {
            fireLatLng(callback, latlng != null, latlng);
          }
        });
  }

  /**
   * Sends a request to Google servers to geocode the specified address. A reply
   * that contains status code, and if successful, one or more {@link Placemark}
   * objects, is passed to the user-specified callback function. Unlike the
   * {@link Geocoder#getLatLng(String,LatLngCallback)} method, the callback
   * function may determine the reasons for failure by examining the code value
   * of the Status field.
   * 
   * @param address the address to search for.
   * @param callback methods to call when the query returns.
   */
  public void getLocations(String address, final LocationCallback callback) {
    GeocoderImpl.impl.getLocations(jsoPeer, address, new LocationsCallback() {
      @Override
      public void callback(Response response) {
        int statusCode = response.getStatus().getCode();
        if (statusCode == StatusCodes.SUCCESS) {
          JsArray<Placemark> placemarks = response.getPlacemarks();
          fireLocationCb(callback, true, statusCode, placemarks);
        } else {
          fireLocationCb(callback, false, statusCode, null);
        }
      }
    });
  }

  /**
   * Sends a request to Google servers to reverse geocode the specified point. A
   * reply that contains status code, and if successful, one or more
   * {@link Placemark} objects, is passed to the user-specified callback
   * function. The callback function may determine the reasons for failure by
   * examining the code value of the Status field.
   * 
   * @param point the point to search for.
   * @param callback methods to call when the query returns.
   */
  public void getLocations(LatLng point, final LocationCallback callback) {
    GeocoderImpl.impl.getLocations(jsoPeer, point, new LocationsCallback() {
      @Override
      public void callback(Response response) {
        int statusCode = response.getStatus().getCode();
        if (statusCode == StatusCodes.SUCCESS) {
          JsArray<Placemark> placemarks = response.getPlacemarks();
          fireLocationCb(callback, true, statusCode, placemarks);
        } else {
          fireLocationCb(callback, false, statusCode, null);
        }
      }
    });
  }

  /**
   * Returns the viewport for magnifying geocoding results within that geocoder.
   */
  public LatLngBounds getViewport() {
    return GeocoderImpl.impl.getViewport(jsoPeer);
  }

  /**
   * Resets the geocoder. In particular this method resets the client-side
   * cache, if one is used by this geocoder.
   */
  public void reset() {
    GeocoderImpl.impl.reset(jsoPeer);
  }

  /**
   * Sets the geocoder to bias search results as if they were sent from the
   * domain specified by the given country code top-level domain (ccTLD).
   * Geocoding is only supported for those countries in which Google Maps itself
   * supports geocoding. Most ccTLD codes are identical to ISO 3166-1 codes,
   * with some notable exceptions. For example, Great Britain's top-level
   * Internet domain (ccTLD) is "uk" (.co.uk) while its ISO 3166-1 code is "GR."
   * Note that the default domain is the domain from which you initially load
   * the Maps API.
   * 
   * @param countryCode country code top-level domain (ccTLD). Country codes are
   *          case insensitive.
   */
  // TODO(samgross): perhaps a resource file with country codes?
  public void setBaseCountryCode(String countryCode) {
    GeocoderImpl.impl.setBaseCountryCode(jsoPeer, countryCode);
  }

  /**
   * Sets the geocoder to magnify geocoding results within or near the given
   * viewport. . Note that setting a viewport does not restrict results to that
   * bounding box, though it will elevate them in priority.
   * 
   * @param bounds the viewport expressed as a {@link LatLngBounds} rectangle
   */
  public void setViewport(LatLngBounds bounds) {
    GeocoderImpl.impl.setViewport(jsoPeer, bounds);
  }

  /**
   * Wrapper to invoke LatLngCallbacks surrounded in the
   * UncaughtExceptionHandler.
   */
  private void fireLatLng(LatLngCallback cb, boolean success, LatLng point) {
    UncaughtExceptionHandler handler = GWT.getUncaughtExceptionHandler();
    if (handler != null) {
      fireLatLngAndCatch(handler, cb, success, point);
    } else {
      fireLatLngImpl(cb, success, point);
    }
  }

  private void fireLatLngAndCatch(UncaughtExceptionHandler handler,
      LatLngCallback cb, boolean success, LatLng point) {
    try {
      fireLatLngImpl(cb, success, point);
    } catch (Throwable e) {
      handler.onUncaughtException(e);
    }
  }

  private void fireLatLngImpl(LatLngCallback cb, boolean success, LatLng point) {
    // Run the callback's code.
    if (success) {
      cb.onSuccess(point);
    } else {
      cb.onFailure();
    }
  }

  private void fireLocationAndCatch(UncaughtExceptionHandler handler,
      LocationCallback cb, boolean success, int statusCode,
      JsArray<Placemark> placemarks) {
    try {
      fireLocationImpl(cb, success, statusCode, placemarks);
    } catch (Throwable e) {
      handler.onUncaughtException(e);
    }
  }

  /**
   * Wrapper to invoke LocationCallbacks surrounded in the
   * UncaughtExceptionHandler.
   */
  private void fireLocationCb(LocationCallback cb, boolean success,
      int statusCode, JsArray<Placemark> placemarks) {
    UncaughtExceptionHandler handler = GWT.getUncaughtExceptionHandler();
    if (handler != null) {
      fireLocationAndCatch(handler, cb, success, statusCode, placemarks);
    } else {
      fireLocationImpl(cb, success, statusCode, placemarks);
    }
  }

  private void fireLocationImpl(LocationCallback cb, boolean success,
      int statusCode, JsArray<Placemark> placemarks) {
    // Run the callback's code.
    if (success) {
      cb.onSuccess(placemarks);
    } else {
      cb.onFailure(statusCode);
    }
  }
}

/*
 * Copyright 2010 Google Inc.
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
package com.google.gwt.maps.client.streetview;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.impl.EventImpl;
import com.google.gwt.maps.client.impl.StreetviewClientImpl;

/**
 * A class to perform searches for Street View data based on parameters that are
 * passed to its methods.
 *
 * @see "http://code.google.com/apis/maps/documentation/reference.html#GStreetviewClient"
 */
public class StreetviewClient {

  /**
   * An enum equivalents for return codes returned by
   * {@link StreetviewData#getCode()}.
   *
   * @see "http://code.google.com/apis/maps/documentation/reference.html#GStreetviewClient.ReturnValues"
   */
  public static enum ReturnValue {
    SUCCESS(200), SERVER_ERROR(500), NO_NEARBY_PANO(600);

    /**
     * Returns enum value for the passed return code or <code>null</code> if
     * return code is unknown.
     *
     * @param returnCode return code.
     * @return enum value for the passed return code or <code>null</code> if
     *         return code is unknown.
     */
    public static ReturnValue getByReturnCode(int returnCode) {
      for (ReturnValue value : ReturnValue.values()) {
        if (returnCode == value.getReturnCode()) {
          return value;
        }
      }
      return null;
    }

    private int returnCode;

    ReturnValue(int returnCode) {
      this.returnCode = returnCode;
    }

    /**
     * Returns return code.
     *
     * @return return code.
     */
    public int getReturnCode() {
      return returnCode;
    }
  }

  private final JavaScriptObject jsoPeer;

  /**
   * Creates a new instance of {@link StreetviewClient} class.
   */
  public StreetviewClient() {
    jsoPeer = StreetviewClientImpl.impl.construct();
    StreetviewClientImpl.impl.bind(jsoPeer, this);
  }

  /**
   * Retrieves the data for the nearest panorama to a given {@link LatLng} and
   * passes it to the provided callback as a {@link StreetviewData} object.
   *
   * @param latLng the geographical coordinates
   * @param callback the callback to fire when the query returns
   */
  public void getNearestPanorama(LatLng latLng,
      final DataStreetviewCallback callback) {
    StreetviewClientImpl.impl.getNearestPanorama(jsoPeer, latLng,
        new EventImpl.StreetviewDataCallback() {
          @Override
          public void callback(StreetviewData data) {
            callback.onDone(data);
          }
        });
  };

  /**
   * Finds the {@link LatLng} of the nearest panorama to a given point and
   * passes it to the provided callback.
   *
   * @param latLng the geographical coordinates
   * @param callback the callback to fire when the query returns
   */
  public void getNearestPanoramaLatLng(LatLng latLng,
      final LatLngStreetviewCallback callback) {
    StreetviewClientImpl.impl.getNearestPanoramaLatLng(jsoPeer, latLng,
        new EventImpl.LatLngCallback() {
          @Override
          public void callback(LatLng latlng) {
            callback.fire(latlng);
          }
        });
  };

  /**
   * Retrieves the data for the given panorama id and passes it to the provided
   * callback as a {@link StreetviewData} object. Ids are unique per panorama
   * and stable for the lifetime of a session, but are liable to change between
   * sessions.
   *
   * @param panoId unique panorama id
   * @param callback the callback to fire when the query returns
   */
  public void getPanoramaById(String panoId,
      final DataStreetviewCallback callback) {
    StreetviewClientImpl.impl.getPanoramaById(jsoPeer, panoId,
        new EventImpl.StreetviewDataCallback() {
          @Override
          public void callback(StreetviewData data) {
            callback.onDone(data);
          }
        });
  };
}

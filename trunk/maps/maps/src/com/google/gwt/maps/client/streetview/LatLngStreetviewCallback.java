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

import com.google.gwt.maps.client.geom.LatLng;

/**
 * An abstract class to be extended in order to use with
 * {@link StreetviewClient#getNearestPanoramaLatLng(LatLng, LatLngStreetviewCallback)}
 */
public abstract class LatLngStreetviewCallback {

  public final void fire(LatLng point) {
    if (point != null) {
      onSuccess(point);
    } else {
      onFailure();
    }
  }

  /**
   * Called when {@link StreetviewClient} query fails.
   */
  public abstract void onFailure();

  /**
   * Called when {@link StreetviewClient} query finishes successfully.
   *
   * @param point coordinates of the found panorama.
   */
  public abstract void onSuccess(LatLng point);
}

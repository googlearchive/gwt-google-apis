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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.geom.LatLngBounds;
import com.google.gwt.maps.client.impl.DirectionsImpl;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.Polyline;

import java.util.AbstractList;
import java.util.List;

/**
 * This class provides encapsulation for the JavaScript Maps API GDirections
 * methods that should be used after a query successfully returns.
 */
public final class DirectionResults {

  private final JavaScriptObject jsoPeer;

  DirectionResults(JavaScriptObject jsoPeer) {
    this.jsoPeer = jsoPeer;
  }

  /**
   * Clears any existing directions results, removes overlays from the map and
   * panel, and cancels any pending load() requests.
   */
  public void clear() {
    DirectionsImpl.impl.clear(jsoPeer);
  }

  /**
   * This method is used to get the bounding box for the result of this
   * directions query. Returns a LatLngBounds object or null if no successful
   * result is available.
   * 
   * @return the bounding box for the result of the directions query.
   */
  public LatLngBounds getBounds() {
    return DirectionsImpl.impl.getBounds(jsoPeer);
  }

  /**
   * Returns an HTML string containing the copyright information for this
   * result.
   * 
   * @return an HTML string containing the copyright information for this
   *         result.
   */
  public String getCopyrightsHtml() {
    return DirectionsImpl.impl.getCopyrightsHtml(jsoPeer);
  }

  /**
   * Returns an object literal representing the total distance of the directions
   * request (across all routes).
   * 
   * @return The distance including number of meters and localized string
   *         representation of the distance in localized units.
   */
  public Distance getDistance() {
    return DirectionsImpl.impl.getDistance(jsoPeer);
  }

  /**
   * Returns an object representing the total time of the directions request
   * (across all routes).
   * 
   * @return the estimated travel time in seconds and in a string version
   *         containing a localized representation of the time.
   */
  public Duration getDuration() {
    return DirectionsImpl.impl.getDuration(jsoPeer);
  }

  /**
   * Returns the list of Markers associated with the geocode.
   * 
   * @return the list of Markers associated with the geocode.
   */
  public List<Marker> getMarkers() {
    return new AbstractList<Marker>() {
      @Override
      public Marker get(int i) {
        return DirectionsImpl.impl.getMarker(jsoPeer, i);
      }

      @Override
      public int size() {
        return DirectionsImpl.impl.getNumGeocodes(jsoPeer);
      }
    };
  }

  /**
   * Returns the list of the geocoded results.
   * 
   * @return list of the geocoded results.
   */
  public List<Placemark> getPlacemarks() {
    return new AbstractList<Placemark>() {
      @Override
      public Placemark get(int i) {
        return DirectionsImpl.impl.getGeocode(jsoPeer, i);
      }

      @Override
      public int size() {
        return DirectionsImpl.impl.getNumGeocodes(jsoPeer);
      }
    };
  }

  /**
   * Returns the Polyline object associated with the entire directions response.
   * Note that there is a single polyline that represents all the routes in the
   * response.
   * 
   * @return the Polyline object associated with the entire directions response.
   */
  // TODO(zundel): this method fails with a CastClassException from the
  // generated JSIO method DirectionsImplImpl.getPolyline()
  public Polyline getPolyline() {
    Object o = nativeGetGwtPeerAsObject(jsoPeer);
    return DirectionsImpl.impl.getPolyline(jsoPeer);
  }

  /**
   * Returns the list of Route objects in the response.
   * 
   * @return the list of Route objects in the response.
   */
  public List<Route> getRoutes() {
    return new AbstractList<Route>() {
      @Override
      public Route get(int i) {
        return DirectionsImpl.impl.getRoute(jsoPeer, i);
      }

      @Override
      public int size() {
        return DirectionsImpl.impl.getNumRoutes(jsoPeer);
      }
    };
  }

  /**
   * Returns an HTML snippet containing a summary of the distance and time for
   * this entire directions request.
   * 
   * @return an HTML snippet containing a summary of the distance and time for
   *         this entire directions request.
   */
  public String getSummaryHtml() {
    return DirectionsImpl.impl.getSummaryHtml(jsoPeer);
  }

  private native Object nativeGetGwtPeerAsObject(JavaScriptObject jsoPeer) /*-{
    return jsoPeer.__gwtPeer;
  }-*/;
}
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
import com.google.gwt.maps.client.impl.DirectionsImpl;
import com.google.gwt.maps.client.impl.EventImpl;
import com.google.gwt.maps.client.impl.JsUtil;
import com.google.gwt.maps.client.impl.MapEvent;
import com.google.gwt.maps.client.impl.DirectionsImpl.Status;
import com.google.gwt.maps.client.impl.EventImpl.VoidCallback;
import com.google.gwt.maps.jsio.client.JSList;

// TODO(samgross): note "load", "error", and "addoverlay" semi-documented events

/**
 * This class is used to obtain driving directions results and display them on a
 * map and/or a text panel.
 */
public final class Directions {

  /**
   * Load a new Directions query.
   * 
   * @param query a string containing any valid directions query, e.g. "from:
   *          Seattle to: San Francisco" or "from: Toronto to: Ottawa to: New
   *          York".
   * @param options optional parameters to use with the query.
   */
  public static void load(String query, DirectionQueryOptions options) {
    load(query, options, null);
  }

  /**
   * Load a new Directions query.
   * 
   * @param query a string containing any valid directions query, e.g. "from:
   *          Seattle to: San Francisco" or "from: Toronto to: Ottawa to: New
   *          York".
   * @param options optional parameters to use with the query.
   * @param callback methods to call when the load() succeeds or fails.
   */
  public static void load(String query, DirectionQueryOptions options,
      DirectionsCallback callback) {
    JavaScriptObject jsoPeer = createDirections(options);
    if (callback != null) {
      addLoadListener(jsoPeer, callback);
    }
    DirectionsImpl.impl.load(jsoPeer, query, options);
  }

  /**
   * Load a new Directions query.
   * 
   * @param query a string containing any valid directions query, e.g. "from:
   *          Seattle to: San Francisco" or "from: Toronto to: Ottawa to: New
   *          York".
   * @param callback methods to call when the load() succeeds or fails.
   */
  public static void load(String query, DirectionsCallback callback) {
    load(query, null, callback);
  }

  /**
   * Load a new directions query using an array of waypoints as input instead of
   * a single query string.
   * 
   * @param waypoints an array of waypoints.
   * @param options optional parameters to use with the query.
   * 
   * @see Directions#load(String,DirectionQueryOptions)
   */
  public static void loadFromWaypoints(Waypoint[] waypoints,
      DirectionQueryOptions options) {
    loadFromWaypoints(waypoints, options, null);
  }

  /**
   * Load a new directions query using an array of waypoints as input instead of
   * a single query string.
   * 
   * @param waypoints Array of waypoints.
   * @param options optional parameters to use with the query.
   * @param callback methods to call when the load() succeeds or fails.
   * 
   * @see Directions#load(String,DirectionQueryOptions,DirectionsCallback)
   */
  public static void loadFromWaypoints(Waypoint[] waypoints,
      DirectionQueryOptions options, DirectionsCallback callback) {
    JavaScriptObject jsoPeer = createDirections(options);
    // Coerce the waypoints into the appropriate type to pass down to
    // JavaScript.
    String[] waypointStrings = new String[waypoints.length];
    for (int i = 0; i < waypoints.length; ++i) {
      waypointStrings[i] = waypoints[i].toString();
    }
    JSList<String> waypointStringList = JsUtil.toJsList(waypointStrings);
    DirectionsImpl.impl.loadFromWaypoints(jsoPeer, waypointStringList, options);
    if (callback != null) {
      addLoadListener(jsoPeer, callback);
    }
  }

  /**
   * Load a new directions query using an array of waypoints as input instead of
   * a single query string.
   * 
   * @param waypoints an array of waypoints.
   * @param callback methods to call when the load() succeeds or fails.
   * 
   * @see Directions#load(String,DirectionsCallback)
   */
  public static void loadFromWaypoints(Waypoint[] waypoints,
      DirectionsCallback callback) {
    loadFromWaypoints(waypoints, null, callback);
  }

  /**
   * Associate a callback with a "load" event. Used to implement
   * DirectionsCallback logic for load() and loadFromWaypoints()
   * 
   * @param jsoPeer A JavaScriptObject to associate the "load" event handle
   *          with.
   * @param callback A method to call when the "load" event fires.
   */
  private static void addLoadListener(final JavaScriptObject jsoPeer,
      final DirectionsCallback callback) {

    EventImpl.impl.addListenerVoid(jsoPeer, MapEvent.LOAD, new VoidCallback() {
      @Override
      public void callback() {
        Status status = DirectionsImpl.impl.getStatus(jsoPeer);
        int statusCode = StatusCodes.API_ERROR;
        if (status != null) {
          statusCode = status.getCode();
        }
        if (statusCode == StatusCodes.SUCCESS) {
          DirectionResults result = new DirectionResults(jsoPeer);
          callback.onSuccess(result);
        } else {
          callback.onFailure(statusCode);
        }
      }
    });

    EventImpl.impl.addListenerVoid(jsoPeer, MapEvent.ERROR, new VoidCallback() {
      @Override
      public void callback() {
        Status status = DirectionsImpl.impl.getStatus(jsoPeer);
        int statusCode = StatusCodes.API_ERROR;
        if (status != null) {
          statusCode = status.getCode();
        }
        callback.onFailure(statusCode);
      }
    });
  }

  /**
   * A factory method to create a new Directions object.
   * 
   * @param options optional parameters to use with the query.
   * @return a new instance of a Directions object.
   */
  private static JavaScriptObject createDirections(DirectionQueryOptions options) {
    if (options == null) {
      return DirectionsImpl.impl.construct(null, null);
    } else if (options.panel != null) {
      return DirectionsImpl.impl.construct(options.map,
          options.panel.getElement());
    } else {
      return DirectionsImpl.impl.construct(options.map, null);
    }
  }

  // This class is not meant to be instantiated by users.
  private Directions() {
  }
}

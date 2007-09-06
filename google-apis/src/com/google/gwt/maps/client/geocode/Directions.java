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
import com.google.gwt.maps.client.impl.DirectionsImpl;
import com.google.gwt.maps.client.impl.EventImpl;
import com.google.gwt.maps.client.impl.EventImpl.VoidCallback;
import com.google.gwt.maps.client.util.JsUtil;

// TODO: note "load", "error", and "addoverlay" semi-documented events

// TODO: do we want to create a nd GDirections object for every load and
// provide an asyncrhonous callback?

// TODO: should status codes be refactored out into their own class?

/**
 * 
 */
public final class Directions {

  public static void load(String query, DirectionQueryOptions options) {
    load(query, options, null);
  }

  public static void load(String query, DirectionQueryOptions options,
      DirectionsCallback callback) {
    JavaScriptObject jsoPeer = createDirections(options);
    DirectionsImpl.impl.load(jsoPeer, query, options);
    if (callback != null) {
      addLoadListener(jsoPeer, callback);
    }
  }

  public static void load(String query, DirectionsCallback callback) {
    load(query, null, callback);
  }

  public static void loadFromWaypoints(Waypoint[] waypoints,
      DirectionQueryOptions options) {
    loadFromWaypoints(waypoints, options, null);
  }

  public static void loadFromWaypoints(Waypoint[] waypoints,
      DirectionQueryOptions options, DirectionsCallback callback) {
    JavaScriptObject jsoPeer = createDirections(options);
    DirectionsImpl.impl.loadFromWaypoints(jsoPeer, JsUtil.toJsList(waypoints),
        options);
    if (callback != null) {
      addLoadListener(jsoPeer, callback);
    }
  }

  public static void loadFromWaypoints(Waypoint[] waypoints,
      DirectionsCallback callback) {
    loadFromWaypoints(waypoints, null, callback);
  }

  private static void addLoadListener(final JavaScriptObject jsoPeer,
      final DirectionsCallback callback) {
    EventImpl.impl.addListenerVoid(jsoPeer, "load", new VoidCallback() {
      public void callback() {
        int statusCode = DirectionsImpl.impl.getStatusCode(jsoPeer);
        if (statusCode == StatusCodes.SUCCESS) {
          DirectionResults result = new DirectionResults(jsoPeer);
          callback.onSuccess(result);
        } else {
          callback.onFailure(statusCode);
        }
      }
    });
  }

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

  private Directions() {
  }

}

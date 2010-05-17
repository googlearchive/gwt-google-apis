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
package com.google.gwt.maps.client.impl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.impl.EventImpl.LatLngCallback;
import com.google.gwt.maps.client.impl.EventImpl.StreetviewDataCallback;
import com.google.gwt.maps.client.streetview.StreetviewClient;
import com.google.gwt.maps.jsio.client.Binding;
import com.google.gwt.maps.jsio.client.Constructor;
import com.google.gwt.maps.jsio.client.JSFlyweightWrapper;

/**
 * Wrapper for the GStreetviewClient object from the Maps API using JSIO.
 */
public interface StreetviewClientImpl extends JSFlyweightWrapper {
  StreetviewClientImpl impl = GWT.create(StreetviewClientImpl.class);

  @Binding
  void bind(JavaScriptObject jsoPeer, StreetviewClient client);

  @Constructor("$wnd.GStreetviewClient")
  JavaScriptObject construct();

  void getNearestPanorama(JavaScriptObject jsoPeer, LatLng latLng,
      StreetviewDataCallback streetviewDataCallback);

  void getNearestPanoramaLatLng(JavaScriptObject jsoPeer, LatLng latLng,
      LatLngCallback callback);

  void getPanoramaById(JavaScriptObject jsoPeer, String panoId,
      StreetviewDataCallback streetviewDataCallback);
}

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
package com.google.gwt.maps.client.impl;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.jsio.client.JSFlyweightWrapper;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.jsio.client.Constructor;
import com.google.gwt.maps.client.geom.LatLngBounds;

/**
 * 
 */
public interface LatLngBoundsImpl extends JSFlyweightWrapper {

  @Constructor("$wnd.GLatLngBounds")
  JavaScriptObject construct();

  @Constructor("$wnd.GLatLngBounds")
  JavaScriptObject construct(LatLng southWest, LatLng northEast);

  boolean contains(JavaScriptObject jsoPeer, LatLng coordinate);

  boolean containsBounds(JavaScriptObject jsoPeer, LatLngBounds other);

  boolean equals(JavaScriptObject jsoPeer, LatLngBounds other);

  void extend(JavaScriptObject jsoPeer, LatLng coordinate);

  LatLng getCenter(JavaScriptObject jsoPeer);

  LatLng getNorthEast(JavaScriptObject jsoPeer);

  LatLng getSouthWest(JavaScriptObject jsoPeer);

  boolean intersects(JavaScriptObject jsoPeer, LatLngBounds other);

  boolean isEmpty(JavaScriptObject jsoPeer);

  boolean isFullLat(JavaScriptObject jsoPeer);

  boolean isFullLng(JavaScriptObject jsoPeer);

  LatLng toSpan(JavaScriptObject jsoPeer);

}

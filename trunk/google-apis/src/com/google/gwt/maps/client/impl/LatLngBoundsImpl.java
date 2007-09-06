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
package com.google.gwt.maps.client.impl;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.jsio.client.JSFlyweightWrapper;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.LatLngBounds;

public interface LatLngBoundsImpl extends JSFlyweightWrapper {

  /**
   * @gwt.constructor $wnd.GLatLngBounds
   */
  public JavaScriptObject construct(LatLng southWest, LatLng northEast);

  public boolean equals(JavaScriptObject jsoPeer, LatLngBounds other);

  public boolean contains(JavaScriptObject jsoPeer, LatLng coordinate);

  public boolean intersects(JavaScriptObject jsoPeer, LatLngBounds other);

  public boolean containsBounds(JavaScriptObject jsoPeer, LatLngBounds other);

  public void extend(JavaScriptObject jsoPeer, LatLng coordinate);

  public LatLng getSouthWest(JavaScriptObject jsoPeer);

  public LatLng getNorthEast(JavaScriptObject jsoPeer);

  public LatLng toSpan(JavaScriptObject jsoPeer);

  public boolean isFullLat(JavaScriptObject jsoPeer);

  public boolean isFullLng(JavaScriptObject jsoPeer);

  public boolean isEmpty(JavaScriptObject jsoPeer);

  public LatLng getCenter(JavaScriptObject jsoPeer);

}

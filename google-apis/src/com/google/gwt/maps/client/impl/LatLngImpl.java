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

public interface LatLngImpl extends JSFlyweightWrapper {

  /**
   * @gwt.constructor $wnd.GLatLng
   */
  public JavaScriptObject construct(double latitude, double longitude);

  /**
   * @gwt.constructor $wnd.GLatLng
   */
  public JavaScriptObject construct(double latitude, double longitude,
      boolean unbounded);

  public double distanceFrom(JavaScriptObject jso, LatLng other);

  public boolean equals(JavaScriptObject jso, LatLng other);

  public double lat(JavaScriptObject jso);

  public double latRadians(JavaScriptObject jso);

  public double lng(JavaScriptObject jso);

  public double lngRadians(JavaScriptObject jso);

  public String toString(JavaScriptObject jso);

  public String toUrlValue(JavaScriptObject jso, int precision);

}

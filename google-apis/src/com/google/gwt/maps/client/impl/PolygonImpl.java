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

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.jsio.client.JSFlyweightWrapper;
import com.google.gwt.jsio.client.JSList;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Polygon;

public interface PolygonImpl extends JSFlyweightWrapper {

  public static final PolygonImpl impl = (PolygonImpl) GWT.create(PolygonImpl.class);

  /**
   * @gwt.constructor $wnd.GPolygon
   */
  public JavaScriptObject construct(JSList /* LatLng[] */points,
      String strokeColor, int strokeWeight, double strokeOpacity,
      String fillColor, double fillOpacity);

  /**
   * @gwt.constructor $wnd.GPolygon
   */
  public JavaScriptObject construct(JSList /* LatLng[] */points);

  public int getVertextCount(Polygon polygon);

  public LatLng getVertex(Polygon polygon, int index);
}

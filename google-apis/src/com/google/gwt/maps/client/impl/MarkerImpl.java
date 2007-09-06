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
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Icon;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;

public interface MarkerImpl extends JSFlyweightWrapper {

  public static final MarkerImpl impl = (MarkerImpl) GWT.create(MarkerImpl.class);

  /**
   * @gwt.constructor $wnd.GMarker
   */
  public JavaScriptObject construct(LatLng point);

  /**
   * @gwt.constructor $wnd.GMarker
   */
  public JavaScriptObject construct(LatLng point, MarkerOptions options);

  public Icon getIcon(Marker marker);

  public LatLng getPoint(Marker marker);

  public boolean isVisible(Marker marker);

  public void openInfoWindow(Marker marker, JavaScriptObject content,
      JavaScriptObject options);

  public void openInfoWindowTabs(Marker marker, JavaScriptObject content,
      JavaScriptObject options);

  public void setImage(Marker marker, String url);

  public void setPoint(Marker marker, LatLng point);

  public void setVisible(Marker marker, boolean visible);

  public void showMapBlowup(Marker marker, JavaScriptObject options);

}

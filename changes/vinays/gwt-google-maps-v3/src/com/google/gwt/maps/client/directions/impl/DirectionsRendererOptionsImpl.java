/* Copyright (c) 2010 Vinay Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.gwt.maps.client.directions.impl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.jsio.client.BeanProperties;
import com.google.gwt.jsio.client.Constructor;
import com.google.gwt.jsio.client.JSFlyweightWrapper;

/**
 * 
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
@BeanProperties
public interface DirectionsRendererOptionsImpl extends JSFlyweightWrapper {
  
  DirectionsRendererOptionsImpl impl = GWT.create(DirectionsRendererOptionsImpl.class);
  
  @Constructor("Object")
  JavaScriptObject construct();

  public JavaScriptObject getDirections(JavaScriptObject jso);

  public boolean isHideRouteList(JavaScriptObject jso);

  public JavaScriptObject getMap(JavaScriptObject jso);

  public JavaScriptObject getMarkerOptions(JavaScriptObject jso);

  public JavaScriptObject getPanel(JavaScriptObject jso);

  public JavaScriptObject getPolylineOptions(JavaScriptObject jso);

  public boolean isPreserveViewport(JavaScriptObject jso);

  public int getRouteIndex(JavaScriptObject jso);

  public boolean isSuppressInfoWindows(JavaScriptObject jso);

  public boolean isSuppressMarkers(JavaScriptObject jso);

  public boolean isSuppressPolylines(JavaScriptObject jso);

  public void setDirections(JavaScriptObject jso, JavaScriptObject directions);

  public void setHideRouteList(JavaScriptObject jso, boolean hideRouteList);

  public void setMap(JavaScriptObject jso, JavaScriptObject map);

  public void setMarkerOptions(JavaScriptObject jso, JavaScriptObject markerOptions);

  public void setPanel(JavaScriptObject jso, JavaScriptObject markerOptions);

  public void setPolylineOptions(JavaScriptObject jso, JavaScriptObject polylineOptions);

  public void setPreserveViewport(JavaScriptObject jso, boolean preserveViewport);

  public void setRouteIndex(JavaScriptObject jso, int routeIndex);

  public void setSuppressInfoWindows(JavaScriptObject jso, boolean suppressInfoWindows);

  public void setSuppressMarkers(JavaScriptObject jso, boolean suppressMarkers);

  public void setSuppressPolylines(JavaScriptObject jso, boolean suppressPolylines);

}

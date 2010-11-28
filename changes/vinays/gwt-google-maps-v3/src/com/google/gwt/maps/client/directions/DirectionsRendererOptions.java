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
package com.google.gwt.maps.client.directions;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.Map;
import com.google.gwt.maps.client.base.ElementProvider;
import com.google.gwt.maps.client.base.HasElementProvider;
import com.google.gwt.maps.client.directions.impl.DirectionsRendererOptionsImpl;
import com.google.gwt.maps.client.overlay.HasMarkerOptions;
import com.google.gwt.maps.client.overlay.HasPolylineOptions;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.maps.client.overlay.PolylineOptions;
import com.google.gwt.user.client.Element;

/**
 * 
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public class DirectionsRendererOptions implements HasDirectionsRendererOptions {
  
  final private JavaScriptObject jso;

  public DirectionsRendererOptions(JavaScriptObject jso) {
    super();
    this.jso = jso;
  }
  
  public DirectionsRendererOptions() {
    this(DirectionsRendererOptionsImpl.impl.construct());
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.directions.HasDirectionsRendererOptions#getDirections()
   */
  @Override
  public HasDirectionsResult getDirections() {
    return new DirectionsResult(DirectionsRendererOptionsImpl.impl.getDirections(jso));
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.directions.HasDirectionsRendererOptions#getMap()
   */
  @Override
  public Map getMap() {
    return DirectionsRendererOptionsImpl.impl.getMap(jso).cast();
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.directions.HasDirectionsRendererOptions#getMarkerOptions()
   */
  @Override
  public HasMarkerOptions getMarkerOptions() {
    return new MarkerOptions(DirectionsRendererOptionsImpl.impl.getMarkerOptions(jso));
  }
  
  @Override
  public HasElementProvider getPanel() {
    return new ElementProvider((Element) DirectionsRendererOptionsImpl.impl.getPanel(jso));
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.directions.HasDirectionsRendererOptions#getPolylineOptions()
   */
  @Override
  public HasPolylineOptions getPolylineOptions() {
    return new PolylineOptions(DirectionsRendererOptionsImpl.impl.getPolylineOptions(jso));
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.directions.HasDirectionsRendererOptions#getRouteIndex()
   */
  @Override
  public int getRouteIndex() {
    return DirectionsRendererOptionsImpl.impl.getRouteIndex(jso);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.directions.HasDirectionsRendererOptions#isHideRouteList()
   */
  @Override
  public boolean isHideRouteList() {
    return DirectionsRendererOptionsImpl.impl.isHideRouteList(jso);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.directions.HasDirectionsRendererOptions#isPreserveViewport()
   */
  @Override
  public boolean isPreserveViewport() {
    return DirectionsRendererOptionsImpl.impl.isPreserveViewport(jso);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.directions.HasDirectionsRendererOptions#isSuppressInfoWindows()
   */
  @Override
  public boolean isSuppressInfoWindows() {
    return DirectionsRendererOptionsImpl.impl.isSuppressInfoWindows(jso);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.directions.HasDirectionsRendererOptions#isSuppressMarkers()
   */
  @Override
  public boolean isSuppressMarkers() {
    return DirectionsRendererOptionsImpl.impl.isSuppressMarkers(jso);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.directions.HasDirectionsRendererOptions#isSuppressPolylines()
   */
  @Override
  public boolean isSuppressPolylines() {
    return DirectionsRendererOptionsImpl.impl.isSuppressPolylines(jso);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.directions.HasDirectionsRendererOptions#setDirections(com.google.gwt.maps.client.directions.HasDirectionsResult)
   */
  @Override
  public void setDirections(HasDirectionsResult directions) {
    DirectionsRendererOptionsImpl.impl.setDirections(jso, directions.getJso());
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.directions.HasDirectionsRendererOptions#setHideRouteList(boolean)
   */
  @Override
  public void setHideRouteList(boolean hideRouteList) {
    DirectionsRendererOptionsImpl.impl.setHideRouteList(jso, hideRouteList);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.directions.HasDirectionsRendererOptions#setMap(com.google.gwt.maps.client.HasMap)
   */
  @Override
  public void setMap(Map map) {
    if (map == null) {
      DirectionsRendererOptionsImpl.impl.setPanel(jso, null);
      return;
    }
    DirectionsRendererOptionsImpl.impl.setMap(jso, map);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.directions.HasDirectionsRendererOptions#setMarkerOptions(com.google.gwt.maps.client.overlay.HasMarkerOptions)
   */
  @Override
  public void setMarkerOptions(HasMarkerOptions markerOptions) {
    if (markerOptions == null) {
      DirectionsRendererOptionsImpl.impl.setPanel(jso, null);
      return;
    }
    DirectionsRendererOptionsImpl.impl.setMarkerOptions(jso, markerOptions.getJso());
  }
  
  @Override
  public void setPanel(HasElementProvider panel) {
    if (panel == null) {
      DirectionsRendererOptionsImpl.impl.setPanel(jso, null);
      return;
    }
    DirectionsRendererOptionsImpl.impl.setPanel(jso, panel.get());
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.directions.HasDirectionsRendererOptions#setPolylineOptions(com.google.gwt.maps.client.overlay.HasPolylineOptions)
   */
  @Override
  public void setPolylineOptions(HasPolylineOptions polylineOptions) {
    DirectionsRendererOptionsImpl.impl.setPolylineOptions(jso, polylineOptions.getJso());
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.directions.HasDirectionsRendererOptions#setPreserveViewport(boolean)
   */
  @Override
  public void setPreserveViewport(boolean preserveViewport) {
    DirectionsRendererOptionsImpl.impl.setPreserveViewport(jso, preserveViewport);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.directions.HasDirectionsRendererOptions#setRouteIndex(int)
   */
  @Override
  public void setRouteIndex(int routeIndex) {
    DirectionsRendererOptionsImpl.impl.setRouteIndex(jso, routeIndex);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.directions.HasDirectionsRendererOptions#setSuppressInfoWindows(boolean)
   */
  @Override
  public void setSuppressInfoWindows(boolean suppressInfoWindows) {
    DirectionsRendererOptionsImpl.impl.setSuppressInfoWindows(jso, suppressInfoWindows);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.directions.HasDirectionsRendererOptions#setSuppressMarkers(boolean)
   */
  @Override
  public void setSuppressMarkers(boolean suppressMarkers) {
    DirectionsRendererOptionsImpl.impl.setSuppressMarkers(jso, suppressMarkers);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.directions.HasDirectionsRendererOptions#setSuppressPolylines(boolean)
   */
  @Override
  public void setSuppressPolylines(boolean suppressPolylines) {
    DirectionsRendererOptionsImpl.impl.setSuppressPolylines(jso, suppressPolylines);
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.HasJso#getJso()
   */
  @Override
  public JavaScriptObject getJso() {
    return jso;
  }

}

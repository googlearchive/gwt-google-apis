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

import com.google.gwt.maps.client.HasJso;
import com.google.gwt.maps.client.Map;
import com.google.gwt.maps.client.base.HasElementProvider;
import com.google.gwt.maps.client.overlay.HasMarkerOptions;
import com.google.gwt.maps.client.overlay.HasPolylineOptions;

/**
 * Object of this interface's implementation defines the properties that can be
 * set on a DirectionsRenderer object.
 * 
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public interface HasDirectionsRendererOptions extends HasJso {

  public HasDirectionsResult getDirections();

  public boolean isHideRouteList();

  public Map getMap();

  public HasMarkerOptions getMarkerOptions();
  
  public HasElementProvider getPanel();

  public HasPolylineOptions getPolylineOptions();

  public boolean isPreserveViewport();

  public int getRouteIndex();

  public boolean isSuppressInfoWindows();

  public boolean isSuppressMarkers();

  public boolean isSuppressPolylines();

  /**
   * The directions to display on the map and/or in a <div> panel, retrieved as
   * a DirectionsResult object from DirectionsService.
   */
  public void setDirections(HasDirectionsResult directions);

  /**
   * This property indicates whether the renderer should provide UI to select
   * amongst alternative routes. By default, this flag is false and a
   * user-selectable list of routes will be shown in the directions' associated
   * panel. To hide that list, set hideRouteList to true.
   */
  public void setHideRouteList(boolean hideRouteList);

  /**
   * Map on which to display the directions.
   */
  public void setMap(Map map);

  /**
   * Options for the markers. All markers rendered by the DirectionsRenderer
   * will use these options.
   */
  public void setMarkerOptions(HasMarkerOptions markerOptions);
  
  /**
   * The <div> in which to display the directions steps.
   */
  public void setPanel(HasElementProvider panel);

  /**
   * Options for the polylines. All polylines rendered by the DirectionsRenderer
   * will use these options.
   */
  public void setPolylineOptions(HasPolylineOptions polylineOptions);

  /**
   * By default, the input map is centered and zoomed to the bounding box of
   * this set of directions. If this option is set to true, the viewport is left
   * unchanged, unless the map's center and zoom were never set.
   */
  public void setPreserveViewport(boolean preserveViewport);

  /**
   * The index of the route within the DirectionsResult object. The default
   * value is 0.
   */
  public void setRouteIndex(int routeIndex);

  /**
   * Suppress the rendering of info windows.
   */
  public void setSuppressInfoWindows(boolean suppressInfoWindows);

  /**
   * Suppress the rendering of markers.
   */
  public void setSuppressMarkers(boolean suppressMarkers);

  /**
   * Suppress the rendering of polylines.
   */
  public void setSuppressPolylines(boolean suppressPolylines);

}

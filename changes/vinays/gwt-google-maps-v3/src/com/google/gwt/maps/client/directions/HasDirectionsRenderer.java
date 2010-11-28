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

/**
 * Renders directions retrieved in the form of a DirectionsResult object
 * retrieved from the DirectionsService. This class extends MVCObject.
 * 
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public interface HasDirectionsRenderer extends HasJso {

  /**
   * Returns the renderer's current set of directions.
   */
  public HasDirectionsResult getDirections();

  /**
   * Returns the map on which the DirectionsResult is rendered.
   */
  public Map getMap();

  /**
   * Returns the panel <div> in which the DirectionsResult is rendered.
   */
  public HasElementProvider getPanel();

  /**
   * Returns the current route index in use by this DirectionsRenderer object.
   */
  public int getRouteIndex();

  /**
   * Set the renderer to use the result from the DirectionsService. Setting a
   * valid set of directions in this manner will display the directions on the
   * renderer's designated map and panel.
   */
  public void setDirections(HasDirectionsResult directions);

  /**
   * This method specifies the map on which directions will be rendered. Pass
   * null to remove the directions from the map.
   */
  public void setMap(Map map);

  /**
   * This method renders the directions in a <div>. Pass null to remove the
   * content from the panel.
   */
  public void setPanel(HasElementProvider panel);

  /**
   * Set the index of the route in the DirectionsResult object to render. By
   * default, the first route in the array will be rendered.
   */
  public void setRouteIndex(int routeIndex);
}

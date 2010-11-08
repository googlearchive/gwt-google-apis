/* Copyright (c) 2010 Google Inc.
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
package com.google.gwt.maps.client.overlay;

import com.google.gwt.maps.client.HasMap;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.mvc.HasMVCObject;
import com.google.gwt.maps.client.mvc.MVCObject;

import java.util.List;

/**
 * A polygon (like a polyline) defines a series of connected coordinates in an
 * ordered sequence; additionally, polygons form a closed loop and define a
 * filled region.
 * 
 * This class extends {@link MVCObject}.
 * 
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public interface HasPolygon extends HasMVCObject {

  /**
   * Returns the map on which this poly is attached.
   */
  HasMap getMap();
  
  /**
   * Retrieves the first path.
   */
  List<LatLng> getPath();
  
  /**
   * Retrieves the paths for this Polygon.
   */
  List<List<LatLng>> getPaths();

  /**
   * Renders this Polyline or Polygon on the specified map. If map is set to
   * null, the Poly will be removed.
   */
  void setMap(HasMap map);
  
  void setOptions(HasPolygonOptions options);
  
  /**
   * Sets the first path. See Polyline options for more details.
   */
  void setPath(List<LatLng> path);
  
  /**
   * Sets the path for this Polygon.
   */
  void setPaths(List<List<LatLng>> paths);
  
}

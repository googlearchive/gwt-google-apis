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

import com.google.gwt.maps.client.HasJso;
import com.google.gwt.maps.client.Map;
import com.google.gwt.maps.client.base.LatLng;

import java.util.List;

/**
 * A polyline is a linear overlay of connected line segments on the map. This
 * class extends MVCObject.
 * 
 */
public interface HasPolyline extends HasJso {

  /**
   * Returns the map on which this poly is attached.
   */
  Map getMap();

  /**
   * Retrieves the first path.
   */
  List<LatLng> getPath();

  /**
   * Renders this Polyline or Polygon on the specified map. If map is set to
   * null, the Poly will be removed.
   */
  void setMap(Map map);

  void setOptions(HasPolylineOptions options);

  /**
   * Sets the first path. See {@link PolylineOptions} for more details.
   */
  void setPath(List<LatLng> path);
  
}

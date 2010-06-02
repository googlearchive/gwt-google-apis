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
package com.google.gwt.maps.client.overlay;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.base.HasLatLng;
import com.google.gwt.maps.client.base.HasPoint;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.base.Point;
import com.google.gwt.maps.client.overlay.impl.ProjectionImpl;

/**
 * 
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public class Projection implements HasProjection {
  
  private final JavaScriptObject jso;
  
  public Projection(JavaScriptObject jso) {
    super();
    this.jso = jso;
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.overlay.HasProjection#fromLatLngToPoint(com.google.gwt.maps.client.base.HasLatLng)
   */
  @Override
  public HasPoint fromLatLngToPoint(HasLatLng latLng) {
    return new Point(ProjectionImpl.impl.fromLatLngToPoint(jso, latLng.getJso()));
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.overlay.HasProjection#fromPointToLatLng(com.google.gwt.maps.client.base.HasPoint)
   */
  @Override
  public HasLatLng fromPointToLatLng(HasPoint point) {
    return new LatLng(ProjectionImpl.impl.fromPointToLatLng(jso, point.getJso()));
  }

  /* (non-Javadoc)
   * @see com.google.gwt.maps.client.HasJso#getJso()
   */
  @Override
  public JavaScriptObject getJso() {
    return jso;
  }

}

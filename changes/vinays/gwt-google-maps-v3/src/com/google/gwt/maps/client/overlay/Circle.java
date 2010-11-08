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
import com.google.gwt.maps.client.HasMap;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.mvc.MVCObject;
import com.google.gwt.maps.client.overlay.impl.CircleImpl;

public class Circle extends MVCObject implements HasCircle {
  
  final private JavaScriptObject jso;
  
  public Circle(final JavaScriptObject jso) {
    this.jso = jso;
  }

  public Circle() {
    this(CircleImpl.impl.construct());
  }

  public Circle(HasCircleOptions circleOptions) {
    this(CircleImpl.impl.construct(circleOptions.getJso()));
  }

  @Override
  public LatLng getCenter() {
    return (LatLng) CircleImpl.impl.getCenter(jso);
  }

  @Override
  public JavaScriptObject getJso() {
    return jso;
  }

  @Override
  public HasMap getMap() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public double getRadius() {
    return CircleImpl.impl.getRadius(jso);
  }

  @Override
  public void setCenter(LatLng center) {
    CircleImpl.impl.setCenter(jso, center);
  }

  @Override
  public void setMap(HasMap map) {
    CircleImpl.impl.setMap(jso, map.getJso());
  }

  @Override
  public void setRadius(double radius) {
    CircleImpl.impl.setRadius(jso, radius);
  }

}

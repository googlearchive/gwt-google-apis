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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.overlay.impl.CircleOptionsImpl;

/**
 * This class implements {@link HasCircleOptions}.
 *
 */
public class CircleOptions implements HasCircleOptions {
  
  final private JavaScriptObject jso;
  
  public CircleOptions(final JavaScriptObject jso) {
    this.jso = jso;
  }

  public CircleOptions() {
    this(CircleOptionsImpl.impl.construct());
  }
  
  @Override
  public LatLng getCenter() {
    return (LatLng) CircleOptionsImpl.impl.getCenter(jso);
  }

  @Override
  public String getFillColor() {
    return CircleOptionsImpl.impl.getFillColor(jso);
  }

  @Override
  public double getFillOpacity() {
    return CircleOptionsImpl.impl.getFillOpacity(jso);
  }

  @Override
  public JavaScriptObject getJso() {
    return jso;
  }

  @Override
  public double getRadius() {
    return CircleOptionsImpl.impl.getRadius(jso);
  }

  @Override
  public String getStrokeColor() {
    return CircleOptionsImpl.impl.getStrokeColor(jso);
  }

  @Override
  public double getStrokeOpacity() {
    return CircleOptionsImpl.impl.getStrokeOpacity(jso);
  }

  @Override
  public int getStrokeWeight() {
    return CircleOptionsImpl.impl.getStrokeWeight(jso);
  }

  @Override
  public void setCenter(LatLng center) {
    CircleOptionsImpl.impl.setCenter(jso, center);
  }

  @Override
  public void setFillColor(String fillColor) {
    CircleOptionsImpl.impl.setFillColor(jso, fillColor);
  }

  @Override
  public void setFillOpacity(double fillOpacity) {
    CircleOptionsImpl.impl.setFillOpacity(jso, fillOpacity);
  }

  @Override
  public void setRadius(double radius) {
    CircleOptionsImpl.impl.setRadius(jso, radius);
  }

  @Override
  public void setStrokeColor(String strokeColor) {
    CircleOptionsImpl.impl.setStrokeColor(jso, strokeColor);
  }

  @Override
  public void setStrokeOpacity(double strokeOpacity) {
    CircleOptionsImpl.impl.setStrokeOpacity(jso, strokeOpacity);
  }

  @Override
  public void setStrokeWeight(int strokeWeight) {
    CircleOptionsImpl.impl.setStrokeWeight(jso, strokeWeight);
  }

}

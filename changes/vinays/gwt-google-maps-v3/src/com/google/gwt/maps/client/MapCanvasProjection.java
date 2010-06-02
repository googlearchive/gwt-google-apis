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
package com.google.gwt.maps.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.base.HasLatLng;
import com.google.gwt.maps.client.base.HasPoint;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.base.Point;
import com.google.gwt.maps.client.impl.MapCanvasProjectionImpl;

/**
 * MapCanvasProjection java overlay.
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public class MapCanvasProjection implements HasMapCanvasProjection {

  final private JavaScriptObject jso;
  
  public MapCanvasProjection(JavaScriptObject jso) {
    this.jso = jso;
  }

  @Override
  public HasLatLng fromContainerPixelToLatLng(HasPoint pixel) {
    return new LatLng(
        MapCanvasProjectionImpl.impl.fromContainerPixelToLatLng(jso, pixel.getJso()));
  }

  @Override
  public HasLatLng fromDivPixelToLatLng(HasPoint pixel) {
    return new LatLng(
        MapCanvasProjectionImpl.impl.fromDivPixelToLatLng(jso, pixel.getJso()));
  }

  @Override
  public HasPoint fromLatLngToContainerPixel(HasLatLng latLng) {
    return new Point(
        MapCanvasProjectionImpl.impl.fromLatLngToContainerPixel(jso, latLng.getJso()));
  }

  @Override
  public HasPoint fromLatLngToDivPixel(HasLatLng latLng) {
    return new Point(
        MapCanvasProjectionImpl.impl.fromLatLngToDivPixel(jso, latLng.getJso()));
  }

  @Override
  public int getWorldWidth() {
    return MapCanvasProjectionImpl.impl.getWorldWidth(jso);
  }

  @Override
  public JavaScriptObject getJso() {
    return jso;
  }

}

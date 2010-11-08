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
package com.google.gwt.maps.client.base;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.HasMap;
import com.google.gwt.maps.client.base.impl.InfoWindowImpl;
import com.google.gwt.maps.client.mvc.HasMVCObject;
import com.google.gwt.maps.client.mvc.MVCObject;

/**
 * This class implements {@link HasInfoWindow}.
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public class InfoWindow extends MVCObject implements HasInfoWindow {

  private JavaScriptObject jso;
  
  
  public InfoWindow(JavaScriptObject jso) {
    this.jso = jso;
  }
  
  public InfoWindow(HasInfoWindowOptions options) {
    this(InfoWindowImpl.impl.construct(options.getJso()));
  }
  
  public InfoWindow() {
    this(InfoWindowImpl.impl.construct());
  }

  @Override
  public void open(HasMap map, HasMVCObject anchor) {
    InfoWindowImpl.impl.open(jso, map.getJso(), anchor.getJso());
  }

  @Override
  public void close() {
    InfoWindowImpl.impl.close(jso);
  }

  @Override
  public String getContent() {
    return InfoWindowImpl.impl.getContent(jso);
  }

  @Override
  public LatLng getPosition() {
    return (LatLng) InfoWindowImpl.impl.getPosition(jso);
  }

  @Override
  public int getZIndex() {
    return InfoWindowImpl.impl.getZIndex(jso);
  }

  @Override
  public void setContent(String html) {
    InfoWindowImpl.impl.setContent(jso, html);
  }

  @Override
  public void setOptions(HasInfoWindowOptions options) {
    InfoWindowImpl.impl.setOptions(jso, options.getJso());
  }

  @Override
  public void setPosition(LatLng position) {
    InfoWindowImpl.impl.setPosition(jso, position);
  }

  @Override
  public void setZIndex(int zIndex) {
    InfoWindowImpl.impl.setZIndex(jso, zIndex);
  }

  @Override
  public JavaScriptObject getJso() {
    return jso;
  }

}

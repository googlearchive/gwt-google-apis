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
import com.google.gwt.maps.client.HasMap;
import com.google.gwt.maps.client.Map;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.mvc.MVCObject;
import com.google.gwt.maps.client.overlay.impl.MarkerImpl;

/**
 * This class implements {@link HasMarker}.
 *
 */
public class Marker extends MVCObject implements HasMarker {

  final private JavaScriptObject jso;
  
  public Marker(JavaScriptObject jso) {
    this.jso = jso;
  }
  
  public Marker() {
    this(MarkerImpl.impl.construct());
  }
  
  public Marker(HasMarkerOptions options) {
    this(MarkerImpl.impl.construct(options.getJso()));
  }
  
  @Override
  public String getCursor() {
    return MarkerImpl.impl.getCursor(jso);
  }

  @Override
  public HasMarkerImage getIcon() {
    return new MarkerImage(MarkerImpl.impl.getIcon(jso));
  }

  @Override
  public void setIcon(HasMarkerImage image) {
    MarkerImpl.impl.setIcon(jso, image.getJso());
  }

  @Override
  public HasMap getMap() {
    return new Map(MarkerImpl.impl.getMap(jso));
  }

  @Override
  public LatLng getPosition() {
    return MarkerImpl.impl.getPosition(jso).cast();
  }

  @Override
  public String getTitle() {
    return MarkerImpl.impl.getTitle(jso);
  }

  @Override
  public int getZIndex() {
    return MarkerImpl.impl.getZIndex(jso);
  }

  @Override
  public boolean isClickable() {
    return MarkerImpl.impl.isClickable(jso);
  }

  @Override
  public boolean isDraggable() {
    return MarkerImpl.impl.isDraggable(jso);
  }

  @Override
  public boolean isFlat() {
    return MarkerImpl.impl.isFlat(jso);
  }

  @Override
  public boolean isVisible() {
    return MarkerImpl.impl.isVisible(jso);
  }

  @Override
  public void setClickable(boolean clickable) {
    MarkerImpl.impl.setClickable(jso, clickable);
  }

  @Override
  public void setCursor(String cursor) {
    MarkerImpl.impl.setCursor(jso, cursor);
  }

  @Override
  public void setDraggable(boolean draggable) {
    MarkerImpl.impl.setDraggable(jso, draggable);
  }

  @Override
  public void setFlat(boolean flat) {
    MarkerImpl.impl.setFlat(jso, flat);
  }

  @Override
  public void setMap(HasMap map) {
    MarkerImpl.impl.setMap(jso, map.getJso());
  }

  @Override
  public void setPosition(LatLng position) {
    MarkerImpl.impl.setPosition(jso, position);
  }

  @Override
  public void setTitle(String title) {
    MarkerImpl.impl.setTitle(jso, title);
  }

  @Override
  public void setVisible(boolean visible) {
    MarkerImpl.impl.setVisible(jso, visible);
  }

  @Override
  public void setZIndex(int zIndex) {
    MarkerImpl.impl.setZIndex(jso, zIndex);
  }

  @Override
  public JavaScriptObject getJso() {
    return jso;
  }

}

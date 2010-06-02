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
import com.google.gwt.maps.client.Map;
import com.google.gwt.maps.client.base.HasLatLng;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.overlay.impl.MarkerOptionsImpl;

/**
 * This class implements {@link HasMarkerOptions}.
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public class MarkerOptions implements HasMarkerOptions {

  final private JavaScriptObject jso;
  
  public MarkerOptions(JavaScriptObject jso) {
    this.jso = jso;
  }
  
  public MarkerOptions() {
    this(MarkerOptionsImpl.impl.construct());
  }
  
  @Override
  public String getCursor() {
    return MarkerOptionsImpl.impl.getCursor(jso);
  }

  @Override
  public HasMarkerImage getIcon() {
    return new MarkerImage(MarkerOptionsImpl.impl.getIcon(jso));
  }

  @Override
  public HasMap getMap() {
    return new Map(MarkerOptionsImpl.impl.getMap(jso));
  }

  @Override
  public HasLatLng getPosition() {
    return new LatLng(MarkerOptionsImpl.impl.getPosition(jso));
  }

  @Override
  public String getTitle() {
    return MarkerOptionsImpl.impl.getTitle(jso);
  }

  @Override
  public int getZIndex() {
    return MarkerOptionsImpl.impl.getZIndex(jso);
  }

  @Override
  public boolean isClickable() {
    return MarkerOptionsImpl.impl.isClickable(jso);
  }

  @Override
  public boolean isDraggable() {
    return MarkerOptionsImpl.impl.isDraggable(jso);
  }

  @Override
  public boolean isFlat() {
    return MarkerOptionsImpl.impl.isFlat(jso);
  }

  @Override
  public boolean isVisible() {
    return MarkerOptionsImpl.impl.isVisible(jso);
  }

  @Override
  public void setClickable(boolean clickable) {
    MarkerOptionsImpl.impl.setClickable(jso, clickable);
  }

  @Override
  public void setCursor(String cursor) {
    MarkerOptionsImpl.impl.setCursor(jso, cursor);
  }

  @Override
  public void setDraggable(boolean draggable) {
    MarkerOptionsImpl.impl.setDraggable(jso, draggable);
  }

  @Override
  public void setFlat(boolean flat) {
    MarkerOptionsImpl.impl.setFlat(jso, flat);
  }

  @Override
  public void setIcon(HasMarkerImage image) {
    MarkerOptionsImpl.impl.setIcon(jso, image.getJso());
  }

  @Override
  public void setMap(HasMap map) {
    MarkerOptionsImpl.impl.setMap(jso, map.getJso());
  }

  @Override
  public void setPosition(HasLatLng position) {
    MarkerOptionsImpl.impl.setPosition(jso, position.getJso());
  }

  @Override
  public void setTitle(String title) {
    MarkerOptionsImpl.impl.setTitle(jso, title);
  }

  @Override
  public void setVisible(boolean visible) {
    MarkerOptionsImpl.impl.setVisible(jso, visible);
  }

  @Override
  public void setZIndex(int zIndex) {
    MarkerOptionsImpl.impl.setZIndex(jso, zIndex);
  }

  @Override
  public JavaScriptObject getJso() {
    return jso;
  }

}

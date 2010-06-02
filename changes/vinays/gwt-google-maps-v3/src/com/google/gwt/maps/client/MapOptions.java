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
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.impl.ControlPositionImpl;
import com.google.gwt.maps.client.impl.MapOptionsImpl;
import com.google.gwt.maps.client.impl.MapTypeControlStyleImpl;
import com.google.gwt.maps.client.impl.NavigationControlStyleImpl;
import com.google.gwt.maps.client.impl.ScaleControlStyleImpl;

/**
 * This class implements {@link HasMapOptions}.
 *
 * @author vinay.sekhri@gmail.com (Vinay Sekhri)
 */
public class MapOptions implements HasMapOptions {

  final private JavaScriptObject jso;
  
  public MapOptions(JavaScriptObject jso) {
    this.jso = jso;
  }
  
  public MapOptions() {
    this(MapOptionsImpl.impl.construct());
  }
  
  @Override
  public String getBackgroundColor() {
    return MapOptionsImpl.impl.getBackgroundColor(jso);
  }

  @Override
  public HasLatLng getCenter() {
    return new LatLng(MapOptionsImpl.impl.getCenter(jso));
  }

  @Override
  public String getDraggableCursor() {
    return MapOptionsImpl.impl.getDraggableCursor(jso);
  }

  @Override
  public String getDraggingCursor() {
    return MapOptionsImpl.impl.getDraggingCursor(jso);
  }

  @Override
  public HasMapTypeControlOptions getMapTypeControlOptions() {
    return new MapTypeControlOptions(MapOptionsImpl.impl.getMapTypeControlOptions(jso),
        new ControlPositionImpl(), new MapTypeControlStyleImpl());
  }

  @Override
  public String getMapTypeId() {
    return MapOptionsImpl.impl.getMapTypeId(jso);
  }

  @Override
  public HasNavigationControlOptions getNavigationControlOptions() {
    return new NavigationControlOptions(MapOptionsImpl.impl.getNavigationControlOptions(jso),
        new ControlPositionImpl(), new NavigationControlStyleImpl());
  }

  @Override
  public HasScaleControlOptions getScaleControlOptions() {
    return new ScaleControlOptions(MapOptionsImpl.impl.getScaleControlOptions(jso),
        new ControlPositionImpl(), new ScaleControlStyleImpl());
  }

  @Override
  public int getZoom() {
    return MapOptionsImpl.impl.getZoom(jso);
  }

  @Override
  public boolean isDisableDefaultUI() {
    return MapOptionsImpl.impl.isDisableDefaultUI(jso);
  }

  @Override
  public boolean isDisableDoubleClickZoom() {
    return MapOptionsImpl.impl.isDisableDoubleClickZoom(jso);
  }

  @Override
  public boolean isDraggable() {
    return MapOptionsImpl.impl.isDraggable(jso);
  }

  @Override
  public boolean isKeyboardShortcuts() {
    return MapOptionsImpl.impl.isKeyboardShortcuts(jso);
  }

  @Override
  public boolean isMapTypeControl() {
    return MapOptionsImpl.impl.isMapTypeControl(jso);
  }

  @Override
  public boolean isNavigationControl() {
    return MapOptionsImpl.impl.isNavigationControl(jso);
  }

  @Override
  public boolean isNoClear() {
    return MapOptionsImpl.impl.isNoClear(jso);
  }

  @Override
  public boolean isScaleControl() {
    return MapOptionsImpl.impl.isScaleControl(jso);
  }

  @Override
  public boolean isScrollwheel() {
    return MapOptionsImpl.impl.isScrollwheel(jso);
  }

  @Override
  public void setBackgroundColor(String backgroundColor) {
    MapOptionsImpl.impl.setBackgroundColor(jso, backgroundColor);
  }

  @Override
  public void setCenter(HasLatLng center) {
    MapOptionsImpl.impl.setCenter(jso, center.getJso());
  }

  @Override
  public void setDisableDefaultUI(boolean disableDefaultUI) {
    MapOptionsImpl.impl.setDisableDefaultUI(jso, disableDefaultUI);
  }

  @Override
  public void setDisableDoubleClickZoom(boolean disableDoubleClickZoom) {
    MapOptionsImpl.impl.setDisableDoubleClickZoom(jso, disableDoubleClickZoom);
  }

  @Override
  public void setDraggable(boolean draggable) {
    MapOptionsImpl.impl.setDraggable(jso, draggable);
  }

  @Override
  public void setDraggableCursor(String draggableCursor) {
    MapOptionsImpl.impl.setDraggableCursor(jso, draggableCursor);
  }

  @Override
  public void setDraggingCursor(String draggingCursor) {
    MapOptionsImpl.impl.setDraggingCursor(jso, draggingCursor);
  }

  @Override
  public void setKeyboardShortcuts(boolean keyboardShortcuts) {
    MapOptionsImpl.impl.setKeyboardShortcuts(jso, keyboardShortcuts);
  }

  @Override
  public void setMapTypeControl(boolean mapTypeControl) {
    MapOptionsImpl.impl.setMapTypeControl(jso, mapTypeControl);
  }

  @Override
  public void setMapTypeControlOptions(
      HasMapTypeControlOptions mapTypeControlOptions) {
    MapOptionsImpl.impl.setMapTypeControlOptions(jso, mapTypeControlOptions.getJso());
  }

  @Override
  public void setMapTypeId(String mapTypeId) {
    MapOptionsImpl.impl.setMapTypeId(jso, mapTypeId);
  }

  @Override
  public void setNavigationControl(boolean navigationControl) {
    MapOptionsImpl.impl.setNavigationControl(jso, navigationControl);
  }

  @Override
  public void setNavigationControlOptions(
      HasNavigationControlOptions navigationControlOptions) {
    MapOptionsImpl.impl.setNavigationControlOptions(jso, navigationControlOptions.getJso());
  }

  @Override
  public void setNoClear(boolean noClear) {
    MapOptionsImpl.impl.setNoClear(jso, noClear);
  }

  @Override
  public void setScaleControl(boolean scaleControl) {
    MapOptionsImpl.impl.setScaleControl(jso, scaleControl);
  }

  @Override
  public void setScaleControlOptions(HasScaleControlOptions scaleControlOptions) {
    MapOptionsImpl.impl.setScaleControlOptions(jso, scaleControlOptions.getJso());
  }

  @Override
  public void setScrollwheel(boolean scrollwheel) {
    MapOptionsImpl.impl.setScrollwheel(jso, scrollwheel);
  }

  @Override
  public void setZoom(int zoom) {
    MapOptionsImpl.impl.setZoom(jso, zoom);
  }

  @Override
  public JavaScriptObject getJso() {
    return jso;
  }

}

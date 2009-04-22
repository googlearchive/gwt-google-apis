/*
 * Copyright 2008 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.gwt.maps.client.impl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.maps.client.DraggableObject;
import com.google.gwt.maps.client.MapType;
import com.google.gwt.maps.client.MapUIOptions;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.Control;
import com.google.gwt.maps.client.control.ControlPosition;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.LatLngBounds;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.client.geom.Size;
import com.google.gwt.maps.client.overlay.Overlay;
import com.google.gwt.maps.jsio.client.Binding;
import com.google.gwt.maps.jsio.client.Constructor;
import com.google.gwt.maps.jsio.client.JSFlyweightWrapper;
import com.google.gwt.maps.jsio.client.JSList;
import com.google.gwt.maps.jsio.client.JSOpaque;
import com.google.gwt.user.client.Element;

/**
 * Wrapper for the GMap object from the Maps API using JSIO.
 */
public interface MapImpl extends JSFlyweightWrapper {

  // TODO(zundel): changing this class to a JavaScript overlay will prevent 
  //  problems when down-grading the API version.
  MapImpl impl = GWT.create(MapImpl.class);

  void addControl(JavaScriptObject jsoPeer, Control control);

  void addControl(JavaScriptObject jsoPeer, Control control,
      ControlPosition position);

  void addMapType(JavaScriptObject jsoPeerPeer, MapType type);

  void addOverlay(JavaScriptObject jsoPeer, Overlay overlay);

  @Binding
  void bind(JavaScriptObject jsoPeer, MapWidget map);

  // Notifies the map of a change of the size of its container. Call this method
  // after the size of the container DOM object has changed, so that the map can
  // adjust itself to fit the new size.
  void checkResize(JavaScriptObject jsoPeer);

  void clearOverlays(JavaScriptObject jsoPeer);

  void closeInfoWindow(JavaScriptObject jsoPeer);

  void closeInfoWindow(MapWidget map);

  @Constructor("$wnd.GMap2")
  JavaScriptObject construct(Element container);

  @Constructor("$wnd.GMap2")
  JavaScriptObject construct(Element container, JavaScriptObject mapOptions);

  boolean continuousZoomEnabled(JavaScriptObject jsoPeer);

  void disableContinuousZoom(JavaScriptObject jsoPeer);

  void disableDoubleClickZoom(JavaScriptObject jsoPeer);

  void disableDragging(JavaScriptObject jsoPeer);

  void disableGoogleBar(JavaScriptObject jsoPeer);

  void disableInfoWindow(JavaScriptObject jsoPeer);

  void disablePinchToZoom(JavaScriptObject jsoPeer);

  void disableScrollWheelZoom(JavaScriptObject jsoPeer);

  boolean doubleClickZoomEnabled(JavaScriptObject jsoPeer);

  boolean draggingEnabled(JavaScriptObject jsoPeer);

  void enableContinuousZoom(JavaScriptObject jsoPeer);

  void enableDoubleClickZoom(JavaScriptObject jsoPeer);

  void enableDragging(JavaScriptObject jsoPeer);

  void enableGoogleBar(JavaScriptObject jsoPeer);

  void enableInfoWindow(JavaScriptObject jsoPeer);

  void enablePinchToZoom(JavaScriptObject jsoPeer);

  void enableScrollWheelZoom(JavaScriptObject jsoPeer);

  LatLng fromContainerPixelToLatLng(JavaScriptObject jsoPeer, Point pixel);

  LatLng fromDivPixelToLatLng(JavaScriptObject jsoPeer, Point pixel);

  Point fromLatLngToContainerPixel(JavaScriptObject jsoPeer, LatLng latlng);

  Point fromLatLngToDivPixel(JavaScriptObject jsoPeer, LatLng latlng);

  LatLngBounds getBounds(JavaScriptObject jsoPeer);

  int getBoundsZoomLevel(JavaScriptObject jsoPeer, LatLngBounds bounds);

  // GLatLng Returns the geographical coordinates of the center point of the map
  // view.
  LatLng getCenter(JavaScriptObject jsoPeer);

  MapType getCurrentMapType(JavaScriptObject jsoPeerPeer);

  MapUIOptions getDefaultUI(JavaScriptObject jsoPeer);

  DraggableObject getDragObject(JavaScriptObject jsoPeerPeer);

  void getEarthInstance(JavaScriptObject jsoPeer, JavaScriptObject jsoCallback);

  JavaScriptObject getInfoWindow(MapWidget map);

  // Replaced by getElement()?
  // public Element getContainer(JavaScriptObject jsoPeer);
  //  
  JSList<MapType> getMapTypes(JavaScriptObject jsoPeerPeer);

  Element getPane(MapWidget map, JSOpaque id);

  Size getSize(JavaScriptObject jsoPeer);

  int getZoom(JavaScriptObject jsoPeer);

  boolean infoWindowEnabled(JavaScriptObject jsoPeer);

  @Constructor("$wnd.GKeyboardHandler")
  JavaScriptObject installKeyboardHandler(MapWidget map);

  // Boolean Returns true iff the map was initialized by setCenter() since it
  // was created.
  boolean isLoaded(JavaScriptObject jsoPeer);

  void openInfoWindow(MapWidget map, LatLng point, JavaScriptObject content,
      JavaScriptObject options);

  void openInfoWindow(MapWidget map, LatLng point, String content,
      JavaScriptObject options);

  void openInfoWindowTabs(MapWidget map, LatLng point,
      JavaScriptObject content, JavaScriptObject options);

  void panBy(JavaScriptObject jsoPeer, Size distance);

  void panDirection(JavaScriptObject jsoPeer, int dx, int dy);

  void panTo(JavaScriptObject jsoPeer, LatLng center);

  boolean pinchToZoomEnabled(JavaScriptObject jsoPeer);

  void removeControl(JavaScriptObject jsoPeer, Control control);

  void removeMapType(JavaScriptObject jsoPeerPeer, MapType type);

  void removeOverlay(JavaScriptObject jsoPeer, Overlay overlay);

  void returnToSavedPosition(JavaScriptObject jsoPeer);

  void savePosition(JavaScriptObject jsoPeer);

  boolean scrollWheelZoomEnabled(JavaScriptObject jsoPeer);

  void setCenter(JavaScriptObject jsoPeer, LatLng center);

  void setCenter(JavaScriptObject jsoPeer, LatLng center, int zoom);

  void setCenter(JavaScriptObject jsoPeer, LatLng center, int zoom,
      MapType type);

  void setMapType(JavaScriptObject jsoPeerPeer, MapType type);

  void setUI(JavaScriptObject jsoPeer, MapUIOptions options);

  void setUIToDefault(JavaScriptObject jsoPeer);

  void setZoom(JavaScriptObject jsoPeer, int level);

  void showMapBlowup(MapWidget map, LatLng point, JavaScriptObject options);

  void zoomIn(JavaScriptObject jsoPeer);

  void zoomOut(JavaScriptObject jsoPeer);
}

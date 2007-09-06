/*
 * Copyright 2007 Google Inc.
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
import com.google.gwt.jsio.client.JSFlyweightWrapper;
import com.google.gwt.jsio.client.JSList;
import com.google.gwt.maps.client.MapType;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.Control;
import com.google.gwt.maps.client.control.ControlPosition;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.LatLngBounds;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.client.geom.Size;
import com.google.gwt.maps.client.overlay.Overlay;
import com.google.gwt.user.client.Element;

public interface MapImpl extends JSFlyweightWrapper {

  public static final MapImpl impl = (MapImpl) GWT.create(MapImpl.class);

  public void addControl(JavaScriptObject jsoPeer, Control control);

  public void addControl(JavaScriptObject jsoPeer, Control control,
      ControlPosition position);

  public void addMapType(JavaScriptObject jsoPeerPeer, MapType type);

  public void addOverlay(JavaScriptObject jsoPeer, Overlay overlay);

  /**
   * @gwt.binding
   */
  public void bind(JavaScriptObject jsoPeer, MapWidget map);

  // Notifies the map of a change of the size of its container. Call this method
  // after the size of the container DOM object has changed, so that the map can
  // adjust itself to fit the new size.
  public void checkResize(JavaScriptObject jsoPeer);

  public void clearOverlays(JavaScriptObject jsoPeer);

  public void closeInfoWindow(MapWidget map);

  /**
   * @gwt.constructor $wnd.GMap2
   */
  public JavaScriptObject construct(Element container);

  /**
   * @gwt.constructor $wnd.GMap2
   */
  public JavaScriptObject construct(Element container,
      JavaScriptObject mapOptions);

  public boolean continuousZoomEnabled(JavaScriptObject jsoPeer);

  public void disableContinuousZoom(JavaScriptObject jsoPeer);

  public void disableDoubleClickZoom(JavaScriptObject jsoPeer);

  public void disableDragging(JavaScriptObject jsoPeer);

  public void disableInfoWindow(JavaScriptObject jsoPeer);

  public void disableScrollWheelZoom(JavaScriptObject jsoPeer);

  public boolean doubleClickZoomEnabled(JavaScriptObject jsoPeer);

  public boolean draggingDisabled(JavaScriptObject jsoPeer);

  public void enableContinuousZoom(JavaScriptObject jsoPeer);

  public void enableDoubleClickZoom(JavaScriptObject jsoPeer);

  public void enableDragging(JavaScriptObject jsoPeer);

  public void enableInfoWindow(JavaScriptObject jsoPeer);

  public void enableScrollWheelZoom(JavaScriptObject jsoPeer);

  public LatLng fromContainerPixelToLatLng(JavaScriptObject jsoPeer, Point pixel);

  public LatLng fromDivPixelToLatLng(JavaScriptObject jsoPeer, Point pixel);

  public Point fromLatLngToDivPixel(JavaScriptObject jsoPeer, LatLng latlng);

  public LatLngBounds getBounds(JavaScriptObject jsoPeer);

  public int getBoundsZoomLevel(JavaScriptObject jsoPeer, LatLngBounds bounds);

  // GLatLng Returns the geographical coordinates of the center point of the map
  // view.
  public LatLng getCenter(JavaScriptObject jsoPeer);

  public MapType getCurrentMapType(JavaScriptObject jsoPeerPeer);

  public JavaScriptObject getInfoWindow(MapWidget map);

  // Replaced by getElement()?
  // public Element getContainer(JavaScriptObject jsoPeer);
  //  
  /**
   * @gwt.typeArgs <com.google.gwt.maps.client.MapType>
   */
  public JSList getMapTypes(JavaScriptObject jsoPeerPeer);

  public Element getPane(MapWidget map, int id);

  public Size getSize(JavaScriptObject jsoPeer);

  public int getZoom(JavaScriptObject jsoPeer);

  public boolean infoWindowEnabled(JavaScriptObject jsoPeer);

  /**
   * @gwt.constructor $wnd.GKeyboardHandler
   */
  public JavaScriptObject installKeyboardHandler(MapWidget map);

  // Boolean Returns true iff the map was initialized by setCenter() since it
  // was created.
  public boolean isLoaded(JavaScriptObject jsoPeer);

  public void openInfoWindow(MapWidget map, LatLng point, JavaScriptObject content,
      JavaScriptObject options);
  
  public void openInfoWindow(MapWidget map, LatLng point, String content,
      JavaScriptObject options);

  public void openInfoWindowTabs(MapWidget map, LatLng point,
      JavaScriptObject content, JavaScriptObject options);

  public void panBy(JavaScriptObject jsoPeer, Size distance);

  public void panDirection(JavaScriptObject jsoPeer, int dx, int dy);

  public void panTo(JavaScriptObject jsoPeer, LatLng center);

  public void removeControl(JavaScriptObject jsoPeer, Control control);

  public void removeMapType(JavaScriptObject jsoPeerPeer, MapType type);

  public void removeOverlay(JavaScriptObject jsoPeer, Overlay overlay);

  public void returnToSavedPosition(JavaScriptObject jsoPeer);

  public void savePosition(JavaScriptObject jsoPeer);

  public boolean scrollWheelZoomEnabled(JavaScriptObject jsoPeer);

  public void setCenter(JavaScriptObject jsoPeer, LatLng center);

  public void setCenter(JavaScriptObject jsoPeer, LatLng center, int zoom);

  public void setCenter(JavaScriptObject jsoPeer, LatLng center, int zoom,
      MapType type);

  public void setMapType(JavaScriptObject jsoPeerPeer, MapType type);

  public void setZoom(JavaScriptObject jsoPeer, int level);

  public void showMapBlowup(MapWidget map, LatLng point, JavaScriptObject options);

  public void zoomIn(JavaScriptObject jsoPeer);

  public void zoomOut(JavaScriptObject jsoPeer);

}

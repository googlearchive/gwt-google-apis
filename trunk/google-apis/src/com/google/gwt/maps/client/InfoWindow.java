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
package com.google.gwt.maps.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.jsio.client.JSList;
import com.google.gwt.maps.client.event.InfoWindowListener;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.Size;
import com.google.gwt.maps.client.impl.InfoWindowImpl;
import com.google.gwt.maps.client.impl.MapImpl;
import com.google.gwt.maps.client.impl.MarkerImpl;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.util.JsUtil;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

import java.util.List;

/**
 * There is only one instance of an {@link InfoWindow} per map.
 * 
 * @see MapWidget#getInfoWindow()
 */
public final class InfoWindow {

  private static class VirtualPanel extends ComplexPanel {
    public void beginAttach(Widget w) {
      // Detach new child.
      w.removeFromParent();
      // Logical attach.
      getChildren().add(w);
    }

    public void finishAttach(Widget w) {
      // Adopt.
      adopt(w);
    }

    public boolean isAttached() {
      return true;
    }
  }

  private final JavaScriptObject jsoPeer;

  private final MapWidget map;

  /**
   * The virtual panel is used to as a point to attach
   */
  private final VirtualPanel virtualPanel = new VirtualPanel();

  /**
   * Package-private constructor to prevent instantiation from outside of the
   * API.
   * 
   * @param map the map to which this InfoWindow belongs.
   */
  InfoWindow(MapWidget map) {
    this.map = map;
    jsoPeer = MapImpl.impl.getInfoWindow(map);
  }

  /**
   * Closes the info window.
   */
  public void close() {
    MapImpl.impl.closeInfoWindow(map);
  }

  /**
   * Returns the array of Elements that hold the content of the tabs of this
   * info window.
   * 
   * @return the Elements that hold the content of the tabs of this info window
   */
  public Element[] getContentContainers() {
    JSList elementList = InfoWindowImpl.impl.getContentContainers(jsoPeer);
    Element[] containers = new Element[elementList.size()];
    JsUtil.toArray(elementList, containers);
    return containers;
  }

  /**
   * Returns the offset, in pixels, of the tip of the info window from the point
   * on the map at whose geographical coordinates the info window is anchored.
   * 
   * @return the offset of the info window
   */
  public Size getPixelOffset() {
    return InfoWindowImpl.impl.getPixelOffset(jsoPeer);
  }

  /**
   * Returns the geographical point at which the info window is anchored. The
   * tip of the window points to this point on the map, modulo the pixel offset.
   */
  public LatLng getPoint() {
    return InfoWindowImpl.impl.getPoint(jsoPeer);
  }

  /**
   * Returns the index, starting at 0, of the current selected tab.
   * 
   * @return the index of the selected tab
   */
  public int getSelectedTab() {
    return InfoWindowImpl.impl.getSelectedTab(jsoPeer);
  }

  /**
   * Returns true if the info window is visible.
   * 
   * @return true if the info window is visible
   */
  public boolean isVisible() {
    return !InfoWindowImpl.impl.isHidden(jsoPeer);
  }

  /**
   * Opens the info window at the given point with the given content.
   * 
   * If an info window is already open on the map
   * 
   * @param point
   * @param content
   */
  public void open(LatLng point, InfoWindowContent content) {
    beginAttach(content);
    switch (content.getType()) {
      case InfoWindowContent.TYPE_ELEMENT:
        MapImpl.impl.openInfoWindow(map, point, content.getContent(),
            content.getOptions());
        break;
      case InfoWindowContent.TYPE_TABBED:
        MapImpl.impl.openInfoWindowTabs(map, point, content.getContent(),
            content.getOptions());
        break;
      case InfoWindowContent.TYPE_MAP_BLOWUP:
        MapImpl.impl.showMapBlowup(map, point, content.getOptions());
        break;
    }
    finishAttach(content);
  }

  public void open(Marker marker, InfoWindowContent content) {
    beginAttach(content);
    switch (content.getType()) {
      case InfoWindowContent.TYPE_ELEMENT:
        MarkerImpl.impl.openInfoWindow(marker, content.getContent(),
            content.getOptions());
        break;
      case InfoWindowContent.TYPE_TABBED:
        MarkerImpl.impl.openInfoWindowTabs(marker, content.getContent(),
            content.getOptions());
        break;
      case InfoWindowContent.TYPE_MAP_BLOWUP:
        MarkerImpl.impl.showMapBlowup(marker, content.getOptions());
        break;
    }
    finishAttach(content);
  }

  /**
   * Selects the tab with the given index. This has the same effect as clicking
   * on the corresponding tab.
   * 
   * @param index the index of the tab to select
   */
  public void selectTab(int index) {
    InfoWindowImpl.impl.selectTab(jsoPeer, index);
  }

  /**
   * Shows or hides the info window.
   * 
   * @param visible true to show the info window, false to hide.
   */
  public void setVisible(boolean visible) {
    if (visible) {
      InfoWindowImpl.impl.show(jsoPeer);
    } else {
      InfoWindowImpl.impl.hide(jsoPeer);
    }
  }

  private void beginAttach(InfoWindowContent content) {
    List contentWidgets = content.getWidgets();
    for (int i = 0; i < contentWidgets.size(); i++) {
      virtualPanel.beginAttach((Widget) contentWidgets.get(i));
    }
  }

  private void finishAttach(InfoWindowContent content) {
    final List contentWidgets = content.getWidgets();
    for (int i = 0; i < contentWidgets.size(); i++) {
      virtualPanel.finishAttach((Widget) contentWidgets.get(i));
    }
    map.addInfoWindowListener(new InfoWindowListener() {
      public void onInfoWindowClosed(MapWidget sender) {
        for (int i = 0; i < contentWidgets.size(); i++) {
          virtualPanel.remove((Widget) contentWidgets.get(i));
        }
      }

      public void onInfoWindowOpened(MapWidget sender) {
      }
    });
  }
}
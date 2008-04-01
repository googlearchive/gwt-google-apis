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
package com.google.gwt.maps.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.jsio.client.JSList;
import com.google.gwt.maps.client.event.InfoWindowCloseClickHandler;
import com.google.gwt.maps.client.event.InfoWindowListener;
import com.google.gwt.maps.client.event.InfoWindowMaximizeClickHandler;
import com.google.gwt.maps.client.event.InfoWindowMaximizeEndHandler;
import com.google.gwt.maps.client.event.InfoWindowRestoreClickHandler;
import com.google.gwt.maps.client.event.InfoWindowRestoreEndHandler;
import com.google.gwt.maps.client.event.InfoWindowCloseClickHandler.InfoWindowCloseClickEvent;
import com.google.gwt.maps.client.event.InfoWindowMaximizeClickHandler.InfoWindowMaximizeClickEvent;
import com.google.gwt.maps.client.event.InfoWindowMaximizeEndHandler.InfoWindowMaximizeEndEvent;
import com.google.gwt.maps.client.event.InfoWindowRestoreClickHandler.InfoWindowRestoreClickEvent;
import com.google.gwt.maps.client.event.InfoWindowRestoreEndHandler.InfoWindowRestoreEndEvent;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.Size;
import com.google.gwt.maps.client.impl.HandlerCollection;
import com.google.gwt.maps.client.impl.InfoWindowImpl;
import com.google.gwt.maps.client.impl.InfoWindowOptionsImpl;
import com.google.gwt.maps.client.impl.JsUtil;
import com.google.gwt.maps.client.impl.MapEvent;
import com.google.gwt.maps.client.impl.MapImpl;
import com.google.gwt.maps.client.impl.MarkerImpl;
import com.google.gwt.maps.client.impl.EventImpl.VoidCallback;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Each map within the Google Maps API may show a single "info window" of type
 * InfoWindow, which displays HTML content in a floating window above the map.
 * The info window looks a little like a comic-book word balloon; it has a
 * content area and a tapered stem, where the tip of the stem is at a specified
 * point on the map. You can see the info window in action by clicking a marker
 * in Google Maps.
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

    @Override
    public boolean isAttached() {
      return true;
    }
  }

  private HandlerCollection<InfoWindowCloseClickHandler> infoWindowCloseClickHandlers;
  private HandlerCollection<InfoWindowMaximizeClickHandler> infoWindowMaximizeClickHandlers;
  private HandlerCollection<InfoWindowMaximizeEndHandler> infoWindowMaximizeEndHandlers;
  private HandlerCollection<InfoWindowRestoreClickHandler> infoWindowRestoreClickHandlers;
  private HandlerCollection<InfoWindowRestoreEndHandler> infoWindowRestoreEndHandlers;
  
  private final JavaScriptObject jsoPeer;

  // private InfoWindowEventCallbacks eventCallbacks = null;

  private final MapWidget map;

  /**
   * The virtual panel is used as a point to attach.
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
   * 
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addInfoWindowCloseClickHandler(
      final InfoWindowCloseClickHandler handler) {
    if (infoWindowCloseClickHandlers == null) {
      infoWindowCloseClickHandlers = new HandlerCollection<InfoWindowCloseClickHandler>(
          jsoPeer, MapEvent.CLOSECLICK);
    }

    infoWindowCloseClickHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        InfoWindowCloseClickEvent e = new InfoWindowCloseClickEvent(
            InfoWindow.this);
        handler.onCloseClick(e);
      }
    });
  }

  /**
   * TODO(zundel): add javadoc comments.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addInfoWindowMaximizeClickHandler(
      final InfoWindowMaximizeClickHandler handler) {
    if (infoWindowMaximizeClickHandlers == null) {
      infoWindowMaximizeClickHandlers = new HandlerCollection<InfoWindowMaximizeClickHandler>(
          jsoPeer, MapEvent.MAXIMIZECLICK);
    }

    infoWindowMaximizeClickHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        InfoWindowMaximizeClickEvent e = new InfoWindowMaximizeClickEvent(
            InfoWindow.this);
        handler.onMaximizeClick(e);
      }
    });
  }

  /**
   * 
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addInfoWindowMaximizeEndHandler(
      final InfoWindowMaximizeEndHandler handler) {
    if (infoWindowMaximizeEndHandlers == null) {
      infoWindowMaximizeEndHandlers = new HandlerCollection<InfoWindowMaximizeEndHandler>(
          jsoPeer, MapEvent.MAXIMIZEEND);
    }

    infoWindowMaximizeEndHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        InfoWindowMaximizeEndEvent e = new InfoWindowMaximizeEndEvent(
            InfoWindow.this);
        handler.onMaximizeEnd(e);
      }
    });
  }

  /**
   * 
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addInfoWindowRestoreClickHandler(
      final InfoWindowRestoreClickHandler handler) {
    if (infoWindowRestoreClickHandlers == null) {
      infoWindowRestoreClickHandlers = new HandlerCollection<InfoWindowRestoreClickHandler>(
          jsoPeer, MapEvent.RESTORECLICK);
    }

    infoWindowRestoreClickHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        InfoWindowRestoreClickEvent e = new InfoWindowRestoreClickEvent(
            InfoWindow.this);
        handler.onRestoreClick(e);
      }
    });
  }

  /**
   * 
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addInfoWindowRestoreEndHandler(
      final InfoWindowRestoreEndHandler handler) {
    if (infoWindowRestoreEndHandlers == null) {
      infoWindowRestoreEndHandlers = new HandlerCollection<InfoWindowRestoreEndHandler>(
          jsoPeer, MapEvent.RESTOREEND);
    }

    infoWindowRestoreEndHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        InfoWindowRestoreEndEvent e = new InfoWindowRestoreEndEvent(
            InfoWindow.this);
        handler.onRestoreEnd(e);
      }
    });
  }

  /**
   * Removes all handlers of this map added with
   * {@link InfoWindow#addInfoWindowRestoreClickHandler(InfoWindowRestoreClickHandler)}.
   */
  public void clearInfoWindowRestoreClickHandlers() {
    if (infoWindowRestoreClickHandlers != null) {
      infoWindowRestoreClickHandlers.clearHandlers();
    }
  }

  /**
   * Removes all handlers of this map added with
   * {@link InfoWindow#addInfoWindowRestoreEndHandler(InfoWindowRestoreEndHandler)}.
   */
  public void clearInfoWindowRestoreEndHandlers() {
    if (infoWindowRestoreEndHandlers != null) {
      infoWindowRestoreEndHandlers.clearHandlers();
    }
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
    JSList<Element> elementList = InfoWindowImpl.impl.getContentContainers(jsoPeer);
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
   * If an info window is already open on the map.
   * 
   * @param point position to use as the origin of the Info Window.
   * @param content An object that is filled with the content to display in the
   *          InfoWindow.
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

  /**
   * Create a new info window on a Marker.
   * 
   * @param marker Marker to open window over.
   * @param content An object that is filled with the content to display in the
   *          InfoWindow.
   */
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
   * Removes a single handler of this map previously added with
   * {@link InfoWindow#addInfoWindowCloseClickHandler(InfoWindowCloseClickHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeInfoWindowCloseClickHandler(
      InfoWindowCloseClickHandler handler) {
    if (infoWindowCloseClickHandlers != null) {
      infoWindowCloseClickHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link InfoWindow#addInfoWindowMaximizeClickHandler(InfoWindowMaximizeClickHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeInfoWindowMaximizeClickHandler(
      InfoWindowMaximizeClickHandler handler) {
    if (infoWindowMaximizeClickHandlers != null) {
      infoWindowMaximizeClickHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link InfoWindow#addInfoWindowMaximizeEndHandler(InfoWindowMaximizeEndHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeInfoWindowMaximizeEndHandler(
      InfoWindowMaximizeEndHandler handler) {
    if (infoWindowMaximizeEndHandlers != null) {
      infoWindowMaximizeEndHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link InfoWindow#addInfoWindowRestoreClickHandler(InfoWindowRestoreClickHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeInfoWindowRestoreClickHandler(
      InfoWindowRestoreClickHandler handler) {
    if (infoWindowRestoreClickHandlers != null) {
      infoWindowRestoreClickHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link InfoWindow#addInfoWindowRestoreEndHandler(InfoWindowRestoreEndHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeInfoWindowRestoreEndHandler(
      InfoWindowRestoreEndHandler handler) {
    if (infoWindowRestoreEndHandlers != null) {
      infoWindowRestoreEndHandlers.removeHandler(handler);
    }
  }

  // TODO(zundel): Implement reset?

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

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(InfoWindowCloseClickEvent event) {
    infoWindowCloseClickHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(InfoWindowMaximizeClickEvent event) {
    infoWindowMaximizeClickHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(InfoWindowMaximizeEndEvent event) {
    infoWindowMaximizeEndHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * @param event an event to deliver to the handler.
   */
  void trigger(InfoWindowRestoreClickEvent event) {
    infoWindowRestoreClickHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(InfoWindowRestoreEndEvent event) {
    infoWindowRestoreEndHandlers.trigger();
  }

  private void beginAttach(InfoWindowContent content) {
    List<Widget> contentWidgets = content.getWidgets();
    for (int i = 0; i < contentWidgets.size(); i++) {
      virtualPanel.beginAttach(contentWidgets.get(i));
    }
    List<InfoWindowListener> listeners = content.getInfoWindowListeners();
    if (listeners != null) {
      initEventCallbacks(content, listeners);
    }
  }

  private void finishAttach(InfoWindowContent content) {
    final List<Widget> contentWidgets = content.getWidgets();
    for (int i = 0; i < contentWidgets.size(); i++) {
      virtualPanel.finishAttach(contentWidgets.get(i));
    }
    // TODO(zundel): modify to use InfoWindowClosedHandler
    map.addInfoWindowListener(new InfoWindowListener() {
      public void onInfoWindowClosed(MapWidget sender) {
        for (int i = 0; i < contentWidgets.size(); i++) {
          virtualPanel.remove(contentWidgets.get(i));
        }
      }

      public void onInfoWindowOpened(MapWidget sender) {
      }
    });
  }

  /**
   * This method implements a chain of listeners for the InfoWindow object
   * instead of just a single callback as provided by the native JavaScript Maps
   * API. This was done to make the GWT API more intuitive to Java programmers.
   */
  private void initEventCallbacks(InfoWindowContent content,
      List<InfoWindowListener> listeners) {
    final List<InfoWindowListener> listenerList = new ArrayList<InfoWindowListener>(
        listeners);

    // Initialize internal callbacks in InfoWindowOptions that will kick off
    // the list of InfoWindowListeners stored in this object.
    InfoWindowOptionsImpl.impl.setOnCloseFn(content.getOptions(),
        new VoidCallback() {
          @Override
          public void callback() {
            Iterator<InfoWindowListener> iter = listenerList.iterator();
            InfoWindowListener cb;

            while (iter.hasNext()) {
              cb = iter.next();
              cb.onInfoWindowClosed(map);
            }
          }
        });
    InfoWindowOptionsImpl.impl.setOnOpenFn(content.getOptions(),
        new VoidCallback() {
          @Override
          public void callback() {
            Iterator<InfoWindowListener> iter = listenerList.iterator();
            InfoWindowListener cb;

            while (iter.hasNext()) {
              cb = iter.next();
              cb.onInfoWindowOpened(map);
            }
          }
        });
  }

}

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

import com.google.gwt.maps.client.event.InfoWindowCloseClickHandler;
import com.google.gwt.maps.client.event.InfoWindowMaximizeClickHandler;
import com.google.gwt.maps.client.event.InfoWindowMaximizeEndHandler;
import com.google.gwt.maps.client.event.InfoWindowRestoreClickHandler;
import com.google.gwt.maps.client.event.InfoWindowRestoreEndHandler;
import com.google.gwt.maps.client.event.MapInfoWindowCloseHandler;
import com.google.gwt.maps.client.event.MapInfoWindowOpenHandler;
import com.google.gwt.maps.client.event.MarkerInfoWindowCloseHandler;
import com.google.gwt.maps.client.event.MarkerInfoWindowOpenHandler;
import com.google.gwt.maps.client.event.InfoWindowCloseClickHandler.InfoWindowCloseClickEvent;
import com.google.gwt.maps.client.event.InfoWindowMaximizeClickHandler.InfoWindowMaximizeClickEvent;
import com.google.gwt.maps.client.event.InfoWindowMaximizeEndHandler.InfoWindowMaximizeEndEvent;
import com.google.gwt.maps.client.event.InfoWindowRestoreClickHandler.InfoWindowRestoreClickEvent;
import com.google.gwt.maps.client.event.InfoWindowRestoreEndHandler.InfoWindowRestoreEndEvent;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.Size;
import com.google.gwt.maps.client.impl.HandlerCollection;
import com.google.gwt.maps.client.impl.InfoWindowImpl;
import com.google.gwt.maps.client.impl.JsUtil;
import com.google.gwt.maps.client.impl.MapEvent;
import com.google.gwt.maps.client.impl.MapImpl;
import com.google.gwt.maps.client.impl.MarkerImpl;
import com.google.gwt.maps.client.impl.EventImpl.VoidCallback;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.Overlay.ConcreteOverlay;
import com.google.gwt.maps.jsio.client.JSList;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

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
public final class InfoWindow extends ConcreteOverlay {

  private static class VirtualPanel extends ComplexPanel {

    public void attach(Widget w) {
      // Detach new child.
      w.removeFromParent();
      // Logical attach.
      getChildren().add(w);
      // Adopt.
      adopt(w);
    }

    @Override
    public boolean isAttached() {
      return true;
    }
  }

  /**
   * Handlers that cover attaching and removing widgets from the virtual panels
   * created above.
   */
  private InfoWindowMaximizeEndHandler attachMaximizeEndHandler;
  private InfoWindowRestoreEndHandler attachRestoreEndHandler;

  /**
   * Private copies of the widgets passed in from InfoWindowContent. Make copies
   * so that an unsuspecting user doesn't overwrite this information.
   */
  private Widget contentMaxWidget;
  private List<Widget> contentWidgets;
  private boolean contentWidgetsAttached = false;
  private boolean contentMaxWidgetAttached = false;

  /**
   * Tracks handlers attached to this InfoWindow instance.
   */
  private HandlerCollection<InfoWindowCloseClickHandler> infoWindowCloseClickHandlers;
  private HandlerCollection<InfoWindowMaximizeClickHandler> infoWindowMaximizeClickHandlers;
  private HandlerCollection<InfoWindowMaximizeEndHandler> infoWindowMaximizeEndHandlers;
  private HandlerCollection<InfoWindowRestoreClickHandler> infoWindowRestoreClickHandlers;
  private HandlerCollection<InfoWindowRestoreEndHandler> infoWindowRestoreEndHandlers;

  private final MapWidget map;

  /**
   * Virtual Panels used as a point to attach the maximized/restored state
   * widgets. This plugs the widget into the GWT Event system.
   */
  private final VirtualPanel virtualMaximizedPanel = new VirtualPanel();
  private final VirtualPanel virtualRestoredPanel = new VirtualPanel();

  /**
   * Package-private constructor to prevent instantiation from outside of the
   * API.
   * 
   * @param map the map to which this InfoWindow belongs.
   */
  InfoWindow(MapWidget map) {
    super(MapImpl.impl.getInfoWindow(map));
    this.map = map;
  }

  /**
   * This event is fired when the info window close button is clicked. An event
   * handler for this event can implement to close the info window, by calling
   * the {@link InfoWindow#close()} method.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addInfoWindowCloseClickHandler(
      final InfoWindowCloseClickHandler handler) {
    maybeInitInfoWindowCloseClickHandlers();

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
   * Signals that the info window is about to be maximized.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addInfoWindowMaximizeClickHandler(
      final InfoWindowMaximizeClickHandler handler) {
    maybeInitInfoWindowMaximizeClickHandlers();

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
   * Signals that the info window is about to be maximized.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addInfoWindowMaximizeEndHandler(
      final InfoWindowMaximizeEndHandler handler) {
    maybeInitInfoWindowMaximizeEndHandlers();

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
   * Signals that the info window is about to be restored to the non-maximized
   * state.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addInfoWindowRestoreClickHandler(
      final InfoWindowRestoreClickHandler handler) {
    maybeInitInfoWindowRestoreClickHandlers();

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
   * Signals that the info window has completed the restore operation to the
   * non-maximized state.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addInfoWindowRestoreEndHandler(
      final InfoWindowRestoreEndHandler handler) {
    maybeInitInfoWindowRestoreEndHandlers();

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
   * Returns <code>true</code> if the info window is visible.
   * 
   * @return <code>true</code> if the info window is visible
   */
  public boolean isVisible() {
    return !InfoWindowImpl.impl.isHidden(jsoPeer);
  }

  /**
   * Maximizes the infowindow. The infowindow must have been opened with
   * maxContent or maxTitle options, and it must not have had its maximization
   * disabled through {@link InfoWindow#setMaximizeEnabled}
   */
  public void maximize() {
    InfoWindowImpl.impl.maximize(jsoPeer);
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
    addMapAttachHandlers(content);

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
  }

  /**
   * Create a new info window on a Marker.
   * 
   * @param marker Marker to open window over.
   * @param content An object that is filled with the content to display in the
   *          InfoWindow.
   */
  public void open(Marker marker, InfoWindowContent content) {
    addMarkerAttachHandlers(marker, content);

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
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link InfoWindow#addInfoWindowCloseClickHandler(InfoWindowCloseClickHandler)} .
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
   * {@link InfoWindow#addInfoWindowMaximizeClickHandler(InfoWindowMaximizeClickHandler)} .
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
   * {@link InfoWindow#addInfoWindowMaximizeEndHandler(InfoWindowMaximizeEndHandler)} .
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
   * {@link InfoWindow#addInfoWindowRestoreClickHandler(InfoWindowRestoreClickHandler)} .
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
   * {@link InfoWindow#addInfoWindowRestoreEndHandler(InfoWindowRestoreEndHandler)} .
   * 
   * @param handler the handler to remove
   */
  public void removeInfoWindowRestoreEndHandler(
      InfoWindowRestoreEndHandler handler) {
    if (infoWindowRestoreEndHandlers != null) {
      infoWindowRestoreEndHandlers.removeHandler(handler);
    }
  }

  /**
   * Restores the info window to its default (non-maximized) state. The
   * {@link InfoWindow} must have been opened with maxContent or maxTitle
   * options
   */
  public void restore() {
    InfoWindowImpl.impl.restore(jsoPeer);
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

  // TODO(zundel): Implement reset?

  /**
   * Enables or disables maximization of the info window. A maximizable info
   * window expands to fill most of the map with contents specified via the
   * maxContent and maxTitle properties of GInfoWindowOptions. The info window
   * must have been opened with maxContent or maxTitle options in order for this
   * function to have any effect. An info window opened with maxContent or
   * maxTitle will have maximization enabled by default.
   * 
   * Note that if the info window is currently opened and this method is set to
   * disable maximizing, this function will remove the maximize button but will
   * not restore the window to its minimized state.
   */
  public void setMaximizeEnabled(boolean enabled) {
    if (enabled) {
      InfoWindowImpl.impl.enableMaximize(jsoPeer);
    } else {
      InfoWindowImpl.impl.disableMaximize(jsoPeer);
    }
  }

  /**
   * Shows or hides the info window.
   * 
   * @param visible pass <code>true</code> to show the info window,
   *          <code>false</code> to hide.
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
    maybeInitInfoWindowCloseClickHandlers();
    infoWindowCloseClickHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(InfoWindowMaximizeClickEvent event) {
    maybeInitInfoWindowMaximizeClickHandlers();
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
    maybeInitInfoWindowMaximizeEndHandlers();
    infoWindowMaximizeEndHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(InfoWindowRestoreClickEvent event) {
    maybeInitInfoWindowRestoreClickHandlers();
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
    maybeInitInfoWindowRestoreEndHandlers();
    infoWindowRestoreEndHandlers.trigger();
  }

  /**
   * Adds handlers that take care of attaching and detaching widgets from the
   * hierarchy.
   * 
   * @param content contains the widgets to attach/detach.
   */
  private void addMapAttachHandlers(InfoWindowContent content) {

    // Make a private copy of the widgets passed in from the content object
    // so no one can pull the rug out from under our attach handlers.
    contentWidgets = content.getWidgets();
    contentMaxWidget = content.getMaxContent();

    MapInfoWindowOpenHandler openHandler = new MapInfoWindowOpenHandler() {

      public void onInfoWindowOpen(MapInfoWindowOpenEvent event) {
        // Make sure this callback is invoked only once.
        event.getSender().removeInfoWindowOpenHandler(this);
        attachContentWidgets();
      }

    };
    map.addInfoWindowOpenHandler(openHandler);

    addMaxContentHandlers();
    MapInfoWindowCloseHandler closeHandler = new MapInfoWindowCloseHandler() {

      public void onInfoWindowClose(MapInfoWindowCloseEvent event) {
        event.getSender().removeInfoWindowCloseHandler(this);
        doCloseHandler(null);
      }
    };
    map.addInfoWindowCloseHandler(closeHandler);
  }

  /**
   * Adds handlers that take care of attaching and detaching widgets from the
   * hierarchy.
   * 
   * @param content contains the widgets to attach/detach.
   */
  private void addMarkerAttachHandlers(final Marker marker,
      InfoWindowContent content) {

    // Make a private copy of the widgets passed in from the content object
    // so no one can pull the rug out from under our attach handlers.
    contentWidgets = content.getWidgets();
    contentMaxWidget = content.getMaxContent();

    MarkerInfoWindowOpenHandler openHandler = new MarkerInfoWindowOpenHandler() {

      public void onInfoWindowOpen(MarkerInfoWindowOpenEvent event) {
        // Make sure this callback is invoked only once.
        event.getSender().removeMarkerInfoWindowOpenHandler(this);
        attachContentWidgets();
      }

    };
    marker.addMarkerInfoWindowOpenHandler(openHandler);

    addMaxContentHandlers();
    MarkerInfoWindowCloseHandler closeHandler = new MarkerInfoWindowCloseHandler() {

      public void onInfoWindowClose(MarkerInfoWindowCloseEvent event) {
        event.getSender().removeMarkerInfoWindowCloseHandler(this);
        doCloseHandler(marker);
      }
    };
    marker.addMarkerInfoWindowCloseHandler(closeHandler);
  }

  /**
   * Removes the handlers used to attach / detach widgets.
   */
  private void doCloseHandler(Marker marker) {
    if (contentWidgetsAttached) {
      removeContentWidgets();
    }
    if (contentMaxWidgetAttached) {
      removeMaxContentWidget();
    }
    if (attachRestoreEndHandler != null) {
      removeInfoWindowRestoreEndHandler(attachRestoreEndHandler);
      attachRestoreEndHandler = null;
    }
    if (attachMaximizeEndHandler != null) {
      removeInfoWindowMaximizeEndHandler(attachMaximizeEndHandler);
      attachMaximizeEndHandler = null;
    }
  }

  /**
   * When the info window is maximized and restored, the widgets need to be
   * detached and re-attached from the 2 virtual panels, as they are detached
   * and re-attached to the DOM.
   */
  private void addMaxContentHandlers() {
    if (contentMaxWidget == null) {
      return;
    }

    if (attachMaximizeEndHandler == null) {
      attachMaximizeEndHandler = new InfoWindowMaximizeEndHandler() {

        public void onMaximizeEnd(InfoWindowMaximizeEndEvent event) {
          removeContentWidgets();
          attachMaxContentWidget();
        }
      };
      addInfoWindowMaximizeEndHandler(attachMaximizeEndHandler);
    }

    if (attachRestoreEndHandler == null) {
      attachRestoreEndHandler = new InfoWindowRestoreEndHandler() {

        public void onRestoreEnd(InfoWindowRestoreEndEvent event) {
          removeMaxContentWidget();
          attachContentWidgets();
        }
      };
      addInfoWindowRestoreEndHandler(attachRestoreEndHandler);
    }
  }

  /**
   * Run the attach step (must be done after elements are actually attached to
   * the DOM).
   */
  private void attachContentWidgets() {
    if (!contentWidgetsAttached) {
      for (int i = 0; i < contentWidgets.size(); i++) {
        virtualRestoredPanel.attach(contentWidgets.get(i));
      }
      contentWidgetsAttached = true;
    }
  }

  private void attachMaxContentWidget() {
    if (!contentMaxWidgetAttached) {
      virtualMaximizedPanel.attach(contentMaxWidget);
      contentMaxWidgetAttached = true;
    }
  }

  /**
   * Lazy init the {@link HandlerCollection}.
   */
  private void maybeInitInfoWindowCloseClickHandlers() {
    if (infoWindowCloseClickHandlers == null) {
      infoWindowCloseClickHandlers = new HandlerCollection<InfoWindowCloseClickHandler>(
          jsoPeer, MapEvent.CLOSECLICK);
    }
  }

  /**
   * Lazy init the {@link HandlerCollection}.
   */
  private void maybeInitInfoWindowMaximizeClickHandlers() {
    if (infoWindowMaximizeClickHandlers == null) {
      infoWindowMaximizeClickHandlers = new HandlerCollection<InfoWindowMaximizeClickHandler>(
          jsoPeer, MapEvent.MAXIMIZECLICK);
    }
  }

  /**
   * Lazy init the {@link HandlerCollection}.
   */
  private void maybeInitInfoWindowMaximizeEndHandlers() {
    if (infoWindowMaximizeEndHandlers == null) {
      infoWindowMaximizeEndHandlers = new HandlerCollection<InfoWindowMaximizeEndHandler>(
          jsoPeer, MapEvent.MAXIMIZEEND);
    }
  }

  /**
   * Lazy init the {@link HandlerCollection}.
   */
  private void maybeInitInfoWindowRestoreClickHandlers() {
    if (infoWindowRestoreClickHandlers == null) {
      infoWindowRestoreClickHandlers = new HandlerCollection<InfoWindowRestoreClickHandler>(
          jsoPeer, MapEvent.RESTORECLICK);
    }
  }

  /**
   * Lazy init the HandlerCollection.
   */
  private void maybeInitInfoWindowRestoreEndHandlers() {
    if (infoWindowRestoreEndHandlers == null) {
      infoWindowRestoreEndHandlers = new HandlerCollection<InfoWindowRestoreEndHandler>(
          jsoPeer, MapEvent.RESTOREEND);
    }
  }

  private void removeContentWidgets() {
    if (contentWidgetsAttached) {
      contentWidgetsAttached = false;
      for (int i = 0; i < contentWidgets.size(); i++) {
        virtualRestoredPanel.remove(contentWidgets.get(i));
      }
    }
  }

  private void removeMaxContentWidget() {
    if (contentMaxWidgetAttached) {
      contentMaxWidgetAttached = false;
      virtualMaximizedPanel.remove(contentMaxWidget);
    }
  }
}

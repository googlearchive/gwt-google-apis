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

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptException;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.maps.client.control.Control;
import com.google.gwt.maps.client.control.ControlAnchor;
import com.google.gwt.maps.client.control.ControlPosition;
import com.google.gwt.maps.client.event.EarthInstanceHandler;
import com.google.gwt.maps.client.event.MapAddMapTypeHandler;
import com.google.gwt.maps.client.event.MapAddOverlayHandler;
import com.google.gwt.maps.client.event.MapClearOverlaysHandler;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.event.MapDoubleClickHandler;
import com.google.gwt.maps.client.event.MapDragEndHandler;
import com.google.gwt.maps.client.event.MapDragHandler;
import com.google.gwt.maps.client.event.MapDragStartHandler;
import com.google.gwt.maps.client.event.MapInfoWindowBeforeCloseHandler;
import com.google.gwt.maps.client.event.MapInfoWindowCloseHandler;
import com.google.gwt.maps.client.event.MapInfoWindowOpenHandler;
import com.google.gwt.maps.client.event.MapMouseMoveHandler;
import com.google.gwt.maps.client.event.MapMouseOutHandler;
import com.google.gwt.maps.client.event.MapMouseOverHandler;
import com.google.gwt.maps.client.event.MapMoveEndHandler;
import com.google.gwt.maps.client.event.MapMoveHandler;
import com.google.gwt.maps.client.event.MapMoveStartHandler;
import com.google.gwt.maps.client.event.MapRemoveMapTypeHandler;
import com.google.gwt.maps.client.event.MapRemoveOverlayHandler;
import com.google.gwt.maps.client.event.MapRightClickHandler;
import com.google.gwt.maps.client.event.MapTypeChangedHandler;
import com.google.gwt.maps.client.event.MapZoomEndHandler;
import com.google.gwt.maps.client.event.EarthInstanceHandler.EarthInstanceEvent;
import com.google.gwt.maps.client.event.MapAddMapTypeHandler.MapAddMapTypeEvent;
import com.google.gwt.maps.client.event.MapAddOverlayHandler.MapAddOverlayEvent;
import com.google.gwt.maps.client.event.MapClearOverlaysHandler.MapClearOverlaysEvent;
import com.google.gwt.maps.client.event.MapClickHandler.MapClickEvent;
import com.google.gwt.maps.client.event.MapDoubleClickHandler.MapDoubleClickEvent;
import com.google.gwt.maps.client.event.MapDragEndHandler.MapDragEndEvent;
import com.google.gwt.maps.client.event.MapDragHandler.MapDragEvent;
import com.google.gwt.maps.client.event.MapDragStartHandler.MapDragStartEvent;
import com.google.gwt.maps.client.event.MapInfoWindowBeforeCloseHandler.MapInfoWindowBeforeCloseEvent;
import com.google.gwt.maps.client.event.MapInfoWindowCloseHandler.MapInfoWindowCloseEvent;
import com.google.gwt.maps.client.event.MapInfoWindowOpenHandler.MapInfoWindowOpenEvent;
import com.google.gwt.maps.client.event.MapMouseMoveHandler.MapMouseMoveEvent;
import com.google.gwt.maps.client.event.MapMouseOutHandler.MapMouseOutEvent;
import com.google.gwt.maps.client.event.MapMouseOverHandler.MapMouseOverEvent;
import com.google.gwt.maps.client.event.MapMoveEndHandler.MapMoveEndEvent;
import com.google.gwt.maps.client.event.MapMoveHandler.MapMoveEvent;
import com.google.gwt.maps.client.event.MapMoveStartHandler.MapMoveStartEvent;
import com.google.gwt.maps.client.event.MapRemoveMapTypeHandler.MapRemoveMapTypeEvent;
import com.google.gwt.maps.client.event.MapRemoveOverlayHandler.MapRemoveOverlayEvent;
import com.google.gwt.maps.client.event.MapRightClickHandler.MapRightClickEvent;
import com.google.gwt.maps.client.event.MapTypeChangedHandler.MapTypeChangedEvent;
import com.google.gwt.maps.client.event.MapZoomEndHandler.MapZoomEndEvent;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.LatLngBounds;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.client.geom.Size;
import com.google.gwt.maps.client.impl.HandlerCollection;
import com.google.gwt.maps.client.impl.JsUtil;
import com.google.gwt.maps.client.impl.MapEvent;
import com.google.gwt.maps.client.impl.MapImpl;
import com.google.gwt.maps.client.impl.EventImpl.IntIntCallback;
import com.google.gwt.maps.client.impl.EventImpl.LatLngCallback;
import com.google.gwt.maps.client.impl.EventImpl.MapTypeCallback;
import com.google.gwt.maps.client.impl.EventImpl.OverlayCallback;
import com.google.gwt.maps.client.impl.EventImpl.OverlayLatLngCallback;
import com.google.gwt.maps.client.impl.EventImpl.PointElementOverlayCallback;
import com.google.gwt.maps.client.impl.EventImpl.VoidCallback;
import com.google.gwt.maps.client.overlay.Overlay;
import com.google.gwt.maps.jsio.client.JSList;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RequiresResize;
import com.google.gwt.user.client.ui.Widget;

import java.util.HashMap;
import java.util.Map;

/**
 * A widget that presents a viewable Google Map to a user.
 * 
 * Note: the GEvent.trigger methods are implemented by the API but intended for
 * internal testing only.
 */
public final class MapWidget extends Composite implements RequiresResize {

  private static class MapPanel extends FlowPanel implements RequiresResize {
    public void addVirtual(Widget w) {
      w.removeFromParent();
      getChildren().add(w);
      adopt(w);
    }

    /**
     * This method is automatically called by the parent container whenever it
     * changes size.
     */
    public void onResize() {
      for (Widget child : getChildren()) {
        if (child instanceof RequiresResize) {
          ((RequiresResize) child).onResize();
        }
      }
    }
  }

  static {
    Window.addCloseHandler(new CloseHandler<Window>() {
      public void onClose(CloseEvent<Window> event) {
        nativeUnload();
      }
    });
  }

  static MapWidget createPeer(
      @SuppressWarnings("unused") JavaScriptObject jsoPeer) {
    throw new UnsupportedOperationException();
  }

  /**
   * Performs the actual callback when getting the earth instance. This method
   * is invoked by the JavaScript function passed to
   * GMap2.getEarthInstance(callback). Implementation is in GWT Java rather than
   * JSNI to simplify the code.
   * 
   * @param earthInstanceHandler the {@link EarthInstanceHandler} to invoke.
   * @param mapWidget the map to add this earth handler to.
   * @param earthInstance the potential Earth object, or null if the earth
   *          instance failed to initialize
   */
  @SuppressWarnings("unused")
  private static void earthInstanceCallback(
      EarthInstanceHandler earthInstanceHandler, MapWidget mapWidget,
      JavaScriptObject earthInstance) {
    EarthInstanceEvent event = new EarthInstanceEvent(mapWidget, earthInstance);

    UncaughtExceptionHandler handler = GWT.getUncaughtExceptionHandler();
    if (handler != null) {
      try {
        earthInstanceHandler.onEarthInstance(event);
      } catch (JavaScriptException e) {
        handler.onUncaughtException(e);
      }
    } else {
      earthInstanceHandler.onEarthInstance(event);
    }
  }

  /**
   * Returns a JavaScript function suitable for passing to
   * GMap2.getEarthInstance(callback) via the MapWidgetImpl JSFlyweightWrapper.
   * 
   * @param handler the EarthInstanceHandler to invoke
   * @return the JavaScript callback function
   */
  private static native JavaScriptObject getEarthInstanceCB(
      EarthInstanceHandler handler, MapWidget mapWidget)/*-{
    return function(object) {
    @com.google.gwt.maps.client.MapWidget::earthInstanceCallback(Lcom/google/gwt/maps/client/event/EarthInstanceHandler;Lcom/google/gwt/maps/client/MapWidget;Lcom/google/gwt/core/client/JavaScriptObject;)(handler, mapWidget, object);
    };
  }-*/;

  private static native void nativeUnload() /*-{
    $wnd.GUnload && $wnd.GUnload();
  }-*/;

  private InfoWindow infoWindow;
  private HandlerCollection<MapInfoWindowBeforeCloseHandler> infoWindowBeforeCloseHandlers;
  private HandlerCollection<MapInfoWindowCloseHandler> infoWindowCloseHandlers;
  private HandlerCollection<MapInfoWindowOpenHandler> infoWindowOpenHandlers;
  /* Reference to GMap2 object. */
  private final JavaScriptObject jsoPeer;
  private HandlerCollection<MapAddMapTypeHandler> mapAddMapTypeHandlers;
  private HandlerCollection<MapAddOverlayHandler> mapAddOverlayHandlers;
  private HandlerCollection<MapClearOverlaysHandler> mapClearOverlaysHandlers;
  private HandlerCollection<MapClickHandler> mapClickHandlers;
  private final MapPanel mapContainer = new MapPanel();
  private HandlerCollection<MapDoubleClickHandler> mapDoubleClickHandlers;
  private HandlerCollection<MapDragEndHandler> mapDragEndHandlers;
  private HandlerCollection<MapDragHandler> mapDragHandlers;
  private HandlerCollection<MapDragStartHandler> mapDragStartHandlers;
  private HandlerCollection<MapMouseMoveHandler> mapMouseMoveHandlers;
  private HandlerCollection<MapMouseOutHandler> mapMouseOutHandlers;
  private HandlerCollection<MapMouseOverHandler> mapMouseOverHandlers;
  private HandlerCollection<MapMoveEndHandler> mapMoveEndHandlers;
  private HandlerCollection<MapMoveHandler> mapMoveHandlers;
  private HandlerCollection<MapMoveStartHandler> mapMoveStartHandlers;
  private HandlerCollection<MapRemoveMapTypeHandler> mapRemoveMapTypeHandlers;
  private HandlerCollection<MapRemoveOverlayHandler> mapRemoveOverlayHandlers;
  private HandlerCollection<MapRightClickHandler> mapRightClickHandlers;
  private HandlerCollection<MapTypeChangedHandler> mapTypeChangedHandlers;
  private HandlerCollection<MapZoomEndHandler> mapZoomEndHandlers;

  /**
   * Cache of the map panes returned for this widget.
   */
  private Map<MapPaneType, MapPane> panes;

  public MapWidget() {
    this(null, 1, null, null);
  }

  /**
   * Creates a new map widget and sets the view to the given center and zoom
   * level.
   * 
   * @param center the geographical point about which to center
   * @param zoomLevel the zoom level
   */
  public MapWidget(LatLng center, int zoomLevel) {
    this(center, zoomLevel, null, null);
  }

  /**
   * Creates a new map widget and sets the view to the specified center point
   * and zoom level.
   * 
   * Note: The 'load' event requires that a handler be registered before
   * GMap2.setCenter() is called. Since that method is always called in this
   * constructor, it isn't clear that gwt-google-apis users needs this event.
   * 
   * @param center the geographical point about which to center
   * @param zoomLevel zoomLevel the zoom level
   * @param options optional settings to specify when creating the map.
   */
  public MapWidget(LatLng center, int zoomLevel, MapOptions options) {
    Maps.assertLoaded();
    initWidget(mapContainer);
    jsoPeer = MapImpl.impl.construct(mapContainer.getElement(), options);
    MapImpl.impl.bind(jsoPeer, this);
    if (center == null) {
      center = LatLng.newInstance(0, 0);
    }
    setCenter(center, zoomLevel);
    // Initialize the InfoWindow instance for this map.
    getInfoWindow();
  }

  /**
   * Creates a new map widget and sets the view to the specified center point
   * and zoom level. Also, sets the dragging and draggable cursor values. See
   * the W3C CSS spec for allowable cursor string values.
   * 
   * Note: The 'load' event requires that a handler be registered before
   * GMap2.setCenter() is called. Since that method is always called in this
   * constructor, it isn't clear that gwt-google-apis users needs this event.
   * 
   * @param center the geographical point about which to center
   * @param zoomLevel zoomLevel the zoom level
   * @param draggableCursor CSS name of the cursor to display when the map is
   *          draggable
   * @param draggingCursor CSS name of the cursor to display when the map is
   *          being dragged
   * @deprecated Use {@link #MapWidget(LatLng, int, MapOptions)} instead.
   */
  @Deprecated
  public MapWidget(LatLng center, int zoomLevel, String draggableCursor,
      String draggingCursor) {
    this(center, zoomLevel, MapOptions.newInstance().setDraggableCursor(
        draggableCursor).setDraggingCursor(draggingCursor));
  }

  /**
   * Adds a control to the map. The default position of the control is used. A
   * control instance cannot be added more than once to the map.
   * 
   * @param control the control to be added
   */
  public void addControl(Control control) {
    MapImpl.impl.addControl(jsoPeer, control);
  }

  /**
   * Adds a control to the map at the given position. A control instance cannot
   * be added more than once to the map.
   * 
   * @param control the control to be added
   * @param position the position of the control
   */
  public void addControl(Control control, ControlPosition position) {
    MapImpl.impl.addControl(jsoPeer, control, position);
  }

  /**
   * Adds a Control's widget to the map.
   * 
   * This method is not intended to be called by the user. To add a custom
   * control to the map, subclass
   * {@link com.google.gwt.maps.client.control.Control.CustomControl} and
   * implement the initialize(MapWidget) method.
   * 
   * @param w the control widget to add to the map
   */
  public void addControlWidget(Widget w) {
    mapContainer.add(w);
  }

  /**
   * This event is fired before the info window closes. (Since 2.83)
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addInfoWindowBeforeCloseHandler(
      final MapInfoWindowBeforeCloseHandler handler) {
    maybeInitInfoWindowBeforeCloseHandlers();

    infoWindowBeforeCloseHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MapInfoWindowBeforeCloseEvent e = new MapInfoWindowBeforeCloseEvent(
            MapWidget.this);
        handler.onInfoWindowBeforeClose(e);
      }
    });
  }

  /**
   * This event is fired when the info window closes. The handler
   * {@link MapInfoWindowBeforeCloseHandler} is fired before this event. If a
   * currently open info window is reopened at a different point using another
   * call to openInfoWindow*(), the handler
   * {@link MapInfoWindowBeforeCloseHandler}, {@link MapInfoWindowCloseHandler}
   * and {@link MapInfoWindowOpenHandler} are fired in this order
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addInfoWindowCloseHandler(final MapInfoWindowCloseHandler handler) {
    maybeInitInfoWindowCloseHandlers();

    infoWindowCloseHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MapInfoWindowCloseEvent e = new MapInfoWindowCloseEvent(MapWidget.this);
        handler.onInfoWindowClose(e);
      }
    });
  }

  /**
   * This event is fired when the info window opens.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addInfoWindowOpenHandler(final MapInfoWindowOpenHandler handler) {
    maybeInitInfoWindowOpenHandlers();

    infoWindowOpenHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MapInfoWindowOpenEvent e = new MapInfoWindowOpenEvent(MapWidget.this);
        handler.onInfoWindowOpen(e);
      }
    });
  }

  /**
   * This event is fired when a map type is added to the map.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMapAddMapTypeHandler(final MapAddMapTypeHandler handler) {
    maybeInitMapAddMapTypeHandlers();

    mapAddMapTypeHandlers.addHandler(handler, new MapTypeCallback() {
      @Override
      public void callback(MapType type) {
        MapAddMapTypeEvent e = new MapAddMapTypeEvent(MapWidget.this, type);
        handler.onAddMapType(e);
      }
    });
  }

  /**
   * This event is fired when a single overlay is added to the map by the method
   * addOverlay(). The new overlay is passed as an argument overlay to the event
   * handler.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMapAddOverlayHandler(final MapAddOverlayHandler handler) {
    maybeInitMapAddOverlayHandlers();

    mapAddOverlayHandlers.addHandler(handler, new OverlayCallback() {
      @Override
      public void callback(Overlay overlay) {
        MapAddOverlayEvent e = new MapAddOverlayEvent(MapWidget.this, overlay);
        handler.onAddOverlay(e);
      }
    });
  }

  /**
   * This event is fired when all overlays are removed at once by the method
   * {@link MapWidget#clearOverlays()}.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMapClearOverlaysHandler(final MapClearOverlaysHandler handler) {
    maybeInitMapClearOverlaysHandlers();

    mapClearOverlaysHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MapClearOverlaysEvent e = new MapClearOverlaysEvent(MapWidget.this);
        handler.onClearOverlays(e);
      }
    });
  }

  /**
   * Add a click handler for mouse click events. Note: this event fires even for
   * double click events (twice!).
   * 
   * @param handler handler to invoke on mouse click events.
   */
  public void addMapClickHandler(final MapClickHandler handler) {
    maybeInitMapClickHandlers();

    mapClickHandlers.addHandler(handler, new OverlayLatLngCallback() {
      @Override
      public void callback(Overlay overlay, LatLng latlng, LatLng overlaylatlng) {
        MapClickEvent e = new MapClickEvent(MapWidget.this, overlay, latlng,
            overlaylatlng);
        handler.onClick(e);
      }
    });
  }

  /**
   * Add a double click handler for mouse double click events. Note that this
   * event will not be invoked if the double click was on a marker object.
   * 
   * @param handler handler to invoke on mouse double click events.
   */
  public void addMapDoubleClickHandler(final MapDoubleClickHandler handler) {
    maybeInitMapDoubleClickHandlers();

    mapDoubleClickHandlers.addHandler(handler, new OverlayLatLngCallback() {
      @Override
      public void callback(Overlay overlay, LatLng latlng, LatLng overlayLatLng) {
        // The overlay parameter is always NULL according to the Maps API and is
        // ignored. The overlaylatlng parameter should be ignored as well.
        MapDoubleClickEvent e = new MapDoubleClickEvent(MapWidget.this, latlng);
        handler.onDoubleClick(e);
      }
    });
  }

  /**
   * This event is fired when the user stops dragging the map.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMapDragEndHandler(final MapDragEndHandler handler) {
    maybeInitMapDragEndHandlers();

    mapDragEndHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MapDragEndEvent e = new MapDragEndEvent(MapWidget.this);
        handler.onDragEnd(e);
      }
    });
  }

  /**
   * This event is repeatedly fired while the user drags the map.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMapDragHandler(final MapDragHandler handler) {
    maybeInitMapDragHandlers();

    mapDragHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MapDragEvent e = new MapDragEvent(MapWidget.this);
        handler.onDrag(e);
      }
    });
  }

  /**
   * This event is fired when the user starts dragging the map.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMapDragStartHandler(final MapDragStartHandler handler) {
    maybeInitMapDragStartHandlers();

    mapDragStartHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MapDragStartEvent e = new MapDragStartEvent(MapWidget.this);
        handler.onDragStart(e);
      }
    });
  }

  /**
   * This event is fired when the user moves the mouse over the map from outside
   * the map.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMapMouseMoveHandler(final MapMouseMoveHandler handler) {
    maybeInitMapMouseMoveHandlers();

    mapMouseMoveHandlers.addHandler(handler, new LatLngCallback() {
      @Override
      public void callback(LatLng point) {
        MapMouseMoveEvent e = new MapMouseMoveEvent(MapWidget.this, point);
        handler.onMouseMove(e);
      }
    });
  }

  /**
   * This event is fired when the user moves the mouse over the map from outside
   * the map.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMapMouseOutHandler(final MapMouseOutHandler handler) {
    maybeInitMapMouseOutHandlers();

    mapMouseOutHandlers.addHandler(handler, new LatLngCallback() {
      @Override
      public void callback(LatLng point) {
        MapMouseOutEvent e = new MapMouseOutEvent(MapWidget.this, point);
        handler.onMouseOut(e);
      }
    });
  }

  /**
   * This event is fired when the user moves the mouse over the map from outside
   * the map.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMapMouseOverHandler(final MapMouseOverHandler handler) {
    maybeInitMapMouseOverHandlers();

    mapMouseOverHandlers.addHandler(handler, new LatLngCallback() {
      @Override
      public void callback(LatLng point) {
        MapMouseOverEvent e = new MapMouseOverEvent(MapWidget.this, point);
        handler.onMouseOver(e);
      }
    });
  }

  /**
   * This event is fired when the change of the map view ends.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMapMoveEndHandler(final MapMoveEndHandler handler) {
    maybeInitMapMoveEndHandlers();

    mapMoveEndHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MapMoveEndEvent e = new MapMoveEndEvent(MapWidget.this);
        handler.onMoveEnd(e);
      }
    });
  }

  /**
   * This event is fired, possibly repeatedly, while the map view is changing.
   * 
   * @param handler handler to invoke on map move events.
   */
  public void addMapMoveHandler(final MapMoveHandler handler) {
    maybeInitMapMoveHandlers();

    mapMoveHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MapMoveEvent e = new MapMoveEvent(MapWidget.this);
        handler.onMove(e);
      }
    });
  }

  /**
   * This event is fired when the map view starts changing. This can be caused
   * by dragging, in which case a {@link MapDragStartEvent} is also fired, or by
   * invocation of a method that changes the map view.
   * 
   * @param handler handler to invoke on map move events.
   */
  public void addMapMoveStartHandler(final MapMoveStartHandler handler) {
    maybeInitMapMoveStartHandlers();

    mapMoveStartHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MapMoveStartEvent e = new MapMoveStartEvent(MapWidget.this);
        handler.onMoveStart(e);
      }
    });
  }

  /**
   * This event is fired when a map type is removed from the map.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMapRemoveMapTypeHandler(final MapRemoveMapTypeHandler handler) {
    maybeInitMapRemoveMapTypeEvent();

    mapRemoveMapTypeHandlers.addHandler(handler, new MapTypeCallback() {
      @Override
      public void callback(MapType type) {
        MapRemoveMapTypeEvent e = new MapRemoveMapTypeEvent(MapWidget.this,
            type);
        handler.onRemoveMapType(e);
      }
    });
  }

  /**
   * This handler is fired when a single overlay is removed by the method
   * {@link MapWidget#removeOverlay(Overlay)}. The overlay that was removed is
   * passed as an argument overlay to the event handler.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMapRemoveOverlayHandler(final MapRemoveOverlayHandler handler) {
    maybeInitMapRemoveOverlayHandlers();

    mapRemoveOverlayHandlers.addHandler(handler, new OverlayCallback() {
      @Override
      public void callback(Overlay overlay) {
        MapRemoveOverlayEvent e = new MapRemoveOverlayEvent(MapWidget.this,
            overlay);
        handler.onRemoveOverlay(e);
      }
    });
  }

  /**
   * Add a click handler for mouse right click events.
   * 
   * This event is fired when the DOM contextmenu event is fired on the map
   * container. If the right click was on a marker, then the marker is passed to
   * the event handler in the overlay argument. The pixel coordinates (in the
   * DOM element that holds the map) of the point that was right clicked and the
   * source element of the DOM event are passed in the point and src arguments
   * respectively. Note that if it is a double right click and double click to
   * zoom is enabled, then the map zooms out and no {@link MapRightClickHandler}
   * event is fired. If, however, double click to zoom is disabled, two
   * {@link MapRightClickHandler} events will be fired.
   * 
   * @param handler handler to invoke on mouse click events.
   */
  public void addMapRightClickHandler(final MapRightClickHandler handler) {
    maybeInitMapRightClickHandlers();

    mapRightClickHandlers.addHandler(handler,
        new PointElementOverlayCallback() {
          @Override
          public void callback(Point point, Element elem, Overlay overlay) {
            MapRightClickEvent e = new MapRightClickEvent(MapWidget.this,
                point, elem, overlay);
            handler.onRightClick(e);
          }
        });
  }

  /**
   * Adds a new map type to the map. See section GMapType for how to define
   * custom map types.
   * 
   * @param type
   */
  public void addMapType(MapType type) {
    MapImpl.impl.addMapType(jsoPeer, type);
  }

  /**
   * This event is fired when another map type is selected.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMapTypeChangedHandler(final MapTypeChangedHandler handler) {
    maybeInitMapTypeChangedHandlers();

    mapTypeChangedHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MapTypeChangedEvent e = new MapTypeChangedEvent(MapWidget.this);
        handler.onTypeChanged(e);
      }
    });
  }

  /**
   * This event is fired when the map reaches a new zoom level.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMapZoomEndHandler(final MapZoomEndHandler handler) {
    maybeInitMapZoomEndHandlers();

    mapZoomEndHandlers.addHandler(handler, new IntIntCallback() {
      @Override
      public void callback(int oldZoomLevel, int newZoomLevel) {
        MapZoomEndEvent e = new MapZoomEndEvent(MapWidget.this, oldZoomLevel,
            newZoomLevel);
        handler.onZoomEnd(e);
      }
    });
  }

  /**
   * Adds an overlay to the map and fires any registered
   * {@link MapAddOverlayHandler}.
   * 
   * @param overlay
   */
  public void addOverlay(Overlay overlay) {
    MapImpl.impl.addOverlay(jsoPeer, overlay);
  }

  /**
   * Notifies the map of a change of the size of its container.
   */
  public void checkResize() {
    MapImpl.impl.checkResize(jsoPeer);
  }

  /**
   * Notifies the map of a change in the size of its container and moves to the
   * center of the map. Call this method after the size of the container DOM
   * object has changed, so that the map can adjust itself to fit the new size.
   * 
   * Note: This call may cause problems with the Maps API if called during a
   * Zoom or other transition.
   */
  public void checkResizeAndCenter() {
    LatLng center = getCenter();
    checkResize();
    setCenter(center);
  }

  /**
   * Removes all overlay from the map, and fires any registered
   * {@link MapClearOverlaysHandler}.
   */
  public void clearOverlays() {
    MapImpl.impl.clearOverlays(jsoPeer);
  }

  /**
   * Close the currently open {@link InfoWindow}.
   */
  public void closeInfoWindow() {
    MapImpl.impl.closeInfoWindow(jsoPeer);
  }

  /**
   * Computes the geographical coordinates from pixel coordinates in the div
   * that holds the draggable map. You need this when you implement interaction
   * with custom overlays.
   * 
   * @param pixel the container coordinates
   * @return the corresponding geographical coordinates
   */
  public LatLng convertContainerPixelToLatLng(Point pixel) {
    return MapImpl.impl.fromContainerPixelToLatLng(jsoPeer, pixel);
  }

  /**
   * Computes the geographical coordinates from pixel coordinates in the div
   * that holds the draggable map. This may be helpful for when you implement
   * interaction with custom overlays that don't extend the {@link Overlay}
   * interface. If this doesn't give you the expected output, try
   * {@link #convertContainerPixelToLatLng(Point)} instead.
   * 
   * @param pixel point on the map to convert to Lat/Lng
   * @return a set of converted coordinates
   */
  public LatLng convertDivPixelToLatLng(Point pixel) {
    return MapImpl.impl.fromDivPixelToLatLng(jsoPeer, pixel);
  }

  /**
   * Computes the pixel coordinates of the given geographical point in the DOM
   * element that contains the map on the page.
   * 
   * @param latlng the geographical coordinates
   * @return the corresponding projection pixel
   */
  public Point convertLatLngToContainerPixel(LatLng latlng) {
    return MapImpl.impl.fromLatLngToContainerPixel(jsoPeer, latlng);
  }

  /**
   * Computes the pixel coordinates of the given geographical point in the DOM
   * element that holds the draggable map. You need this method to position a
   * custom overlay when you implement the GOverlay.redraw() method for a custom
   * overlay.
   * 
   * @param latlng the geographical coordinates
   * @return the corresponding projection pixel
   */
  public Point convertLatLngToDivPixel(LatLng latlng) {
    return MapImpl.impl.fromLatLngToDivPixel(jsoPeer, latlng);
  }

  /**
   * Returns the the visible rectangular region of the map view in geographical
   * coordinates.
   * 
   * @return the visible region of the map view
   */
  public LatLngBounds getBounds() {
    return MapImpl.impl.getBounds(jsoPeer);
  }

  /**
   * Returns the zoom level at which the given rectangular region fits in the
   * map view. The zoom level is computed for the currently selected map type.
   * If no map type is selected yet, the first on the list of map types is used.
   * 
   * @param bounds a rectangular region to test
   * @return the zoom level at which the bounds fit in the map view.
   */
  public int getBoundsZoomLevel(LatLngBounds bounds) {
    return MapImpl.impl.getBoundsZoomLevel(jsoPeer, bounds);
  }

  /**
   * Returns the geographical coordinates of the center point of the map view.
   * 
   * @return the center of the map view
   */
  public LatLng getCenter() {
    return MapImpl.impl.getCenter(jsoPeer);
  }

  /**
   * Returns the currently selected map type.
   * 
   * @return the currently selected map type
   */
  public MapType getCurrentMapType() {
    return MapImpl.impl.getCurrentMapType(jsoPeer);
  }

  /**
   * Returns a {@link MapUIOptions} object specifying default behaviour and UI
   * elements for the Map, based on the UI of maps.google.com.
   */
  public MapUIOptions getDefaultUI() {
    return MapImpl.impl.getDefaultUI(jsoPeer);
  }

  /**
   * Returns the draggable object used by this map.
   * 
   * @return the draggable object used by this map.
   */
  public DraggableObject getDragObject() {
    return MapImpl.impl.getDragObject(jsoPeer);
  }

  /**
   * Asynchronously retrieves the Earth instance, initializing it if necessary.
   * On success, the event will contain an initialized Earth Plugin object. On
   * failure, the Earth Plugin object will be null.
   * 
   * @param handler the {@link EarthInstanceHandler} to invoke on initialization
   *          or failure
   */
  public void getEarthInstance(EarthInstanceHandler handler) {
    MapImpl.impl.getEarthInstance(jsoPeer, getEarthInstanceCB(handler, this));
  }

  /**
   * Gets the info window associated with the map.
   * 
   * There is only one info window per map.
   * 
   * @return the info window associated with the map.
   */
  public InfoWindow getInfoWindow() {
    if (infoWindow == null) {
      infoWindow = new InfoWindow(this);
    }
    return infoWindow;
  }

  /**
   * Gets the array of map types registered with this map.
   * 
   * @return the map types registered with this map
   */
  public MapType[] getMapTypes() {
    new ControlPosition(ControlAnchor.BOTTOM_LEFT, 0, 0);
    JSList<MapType> types = MapImpl.impl.getMapTypes(jsoPeer);
    MapType[] returnValue = new MapType[types.size()];
    JsUtil.toArray(types, returnValue);
    return returnValue;
  }

  /**
   * Returns the overlay DIV with the given id. Used by some subclasses of
   * {@link Overlay}.
   * 
   * @param type the id of the layer
   * @return the corresponding overlay pane
   */
  public MapPane getPane(MapPaneType type) {
    // lazy init the hash map
    if (panes == null) {
      panes = new HashMap<MapPaneType, MapPane>();
    }

    // See if we've already created a pane for this layer. Creating the layer
    // twice will cause GWT to assert on the setElement() call.
    MapPane found = panes.get(type);
    if (found != null) {
      return found;
    }

    // Create a new pane panel
    MapPane newPane = MapPane.getPane(this, type);
    panes.put(type, newPane);
    return newPane;
  }

  /**
   * This method is not meant to be published, but is needed internally to
   * support the GeoXmlOverlay gotoDefaultViewport() method.
   * 
   * @return JavaScript object that is encapsulated by the MapWidget object.
   */
  public JavaScriptObject getPeer() {
    return jsoPeer;
  }

  /**
   * Returns the size of the map view in pixels.
   * 
   * @return the size of the map view in pixels
   */
  // TODO(samgross): this should probably be related to the size of the widget
  public Size getSize() {
    return MapImpl.impl.getSize(jsoPeer);
  }

  /**
   * Returns the current zoom level.
   * 
   * @return the current zoom level
   */
  public int getZoomLevel() {
    return MapImpl.impl.getZoom(jsoPeer);
  }

  /**
   * Enables keyboard shortcuts on the map.
   */
  public void installKeyboardHandler() {
    MapImpl.impl.installKeyboardHandler(this);
  }

  /**
   * Returns <code>true</code> if continuous smooth zooming is enabled.
   * 
   * @return <code>true</code> if continuous smooth zooming is enabled
   */
  public boolean isContinuousZoomEnabled() {
    return MapImpl.impl.continuousZoomEnabled(jsoPeer);
  }

  /**
   * Returns <code>true</code> if double-click to zoom is enabled.
   * 
   * @return <code>true</code> if double-click to zoom is enabled
   */
  public boolean isDoubleClickZoomEnabled() {
    return MapImpl.impl.doubleClickZoomEnabled(jsoPeer);
  }

  /**
   * Returns <code>true</code> if dragging of the map is enabled.
   * 
   * @return <code>true</code> if dragging of the map is enabled
   */
  public boolean isDraggable() {
    return MapImpl.impl.draggingEnabled(jsoPeer);
  }

  /**
   * Returns <code>true</code> if opening info windows is enabled.
   * 
   * @return <code>true</code> if opening info windows is enabled
   */
  public boolean isInfoWindowEnabled() {
    return MapImpl.impl.infoWindowEnabled(jsoPeer);
  }

  /**
   * Returns a Boolean indicating whether pinch to zoom is enabled.
   * 
   * @return <code>true</code> if ping to zoom is enabled.
   */
  public boolean isPinchToZoomEnabled() {
    return MapImpl.impl.pinchToZoomEnabled(jsoPeer);
  }

  /**
   * Returns <code>true</code> if zooming using a mouse's scroll wheel is
   * enabled.
   * 
   * @return <code>true</code> if zooming using a mouse's scroll wheel is
   *         enabled
   */
  public boolean isScrollWheelZoomEnabled() {
    return MapImpl.impl.scrollWheelZoomEnabled(jsoPeer);
  }

  /**
   * This method is automatically called by the parent container whenever it
   * changes size. This allows a map that is sized to a certain percentage of
   * its parent container to adjust its contents (eliminating grey areas at the
   * border).
   */
  public void onResize() {
    checkResizeAndCenter();
  }

  /**
   * Starts a pan animation by the given distance in pixels. +1 is left and up,
   * -1 is right and down.
   * 
   * @param dx the number of pixels to pan left
   * @param dy the number of pixels to pan up
   */
  public void panBy(int dx, int dy) {
    MapImpl.impl.panBy(jsoPeer, Size.newInstance(dx, dy));
  }

  /**
   * Starts a pan animation by 1/3rd the size of the map in the indicated
   * directions. +1 is right and down, -1 is left and up, respectively. For
   * example, to do a large pan east, do panDirection(-1, 0).
   * 
   * @param dx the number of units to pan left
   * @param dy the number of units to pan up
   */
  public void panDirection(int dx, int dy) {
    MapImpl.impl.panDirection(jsoPeer, dx, dy);
  }

  /**
   * Centers the map to the given point. If the point is visible in the current
   * map view, the map pans in a smooth animation.
   * 
   * @param center the new center
   */
  public void panTo(LatLng center) {
    MapImpl.impl.panTo(jsoPeer, center);
  }

  /**
   * Removes the given control from the map.
   * 
   * @param control the control to remove
   */
  public void removeControl(Control control) {
    MapImpl.impl.removeControl(jsoPeer, control);
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link MapWidget#addInfoWindowBeforeCloseHandler(MapInfoWindowBeforeCloseHandler)}
   * .
   * 
   * @param handler the handler to remove
   */
  public void removeInfoWindowBeforeCloseHandler(
      MapInfoWindowBeforeCloseHandler handler) {
    if (infoWindowBeforeCloseHandlers != null) {
      infoWindowBeforeCloseHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link MapWidget#addInfoWindowCloseHandler(MapInfoWindowCloseHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeInfoWindowCloseHandler(MapInfoWindowCloseHandler handler) {
    if (infoWindowCloseHandlers != null) {
      infoWindowCloseHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link MapWidget#addInfoWindowOpenHandler(MapInfoWindowOpenHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeInfoWindowOpenHandler(MapInfoWindowOpenHandler handler) {
    if (infoWindowOpenHandlers != null) {
      infoWindowOpenHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link MapWidget#addMapAddMapTypeHandler(MapAddMapTypeHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeMapAddMapTypeHandler(MapAddMapTypeHandler handler) {
    if (mapAddMapTypeHandlers != null) {
      mapAddMapTypeHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link MapWidget#addMapAddOverlayHandler(MapAddOverlayHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeMapAddOverlayHandler(MapAddOverlayHandler handler) {
    if (mapAddOverlayHandlers != null) {
      mapAddOverlayHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link MapWidget#addMapClearOverlaysHandler(MapClearOverlaysHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeMapClearOverlaysHandler(MapClearOverlaysHandler handler) {
    if (mapClearOverlaysHandlers != null) {
      mapClearOverlaysHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link MapWidget#addMapClickHandler(MapClickHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeMapClickHandler(MapClickHandler handler) {
    if (mapClickHandlers != null) {
      mapClickHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link MapWidget#addMapDoubleClickHandler(MapDoubleClickHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeMapDoubleClickHandler(MapDoubleClickHandler handler) {
    if (mapDoubleClickHandlers != null) {
      mapDoubleClickHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link MapWidget#addMapDragEndHandler(MapDragEndHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeMapDragEndHandler(MapDragEndHandler handler) {
    if (mapDragEndHandlers != null) {
      mapDragEndHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link MapWidget#addMapDragHandler(MapDragHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeMapDragHandler(MapDragHandler handler) {
    if (mapDragHandlers != null) {
      mapDragHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link MapWidget#addMapDragStartHandler(MapDragStartHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeMapDragStartHandler(MapDragStartHandler handler) {
    if (mapDragStartHandlers != null) {
      mapDragStartHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link MapWidget#addMapMouseOutHandler(MapMouseOutHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeMapMouseMoveHandler(MapMouseMoveHandler handler) {
    if (mapMouseMoveHandlers != null) {
      mapMouseMoveHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link MapWidget#addMapMouseOutHandler(MapMouseOutHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeMapMouseOutHandler(MapMouseOutHandler handler) {
    if (mapMouseOutHandlers != null) {
      mapMouseOutHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link MapWidget#addMapMouseOverHandler(MapMouseOverHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeMapMouseOverHandler(MapMouseOverHandler handler) {
    if (mapMouseOverHandlers != null) {
      mapMouseOverHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link MapWidget#addMapMoveEndHandler(MapMoveEndHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeMapMoveEndHandler(MapMoveEndHandler handler) {
    if (mapMoveEndHandlers != null) {
      mapMoveEndHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link MapWidget#addMapMoveHandler(MapMoveHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeMapMoveHandler(MapMoveHandler handler) {
    if (mapMoveHandlers != null) {
      mapMoveHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link MapWidget#addMapMoveStartHandler(MapMoveStartHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeMapMoveStartHandler(MapMoveStartHandler handler) {
    if (mapMoveStartHandlers != null) {
      mapMoveStartHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link MapWidget#addMapRemoveMapTypeHandler(MapRemoveMapTypeHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeMapRemoveMapTypeHandler(MapRemoveMapTypeHandler handler) {
    if (mapRemoveMapTypeHandlers != null) {
      mapRemoveMapTypeHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link MapWidget#addMapRemoveOverlayHandler(MapRemoveOverlayHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeMapRemoveOverlayHandler(MapRemoveOverlayHandler handler) {
    if (mapRemoveOverlayHandlers != null) {
      mapRemoveOverlayHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link MapWidget#addMapRightClickHandler(MapRightClickHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeMapRightClickHandler(MapRightClickHandler handler) {
    if (mapRightClickHandlers != null) {
      mapRightClickHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes the map type from the map.
   * 
   * If the current map type is removed, the map will switch to the first map
   * type. The last remaining map type cannot be removed.
   * 
   * This method will update the set of buttons displayed by the {@link Control}
   * and will fire any registered instances of {@link MapRemoveMapTypeHandler}.
   */
  public void removeMapType(MapType type) {
    MapImpl.impl.removeMapType(jsoPeer, type);
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link MapWidget#addMapTypeChangedHandler(MapTypeChangedHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeMapTypeChangedHandler(MapTypeChangedHandler handler) {
    if (mapTypeChangedHandlers != null) {
      mapTypeChangedHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link MapWidget#addMapZoomEndHandler(MapZoomEndHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeMapZoomEndHandler(MapZoomEndHandler handler) {
    if (mapZoomEndHandlers != null) {
      mapZoomEndHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes the overlay from the map. If the overlay was on the map, it fires
   * any {@link MapRemoveOverlayHandler} handlers.
   * 
   * @param overlay the overlay to remove from the map
   */
  public void removeOverlay(Overlay overlay) {
    MapImpl.impl.removeOverlay(jsoPeer, overlay);
  }

  /**
   * Restores the map view that was saved by {@link MapWidget#savePosition()}.
   */
  public void returnToSavedPosition() {
    MapImpl.impl.returnToSavedPosition(jsoPeer);
  }

  /**
   * Stores the current map position and zoom level for later recall by
   * {@link MapWidget#returnToSavedPosition()}.
   */
  public void savePosition() {
    MapImpl.impl.savePosition(jsoPeer);
  }

  /**
   * Centers the map to the given latitude and longitude.
   * 
   * To center the map using a smooth animation see
   * {@link MapWidget#panTo(LatLng)}.
   * 
   * @param center the geographical coordinate about which to center
   */
  public void setCenter(LatLng center) {
    MapImpl.impl.setCenter(jsoPeer, center);
  }

  /**
   * Centers the map to the given latitude and longitude and sets the zoom
   * level.
   * 
   * To center the map using a smooth animation see
   * {@link MapWidget#panTo(LatLng)}.
   * 
   * @param center the geographical coordinate about which to center
   * @param zoomLevel the zoom level
   */
  public void setCenter(LatLng center, int zoomLevel) {
    MapImpl.impl.setCenter(jsoPeer, center, zoomLevel);
  }

  /**
   * Centers the map to the given latitude and longitude and sets the zoom level
   * and current map type.
   * 
   * The map type must be known to the map. To center the map using a smooth
   * animation see {@link MapWidget#panTo(LatLng)}.
   * 
   * @param center the geographical coordinate about which to center
   * @param zoomLevel the zoom level
   * @param type the viewed map type
   */
  public void setCenter(LatLng center, int zoomLevel, MapType type) {
    MapImpl.impl.setCenter(jsoPeer, center, zoomLevel, type);
  }

  /**
   * Enables or disables continuous zooming on supported browsers. Continuous
   * zooming is disabled by default.
   * 
   * @param enabled <code>true</code> to enable continuous zooming
   */
  public void setContinuousZoom(boolean enabled) {
    if (enabled) {
      MapImpl.impl.enableContinuousZoom(jsoPeer);
    } else {
      MapImpl.impl.disableContinuousZoom(jsoPeer);
    }
  }

  /**
   * Sets the view to the given map type. The type must be known to the map via
   * {@link MapWidget#addMapType(MapType)} or be one of the three default map
   * types.
   * 
   * @param type the desired map type
   */
  public void setCurrentMapType(MapType type) {
    MapImpl.impl.setMapType(jsoPeer, type);
  }

  /**
   * Enables or disables the double click to zoom functionality. Double-click to
   * zoom is disabled by default.
   * 
   * @param enabled <code>true</code> to enable double-click to zoom.
   */
  public void setDoubleClickZoom(boolean enabled) {
    if (enabled) {
      MapImpl.impl.enableDoubleClickZoom(jsoPeer);
    } else {
      MapImpl.impl.disableDoubleClickZoom(jsoPeer);
    }
  }

  /**
   * Sets whether the map is draggable.
   * 
   * @param draggable <code>true</code> if the map is draggable
   */
  public void setDraggable(boolean draggable) {
    if (draggable) {
      MapImpl.impl.enableDragging(jsoPeer);
    } else {
      MapImpl.impl.disableDragging(jsoPeer);
    }
  }

  /**
   * Enables or disables the GoogleBar, an integrated search control, to the
   * map. When enabled, this control takes the place of the default Powered By
   * Google logo. Note that this control is not enabled by default.
   * 
   * @param enabled pass <code>true</code> to enable the Googlebar.
   */
  public void setGoogleBarEnabled(boolean enabled) {
    if (enabled) {
      MapImpl.impl.enableGoogleBar(jsoPeer);
    } else {
      MapImpl.impl.disableGoogleBar(jsoPeer);
    }
  }

  @Override
  public void setHeight(String height) {
    super.setHeight(height);
    checkResize();
  }

  /**
   * Sets whether info window operations on the map are enabled. Info windows
   * are enabled by default.
   * 
   * @param enabled <code>true</code> to enable opening info windows
   */
  public void setInfoWindowEnabled(boolean enabled) {
    if (enabled) {
      MapImpl.impl.enableInfoWindow(jsoPeer);
    } else {
      MapImpl.impl.disableInfoWindow(jsoPeer);
    }
  }

  /**
   * Enables or disables pinching to zoom on an iPhone or iPod touch. Note:
   * pinch to zoom is enabled by default.
   * 
   * @param value pass <code>false</code> to disable pinching to zoom.
   */
  public void setPinchToZoom(boolean value) {
    if (value) {
      MapImpl.impl.enablePinchToZoom(jsoPeer);
    } else {
      MapImpl.impl.disablePinchToZoom(jsoPeer);
    }
  }

  /**
   * Enables zooming using a mouse's scroll wheel. Scroll wheel zoom is disabled
   * by default.
   * 
   * @param enabled <code>true</code> to enable scroll wheel zooming
   */
  public void setScrollWheelZoomEnabled(boolean enabled) {
    if (enabled) {
      MapImpl.impl.enableScrollWheelZoom(jsoPeer);
    } else {
      MapImpl.impl.disableScrollWheelZoom(jsoPeer);
    }
  }

  @Override
  public void setSize(String width, String height) {
    super.setWidth(width);
    super.setHeight(height);
    checkResize();
  }

  /**
   * Adds behaviour and UI elements specified in the options parameter, which
   * can be a modified version of the object returned from getDefaultUI().
   * 
   * @param options the user interface options to set on the map.
   */
  public void setUI(MapUIOptions options) {
    MapImpl.impl.setUI(jsoPeer, options);
  }

  /**
   * Adds the default behaviour and UI elements specified in getDefaultUI() to
   * the Map.
   */
  public void setUIToDefault() {
    MapImpl.impl.setUIToDefault(jsoPeer);
  }

  @Override
  public void setWidth(String width) {
    super.setWidth(width);
    checkResize();
  }

  /**
   * Sets the zoom level to the given new value.
   * 
   * @param level the new zoom level
   */
  public void setZoomLevel(int level) {
    MapImpl.impl.setZoom(jsoPeer, level);
  }

  /**
   * Increments zoom level by one.
   */
  public void zoomIn() {
    MapImpl.impl.zoomIn(jsoPeer);
  }

  /**
   * Decrements zoom level by one.
   */
  public void zoomOut() {
    MapImpl.impl.zoomOut(jsoPeer);
  }

  /**
   * Adopts the widget, but does not modify the DOM.
   * 
   * @param w the widget to adopt
   */
  protected void addVirtual(Widget w) {
    mapContainer.addVirtual(w);
  }

  @Override
  protected void onAttach() {
    super.onAttach();
    checkResizeAndCenter();

    // This nested deferred command works around a problem at maps
    // initialization time where the map does not resize correctly to its
    // container.
    DeferredCommand.addCommand(new Command() {
      public void execute() {
        DeferredCommand.addCommand(new Command() {
          public void execute() {
            checkResizeAndCenter();
          }
        });
      }
    });
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(MapAddMapTypeEvent event) {
    maybeInitMapAddMapTypeHandlers();
    mapAddMapTypeHandlers.trigger(event.getType());
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(MapAddOverlayEvent event) {
    maybeInitMapAddOverlayHandlers();
    mapAddOverlayHandlers.trigger(event.getOverlay());
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */

  void trigger(MapClearOverlaysEvent event) {
    maybeInitMapClearOverlaysHandlers();
    mapClearOverlaysHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(MapClickEvent event) {
    maybeInitMapClickHandlers();
    mapClickHandlers.trigger(event.getOverlay(), event.getLatLng(),
        event.getOverlayLatLng());
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(MapDoubleClickEvent event) {
    maybeInitMapDoubleClickHandlers();
    mapDoubleClickHandlers.trigger(null, event.getLatLng());
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(MapDragEndEvent event) {
    maybeInitMapDragEndHandlers();
    mapDragEndHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(MapDragEvent event) {
    maybeInitMapDragHandlers();
    mapDragHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(MapDragStartEvent event) {
    maybeInitMapDragStartHandlers();
    mapDragStartHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(MapInfoWindowBeforeCloseEvent event) {
    maybeInitInfoWindowBeforeCloseHandlers();
    infoWindowBeforeCloseHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(MapInfoWindowCloseEvent event) {
    maybeInitInfoWindowCloseHandlers();
    infoWindowCloseHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(MapInfoWindowOpenEvent event) {
    maybeInitInfoWindowOpenHandlers();
    infoWindowOpenHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(MapMouseMoveEvent event) {
    maybeInitMapMouseMoveHandlers();
    mapMouseMoveHandlers.trigger(event.getLatLng());
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(MapMouseOutEvent event) {
    maybeInitMapMouseOutHandlers();
    mapMouseOutHandlers.trigger(event.getLatLng());
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(MapMouseOverEvent event) {
    maybeInitMapMouseOverHandlers();
    mapMouseOverHandlers.trigger(event.getLatLng());
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(MapMoveEndEvent event) {
    maybeInitMapMoveEndHandlers();
    mapMoveEndHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(MapMoveEvent event) {
    maybeInitMapMoveHandlers();
    mapMoveHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(MapMoveStartEvent event) {
    maybeInitMapMoveStartHandlers();
    mapMoveStartHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(MapRemoveMapTypeEvent event) {
    maybeInitMapRemoveMapTypeEvent();
    mapRemoveMapTypeHandlers.trigger(event.getType());
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(MapRemoveOverlayEvent event) {
    maybeInitMapRemoveOverlayHandlers();
    mapRemoveOverlayHandlers.trigger(event.getOverlay());
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(MapRightClickEvent event) {
    maybeInitMapRightClickHandlers();
    mapRightClickHandlers.trigger(event.getPoint(), event.getElement(),
        event.getOverlay());
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(MapTypeChangedEvent event) {
    maybeInitMapTypeChangedHandlers();
    mapTypeChangedHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(MapZoomEndEvent event) {
    maybeInitMapZoomEndHandlers();
    mapZoomEndHandlers.trigger(event.getOldZoomLevel(), event.getNewZoomLevel());
  }

  /**
   * Lazy init the HandlerCollection.
   */
  private void maybeInitInfoWindowBeforeCloseHandlers() {
    if (infoWindowBeforeCloseHandlers == null) {
      infoWindowBeforeCloseHandlers = new HandlerCollection<MapInfoWindowBeforeCloseHandler>(
          jsoPeer, MapEvent.INFOWINDOWBEFORECLOSE);
    }
  }

  /**
   * Lazy init the HandlerCollection.
   */
  private void maybeInitInfoWindowCloseHandlers() {
    if (infoWindowCloseHandlers == null) {
      infoWindowCloseHandlers = new HandlerCollection<MapInfoWindowCloseHandler>(
          jsoPeer, MapEvent.INFOWINDOWCLOSE);
    }
  }

  /**
   * Lazy init the HandlerCollection.
   */
  private void maybeInitInfoWindowOpenHandlers() {
    if (infoWindowOpenHandlers == null) {
      infoWindowOpenHandlers = new HandlerCollection<MapInfoWindowOpenHandler>(
          jsoPeer, MapEvent.INFOWINDOWOPEN);
    }
  }

  /**
   * Lazy init the HandlerCollection.
   */
  private void maybeInitMapAddMapTypeHandlers() {
    if (mapAddMapTypeHandlers == null) {
      mapAddMapTypeHandlers = new HandlerCollection<MapAddMapTypeHandler>(
          jsoPeer, MapEvent.ADDMAPTYPE);
    }
  }

  /**
   * Lazy init the HandlerCollection.
   */
  private void maybeInitMapAddOverlayHandlers() {
    if (mapAddOverlayHandlers == null) {
      mapAddOverlayHandlers = new HandlerCollection<MapAddOverlayHandler>(
          jsoPeer, MapEvent.ADDOVERLAY);
    }
  }

  /**
   * Lazy init the HandlerCollection.
   */
  private void maybeInitMapClearOverlaysHandlers() {
    if (mapClearOverlaysHandlers == null) {
      mapClearOverlaysHandlers = new HandlerCollection<MapClearOverlaysHandler>(
          jsoPeer, MapEvent.CLEAROVERLAYS);
    }
  }

  /**
   * Lazy init the HandlerCollection.
   */
  private void maybeInitMapClickHandlers() {
    if (mapClickHandlers == null) {
      mapClickHandlers = new HandlerCollection<MapClickHandler>(jsoPeer,
          MapEvent.CLICK);
    }
  }

  /**
   * Lazy init the HandlerCollection.
   */
  private void maybeInitMapDoubleClickHandlers() {
    if (mapDoubleClickHandlers == null) {
      mapDoubleClickHandlers = new HandlerCollection<MapDoubleClickHandler>(
          jsoPeer, MapEvent.DBLCLICK);
    }
  }

  /**
   * Lazy init the HandlerCollection.
   */
  private void maybeInitMapDragEndHandlers() {
    if (mapDragEndHandlers == null) {
      mapDragEndHandlers = new HandlerCollection<MapDragEndHandler>(jsoPeer,
          MapEvent.DRAGEND);
    }
  }

  /**
   * Lazy init the HandlerCollection.
   */
  private void maybeInitMapDragHandlers() {
    if (mapDragHandlers == null) {
      mapDragHandlers = new HandlerCollection<MapDragHandler>(jsoPeer,
          MapEvent.DRAG);
    }
  }

  /**
   * Lazy init the HandlerCollection.
   */
  private void maybeInitMapDragStartHandlers() {
    if (mapDragStartHandlers == null) {
      mapDragStartHandlers = new HandlerCollection<MapDragStartHandler>(
          jsoPeer, MapEvent.DRAGSTART);
    }
  }

  /**
   * Lazy init the HandlerCollection.
   */
  private void maybeInitMapMouseMoveHandlers() {
    if (mapMouseMoveHandlers == null) {
      mapMouseMoveHandlers = new HandlerCollection<MapMouseMoveHandler>(
          jsoPeer, MapEvent.MOUSEMOVE);
    }
  }

  /**
   * Lazy init the HandlerCollection.
   */
  private void maybeInitMapMouseOutHandlers() {
    if (mapMouseOutHandlers == null) {
      mapMouseOutHandlers = new HandlerCollection<MapMouseOutHandler>(jsoPeer,
          MapEvent.MOUSEOUT);
    }
  }

  /**
   * Lazy init the HandlerCollection.
   */
  private void maybeInitMapMouseOverHandlers() {
    if (mapMouseOverHandlers == null) {
      mapMouseOverHandlers = new HandlerCollection<MapMouseOverHandler>(
          jsoPeer, MapEvent.MOUSEOVER);
    }
  }

  /**
   * Lazy init the HandlerCollection.
   */
  private void maybeInitMapMoveEndHandlers() {
    if (mapMoveEndHandlers == null) {
      mapMoveEndHandlers = new HandlerCollection<MapMoveEndHandler>(jsoPeer,
          MapEvent.MOVEEND);
    }
  }

  /**
   * Lazy init the HandlerCollection.
   */
  private void maybeInitMapMoveHandlers() {
    if (mapMoveHandlers == null) {
      mapMoveHandlers = new HandlerCollection<MapMoveHandler>(jsoPeer,
          MapEvent.MOVE);
    }
  }

  /**
   * Lazy init the HandlerCollection.
   */
  private void maybeInitMapMoveStartHandlers() {
    if (mapMoveStartHandlers == null) {
      mapMoveStartHandlers = new HandlerCollection<MapMoveStartHandler>(
          jsoPeer, MapEvent.MOVESTART);
    }
  }

  /**
   * Lazy init the HandlerCollection.
   */
  private void maybeInitMapRemoveMapTypeEvent() {
    if (mapRemoveMapTypeHandlers == null) {
      mapRemoveMapTypeHandlers = new HandlerCollection<MapRemoveMapTypeHandler>(
          jsoPeer, MapEvent.REMOVEMAPTYPE);
    }
  }

  /**
   * Lazy init the HandlerCollection.
   */
  private void maybeInitMapRemoveOverlayHandlers() {
    if (mapRemoveOverlayHandlers == null) {
      mapRemoveOverlayHandlers = new HandlerCollection<MapRemoveOverlayHandler>(
          jsoPeer, MapEvent.REMOVEOVERLAY);
    }
  }

  /**
   * Lazy init the HandlerCollection.
   */
  private void maybeInitMapRightClickHandlers() {
    if (mapRightClickHandlers == null) {
      mapRightClickHandlers = new HandlerCollection<MapRightClickHandler>(
          jsoPeer, MapEvent.SINGLERIGHTCLICK);
    }
  }

  /**
   * Lazy init the HandlerCollection.
   */
  private void maybeInitMapTypeChangedHandlers() {
    if (mapTypeChangedHandlers == null) {
      mapTypeChangedHandlers = new HandlerCollection<MapTypeChangedHandler>(
          jsoPeer, MapEvent.MAPTYPECHANGED);
    }
  }

  /**
   * Lazy init the HandlerCollection.
   */
  private void maybeInitMapZoomEndHandlers() {
    if (mapZoomEndHandlers == null) {
      mapZoomEndHandlers = new HandlerCollection<MapZoomEndHandler>(jsoPeer,
          MapEvent.ZOOMEND);
    }
  }
}

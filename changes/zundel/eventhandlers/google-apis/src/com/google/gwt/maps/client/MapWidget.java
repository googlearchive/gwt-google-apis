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
import com.google.gwt.maps.client.control.Control;
import com.google.gwt.maps.client.control.ControlAnchor;
import com.google.gwt.maps.client.control.ControlPosition;
import com.google.gwt.maps.client.event.MapInfoWindowBeforeCloseHandler;
import com.google.gwt.maps.client.event.MapInfoWindowCloseHandler;
import com.google.gwt.maps.client.event.InfoWindowListener;
import com.google.gwt.maps.client.event.MapInfoWindowOpenHandler;
import com.google.gwt.maps.client.event.MapAddMapTypeHandler;
import com.google.gwt.maps.client.event.MapAddOverlayHandler;
import com.google.gwt.maps.client.event.MapClearOverlaysHandler;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.event.MapClickListener;
import com.google.gwt.maps.client.event.MapDoubleClickHandler;
import com.google.gwt.maps.client.event.MapDragEndHandler;
import com.google.gwt.maps.client.event.MapDragHandler;
import com.google.gwt.maps.client.event.MapDragListener;
import com.google.gwt.maps.client.event.MapDragStartHandler;
import com.google.gwt.maps.client.event.MapMouseListener;
import com.google.gwt.maps.client.event.MapMouseMoveHandler;
import com.google.gwt.maps.client.event.MapMouseOutHandler;
import com.google.gwt.maps.client.event.MapMouseOverHandler;
import com.google.gwt.maps.client.event.MapMoveEndHandler;
import com.google.gwt.maps.client.event.MapMoveHandler;
import com.google.gwt.maps.client.event.MapMoveListener;
import com.google.gwt.maps.client.event.MapMoveStartHandler;
import com.google.gwt.maps.client.event.MapRemoveMapTypeHandler;
import com.google.gwt.maps.client.event.MapRemoveOverlayHandler;
import com.google.gwt.maps.client.event.MapRightClickHandler;
import com.google.gwt.maps.client.event.MapTypeChangedHandler;
import com.google.gwt.maps.client.event.MapTypeListener;
import com.google.gwt.maps.client.event.MapZoomEndHandler;
import com.google.gwt.maps.client.event.MapZoomListener;
import com.google.gwt.maps.client.event.OverlayListener;
import com.google.gwt.maps.client.event.MapInfoWindowBeforeCloseHandler.MapInfoWindowBeforeCloseEvent;
import com.google.gwt.maps.client.event.MapInfoWindowCloseHandler.MapInfoWindowCloseEvent;
import com.google.gwt.maps.client.event.MapInfoWindowOpenHandler.MapInfoWindowOpenEvent;
import com.google.gwt.maps.client.event.MapAddMapTypeHandler.MapAddMapTypeEvent;
import com.google.gwt.maps.client.event.MapAddOverlayHandler.MapAddOverlayEvent;
import com.google.gwt.maps.client.event.MapClearOverlaysHandler.MapClearOverlaysEvent;
import com.google.gwt.maps.client.event.MapClickHandler.MapClickEvent;
import com.google.gwt.maps.client.event.MapDoubleClickHandler.MapDoubleClickEvent;
import com.google.gwt.maps.client.event.MapDragEndHandler.MapDragEndEvent;
import com.google.gwt.maps.client.event.MapDragHandler.MapDragEvent;
import com.google.gwt.maps.client.event.MapDragStartHandler.MapDragStartEvent;
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
import com.google.gwt.maps.client.impl.EventImpl;
import com.google.gwt.maps.client.impl.HandlerCollection;
import com.google.gwt.maps.client.impl.JsUtil;
import com.google.gwt.maps.client.impl.ListenerCollection;
import com.google.gwt.maps.client.impl.MapEvent;
import com.google.gwt.maps.client.impl.MapImpl;
import com.google.gwt.maps.client.impl.MapOptionsImpl;
import com.google.gwt.maps.client.impl.EventImpl.IntIntCallback;
import com.google.gwt.maps.client.impl.EventImpl.LatLngCallback;
import com.google.gwt.maps.client.impl.EventImpl.MapTypeCallback;
import com.google.gwt.maps.client.impl.EventImpl.OverlayCallback;
import com.google.gwt.maps.client.impl.EventImpl.OverlayLatLngCallback;
import com.google.gwt.maps.client.impl.EventImpl.PointElementOverlayCallback;
import com.google.gwt.maps.client.impl.EventImpl.VoidCallback;
import com.google.gwt.maps.client.overlay.Overlay;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.WindowCloseListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * A widget that presents a viewable Google Map to a user.
 * 
 * Note: the GEvent.trigger methods are implemented by the API but intended for
 * internal testing only.
 */
public final class MapWidget extends Composite {

  private static class MapPanel extends FlowPanel {
    public void addVirtual(Widget w) {
      w.removeFromParent();
      getChildren().add(w);
      adopt(w);
    }
  }

  private static final LatLng DEFAULT_CENTER = new LatLng(33.781466, -84.387519);

  static {
    Window.addWindowCloseListener(new WindowCloseListener() {
      public void onWindowClosed() {
        nativeUnload();
      }

      public String onWindowClosing() {
        return null;
      }
    });
  }

  private static native void nativeUnload() /*-{
    $wnd.GUnload && $wnd.GUnload();
   }-*/;

  static MapWidget createPeer(JavaScriptObject jsoPeer) {
    throw new UnsupportedOperationException();
  }

  private HandlerCollection<MapClickHandler> mapClickHandlers;
  private ListenerCollection<MapClickListener> clickListeners;
  private HandlerCollection<MapDoubleClickHandler> mapDoubleClickHandlers;
  private ListenerCollection<MapDragListener> dragListeners;
  private HandlerCollection<MapInfoWindowBeforeCloseHandler> infoWindowBeforeCloseHandlers;
  private HandlerCollection<MapInfoWindowCloseHandler> infoWindowCloseHandlers;
  private ListenerCollection<InfoWindowListener> infoWindowListeners;
  private HandlerCollection<MapInfoWindowOpenHandler> infoWindowOpenHandlers;

  /* Reference to GMap2 object. */
  private final JavaScriptObject jsoPeer;

  private HandlerCollection<MapAddMapTypeHandler> mapAddMapTypeHandlers;
  private HandlerCollection<MapAddOverlayHandler> mapAddOverlayHandlers;
  private HandlerCollection<MapClearOverlaysHandler> mapClearOverlaysHandlers;
  private final MapPanel mapContainer = new MapPanel();
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
  private HandlerCollection<MapTypeChangedHandler> mapTypeChangedHandlers;
  private ListenerCollection<MapTypeListener> mapTypeListeners;
  private HandlerCollection<MapZoomEndHandler> mapZoomEndHandlers;
  private ListenerCollection<MapMouseListener> mouseListeners;
  private ListenerCollection<MapMoveListener> moveListeners;
  private ListenerCollection<OverlayListener> overlayListeners;
  private HandlerCollection<MapRightClickHandler> mapRightClickHandlers;
  private ListenerCollection<MapZoomListener> zoomListeners;

  public MapWidget() {
    this(DEFAULT_CENTER, 8, null, null);
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
   * and zoom level. Also, sets the dragging and draggable cursor values. See
   * the W3C CSS spec for allowable cursor string values.
   * 
   * Note: The 'load' event requires that a handler be registered before
   * GMap2.setCenter() is called. Since that method is always called in this
   * constructor, it isn't clear that gwt-google-apis users needs this
   * event.
   *
   * @param center the geographical point about which to center
   * @param zoomLevel zoomLevel the zoom level
   * @param draggableCursor CSS name of the cursor to display when the map is
   *          draggable
   * @param draggingCursor CSS name of the cursor to display when the map is
   *          being dragged
   */
  public MapWidget(LatLng center, int zoomLevel, String draggableCursor,
      String draggingCursor) {
    initWidget(mapContainer);
    JavaScriptObject opts = MapOptionsImpl.impl.construct();
    MapOptionsImpl.impl.setDraggableCursor(opts, draggableCursor);
    MapOptionsImpl.impl.setDraggingCursor(opts, draggingCursor);
    jsoPeer = MapImpl.impl.construct(getElement(), opts);
    MapImpl.impl.bind(jsoPeer, this);
    setCenter(center, zoomLevel);
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
   * control to the map, subclass {@link Control.CustomControl} and implement
   * the initialize(MapWidget) method.
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
    if (infoWindowBeforeCloseHandlers == null) {
      infoWindowBeforeCloseHandlers = new HandlerCollection<MapInfoWindowBeforeCloseHandler>(
          jsoPeer, MapEvent.INFOWINDOWBEFORECLOSE);
    }

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
   * call to openInfoWindow*(), the handler {@link MapInfoWindowBeforeCloseHandler},
   * {@link MapInfoWindowCloseHandler} and {@link MapInfoWindowOpenHandler} are fired
   * in this order
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addInfoWindowCloseHandler(final MapInfoWindowCloseHandler handler) {
    if (infoWindowCloseHandlers == null) {
      infoWindowCloseHandlers = new HandlerCollection<MapInfoWindowCloseHandler>(
          jsoPeer, MapEvent.INFOWINDOWCLOSE);
    }

    infoWindowCloseHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MapInfoWindowCloseEvent e = new MapInfoWindowCloseEvent(MapWidget.this);
        handler.onInfoWindowClose(e);
      }
    });
  }

  /**
   * Add a listener for InfoWindow events.
   * 
   * @param listener listener to invoke on InfoWindow events
   * 
   * @deprecated see
   *             {@link MapWidget#addInfoWindowBeforeCloseHandler(MapInfoWindowBeforeCloseHandler)},
   *             {@link MapWidget#addInfoWindowCloseHandler(MapInfoWindowCloseHandler)},
   *             and
   *             {@link MapWidget#addInfoWindowOpenHandler(MapInfoWindowOpenHandler)}
   */
  @Deprecated
  public void addInfoWindowListener(final InfoWindowListener listener) {
    if (infoWindowListeners == null) {
      infoWindowListeners = new ListenerCollection<InfoWindowListener>();
    }
    JavaScriptObject infoWindowEventHandles[] = {
        EventImpl.impl.addListenerVoid(jsoPeer, MapEvent.INFOWINDOWOPEN,
            new VoidCallback() {
              @Override
              public void callback() {
                listener.onInfoWindowOpened(MapWidget.this);
              }
            }),
        EventImpl.impl.addListenerVoid(jsoPeer, MapEvent.INFOWINDOWCLOSE,
            new VoidCallback() {
              @Override
              public void callback() {
                listener.onInfoWindowClosed(MapWidget.this);
              }
            })};
    infoWindowListeners.addListener(listener, infoWindowEventHandles);
  }

  /**
   * This event is fired when the info window opens.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addInfoWindowOpenHandler(final MapInfoWindowOpenHandler handler) {
    if (infoWindowOpenHandlers == null) {
      infoWindowOpenHandlers = new HandlerCollection<MapInfoWindowOpenHandler>(
          jsoPeer, MapEvent.INFOWINDOWOPEN);
    }

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
    if (mapAddMapTypeHandlers == null) {
      mapAddMapTypeHandlers = new HandlerCollection<MapAddMapTypeHandler>(
          jsoPeer, MapEvent.ADDMAPTYPE);
    }

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
    if (mapAddOverlayHandlers == null) {
      mapAddOverlayHandlers = new HandlerCollection<MapAddOverlayHandler>(
          jsoPeer, MapEvent.ADDOVERLAY);
    }

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
    if (mapClearOverlaysHandlers == null) {
      mapClearOverlaysHandlers = new HandlerCollection<MapClearOverlaysHandler>(
          jsoPeer, MapEvent.CLEAROVERLAYS);
    }

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
    if (mapClickHandlers == null) {
      mapClickHandlers = new HandlerCollection<MapClickHandler>(jsoPeer,
          MapEvent.CLICK);
    }

    mapClickHandlers.addHandler(handler, new OverlayLatLngCallback() {
      @Override
      public void callback(Overlay overlay, LatLng latlng) {
        MapClickEvent e = new MapClickEvent(MapWidget.this, overlay, latlng);
        handler.onClick(e);
      }
    });
  }

  /**
   * Add a click listener for mouse click events.
   * 
   * @param listener listener to invoke on mouse click events.
   * 
   * @deprecated replace with
   *             {@link MapWidget#addMapClickHandler(MapClickHandler)},
   *             {@link MapWidget#addMapDoubleClickHandler(MapDoubleClickHandler)},
   *             and
   *             {@link MapWidget#addMapRightClickHandler(MapRightClickHandler)}
   */
  @Deprecated
  public void addMapClickListener(final MapClickListener listener) {
    if (clickListeners == null) {
      clickListeners = new ListenerCollection<MapClickListener>();
    }
    JavaScriptObject clickEventHandles[] = {
        EventImpl.impl.addListener(jsoPeer, MapEvent.CLICK,
            new OverlayLatLngCallback() {
              @Override
              public void callback(Overlay overlay, LatLng latlng) {
                listener.onClick(MapWidget.this, overlay, latlng);
              }
            }),
        EventImpl.impl.addListener(jsoPeer, MapEvent.DBLCLICK,
            new OverlayLatLngCallback() {
              @Override
              public void callback(Overlay overlay, LatLng latlng) {
                listener.onDoubleClick(MapWidget.this, overlay, latlng);
              }
            }),
        EventImpl.impl.addListener(jsoPeer, MapEvent.SINGLERIGHTCLICK,
            new PointElementOverlayCallback() {
              @Override
              public void callback(Point point, Element element, Overlay overlay) {
                listener.onRightClick(MapWidget.this, point, element, overlay);
              }
            })};
    clickListeners.addListener(listener, clickEventHandles);
  }

  /**
   * Add a double click handler for mouse double click events. Note that this
   * event will not be invoked if the double click was on a marker object.
   * 
   * @param handler handler to invoke on mouse double click events.
   */
  public void addMapDoubleClickHandler(final MapDoubleClickHandler handler) {
    if (mapDoubleClickHandlers == null) {
      mapDoubleClickHandlers = new HandlerCollection<MapDoubleClickHandler>(
          jsoPeer, MapEvent.DBLCLICK);
    }

    mapDoubleClickHandlers.addHandler(handler, new OverlayLatLngCallback() {
      @Override
      public void callback(Overlay overlay, LatLng latlng) {
        // the overlay parameter is always NULL according to the Maps API.
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
    if (mapDragEndHandlers == null) {
      mapDragEndHandlers = new HandlerCollection<MapDragEndHandler>(jsoPeer,
          MapEvent.DRAGEND);
    }

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
    if (mapDragHandlers == null) {
      mapDragHandlers = new HandlerCollection<MapDragHandler>(jsoPeer,
          MapEvent.DRAG);
    }

    mapDragHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MapDragEvent e = new MapDragEvent(MapWidget.this);
        handler.onDrag(e);
      }
    });
  }

  /**
   * Add a drag listener for mouse drag events.
   * 
   * @param listener listener to invoke on mouse drag events.
   * 
   * @deprecated see
   *             {@link MapWidget#addMapDragStartHandler(MapDragStartHandler)},
   *             {@link MapWidget#addMapDragEndHandler(MapDragEndHandler)}, and
   *             {@link MapWidget#addMapDragHandler(MapDragHandler)}
   */
  @Deprecated
  public void addMapDragListener(final MapDragListener listener) {
    if (dragListeners == null) {
      dragListeners = new ListenerCollection<MapDragListener>();
    }
    JavaScriptObject dragEventHandles[] = {
        EventImpl.impl.addListenerVoid(jsoPeer, MapEvent.DRAGSTART,
            new VoidCallback() {
              @Override
              public void callback() {
                listener.onDragStart(MapWidget.this);
              }
            }),
        EventImpl.impl.addListenerVoid(jsoPeer, MapEvent.DRAG,
            new VoidCallback() {
              @Override
              public void callback() {
                listener.onDrag(MapWidget.this);
              }
            }),
        EventImpl.impl.addListenerVoid(jsoPeer, MapEvent.DRAGEND,
            new VoidCallback() {
              @Override
              public void callback() {
                listener.onDragEnd(MapWidget.this);
              }
            })};
    dragListeners.addListener(listener, dragEventHandles);
  }

  /**
   * This event is fired when the user starts dragging the map.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMapDragStartHandler(final MapDragStartHandler handler) {
    if (mapDragStartHandlers == null) {
      mapDragStartHandlers = new HandlerCollection<MapDragStartHandler>(
          jsoPeer, MapEvent.DRAGSTART);
    }

    mapDragStartHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MapDragStartEvent e = new MapDragStartEvent(MapWidget.this);
        handler.onDragStart(e);
      }
    });
  }

  /**
   * Add a listener for mouse move events.
   * 
   * @param listener listener to invoke on mouse move events.
   * 
   * @deprecated see
   *             {@link MapWidget#addMapMouseMoveHandler(MapMouseMoveHandler)},
   *             {@link MapWidget#addMapMouseOutHandler(MapMouseOutHandler)},
   *             and
   *             {@link MapWidget#addMapMouseOverHandler(MapMouseOverHandler)}
   */
  @Deprecated
  public void addMapMouseListener(final MapMouseListener listener) {
    if (mouseListeners == null) {
      mouseListeners = new ListenerCollection<MapMouseListener>();
    }
    JavaScriptObject mouseEventHandles[] = {
        EventImpl.impl.addListener(jsoPeer, MapEvent.MOUSEOVER,
            new LatLngCallback() {
              @Override
              public void callback(LatLng latlng) {
                listener.onMouseOver(MapWidget.this, latlng);
              }
            }),
        EventImpl.impl.addListener(jsoPeer, MapEvent.MOUSEOUT,
            new LatLngCallback() {
              @Override
              public void callback(LatLng latlng) {
                listener.onMouseOut(MapWidget.this, latlng);
              }
            }),
        EventImpl.impl.addListener(jsoPeer, MapEvent.MOUSEMOVE,
            new LatLngCallback() {
              @Override
              public void callback(LatLng latlng) {
                listener.onMouseMove(MapWidget.this, latlng);
              }
            })};
    mouseListeners.addListener(listener, mouseEventHandles);
  }

  /**
   * This event is fired when the user moves the mouse over the map from outside
   * the map.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMapMouseMoveHandler(final MapMouseMoveHandler handler) {
    if (mapMouseOutHandlers == null) {
      mapMouseMoveHandlers = new HandlerCollection<MapMouseMoveHandler>(
          jsoPeer, MapEvent.MOUSEMOVE);
    }

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
    if (mapMouseOutHandlers == null) {
      mapMouseOutHandlers = new HandlerCollection<MapMouseOutHandler>(jsoPeer,
          MapEvent.MOUSEOUT);
    }

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
    if (mapMouseOverHandlers == null) {
      mapMouseOverHandlers = new HandlerCollection<MapMouseOverHandler>(
          jsoPeer, MapEvent.MOUSEOVER);
    }

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
    if (mapMoveEndHandlers == null) {
      mapMoveEndHandlers = new HandlerCollection<MapMoveEndHandler>(jsoPeer,
          MapEvent.MOVEEND);
    }

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
    if (mapMoveHandlers == null) {
      mapMoveHandlers = new HandlerCollection<MapMoveHandler>(jsoPeer,
          MapEvent.MOVE);
    }

    mapMoveHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MapMoveEvent e = new MapMoveEvent(MapWidget.this);
        handler.onMove(e);
      }
    });
  }

  /**
   * Add a listener for map move events.
   * 
   * @param listener listener to invoke on map move events.
   * 
   * @deprecated see {@link MapWidget#addMapMoveHandler(MapMoveHandler)},
   *             {@link MapWidget#addMapMoveEndHandler(MapMoveEndHandler)}, and
   *             {@link MapWidget#addMapMoveStartHandler(MapMoveStartHandler)}
   */
  @Deprecated
  public void addMapMoveListener(final MapMoveListener listener) {
    if (moveListeners == null) {
      moveListeners = new ListenerCollection<MapMoveListener>();
    }
    JavaScriptObject moveEventHandles[] = {
        EventImpl.impl.addListenerVoid(jsoPeer, MapEvent.MOVE,
            new VoidCallback() {
              @Override
              public void callback() {
                listener.onMove(MapWidget.this);
              }
            }),
        EventImpl.impl.addListenerVoid(jsoPeer, MapEvent.MOVESTART,
            new VoidCallback() {
              @Override
              public void callback() {
                listener.onMoveStart(MapWidget.this);
              }
            }),
        EventImpl.impl.addListenerVoid(jsoPeer, MapEvent.MOVEEND,
            new VoidCallback() {
              @Override
              public void callback() {
                listener.onMoveEnd(MapWidget.this);
              }
            })};
    moveListeners.addListener(listener, moveEventHandles);
  }

  /**
   * This event is fired when the map view starts changing. This can be caused
   * by dragging, in which case a {@link MapDragStartEvent} is also fired, or by
   * invocation of a method that changes the map view.
   * 
   * @param handler handler to invoke on map move events.
   */
  public void addMapMoveStartHandler(final MapMoveStartHandler handler) {
    if (mapMoveStartHandlers == null) {
      mapMoveStartHandlers = new HandlerCollection<MapMoveStartHandler>(
          jsoPeer, MapEvent.MOVESTART);
    }

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
    if (mapRemoveMapTypeHandlers == null) {
      mapRemoveMapTypeHandlers = new HandlerCollection<MapRemoveMapTypeHandler>(
          jsoPeer, MapEvent.REMOVEMAPTYPE);
    }

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
    if (mapRemoveOverlayHandlers == null) {
      mapRemoveOverlayHandlers = new HandlerCollection<MapRemoveOverlayHandler>(
          jsoPeer, MapEvent.REMOVEOVERLAY);
    }

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
    if (mapRightClickHandlers == null) {
      mapRightClickHandlers = new HandlerCollection<MapRightClickHandler>(
          jsoPeer, MapEvent.SINGLERIGHTCLICK);
    }

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
    if (mapTypeChangedHandlers == null) {
      mapTypeChangedHandlers = new HandlerCollection<MapTypeChangedHandler>(
          jsoPeer, MapEvent.MAPTYPECHANGED);
    }

    mapTypeChangedHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MapTypeChangedEvent e = new MapTypeChangedEvent(MapWidget.this);
        handler.onTypeChanged(e);
      }
    });
  }

  /**
   * 
   * @param listener
   * 
   * @deprecated see
   *             {@link MapWidget#addMapTypeChangedHandler(MapTypeChangedHandler)},
   *             {@link MapWidget#addMapAddMapTypeHandler(MapAddMapTypeHandler)},
   *             {@link MapWidget#addMapRemoveMapTypeHandler(MapRemoveMapTypeHandler)}
   */
  @Deprecated
  public void addMapTypeListener(final MapTypeListener listener) {
    if (mapTypeListeners == null) {
      mapTypeListeners = new ListenerCollection<MapTypeListener>();
    }

    JavaScriptObject mapTypeEventHandles[] = {
        EventImpl.impl.addListener(jsoPeer, MapEvent.ADDMAPTYPE,
            new MapTypeCallback() {
              @Override
              public void callback(MapType mapType) {
                listener.onMapTypeAdded(MapWidget.this, mapType);
              }
            }),
        EventImpl.impl.addListener(jsoPeer, MapEvent.REMOVEMAPTYPE,
            new MapTypeCallback() {
              @Override
              public void callback(MapType mapType) {
                listener.onMapTypeRemoved(MapWidget.this, mapType);
              }
            }),
        EventImpl.impl.addListenerVoid(jsoPeer, MapEvent.MAPTYPECHANGED,
            new VoidCallback() {
              @Override
              public void callback() {
                listener.onMapTypeChanged(MapWidget.this);
              }
            })};
    mapTypeListeners.addListener(listener, mapTypeEventHandles);
  }

  /**
   * This event is fired when a map type is added to the map.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMapZoomEndHandler(final MapZoomEndHandler handler) {
    if (mapZoomEndHandlers == null) {
      mapZoomEndHandlers = new HandlerCollection<MapZoomEndHandler>(jsoPeer,
          MapEvent.ZOOMEND);
    }

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
   * Add a listener for changes in the zoom level of the map.
   * 
   * @param listener a listener to call back when the zoom level of the map
   *          changes.
   * 
   * @deprecated see {@link MapWidget#addMapZoomEndHandler(MapZoomEndHandler)}
   */
  @Deprecated
  public void addMapZoomListener(final MapZoomListener listener) {
    if (zoomListeners == null) {
      zoomListeners = new ListenerCollection<MapZoomListener>();
    }
    JavaScriptObject zoomEventHandles[] = {EventImpl.impl.addListener(jsoPeer,
        MapEvent.ZOOMEND, new IntIntCallback() {
          @Override
          public void callback(int oldLevel, int newLevel) {
            listener.onZoom(MapWidget.this, oldLevel, newLevel);
          }
        })};
    zoomListeners.addListener(listener, zoomEventHandles);
  }

  /**
   * Adds an overlay to the map and fires any
   * {@link OverlayListener#onOverlayAdded(MapWidget, Overlay)} listeners.
   * 
   * @param overlay
   */
  public void addOverlay(Overlay overlay) {
    MapImpl.impl.addOverlay(jsoPeer, overlay);
  }

  /**
   * Add a listener for overlays being added and removed from the map.
   * 
   * @param listener a listener to call back.
   * 
   * @deprecated see
   *             {@link MapWidget#addMapAddOverlayHandler(MapAddOverlayHandler)}
   *             and
   *             {@link MapWidget#addMapRemoveOverlayHandler(MapRemoveOverlayHandler)}
   */
  public void addOverlayListener(final OverlayListener listener) {
    if (overlayListeners == null) {
      overlayListeners = new ListenerCollection<OverlayListener>();
    }
    JavaScriptObject overlayEventHandles[] = {
        EventImpl.impl.addListener(jsoPeer, MapEvent.ADDOVERLAY,
            new OverlayCallback() {
              @Override
              public void callback(Overlay overlay) {
                listener.onOverlayAdded(MapWidget.this, overlay);
              }
            }),
        EventImpl.impl.addListener(jsoPeer, MapEvent.REMOVEOVERLAY,
            new OverlayCallback() {
              @Override
              public void callback(Overlay overlay) {
                listener.onOverlayRemoved(MapWidget.this, overlay);
              }
            }),
        EventImpl.impl.addListenerVoid(jsoPeer, MapEvent.CLEAROVERLAYS,
            new VoidCallback() {
              @Override
              public void callback() {
                listener.onOverlaysCleared(MapWidget.this);
              }
            })};
    overlayListeners.addListener(listener, overlayEventHandles);
  }

  /**
   * Notifies the map of a change of the size of its container. Call this method
   * after the size of the container DOM object has changed, so that the map can
   * adjust itself to fit the new size.
   */
  public void checkResize() {
    LatLng center = getCenter();
    MapImpl.impl.checkResize(jsoPeer);
    setCenter(center);
  }

  /**
   * Removes all listeners of this map added with
   * {@link MapWidget#addMapClickListener(MapClickListener)}.
   * 
   * @deprecated
   */
  @Deprecated
  public void clearMapClickListeners() {
    if (clickListeners != null) {
      clickListeners.clearListeners();
    }
  }

  /**
   * Removes all listeners of this map added with
   * {@link MapWidget#addMapDragListener(MapDragListener)}.
   * 
   * @deprecated
   */
  @Deprecated
  public void clearMapDragListeners() {
    if (dragListeners != null) {
      dragListeners.clearListeners();
    }
  }

  /**
   * Removes all listeners of this map added with
   * {@link MapWidget#addMapMouseListener(MapMouseListener)}.
   * 
   * @deprecated
   */
  @Deprecated
  public void clearMapMouseListeners() {
    if (mouseListeners != null) {
      mouseListeners.clearListeners();
    }
  }

  /**
   * Removes all listeners of this map added with
   * {@link MapWidget#addMapMoveListener(MapMoveListener)}.
   * 
   * @deprecated
   */
  @Deprecated
  public void clearMapMoveListeners() {
    if (moveListeners != null) {
      moveListeners.clearListeners();
    }
  }

  /**
   * Removes all listeners of this map added with
   * {@link MapWidget#addMapTypeListener(MapTypeListener)}.
   * 
   * @deprecated
   */
  @Deprecated
  public void clearMapTypeListeners() {
    if (mapTypeListeners != null) {
      mapTypeListeners.clearListeners();
    }
  }

  /**
   * Removes all listeners of this map added with
   * {@link MapWidget#addMapZoomListener(MapZoomListener)}.
   * 
   * @deprecated
   */
  @Deprecated
  public void clearMapZoomListeners() {
    if (zoomListeners != null) {
      zoomListeners.clearListeners();
    }
  }

  /**
   * Removes all listeners of this map added with
   * {@link MapWidget#addOverlayListener(OverlayListener)}.
   * 
   * @deprecated
   */
  @Deprecated
  public void clearOverlayListeners() {
    if (overlayListeners != null) {
      overlayListeners.clearListeners();
    }
  }

  /**
   * Removes all overlay from the map, and fires the
   * {@link OverlayListener#onOverlaysCleared(MapWidget)} listener.
   */
  public void clearOverlays() {
    MapImpl.impl.clearOverlays(jsoPeer);
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
   * that holds the draggable map. You need this when you implement interaction
   * with custom overlays.
   * 
   * @param pixel point on the map to convert to Lat/Lng
   * @return a set of converted coordinates
   */
  public LatLng convertDivPixelToLatLng(Point pixel) {
    return MapImpl.impl.fromDivPixelToLatLng(jsoPeer, pixel);
  }

  /**
   * Converts from geographical coordinates to the pixel coordinates used by the
   * current projection.
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
   * Gets the info window associated with the map.
   * 
   * There is only one info window per map.
   * 
   * TODO(samgross): assign the info window to an instance field so that there
   * is only one instance per map.
   * 
   * @return the info window associated with the map.
   */
  public InfoWindow getInfoWindow() {
    return new InfoWindow(this);
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
    return MapPane.getPane(this, type);
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
   * Returns true if continuous smooth zooming is enabled.
   * 
   * @return true if continuous smooth zooming is enabled
   */
  public boolean isContinuousZoomEnabled() {
    return MapImpl.impl.continuousZoomEnabled(jsoPeer);
  }

  /**
   * Returns true if double-click to zoom is enabled.
   * 
   * @return true if double-click to zoom is enabled
   */
  public boolean isDoubleClickZoomEnabled() {
    return MapImpl.impl.doubleClickZoomEnabled(jsoPeer);
  }

  /**
   * Returns true if dragging of the map is enabled.
   * 
   * @return true if dragging of the map is enabled
   */
  public boolean isDraggable() {
    return MapImpl.impl.draggingEnabled(jsoPeer);
  }

  /**
   * Returns true if opening info windows is enabled.
   * 
   * @return true if opening info windows is enabled
   */
  public boolean isInfoWindowEnabled() {
    return MapImpl.impl.infoWindowEnabled(jsoPeer);
  }

  /**
   * Returns true if zooming using a mouse's scroll wheel is enabled.
   * 
   * @return true if zooming using a mouse's scroll wheel is enabled
   */
  public boolean isScrollWheelZoomEnabled() {
    return MapImpl.impl.scrollWheelZoomEnabled(jsoPeer);
  }

  /**
   * Starts a pan animation by the given distance in pixels. +1 is left and up,
   * -1 is right and down.
   * 
   * @param dx the number of pixels to pan left
   * @param dy the number of pixels to pan up
   */
  public void panBy(int dx, int dy) {
    MapImpl.impl.panBy(jsoPeer, new Size(dx, dy));
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
   * {@link MapWidget#addInfoWindowBeforeCloseHandler(MapInfoWindowBeforeCloseHandler)}.
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
   * Removes a single listener of this map previously added with
   * {@link MapWidget#addInfoWindowListener(InfoWindowListener)}.
   * 
   * @param listener the listener to remove
   * 
   * @deprecated
   */
  @Deprecated
  public void removeInfoWindowListener(InfoWindowListener listener) {
    if (infoWindowListeners != null) {
      infoWindowListeners.removeListener(listener);
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
   * Removes a single listener of this map previously added with
   * {@link MapWidget#addMapClickListener(MapClickListener)}.
   * 
   * @param listener the listener to remove
   * 
   * @deprecated
   */
  @Deprecated
  public void removeMapClickListener(MapClickListener listener) {
    if (clickListeners != null) {
      clickListeners.removeListener(listener);
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
   * Removes the given drag listener from the map previously added with
   * {@link MapWidget#addMapDragListener(MapDragListener)}.
   * 
   * @param listener the drag listener to remove
   * 
   * @deprecated
   */
  @Deprecated
  public void removeMapDragListener(MapDragListener listener) {
    if (dragListeners != null) {
      dragListeners.removeListener(listener);
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
   * Removes a single listener of this map previously added with
   * {@link MapWidget#addMapMouseListener(MapMouseListener)}.
   * 
   * 
   * @param listener the listener to remove
   * 
   * @deprecated
   */
  @Deprecated
  public void removeMapMouseListener(MapMouseListener listener) {
    if (mouseListeners != null) {
      mouseListeners.removeListener(listener);
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
   * Removes a single listener of this map previously added with
   * {@link MapWidget#addMapMoveListener(MapMoveListener)}.
   * 
   * 
   * @param listener the listener to remove
   * 
   * @deprecated
   */
  @Deprecated
  public void removeMapMoveListener(MapMoveListener listener) {
    if (moveListeners != null) {
      moveListeners.removeListener(listener);
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
   * and will fire any
   * {@link MapTypeListener#onMapTypeRemoved(MapWidget, MapType)} listeners.
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
   * Removes a single listener of this map previously added with
   * {@link MapWidget#addMapTypeListener(MapTypeListener)}.
   * 
   * @param listener the listener to remove
   * 
   * @deprecated
   */
  @Deprecated
  public void removeMapTypeListener(MapTypeListener listener) {
    if (mapTypeListeners != null) {
      mapTypeListeners.removeListener(listener);
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
   * Removes a single listener of this map previously added with
   * {@link MapWidget#addMapZoomListener(MapZoomListener)}.
   * 
   * @param listener the listener to remove
   * 
   * @deprecated
   */
  @Deprecated
  public void removeMapZoomListener(MapZoomListener listener) {
    if (zoomListeners != null) {
      zoomListeners.removeListener(listener);
    }
  }

  /**
   * Removes the overlay from the map. If the overlay was on the map, it fires
   * any {@link OverlayListener#onOverlayRemoved(MapWidget, Overlay)} listeners.
   * 
   * @param overlay the overlay to remove from the map
   */
  public void removeOverlay(Overlay overlay) {
    MapImpl.impl.removeOverlay(jsoPeer, overlay);
  }

  /**
   * /** Removes a single listener of this map previously added with
   * {@link MapWidget#addOverlayListener(OverlayListener)}.
   * 
   * @deprecated
   */
  @Deprecated
  public void removeOverlayListener(OverlayListener listener) {
    if (overlayListeners != null) {
      overlayListeners.removeListener(listener);
    }
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
   * @param enabled true to enable continuous zooming
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
   * @param enabled true to enable double-click to zoom.
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
   * @param draggable true if the map is draggable
   */
  public void setDraggable(boolean draggable) {
    if (draggable) {
      MapImpl.impl.enableDragging(jsoPeer);
    } else {
      MapImpl.impl.disableDragging(jsoPeer);
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
   * @param enabled true to enable opening info windows
   */
  public void setInfoWindowEnabled(boolean enabled) {
    if (enabled) {
      MapImpl.impl.enableInfoWindow(jsoPeer);
    } else {
      MapImpl.impl.disableInfoWindow(jsoPeer);
    }
  }

  /**
   * Enables zooming using a mouse's scroll wheel. Scroll wheel zoom is disabled
   * by default.
   * 
   * @param enabled true to enable scroll wheel zooming
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
    checkResize();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(MapInfoWindowBeforeCloseEvent event) {
    if (infoWindowBeforeCloseHandlers == null) {
      infoWindowBeforeCloseHandlers = new HandlerCollection<MapInfoWindowBeforeCloseHandler>(
          jsoPeer, MapEvent.INFOWINDOWBEFORECLOSE);
    }

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
    if (infoWindowCloseHandlers == null) {
      infoWindowCloseHandlers = new HandlerCollection<MapInfoWindowCloseHandler>(
          jsoPeer, MapEvent.INFOWINDOWCLOSE);
    }
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
    if (infoWindowOpenHandlers == null) {
      infoWindowOpenHandlers = new HandlerCollection<MapInfoWindowOpenHandler>(
          jsoPeer, MapEvent.INFOWINDOWOPEN);
    }
    infoWindowOpenHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(MapAddMapTypeEvent event) {
    if (mapAddMapTypeHandlers == null) {
      mapAddMapTypeHandlers = new HandlerCollection<MapAddMapTypeHandler>(
          jsoPeer, MapEvent.ADDMAPTYPE);
    }
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
    if (mapAddOverlayHandlers == null) {
      mapAddOverlayHandlers = new HandlerCollection<MapAddOverlayHandler>(
          jsoPeer, MapEvent.ADDOVERLAY);
    }
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
    if (mapClearOverlaysHandlers == null) {
      mapClearOverlaysHandlers = new HandlerCollection<MapClearOverlaysHandler>(
          jsoPeer, MapEvent.CLEAROVERLAYS);
    }
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
    if (mapClickHandlers == null) {
      mapClickHandlers = new HandlerCollection<MapClickHandler>(jsoPeer,
          MapEvent.CLICK);
    }
    mapClickHandlers.trigger(event.getOverlay(), event.getLatLng());
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(MapDoubleClickEvent event) {
    if (mapDoubleClickHandlers == null) {
      mapDoubleClickHandlers = new HandlerCollection<MapDoubleClickHandler>(
          jsoPeer, MapEvent.DBLCLICK);
    }
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
    if (mapDragEndHandlers == null) {
      mapDragEndHandlers = new HandlerCollection<MapDragEndHandler>(jsoPeer,
          MapEvent.DRAGEND);
    }
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
    if (mapDragHandlers == null) {
      mapDragHandlers = new HandlerCollection<MapDragHandler>(jsoPeer,
          MapEvent.DRAG);
    }
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
    if (mapDragStartHandlers == null) {
      mapDragStartHandlers = new HandlerCollection<MapDragStartHandler>(
          jsoPeer, MapEvent.DRAGSTART);
    }
    mapDragStartHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(MapMouseMoveEvent event) {
    if (mapMouseMoveHandlers == null) {
      mapMouseMoveHandlers = new HandlerCollection<MapMouseMoveHandler>(
          jsoPeer, MapEvent.MOUSEMOVE);
    }
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
    if (mapMouseOutHandlers == null) {
      mapMouseOutHandlers = new HandlerCollection<MapMouseOutHandler>(jsoPeer,
          MapEvent.MOUSEOUT);
    }
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
    if (mapMouseOverHandlers == null) {
      mapMouseOverHandlers = new HandlerCollection<MapMouseOverHandler>(
          jsoPeer, MapEvent.MOUSEOVER);
    }
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
    if (mapMoveEndHandlers == null) {
      mapMoveEndHandlers = new HandlerCollection<MapMoveEndHandler>(jsoPeer,
          MapEvent.MOVEEND);
    }
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
    if (mapMoveHandlers == null) {
      mapMoveHandlers = new HandlerCollection<MapMoveHandler>(jsoPeer,
          MapEvent.MOVE);
    }
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
    if (mapMoveStartHandlers == null) {
      mapMoveStartHandlers = new HandlerCollection<MapMoveStartHandler>(
          jsoPeer, MapEvent.MOVESTART);
    }
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
    if (mapRemoveMapTypeHandlers == null) {
      mapRemoveMapTypeHandlers = new HandlerCollection<MapRemoveMapTypeHandler>(
          jsoPeer, MapEvent.REMOVEMAPTYPE);
    }
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
    if (mapRemoveOverlayHandlers == null) {
      mapRemoveOverlayHandlers = new HandlerCollection<MapRemoveOverlayHandler>(
          jsoPeer, MapEvent.REMOVEOVERLAY);
    }
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
    if (mapRightClickHandlers == null) {
      mapRightClickHandlers = new HandlerCollection<MapRightClickHandler>(
          jsoPeer, MapEvent.SINGLERIGHTCLICK);
    }
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
    if (mapTypeChangedHandlers == null) {
      mapTypeChangedHandlers = new HandlerCollection<MapTypeChangedHandler>(
          jsoPeer, MapEvent.MAPTYPECHANGED);
    }
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
    if (mapZoomEndHandlers == null) {
      mapZoomEndHandlers = new HandlerCollection<MapZoomEndHandler>(jsoPeer,
          MapEvent.ZOOMEND);
    }
    mapZoomEndHandlers.trigger(event.getOldZoomLevel(), event.getNewZoomLevel());
  }
}

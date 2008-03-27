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
import com.google.gwt.maps.client.event.InfoWindowBeforeCloseHandler;
import com.google.gwt.maps.client.event.InfoWindowCloseHandler;
import com.google.gwt.maps.client.event.InfoWindowListener;
import com.google.gwt.maps.client.event.InfoWindowOpenHandler;
import com.google.gwt.maps.client.event.MapAddMapTypeHandler;
import com.google.gwt.maps.client.event.MapAddOverlayHandler;
import com.google.gwt.maps.client.event.MapClearOverlaysHandler;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.event.MapClickListener;
import com.google.gwt.maps.client.event.MapDoubleClickHandler;
import com.google.gwt.maps.client.event.MapDragListener;
import com.google.gwt.maps.client.event.MapDragStartHandler;
import com.google.gwt.maps.client.event.MapDragendHandler;
import com.google.gwt.maps.client.event.MapLoadHandler;
import com.google.gwt.maps.client.event.MapMouseListener;
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
import com.google.gwt.maps.client.event.InfoWindowBeforeCloseHandler.InfoWindowBeforeCloseEvent;
import com.google.gwt.maps.client.event.InfoWindowCloseHandler.InfoWindowCloseEvent;
import com.google.gwt.maps.client.event.InfoWindowOpenHandler.InfoWindowOpenEvent;
import com.google.gwt.maps.client.event.MapAddMapTypeHandler.MapAddMapTypeEvent;
import com.google.gwt.maps.client.event.MapAddOverlayHandler.MapAddOverlayEvent;
import com.google.gwt.maps.client.event.MapClearOverlaysHandler.MapClearOverlaysEvent;
import com.google.gwt.maps.client.event.MapClickHandler.MapClickEvent;
import com.google.gwt.maps.client.event.MapDoubleClickHandler.MapDoubleClickEvent;
import com.google.gwt.maps.client.event.MapDragStartHandler.MapDragStartEvent;
import com.google.gwt.maps.client.event.MapDragendHandler.MapDragendEvent;
import com.google.gwt.maps.client.event.MapLoadHandler.MapLoadEvent;
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

  private HandlerCollection<MapClickHandler> clickHandlers;
  private ListenerCollection<MapClickListener> clickListeners;
  private HandlerCollection<MapDoubleClickHandler> doubleClickHandlers;
  private ListenerCollection<MapDragListener> dragListeners;

  private HandlerCollection<InfoWindowBeforeCloseHandler> infoWindowBeforeCloseHandlers;
  private HandlerCollection<InfoWindowCloseHandler> infoWindowCloseHandlers;
  private ListenerCollection<InfoWindowListener> infoWindowListeners;

  private HandlerCollection<InfoWindowOpenHandler> infoWindowOpenHandlers;

  /* Reference to GMap2 object. */
  private final JavaScriptObject jsoPeer;

  private HandlerCollection<MapAddMapTypeHandler> mapAddMapTypeHandlers;
  private HandlerCollection<MapAddOverlayHandler> mapAddOverlayHandlers;
  private HandlerCollection<MapClearOverlaysHandler> mapClearOverlaysHandlers;
  private final MapPanel mapContainer = new MapPanel();
  private HandlerCollection<MapDragendHandler> mapDragendHandlers;
  private HandlerCollection<MapDragStartHandler> mapDragStartHandlers;

  private HandlerCollection<MapLoadHandler> mapLoadHandlers;

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

  private HandlerCollection<MapRightClickHandler> rightClickHandlers;

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
   * 
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addInfoWindowBeforeCloseHandler(
      final InfoWindowBeforeCloseHandler handler) {
    if (infoWindowBeforeCloseHandlers == null) {
      infoWindowBeforeCloseHandlers = new HandlerCollection<InfoWindowBeforeCloseHandler>(
          jsoPeer, MapEvent.INFOWINDOWBEFORECLOSE);
    }

    infoWindowBeforeCloseHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        InfoWindowBeforeCloseEvent e = new InfoWindowBeforeCloseEvent(
            MapWidget.this);
        handler.onInfoWindowBeforeClose(e);
      }
    });
  }

  /**
   * 
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addInfoWindowCloseHandler(final InfoWindowCloseHandler handler) {
    if (infoWindowCloseHandlers == null) {
      infoWindowCloseHandlers = new HandlerCollection<InfoWindowCloseHandler>(
          jsoPeer, MapEvent.INFOWINDOWCLOSE);
    }

    infoWindowCloseHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        InfoWindowCloseEvent e = new InfoWindowCloseEvent(MapWidget.this);
        handler.onInfoWindowClose(e);
      }
    });
  }

  /**
   * Add a listener for InfoWindow events.
   * 
   * @param listener listener to invoke on InfoWindow events
   */
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
   * 
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addInfoWindowOpenHandler(final InfoWindowOpenHandler handler) {
    if (infoWindowOpenHandlers == null) {
      infoWindowOpenHandlers = new HandlerCollection<InfoWindowOpenHandler>(
          jsoPeer, MapEvent.INFOWINDOWOPEN);
    }

    infoWindowOpenHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        InfoWindowOpenEvent e = new InfoWindowOpenEvent(MapWidget.this);
        handler.onInfoWindowOpen(e);
      }
    });
  }

  /**
   * 
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMapAddMapTypeHandler(final MapAddMapTypeHandler handler) {
    if (mapAddMapTypeHandlers == null) {
      mapAddMapTypeHandlers = new HandlerCollection<MapAddMapTypeHandler>(
          jsoPeer, MapEvent.ADDMAPTYPE);
    }

    mapAddMapTypeHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MapAddMapTypeEvent e = new MapAddMapTypeEvent(MapWidget.this);
        handler.onAddMapType(e);
      }
    });
  }

  /**
   * 
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMapAddOverlayHandler(final MapAddOverlayHandler handler) {
    if (mapAddOverlayHandlers == null) {
      mapAddOverlayHandlers = new HandlerCollection<MapAddOverlayHandler>(
          jsoPeer, MapEvent.ADDOVERLAY);
    }

    mapAddOverlayHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MapAddOverlayEvent e = new MapAddOverlayEvent(MapWidget.this);
        handler.onAddOverlay(e);
      }
    });
  }

  /**
   * 
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
    if (clickHandlers == null) {
      clickHandlers = new HandlerCollection<MapClickHandler>(jsoPeer,
          MapEvent.CLICK);
    }

    clickHandlers.addHandler(handler, new OverlayLatLngCallback() {
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
   *             {@link MapWidget#addMapClickHandler(MapClickHandler)}
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
    if (doubleClickHandlers == null) {
      doubleClickHandlers = new HandlerCollection<MapDoubleClickHandler>(
          jsoPeer, MapEvent.DBLCLICK);
    }

    doubleClickHandlers.addHandler(handler, new OverlayLatLngCallback() {
      @Override
      public void callback(Overlay overlay, LatLng latlng) {
        // the overlay parameter is always NULL according to the Maps API.
        MapDoubleClickEvent e = new MapDoubleClickEvent(MapWidget.this, latlng);
        handler.onDoubleClick(e);
      }
    });
  }

  /**
   * 
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMapDragendHandler(final MapDragendHandler handler) {
    if (mapDragendHandlers == null) {
      mapDragendHandlers = new HandlerCollection<MapDragendHandler>(jsoPeer,
          MapEvent.DRAGEND);
    }

    mapDragendHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MapDragendEvent e = new MapDragendEvent(MapWidget.this);
        handler.onDragend(e);
      }
    });
  }

  /**
   * Add a drag listener for mouse drag events.
   * 
   * @param listener listener to invoke on mouse drag events.
   */
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
   * 
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
   * 
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMapLoadHandler(final MapLoadHandler handler) {
    if (mapLoadHandlers == null) {
      mapLoadHandlers = new HandlerCollection<MapLoadHandler>(jsoPeer,
          MapEvent.LOAD);
    }

    mapLoadHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MapLoadEvent e = new MapLoadEvent(MapWidget.this);
        handler.onLoad(e);
      }
    });
  }

  /**
   * Add a listener for mouse move events.
   * 
   * @param listener listener to invoke on mouse move events.
   */
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
   * 
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMapMouseOutHandler(final MapMouseOutHandler handler) {
    if (mapMouseOutHandlers == null) {
      mapMouseOutHandlers = new HandlerCollection<MapMouseOutHandler>(jsoPeer,
          MapEvent.MOUSEOUT);
    }

    mapMouseOutHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MapMouseOutEvent e = new MapMouseOutEvent(MapWidget.this);
        handler.onMouseOut(e);
      }
    });
  }

  /**
   * 
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMapMouseOverHandler(final MapMouseOverHandler handler) {
    if (mapMouseOverHandlers == null) {
      mapMouseOverHandlers = new HandlerCollection<MapMouseOverHandler>(
          jsoPeer, MapEvent.MOUSEOVER);
    }

    mapMouseOverHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MapMouseOverEvent e = new MapMouseOverEvent(MapWidget.this);
        handler.onMouseOver(e);
      }
    });
  }

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
   */
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
   * 
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMapRemoveMapTypeHandler(final MapRemoveMapTypeHandler handler) {
    if (mapRemoveMapTypeHandlers == null) {
      mapRemoveMapTypeHandlers = new HandlerCollection<MapRemoveMapTypeHandler>(
          jsoPeer, MapEvent.REMOVEMAPTYPE);
    }

    mapRemoveMapTypeHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MapRemoveMapTypeEvent e = new MapRemoveMapTypeEvent(MapWidget.this);
        handler.onRemoveMapType(e);
      }
    });
  }

  /**
   * 
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMapRemoveOverlayHandler(final MapRemoveOverlayHandler handler) {
    if (mapRemoveOverlayHandlers == null) {
      mapRemoveOverlayHandlers = new HandlerCollection<MapRemoveOverlayHandler>(
          jsoPeer, MapEvent.REMOVEOVERLAY);
    }

    mapRemoveOverlayHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MapRemoveOverlayEvent e = new MapRemoveOverlayEvent(MapWidget.this);
        handler.onRemoveOverlay(e);
      }
    });
  }

  /**
   * Add a click handler for mouse right click events.
   * 
   * @param handler handler to invoke on mouse click events.
   */
  public void addMapRightClickHandler(final MapRightClickHandler handler) {
    if (rightClickHandlers == null) {
      rightClickHandlers = new HandlerCollection<MapRightClickHandler>(jsoPeer,
          MapEvent.SINGLERIGHTCLICK);
    }

    rightClickHandlers.addHandler(handler, new PointElementOverlayCallback() {
      @Override
      public void callback(Point point, Element elem, Overlay overlay) {
        MapRightClickEvent e = new MapRightClickEvent(MapWidget.this, point,
            elem, overlay);
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
   * 
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
   * 
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMapZoomEndHandler(final MapZoomEndHandler handler) {
    if (mapZoomEndHandlers == null) {
      mapZoomEndHandlers = new HandlerCollection<MapZoomEndHandler>(jsoPeer,
          MapEvent.ZOOMEND);
    }

    mapZoomEndHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MapZoomEndEvent e = new MapZoomEndEvent(MapWidget.this);
        handler.onZoomEnd(e);
      }
    });
  }

  /**
   * Add a listener for changes in the zoom level of the map.
   * 
   * @param listener a listener to call back when the zoom level of the map
   *          changes.
   */
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
   * Removes all handlers of this map added with
   * {@link MapWidget#addInfoWindowBeforeCloseHandler(InfoWindowBeforeCloseHandler)}.
   */
  public void clearInfoWindowBeforeCloseHandlers() {
    if (infoWindowBeforeCloseHandlers != null) {
      infoWindowBeforeCloseHandlers.clearHandlers();
    }
  }

  /**
   * Removes all handlers of this map added with
   * {@link MapWidget#addInfoWindowCloseHandler(InfoWindowCloseHandler)}.
   */
  public void clearInfoWindowCloseHandlers() {
    if (infoWindowCloseHandlers != null) {
      infoWindowCloseHandlers.clearHandlers();
    }
  }

  /**
   * Removes all listeners of this map added with
   * {@link MapWidget#addInfoWindowListener(InfoWindowListener)}.
   */
  public void clearInfoWindowListeners() {
    if (infoWindowListeners != null) {
      infoWindowListeners.clearListeners();
    }
  }

  /**
   * Removes all handlers of this map added with
   * {@link MapWidget#addInfoWindowOpenHandler(InfoWindowOpenHandler)}.
   */
  public void clearInfoWindowOpenHandlers() {
    if (infoWindowOpenHandlers != null) {
      infoWindowOpenHandlers.clearHandlers();
    }
  }

  /**
   * Removes all handlers of this map added with
   * {@link MapWidget#addMapAddMapTypeHandler(MapAddMapTypeHandler)}.
   */
  public void clearMapAddMapTypeHandlers() {
    if (mapAddMapTypeHandlers != null) {
      mapAddMapTypeHandlers.clearHandlers();
    }
  }

  /**
   * Removes all handlers of this map added with
   * {@link MapWidget#addMapAddOverlayHandler(MapAddOverlayHandler)}.
   */
  public void clearMapAddOverlayHandlers() {
    if (mapAddOverlayHandlers != null) {
      mapAddOverlayHandlers.clearHandlers();
    }
  }

  /**
   * Removes all handlers of this map added with
   * {@link MapWidget#addMapClearOverlaysHandler(MapClearOverlaysHandler)}.
   */
  public void clearMapClearOverlaysHandlers() {
    if (mapClearOverlaysHandlers != null) {
      mapClearOverlaysHandlers.clearHandlers();
    }
  }

  /**
   * Removes all click handlers of this map added with
   * {@link MapWidget#addMapClickHandler(MapClickHandler)}.
   */
  public void clearMapClickHandlers() {
    if (clickHandlers != null) {
      clickHandlers.clearHandlers();
    }
  }

  /**
   * Removes all listeners of this map added with
   * {@link MapWidget#addMapClickListener(MapClickListener)}.
   */
  public void clearMapClickListeners() {
    if (clickListeners != null) {
      clickListeners.clearListeners();
    }
  }

  /**
   * Removes all click handlers of this map added with
   * {@link MapWidget#addMapDoubleClickHandler(MapDoubleClickHandler)}.
   */
  public void clearMapDoubleClickHandlers() {
    if (doubleClickHandlers != null) {
      doubleClickHandlers.clearHandlers();
    }
  }

  /**
   * Removes all handlers of this map added with
   * {@link MapWidget#addMapDragendHandler(MapDragendHandler)}.
   */
  public void clearMapDragendHandlers() {
    if (mapDragendHandlers != null) {
      mapDragendHandlers.clearHandlers();
    }
  }

  /**
   * Removes all listeners of this map added with
   * {@link MapWidget#addMapDragListener(MapDragListener)}.
   */
  public void clearMapDragListeners() {
    if (dragListeners != null) {
      dragListeners.clearListeners();
    }
  }

  /**
   * Removes all handlers of this map added with
   * {@link MapWidget#addMapDragStartHandler(MapDragStartHandler)}.
   */
  public void clearMapDragStartHandlers() {
    if (mapDragStartHandlers != null) {
      mapDragStartHandlers.clearHandlers();
    }
  }

  /**
   * Removes all handlers of this map added with
   * {@link MapWidget#addMapLoadHandler(MapLoadHandler)}.
   */
  public void clearMapLoadHandlers() {
    if (mapLoadHandlers != null) {
      mapLoadHandlers.clearHandlers();
    }
  }

  /**
   * Removes all listeners of this map added with
   * {@link MapWidget#addMapMouseListener(MapMouseListener)}.
   */
  public void clearMapMouseListeners() {
    if (mouseListeners != null) {
      mouseListeners.clearListeners();
    }
  }

  /**
   * Removes all handlers of this map added with
   * {@link MapWidget#addMapMouseOutHandler(MapMouseOutHandler)}.
   */
  public void clearMapMouseOutHandlers() {
    if (mapMouseOutHandlers != null) {
      mapMouseOutHandlers.clearHandlers();
    }
  }

  /**
   * Removes all handlers of this map added with
   * {@link MapWidget#addMapMouseOverHandler(MapMouseOverHandler)}.
   */
  public void clearMapMouseOverHandlers() {
    if (mapMouseOverHandlers != null) {
      mapMouseOverHandlers.clearHandlers();
    }
  }

  /**
   * Removes all handlers of this map added with
   * {@link MapWidget#addMapMoveEndHandler(MapMoveEndHandler)}.
   */
  public void clearMapMoveEndHandlers() {
    if (mapMoveEndHandlers != null) {
      mapMoveEndHandlers.clearHandlers();
    }
  }

  /**
   * Removes all handlers of this map added with
   * {@link MapWidget#addMapMoveHandler(MapMoveHandler)}.
   */
  public void clearMapMoveHandlers() {
    if (mapMoveHandlers != null) {
      mapMoveHandlers.clearHandlers();
    }
  }

  /**
   * Removes all listeners of this map added with
   * {@link MapWidget#addMapMoveListener(MapMoveListener)}.
   */
  public void clearMapMoveListeners() {
    if (moveListeners != null) {
      moveListeners.clearListeners();
    }
  }

  /**
   * Removes all handlers of this map added with
   * {@link MapWidget#addMapMoveStartHandler(MapMoveStartHandler)}.
   */
  public void clearMapMoveStartHandlers() {
    if (mapMoveStartHandlers != null) {
      mapMoveStartHandlers.clearHandlers();
    }
  }

  /**
   * Removes all handlers of this map added with
   * {@link MapWidget#addMapRemoveMapTypeHandler(MapRemoveMapTypeHandler)}.
   */
  public void clearMapRemoveMapTypeHandlers() {
    if (mapRemoveMapTypeHandlers != null) {
      mapRemoveMapTypeHandlers.clearHandlers();
    }
  }

  /**
   * Removes all handlers of this map added with
   * {@link MapWidget#addMapRemoveOverlayHandler(MapRemoveOverlayHandler)}.
   */
  public void clearMapRemoveOverlayHandlers() {
    if (mapRemoveOverlayHandlers != null) {
      mapRemoveOverlayHandlers.clearHandlers();
    }
  }

  /**
   * Removes all click handlers of this map added with
   * {@link MapWidget#addMapRightClickHandler(MapRightClickHandler)}.
   */
  public void clearMapRightClickHandlers() {
    if (rightClickHandlers != null) {
      rightClickHandlers.clearHandlers();
    }
  }

  /**
   * Removes all handlers of this map added with
   * {@link MapWidget#addMapTypeChangedHandler(MapTypeChangedHandler)}.
   */
  public void clearMapTypeChangedHandlers() {
    if (mapTypeChangedHandlers != null) {
      mapTypeChangedHandlers.clearHandlers();
    }
  }

  /**
   * Removes all listeners of this map added with
   * {@link MapWidget#addMapTypeListener(MapTypeListener)}.
   */
  public void clearMapTypeListeners() {
    if (mapTypeListeners != null) {
      mapTypeListeners.clearListeners();
    }
  }

  /**
   * Removes all handlers of this map added with
   * {@link MapWidget#addMapZoomEndHandler(MapZoomEndHandler)}.
   */
  public void clearMapZoomEndHandlers() {
    if (mapZoomEndHandlers != null) {
      mapZoomEndHandlers.clearHandlers();
    }
  }

  /**
   * Removes all listeners of this map added with
   * {@link MapWidget#addMapZoomListener(MapZoomListener)}.
   */
  public void clearMapZoomListeners() {
    if (zoomListeners != null) {
      zoomListeners.clearListeners();
    }
  }

  /**
   * Removes all listeners of this map added with
   * {@link MapWidget#addOverlayListener(OverlayListener)}.
   */
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
   * {@link MapWidget#addInfoWindowBeforeCloseHandler(InfoWindowBeforeCloseHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeInfoWindowBeforeCloseHandler(
      InfoWindowBeforeCloseHandler handler) {
    if (infoWindowBeforeCloseHandlers != null) {
      infoWindowBeforeCloseHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link MapWidget#addInfoWindowCloseHandler(InfoWindowCloseHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeInfoWindowCloseHandler(InfoWindowCloseHandler handler) {
    if (infoWindowCloseHandlers != null) {
      infoWindowCloseHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a single listener of this map previously added with
   * {@link MapWidget#addInfoWindowListener(InfoWindowListener)}.
   * 
   * @param listener the listener to remove
   */
  public void removeInfoWindowListener(InfoWindowListener listener) {
    if (infoWindowListeners != null) {
      infoWindowListeners.removeListener(listener);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link MapWidget#addInfoWindowOpenHandler(InfoWindowOpenHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeInfoWindowOpenHandler(InfoWindowOpenHandler handler) {
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
    if (clickHandlers != null) {
      clickHandlers.removeHandler(handler);
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
    if (doubleClickHandlers != null) {
      doubleClickHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link MapWidget#addMapDragendHandler(MapDragendHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeMapDragendHandler(MapDragendHandler handler) {
    if (mapDragendHandlers != null) {
      mapDragendHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes the given drag listener from the map previously added with
   * {@link MapWidget#addMapDragListener(MapDragListener)}.
   * 
   * @param listener the drag listener to remove
   */
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
   * Removes a single handler of this map previously added with
   * {@link MapWidget#addMapLoadHandler(MapLoadHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeMapLoadHandler(MapLoadHandler handler) {
    if (mapLoadHandlers != null) {
      mapLoadHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a single listener of this map previously added with
   * {@link MapWidget#addMapMouseListener(MapMouseListener)}.
   * 
   * 
   * @param listener the listener to remove
   */
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
   */
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
    if (rightClickHandlers != null) {
      rightClickHandlers.removeHandler(handler);
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
   */
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
   */
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
   */
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
   * Manually trigger the specified event on this object.
   * 
   * @param event an event to deliver to the handler.
   */
  public void trigger(InfoWindowBeforeCloseEvent event) {
    infoWindowBeforeCloseHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * @param event an event to deliver to the handler.
   */
  public void trigger(InfoWindowCloseEvent event) {
    infoWindowCloseHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * @param event an event to deliver to the handler.
   */
  public void trigger(InfoWindowOpenEvent event) {
    infoWindowOpenHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * @param event an event to deliver to the handler.
   */
  public void trigger(MapAddMapTypeEvent event) {
    mapAddMapTypeHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * @param event an event to deliver to the handler.
   */
  public void trigger(MapAddOverlayEvent event) {
    mapAddOverlayHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * @param event an event to deliver to the handler.
   */
  public void trigger(MapClearOverlaysEvent event) {
    mapClearOverlaysHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * @param event an event to deliver to the handler.
   */
  public void trigger(MapClickEvent event) {
    clickHandlers.trigger(event.getOverlay(), event.getPoint());
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * @param event an event to deliver to the handler.
   */
  public void trigger(MapDoubleClickEvent event) {
    doubleClickHandlers.trigger(null, event.getPoint());
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * @param event an event to deliver to the handler.
   */
  public void trigger(MapDragendEvent event) {
    mapDragendHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * @param event an event to deliver to the handler.
   */
  public void trigger(MapDragStartEvent event) {
    mapDragStartHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * @param event an event to deliver to the handler.
   */
  public void trigger(MapLoadEvent event) {
    mapLoadHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * @param event an event to deliver to the handler.
   */
  public void trigger(MapMouseOutEvent event) {
    mapMouseOutHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * @param event an event to deliver to the handler.
   */
  public void trigger(MapMouseOverEvent event) {
    mapMouseOverHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * @param event an event to deliver to the handler.
   */
  public void trigger(MapRemoveMapTypeEvent event) {
    mapRemoveMapTypeHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * @param event an event to deliver to the handler.
   */
  public void trigger(MapRemoveOverlayEvent event) {
    mapRemoveOverlayHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * @param event an event to deliver to the handler.
   */
  public void trigger(MapRightClickEvent event) {
    rightClickHandlers.trigger(event.getPoint(), event.getElement(),
        event.getOverlay());
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * @param event an event to deliver to the handler.
   */
  public void trigger(MapTypeChangedEvent event) {
    mapTypeChangedHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * @param event an event to deliver to the handler.
   */
  public void trigger(MapZoomEndEvent event) {
    mapZoomEndHandlers.trigger();
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

}

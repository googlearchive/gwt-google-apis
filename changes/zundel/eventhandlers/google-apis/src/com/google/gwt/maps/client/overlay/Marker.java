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
package com.google.gwt.maps.client.overlay;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.jsio.client.impl.Extractor;
import com.google.gwt.maps.client.event.MarkerClickHandler;
import com.google.gwt.maps.client.event.MarkerClickListener;
import com.google.gwt.maps.client.event.MarkerDoubleClickHandler;
import com.google.gwt.maps.client.event.MarkerDragEndHandler;
import com.google.gwt.maps.client.event.MarkerDragHandler;
import com.google.gwt.maps.client.event.MarkerDragListener;
import com.google.gwt.maps.client.event.MarkerDragStartHandler;
import com.google.gwt.maps.client.event.MarkerInfoWindowBeforeCloseHandler;
import com.google.gwt.maps.client.event.MarkerInfoWindowOpenHandler;
import com.google.gwt.maps.client.event.MarkerMouseDownHandler;
import com.google.gwt.maps.client.event.MarkerMouseListener;
import com.google.gwt.maps.client.event.MarkerMouseOutHandler;
import com.google.gwt.maps.client.event.MarkerMouseOverHandler;
import com.google.gwt.maps.client.event.MarkerMouseUpHandler;
import com.google.gwt.maps.client.event.MarkerRemoveHandler;
import com.google.gwt.maps.client.event.MarkerVisibilityChangedHandler;
import com.google.gwt.maps.client.event.RemoveListener;
import com.google.gwt.maps.client.event.VisibilityListener;
import com.google.gwt.maps.client.event.MarkerClickHandler.MarkerClickEvent;
import com.google.gwt.maps.client.event.MarkerDoubleClickHandler.MarkerDoubleClickEvent;
import com.google.gwt.maps.client.event.MarkerDragEndHandler.MarkerDragEndEvent;
import com.google.gwt.maps.client.event.MarkerDragHandler.MarkerDragEvent;
import com.google.gwt.maps.client.event.MarkerDragStartHandler.MarkerDragStartEvent;
import com.google.gwt.maps.client.event.MarkerInfoWindowBeforeCloseHandler.MarkerInfoWindowBeforeCloseEvent;
import com.google.gwt.maps.client.event.MarkerInfoWindowOpenHandler.MarkerInfoWindowOpenEvent;
import com.google.gwt.maps.client.event.MarkerMouseDownHandler.MarkerMouseDownEvent;
import com.google.gwt.maps.client.event.MarkerMouseOutHandler.MarkerMouseOutEvent;
import com.google.gwt.maps.client.event.MarkerMouseOverHandler.MarkerMouseOverEvent;
import com.google.gwt.maps.client.event.MarkerMouseUpHandler.MarkerMouseUpEvent;
import com.google.gwt.maps.client.event.MarkerRemoveHandler.MarkerRemoveEvent;
import com.google.gwt.maps.client.event.MarkerVisibilityChangedHandler.MarkerVisibilityChangedEvent;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.impl.EventImpl;
import com.google.gwt.maps.client.impl.HandlerCollection;
import com.google.gwt.maps.client.impl.ListenerCollection;
import com.google.gwt.maps.client.impl.MapEvent;
import com.google.gwt.maps.client.impl.MarkerImpl;
import com.google.gwt.maps.client.impl.EventImpl.BooleanCallback;
import com.google.gwt.maps.client.impl.EventImpl.VoidCallback;
import com.google.gwt.maps.client.overlay.Overlay.ConcreteOverlay;

/**
 * Marks a position on the map. It extends the ConcreteOverlay class and thus is
 * added to the map using the MapWidget.addOverlay() method.
 * 
 * A marker object has a point, which is the geographical position where the
 * marker is anchored on the map, and an icon. If the icon is not set in the
 * constructor, the default icon Icon.DEFAULT_ICON is used.
 * 
 * After it is added to a map, the info window of that map can be opened through
 * the marker. The marker object will fire mouse events and InfoWindow events.
 * 
 */
public final class Marker extends ConcreteOverlay {

  // TODO: DELETE ME! (needs to function w/o)
  @SuppressWarnings("unused")
  private static final Extractor<Marker> __extractor = new Extractor<Marker>() {
    public Marker fromJS(JavaScriptObject jso) {
      throw new UnsupportedOperationException();
    }

    public JavaScriptObject toJS(Marker o) {
      return o.jsoPeer;
    }
  };

  private static final EventImpl EVENT_IMPL = EventImpl.impl;

  static Marker createPeer(JavaScriptObject jsoPeer) {
    return new Marker(jsoPeer);
  }

  // Keep track of JSO's registered for each instance of addXXXListener()
  private ListenerCollection<MarkerClickListener> clickListeners;
  private ListenerCollection<MarkerDragListener> dragListeners;
  private HandlerCollection<MarkerInfoWindowBeforeCloseHandler> infoWindowBeforeCloseHandlers;
  private HandlerCollection<MarkerInfoWindowOpenHandler> infoWindowOpenHandlers;
  private HandlerCollection<MarkerClickHandler> markerClickHandlers;
  private HandlerCollection<MarkerDoubleClickHandler> markerDoubleClickHandlers;
  private HandlerCollection<MarkerDragEndHandler> markerDragEndHandlers;
  private HandlerCollection<MarkerDragHandler> markerDragHandlers;
  private HandlerCollection<MarkerDragStartHandler> markerDragStartHandlers;
  private HandlerCollection<MarkerMouseDownHandler> markerMouseDownHandlers;
  private HandlerCollection<MarkerMouseOutHandler> markerMouseOutHandlers;
  private HandlerCollection<MarkerMouseOverHandler> markerMouseOverHandlers;
  private HandlerCollection<MarkerMouseUpHandler> markerMouseUpHandlers;
  private HandlerCollection<MarkerRemoveHandler> markerRemoveHandlers;
  private HandlerCollection<MarkerVisibilityChangedHandler> markerVisibilityChangedHandlers;
  private ListenerCollection<MarkerMouseListener> mouseListeners;
  private ListenerCollection<RemoveListener> removeListeners;
  private ListenerCollection<VisibilityListener> visibilityListeners;

  /**
   * Create a new marker at the specified point using default options. Add the
   * newly created marker to a
   * 
   * @{link MapWidget} with the
   *        {@link com.google.gwt.maps.client.MapWidget#addOverlay(Overlay)}
   *        method.
   * 
   * @param point The point to create the new marker.
   */
  public Marker(LatLng point) {
    super(MarkerImpl.impl.construct(point));
  }

  /**
   * Create a new marker at the specified point using the supplied options
   * overrides. Add the newly created marker to a
   * 
   * @{link MapWidget} with the
   *        {@link com.google.gwt.maps.client.MapWidget#addOverlay(Overlay)}
   *        method.
   * 
   * @param point The point to create the new marker.
   * @param options Use settings in this object to override the Marker defaults.
   */
  public Marker(LatLng point, MarkerOptions options) {
    super(MarkerImpl.impl.construct(point, options));
  }

  private Marker(JavaScriptObject jsoPeer) {
    super(jsoPeer);
  }

  /**
   * 
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMarkerInfoWindowBeforeCloseHandler(
      final MarkerInfoWindowBeforeCloseHandler handler) {
    if (infoWindowBeforeCloseHandlers == null) {
      infoWindowBeforeCloseHandlers = new HandlerCollection<MarkerInfoWindowBeforeCloseHandler>(
          jsoPeer, MapEvent.INFOWINDOWBEFORECLOSE);
    }

    infoWindowBeforeCloseHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MarkerInfoWindowBeforeCloseEvent e = new MarkerInfoWindowBeforeCloseEvent(
            Marker.this);
        handler.onInfoWindowBeforeClose(e);
      }
    });
  }

  /**
   * 
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMarkerInfoWindowOpenHandler(
      final MarkerInfoWindowOpenHandler handler) {
    if (infoWindowOpenHandlers == null) {
      infoWindowOpenHandlers = new HandlerCollection<MarkerInfoWindowOpenHandler>(
          jsoPeer, MapEvent.INFOWINDOWOPEN);
    }

    infoWindowOpenHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MarkerInfoWindowOpenEvent e = new MarkerInfoWindowOpenEvent(Marker.this);
        handler.onInfoWindowOpen(e);
      }
    });
  }

  /**
   * 
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMarkerClickHandler(final MarkerClickHandler handler) {
    if (markerClickHandlers == null) {
      markerClickHandlers = new HandlerCollection<MarkerClickHandler>(jsoPeer,
          MapEvent.CLICK);
    }

    markerClickHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MarkerClickEvent e = new MarkerClickEvent(Marker.this);
        handler.onClick(e);
      }
    });
  }

  /**
   * Associate a click event listener with this Marker.
   * 
   * @param listener a click listener
   */
  public void addMarkerClickListener(final MarkerClickListener listener) {
    if (clickListeners == null) {
      clickListeners = new ListenerCollection<MarkerClickListener>();
    }
    JavaScriptObject[] clickEventHandles = {
        EVENT_IMPL.addListenerVoid(jsoPeer, MapEvent.CLICK, new VoidCallback() {
          @Override
          public void callback() {
            listener.onClick(Marker.this);
          }
        }),
        EVENT_IMPL.addListenerVoid(jsoPeer, MapEvent.DBLCLICK,
            new VoidCallback() {
              @Override
              public void callback() {
                listener.onDoubleClick(Marker.this);
              }
            })};
    clickListeners.addListener(listener, clickEventHandles);
  }

  /**
   * 
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMarkerDoubleClickHandler(final MarkerDoubleClickHandler handler) {
    if (markerDoubleClickHandlers == null) {
      markerDoubleClickHandlers = new HandlerCollection<MarkerDoubleClickHandler>(
          jsoPeer, MapEvent.DBLCLICK);
    }

    markerDoubleClickHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MarkerDoubleClickEvent e = new MarkerDoubleClickEvent(Marker.this);
        handler.onDoubleClick(e);
      }
    });
  }

  /**
   * 
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMarkerDragEndHandler(final MarkerDragEndHandler handler) {
    if (markerDragEndHandlers == null) {
      markerDragEndHandlers = new HandlerCollection<MarkerDragEndHandler>(
          jsoPeer, MapEvent.DRAGEND);
    }

    markerDragEndHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MarkerDragEndEvent e = new MarkerDragEndEvent(Marker.this);
        handler.onDragEnd(e);
      }
    });
  }

  /**
   * 
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMarkerDragHandler(final MarkerDragHandler handler) {
    if (markerDragHandlers == null) {
      markerDragHandlers = new HandlerCollection<MarkerDragHandler>(jsoPeer,
          MapEvent.DRAG);
    }

    markerDragHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MarkerDragEvent e = new MarkerDragEvent(Marker.this);
        handler.onDrag(e);
      }
    });
  }

  /**
   * Associate a drag listener with this Marker.
   * 
   * @param listener a drag event listener
   */
  public void addMarkerDragListener(final MarkerDragListener listener) {
    if (dragListeners == null) {
      dragListeners = new ListenerCollection<MarkerDragListener>();
    }
    JavaScriptObject[] dragEventHandles = {
        EVENT_IMPL.addListenerVoid(jsoPeer, MapEvent.DRAGSTART,
            new VoidCallback() {
              @Override
              public void callback() {
                listener.onDragStart(Marker.this);
              }
            }),
        EVENT_IMPL.addListenerVoid(jsoPeer, MapEvent.DRAG, new VoidCallback() {
          @Override
          public void callback() {
            listener.onDrag(Marker.this);
          }
        }),
        EVENT_IMPL.addListenerVoid(jsoPeer, MapEvent.DRAGEND,
            new VoidCallback() {
              @Override
              public void callback() {
                listener.onDragEnd(Marker.this);
              }
            })};
    dragListeners.addListener(listener, dragEventHandles);
  }

  /**
   * 
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMarkerDragStartHandler(final MarkerDragStartHandler handler) {
    if (markerDragStartHandlers == null) {
      markerDragStartHandlers = new HandlerCollection<MarkerDragStartHandler>(
          jsoPeer, MapEvent.DRAGSTART);
    }

    markerDragStartHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MarkerDragStartEvent e = new MarkerDragStartEvent(Marker.this);
        handler.onDragStart(e);
      }
    });
  }

  /**
   * 
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMarkerMouseDownHandler(final MarkerMouseDownHandler handler) {
    if (markerMouseDownHandlers == null) {
      markerMouseDownHandlers = new HandlerCollection<MarkerMouseDownHandler>(
          jsoPeer, MapEvent.MOUSEDOWN);
    }

    markerMouseDownHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MarkerMouseDownEvent e = new MarkerMouseDownEvent(Marker.this);
        handler.onMouseDown(e);
      }
    });
  }

  /**
   * Associate a mouse listener with this Marker.
   * 
   * @param listener a mouse event listener
   */
  public void addMarkerMouseListener(final MarkerMouseListener listener) {
    if (mouseListeners == null) {
      mouseListeners = new ListenerCollection<MarkerMouseListener>();
    }
    JavaScriptObject mouseEventHandles[] = {
        EVENT_IMPL.addListenerVoid(jsoPeer, MapEvent.MOUSEDOWN,
            new VoidCallback() {
              @Override
              public void callback() {
                listener.onMouseDown(Marker.this);
              }
            }),
        EVENT_IMPL.addListenerVoid(jsoPeer, MapEvent.MOUSEUP,
            new VoidCallback() {
              @Override
              public void callback() {
                listener.onMouseUp(Marker.this);
              }
            }),
        EVENT_IMPL.addListenerVoid(jsoPeer, MapEvent.MOUSEOVER,
            new VoidCallback() {
              @Override
              public void callback() {
                listener.onMouseOver(Marker.this);
              }
            }),
        EVENT_IMPL.addListenerVoid(jsoPeer, MapEvent.MOUSEOUT,
            new VoidCallback() {
              @Override
              public void callback() {
                listener.onMouseOut(Marker.this);
              }
            })};
    mouseListeners.addListener(listener, mouseEventHandles);
  }

  /**
   * 
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMarkerMouseOutHandler(final MarkerMouseOutHandler handler) {
    if (markerMouseOutHandlers == null) {
      markerMouseOutHandlers = new HandlerCollection<MarkerMouseOutHandler>(
          jsoPeer, MapEvent.MOUSEOUT);
    }

    markerMouseOutHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MarkerMouseOutEvent e = new MarkerMouseOutEvent(Marker.this);
        handler.onMouseOut(e);
      }
    });
  }

  /**
   * 
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMarkerMouseOverHandler(final MarkerMouseOverHandler handler) {
    if (markerMouseOverHandlers == null) {
      markerMouseOverHandlers = new HandlerCollection<MarkerMouseOverHandler>(
          jsoPeer, MapEvent.MOUSEOVER);
    }

    markerMouseOverHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MarkerMouseOverEvent e = new MarkerMouseOverEvent(Marker.this);
        handler.onMouseOver(e);
      }
    });
  }

  /**
   * 
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMarkerMouseUpHandler(final MarkerMouseUpHandler handler) {
    if (markerMouseUpHandlers == null) {
      markerMouseUpHandlers = new HandlerCollection<MarkerMouseUpHandler>(
          jsoPeer, MapEvent.MOUSEUP);
    }

    markerMouseUpHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MarkerMouseUpEvent e = new MarkerMouseUpEvent(Marker.this);
        handler.onMouseUp(e);
      }
    });
  }

  /**
   * 
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMarkerRemoveHandler(final MarkerRemoveHandler handler) {
    if (markerRemoveHandlers == null) {
      markerRemoveHandlers = new HandlerCollection<MarkerRemoveHandler>(
          jsoPeer, MapEvent.REMOVE);
    }

    markerRemoveHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MarkerRemoveEvent e = new MarkerRemoveEvent(Marker.this);
        handler.onRemove(e);
      }
    });
  }

  /**
   * 
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMarkerVisibilityChangedHandler(
      final MarkerVisibilityChangedHandler handler) {
    if (markerVisibilityChangedHandlers == null) {
      markerVisibilityChangedHandlers = new HandlerCollection<MarkerVisibilityChangedHandler>(
          jsoPeer, MapEvent.VISIBILITYCHANGED);
    }

    markerVisibilityChangedHandlers.addHandler(handler, new BooleanCallback() {
      @Override
      public void callback(boolean visible) {
        MarkerVisibilityChangedEvent e = new MarkerVisibilityChangedEvent(
            Marker.this, visible);
        handler.onVisibilityChanged(e);
      }
    });
  }

  /**
   * Associate a remove listener with this Marker.
   * 
   * @param listener a remove event listener
   */
  public void addRemoveListener(final RemoveListener listener) {

    if (removeListeners == null) {
      removeListeners = new ListenerCollection<RemoveListener>();
    }

    JavaScriptObject removeEventHandles[] = {EVENT_IMPL.addListenerVoid(
        jsoPeer, MapEvent.REMOVE, new VoidCallback() {
          @Override
          public void callback() {
            listener.onRemove(Marker.this);
          }
        })};
    removeListeners.addListener(listener, removeEventHandles);
  }

  /**
   * Associate the specified listener with this Marker.
   * 
   * @param listener a visibility event listener
   */
  public void addVisibilityListener(final VisibilityListener listener) {
    if (visibilityListeners == null) {
      visibilityListeners = new ListenerCollection<VisibilityListener>();
    }
    JavaScriptObject visibilityEventHandles[] = {EVENT_IMPL.addListener(
        jsoPeer, MapEvent.VISIBILITYCHANGED, new BooleanCallback() {
          @Override
          public void callback(boolean isVisible) {
            listener.onVisibilityChanged(Marker.this, isVisible);
          }
        })};
    visibilityListeners.addListener(listener, visibilityEventHandles);
  }

  /**
   * @return the current icon used for this Marker.
   */
  public Icon getIcon() {
    return MarkerImpl.impl.getIcon(this);
  }

  /**
   * @return the current position of this Marker.
   */
  public LatLng getPoint() {
    return MarkerImpl.impl.getPoint(this);
  }

  /**
   * See if this Marker was created as a draggable marker type, that is, the
   * draggable option was set in MarkerOptions when it was constructed.
   * 
   * @return <code>true</code> if the marker was initialized as a draggable
   *         type of marker
   */
  public boolean isDraggable() {
    return MarkerImpl.impl.draggable(this);
  }

  /**
   * Returns <code>true</code> if this marker is not only a draggable type of
   * marker.
   * 
   * @return <code>true</code> if the marker can currently be dragged
   * 
   * @see Marker#isDraggable()
   * @see Marker#setDraggingEnabled(boolean)
   */
  public boolean isDraggingEnabled() {
    return MarkerImpl.impl.draggingEnabled(this);
  }

  /**
   * @return returns <code>true</code> if the marker is currently visible on
   *         the map
   */
  public boolean isVisible() {
    return !MarkerImpl.impl.isHidden(this);
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link Marker#addMarkerInfoWindowBeforeCloseHandler(MarkerInfoWindowBeforeCloseHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeInfoWindowBeforeCloseHandler(
      MarkerInfoWindowBeforeCloseHandler handler) {
    if (infoWindowBeforeCloseHandlers != null) {
      infoWindowBeforeCloseHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link Marker#addMarkerInfoWindowOpenHandler(MarkerInfoWindowOpenHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeInfoWindowOpenHandler(MarkerInfoWindowOpenHandler handler) {
    if (infoWindowOpenHandlers != null) {
      infoWindowOpenHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link Marker#addMarkerClickHandler(MarkerClickHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeMarkerClickHandler(MarkerClickHandler handler) {
    if (markerClickHandlers != null) {
      markerClickHandlers.removeHandler(handler);
    }
  }

  /**
   * Remove the specified click listener registered with this marker.
   * 
   * @param listener click listener events to remove
   */
  public void removeMarkerClickListener(MarkerClickListener listener) {
    if (clickListeners != null) {
      clickListeners.removeListener(listener);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link Marker#addMarkerDoubleClickHandler(MarkerDoubleClickHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeMarkerDoubleClickHandler(MarkerDoubleClickHandler handler) {
    if (markerDoubleClickHandlers != null) {
      markerDoubleClickHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link Marker#addMarkerDragEndHandler(MarkerDragEndHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeMarkerDragEndHandler(MarkerDragEndHandler handler) {
    if (markerDragEndHandlers != null) {
      markerDragEndHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link Marker#addMarkerDragHandler(MarkerDragHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeMarkerDragHandler(MarkerDragHandler handler) {
    if (markerDragHandlers != null) {
      markerDragHandlers.removeHandler(handler);
    }
  }

  /**
   * Remove the specified drag listener registered with this marker.
   * 
   * @param listener drag listener events to remove
   */
  public void removeMarkerDragListener(MarkerDragListener listener) {
    if (dragListeners != null) {
      dragListeners.removeListener(listener);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link Marker#addMarkerDragStartHandler(MarkerDragStartHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeMarkerDragStartHandler(MarkerDragStartHandler handler) {
    if (markerDragStartHandlers != null) {
      markerDragStartHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link Marker#addMarkerMouseDownHandler(MarkerMouseDownHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeMarkerMouseDownHandler(MarkerMouseDownHandler handler) {
    if (markerMouseDownHandlers != null) {
      markerMouseDownHandlers.removeHandler(handler);
    }
  }

  /**
   * Remove a single mouse listener registered with this marker.
   * 
   * @param listener mouse listener to remove
   */
  public void removeMarkerMouseListener(MarkerMouseListener listener) {
    if (mouseListeners != null) {
      mouseListeners.removeListener(listener);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link Marker#addMarkerMouseOutHandler(MarkerMouseOutHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeMarkerMouseOutHandler(MarkerMouseOutHandler handler) {
    if (markerMouseOutHandlers != null) {
      markerMouseOutHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link Marker#addMarkerMouseOverHandler(MarkerMouseOverHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeMarkerMouseOverHandler(MarkerMouseOverHandler handler) {
    if (markerMouseOverHandlers != null) {
      markerMouseOverHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link Marker#addMarkerMouseUpHandler(MarkerMouseUpHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeMarkerMouseUpHandler(MarkerMouseUpHandler handler) {
    if (markerMouseUpHandlers != null) {
      markerMouseUpHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link Marker#addMarkerRemoveHandler(MarkerRemoveHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeMarkerRemoveHandler(MarkerRemoveHandler handler) {
    if (markerRemoveHandlers != null) {
      markerRemoveHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link Marker#addMarkerVisibilityChangedHandler(MarkerVisibilityChangedHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeMarkerVisibilityChangedHandler(
      MarkerVisibilityChangedHandler handler) {
    if (markerVisibilityChangedHandlers != null) {
      markerVisibilityChangedHandlers.removeHandler(handler);
    }
  }

  /**
   * Remove a single remove listener registered with this marker.
   * 
   * @param listener the remove listener to remove
   */
  public void removeRemoveListener(RemoveListener listener) {
    if (removeListeners != null) {
      removeListeners.clearListeners();
    }
  }

  /**
   * Remove a single visibility listener registered with this marker.
   * 
   * @param listener visibility listener to remove
   */
  public void removeVisibilityListener(VisibilityListener listener) {
    if (visibilityListeners != null) {
      visibilityListeners.removeListener(listener);
    }
  }

  /**
   * Allow this marker to be dragged. Note: in order for dragging to work, the
   * Marker must be created using the
   * 
   * @{link MarkerOptions#setDraggable(boolean) option.
   * @param value <code>true</code> to allow the marker to be dragged.
   */
  public void setDraggingEnabled(boolean value) {
    if (value) {
      MarkerImpl.impl.enableDragging(this);
    } else {
      MarkerImpl.impl.disableDragging(this);
    }
  }

  /**
   * Use an image for this marker.
   * 
   * @param url The URL to the image to display.
   */
  public void setImage(String url) {
    MarkerImpl.impl.setImage(this, url);
  }

  /**
   * Move the marker to the specified point.
   * 
   * @param point position to move the marker to.
   */
  public void setPoint(LatLng point) {
    MarkerImpl.impl.setPoint(this, point);
  }

  /**
   * Toggle the visibility of the Marker on the map it is associated with.
   * 
   * @param visible set to <code>true</code> to make the marker visible.
   */
  public void setVisible(boolean visible) {
    if (visible) {
      MarkerImpl.impl.show(this);
    } else {
      MarkerImpl.impl.hide(this);
    }
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(MarkerInfoWindowBeforeCloseEvent event) {
    infoWindowBeforeCloseHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(MarkerInfoWindowOpenEvent event) {
    infoWindowOpenHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(MarkerClickEvent event) {
    markerClickHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(MarkerDoubleClickEvent event) {
    markerDoubleClickHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(MarkerDragEndEvent event) {
    markerDragEndHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(MarkerDragEvent event) {
    markerDragHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(MarkerDragStartEvent event) {
    markerDragStartHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(MarkerMouseDownEvent event) {
    markerMouseDownHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(MarkerMouseOutEvent event) {
    markerMouseOutHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
   void trigger(MarkerMouseOverEvent event) {
    markerMouseOverHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(MarkerMouseUpEvent event) {
    markerMouseUpHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(MarkerRemoveEvent event) {
    markerRemoveHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(MarkerVisibilityChangedEvent event) {
    markerVisibilityChangedHandlers.trigger();
  }
}

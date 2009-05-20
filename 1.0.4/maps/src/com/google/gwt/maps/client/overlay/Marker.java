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
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.event.MarkerClickHandler;
import com.google.gwt.maps.client.event.MarkerDoubleClickHandler;
import com.google.gwt.maps.client.event.MarkerDragEndHandler;
import com.google.gwt.maps.client.event.MarkerDragHandler;
import com.google.gwt.maps.client.event.MarkerDragStartHandler;
import com.google.gwt.maps.client.event.MarkerInfoWindowBeforeCloseHandler;
import com.google.gwt.maps.client.event.MarkerInfoWindowCloseHandler;
import com.google.gwt.maps.client.event.MarkerInfoWindowOpenHandler;
import com.google.gwt.maps.client.event.MarkerMouseDownHandler;
import com.google.gwt.maps.client.event.MarkerMouseOutHandler;
import com.google.gwt.maps.client.event.MarkerMouseOverHandler;
import com.google.gwt.maps.client.event.MarkerMouseUpHandler;
import com.google.gwt.maps.client.event.MarkerRemoveHandler;
import com.google.gwt.maps.client.event.MarkerVisibilityChangedHandler;
import com.google.gwt.maps.client.event.MarkerClickHandler.MarkerClickEvent;
import com.google.gwt.maps.client.event.MarkerDoubleClickHandler.MarkerDoubleClickEvent;
import com.google.gwt.maps.client.event.MarkerDragEndHandler.MarkerDragEndEvent;
import com.google.gwt.maps.client.event.MarkerDragHandler.MarkerDragEvent;
import com.google.gwt.maps.client.event.MarkerDragStartHandler.MarkerDragStartEvent;
import com.google.gwt.maps.client.event.MarkerInfoWindowBeforeCloseHandler.MarkerInfoWindowBeforeCloseEvent;
import com.google.gwt.maps.client.event.MarkerInfoWindowCloseHandler.MarkerInfoWindowCloseEvent;
import com.google.gwt.maps.client.event.MarkerInfoWindowOpenHandler.MarkerInfoWindowOpenEvent;
import com.google.gwt.maps.client.event.MarkerMouseDownHandler.MarkerMouseDownEvent;
import com.google.gwt.maps.client.event.MarkerMouseOutHandler.MarkerMouseOutEvent;
import com.google.gwt.maps.client.event.MarkerMouseOverHandler.MarkerMouseOverEvent;
import com.google.gwt.maps.client.event.MarkerMouseUpHandler.MarkerMouseUpEvent;
import com.google.gwt.maps.client.event.MarkerRemoveHandler.MarkerRemoveEvent;
import com.google.gwt.maps.client.event.MarkerVisibilityChangedHandler.MarkerVisibilityChangedEvent;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.impl.HandlerCollection;
import com.google.gwt.maps.client.impl.MapEvent;
import com.google.gwt.maps.client.impl.MarkerImpl;
import com.google.gwt.maps.client.impl.EventImpl.BooleanCallback;
import com.google.gwt.maps.client.impl.EventImpl.VoidCallback;
import com.google.gwt.maps.client.overlay.Overlay.ConcreteOverlay;
import com.google.gwt.maps.jsio.client.impl.Extractor;

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
public class Marker extends ConcreteOverlay {

  // TODO: DELETE ME! (needs to function w/o)
  @SuppressWarnings("unused")
  private static final Extractor<Marker> __extractor = new Extractor<Marker>() {
    public native Marker fromJS(JavaScriptObject jso) /*-{
      if (jso.__gwtPeer == null) {
        return @com.google.gwt.maps.client.overlay.Marker::createPeer(Lcom/google/gwt/core/client/JavaScriptObject;)(jso);
      }
      return  jso.__gwtPeer;
    }-*/;

    public JavaScriptObject toJS(Marker o) {
      return o.jsoPeer;
    }
  };

  public static Marker createPeer(JavaScriptObject jsoPeer) {
    return new Marker(jsoPeer);
  }

  // Keep track of JSO's registered for each instance of addXXXListener()
  private HandlerCollection<MarkerClickHandler> markerClickHandlers;
  private HandlerCollection<MarkerDoubleClickHandler> markerDoubleClickHandlers;
  private HandlerCollection<MarkerDragEndHandler> markerDragEndHandlers;
  private HandlerCollection<MarkerDragHandler> markerDragHandlers;
  private HandlerCollection<MarkerDragStartHandler> markerDragStartHandlers;
  private HandlerCollection<MarkerInfoWindowBeforeCloseHandler> markerInfoWindowBeforeCloseHandlers;
  private HandlerCollection<MarkerInfoWindowCloseHandler> markerInfoWindowCloseHandlers;
  private HandlerCollection<MarkerInfoWindowOpenHandler> markerInfoWindowOpenHandlers;
  private HandlerCollection<MarkerMouseDownHandler> markerMouseDownHandlers;
  private HandlerCollection<MarkerMouseOutHandler> markerMouseOutHandlers;
  private HandlerCollection<MarkerMouseOverHandler> markerMouseOverHandlers;
  private HandlerCollection<MarkerMouseUpHandler> markerMouseUpHandlers;
  private HandlerCollection<MarkerRemoveHandler> markerRemoveHandlers;
  private HandlerCollection<MarkerVisibilityChangedHandler> markerVisibilityChangedHandlers;

  /**
   * Create a new marker at the specified point using default options. Add the
   * newly created marker to a
   * 
   * {@link com.google.gwt.maps.client.MapWidget} with the
   * {@link com.google.gwt.maps.client.MapWidget#addOverlay(Overlay)} method.
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
   * {@link com.google.gwt.maps.client.MapWidget} with the
   * {@link com.google.gwt.maps.client.MapWidget#addOverlay(Overlay)} method.
   * 
   * @param point The point to create the new marker.
   * @param options Use settings in this object to override the Marker defaults.
   */
  public Marker(LatLng point, MarkerOptions options) {
    super(MarkerImpl.impl.construct(point, options));
  }
  
  /**
   * Create this marker from an existing JavaScriptObject instance.
   * 
   * @param jsoPeer an existing JavaScriptObject instance.
   */
  protected Marker(JavaScriptObject jsoPeer) {
    super(jsoPeer);
  }

  /**
   * This event is fired when the marker icon was clicked. Notice that this
   * event will also fire for the map, with the marker passed as an argument to
   * the event handler.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMarkerClickHandler(final MarkerClickHandler handler) {
    maybeInitMarkerClickHandlers();

    markerClickHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MarkerClickEvent e = new MarkerClickEvent(Marker.this);
        handler.onClick(e);
      }
    });
  }

  /**
   * This event is fired when the marker icon was double-clicked. Notice that
   * this event will not fire for the map, because the map centers on
   * double-click as a hardwired behavior.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMarkerDoubleClickHandler(final MarkerDoubleClickHandler handler) {
    maybeInitMarkerDoubleClickHandlers();

    markerDoubleClickHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MarkerDoubleClickEvent e = new MarkerDoubleClickEvent(Marker.this);
        handler.onDoubleClick(e);
      }
    });
  }

  /**
   * If the marker is enabled for dragging, this event is fired when the marker
   * ceases to be dragged.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMarkerDragEndHandler(final MarkerDragEndHandler handler) {
    maybeInitDragEndHandlers();

    markerDragEndHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MarkerDragEndEvent e = new MarkerDragEndEvent(Marker.this);
        handler.onDragEnd(e);
      }
    });
  }

  /**
   * If the marker is enabled for dragging, this event is fired when the marker
   * is being dragged.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMarkerDragHandler(final MarkerDragHandler handler) {
    maybeInitMarkerDragHandlers();

    markerDragHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MarkerDragEvent e = new MarkerDragEvent(Marker.this);
        handler.onDrag(e);
      }
    });
  }

  /**
   * If the marker is enabled for dragging, this event is fired when the marker
   * dragging begins.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMarkerDragStartHandler(final MarkerDragStartHandler handler) {
    maybeInitMarkerDragStartHandlers();

    markerDragStartHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MarkerDragStartEvent e = new MarkerDragStartEvent(Marker.this);
        handler.onDragStart(e);
      }
    });
  }

  /**
   * This event is fired before the info window of the map that was opened
   * through this marker is closed.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMarkerInfoWindowBeforeCloseHandler(
      final MarkerInfoWindowBeforeCloseHandler handler) {
    maybeInitMarkerInfoWindowBeforeCloseHandlers();

    markerInfoWindowBeforeCloseHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MarkerInfoWindowBeforeCloseEvent e = new MarkerInfoWindowBeforeCloseEvent(
            Marker.this);
        handler.onInfoWindowBeforeClose(e);
      }
    });
  }

  /**
   * This event is fired when the info window of the map that was opened through
   * this marker is closed. This happens when either the info window was closed,
   * or when it was opened on another marker, or on the map. The handler
   * {@link MarkerInfoWindowBeforeCloseHandler} is fired before this event.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMarkerInfoWindowCloseHandler(
      final MarkerInfoWindowCloseHandler handler) {
    maybeInitMarkerInfoWindowCloseHandlers();

    markerInfoWindowCloseHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MarkerInfoWindowCloseEvent e = new MarkerInfoWindowCloseEvent(
            Marker.this);
        handler.onInfoWindowClose(e);
      }
    });
  }

  /**
   * This event is fired when the info window of the map was opened through this
   * marker.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMarkerInfoWindowOpenHandler(
      final MarkerInfoWindowOpenHandler handler) {
    maybeInitMarkerInfoWindowOpenHandlers();

    markerInfoWindowOpenHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MarkerInfoWindowOpenEvent e = new MarkerInfoWindowOpenEvent(Marker.this);
        handler.onInfoWindowOpen(e);
      }
    });
  }

  /**
   * This event is fired when the DOM "mousedown" event is fired on the marker
   * icon. Notice that the marker will stop the "mousedown" DOM event, so that
   * it doesn't cause the map to start dragging.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMarkerMouseDownHandler(final MarkerMouseDownHandler handler) {
    maybeInitMarkerMouseDownHandlers();

    markerMouseDownHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MarkerMouseDownEvent e = new MarkerMouseDownEvent(Marker.this);
        handler.onMouseDown(e);
      }
    });
  }

  /**
   * This event is fired when the mouse leaves the area of the marker icon.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMarkerMouseOutHandler(final MarkerMouseOutHandler handler) {
    maybeInitMarkerMouseOutHandlers();

    markerMouseOutHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MarkerMouseOutEvent e = new MarkerMouseOutEvent(Marker.this);
        handler.onMouseOut(e);
      }
    });
  }

  /**
   * This event is fired when the mouse enters the area of the marker icon.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMarkerMouseOverHandler(final MarkerMouseOverHandler handler) {
    maybeInitMarkerMouseOverEvent();

    markerMouseOverHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MarkerMouseOverEvent e = new MarkerMouseOverEvent(Marker.this);
        handler.onMouseOver(e);
      }
    });
  }

  /**
   * This event is fired for the DOM "mouseup" on the marker. Notice that the
   * marker will not stop the "mousedown" DOM event, because it will not confuse
   * the drag handler of the map.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMarkerMouseUpHandler(final MarkerMouseUpHandler handler) {
    maybeInitMarkerMouseUpHandlers();

    markerMouseUpHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MarkerMouseUpEvent e = new MarkerMouseUpEvent(Marker.this);
        handler.onMouseUp(e);
      }
    });
  }

  /**
   * This event is fired when the marker is removed from the map, using
   * {@link com.google.gwt.maps.client.MapWidget#removeOverlay} or
   * {@link com.google.gwt.maps.client.MapWidget#clearOverlays}.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMarkerRemoveHandler(final MarkerRemoveHandler handler) {
    maybeInitMarkerRemoveHandlers();

    markerRemoveHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        MarkerRemoveEvent e = new MarkerRemoveEvent(Marker.this);
        handler.onRemove(e);
      }
    });
  }

  /**
   * This event is fired when the visibility of the marker is changed (i.e. the
   * visibility is flipped from visible to hidden or vice-versa). The
   * <code>visible</code> parameter refers to the state of the marker after the
   * visibility change has happened.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addMarkerVisibilityChangedHandler(
      final MarkerVisibilityChangedHandler handler) {
    maybeInitMarkerVisibilityChangeHandlers();

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
   * Closes the info window only if it belongs to this marker.
   */
  public void closeInfoWindow() {
    MarkerImpl.impl.closeInfoWindow(this);
  }

  /**
   * @return the current icon used for this Marker.
   */
  public Icon getIcon() {
    return MarkerImpl.impl.getIcon(this);
  }

  /**
   * Returns the geographical coordinates at which this marker is anchored, as
   * set by the constructor or by {@link #setLatLng(LatLng)}.
   * 
   * @return the geographical coordinates at which this marker is anchored.
   */
  public LatLng getLatLng() {
    return MarkerImpl.impl.getLatLng(this);
  }

  /**
   * @deprecated
   * @return the current position of this Marker.
   */
  @Deprecated
  public LatLng getPoint() {
    return MarkerImpl.impl.getPoint(this);
  }

  /**
   * Returns the title of this marker, as set by the constructor via the
   * {@link MarkerOptions#setTitle(String)} method. Returns <code>null</code> if
   * no title is passed in.
   * 
   * @return the title of this marker.
   */
  public String getTitle() {
    return MarkerImpl.impl.getTitle(this);
  }

  /**
   * See if this Marker was created as a draggable marker type, that is, the
   * draggable option was set in MarkerOptions when it was constructed.
   * 
   * @return <code>true</code> if the marker was initialized as a draggable type
   *         of marker
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
   * Returns <code>true</code> if the marker is currently visible on the map.
   * 
   * @return <code>true</code> if the marker is currently visible on the map.
   */
  public boolean isVisible() {
    return !MarkerImpl.impl.isHidden(this);
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
   * {@link Marker#addMarkerInfoWindowBeforeCloseHandler(MarkerInfoWindowBeforeCloseHandler)}
   * .
   * 
   * @param handler the handler to remove
   */
  public void removeMarkerInfoWindowBeforeCloseHandler(
      MarkerInfoWindowBeforeCloseHandler handler) {
    if (markerInfoWindowBeforeCloseHandlers != null) {
      markerInfoWindowBeforeCloseHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link Marker#addMarkerInfoWindowCloseHandler(MarkerInfoWindowCloseHandler)}
   * .
   * 
   * @param handler the handler to remove
   */
  public void removeMarkerInfoWindowCloseHandler(
      MarkerInfoWindowCloseHandler handler) {
    if (markerInfoWindowCloseHandlers != null) {
      markerInfoWindowCloseHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link Marker#addMarkerInfoWindowOpenHandler(MarkerInfoWindowOpenHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removeMarkerInfoWindowOpenHandler(
      MarkerInfoWindowOpenHandler handler) {
    if (markerInfoWindowOpenHandlers != null) {
      markerInfoWindowOpenHandlers.removeHandler(handler);
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
   * {@link Marker#addMarkerVisibilityChangedHandler(MarkerVisibilityChangedHandler)}
   * .
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
   * Allow this marker to be dragged. Note: in order for dragging to work, the
   * Marker must be created using the
   * 
   * {@link MarkerOptions#setDraggable(boolean)} option.
   * 
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
   * Sets the geographical coordinates of the point at which this marker is
   * anchored.
   * 
   * @param point the geographical coordinates at which this marker is anchored.
   */
  public void setLatLng(LatLng point) {
    MarkerImpl.impl.setLatLng(this, point);
  }

  /**
   * Move the marker to the specified point.
   * 
   * @deprecated
   * @param point position to move the marker to.
   */
  @Deprecated
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
   * Opens the map info window over the icon of the marker.
   */
  public void showMapBlowup() {
    MarkerImpl.impl.showMapBlowup(this);
  }

  /**
   * Opens the map info window over the icon of the marker. The content of the
   * info window is a closeup map around the marker position. Only options
   * <code>zoomLevel</code> and <code>mapType</code> in the InfoWindowContent
   * are applicable.
   * 
   * @param content overridden settings of <code>zoomLevel</code> or
   *          <code>mapType</code>
   */
  public void showMapBlowup(InfoWindowContent content) {
    MarkerImpl.impl.showMapBlowup(this, content.getOptions());
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(MarkerClickEvent event) {
    maybeInitMarkerClickHandlers();
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
    maybeInitMarkerDoubleClickHandlers();
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
    maybeInitDragEndHandlers();
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
    maybeInitMarkerDragHandlers();
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
    maybeInitMarkerDragStartHandlers();
    markerDragStartHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(MarkerInfoWindowBeforeCloseEvent event) {
    maybeInitMarkerInfoWindowBeforeCloseHandlers();
    markerInfoWindowBeforeCloseHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(MarkerInfoWindowCloseEvent event) {
    maybeInitMarkerInfoWindowCloseHandlers();
    markerInfoWindowCloseHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(MarkerInfoWindowOpenEvent event) {
    maybeInitMarkerInfoWindowOpenHandlers();
    markerInfoWindowOpenHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(MarkerMouseDownEvent event) {
    maybeInitMarkerMouseDownHandlers();
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
    maybeInitMarkerMouseOutHandlers();
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
    maybeInitMarkerMouseOverEvent();
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
    maybeInitMarkerMouseUpHandlers();
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
    maybeInitMarkerRemoveHandlers();
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
    maybeInitMarkerVisibilityChangeHandlers();
    markerVisibilityChangedHandlers.trigger(event.isVisible());
  }

  /**
   * Lazy init the HandlerCollection.
   */
  private void maybeInitDragEndHandlers() {
    if (markerDragEndHandlers == null) {
      markerDragEndHandlers = new HandlerCollection<MarkerDragEndHandler>(
          jsoPeer, MapEvent.DRAGEND);
    }
  }

  /**
   * Lazy init the HandlerCollection.
   */
  private void maybeInitMarkerClickHandlers() {
    if (markerClickHandlers == null) {
      markerClickHandlers = new HandlerCollection<MarkerClickHandler>(jsoPeer,
          MapEvent.CLICK);
    }
  }

  /**
   * Lazy init the HandlerCollection.
   */
  private void maybeInitMarkerDoubleClickHandlers() {
    if (markerDoubleClickHandlers == null) {
      markerDoubleClickHandlers = new HandlerCollection<MarkerDoubleClickHandler>(
          jsoPeer, MapEvent.DBLCLICK);
    }
  }

  /**
   * Lazy init the HandlerCollection.
   */
  private void maybeInitMarkerDragHandlers() {
    if (markerDragHandlers == null) {
      markerDragHandlers = new HandlerCollection<MarkerDragHandler>(jsoPeer,
          MapEvent.DRAG);
    }
  }

  /**
   * Lazy init the HandlerCollection.
   */
  private void maybeInitMarkerDragStartHandlers() {
    if (markerDragStartHandlers == null) {
      markerDragStartHandlers = new HandlerCollection<MarkerDragStartHandler>(
          jsoPeer, MapEvent.DRAGSTART);
    }
  }

  /**
   * Lazy init the HandlerCollection.
   */
  private void maybeInitMarkerInfoWindowBeforeCloseHandlers() {
    if (markerInfoWindowBeforeCloseHandlers == null) {
      markerInfoWindowBeforeCloseHandlers = new HandlerCollection<MarkerInfoWindowBeforeCloseHandler>(
          jsoPeer, MapEvent.INFOWINDOWBEFORECLOSE);
    }
  }

  /**
   * Lazy init the HandlerCollection.
   */
  private void maybeInitMarkerInfoWindowCloseHandlers() {
    if (markerInfoWindowCloseHandlers == null) {
      markerInfoWindowCloseHandlers = new HandlerCollection<MarkerInfoWindowCloseHandler>(
          jsoPeer, MapEvent.INFOWINDOWCLOSE);
    }
  }

  /**
   * Lazy init the HandlerCollection.
   */
  private void maybeInitMarkerInfoWindowOpenHandlers() {
    if (markerInfoWindowOpenHandlers == null) {
      markerInfoWindowOpenHandlers = new HandlerCollection<MarkerInfoWindowOpenHandler>(
          jsoPeer, MapEvent.INFOWINDOWOPEN);
    }
  }

  /**
   * Lazy init the HandlerCollection.
   */
  private void maybeInitMarkerMouseDownHandlers() {
    if (markerMouseDownHandlers == null) {
      markerMouseDownHandlers = new HandlerCollection<MarkerMouseDownHandler>(
          jsoPeer, MapEvent.MOUSEDOWN);
    }
  }

  /**
   * Lazy init the HandlerCollection.
   */
  private void maybeInitMarkerMouseOutHandlers() {
    if (markerMouseOutHandlers == null) {
      markerMouseOutHandlers = new HandlerCollection<MarkerMouseOutHandler>(
          jsoPeer, MapEvent.MOUSEOUT);
    }
  }

  /**
   * Lazy init the HandlerCollection.
   */
  private void maybeInitMarkerMouseOverEvent() {
    if (markerMouseOverHandlers == null) {
      markerMouseOverHandlers = new HandlerCollection<MarkerMouseOverHandler>(
          jsoPeer, MapEvent.MOUSEOVER);
    }
  }

  /**
   * Lazy init the HandlerCollection.
   * 
   */
  private void maybeInitMarkerMouseUpHandlers() {
    if (markerMouseUpHandlers == null) {
      markerMouseUpHandlers = new HandlerCollection<MarkerMouseUpHandler>(
          jsoPeer, MapEvent.MOUSEUP);
    }
  }

  /**
   * Lazy init the HandlerCollection.
   */
  private void maybeInitMarkerRemoveHandlers() {
    if (markerRemoveHandlers == null) {
      markerRemoveHandlers = new HandlerCollection<MarkerRemoveHandler>(
          jsoPeer, MapEvent.REMOVE);
    }
  }

  /**
   * Lazy init the HandlerCollection.
   */
  private void maybeInitMarkerVisibilityChangeHandlers() {
    if (markerVisibilityChangedHandlers == null) {
      markerVisibilityChangedHandlers = new HandlerCollection<MarkerVisibilityChangedHandler>(
          jsoPeer, MapEvent.VISIBILITYCHANGED);
    }
  }
}

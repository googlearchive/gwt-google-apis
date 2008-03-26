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
import com.google.gwt.maps.client.event.MarkerClickListener;
import com.google.gwt.maps.client.event.MarkerDragListener;
import com.google.gwt.maps.client.event.MarkerMouseListener;
import com.google.gwt.maps.client.event.RemoveListener;
import com.google.gwt.maps.client.event.VisibilityListener;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.impl.EventImpl;
import com.google.gwt.maps.client.impl.MapEvent;
import com.google.gwt.maps.client.impl.ListenerCollection;
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
  private ListenerCollection<MarkerMouseListener> mouseListeners;
  private ListenerCollection<RemoveListener> removeListeners;
  private ListenerCollection<VisibilityListener> visibilityListeners;

  private Marker(JavaScriptObject jsoPeer) {
    super(jsoPeer);
  }

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
   * Remove all click listeners registered with this Marker.
   */
  public void clearMarkerClickListeners() {
    if (clickListeners != null) {
      clickListeners.clearListeners();
    }
  }

  /**
   * Remove all drag listeners registered with this Marker.
   */
  public void clearMarkerDragListeners() {
    if (dragListeners != null) {
      dragListeners.clearListeners();
    }
  }

  /**
   * Remove all mouse listeners registered with this Marker.
   */
  public void clearMouseListeners() {
    if (mouseListeners != null) {
      mouseListeners.clearListeners();
    }
  }

  /**
   * Remove all remove listeners registered with this Marker.
   */
  public void clearRemoveListeners() {
    if (removeListeners != null) {
      removeListeners.clearListeners();
    }
  }

  /**
   * Remove all visibility listeners registered with this Marker.
   */
  public void clearVisibilityListeners() {
    if (visibilityListeners != null) {
      visibilityListeners.clearListeners();
    }
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
 * @return <code>true</code> if the marker was initialized as a draggable type
 *         of marker
 */
  public boolean isDraggable() {
    return MarkerImpl.impl.draggable(this);
  }

/**
 * Returns <code>true</code> if this marker is not only a draggable type of marker.
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
   * @return returns <code>true</code> if the marker is currently visible on the map
   */
  public boolean isVisible() {
    return !MarkerImpl.impl.isHidden(this);
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
}

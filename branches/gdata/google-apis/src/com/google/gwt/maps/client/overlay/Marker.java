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
package com.google.gwt.maps.client.overlay;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.jsio.client.impl.Extractor;
import com.google.gwt.maps.client.event.DragListener;
import com.google.gwt.maps.client.event.MarkerClickListener;
import com.google.gwt.maps.client.event.MarkerMouseListener;
import com.google.gwt.maps.client.event.RemoveListener;
import com.google.gwt.maps.client.event.VisibilityListener;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.impl.EventImpl;
import com.google.gwt.maps.client.impl.MarkerImpl;
import com.google.gwt.maps.client.impl.EventImpl.BooleanCallback;
import com.google.gwt.maps.client.impl.EventImpl.VoidCallback;
import com.google.gwt.maps.client.overlay.Overlay.ConcreteOverlay;

/**
 * 
 */
public final class Marker extends ConcreteOverlay {

  private static final EventImpl EVENT_IMPL = EventImpl.impl;

  // TODO: DELETE ME! (needs to function w/o)
  private static final Extractor __extractor = new Extractor() {
    public Object fromJS(JavaScriptObject jso) {
      throw new UnsupportedOperationException();
    }

    public JavaScriptObject toJS(Object o) {
      return ((Marker) o).jsoPeer;
    }
  };

  static Marker createPeer(JavaScriptObject jsoPeer) {
    return new Marker(jsoPeer);
  }

  public Marker(LatLng point) {
    super(MarkerImpl.impl.construct(point));
  }

  public Marker(LatLng point, MarkerOptions options) {
    super(MarkerImpl.impl.construct(point, options));
  }

  private Marker(JavaScriptObject jsoPeer) {
    super(jsoPeer);
  }

  //
  // public Marker(LatLng point, Icon icon) {
  // }
  //
  // public Marker(LatLng point, Icon icon, String title) {
  // }
  //
  // public Marker(LatLng point, Icon icon, String title, boolean clickable,
  // boolean draggable, boolean bouncy, double bounceGravity) {
  // }

  public void addClickListener(final MarkerClickListener listener) {
    EVENT_IMPL.associate(listener, new JavaScriptObject[] {
        EVENT_IMPL.addListenerVoid(jsoPeer, "click", new VoidCallback() {
          public void callback() {
            System.out.println("called");
            listener.onClick(Marker.this);
          }
        }), EVENT_IMPL.addListenerVoid(jsoPeer, "dblclick", new VoidCallback() {
          public void callback() {
            listener.onDoubleClick(Marker.this);
          }
        })});
  }

  public void addDragListener(final DragListener listener) {
    EVENT_IMPL.associate(listener, new JavaScriptObject[] {
        EVENT_IMPL.addListenerVoid(jsoPeer, "dragstart", new VoidCallback() {
          public void callback() {
            listener.onDragStart();
          }
        }), EVENT_IMPL.addListenerVoid(jsoPeer, "drag", new VoidCallback() {
          public void callback() {
            listener.onDrag();
          }
        }), EVENT_IMPL.addListenerVoid(jsoPeer, "dragend", new VoidCallback() {
          public void callback() {
            listener.onDragEnd();
          }
        })});
  }

  public void addMouseListener(final MarkerMouseListener listener) {
    EVENT_IMPL.associate(listener, new JavaScriptObject[] {
        EVENT_IMPL.addListenerVoid(jsoPeer, "mousedown", new VoidCallback() {
          public void callback() {
            listener.onMouseDown(Marker.this);
          }
        }), EVENT_IMPL.addListenerVoid(jsoPeer, "mouseup", new VoidCallback() {
          public void callback() {
            listener.onMouseUp(Marker.this);
          }
        }),
        EVENT_IMPL.addListenerVoid(jsoPeer, "mouseover", new VoidCallback() {
          public void callback() {
            listener.onMouseOver(Marker.this);
          }
        }), EVENT_IMPL.addListenerVoid(jsoPeer, "mouseout", new VoidCallback() {
          public void callback() {
            listener.onMouseOut(Marker.this);
          }
        })});
  }

  // TODO: dragging, draggable

  public void addRemoveListener(final RemoveListener listener) {
    EVENT_IMPL.associate(listener, EVENT_IMPL.addListenerVoid(jsoPeer,
        "remove", new VoidCallback() {
          public void callback() {
            listener.onRemove(Marker.this);
          }
        }));
  }

  public void addVisibilityListener(final VisibilityListener listener) {
    EVENT_IMPL.associate(listener, EVENT_IMPL.addListener(jsoPeer,
        "visibilitychanged", new BooleanCallback() {
          public void callback(boolean isVisible) {
            listener.onVisibilityChanged(Marker.this, isVisible);
          }
        }));
  }

  public void clearClickListeners() {
    EventImpl.impl.clearListeners(jsoPeer, "click");
    EventImpl.impl.clearListeners(jsoPeer, "dblclick");
  }

  public void clearDragListeners() {
    EventImpl.impl.clearListeners(jsoPeer, "dragstart");
    EventImpl.impl.clearListeners(jsoPeer, "drag");
    EventImpl.impl.clearListeners(jsoPeer, "dragend");
  }

  public void clearMouseListeners() {
    EventImpl.impl.clearListeners(jsoPeer, "mousedown");
    EventImpl.impl.clearListeners(jsoPeer, "mouseup");
    EventImpl.impl.clearListeners(jsoPeer, "mouseover");
    EventImpl.impl.clearListeners(jsoPeer, "mouseout");
  }

  public void clearRemoveListeners() {
    EventImpl.impl.clearListeners(jsoPeer, "remove");
  }

  public void clearVisibilityListeners() {
    EventImpl.impl.clearListeners(jsoPeer, "visibilitychanged");
  }

  public Icon getIcon() {
    return MarkerImpl.impl.getIcon(this);
  }

  public LatLng getPoint() {
    return MarkerImpl.impl.getPoint(this);
  }

  public boolean isVisible() {
    return !MarkerImpl.impl.isHidden(this);
  }

  // TODO: figure out info window stuff (events)

  public void removeClickListener(MarkerClickListener listener) {
    EVENT_IMPL.removeListeners(listener);
  }

  public void removeDragListener(DragListener listener) {
    EVENT_IMPL.removeListeners(listener);
  }

  public void removeMouseListener(MarkerMouseListener listener) {
    EVENT_IMPL.removeListeners(listener);
  }

  public void removeRemoveListener(RemoveListener listener) {
    EVENT_IMPL.removeListeners(listener);
  }

  public void removeVisibilityListener(VisibilityListener listener) {
    EVENT_IMPL.removeListeners(listener);
  }

  public void setImage(String url) {
    MarkerImpl.impl.setImage(this, url);
  }

  public void setPoint(LatLng point) {
    MarkerImpl.impl.setPoint(this, point);
  }

  public void setVisible(boolean visible) {
    if (visible) {
      MarkerImpl.impl.show(this);
    } else {
      MarkerImpl.impl.hide(this);
    }
  }

}

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
import com.google.gwt.maps.client.MapPane.MapPaneType;
import com.google.gwt.maps.client.control.Control;
import com.google.gwt.maps.client.control.ControlPosition;
import com.google.gwt.maps.client.event.DragListener;
import com.google.gwt.maps.client.event.InfoWindowListener;
import com.google.gwt.maps.client.event.MapClickListener;
import com.google.gwt.maps.client.event.MapMouseListener;
import com.google.gwt.maps.client.event.MapMoveListener;
import com.google.gwt.maps.client.event.MapTypeListener;
import com.google.gwt.maps.client.event.MapZoomListener;
import com.google.gwt.maps.client.event.OverlayListener;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.LatLngBounds;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.client.geom.Size;
import com.google.gwt.maps.client.impl.EventImpl;
import com.google.gwt.maps.client.impl.MapEvent;
import com.google.gwt.maps.client.impl.MapImpl;
import com.google.gwt.maps.client.impl.MapOptionsImpl;
import com.google.gwt.maps.client.impl.EventImpl.IntIntCallback;
import com.google.gwt.maps.client.impl.EventImpl.LatLngCallback;
import com.google.gwt.maps.client.impl.EventImpl.MapTypeCallback;
import com.google.gwt.maps.client.impl.EventImpl.OverlayCallback;
import com.google.gwt.maps.client.impl.EventImpl.OverlayLatLngCallback;
import com.google.gwt.maps.client.impl.EventImpl.VoidCallback;
import com.google.gwt.maps.client.impl.EventImpl.PointElementOverlayCallback;
import com.google.gwt.maps.client.overlay.Overlay;
import com.google.gwt.maps.client.util.JsUtil;
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

  /**
   * Reference to GMap2 object.
   */
  private final JavaScriptObject jsoPeer;

  private final MapPanel mapContainer = new MapPanel();

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

  public void addClickListener(final MapClickListener listener) {
    
    EventImpl.impl.associate(listener, EventImpl.impl.addListener(jsoPeer,
        MapEvent.CLICK, new OverlayLatLngCallback() {
          @Override
          public void callback(Overlay overlay, LatLng latlng) {
            listener.onClick(MapWidget.this, overlay, latlng);
          }         
        }));
    EventImpl.impl.associate(listener, EventImpl.impl.addListener(jsoPeer,
        MapEvent.DBLCLICK, new OverlayLatLngCallback() {
      @Override
      public void callback(Overlay overlay, LatLng latlng) {
        listener.onDoubleClick(MapWidget.this, overlay, latlng);
      }     
    }));
    EventImpl.impl.associate(listener, EventImpl.impl.addListener(jsoPeer,
        MapEvent.SINGLERIGHTCLICK, new PointElementOverlayCallback() {
      @Override
      public void callback(Point point, Element element,
          Overlay overlay) {
        listener.onRightClick(MapWidget.this, point, element, overlay);
      }     
    }));
  }

  /**
   * Adds a control to the map. The default position of the control is used, as
   * determined by {@link Control#getDefaultPosition()}. A control instance
   * cannot be added more than once to the map.
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
   * control to the map, subclass {@link CustomControl} and implement the
   * {@link CustomControl#initialize(Map)} method.
   * 
   * @param w the control widget to add to the map
   */
  public void addControlWidget(Widget w) {
    mapContainer.add(w);
  }

  public void addDragListener(final DragListener listener) {
    EventImpl.impl.associate(listener, new JavaScriptObject[] {
        EventImpl.impl.addListenerVoid(jsoPeer, MapEvent.DRAGSTART,
            new VoidCallback() {
              @Override
              public void callback() {
                listener.onDragStart();
              }
            }),
        EventImpl.impl.addListenerVoid(jsoPeer, MapEvent.DRAG,
            new VoidCallback() {
              @Override
              public void callback() {
                listener.onDrag();
              }
            }),
        EventImpl.impl.addListenerVoid(jsoPeer, MapEvent.DRAGEND,
            new VoidCallback() {
              @Override
              public void callback() {
                listener.onDragEnd();
              }
            })});
  }

  public void addInfoWindowListener(final InfoWindowListener listener) {
    EventImpl.impl.associate(listener, new JavaScriptObject[] {
        EventImpl.impl.addListenerVoid(jsoPeer,
            MapEvent.INFOWINDOWOPEN, new VoidCallback() {
              @Override
              public void callback() {
                listener.onInfoWindowOpened(MapWidget.this);
              }
            }),
        EventImpl.impl.addListenerVoid(jsoPeer,
            MapEvent.INFOWINDOWCLOSE, new VoidCallback() {
              @Override
              public void callback() {
                listener.onInfoWindowClosed(MapWidget.this);
              }
            })});
  }

  public void addMapMouseListener(final MapMouseListener listener) {
    EventImpl.impl.associate(listener, new JavaScriptObject[] {
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
            })});
  }

  public void addMapMoveListener(final MapMoveListener listener) {
    EventImpl.impl.associate(listener, new JavaScriptObject[] {
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
            })});
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

  public void addMapTypeListener(final MapTypeListener listener) {
    EventImpl.impl.associate(listener, new JavaScriptObject[] {
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
        EventImpl.impl.addListenerVoid(jsoPeer,
            MapEvent.MAPTYPECHANGED, new VoidCallback() {
              @Override
              public void callback() {
                listener.onMapTypeChanged(MapWidget.this);
              }
            })});
  }

  public void addMapZoomListener(final MapZoomListener listener) {
    EventImpl.impl.associate(listener, EventImpl.impl.addListener(jsoPeer,
        MapEvent.ZOOMEND, new IntIntCallback() {
          @Override
          public void callback(int oldLevel, int newLevel) {
            listener.onZoom(MapWidget.this, oldLevel, newLevel);
          }
        }));
  }

  /**
   * Adds an overlay to the map and fires the "addoverlay" event.
   * 
   * @param overlay
   */
  public void addOverlay(Overlay overlay) {
    MapImpl.impl.addOverlay(jsoPeer, overlay);
  }

  public void addOverlayListener(final OverlayListener listener) {
    EventImpl.impl.associate(listener, new JavaScriptObject[] {
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
            })});
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

  public void clearDragListeners() {
    EventImpl.impl.clearListeners(jsoPeer, MapEvent.DRAGSTART);
    EventImpl.impl.clearListeners(jsoPeer, MapEvent.DRAG);
    EventImpl.impl.clearListeners(jsoPeer, MapEvent.DRAGEND);
  }

  public void clearInfoWindowListeners() {
    EventImpl.impl.clearListeners(jsoPeer, MapEvent.INFOWINDOWOPEN);
    EventImpl.impl.clearListeners(jsoPeer, MapEvent.INFOWINDOWCLOSE);
  }

  public void clearMapClickListeners() {
    EventImpl.impl.clearListeners(jsoPeer, MapEvent.CLICK);
  }

  public void clearMapMouseListeners() {
    EventImpl.impl.clearListeners(jsoPeer, MapEvent.MOUSEOVER);
    EventImpl.impl.clearListeners(jsoPeer, MapEvent.MOUSEOUT);
    EventImpl.impl.clearListeners(jsoPeer, MapEvent.MOUSEMOVE);
  }

  public void clearMapMoveListeners() {
    EventImpl.impl.clearListeners(jsoPeer, MapEvent.MOVE);
    EventImpl.impl.clearListeners(jsoPeer, MapEvent.MOVESTART);
    EventImpl.impl.clearListeners(jsoPeer, MapEvent.MOVEEND);
  }

  public void clearMapTypeListeners() {
    EventImpl.impl.clearListeners(jsoPeer, MapEvent.ADDMAPTYPE);
    EventImpl.impl.clearListeners(jsoPeer, MapEvent.REMOVEMAPTYPE);
    EventImpl.impl.clearListeners(jsoPeer, MapEvent.MAPTYPECHANGED);
  }

  public void clearMapZoomListeners() {
    EventImpl.impl.clearListeners(jsoPeer, MapEvent.ZOOMEND);
  }

  public void clearOverlayListeners() {
    EventImpl.impl.clearListeners(jsoPeer, MapEvent.ADDOVERLAY);
    EventImpl.impl.clearListeners(jsoPeer, MapEvent.REMOVEOVERLAY);
    EventImpl.impl.clearListeners(jsoPeer, MapEvent.CLEAROVERLAYS);
  }

  /**
   * Removes all overlay from the map, and fires the "clearoverlays" event.
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
   * @return the zoom level at wich the bounds fit in the map view.
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
   * TODO: assign the info window to an instance field so that there is only one
   * instance per map.
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
    new ControlPosition(ControlPosition.BOTTOM_LEFT, 0, 0);
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
  // TODO: this should probably be related to the size of the widget
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
   * Removes the given drag listener from the map.
   * 
   * @param listener the drag listener to remove
   */
  public void removeDragListener(DragListener listener) {
    EventImpl.impl.removeListeners(listener);
  }

  public void removeInfoWindowListener(InfoWindowListener listener) {
    EventImpl.impl.removeListeners(listener);
  }

  public void removeMapClickListener(MapClickListener listener) {
    EventImpl.impl.removeListeners(listener);
  }

  public void removeMapMouseListener(MapMouseListener listener) {
    EventImpl.impl.removeListeners(listener);
  }

  public void removeMapMoveListener(MapMoveListener listener) {
    EventImpl.impl.removeListeners(listener);
  }

  /**
   * Removes the map type from the map.
   * 
   * If the current map type is removed, the map will switch to the first map
   * type. The last remaining map type cannot be removed.
   * 
   * This method will update the set of buttons displayed by the
   * {@link Control} and will fire the "removemaptype" event.
   */
  public void removeMapType(MapType type) {
    MapImpl.impl.removeMapType(jsoPeer, type);
  }

  public void removeMapTypeListener(MapTypeListener listener) {
    EventImpl.impl.removeListeners(listener);
  }

  public void removeMapZoomListener(MapZoomListener listener) {
    EventImpl.impl.removeListeners(listener);
  }

  /**
   * Removes the overlay from the map. If the overlay was on the map, it fires
   * the "removeoverlay" event.
   * 
   * @param overlay the overlay to remove from the map
   */
  public void removeOverlay(Overlay overlay) {
    MapImpl.impl.removeOverlay(jsoPeer, overlay);
  }

  public void removeOverlayListener(OverlayListener listener) {
    EventImpl.impl.removeListeners(listener);
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
}

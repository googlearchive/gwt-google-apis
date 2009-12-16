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

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.maps.client.event.PolygonCancelLineHandler;
import com.google.gwt.maps.client.event.PolygonClickHandler;
import com.google.gwt.maps.client.event.PolygonEndLineHandler;
import com.google.gwt.maps.client.event.PolygonLineUpdatedHandler;
import com.google.gwt.maps.client.event.PolygonMouseOutHandler;
import com.google.gwt.maps.client.event.PolygonMouseOverHandler;
import com.google.gwt.maps.client.event.PolygonRemoveHandler;
import com.google.gwt.maps.client.event.PolygonVisibilityChangedHandler;
import com.google.gwt.maps.client.event.PolygonCancelLineHandler.PolygonCancelLineEvent;
import com.google.gwt.maps.client.event.PolygonClickHandler.PolygonClickEvent;
import com.google.gwt.maps.client.event.PolygonEndLineHandler.PolygonEndLineEvent;
import com.google.gwt.maps.client.event.PolygonLineUpdatedHandler.PolygonLineUpdatedEvent;
import com.google.gwt.maps.client.event.PolygonMouseOutHandler.PolygonMouseOutEvent;
import com.google.gwt.maps.client.event.PolygonMouseOverHandler.PolygonMouseOverEvent;
import com.google.gwt.maps.client.event.PolygonRemoveHandler.PolygonRemoveEvent;
import com.google.gwt.maps.client.event.PolygonVisibilityChangedHandler.PolygonVisibilityChangedEvent;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.LatLngBounds;
import com.google.gwt.maps.client.impl.HandlerCollection;
import com.google.gwt.maps.client.impl.MapEvent;
import com.google.gwt.maps.client.impl.PolygonImpl;
import com.google.gwt.maps.client.impl.EventImpl.BooleanCallback;
import com.google.gwt.maps.client.impl.EventImpl.LatLngCallback;
import com.google.gwt.maps.client.impl.EventImpl.VoidCallback;
import com.google.gwt.maps.client.overlay.Overlay.ConcreteOverlay;

/**
 * This is a map overlay that draws a polygon on the map, using the vector
 * drawing facilities of the browser if they are available, or an image overlay
 * from Google servers otherwise. This is very similar to a
 * {@link com.google.gwt.maps.client.overlay.Polyline}, except that you can
 * additionally specify a fill color and opacity.
 */
public class Polygon extends ConcreteOverlay {

  /**
   * Used to create a new Polygon by wrapping an existing GPolygon object. This
   * method is invoked by the JSIO library.
   * 
   * @param jsoPeer GPolygon object to wrap.
   * @return a new instance of Polygon.
   */
  public static Polygon createPeer(JavaScriptObject jsoPeer) {
    return new Polygon(jsoPeer);
  }

  /**
   * Create a polygon from an array of polylines. Overlapping regions of the
   * polygons will be transparent.
   * 
   * @param polylines array of polylines to use as the outline for the polygon.
   * @return a new instance of Polygon.
   */
  public static Polygon fromEncoded(EncodedPolyline[] polylines) {
    return new Polygon(nativeFromEncoded(toJsArray(polylines)));
  }

  /**
   * Create a polygon from an array of polylines. Overlapping regions of the
   * polygons will be transparent.
   * 
   * @param polylines array of polylines to use as the outline for the polygon.
   * @param fill whether to fill in the polygon with the specified color.
   * @param color the color to use for the fill.
   * @param opacity Opacity to use for the fill.
   * @param outline <code>true</code>
   * @return a new instance of Polygon.
   */
  public static Polygon fromEncoded(EncodedPolyline[] polylines, boolean fill,
      String color, double opacity, boolean outline) {
    return new Polygon(nativeFromEncoded(toJsArray(polylines), fill, color,
        opacity, outline));
  }

  /**
   * This method is a little trick we can use in WebMode. EncodedPolyline is a
   * JSO subclass, and a JS array is really an object, so this JSNI method
   * basically just casts the array to a JSO. In Java things are different, so
   * this trick doesn't work - be sure to surround with a GWT.isScript() test.
   * 
   * @param array The array to pass into JavaScript.
   * @return a JsArray representing the input argument.
   */
  private static native JsArray<EncodedPolyline> nativeArrayToJavaScriptObject(
      EncodedPolyline[] array) /*-{
    return array;
  }-*/;

  private static native JavaScriptObject nativeFromEncoded(
      JsArray<EncodedPolyline> polylinesIn) /*-{
    return new $wnd.GPolygon.fromEncoded({polylines: polylinesIn});
  }-*/;

  private static native JavaScriptObject nativeFromEncoded(
      JsArray<EncodedPolyline> polylinesIn, boolean fillIn, String colorIn,
      double opacityIn, boolean outlineIn) /*-{
    return new $wnd.GPolygon.fromEncoded({polylines: polylinesIn, fill: fillIn, color: colorIn, opacity: opacityIn, outline: outlineIn});
  }-*/;

  @SuppressWarnings("unchecked")
  private static JsArray<EncodedPolyline> toJsArray(EncodedPolyline[] array) {
    if (GWT.isScript()) {
      // This is the most efficient thing to do, and works in web mode
      return nativeArrayToJavaScriptObject(array);
    }

    // This is a workaround for hosted mode. Make a copy of the array.
    JsArray<EncodedPolyline> result = (JsArray<EncodedPolyline>) JavaScriptObject.createArray();
    for (int i = 0; i < array.length; ++i) {
      result.set(i, array[i]);
    }
    assert (array.length == result.length());
    return result;
  }

  private HandlerCollection<PolygonCancelLineHandler> polygonCancelLineHandlers;
  private HandlerCollection<PolygonClickHandler> polygonClickHandlers;
  private HandlerCollection<PolygonEndLineHandler> polygonEndLineHandlers;
  private HandlerCollection<PolygonLineUpdatedHandler> polygonLineUpdatedHandlers;
  private HandlerCollection<PolygonMouseOutHandler> polygonMouseOutHandlers;
  private HandlerCollection<PolygonMouseOverHandler> polygonMouseOverHandlers;
  private HandlerCollection<PolygonRemoveHandler> polygonRemoveHandlers;
  private HandlerCollection<PolygonVisibilityChangedHandler> polygonVisibilityChangedHandlers;

  /**
   * Create a Polygon from an array of points.
   * 
   * @param points the points to construct the polygon.
   */
  public Polygon(LatLng[] points) {
    super(PolygonImpl.impl.construct(LatLng.toJsArray(points)));
  }

  /**
   * Create a polygon from an array of points, specifying optional parameters.
   * 
   * @param points the points to construct the polygon.
   * @param strokeColor The line color, a string that contains the color in
   *          hexadecimal numeric HTML style, i.e. #RRGGBB.
   * @param strokeWeight The width of the line in pixels.
   * @param strokeOpacity The opacity of the line - a value between 0.0 and 1.0.
   * @param fillColor The fill color, a string that contains the color in
   *          hexadecimal numeric HTML style, i.e. #RRGGBB.
   * @param fillOpacity The opacity of the fill - a value between 0.0 and 1.0.
   */
  public Polygon(LatLng[] points, String strokeColor, int strokeWeight,
      double strokeOpacity, String fillColor, double fillOpacity) {
    super(PolygonImpl.impl.construct(LatLng.toJsArray(points), strokeColor,
        strokeWeight, strokeOpacity, fillColor, fillOpacity));
  }

  /**
   * Create a polygon from an array of points, specifying optional parameters.
   * 
   * @param points the points to construct the polygon.
   * @param strokeColor The line color, a string that contains the color in
   *          hexadecimal numeric HTML style, i.e. #RRGGBB.
   * @param strokeWeight The width of the line in pixels.
   * @param strokeOpacity The opacity of the line - a value between 0.0 and 1.0.
   * @param fillColor The fill color, a string that contains the color in
   *          hexadecimal numeric HTML style, i.e. #RRGGBB.
   * @param fillOpacity The opacity of the fill - a value between 0.0 and 1.0.
   * @param options additional options
   */
  public Polygon(LatLng[] points, String strokeColor, int strokeWeight,
      double strokeOpacity, String fillColor, double fillOpacity,
      PolygonOptions options) {
    super(PolygonImpl.impl.construct(LatLng.toJsArray(points), strokeColor,
        strokeWeight, strokeOpacity, fillColor, fillOpacity, options));
  }
  
  /**
   * Create this polygon from an existing JavaScriptObject instance.
   * 
   * @param jsoPeer an existing JavaScriptObject instance.
   */
  protected Polygon(JavaScriptObject jsoPeer) {
    super(jsoPeer);
  }

  /**
   * This event is fired when the polygon is being edited and the edit is
   * canceled. See {@link Polygon#setEditingEnabled(boolean)}
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addPolygonCancelLineHandler(final PolygonCancelLineHandler handler) {
    maybeInitPolygonCancelLineHandlers();

    polygonCancelLineHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        PolygonCancelLineEvent e = new PolygonCancelLineEvent(Polygon.this);
        handler.onCancel(e);
      }
    });
  }

  /**
   * This event is fired when the polygon is clicked. Note that this event also
   * subsequently triggers a "click" event on the map, where the polygon is
   * passed as the overlay argument within that event.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addPolygonClickHandler(final PolygonClickHandler handler) {
    maybeInitPolygonClickHandlers();

    polygonClickHandlers.addHandler(handler, new LatLngCallback() {
      @Override
      public void callback(LatLng latlng) {
        PolygonClickEvent e = new PolygonClickEvent(Polygon.this, latlng);
        handler.onClick(e);
      }
    });
  }

  /**
   * This event is fired when the polygon is being edited and the edit is
   * completed. See {@link Polygon#setEditingEnabled(boolean)}
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addPolygonEndLineHandler(final PolygonEndLineHandler handler) {
    maybeInitPolygonEndLineHandlers();

    polygonEndLineHandlers.addHandler(handler, new LatLngCallback() {
      @Override
      public void callback(LatLng latlng) {
        PolygonEndLineEvent e = new PolygonEndLineEvent(Polygon.this, latlng);
        handler.onEnd(e);
      }
    });
  }

  /**
   * This event is fired when the polygon has a vertex inserted. See
   * {@link Polygon#insertVertex(int,LatLng)}
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addPolygonLineUpdatedHandler(
      final PolygonLineUpdatedHandler handler) {
    maybeInitPolygonLineUpdatedHandlers();

    polygonLineUpdatedHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        PolygonLineUpdatedEvent e = new PolygonLineUpdatedEvent(Polygon.this);
        handler.onUpdate(e);
      }
    });
  }

  /**
   * This event is fired when the mouse moves out of a polygon.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addPolygonMouseOutHandler(final PolygonMouseOutHandler handler) {
    maybeInitPolygonMouseOutHandlers();

    polygonMouseOutHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        PolygonMouseOutEvent e = new PolygonMouseOutEvent(Polygon.this);
        handler.onMouseOut(e);
      }
    });
  }

  /**
   * This event is fired when the mouse moves over a polygon.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addPolygonMouseOverHandler(final PolygonMouseOverHandler handler) {
    maybeInitPolygonMouseOverHandlers();

    polygonMouseOverHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        PolygonMouseOverEvent e = new PolygonMouseOverEvent(Polygon.this);
        handler.onMouseOver(e);
      }
    });
  }

  /**
   * This event is fired when the polygon is removed from the map, using
   * {@link com.google.gwt.maps.client.MapWidget#removeOverlay} or
   * {@link com.google.gwt.maps.client.MapWidget#clearOverlays}.
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addPolygonRemoveHandler(final PolygonRemoveHandler handler) {
    maybeInitPolygonRemoveHandlers();

    polygonRemoveHandlers.addHandler(handler, new VoidCallback() {
      @Override
      public void callback() {
        PolygonRemoveEvent e = new PolygonRemoveEvent(Polygon.this);
        handler.onRemove(e);
      }
    });
  }

  /**
   * This event is fired when the polygon is clicked. Note that this event also
   * subsequently triggers a "click" event on the map, where the polygon is
   * passed as the overlay argument within that event
   * 
   * @param handler the handler to call when this event fires.
   */
  public void addPolygonVisibilityChangedHandler(
      final PolygonVisibilityChangedHandler handler) {
    maybeInitPolygonVisibilityChangedHandlers();

    polygonVisibilityChangedHandlers.addHandler(handler, new BooleanCallback() {
      @Override
      public void callback(boolean visible) {
        PolygonVisibilityChangedEvent e = new PolygonVisibilityChangedEvent(
            Polygon.this, visible);
        handler.onVisibilityChanged(e);
      }
    });
  }

  /**
   * Removes with the given index in the polygon and updates the shape of the
   * polygon accordingly. The Polygon must already be added to the map via
   * {@link com.google.gwt.maps.client.MapWidget#addOverlay(Overlay)}.
   * 
   * @param index the index of the vertex to remove.
   */
  public void deleteVertex(int index) {
    PolygonImpl.impl.deleteVertex(jsoPeer, index);
  }

  /**
   * Returns the area (in square meters) of the polygon, assuming a spherical
   * Earth.
   * 
   * @return the area (in square meters) of the polygon, assuming a spherical
   *         Earth.
   */
  public double getArea() {
    return PolygonImpl.impl.getArea(jsoPeer);
  }

  /**
   * Returns the bounds for this polygon.
   * 
   * @return the bounds for this polygon.
   */
  public LatLngBounds getBounds() {
    return PolygonImpl.impl.getBounds(jsoPeer);
  }

  /**
   * Returns the position of the specified vertex in the polygon.
   * 
   * @param index the vertex to return.
   * @return the position of the specified vertex in the polygon.
   */
  public LatLng getVertex(int index) {
    return PolygonImpl.impl.getVertex(jsoPeer, index);
  }

  /**
   * Returns the number of vertices in the polygon.
   * 
   * @return the number of vertices in the polygon.
   */
  public int getVertexCount() {
    return PolygonImpl.impl.getVertexCount(jsoPeer);
  }

  /**
   * Inserts a new point at the given index in the polygon. The {@link Polygon}
   * must already be added to the map via
   * {@link com.google.gwt.maps.client.MapWidget#addOverlay(Overlay)}
   * 
   * @param index position in the polygon to insert the new point.
   * @param latlng point to insert into the polygon.
   */
  public void insertVertex(int index, LatLng latlng) {
    PolygonImpl.impl.insertVertex(jsoPeer, index, latlng);
  }

  /**
   * Returns true if the polygon is visible on the map.
   * 
   * @return true if the polygon is visible on the map.
   */
  public boolean isVisible() {
    return !PolygonImpl.impl.isHidden(jsoPeer);
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link Polygon#addPolygonCancelLineHandler(PolygonCancelLineHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removePolygonCancelLineHandler(PolygonCancelLineHandler handler) {
    if (polygonCancelLineHandlers != null) {
      polygonCancelLineHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link Polygon#addPolygonClickHandler(PolygonClickHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removePolygonClickHandler(PolygonClickHandler handler) {
    if (polygonClickHandlers != null) {
      polygonClickHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link Polygon#addPolygonEndLineHandler(PolygonEndLineHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removePolygonEndLineHandler(PolygonEndLineHandler handler) {
    if (polygonEndLineHandlers != null) {
      polygonEndLineHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link Polygon#addPolygonLineUpdatedHandler(PolygonLineUpdatedHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removePolygonLineUpdatedHandler(PolygonLineUpdatedHandler handler) {
    if (polygonLineUpdatedHandlers != null) {
      polygonLineUpdatedHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link Polygon#addPolygonMouseOutHandler(PolygonMouseOutHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removePolygonMouseOutHandler(PolygonMouseOutHandler handler) {
    if (polygonMouseOutHandlers != null) {
      polygonMouseOutHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link Polygon#addPolygonMouseOverHandler(PolygonMouseOverHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removePolygonMouseOverHandler(PolygonMouseOverHandler handler) {
    if (polygonMouseOverHandlers != null) {
      polygonMouseOverHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link Polygon#addPolygonRemoveHandler(PolygonRemoveHandler)}.
   * 
   * @param handler the handler to remove
   */
  public void removePolygonRemoveHandler(PolygonRemoveHandler handler) {
    if (polygonRemoveHandlers != null) {
      polygonRemoveHandlers.removeHandler(handler);
    }
  }

  /**
   * Removes a single handler of this map previously added with
   * {@link Polygon#addPolygonVisibilityChangedHandler(PolygonVisibilityChangedHandler)}
   * .
   * 
   * @param handler the handler to remove
   */
  public void removePolygonVisibilityChangedHandler(
      PolygonVisibilityChangedHandler handler) {
    if (polygonVisibilityChangedHandlers != null) {
      polygonVisibilityChangedHandlers.removeHandler(handler);
    }
  }

  /**
   * Allows a user to construct (or modify) a {@link Polygon} object by clicking
   * on additional points on the map. The {@link Polygon} must already be added
   * to the map via
   * {@link com.google.gwt.maps.client.MapWidget#addOverlay(Overlay)}, even if
   * the polygon is initially unpopulated and contains no vertices. Each click
   * adds an additional vertex to the chain, and drawing may be terminated
   * through either a double-click or clicking again on the last point added, at
   * which point an {@link PolygonEndLineEvent} event will be triggered if the
   * polygon was successfully completed; otherwise, a
   * {@link PolygonCancelLineEvent} event will be triggered, but the polygon
   * will not be removed from the map. If modifying an existing {@link Polygon},
   * vertices are connected from either the starting or ending points of the
   * existing polygon, specified in the optional {link
   * {@link PolyEditingOptions#setFromStart(boolean)}.
   */
  public void setDrawingEnabled() {
    PolygonImpl.impl.enableDrawing(jsoPeer);
  }

  /**
   * Enable drawing as in {@link Polygon#setDrawingEnabled()} but with control
   * over the polygon drawing parameters.
   * 
   * @param opts parameters for the polygon editing session.
   */
  public void setDrawingEnabled(PolyEditingOptions opts) {
    PolygonImpl.impl.enableDrawing(jsoPeer, opts);
  }

  /**
   * Allows modification of an existing {@link Polygon} chain of points. When
   * enabled, users may select and drag existing vertices. Unless a vertex limit
   * less than current number of vertices is specified by
   * {@link PolyEditingOptions#setMaxVertices(int)}, "ghost" points will also be
   * added at the midpoints of polyline sections, allowing users to interpolate
   * new vertices by clicking and dragging these additional vertices. A
   * {@link PolygonLineUpdatedEvent} event will be triggered whenever vertex is
   * added or moved.
   * 
   * Note, you must add the polyline to the map before enabling editing.
   * 
   * @param enabled <code>true</code> to turn on editing of this polyline.
   */
  public void setEditingEnabled(boolean enabled) {
    if (enabled) {
      PolygonImpl.impl.enableEditing(jsoPeer);
    } else {
      PolygonImpl.impl.disableEditing(jsoPeer);
    }
  }

  /**
   * Enable editing as in {@link Polygon#setEditingEnabled(boolean)}, but with
   * control over the drawing parameters.
   * 
   * Note, you must add the polyline to the map before enabling editing.
   * 
   * @param opts parameters for the editing session.
   */
  public void setEditingEnabled(PolyEditingOptions opts) {
    PolygonImpl.impl.enableEditing(jsoPeer, opts);
  }

  /**
   * Changes the style of the polygon fill. The {@link Polygon} must already be
   * added to the map via
   * {@link com.google.gwt.maps.client.MapWidget#addOverlay(Overlay)}
   * 
   * @param style options for drawing the polygon fill.
   */
  public void setFillStyle(PolyStyleOptions style) {
    PolygonImpl.impl.setFillStyle(jsoPeer, style);
  }

  /**
   * Changes the style of the polylgon outline. The {@link Polygon} must already
   * be added to the map via
   * {@link com.google.gwt.maps.client.MapWidget#addOverlay(Overlay)}
   * 
   * @param style options for drawing the polygon outline.
   */
  public void setStrokeStyle(PolyStyleOptions style) {
    PolygonImpl.impl.setStrokeStyle(jsoPeer, style);
  }

  /**
   * Show or hide the polygon.
   * 
   * @param visible true to show the polygon.
   */
  public void setVisible(boolean visible) {
    if (visible) {
      PolygonImpl.impl.show(jsoPeer);
    } else {
      PolygonImpl.impl.hide(jsoPeer);
    }
  }

  /**
   * Returns <code>true</code> if this environment supports the
   * {@link Polygon#setVisible(boolean)} method.
   * 
   * @return true if setVisible(<code>false</code>) is supported.
   */
  public boolean supportsHide() {
    return PolygonImpl.impl.supportsHide(jsoPeer);
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(PolygonCancelLineEvent event) {
    maybeInitPolygonCancelLineHandlers();
    polygonCancelLineHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(PolygonClickEvent event) {
    maybeInitPolygonClickHandlers();
    polygonRemoveHandlers.trigger(event.getLatLng());
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(PolygonEndLineEvent event) {
    maybeInitPolygonEndLineHandlers();
    polygonEndLineHandlers.trigger(event.getLatLng());
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(PolygonLineUpdatedEvent event) {
    maybeInitPolygonLineUpdatedHandlers();
    polygonLineUpdatedHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(PolygonMouseOutEvent event) {
    maybeInitPolygonMouseOutHandlers();
    polygonMouseOutHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(PolygonMouseOverEvent event) {
    maybeInitPolygonMouseOverHandlers();
    polygonMouseOverHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(PolygonRemoveEvent event) {
    maybeInitPolygonRemoveHandlers();
    polygonRemoveHandlers.trigger();
  }

  /**
   * Manually trigger the specified event on this object.
   * 
   * Note: The trigger() methods are provided for unit testing purposes only.
   * 
   * @param event an event to deliver to the handler.
   */
  void trigger(PolygonVisibilityChangedEvent event) {
    maybeInitPolygonVisibilityChangedHandlers();
    polygonVisibilityChangedHandlers.trigger();
  }

  private void maybeInitPolygonCancelLineHandlers() {
    if (polygonCancelLineHandlers == null) {
      polygonCancelLineHandlers = new HandlerCollection<PolygonCancelLineHandler>(
          jsoPeer, MapEvent.CANCELLINE);
    }
  }

  private void maybeInitPolygonClickHandlers() {
    if (polygonClickHandlers == null) {
      polygonClickHandlers = new HandlerCollection<PolygonClickHandler>(
          jsoPeer, MapEvent.CLICK);
    }
  }

  private void maybeInitPolygonEndLineHandlers() {
    if (polygonEndLineHandlers == null) {
      polygonEndLineHandlers = new HandlerCollection<PolygonEndLineHandler>(
          jsoPeer, MapEvent.ENDLINE);
    }
  }

  private void maybeInitPolygonLineUpdatedHandlers() {
    if (polygonLineUpdatedHandlers == null) {
      polygonLineUpdatedHandlers = new HandlerCollection<PolygonLineUpdatedHandler>(
          jsoPeer, MapEvent.LINEUPDATED);
    }
  }

  private void maybeInitPolygonMouseOutHandlers() {
    if (polygonMouseOutHandlers == null) {
      polygonMouseOutHandlers = new HandlerCollection<PolygonMouseOutHandler>(
          jsoPeer, MapEvent.MOUSEOUT);
    }
  }

  private void maybeInitPolygonMouseOverHandlers() {
    if (polygonMouseOverHandlers == null) {
      polygonMouseOverHandlers = new HandlerCollection<PolygonMouseOverHandler>(
          jsoPeer, MapEvent.MOUSEOVER);
    }
  }

  private void maybeInitPolygonRemoveHandlers() {
    if (polygonRemoveHandlers == null) {
      polygonRemoveHandlers = new HandlerCollection<PolygonRemoveHandler>(
          jsoPeer, MapEvent.REMOVE);
    }
  }

  private void maybeInitPolygonVisibilityChangedHandlers() {
    if (polygonVisibilityChangedHandlers == null) {
      polygonVisibilityChangedHandlers = new HandlerCollection<PolygonVisibilityChangedHandler>(
          jsoPeer, MapEvent.VISIBILITYCHANGED);
    }
  }
}

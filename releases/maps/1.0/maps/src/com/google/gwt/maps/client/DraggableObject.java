/*
 * Copyright 2009 Google Inc.
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
import com.google.gwt.dom.client.Element;
import com.google.gwt.maps.client.geom.Point;

/**
 * This class makes a DOM element draggable. The static members for changing the
 * drag cursors affect all subsequently created draggable objects, such as the
 * map, zoom control slider, and overview map rectangles. The per-instance
 * members affect only their particular instance.
 * 
 * For example, before creating the map, you can call
 * DraggableObject.setDraggableCursorDefault('default') and
 * DraggableObject.setDraggingCursorDefault('move') to get the pre-API 2.56
 * style. Alternatively, the Map constructor can take options to set its
 * DraggableObject's cursor style. See the W3C CSS specification for allowable
 * cursor values.
 * 
 */
public class DraggableObject extends JavaScriptObject {

  /**
   * Returns the current dragging cursor in use by the map. If not set through
   * the static setDraggingCursor() method, this returns the default cursor used
   * by the map for its controls and markers.
   * 
   * @return the current dragging cursor.
   */
  public static native String getDraggableCursorDefault() /*-{
    return $wnd.GDraggableObject.getDraggableCursor();
  }-*/;

  /**
   * Returns the current draggable cursor in use by the map. If not set through
   * the static setDraggableCursor() method, this returns the default cursor
   * used by the map for its controls and markers.
   * 
   * @return the current draggable cursor.
   */
  public static native String getDraggingCursorDefault() /*-{
    return $wnd.GDraggableObject.getDraggingCursor();
  }-*/;

  /**
   * Sets up event handlers so that the source element can be dragged. Left and
   * top optionally position the element, and the optional container serves as a
   * bounding box.
   * 
   * @param element the element to turn into a draggable object.
   */
  public static final native DraggableObject newInstance(Element element) /*-{
    return new $wnd.GDraggableObject(element);
  }-*/;

  /**
   * Sets up event handlers so that the source element can be dragged. Left and
   * top optionally position the element, and the optional container serves as a
   * bounding box.
   * 
   * @param element the element to turn into a draggable object.
   * @param options optional parameters.
   */
  public static final native DraggableObject newInstance(Element element,
      DraggableObjectOptions options) /*-{
    return new $wnd.GDraggableObject(element, options);
  }-*/;

  /**
   * Sets the draggable cursor for subsequently created draggable objects.
   * 
   * @param cursor the draggable cursor for subsequently created draggable
   *          objects.
   */
  public static native void setDraggableCursorDefault(String cursor) /*-{
    $wnd.GDraggableObject.setDraggableCursor(cursor);
  }-*/;

  /**
   * Sets the dragging cursor for subsequently created draggable objects.
   * 
   * @param cursor the dragging cursor for subsequently created draggable
   *          objects.
   */
  public static native void setDraggingCursorDefault(String cursor) /*-{
    $wnd.GDraggableObject.setDraggingCursor(cursor);
  }-*/;

  protected DraggableObject() {
    // protected constructor required for JavaScriptObject overlays.
  }

  /**
   * Moves the DraggableObject by a given size offset. This method uses the DOM
   * coordinate system, i.e. width increases to the left, and height increases
   * downwards.
   * 
   * @param point offset to move object.
   */
  public final native void moveBy(Point point) /*-{
    this.moveBy(point);
  }-*/;

  /**
   * Moves the GDraggableObject to a given absolute position. The position is in
   * pixel coordinates relative to the parent node. This method uses the DOM
   * coordinate system, i.e. the X coordinate increases to the left, and the Y
   * coordinate increases downwards.
   * 
   * @param point absolute position to move to.
   */
  public final native void moveTo(Point point) /*-{
    this.moveTo(point);
  }-*/;

  /**
   * Sets the cursor when the mouse is over this draggable object.
   * 
   * @param cursor CSS specification of cursor type.
   */
  public final native void setDraggableCursor(String cursor) /*-{
    this.setDraggableCursor(cursor);
  }-*/;

  /**
   * Sets the cursor when the mouse is held down, dragging this draggable
   * object.
   * 
   * @param cursor CSS specification of cursor type.
   */
  public final native void setDraggingCursor(String cursor) /*-{
    this.setDraggingCursor(cursor);
  }-*/;

  // TODO(zundel): implement events
}

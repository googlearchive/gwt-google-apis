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

/**
 * This class makes a DOM element draggable. The static members for changing the
 * drag cursors affect all subsequently created draggable objects, such as the
 * map, zoom control slider, and overview map rectangles. The per-instance
 * members affect only their particular instance.
 * 
 * For example, before creating the map, you can call
 * DraggableObject.setDraggableCursor('default') and
 * DraggableObject.setDraggingCursor('move') to get the pre-API 2.56 style.
 * Alternatively, the Map constructor can take options to set its
 * DraggableObject's cursor style. See the W3C CSS specification for allowable
 * cursor values.
 * 
 */
public class DraggableObjectOptions extends JavaScriptObject {

  /**
   * Instantiate a new DraggableObjectOptions object.
   * 
   * @return a new DraggableObjectOptions object.
   */
  public static final native DraggableObjectOptions newInstance() /*-{
    // A more complex constructor works around inlining bug. See GWT issue 3568
    // http://code.google.com/p/google-web-toolkit/issues/detail?id=3568
    var obj;
    obj = new $wnd.Object();
    return obj;
  }-*/;

  protected DraggableObjectOptions() {
    // protected constructor required for JavaScriptObject overlays.
  }

  /**
   * A DOM element that will act as a bounding box for the draggable object.
   * 
   * @param element a DOM element taht will be a bounding box.
   */
  public final native DraggableObjectOptions setContainer(Element element) /*-{
    this.container = element;
    return this;
  }-*/;

  /**
   * The cursor to show on mouseover.
   * 
   * @param value The CSS name for the cursor to show on mouse over.
   */
  public final native DraggableObjectOptions setDraggableCursor(String value) /*-{
    this.draggableCursor = value;
    return this;
  }-*/;

  /**
   * The cursor to show while dragging.
   * 
   * @param value The CSS name for the cursor to show while dragging.
   */
  public final native DraggableObjectOptions setDraggingCursor(String value) /*-{
    this.draggingCursor = value;
    return this;
  }-*/;

  /**
   * The left starting position of the object.
   * 
   * @param pos the left starting position of the object.
   */
  public final native DraggableObjectOptions setLeft(int pos) /*-{
    this.left = pos;
    return this;
  }-*/;

  /**
   * The top starting position of the object.
   * 
   * @param pos the top starting position of the object.
   */
  public final native DraggableObjectOptions setTop(int pos) /*-{
    this.top = pos;
    return this;
  }-*/;

}
